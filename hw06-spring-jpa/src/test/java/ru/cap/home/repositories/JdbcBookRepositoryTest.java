package ru.cap.home.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import ru.cap.home.models.Author;
import ru.cap.home.models.Book;
import ru.cap.home.models.Genre;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@DisplayName("Unit test JdbcBookRepository")
@ExtendWith(MockitoExtension.class)
class JdbcBookRepositoryTest {

    @Mock
    private NamedParameterJdbcOperations namedParameterJdbcOperations;
    private BookRepository bookRepository;


    @BeforeEach
    void setUp() {
        bookRepository = new JdbcBookRepository(namedParameterJdbcOperations);
    }

    @Test
    void testFindAll() {

        List<Book> expectedBook = Arrays.asList(new Book(1, "Genre_1", new Author(1 , "Author_1"), new Genre(1, "Genre_1")));
        when(namedParameterJdbcOperations.query(anyString(), ArgumentMatchers.<RowMapper<Book>>any()))
                .thenReturn(expectedBook);

        List<Book> actualBook = bookRepository.findAll();
        assertEquals(expectedBook, actualBook);

    }

    @Test
    void testFindByIdWithRec() {

        List<Book> expectedBook = Arrays.asList(new Book(1, "Genre_1", new Author(1 , "Author_1"), new Genre(1, "Genre_1")));

        when(namedParameterJdbcOperations
                .query(anyString(),  ArgumentMatchers.<Map<String, ?>>any(), ArgumentMatchers.<RowMapper<Book>>any()))
                .thenReturn(expectedBook);

        assertEquals(bookRepository.findById(1).get(), expectedBook.get(0));

    }

    @Test
    void testFindByIdWithoutRec() {

        List<Book> expectedBook = new ArrayList<>();

        when(namedParameterJdbcOperations
                .query(anyString(),  ArgumentMatchers.<Map<String, ?>>any(), ArgumentMatchers.<RowMapper<Book>>any()))
                .thenReturn(expectedBook);

        assertEquals(bookRepository.findById(2), Optional.empty());

    }

}