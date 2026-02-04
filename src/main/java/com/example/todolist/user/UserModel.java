package com.example.todolist.user;

import jakarta.persistence.*;

import java.util.UUID;

@Entity (name = "users")
public class UserModel {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    @Column(unique = true)
    private String userName;
    private String name;
    private String password;

    public UserModel() {
    }

    public UserModel(UUID id, String userName, String name, String password) {
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.password = password;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
