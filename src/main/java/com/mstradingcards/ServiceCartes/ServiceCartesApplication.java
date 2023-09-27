package com.mstradingcards.ServiceCartes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.mstradingcards.ServiceCartes"})
@EntityScan("com.mstradingcards.ServiceCartes.models")
@EnableJpaRepositories("com.mstradingcards.ServiceCartes.repository")
public class ServiceCartesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceCartesApplication.class, args);
	}

}
