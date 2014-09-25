CREATE TABLE `WorkPlan` (
  `_id` INT NOT NULL ,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0 ,
  `headline` VARCHAR(256) NOT NULL ,
  `FlywheelMilestone__id` CHAR(40) NOT NULL ,
  `sequence` INT(11) NULL ,
  `scheduled_start_date` BIGINT(14) NULL ,
  `scheduled_end_date` BIGINT(14) NULL ,
  PRIMARY KEY (`_id`) ,
  CONSTRAINT `fk_WorkPlan_Milestone`
    FOREIGN KEY (`FlywheelMilestone__id` )
    REFERENCES `FlywheelMilestone` (`_id` ) );
    