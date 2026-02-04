package com.example.todolist.filter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.example.todolist.repositories.UsersRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;


@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var servletpath = request.getServletPath();

        if (servletpath.startsWith("/tasks/")){
            // Pega a autenticação (usuário e senha)
            var authorization = request.getHeader("Authorization");
            var authEncoded = authorization.substring("Basic".length()).trim();

            byte[] authDecode = Base64.getDecoder().decode(authEncoded);

            var authSrtring = new String(authDecode);

            System.out.println("Authorization");
            System.out.println(authSrtring);

            String[] credentials = authSrtring.split(":");
            String username = credentials[0];
            String password = credentials[1];

            System.out.println(username);
            System.out.println(password);

            // Valida usuário
            var user = this.usersRepository.findByuserName(username);
            if(user == null){
                response.sendError(401); //Usuário sem permissão
            }else {
                // Validar senha
                var passwordVerifyv = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                if (passwordVerifyv.verified){
                    request.setAttribute("idUser", user.getId());
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(401);
                }
                // Se tudo estiver correto, segue o processo

            }
        } else {
            filterChain.doFilter(request, response);
        }

    }
}
