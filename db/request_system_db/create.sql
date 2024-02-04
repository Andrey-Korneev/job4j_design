create table roles(
    id serial primary key,
    name varchar(255)
);

create table rules(
    id serial primary key,
    name varchar(255),
    description text
);

create table roles_rules(
    id serial primary key,
    role_id int references roles(id),
    rule_id int references rules(id)
);

create table users(
    id serial primary key,
    name varchar(255),
    age int,
    role_id int references roles(id)
);

create table categories(
    id serial primary key,
    name varchar(255)
);

create table states(
    id serial primary key,
    name varchar(255)
);

create table items(
    id serial primary key,
    ordinal_number int,
    content varchar(255),
    user_id int references users(id),
    category_id int references categories(id),
    state_id int references states(id)
);

create table comments(
    id serial primary key,
    content varchar(255),
    addition_date timestamp
    item_id int references items(id)
);

create table attachs(
    id serial primary key,
    filename varchar(255),
    item_id int references items(id)
);