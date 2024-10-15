CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    status VARCHAR(10) NOT NULL,
    balance DECIMAL(10, 2) NOT NULL DEFAULT 0.00
);

CREATE TABLE operations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(50) NOT NULL,
    cost DECIMAL(10, 2) NOT NULL
);

CREATE TABLE records (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    operation_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    user_balance DECIMAL(10, 2) NOT NULL,
    operation_response VARCHAR(255) NOT NULL,
    date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    active BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_record_operation FOREIGN KEY (operation_id) REFERENCES operations(id),
    CONSTRAINT fk_record_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE INDEX idx_user_username ON users (username);
CREATE INDEX idx_record_user_id ON records (user_id);
CREATE INDEX idx_record_operation_id ON records (operation_id);
