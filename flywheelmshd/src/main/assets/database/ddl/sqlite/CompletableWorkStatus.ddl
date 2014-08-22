CREATE TABLE `CompletableWorkStatus` (
  `work_status` CHAR(23) NOT NULL ,
  `name` CHAR(27) NULL ,
  `description` VARCHAR(128) NOT NULL ,
  `color` CHAR(18) NULL ,
  PRIMARY KEY (`work_status`) );
  