CREATE TABLE menu_items (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            category VARCHAR(100) NOT NULL,
                            price DECIMAL(10, 2) NOT NULL,
                            description VARCHAR(255)
);
