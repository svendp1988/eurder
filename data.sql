-- INSERT INTO eurder.addresses (street, street_number, postal_code, city) VALUES ('street', 'streetNumber', 'postalCode', 'city');
-- INSERT INTO eurder.users (first_name, last_name, email, user_role, password, address_id) VALUES ('admin', 'order', 'admin@order.com', 'ADMIN', 'root', 3);
-- INSERT INTO eurder.items (name, description, price, amount) VALUES ('beer', 'duvel', 34.5, 24);
-- INSERT INTO eurder.items (name, description, price, amount) VALUES ('chips', 'crocky pickles', 1.8, 100);
-- INSERT INTO eurder.items (name, description, price, amount) VALUES ('pizza', 'big american', 2.8, 20);
-- INSERT INTO eurder.items (name, description, price, amount) VALUES ('toilet paper', 'three layers', 0.72, 1000);
-- INSERT INTO eurder.items (name, description, price, amount) VALUES ('milk', 'semi-skimmed', 1.2, 50);
DROP TABLE eurder.users;
CREATE TABLE eurder.users
(
    user_id     bigint      not null GENERATED ALWAYS AS IDENTITY ,
    first_name  text        not null ,
    last_name   text        not null ,
    email       text        not null ,
    user_role   text        not null ,
    password    text        not null ,
    address_id  bigint      not null ,
    CONSTRAINT users_pk PRIMARY KEY (user_id) ,
    CONSTRAINT users_fk FOREIGN KEY (address_id) REFERENCES eurder.addresses(address_id)
);
INSERT INTO eurder.addresses (street, street_number, postal_code, city) VALUES ('Driehoeksstraat', '1', '3891', 'Buvingen');
INSERT INTO eurder.addresses (street, street_number, postal_code, city) VALUES ('Sint-Trudostraat', '7A', '3891', 'Buvingen');

INSERT INTO eurder.users (first_name, last_name, email, user_role, password, address_id) VALUES ('Admin', 'Istrator', 'admin@order.com', 'ADMIN', 'root', 4);
INSERT INTO eurder.users (first_name, last_name, email, user_role, password, address_id) VALUES ('Sven', 'De Potter', 'sven@order.com', 'CUSTOMER', 'customer', 5);

