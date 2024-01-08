package com.spring.fakestore.fakestore.controllers;

import com.spring.fakestore.fakestore.models.BookingModel;
import com.spring.fakestore.fakestore.models.Proposal;
import com.spring.fakestore.fakestore.repository.BookingModelRepository;
import com.spring.fakestore.fakestore.repository.ProposalRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "proposal")
public class ProposalController {
    private ProposalRepository proposalRepository;

    public ProposalController(ProposalRepository proposalRepository) {
        this.proposalRepository = proposalRepository;
    }
    @GetMapping(path = "getall",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Proposal>> getall()
    {
        return ResponseEntity.ok(proposalRepository.getAll());
    }
    @GetMapping(path = "getbyid/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Proposal> getbyid(@PathVariable(name = "id") long id)
    {
        try{
            return ResponseEntity.ok(proposalRepository.getById(id));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }
    @PutMapping(path = "updatestatus/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateStatus(@PathVariable(name = "id") long id)
    {
        String messages ="Updated your uptaded data is -->"+proposalRepository.getById(id);
        try
        {
            boolean result = proposalRepository.updateStatus(id);
            if (result)
            {
                return ResponseEntity.ok(messages);
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
