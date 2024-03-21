package ru.cap.home.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.cap.home.models.Author;
import ru.cap.home.repositories.AuthorRepository;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    private AuthorService authorService;

    @BeforeEach
    public void setUp() {
        authorService = new AuthorServiceImpl(authorRepository);
    }

    @Test
    void testFindAll() {

        List<Author> authorList = Arrays.asList(new Author(1, "Author_1"));

        when(authorRepository.findAll()).thenReturn(authorList);

        assertThat(authorList).hasSize(1)
                .containsExactlyElementsOf(authorService.findAll())
                .hasSize(1);

    }
}