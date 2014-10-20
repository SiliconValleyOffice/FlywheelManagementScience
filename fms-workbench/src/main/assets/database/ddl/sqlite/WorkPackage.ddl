CREATE TABLE `WorkPackage` (
  `_id` CHAR(40) NOT NULL ,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0 ,
  `headline` VARCHAR(256) NOT NULL ,
  `ProjectAsset__id` CHAR(40) NULL ,
  `sequence` INT(11) NULL ,
  `FlywheelCommitment__id` CHAR(40) NULL ,
  PRIMARY KEY (`_id`) ,
  CONSTRAINT `fk_WorkPackage_FlywheelCommitment1`
    FOREIGN KEY (`FlywheelCommitment__id` )
    REFERENCES `CadenceWorkPackageCommitment` (`_id` ) ,
  CONSTRAINT `fk_WorkPackage_ProjectAsset1`
    FOREIGN KEY (`ProjectAsset__id` )
    REFERENCES `ProjectAsset` (`_id` ) );
