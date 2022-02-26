set MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS hero(
id int PRIMARY KEY auto_increment,
age int,
name varchar,
special_power varchar,
special_move varchar,
preferred_weapon varchar,
weakness varchar,
squad_id int,
);

CREATE TABLE IF NOT EXISTS squad(
id int PRIMARY KEY auto_increment,
name varchar,
max_size int,
cause varchar,
);