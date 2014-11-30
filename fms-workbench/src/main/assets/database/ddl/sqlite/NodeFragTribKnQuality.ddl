CREATE TABLE `NodeFragTribKnQuality` (
  `_id` CHAR(40) NOT NULL,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0,
  `parent_id` CHAR(40) NOT NULL,
  `parent_node_type_code` CHAR(3) NOT NULL,
  `governance_quality` INT(11) NOT NULL,
  `governance_quality_timestamp` BIGINT(14) NOT NULL,
  `facilitation_issue_quality` INT(11) NOT NULL,
  `facilitation_issue_quality_timestamp` BIGINT(14) NOT NULL,
  `facilitator_quality` INT(11) NOT NULL,
  `facilitator_quality_timestamp` BIGINT(14) NOT NULL,
  `parent_fractals_quality` INT(11) NOT NULL,
  `parent_fractals_quality_timestamp` BIGINT(14) NOT NULL,
  `child_fractals_quality` INT(11) NOT NULL,
  `child_fractals_quality_timestamp` BIGINT(14) NOT NULL,
  `cadence_commitment_quality` INT(11) NOT NULL,
  `cadence_commitment_quality_timestamp` BIGINT(14) NOT NULL,
  `task_points_budget_quality` INT(11) NOT NULL,
  `task_points_budget_quality_timestamp` BIGINT(14) NOT NULL,
  `work_team_quality` INT(11) NOT NULL,
  `work_team_quality_timestamp` BIGINT(14) NOT NULL,
  `strategic_commitment_quality` INT(11) NOT NULL,
  `strategic_commitment_quality_timestamp` BIGINT(14) NOT NULL,
  `story_quality` INT(11) NOT NULL,
  `story_quality_timestamp` BIGINT(14) NOT NULL,
  `completion_quality` INT(11) NOT NULL,
  `completion_quality_timestamp` BIGINT(14) NOT NULL,
  `sequence_quality` INT(11) NOT NULL,
  `sequence_quality_timestamp` BIGINT(14) NOT NULL,
  PRIMARY KEY (`_id`),
  CONSTRAINT `fk_NodeQuality_NodeQualityDecorator1`
    FOREIGN KEY (`facilitation_issue_quality`)
    REFERENCES `TribKnQualityEnumeration` (`quality_enumeration`) ,
  CONSTRAINT `fk_NodeQuality_NodeQualityDecorator2`
    FOREIGN KEY (`governance_quality`)
    REFERENCES `TribKnQualityEnumeration` (`quality_enumeration`) ,
  CONSTRAINT `fk_NodeQuality_NodeQualityDecorator3`
    FOREIGN KEY (`story_quality`)
    REFERENCES `TribKnQualityEnumeration` (`quality_enumeration`) ,
  CONSTRAINT `fk_NodeQuality_NodeQualityDecorator4`
    FOREIGN KEY (`child_fractals_quality`)
    REFERENCES `TribKnQualityEnumeration` (`quality_enumeration`) ,
  CONSTRAINT `fk_NodeQuality_NodeQualityDecorator5`
    FOREIGN KEY (`work_team_quality`)
    REFERENCES `TribKnQualityEnumeration` (`quality_enumeration`) ,
  CONSTRAINT `fk_NodeQuality_NodeQualityDecorator6`
    FOREIGN KEY (`strategic_commitment_quality`)
    REFERENCES `TribKnQualityEnumeration` (`quality_enumeration`) ,
  CONSTRAINT `fk_NodeQuality_NodeQualityDecorator7`
    FOREIGN KEY (`parent_fractals_quality`)
    REFERENCES `TribKnQualityEnumeration` (`quality_enumeration`) ,
  CONSTRAINT `fk_NodeQuality_NodeQualityDecorator8`
    FOREIGN KEY (`completion_quality`)
    REFERENCES `TribKnQualityEnumeration` (`quality_enumeration`) ,
  CONSTRAINT `fk_NodeQuality_NodeQualityDecorator9`
    FOREIGN KEY (`task_points_budget_quality`)
    REFERENCES `TribKnQualityEnumeration` (`quality_enumeration`) ,
  CONSTRAINT `fk_NodeFragTribKnQuality_TribKnQualityEnumeration1`
    FOREIGN KEY (`cadence_commitment_quality`)
    REFERENCES `TribKnQualityEnumeration` (`quality_enumeration`) ,
  CONSTRAINT `fk_NodeFragTribKnQuality_TribKnQualityEnumeration2`
    FOREIGN KEY (`facilitator_quality`)
    REFERENCES `TribKnQualityEnumeration` (`quality_enumeration`) ,
  CONSTRAINT `fk_NodeFragTribKnQuality_FmmNodeDictionary1`
    FOREIGN KEY (`parent_node_type_code`)
    REFERENCES `FmmNodeDefinition` (`node_type_code`) ,
  CONSTRAINT `fk_NodeFragTribKnQuality_TribKnQualityEnumeration3`
    FOREIGN KEY (`sequence_quality`)
    REFERENCES `TribKnQualityEnumeration` (`quality_enumeration`) );