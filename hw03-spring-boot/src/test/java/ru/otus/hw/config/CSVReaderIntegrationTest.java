package ru.otus.hw.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.hw.dao.dto.QuestionDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CSVReaderIntegrationTest {

    @Mock
    private TestFileNameProvider testFileNameProvider;

    private CSVReader dataReader;

    @Test
    @DisplayName("integration read data test")
    public void readDataTest() {

        dataReader = new CSVReader(testFileNameProvider);

        when(testFileNameProvider.getTestFileName()).thenReturn("questions.csv");
        List<QuestionDto> questions = dataReader.readData();

        assertEquals(5, questions.size());
    }
}