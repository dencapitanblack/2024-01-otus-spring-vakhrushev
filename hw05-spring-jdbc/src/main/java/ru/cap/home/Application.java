package ru.cap.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.cap.home.repositories.GenreRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);

		GenreRepository gp = context.getBean(GenreRepository.class);

		System.out.println(gp.findById(1).get().getGenreName());



	}

}
