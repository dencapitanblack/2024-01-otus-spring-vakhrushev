package config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.hw.config.*;
import ru.otus.hw.dao.dto.QuestionDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CSVReaderIntegrationTest {

    @Mock
    TestFileNameProvider testFileNameProvider;

    @InjectMocks
    CSVReader dataReader;

    @Test
    @DisplayName("integration read data test")
    public void readDataTest() {

        when(testFileNameProvider.getTestFileName()).then(invocation -> "test_questions.csv");
        List<QuestionDto> questions = dataReader.readData();

        assertEquals(2, questions.size());
    }
}
