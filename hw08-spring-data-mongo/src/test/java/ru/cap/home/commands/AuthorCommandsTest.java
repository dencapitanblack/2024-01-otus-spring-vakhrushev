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

import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@DisplayName("Shell commands author")
@SpringBootTest
class AuthorCommandsTest {

    private static final String SHOW_ALL_AUTHORS = "author";
    private static final String SHOW_ALL_AUTHORS_SHORT = "a";

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
    void shouldReturnListOfAllAuthors() throws Exception {

        when(inputProvider.readInput())
                .thenReturn(() -> SHOW_ALL_AUTHORS)
                .thenReturn(() -> SHOW_ALL_AUTHORS_SHORT)
                .thenReturn(null);

        shell.run(inputProvider);
        verify(resultHandlerService, times(2)).handle(argumentCaptor.capture());
        List<Object> result = argumentCaptor.getAllValues();


        for (Object o : result) {
            assertThat(o.toString()).contains(". author - ");
        }
    }

}