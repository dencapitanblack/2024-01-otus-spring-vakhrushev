package ru.otus.hw.config;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.hw.dao.dto.QuestionDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CSVReader implements DataReader<QuestionDto> {

    private final TestFileNameProvider fileNameProvider;

    @Override
    public List<QuestionDto> readData() {

        String questionsFileName = fileNameProvider.getTestFileName();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(questionsFileName);
             InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            List<QuestionDto> questions = new CsvToBeanBuilder<QuestionDto>(reader).withSkipLines(1)
                    .withSeparator(';').withType(QuestionDto.class).build().parse();

            return questions;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
