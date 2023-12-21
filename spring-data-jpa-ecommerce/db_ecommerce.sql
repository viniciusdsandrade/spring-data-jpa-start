DROP DATABASE IF EXISTS db_ecommerce;
CREATE DATABASE IF NOT EXISTS db_ecommerce;
USE db_ecommerce;

SELECT * FROM tb_product;

CREATE TABLE IF NOT EXISTS tb_product
(
	id                 BIGINT UNSIGNED AUTO_INCREMENT NOT NULL,
	stock_keeping_unit VARCHAR(255) UNIQUE            NOT NULL,
	name               VARCHAR(50)                    NULL,
	description        VARCHAR(100)                   NULL,
	price              DECIMAL                        NULL,
	active             BIT(1)                         NOT NULL,
	image_url          VARCHAR(1000)                  NULL,
	data_created       DATETIME                       NULL,
	last_updated       DATETIME                       NULL,

	PRIMARY KEY (id)
);

INSERT INTO tb_product (stock_keeping_unit, name, description, price, active, image_url, data_created, last_updated)
VALUES ('SKU001', 'Laptop Dell XPS 13', 'Powerful and compact laptop for productivity', 1299.99, 1, 'laptop_xps13.jpg', '2023-01-01 12:00:00', '2023-01-01 12:30:00');

INSERT INTO tb_product (stock_keeping_unit, name, description, price, active, image_url, data_created, last_updated)
VALUES ('SKU002', 'Smartphone iPhone 13', 'Latest iPhone with advanced features', 999.99, 1, 'iphone13.jpg', '2023-01-02 13:00:00', '2023-01-02 13:45:00');

INSERT INTO tb_product (stock_keeping_unit, name, description, price, active, image_url, data_created, last_updated)
VALUES ('SKU003', '4K Ultra HD Smart TV', 'Immersive entertainment experience at home', 899.99, 0, 'smart_tv_4k.jpg', '2023-01-03 14:30:00', '2023-01-03 15:15:00');

INSERT INTO tb_product (stock_keeping_unit, name, description, price, active, image_url, data_created, last_updated)
VALUES ('SKU004', 'Wireless Noise-Canceling Headphones', 'Premium headphones for an enhanced audio experience', 249.99, 1, 'headphones_wireless.jpg', '2023-01-04 16:00:00', '2023-01-04 16:45:00');

INSERT INTO tb_product (stock_keeping_unit, name, description, price, active, image_url, data_created, last_updated)
VALUES ('SKU005', 'Digital Camera Sony Alpha A7 III', 'Professional-grade mirrorless camera for photographers', 1999.99, 0, 'sony_alpha_a7iii.jpg', '2023-01-05 17:30:00', '2023-01-05 18:15:00');

INSERT INTO tb_product (stock_keeping_unit, name, description, price, active, image_url, data_created, last_updated)
VALUES ('SKU006', 'Fitness Tracker Fitbit Charge 5', 'Monitor your health and fitness with advanced tracking features', 149.99, 1, 'fitbit_charge5.jpg', '2023-01-06 19:00:00', '2023-01-06 19:45:00');

INSERT INTO tb_product (stock_keeping_unit, name, description, price, active, image_url, data_created, last_updated)
VALUES ('SKU007', 'Coffee Maker Nespresso VertuoPlus', 'Brew your favorite coffee with this stylish and efficient coffee maker', 179.99, 1, 'nespresso_vertuoplus.jpg', '2023-01-07 20:30:00', '2023-01-07 21:15:00');

INSERT INTO tb_product (stock_keeping_unit, name, description, price, active, image_url, data_created, last_updated)
VALUES ('SKU008', 'Gaming Console PlayStation 5', 'Experience next-gen gaming with the powerful PS5 console', 499.99, 0, 'playstation_5.jpg', '2023-01-08 22:00:00', '2023-01-08 22:45:00');

INSERT INTO tb_product (stock_keeping_unit, name, description, price, active, image_url, data_created, last_updated)
VALUES ('SKU009', 'Smart Thermostat Nest Learning Thermostat', 'Save energy with this intelligent and programmable thermostat', 199.99, 1, 'nest_learning_thermostat.jpg', '2023-01-09 23:30:00', '2023-01-09 23:59:59');

INSERT INTO tb_product (stock_keeping_unit, name, description, price, active, image_url, data_created, last_updated)
VALUES ('SKU010', 'Electric Toothbrush Philips Sonicare DiamondClean', 'Achieve cleaner and healthier teeth with advanced sonic technology', 129.99, 0, 'sonicare_diamondclean.jpg', '2023-01-10 12:00:00', '2023-01-10 12:30:00');
