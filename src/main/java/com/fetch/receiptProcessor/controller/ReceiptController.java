package com.fetch.receiptProcessor.controller;


import com.fetch.receiptProcessor.entity.ReceiptEntity;
import com.fetch.receiptProcessor.model.IdResponseModel;
import com.fetch.receiptProcessor.model.PointsResponseModel;
import com.fetch.receiptProcessor.service.ReceiptService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/receipts")
@RequiredArgsConstructor
public class ReceiptController {

    public  ReceiptController(ReceiptService receiptService){
        this.receiptService =receiptService;
         }

    @Autowired
    private ReceiptService receiptService;
    
    @PostMapping("/process")
    public ResponseEntity<IdResponseModel> addPurchase(@RequestBody @NonNull ReceiptEntity receiptModel){
       return ResponseEntity.ok().body(IdResponseModel.builder().id(receiptService.processReceipt(receiptModel)).build());
    }

    @GetMapping("/{id}/points")
    public ResponseEntity<PointsResponseModel> getPoints(@PathVariable @NonNull UUID id){
        return ResponseEntity.ok().body(receiptService.getPoints(id));
    }

}
