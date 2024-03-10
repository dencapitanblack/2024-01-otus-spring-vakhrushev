package ru.otus.hw.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ResultServiceImplTest {

    @MockBean
    private LocalizedMessageService localizedMessageService;

    @Autowired
    private ResultServiceImpl resultService;

    @Test
    void setAnswer() {
        resultService.setAnswer(true);
        assertEquals(1, resultService.getTotalCountResult());
    }


    @Test
    void getResult() {
        String[] messageParams = new String[] {String.valueOf(resultService.getCorrectCountResult()), String.valueOf(resultService.getTotalCountResult())};
        when(localizedMessageService.getMessage("user.result", messageParams)).thenReturn("testCode");
        assertEquals("testCode", resultService.getResult());
    }
}