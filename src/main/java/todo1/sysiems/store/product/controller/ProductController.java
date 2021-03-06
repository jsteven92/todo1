package todo1.sysiems.store.product.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import todo1.sysiems.store.helper.FormatMessage;
import todo1.sysiems.store.product.entity.Product;
import todo1.sysiems.store.product.entity.SuperHero;
import todo1.sysiems.store.product.service.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    private FormatMessage message = new FormatMessage();

    /**I can search all products or only search for super hero type */
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public ResponseEntity<List<Product>> listProduct(@RequestParam(name = "superHeroId", required = false) Long superHeroId) {
        
        List<Product> products = new ArrayList<>();
        if (null ==  superHeroId){
             products = productService.listAllProduct();
            if (products.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }else{
            products = productService.findBySuperHero(SuperHero.builder().id(superHeroId).build());
            if (products.isEmpty()){
                return ResponseEntity.notFound().build();
            }
        }


        return ResponseEntity.ok(products);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    @RequestMapping(value = "/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
        System.out.println("..");
        System.out.println("..");
        System.out.println("..");
        System.out.println("..");
        System.out.println("..");
        Product product = productService.getProduct(id);

        if (null == product) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(product);

    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product, BindingResult result) {

        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message.formatMessage(result));
        }

        Product productCreate = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productCreate);
    }

    
   @PutMapping(value = "/{id}")
   public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product){
       product.setId(id);
       System.out.println(product);
       System.out.println();
       System.out.println();
       System.out.println();
       Product productDB =  productService.updateProduct(product);
       if (productDB == null){
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.ok(productDB);
   }

   @DeleteMapping(value = "/{id}")
   public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id){
       Product productDelete = productService.deleteProduct(id);
       if (productDelete == null){
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.ok(productDelete);
   }

   @PutMapping (value = "/{id}/stock")
   public ResponseEntity<Product> updateStockProduct(@PathVariable  Long id ,@RequestParam(name = "quantity", required = true) int quantity){

       Product productOld = productService.getProduct(id);

       Product product = productService.updateStock(id, quantity);
       if (product == null){
           return ResponseEntity.notFound().build();
       }

       if (product.getStock() == productOld.getStock()){
           return ResponseEntity.noContent().build();
       }
       return ResponseEntity.ok(product);
   }

}
