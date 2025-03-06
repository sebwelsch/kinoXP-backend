CREATE DATABASE kinoxp_db;
USE kinoxp_db;

-- Brugere (kun biografens personale)
CREATE TABLE IF NOT EXISTS users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Biografer
CREATE TABLE IF NOT EXISTS cinema (
    cinema_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    location VARCHAR(255) NOT NULL
);

-- Biografsale
CREATE TABLE IF NOT EXISTS theater_halls (
    hall_id INT PRIMARY KEY AUTO_INCREMENT,
    cinema_id INT NOT NULL,
    name VARCHAR(50) NOT NULL,
    seats INT NOT NULL,
    rows INT NOT NULL,
    FOREIGN KEY (cinema_id) REFERENCES cinema(cinema_id) ON DELETE CASCADE
);

-- Film
CREATE TABLE IF NOT EXISTS movies (
    movie_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    age_rating INT NOT NULL,
    category VARCHAR(50),
    actors VARCHAR(255),
    duration INT NOT NULL,
    cover_image VARCHAR(255)
);

-- Forestillinger
CREATE  IF NOT EXISTS shows (
    show_id INT PRIMARY KEY AUTO_INCREMENT,
    movie_id INT NOT NULL,
    hall_id INT NOT NULL,
    start_date VARCHAR(20) NOT NULL,
    end_date VARCHAR(20) NOT NULL,
    time JSON NOT NULL DEFAULT ('["13:00", "17:00", "22:00"]'),
    FOREIGN KEY (movie_id) REFERENCES movies(movie_id) ON DELETE CASCADE,
    FOREIGN KEY (hall_id) REFERENCES theater_halls(hall_id) ON DELETE CASCADE
);

-- Bookinger
CREATE TABLE IF NOT EXISTS bookings (
    booking_id INT PRIMARY KEY AUTO_INCREMENT,
    show_id INT NOT NULL,
    time VARCHAR(10) NOT NULL,
    seats INT NOT NULL,
    date VARCHAR(20) NOT NULL,
    customer_name VARCHAR(100) NOT NULL,
    customer_email VARCHAR(100) NOT NULL,
    FOREIGN KEY (show_id) REFERENCES shows(show_id) ON DELETE CASCADE
);
