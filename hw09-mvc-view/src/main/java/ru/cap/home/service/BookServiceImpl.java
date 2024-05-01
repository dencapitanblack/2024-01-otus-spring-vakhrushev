package ru.cap.home.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cap.home.exceptions.EntityNotFoundException;
import ru.cap.home.models.Author;
import ru.cap.home.models.Book;
import ru.cap.home.models.Genre;
import ru.cap.home.repositories.AuthorRepository;
import ru.cap.home.repositories.BookRepository;
import ru.cap.home.repositories.GenreRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    @Override
    @Transactional
    public Book findById(long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BookDto with id = %d not found".formatted(id)));
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public Book insert(String title, Set<Long> idAuthors, Set<Long> idGenres) {
        return save(0, title, idAuthors, idGenres);
    }

    @Override
    @Transactional
    public Book update(long id, String title) {
        Book book = findById(id);
        book.setTitle(title);
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    private Book save(long id, String title, Set<Long> idAuthors, Set<Long> idGenres) {

        title = String.join(" ", title.split(","));

        Set<Author> authors = idAuthors.stream()
                .map(a -> authorRepository.findById(a)
                .orElseThrow(() -> new EntityNotFoundException("Author with id %d not found".formatted(a))))
                .collect(Collectors.toSet());

        Set<Genre> genres = idGenres.stream()
                .map(g -> genreRepository.findById(g)
                        .orElseThrow(() -> new EntityNotFoundException("Genre with id %d not found".formatted(g))))
                .collect(Collectors.toSet());

        var book = Book.builder()
                .id(id)
                .title(title)
                .authors(authors)
                .genres(genres)
                .build();

        return bookRepository.save(book);
    }



}
