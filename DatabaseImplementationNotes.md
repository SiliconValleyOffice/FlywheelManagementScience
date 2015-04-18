# Overview #

All data in the FMM (Flywheel Management Model) is stored in a database.  Each database instance is called a **Repository Service** within the application.

The subset of data stored for a specific Organization in a **Repository Service** (database instance) is called a **Flywheel Repository** in the application.

## Design Objectives ##

  * Support a local Repository Service (SqLite database instance), as well as multiple remote Repository Services (database instances).  The FmmDatabaseMediator implements the notion of an "active" Repository Service to support this design objective.
  * Support multiple database technologies.  Currently supports SqLite locally and MySql remotely.  See the section titled _Multiple Database Technology Support_ for an overview of how this is implemented and how to add support for additional database technologies
  * Identical table names and column names across all database technologies.  Identical logical design
  * Support easy migration of a Flywheel Repository from one Repository Service (database instance) to another, regardless of the physical location or underlying database technology

## Active Repository Service ##

Although a user can configure multiple Flywheel Repositories hosted by different Repository Services in FlywheelMS, only one is _active_ at any given moment.

A Repository Service becomes active when a user begins to browse or edit a Flywheel Repository hosted by that service.

## Multiple Database Technology Support ##

Both the FmmDatabaseMediator and each [[TableName](TableName.md)]Dao are subclassed for each type of database technology supported by FlywheelMS.

To add support for a new database technology, just create a new subtype for FmmDatabaseMediator and each [[TableName](TableName.md)]Dao.

# FmmDatabaseMediator - abstract class #

This class is the application's gateway to the Active Repository Service.

An instance is created at runtime for each configured Repository Service.

The FmmDatabaseMediator class collaborates with DAO classes created for each database table.

## Subclasses of FmmDatabaseMediator ##

Since FmmDatabaseMediator is abstract, a concrete subclass of FmmDatabaseMediator is implemented for each type of database technology supported by FlywheelMS.

FlywheelMS currently supports the following database technologies:
  * SQLite (for a local Repository Service)  -  FmmDatabaseMediatorSqLite
  * MySQL (for remote Repository Services)  -  FmmDatabaseMediatorMySql

# DAO Class Hierarchy #

There is a DAO class hierarchy for each database technology supported by FlywheelMS.  This hierarchy supports the creation of  DAO class for each database table.

The remainder off this section describes DAO architectural elements that are common to all database technologies.

## FmmNodeDao ##

This is the root of all DAO classes, regardless of the database technology.

  * establishes the generic types of T (the underlying database table's hydrated object type) and C (the cursor type of the underlying database technology)

# ColumnDefinition interfaces #

Column names are defined in a _network_ of interface files.  The DAO implementation for a given database table defines its unique column names, and "implements" the appropriate interface for its generic column names.

Benefits:
  * assures consistent column definitions across implementations for each database technology
  * assures consistent column names across database tables for functionally identical data

# DAO class hierarchy for SQLite #

  * FmmNodeDao
    * FmmNodeDaoSqLite
      * NodeIdDaoSqLite
        * HeadlineNodeDaoSqLite
        * CommitmentNodeDaoSqLite

## FmmNodeDaoSqLite ##

  * extends FmmNodeDao
  * root of the class hierarchy for all DaoSqLite classes
  * specifies the SQLite **Cursor** class to resolve the 

&lt;C&gt;

 generic of FmmNodeDao
  * creates putSqLiteColumnIndexMapEntry(), which is used by all subclasses

## NodeIdDaoSqLite ##

  * extends FmmNodeDaoSqLite
  * adds "_id" to aColumnIndexMap_

# Implementing a Concrete Class for [[TableName](TableName.md)]DaoSqLite #

  * create new [[TableName](TableName.md)]ColumnDefinition interface, extending the appropriate interfaces which define non-unique (to this specific table) column names.  Define column names not already defined in the superclass(es)
  * extend appropriate DAO super class to create concrete DAO class
  * implement buildSqLiteColumnIndexMap() to add column names not in the super class.  Call _super_ if you are _decorating_ column names in the super class.

## Create [[TableName](TableName.md)]ColumnDefinition ##

  * extend all appropriate interface for common column names
  * add column names unique to this table

## Create [[TableName](TableName.md)]DaoSqLite ##

  * add singleton code
  * implement buildColumnIndexMap(), calling _super_ and then adding the columns that are unique to this table
  * implement getColumnValues(), calling _super_ to retrieve columns that are not unique to this table.  Then retrieve columns that are unique to the table.

/////////////////////////////////////////



  * FmmNodeDao - underlying table has an "_id" primary key column_

  * FmmCommitmentNodeDao
    * FlywheelCommitmentDao
    * ServiceDeliveryCommitmentDao
    * StrategicCommitmentDao

  * FmmCompletableDao

  * FmmHeadlineNodeDao
    * CommmunityMemberDao

  * FmmLinkNodeDao - underlying table has a 2-column compound primary key
    * ProjectAssetLinkToWorkPackageDao


## FmmLinkNodeDao ##

Extends FmmNodeDao

  * provides buildSqLiteColumnIndexMap(), which blocks the addition of the "_id" column to the ColumnIndexMap, since it does not call_super

# Implementation Pattern for a New Database Table #

  * select appropriate Dao base class
  * implement [[TableName](TableName.md)]Dao, extending appropriate Dao base class
  * implement [[TableName](TableName.md)]Dao[[DatabaseTechnology](DatabaseTechnology.md)] for each supported technology