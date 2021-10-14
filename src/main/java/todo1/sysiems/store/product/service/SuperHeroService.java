package todo1.sysiems.store.product.service;

import java.util.List;

import todo1.sysiems.store.product.entity.SuperHero;

public interface SuperHeroService {

    public List<SuperHero> listAllSuperHero();
    public SuperHero createSuperHero(SuperHero superHero);
    
}
