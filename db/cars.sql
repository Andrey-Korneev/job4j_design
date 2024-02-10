create table car_bodies(
    id serial primary key,
    name varchar(255)
);

create table car_engines(
    id serial primary key,
    name varchar(255)
);

create table car_transmissions(
    id serial primary key,
    name varchar(255)
);

create table cars(
    id serial primary key,
    name varchar(255),
    body_id int references car_bodies(id),
    engine_id int references car_engines(id),
    transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values('�����');
insert into car_bodies(name) values('�������');
insert into car_bodies(name) values('�����');
insert into car_bodies(name) values('���������');
insert into car_bodies(name) values('���������');
insert into car_bodies(name) values('�������');
insert into car_bodies(name) values('�����������');
insert into car_bodies(name) values('����');
insert into car_bodies(name) values('�������');

insert into car_engines(name) values('����������');
insert into car_engines(name) values('���������');
insert into car_engines(name) values('���������');
insert into car_engines(name) values('�������������');

insert into car_transmissions(name) values('������������');
insert into car_transmissions(name) values('�������');
insert into car_transmissions(name) values('�����');
insert into car_transmissions(name) values('��������');

insert into cars(name, body_id, engine_id, transmission_id) values('�������', 1, 2, 2);
insert into cars(name, body_id, engine_id, transmission_id) values('��������', 4, 1, 2);
insert into cars(name, body_id, engine_id, transmission_id) values('������', 5, 3, 3);
insert into cars(name, body_id, engine_id, transmission_id) values('����', 3, 2, 1);
insert into cars(name, body_id, engine_id, transmission_id) values('����', 1, 3, 3);
insert into cars(name, body_id, engine_id, transmission_id) values('����', 6, null, 2);
insert into cars(name, body_id, engine_id, transmission_id) values('������', 7, 1, 1);
insert into cars(name, body_id, engine_id, transmission_id) values('����', 6, 2, 3);
insert into cars(name, body_id, engine_id, transmission_id) values('����', 2, 1, 2);
insert into cars(name, body_id, engine_id, transmission_id) values('�����', 1, 1, 3);
insert into cars(name, body_id, engine_id, transmission_id) values('������', 4, 2, null);
insert into cars(name, body_id, engine_id, transmission_id) values('���', 1, 2, 1);
insert into cars(name, body_id, engine_id, transmission_id) values('�����', 2, 2, 2);

--1)
select c.id ID, c.name �����, b.name �����, e.name "��� ���������", t.name �����������
from cars c
left join car_bodies b on c.body_id = b.id
left join car_engines e on c.engine_id = e.id
left join car_transmissions t on c.transmission_id = t.id;

--2)
select b.id, b.name from car_bodies b
left join cars c on c.body_id = b.id
where c.body_id is null;

--3)
select e.id, e.name from car_engines e
left join cars c on c.engine_id = e.id
where c.engine_id is null;

--4)
select t.id, t.name from car_transmissions t
left join cars c on c.transmission_id = t.id
where c.transmission_id is null;