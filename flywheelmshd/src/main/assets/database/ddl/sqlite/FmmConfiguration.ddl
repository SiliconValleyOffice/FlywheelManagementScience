CREATE TABLE `FmmConfiguration` (
  `_id` CHAR(40) NOT NULL ,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0 ,
  `headline` VARCHAR(256) NOT NULL UNIQUE,
  `Organization__id` CHAR(40) NULL ,
  `for_this_fmm` TINYINT(1) NOT NULL ,
  `last_sync_datetime` BIGINT(14) NULL ,
  `access_scope` TEXT(7) NOT NULL ,
  `db_version` INT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (`_id`) ,
  CONSTRAINT `fk_FmmConfiguration_FmsOrganization1`
    FOREIGN KEY (`Organization__id` )
    REFERENCES `FmsOrganization` (`_id` ) );
    