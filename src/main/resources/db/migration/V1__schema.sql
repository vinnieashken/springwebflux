CREATE TABLE IF NOT EXISTS Product (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    code VARCHAR(255),
    price DOUBLE,
    created_at DATETIME,
    updated_at DATETIME,
    PRIMARY KEY (id)
    );
