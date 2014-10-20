CREATE TABLE `GovernanceTarget` (
  `governance_target` CHAR(40) NOT NULL ,
  `sponsor_requirement` CHAR(8) NOT NULL ,
  `facilitator_requirement` CHAR(8) NOT NULL ,
  `customer_requirement` CHAR(8) NOT NULL ,
  `administrator_requirement` CHAR(8) NOT NULL ,
  `auto_completable` TINYINT NOT NULL ,
  PRIMARY KEY (`governance_target`) ,
  CONSTRAINT `fk_GovernanceTarget_GovernanceParticipationType`
    FOREIGN KEY (`sponsor_requirement` )
    REFERENCES `GovernanceParticipationType` (`governance_participation_type` ) ,
  CONSTRAINT `fk_GovernanceTarget_GovernanceParticipationType2`
    FOREIGN KEY (`facilitator_requirement` )
    REFERENCES `GovernanceParticipationType` (`governance_participation_type` ) ,
  CONSTRAINT `fk_GovernanceTarget_GovernanceParticipationType3`
    FOREIGN KEY (`customer_requirement` )
    REFERENCES `GovernanceParticipationType` (`governance_participation_type` ) ,
  CONSTRAINT `fk_GovernanceTarget_GovernanceParticipationType4`
    FOREIGN KEY (`administrator_requirement` )
    REFERENCES `GovernanceParticipationType` (`governance_participation_type` ) ,
  CONSTRAINT `fk_GovernanceTarget_FmmNode`
    FOREIGN KEY (`governance_target` )
    REFERENCES `FmmNodeDefinition` (`node_name` ) );
