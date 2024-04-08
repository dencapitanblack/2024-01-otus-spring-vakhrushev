package ru.cap.home.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.cap.home.models.Book;


public interface BookRepository extends MongoRepository<Book, String> {
}
