
DROP TABLE IF EXISTS country;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS restaurants;



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
   promo_photo VARCHAR NOT NULL,
   latitude FLOAT ,
   longitude FLOAT
);

