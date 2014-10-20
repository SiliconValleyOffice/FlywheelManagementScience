CREATE TABLE `WorkPackage` (
  `_id` CHAR(40) NOT NULL ,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0 ,
  `headline` VARCHAR(256) NOT NULL ,
  `WorkAsset__id` CHAR(40) NULL ,
  `sequence` INT(11) NULL ,
  `CadenceCommitment__id` CHAR(40) NULL ,
  PRIMARY KEY (`_id`) ,
  CONSTRAINT `fk_WorkPackage_CadenceCommitment`
    FOREIGN KEY (`CadenceCommitment__id` )
    REFERENCES `CadenceWorkPackageCommitment` (`_id` ) ,
  CONSTRAINT `fk_WorkPackage_WorkAsset`
    FOREIGN KEY (`WorkAsset__id` )
    REFERENCES `WorkAsset` (`_id` ) );
