DROP DATABASE IF EXISTS guessthenumber;
CREATE DATABASE guessthenumber;

USE guessthenumber;

CREATE TABLE game(
GameId INT PRIMARY KEY AUTO_INCREMENT,
UserName VARCHAR(50) NOT NULL,
GameStatus Varchar(30) Not null
);

CREATE TABLE gameround(
RoundId INT PRIMARY KEY AUTO_INCREMENT,
UserGuess int not null,
Result varchar(20) not null,
Time datetime not null
);

ALTER TABLE gameround
ADD COLUMN
GameId int NOT NULL,
Add foreign key fk_game_gameround(GameId)
references game(GameId);

desc game;
desc gameround;