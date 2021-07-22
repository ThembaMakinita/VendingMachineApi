package com.jtm.vending.vendingmachine.commons.constants;


public enum Money {

    FIFTY_CENTS(0.5),
    ONE_RAND(1.0),
    TWO_RANDS(2.0),
    FIVE_RANDS(5.0),
    TEN_RANDS(10.0),
    TWENTY_RANDS(20.0),
    FIFTY_RANDS(50.0);

    private double value;

    Money(double value){
        this.value = value;
    }

    public double getValue(){
        return this.value;
    }
}
