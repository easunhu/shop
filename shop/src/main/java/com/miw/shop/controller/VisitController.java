package com.miw.shop.controller;

import com.miw.shop.entity.Visit;
import com.miw.shop.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/visits")
public class VisitController {

    private VisitService visitService;

    @Autowired
    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getVisitById(@PathVariable(value = "id") Long visitId) {
        try {
            Optional<Visit> opt = visitService.findById(visitId);
            if (!opt.isPresent()) {
                return ResponseHandler.generateResponse("No data found!", HttpStatus.OK, null);
            }
            return ResponseHandler.generateResponse("Data retrieved!", HttpStatus.OK, opt.get());
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @GetMapping(value = "/itemId/{id}")
    public ResponseEntity<Object> getVisitsByItemId(@PathVariable(value = "id") Long itemId) {
        try {
            List<Visit> visits = visitService.findByItemId(itemId);
            return ResponseHandler.generateResponse("Data retrieved!", HttpStatus.OK, visits);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @GetMapping(value = "/")
    public ResponseEntity<Object> getAllVisits() {
        try {
            List<Visit> visits = visitService.findAll();
            return ResponseHandler.generateResponse("Data retrieved!", HttpStatus.OK, visits);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
}