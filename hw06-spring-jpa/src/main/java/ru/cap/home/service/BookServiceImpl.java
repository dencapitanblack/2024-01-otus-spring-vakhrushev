package ru.cap.home.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cap.home.exceptions.EntityNotFoundException;
import ru.cap.home.models.Author;
import ru.cap.home.models.Book;
import ru.cap.home.models.Genre;
import ru.cap.home.repositories.AuthorRepository;
import ru.cap.home.repositories.BookRepository;
import ru.cap.home.repositories.GenreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    @Override
    public Book findById(long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment with id = %id not found".formatted(id)));
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book insert(String title, List<Long> idAuthors, List<Long> idGenres) {
        return save(0, title, idAuthors, idGenres);
    }

    @Override
    public Book update(long id, String title, List<Long> idAuthors, List<Long> idGenres) {
        return save(id, title, idAuthors, idGenres);
    }

    @Override
    public Book update(long id, String title) {
        Book book = findById(id);
        book.setTitle(title);
        return bookRepository.save(book);
    }

    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    private Book save(long id, String title, List<Long> idAuthors, List<Long> idGenres) {

        title = String.join(" ", title.split(","));

        List<Author> authors = idAuthors.stream()
                .map(a -> authorRepository.findById(a)
                .orElseThrow(() -> new EntityNotFoundException("Author with id %d not found".formatted(a))))
                .toList();

        List<Genre> genres = idGenres.stream()
                .map(g -> genreRepository.findById(g)
                        .orElseThrow(() -> new EntityNotFoundException("Author with id %d not found".formatted(g))))
                .toList();

        var book = new Book(id, title, authors, genres);
        return bookRepository.save(book);
    }



}
