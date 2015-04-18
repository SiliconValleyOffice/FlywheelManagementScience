# Introduction #

The story editor captures special key events and uses them as accelerators for editing functions.


# Details #

3 places to capture these events
  * physical keyboard (bluetooth?) handled by FseParagraphContentTextView.onKeyDown(int aKeyCode, KeyEvent aKeyEvent)
  * soft keyboard and FDK handled by ...
  * key handling that is unique to a specific widget handled by widget.setOnKeyListener(OnKeyListener aKeyListener)