# Introduction #

This class is used in collaboration with a _Spinnner_ view to implement multiple vertical menus in the same physical area of the screen.  The column heading of these menus is a spinnner that enables switching between menus.  The spinner also indicates how many menus are available and which one is selected.

GcgSpinnableMenu will support up to 5 different menus, with typically 5 or fewer options per menu (more with smaller icons); supporting 25 easily accessible and highly visual menu options in a relatively modest piece of physical screen real estate.

These spinnable menus are typically placed on the far left and/or far right of a landscaped layout to create a key element of the gConGUI usability; high speed, complex interactions with the data.

# Collaboration #

  * a string array resource containing the headings for each menu
  * a layout file that defines the spinnable menu view components
  * an instance of _GcgSpinnableMenu_ implemented in the class mediating the parent view
  * application-specific implementation of the various listeners and processing that supports the functionality of each menu

# String Array #

This array is references as a resource when instantiating the _GcgSpinnableMenu_ object in the containing activity's class.

The order of items in this array will control the order in which menus are processed in the _GcgSpinnableMenu_ class.

```
    <string-array name="fse__right_menu__heading_array">
        <item>Style</item>
        <item>Sequence</item>
    </string-array>
```

# Layout #

Elements of this layout are referenced by resource ID when instantiating the _GcgSpinnableMenu_ object in the containing activity's class.

It is recommended that the Spinnnable Menu be defined in its own layout file and included in the layout file for the activity,

```
    <include
        layout="@layout/fse__right_menu__include" />
```

  * a 

&lt;Spinner&gt;

 is defined at the top of a 

&lt;FrameLayout&gt;

_* a_

&lt;LinearLayout&gt;

 is defined after the 

&lt;Spinner&gt;

; one for each menu controlled by the Spinner
  * note the specification of right versus left decorators (for left and right menus)
  * each menu's LinearLayout contains the buttons and other views for that specific menu

Sample contents of the include file:
```
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/fse__right_menu__frame_layout"
    android:layout_width="100dp"
    android:layout_height="match_parent" >

    <Spinner
        android:id="@+id/fse__right_menu__heading_spinner"
        android:layout_width="match_parent"
        android:layout_height="@dimen/menu__label_height"
        android:layout_marginBottom="8dp"
        android:background="@drawable/menu_heading__1_of_2__spinner_background__right"
        android:gravity="right"
        android:spinnerMode="dropdown"
        android:textColor="@color/White"
        android:textSize="14.0dp" >
    </Spinner>

    <LinearLayout
        android:id="@+id/fse__style_menu"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_marginTop="50dp"
        android:gravity="center_horizontal"
        android:orientation="vertical" >

        <Button
            android:id="@+id/indent_button"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginBottom="12dp"
            android:background="@color/tranparent"
            android:drawableStart="@drawable/style_indent"
            android:padding="@dimen/padding_medium" >
        </Button>

        <Button
            android:id="@+id/outdent_button"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginBottom="12dp"
            android:background="@color/tranparent"
            android:drawableStart="@drawable/style_outdent"
            android:padding="@dimen/padding_medium" >
        </Button>
    </LinearLayout>

    <LinearLayout
        android:id="@+id/fse__sequence_menu"
        android:layout_width="80dp"
        android:layout_height="match_parent"
        android:layout_margin="0dp"
        android:gravity="center_horizontal"
        android:orientation="vertical"
        android:visibility="invisible" >

        <Button
            android:id="@+id/move_up_button"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginBottom="12dp"
            android:layout_marginTop="@dimen/right_menu__top_margin"
            android:background="@color/tranparent"
            android:drawableStart="@drawable/sequence_move_up"
            android:padding="@dimen/padding_medium" >
        </Button>

        <Button
            android:id="@+id/move_down_button"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginBottom="12dp"
            android:background="@color/tranparent"
            android:drawableStart="@drawable/sequence_move_down"
            android:padding="@dimen/padding_medium" >
        </Button>
    </LinearLayout>

</FrameLayout>
```

# Instance of GcgSpinnableMenu #

This instance is created inside the class that mediates the enclosing activity, usually as a private data member of that class.

The local variable _theMenuFrameLayout_ needs to be retrieved from the inflated layout for the enclosing activity.

Elements of _theMenuBodyResourceIdArray_ must be in the same sequence as the _fse__right\_menu__heading\_array_.

Note the specification of RIGHT versus LEFT decorators.

```
	private GcgSpinnableMenu rightMenu;

	private void initRightMenu() {
		int[] theMenuBodyResourceIdArray = {
				R.id.fse__style_menu__body,
				R.id.fse__sequence_menu__body
		};
		FrameLayout theMenuFrameLayout =
				(FrameLayout) this.storyEditorFragmentView.findViewById(R.id.fse__right_menu__frame_layout);
		this.rightMenu = new GcgSpinnableMenu(
				getActivity(),
				theMenuFrameLayout,
                                GcgSpinnableMenu.DECORATORS_RIGHT,
				R.id.fse__right_menu__heading_spinner,
				R.array.fse__right_menu__heading_array,
				theMenuBodyResourceIdArray );
		// application-specific setup for each menu body
		initStyleMenu();
		initSequenceMenu();
	}
```

# Application-specific Implementation of Listeners #