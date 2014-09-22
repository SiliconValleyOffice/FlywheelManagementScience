/* @(#)GcgContainer.java
** 
** Copyright (C) 2012 by Steven D. Stamps
**
**             Trademarks & Copyrights
** Flywheel Management Science(TM), Flywheel Management Model(TM),
** Flywheel Story Editor(TM) and FlywheelMS(TM) are exclusive trademarks
** of Steven D. Stamps and may only be used freely for the purpose of
** identifying the unforked version of this software.  Subsequent forks
** may not use these trademarks.  All other rights are reserved.
**
** DecKanGL (Decorated Kanban Glyph Language) and TribKn (Tribal Knowledge)
** are also exclusive trademarks of Steven D. Stamps.  These may be used
** freely within the unforked FlywheelMS application and documentation.
** All other rights are reserved.
**
** gConGUI (game Controller Graphical User Interface) is an exclusive
** trademark of Steven D. Stamps.  This trademark may be used freely
** within the unforked FlywheelMS application and documentation.
** All other rights are reserved.
**
** Trademark information is available at
** <http://www.flywheelms.com/trademarks>
**
** Flywheel Management Science(TM) is a copyrighted body of management
** metaphors, governance processes, and leadership techniques that is
** owned by Steven D. Stamps.  These copyrighted materials may be freely
** used, without alteration, by the community (users and developers)
** surrounding this GPL3-licensed software.  Additional copyright
** information is available at <http://www.flywheelms.org/copyrights>
**
**              GPL3 Software License
** This program is free software: you can use it, redistribute it and/or
** modify it under the terms of the GNU General Public License, version 3,
** as published by the Free Software Foundation. This program is distributed
** in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
** even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
** PURPOSE.  See the GNU General Public License for more details. You should
** have received a copy of the GNU General Public License, in a file named
** COPYING, along with this program.  If you cannot find your copy, see
** <http://www.gnu.org/licenses/gpl-3.0.html>.
*/

package com.flywheelms.library.gcg.container;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flywheelms.library.R;
import com.flywheelms.library.gcg.activity.GcgActivity;
import com.flywheelms.library.gcg.button.multi_shift.GcgMultiShiftState;
import com.flywheelms.library.gcg.helper.GcgGuiHelper;

public abstract class GcgContainer extends LinearLayout {
	
	protected GcgActivity gcgActivity;
	public static final String container_layout__MENU_PARAMETER = "menu_parameter";
	public static final String container_layout__VERTICAL = "vertical";
	public static final String container_layout__VERTICAL__NO_LABEL_DRAWABLE = "vertical__no_label_drawable";
	public static final String container_layout__HORIZONTAL = "horizontal";
	public static final String container_layout__HORIZONTAL__NO_LABEL_DRAWABLE = "horizontal__no_label_drawable";
	public static final String container_layout__NO_LABEL = "no_label";
	public static final String on_click__DEFAULT = "default";
	public static final String on_click__CUSTOM = "custom";
	public static final String on_click__NONE = "disables";
	private static final int resource_id__WIDGET_LABEL = R.id.widget_label;
	protected String containerLayout = container_layout__HORIZONTAL;
	protected boolean scrollableContent = false;
	protected int labelWidth;
	protected boolean isTransparentBackground = false;
	protected boolean inputEnabled = true;
	protected TextView widgetFdkCursor;
	protected boolean isTransparentBackgroundParameter = false;
	protected String onClick = on_click__DEFAULT;
	protected String onLongClick = on_click__DEFAULT;
	protected String dataType;
	protected ViewGroup containerParent;
	protected TextView labelTextView;
	protected String labelTextString;
	protected Object originalValue;
	protected String labelPrefix = "";
	protected String labelSuffix = "";
	protected GcgMultiShiftState initialMultiShiftState;
	protected GcgMultiShiftState activeMultiShiftState;
	protected boolean resetMultiShiftStateAfterDictation = true;
	protected int minimumDataLength;

	public GcgContainer(Context aContext) {
		super(aContext);
		if(! isManualSetup()) {
			setup();
		}
	}

	public GcgContainer(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
		processCustomAttributes(aContext, anAttributeSet);
		if(! isManualSetup()) {
			setup();
		}
	}

	public GcgContainer(Context aContext, AttributeSet anAttributeSet, int aStyleDefinition) {
		super(aContext, anAttributeSet, aStyleDefinition);
		processCustomAttributes(aContext, anAttributeSet);
		if(! isManualSetup()) {
			setup();
		}
	}
	
	protected boolean isManualSetup() {
		return false;
	}

	@SuppressWarnings("incomplete-switch")
	protected void processCustomAttributes(Context aContext, AttributeSet anAttributeSet) {
		processGcgLabelAttributes(aContext, anAttributeSet);
		TypedArray aTypedArray = aContext.obtainStyledAttributes(anAttributeSet, R.styleable.GcgWidget);
		final int theArraySize = aTypedArray.getIndexCount();
		for (int theIndex = 0; theIndex < theArraySize; ++theIndex) {
			int theAttributeIndex = aTypedArray.getIndex(theIndex);
			switch (theAttributeIndex) {
				case R.styleable.GcgWidget_containerLayout:
					this.containerLayout = aTypedArray.getString(theAttributeIndex);
					break;
				case R.styleable.GcgWidget_scrollableContent:
					this.scrollableContent = aTypedArray.getBoolean(theAttributeIndex, false);
					break;
				case R.styleable.GcgWidget_onClick:
					this.onClick = aTypedArray.getString(theAttributeIndex);
					break;
				case R.styleable.GcgWidget_onLongClick:
					this.onClick = aTypedArray.getString(theAttributeIndex);
					break;
				case R.styleable.GcgWidget_transparentBackground:
					this.isTransparentBackground = aTypedArray.getBoolean(theAttributeIndex, false);
					break;
				case R.styleable.GcgWidget_inputEnabled:
					this.inputEnabled = aTypedArray.getBoolean(theAttributeIndex, true);
					break;
			}
		}
		aTypedArray.recycle();
	}
	
	@SuppressWarnings("incomplete-switch")
	protected void processGcgLabelAttributes(Context aContext, AttributeSet anAttributeSet) {
		TypedArray aTypedArray = aContext.obtainStyledAttributes(anAttributeSet, R.styleable.GcgLabel);
		final int theArraySize = aTypedArray.getIndexCount();
		for (int theIndex = 0; theIndex < theArraySize; ++theIndex) {
			int theAttributeIndex = aTypedArray.getIndex(theIndex);
			switch (theAttributeIndex) {
				case R.styleable.GcgLabel_labelWidth:
					this.labelWidth = aTypedArray.getInteger(theAttributeIndex, 0);
					break;
				case R.styleable.GcgLabel_labelText:
					this.labelTextString = aTypedArray.getString(theAttributeIndex);
					break;
				case R.styleable.GcgLabel_labelPrefix:
					this.labelPrefix = aTypedArray.getString(theAttributeIndex);
					break;
				case R.styleable.GcgLabel_labelSuffix:
					this.labelSuffix= aTypedArray.getString(theAttributeIndex);
					break;
			}
		}
		aTypedArray.recycle();
	}

	protected void setup() {
		inflate(getContext(), getContainerLayoutResourceId(), this);
	}
	
	public void setLabelText(String aString) {
		this.labelTextView.setText(aString);
	}
	
	protected Drawable getPrimaryLabelDrawable() {
		return null;
	}

	public GcgActivity getGcgActivity() {
		return this.gcgActivity;
	}
	
	public void setGcgActivity(GcgActivity aLibraryActivity) {
		this.gcgActivity = aLibraryActivity;
	}

	protected boolean hasLabel() {
		return ! this.containerLayout.equals(container_layout__NO_LABEL) && ! this.containerLayout.equals(container_layout__MENU_PARAMETER);
	}
	
	protected boolean isMenuParameter() {
		return this.containerLayout.equals(container_layout__MENU_PARAMETER);
	}
	
	protected String getLabelText() {
		return this.labelTextString;
	}
	
	protected abstract int getContainerLayoutResourceId();

	protected void setTransparentBackground() {
		return;
	}

	public void setInitialValue(@SuppressWarnings("unused") Object anObject) {
		return;
	}

	public void setInputType(@SuppressWarnings("unused") int anInputType) {
		return;
	}
	
	public void setInitialValue() {
		return;
	}
	
	public void setContainerWidth(int aContainerWidthInDp) {
		LayoutParams theLayoutParams = (LayoutParams) this.containerParent.getLayoutParams();
		theLayoutParams.width = GcgGuiHelper.getPixelsForDp(aContainerWidthInDp);
		this.containerParent.setLayoutParams(theLayoutParams);
	}
	
	public void setContainerHeight(int aContainerHeightInDp) {
		LayoutParams theLayoutParams = (LayoutParams) this.containerParent.getLayoutParams();
		theLayoutParams.height = GcgGuiHelper.getPixelsForDp(aContainerHeightInDp);
		this.containerParent.setLayoutParams(theLayoutParams);
	}
	
	public void setContainerDimensions(int aContainerWidthInDp, int aContainerHeightInDp) {
		LayoutParams theLayoutParams = (LayoutParams) this.containerParent.getLayoutParams();
		theLayoutParams.width = GcgGuiHelper.getPixelsForDp(aContainerWidthInDp);
		theLayoutParams.height = GcgGuiHelper.getPixelsForDp(aContainerHeightInDp);
		this.containerParent.setLayoutParams(theLayoutParams);
	}
	
	public void setContainerBackground(Drawable aDrawable) {
		this.containerParent.setBackground(aDrawable);
	}

}
