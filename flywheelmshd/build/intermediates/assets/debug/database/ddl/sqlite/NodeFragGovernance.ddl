CREATE TABLE `NodeFragGovernance` (
  `_id` CHAR(40) NOT NULL ,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0 ,
  `parent_id` CHAR(40) NOT NULL UNIQUE ,
  `parent_node_type_code` CHAR(3) NOT NULL ,
  `governance_target` CHAR(18) NOT NULL ,
  `sponsor_id` CHAR(40) NULL ,
  `sponsor_commitment` CHAR(9) NULL ,
  `sponsor_commitment_timestamp` BIGINT(14) NULL ,
  `sponsor_proposed_by_id` CHAR(40) NULL ,
  `sponsor_proposed_timestamp` BIGINT(14) NULL ,
  `sponsor_locked_by_id` CHAR(40) NULL ,
  `sponsor_locked_timestamp` BIGINT(14) NULL ,
  `sponsor_satisfaction_score` INT NULL ,
  `facilitator_id` CHAR(40) NULL ,
  `facilitator_commitment` CHAR(9) NULL ,
  `facilitator_commitment_timestamp` BIGINT(14) NULL ,
  `facilitator_proposed_by_id` CHAR(40) NULL ,
  `facilitator_proposed_timestamp` BIGINT(14) NULL ,
  `facilitator_locked_by_id` CHAR(40) NULL ,
  `facilitator_locked_timestamp` BIGINT(14) NULL ,
  `facilitator_satisfaction_score` INT NULL ,
  `customer_id` CHAR(40) NULL ,
  `customer_commitment` CHAR(9) NULL ,
  `customer_commitment_timestamp` BIGINT(14) NULL ,
  `customer_proposed_by_id` CHAR(40) NULL ,
  `customer_proposed_timestamp` BIGINT(14) NULL ,
  `customer_locked_by_id` CHAR(40) NULL ,
  `customer_locked_timestamp` BIGINT(14) NULL ,
  `customer_satisfaction_score` INT NULL ,
  `administrator_id` CHAR(40) NULL ,
  `administrator_commitment` CHAR(9) NULL ,
  `administrator_commitment_timestamp` BIGINT(14) NULL ,
  `administrator_proposed_by_id` CHAR(40) NULL ,
  `administrator_proposed_timestamp` BIGINT(14) NULL ,
  `administrator_locked_by_id` CHAR(40) NULL ,
  `administrator_locked_timestamp` BIGINT(14) NULL ,
  `administrator_satisfaction_score` INT NULL ,
  PRIMARY KEY (`_id`) ,
  CONSTRAINT `fk_Governance_CommunityMember1`
    FOREIGN KEY (`sponsor_id` )
    REFERENCES `CommunityMember` (`_id` ) ,
  CONSTRAINT `fk_Governance_AssignmentCommitmentType1`
    FOREIGN KEY (`sponsor_commitment` )
    REFERENCES `AssignmentCommitmentType` (`assignment_commitment_type` ) ,
  CONSTRAINT `fk_Governance_CommunityMember2`
    FOREIGN KEY (`facilitator_id` )
    REFERENCES `CommunityMember` (`_id` ) ,
  CONSTRAINT `fk_Governance_AssignmentCommitmentType2`
    FOREIGN KEY (`facilitator_commitment` )
    REFERENCES `AssignmentCommitmentType` (`assignment_commitment_type` ) ,
  CONSTRAINT `fk_Governance_CommunityMember3`
    FOREIGN KEY (`customer_id` )
    REFERENCES `CommunityMember` (`_id` ) ,
  CONSTRAINT `fk_Governance_AssignmentCommitmentType3`
    FOREIGN KEY (`customer_commitment` )
    REFERENCES `AssignmentCommitmentType` (`assignment_commitment_type` ) ,
  CONSTRAINT `fk_Governance_CommunityMember4`
    FOREIGN KEY (`administrator_id` )
    REFERENCES `CommunityMember` (`_id` ) ,
  CONSTRAINT `fk_Governance_AssignmentCommitmentType4`
    FOREIGN KEY (`administrator_commitment` )
    REFERENCES `AssignmentCommitmentType` (`assignment_commitment_type` ) ,
  CONSTRAINT `fk_Governance_CommunityMember5`
    FOREIGN KEY (`sponsor_proposed_by_id` )
    REFERENCES `CommunityMember` (`_id` ) ,
  CONSTRAINT `fk_Governance_CommunityMember6`
    FOREIGN KEY (`facilitator_proposed_by_id` )
    REFERENCES `CommunityMember` (`_id` ) ,
  CONSTRAINT `fk_Governance_CommunityMember7`
    FOREIGN KEY (`customer_proposed_by_id` )
    REFERENCES `CommunityMember` (`_id` ) ,
  CONSTRAINT `fk_Governance_CommunityMember8`
    FOREIGN KEY (`administrator_proposed_by_id` )
    REFERENCES `CommunityMember` (`_id` ) ,
  CONSTRAINT `fk_NodeFragGovernance_GovernanceTarget1`
    FOREIGN KEY (`governance_target` )
    REFERENCES `GovernanceTarget` (`governance_target` ) ,
  CONSTRAINT `fk_NodeFragGovernance_FmmNodeDictionary1`
    FOREIGN KEY (`parent_node_type_code` )
    REFERENCES `FmmNodeDefinition` (`node_type_code` ) ,
  CONSTRAINT `fk_NodeFragGovernance_GovernanceSatisfaction1`
    FOREIGN KEY (`sponsor_satisfaction_score` )
    REFERENCES `GovernanceSatisfaction` (`score` ) ,
  CONSTRAINT `fk_NodeFragGovernance_GovernanceSatisfaction2`
    FOREIGN KEY (`customer_satisfaction_score` )
    REFERENCES `GovernanceSatisfaction` (`score` ) ,
  CONSTRAINT `fk_NodeFragGovernance_GovernanceSatisfaction3`
    FOREIGN KEY (`facilitator_satisfaction_score` )
    REFERENCES `GovernanceSatisfaction` (`score` ) ,
  CONSTRAINT `fk_NodeFragGovernance_GovernanceSatisfaction4`
    FOREIGN KEY (`administrator_satisfaction_score` )
    REFERENCES `GovernanceSatisfaction` (`score` ) );
    