package ru.cap.home.repositories;

import org.hamcrest.Matchers;
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
import ru.cap.home.repositories.mappers.AuthorMapper;

import java.util.*;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@DisplayName("Unit test JdbcAuthorRepository")
@ExtendWith(MockitoExtension.class)
class JdbcAuthorRepositoryTest {

    @Mock
    private NamedParameterJdbcOperations namedParameterJdbcOperations;
    private AuthorRepository authorRepository;


    @BeforeEach
    void setUp() {
        authorRepository = new JdbcAuthorRepository(namedParameterJdbcOperations);
    }

    @Test
    void testFindAll() {

        List<Author> expectedAuthor = Arrays.asList(new Author(1, "Author_1"), new Author(2, "Author_2"));
        when(namedParameterJdbcOperations.query(eq("select id, fullname from author"), ArgumentMatchers.<RowMapper<Author>>any()))
                .thenReturn(expectedAuthor);

        List<Author> actualAuthors = authorRepository.findAll();
        assertEquals(expectedAuthor, actualAuthors);

    }

    @Test
    void testFindByIdWithRec() {

        List<Author> authorList = Arrays.asList(new Author(1, "Author_1"));

        Map<String, Object> params = Collections.singletonMap("id", 1);

        when(namedParameterJdbcOperations
                .query(eq("select id, fullname from author where id = :id"),  ArgumentMatchers.<Map<String, ?>>any(), ArgumentMatchers.<RowMapper<Author>>any()))
                .thenReturn(authorList);

        assertEquals(authorRepository.findById(1).get(), authorList.get(0));

    }

    @Test
    void testFindByIdWithoutRec() {

        List<Author> authorList = new ArrayList<>();

        when(namedParameterJdbcOperations
                .query(eq("select id, fullname from author where id = :id"),  ArgumentMatchers.<Map<String, ?>>any(), ArgumentMatchers.<RowMapper<Author>>any()))
                .thenReturn(authorList);

        assertEquals(authorRepository.findById(2), Optional.empty());

    }
}