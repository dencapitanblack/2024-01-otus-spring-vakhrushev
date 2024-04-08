package ru.cap.home.service;

import ru.cap.home.models.Author;

import java.util.List;
import java.util.Set;

public interface AuthorService {
    List<Author> findAll();

    Set<String> getAuthorsFromId(Set<Integer> set);

}
