package ru.cap.home.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.cap.home.models.Book;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    TestEntityManager em;

    @Autowired
    BookRepository bookRepository;

    @Test
    void findAll() {

        List<Book> books = bookRepository.findAll();
        assertThat(books).hasSizeGreaterThanOrEqualTo(2);

        books.stream().forEach(book -> {
            assertThat(book.getAuthor()).isNotNull();
            assertThat(book.getGenre()).isNotNull();
        });

    }
}