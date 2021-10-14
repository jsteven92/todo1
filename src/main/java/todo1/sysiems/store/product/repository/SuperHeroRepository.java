package todo1.sysiems.store.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import todo1.sysiems.store.product.entity.SuperHero;

@Repository
public interface SuperHeroRepository extends JpaRepository< SuperHero , Long >{
    
}
