select * from fauna;

select * from fauna where name LIKE '%fish%';

select * from fauna where avg_age between 10000 and 21000;

select * from fauna where discovery_date is null;

select * from fauna where extract(year from discovery_date) < 1950;