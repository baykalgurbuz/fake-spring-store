package com.spring.fakestore.fakestore.controllers;


import com.spring.fakestore.fakestore.models.Users;
import com.spring.fakestore.fakestore.repository.UsersRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "users")
public class UsersController {
    private UsersRepository usersRepository;

    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    @GetMapping(value = "getall",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Users>> getall()
    {
        return ResponseEntity.ok(usersRepository.getAll());
    }

}
