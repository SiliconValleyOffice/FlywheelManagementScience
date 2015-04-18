# Introduction #

Super integration with Linux and Windoze.

# Install Oracle VirtualBox #

Can be obtained through Software Center, but downloading directly from Oracle will provide a more current version.

The downloaded file can/should be installed by Software Center.

# Install Genymotion #

Download from genymotion.com.

You will need to login to their cloud to download.

A good install path is /home/your\_login.  The installation program will create a subdirectory named **genymotion**.

```
$ cd [DOWNLOAD_PATH]
$ chmod +x genymotion-[VERSION]_[ARCH].bin
$ ./genymotion-[VERSION]_[ARCH].bin -d [YOUR_INSTALL_PATH]
```

# Install and Configure Virtual Device of Choice #

We are currently using the Nexus 7 4.3 VM available at the Genymotion cloud.

# Virtual Device Launcher Shortcut for Ubuntu #

  * start genymotion
```
$ /home/your_id/genymotion/genymotion
```
  * start your VM
  * lock to launcher
  * stop VM
  * edit /home/your\_id/.local/share/applications/player.desktop
```
Put quotes around the VM name.  For some reason Unity does not write these.
If unsure of the VM name, see them with the following command:
$ VBoxManage list vms
```
  * You can now just click it on the launcher to start VM
  * Any subsequent changes to the launch parameters must be made to /home/your\_id/.local/share/application/player.desktop

Sample list vms output:
```
$ VBoxManage list vms
"Nexus 7 - 4.3 - API 18 - 800x1280" {aa1e70fd-50d0-436a-867e-d6b2641d7512}
$ 
```

Sample file:
```
[Desktop Entry]
Encoding=UTF-8
Version=1.0
Type=Application
Name=Genymotion - Nexus 7 - 4.3 (Steve's virtual tablet)
Icon=player.png
Path=/home/sstamps/genymotion
Exec=/home/sstamps/genymotion/player --vm-name "Nexus 7 - 4.3 - API 18 - 800x1280"
StartupNotify=false
StartupWMClass=Player
OnlyShowIn=Unity;
X-UnityGenerated=true
```

# Install Play Store #

URL reference
```
http://stackoverflow.com/questions/17831990/how-do-you-install-google-frameworks-play-accounts-etc-on-a-genymotion-virtu
```

  1. Download the following ZIP file:
```
ARM Translation Installer v1.1(Mirrors)
    http://goo.gl/JBQmPa
```
  1. Download the correct GApps zip file for your Android version:
```
Google Apps for Android 4.4(Mirror)(4.4 GApps might have bugs)
    http://goo.gl/TVcd93    OR    http://goo.gl/3EGMm4
Google Apps for Android 4.3(Mirrors)
    http://goo.im/gapps/gapps-jb-20130813-signed.zip  OR  http://goo.gl/cC8xHR
```
  1. Open your Genymotion VM and go to the Homescreen
  1. Drag&Drop the Genymotion-ARM-Translation.zip onto the Genymotion VM window.
> > It should say "File transfer in progress", once it asks you to flash it click 'OK'
  1. Reboot your VM using ADB.  Rebooting 4.4.2 has security restrictions that prevent this from working.  More research into the new security framework required.  This works with Nexus 4.3 and below.
```
$ adb devices
List of devices attached 
192.168.56.101:5555	device

$ adb -s 192.168.56.101:5555 reboot
```
  1. Once you're on the Homescreen again Drag&Drop the gapps-??-signed.zip(the name varies) onto your VM, and click 'OK' when asked
  1. Once it finishes, again Reboot your VM and open the Google Play Store.
  1. Sign in using your Google account
  1. Once in the Store go to the 'My Apps' menu and let everything update(fixes a lot of issues), also try updating Google Play Services directly.
  1. Try searching for 'Dropbox' (or another favorite app)
  1. If the app shows up in the results and you're able to Download/Install it, then congrats.  You now have ARM support and Google Play fully setup!

# Enable Microphone Input (FDK) #

  * add yourself to the group named **vboxusers**
```
$ sudo gpasswd -a sstamps vboxusers
```
  * shut down your virtual machine and restart VBox