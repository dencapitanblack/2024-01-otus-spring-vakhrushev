package ru.cap.home.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.cap.home.models.Author;

import java.util.List;

public interface AuthorRepository extends MongoRepository<Author, String> {

    List<Author> findAllByOrderByAuthorNameAsc();

}
