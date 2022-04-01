--drop table if exists candidate cascade;

create table if not exists post (
    id serial primary key,
    name text,
    created timestamp
);

--insert into post (name, created) values ('МСК', '2000-01-01 02:00:00');

create table if not exists candidate (
    id serial primary key,
    name text,
    city_id int references city(id),
    created timestamp
);

create table if not exists users (
    id serial primary key,
    name text,
    email text,
    password text
);

create table if not exists city (
  id serial primary key,
  name text unique
);

insert into city (name) values ('МСК');
insert into city (name) values ('СПБ');
insert into city (name) values ('НН');
insert into city (name) values ('Брянск');
insert into city (name) values ('Киев');