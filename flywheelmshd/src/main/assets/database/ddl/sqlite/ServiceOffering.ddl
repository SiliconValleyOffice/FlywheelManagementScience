CREATE TABLE `ServiceOffering` (
  `_id` CHAR(40) NOT NULL ,
  `headline` VARCHAR(256) NOT NULL ,
  `default_sla_max_service_requests` INT NULL ,
  `default_sla_max_service_triages` INT NULL ,
  `provide_triage_services` TINYINT NULL ,
  PRIMARY KEY (`_id`) );
