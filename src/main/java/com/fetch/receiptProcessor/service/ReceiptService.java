package com.fetch.receiptProcessor.service;

import java.util.UUID;

import com.fetch.receiptProcessor.entity.ReceiptEntity;
import com.fetch.receiptProcessor.model.PointsResponseModel;

public interface ReceiptService {
    UUID processReceipt(ReceiptEntity receipt);
    PointsResponseModel getPoints(UUID id);
}