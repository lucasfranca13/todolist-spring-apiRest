package com.example.todolist.repositories;

import com.example.todolist.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UserModel, Integer> {
    UserModel findByuserName(String userName);
}
