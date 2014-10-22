CREATE TABLE `OrganizationGovernanceTarget` (
  `FmsOrganization__id` CHAR(40) NOT NULL ,
  `governance_target` CHAR(40) NOT NULL ,
  `sponsor_requirement` CHAR(8) NOT NULL ,
  `facilitator_requirement` CHAR(8) NOT NULL ,
  `customer_requirement` CHAR(8) NOT NULL ,
  `administrator_requirement` CHAR(8) NOT NULL ,
  PRIMARY KEY (`FmsOrganization__id`, `governance_target`) ,
  CONSTRAINT `fk_Organization_GovernanceTarget_Organization`
    FOREIGN KEY (`FmsOrganization__id` )
    REFERENCES `Organization` (`_id` ) ,
  CONSTRAINT `fk_Organization_GovernanceTarget_GovernanceParticipationType`
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
  CONSTRAINT `fk_OrganizationLinkToGovernanceTarget_GovernanceTarget`
    FOREIGN KEY (`governance_target` )
    REFERENCES `GovernanceTarget` (`governance_target` ) );
