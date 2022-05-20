DROP TABLE IF EXISTS quotes;

CREATE table quotes (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  age INT NOT NULL,
  zip VARCHAR(5) NOT NULL,
  dob DATE NOT NULL,
  quote Decimal(5,2)


);