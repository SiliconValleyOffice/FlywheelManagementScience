# Introduction #

New FMM Templates must be generated after a DDL change.


# Steps #

  * remove templates from Eclipse environment
  * prepare device
  * run new APK on device
  * templates will be generated in database directory on device
  * copy templates back to Eclipse

# Prepare device #

If installed, use the Workbench application to delete all existing FMMs.

Asus NodePad
  * su - root
  * chmod 777 . /storage/emulated/legacy/Android
> > (link target for /data)
  * chmod 777 /data
  * chmod 777 /data/data
  * chmod -R 777 /data/data/com.flywheelms.fwb

Following steps are necessary if you did not use the Workbench to delete all FMMs.
  * rm /data/data/com.flywheelms.fwb/databases/`*`
  * rm /data/data/com.flywheelms.fwb/files/`*`

# Copy templates back to Eclipse #

  * chmod -R 777 /data/data/com.flywheelms.fwb/databases
  * copy templates to project assets directory
  * rm /data/data/com.flywheelms.fwb/databases/`*`
