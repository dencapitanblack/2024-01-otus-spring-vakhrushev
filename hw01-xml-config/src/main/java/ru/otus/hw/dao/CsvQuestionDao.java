package ru.otus.hw.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import ru.otus.hw.config.TestFileNameProvider;
import ru.otus.hw.dao.dto.QuestionDto;
import ru.otus.hw.domain.Question;
import ru.otus.hw.exceptions.QuestionReadException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;



@RequiredArgsConstructor
public class CsvQuestionDao implements QuestionDao {

    private final TestFileNameProvider fileNameProvider;

    @Override
    public List<Question> findAll() {
        List<Question> retList = new ArrayList<>();
        String questionsFileName = fileNameProvider.getTestFileName();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(questionsFileName);
             InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            List<QuestionDto> questions = new CsvToBeanBuilder(reader).withSkipLines(1)
                    .withSeparator(';').withType(QuestionDto.class).build().parse();

            if (questions.isEmpty()) {
                throw new QuestionReadException("No data found!", new RuntimeException());
            }

            for (QuestionDto question : questions) {
                retList.add(question.toDomainObject());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return retList;
    }
}