package ru.cap.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.cap.home.models.Author;
import ru.cap.home.models.Book;
import ru.cap.home.models.Genre;
import ru.cap.home.repositories.BookRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);


	}

}
