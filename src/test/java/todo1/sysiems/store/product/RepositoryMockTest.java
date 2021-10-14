package todo1.sysiems.store.product;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import todo1.sysiems.store.product.entity.Product;
import todo1.sysiems.store.product.entity.SuperHero;
import todo1.sysiems.store.product.repository.ProductRepository;

@DataJpaTest
public class RepositoryMockTest {
    
    @Autowired
    private ProductRepository productRep;

    @Test
    public void whenFindBySuperHero_thenReturnListProduct(){
        List<Product> products01 = productRep.findBySuperHero(SuperHero.builder().id(1L).build());
        List<Product> products02 = productRep.findBySuperHero(SuperHero.builder().id(2L).build());

        Assertions.assertThat(products01.size()).isEqualTo(1);
        Assertions.assertThat(products02.size()).isEqualTo(0);
    }
}
