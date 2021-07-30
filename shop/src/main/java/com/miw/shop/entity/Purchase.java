package com.miw.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "Purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long userId;
    private double total;
    private LocalDateTime updatedAt = LocalDateTime.now();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<PurchaseItem> purchaseItems = new ArrayList<PurchaseItem>();
}
