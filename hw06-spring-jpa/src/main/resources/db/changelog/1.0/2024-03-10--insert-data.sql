--liquibase formatted sql

--changeset dvakhrushev:2024-03-10--insert-data
insert into author(fullname) values ('A. Pushkin'), ('A. Hayley'), ('K. Smith'), ('Z. Freud');
insert into genre(genrename) values ('Fiction'), ('Non-fiction'), ('Drama'), ('Chick lit'), ('Fantasy'), ('Travels book'), ('Fairy tale');
insert into book(title, authorid, genreid) values ('BookTitle_pr', 1, 1), ('BookTitle_2pr', 2, 2), ('BookTitle_3pr', 3, 3);