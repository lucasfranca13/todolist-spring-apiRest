package com.example.todolist.user;


import at.favre.lib.crypto.bcrypt.BCrypt;
import com.example.todolist.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

/*
*  body
*/
    @Autowired
    private UsersRepository usersRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel user){
        var userAux = this.usersRepository.findByuserName(user.getUserName());

        if(userAux != null){
            // Mensagem de Erro
            // Status Code
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
        }

        var passWordHashred = BCrypt.withDefaults().
                hashToString(12,user.getPassword().toCharArray());

        user.setPassword(passWordHashred);

        var userCreated = this.usersRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
}
