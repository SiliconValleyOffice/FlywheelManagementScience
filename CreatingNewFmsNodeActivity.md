# Introduction #

A general outline for creating a new FMM Node Activity.

## assumptions ##

  * existing database table
  * existing serializable FMM node class

# Details #

  * create wiki page
  * create new entry in FmsHelpIndex
  * create activity (use FiscalYearActivity as a template)

  * create database routines for the FMM node
    * FmmDatabaseMediator
    * FmmDatabaseMediatorSqLite
    * FmmDatabaseMediatorMySql

  * create drawable for heading

  * FmsActivityHelper
    * create start()
    * update startFmmNodeActivity()