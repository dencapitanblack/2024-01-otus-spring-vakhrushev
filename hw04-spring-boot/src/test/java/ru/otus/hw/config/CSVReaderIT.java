package ru.otus.hw.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.hw.dao.dto.QuestionDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CSVReaderIT {

    @Autowired
    private CSVReader dataReader;

    @Test
    @DisplayName("integration read data test")
    public void readDataTest() {

        List<QuestionDto> questions = dataReader.readData();
        assertEquals(5, questions.size());
    }
}