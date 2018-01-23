SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS restaurants (
 idRestaurant INT PRIMARY KEY auto_increment,
 name VARCHAR,
 address VARCHAR,
 zipcode VARCHAR,
 phone VARCHAR,
 website VARCHAR,
 email VARCHAR
);

CREATE TABLE IF NOT EXISTS foodtypes (
 idFoodtypes INT PRIMARY KEY auto_increment,
 foodtype VARCHAR
);

CREATE TABLE IF NOT EXISTS reviews (
 idReview INT PRIMARY KEY auto_increment,
 writtenBy VARCHAR,
 content VARCHAR,
 rating VARCHAR,
 idRestaurant INTEGER
);