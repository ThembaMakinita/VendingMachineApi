package com.jtm.vending.vendingmachine.core.model;

import lombok.Data;

@Data
public class CashDTO {
    private String respMSG;
    private double totalChangeOutput;
    private double totalAmmountInserted;
    private int numberOf50Cents;
    private int numberOf1Rands;
    private int numberOf2Rands;
    private int numberOf5Rands;
    private int numberOf10Rands;
    private int numberOf20Rands;
    private int numberOf50Rands;
}
