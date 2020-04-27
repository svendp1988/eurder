-- CREATE SCHEMA eurder;
-- SET SCHEMA eurder;
-- DROP TABLE eurder.items cascade;
-- CREATE TABLE eurder.items
-- (
--     item_id       bigint  NOT NULL GENERATED ALWAYS AS IDENTITY,
--     name          text    NOT NULL,
--     description   text    NOT NULL,
--     price         numeric NOT NULL,
--     shipping_date date DEFAULT CURRENT_DATE,
--     amount integer NOT NULL,
--     CONSTRAINT item_pk PRIMARY KEY (item_id)
-- );

CREATE TABLE eurder.addresses
(
    address_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ,
    street      text  NOT NULL ,
    street_number text NOT NULL ,
    postal_code text    NOT NULL ,
    city    text    NOT NULL ,
    CONSTRAINT addresses_pk PRIMARY KEY (address_id)
);

CREATE TABLE eurder.users
(
    user_id     bigint  NOT NULL GENERATED ALWAYS AS IDENTITY,
    first_name  text    NOT NULL,
    last_name   text    NOT NULL ,
    email       text    NOT NULL ,
    user_role   text    NOT NULL ,
    password    text    NOT NULL ,
    address_id  bigint  NOT NULL ,
    CONSTRAINT users_pk PRIMARY KEY (user_id),
    CONSTRAINT users_fk FOREIGN KEY (address_id) REFERENCES eurder.addresses (address_id) ON DELETE CASCADE ON UPDATE CASCADE
);
