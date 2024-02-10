--1)
create table departments(
    id serial primary key,
    name varchar(255)
);

create table employees(
    id serial primary key,
    name varchar(255),
    department_id int references departments(id)
);

insert into departments(name) values('Отдел кадров');
insert into departments(name) values('Производственный отдел');
insert into departments(name) values('Диспетчерская');
insert into departments(name) values('Бухгалтерия');
insert into departments(name) values('Отдел маркетинга');
insert into departments(name) values('Транспортный отдел');
insert into departments(name) values('Юристы');

insert into employees(name, department_id) values('Лидия Иванова', 1);
insert into employees(name, department_id) values('Антон Федоров', 2);
insert into employees(name, department_id) values('Сергей Емельянов', 2);
insert into employees(name, department_id) values('Андрей Опилкин', 3);
insert into employees(name, department_id) values('Татьяна Хвостик', 4);
insert into employees(name, department_id) values('Игорь Петренко', 5);
insert into employees(name, department_id) values('Светлана Антипова', 5);
insert into employees(name, department_id) values('Дмитрий Батурин', null);
insert into employees(name, department_id) values('Елена Задорожная', null);

--2)
select * from employees e left join departments d on e.department_id = d.id;
select * from employees e right join departments d on e.department_id = d.id;

select * from departments d left join employees e on e.department_id = d.id;
select * from departments d right join employees e on e.department_id = d.id;

select * from employees e full join departments d on e.department_id = d.id;

select * from employees e cross join departments d;

--3)
select * from departments d
left join employees e on e.department_id = d.id
where department_id is null;

--4)
select e.name "Имя сотрудника", d.name Отдел, e.department_id "ID отдела"
from employees e
left join departments d on e.department_id = d.id;

select e.name "Имя сотрудника", d.name Отдел, e.department_id "ID отдела"
from departments d
right join employees e on e.department_id = d.id;

--5)
create table teens(
    id serial primary key,
    name varchar(255),
    gender boolean
);

insert into teens(name, gender) values('Вася', true);
insert into teens(name, gender) values('Витя', true);
insert into teens(name, gender) values('Лена', false);
insert into teens(name, gender) values('Дима', true);
insert into teens(name, gender) values('Таня', false);
insert into teens(name, gender) values('Лера', false);
insert into teens(name, gender) values('Иван', true);
insert into teens(name, gender) values('Марина', false);
insert into teens(name, gender) values('Петя', true);
insert into teens(name, gender) values('Оля', false);
insert into teens(name, gender) values('Катя', false);
insert into teens(name, gender) values('Костя', true);
insert into teens(name, gender) values('Аня', false);

select t.name, teens.name from teens t
cross join teens
where t.gender > teens.gender;