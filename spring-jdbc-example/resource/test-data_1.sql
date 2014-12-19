-- #######################################
-- Copyright © Kaustuv Maji , 2014
-- Repos - https://github.com/kaustuvmaji
-- Blog -  http://kaustuvmaji.blogspot.in
-- #######################################

DROP TABLE if exists `TEST`.`Member` ;

COMMIT;

CREATE TABLE `TEST`.`Member` (
   ID   INT NOT NULL AUTO_INCREMENT,
   NAME VARCHAR(20) NOT NULL,
   AGE  INT NOT NULL,
   PRIMARY KEY (ID)
);

COMMIT;

SELECT * FROM `TEST`.`Member` ;

DELIMITER //

DROP PROCEDURE IF EXISTS `TEST`.`fetchRecord` //
CREATE PROCEDURE `TEST`.`fetchRecord` (
IN in_id INTEGER,
OUT out_name VARCHAR(20),
OUT out_age  INTEGER)
BEGIN
   SELECT NAME, age
   INTO out_name, out_age
   FROM Member WHERE id = in_id;
END //

DELIMITER ;

COMMIT;