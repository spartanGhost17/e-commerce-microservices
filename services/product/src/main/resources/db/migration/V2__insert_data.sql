-- Insert categories with sequences
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Computer Keyboards', 'Keyboards');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Computer Monitors', 'Monitors');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Display Screens', 'Screens');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Computer Mice', 'Mice');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Computer Accessories', 'Accessories');

-- Insert products for the 'Keyboards' category
INSERT INTO public.product (id, available_quantity, description, name, price, category_id, serial_number)
VALUES
    (nextval('product_seq'), 10, 'Mechanical keyboard with RGB lighting', 'Mechanical Keyboard 1', 99.99, (SELECT id FROM category WHERE name = 'Keyboards'), '123e4567-e89b-12d3-a456-426614174100'),
    (nextval('product_seq'), 15, 'Wireless compact keyboard', 'Wireless Compact Keyboard 1', 79.99, (SELECT id FROM category WHERE name = 'Keyboards'), '123e4567-e89b-12d3-a456-426614174101'),
    (nextval('product_seq'), 20, 'Backlit gaming keyboard with customizable keys', 'Gaming Keyboard 1', 129.99, (SELECT id FROM category WHERE name = 'Keyboards'), '123e4567-e89b-12d3-a456-426614174102'),
    (nextval('product_seq'), 25, 'Mechanical keyboard with wrist rest', 'Ergonomic Keyboard 1', 109.99, (SELECT id FROM category WHERE name = 'Keyboards'), '123e4567-e89b-12d3-a456-426614174103'),
    (nextval('product_seq'), 18, 'Wireless keyboard and mouse combo', 'Wireless Combo 1', 69.99, (SELECT id FROM category WHERE name = 'Keyboards'), '123e4567-e89b-12d3-a456-426614174104');

-- Insert products for the 'Monitors' category
INSERT INTO public.product (id, available_quantity, description, name, price, category_id, serial_number)
VALUES
    (nextval('product_seq'), 30, '27-inch IPS monitor with 4K resolution', '4K Monitor 1', 399.99, (SELECT id FROM category WHERE name = 'Monitors'), '123e4567-e89b-12d3-a456-426614174105'),
    (nextval('product_seq'), 25, 'Ultra-wide gaming monitor with HDR support', 'Ultra-wide Gaming Monitor 1', 499.99, (SELECT id FROM category WHERE name = 'Monitors'), '123e4567-e89b-12d3-a456-426614174106'),
    (nextval('product_seq'), 22, '24-inch LED monitor for office use', 'Office Monitor 1', 179.99, (SELECT id FROM category WHERE name = 'Monitors'), '123e4567-e89b-12d3-a456-426614174107'),
    (nextval('product_seq'), 28, '32-inch curved monitor with AMD FreeSync', 'Curved Monitor 1', 329.99, (SELECT id FROM category WHERE name = 'Monitors'), '123e4567-e89b-12d3-a456-426614174108'),
    (nextval('product_seq'), 35, 'Portable USB-C monitor for laptops', 'Portable Monitor 1', 249.99, (SELECT id FROM category WHERE name = 'Monitors'), '123e4567-e89b-12d3-a456-426614174109');

-- Insert products for the 'Screens' category
INSERT INTO public.product (id, available_quantity, description, name, price, category_id, serial_number)
VALUES
    (nextval('product_seq'), 15, 'Curved OLED gaming screen with 240Hz refresh rate', 'Curved OLED Gaming Screen 1', 799.99, (SELECT id FROM category WHERE name = 'Screens'), '123e4567-e89b-12d3-a456-426614174110'),
    (nextval('product_seq'), 18, 'Flat QLED monitor with 1440p resolution', 'QLED Monitor 1', 599.99, (SELECT id FROM category WHERE name = 'Screens'), '123e4567-e89b-12d3-a456-426614174111'),
    (nextval('product_seq'), 22, '27-inch touch screen display for creative work', 'Touch Screen Display 1', 699.99, (SELECT id FROM category WHERE name = 'Screens'), '123e4567-e89b-12d3-a456-426614174112'),
    (nextval('product_seq'), 20, 'Ultra-slim 4K HDR display for multimedia', 'Ultra-slim 4K HDR Display 1', 449.99, (SELECT id FROM category WHERE name = 'Screens'), '123e4567-e89b-12d3-a456-426614174113'),
    (nextval('product_seq'), 25, 'Gaming projector with low input lag', 'Gaming Projector 1', 899.99, (SELECT id FROM category WHERE name = 'Screens'), '123e4567-e89b-12d3-a456-426614174114');

-- Insert products for the 'Mice' category
INSERT INTO public.product (id, available_quantity, description, name, price, category_id, serial_number)
VALUES
    (nextval('product_seq'), 30, 'Wireless gaming mouse with customizable RGB lighting', 'RGB Gaming Mouse 1', 59.99, (SELECT id FROM category WHERE name = 'Mice'), '123e4567-e89b-12d3-a456-426614174115'),
    (nextval('product_seq'), 28, 'Ergonomic wired mouse for productivity', 'Ergonomic Wired Mouse 1', 29.99, (SELECT id FROM category WHERE name = 'Mice'), '123e4567-e89b-12d3-a456-426614174116'),
    (nextval('product_seq'), 32, 'Ambidextrous gaming mouse with high DPI', 'Ambidextrous Gaming Mouse 1', 69.99, (SELECT id FROM category WHERE name = 'Mice'), '123e4567-e89b-12d3-a456-426614174117'),
    (nextval('product_seq'), 26, 'Travel-sized compact mouse for laptops', 'Travel Mouse 1', 19.99, (SELECT id FROM category WHERE name = 'Mice'), '123e4567-e89b-12d3-a456-426614174118'),
    (nextval('product_seq'), 35, 'Vertical ergonomic mouse for reduced strain', 'Vertical Ergonomic Mouse 1', 39.99, (SELECT id FROM category WHERE name = 'Mice'), '123e4567-e89b-12d3-a456-426614174119');

-- Insert products for the 'Accessories' category
INSERT INTO public.product (id, available_quantity, description, name, price, category_id, serial_number)
VALUES
    (nextval('product_seq'), 25, 'Adjustable laptop stand with cooling fan', 'Adjustable Laptop Stand 1', 34.99, (SELECT id FROM category WHERE name = 'Accessories'), '123e4567-e89b-12d3-a456-426614174120'),
    (nextval('product_seq'), 20, 'Wireless charging pad for smartphones', 'Wireless Charging Pad 1', 24.99, (SELECT id FROM category WHERE name = 'Accessories'), '123e4567-e89b-12d3-a456-426614174121'),
    (nextval('product_seq'), 28, 'Gaming headset stand with RGB lighting', 'RGB Headset Stand 1', 49.99, (SELECT id FROM category WHERE name = 'Accessories'), '123e4567-e89b-12d3-a456-426614174122'),
    (nextval('product_seq'), 22, 'Bluetooth mechanical keypad for tablets', 'Bluetooth Keypad 1', 39.99, (SELECT id FROM category WHERE name = 'Accessories'), '123e4567-e89b-12d3-a456-426614174123'),
    (nextval('product_seq'), 30, 'External hard drive enclosure with USB-C', 'External Hard Drive Enclosure 1', 29.99, (SELECT id FROM category WHERE name = 'Accessories'), '123e4567-e89b-12d3-a456-426614174124');

/*-- Insert product images
--INSERT INTO public.productimage (id, image_url, product_id)
--VALUES
--    (nextval('productimage_seq'), '123e4567-e89b-12d3-a456-426614174100/image1.png', (SELECT id FROM product WHERE serial_number = '123e4567-e89b-12d3-a456-426614174100')),
--    (nextval('productimage_seq'), '123e4567-e89b-12d3-a456-426614174100/image2.png', (SELECT id FROM product WHERE serial_number = '123e4567-e89b-12d3-a456-426614174100')),
--    (nextval('productimage_seq'), '123e4567-e89b-12d3-a456-426614174101/image1.png', (SELECT id FROM product WHERE serial_number = '123e4567-e89b-12d3-a456-426614174101')),
--    (nextval('productimage_seq'), '123e4567-e89b-12d3-a456-426614174102/image1.png', (SELECT id FROM product WHERE serial_number = '123e4567-e89b-12d3-a456-426614174102')),
--    (nextval('productimage_seq'), '123e4567-e89b-12d3-a456-426614174103/image1.png', (SELECT id FROM product WHERE serial_number = '123e4567-e89b-12d3-a456-426614174103')),
--    (nextval('productimage_seq'), '123e4567-e89b-12d3-a456-426614174104/image1.png', (SELECT id FROM product WHERE serial_number = '123e4567-e89b-12d3-a456-426614174104')),
--    (nextval('productimage_seq'), '123e4567-e89b-12d3-a456-426614174105/image1.png', (SELECT id FROM product WHERE serial_number = '123e4567-e89b-12d3-a456-426614174105')),
--    (nextval('productimage_seq'), '123e4567-e89b-12d3-a456-426614174106/image1.png', (SELECT id FROM product WHERE serial_number = '123e4567-e89b-12d3-a456-426614174106')),
--    (nextval('productimage_seq'), '123e4567-e89b-12d3-a456-426614174107/image1.png', (SELECT id FROM product WHERE serial_number = '123e4567-e89b-12d3-a456-426614174107')),
--    (nextval('productimage_seq'), '123e4567-e89b-12d3-a456-426614174108/image1.png', (SELECT id FROM product WHERE serial_number = '123e4567-e89b-12d3-a456-426614174108')),
--    (nextval('productimage_seq'), '123e4567-e89b-12d3-a456-426614174109/image1.png', (SELECT id FROM product WHERE serial_number = '123e4567-e89b-12d3-a456-426614174109')),
--    (nextval('productimage_seq'), '123e4567-e89b-12d3-a456-426614174110/image1.png', (SELECT id FROM product WHERE serial_number = '123e4567-e89b-12d3-a456-426614174110')),
--    (nextval('productimage_seq'), '123e4567-e89b-12d3-a456-426614174111/image1.png', (SELECT id FROM product WHERE serial_number = '123e4567-e89b-12d3-a456-426614174111')),
--    (nextval('productimage_seq'), '123e4567-e89b-12d3-a456-426614174112/image1.png', (SELECT id FROM product WHERE serial_number = '123e4567-e89b-12d3-a456-426614174112')),
--    (nextval('productimage_seq'), '123e4567-e89b-12d3-a456-426614174113/image1.png', (SELECT id FROM product WHERE serial_number = '123e4567-e89b-12d3-a456-426614174113')),
--    (nextval('productimage_seq'), '123e4567-e89b-12d3-a456-426614174114/image1.png', (SELECT id FROM product WHERE serial_number = '123e4567-e89b-12d3-a456-426614174114')),
--    (nextval('productimage_seq'), '123e4567-e89b-12d3-a456-426614174115/image1.png', (SELECT id FROM product WHERE serial_number = '123e4567-e89b-12d3-a456-426614174115')),
--    (nextval('productimage_seq'), '123e4567-e89b-12d3-a456-426614174116/image1.png', (SELECT id FROM product WHERE serial_number = '123e4567-e89b-12d3-a456-426614174116')),
--    (nextval('productimage_seq'), '123e4567-e89b-12d3-a456-426614174117/image1.png', (SELECT id FROM product WHERE serial_number = '123e4567-e89b-12d3-a456-426614174117')),
--    (nextval('productimage_seq'), '123e4567-e89b-12d3-a456-426614174118/image1.png', (SELECT id FROM product WHERE serial_number = '123e4567-e89b-12d3-a456-426614174118')),
--    (nextval('productimage_seq'), '123e4567-e89b-12d3-a456-426614174119/image1.png', (SELECT id FROM product WHERE serial_number = '123e4567-e89b-12d3-a456-426614174119')),
--    (nextval('productimage_seq'), '123e4567-e89b-12d3-a456-426614174120/image1.png', (SELECT id FROM product WHERE serial_number = '123e4567-e89b-12d3-a456-426614174120')),
--    (nextval('productimage_seq'), '123e4567-e89b-12d3-a456-426614174121/image1.png', (SELECT id FROM product WHERE serial_number = '123e4567-e89b-12d3-a456-426614174121')),
--    (nextval('productimage_seq'), '123e4567-e89b-12d3-a456-426614174122/image1.png', (SELECT id FROM product WHERE serial_number = '123e4567-e89b-12d3-a456-426614174122')),
--    (nextval('productimage_seq'), '123e4567-e89b-12d3-a456-426614174123/image1.png', (SELECT id FROM product WHERE serial_number = '123e4567-e89b-12d3-a456-426614174123')),
--    (nextval('productimage_seq'), '123e4567-e89b-12d3-a456-426614174124/image1.png', (SELECT id FROM product WHERE serial_number = '123e4567-e89b-12d3-a456-426614174124'));*/
