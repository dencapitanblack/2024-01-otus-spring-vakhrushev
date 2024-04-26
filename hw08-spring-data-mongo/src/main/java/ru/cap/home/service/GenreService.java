package ru.cap.home.service;

import ru.cap.home.models.Genre;

import java.util.List;
import java.util.Set;

public interface GenreService {
    List<Genre> findAll();

    Set<String> getGenresFromId(Set<Integer> set);

}
