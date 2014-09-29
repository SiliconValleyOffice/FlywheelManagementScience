CREATE TABLE `FiscalYearHolidayBreak` (
  `_id` CHAR(40) NOT NULL,
  `row_timestamp` BIGINT(14) NULL,
  `headline` VARCHAR(256) NOT NULL,
  `FiscalYear__id` CHAR(40) NOT NULL,
  `holiday_date` BIGINT(14) NOT NULL,
  `break_start_date` BIGINT(14) NOT NULL,
  `break_end_date` BIGINT(14) NOT NULL,
  PRIMARY KEY (`_id`),
  CONSTRAINT `fk_FiscalYearHolidayBreak_FiscalYear`
    FOREIGN KEY (`FiscalYear__id`)
    REFERENCES `FiscalYear` (`_id`) );