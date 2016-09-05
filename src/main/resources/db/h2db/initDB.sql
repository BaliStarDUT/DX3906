DROP TABLE heros IF EXISTS; 
CREATE TABLE heros(
  id         INTEGER IDENTITY PRIMARY KEY,
	nickname	VARCHAR(20),
	name_en		VARCHAR(20),
	name_cn		VARCHAR(20),
	type		VARCHAR(20),
	story		VARCHAR(255),
	headpic_url VARCHAR(30),
	headpic		BLOB,
	sounds_url	VARCHAR(30),
	sounds		BLOB
);
--CREATE INDEX nicknameIndex ON heros (nickname);
