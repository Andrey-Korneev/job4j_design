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

insert into department(name, business_code) values('����� ������', '�98-12');

insert into employee(name, age, department_id) values('Oleg Smirnov', 28, 1);

insert into employee(name, age, department_id) values('Irina Timofeeva', 32, 1);

insert into employee(name, age) values('Fedor Utkin', 21);

select e.name, e.age, d.name from employee as e join department as d on e.department_id = d.id;

select e.name as ���, e.age as �������, d.name as �����
    from employee as e inner join department as d on e.department_id = d.id;

select e.name ���, d.name �����, d.business_code as "��� ������������"
    from employee e join department d on e.department_id = d.id;