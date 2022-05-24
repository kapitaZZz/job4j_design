create table employees (
	id serial primary key,
	name varchar(255)
);

create table departments(
	id serial primary key,
	name varchar(255),
	employee_id int references employees(id)
);

insert into departments(name) values ('IT'), ('Marketing'), ('HR'), ('Sale');

insert into employees(name) values ('Sam'), ('Thobias'), ('Helen'), ('Steve'), ('Margo');

select * from employees e left join departments d on e.id = d.employee_id;
select * from employees e right join departments d on e.id = d.employee_id;
select * from employees e full join departments d on e.id = d.employee_id;
select * from employees e cross join departments d;

select e.name, d.name as departament from departments d right join employees e
on e.id = d.employee_id;

select e.name, d.name as departament from employees e left join departments d
on e.id = d.employee_id;

select d.name from departments d left join employees e on e.id = d.employee_id
where d.employee_id is null;

create table teens(
  name varchar(255),
  gender varchar(10)
);

insert into teens(name, gender) values ('Irina', 'female'), ('Mariya', 'female'), ('Petr', 'male');

select * from teens t1 cross join teens t2
where t1.gender != t2.gender;

select * from teens t1 cross join teens t2
where t1.gender != t2.gender;