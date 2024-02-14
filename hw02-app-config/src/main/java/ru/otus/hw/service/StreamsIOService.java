package ru.otus.hw.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.hw.exceptions.IncorrectInputException;

import java.io.InputStream;
import java.io.PrintStream;

@Service
public class StreamsIOService implements IOService {

    private final PrintStream printStream;

    private final InputStream inputStream;

    public StreamsIOService(@Value("") PrintStream printStream, InputStream inputStream) {

        this.printStream = printStream;
        this.inputStream = inputStream;
    }

    @Override
    public void printLine(String s) {
        printStream.println(s);
    }

    @Override
    public void printFormattedLine(String s, Object... args) {
        printStream.printf(s + "%n", args);
    }

    @Override
    public int getIntegerUserInput() throws IncorrectInputException {
        return 0;
    }

    @Override
    public String getStringUserInput() {
        return null;
    }
}
