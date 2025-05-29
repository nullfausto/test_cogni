-- Create a database

CREATE DATABASE ECommercePlatform;

CREATE TABLE Users(
    userid INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) UNIQUE,
    email VARCHAR(100) UNIQUE,
    password CHAR(60) NOT NULL,
    role VARCHAR(100) NOT NULL
);

CREATE TABLE Products(
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    category VARCHAR(100) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    stock_quantity INT
);

CREATE TABLE Orders(
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    order_date DATE NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    order_status VARCHAR(50) NOT NULL DEFAULT 'pending',
    FOREIGN KEY (user_id) REFERENCES Users(userid) ON DELETE CASCADE
);

CREATE TABLE OrderDetails(
    order_detail_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL(10, 2),
    FOREIGN KEY (order_id) REFERENCES Orders(order_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES Products(product_id) ON DELETE CASCADE
);

CREATE TABLE Payments(
    payment_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    payment_date DATE NOT NULL,
    payment_method VARCHAR(50),
    amount DECIMAL (10, 2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES Orders(order_id) ON DELETE CASCADE
);

CREATE TABLE Reviews(
    review_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    user_id INT NOT NULL,
    review_text TEXT,
    rating TINYINT NOT NULL,
    review_date DATETIME NOT NULL,
    FOREIGN KEY (product_id) REFERENCES Products(product_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES Users(userid) ON DELETE CASCADE
);

-- Populating the tables
INSERT INTO Users (username, email, password, role) VALUES
('frida_vd', 'frids@test.com', '$2a$10$x', 'user'),
('sophie_esc', 'sophesc@gmail.com', '$2a$10$x', 'user'),
('oscar2x', 'oscari@io.com', '$2a$10$x', 'admin'),
('carlos_eduardo', 'charli2e@io.com', '$2a$10$x', 'user'),
('sararob', 'sarar@test.com', '$2a$10$x', 'moderator'),
('luishdz', 'luish@io.com','$2a$10$x', 'user'),
('selene123', 'sele@test.com', '$2a$10$x', 'user'),
('denzelv20', 'denzelv@io.com', '$2a$10$x', 'user'),
('joel_miller', 'joelm@gmail.com', '$2a$10$x', 'moderator'),
('fausto1401', 'faus1@io.com', '$2a$10$x', 'admin');

INSERT INTO Products (product_name, category, price, stock_quantity) VALUES
('Wireless Bluetooth Earbuds', 'Electronics', 59.99, 150),
('Organic Cotton T-Shirt', 'Clothing', 24.95, 300),
('Stainless Steel Water Bottle', 'Home & Kitchen', 19.99, 200),
('Non-Stick Frying Pan', 'Cookware', 34.50, 85),
('Yoga Mat', 'Fitness', 29.99, 120),
('Bestseller Novel', 'Books', 14.99, 75),
('Smart LED Light Bulb', 'Smart Home', 22.95, 180),
('Acrylic Paint Set', 'Arts & Crafts', 42.75, 60),
('Wireless Phone Charger', 'Electronics', 27.99, 210),
('Memory Foam Pillow', 'Bedding', 39.95, 95);

INSERT INTO Orders (user_id, order_date, total_amount, order_status) VALUES
(1, '2023-01-15', 89.97, 'completed'),
(1, '2023-02-20', 54.95, 'completed'),
(1, '2023-03-10', 112.50, 'shipped'),
(2, '2023-01-22', 34.99, 'completed'),
(2, '2023-04-05', 78.45, 'processing'),
(3, '2023-02-14', 149.99, 'completed'),
(3, '2023-03-01', 45.00, 'cancelled'),
(3, '2023-05-18', 92.75, 'shipped'),
(4, '2023-01-30', 27.50, 'completed'),
(5, '2023-02-28', 63.20, 'completed'),
(5, '2023-04-15', 155.35, 'shipped'),
(6, '2023-03-12', 42.99, 'completed'),
(6, '2023-03-25', 88.60, 'completed'),
(6, '2023-05-01', 71.25, 'processing'),
(7, '2023-04-22', 119.95, 'shipped'),
(8, '2023-02-05', 29.99, 'completed'),
(8, '2023-05-10', 64.75, 'processing'),
(9, '2023-01-18', 53.45, 'completed'),
(9, '2023-03-30', 97.80, 'shipped'),
(10, '2023-04-30', 210.00, 'completed');

INSERT INTO OrderDetails (order_id, product_id, quantity, unit_price) VALUES
(1, 3, 2, 19.99),  
(1, 5, 1, 29.99),  
(1, 9, 1, 27.99),  
(2, 2, 1, 24.95),  
(2, 7, 1, 22.95),  
(2, 10, 1, 39.95), 
(3, 1, 1, 59.99),  
(3, 4, 1, 34.50),  
(3, 8, 1, 42.75),  
(4, 3, 1, 19.99),  
(4, 6, 1, 14.99),  
(5, 1, 1, 59.99),  
(5, 10, 1, 39.95), 
(6, 1, 2, 59.99),  
(6, 3, 1, 19.99),  
(7, 2, 1, 24.95),  
(7, 5, 1, 29.99),  
(8, 7, 2, 22.95),  
(8, 9, 1, 27.99),  
(9, 6, 1, 14.99),  
(9, 3, 1, 19.99),  
(10, 4, 1, 34.50), 
(10, 8, 1, 42.75), 
(11, 1, 1, 59.99), 
(11, 3, 1, 19.99), 
(11, 5, 1, 29.99),  
(11, 7, 1, 22.95),  
(12, 2, 1, 24.95),  
(12, 9, 1, 27.99),  
(13, 1, 1, 59.99),  
(13, 10, 1, 39.95), 
(14, 3, 2, 19.99),  
(14, 6, 1, 14.99),  
(14, 8, 1, 42.75),  
(15, 1, 2, 59.99),  
(15, 10, 1, 39.95), 
(16, 5, 1, 29.99),  
(17, 2, 1, 24.95),  
(17, 3, 1, 19.99),  
(17, 7, 1, 22.95),  
(18, 4, 1, 34.50),  
(18, 6, 1, 14.99),  
(18, 9, 1, 27.99),  
(19, 1, 1, 59.99),  
(19, 8, 1, 42.75),  
(20, 1, 3, 59.99),  
(20, 10, 1, 39.95); 

INSERT INTO Payments (order_id, payment_date, payment_method, amount) VALUES
(1, '2023-01-15', 'Credit Card', 89.97),
(2, '2023-02-20', 'PayPal', 54.95),
(3, '2023-03-10', 'Credit Card', 112.50),
(4, '2023-01-22', 'Debit Card', 34.99),
(5, '2023-04-05', 'Credit Card', 78.45),
(6, '2023-02-14', 'Apple Pay', 149.99),
(7, '2023-03-01', 'Credit Card', 45.00),
(8, '2023-05-18', 'Google Pay', 92.75),
(9, '2023-01-30', 'Debit Card', 27.50),
(10, '2023-02-28', 'Credit Card', 63.20),
(11, '2023-04-15', 'PayPal', 155.35),
(12, '2023-03-12', 'Debit Card', 42.99),
(13, '2023-03-25', 'Credit Card', 88.60),
(14, '2023-05-01', 'Apple Pay', 71.25),
(15, '2023-04-22', 'Credit Card', 119.95),
(16, '2023-02-05', 'Debit Card', 29.99),
(17, '2023-05-10', 'PayPal', 64.75),
(18, '2023-01-18', 'Credit Card', 53.45),
(19, '2023-03-30', 'Google Pay', 97.80),
(20, '2023-04-30', 'Bank Transfer', 210.00);


INSERT INTO Reviews (product_id, user_id, review_text, rating, review_date) VALUES
(1, 2, 'Great sound quality and battery life!', 5, '2023-02-15 14:30:00'),
(1, 5, 'Good but the left earbud sometimes disconnects', 3, '2023-03-02 09:15:00'),
(1, 8, 'Absolutely worth the price! Noise cancellation works perfectly.', 5, '2023-04-10 18:45:00'),
(2, 1, 'Very comfortable fabric, true to size', 4, '2023-02-25 11:20:00'),
(2, 3, 'Shrunk after first wash, disappointing', 2, '2023-03-12 16:50:00'),
(2, 7, 'Love the fit and material!', 5, '2023-04-05 13:10:00'),
(3, 4, 'Leaks when placed sideways', 2, '2023-02-01 08:45:00'),
(3, 6, 'Sturdy and keeps water cold for hours', 5, '2023-03-20 10:30:00'),
(3, 9, 'Perfect size for my gym bag', 4, '2023-05-01 17:25:00'),
(4, 2, 'Food sticks to the surface despite being non-stick', 2, '2023-03-15 12:40:00'),
(4, 5, 'Heats evenly and cleans easily', 5, '2023-04-20 14:15:00'),
(4, 10, 'Great pan for the price', 4, '2023-05-05 09:50:00'),
(5, 1, 'Too thin for my liking', 3, '2023-02-28 07:30:00'),
(5, 3, 'Perfect thickness and grip', 5, '2023-03-18 15:20:00'),
(5, 8, 'Started peeling after 2 months of use', 2, '2023-04-25 19:05:00'),
(6, 4, 'Could not put it down! Amazing story', 5, '2023-02-10 21:15:00'),
(6, 7, 'Overhyped in my opinion', 3, '2023-03-05 10:40:00'),
(7, 2, 'Bright and energy efficient', 4, '2023-03-08 13:55:00'),
(7, 6, 'Stopped working after 3 weeks', 1, '2023-04-12 08:20:00'),
(8, 3, 'Vibrant colors, great for beginners', 5, '2023-03-22 16:10:00'),
(8, 9, 'Some colors dried out quickly', 3, '2023-05-08 11:45:00'),
(9, 1, 'Charges fast but gets warm', 4, '2023-02-18 14:25:00'),
(9, 5, 'Does not work with my phone case on', 2, '2023-04-15 09:30:00'),
(10, 4, 'Best sleep I have had in years!', 5, '2023-03-10 22:05:00'),
(10, 7, 'Too firm for my preference', 3, '2023-04-28 07:50:00');

-- SQL query for retrieving a list of all products in a specific category
SELECT p.product_name, p.category
FROM Products p
WHERE p.category = 'Electronics' -- Name of the category

-- Retrieve the details of a specific user by providing their user_id
CREATE VIEW UserDetails AS
SELECT u.username, u.email, u.role
FROM Users u
WHERE u.userid = '1'; -- Insert specific user id

SELECT * FROM UserDetails; -- For executing the view

-- Retrieve the order history for a particular user

SELECT u.username,
    o.order_date,
    o.total_amount,
    od.product_id,
    p.product_name,
    od.quantity
FROM Orders o
LEFT JOIN OrderDetails od ON o.order_id = od.order_id
LEFT JOIN Products p ON od.product_id = p.product_id
LEFT JOIN Users u ON o.user_id = u.userid
WHERE o.user_id = '1';

-- Retrieve the products in an order along with their quantities and prices
-- Sorting by name A-Z
SELECT p.product_name, p.stock_quantity, p.price, p.category
FROM Products p
ORDER BY p.product_name ASC;

-- Sorting by price (Most expensive to cheaper)
SELECT p.product_name, p.stock_quantity, p.price, p.category
FROM Products p
ORDER BY p.price DESC;

-- Sort by quantities (Product with most amount in stock to the less)
SELECT p.product_name, p.stock_quantity, p.price, p.category
FROM Products p
ORDER BY p.stock_quantity DESC;

-- Retrieve rating of a product
SELECT r.rating, -- AVG(r.rating)
    p.product_name
FROM Reviews r
LEFT JOIN Products p ON r.product_id = p.product_id
WHERE r.product_id = '1';

-- Retrieve average rating of a product
SELECT 
    p.product_name,
    AVG(r.rating) AS average_rating
FROM Reviews r
LEFT JOIN Products p ON r.product_id = p.product_id
WHERE r.product_id = 1;


-- Retrieve the total revenue for a given month

SELECT SUM(Payments.amount) as total_revenue
FROM Payments;

-- Modifying the database
-- Add a new product to the inventory
INSERT INTO Products (product_name, category, price, stock_quantity) VALUES
('Noise Cancelling Microphone', 'Electronics', 40.00, 100);

-- Place a new order for an user
INSERT INTO Orders(user_id, order_date, total_amount, order_status) VALUES
(10, '2023-04-30', 80.00, 'completed');

-- Updating the stock quantity of a product
UPDATE Products
SET stock_quantity = 50
WHERE product_id = 1;

-- Multiple products
/*

Update multiple products at once
UPDATE Products
SET stock_quantity = 
    CASE 
        WHEN product_id = 1 THEN 100
        WHEN product_id = 2 THEN 75
        WHEN product_id = 3 THEN 50
    END
WHERE product_id IN (1, 2, 3);

*/

-- Remove an user's review from a specific product
DELETE FROM Reviews
WHERE user_id = 4 AND product_id = 1;

-- Identify the top 3 selling products

SELECT p.product_name,
    SUM(od.quantity) as quantities
FROM OrderDetails od
LEFT JOIN Products p ON od.product_id = p.product_id
GROUP BY p.product_name
ORDER BY quantities DESC LIMIT 3;

-- Find users who have placed orders exceeding a certain amount

SELECT o.total_amount,
    u.username
FROM Orders o
LEFT JOIN Users u ON o.user_id = u.userid
WHERE O.total_amount >= 90;


-- Find users who have places orders exceeding a total certain amount

SELECT SUM(o.total_amount) as totalAmount, 
    u.username
FROM Orders o
LEFT JOIN Users u ON o.user_id = u.userid
GROUP BY u.username
HAVING totalAmount >= 200.00;

-- Calculate the overall average rating for a specific product category
SELECT p.product_name,
    AVG(r.rating) AS average_rating
FROM Reviews r
LEFT JOIN Products p ON r.product_id = p.product_id
WHERE p.category = 'Electronics'
GROUP BY p.product_name;

-- Calculate the overall average rating for each product category

SELECT p.category,
    ROUND(AVG(r.rating), 2) AS average_rating,
    COUNT(r.review_id) AS review_count
FROM Reviews r
LEFT JOIN Products p ON r.product_id = p.product_id
GROUP BY p.category
ORDER BY average_rating DESC;