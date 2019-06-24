

INSERT INTO country (name) VALUES ('India');
INSERT INTO country (name) VALUES ('Brazil');
INSERT INTO country (name) VALUES ('USA');
INSERT INTO country (name) VALUES ('Italy');


INSERT INTO users (first_name,last_name,email,country,city,account_role,password,phone)
 VALUES ('Amir','Bunjo','bunjo16@gmail.com','BiH','Sarajevo','USER','YnVuam8xMjM=','38762360120');


INSERT INTO restaurants (city_id,name,street,promo_photo) VALUES (1,'Vatra','Ferhadija','/assets/img/restaurant-promo/vatra.jpg' );
INSERT INTO restaurants (city_id,name,street,promo_photo) VALUES (1,'Chipas','Strosmajerova','/assets/img/restaurant-promo/chipas.jpg' );
INSERT INTO restaurants (city_id,name,street,promo_photo) VALUES (1,'Four Seasons','Marijin Dvor 1','/assets/img/restaurant-promo/four-seasons.jpg' );
INSERT INTO restaurants (city_id,name,street,promo_photo) VALUES (1,'Vapiano','Marijin Dvor 2','/assets/img/restaurant-promo/vapiano.jpeg' );
INSERT INTO restaurants (city_id,name,street,promo_photo) VALUES (1,'Zmaj','Autobuska','/assets/img/restaurant-promo/zmaj.jpg' );
INSERT INTO restaurants (city_id,name,street,promo_photo) VALUES (2,'Pacific','Josipa Bana Jelacica','/assets/img/restaurant-promo/pacific.jpg' );
INSERT INTO restaurants (city_id,name,street,promo_photo) VALUES (1,'Radon Plaza','Stup 2','/assets/img/restaurant-promo/radon.jpg' );
INSERT INTO restaurants (city_id,name,street,promo_photo) VALUES (1,'Brajlovic','Samira Ćatovića Kobre 6','/assets/img/restaurant-promo/brajlovic.jpg' );
INSERT INTO restaurants (city_id,name,street,promo_photo) VALUES (3,'Nanina kuhinja','Kundurdžiluk 35','/assets/img/restaurant-promo/nanina.jpg' );
INSERT INTO restaurants (city_id,name,street,promo_photo) VALUES (3,'Vrelo Bosne','Magistrala 66, Ilidza','/assets/img/restaurant-promo/vrelo-bosne.jpg' );
INSERT INTO restaurants (city_id,name,street,promo_photo) VALUES (3,'Konyali Ahmet Usta','Kundurdžiluk','/assets/img/restaurant-promo/konyali.jpeg' );
INSERT INTO restaurants (city_id,name,street,promo_photo) VALUES (3,'Ćevabdžinica Mrkva','Bravadžiluk 13','/assets/img/restaurant-promo/mrkva.jpg' );
INSERT INTO restaurants (city_id,name,street,promo_photo) VALUES (3,'Fabrika dobre hrane','Milana Preloga','/assets/img/restaurant-promo/fabrika.jpeg' );

INSERT INTO restaurants (city_id,name,street,promo_photo) VALUES (1,'Vatra','Ferhadija','/assets/img/restaurant-promo/vatra.jpg' );
INSERT INTO restaurants (city_id,name,street,promo_photo) VALUES (1,'Chipas','Strosmajerova','/assets/img/restaurant-promo/chipas.jpg' );
INSERT INTO restaurants (city_id,name,street,promo_photo) VALUES (1,'Four Seasons','Marijin Dvor 1','/assets/img/restaurant-promo/four-seasons.jpg' );
INSERT INTO restaurants (city_id,name,street,promo_photo) VALUES (1,'Vapiano','Marijin Dvor 2','/assets/img/restaurant-promo/vapiano.jpeg' );
INSERT INTO restaurants (city_id,name,street,promo_photo) VALUES (1,'Zmaj','Autobuska','/assets/img/restaurant-promo/zmaj.jpg' );
INSERT INTO restaurants (city_id,name,street,promo_photo) VALUES (2,'Pacific','Josipa Bana Jelacica','/assets/img/restaurant-promo/pacific.jpg' );
INSERT INTO restaurants (city_id,name,street,promo_photo) VALUES (1,'Radon Plaza','Stup 2','/assets/img/restaurant-promo/radon.jpg' );
INSERT INTO restaurants (city_id,name,street,promo_photo) VALUES (1,'Brajlovic','Samira Ćatovića Kobre 6','/assets/img/restaurant-promo/brajlovic.jpg' );
INSERT INTO restaurants (city_id,name,street,promo_photo) VALUES (3,'Nanina kuhinja','Kundurdžiluk 35','/assets/img/restaurant-promo/nanina.jpg' );
INSERT INTO restaurants (city_id,name,street,promo_photo) VALUES (3,'Vrelo Bosne','Magistrala 66, Ilidza','/assets/img/restaurant-promo/vrelo-bosne.jpg' );
INSERT INTO restaurants (city_id,name,street,promo_photo) VALUES (3,'Konyali Ahmet Usta','Kundurdžiluk','/assets/img/restaurant-promo/konyali.jpeg' );
INSERT INTO restaurants (city_id,name,street,promo_photo) VALUES (3,'Ćevabdžinica Mrkva','Bravadžiluk 13','/assets/img/restaurant-promo/mrkva.jpg' );
INSERT INTO restaurants (city_id,name,street,promo_photo) VALUES (3,'Fabrika dobre hrane','Milana Preloga','/assets/img/restaurant-promo/fabrika.jpeg' );




INSERT INTO tables (restaurant_id,seats,available) VALUES (1,2,TRUE);
INSERT INTO tables (restaurant_id,seats,available) VALUES (1,2,TRUE);
INSERT INTO tables (restaurant_id,seats,available) VALUES (1,3,TRUE);
INSERT INTO tables (restaurant_id,seats,available) VALUES (1,3,TRUE);
INSERT INTO tables (restaurant_id,seats,available) VALUES (1,4,TRUE);
INSERT INTO tables (restaurant_id,seats,available) VALUES (1,4,TRUE);
INSERT INTO tables (restaurant_id,seats,available) VALUES (1,4,TRUE);
INSERT INTO tables (restaurant_id,seats,available) VALUES (1,4,TRUE);

INSERT INTO tables (restaurant_id,seats,available) VALUES (2,4,TRUE);
INSERT INTO tables (restaurant_id,seats,available) VALUES (2,4,TRUE);
INSERT INTO tables (restaurant_id,seats,available) VALUES (2,4,FALSE);
INSERT INTO tables (restaurant_id,seats,available) VALUES (3,4,FALSE);


INSERT INTO reservation (restaurant_id,table_id,time,date,guest_number) VALUES (1,3,'13:30',TO_DATE('22/06/2019', 'DD/MM/YYYY'),3);

INSERT INTO reservation (restaurant_id,table_id,time,date,guest_number) VALUES (1,2,'15:30',TO_DATE('22/06/2019', 'DD/MM/YYYY'),2);

INSERT INTO reservation (restaurant_id,table_id,time,date,guest_number) VALUES (1,4,'12:30',TO_DATE('22/06/2019', 'DD/MM/YYYY'),3);

INSERT INTO reservation (restaurant_id,table_id,time,date,guest_number) VALUES (1,4,'16:00',TO_DATE('21/06/2019', 'DD/MM/YYYY'),4);

INSERT INTO reservation (restaurant_id,table_id,time,date,guest_number) VALUES (1,4,'15:00',TO_DATE('22/06/2019', 'DD/MM/YYYY'),4);


INSERT INTO reservation (restaurant_id,table_id,time,date,guest_number) VALUES (1,2,'13:15',TO_DATE('22/06/2019', 'DD/MM/YYYY'),2);

INSERT INTO reservation (restaurant_id,table_id,time,date,guest_number) VALUES (1,3,'15:30',TO_DATE('22/06/2019', 'DD/MM/YYYY'),3);

INSERT INTO reservation (restaurant_id,table_id,time,date,guest_number) VALUES (1,1,'13:30',TO_DATE('22/06/2019', 'DD/MM/YYYY'),2);

INSERT INTO reservation (restaurant_id,table_id,time,date,guest_number) VALUES (1,4,'14:30',TO_DATE('21/06/2019', 'DD/MM/YYYY'),2);

INSERT INTO reservation (restaurant_id,table_id,time,date,guest_number) VALUES (1,4,'17:30',TO_DATE('21/06/2019', 'DD/MM/YYYY'),4);

INSERT INTO reservation (restaurant_id,table_id,time,date,guest_number) VALUES (1,5,'14:30',TO_DATE('22/06/2019', 'DD/MM/YYYY'),4);

