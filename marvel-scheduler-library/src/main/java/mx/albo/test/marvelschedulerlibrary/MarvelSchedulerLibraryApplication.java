package mx.albo.test.marvelschedulerlibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
public class MarvelSchedulerLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarvelSchedulerLibraryApplication.class, args);
	}

}
