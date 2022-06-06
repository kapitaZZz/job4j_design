create table body (
	id serial primary key,
	name varchar(255)
);

create table engine (
	id serial primary key,
	name varchar(255)
);

create table transmission (
	id serial primary key,
	name varchar(255)
);

create table car(
	id serial primary key,
	name varchar(255),
	body_id int references body(id),
	engine_id int references engine(id),
	transmission_id int references transmission(id)
);

insert into body(name) values ('Sport'), ('PickUp'), ('Heavy');
insert into engine(name) values ('V8'), ('V4'), ('V6');
insert into transmission(name) values ('Automatic'), ('Robotronic'), ('Manual');
insert into car(name, body_id, engine_id, transmission_id) values ('BMW', 1, 3, 1);
insert into car(name, body_id, engine_id, transmission_id) values ('Dodge', 1, 1, 2);
insert into car(name, body_id, engine_id, transmission_id) values ('Jeep', 3, 2, 2);

select car.name as car, body.name as body,
       engine.name as engine, transmission.name as transmission
from car
       left join body on car.body_id = body.id
       left join engine on car.engine_id = engine.id
       left join transmission on car.transmission = transmission.id;

select b.name from body b left join car c on c.body_id = b.id where c.body_id is null;
select e.name from engine e left join car c on c.engine_id = e.id where c.engine_id is null;
select t.name from transmission t left join car c on c.transmission_id = b.id where c.transmission_id is null;
