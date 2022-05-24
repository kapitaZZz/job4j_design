create table type (
	id serial primary key,
	name varchar(100)
);

create table product (
	id serial primary key,
	name varchar(50),
	type_id int references type(id),
	expired_date text,
	price text
);

insert into type(name) values('Сыр'), ('Молоко'), ('Мороженое');

insert into product(name, type_id, expired_date, price)
values ('Hohland', 1, '10-12-2023', 150);
insert into product(name, type_id, expired_date, price)
values ('President', 1, '04-06-2024', 90);
insert into product(name, type_id, expired_date, price)
values ('Parmalat', 2, '01-01-2024', 120);
insert into product(name, type_id, expired_date, price)
values ('Magnat', 3, '18-07-2022', 180);
insert into product(name, type_id, expired_date, price)
values ('Cocunt', 2, '05-09-2022', 250);
insert into product(name, type_id, expired_date, price)
values ('Venice', 3, '21-09-2023', 400);
insert into product(name, type_id, expired_date, price)
values ('5 Копеек', 3, '07-10-2023', 390);

select p.name from product p join type t on p.type_id = t.id
where t.name = 'Сыр';

select p.name from product p join type t on p.type_id = t.id
where p.name like '%мороженое%';

select p.name from product p join type t on p.type_id = t.id
where p.expired_date < current_date;

select p.name from product p join type t on p.type_id = t.id
where p.price = (select max(price) from product);

select t.name, count(*) from product p join
type t on p.type_id = t.id group by t.name;

select * from product p join type t on p.type_id = t.id
where t.name in('Сыр', 'Молоко');

select t.name, count(*) from product p join type t
on p.type_id = t.id
group by t.name
having count(*) < 10;

select p.name, t.name from product p join type t on p.type_id = t.id;