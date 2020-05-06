-- ADD YOUR MIGRATION SCRIPT BELOW

CREATE TABLE customer (
  id CHAR(36) NOT NULL,
  first_name VARCHAR(50) NULL,
  last_name VARCHAR(50) NOT NULL
);

CREATE TABLE car_customer (
  car_id CHAR(36) NOT NULL,
  customer_id CHAR(36) NOT NULL,
  
  FOREIGN KEY (car_id) REFERENCES car(id),
  FOREIGN KEY (customer_id) REFERENCES customer(id)
);

-----------------
-- SAMPLE DATA --
-----------------

INSERT INTO customer VALUES ('10d809c9-92d3-478e-9478-5f19f12aaf88', 'Joseph', 'Brant');
INSERT INTO customer VALUES ('4139ef44-c1ea-43f1-b2e9-706c8167d8a3', 'Tabangi', 'Motors');
INSERT INTO customer VALUES ('f1766f09-77e9-4a05-afa4-ba9e92cb43e1', 'RedHill', 'Toyota');
INSERT INTO customer VALUES ('2ade7870-d8e4-4af3-9f5a-79e44dbbe975', 'John', 'Snow');

-- First customer cars

INSERT INTO car_customer(car_id, customer_id) VALUES ('5ed6092b-6924-4d31-92d0-b77d4d777b46', '10d809c9-92d3-478e-9478-5f19f12aaf88');
INSERT INTO car_customer(car_id, customer_id) VALUES ('671ccc3f-3cda-4544-be66-a27545202a3c', '10d809c9-92d3-478e-9478-5f19f12aaf88');
INSERT INTO car_customer(car_id, customer_id) VALUES ('bcc1da6d-393a-4232-870f-80a759ac4fe1', '10d809c9-92d3-478e-9478-5f19f12aaf88');

-- Second customer cars

INSERT INTO car_customer(car_id, customer_id) VALUES ('671ccc3f-3cda-4544-be66-a27545202a3c', '4139ef44-c1ea-43f1-b2e9-706c8167d8a3');
INSERT INTO car_customer(car_id, customer_id) VALUES ('7937df4d-05ab-416b-92af-015bd936d515', '4139ef44-c1ea-43f1-b2e9-706c8167d8a3');
INSERT INTO car_customer(car_id, customer_id) VALUES ('bcc1da6d-393a-4232-870f-80a759ac4fe1', '4139ef44-c1ea-43f1-b2e9-706c8167d8a3');

-- Third customer cars

INSERT INTO car_customer(car_id, customer_id) VALUES ('7937df4d-05ab-416b-92af-015bd936d515', 'f1766f09-77e9-4a05-afa4-ba9e92cb43e1');
