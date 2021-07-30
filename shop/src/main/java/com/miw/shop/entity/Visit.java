package com.miw.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "Visit")
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long itemId;
    private LocalDateTime updatedAt = LocalDateTime.now();
}
