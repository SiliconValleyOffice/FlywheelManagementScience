# Introduction #

From Android Studio, launch the device manager.

# you need permission to data directory #

```
    Open terminal emulator on device and "su - root"
    chmod 777 /storage/emulated/legacy/Android
    chmod 777 /data
    chmod 777 /data/data
    chmod -R 777 /data/data/com.application.pacakage
```

# Install SQLiteManager plugin #


Download the sqlitemanager jar, put it in the eclipse dropins folder and restart eclipse.

> http://www.coderzheaven.com/android1/com.questoid.sqlitemanager_1.0.0.jar


# Using SQLiteManager in device monitor #

  * select com.flywheelms.workbench
  * select File Explorer tab
  * open node /data/data/com.flywheelms.workbench/databases
  * select {FmmName].db
  * click the sqlitemanager icon on the top right of the File Explorer window
  * in the "CellObject SQLite Browser" tab (usually at the bottom of the IDE), you can see 2 tabs: DatabaseStructure and Browse Data

Here is a video on how to use it.
> http://www.youtube.com/watch?v=sAvlV0uw5zc