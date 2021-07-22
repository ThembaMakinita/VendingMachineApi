package com.jtm.vending.vendingmachine.core.service;

import com.jtm.vending.vendingmachine.commons.constants.CashCalculator;
import com.jtm.vending.vendingmachine.commons.constants.Money;
import com.jtm.vending.vendingmachine.commons.data.CashBundle;
import org.springframework.stereotype.Service;

@Service
public class VendService implements CashCalculator {


    @Override
    public double calculateTotal(CashBundle moneyEntered) {
        return moneyEntered.getTotal();
    }

    @Override
    public CashBundle calculateChange(double amountMoneyToReturn) {

        CashBundle change = new CashBundle(new int[7]);
        double remainingAmount = amountMoneyToReturn;

        change.numberOf50Rands = (int) (remainingAmount / (Money.valueOf("FIFTY_RANDS").getValue()));
        remainingAmount = (int) (remainingAmount % (Money.valueOf("FIFTY_RANDS").getValue()));

        change.numberOf20Rands = (int) (remainingAmount / (Money.valueOf("TWENTY_RANDS").getValue()));
        remainingAmount = (int) (remainingAmount % (Money.valueOf("TWENTY_RANDS").getValue()));

        change.numberOf10Rands = (int) (remainingAmount / (Money.valueOf("TEN_RANDS").getValue()));
        remainingAmount = (int) (remainingAmount % (Money.valueOf("TEN_RANDS").getValue()));

        change.numberOf5Rands = (int) (remainingAmount / (Money.valueOf("FIVE_RANDS").getValue()));
        remainingAmount = (int) (remainingAmount % (Money.valueOf("FIVE_RANDS").getValue()));

        change.numberOf2Rands = (int) (remainingAmount / (Money.valueOf("TWO_RANDS").getValue()));
        remainingAmount = (int) (remainingAmount % (Money.valueOf("TWO_RANDS").getValue()));

        change.numberOf1Rands = (int) (remainingAmount / (Money.valueOf("ONE_RAND").getValue()));
        remainingAmount = (int) (remainingAmount % (Money.valueOf("ONE_RAND").getValue()));

        change.numberOf50Cents = (int) (remainingAmount / (Money.valueOf("FIFTY_CENTS").getValue()));


        return change;
    }
}
