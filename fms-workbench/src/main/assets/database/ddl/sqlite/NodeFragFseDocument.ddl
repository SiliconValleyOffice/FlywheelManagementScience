CREATE TABLE `NodeFragFseDocument` (
  `_id` CHAR(40) NOT NULL ,
  `row_timestamp` BIGINT(14) NOT NULL DEFAULT 0 ,
  `parent_id` CHAR(40) NOT NULL UNIQUE ,
  `parent_node_type_code` CHAR(3) NOT NULL ,
  `document_id` CHAR(40) NOT NULL ,
  `document_transaction_type` TEXT(12) NOT NULL ,
  `serialized_document` TEXT NOT NULL ,
  PRIMARY KEY (`_id`) ,
  CONSTRAINT `fk_FseDocument_FmmNodeDictionary`
    FOREIGN KEY (`parent_node_type_code` )
    REFERENCES `FmmNodeDefinition` (`node_type_code` ) ,
  CONSTRAINT `fk_FseDocument_FseDocumentTransactionType`
    FOREIGN KEY (`document_transaction_type` )
    REFERENCES `FseDocumentTransactionType` (`document_transaction_type` ) );
    