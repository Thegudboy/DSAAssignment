create database  SocialMedia;
use SocialMedia;
-- User Table:

CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,  -- Assuming username should be unique
    password VARCHAR(255) NOT NULL,
    INDEX idx_username (username)  -- Adding an index on username
);

-- CurrentUser Table:

CREATE TABLE currentuser (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Connection Table:

CREATE TABLE connection (
    id INT AUTO_INCREMENT PRIMARY KEY,
    follower_username VARCHAR(255) NOT NULL,
    following_username VARCHAR(255) NOT NULL,
    FOREIGN KEY (follower_username) REFERENCES user(username),
    FOREIGN KEY (following_username) REFERENCES user(username)
);

-- Post Table:

CREATE TABLE post (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    discription TEXT,
    likeCount INT DEFAULT 0,
    FOREIGN KEY (username) REFERENCES user(username)
);
