package com.miw.shop.controller;

import com.miw.shop.entity.Item;
import com.miw.shop.entity.Visit;
import com.miw.shop.service.ItemService;
import com.miw.shop.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private ItemService itemService;
    private VisitService visitService;

    @Autowired
    public ItemController(ItemService itemService, VisitService visitService) {
        this.itemService = itemService;
        this.visitService = visitService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getItemById(@PathVariable(value = "id") Long itemId) {
        try {
            Optional<Item> item = itemService.findById(itemId);
            if (!item.isPresent()) {
                return ResponseHandler.generateResponse("No data found!", HttpStatus.OK, null);
            }
            Visit visit = Visit.builder().itemId(itemId).updatedAt(now()).build();
            visitService.save(visit);

            /*apply price surge, count visits for last 1 hour, if more than 10, price increase 10%*/
            Item it = item.get();
            List<Visit> visits = visitService.findByItemId(itemId);
            if (visits != null && countOneHourVisit(visits) > 10) {
                it.setPrice((int) (1.1 * it.getPrice()));
            }
            return ResponseHandler.generateResponse("Data retrieved!", HttpStatus.OK, it);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @GetMapping(value = "/")
    public ResponseEntity<Object> getAllItems() {
        try {
            List<Item> items = itemService.findAll();
            return ResponseHandler.generateResponse("Data retrieved!", HttpStatus.OK, items);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    //count visits for last 1 hour
    private long countOneHourVisit(List<Visit> visits) {
        return visits.stream().dropWhile(v -> ChronoUnit.HOURS.between(v.getUpdatedAt(), LocalDateTime.now()) > 1).count();
    }
}