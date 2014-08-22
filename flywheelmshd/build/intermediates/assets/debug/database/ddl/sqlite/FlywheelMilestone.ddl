CREATE TABLE `FlywheelMilestone` (
  `_id` CHAR(40) NOT NULL ,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0 ,
  `headline` VARCHAR(256) NOT NULL ,
  `FiscalYear__id` CHAR(40) NOT NULL ,
  `sequence` INT(11) NULL ,
  `scheduled_start_date` BIGINT(14) NOT NULL DEFAULT 0 ,
  `scheduled_end_date` BIGINT(14) NOT NULL DEFAULT 0 ,
  `NodeFragWorkTaskBudget__id` CHAR(40) NULL ,
  `NodeFragCompletion__id` CHAR(40) NULL ,
  PRIMARY KEY (`_id`) ,
  CONSTRAINT `fk_Sprint_FiscalYear1`
    FOREIGN KEY (`FiscalYear__id` )
    REFERENCES `FiscalYear` (`_id` ) ,
  CONSTRAINT `fk_FlywheelMilestone_NodeFragWorkTaskBudget1`
    FOREIGN KEY (`NodeFragWorkTaskBudget__id` )
    REFERENCES `NodeFragWorkTaskBudget` (`_id` ) ,
  CONSTRAINT `fk_FlywheelMilestone_NodeFragCompletion1`
    FOREIGN KEY (`NodeFragCompletion__id` )
    REFERENCES `NodeFragCompletion` (`_id` ) );
