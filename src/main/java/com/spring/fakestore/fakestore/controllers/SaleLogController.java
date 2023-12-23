package com.spring.fakestore.fakestore.controllers;

import com.spring.fakestore.fakestore.models.SaleLog;
import com.spring.fakestore.fakestore.models.Users;
import com.spring.fakestore.fakestore.repository.SaleLogRepository;
import com.spring.fakestore.fakestore.repository.UsersRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "salelog")
public class SaleLogController {
    private SaleLogRepository saleLogRepository;

    public SaleLogController(SaleLogRepository saleLogRepository) {
        this.saleLogRepository = saleLogRepository;
    }
    @GetMapping(path = "getall",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SaleLog>> getall()
    {
        return ResponseEntity.ok(saleLogRepository.getAll());
    }
    @GetMapping(path = "getbyid/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SaleLog> getbyid(@PathVariable(name = "id") long id)
    {
        try{
            return ResponseEntity.ok(saleLogRepository.getById(id));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }
    @PostMapping(path = "save",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> save(@RequestBody SaleLog saleLog)
    {
        try
        {
            boolean result=saleLogRepository.save(saleLog);
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
            boolean result=saleLogRepository.deleteById(id);
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
    public ResponseEntity<String> update(@RequestBody SaleLog saleLog)
    {
        try
        {
            boolean result = saleLogRepository.update(saleLog);
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
