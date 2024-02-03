create table company(
    id serial primary key,
    name varchar(255),
    business_type varchar(255),
    address varchar(255)
);

create table position(
    id serial primary key,
    name varchar(255)
);

create table authority(
    id serial primary key,
    name varchar(255),
    description text
);

create table authority_position(
    id serial primary key,
    authority_id int references authority(id),
    position_id int references position(id)
);

create table employee(
    id serial primary key,
    job_number varchar(255),
    company_id int references company(id),
    position_id int references position(id)
);

create table personal_info(
    id serial primary key,
    name varchar(255),
    age int,
    address varchar(255),
    employee_id int references employee(id) unique
);