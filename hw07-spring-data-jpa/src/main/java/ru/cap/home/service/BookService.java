package ru.cap.home.service;

import ru.cap.home.models.Book;

import java.util.List;
import java.util.Set;

public interface BookService {

    Book findById(long id);

    List<Book> findAll();

    Book insert(String title, Set<Long> idAuthors, Set<Long> idGenres);

    Book update(long id, String title);

    void deleteById(long id);

}
