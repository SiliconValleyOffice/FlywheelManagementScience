CREATE TABLE `DiscussionTopicLinkToNodeFragAuditBlock` (
  `id` CHAR(40) NOT NULL,
  `DiscussionTopic__id` CHAR(40) NOT NULL,
  `NodeFragAuditBlock__id` CHAR(40) NOT NULL,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0,
  `sequence` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_DiscussionTopicLinkToNodeFragAuditBlock_DiscussionTopic1`
    FOREIGN KEY (`DiscussionTopic__id`)
    REFERENCES `DiscussionTopic` (`_id`)
  CONSTRAINT `fk_DiscussionTopicLinkToNodeFragAuditBlock_NodeFragAuditBlock1`
    FOREIGN KEY (`NodeFragAuditBlock__id`)
    REFERENCES `NodeFragAuditBlock` (`_id`) );
