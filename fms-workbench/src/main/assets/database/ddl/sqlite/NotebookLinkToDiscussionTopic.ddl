CREATE TABLE `NotebookLinkToDiscussionTopic` (
  `_id` CHAR(40) NOT NULL,
  `Notebook__id` CHAR(40) NOT NULL,
  `DiscussionTopic__id` CHAR(40) NOT NULL,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0,
  `sequence` INT(11)  NOT NULL,
  PRIMARY KEY (`_id`),
  CONSTRAINT `fk_Notebook_has_DiscussionTopic_Notebook1`
    FOREIGN KEY (`Notebook__id`)
    REFERENCES `Notebook` (`_id`) ,
  CONSTRAINT `fk_Notebook_has_DiscussionTopic_DiscussionTopic1`
    FOREIGN KEY (`DiscussionTopic__id`)
    REFERENCES `DiscussionTopic` (`_id`) );
