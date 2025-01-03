create database MvcDemo;
use MvcDemo;
create table Account (
	id int identity(1,1),
	username varchar(255) unique,
	[password] varchar(255),
	[role] int,
	primary key(id)
);

create table Category (
	id int identity(1,1),
	[name] varchar(255) unique,
	primary key(id)
);

create table Product (
	id int identity(1,1),
	[name] varchar(255),
	price float,
	product_year int,
	[image] varchar(500),
	category_id int,
	primary key(id),
	foreign key (category_id) references Category(id)
);


INSERT INTO Category ([name]) VALUES
('Electronics'),
('Clothing'),
('Books'),
('Furniture'),
('Toys');

INSERT INTO Product ([name], price, product_year, [image], category_id) VALUES
('Smartphone', 699.99, 2023, 'https://img.freepik.com/free-vector/realistic-display-smartphone-with-different-apps_52683-30241.jpg', 1), -- Electronics
('Laptop', 1199.99, 2022, 'https://m.media-amazon.com/images/I/61Qe0euJJZL.jpg', 1), -- Electronics
('T-Shirt', 19.99, 2023, 'https://dictionary.cambridge.org/images/thumb/Tshirt_noun_001_18267.jpg?version=6.0.43', 2), -- Clothing
('Novel', 9.99, 2021, 'https://cdn0.fahasa.com/media/catalog/product/m/a/mat-biec_bia-mem_in-lan-thu-44.jpg', 3), -- Books
('Desk', 199.99, 2020, 'https://www.ikea.com/us/en/images/products/micke-desk-black-brown__0735981_pe740299_s5.jpg?f=s', 4), -- Furniture
('Sofa', 899.99, 2022, 'https://cozyliving.com.vn/cdn/shop/products/Cozy-Day23144copy_f27f18ca-fdc9-4686-bf68-a141ecc754af.jpg?v=1655780871', 4); -- Furniture

