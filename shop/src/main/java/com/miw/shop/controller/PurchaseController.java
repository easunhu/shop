package com.miw.shop.controller;

import com.miw.shop.entity.Item;
import com.miw.shop.entity.Purchase;
import com.miw.shop.entity.PurchaseItem;
import com.miw.shop.service.ItemService;
import com.miw.shop.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    private PurchaseService purchaseService;
    private ItemService itemService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService, ItemService itemService) {
        this.purchaseService = purchaseService;
        this.itemService = itemService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getPurchaseById(@PathVariable(value = "id") Long purchaseId) {
        try {
            Optional<Purchase> opt = purchaseService.findById(purchaseId);
            if (!opt.isPresent()) {
                return ResponseHandler.generateResponse("No data found!", HttpStatus.OK, null);
            }
            return ResponseHandler.generateResponse("Data retrieved!", HttpStatus.OK, opt.get());
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @GetMapping(value = "/")
    public ResponseEntity<Object> getAllPurchases() {
        try {
            List<Purchase> purchases = purchaseService.findAll();
            return ResponseHandler.generateResponse("Data retrieved!", HttpStatus.OK, purchases);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PostMapping(
            value = "/createPurchase", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<Object> createPurchase(@RequestBody Purchase purchase) {
        try {
            /* validate purchase quantity with item remaining, return out-of-stock message if can not fulfill the purchase order.
             * decrease the item remaining with the purchase quantity, wrap in one transaction.
             */
            for (PurchaseItem pi : purchase.getPurchaseItems()) {
                Optional<Item> opt = itemService.findById(pi.getItemId());
                if (opt.isPresent() && pi.getQuantity() > opt.get().getRemaining()) {
                    return ResponseHandler.generateResponse(format("Item %s out of stock!", pi.getItemId()), HttpStatus.MULTI_STATUS, null);
                }
                Item item = opt.get();
                item.setRemaining(item.getRemaining() - pi.getQuantity());
                itemService.save(item);
                pi.setUpdatedAt(LocalDateTime.now());
            }
            purchase.setUpdatedAt(LocalDateTime.now());
            Purchase savedPurchase = purchaseService.save(purchase);
            return ResponseHandler.generateResponse("Purchase created!", HttpStatus.OK, savedPurchase);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
}