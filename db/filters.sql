create table type(
    id serial primary key,
    name varchar(255)
);

create table product(
    id serial primary key,
    name varchar(255),
    expired_date date,
    price float,
    type_id int references type(id)
);

insert into type(name) values('СЫР');
insert into type(name) values('МОЛОКО');
insert into type(name) values('МОРОЖЕНОЕ');
insert into type(name) values('ЙОГУРТ');

insert into product(name, expired_date, price, type_id) values('Сыр плавленный', '2024-02-15', 80, 1);
insert into product(name, expired_date, price, type_id) values('Сыр моцарелла', '2024-02-13', 120, 1);
insert into product(name, expired_date, price, type_id) values('Сыр пармезан', '2024-02-17', 95, 1);
insert into product(name, expired_date, price, type_id) values('Сыр бри', '2024-02-22', 98, 1);
insert into product(name, expired_date, price, type_id) values('Сыр чеддер', '2024-02-05', 89, 1);
insert into product(name, expired_date, price, type_id) values('Сыр адыгейский', '2024-02-01', 100, 1);
insert into product(name, expired_date, price, type_id) values('Сыр гауда', '2024-02-18', 112, 1);
insert into product(name, expired_date, price, type_id) values('Сыр фета', '2024-02-20', 105, 1);
insert into product(name, expired_date, price, type_id) values('Сыр сливочный', '2024-02-27', 94, 1);
insert into product(name, expired_date, price, type_id) values('Сыр брынза', '2024-02-23', 90, 1);
insert into product(name, expired_date, price, type_id) values('Сыр рокфор', '2024-02-19', 125, 1);

insert into product(name, expired_date, price, type_id) values('Молоко обыкновенное', '2024-02-12', 50, 2);
insert into product(name, expired_date, price, type_id) values('Молоко топленое', '2024-02-15', 75, 2);
insert into product(name, expired_date, price, type_id) values('Молоко сгущенное', '2024-02-18', 90, 2);
insert into product(name, expired_date, price, type_id) values('Молоко сухое', '2024-02-05', 105, 2);
insert into product(name, expired_date, price, type_id) values('Молоко козье', '2024-02-11', 86, 2);
insert into product(name, expired_date, price, type_id) values('Молоко овечье', '2024-02-21', 95, 2);
insert into product(name, expired_date, price, type_id) values('Молоко кобылье', '2024-02-23', 110, 2);
insert into product(name, expired_date, price, type_id) values('Молоко оленье', '2024-02-04', 200, 2);
insert into product(name, expired_date, price, type_id) values('Молоко верблюжье', '2024-02-08', 180, 2);
insert into product(name, expired_date, price, type_id) values('Молоко кокосовое', '2024-03-24', 110, 2);
insert into product(name, expired_date, price, type_id) values('Молоко соевое', '2024-03-05', 122, 2);

insert into product(name, expired_date, price, type_id) values('Мороженое сливочное', '2024-02-11', 45, 3);
insert into product(name, expired_date, price, type_id) values('Мороженое пломбир', '2024-02-10', 58, 3);
insert into product(name, expired_date, price, type_id) values('Мороженое ванильное', '2024-02-15', 64, 3);
insert into product(name, expired_date, price, type_id) values('Мороженое шоколадное', '2024-02-24', 67, 3);
insert into product(name, expired_date, price, type_id) values('Мороженое ореховое', '2024-02-27', 80, 3);
insert into product(name, expired_date, price, type_id) values('Мороженое крем-брюле', '2024-03-15', 90, 3);
insert into product(name, expired_date, price, type_id) values('Мороженое шербет', '2024-02-28', 95, 3);
insert into product(name, expired_date, price, type_id) values('Мороженое фруктовый лед', '2024-02-01', 50, 3);
insert into product(name, expired_date, price, type_id) values('Мороженое эскимо', '2024-03-12', 70, 3);

insert into product(name, expired_date, price, type_id) values('Йогурт традиционный', '2024-02-08', 72, 4);
insert into product(name, expired_date, price, type_id) values('Йогурт греческий', '2024-02-18', 84, 4);
insert into product(name, expired_date, price, type_id) values('Йогурт с пробиотиками', '2024-02-25', 92, 4);
insert into product(name, expired_date, price, type_id) values('Йогурт замороженный', '2024-02-26', 78, 4);
insert into product(name, expired_date, price, type_id) values('Йогурт скайр', '2024-03-08', 200, 4);
insert into product(name, expired_date, price, type_id) values('Йогурт австралийский', '2024-02-23', 125, 4);
insert into product(name, expired_date, price, type_id) values('Йогурт швейцарский', '2024-02-02', 130, 4);
insert into product(name, expired_date, price, type_id) values('Йогурт фруктовый', '2024-03-16', 90, 4);

select p.name Имя, p.expired_date "Срок годности", p.price Цена, t.name Тип
from product p
join type t on t.id = p.type_id
where t.name = 'СЫР';


select p.name Имя, p.expired_date "Срок годности", p.price Цена
from product p
where lower(p.name) like '%мороженое%';


select p.name Имя, p.expired_date "Срок годности", p.price Цена
from product p
where p.expired_date < now();


select p.name Имя, p.expired_date "Срок годности", p.price Цена
from product p
where p.price = (select max(p.price) from product p);


select t.name "Имя типа", count(*) Количество from type t
join product p on p.type_id = t.id
group by t.name;


select p.name Имя, p.expired_date "Срок годности", p.price Цена, t.name Тип
from product p
join type t on t.id = p.type_id
where t.name = 'СЫР' or t.name = 'МОЛОКО';


select t.name "Имя типа", count(*) Количество from type t
join product p on p.type_id = t.id
group by t.name
having count(*) < 10;


select p.name Имя, p.expired_date "Срок годности", p.price Цена, t.name Тип
from product p
join type t on t.id = p.type_id;