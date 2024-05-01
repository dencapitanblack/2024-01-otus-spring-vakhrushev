package ru.cap.home.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cap.home.dto.BookDto;
import ru.cap.home.exceptions.EntityNotFoundException;
import ru.cap.home.models.Author;
import ru.cap.home.models.Book;
import ru.cap.home.models.Genre;
import ru.cap.home.repositories.AuthorRepository;
import ru.cap.home.repositories.BookRepository;
import ru.cap.home.repositories.GenreRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    @Override
    @Transactional
    public BookDto findById(long id) {
        return BookDto.toDto(bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BookDto with id = %d not found".formatted(id))));
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream().map(BookDto::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BookDto insert(String title, Long idAuthor, Long idGenre) {
        return save(0, title, idAuthor, idGenre);
    }

    @Override
    @Transactional
    public BookDto update(long id, String title) {

        BookDto book = findById(id);
        book.setTitle(title);
        return BookDto.toDto(bookRepository.save(BookDto.toDomain(book)));
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    private BookDto save(long id, String title, Long idAuthor, Long idGenre) {

        Author author = authorRepository.findById(idAuthor)
                .orElseThrow(() -> new EntityNotFoundException("Author with id %d not found".formatted(idAuthor)));

        Genre genre = genreRepository.findById(idGenre)
                .orElseThrow(() -> new EntityNotFoundException("Genre with id %d not found".formatted(idGenre)));


        var book = Book.builder()
                .id(id)
                .title(title)
                .author(author)
                .genre(genre)
                .build();

        BookDto bookDto = BookDto.toDto(bookRepository.save(book));

        return bookDto;
    }
}
