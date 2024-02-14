package ru.otus.hw.service;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
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
}
