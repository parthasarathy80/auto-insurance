DROP TABLE IF EXISTS quotes;

CREATE table quotes (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  zip VARCHAR(5) NOT NULL,
  dob date NOT NULL,
  quote Decimal(10,2),
  createdTime TIMESTAMP DEFAULT now()
);



