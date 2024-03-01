package ru.otus.hw.service;

public interface LocalizedMessageService {
    String getMessage(String messageCode, Object... args);
}
