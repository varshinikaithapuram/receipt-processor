package com.fetch.receiptProcessor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptModel {
    private String retailer;
    private String purchaseDate;
    private String purchaseTime;
    private List<ItemModel> items;
    private String total;
}
