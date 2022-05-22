create table cars (
	id serial primary key,
	car_namber varchar(10)
);

create table worker (
	id serial primary key,
	name varchar(255)
);

create table worker_cars (
	id serial primary key,
	car_id int references cars(id),
	worker_id int references worker(id)
);