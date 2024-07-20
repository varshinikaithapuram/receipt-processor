package com.fetch.receiptProcessor.service.impl;

import com.fetch.receiptProcessor.entity.ItemEntity;
import com.fetch.receiptProcessor.entity.ReceiptEntity;
import com.fetch.receiptProcessor.exceptions.CalculationException;
import com.fetch.receiptProcessor.exceptions.ReceiptNotFoundException;
import com.fetch.receiptProcessor.exceptions.ReceiptProcessingException;
import com.fetch.receiptProcessor.model.PointsResponseModel;
import com.fetch.receiptProcessor.repository.ItemEntityRepository;
import com.fetch.receiptProcessor.repository.ReceiptEntityRepository;
import com.fetch.receiptProcessor.service.ReceiptService;
import com.fetch.receiptProcessor.utils.PointsCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReceiptServiceImpl implements ReceiptService{

    @Autowired
    private ReceiptEntityRepository receiptRepository;
    @Autowired
    private ItemEntityRepository itemEntityRepository;


    @Transactional
    public UUID processReceipt(ReceiptEntity receiptEntity) {

        try {
            ReceiptEntity entity = ReceiptEntity.builder()
                    .retailer(receiptEntity.getRetailer())
                    .purchaseDate(receiptEntity.getPurchaseDate())
                    .purchaseTime(receiptEntity.getPurchaseTime())
                    .total(receiptEntity.getTotal())
                    .build();

            ReceiptEntity receipt = receiptRepository.save(entity);

            List<ItemEntity> items = receiptEntity.getItems().stream()
                    .map(itemEntity -> ItemEntity.builder()
                            .shortDescription(itemEntity.getShortDescription())
                            .price(itemEntity.getPrice())
                            .receipt(receipt)
                            .build())
                    .collect(Collectors.toList());

            itemEntityRepository.saveAll(items);
            return receipt.getId();
        } catch (Exception e) {
            throw new ReceiptProcessingException("Failed to process the receipt", e);
        }
    }

    public PointsResponseModel getPoints(UUID id) {
        PointsCalculator calculator = new PointsCalculator();
        PointsResponseModel points = null;
        Optional<ReceiptEntity> receiptEntity = receiptRepository.findById(id);
        ReceiptEntity receipt = receiptEntity.orElseThrow(() -> new ReceiptNotFoundException(id));
        try {
        points = calculator.calculatePoints(receipt);
        }
        catch(Exception e) {
        	throw new CalculationException("Error calculating points");
        }
        return points;
    }

}
