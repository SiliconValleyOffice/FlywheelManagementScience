CREATE TABLE `OrganizationLockConfig` (
  `FmsOrganization__id` CHAR(40) NOT NULL ,
  `fmm_node_name` CHAR(40) NOT NULL ,
  `lock_type` CHAR(25) NOT NULL ,
  `sponsor_can_lock` TINYINT NULL ,
  `facilitator_can_lock` TINYINT NULL ,
  `customer_can_lock` TINYINT NULL ,
  `administrator_can_lock` TINYINT NULL ,
  PRIMARY KEY (`FmsOrganization__id`, `fmm_node_name`, `lock_type`) ,
  CONSTRAINT `fk_Organization_LockConfig_Organization`
    FOREIGN KEY (`FmsOrganization__id` )
    REFERENCES `Organization` (`_id` ) ,
  CONSTRAINT `fk_OrganizationLinkToLockConfig_LockConfig`
    FOREIGN KEY (`fmm_node_name` , `lock_type` )
    REFERENCES `LockConfig` (`fmm_node_name` , `lock_type` ) );
