CREATE TABLE `TribKnQualityEnumeration` (
  `quality_enumeration` INT(11) NOT NULL ,
  `tribkn_element` CHAR(20) NOT NULL ,
  `tribkn_quality_normalized_description` CHAR(48) NOT NULL ,
  `description` VARCHAR(128) NOT NULL ,
  PRIMARY KEY (`quality_enumeration`) ,
  CONSTRAINT `fk_NodeQualityDecorator_NodeQualityShortDescription1`
    FOREIGN KEY (`tribkn_quality_normalized_description` )
    REFERENCES `TribKnQualityNormalizedDescription` (`tribkn_quality_normalized_description` ) ,
  CONSTRAINT `fk_NodeQualityDecorator_TribKnComponent1`
    FOREIGN KEY (`tribkn_element` )
    REFERENCES `TribKnElement` (`tribkn_element` ) );
    