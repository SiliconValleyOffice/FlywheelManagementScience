CREATE TABLE `WorkBudget` (
  `_id` CHAR(40) NOT NULL ,
  `row_timestamp` BIGINT(14) NOT NULL ,
  `target_node__id` CHAR(40) NOT NULL ,
  `budgeted_work_packages` INT NULL ,
  `budgeted_work_tasks` INT NULL ,
  `budgeted_average_work_task_size` INT NULL ,
  `budget_proposed_by` CHAR(40) NULL ,
  `budget_proposed_datetime` BIGINT(14) NULL ,
  `budget_approved_by` CHAR(40) NULL ,
  `budget_approved_datetime` BIGINT(14) NULL ,
  `completion_actual_work_tasks` INT NULL ,
  `completion_actual_work_packages_` INT NULL ,
  `completion_actual_person_hours` INT NULL ,
  `completion_average_work_task_size` INT NULL ,
  PRIMARY KEY (`_id`) ,
  CONSTRAINT `fk_WorkBudget_CommunityMember1`
    FOREIGN KEY (`budget_proposed_by` )
    REFERENCES `CommunityMember` (`_id` ) ,
  CONSTRAINT `fk_WorkBudget_CommunityMember2`
    FOREIGN KEY (`budget_approved_by` )
    REFERENCES `CommunityMember` (`_id` ) );
