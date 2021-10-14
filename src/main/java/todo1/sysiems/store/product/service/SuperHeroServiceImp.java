package todo1.sysiems.store.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import todo1.sysiems.store.product.entity.SuperHero;
import todo1.sysiems.store.product.repository.SuperHeroRepository;

@Service
@RequiredArgsConstructor
public class SuperHeroServiceImp implements SuperHeroService {

    private final SuperHeroRepository superHeroRep;

    @Override
    public List<SuperHero> listAllSuperHero() {
        return superHeroRep.findAll();
    }

    @Override
    public SuperHero createSuperHero(SuperHero superHero) {
        return superHeroRep.save(superHero);
    }

}
