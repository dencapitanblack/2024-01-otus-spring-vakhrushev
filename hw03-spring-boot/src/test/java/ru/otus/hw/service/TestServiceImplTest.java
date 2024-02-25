package ru.otus.hw.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.hw.processor.QuestionProcessor;

import static org.mockito.Mockito.doAnswer;

@ExtendWith(MockitoExtension.class)
class TestServiceImplTest {

    @Mock
    private UserService userService;

    @Mock
    private QuestionProcessor questionProcessor;

    @Mock
    private ResultService resultService;

    @Mock
    private IOService ioService;

    private TestServiceImpl testService;

    @BeforeEach
    void setUp() {
        testService = new TestServiceImpl(userService,questionProcessor,resultService,ioService);
    }

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