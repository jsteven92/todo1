package todo1.sysiems.store.product.service;

import java.util.List;

import todo1.sysiems.store.product.entity.Product;
import todo1.sysiems.store.product.entity.SuperHero;

public interface ProductService {
    public List<Product> listAllProduct();
    public Product getProduct(Long id);

    public Product createProduct(Product product);
    public Product updateProduct(Product product);
    public  Product deleteProduct(Long id);
    public List<Product> findBySuperHero(SuperHero name);
    public Product updateStock(Long id, int quantity);
}
