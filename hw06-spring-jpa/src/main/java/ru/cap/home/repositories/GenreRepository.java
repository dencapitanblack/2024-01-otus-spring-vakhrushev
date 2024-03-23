package ru.cap.home.repositories;

import ru.cap.home.models.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {

    List<Genre> findAll();

    Optional<Genre> findById(long id);

}
