-- DO NOT MODIFY THIS FILE

CREATE TABLE make(
  id CHAR(36) NOT NULL,
  name VARCHAR(100) NOT NULL
);

CREATE TABLE model(
  id CHAR(36) NOT NULL,
  name VARCHAR(100) NOT NULL
);

CREATE TABLE car(
  id CHAR(36) NOT NULL,
  color VARCHAR(50) NOT NULL,
  make_id CHAR(36) NOT NULL,
  model_id CHAR(36) NOT NULL,

  FOREIGN KEY (make_id) REFERENCES make(id),
  FOREIGN KEY (model_id) REFERENCES model(id)
);


-----------------
-- SAMPLE DATA --
-----------------

INSERT INTO make VALUES ('c1bbd5cd-0db5-4af4-b9ad-61da0a6acbf1', 'Ford');
INSERT INTO make VALUES ('71431b55-e504-41fd-918c-80aa42c86c54', 'Chevrolet');
INSERT INTO make VALUES ('7abb3017-6ea8-4f69-9a90-7eef1cb17c8a', 'Mercedes-Benz');
INSERT INTO make VALUES ('a73b80bb-cfbd-4f3f-91d4-8c43217a71b6', 'Audi');
INSERT INTO make VALUES ('a64c44da-412a-4611-a44e-5b203dcd026f', 'Honda');

-- Ford models --
INSERT INTO model VALUES ('85de22e2-6c93-445d-b68b-d694accfc8f4', 'F150');
INSERT INTO model VALUES ('82d957db-fa91-41f1-b354-f522c7e0257d', 'Mustang');

-- Chevrolet models --
INSERT INTO model VALUES ('d4a8f9d1-3180-4264-b75b-24ab769aa5c6', 'Cruze');
INSERT INTO model VALUES ('8da46615-1601-43ef-ab03-ae420a5f66e8', 'Malibu');

-- Audi models --
INSERT INTO model VALUES ('141ac7cf-300e-479c-87a5-0beafca6be93', 'A4');
INSERT INTO model VALUES ('cb917c13-9905-4fc1-9807-bb5c3d0c9857', 'A6');
INSERT INTO model VALUES ('72cb5fb3-726b-4db5-9e30-3fd50a3f6573', 'A8');

-- Honda models --
INSERT INTO model VALUES ('0f157c3f-3125-4594-8f9f-40c1b2f35827', 'Accord');
INSERT INTO model VALUES ('d7683a9a-7f6c-4715-bffb-129f4636facb', 'Pilot');

INSERT INTO car VALUES ('5ed6092b-6924-4d31-92d0-b77d4d777b46', 'RED', 'c1bbd5cd-0db5-4af4-b9ad-61da0a6acbf1', '85de22e2-6c93-445d-b68b-d694accfc8f4');
INSERT INTO car VALUES ('671ccc3f-3cda-4544-be66-a27545202a3c', 'WHITE', '71431b55-e504-41fd-918c-80aa42c86c54', 'd4a8f9d1-3180-4264-b75b-24ab769aa5c6');
INSERT INTO car VALUES ('bcc1da6d-393a-4232-870f-80a759ac4fe1', 'BLUE', '71431b55-e504-41fd-918c-80aa42c86c54', 'd7683a9a-7f6c-4715-bffb-129f4636facb');
INSERT INTO car VALUES ('7937df4d-05ab-416b-92af-015bd936d515', 'BLACK', 'a73b80bb-cfbd-4f3f-91d4-8c43217a71b6', '72cb5fb3-726b-4db5-9e30-3fd50a3f6573');
