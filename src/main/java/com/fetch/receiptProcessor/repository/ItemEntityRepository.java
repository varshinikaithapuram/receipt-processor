package com.fetch.receiptProcessor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fetch.receiptProcessor.entity.ItemEntity;
import com.fetch.receiptProcessor.entity.ReceiptEntity;

import java.util.List;
import java.util.UUID;

public interface ItemEntityRepository extends JpaRepository<ItemEntity, UUID> {
    List<ItemEntity> findByReceipt(ReceiptEntity receiptEntity);
}