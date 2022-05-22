select c.name as Name, p.num as Phone from clients as c join phones as p on c.id = p.client_id;

select c.name, c.age, p.num from clients as c
join phones as p on c.id = p.client_id where gender = 'male';

select c.name as Name, p.num as Number from clients as c
join phones as p on c.id = p.client_id where age < 30;