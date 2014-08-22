CREATE TABLE `PortfolioLinkToProject` (
  `Portfolio__id` CHAR(40) NOT NULL ,
  `Project__id` CHAR(40) NOT NULL ,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`Portfolio__id`, `Project__id`) ,
  CONSTRAINT `fk_Portfolio_has_Project_Portfolio1`
    FOREIGN KEY (`Portfolio__id` )
    REFERENCES `Portfolio` (`_id` ) ,
  CONSTRAINT `fk_Portfolio_has_Project_Project1`
    FOREIGN KEY (`Project__id` )
    REFERENCES `Project` (`_id` ) );
