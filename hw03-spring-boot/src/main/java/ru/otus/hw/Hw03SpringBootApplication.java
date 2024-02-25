package ru.otus.hw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.hw.service.TestRunnerService;

@SpringBootApplication
public class Hw03SpringBootApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(Hw03SpringBootApplication.class, args);
		var testRunnerService = context.getBean(TestRunnerService.class);
		testRunnerService.run();

	}
}