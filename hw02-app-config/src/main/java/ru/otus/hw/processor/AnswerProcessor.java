package ru.otus.hw.processor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw.exceptions.IncorrectInputException;
import ru.otus.hw.service.IOService;
import ru.otus.hw.service.ResultService;

@Service
@RequiredArgsConstructor
public class AnswerProcessor implements ProgramProcessor {

    private final ResultService resultService;

    private final IOService ioService;

    private int correctAnswer;


    @Override
    public void runProcess() {

        while (true) {
            try {
                ioService.printWithoutNewLine("Your answer is: ");
                resultService.setAnswer(ioService.getIntegerUserInput() == correctAnswer);
                break;
            } catch (IncorrectInputException ex) {
                ioService.printFormattedLine("Ошибка!\n");
                ioService.printLine("");
            }
        }
    }

    public void setCorrectAnswer(int answerNdx) {
        correctAnswer = answerNdx;
    }


}
