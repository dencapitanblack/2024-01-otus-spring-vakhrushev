package ru.cap.home.service.listeners;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import ru.cap.home.models.Book;
import ru.cap.home.service.CommentService;

@Component
@RequiredArgsConstructor
public class BookCascadeDeleteMongoEventListener extends AbstractMongoEventListener<Book> {

    private final CommentService commentService;

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Book> event) {

        super.onBeforeDelete(event);
        String bookId = String.valueOf(event.getSource().get("_id"));
        commentService.findAllByBookId(bookId).forEach(comment -> commentService.deleteComment(comment.getId()));

    }
}
