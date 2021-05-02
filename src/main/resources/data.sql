DROP TABLE IF EXISTS entry;

CREATE TABLE entry (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  words VARCHAR(250) NOT NULL
);

INSERT INTO entry (words) VALUES
  ('Word Words Wor word'),
  ('Word Word Word word'),
  ('This is a Notebook entry application');