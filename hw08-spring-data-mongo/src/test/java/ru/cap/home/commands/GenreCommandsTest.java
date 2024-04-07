package ru.cap.home.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.shell.InputProvider;
import org.springframework.shell.ResultHandlerService;
import org.springframework.shell.Shell;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@DisplayName("Shell commands author")
@SpringBootTest
class GenreCommandsTest {

    private static final String SHOW_ALL_GENRE = "genre";
    private static final String SHOW_ALL_GENRE_SHORT = "g";

    private InputProvider inputProvider;

    private ArgumentCaptor<Object> argumentCaptor;

    @SpyBean
    private ResultHandlerService resultHandlerService;

    @Autowired
    private Shell shell;

    @BeforeEach
    void setUp() {
        inputProvider = mock(InputProvider.class);
        argumentCaptor = ArgumentCaptor.forClass(Object.class);
    }

    @Test
    void shouldReturnListOfAllGenres() throws Exception {

        when(inputProvider.readInput())
                .thenReturn(() -> SHOW_ALL_GENRE)
                .thenReturn(() -> SHOW_ALL_GENRE_SHORT)
                .thenReturn(null);

        shell.run(inputProvider);
        verify(resultHandlerService, times(2)).handle(argumentCaptor.capture());
        List<Object> result = argumentCaptor.getAllValues();

        for (Object o : result) {
            assertEquals(
                    "Id: 1, Name: genre_1,Id: 2, Name: genre_2,Id: 3, Name: genre_3".replaceAll("\\s+", ""),
                    o.toString().replaceAll("\\s+", ""));
        }
    }

}