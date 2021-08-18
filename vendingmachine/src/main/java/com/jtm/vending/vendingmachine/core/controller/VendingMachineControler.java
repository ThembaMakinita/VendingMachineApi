package com.jtm.vending.vendingmachine.core.controller;

import com.jtm.vending.vendingmachine.commons.converters.BeanConverter;
import com.jtm.vending.vendingmachine.commons.data.CashBundle;
import com.jtm.vending.vendingmachine.core.model.CashDTO;
import com.jtm.vending.vendingmachine.core.model.ProductDTO;
import com.jtm.vending.vendingmachine.core.service.ProductService;
import com.jtm.vending.vendingmachine.core.service.VendService;
import com.jtm.vending.vendingmachine.database.entities.Product;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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
    Logger logger = LogManager.getLogger(VendingMachineControler.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private BeanConverter beanConverter;

    @Autowired
    private VendService vendService;

    @GetMapping("/getAllProducts")
    public ResponseEntity getAllProducts(){
        //get the products from the database
        logger.info("Method - getAllProducts : getting products from the database.");
        List<Product>products = productService.getAllProducts();
        if(products==null)
            return new ResponseEntity<>("Exception Occurred: Unable to find the list of Products", HttpStatus.BAD_REQUEST);

        //convert data object to a pojo
        List<ProductDTO> responseDto = beanConverter.mapList(products,ProductDTO.class);
        if(responseDto != null && responseDto.size()>0){
            //if the product list isn't empty return it to the calling module.
            logger.info("Method - getAllProducts : returning the list of products to the calling module.");
            return new ResponseEntity(responseDto,HttpStatus.OK);
        }
        else{
            logger.error("Method - getAllProducts : No Results Found For Product List.");
            return new ResponseEntity<>("No Results Found For Product List", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/placeOrder/{productId}")
    public ResponseEntity selectProduct(@PathVariable(value = "productId") long productId, @RequestBody CashBundle cashBundle){
        //get the selected product from the database by its id
        Product product = productService.getProductById(productId);
        if(product != null){
            //get the cost price of the product
            double price  = product.getItemPrice();
            logger.info(" Method - selectProduct : getting the cost price of the product  - " + product.getName()+ "price is R" +price);
            //get the total amount entered to purchase this product by the user.
            double totalAmountEntered = vendService.calculateTotal(cashBundle);
            logger.info(" Method - selectProduct : getting total amount entered to purchase this product  - " + product.getName()+ "ammount entered is R" +totalAmountEntered);
            if(totalAmountEntered >= price) {
                //when user entered enough money check to see if product is available.
                logger.info(" Method - selectProduct : User has enough funds for product." );
                if(product.getAvailable() > 0) {
                    //if product is available then do the sale.
                    logger.info(" Method - selectProduct : The product is available." );
                    double changeRequired = totalAmountEntered - price;
                    CashBundle cashBundleReturn = vendService.calculateChange(changeRequired);
                    //mapping response to the user
                    CashDTO cashDTO = beanConverter.mapBean(cashBundleReturn, CashDTO.class);
                    cashDTO.setTotalAmmountInserted(totalAmountEntered);
                    cashDTO.setTotalChangeOutput(changeRequired);
                    cashDTO.setRespMSG("Success!!");
                    //update the availability of that particular product.
                    logger.info(" Method - selectProduct : Updating the available units for this product to " + (product.getAvailable() - 1));
                    product.setAvailable(product.getAvailable() - 1);
                    productService.updateProduct(product);
                    return new ResponseEntity(cashDTO, HttpStatus.OK);
                }
                CashDTO cashDTO = beanConverter.mapBean(cashBundle, CashDTO.class);
                cashDTO.setRespMSG("Sorry that product is not Available, Here is your cash back.");
                return  new ResponseEntity(cashDTO, HttpStatus.OK);
            }
            else{
                logger.error(" Method - selectProduct : Insufficient funds to buy " + product.getName() + "please add the correct ammount and try again");
                return  new ResponseEntity("Insufficient funds to buy " + product.getName() + "please add the correct ammount and try again", HttpStatus.BAD_REQUEST );
            }
        }
        logger.error(" Method - selectProduct : No Results Found For Product List");
        return new ResponseEntity<>("No Results Found For Product List", HttpStatus.NOT_FOUND);
    }

}
