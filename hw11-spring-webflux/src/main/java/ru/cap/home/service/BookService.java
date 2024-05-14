package ru.cap.home.service;

import ru.cap.home.dto.BookDto;

import java.util.List;

public interface BookService {

    BookDto findById(long id);

    List<BookDto> findAll();

    BookDto insert(String title, Long idAuthor, Long idGenre);

    BookDto update(BookDto bookDto);

    void deleteById(long id);

}
