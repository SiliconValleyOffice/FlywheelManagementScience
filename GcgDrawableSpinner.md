# Introduction #

GcgDrawableSpinner is a copyrighted element of the gConGui(TM) GUI framework.

An implementation of the abstract _GcgDrawableSpinner_ class will present a drop-down of drawables from which to choose.

# Collaboration #

  * Drawable resources are created; 1 for each of the spinner options
  * An application-specific class is created which extends _GcgDrawableSpinner_ and uses the drawable choices.
  * the new class is referenced in layout resources for the application

# Sample Custom Class which extends FmsMultiStateButton #


```
// com.flywheelms.fse.widget.FseEditScopeSpinner
public class FseEditScopeSpinner extends GcgDrawableSpinner {
	
	public static final Integer[] drawableResourceIdArray = {
		R.drawable.fse__edit_scope__paragraph,
		R.drawable.fse__edit_scope__hierarchy,
		R.drawable.fse__edit_scope__to_end_of_document,
		R.drawable.fse__edit_scope__selection
	};

	public FseEditScopeSpinner(Context aContext) {
		super(aContext, drawableResourceIdArray);
		setup();
	}

	public FseEditScopeSpinner(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet, drawableResourceIdArray);
		setup();
	}

	public FseEditScopeSpinner(Context aContext, AttributeSet anAttributeSet, int aDefStyle) {
		super(aContext, anAttributeSet, aDefStyle, drawableResourceIdArray);
		setup();
	}
	
	private void setup() {
		GcgDrawableSpinnerArrayAdapter theCustomArrayAdapter =
				new GcgDrawableSpinnerArrayAdapter(getContext(), this.drawableResourceIdArray);
		this.setAdapter(theCustomArrayAdapter);
	}

}

```

# Layout Resource #

Note that the full class name of the subclass is referenced in the tag.

```
        <com.flywheelms.fse.widget.FseEditScopeSpinner
            android:id="@+id/scope_button__style"
            android:layout_width="wrap_content"
            android:layout_height="60dp"
            android:layout_margin="0dp"
            android:layout_marginBottom="10dp"
            android:layout_marginTop="@dimen/right_menu__top_margin"
            android:padding="0dp" />
```