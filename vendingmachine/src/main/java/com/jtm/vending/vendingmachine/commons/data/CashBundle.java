package com.jtm.vending.vendingmachine.commons.data;

import com.jtm.vending.vendingmachine.commons.constants.Money;
import lombok.Data;

@Data
public class CashBundle {

    public int numberOf50Cents;
    public int numberOf1Rands;
    public int numberOf2Rands;
    public int numberOf5Rands;
    public int numberOf10Rands;
    public int numberOf20Rands;
    public int numberOf50Rands;

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

    public CashBundle (int... enteredMoney){
        this.numberOf50Cents = enteredMoney[0];
        this.numberOf1Rands = enteredMoney[1];
        this.numberOf2Rands = enteredMoney[2];
        this.numberOf5Rands = enteredMoney[3];
        this.numberOf10Rands = enteredMoney[4];
        this.numberOf20Rands = enteredMoney[5];
        this.numberOf50Rands = enteredMoney[6];
    }

    public double getTotal(){
        double total = 0;
        System.out.println("****************** YESSS *****************");
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
