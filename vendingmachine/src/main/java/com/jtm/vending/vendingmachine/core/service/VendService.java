package com.jtm.vending.vendingmachine.core.service;

import com.jtm.vending.vendingmachine.commons.constants.CashCalculator;
import com.jtm.vending.vendingmachine.commons.constants.Money;
import com.jtm.vending.vendingmachine.commons.data.CashBundle;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class VendService implements CashCalculator {
    Logger logger = LogManager.getLogger(VendService.class);
    //Method (calculateTotal): Calculates the total amount that was entered by the user, based on the cash denominations that is accepted by our vending machine.
    @Override
    public double calculateTotal(CashBundle moneyEntered) {
        logger.info("Method - calculateTotal : calculating total... Total =" + moneyEntered.getTotal());
        return moneyEntered.getTotal();
    }


    //Method (calculateTotal): Calculates the total amount that needs to be returned to the user should they have payed more than what the product costs.
    // And will return the total value in the cash denominations that is accepted by our machine.
    @Override
    public CashBundle calculateChange(double amountMoneyToReturn) {

        CashBundle change = new CashBundle(0,0,0,0,0,0,0);
        double remainingAmount = amountMoneyToReturn;

        change.setNumberOf50Rands((int) (remainingAmount / (Money.valueOf("FIFTY_RANDS").getValue())));
        remainingAmount =  (remainingAmount % (Money.valueOf("FIFTY_RANDS").getValue()));

        change.setNumberOf20Rands((int) (remainingAmount / (Money.valueOf("TWENTY_RANDS").getValue())));
        remainingAmount =  (remainingAmount % (Money.valueOf("TWENTY_RANDS").getValue()));

        change.setNumberOf10Rands((int) (remainingAmount / (Money.valueOf("TEN_RANDS").getValue())));
        remainingAmount =  (remainingAmount % (Money.valueOf("TEN_RANDS").getValue()));

        change.setNumberOf5Rands((int) (remainingAmount / (Money.valueOf("FIVE_RANDS").getValue())));
        remainingAmount =  (remainingAmount % (Money.valueOf("FIVE_RANDS").getValue()));

        change.setNumberOf2Rands((int) (remainingAmount / (Money.valueOf("TWO_RANDS").getValue())));
        remainingAmount =  (remainingAmount % (Money.valueOf("TWO_RANDS").getValue()));

        change.setNumberOf1Rands((int) (remainingAmount / (Money.valueOf("ONE_RAND").getValue())));
        remainingAmount =  (remainingAmount % (Money.valueOf("ONE_RAND").getValue()));

        change.setNumberOf50Cents((int) (remainingAmount / (Money.valueOf("FIFTY_CENTS").getValue())));
        logger.info("Method - calculateTotal : calculating the change to return to user... Total Change =" + change.toString());
        return change;
    }
}
