set MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS hero(
id int PRIMARY KEY auto_increment,
age int,
name varchar,
power varchar,
move varchar,
weapon varchar,
weakness varchar,
squadid int
);

CREATE TABLE IF NOT EXISTS squad(
id int PRIMARY KEY auto_increment,
name varchar,
max_size int,
cause varchar,
);