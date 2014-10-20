CREATE TABLE `StrategicCommitment` (
  `_id` CHAR(40) NOT NULL ,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0 ,
  `StrategicAsset__id` CHAR(40) NOT NULL UNIQUE ,
  `StrategicMilestone__id` CHAR(40) NOT NULL ,
  `sequence` INT(11) NOT NULL ,
  `completion_commitment_type` CHAR(9) NOT NULL ,
  `suggested_timestamp` BIGINT(14) NULL ,
  `suggested_by` CHAR(40) NULL ,
  `proposed_by` CHAR(40) NULL ,
  `proposed_timestamp` BIGINT(14) NULL ,
  `confirmed_by` CHAR(40) NULL ,
  `confirmed_timestamp` BIGINT(14) NULL ,
  `declined_by` CHAR(40) NULL ,
  `declined_timestamp` BIGINT(14) NULL ,
  `canceled_by` CHAR(40) NULL ,
  `canceled_timestamp` BIGINT(14) NULL ,
  PRIMARY KEY (`_id`) ,
  CONSTRAINT `fk_DeliveryCommitment_CommunityMember`
    FOREIGN KEY (`proposed_by` )
    REFERENCES `CommunityMember` (`_id` ) ,
  CONSTRAINT `fk_DeliveryCommitment_CommunityMember2`
    FOREIGN KEY (`confirmed_by` )
    REFERENCES `CommunityMember` (`_id` ) ,
  CONSTRAINT `fk_DeliveryCommitment_CommunityMember3`
    FOREIGN KEY (`declined_by` )
    REFERENCES `CommunityMember` (`_id` ) ,
  CONSTRAINT `fk_DeliveryCommitment_CommunityMember4`
    FOREIGN KEY (`canceled_by` )
    REFERENCES `CommunityMember` (`_id` ) ,
  CONSTRAINT `fk_DeliveryCommitment_DeliveryCommitmentType`
    FOREIGN KEY (`completion_commitment_type` )
    REFERENCES `CompletionCommitmentType` (`completion_commitment_type` ) ,
  CONSTRAINT `fk_MilestoneCommitment_Milestone`
    FOREIGN KEY (`StrategicMilestone__id` )
    REFERENCES `StrategicMilestone` (`_id` ) ,
  CONSTRAINT `fk_StrategicCommitment_ProjectAsset`
    FOREIGN KEY (`StrategicAsset__id` )
    REFERENCES `ProjectAsset` (`_id` ) );
    