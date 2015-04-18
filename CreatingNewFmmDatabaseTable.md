# Introduction #

FMM nodes are persisted in a relational database.

Currently FlywheelMS supports 2 database technologies; MySQL and SQLite.

Don't forget to generate new FMM templates when the DDL changes!!!

## Collaborating Classes ##

  * [[FmmNode](FmmNode.md)]
  * [[FmmNode](FmmNode.md)]ColumnDefinition
  * [[FmmNode](FmmNode.md)]DaoSqLite
  * [[FmmNode](FmmNode.md)]DaoMySql
  * FmmDatabaseMediator

# Steps #

## Database Review ##
  * review/update logical database design
  * make sure DDL matches logical design

## [[FmmNode](FmmNode.md)] Class Review ##
  * confirm that associated [[FmmNode](FmmNode.md)] class data members support the DDL
  * make changes to improve collaboration of node class and database table(s)
## FmmNodeColumnDefinition ##
    * create if not exists
    * extends correct interface
    * includes unique columns
## FmmDatabaseMediator ##
This class implements the generic database logic for an FMM node, and then delegates to the appropriate DAO class, depending upon the database technology being used by the specific repository.
    * create new section for FMM Node (copy/modify FiscalYear)
    * create methods for collections
    * create methods for subsets (retrieve by parent)
## [[FmmNode](FmmNode.md)]DaoMySql ##
A delegate of FmmDatabaseMediator.  Contains database logic that is unique to MySQL.  There is one of these classes for each FMM node.
    * create if not exists
    * create missing methods
## [[FmmNode](FmmNode.md)]DaoSqLite ##
A delegate of FmmDatabaseMediator.  Contains database logic that is unique to SQLite.  There is one of these classes for each FMM node.
    * create if not exists
    * create new section for FMM Node (copy/modify FiscalYear)
    * create methods for collections
    * create methods for subsets (retrieve by parent)