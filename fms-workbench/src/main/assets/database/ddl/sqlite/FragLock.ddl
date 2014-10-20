CREATE TABLE `FragLock` (
  `_id` CHAR(40) NOT NULL ,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0 ,
  `parent_id` CHAR(40) NOT NULL UNIQUE ,
  `parent_node_type_code` CHAR(3) NOT NULL ,
  `grandparent_id` CHAR(40) NOT NULL ,
  `grandparent_node_type_code` CHAR(3) NOT NULL ,
  `locked` TINYINT NULL ,
  `locked_by` CHAR(40) NULL ,
  `locked_timestamp` BIGINT(14) NULL ,
  `unlocked_by` CHAR(40) NULL ,
  `unlocked_timestamp` BIGINT(14) NULL ,
  PRIMARY KEY (`_id`) ,
  CONSTRAINT `fk_NodeFrag_Lock_CommunityMember`
    FOREIGN KEY (`locked_by` )
    REFERENCES `CommunityMember` (`_id` ) ,
  CONSTRAINT `fk_NodeFragLock_FmmNodeDictionary`
    FOREIGN KEY (`parent_node_type_code` )
    REFERENCES `FmmNodeDefinition` (`node_type_code` ) ,
  CONSTRAINT `fk_NodeFragLock_CommunityMember`
    FOREIGN KEY (`unlocked_by` )
    REFERENCES `CommunityMember` (`_id` ) ,
  CONSTRAINT `fk_FragLock_FmmNodeDictionary`
    FOREIGN KEY (`grandparent_node_type_code` )
    REFERENCES `FmmNodeDefinition` (`node_type_code` ) );
    