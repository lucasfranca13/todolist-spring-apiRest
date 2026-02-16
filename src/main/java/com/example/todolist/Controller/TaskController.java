package com.example.todolist.Controller;

import com.example.todolist.task.ItaskRepository;
import com.example.todolist.task.TaskModel;
import com.example.todolist.utils.Utils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@Tag(name = "Tasks", description = "Operações relacionadas às tarefas do usuário")
public class TaskController extends TaskModel {

    @Autowired
    private ItaskRepository taskRepository;

    @PostMapping("/")
    @Operation(summary = "Criar nova tarefa",
            security = @SecurityRequirement(name = "basicAuth"))
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request){
        var idUser = request.getAttribute( "idUser");
        taskModel.setIdUser((UUID) idUser);

        var currentDate = LocalDateTime.now();

        if(currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de início ou data de término deve ser maior que a data atual!");
        }

        if(taskModel.getStartAt().isAfter(taskModel.getEndAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("A data de início deve ser menor que a data de término");
        }

        var task = this.taskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @GetMapping("/")
    @Operation(summary = "Listar tarefas do usuário autenticado",
            security = @SecurityRequirement(name = "basicAuth"))
    public List<TaskModel> list(HttpServletRequest request) {
        var idUser = (UUID) request.getAttribute("idUser");
        return this.taskRepository.findByIdUser(idUser);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar tarefa existente",
            security = @SecurityRequirement(name = "basicAuth"))
    public ResponseEntity update(
            @RequestBody TaskModel taskModel,
            @PathVariable UUID id,
            HttpServletRequest request
    ) {
        var idUser = (UUID) request.getAttribute("idUser");

        var optionalTask = this.taskRepository.findById(id);

        if (optionalTask.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Tarefa não encontrada");
        }

        var task = optionalTask.get();

        // Garante que a task pertence ao usuário
        if (!task.getIdUser().equals(idUser)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Você não tem permissão para alterar esta tarefa");
        }

        Utils.copyNonNullProprities(taskModel, task);

        task.setId(id);
        task.setIdUser(idUser);
        return ResponseEntity.ok(this.taskRepository.save(task));
    }
}
