package me.snowdrop.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EasyNotesApplication {

	private static Logger LOG = LoggerFactory.getLogger(EasyNotesApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EasyNotesApplication.class, args);
	}

}
