package ru.otus.hw.config;


import java.util.List;

public interface DataReader<T> {
    List<T> readData();

}
