CREATE TABLE IF NOT EXISTS USERAF(
  `userID` INTEGER NOT NULL AUTO_INCREMENT,
  `fullname` varchar(255) NOT NULL,
  `birthdate` DATE  NOT NULL,
  `country` varchar(255) NOT NULL,
  `gender` char (1) ,
  `phone` int(11) ,
  PRIMARY KEY (`userID`)
);

INSERT INTO USERAF (birthdate, country, gender, fullname, phone ) VALUES
  ('1994-05-01','FRANCE', 'F', 'John Petrucci', 65842156),
  ('2022-02-01','BELGIUM', 'M', 'John Mayer', 444444),
  ('1974-05-01','CANADA', 'M', 'John Frusciante', 65842156),
  ('1984-02-10','USA', 'M', 'John Lennon', 65842156),
  ('1962-07-01','FRANCE', 'M', 'Johnny Walker', 01020304);