package com.jtm.vending.vendingmachine.core.controller;

import com.jtm.vending.vendingmachine.commons.converters.BeanConverter;
import com.jtm.vending.vendingmachine.commons.data.CashBundle;
import com.jtm.vending.vendingmachine.core.model.CashDTO;
import com.jtm.vending.vendingmachine.core.model.ProductDTO;
import com.jtm.vending.vendingmachine.core.service.ProductService;
import com.jtm.vending.vendingmachine.core.service.VendService;
import com.jtm.vending.vendingmachine.database.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vend")
public class VendingMachineControler {

    //ToDo Add proper logging in project
    //ToDo clean up code to make it more efficient
    //ToDo also add exception proper handling

    @Autowired
    private ProductService productService;

    @Autowired
    private BeanConverter beanConverter;

    @Autowired
    private VendService vendService;

    @GetMapping("/getAllProducts")
    public ResponseEntity getAllProducts(){
        List<Product>products = productService.getAllProducts();
        if(products==null)
            return new ResponseEntity<>("Exception Occurred: Unable to find the list of Products", HttpStatus.BAD_REQUEST);

        List<ProductDTO> responseDto = beanConverter.mapList(products,ProductDTO.class);
        if(responseDto != null && responseDto.size()>0){
            return new ResponseEntity(responseDto,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("No Results Found For Product List", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/placeOrder/{productId}")
    public ResponseEntity selectProduct(@PathVariable(value = "productId") long productId, @RequestBody CashBundle cashBundle){
        Product product = productService.getProductById(productId);
        //CashDTO cashDTO = beanConverter.mapBean(cashBundle,CashDTO.class);
        if(product != null){
            double price  = product.getItemPrice();
            double totalAmmountEntered = vendService.calculateTotal(cashBundle);
            if(totalAmmountEntered >= price) {
                double changeRequred = totalAmmountEntered - price;
                CashBundle cashBundlel =vendService.calculateChange(changeRequred);
                CashDTO cashDTO = beanConverter.mapBean(cashBundlel,CashDTO.class);
                cashDTO.setTotalAmmountInserted(totalAmmountEntered);
                cashDTO.setTotalChangeOutput(changeRequred);
                cashDTO.setRespMSG("Success!!");
                return new ResponseEntity(cashDTO,HttpStatus.OK);
            }
            else{
                return  new ResponseEntity("Insufficient funds to buy " + product.getName() + "please add the correct ammount and try again", HttpStatus.BAD_REQUEST );
            }
        }
        return new ResponseEntity<>("No Results Found For Product List", HttpStatus.NOT_FOUND);
    }

}
