package ru.cap.home.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.cap.home.models.Author;
import ru.cap.home.models.Book;
import ru.cap.home.models.Genre;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(BookRepositoryJpa.class)
class BookRepositoryJpaIT {

    @Autowired
    TestEntityManager em;

    @Autowired
    BookRepositoryJpa repositoryJpa;

    @Test
    void givenBook_whenFindById_thenSuccess() {
        assertThat(repositoryJpa.findById(1)).contains(em.find(Book.class, 1));
    }

    @Test
    void givenBook_whenFindAll_thenSuccess() {
        assertThat(repositoryJpa.findAll()).hasSizeGreaterThanOrEqualTo(2);
    }

    @Test
    void givenNewBook_whenSave_thenSuccess() {
        Book book = new Book("title", Set.of(em.find(Genre.class, 1)), Set.of(em.find(Author.class, 1)));
        repositoryJpa.save(book);
        assertThat(book.getId()).isNotEqualTo(0);
    }

    @Test
    void givenUpdatedBook_whenSave_thenSuccess() {
        String expected = "test new title";
        Book book = em.find(Book.class, 1);
        book.setTitle(expected);
        repositoryJpa.save(book);
        assertThat(em.find(Book.class, 1).getTitle()).isEqualTo(expected);
    }

    @Test
    void givenBook_whenDelete_thenDelete() {
        repositoryJpa.deleteById(1);
        assertThat(em.find(Book.class, 1)).isNull();
    }
}