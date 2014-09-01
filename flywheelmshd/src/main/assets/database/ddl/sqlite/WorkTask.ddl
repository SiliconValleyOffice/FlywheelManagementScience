CREATE TABLE `WorkTask` (
  `_id` CHAR(40) NOT NULL ,
  `row_timestamp` BIGINT(14) NOT NULL ,
  `headline` VARCHAR(256) NOT NULL ,
  `WorkPackage__id` CHAR(40) NOT NULL ,
  `sequence` INT(11) NULL ,
  `WorkPlan__id` INT NULL ,
  `work_plan_sequence` INT(11) NULL ,
  `budgeted_person_hours` INT NULL ,
  `actual_person_hours` INT NULL ,
  `work_status` CHAR(23) NOT NULL ,
  PRIMARY KEY (`_id`) ,
  CONSTRAINT `fk_WorkTask_WorkPlan1`
    FOREIGN KEY (`WorkPlan__id` )
    REFERENCES `WorkPlan` (`_id` ) ,
  CONSTRAINT `fk_WorkTask_CompletableWorkState1`
    FOREIGN KEY (`work_status` )
    REFERENCES `CompletableWorkStatus` (`work_status` ) ,
  CONSTRAINT `fk_WorkTask_WorkPackage1`
    FOREIGN KEY (`WorkPackage__id` )
    REFERENCES `WorkPackage` (`_id` ) );
