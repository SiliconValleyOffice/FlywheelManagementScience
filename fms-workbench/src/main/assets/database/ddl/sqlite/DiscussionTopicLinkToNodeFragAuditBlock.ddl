CREATE TABLE `DiscussionTopicLinkToNodeFragAuditBlock` (
  `DiscussionTopic__id` CHAR(40) NOT NULL ,
  `NodeFragAuditBlock__id` CHAR(40) NOT NULL ,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`DiscussionTopic__id`, `NodeFragAuditBlock__id`) ,
  CONSTRAINT `fk_DiscussionTopicLinkToNodeFragAuditBlock_DiscussionTopic`
    FOREIGN KEY (`DiscussionTopic__id` )
    REFERENCES `DiscussionTopic` (`_id` ) ,
  CONSTRAINT `fk_DiscussionTopicLinkToNodeFragAuditBlock_NodeFragAuditBlock`
    FOREIGN KEY (`NodeFragAuditBlock__id` )
    REFERENCES `NodeFragAuditBlock` (`_id` ) );
