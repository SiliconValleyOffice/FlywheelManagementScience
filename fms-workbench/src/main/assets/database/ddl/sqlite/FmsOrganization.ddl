CREATE TABLE `FmsOrganization` (
  `_id` CHAR(40) NOT NULL ,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0 ,
  `headline` VARCHAR(256) NOT NULL UNIQUE,
  `authentication_url` VARCHAR(45) NULL ,
  `authentication_type` VARCHAR(45) NULL ,
  `first_month_of_fiscal_year` INT NULL,
  PRIMARY KEY (`_id`) );
