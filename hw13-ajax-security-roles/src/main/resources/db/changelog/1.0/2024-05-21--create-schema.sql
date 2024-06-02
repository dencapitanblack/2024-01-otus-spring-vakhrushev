--liquibase formatted sql

--changeset dvakhrushev:2024-05-21--create-schema

drop table if exists author cascade ;

drop table if exists book cascade ;

drop table if exists comment cascade ;

drop table if exists genre cascade ;

create table author (
                        id bigint generated by default as identity,
                        full_name varchar(255) not null,
                        primary key (id)
);

create table book (
                      author_id bigint not null,
                      genre_id bigint not null,
                      id bigint generated by default as identity,
                      title varchar(255) not null,
                      primary key (id)
);

create table comment (
                         book_id bigint,
                         id bigint generated by default as identity,
                         comment varchar(1000) not null,
                         primary key (id)
);

create table genre (
                       id bigint generated by default as identity,
                       genre_name varchar(255) not null,
                       primary key (id)
);

alter table if exists book
    add constraint FKklnrv3weler2ftkweewlky958
    foreign key (author_id)
    references author;

alter table if exists book
    add constraint FKm1t3yvw5i7olwdf32cwuul7ta
    foreign key (genre_id)
    references genre;

alter table if exists comment
    add constraint FKkko96rdq8d82wm91vh2jsfak7
    foreign key (book_id)
    references book  ON DELETE CASCADE;

CREATE TABLE users (
                       username VARCHAR(50) NOT NULL,
                       password VARCHAR(100) NOT NULL,
                       enabled TINYINT NOT NULL DEFAULT 1,
                       PRIMARY KEY (username)
);