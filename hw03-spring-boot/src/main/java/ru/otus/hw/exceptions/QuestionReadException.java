package ru.otus.hw.exceptions;

public class QuestionReadException extends AppException {
    public QuestionReadException(String message, Throwable ex) {
        super(message, ex);
    }
}
