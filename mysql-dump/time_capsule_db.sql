

CREATE DATABASE UserDatabase;
USE UserDatabase;

CREATE TABLE Users (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Username VARCHAR(255) NOT NULL,
    Password VARCHAR(255) NOT NULL
);
CREATE DATABASE time_capsule_db;
use time_capsule_db;
CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- ID column, auto-incremented
    email VARCHAR(255) NOT NULL UNIQUE,    -- Email column, non-null and unique
    password VARCHAR(255) NOT NULL         -- Password column, non-null
);
CREATE TABLE time_capsule (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,            -- TimeCapsule ID, auto-incremented
    user_id BIGINT NOT NULL,                         -- Foreign key referencing the User table
    encrypted_message VARCHAR(255),                  -- Encrypted message column
    CONSTRAINT fk_user FOREIGN KEY (user_id)        -- Foreign key constraint
        REFERENCES user(id)                         -- Refers to the id column in the user table
                      -- Delete related time_capsules if the user is deleted
);
SELECT user, host, plugin FROM mysql.user WHERE user = 'root';
SELECT * FROM user;
