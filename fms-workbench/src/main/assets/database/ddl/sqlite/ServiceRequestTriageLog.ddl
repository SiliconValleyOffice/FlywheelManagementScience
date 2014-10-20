CREATE TABLE `ServiceRequestTriageLog` (
  `TacticalMilestone__id` CHAR(40) NOT NULL ,
  `ServiceOffering__id` CHAR(40) NOT NULL ,
  `WorkTask__id` CHAR(40) NOT NULL ,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`TacticalMilestone__id`, `ServiceOffering__id`, `WorkTask__id`) ,
  CONSTRAINT `fk_ServiceRequestTriagePackage_TacticalMilestone`
    FOREIGN KEY (`TacticalMilestone__id` )
    REFERENCES `TacticalMilestone` (`_id` ) ,
  CONSTRAINT `fk_ServiceRequestTriageLog_ServiceOffering`
    FOREIGN KEY (`ServiceOffering__id` )
    REFERENCES `ServiceOffering` (`_id` ) ,
  CONSTRAINT `fk_ServiceRequestTriageLog_WorkTask`
    FOREIGN KEY (`WorkTask__id` )
    REFERENCES `WorkTask` (`_id` ) );
