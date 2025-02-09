-- Create Users Table
CREATE TABLE IF NOT EXISTS WEB_USER (
                                        id BIGSERIAL PRIMARY KEY,
                                        username VARCHAR(255) UNIQUE NOT NULL,
                                        password VARCHAR(255) NOT NULL,
                                        full_name VARCHAR(255),
                                        phone_number VARCHAR(255),
                                        birthdate DATE,
                                        role VARCHAR(255) NOT NULL
);

-- Insert Users (Avoid Duplicate Entries)
INSERT INTO WEB_USER (id, username, password, full_name, phone_number, role)
VALUES
    (101, 'admin', '$2a$10$lg5MhJ4lFaTKXIZ9vjgw6escuwQE9E1XMKH5adeE/cXN3Tx./MzpC', 'adminName', '0175', 'ROLE_ADMIN'),
    (102, 'user', '$2a$10$ZtnId8WGT2dUSeX5wAjXWu.1/HmI1rwyoYa1USPKybyN6Ojq/.CF6', 'muller', '01751', 'ROLE_USER')
ON CONFLICT (id) DO NOTHING;

-- Create Address Table
CREATE TABLE IF NOT EXISTS ADDRESS (
                                       id BIGSERIAL PRIMARY KEY,
                                       city VARCHAR(255) NOT NULL,
                                       street VARCHAR(255) NOT NULL,
                                       number VARCHAR(50),
                                       postal_code VARCHAR(20),
                                       user_id BIGINT,
                                       FOREIGN KEY (user_id) REFERENCES WEB_USER(id) ON DELETE CASCADE
);

-- Insert Addresses
INSERT INTO ADDRESS (id, city, street, number, postal_code, user_id)
VALUES
    (101, 'CityA', 'Street1', '101', '11111', 101),
    (102, 'CityB', 'Street2', '102', '22222', 102),
    (103, 'CityC', 'Street3', '103', '33333', 102),
    (104, 'CityD', 'Street4', '104', '44444', 102);

-- Create Orders Table
CREATE TABLE IF NOT EXISTS SHOP_ORDER (
                                          id BIGSERIAL PRIMARY KEY,
                                          price DECIMAL(10,2) NOT NULL,
                                          user_id BIGINT,
                                          FOREIGN KEY (user_id) REFERENCES WEB_USER(id) ON DELETE CASCADE
);

-- Insert Orders
INSERT INTO SHOP_ORDER (id, price, user_id)
VALUES
    (101, 100.00, 101),
    (102, 200.00, 101);

-- Create Order Items Table
CREATE TABLE IF NOT EXISTS ORDER_ITEM (
                                          id BIGSERIAL PRIMARY KEY,
                                          position VARCHAR(255),
                                          price DECIMAL(10,2) NOT NULL,
                                          order_id BIGINT,
                                          FOREIGN KEY (order_id) REFERENCES SHOP_ORDER (id) ON DELETE CASCADE
);

-- Insert Order Items
INSERT INTO ORDER_ITEM (id, position, price, order_id)
VALUES
    (101, 'Item 1', 10.00, 101),
    (102, 'Item 2', 20.00, 101),
    (103, 'Item 3', 15.00, 102),
    (104, 'Item 4', 25.00, 102);

-- Create Crate Table
CREATE TABLE IF NOT EXISTS CRATE (
                                     id BIGSERIAL PRIMARY KEY,
                                     name VARCHAR(255) NOT NULL,
                                     crate_pic VARCHAR(255),
                                     no_of_bottles INT NOT NULL,
                                     price DECIMAL(10,2) NOT NULL,
                                     crates_in_stock INT NOT NULL
);

-- Insert Crates
INSERT INTO CRATE (id, name, crate_pic, no_of_bottles, price, crates_in_stock)
VALUES
    (101, 'Beer Crate', 'beer_crate.jpg', 24, 75.00, 20),
    (102, 'Water Crate', 'water_crate.jpg', 12, 12.00, 50),
    (103, 'Wine Crate', 'wine_crate.jpg', 6, 90.00, 10),
    (104, 'Soda Crate', 'soda_crate.jpg', 12, 18.00, 30),
    (105, 'Juice Crate', 'juice_crate.jpg', 6, 15.00, 25),
    (106, 'Milk Crate', 'milk_crate.jpg', 12, 14.40, 40),
    (107, 'Whiskey Crate', 'whiskey_crate.jpg', 6, 240.00, 5),
    (108, 'Cider Crate', 'cider_crate.jpg', 12, 35.00, 15),
    (109, 'Champagne Crate', 'champagne_crate.jpg', 6, 300.00, 2),
    (110, 'Tequila Crate', 'tequila_crate.jpg', 6, 150.00, 8);

-- Create Bottle Table
CREATE TABLE IF NOT EXISTS BOTTLE (
                                      id BIGSERIAL PRIMARY KEY,
                                      name VARCHAR(255) NOT NULL,
                                      bottle_pic VARCHAR(255),
                                      volume DECIMAL(5,2) NOT NULL,
                                      is_alcoholic BOOLEAN,
                                      volume_percent DOUBLE PRECISION,
                                      price DECIMAL(10,2) NOT NULL,
                                      supplier VARCHAR(255),
                                      in_stock INT NOT NULL,
                                      crate_id BIGINT,
                                      FOREIGN KEY (crate_id) REFERENCES CRATE(id) ON DELETE SET NULL
);

-- Insert Bottles
INSERT INTO BOTTLE (id, name, bottle_pic, volume, is_alcoholic, volume_percent, price, supplier, in_stock, crate_id)
VALUES
    (101, 'Beer', 'beer.jpg', 0.50, TRUE, 5.0, 3.50, 'Local Brewery', 50, 101),
    (102, 'Water', 'water.jpg', 1.00, FALSE, 0.0, 1.00, 'Spring Co.', 200, 102),
    (103, 'Wine', 'wine.jpg', 0.75, TRUE, 12.5, 15.00, 'Vineyard', 30, 103),
    (104, 'Whiskey', 'whiskey.jpg', 0.70, TRUE, 40.0, 40.00, 'Distillery', 15, 107),
    (105, 'Soda', 'soda.jpg', 0.33, FALSE, 0.0, 1.50, 'Soda Co.', 100, 104),
    (106, 'Juice', 'juice.jpg', 1.00, FALSE, 0.0, 3.00, 'Orchard Co.', 75, 105),
    (107, 'Champagne', 'champagne.jpg', 0.75, TRUE, 11.5, 50.00, 'Winery', 10, 109),
    (108, 'Tequila', 'tequila.jpg', 0.70, TRUE, 38.0, 25.00, 'Distillery', 20, 110),
    (109, 'Cider', 'cider.jpg', 0.50, TRUE, 4.5, 3.00, 'Cider Co.', 60, 108),
    (110, 'Milk', 'milk.jpg', 1.00, FALSE, 0.0, 1.20, 'Dairy Co.', 120, 106);
