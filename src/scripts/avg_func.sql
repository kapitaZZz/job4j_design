insert into people (name) values ('Sam'), ('Steve'), ('Tobias');
insert into devices (name, price) values ('PC', 15000), ('Eyephone', 50000), ('Headphones', 3500);
insert into devices_people (device_id, people_id) values(1, 3), (2, 3), (3, 1), (2, 2);


select avg(price) from devices;

select p.name, avg(d.price) from people as p
join devices_people as dp on p.id = dp.people_id
join devices as d on d.id = dp.device_id
group by p.name;

select p.name, avg(d.price) from people as p
join devices_people as dp on p.id = dp.people_id
join devices as d on d.id = dp.device_id
group by p.name having avg(d.price) > 5000;