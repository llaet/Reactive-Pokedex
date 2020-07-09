package com.java.pokedex;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;

import com.java.pokedex.repository.PokedexRepository;
import com.java.pokedex.pokemon.Pokemon;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class PokedexApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokedexApplication.class, args);
	}
	
	@Bean
	//just testing objects persistence with embedded mongo
	CommandLineRunner init(ReactiveMongoOperations operations, PokedexRepository repository) {
		return args -> {
			Flux<Pokemon> pokedexFlux = Flux.just(
					new Pokemon(null, "Larvitar", "Pedra/Solo", "Véu de areia", 72D),
					new Pokemon(null, "Pupitar", "Pedra/Solo", "Casca coberta", 152D),
					new Pokemon(null, "Tyranitar", "Pedra/Escuridão", "Fluxo de areia", 202D)
					).flatMap(repository::save);
		};
	}
}
