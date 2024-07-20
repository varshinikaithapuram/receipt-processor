package com.fetch.receiptProcessor.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Data
@Builder
@Entity
@Table(name = "item_table")
@NoArgsConstructor
@AllArgsConstructor
public class ItemEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "item_id", nullable = false, columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "shortDescription", nullable = false)
    private String shortDescription;
    @Column(name = "price", nullable = false)
    private double price;
    @ManyToOne
    @JoinColumn(name = "receipt_id")
    private ReceiptEntity receipt;

}
