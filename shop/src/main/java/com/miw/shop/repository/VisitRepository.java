package com.miw.shop.repository;

import com.miw.shop.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
    Long countByItemId(Long itemId);

    @Query("SELECT v FROM Visit v WHERE v.itemId = ?1")
    List<Visit> findByItemId(Long itemId);
}