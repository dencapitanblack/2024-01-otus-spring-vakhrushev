package ru.otus.hw.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.hw.processor.QuestionProcessor;

import static org.mockito.Mockito.doAnswer;

@SpringBootTest
class TestServiceImplTest {

    @MockBean
    private UserService userService;

    @MockBean
    private QuestionProcessor questionProcessor;

    @MockBean
    private ResultService resultService;

    @Autowired
    private TestServiceImpl testService;

    @Test
    void executeTest() {

        doAnswer(invocationOnMock -> {
            return null;
        }).when(resultService).getResult();

        doAnswer(invocationOnMock -> {
            return null;
        }).when(questionProcessor).runProcess();

        doAnswer(invocationOnMock -> {
            return null;
        }).when(userService).runUserProfileCreator();

        testService.executeTest();
    }
}