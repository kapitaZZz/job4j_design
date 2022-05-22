create table phone_numbers (
	id serial primary key,
	number varchar(20)
);

create table users (
	id serial primary key,
	name varchar(255)
);

create table phones_ref (
	id serial primary key,
	phone_id int references phone_numbers(id) unique,
	user_id int references users(id) unique
);