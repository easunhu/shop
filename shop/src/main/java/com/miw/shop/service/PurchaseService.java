package com.miw.shop.service;

import com.miw.shop.entity.Purchase;
import com.miw.shop.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;

    public List<Purchase> findAll() {
        return (List<Purchase>) purchaseRepository.findAll();
    }

    public Optional<Purchase> findById(Long id) {
        return purchaseRepository.findById(id);
    }

    public Purchase save(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }
}
