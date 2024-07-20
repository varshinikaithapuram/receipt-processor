package com.fetch.receiptProcessor.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@Entity
@Table(name = "receipt_table")
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "receipt_id", nullable = false, columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "retailer", nullable = false)
    private String retailer;
    @Column(name = "purchaseDate", nullable = false)
    private String purchaseDate;
    @Column(name = "purchaseTime", nullable = false)
    private String purchaseTime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receipt")
    private List<ItemEntity> items;
    @Column(name = "total", nullable = false)
    private double total;
}
