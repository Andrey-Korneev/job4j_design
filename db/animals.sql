create table fauna
(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values ('Ice fish', 4560, '1905-01-01');

insert into fauna(name, avg_age, discovery_date) values ('Blue-winged falcon', 7300, null);

insert into fauna(name, avg_age, discovery_date) values ('Two-spotted climbing snake', 3650, '1925-01-01');

insert into fauna(name, avg_age, discovery_date) values ('Philippine crocodile', 27375, '1935-01-01');

insert into fauna(name, avg_age, discovery_date) values ('Pygmy varanus', 18250, null);

insert into fauna(name, avg_age, discovery_date) values ('Green spiny shark', 9125, '1953-01-01');

insert into fauna(name, avg_age, discovery_date) values ('Chinese sheep', 4015, '1963-01-01');

insert into fauna(name, avg_age, discovery_date) values ('Northern sea bass', 15330, '1970-01-01');

insert into fauna(name, avg_age, discovery_date) values ('Silver rice hamster', 730, '1978-01-01');

insert into fauna(name, avg_age, discovery_date) values ('Wheeler boar fish', 16425, '1983-01-01');

insert into fauna(name, avg_age, discovery_date) values ('Red-tailed monkey', 10950, '1988-01-01');


select * from fauna where name like '%fish%';

select * from fauna where avg_age >= 10000 and avg_age <= 21000;

select * from fauna where avg_age between 10000 and 21000;

select * from fauna where discovery_date is null;

select * from fauna where discovery_date < '1950-01-01';







