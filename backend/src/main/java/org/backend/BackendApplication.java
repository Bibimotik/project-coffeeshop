package org.backend;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class BackendApplication {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(
			BackendApplication.class
	);
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
