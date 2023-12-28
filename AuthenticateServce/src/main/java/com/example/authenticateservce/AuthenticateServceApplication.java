package com.example.authenticateservce;

import com.example.authenticateservce.config.AuditorAwareImpl;
import com.example.authenticateservce.entity.UserEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AuthenticateServceApplication {
	@Bean
	public AuditorAware<Long> auditorAware() {
		return new AuditorAwareImpl();
	}

	public static void main(String[] args) {
		SpringApplication.run(AuthenticateServceApplication.class, args);
	}

}
