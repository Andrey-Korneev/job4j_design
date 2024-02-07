create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values('телевизор', 10000);
insert into devices(name, price) values('ноутбук', 15000);
insert into devices(name, price) values('cd-плеер', 4000);
insert into devices(name, price) values('наушники', 450);
insert into devices(name, price) values('мобильный телефон', 7500);
insert into devices(name, price) values('персональный компьютер', 20000);

insert into people(name) values('Иван');
insert into people(name) values('Николай');
insert into people(name) values('Светлана');
insert into people(name) values('Татьяна');

insert into devices_people(people_id, device_id) values(1, 2);
insert into devices_people(people_id, device_id) values(1, 4);
insert into devices_people(people_id, device_id) values(1, 5);
insert into devices_people(people_id, device_id) values(2, 1);
insert into devices_people(people_id, device_id) values(2, 5);
insert into devices_people(people_id, device_id) values(3, 3);
insert into devices_people(people_id, device_id) values(3, 4);
insert into devices_people(people_id, device_id) values(4, 4);
insert into devices_people(people_id, device_id) values(4, 5);
insert into devices_people(people_id, device_id) values(4, 6);

select avg(price) as "Средняя цена устройств" from devices;

select p.name Имя, avg(d.price) as "Средняя цена устройств"
from devices_people dp
join people p on dp.people_id = p.id
join devices d on dp.device_id = d.id
group by p.name
order by p.name;

select p.name Имя, avg(d.price) as "Средняя цена устройств"
from devices_people dp
join people p on dp.people_id = p.id
join devices d on dp.device_id = d.id
group by p.name
having avg(d.price) > 5000
order by p.name;


