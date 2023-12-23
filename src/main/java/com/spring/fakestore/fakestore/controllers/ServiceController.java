package com.spring.fakestore.fakestore.controllers;

import com.spring.fakestore.fakestore.models.Service;
import com.spring.fakestore.fakestore.models.Users;
import com.spring.fakestore.fakestore.repository.ServiceRepository;
import com.spring.fakestore.fakestore.repository.UsersRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = "service")
public class ServiceController {
    private ServiceRepository serviceRepository;

    public ServiceController(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @GetMapping(path = "getall",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Service>> getall()
    {
        return ResponseEntity.ok(serviceRepository.getAll());
    }
    @GetMapping(path = "getbyid/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Service> getbyid(@PathVariable(name = "id") long id)
    {
        try{
            return ResponseEntity.ok(serviceRepository.getById(id));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }
    @PostMapping(path = "save",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> save(@RequestBody Service service)
    {
        try
        {
            boolean result=serviceRepository.save(service);
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
            boolean result=serviceRepository.deleteById(id);
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
    @PostMapping(path = "update",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@RequestBody Service service)
    {
        try
        {
            boolean result = serviceRepository.update(service);
            if (result)
            {
                return ResponseEntity.ok("Updated");
            }
            else
            {
                return ResponseEntity.internalServerError().body("Not Updated");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Internal 500 error.");
        }
    }
}
