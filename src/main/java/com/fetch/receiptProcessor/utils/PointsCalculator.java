package com.fetch.receiptProcessor.utils;

import com.fetch.receiptProcessor.entity.ItemEntity;
import com.fetch.receiptProcessor.entity.ReceiptEntity;
import com.fetch.receiptProcessor.model.PointsResponseModel;


import java.time.LocalDate;
import java.time.LocalTime;

public class PointsCalculator {

    public static int calculateRetailerPoints(String retailer) {
        return (int) retailer.chars().filter(Character::isLetterOrDigit).count() * PointsConstants.ALPHANUMERIC_POINT;
    }

    public int calculateRoundedDollarWithNoCents(double total){
        return total % 1==0 ? PointsConstants.ROUND_DOLLAR_POINTS : 0;
    }

    public int calculateMultipleOf25(double total){
        return total%0.25 ==0 ? PointsConstants.MULTIPLE_OF_25_POINTS : 0;
    }

    public int calculateEveryTwoItemsOnTheReceipt(int size){
        return (size/2)* PointsConstants.EVERY_TWO_ITEMS_POINTS;
    }

    public int calculateItemPointsByDescription (ReceiptEntity purchaseEntity){
        int total =0; 
        for (ItemEntity item: purchaseEntity.getItems()) {
            if(item.getShortDescription().trim().length()%3==0){
                total= total+ (int) Math.ceil (item.getPrice()* PointsConstants.LENGTH_OF_ITEM_DESCRIPTION_POINTS);
            }
        }
        return  total;
    }

    public static int calculatePurchaseDatePoints(String purchaseDate) {
        LocalDate date = LocalDate.parse(purchaseDate);
        return date.getDayOfMonth() % 2 != 0 ? PointsConstants.ODD_DATE_POINTS : 0;
    }
    
    public static int calculatePurchaseTimePoints(String purchaseTime) {
        LocalTime time = LocalTime.parse(purchaseTime);
        return (time.isAfter(LocalTime.of(14, 0)) && time.isBefore(LocalTime.of(16, 0))) ? PointsConstants.TIME_OF_PURCHASE_POINTS : 0;
    }

    public PointsResponseModel calculatePoints(ReceiptEntity purchaseEntity) {
        int points =0;
        points += calculateRetailerPoints(purchaseEntity.getRetailer());
        points += calculateRoundedDollarWithNoCents(purchaseEntity.getTotal());
        points += calculateMultipleOf25(purchaseEntity.getTotal());
        points +=calculateEveryTwoItemsOnTheReceipt(purchaseEntity.getItems().size());
        points +=calculateItemPointsByDescription(purchaseEntity);
        points +=calculatePurchaseDatePoints(purchaseEntity.getPurchaseDate());
        points +=calculatePurchaseTimePoints(purchaseEntity.getPurchaseTime());
        return  PointsResponseModel.builder().points(points).build();
    }

}
