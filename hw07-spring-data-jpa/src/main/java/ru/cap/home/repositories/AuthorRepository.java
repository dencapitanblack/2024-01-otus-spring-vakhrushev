package ru.cap.home.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cap.home.models.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
