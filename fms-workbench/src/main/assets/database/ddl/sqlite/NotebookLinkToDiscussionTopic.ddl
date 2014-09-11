CREATE TABLE `NotebookLinkToDiscussionTopic` (
  `Notebook__id` CHAR(40) NOT NULL ,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0 ,
  `DiscussionTopic__id` CHAR(40) NOT NULL ,
  `sequence` INT(11) NULL ,
  PRIMARY KEY (`Notebook__id`, `DiscussionTopic__id`) ,
  CONSTRAINT `fk_Notebook_has_DiscussionTopic_Notebook1`
    FOREIGN KEY (`Notebook__id` )
    REFERENCES `Notebook` (`_id` ) ,
  CONSTRAINT `fk_Notebook_has_DiscussionTopic_DiscussionTopic1`
    FOREIGN KEY (`DiscussionTopic__id` )
    REFERENCES `DiscussionTopic` (`_id` ) );
