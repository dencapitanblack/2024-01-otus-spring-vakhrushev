package ru.otus.hw.config;


import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class AppProperties implements TestFileNameProvider {

    @Getter
    private String testFileName;

    public AppProperties(AppSettings appSettings) {
        this.testFileName = appSettings.getQuestionFileName();
    }

}
