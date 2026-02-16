package com.example.todolist.task;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "tb_tasks")
@Schema(description = "Entidade que representa uma tarefa")
public class TaskModel {

    /*
    *
    * ID
    * Usuário
    * Descrição
    * Título
    * Data de Início
    * Data de término
    * Prioridade
    *
    * */

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    @Schema(description = "Descrição da tarefa", example = "Tarefa para auxiliar na rotina de trabalho.")
    private String description;

    @Column(length = 50)
    @Schema(description = "Título da tarefa", example = "Primeira Tarefa")
    private String title;
    @Schema(description = "Data Início", example = "2026-02-08T13:30:15")
    private LocalDateTime startAt;
    @Schema(description = "Data Fim", example = "2026-02-12T17:00:00")
    private LocalDateTime endAt;
    @Schema(description = "Prioridade", example = "Alta")
    private String priority;

    @CreationTimestamp
    private LocalDateTime createdAt;


    private UUID idUser;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws Exception {
        if(title.length() > 50){
            throw new Exception("O campo title deve conter no máximo 50 caracteres");
        }

        this.title = title;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalDateTime startAt) {
        this.startAt = startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

    public void setEndAt(LocalDateTime endAt) {
        this.endAt = endAt;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UUID getIdUser() {
        return idUser;
    }

    public void setIdUser(UUID idUser) {
        this.idUser = idUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
