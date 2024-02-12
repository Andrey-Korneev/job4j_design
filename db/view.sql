create table students(
    id serial primary key,
    name varchar(50)
);

create table authors(
    id serial primary key,
    name varchar(50)
);

create table books(
    id serial primary key,
    name varchar(200),
    author_id integer references authors(id)
);

create table orders(
    id serial primary key,
    active boolean default true,
    book_id integer references books(id),
    student_id integer references students(id)
);

insert into students (name) values ('Иван Иванов');
insert into students (name) values ('Петр Петров');
insert into students (name) values ('Олег Смирнов');
insert into students (name) values ('Ирина Волкова');

insert into authors (name) values ('Александр Пушкин');
insert into authors (name) values ('Николай Гоголь');

insert into books (name, author_id) values ('Евгений Онегин', 1);
insert into books (name, author_id) values ('Капитанская дочка', 1);
insert into books (name, author_id) values ('Дубровский', 1);
insert into books (name, author_id) values ('Мертвые души', 2);
insert into books (name, author_id) values ('Вий', 2);

insert into orders (book_id, student_id) values (1, 1);
insert into orders (book_id, student_id) values (1, 3);
insert into orders (active, book_id, student_id) values (false, 5, 2);
insert into orders (book_id, student_id) values (3, 1);
insert into orders (book_id, student_id) values (4, 4);
insert into orders (book_id, student_id) values (4, 2);
insert into orders (active, book_id, student_id) values (false, 2, 2);
insert into orders (book_id, student_id) values (5, 3);
insert into orders (book_id, student_id) values (2, 4);
insert into orders (book_id, student_id) values (2, 3);
insert into orders (book_id, student_id) values (2, 1);

create view show_books_more_than_1_has_been_issued
as
select b.name Книга, a.name Автор, count(b.name) "Кол-во выданных (если больше одной)"
from books b
join orders o on b.id = o.book_id
join authors a on b.author_id = a.id
where o.active = true
group by (b.name, a.name)
having count(b.name) >= 2;

select * from show_books_more_than_1_has_been_issued;