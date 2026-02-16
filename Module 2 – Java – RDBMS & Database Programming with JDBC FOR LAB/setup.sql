CREATE DATABASE IF NOT EXISTS studentdb;
USE studentdb;

-- Table for Student
CREATE TABLE IF NOT EXISTS students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    fname VARCHAR(50),
    lname VARCHAR(50),
    email VARCHAR(100)
);

-- Insert some dummy data for Lab 1
INSERT INTO students (fname, lname, email) VALUES 
('John', 'Doe', 'john.doe@example.com'),
('Jane', 'Smith', 'jane.smith@example.com'),
('Alice', 'Johnson', 'alice.j@example.com');

-- Stored Procedure for Lab 4
DELIMITER //

CREATE PROCEDURE get_student_name(IN student_id INT, OUT full_name VARCHAR(100))
BEGIN
    SELECT CONCAT(fname, ' ', lname) INTO full_name
    FROM students
    WHERE id = student_id;
END //

DELIMITER ;
