package ru.otus.hw.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CSVReaderUnitTest {

    @Mock
    private TestFileNameProvider fileNameProvider;

    private CSVReader csvReader;

    @BeforeEach
    void setUp() {
        csvReader = new CSVReader(fileNameProvider);
    }

    @Test
    void readData() {

        when(fileNameProvider.getTestFileName()).thenReturn("questions.csv");
        csvReader.readData();
    }
}