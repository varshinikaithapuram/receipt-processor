package com.fetch.receiptProcessor.utils;



public final class PointsConstants {

    public static final int ALPHANUMERIC_POINT = 1;
    public static final int ROUND_DOLLAR_POINTS = 50;
    public static final int MULTIPLE_OF_25_POINTS = 25;
    public static final int EVERY_TWO_ITEMS_POINTS = 5;
    public static final double LENGTH_OF_ITEM_DESCRIPTION_POINTS = 0.2;
    public static final int ODD_DATE_POINTS = 6;
    public static final int TIME_OF_PURCHASE_POINTS = 10;

    private PointsConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
