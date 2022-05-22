create table clients(
    id serial primary key,
    name varchar(255),
    gender varchar (255),
    age smallint
);

create table phones(
    id serial primary key,
    num bigint,
    client_id int references clients(id)
);

insert into clients(name, gender, age) values ('Helen', 'female', 27);
insert into clients(name, gender, age) values ('Sam', 'male', 35);
insert into clients(name, gender, age) values ('Martha', 'female', 45);

insert into phones(num, client_id) values (81234567890, 1);
insert into phones(num, client_id) values (80987654321, 1);
insert into phones(num, client_id) values (81233214566, 2);
insert into phones(num, client_id) values (85555555555, 3);


select c.name as Name, p.num as Phone from clients as c join phones as p on c.id = p.client_id;

select c.name, c.age, p.num from clients as c
join phones as p on c.id = p.client_id where gender = 'male';

select c.name as Name, p.num as Number from clients as c
join phones as p on c.id = p.client_id where age < 30;