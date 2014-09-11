CREATE TABLE `OrganizationGovernanceTarget` (
  `Organization__id` CHAR(40) NOT NULL ,
  `governance_target` CHAR(40) NOT NULL ,
  `sponsor_requirement` CHAR(8) NOT NULL ,
  `facilitator_requirement` CHAR(8) NOT NULL ,
  `customer_requirement` CHAR(8) NOT NULL ,
  `administrator_requirement` CHAR(8) NOT NULL ,
  PRIMARY KEY (`Organization__id`, `governance_target`) ,
  CONSTRAINT `fk_Organization_GovernanceTarget_Organization1`
    FOREIGN KEY (`Organization__id` )
    REFERENCES `Organization` (`_id` ) ,
  CONSTRAINT `fk_Organization_GovernanceTarget_GovernanceParticipationType1`
    FOREIGN KEY (`sponsor_requirement` )
    REFERENCES `GovernanceParticipationType` (`governance_participation_type` ) ,
  CONSTRAINT `fk_Organization_GovernanceTarget_GovernanceParticipationType2`
    FOREIGN KEY (`facilitator_requirement` )
    REFERENCES `GovernanceParticipationType` (`governance_participation_type` ) ,
  CONSTRAINT `fk_Organization_GovernanceTarget_GovernanceParticipationType3`
    FOREIGN KEY (`customer_requirement` )
    REFERENCES `GovernanceParticipationType` (`governance_participation_type` ) ,
  CONSTRAINT `fk_Organization_GovernanceTarget_GovernanceParticipationType4`
    FOREIGN KEY (`administrator_requirement` )
    REFERENCES `GovernanceParticipationType` (`governance_participation_type` ) ,
  CONSTRAINT `fk_OrganizationLinkToGovernanceTarget_GovernanceTarget1`
    FOREIGN KEY (`governance_target` )
    REFERENCES `GovernanceTarget` (`governance_target` ) );
