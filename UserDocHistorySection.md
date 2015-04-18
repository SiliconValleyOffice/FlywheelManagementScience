# Introduction #

The History document section provides a list of the Document Transactions.

A review of these transactions will reveal the gestation of the document, who participated, how often, and when.

# Document Transactions #

All of a user's changes for a day are aggregated into a single transaction, unless someone else has also modified the document.  In that case, the contiguous transactions are aggregated.

Transactions that are of type CHECKPOINT will not be aggregated with any subsequent transactions, even if by the same user.

## Transaction Types ##