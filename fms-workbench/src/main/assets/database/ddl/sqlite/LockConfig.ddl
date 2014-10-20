CREATE TABLE `LockConfig` (
  `fmm_node_name` CHAR(18) NOT NULL ,
  `lock_type` CHAR(25) NOT NULL ,
  `sponsor_can_lock` TINYINT NULL ,
  `facilitator_can_lock` TINYINT NULL ,
  `customer_can_lock` TINYINT NULL ,
  `administrator_can_lock` TINYINT NULL ,
  PRIMARY KEY (`fmm_node_name`, `lock_type`) ,
  CONSTRAINT `fk_LockConfig_FmmNode`
    FOREIGN KEY (`fmm_node_name` )
    REFERENCES `FmmNodeDefinition` (`node_name` ) );
    