CREATE TABLE `WorkTaskAssignment` (
  `CommunityMember__id` CHAR(40) NOT NULL ,
  `WorkTask__id` CHAR(40) NOT NULL ,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0 ,
  `FunctionalTeam__id` CHAR(40) NOT NULL ,
  `assignment_commitment_type` CHAR(9) NOT NULL ,
  `estimated_hours` INT NULL ,
  `actual_hours` INT NULL ,
  `work_status` CHAR(23) NOT NULL ,
  PRIMARY KEY (`CommunityMember__id`, `WorkTask__id`) ,
  CONSTRAINT `fk_CommunityMember_has_WorkTask_CommunityMember`
    FOREIGN KEY (`CommunityMember__id` )
    REFERENCES `CommunityMember` (`_id` ) ,
  CONSTRAINT `fk_CommunityMember_has_WorkTask_WorkTask`
    FOREIGN KEY (`WorkTask__id` )
    REFERENCES `WorkTask` (`_id` ) ,
  CONSTRAINT `fk_CommunityMember_has_WorkTask_FunctionalTeam`
    FOREIGN KEY (`FunctionalTeam__id` )
    REFERENCES `FunctionalTeam` (`_id` ) ,
  CONSTRAINT `fk_CommunityMember_has_WorkTask_AssignmentCommitmentType_enum`
    FOREIGN KEY (`assignment_commitment_type` )
    REFERENCES `AssignmentCommitmentType` (`assignment_commitment_type` ) ,
  CONSTRAINT `fk_WorkTaskAssignment_WorkStatus`
    FOREIGN KEY (`work_status` )
    REFERENCES `CompletableWorkStatus` (`work_status` ) );
