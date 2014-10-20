CREATE TABLE `ServiceOfferingSla` (
  `TacticalMilestone__id` CHAR(40) NOT NULL ,
  `ServiceOffering__id` CHAR(40) NOT NULL ,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0 ,
  `max_service_requests` INT NOT NULL ,
  `max_service_triage` INT NOT NULL ,
  PRIMARY KEY (`TacticalMilestone__id`, `ServiceOffering__id`) ,
  CONSTRAINT `fk_Sprint_has_ServiceOffering_Sprint`
    FOREIGN KEY (`TacticalMilestone__id` )
    REFERENCES `TacticalMilestone` (`_id` ) ,
  CONSTRAINT `fk_Sprint_has_ServiceOffering_ServiceOffering`
    FOREIGN KEY (`ServiceOffering__id` )
    REFERENCES `ServiceOffering` (`_id` ) );
