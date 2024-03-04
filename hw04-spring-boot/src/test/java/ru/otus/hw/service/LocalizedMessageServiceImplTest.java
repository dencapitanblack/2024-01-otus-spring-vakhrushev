package ru.otus.hw.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import ru.otus.hw.config.AppProperties;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocalizedMessageServiceImplTest {

    @Mock
    private AppProperties appProperties;

    @Mock
    private MessageSource messageSource;

    private LocalizedMessageService localizedMessageService;

    @BeforeEach
    void setUp() {
        localizedMessageService = new LocalizedMessageServiceImpl(appProperties, messageSource);
    }

    @Test
    void getMessage() {

        when(appProperties.getLocale()).thenReturn(Locale.getDefault());
        when(messageSource.getMessage("testCode", null, appProperties.getLocale())).thenReturn("test message");

        assertEquals("test message", localizedMessageService.getMessage("testCode", null));
    }
}