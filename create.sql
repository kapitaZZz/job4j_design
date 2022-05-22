create table games_db (
	id serial primary key,
	game_name varchar(255),
	definition text,
	is_completed boolean
);

insert into games_db (game_name, definition, is_completed)
  values ('Max Payne', 'Action', true);

update games_db set game_name = 'Splinter Cell';

delete from games_db;