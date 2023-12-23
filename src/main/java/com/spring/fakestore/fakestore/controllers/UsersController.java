package com.spring.fakestore.fakestore.controllers;


import com.spring.fakestore.fakestore.models.Users;
import com.spring.fakestore.fakestore.repository.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "users")
public class UsersController {
    private UsersRepository usersRepository;

    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    @GetMapping(path = "getall",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Users>> getall()
    {
        return ResponseEntity.ok(usersRepository.getAll());
    }
    @GetMapping(path = "getbyid/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> getbyid(@PathVariable(name = "id") long id)
    {
        try{
            return ResponseEntity.ok(usersRepository.getById(id));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }
    @PostMapping(path = "save",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> save(@RequestBody Users users)
    {
        try
        {
            boolean result=usersRepository.save(users);
            if (result)
            {
                return  ResponseEntity.ok("Saved");
            }
            else {
                return  ResponseEntity.ok("UnSaved!");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Internal Server hatası");
        }
    }
    @DeleteMapping(path = "deletebyid/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deletebyid(@PathVariable(name ="id") long id)
    {
        try
        {
            boolean result=usersRepository.deleteById(id);
            if (result)
            {
                return  ResponseEntity.ok("DELETED");
            }
            else {
                return  ResponseEntity.ok("UnSaved!");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Internal Server hatası");
        }
    }
}
