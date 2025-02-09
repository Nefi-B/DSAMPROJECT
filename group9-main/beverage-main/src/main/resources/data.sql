-- Create Users
CREATE TABLE IF NOT EXISTS WEB_USER
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(255),
    phone_number VARCHAR(255),
    birthdate DATE,
    role VARCHAR(255)
    );

INSERT INTO web_user (id, username, password, full_name, phone_number, role)
VALUES (101, 'admin', '$2a$10$lg5MhJ4lFaTKXIZ9vjgw6escuwQE9E1XMKH5adeE/cXN3Tx./MzpC','adminName', '0175', 'ROLE_ADMIN'),
       (102, 'user', '$2a$10$ZtnId8WGT2dUSeX5wAjXWu.1/HmI1rwyoYa1USPKybyN6Ojq/.CF6','muller', '01751', 'ROLE_USER');
--
CREATE TABLE IF NOT EXISTS ADDRESS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    city VARCHAR(255) NOT NULL,
    street VARCHAR(255) NOT NULL,
    number VARCHAR(50),
    postal_code VARCHAR(20),
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES WEB_USER(id)
    );

-- Create Addresses
INSERT INTO address (id, city, street, number, postal_code, user_id)
VALUES (101, 'CityA', 'Street1', '101', '11111', '101'),
       (102, 'CityB', 'Street2', '102', '22222', '102'),
       (103, 'CityC', 'Street3', '103', '33333', '102'),
       (104, 'CityD', 'Street4', '104', '44444', '102');

CREATE TABLE IF NOT EXISTS SHOP_ORDER (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    price DOUBLE NOT NULL,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES WEB_USER(id)
    );

-- Create Orders
INSERT INTO shop_order (id, price, user_id)
VALUES (101, 100.0, 101),
       (102, 200.0, 101);

CREATE TABLE IF NOT EXISTS ORDER_ITEM (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    position VARCHAR(255),
    price DOUBLE NOT NULL,
    order_id BIGINT,
    FOREIGN KEY (order_id) REFERENCES SHOP_ORDER (id)
    );

-- Create OrderItems and assign them to Orders
INSERT INTO order_item (id, position, price, order_id)
VALUES (101, 'Item 1', 10.0, 101),
       (102, 'Item 2', 20.0, 101),
       (103, 'Item 3', 15.0, 102),
       (104, 'Item 4', 25.0, 102);

CREATE TABLE IF NOT EXISTS CRATE (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    crate_pic VARCHAR(255),
    no_of_bottles INT NOT NULL,
    price DOUBLE NOT NULL,
    crates_in_stock INT NOT NULL
    );

-- Crate Table
INSERT INTO crate (id, name, crate_pic, no_of_bottles, price, crates_in_stock)
VALUES (101, 'Beer Crate', 'beer_crate.jpg', 24, 75.0, 20),
       (102, 'Water Crate', 'water_crate.jpg', 12, 12.0, 50),
       (103, 'Wine Crate', 'wine_crate.jpg', 6, 90.0, 10),
       (104, 'Soda Crate', 'soda_crate.jpg', 12, 18.0, 30),
       (105, 'Juice Crate', 'juice_crate.jpg', 6, 15.0, 25),
       (106, 'Milk Crate', 'milk_crate.jpg', 12, 14.4, 40),
       (107, 'Whiskey Crate', 'whiskey_crate.jpg', 6, 240.0, 5),
       (108, 'Cider Crate', 'cider_crate.jpg', 12, 35.0, 15),
       (109, 'Champagne Crate', 'champagne_crate.jpg', 6, 300.0, 2),
       (110,'Tequila Crate', 'tequila_crate.jpg', 6, 150.0, 8);

CREATE TABLE IF NOT EXISTS BOTTLE (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      name VARCHAR(255) NOT NULL,
    bottle_pic VARCHAR(255),
    volume DOUBLE NOT NULL,
    is_alcoholic BOOLEAN,
    volume_percent DOUBLE,
    price DOUBLE NOT NULL,
    supplier VARCHAR(255),
    in_stock INT NOT NULL,
    crate_id BIGINT,
    FOREIGN KEY (crate_id) REFERENCES CRATE(id)
    );

-- Bottle Table
INSERT INTO bottle (id, name, bottle_pic, volume, is_alcoholic, volume_percent, price, supplier, in_stock, crate_id)
VALUES (101, 'Beer', 'beer.jpg', 0.5, true, 5.0, 3.5, 'Local Brewery', 50, 101),
       (102, 'Water', 'water.jpg', 1.0, false, 0.0, 1.0, 'Spring Co.', 200, 102),
       (103, 'Wine', 'wine.jpg', 0.75, true, 12.5, 15.0, 'Vineyard', 30, 103),
       (104, 'Whiskey', 'whiskey.jpg', 0.7, true, 40.0, 40.0, 'Distillery', 15, 107),
       (105, 'Soda', 'soda.jpg', 0.33, false, 0.0, 1.5, 'Soda Co.', 100, 104),
       (106, 'Juice', 'juice.jpg', 1.0, false, 0.0, 3.0, 'Orchard Co.', 75, 105),
       (107, 'Champagne', 'champagne.jpg', 0.75, true, 11.5, 50.0, 'Winery', 10, 109),
       (108, 'Tequila', 'tequila.jpg', 0.7, true, 38.0, 25.0, 'Distillery', 20, 110),
       (109, 'Cider', 'cider.jpg', 0.5, true, 4.5, 3.0, 'Cider Co.', 60, 108),
       (110, 'Milk', 'milk.jpg', 1.0, false, 0.0, 1.2, 'Dairy Co.', 120, 106);
