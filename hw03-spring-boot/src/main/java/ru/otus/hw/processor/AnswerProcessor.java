package ru.otus.hw.processor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw.exceptions.IncorrectInputException;
import ru.otus.hw.service.IOService;
import ru.otus.hw.service.LocalizedMessageService;
import ru.otus.hw.service.ResultService;

@Service
@RequiredArgsConstructor
public class AnswerProcessor implements ProgramProcessor {

    private final ResultService resultService;

    private final IOService ioService;

    private final LocalizedMessageService localizedMessageService;

    private int correctAnswer;


    @Override
    public void runProcess() {

        while (true) {
            try {

                ioService.printWithoutNewLine(localizedMessageService.getMessage("process.answer.is"));
                int userInput = ioService.getIntegerUserInput();
                resultService.setAnswer(userInput == correctAnswer);
                break;

            } catch (IncorrectInputException ex) {

                ioService.printFormattedLine(localizedMessageService.getMessage("process.answer.input.exception"));
            }
        }
    }

    public void setCorrectAnswer(int answerNdx) {
        correctAnswer = answerNdx;
    }


}
