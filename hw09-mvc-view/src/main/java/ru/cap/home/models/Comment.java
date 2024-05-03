package ru.cap.home.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GenerationType;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "comment", length = 1000, nullable = false)
    private String comment;

    @ManyToOne(targetEntity = Book.class, fetch = FetchType.EAGER)
    private Book book;

    public Comment(String comment, Book book) {
        this.comment = comment;
        this.book = book;
    }

    public String toString() {
        return "id " + id + " comment " + comment;
    }

}


