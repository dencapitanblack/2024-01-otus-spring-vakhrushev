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

    private final LocalizedMessageService localizedMessageService;

    @Override
    public void setAnswer(boolean isCorrect) {
        totalCountResult++;

        if (isCorrect) {
            correctCountResult++;
        }
    }

    @Override
    public String getResult() {
        String[] messageParams = new String[]{String.valueOf(correctCountResult), String.valueOf(totalCountResult)};
        return localizedMessageService.getMessage("user.result", messageParams);
    }

}
