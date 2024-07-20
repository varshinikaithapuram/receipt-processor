package com.fetch.receiptProcessor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fetch.receiptProcessor.entity.ReceiptEntity;

import java.util.UUID;

public interface ReceiptEntityRepository extends JpaRepository<ReceiptEntity, UUID> {

}