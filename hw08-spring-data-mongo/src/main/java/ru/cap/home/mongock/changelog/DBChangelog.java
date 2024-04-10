package ru.cap.home.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;

@ChangeLog(order = "001")
public class DBChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "den", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insert authors", author = "den", runAlways = true)
    public void insertAuthors(MongoDatabase db) {
        MongoCollection<Document> myCollection = db.getCollection("author");
        var doc1 = new Document().append("authorName", "Lermontov");
        var doc2 = new Document().append("authorName", "Mark Tven");
        var doc3 = new Document().append("authorName", "Lev Tolstoy");
        var doc4 = new Document().append("authorName", "Theodor Draizer");
        myCollection.insertMany(List.of(doc1, doc2, doc3, doc4));
    }

    @ChangeSet(order = "003", id = "insert genres", author = "den", runAlways = true)
    public void insertGenres(MongoDatabase db) {
        MongoCollection<Document> myCollection = db.getCollection("genre");
        var doc1 = new Document().append("genreName", "Novel");
        var doc2 = new Document().append("genreName", "Ode");
        var doc3 = new Document().append("genreName", "Essay");
        var doc4 = new Document().append("genreName", "Poem");
        var doc5 = new Document().append("genreName", "Play");
        var doc6 = new Document().append("genreName", "History");
        myCollection.insertMany(List.of(doc1, doc2, doc3, doc4, doc5, doc6));
    }

    @ChangeSet(order = "004", id = "insert books", author = "den", runAlways = true)
    public void insertBook(MongoDatabase db) {
        MongoCollection<Document> myCollection = db.getCollection("book");
        var doc1 = new Document().append("bookName", "Years book").append("authors", "Lermontov, Theodor Draizer").append("genres", "Poem, Novel");
        var doc2 = new Document().append("bookName", "Years book").append("authors", "Lev Tolstoy").append("genres", "Poem, Ode");
        var doc3 = new Document().append("bookName", "Years book").append("authors", "Mark Tven").append("genres", "Play");
        var doc4 = new Document().append("bookName", "Years book").append("authors", "Theodor Draizer").append("genres", "History");
        myCollection.insertMany(List.of(doc1, doc2, doc3, doc4));
    }


}