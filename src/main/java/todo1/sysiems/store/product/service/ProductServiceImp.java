package todo1.sysiems.store.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import todo1.sysiems.store.product.entity.Product;
import todo1.sysiems.store.product.entity.SuperHero;
import todo1.sysiems.store.product.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {
    
    
    private final ProductRepository productRep;

    @Override
    public List<Product> listAllProduct() {
        
        return productRep.findAll();
    }

    @Override
    public Product getProduct(Long id) {

        return productRep.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
        product.setStatus("CREATED");
        return productRep.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Product productDB = getProduct(product.getId());
        if ( null == productDB){
            return null;
        }

        productDB.setName(product.getName());
        productDB.setPrice(product.getPrice());
        productDB.setSuperHero(product.getSuperHero());

        return productRep.save(productDB);
        
    }

    @Override
    public Product deleteProduct(Long id) {
        Product productDB = getProduct(id);
        if (null == productDB){
            return null;
        }
        productDB.setStatus("DELETED");

        return productRep.save(productDB);
    }

    @Override
    public List<Product> findBySuperHero(SuperHero name) {
       return productRep.findBySuperHero(name);
    }

    @Override
    public Product updateStock(Long id, int quantity) {
        Product productDB = getProduct(id);
        if (null == productDB){
            return null;
        }

        if( (productDB.getStock() + quantity) < 0){
            return productDB;
        }

        int stock =  productDB.getStock() + quantity;
        
        productDB.setStock(stock);
        return productRep.save(productDB);
    }
    
}
