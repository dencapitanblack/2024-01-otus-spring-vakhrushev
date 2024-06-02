--liquibase formatted sql

--changeset dvakhrushev:2024-05-21--insert-data
iNSERT INTO author (full_name) VALUES('T. Draizer');
iNSERT INTO author (full_name) VALUES('A. Pushkin');
iNSERT INTO author (full_name) VALUES('M. Tven');
iNSERT INTO genre (genre_name) VALUES('Classic');
iNSERT INTO genre (genre_name) VALUES('Poem');
iNSERT INTO genre (genre_name) VALUES('Drama');
iNSERT INTO BOOK (AUTHOR_ID,GENRE_ID,TITLE) VALUES(1,1, 'Finansist');
iNSERT INTO BOOK (AUTHOR_ID,GENRE_ID,TITLE) VALUES(2,2, 'Time');
insert into comment(book_id, comment) values (1, 'Cool!');
insert into comment(book_id, comment) values (1, 'Best!');
insert into comment(book_id, comment) values (1, 'Exc!');
insert into comment(book_id, comment) values (2, 'Cool! v2');
insert into comment(book_id, comment) values (2, 'Best! v2');
insert into comment(book_id, comment) values (2, 'Exc! v2');
insert into users(username, password, enabled) values ('admin', 'admin', 1);
insert into users(username, password, enabled) values ('user', 'user', 1);