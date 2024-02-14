package ru.otus.hw.config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppProperties implements TestFileNameProvider {

    @Getter
    private String testFileName;

    public AppProperties(@Value("questions.csv") String testFileName) {
        this.testFileName = testFileName;
    }

}
