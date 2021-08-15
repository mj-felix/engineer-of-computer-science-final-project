CREATE USER 'portal_user'@'localhost' IDENTIFIED BY 'Morenike5';
GRANT SELECT, INSERT, UPDATE, DELETE ON *.* TO 'portal_user'@'localhost' IDENTIFIED BY 'Morenike5' WITH MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0 ;
GRANT ALL PRIVILEGES ON portal.* TO 'portal_user'@'localhost';

create database if not exists portal CHARACTER SET utf8 collate 'utf8_general_ci';
use portal;
SET foreign_key_checks = 0;
drop table if exists CATEGORY, DISHINGREDIENT, INGREDIENTS, USERACHIEVEMENT, ACHIEVEMENTS, USERREWARD, REWARDS, USERCOMMUNICATOR, COMMUNICATORS, DISHIMAGEUSER, RATES, COMMENTS, USERDISHTODO, DISHES, USERRANK, USERS, RANKS, IMAGES;
SET foreign_key_checks = 1;

create table if not exists IMAGES (
	ID integer unsigned not null AUTO_INCREMENT,
	IPATH varchar(255) not null,
	STATUS tinyint not null DEFAULT 0,
	ADDITIONDATE date not null,
	ADDITIONTIME time not null,
		
	PRIMARY KEY (ID)
) DEFAULT CHARSET=utf8;

create table if not exists USERS (
	ID integer unsigned not null AUTO_INCREMENT PRIMARY KEY,
	EMAIL varchar(255) not null,
	PASSWORD varchar(255) not null,
	NICK varchar(255) not null,
	DATEOFBIRTH date not null,
	PRIVELEGELEVEL tinyint unsigned not null DEFAULT 0,
	REGISTRATIONDATE date not null,
	REGISTRATIONTIME time not null,
	XPOINTS integer unsigned not null DEFAULT 0,
	XPOINTSREEDEMED integer unsigned not null DEFAULT 0,
	COUNTRY varchar(255),
	WWW varchar(255),
	SEX varchar(255),
	STATUS tinyint not null DEFAULT 0,
	ACTIVATIONKEY varchar(255) not null,
	
	IMAGE_ID integer unsigned,
	FOREIGN KEY (IMAGE_ID) REFERENCES IMAGES(ID)
) DEFAULT CHARSET=utf8;

create table if not exists RANKS (
	ID integer unsigned not null AUTO_INCREMENT PRIMARY KEY,
	NAME varchar(255) not null,
	DESCRIPTION text not null,
	REQUIREDPOINTS integer unsigned not null DEFAULT 0,
	
	IMAGE_ID integer unsigned,
	FOREIGN KEY (IMAGE_ID) REFERENCES IMAGES(ID)
) DEFAULT CHARSET=utf8;

create table if not exists USERRANK (
	USER_ID integer unsigned not null,
	RANK_ID integer unsigned not null,
	URDATE date not null,
	URTIME time not null,

	PRIMARY KEY (USER_ID, RANK_ID),
	FOREIGN KEY (USER_ID) REFERENCES USERS(ID),
	FOREIGN KEY (RANK_ID) REFERENCES RANKS(ID)
) DEFAULT CHARSET=utf8;

create table if not exists CATEGORIES (
	ID integer unsigned not null AUTO_INCREMENT PRIMARY KEY,
	NAME varchar(255) not null
) DEFAULT CHARSET=utf8;

create table if not exists DISHES (
	ID integer unsigned not null AUTO_INCREMENT PRIMARY KEY,
	NAME varchar(255) not null,
	DESCRIPTION text not null,
	AMOUNTOFCALORIES integer unsigned not null DEFAULT 0,
	PREPTIME integer unsigned not null DEFAULT 0,
	DIFICULTYLEVEL tinyint unsigned not null DEFAULT 0,
	DSIZE tinyint unsigned not null DEFAULT 0,
	AVERAGERATE DECIMAL( 3, 2 ) not null DEFAULT 0,
	ADDITIONDATE date not null,
	ADDITIONTIME time not null,
	STATUS tinyint unsigned not null DEFAULT 1,
	
	USER_ID integer unsigned not null,
	CATEGORY_ID integer unsigned not null,
	FOREIGN KEY (USER_ID) REFERENCES USERS(ID),
	FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORIES(ID)
) DEFAULT CHARSET=utf8;

create table if not exists USERDISHTODO (
	USER_ID integer unsigned not null,
	DISH_ID integer unsigned not null,
	
	PRIMARY KEY (USER_ID, DISH_ID),
	FOREIGN KEY (USER_ID) REFERENCES USERS(ID),
	FOREIGN KEY (DISH_ID) REFERENCES DISHES(ID)
) DEFAULT CHARSET=utf8;

create table if not exists COMMENTS (
	ID integer unsigned not null AUTO_INCREMENT,
	USER_ID integer unsigned not null,
	DISH_ID integer unsigned not null,
	CTEXT text not null,
	STATUS tinyint unsigned not null DEFAULT 1,
	CDATE date not null,
	CTIME time not null,
	
	PRIMARY KEY (ID, USER_ID, DISH_ID),
	FOREIGN KEY (USER_ID) REFERENCES USERS(ID),
	FOREIGN KEY (DISH_ID) REFERENCES DISHES(ID)
) DEFAULT CHARSET=utf8;

create table if not exists RATES (
	USER_ID integer unsigned not null,
	DISH_ID integer unsigned not null,
	RATE tinyint unsigned not null DEFAULT 0,
	RDATE date not null,
	RTIME time not null,
	
	PRIMARY KEY (USER_ID, DISH_ID),
	FOREIGN KEY (USER_ID) REFERENCES USERS(ID),
	FOREIGN KEY (DISH_ID) REFERENCES DISHES(ID)
) DEFAULT CHARSET=utf8;

create table if not exists DISHIMAGEUSER (
	USER_ID integer unsigned not null,
	DISH_ID integer unsigned not null,
	IMAGE_ID integer unsigned not null,
	
	PRIMARY KEY (USER_ID, DISH_ID, IMAGE_ID),
	FOREIGN KEY (USER_ID) REFERENCES USERS(ID),
	FOREIGN KEY (IMAGE_ID) REFERENCES IMAGES(ID),
	FOREIGN KEY (DISH_ID) REFERENCES DISHES(ID)
) DEFAULT CHARSET=utf8;

create table if not exists COMMUNICATORS (
	ID integer unsigned not null AUTO_INCREMENT PRIMARY KEY,
	NAME varchar(255) not null
) DEFAULT CHARSET=utf8;

create table if not exists USERCOMMNICATOR (
	USER_ID integer unsigned not null,
	COMMUNICATOR_ID integer unsigned not null,
	LOGIN varchar(255) not null,
	
	PRIMARY KEY (USER_ID, COMMUNICATOR_ID),
	FOREIGN KEY (USER_ID) REFERENCES USERS(ID),
	FOREIGN KEY (COMMUNICATOR_ID) REFERENCES COMMUNICATORS(ID)
) DEFAULT CHARSET=utf8;

create table if not exists REWARDS (
	ID integer unsigned not null AUTO_INCREMENT PRIMARY KEY,
	NAME varchar(255) not null,
	DESCRIPTION text not null,
	XPOINTSCOST integer unsigned not null DEFAULT 0
) DEFAULT CHARSET=utf8;

create table if not exists USERREWARD (
	ID integer unsigned not null AUTO_INCREMENT PRIMARY KEY,
	USER_ID integer unsigned not null,
	REWARD_ID integer unsigned not null,
	URDATE date not null,
	URTIME time not null,
	ADDRESS varchar(255) not null,
	
	FOREIGN KEY (USER_ID) REFERENCES USERS(ID),
	FOREIGN KEY (REWARD_ID) REFERENCES REWARDS(ID)
) DEFAULT CHARSET=utf8;

create table if not exists ACHIEVEMENTS (
	ID integer unsigned not null AUTO_INCREMENT PRIMARY KEY,
	NAME varchar(255) not null,
	DESCRIPTION text not null,
	XPOINTS integer unsigned not null DEFAULT 0
) DEFAULT CHARSET=utf8;

create table if not exists USERACHIEVEMENT (
	ID integer unsigned not null AUTO_INCREMENT PRIMARY KEY,
	USER_ID integer unsigned not null,
	ACHIEVEMENT_ID integer unsigned not null,
	UADATE date not null,
	UATIME time not null,
	
	FOREIGN KEY (USER_ID) REFERENCES USERS(ID),
	FOREIGN KEY (ACHIEVEMENT_ID) REFERENCES ACHIEVEMENTS(ID)
) DEFAULT CHARSET=utf8;

create table if not exists INGREDIENTS (
	ID integer unsigned not null AUTO_INCREMENT PRIMARY KEY,
	NAME varchar(255) not null,
	UNIT varchar(255) not null
) DEFAULT CHARSET=utf8;

create table if not exists DISHINGREDIENT (
	DISH_ID integer unsigned not null,
	INGREDIENT_ID integer unsigned not null,
	AMOUNT integer unsigned not null DEFAULT 1,
	
	PRIMARY KEY (DISH_ID, INGREDIENT_ID),
	FOREIGN KEY (DISH_ID) REFERENCES DISHES(ID),
	FOREIGN KEY (INGREDIENT_ID) REFERENCES INGREDIENTS(ID)
) DEFAULT CHARSET=utf8;

DROP TRIGGER IF EXISTS RATES_TRIGGER; 
DELIMITER //

CREATE TRIGGER RATES_TRIGGER AFTER INSERT ON RATES
FOR EACH ROW
BEGIN
UPDATE DISHES SET AVERAGERATE = (SELECT ROUND(AVG(RATE),2) FROM RATES WHERE DISH_ID=new.dish_id) WHERE ID = new.dish_id;
END;

//
DELIMITER ;

DROP TRIGGER IF EXISTS DISHES_TRIGGER;
DELIMITER //

CREATE TRIGGER DISHES_TRIGGER AFTER INSERT ON DISHES
FOR EACH ROW
BEGIN
INSERT INTO USERACHIEVEMENT SET USER_ID = new.USER_ID, ACHIEVEMENT_ID='1', UADATE=CURDATE(), UATIME=CURTIME();
END;

//
DELIMITER ;

DROP TRIGGER IF EXISTS USERACHIEVEMENT_TRIGGER;
DELIMITER //

CREATE TRIGGER USERACHIEVEMENT_TRIGGER AFTER INSERT ON USERACHIEVEMENT
FOR EACH ROW
BEGIN
DECLARE xp1 INT;
DECLARE xp2 INT;
SELECT XPOINTS INTO xp1 FROM ACHIEVEMENTS WHERE ID=new.ACHIEVEMENT_ID;

SELECT XPOINTS INTO xp2 FROM USERS WHERE ID=new.USER_ID;
UPDATE USERS SET XPOINTS=(xp1+xp2) WHERE ID=new.USER_ID;
END;

//
DELIMITER ;

DROP TRIGGER IF EXISTS IMAGES_TRIGGER;
DELIMITER //

CREATE TRIGGER IMAGES_TRIGGER AFTER UPDATE ON IMAGES
FOR EACH ROW
BEGIN
DECLARE x INT(10);
DECLARE c INT;
	IF NEW.status != OLD.status AND NEW.status = '1' THEN
		IF NEW.ipath LIKE '%avatar%' THEN
			SELECT ID INTO x FROM USERS WHERE IMAGE_ID=NEW.ID;	
			SELECT COUNT(*) INTO c FROM USERACHIEVEMENT WHERE USER_ID=x AND ACHIEVEMENT_ID='2';
			IF c=0 THEN
			INSERT INTO USERACHIEVEMENT SET USER_ID = x, ACHIEVEMENT_ID='2', UADATE=CURDATE(), UATIME=CURTIME();
			END IF;
		ELSE 
			SELECT USER_ID INTO x FROM DISHIMAGEUSER WHERE IMAGE_ID=NEW.ID;	
			INSERT INTO USERACHIEVEMENT SET USER_ID = x, ACHIEVEMENT_ID='3', UADATE=CURDATE(), UATIME=CURTIME();
		END IF;
		
	END IF;
END;

//
DELIMITER ;


DROP TRIGGER IF EXISTS USERS_TRIGGER;
DELIMITER //

CREATE TRIGGER USERS_TRIGGER AFTER UPDATE ON USERS
FOR EACH ROW
BEGIN
DECLARE x INT;
DECLARE Y INT(10);
	IF NEW.xpoints > OLD.xpoints THEN
		SELECT RANKS.ID INTO y FROM RANKS, USERRANK WHERE USERRANK.RANK_ID=RANKS.ID AND USERRANK.USER_ID=NEW.ID ORDER BY USERRANK.URDATE, USERRANK.URTIME DESC LIMIT 1;
		IF y<=3 THEN
			SELECT REQUIREDPOINTS INTO x FROM RANKS WHERE ID=(y+1);
			IF NEW.xpoints>=x THEN
				INSERT INTO USERRANK SET USER_ID = NEW.ID, RANK_ID=(y+1), URDATE=CURDATE(), URTIME=CURTIME();
			END IF;
		END IF;
	END IF;
	IF NEW.status != OLD.status AND NEW.status = '1' AND OLD.status != '-1' THEN
		INSERT INTO USERRANK SET USER_ID = NEW.ID, RANK_ID='1', URDATE=CURDATE(), URTIME=CURTIME();
	END IF;
END;

//
DELIMITER ;

DROP TRIGGER IF EXISTS USERREWARD_TRIGGER;
DELIMITER //

CREATE TRIGGER USERREWARD_TRIGGER AFTER INSERT ON USERREWARD
FOR EACH ROW
BEGIN
DECLARE xp1 INT;
DECLARE xp2 INT;
SELECT 	XPOINTSCOST INTO xp1 FROM REWARDS WHERE ID=new.REWARD_ID;

SELECT 	XPOINTSREEDEMED INTO xp2 FROM USERS WHERE ID=new.USER_ID;
UPDATE USERS SET XPOINTSREEDEMED=(xp1+xp2) WHERE ID=new.USER_ID;
END;

//
DELIMITER ;

INSERT INTO CATEGORIES (`ID`, `NAME`) VALUES
(1, 'Cat1'),
(2, 'Cat2'),
(3, 'Cat3'),
(4, 'Cat4');

INSERT INTO INGREDIENTS (`ID`, `NAME`, `UNIT`) VALUES
(1, 'Ingr1', 'szt'),
(2, 'Ingr2', 'dkg'),
(3, 'Ingr3', 'dkg'),
(4, 'Ingr4', 'szt'),
(5, 'Ingr5', 'szczypta'),
(6, 'Ingr6', 'dkg');

INSERT INTO IMAGES (`ID`, `IPATH`, `STATUS`, `ADDITIONDATE`, `ADDITIONTIME`) VALUES
(1, '/images/avatar/cook1.jpg', 1, '2011-12-08', '20:40:33'),
(2, '/images/avatar/cook2.jpg', 1, '2011-12-08', '20:41:59'),
(3, '/images/avatar/cook3.jpg', 1, '2011-12-08', '20:41:59'),
(4, '/images/avatar/cook4.jpg', 1, '2011-12-08', '20:42:25');

INSERT INTO RANKS (`ID`, `NAME`, `DESCRIPTION`, `REQUIREDPOINTS`, `IMAGE_ID`) VALUES
(1, 'Cook1', 'Cook1 description.', 0, 1),
(2, 'Cook2', 'Cook2 description.', 50, 2),
(3, 'Cook3', 'Cook3 description.', 100, 3),
(4, 'Cook4', 'Cook4 description.', 200, 4);

INSERT INTO REWARDS (`ID`, `NAME`, `DESCRIPTION`, `XPOINTSCOST`) VALUES
(1, 'Reward1', 'Reward1 description.', 40),
(2, 'Reward2', 'Reward2 description.', 200);

INSERT INTO ACHIEVEMENTS (`ID`, `NAME`, `DESCRIPTION`, `XPOINTS`) VALUES
(1, 'Dodanie potrawy/Adding dish', 'Dodanie potrawy jest warte 10 punktów doświadczenia.', 10),
(2, 'Dodanie zdjęcia profilowego/Adding profile photo', 'Dodanie zdjęcia profilowego jest warte 5 punktów doświadczenia. Naliczenie punktów odbywa sie po zaakceptowaniu zdjęcia przez administratora.', 5),
(3, 'Dodanie zdjęcia potrawy/Adding dish photo', 'Dodanie zdjęcia potrawy jest warte 5 punktów doświadczenia. Naliczenie punktów odbywa sie po zaakceptowaniu zdjęcia przez administratora.', 5);


INSERT INTO USERS (`ID`, `EMAIL`, `PASSWORD`, `NICK`, `DATEOFBIRTH`, `PRIVELEGELEVEL`, `REGISTRATIONDATE`, `REGISTRATIONTIME`, `XPOINTS`, `XPOINTSREEDEMED`, `COUNTRY`, `WWW`, `SEX`, `STATUS`, `ACTIVATIONKEY`, `IMAGE_ID`) VALUES
(1, 'mszonline@gmail.com', '1ee2951e642aa0e4c2951f8993789a72adf495c93173eda3e61d4069bb41ad43', 'mszonline', '1984-04-27', 1, '2011-12-11', '20:00:33', 0, 0, NULL, NULL, NULL, 0, 'eb786d8b', NULL);

UPDATE USERS SET STATUS='1';

INSERT INTO DISHES (`ID`, `NAME`, `DESCRIPTION`, `AMOUNTOFCALORIES`, `PREPTIME`, `DIFICULTYLEVEL`, `DSIZE`, `AVERAGERATE`, `ADDITIONDATE`, `ADDITIONTIME`, `STATUS`, `USER_ID`, `CATEGORY_ID`) VALUES
(1, 'Dish1', 'Lorem ipsum ....', 1000, 45, 1, 3, 0.00, '2011-12-18', '13:16:23', 1, 1, 1),
(2, 'Dish2', 'Lorem ipsum...', 400, 30, 2, 2, 0.00, '2011-12-18', '13:20:18', 1, 1, 2);

INSERT INTO DISHINGREDIENT (`DISH_ID`, `INGREDIENT_ID`, `AMOUNT`) VALUES
(1, 1, 1),
(1, 2, 1),
(2, 1, 1);