SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS restaurants (
 restaurantId int PRIMARY KEY auto_increment,
 name VARCHAR,
 address VARCHAR,
 zipcode VARCHAR,
 phone VARCHAR,
 website VARCHAR,
 email VARCHAR
);

CREATE TABLE IF NOT EXISTS foodtypes (
 foodtypeId int PRIMARY KEY auto_increment,
 foodtype VARCHAR
);

CREATE TABLE IF NOT EXISTS reviews (
 idReview int PRIMARY KEY auto_increment,
 writtenBy VARCHAR,
 rating INTEGER,
 content VARCHAR,
 restaurantId INTEGER
);