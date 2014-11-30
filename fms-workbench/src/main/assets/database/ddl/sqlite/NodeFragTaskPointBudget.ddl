CREATE TABLE `NodeFragTaskPointBudget` (
  `_id` CHAR(40) NOT NULL,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0,
  `parent_id` CHAR(40) NOT NULL,
  `parent_node_type_code` CHAR(3) NOT NULL,
  `estimated_task_points` INT NULL,
  `estimated_average_hours_per_task_point` FLOAT NULL,
  `estimate_by` CHAR(40) NULL,
  `estimate_timestamp` BIGINT(14) NULL,
  `estimate_data_quality` CHAR(9) NULL,
  `budgeted_task_points` INT NULL,
  `budgeted_average_hours_per_task_point` FLOAT NULL,
  `budget_by` CHAR(40) NULL,
  `budget_timestamp` BIGINT(14) NULL,
  `budget_data_quality` CHAR(9) NULL,
  `work_breakdown_estimated_task_points` INT NULL,
  `work_breakdown_estimated_average_hours_per_task_point` FLOAT NULL,
  `work_breakdown_budgeted_task_points` INT NULL,
  `work_breakdown_budgeted_average_hours_per_task_point` FLOAT NULL,
  `completed_task_points` INT NULL,
  `completed_average_hours_per_task_point` FLOAT NULL,
  `serialized_history` TEXT NULL,
  PRIMARY KEY (`_id`),
  CONSTRAINT `fk_NodeFragTaskPointBudget_FmmNodeDictionary1`
    FOREIGN KEY (`parent_node_type_code`)
    REFERENCES `FmmNodeDefinition` (`node_type_code`),
  CONSTRAINT `fk_NodeFragTaskPointBudget_CommunityMember1`
    FOREIGN KEY (`estimate_by`)
    REFERENCES `CommunityMember` (`_id`),
  CONSTRAINT `fk_NodeFragTaskPointBudget_CommunityMember2`
    FOREIGN KEY (`budget_by`)
    REFERENCES `CommunityMember` (`_id`),
  CONSTRAINT `fk_NodeFragTaskPointBudget_CommunityMember3`
    FOREIGN KEY (`budget_data_quality`)
    REFERENCES `CommunityMember` (`_id`),
  CONSTRAINT `fk_NodeFragTaskPointBudget_CommunityMember4`
    FOREIGN KEY (`estimate_data_quality`)
    REFERENCES `CommunityMember` (`_id`) );