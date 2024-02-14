package ru.otus.hw.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    @Getter
    private int correctCountResult;

    @Getter
    private int totalCountResult;


    private final IOService ioService;

    @Override
    public void setAnswer(boolean isCorrect) {
        totalCountResult++;

        if (isCorrect) {
            correctCountResult++;
        }
    }

    @Override
    public void printResult() {
        ioService.printFormattedLine("Testing is over. Your result is %d out of %d",
                correctCountResult,
                totalCountResult);
    }
}
