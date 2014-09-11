/* @(#)GcgWidgetSpinner.java
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

package com.flywheelms.library.gcg.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.flywheelms.library.R;
import com.flywheelms.library.fdk.FdkHostSupport;
import com.flywheelms.library.fdk.enumerator.FdkKeyboardStyle;
import com.flywheelms.library.fdk.interfaces.FdkListener;
import com.flywheelms.library.gcg.GcgActivity;
import com.flywheelms.library.gcg.GcgApplication;
import com.flywheelms.library.gcg.helper.GcgGuiableSpinnerArrayAdapter;
import com.flywheelms.library.gcg.interfaces.GcgGuiable;

import java.util.ArrayList;

@SuppressLint("ClickableViewAccessibility")
public abstract class GcgWidgetSpinner extends GcgWidget {
	
	public static final int logical_position__FIRST = 0;
	public static final int logical_position__PREVIOUS = 1;
	public static final int logical_position__NEXT = 2;
	public static final int logical_position__LAST = 99;
	protected GcgSpinner spinner;
	protected ArrayAdapter<GcgGuiable> arrayAdapter;
	protected ArrayList<? extends GcgGuiable> gcgGuiableList;
	private boolean filterParameterRequired = false;
	private boolean filterParameterRequirementWasSet = false;
	public static final String filter_id__NO_FILTER = GcgApplication.getStringResource(R.string.filter_id__no_filter);
	private String filterId;

	public GcgWidgetSpinner(Context aContext) {
		super(aContext);
		setup();
		setInitialMultiShiftState(null);
		resetMultiShiftStateAfterDictation(false);
	}

	public GcgWidgetSpinner(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
		setup();
		setInitialMultiShiftState(null);
		resetMultiShiftStateAfterDictation(false);
	}

	@Override
	public int getInputType() {
		return InputType.TYPE_CLASS_TEXT;
	}

	@Override
	@SuppressWarnings("incomplete-switch")
	protected void processCustomAttributes(Context aContext, AttributeSet anAttributeSet) {
		super.processCustomAttributes(aContext, anAttributeSet);
		processGcgLabelAttributes(aContext, anAttributeSet);
		setFilterId(filter_id__NO_FILTER);
		TypedArray aTypedArray = aContext.obtainStyledAttributes(anAttributeSet, R.styleable.GcgSpinnner);
		final int theArraySize = aTypedArray.getIndexCount();
		for (int theIndex = 0; theIndex < theArraySize; ++theIndex) {
			int theAttributeIndex = aTypedArray.getIndex(theIndex);
			switch (theAttributeIndex) {
				case R.styleable.GcgSpinnner_filterParameterRequired:
					setFilterParameterRequired(aTypedArray.getBoolean(theAttributeIndex, false));
					this.filterParameterRequirementWasSet = true;
					break;
				case R.styleable.GcgSpinnner_filterId:
					setFilterId(aTypedArray.getString(theAttributeIndex));
					if(! this.filterParameterRequirementWasSet && ! getFilterId().equals(filter_id__NO_FILTER)) {
						this.filterParameterRequired = true;
					}
					break;
			}
		}
		aTypedArray.recycle();
	}
	
	@Override
	protected boolean deferredSetup() {
		return true;
	}

	@Override
	public void setup() {
		super.setup();
		this.widgetFdkCursor = (TextView) this.widgetContainer.findViewById(R.id.widget_fdk_cursor);
		this.spinner = (GcgSpinner) this.widgetContainer.findViewById(R.id.widget_spinner);
		if(hasLabel()) {
			this.labelTextView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					GcgWidgetSpinner.this.performSpinnerClick();
					GcgWidgetSpinner.this.requestFdkConsumerFocus();
				}
			});
			this.labelTextView.setBackgroundResource(R.drawable.gcg__background_state_list__label);
		}
		this.spinner.setGcgWidgetSpinner(this);
		if(this.widgetFdkCursor != null) {
			this.spinner.setOnFocusChangeListener(new OnFocusChangeListener() {
				
				@Override
				public void onFocusChange(View aView, boolean bHasFocus) {
					if(bHasFocus) {
						GcgWidgetSpinner.this.setWidgetCursor(VISIBLE);
						GcgWidgetSpinner.this.requestFdkConsumerFocusFromTouch();
					} else {
						GcgWidgetSpinner.this.setWidgetCursor(INVISIBLE);
					}
				}
			});
			this.spinner.setOnTouchListener(new OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					
					if(GcgWidgetSpinner.this.spinner.hasFocus()) {
						GcgWidgetSpinner.this.spinner.performClick();
					}
					GcgWidgetSpinner.this.setWidgetCursor(VISIBLE);
					GcgWidgetSpinner.this.requestFdkConsumerFocusFromTouch();
					return false;
				}
			});
		}
		if(! deferDataInitialization()) {
			this.gcgGuiableList = updateGuiableList();
			this.arrayAdapter = new GcgGuiableSpinnerArrayAdapter(getContext(), new ArrayList<GcgGuiable>(this.gcgGuiableList), isDisplayOnlyDrawable());
			this.spinner.setAdapter(this.arrayAdapter);
			this.spinner.setEnabled(this.inputEnabled);
			if(! this.inputEnabled) {
				this.spinner.setBackgroundResource(R.color.gcg__widget_data__background_color__disabled);
			}
			setInitialValue();
			manageBackgroundState();
		}
	}

	protected void performSpinnerClick() {
		if(this.spinner.isEnabled()) {
			this.spinner.performClick();
		}
	}

	@Override
	public void setGcgActivity(GcgActivity aLibraryActivity) {
		super.setGcgActivity(aLibraryActivity);
		this.spinner.setGcgWidgetSpinner(this);
	}

	protected boolean isMinimumInput() {
		return getListSize() > 0 && getSelectedItem() != null;
	}
	
	public ArrayList<String> getRowStringList() {
		ArrayList<String> theRowStringList = new ArrayList<String>();
		for(GcgGuiable theGcgGuiable : this.gcgGuiableList) {
			theRowStringList.add(theGcgGuiable.getDataText());
		}
		return theRowStringList;
	}
	
	protected boolean isDisplayOnlyDrawable() {
		return false;
	}

	protected void manageBackgroundState() {
		setDataStatus(isMinimumInput());
	}

	public void setDataStatus(boolean bIsMinimumInput) {
		if(! this.inputEnabled) {
			return;
		}
		this.spinner.setBackgroundResource(bIsMinimumInput ?
				R.drawable.gcg__spinner_background :
				R.drawable.gcg__spinner_background__invalid);
	}

	public void setDataStatus() {
		setDataStatus(isMinimumInput());
	}

	public void removeCurrentSpinnerSelection() {
		int thePosition = this.spinner.getSelectedItemPosition();
		this.gcgGuiableList.remove(thePosition);
		this.arrayAdapter = new GcgGuiableSpinnerArrayAdapter(getContext(), new ArrayList<GcgGuiable>(this.gcgGuiableList), isDisplayOnlyDrawable());
		this.spinner.setAdapter(this.arrayAdapter);
		setInitialSelection(thePosition);
		manageBackgroundState();
	}

	public void updateSpinnerData() {
		updateSpinnerData(updateGuiableList());
	}
	
	public void updateSpinnerData(ArrayList<? extends GcgGuiable> aGcgGuiableList) {
		this.gcgGuiableList = aGcgGuiableList;
		this.arrayAdapter = new GcgGuiableSpinnerArrayAdapter(getContext(), new ArrayList<GcgGuiable>(this.gcgGuiableList), isDisplayOnlyDrawable());
		this.spinner.setAdapter(this.arrayAdapter);
		setInitialSelection();
		manageBackgroundState();
	}

	protected void setInitialSelection() {
		this.spinner.setSelection(0);
	}

	protected void setInitialSelection(int aSelection) {
		if(this.spinner.getCount() == 0) {
			return;
		}
		if(aSelection < this.spinner.getCount()) {
			this.spinner.setSelection(aSelection);
		} else {
			this.spinner.setSelection(this.spinner.getCount() - 1);
		}
	}

	@Override
	public void setHint(int aResourceId) {
		return;
	}

	@Override
	public void setHint(String aStringId) {
		return;
	}
	
	@Override
	protected void setTransparentBackground() {
		return;
	}

	public void setSelection(GcgGuiable aGuiable) {
		this.spinner.setSelection(this.arrayAdapter.getPosition(aGuiable));
	}

	public void setSelection(int aPosition) {
		this.spinner.setSelection(aPosition);
	}
	
	public GcgGuiable getSelectedItem() {
		return (GcgGuiable) this.spinner.getSelectedItem();
	}
	
	public int getSelectedItemPosition() {
		return this.spinner.getSelectedItemPosition();
	}
	
	@Override
	public GcgGuiable getData() {
		return getSelectedItem();
	}
	
	@Override
	public void setData(Object aGcgGuiable) {
		setSelection((GcgGuiable) aGcgGuiable);
	}

	protected abstract ArrayList<? extends GcgGuiable> updateGuiableList();

	@Override
	protected int getWidgetLayoutResourceId() {
		int theResourceId = R.layout.gcg__widget__spinner__horizontal;
		if(this.containerLayout.equals(container_layout__MENU_PARAMETER)) {
			theResourceId = R.layout.gcg__widget__spinner__menu_parameter;
		} else if(this.containerLayout.equals(container_layout__VERTICAL)) {
			theResourceId = R.layout.gcg__widget__spinner__vertical;
		} else if(this.containerLayout.equals(container_layout__VERTICAL__NO_LABEL_DRAWABLE)) {
			theResourceId = R.layout.gcg__widget__spinner__vertical;
		} else if(this.containerLayout.equals(container_layout__NO_LABEL)) {
			theResourceId = R.layout.gcg__widget__spinner__no_label;
		}
		return theResourceId;
	}
	
	public void setOnItemSelectedListener(OnItemSelectedListener aListener) {
		this.spinner.setOnItemSelectedListener(aListener);
	}

	@Override
	public void setInitialValue() {
		setSelection(0);
		setOriginalValue(getSelectedItem());
	}

	@Override
	public void setInitialValue(Object anObject) {
		setOriginalValue(anObject);
		setSelection((GcgGuiable) anObject);
	}
	
	@Override
	public String toString() {
		return getSelectedItem() == null ? "" : getSelectedItem().toString();
	}

	public int getCount() {
		return this.spinner.getAdapter().getCount();
	}
	
	public int getLogicalPosition(final int aLogicalPositionParameter, int aPositionAnchor) {
		int theListSize = this.spinner.getAdapter().getCount();
		int theNewPosition = 0;
		if(theListSize == 0 || aLogicalPositionParameter == logical_position__FIRST) {
			return theNewPosition;
		}
		switch(aLogicalPositionParameter) {
		case logical_position__NEXT:
			theNewPosition = aPositionAnchor < theListSize ? aPositionAnchor : theListSize - 1;
		break;
		case logical_position__PREVIOUS:
			theNewPosition = aPositionAnchor - 1;
			break;
		case logical_position__LAST:
			theNewPosition = theListSize - 1;
			break;
			default:
		}
		return theNewPosition;
	}

	@Override
	public void onDictationResults(ArrayList<String> aDictationResultsList) {
		FdkHostSupport.positionSpinnerListRow(aDictationResultsList, this);
	}

	@Override
	public View getViewToFocus() {
		return this.spinner;
	}
	
	@Override
	public void setFdkListener(FdkListener anFdkListener) {
		super.setFdkListener(anFdkListener);
		this.spinner.setFocusable(true);
		this.spinner.setFocusableInTouchMode(true);
	}

	@Override
	public FdkKeyboardStyle getKeyboardStyle() {
		return FdkKeyboardStyle.widget_input__SPINNER;
	}

	public ArrayList<? extends GcgGuiable> getGcgGuiableList() {
		return this.gcgGuiableList;
	}
	
	public int getListSize() {
		if(this.gcgGuiableList == null) {
			return 0;
		}
		return this.gcgGuiableList.size();
	}

	public boolean selectFirstDataText(String aDataText) {
		boolean isFound = false;
		for(GcgGuiable theGcgGuiable : this.gcgGuiableList) {
			if(theGcgGuiable.getDataText().equals(aDataText)) {
				this.spinner.setSelection(this.gcgGuiableList.indexOf(theGcgGuiable));
				isFound = true;
			}
		}
		return isFound;
	}

	public boolean filterParameterRequired() {
		return this.filterParameterRequired;
	}

	public void setFilterParameterRequired(boolean bParameterRequired) {
		this.filterParameterRequired = bParameterRequired;
	}

	public String getFilterId() {
		return this.filterId;
	}

	public void setFilterId(String aFilterId) {
		this.filterId = aFilterId;
	}
	
	@Override
	public void setEnabled(boolean bEnabled) {
		if(this.labelTextView != null) {
			this.labelTextView.setEnabled(bEnabled);
		}
		this.spinner.setEnabled(bEnabled);
	}

}
