# Introduction #

To generate an installable application package for debug/testing outside the Eclipse environnment.

# Details #

Some basic info:
  * Keystore file: /home/sstamps/.android/debug.keystore
  * Keystore name: "debug.keystore"
  * Keystore password: "android"
  * Key alias: "androiddebugkey"
  * Key password: "android"
  * CN: "CN=Android Debug,O=Android,C=US"

# From Eclipse #

  * right-click flywheelms-hd project
  * select Android Tools -> Export Signed Application Package

Wizard steps:
  * select flywheelms-hd project
  * select "Use existing keystore" and enter "android" password
  * select "Use existing key" and enter "android" password
  * select destination APK file
  * click Finish button