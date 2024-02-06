create table department(
    id serial primary key,
    name varchar(255),
    business_code varchar(255)
);

create table employee(
    id serial primary key,
    name varchar(255),
    age int,
    department_id int references department(id)
);

insert into department(name, business_code) values('Отдел продаж', 'П98-12');

insert into employee(name, age, department_id) values('Олег Смирнов', 28, 1);

insert into employee(name, age, department_id) values('Ирина Тимофеева', 32, 1);

insert into employee(name, age) values('Федор Уткин', 21);

select e.name, e.age, d.name from employee as e join department as d on e.department_id = d.id;

select e.name as Имя, e.age as Возраст, d.name as Отдел
    from employee as e inner join department as d on e.department_id = d.id;

select e.name Имя, d.name Отдел, d.business_code as "Вид деятельности"
    from employee e join department d on e.department_id = d.id;