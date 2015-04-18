## Introduction ##

These instructions assume a "clean install" for a new FlywheelMS developer on a machine that does not have a current Java/Android Studio development environment.

Developers with an existing development environment may want to use this as a checklist to verify a current/complete configuration.

## Download the JDK 8 ##

### Windows ###

Download the latest JDK 8, Standard Edition, from http://www.oracle.com/technetwork/java/

### Ubuntu ###

Use the Software Center for installation!
  * OpenJDK 8

## Download Android Studio ##

Get latest from Google.

### Android Studio config ###

- Settings
> Code Style --> General
> > Right margin = 200


> Code Style --> Java
> > Wrapping and Baces
> > > if() statement force braces always
> > > for() statement force braces always
> > > while() statement force braces always
> > > do while() statement force braces always
> > > try catch and finally statements on new line always
> > > ternary - align ? and : on next line
> > > array initializer - align when multiline, new line after }

> > Blank Lines
> > > Max blank lines 1
> > > After class header 1

> > Arrangement
> > > Keep getters and setters together - unchecked


> Compiler --> Android Compiler
> > Force jumbo mode = true


> Keymap
> > Eclipse (standard for the team, to facilitate pairing and to leverage Eclipse/Android skills)

## Initialize your project directory ##

Create a development directory and get a copy of the project with this command.


> git clone https://flywheelms@code.google.com/p/flywheel-management-science/

## Android SDK ##

When you open the project with Android Studio the first time, it will give you the option to download an Android SDK.  Look in the Workbench module's manifest file to see what versions of the SDK are currently being used by the project.

## To Make Project Contributions ##

Send an email to flywheelms@gmail.com to describe how you would like to contribute.

### Create a Google Code account ###

If you don't already have a gmail or google account, go here:
> https://www.google.com/accounts/NewAccount

Send your Google account name to the following email address to be added to the project.
> Mailto:flywheelms@gmail.com

## Monitor Source Code Commits via Email Notification ##

If you are an active developer, you will want to monitor the pulse of development.  This will help you:
  * know what is changing
  * understand the pace of change
  * know who is committing code
  * know how quickly your uncommitted changes are becoming "stale"  :-D

Of course you should always synchronize before developing/modifying code.  The trick is to quickly finish your task and get the code synchronized before there are merge conflicts with other developers.

We strongly suggest that you get yourself added to the email distribution list for Google Code commits.  The best way is to send an email to flywheelms@gmail.com requesting to be added to the distribution list.

In addition to reviewing development activity, be sure to check out the wiki for recent additions/changes.