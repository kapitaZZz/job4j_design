insert into roles(title) values ('role1');
insert into roles(title) values ('role2');

insert into rules(description) values ('first rule');
insert into rules(description) values ('second rule');

insert into rules_role(rule_id, role_id) values (1, 1);
insert into rules_role(rule_id, role_id) values (1, 2);
insert into rules_role(rule_id, role_id) values (2, 2);

insert into categories(title, description) values ('title1', 'first description');
insert into categories(title, description) values ('title2', 'second description');

insert into states(title) values ('accepted');
insert into states(title) values ('awaiting');

insert into users(name, role_id) values ('Sam', 2);
insert into users(name, role_id) values ('Steve', 1);

insert into items(title, description, user_id, state_id, category_id)
values ('First item', 'First description', 1, 2, 2);

insert into items(title, description, user_id, state_id, category_id)
values ('Second item', 'Second description', 2, 1, 1); 