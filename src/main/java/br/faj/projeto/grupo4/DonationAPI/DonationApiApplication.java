package br.faj.projeto.grupo4.DonationAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class DonationApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DonationApiApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer WebMvcConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://127.0.0.1:5500");
				registry.addMapping("/**").allowedMethods("GET", "PUT", "POST");
			}
		};
	}
}
