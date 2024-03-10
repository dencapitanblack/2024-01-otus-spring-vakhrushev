package ru.otus.hw.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = {CSVReader.class})
class CSVReaderTest {

    @MockBean
    private TestFileNameProvider fileNameProvider;

    @Autowired
    private CSVReader csvReader;

    @Test
    void readData() {

        when(fileNameProvider.getTestFileName()).thenReturn("questions.csv");
        csvReader.readData();
    }
}