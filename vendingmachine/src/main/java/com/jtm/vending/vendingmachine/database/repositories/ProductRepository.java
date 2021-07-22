package com.jtm.vending.vendingmachine.database.repositories;

import com.jtm.vending.vendingmachine.database.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository  extends JpaRepository<Product,Long> {
}
