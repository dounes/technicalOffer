DROP TABLE IF EXISTS USERAF;

CREATE TABLE USERAF(
   userID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(100) NOT NULL,
   countryOfResidence VARCHAR(100) NOT NULL,
   birthdate VARCHAR(100) NOT NULL,
   phoneNumber INT,
   gender CHAR(1)
);