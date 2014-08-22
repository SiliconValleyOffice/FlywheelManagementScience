CREATE TABLE `ServiceRequestLinkToWorkTask` (
  `ServiceRequest__id` CHAR(40) NOT NULL ,
  `WorkTask__id` CHAR(40) NOT NULL ,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0 ,
  `sequence` INT(11) NULL ,
  PRIMARY KEY (`ServiceRequest__id`, `WorkTask__id`) ,
  CONSTRAINT `fk_ServiceRequest_has_WorkTask_ServiceRequest1`
    FOREIGN KEY (`ServiceRequest__id` )
    REFERENCES `ServiceRequest` (`_id` ) ,
  CONSTRAINT `fk_ServiceRequest_has_WorkTask_WorkTask1`
    FOREIGN KEY (`WorkTask__id` )
    REFERENCES `WorkTask` (`_id` ) );
