package ru.cap.home.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.cap.home.models.Book;

public interface BookRepository extends ReactiveCrudRepository<Book, String> {
    
}
