package com.qa.ToDoList.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.ToDoList.domain.User;

@Repository //collection of method signatures 
public interface UserRepo extends JpaRepository <User, Long >{ //methods needs to be used for the database

}
