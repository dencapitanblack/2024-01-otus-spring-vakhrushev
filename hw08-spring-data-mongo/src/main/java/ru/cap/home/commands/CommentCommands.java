package ru.cap.home.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.cap.home.converters.CommentConverter;
import ru.cap.home.service.BookService;
import ru.cap.home.service.CommentService;

@ShellComponent
@RequiredArgsConstructor
public class CommentCommands {

    private final CommentService commentService;

    private final BookService bookService;

    private final CommentConverter commentConverter;

    @ShellMethod(value = "Create comment", key = "cc")
    public String createComment(@ShellOption(help = "Give an id of book", value = "book") int bookId,
                                @ShellOption(help = "Give a comment", value = "comment") String comment) {

        commentService.insert(comment, bookId);
        return "Comment added";
    }

    @ShellMethod(value = "Read comment", key = "cread")
    public String readComment(@ShellOption(help = "Give an id of book", value = "book") int bookId) {

        String commentView = commentConverter.commentToString(
                commentService.findAllByBookId(bookService.getById(bookId).getId()));

        return commentView.isEmpty() ? "No comments found" : commentView;
    }

    @ShellMethod(value = "Update comment", key = "cupd")
    public String updateComment(@ShellOption(help = "Give an id of comment", value = "id") String commentId,
                                @ShellOption(help = "Give a comment", value = "comment") String comment) {
        commentService.update(comment, commentId);
        return "Comment updated";
    }

    @ShellMethod(value = "Delete comment", key = "cdel")
    public String deleteComment(@ShellOption(help = "Give an id of comment", value = "comment") String commentId) {

        commentService.findById(commentId);
        commentService.deleteComment(commentId);

        return "Comment deleted";
    }

}
