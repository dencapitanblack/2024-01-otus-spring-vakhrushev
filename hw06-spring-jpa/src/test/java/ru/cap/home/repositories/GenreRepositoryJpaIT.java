package ru.cap.home.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.cap.home.models.Genre;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@Import(GenreRepositoryJpa.class)
class GenreRepositoryJpaIT {

    @Autowired
    TestEntityManager em;

    @Autowired
    GenreRepositoryJpa repositoryJpa;

    @Test
    void givenGenre_whenFindAll_thenSuccess() {

        Genre genre1 = new Genre();
        Genre genre2 = new Genre();
        Genre genre3 = new Genre();
        Genre genre4 = new Genre();
        Genre genre5 = new Genre();

        genre1.setGenreName("genre_1");
        genre2.setGenreName("genre_2");
        genre3.setGenreName("genre_3");
        genre4.setGenreName("genre_4");
        genre5.setGenreName("genre_5");

        em.persist(genre1);
        em.persist(genre2);
        em.persist(genre3);
        em.persist(genre4);
        em.persist(genre5);

        assertThat(repositoryJpa.findAll()).hasSizeGreaterThanOrEqualTo(5);
    }

    @Test
    void givenGenre_whenFindById_thenSuccess() {

        Genre genre1 = new Genre();
        genre1.setGenreName("genre_1");

        em.persist(genre1);

        assertThat(repositoryJpa.findById(genre1.getId())).contains(genre1);

    }


}