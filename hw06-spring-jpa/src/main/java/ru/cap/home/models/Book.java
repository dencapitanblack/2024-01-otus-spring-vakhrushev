package ru.cap.home.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    // entity graph
    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "book_id")
    private List<Comment> comments;


    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Genre.class, cascade = CascadeType.PERSIST)
    @JoinTable(name = "book_genre", joinColumns = @JoinColumn(name = "book_id"))
    private List<Genre> genres;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Author.class, cascade = CascadeType.PERSIST)
    @JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;

    public Book(String title, List<Genre> genres, List<Author> authors) {
        this.title = title;
        this.genres = genres;
        this.authors = authors;
    }

    public Book(long id, String title, List<Author> authors, List<Genre> genres) {
        this.id = id;
        this.title = title;
        this.genres = genres;
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title;
    }
}
