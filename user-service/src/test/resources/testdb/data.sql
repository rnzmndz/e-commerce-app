-- Insert authorities
INSERT INTO authority (name) VALUES ('ROLE_USER'), ('ROLE_ADMIN');

-- Insert users
INSERT INTO user_details (username, password, email, first_name, last_name, role, created_at, updated_at)
VALUES
('user1', 'password1', 'user1@example.com', 'John', 'Doe', 'CUSTOMER', NOW(), NOW()),
('user2', 'password2', 'user2@example.com', 'Jane', 'Smith', 'CUSTOMER', NOW(), NOW()),
('user3', 'password3', 'user3@example.com', 'Alice', 'Johnson', 'ADMIN', NOW(), NOW()),
('user4', 'password4', 'user4@example.com', 'Bob', 'Brown', 'CUSTOMER', NOW(), NOW()),
('user5', 'password5', 'user5@example.com', 'Charlie', 'Davis', 'ADMIN', NOW(), NOW());

-- Insert addresses
INSERT INTO address (street, city, state, zip_code, country, user_id)
VALUES
('123 Main St', 'Springfield', 'IL', '62701', 'USA', 1),
('456 Elm St', 'Springfield', 'IL', '62702', 'USA', 2),
('789 Oak St', 'Springfield', 'IL', '62703', 'USA', 3),
('101 Maple St', 'Springfield', 'IL', '62704', 'USA', 4),
('202 Pine St', 'Springfield', 'IL', '62705', 'USA', 5);

-- Insert profiles
INSERT INTO profile (bio, picture_url, user_id)
VALUES
('Bio for user1', 'http://example.com/user1.jpg', 1),
('Bio for user2', 'http://example.com/user2.jpg', 2),
('Bio for user3', 'http://example.com/user3.jpg', 3),
('Bio for user4', 'http://example.com/user4.jpg', 4),
('Bio for user5', 'http://example.com/user5.jpg', 5);

-- Assign authorities to users
INSERT INTO user_authorities (user_id, authority_id)
VALUES
(1, 1),
(2, 1),
(3, 2),
(4, 1),
(5, 2);