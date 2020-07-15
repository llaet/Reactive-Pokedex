package com.java.pokedex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.java.pokedex.pokemon.Pokemon;
import com.java.pokedex.repository.PokedexRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/pokemons")
public class PokedexController {

	@Autowired
	private PokedexRepository repository;
	
	@GetMapping
	public Flux<Pokemon> findAll(){
		return this.repository.findAll();
	}
	
	@GetMapping("/{id}")
	public Mono<ResponseEntity<Pokemon>> findById(@PathVariable("id") String id) {
		return this.repository.findById(id)
				.map(pokemon -> ResponseEntity.ok(pokemon))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Pokemon> save(@RequestBody Pokemon pokemon){
		return this.repository.save(pokemon);
	}
}
