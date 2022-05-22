create table roles(
    id serial primary key,
    title varchar(255)
);
create table rules(
    id serial primary key,
    description text
);
create table rules_role(
    id serial primary key,
    rule_id int references rules(id),
    role_id int references roles(id)
);
create table categories(
    id serial primary key,
    title varchar(255),
    description text
);
create table states(
    id serial primary key,
    title varchar(255)
);
create table users(
    id serial primary key,
    name varchar(255),
    role_id int references roles(id)
);
create table items(
    id serial primary key,
    title varchar(255),
    description text,
    user_id int references users(id),
    state_id int references states(id),
    category_id int references categories(id)
);
create table comments(
    id serial primary key,
    date_time timestamp default now(),
    description text,
    item_id int references items(id)
);
create table attachs(
    id serial primary key,
    file_name varchar(255),
    item_id int references items(id)
);