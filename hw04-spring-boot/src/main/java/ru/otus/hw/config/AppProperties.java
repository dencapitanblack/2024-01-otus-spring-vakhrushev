package ru.otus.hw.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;
import java.util.Map;

@Setter
@ConfigurationProperties(prefix = "user", ignoreUnknownFields = false)
public class AppProperties implements TestFileNameProvider, LocaleConfig {

    @Getter
    private Locale locale;

    private Map<String, String> fileNameByLocaleTag;

    public String getTestFileName() {
        return fileNameByLocaleTag.get(locale.toLanguageTag());
    }
}
