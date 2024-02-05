package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.hw.service.TestRunnerService;
import ru.otus.hw.service.TestRunnerServiceImpl;
import ru.otus.hw.service.TestService;

import static org.mockito.Mockito.doAnswer;

@ExtendWith(MockitoExtension.class)
public class TestRunnerServiceImplTest {

    @Mock
    private TestService testService;

    private TestRunnerService testRunnerService;

    @BeforeEach
    void setUp() {
        testRunnerService = new TestRunnerServiceImpl(testService);
    }

    @Test
    void run() {

        doAnswer(invocation -> {
            return null;
        }).when(testService).executeTest();

        testRunnerService.run();

    }
}