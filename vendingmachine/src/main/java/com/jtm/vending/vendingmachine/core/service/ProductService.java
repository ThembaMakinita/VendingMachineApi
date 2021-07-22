package com.jtm.vending.vendingmachine.core.service;

import com.jtm.vending.vendingmachine.commons.data.CashBundle;
import com.jtm.vending.vendingmachine.database.entities.Product;
import com.jtm.vending.vendingmachine.database.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public List<Product> getAllProducts() {
        try{
            List<Product>products = productRepository.findAll();
            return products;
        }catch(Exception exception){
            return null;
        }
    }

    @Transactional
    public Product getProductById (Long id){
        try {
            Product product = productRepository.getById(id);
            return product;
        }catch(Exception exception){
            return null;
        }
    }
}
