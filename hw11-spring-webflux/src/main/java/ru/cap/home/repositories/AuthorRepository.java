package ru.cap.home.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.cap.home.models.Author;

public interface AuthorRepository extends ReactiveCrudRepository<Author, String> {

}
