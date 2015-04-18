OUT OF DATE

Do not use enum for any non-trivial objects.

See FmmFrame for an example of the new approach.


# Introduction #

Enumerator is one of the node types in the FMM.  Enumerators are supported by a `[JavaEnum]_enum` table in fmm.db.

## Purpose ##

  * there is a 1:1 mapping between the underlying `[JavaEnum]_enum` table in fmm.dbto and its associated Java enum in the code base
  * the `[JavaEnum]_enum` table only contains a row for each value in the enum class
  * these enum tables support implementation of fmm.db using database technology whose SQL implementation does not support an ENUM

# Database Implementation #

## Table Names ##

Tables are named ` [JavaEnum]_enum `.

## Table Creation ##

The Java enum is responsible for creating its underlying database table by implementing
```
public static void createTable(SQLiteDatabase aDatabase)
```

The createTable() method is called (indirectly) from FmmOpenHelper.onCreate()

This method reads and executes the DDL from a text file in the _res/raw_ directory, referencing it by Resource ID.

The Resource ID for the DDL file is ` R.raw.[file_name] `, where file name for table ClassName\_enum is ` class_name_enum_ddl `.

## Row Initialization ##

In order to maintain strict synchronization between the Java enum class instances and the underlying database table, the enum class is responsible for creating a row in its underlying database table for each instance of the enum.  This is implemented in the enumerator's method
```
public static void initializeTableData(SQLiteDatabase aDatabase)
```

The initializeTableData() method is called (indirectly) from FmmOpenHelper.onCreate()

# Java _enum_ Implementation #

  * create new enum in com.flywheelms.flywheelms.fmm.node.enumerator
  * insert copyright notice at top of file
  * enum implements FmmEnumNode
  * model the implementation of the new enum on AssignmentCommitmentType