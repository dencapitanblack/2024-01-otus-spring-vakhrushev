package ru.otus.hw.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.hw.exceptions.IncorrectInputException;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

@Service
public class StreamsIOService implements IOService {

    private final PrintStream printStream;

    private final Scanner scanner;

    public StreamsIOService(@Value("#{T(System).out}") PrintStream printStream,
                            @Value("#{T(System).in}") InputStream inputStream) {
        this.printStream = printStream;
        this.scanner = new Scanner(inputStream);
    }

    @Override
    public void printLine(String s) {
        printStream.println(s);
    }

    @Override
    public void printWithoutNewLine(String s) {
        printStream.print(s);
    }

    @Override
    public void printFormattedLine(String s, Object... args) {
        printStream.printf(s + "%n", args);
    }

    @Override
    public int getIntegerUserInput() throws IncorrectInputException {

        try {
            return scanner.nextInt();
        } catch (InputMismatchException ex) {
            throw new IncorrectInputException("Answer must be a valid integer value", new RuntimeException());
        }
    }

    @Override
    public String getStringUserInput() {
        return scanner.nextLine();
    }
}
