CREATE TABLE `WorkPlan` (
  `_id` INT NOT NULL,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0,
  `headline` VARCHAR(256) NOT NULL,
  `Cadence__id` CHAR(40) NOT NULL,
  `sequence` INT(11) NULL,
  `scheduled_start_date` BIGINT(14) NULL,
  `scheduled_end_date` BIGINT(14) NULL,
  PRIMARY KEY (`_id`),
  CONSTRAINT `fk_WorkPlan_Cadence`
    FOREIGN KEY (`Cadence__id`)
    REFERENCES `Cadence` (`_id`) );