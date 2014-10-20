CREATE TABLE `FacilitationIssue` (
  `_id` CHAR(40) NOT NULL ,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0 ,
  `issue_origination_node` CHAR(40) NOT NULL ,
  `node_quality_metric` CHAR(20) NOT NULL ,
  `headline` VARCHAR(256) NOT NULL ,
  `issue_status` CHAR(9) NOT NULL ,
  `proposed_by_id` CHAR(40) NOT NULL ,
  `proposed_datetime` BIGINT(14) NULL ,
  `confirmed_by_id` CHAR(40) NOT NULL ,
  `confirmed_datetime` BIGINT(14) NULL ,
  `defered_by_id` CHAR(40) NOT NULL ,
  `defered_datetime` BIGINT(14) NULL ,
  `resolved_by_id` CHAR(40) NOT NULL ,
  `resolved_datetime` BIGINT(14) NULL ,
  PRIMARY KEY (`_id`) ,
  CONSTRAINT `fk_FacilitationIssue_IssueStatus`
    FOREIGN KEY (`issue_status` )
    REFERENCES `FacilitationIssueStatus` (`issue_status` ) ,
  CONSTRAINT `fk_FacilitationIssue_CommunityMember`
    FOREIGN KEY (`proposed_by_id` )
    REFERENCES `CommunityMember` (`_id` ) ,
  CONSTRAINT `fk_FacilitationIssue_CommunityMember2`
    FOREIGN KEY (`confirmed_by_id` )
    REFERENCES `CommunityMember` (`_id` ) ,
  CONSTRAINT `fk_FacilitationIssue_CommunityMember3`
    FOREIGN KEY (`defered_by_id` )
    REFERENCES `CommunityMember` (`_id` ) ,
  CONSTRAINT `fk_FacilitationIssue_CommunityMember4`
    FOREIGN KEY (`resolved_by_id` )
    REFERENCES `CommunityMember` (`_id` ) ,
  CONSTRAINT `fk_FacilitationIssue_NodeQualityMetric_enum`
    FOREIGN KEY (`node_quality_metric` )
    REFERENCES `TribKnElement` (`tribkn_element` ) );
