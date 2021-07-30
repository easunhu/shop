DROP TABLE IF EXISTS Item;
CREATE TABLE Item (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  description VARCHAR(250) NOT NULL,
  price INT NOT NULL,
  remaining INT NOT NULL
);

INSERT INTO Item (name, description, price, remaining) VALUES
  ('Alpha', 'Alpha is the best', 100, 10),
  ('Bill', 'Bill for food', 200, 20),
  ('Cat', 'Cat need this', 300, 30);

