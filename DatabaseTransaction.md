# Introduction #

Notes for handling _**transactions**_ in the FMM database.


# Coding Pattern #

Please follow the pattern:
```
   db.beginTransaction();
   try {
     ...
     db.setTransactionSuccessful();
   } finally {
     db.endTransaction();
   }
```