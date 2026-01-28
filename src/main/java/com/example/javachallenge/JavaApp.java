package com.example.javachallenge;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class JavaApp {

	public static void main(String[] args) {
		SpringApplication.run(JavaApp.class, args);
	}

	@Bean
	CommandLineRunner pingSelf() {
		return args -> {
			// small delay to ensure the web server is ready
			Thread.sleep(1000);

			RestTemplate rest = new RestTemplate();
			String url = "http://localhost:8080/hello";

			try {
				String response = rest.getForObject(url, String.class);
				System.out.println("Self-check OK -> " + response);
			} catch (Exception e) {
				System.err.println("Self-check FAILED: " + e.getMessage());
			}
		};
	}
}
