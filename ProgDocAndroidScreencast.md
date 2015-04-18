# Introduction #

This is a 5 cent solution for displaying the tablet screen on your development computer screen.
  * to share your tablet screen during a Skype (or other) collaboration
  * to record a sequence of screen events for playback
  * to take screen shots (instead of using the DDMS perspective of Eclipse or ADT)

Issues:
  * medium latency waiting for tablet events to render on the workstation screen
  * no voice/audio capture

# Details #

URLs
  * http://www.howtogeek.com/howto/42491/how-to-remote-view-and-control-your-android-phone/
  * http://code.google.com/p/androidscreencast/

# Steps #

These steps assume that you have already installed Eclipse or ADT, and ADB is running and able to connect to your tablet device.

If you are not a developer, you can just install the Android SDK and configure/start ADB manually  (see URLs in earlier section of this page).

  * download the jnlp file
```
http://androidscreencast.googlecode.com/svn/trunk/AndroidScreencast/dist/androidscreencast.jnlp
```
  * create a directory such as
```
/home/your_id/DevelopmentTools
```
  * move the jnlp from the Downloads directory to the DevelopmentTools directory
  * find the location of your bin/javaws
  * create a shell file named startAndroidScreencast.sh in the DevelopmentTools directory
```
/usr/lib/jvm/jdk1.6.0/bin/javaws ./androidscreencast.jnlp
```
  * chmod 777 startAndroidScreencast.sh
  * execute the shell script and select your device