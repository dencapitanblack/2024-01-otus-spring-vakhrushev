package ru.cap.home.services;

import ru.cap.home.models.Book;
import java.util.List;
import java.util.Optional;

public interface BookService {

    Optional<Book> findById(long id);

    List<Book> findAll();

    Book insert(String title, long authorId, long genreId);

    Book update(long id, String title, long authorId, long genreId);

    Book update(long id, String title);

    void deleteById(long id);

}
