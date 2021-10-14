package todo1.sysiems.store.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import todo1.sysiems.store.product.entity.Product;
import todo1.sysiems.store.product.entity.SuperHero;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{

    public List<Product> findBySuperHero(SuperHero name);

}
