package ru.cap.home.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cap.home.exceptions.EntityNotFoundException;
import ru.cap.home.exceptions.FieldRequired;
import ru.cap.home.models.Book;
import ru.cap.home.repositories.AuthorRepository;
import ru.cap.home.repositories.BookRepository;
import ru.cap.home.repositories.GenreRepository;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    @Override
    public Book getById(int id) {
        return findById(id);
    }

    private Book findById(int id) {

        if (id >= findAll().size()) {
            throw new EntityNotFoundException("Book with id %d not found".formatted(id));
        }

        return findAll().get(id);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book insert(String title, Set<String> authors, Set<String> genres) {

        Book book = new Book();

        book.setBookName(title.replaceAll(",", " "));
        book.setGenres(genres);
        book.setAuthors(authors);

        return save(book);
    }

    @Override
    public Book update(int id, String title) {
        Book book = findById(id);
        book.setBookName(title.replaceAll(",", " "));
        return save(book);
    }

    @Override
    public void deleteById(int id) {
        bookRepository.delete(findById(id));
    }

    private Book save(Book book) {

        if (book.getAuthors().isEmpty()) {
            throw new FieldRequired("Authors must be set");
        }

        if (book.getGenres().isEmpty()) {
            throw new FieldRequired("Genres must be set");
        }

        if (book.getBookName().isEmpty()) {
            throw new FieldRequired("Book name must be filled");
        }

        return bookRepository.save(book);
    }



}
