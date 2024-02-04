insert into roles(name) values('admin');

insert into rules(name, description) values('add all', 'can add any database entity');

insert into rules(name, description) values('delete all', 'can delete any database entity');

insert into roles_rules(role_id, rule_id) values(1, 1);

insert into roles_rules(role_id, rule_id) values(1, 2);

insert into users(name, age, role_id) values('Ivanov Ivan', 35, 1);

insert into categories(name) values('repair');

insert into states(name) values('open');

insert into items(ordinal_number, content, user_id, category_id, state_id) values(1, 'printer repair required', 1, 1, 1);

insert into comments(content, addition_date, item_id) values('this printer is very ancient', current_timestamp, 1);

insert into attachs(filename, item_id) values('ftp://ftp.server.ru/docs/printers/HP_LaserJet_3052.pdf', 1);



