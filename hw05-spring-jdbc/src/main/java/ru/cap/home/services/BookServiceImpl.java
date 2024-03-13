package ru.cap.home.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ru.cap.home.models.Author;
import ru.cap.home.models.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cap.home.repositories.BookRepository;
import ru.cap.home.repositories.GenreRepository;
import ru.cap.home.repositories.AuthorRepository;
import ru.cap.home.exceptions.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final GenreRepository genreRepository;

    private final AuthorRepository authorRepository;

    @Override
    public Optional<Book> findById(long id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book insert(String title, long authorId, long genreId) {
        return save(0, title, authorId, genreId);
    }

    @Override
    public Book update(long id, String title, long authorId, long genreId) {
        return save(id, title, authorId, genreId);
    }

    @Override
    public Book update(long id, String title) {
        Book book = findById(id).orElseThrow(() -> new EntityNotFoundException("Book with id %d not found".formatted(id)));
        return save(id, title, book.getAuthor().getId(), book.getGenre().getId());
    }

    @Override
    public void deleteById(long id) {
        bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book with id %d not found".formatted(id)));
        bookRepository.deleteById(id);
    }

    private Book save(long id, String title, long authorId, long genreId) {

        title = String.join(" ", title.split(","));

        var author = authorRepository.findById(authorId).orElseThrow(() -> new EntityNotFoundException("Author with id %d not found".formatted(authorId)));
        var genre = genreRepository.findById(genreId).orElseThrow(() -> new EntityNotFoundException("Genre with id %d not found".formatted(genreId)));
        var book = new Book(id, title, author, genre);
        return bookRepository.save(book);
    }

}