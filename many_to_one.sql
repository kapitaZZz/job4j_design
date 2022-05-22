create table worker (
	id serial primary key,
	name varchar(255),
	surname varchar(255)
);

create table special (
	id serial primary key,
	name varchar(255),
	worker_id int references worker(id)
);