package ru.cap.home.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.cap.home.models.Author;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(AuthorRepositoryJpa.class)
class AuthorRepositoryJpaTest {

    @Autowired
    TestEntityManager em;

    @Autowired
    AuthorRepositoryJpa repositoryJpa;

    @Test
    void givenAuthor_whenFindAll_thenSuccess() {

        Author author1 = new Author("author_1_test");
        Author author2 = new Author("author_2_test");
        em.persist(author1);
        em.persist(author2);

        assertThat(repositoryJpa.findAll()).hasSizeGreaterThanOrEqualTo(2)
                .containsAll(Arrays.asList(author1, author2));

    }

    @Test
    void givenAuthor_whenFindById_thenSuccess() {

        Author author1 = new Author("author_1_test");
        em.persist(author1);
        assertThat(repositoryJpa.findById(author1.getId()).get()).isEqualTo(em.find(Author.class, author1.getId()));
    }

}