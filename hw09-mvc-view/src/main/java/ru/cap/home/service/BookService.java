package ru.cap.home.service;

import ru.cap.home.dto.BookDto;

import java.util.List;

public interface BookService {

    BookDto findById(long id);

    List<BookDto> findAll();

    BookDto insert(String title, Long idAuthor, Long idGenre);

    BookDto update(long id, String title);

    void deleteById(long id);

}
