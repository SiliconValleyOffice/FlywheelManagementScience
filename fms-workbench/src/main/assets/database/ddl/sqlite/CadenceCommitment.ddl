CREATE TABLE `CadenceCommitment` (
  `_id` CHAR(40) NOT NULL,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0,
  `Cadence__id` CHAR(40) NOT NULL,
  `WorkPackage__id` CHAR(40) NOT NULL,
  `sequence` INT(11) NOT NULL,
  `completion_commitment_type` CHAR(9) NOT NULL,
  `suggested_by` CHAR(40) NULL,
  `suggested_timestamp` BIGINT(14) NULL,
  `proposed_by` CHAR(40) NULL,
  `proposed_timestamp` BIGINT(14) NULL,
  `confirmed_by` CHAR(40) NULL,
  `confirmed_timestamp` BIGINT(14) NULL,
  `declined_by` CHAR(40) NULL,
  `declined_timestamp` BIGINT(14) NULL,
  `canceled_by_id` CHAR(40) NULL,
  `canceled_datetime` BIGINT(14) NULL,
  PRIMARY KEY (`_id`),
  CONSTRAINT `fk_Commitment_Cadence`
    FOREIGN KEY (`Cadence__id`)
    REFERENCES `Cadence` (`_id`),
  CONSTRAINT `fk_Commitment_CompletionCommitmentType`
    FOREIGN KEY (`completion_commitment_type`)
    REFERENCES `CompletionCommitmentType` (`completion_commitment_type`),
  CONSTRAINT `fk_Commitment_CommunityMember_Proposed`
    FOREIGN KEY (`proposed_by`)
    REFERENCES `CommunityMember` (`_id`),
  CONSTRAINT `fk_Commitment_CommunityMember_Confirmed`
    FOREIGN KEY (`confirmed_by`)
    REFERENCES `CommunityMember` (`_id`),
  CONSTRAINT `fk_Commitment_CommunityMember_Declined`
    FOREIGN KEY (`declined_by`)
    REFERENCES `CommunityMember` (`_id`),
  CONSTRAINT `fk_Commitment_CommunityMember_Canceled`
    FOREIGN KEY (`canceled_by_id`)
    REFERENCES `CommunityMember` (`_id`),
  CONSTRAINT `fk_Commitment_WorkPackage`
    FOREIGN KEY (`WorkPackage__id`)
    REFERENCES `WorkPackage` (`_id`) );
    