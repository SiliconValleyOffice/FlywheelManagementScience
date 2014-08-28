CREATE TABLE `FlywheelServiceDeliveryCommitment` (
  `ServiceRequest__id` CHAR(40) NOT NULL ,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0 ,
  `FlywheelMilestone__id` CHAR(40) NOT NULL ,
  `sequence` INT(11) NOT NULL ,
  `completion_commitment_type` CHAR(9) NOT NULL ,
  `suggested_by` CHAR(40) NULL ,
  `suggested_timestamp` BIGINT(14) NULL ,
  `proposed_by` CHAR(40) NULL ,
  `proposed_timestamp` BIGINT(14) NULL ,
  `confirmed_by` CHAR(40) NULL ,
  `confirmed_timestamp` BIGINT(14) NULL ,
  `declined_by` CHAR(40) NULL ,
  `declined_timestamp` BIGINT(14) NULL ,
  `canceled_by` CHAR(40) NULL ,
  `canceled_timestamp` BIGINT(14) NULL ,
  PRIMARY KEY (`ServiceRequest__id`) ,
  CONSTRAINT `fk_ServiceDeliveryCommitment_Sprint1`
    FOREIGN KEY (`FlywheelMilestone__id` )
    REFERENCES `FlywheelMilestone` (`_id` ) ,
  CONSTRAINT `fk_ServiceDeliveryCommitment_CompletionCommitmentType1`
    FOREIGN KEY (`completion_commitment_type` )
    REFERENCES `CompletionCommitmentType` (`completion_commitment_type` ) ,
  CONSTRAINT `fk_ServiceDeliveryCommitment_CommunityMember1`
    FOREIGN KEY (`proposed_by` )
    REFERENCES `CommunityMember` (`_id` ) ,
  CONSTRAINT `fk_ServiceDeliveryCommitment_CommunityMember2`
    FOREIGN KEY (`confirmed_by` )
    REFERENCES `CommunityMember` (`_id` ) ,
  CONSTRAINT `fk_ServiceDeliveryCommitment_CommunityMember3`
    FOREIGN KEY (`declined_by` )
    REFERENCES `CommunityMember` (`_id` ) ,
  CONSTRAINT `fk_ServiceDeliveryCommitment_CommunityMember4`
    FOREIGN KEY (`canceled_by` )
    REFERENCES `CommunityMember` (`_id` ) ,
  CONSTRAINT `fk_ServiceDeliveryCommitment_ServiceRequest1`
    FOREIGN KEY (`ServiceRequest__id` )
    REFERENCES `ServiceRequest` (`_id` ) );
    