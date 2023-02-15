CREATE TABLE book (Id int(255) NOT NULL PRIMARY KEY AUTO_INCREMENT, Title varchar(255), Category enum('fantasy', 'horror', 'action', 'science_fiction'), Author varchar(255), Pages int(255));
CREATE TABLE video (Id int(255) NOT NULL PRIMARY KEY AUTO_INCREMENT, Title varchar(255), Category enum('fantasy', 'horror', 'action', 'science_fiction'), Director varchar(255), Duration int(255));
CREATE TABLE game (Id int(255) NOT NULL PRIMARY KEY AUTO_INCREMENT, Title varchar(255), Category enum('fantasy', 'horror', 'action', 'science_fiction'), Developer varchar(255), Metascore int(255));

SELECT * FROM book;
INSERT INTO book (Id, Title, Category, Author, Pages) VALUE (2, 'ThisBook', 'horror', 'AnAuthor', 400); 
INSERT INTO video (Id, Title, Category, Director, Duration) VALUE (1, 'ThisMovie', 'action', 'ADirector', 650);
SELECT * FROM video;
SELECT user FROM mysql;
SELECT * FROM book WHERE 'Id = 3';
SELECT * FROM book WHERE TITLE LIKE '%2%';
SELECT * FROM book WHERE Category LIKE 'action';

DROP TABLE book;
DROP TABLE game;
DROP TABLE video;

CREATE TABLE person (Id int(255) NOT NULL PRIMARY KEY AUTO_INCREMENT, Name varchar(255));
CREATE TABLE book (Id int(255) NOT NULL PRIMARY KEY AUTO_INCREMENT, Title varchar(255), Category enum('fantasy', 'horror', 'action', 'science_fiction', 'other'), Author int(255), Pages int(255), FOREIGN KEY (Author) REFERENCES Person(Id));
CREATE TABLE video (Id int(255) NOT NULL PRIMARY KEY AUTO_INCREMENT, Title varchar(255), Category enum('fantasy', 'horror', 'action', 'science_fiction', 'other'), Director int(255), Duration int(255), FOREIGN KEY (Director) REFERENCES Person(Id));
CREATE TABLE game (Id int(255) NOT NULL PRIMARY KEY AUTO_INCREMENT, Title varchar(255), Category enum('fantasy', 'horror', 'action', 'science_fiction', 'other'), Developer int(255), Metascore int(255), FOREIGN KEY (Developer) REFERENCES Person(Id));

INSERT INTO person (Id, Name) VALUE (5, 'Bob');
INSERT INTO book (Id, Title, Category, Author, Pages) VALUE (2, 'Another book', 'action', 4, 500);
INSERT INTO game (Id, Title, Category, Developer, Metascore) VALUE (4, 'A game', 'science_fiction', 4, 80);
INSERT INTO video (Id, Title, Category, Director, Duration) VALUE (100, 'Please delete me', 'action', 3, 9090);

DELETE FROM person WHERE Id = '5';
DELETE FROM book WHERE Id = 2;

SELECT * FROM person;
SELECT * FROM book;
SELECT * FROM game;
SELECT * FROM video;