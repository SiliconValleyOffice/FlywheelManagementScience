CREATE TABLE `Portfolio` (
  `_id` CHAR(40) NOT NULL ,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0 ,
  `headline` VARCHAR(256) NOT NULL ,
  `Organization__id` CHAR(40) NOT NULL ,
PRIMARY KEY (`_id`) ,
CONSTRAINT `fk_Portfolio_Organization`
  FOREIGN KEY (`Organization__id`)
  REFERENCES `FmsOrganization` (`_id`) );
