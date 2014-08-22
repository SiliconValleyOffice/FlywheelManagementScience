CREATE TABLE `FacilitationIssueNodeTarget` (
  `FacilitationIssue__id` CHAR(40) NOT NULL ,
  `FmmNode__id` CHAR(40) NOT NULL ,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0 ,
  `sequence` INT(11) NULL ,
  PRIMARY KEY (`FacilitationIssue__id`, `FmmNode__id`) ,
  CONSTRAINT `fk_FacilitationIssue_has_WorkTask_FacilitationIssue1`
    FOREIGN KEY (`FacilitationIssue__id` )
    REFERENCES `FacilitationIssue` (`_id` ) );
