package ru.otus.hw.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.doAnswer;

@ExtendWith(MockitoExtension.class)
class TestRunnerServiceImplTest {

    @Mock
    private TestService testService;

    private TestRunnerService testRunnerService;

    @BeforeEach
    void setUp() {
        testRunnerService = new TestRunnerServiceImpl(testService);
    }

    @Test
    void run() {

        doAnswer(invocationOnMock -> {
            return null;
        }).when(testService).executeTest();

        testRunnerService.run();

    }
}