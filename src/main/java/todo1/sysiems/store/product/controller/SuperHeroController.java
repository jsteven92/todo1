package todo1.sysiems.store.product.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.validation.BindingResult;

import todo1.sysiems.store.product.entity.SuperHero;
import todo1.sysiems.store.helper.FormatMessage;
import todo1.sysiems.store.product.service.SuperHeroService;

@RestController
@RequestMapping(value = "/superHero")
public class SuperHeroController {

    @Autowired
    private SuperHeroService superHeroService;

    
    private FormatMessage message = new FormatMessage();

    @GetMapping
    public ResponseEntity<List<SuperHero>> listSuperHero() {
        List<SuperHero> superHeros = superHeroService.listAllSuperHero();

        if (superHeros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(superHeros);
    }

    @PostMapping
    public ResponseEntity<SuperHero> createSuperHero(@Valid @RequestBody SuperHero superHero, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message.formatMessage(result));
        }

        SuperHero superHeroDB = superHeroService.createSuperHero(superHero);

        return ResponseEntity.status(HttpStatus.CREATED).body(superHeroDB);
    }

}
