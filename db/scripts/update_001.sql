create table if not exists post (
    id serial primary key,
    name text
);

create table if not exists candidate (
    id serial primary key,
    name text
);

create table if not exists users (
    id serial primary key,
    name text,
    email text,
    password text
);