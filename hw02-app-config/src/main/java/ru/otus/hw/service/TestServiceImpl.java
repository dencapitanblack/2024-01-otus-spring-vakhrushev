package ru.otus.hw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw.processor.QuestionProcessor;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final UserService userService;

    private final QuestionProcessor questionProcessor;

    private final ResultService resultService;


    @Override
    public void executeTest() {

        userService.runUserProfileCreator();
        questionProcessor.runProcess();
        resultService.printResult();

    }
}