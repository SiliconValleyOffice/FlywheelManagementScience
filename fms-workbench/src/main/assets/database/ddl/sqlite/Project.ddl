CREATE TABLE `Project` (
  `_id` CHAR(40) NOT NULL ,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0 ,
  `headline` VARCHAR(256) NOT NULL ,
  `Portfolio__id` CHAR(40) NULL ,
PRIMARY KEY (`_id`),
CONSTRAINT `fk_Project_Portfolio`
 FOREIGN KEY (`Portfolio__id`)
 REFERENCES `Portfolio` (`_id`) );
