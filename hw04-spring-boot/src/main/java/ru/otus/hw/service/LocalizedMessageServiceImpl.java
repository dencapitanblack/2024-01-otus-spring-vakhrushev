package ru.otus.hw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.hw.config.AppProperties;

@Service
@RequiredArgsConstructor
public class LocalizedMessageServiceImpl implements LocalizedMessageService {

    private final AppProperties appProperties;

    private final MessageSource messageSource;

    @Override
    public String getMessage(String messageCode, Object... args) {
        return messageSource.getMessage(messageCode, args, appProperties.getLocale());
    }
}
