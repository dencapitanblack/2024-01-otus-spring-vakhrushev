insert into author(full_name) values ('author_1');
insert into author(full_name) values ('author_2');

insert into genre(genre_name) values ('genre_1');
insert into genre(genre_name) values ('genre_2');
insert into genre(genre_name) values ('genre_3');
insert into genre(genre_name) values ('genre_4');
insert into genre(genre_name) values ('genre_5');
insert into genre(genre_name) values ('genre_6');
insert into genre(genre_name) values ('genre_7');

insert into book(title) values ('title_1');
insert into book(title) values ('title_2');
insert into comment(comment, book_id) values ('comment_1_1', 1);
insert into comment(comment, book_id) values ('comment_1_2', 1);
insert into comment(comment, book_id) values ('comment_1_3', 1);
insert into comment(comment, book_id) values ('comment_1_4', 1);
insert into comment(comment, book_id) values ('comment_2_1', 2);
insert into comment(comment, book_id) values ('comment_2_1', 2);

insert into book_author (author_id, book_id ) values(1, 1);
insert into book_author (author_id, book_id ) values(2, 2);
insert into book_author (author_id, book_id ) values(2, 1);
insert into book_genre (book_id, genres_id) values (2, 1);
insert into book_genre (book_id, genres_id) values (1, 1);
insert into book_genre (book_id, genres_id) values (1, 2);
insert into book_genre (book_id, genres_id) values (1, 3);
