# Introduction #

The Flywheel Dictation Keyboard (tm) is a trademarked user input feature created for the Flywheel Management Science (tm) family of Android tablet applications.

The purpose of the FDK is to enable high-velocity data capture via voice input; in a manner that is convenient, accurate and includes appropriate punctuation.

# Overview #

FDK is not an "Android Input Method", but rather a custom input method that is tightly integrated into the host application.

# Keyboards #

  * Styles
  * 2 keypads

## keyboard instances ##
  * dialogs have only one keyboard instance
  * activities that do not have Perspectives have only 1 keyboard instance
  * each Perspective has its own keyboard instance

## keyboard layout ##
  * all keyboards share the same fdkleft\_keypad\_container (LinearLayout)
  * each Perspective has its own fdkright\_keypad\_container (LinearLayout)

## keyboard attributes ##
  * each keyboard has its own instance of left and right keypad

# keypads #
  * keyboard constructs its own keypads
  * keypad layout based upon the keyboard style

  * the activity/dialog has an empty LinearLayout for each of the left and right keypads

# XML #

XML for FDK activities must define 4 standard elements:
  * **gcg`__thumbpad__`left**
  * **fdk`__left_keypad__`container**
  * **gcg`__thumbpad__`right**
  * **fdk`__right_keypad__`container**

If the left (or right) Thumbpad contains anything other than the FdkKeypad, it should be defined in XML with an id of **fdk`__left_keypad__`peer\_view**

## Thumbpads ##

FdkKeypads are always defined within a GcgThumbpad.

Interfaces that use the FDK must include the elements named **gcg`__thumbpad__`left** and **gcg`__thumbpad__`right**.  These elements must define their respective **fdk`__left_keypad__`container** or **fdk`__right_keypad__`container**

Left Thumbpad Example:
```

    <include
        android:id="@+id/gcg__thumbpad__left"
        layout="@layout/workbench__left_thumbpad" />

```

This GUI element must be initialized by calling `initializeThumbpadLeft()`.  This is usually done in a GcgLibraryActivity subclass.  GcgPerspectives share the same instance of left thumbpad.

Right Thumbpad Example:

```

    <include
    	android:id="@+id/gcg__thumbpad__left"
        layout="@layout/fmm_node__editor__left_thumbpad" />

```

This GUI element must be initialized by calling `initializeThumbpadRight()`.  Each GcgPerspective must have its own instance of right thumbpad.

## Keypads ##

Each FdkKeypad is defined inside its respective GcgThumbpad.

Left Keypad Example:
```

    <include
        android:id="@+id/fdk__left_keypad__container"
        layout="@layout/fdk__keypad__container" />

```

Note in the example below that the definition for **fdk`__keypad__`right** references the same layout as **fdk`__keypad__`left** above.  The actual keypad button contents are determined when instantiating the FdkKeyboard object at runtime through specification of an FdkKeyboardStyle.

Right Keypad Example:
```

    <include
        android:id="@+id/fdk__right_keypad__container"
        layout="@layout/fdk__keypad__container" />

```




fdkLeftKeypadParent
fdkLeftKeypadContainer
fdkLeftKeypadPeer


fdkKeyboard.activate()

fdkKeypad.activate()

replace contents of container
make visible
make peer invisible