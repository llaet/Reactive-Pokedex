package com.java.pokedex.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.java.pokedex.pokemon.Pokemon;

public interface PokedexRepository extends ReactiveMongoRepository<Pokemon, String>{
}
