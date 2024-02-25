package ru.otus.hw.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.hw.config.DataReader;
import ru.otus.hw.dao.dto.QuestionDto;
import ru.otus.hw.domain.Question;
import ru.otus.hw.exceptions.QuestionReadException;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CsvQuestionDao implements QuestionDao {

    private final DataReader<QuestionDto> dataReader;

    @Override
    public List<Question> findAll() {
        List<Question> retList = new ArrayList<>();

        List<QuestionDto> questions = dataReader.readData();

        if (questions.isEmpty()) {
            throw new QuestionReadException("No data found!", new RuntimeException());
        }

        for (QuestionDto question : questions) {
            retList.add(question.toDomainObject());
        }

        return retList;
    }
}