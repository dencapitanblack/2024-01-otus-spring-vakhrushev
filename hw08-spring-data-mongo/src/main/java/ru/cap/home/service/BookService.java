package ru.cap.home.service;

import ru.cap.home.models.Book;

import java.util.List;
import java.util.Set;

public interface BookService {

    List<Book> findAll();

    Book insert(String title, Set<String> authors, Set<String> genres);

    Book update(int id, String title);

    void deleteById(int id);

    Book getById(int id);

}
