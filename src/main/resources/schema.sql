
DROP TABLE IF EXISTS country;
DROP TABLE IF EXISTS reviews;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS restaurants CASCADE;
DROP TABLE IF EXISTS reservation;
DROP TABLE IF EXISTS tables;
DROP TABLE IF EXISTS meals;

DROP TABLE IF EXISTS category;







CREATE TABLE IF NOT EXISTS country (
   id SERIAL PRIMARY KEY,
   name VARCHAR NOT NULL
);


CREATE TABLE IF NOT EXISTS users (
   id SERIAL PRIMARY KEY,
   first_name VARCHAR NOT NULL,
   last_name VARCHAR NOT NULL,
   email VARCHAR NOT NULL,
   country VARCHAR NOT NULL,
   city VARCHAR NOT NULL,
   account_role VARCHAR NOT NULL,
   password VARCHAR NOT NULL,
   phone VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS restaurants (
   id SERIAL PRIMARY KEY,
   city_id VARCHAR NOT NULL,
   name VARCHAR NOT NULL,
   street VARCHAR NOT NULL,
   description VARCHAR ,
   photos VARCHAR ,
   mark FLOAT,
   votes FLOAT,
   price_range FLOAT,
   food_types VARCHAR,
   promo_photo VARCHAR NOT NULL,
   cover_photo VARCHAR,
   latitude FLOAT ,
   longitude FLOAT
);



CREATE TABLE IF NOT EXISTS tables (
 id SERIAL PRIMARY KEY,
 restaurant_id integer  references restaurants(id),
 seats INT NOT NULL





);

CREATE TABLE IF NOT EXISTS reservation (
 id SERIAL PRIMARY KEY,
 restaurant_id integer  references restaurants(id),
 table_id integer ,
 time TIME NOT NULL,
 date DATE NOT NULL,
 guest_number INT NOT NULL
);

CREATE TABLE IF NOT EXISTS reviews (
 id SERIAL PRIMARY KEY,
 restaurant_id integer  references restaurants(id),
 user_id integer  not null references users(id),
 mark FLOAT,
 comment VARCHAR

);

CREATE TABLE IF NOT EXISTS meals (
 id SERIAL PRIMARY KEY,
 restaurant_id integer  references restaurants(id),
 name VARCHAR,
 description VARCHAR,
 price FLOAT,
 meal_type VARCHAR,
 meal_category VARCHAR

);

CREATE TABLE IF NOT EXISTS category (
 id SERIAL PRIMARY KEY,
 name VARCHAR





);



