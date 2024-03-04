package ru.otus.hw.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResultServiceImplTest {

    @Mock
    private LocalizedMessageService localizedMessageService;

    private ResultServiceImpl resultService;

    @BeforeEach
    void setUp() {
        resultService = new ResultServiceImpl(localizedMessageService);
    }

    @Test
    void setAnswer() {
        resultService.setAnswer(true);
        assertEquals(1, resultService.getTotalCountResult());
    }


    @Test
    void getResult() {
        when(localizedMessageService.getMessage("user.result", new String[] {"0", "0"})).thenReturn("testCode");
        assertEquals("testCode", resultService.getResult());
    }
}