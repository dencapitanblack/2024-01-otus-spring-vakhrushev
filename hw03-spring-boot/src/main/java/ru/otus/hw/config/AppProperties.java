package ru.otus.hw.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Getter
@Component
public class AppProperties implements TestFileNameProvider, LocaleConfig {

    private final String testFileName;

    private final String pathToFile;

    private final Locale locale;

    public AppProperties(@Value("${user.filepath}") String pathToFile,
                         @Value("${user.filename}") String testFileName,
                         @Value("${user.locale}") Locale locale) {
        this.testFileName = testFileName;
        this.pathToFile = pathToFile;
        this.locale = locale;
    }

}
