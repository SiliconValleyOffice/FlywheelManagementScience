CREATE TABLE `NodeFragCompletion` (
  `_id` CHAR(40) NOT NULL ,
  `row_timestamp` BIGINT(14) NULL ,
  `parent_id` CHAR(40) NOT NULL UNIQUE ,
  `parent_node_type_code` CHAR(3) NOT NULL ,
  `auto_completable` TINYINT NULL ,
  `work_status` CHAR(23) NOT NULL ,
  `yellow_by` CHAR(40) NULL ,
  `yellow_timestamp` BIGINT(14) NULL ,
  `orange_by` CHAR(40) NULL ,
  `orange_timestamp` BIGINT(14) NULL ,
  `pink_by` CHAR(40) NULL ,
  `pink_timestamp` BIGINT(14) NULL ,
  `green_by` CHAR(40) NULL ,
  `green_timestamp` BIGINT(14) NULL ,
  `completion_confirmed_by` CHAR(40) NULL ,
  `completion_confirmed_timestamp` BIGINT(14) NULL ,
  PRIMARY KEY (`_id`) ,
  CONSTRAINT `fk_NodeFragWorkStatus_WorkStatus1`
    FOREIGN KEY (`work_status` )
    REFERENCES `CompletableWorkStatus` (`work_status` ) ,
  CONSTRAINT `fk_NodeFragCompletion_FmmNodeDictionary1`
    FOREIGN KEY (`parent_node_type_code` )
    REFERENCES `FmmNodeDefinition` (`node_type_code` ) ,
  CONSTRAINT `fk_NodeFragCompletion_CommunityMember1`
    FOREIGN KEY (`yellow_by` )
    REFERENCES `CommunityMember` (`_id` ) ,
  CONSTRAINT `fk_NodeFragCompletion_CommunityMember2`
    FOREIGN KEY (`orange_by` )
    REFERENCES `CommunityMember` (`_id` ) ,
  CONSTRAINT `fk_NodeFragCompletion_CommunityMember3`
    FOREIGN KEY (`pink_by` )
    REFERENCES `CommunityMember` (`_id` ) ,
  CONSTRAINT `fk_NodeFragCompletion_CommunityMember4`
    FOREIGN KEY (`green_by` )
    REFERENCES `CommunityMember` (`_id` ) );
    