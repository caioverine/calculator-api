-- Insert initial users
INSERT INTO users (username, password, status, balance) VALUES
('user.usr@email.com', 'userpsw', 'ACTIVE', 100.00),
('user2@example.com', 'password2', 'ACTIVE', 50.00),
('user3@example.com', 'password3', 'INACTIVE', 0.00);

-- Insert initial operations (with costs for each type)
INSERT INTO operations (type, cost) VALUES
('ADDITION', 1.00),
('SUBTRACTION', 1.00),
('MULTIPLICATION', 2.00),
('DIVISION', 2.50),
('SQUARE_ROOT', 3.00),
('RANDOM_STRING', 5.00);

-- Optionally insert some records for testing
INSERT INTO records (operation_id, user_id, amount, user_balance, operation_response, date, active) VALUES
(1, 1, 10, 99.00, '10 + 5 = 15', CURRENT_TIMESTAMP, TRUE),
(2, 1, 20, 98.00, '20 - 5 = 15', CURRENT_TIMESTAMP, TRUE),
(3, 2, 5, 48.00, '5 * 2 = 10', CURRENT_TIMESTAMP, TRUE);
