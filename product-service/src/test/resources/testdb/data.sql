-- Insert sample users first
INSERT INTO user_detail (id, user_name, email) VALUES
(1, 'john_doe', 'john@example.com'),
(2, 'jane_smith', 'jane@example.com'),
(3, 'mike_wilson', 'mike@example.com'),
(4, 'sarah_jones', 'sarah@example.com'),
(5, 'tom_brown', 'tom@example.com');

-- Insert rest of the sample data
INSERT INTO brand (id, name, description, logo) VALUES
(1, 'Nike', 'Leading sports brand', 'nike_logo.png'),
(2, 'Apple', 'Tech innovation leader', 'apple_logo.png'),
(3, 'Samsung', 'Electronics giant', 'samsung_logo.png');

INSERT INTO category (id, name, description, sub_category_id) VALUES
(1, 'Electronics', 'Electronic devices', NULL),
(2, 'Phones', 'Mobile devices', 1),
(3, 'Sportswear', 'Athletic clothing', NULL),
(4, 'Footwear', 'Athletic shoes', 3);

INSERT INTO product (id, name, description, sku, brand_id) VALUES
(1, 'iPhone 14', 'Latest iPhone model', 'APL-IP14-001', 2),
(2, 'Air Jordan 1', 'Classic basketball shoes', 'NK-AJ1-001', 1),
(3, 'Galaxy S23', 'Premium Android phone', 'SAM-S23-001', 3),
(4, 'Nike Dri-FIT', 'Performance t-shirt', 'NK-DF-001', 1),
(5, 'MacBook Pro', 'Professional laptop', 'APL-MBP-001', 2);

INSERT INTO price (id, amount, currency) VALUES
(1, 999.99, 'USD'),
(2, 179.99, 'USD'),
(3, 899.99, 'USD'),
(4, 45.99, 'USD'),
(5, 1499.99, 'USD');

INSERT INTO product_variant (id, name, price_id, stock, product_id) VALUES
(1, 'iPhone 14 Pro 256GB', 1, 100, 1),
(2, 'Air Jordan 1 High OG', 2, 50, 2),
(3, 'Galaxy S23 Ultra 512GB', 3, 75, 3),
(4, 'Nike Dri-FIT Medium Black', 4, 200, 4),
(5, 'MacBook Pro 16" M2', 5, 50, 5);

INSERT INTO product_image (id, url, is_primary, product_id) VALUES
(1, 'https://example.com/iphone14.jpg', TRUE, 1),
(2, 'https://example.com/airjordan1.jpg', TRUE, 2),
(3, 'https://example.com/galaxys23.jpg', TRUE, 3),
(4, 'https://example.com/drifit.jpg', TRUE, 4),
(5, 'https://example.com/macbook.jpg', TRUE, 5);

INSERT INTO product_review (id, rating, comment, user_detail_id, product_id) VALUES
(1, 5, 'Excellent phone!', 1, 1),
(2, 4, 'Great shoes!', 2, 2),
(3, 5, 'Amazing device!', 3, 3),
(4, 5, 'Perfect fit!', 4, 4),
(5, 4, 'Powerful laptop!', 5, 5);