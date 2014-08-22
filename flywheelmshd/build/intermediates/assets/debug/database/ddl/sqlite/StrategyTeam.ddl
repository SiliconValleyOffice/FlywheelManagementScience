CREATE TABLE `StrategyTeam` (
  `_id` CHAR(40) NOT NULL ,
  `headline` VARCHAR(256) NOT NULL ,
  `Organization__id` CHAR(40) NOT NULL ,
  PRIMARY KEY (`_id`) ,
  CONSTRAINT `fk_GovernanceTeam_Organization1`
    FOREIGN KEY (`Organization__id` )
    REFERENCES `FmsOrganization` (`_id` ) );
