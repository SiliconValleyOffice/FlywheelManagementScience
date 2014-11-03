CREATE TABLE `PdfPublication` (
  `_id` CHAR(40) NOT NULL,
  `row_timestamp` BIGINT(14) NOT NULL,
  `CommunityMember__id` CHAR(40) NOT NULL,
  `headline_node__id` CHAR(40) NOT NULL,
  `headline_node_type_code` CHAR(3) NOT NULL,
  `content_summary` TEXT NOT NULL,
  `destination_summary_1` TEXT NOT NULL,
  `destination_summary_2` TEXT NOT NULL,
  PRIMARY KEY (`_id`),
  CONSTRAINT `fk_FmmEvent_CommunityMember1`
  FOREIGN KEY (`CommunityMember__id`)
  REFERENCES `CommunityMember` (`_id`) ,
  CONSTRAINT `fk_PdfPublication_FmmNodeDictionary1`
  FOREIGN KEY (`headline_node_type_code`)
  REFERENCES `FmmNodeDefinition` (`node_type_code`) );
