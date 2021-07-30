package com.miw.shop.service;

import com.miw.shop.entity.Visit;
import com.miw.shop.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitService {
    @Autowired
    private VisitRepository visitRepository;

    public List<Visit> findAll() {
        return (List<Visit>) visitRepository.findAll();
    }

    public List<Visit> findByItemId(Long itemId) {
        return (List<Visit>) visitRepository.findByItemId(itemId);
    }

    public Optional<Visit> findById(Long id) {
        return visitRepository.findById(id);
    }

    public Visit save(Visit visit) {
        return visitRepository.saveAndFlush(visit);
    }
}
