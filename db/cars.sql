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

insert into car_bodies(name) values('Седан');
insert into car_bodies(name) values('Хетчбэк');
insert into car_bodies(name) values('Пикап');
insert into car_bodies(name) values('Универсал');
insert into car_bodies(name) values('Кроссовер');
insert into car_bodies(name) values('Минивэн');
insert into car_bodies(name) values('Внедорожник');
insert into car_bodies(name) values('Купе');
insert into car_bodies(name) values('Родстер');

insert into car_engines(name) values('Бензиновый');
insert into car_engines(name) values('Дизельный');
insert into car_engines(name) values('Гибридный');
insert into car_engines(name) values('Электрический');

insert into car_transmissions(name) values('Механическая');
insert into car_transmissions(name) values('Автомат');
insert into car_transmissions(name) values('Робот');
insert into car_transmissions(name) values('Вариатор');

insert into cars(name, body_id, engine_id, transmission_id) values('Ситроен', 1, 2, 2);
insert into cars(name, body_id, engine_id, transmission_id) values('Мерседес', 4, 1, 2);
insert into cars(name, body_id, engine_id, transmission_id) values('Ниссан', 5, 3, 3);
insert into cars(name, body_id, engine_id, transmission_id) values('Форд', 3, 2, 1);
insert into cars(name, body_id, engine_id, transmission_id) values('Ауди', 1, 3, 3);
insert into cars(name, body_id, engine_id, transmission_id) values('Пежо', 6, null, 2);
insert into cars(name, body_id, engine_id, transmission_id) values('Тойота', 7, 1, 1);
insert into cars(name, body_id, engine_id, transmission_id) values('Рено', 6, 2, 3);
insert into cars(name, body_id, engine_id, transmission_id) values('Лада', 2, 1, 2);
insert into cars(name, body_id, engine_id, transmission_id) values('Шкода', 1, 1, 3);
insert into cars(name, body_id, engine_id, transmission_id) values('Вольво', 4, 2, null);
insert into cars(name, body_id, engine_id, transmission_id) values('БМВ', 1, 2, 1);
insert into cars(name, body_id, engine_id, transmission_id) values('Хонда', 2, 2, 2);

--1)
select c.id ID, c.name Марка, b.name Кузов, e.name "Тип двигателя", t.name Трансмиссия
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