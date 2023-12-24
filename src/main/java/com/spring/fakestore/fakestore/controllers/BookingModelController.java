package com.spring.fakestore.fakestore.controllers;

import com.spring.fakestore.fakestore.models.BookingModel;
import com.spring.fakestore.fakestore.models.Users;
import com.spring.fakestore.fakestore.repository.BookingModelRepository;
import com.spring.fakestore.fakestore.repository.UsersRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "bookingmodel")
public class BookingModelController {
    private BookingModelRepository bookingModelRepository;

    public BookingModelController(BookingModelRepository bookingModelRepository) {
        this.bookingModelRepository = bookingModelRepository;
    }
    @GetMapping(path = "getall",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookingModel>> getall()
    {
        return ResponseEntity.ok(bookingModelRepository.getAll());
    }
    @GetMapping(path = "getallwithdate",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookingModel>> getallwithdate()
    {
        return ResponseEntity.ok(bookingModelRepository.getAllWithDate());
    }
    @GetMapping(path = "getbyid/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookingModel> getbyid(@PathVariable(name = "id") long id)
    {
        try{
            return ResponseEntity.ok(bookingModelRepository.getById(id));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }
    @PostMapping(path = "save",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> save(@RequestBody BookingModel bookingModel)
    {
        try
        {
            Long savedId = bookingModelRepository.save(bookingModel);
            String messages ="Saved -> Your booking id is ->"+bookingModelRepository.lastItem();
            if (savedId != null)
            {
                return  ResponseEntity.ok(messages);
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
            boolean result=bookingModelRepository.deleteById(id);
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
    public ResponseEntity<String> update(@RequestBody BookingModel bookingModel)
    {
        try
        {
            boolean result = bookingModelRepository.update(bookingModel);
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
    @PutMapping(path = "updatestatus/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateStatus(@PathVariable(name = "id") long id)
    {
        try
        {
            boolean result = bookingModelRepository.updateStatus(id);
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
    @PutMapping(path = "completestatus/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> completestatus(@PathVariable(name = "id") long id)
    {
        try
        {
            boolean result = bookingModelRepository.completeStatus(id);
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
