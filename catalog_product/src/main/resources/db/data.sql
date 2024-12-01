CREATE DATABASE catalog_product;




CREATE TABLE catalog_product.product (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(250) NOT NULL,
                         description VARCHAR(250) NULL,
                         price DECIMAL(10, 2) NOT NULL,
                         category_id BIGINT NOT NULL,
                         active BOOLEAN DEFAULT TRUE,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         FOREIGN KEY (category_id) REFERENCES category(id)
);



CREATE TABLE catalog_product.category (
                                 id serial4 NOT NULL,
                                 "name" varchar(250) NOT NULL,
                                 description varchar(250) NULL,
                                 CONSTRAINT category_pkey PRIMARY KEY (id)
);





INSERT INTO catalog_product.product (name, description, price, category_id, active)
VALUES
    ('Smartphone', 'Latest model smartphone', 599.99, 1, TRUE),
    ('T-shirt', 'Cotton T-shirt, size M', 19.99, 2, TRUE);



INSERT INTO catalog_product.category (name, description)
VALUES
    ('Electronics', 'Various electronic devices such as smartphones, laptops, and TVs.'),
    ('Furniture', 'Home and office furniture including chairs, tables, and sofas.'),
    ('Clothing', 'Fashionable apparel for men, women, and children, including casual and formal wear.'),
    ('Books', 'Fiction, non-fiction, academic, and reference books.'),
    ('Groceries', 'Fresh produce, dairy, packaged food, and household essentials.');



INSERT INTO catalog_product.product (id, name, description, price, category_id, is_available, created_at, updated_at, image_url)
VALUES
    (1, 'Smartphone Samsung Galaxy S21', 'Latest Samsung smartphone with 5G support and 128GB storage.', 799.99, 1, true, '2024-12-01 00:43:08.095', '2024-12-01', 'https://avatars.mds.yandex.net/get-goods_pic/8962683/hat89067f1626a543d28b55db916248150a/600x600'),
    (2, 'Laptop Apple MacBook Pro 13\"', 'High-performance laptop with M1 chip and Retina display.', 1299.99, 1, true, '2024-12-01 00:43:08.095', '2024-12-01', 'https://avatars.mds.yandex.net/get-mpic/1589815/img_id198999777704206327.jpeg/orig'),
    (3, 'Wireless Headphones Sony WH-1000XM4', 'Noise-canceling over-ear headphones with long battery life.', 1059.99, 1, true, '2024-12-01 00:43:08.095', '2024-12-01', 'https://audio-technica-shop.ru/image/cache/catalog/0708/naushniki-bluetooth-beats-solo-2-wireless-white-mhnh2ze-a-100000015474_8-1000x1000.jpg'),
    (4, '4K TV LG OLED65CX', '65-inch 4K OLED TV with perfect black levels and AI-enhanced picture quality.', 2499.99, 1, true, '2024-12-01 00:43:08.095', '2024-12-01', 'https://avatars.mds.yandex.net/i?id=985951a80d4fb634fcccef52cf7d4d37_l-5707862-images-thumbs&n=13'),
    (5, 'Smartwatch Fitbit Charge 5', 'Fitness tracker with built-in GPS, heart rate monitor, and sleep tracking.', 179.99, 1, true, '2024-12-01 00:43:08.095', '2024-12-01', 'https://avatars.mds.yandex.net/i?id=a2dd891623f7251eeba432260e5ca51e_l-8185861-images-thumbs&n=13'),
    (6, 'Office Chair Herman Miller Aeron', 'Ergonomic office chair designed for comfort and support.', 1395.0, 2, true, '2024-12-01 00:43:08.095', '2024-12-01', 'https://i.ebayimg.com/00/s/OTk5WDk5Mg==/z/n-MAAOxy5rpSTIFJ/$(KGrHqN,!mEFJLdUuWKwBSTIFI,5qg~~60_57.JPG?set_id=880000500F'),
    (7, 'Wooden Dining Table', 'Solid wood dining table with a modern design.', 899.99, 2, true, '2024-12-01 00:43:08.095', '2024-12-01', 'https://i.pinimg.com/736x/5b/f7/0a/5bf70ab680792ca363e06121fe136639.jpg'),
    (8, 'Leather Sofa', 'Luxury leather sofa with comfortable seating for three.', 1450.0, 2, true, '2024-12-01 00:43:08.095', '2024-12-01', 'https://avatars.mds.yandex.net/i?id=8041b1803e94d627e191e03c768e6f706172ef84-9065994-images-thumbs&n=13'),
    (9, 'Mens T-shirt Nike', 'Comfortable cotton t-shirt for men with Nike logo.', 24.99, 3, true, '2024-12-01 00:43:08.095', '2024-12-01', 'https://s3-eu-west-1.amazonaws.com/images.linnlive.com/8ddeb9241c205d981ebb6b6c3d7765ce/db49e0bd-12f8-4c09-a13f-72d91896edc2.jpg'),
    (10, 'Womens Summer Dress Zara', 'Stylish and breathable summer dress for women.', 49.99, 3, true, '2024-12-01 00:43:08.095', '2024-12-01', 'https://avatars.mds.yandex.net/i?id=197dc0941c878128519ebc186b362508f59cbf08-7663734-images-thumbs&n=13'),
    (11, 'Fiction Book \"The Great Gatsby\"', 'Classic novel by F. Scott Fitzgerald about the Jazz Age in America.', 15.99, 4, true, '2024-12-01 00:43:08.095', '2024-12-01', 'https://i.pinimg.com/originals/d8/db/e4/d8dbe46db5e2d0047750ecba2f1506fe.jpg'),
    (12, 'Cookbook \"The Joy of Cooking\"', 'Comprehensive cookbook with classic and modern recipes.', 29.99, 4, true, '2024-12-01 00:43:08.095', '2024-12-01', 'https://avatars.mds.yandex.net/i?id=69ad4140503f8ab9dd0e9a79b4f19ad8_l-8182695-images-thumbs&n=13'),
    (13, 'Apple Pie', 'Freshly baked apple pie made with the finest ingredients.', 8.99, 5, true, '2024-12-01 00:43:08.095', '2024-12-01', 'https://yastatic.net/naydex/yandex-search/Wja6ae322/371a51PW/hmQXKy-wPi7JW9enccU6reQxv8AL69oksVSbE5GVH6PxTjY6fvlnBLSRYOdnnYtnwUuZbc4K3Tr181gTlE2GoSWAVrmcUw0ICnllsQInuKw0cNtguj5_9ZL2I'),
    (14, 'Organic Avocados', 'Fresh and organic avocados, perfect for salads and sandwiches.', 3.99, 5, true, '2024-12-01 00:43:08.095', '2024-12-01', 'https://avatars.mds.yandex.net/i?id=dd289b567a9c7f978071b95acd2c3176416f1104-5228090-images-thumbs&n=13');
