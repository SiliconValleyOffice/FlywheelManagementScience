# Introduction #

Subclasses of _GcgMultiStateButton_ create a button that cycles to the next button state with each click event.  Subclasses may implement any number of states through which to cycle.

# Collaboration #

  * Drawable resources are created; 1 for each of the button states
  * An application-specific class is created which extends _GcgMultiStateButton_ and uses the drawable states
  * the new subclass is referenced in layout resources for the application

# Sample custom class which implements _GcgMultiStateButton_ #

```
/*
 * com.flywheelms.flywheelms.fms.widget.ShowButtonNodeSummary
 */
public class ShowButtonNodeChildSummary extends GcgMultiStateButton {
	
	public static final int[] drawableResourceIdArray = {
		R.drawable.filter_button__node_summary__on,
		R.drawable.filter_button__node_summary__level_2,
		R.drawable.filter_button__node_summary__level_3,
		R.drawable.filter_button__node_summary__off
	};

	public ShowButtonNodeChildSummary(Context aContext) {
		super(aContext, drawableResourceIdArray);
	}

	public ShowButtonNodeChildSummary(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet, drawableResourceIdArray);
	}

	public ShowButtonNodeChildSummary(Context aContext, AttributeSet anAttributeSet, int aDefStyle) {
		super(aContext, anAttributeSet, aDefStyle, drawableResourceIdArray);
	}

}
```

# Layout Resource #

```
        <com.flywheelms.flywheelms.fms.widget.ShowButtonNodeChildSummary
            android:id="@+id/strategy__show_button__node_child_summary"
            android:layout_width="105dp"
            android:layout_height="38dp"
            android:layout_gravity="right"
            android:layout_marginBottom="@dimen/show_button__bottom_margin"
            android:layout_marginLeft="10dp"
            android:layout_marginTop="@dimen/right_menu__top_margin"
            android:onClick="onShowNodeChildSummaryClicked" />
```