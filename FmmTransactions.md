#Overview of FmmTransaction collaborating classes.

# Introduction #

All changes to the FMM by FMS are made inside an FmmTransaction.

## Collaborating Classes ##

  * TransactionId
  * FmmTransactionDictionary (enum)
  * FmmNodeDictionary (enum)
  * NodeFragAuditBlock
  * TransactionLog
  * TransactionDetail
  * TransactionDetailType (enum)
  * FmmTransaction (interface)
  * FmmTransactionAbstraction (abstract)
  * FmmTransactionMediator

## Collaborating Database Tables ##

  * NodeFragAuditBlock
  * TransactionLog
  * FmmTransactionDictionary
  * TransactionType
  * TransactionCollaborator
  * FmmNodeDictionary
  * TransactionDetail
  * TransactionDetailType

# TransactionId #

3 character NodeTypeCode of principle participant
"-"
3 character TransactionType

# FmmTransactionDictionary #