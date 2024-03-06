package ru.otus.hw.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import ru.otus.hw.config.AppProperties;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class LocalizedMessageServiceImplTest {

    @MockBean
    private AppProperties appProperties;

    @MockBean
    private MessageSource messageSource;

    @Autowired
    private LocalizedMessageService localizedMessageService;

    @Test
    void getMessage() {

        when(appProperties.getLocale()).thenReturn(Locale.getDefault());
        when(messageSource.getMessage("testCode", null, appProperties.getLocale())).thenReturn("test message");

        assertEquals("test message", localizedMessageService.getMessage("testCode", null));
    }
}