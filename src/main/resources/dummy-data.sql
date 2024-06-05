CREATE TABLE Book (
    isbn VARCHAR(255) PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    gender VARCHAR(50),
    price NUMERIC(10, 2),
    quantity NUMERIC(10, 2)
);

CREATE TABLE CreditCard (
    number VARCHAR(19) PRIMARY KEY,
    valid BOOLEAN NOT NULL
);

INSERT INTO Book (isbn, title, gender, price, quantity) VALUES
('978-3-16-148410-0', 'Introduction to PostgreSQL', 'Education', 39.99, 10),
('978-0-262-03384-8', 'Artificial Intelligence: A Modern Approach', 'Technology', 99.99, 5),
('978-0-545-01022-1', 'The Hunger Games', 'Fiction', 12.99, 50),
('978-0-7432-7356-5', 'Angels and Demons', 'Thriller', 14.99, 20),
('978-1-4028-9462-6', 'Harry Potter and the Philosopher Stone', 'Fantasy', 8.99, 100);

INSERT INTO CreditCard (number, valid) VALUES
('1234-5678-9012-3456', TRUE),
('2345-6789-0123-4567', FALSE),
('3456-7890-1234-5678', TRUE),
('4567-8901-2345-6789', TRUE),
('5678-9012-3456-7890', FALSE);