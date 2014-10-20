CREATE TABLE `NodeFragAuditBlock` (
  `_id` CHAR(40) NOT NULL ,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0 ,
  `parent_id` CHAR(40) NOT NULL ,
  `parent_node_type_code` CHAR(3) NOT NULL ,
  `parent_abbreviated_node_id` TEXT(9) NOT NULL ,
  `headline` VARCHAR(256) NOT NULL ,
  `iteration` INT NULL DEFAULT 0 ,
  `created_by` CHAR(40) NOT NULL ,
  `created_timestamp` BIGINT(14) NOT NULL ,
  `last_updated_by` CHAR(40) NOT NULL ,
  `serialized_node_history` TEXT NULL ,
  PRIMARY KEY (`_id`) ,
  CONSTRAINT `fk_NodeStatus_CommunityMember`
    FOREIGN KEY (`last_updated_by` )
    REFERENCES `CommunityMember` (`_id` ) ,
  CONSTRAINT `fk_NodeStatus_CommunityMember2`
    FOREIGN KEY (`created_by` )
    REFERENCES `CommunityMember` (`_id` ) ,
  CONSTRAINT `fk_NodeFragAuditBlock_FmmNodeDictionary`
    FOREIGN KEY (`parent_node_type_code` )
    REFERENCES `FmmNodeDefinition` (`node_type_code` ) );
    