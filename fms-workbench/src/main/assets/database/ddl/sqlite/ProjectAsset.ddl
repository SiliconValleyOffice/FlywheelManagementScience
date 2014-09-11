CREATE TABLE `ProjectAsset` (
  `_id` CHAR(40) NOT NULL ,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0 ,
  `headline` VARCHAR(256) NOT NULL ,
  `Project__id` CHAR(40) NULL ,
  `sequence` INT(11) NULL ,
  PRIMARY KEY (`_id`) ,
  CONSTRAINT `fk_ProjectAsset_Project1`
    FOREIGN KEY (`Project__id` )
    REFERENCES `Project` (`_id` ) )