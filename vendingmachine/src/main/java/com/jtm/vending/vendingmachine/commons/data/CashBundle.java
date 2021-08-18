package com.jtm.vending.vendingmachine.commons.data;

import com.jtm.vending.vendingmachine.commons.constants.Money;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CashBundle {

    private int numberOf50Cents;
    private int numberOf1Rands;
    private int numberOf2Rands;
    private int numberOf5Rands;
    private int numberOf10Rands;
    private int numberOf20Rands;
    private int numberOf50Rands;

    public CashBundle(){
    }

    public CashBundle(int numberOf50Cents, int numberOf1Rands, int numberOf2Rands, int numberOf5Rands, int numberOf10Rands, int numberOf20Rands, int numberOf50Rands) {
        this.numberOf50Cents = numberOf50Cents;
        this.numberOf1Rands = numberOf1Rands;
        this.numberOf2Rands = numberOf2Rands;
        this.numberOf5Rands = numberOf5Rands;
        this.numberOf10Rands = numberOf10Rands;
        this.numberOf20Rands = numberOf20Rands;
        this.numberOf50Rands = numberOf50Rands;
    }


    public double getTotal(){
        double total = 0;
        total += numberOf50Cents  * (Money.valueOf("FIFTY_CENTS").getValue());
        total += numberOf1Rands  * (Money.valueOf("ONE_RAND").getValue());
        total += numberOf2Rands  * (Money.valueOf("TWO_RANDS").getValue());
        total += numberOf5Rands  * (Money.valueOf("FIVE_RANDS").getValue());
        total += numberOf10Rands  * (Money.valueOf("TEN_RANDS").getValue());
        total += numberOf20Rands  * (Money.valueOf("TWENTY_RANDS").getValue());
        total += numberOf50Rands  * (Money.valueOf("FIFTY_RANDS").getValue());
        return total;
    }
}
