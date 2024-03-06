package ru.otus.hw.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.doAnswer;


@SpringBootTest
class TestRunnerServiceImplTest {

    @MockBean
    private TestService testService;

    @Autowired
    private TestRunnerService testRunnerService;

    @Test
    void run() {

        doAnswer(invocationOnMock -> {
            return null;
        }).when(testService).executeTest();

        testRunnerService.run();

    }
}