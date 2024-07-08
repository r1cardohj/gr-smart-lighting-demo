create table device (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    device_Code VARCHAR(255) NOT NULL,
    specifications VARCHAR(1024),
    position VARCHAR(512),
    lighting_type VARCHAR(50),
    is_online BOOLEAN DEFAULT FALSE,
    serial_number VARCHAR(255),
    charge_By VARCHAR(10),
    ex_factory_date DATE,
    expired_date DATE,
    created_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL
);
