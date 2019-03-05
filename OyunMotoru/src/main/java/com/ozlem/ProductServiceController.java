package com.ozlem;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductServiceController {
   private static Map<Integer, Product> productRepo = new HashMap<Integer, Product>();
   static {
      Product honey = new Product();
      honey.setProductId(1);
      honey.setProductName("honey");
      honey.setCategoryId(1);
      honey.setQuantityPerUnit("honey");
      honey.setUnitPrice(BigDecimal.ZERO);
      honey.setUnitsInStock(100);
      
      productRepo.put(honey.getProductId(), honey);
      
      Product almond = new Product();
      almond.setProductId(2);
      almond.setCategoryId(2);
      almond.setProductName("almond");
      almond.setQuantityPerUnit("almond");
      almond.setUnitPrice(BigDecimal.TEN);
      almond.setUnitsInStock(10);
      productRepo.put(almond.getProductId(), almond);
   }
   
   @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<Object> delete(@PathVariable("id") String id) { 
      productRepo.remove(Integer.valueOf(id));
      return new ResponseEntity<Object>("Product is deleted successsfully", HttpStatus.OK);
   }
   
   @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
   public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product) { 
      productRepo.remove(Integer.valueOf(id));
      product.setProductId(Integer.valueOf(id));
      productRepo.put(product.getProductId(), product);
      return new ResponseEntity<Object>("Product is updated successsfully", HttpStatus.OK);
   }
   
   @RequestMapping(value = "/products", method = RequestMethod.POST)
   public ResponseEntity<Object> createProduct(@RequestBody Product product) {
      productRepo.put(product.getProductId(), product);
      return new ResponseEntity<Object>("Product is created successfully", HttpStatus.CREATED);
   }
   
   @CrossOrigin(origins = "http://localhost:4200")
   @RequestMapping(value = "/products")
   public ResponseEntity<Object> getProduct() {
      return new ResponseEntity<Object>(productRepo.values(), HttpStatus.OK);
   }
   
   @CrossOrigin(origins = "http://localhost:4200")
   @RequestMapping(value = "/products/{id}")
   public ResponseEntity<Object> getProductById(@PathVariable("id") Integer id) {
      return new ResponseEntity<Object>(productRepo.get(id), HttpStatus.OK);
   }
}