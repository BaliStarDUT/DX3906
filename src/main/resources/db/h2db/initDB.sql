--DROP TABLE heros IF EXISTS; 
CREATE TABLE IF NOT EXISTS heros(
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
--DROP TABLE position IF EXISTS; 
CREATE TABLE IF NOT EXISTS position (
  sid int(11) NOT NULL AUTO_INCREMENT,
  latitude double DEFAULT NULL,
  longitude double DEFAULT NULL,
  accuracy double DEFAULT NULL,
  altitude double DEFAULT NULL,
  altitudeAccuracy double DEFAULT NULL,
  heading double DEFAULT NULL,
  speed double DEFAULT NULL,
  timestamp datetime DEFAULT NULL,
  PRIMARY KEY (sid),
  UNIQUE KEY sid (sid) USING BTREE
); 
--ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 
