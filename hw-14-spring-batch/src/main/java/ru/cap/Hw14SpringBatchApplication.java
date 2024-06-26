package ru.cap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.cap.config.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class Hw14SpringBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(Hw14SpringBatchApplication.class, args);
	}
}
