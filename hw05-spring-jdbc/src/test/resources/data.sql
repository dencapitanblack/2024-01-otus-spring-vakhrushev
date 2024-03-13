delete from book;
delete from author;
delete from genre;

insert into author(id, fullname)
values (1, 'Author_1'), (2, 'Author_2'), (3, 'Author_3');

insert into genre(id, genrename)
values (1, 'Genre_1'), (2, 'Genre_2'), (3, 'Genre_3');

insert into book(id, title, authorid, genreid)
values (1, 'BookTitle_1', 1, 1), (2, 'BookTitle_2', 2, 2), (3, 'BookTitle_3', 3, 3);