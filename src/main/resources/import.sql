-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

INSERT INTO user (name, email, image) VALUES
                                          ('John Doe', 'johndoe@gmail.com', 'placeholder.png'),
                                          ('Marko Markovic', 'markomarkovic@gmail.com', 'placeholder.png'),
                                          ('Jovan Jovanovic', 'jovanjovanovic@gmail.com', 'placeholder.png'),
                                          ('Petar Petrovic', 'petarpetrovic@gmail.com', 'placeholder.png');

INSERT INTO product (name, price) VALUES
                                      ('Simple table', 100),
                                      ('Table with drawer', 200),
                                      ('Table with drawer and shelf', 300),
                                      ('Table with drawer and shelf and wheels', 400),
                                      ('Chair', 50),
                                      ('Chair with armrest', 100),
                                      ('Chair with armrest and wheels', 150),
                                      ('Chair with armrest and wheels and headrest', 200);

INSERT INTO orders (date, user_id) VALUES
                                       ('2021-01-01', 1),
                                       ('2021-01-02', 2),
                                       ('2021-01-03', 3),
                                       ('2021-01-04', 4);

INSERT INTO order_product (order_id, product_id) VALUES
                                                     (1, 1),
                                                     (1, 5),
                                                     (2, 2),
                                                     (2, 6),
                                                     (3, 3),
                                                     (3, 7),
                                                     (4, 4),
                                                     (4, 8);

