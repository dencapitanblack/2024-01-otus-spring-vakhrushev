package ru.cap.home.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cap.home.models.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
