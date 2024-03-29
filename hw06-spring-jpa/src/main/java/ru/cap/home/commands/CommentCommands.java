package ru.cap.home.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import ru.cap.home.converters.CommentConverter;
import ru.cap.home.service.BookService;
import ru.cap.home.service.CommentService;

import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class CommentCommands {

    private final CommentService commentService;

    private final BookService bookService;

    private final CommentConverter commentConverter;

    @ShellMethod(value = "Create comment", key = "cc")
    public String createComment(@ShellOption(help = "Give an id of book", value = "book") long bookId,
                                @ShellOption(help = "Give a comment", value = "comment") String comment) {

        commentService.insert(comment, bookId);
        return "Comment added";
    }

    @ShellMethod(value = "Read comment", key = "cread")
    @Transactional
    public String readComment(@ShellOption(help = "Give an id of book", value = "book") long bookId) {

        String commentView = commentService.findAllByBookId(bookId).stream().map(commentConverter::commentToString)
                .collect(Collectors.joining("\n"));

        return commentView.isEmpty() ? "No comments found" : commentView;
    }

    @ShellMethod(value = "Update comment", key = "cupd")
    public String updateComment(@ShellOption(help = "Give an id of comment", value = "id") long commentId,
                                @ShellOption(help = "Give a comment", value = "comment") String comment) {
        commentService.update(comment, commentId);
        return "Comment updated";
    }

    @ShellMethod(value = "Delete comment", key = "cdel")
    public String deleteComment(@ShellOption(help = "Give an id of comment", value = "comment") long commentId) {
        commentService.deleteComment(commentId);
        return "Comment deleted";
    }

}
