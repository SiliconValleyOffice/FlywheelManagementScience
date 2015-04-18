# Introduction #

FseDocumentSectionHistory is one of 5 Document Sections in an FSE (Flywheel Story Editor) Document.

The History section keeps track of all changes to an FSE Document, supporting the following functionality:
  * rollback
  * history reconstruction
  * community member participation
  * lifecycle analysis

# Collaborators #

|FseDocument|an aggregation of FseDocumentSection objects, including FseDocumentSectionHistory|
|:----------|:--------------------------------------------------------------------------------|
|FseDocumentSection| super class for all document sections|
|FseDocumentSectionHistory|the "model" class which contains the collection of FseDocumentTransaction objects.  Extends FseDocumentSection.  serializes and de-serializes the document section|
|FseDocumentSectionView| super class for all document section views|
|FseDocumentSectionHistoryView|the GUI for displaying  FseDocumentSectionHistory.  Extends FseDocumentSectionView and manages the document section tabs for the History section.|
|FseHistoryTableView|manages the table view and creates transaction rows as needed|
|FseDocumentTransaction| the "model" for an atomic document transaction.  serializes and de-serializes the transaction|
|FseDocumentTransactionView| the GUI for displaying a FseDocumentTransaction row|
|FseDocumentTransactionType|CREATE, MODIFY, LOCK, UNLOCK, ROLLBACK|
|FseDocumentBodyAudit|Count how many paragraphs in the document body (Story or Notes) were: new, modified, deleted, unchanged, locked, or unlocked |