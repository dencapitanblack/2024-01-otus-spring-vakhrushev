package ru.cap.home.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.cap.home.models.Genre;

public interface GenreRepository extends ReactiveCrudRepository<Genre, String> {
}
