# Introduction #

The FlywheelMS application enables you to save the current state/values of a broad range of GUI components throughout the application.

Types of GUI components that support saved preferences include:
  * wizard steps
  * menus
  * navigation trees

# Availability of this Feature #

Any GUI component that supports this feature will include a small glyph.

= Accessing the Feature

There are 2 ways to manage GUI preferences for a component:
  * tap on the preferences glyph
  * long press on the background of the GUI component

# Available Actions #

```
CLEAR - clear all preferences.
        Will not change contents of GUI.
        Default values will be displayed next time this component is used.
CANCEL - do nothing
RESTORE - restore GUI to saved preferences
SAVE - save the current state/contents of the component
```