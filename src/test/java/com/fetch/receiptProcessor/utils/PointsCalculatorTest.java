package com.fetch.receiptProcessor.utils;


import com.fetch.receiptProcessor.entity.ItemEntity;
import com.fetch.receiptProcessor.entity.ReceiptEntity;
import com.fetch.receiptProcessor.model.PointsResponseModel;
import com.fetch.receiptProcessor.utils.PointsCalculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class PointsCalculatorTest {

    private PointsCalculator calculator;

    @BeforeEach
    public void setUp() {
    	calculator = new PointsCalculator();
    }

    @Test
    public void testCalculateAlphanumericPoint() {
        String retailerName = "ABC123   ";
        int points = PointsCalculator.calculateRetailerPoints(retailerName);
        Assertions.assertEquals(6, points);
    }
    @Test
    public void testCalculateAlphanumericPointNegative() {
        String input = "  Hello&&&1_2.3";
        int points = PointsCalculator.calculateRetailerPoints(input);
        Assertions.assertEquals(8, points);
    }

    @Test
    public void testCalculateRoundedDollarAmountWithNoCents() {
        double total = 10.0;
        int points = calculator.calculateRoundedDollarWithNoCents(total);
        Assertions.assertEquals(50, points);
    }

    @Test
    public void testCalculateMultipleOf025() {
        double total = 0.75;
        int points = calculator.calculateMultipleOf25(total);
        Assertions.assertEquals(25, points);
    }
    @Test
    public void testCalculateMultipleOf025WholeNumber() {
        double total = 1.00;
        int points = calculator.calculateMultipleOf25(total);
        Assertions.assertEquals(25, points);
    }
    @Test
    public void testCalculateMultipleOf025Negative() {
        double total = 1.05;
        int points = calculator.calculateMultipleOf25(total);
        Assertions.assertEquals(0, points);
    }

    @Test
    public void testCalculateEveryTwoItemsOnTheReceiptSizeOddNumber() {
        int size = 5;
        int points = calculator.calculateEveryTwoItemsOnTheReceipt(size);
        Assertions.assertEquals(10, points);
    }

    @Test
    public void testCalculateEveryTwoItemsOnTheReceiptSizeEvenNumber() {
        int size = 10;
        int points = calculator.calculateEveryTwoItemsOnTheReceipt(size);
        Assertions.assertEquals(25, points);
    }

    @Test
    public void testCalculateLengthOfTheItemDescription() {
        ReceiptEntity purchaseEntity = new ReceiptEntity();
        purchaseEntity.setItems(new ArrayList<>());
        purchaseEntity.getItems().add(ItemEntity.builder().shortDescription("Mountain Dew 12PK").price(6.49).build());
        purchaseEntity.getItems().add(ItemEntity.builder().shortDescription("Emils Cheese Pizza").price(12.25).build());
        purchaseEntity.getItems().add(ItemEntity.builder().shortDescription("Knorr Creamy Chicken").price(1.26).build());
        purchaseEntity.getItems().add(ItemEntity.builder().shortDescription("Doritos Nacho Cheese").price(3.35).build());
        purchaseEntity.getItems().add(ItemEntity.builder().shortDescription("   Klarbrunn 12-PK 12 FL OZ  ").price(12.00).build());

        int points = calculator.calculateItemPointsByDescription(purchaseEntity);
        Assertions.assertEquals(6 , points);
    }

    @Test
    public void testCalculateOnPurchaseDate() {
        String purchaseDate = "2023-06-01";
        int points = PointsCalculator.calculatePurchaseDatePoints(purchaseDate);
        Assertions.assertEquals(6, points);
    }
    @Test
    public void testCalculateOnPurchaseDateNegative() {
        String purchaseDate = "2023-06-10";
        int points = PointsCalculator.calculatePurchaseDatePoints(purchaseDate);
        Assertions.assertEquals(0, points);
    }

    @Test
    public void testCalculateTimeOfPurchase() {
        String purchaseTime = "15:30";
        int points = PointsCalculator.calculatePurchaseTimePoints(purchaseTime);
        Assertions.assertEquals(10, points);
    }
    @Test
    public void testCalculateTimeOfPurchaseNegative() {
        String purchaseTime = "03:30";
        int points = PointsCalculator.calculatePurchaseTimePoints(purchaseTime);
        Assertions.assertEquals(0, points);
    }

    @Test
    public void testCalculatePoints() {
        ReceiptEntity purchaseEntity = new ReceiptEntity();
        purchaseEntity.setItems(new ArrayList<>());
        purchaseEntity.setRetailer("Target");
        purchaseEntity.setTotal(35.35);
        purchaseEntity.setPurchaseDate("2022-01-01");
        purchaseEntity.setPurchaseTime("13:01");
        purchaseEntity.getItems().add(ItemEntity.builder().shortDescription("Mountain Dew 12PK").price(6.49).build());
        purchaseEntity.getItems().add(ItemEntity.builder().shortDescription("Emils Cheese Pizza").price(12.25).build());
        purchaseEntity.getItems().add(ItemEntity.builder().shortDescription("Knorr Creamy Chicken").price(1.26).build());
        purchaseEntity.getItems().add(ItemEntity.builder().shortDescription("Doritos Nacho Cheese").price(3.35).build());
        purchaseEntity.getItems().add(ItemEntity.builder().shortDescription("   Klarbrunn 12-PK 12 FL OZ  ").price(12.00).build());

        PointsResponseModel pointsDTO = calculator.calculatePoints(purchaseEntity);
        Assertions.assertEquals(28, pointsDTO.getPoints());
    }

    @Test
    public void testCalculatePointsSecondTestCase() {
        ReceiptEntity purchaseEntity = new ReceiptEntity();
        purchaseEntity.setItems(new ArrayList<>());
        purchaseEntity.setRetailer("M&M Corner Market");
        purchaseEntity.setTotal(9.00);
        purchaseEntity.setPurchaseDate("2022-03-20");
        purchaseEntity.setPurchaseTime("14:33");
        purchaseEntity.getItems().add(ItemEntity.builder().shortDescription("Gatorade").price(2.25).build());
        purchaseEntity.getItems().add(ItemEntity.builder().shortDescription("Gatorade").price(2.25).build());
        purchaseEntity.getItems().add(ItemEntity.builder().shortDescription("Gatorade").price(2.25).build());
        purchaseEntity.getItems().add(ItemEntity.builder().shortDescription("Gatorade").price(2.25).build());
        PointsResponseModel pointsDTO = calculator.calculatePoints(purchaseEntity);
        Assertions.assertEquals(109, pointsDTO.getPoints());
    }
}
