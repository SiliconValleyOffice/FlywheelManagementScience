# Introduction #

A simple approach to making any View "flingable".

# Collaborating Classes #

  * GcgFlingController (interface)
  * GcgFlingListener (extends SimpleOnGestureListener)
  * GcgHelper (various static methods)

## GcgFlingController ##

```
	void onFlingUp();
	
	void onFlingDown();
	
	void onFlingRight();
	
	void onFlingLeft();
```

## GcgHelper ##

Use:
```
public static OnTouchListener getFlingListener(Context aContext, final GcgFlingController aFlingController)
```

## GcgFlingListener ##

Defines:
  * SWIPE\_MIN\_DISTANCE
  * SWIPE\_MAX\_OFF\_PATH
  * SWIPE\_THRESHOLD\_VELOCITY

# Implementation #

  * implement GcgFlingController in SomeClass
  * construct OnTouchListener, referencing SomeClass
  * attach listener to view

## Implement GcgFlingController ##

Modify _SomeClass_ to implement GcgFlingController.
## Construct OnTouchListener ##

```
OnTouchListener theTouchListener = GcgHelper.getFlingListener(aContext, aSomeClassInstance);
```

## Attach Listener to View ##

```
aView.setOnTouchListener(GcgHelper.getFlingListener(aContext, aViewFlipper));
```