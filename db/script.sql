create table person (
    id serial primary key,
    name varchar(255),
    gender boolean, age integer
);

insert into person(name, gender, age) values('John Doe', true, 38);

select * from person;

update person set name = 'Alex';

select * from person;

delete from person;

select * from person;