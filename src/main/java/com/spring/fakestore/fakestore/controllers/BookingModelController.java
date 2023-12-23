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
            boolean result=bookingModelRepository.save(bookingModel);
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
}
