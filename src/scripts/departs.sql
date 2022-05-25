create table departments(
    id serial primary key,
    name varchar(255)
);
create table employees(
    id serial primary key,
    name varchar(255),
    department_id int references departments(id)
);

insert into departments(name) values ('IT'), ('Marketing'), ('HR'), ('Sale');

insert into employees(name) values ('Sam'), ('Thobias'), ('Helen'), ('Steve'), ('Margo');

select * from departments d join employees e
on d.id = e.department_id;

select * from departments d left join employees e
on d.id = e.department_id;

select * from departments d right join employees e
on d.id = e.department_id;

select * from departments d full join employees e
on d.id = e.department_id;

select * from departments cross join employees;

select e.name, d.name
from departments d right join employees e
on d.id = e.department_id;

select e.name, d.name
from employees e left join departments d
on d.id = e.department_id;

select d.name from departments d left join employees e
on d.id = e.department_id
where e.department_id is null;

create table teens(
  name varchar(255),
  gender varchar(10)
);

insert into teens(name, gender) values ('Irina', 'female'), ('Mariya', 'female'), ('Petr', 'male');

select * from teens t1 cross join teens t2
where t1.gender != t2.gender;

select * from teens t1 cross join teens t2
where t1.gender != t2.gender;