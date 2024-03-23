package ru.cap.home.service;

import ru.cap.home.models.Book;

import java.util.List;

public interface BookService {

    Book findById(long id);

    List<Book> findAll();

    Book insert(String title, List<Long> idAuthors, List<Long> idGenres);

    Book update(long id, String title, List<Long> idAuthors, List<Long> idGenres);

    Book update(long id, String title);

    void deleteById(long id);

}
