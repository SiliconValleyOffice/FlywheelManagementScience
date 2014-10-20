CREATE TABLE `WorkAsset` (
  `_id` CHAR(40) NOT NULL ,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0 ,
  `headline` VARCHAR(256) NOT NULL ,
  `Project__id` CHAR(40) NULL ,
  `sequence` INT(11) NULL ,
  `is_strategic` TINYINT NULL DEFAULT 0 ,
  PRIMARY KEY (`_id`) ,
  CONSTRAINT `fk_WorkAsset_Project`
    FOREIGN KEY (`Project__id` )
    REFERENCES `Project` (`_id` ) );
