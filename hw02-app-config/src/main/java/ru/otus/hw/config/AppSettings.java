package ru.otus.hw.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Getter
@Component
@PropertySource("classpath:app-setting.properties")
public class AppSettings {

    private final String pathToFile;

    private final String questionFileName;

    public AppSettings(@Value("${app.filepath}") String pathToFile, @Value("${app.filename}") String questionFileName) {
        this.pathToFile = pathToFile;
        this.questionFileName = questionFileName;
    }
}
