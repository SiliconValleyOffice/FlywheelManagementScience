# Introduction #

Custom layout widgets are created for complex layout elements, or elements that will be re-used.


# Collaborators #

  * custom layout file such as custom\_class\_view.xml
  * custom class such as CustomClassView.java
  * custom tag in the xml such as <com.flywheelms/fse/widget/CustomClassView />

# custom layout file #

This is a normal Android layout file.
  * The outer tag will determine the class which the custom class must extend.  Frequently LinearLayout, FrameLayout, RelativeLayout or TableLayout.

# custom class #

  * referenced and instantiated directly from a layout.xml file
  * must include a constructor with the following signature - (Context context, AttributeSet attrs)
  * constructor usually inflates the associated layout file into a local data member
  * usually has an initialize() method, with an appropriate signature, to enable the Activity or Fragment to implement other class initialization
  * often has a view[Whatever](Whatever.md)() method to populate the child view from the data model

```
// com.flywheelms.flywheelms_library_hd.fms.widget.FmsAuditBlockView
public class FmsAuditBlockView extends LinearLayout {
	
	private LinearLayout viewLayout;

	public FmsAuditBlockView(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
		this.viewLayout = (LinearLayout) LayoutInflater.from(aContext).inflate(R.layout.fms__node_frag_audit_block__view, (ViewGroup) getParent());
		addView(this.viewLayout);
	}
	
	public void viewAuditBlock(NodeFragAuditBlock anAuditBlock) {
		((TextView) this.viewLayout.findViewById(R.id.audit_block__created_by__data))
			.setText(anAuditBlock.getCreatedByNodeIdString());
		((TextView) this.viewLayout.findViewById(R.id.audit_block__created_timestamp__data))
			.setText(anAuditBlock.getCreatedTimestamp().toString());
		((TextView) this.viewLayout.findViewById(R.id.audit_block__last_updated_by__data))
			.setText(anAuditBlock.getLastUpdatedByNodeIdString());
		((TextView) this.viewLayout.findViewById(R.id.audit_block__last_updated_timestamp__data))
			.setText(anAuditBlock.getLastUpdatedTimestamp().toString());
		((TextView) this.viewLayout.findViewById(R.id.audit_block__locked_by__data))
			.setText(getLockedByNodeIdString(anAuditBlock));
		((TextView) this.viewLayout.findViewById(R.id.audit_block__locked_timestamp__data))
			.setText(getLockedTimestampString(anAuditBlock));
		((TextView) this.viewLayout.findViewById(R.id.audit_block__locked_status__data))
			.setText(getLockedStatusString(anAuditBlock));
		updateLockedLabels(anAuditBlock);
	}
    }
```

# Custom Tag #

```

        <!-- inflates fms__node_frag_audit_block__view -->
        <com.flywheelms.flywheelms_library_hd.fms.widget.FmsAuditBlockView
            android:id="@+id/fms__audit_block__view"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_alignParentTop="true" />
```