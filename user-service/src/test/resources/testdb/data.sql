-- Insert authorities
INSERT INTO authority (name) VALUES ('ROLE_ADMIN'), ('ROLE_CUSTOMER');

-- Insert users
INSERT INTO user_details (username, password, email, first_name, last_name, role, created_at, updated_at)
VALUES
    ('john_doe', 'password123', 'john.doe@example.com', 'John', 'Doe', 'ADMIN', NOW(), NOW()),
    ('jane_smith', 'password123', 'jane.smith@example.com', 'Jane', 'Smith', 'CUSTOMER', NOW(), NOW()),
    ('alice_wonder', 'password123', 'alice.wonder@example.com', 'Alice', 'Wonder', 'CUSTOMER', NOW(), NOW()),
    ('bob_builder', 'password123', 'bob.builder@example.com', 'Bob', 'Builder', 'CUSTOMER', NOW(), NOW()),
    ('charlie_brown', 'password123', 'charlie.brown@example.com', 'Charlie', 'Brown', 'CUSTOMER', NOW(), NOW()),
    ('diana_prince', 'password123', 'diana.prince@example.com', 'Diana', 'Prince', 'ADMIN', NOW(), NOW()),
    ('edward_scissorhands', 'password123', 'edward.scissorhands@example.com', 'Edward', 'Scissorhands', 'CUSTOMER', NOW(), NOW()),
    ('fiona_shrek', 'password123', 'fiona.shrek@example.com', 'Fiona', 'Shrek', 'CUSTOMER', NOW(), NOW()),
    ('george_jetson', 'password123', 'george.jetson@example.com', 'George', 'Jetson', 'CUSTOMER', NOW(), NOW()),
    ('harry_potter', 'password123', 'harry.potter@example.com', 'Harry', 'Potter', 'CUSTOMER', NOW(), NOW());

-- Assign authorities to users
INSERT INTO user_authorities (user_id, authority_id)
VALUES
    (1, (SELECT id FROM authority WHERE name = 'ROLE_ADMIN')),
    (2, (SELECT id FROM authority WHERE name = 'ROLE_CUSTOMER')),
    (3, (SELECT id FROM authority WHERE name = 'ROLE_CUSTOMER')),
    (4, (SELECT id FROM authority WHERE name = 'ROLE_CUSTOMER')),
    (5, (SELECT id FROM authority WHERE name = 'ROLE_CUSTOMER')),
    (6, (SELECT id FROM authority WHERE name = 'ROLE_ADMIN')),
    (7, (SELECT id FROM authority WHERE name = 'ROLE_CUSTOMER')),
    (8, (SELECT id FROM authority WHERE name = 'ROLE_CUSTOMER')),
    (9, (SELECT id FROM authority WHERE name = 'ROLE_CUSTOMER')),
    (10, (SELECT id FROM authority WHERE name = 'ROLE_CUSTOMER'));

-- Insert addresses for users
INSERT INTO address (street, city, state, zip_code, country, user_id)
VALUES
    ('123 Main St', 'Springfield', 'IL', '62701', 'USA', 1),
    ('456 Elm St', 'Shelbyville', 'IL', '62702', 'USA', 2),
    ('789 Oak St', 'Capital City', 'IL', '62703', 'USA', 3),
    ('101 Pine St', 'Ogdenville', 'IL', '62704', 'USA', 4),
    ('202 Maple St', 'North Haverbrook', 'IL', '62705', 'USA', 5),
    ('303 Birch St', 'Springfield', 'IL', '62706', 'USA', 6),
    ('404 Cedar St', 'Shelbyville', 'IL', '62707', 'USA', 7),
    ('505 Walnut St', 'Capital City', 'IL', '62708', 'USA', 8),
    ('606 Spruce St', 'Ogdenville', 'IL', '62709', 'USA', 9),
    ('707 Fir St', 'North Haverbrook', 'IL', '62710', 'USA', 10);

-- Insert profiles for users
INSERT INTO profile (bio, picture_url, user_id)
VALUES
    ('I am an admin user.', 'https://example.com/john_doe.jpg', 1),
    ('I am a customer user.', 'https://example.com/jane_smith.jpg', 2),
    ('I love adventures.', 'https://example.com/alice_wonder.jpg', 3),
    ('I build things.', 'https://example.com/bob_builder.jpg', 4),
    ('I am a good boy.', 'https://example.com/charlie_brown.jpg', 5),
    ('I am a warrior.', 'https://example.com/diana_prince.jpg', 6),
    ('I have scissors for hands.', 'https://example.com/edward_scissorhands.jpg', 7),
    ('I am an ogre.', 'https://example.com/fiona_shrek.jpg', 8),
    ('I live in the future.', 'https://example.com/george_jetson.jpg', 9),
    ('I am a wizard.', 'https://example.com/harry_potter.jpg', 10);