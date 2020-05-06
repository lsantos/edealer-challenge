-- ADD YOUR MIGRATION SCRIPT BELOW


CREATE TABLE make_model (
  make_id CHAR(36) NOT NULL,
  model_id CHAR(36) NOT NULL,
  
  FOREIGN KEY (make_id) REFERENCES make(id),
  FOREIGN KEY (model_id) REFERENCES model(id)
);

CREATE UNIQUE INDEX "uniq_model" ON make_model ( make_id, model_id );

-----------------
-- SAMPLE DATA --
-----------------

-- Ford models --

INSERT INTO make_model(make_id, model_id) VALUES ('c1bbd5cd-0db5-4af4-b9ad-61da0a6acbf1', '85de22e2-6c93-445d-b68b-d694accfc8f4');
INSERT INTO make_model(make_id, model_id) VALUES ('c1bbd5cd-0db5-4af4-b9ad-61da0a6acbf1', '82d957db-fa91-41f1-b354-f522c7e0257d');

-- Chevrolet models --

INSERT INTO make_model(make_id, model_id) VALUES ('71431b55-e504-41fd-918c-80aa42c86c54', 'd4a8f9d1-3180-4264-b75b-24ab769aa5c6');
INSERT INTO make_model(make_id, model_id) VALUES ('71431b55-e504-41fd-918c-80aa42c86c54', '8da46615-1601-43ef-ab03-ae420a5f66e8');

-- Audi model

INSERT INTO make_model(make_id, model_id) VALUES ('a73b80bb-cfbd-4f3f-91d4-8c43217a71b6', '141ac7cf-300e-479c-87a5-0beafca6be93');
INSERT INTO make_model(make_id, model_id) VALUES ('a73b80bb-cfbd-4f3f-91d4-8c43217a71b6', 'cb917c13-9905-4fc1-9807-bb5c3d0c9857');
INSERT INTO make_model(make_id, model_id) VALUES ('a73b80bb-cfbd-4f3f-91d4-8c43217a71b6', '72cb5fb3-726b-4db5-9e30-3fd50a3f6573');

-- Honda models

INSERT INTO make_model(make_id, model_id) VALUES ('a64c44da-412a-4611-a44e-5b203dcd026f', '0f157c3f-3125-4594-8f9f-40c1b2f35827');
INSERT INTO make_model(make_id, model_id) VALUES ('a64c44da-412a-4611-a44e-5b203dcd026f', 'd7683a9a-7f6c-4715-bffb-129f4636facb');

