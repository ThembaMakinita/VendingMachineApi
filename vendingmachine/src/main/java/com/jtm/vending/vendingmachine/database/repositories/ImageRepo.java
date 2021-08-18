package com.jtm.vending.vendingmachine.database.repositories;

import com.jtm.vending.vendingmachine.database.entities.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepo extends JpaRepository<ProductImage,Long> {
    Optional<ProductImage> findByName(String name);
}
