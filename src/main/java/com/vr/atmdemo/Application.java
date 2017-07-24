package com.vr.atmdemo;

import com.vr.atmdemo.entity.Card;
import com.vr.atmdemo.repository.CardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

/**
 * The Class Application.
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Application.class);
	}

	/**
	 * Test data population.
	 *
	 * @param cardRepo the card repo
	 * @return the command line runner
	 */
	@Bean
	public CommandLineRunner dbInit(CardRepository cardRepo) {
		return (args) -> {
			cardRepo.save(new Card("1111222233334444", "1111", 111.1, true));
			cardRepo.save(new Card("4444333322221111", "1111", 1000, true));
			cardRepo.save(new Card("2222333344441111", "1111", 4546, true));
		};
	}
}
