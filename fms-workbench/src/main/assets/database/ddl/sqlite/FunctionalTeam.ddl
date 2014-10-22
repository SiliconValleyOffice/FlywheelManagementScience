CREATE TABLE `FunctionalTeam` (
  `_id` CHAR(40) NOT NULL ,
  `headline` VARCHAR(256) NOT NULL ,
  `FmsOrganization__id` CHAR(40) NOT NULL ,
  PRIMARY KEY (`_id`) ,
  CONSTRAINT `fk_FunctionalTeam_Organization`
    FOREIGN KEY (`FmsOrganization__id` )
    REFERENCES `FmsOrganization` (`_id` ) );
