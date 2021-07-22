package com.jtm.vending.vendingmachine.commons.constants;

import com.jtm.vending.vendingmachine.commons.data.CashBundle;

public interface CashCalculator {

    double calculateTotal(CashBundle moneyEntered);
    CashBundle calculateChange(double amountMoneyToReturn);
}
