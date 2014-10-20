CREATE TABLE `ServiceRequest` (
  `_id` CHAR(40) NOT NULL ,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0 ,
  `headline` VARCHAR(256) NOT NULL ,
  `ServiceOffering__id` CHAR(40) NOT NULL ,
  `auto_completable` TINYINT NULL ,
  `work_status` CHAR(23) NOT NULL ,
  PRIMARY KEY (`_id`) ,
  CONSTRAINT `fk_ServiceRequest_ServiceOffering`
    FOREIGN KEY (`ServiceOffering__id` )
    REFERENCES `ServiceOffering` (`_id` ) ,
  CONSTRAINT `fk_ServiceRequest_WorkStatus`
    FOREIGN KEY (`work_status` )
    REFERENCES `CompletableWorkStatus` (`work_status` ) );
