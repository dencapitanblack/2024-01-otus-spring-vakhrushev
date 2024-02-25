package ru.otus.hw.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    @Getter
    private int correctCountResult;

    @Getter
    private int totalCountResult;

    @Override
    public void setAnswer(boolean isCorrect) {
        totalCountResult++;

        if (isCorrect) {
            correctCountResult++;
        }
    }

    @Override
    public String getResult() {
        return String.format("Testing is over. Your result is %d out of %d", correctCountResult, totalCountResult);
    }
}
