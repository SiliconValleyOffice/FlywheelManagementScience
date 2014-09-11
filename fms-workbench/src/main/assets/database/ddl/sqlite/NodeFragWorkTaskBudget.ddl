CREATE TABLE `NodeFragWorkTaskBudget` (
  `_id` CHAR(40) NOT NULL ,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0 ,
  `parent_id` CHAR(40) NOT NULL UNIQUE ,
  `parent_node_type_code` CHAR(3) NOT NULL ,
  `estimated_total_task_count` INT NULL ,
  `estimated_average_hours_per_task` FLOAT NULL ,
  `swag_by` CHAR(40) NULL ,
  `swag_timestamp` BIGINT(14) NULL ,
  `estimate_confirmed_by` CHAR(40) NULL ,
  `estimate_confirmed_timestamp` BIGINT(14) NULL ,
  `budgeted_total_task_count` INT NULL ,
  `budgeted_average_hours_per_task` FLOAT NULL ,
  `budget_proposed_by` CHAR(40) NULL ,
  `budget_proposed_timestamp` BIGINT(14) NULL ,
  `budget_confirmed_by` CHAR(40) NULL ,
  `budget_confirmed_timestamp` BIGINT(14) NULL ,
  `work_breakdown_total_task_count` INT NULL ,
  `work_breakdown_average_hours_per_task` FLOAT NULL ,
  `task_count_after_completion` INT NULL ,
  `average_hours_per_task_after_completion` FLOAT NULL ,
  PRIMARY KEY (`_id`) ,
  CONSTRAINT `fk_NodeFragWorkTaskBudget_FmmNodeDictionary1`
    FOREIGN KEY (`parent_node_type_code` )
    REFERENCES `FmmNodeDefinition` (`node_type_code` ) ,
  CONSTRAINT `fk_NodeFragWorkTaskBudget_CommunityMember1`
    FOREIGN KEY (`swag_by` )
    REFERENCES `CommunityMember` (`_id` ) ,
  CONSTRAINT `fk_NodeFragWorkTaskBudget_CommunityMember2`
    FOREIGN KEY (`budget_proposed_by` )
    REFERENCES `CommunityMember` (`_id` ) ,
  CONSTRAINT `fk_NodeFragWorkTaskBudget_CommunityMember3`
    FOREIGN KEY (`budget_confirmed_by` )
    REFERENCES `CommunityMember` (`_id` ) ,
  CONSTRAINT `fk_NodeFragWorkTaskBudget_CommunityMember4`
    FOREIGN KEY (`estimate_confirmed_by` )
    REFERENCES `CommunityMember` (`_id` ) );
    