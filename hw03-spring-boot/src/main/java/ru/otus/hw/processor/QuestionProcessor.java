package ru.otus.hw.processor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Answer;
import ru.otus.hw.domain.Question;
import ru.otus.hw.service.IOService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionProcessor implements ProgramProcessor {

    private final QuestionDao questionDao;

    private final AnswerProcessor answerProcessor;

    private final IOService ioService;

    @Override
    public void runProcess() {

        List<Question> questionList = questionDao.findAll();

        for (Question question : questionList) {
            askQuestion(question);
            answerProcessor.runProcess();
        }
    }

    private void askQuestion(Question question) {

        ioService.printFormattedLine(question.text());
        printAnswers(question.answers());
    }

    private void printAnswers(List<Answer> answers) {

        for (int i = 0; i < answers.size(); i++) {

            if (answers.get(i).isCorrect()) {
                answerProcessor.setCorrectAnswer(i + 1);
            }

            ioService.printFormattedLine("\t[%d] " + answers.get(i).text(), i + 1);
        }
    }

}
