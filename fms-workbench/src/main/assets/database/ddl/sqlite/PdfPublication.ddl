CREATE TABLE `PdfPublication` (
  `_id` CHAR(40) NOT NULL ,
  `row_timestamp` BIGINT(14) NULL ,
  `CommunityMember__id` CHAR(40) NOT NULL ,
  `target_node__id` CHAR(40) NOT NULL ,
  `target_node_type_code` CHAR(3) NOT NULL ,
  `content_summary` TEXT NOT NULL ,
  `destination_summary_` TEXT NOT NULL ,
  `destination_summary_2` TEXT NOT NULL ,
  PRIMARY KEY (`_id`) ,
  CONSTRAINT `fk_FmmEvent_CommunityMember`
    FOREIGN KEY (`CommunityMember__id` )
    REFERENCES `CommunityMember` (`_id` ) ,
  CONSTRAINT `fk_PdfPublication_FmmNodeDictionary`
    FOREIGN KEY (`target_node_type_code` )
    REFERENCES `FmmNodeDefinition` (`node_type_code` ) );
    