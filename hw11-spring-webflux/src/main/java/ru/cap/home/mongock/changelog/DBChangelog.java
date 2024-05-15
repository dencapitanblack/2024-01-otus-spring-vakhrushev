package ru.cap.home.mongock.changelog;

import com.mongodb.reactivestreams.client.MongoCollection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.cap.home.models.Comment;
import ru.cap.home.repositories.BookRepository;
import ru.cap.home.repositories.CommentRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class DBChangelog implements ApplicationRunner {

    private final ReactiveMongoDatabaseFactory factory;

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {

        dropDb()
                .then(insertAuthors())
                .then(insertGenres())
                .then(insertBook())
                .thenMany(insertComment())
                .subscribe();

    }

    public Mono<Void> dropDb() {
        return factory.getMongoDatabase().flatMap(mongoDatabase -> Mono.when(mongoDatabase.drop()));
    }

    public Mono<Void> insertAuthors() {

        return factory.getMongoDatabase().flatMap(db -> {

            MongoCollection<Document> myCollection = db.getCollection("author");

            return Mono.when(
                    myCollection.insertOne(new Document().append("fullName", "Lermontov")),
                    myCollection.insertOne(new Document().append("fullName", "Mark Tven")),
                    myCollection.insertOne(new Document().append("fullName", "Lev Tolstoy")),
                    myCollection.insertOne(new Document().append("fullName", "Theodor Draizer"))
            );
        });
    }

    public Mono<Void> insertGenres() {

        return factory.getMongoDatabase().flatMap(db -> {

            MongoCollection<Document> myCollection = db.getCollection("genre");

            return Mono.when(
                    myCollection.insertOne(new Document().append("genreName", "Novel")),
                    myCollection.insertOne(new Document().append("genreName", "Ode")),
                    myCollection.insertOne(new Document().append("genreName", "Essay")),
                    myCollection.insertOne(new Document().append("genreName", "Poem")),
                    myCollection.insertOne(new Document().append("genreName", "Play")),
                    myCollection.insertOne(new Document().append("genreName", "History"))
            );
        });
    }

    public Mono<Void> insertBook() {

        return factory.getMongoDatabase().flatMap(db -> {

            MongoCollection<Document> myCollection = db.getCollection("book");

            return Mono.when(

                    myCollection.insertOne(new Document().append("title", "Years book 1")
                            .append("author", "Lermontov").append("genre", "Novel")),
                    myCollection.insertOne(new Document().append("title", "Years book 2")
                            .append("author", "Lev Tolstoy").append("genre", "Poem")),
                    myCollection.insertOne(new Document().append("title", "Years book 3")
                            .append("author", "Mark Tven").append("genre", "Play")),
                    myCollection.insertOne(new Document().append("title", "Years book 4")
                            .append("author", "Theodor Draizer").append("genre", "History"))
            );
        });
    }

    public Flux<Void> insertComment() {

        return bookRepository.findAll().flatMap(book -> {
            return Mono.when(
                    commentRepository.save(new Comment("comment_1_" + book.getId(), book)),
                    commentRepository.save(new Comment("comment_2_" + book.getId(), book))
                    );
        });

    }
}