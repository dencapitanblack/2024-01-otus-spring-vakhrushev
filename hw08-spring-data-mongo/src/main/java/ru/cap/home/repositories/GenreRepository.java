package ru.cap.home.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.cap.home.models.Genre;

import java.util.List;

public interface GenreRepository extends MongoRepository<Genre, String> {

    List<Genre> findAllByOrderByGenreNameAsc();

}
