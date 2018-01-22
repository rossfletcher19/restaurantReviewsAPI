SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS restaurants (
 idRestaurant2 int PRIMARY KEY auto_increment,
 name VARCHAR,
 address VARCHAR,
 zipcode VARCHAR,
 phone VARCHAR,
 website VARCHAR,
 email VARCHAR
);

CREATE TABLE IF NOT EXISTS foodtypes (
 idFoodtypes int PRIMARY KEY auto_increment,
 foodtype VARCHAR
);

CREATE TABLE IF NOT EXISTS reviews (
 idReview int PRIMARY KEY auto_increment,
 writtenBy VARCHAR,
 content VARCHAR,
 rating VARCHAR,
 restaurantId INTEGER
);