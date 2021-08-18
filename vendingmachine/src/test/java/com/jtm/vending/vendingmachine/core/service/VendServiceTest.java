package com.jtm.vending.vendingmachine.core.service;

import com.jtm.vending.vendingmachine.commons.data.CashBundle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class VendServiceTest {

    private VendService vendService;
    private CashBundle cashBundle;

    @BeforeEach
    void setUp(){
        vendService = new VendService();
        cashBundle = new CashBundle(1,1,1,1,1,1,1);
    }

    @Test
    void calculateTotal() {
        //Given the sum of all single denominations accepted by our vending machine is equal to 88.50
        // 50 cents + R1.00 + R2.00 + R5.00 + R10.00 + R20.00 + R50.00 = R88.50
        double amount = 88.50;

        //When calculating the total by using this method. where we've initialized the class with all the single denominations accepted by our machine
        double result = vendService.calculateTotal(cashBundle);

        //Then we should get the same result.
        assertThat(result).isEqualTo(amount);
    }

    @Test
    void calculateChange() {

        CashBundle result = vendService.calculateChange(88.50);

        assertThat(result).isEqualTo(cashBundle);

    }
}