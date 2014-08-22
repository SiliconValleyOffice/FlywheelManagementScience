CREATE TABLE `LockType` (
  `lock_type` CHAR(25) NOT NULL ,
  `description` VARCHAR(128) NOT NULL ,
  `may_resequence` TINYINT NULL ,
  `may_change_headline` TINYINT NULL ,
  PRIMARY KEY (`lock_type`) );
