package todo1.sysiems.store.product;

import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import todo1.sysiems.store.product.entity.SuperHero;
import todo1.sysiems.store.product.repository.SuperHeroRepository;
import todo1.sysiems.store.product.service.SuperHeroService;
import todo1.sysiems.store.product.service.SuperHeroServiceImp;

@SpringBootTest
public class SuperHeroServiceMockTest {
    
    private SuperHeroRepository superHeroRepMock = Mockito.mock(SuperHeroRepository.class);

  
    private SuperHeroService superHeroService;

    @BeforeEach
    public void setUp() {
        superHeroService = new SuperHeroServiceImp( superHeroRepMock );
        List<SuperHero> superHeroMock = new ArrayList<>();
        SuperHero superHero01 = SuperHero.builder().id(1L).name("super man").editorial("DC Comics").build();
        
        superHeroMock.add(superHero01);
       
        Mockito.when(superHeroRepMock.findAll()).thenReturn(superHeroMock);
        Mockito.when(superHeroRepMock.findById(1L)).thenReturn(Optional.of(superHero01));
        Mockito.when(superHeroRepMock.save(any(SuperHero.class))).thenReturn(superHero01);

    }

    @Test
    public void whenGetAllSuperHero_thenReturnListSuperHero() {
        List<SuperHero> superHeros01 = superHeroService.listAllSuperHero();
        Assertions.assertThat(superHeros01.size()).isEqualTo(1);
    }

    @Test
    public void whenCreateSuperHero_thenReturnSuperHero(){
        SuperHero super01 =SuperHero.builder().id(1L).name("super man").editorial("DC Comics").build();

        SuperHero superSave = superHeroService.createSuperHero(super01);
        
        Assertions.assertThat(superSave).isEqualTo(super01);
    }
}
