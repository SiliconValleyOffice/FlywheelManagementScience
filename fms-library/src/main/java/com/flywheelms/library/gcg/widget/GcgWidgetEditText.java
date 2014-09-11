/* @(#)GcgWidgetEditText.java
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

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import com.flywheelms.library.R;
import com.flywheelms.library.fdk.FdkHostSupport;
import com.flywheelms.library.fdk.enumerator.FdkKeyboardStyle;
import com.flywheelms.library.fmm.node.impl.enumerator.CompletableWorkStatus;
import com.flywheelms.library.gcg.GcgActivity;
import com.flywheelms.library.gcg.button.multi_shift.GcgMultiShiftState;
import com.flywheelms.library.gcg.interfaces.GcgGuiable;

import java.util.ArrayList;

public abstract class GcgWidgetEditText extends GcgWidget {

	private static final int child_index__EDIT_TEXT = 2;
	protected GcgEditText editText;

	public GcgWidgetEditText(Context aContext) {
		super(aContext);
		setInitialMultiShiftState(GcgMultiShiftState.LOWER_CASE);
		resetMultiShiftStateAfterDictation(true);
	}

	public GcgWidgetEditText(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
		setInitialMultiShiftState(GcgMultiShiftState.LOWER_CASE);
		resetMultiShiftStateAfterDictation(true);
	}

	public GcgWidgetEditText(Context aContext, AttributeSet anAttributeSet, int aStyleDefinition) {
		super(aContext, anAttributeSet, aStyleDefinition);
		setInitialMultiShiftState(GcgMultiShiftState.LOWER_CASE);
		resetMultiShiftStateAfterDictation(true);
	}   

	@Override
	public int getInputType() {
		return InputType.TYPE_CLASS_TEXT;
	}
	
	@Override
	public void setFdkCursorEnabled(boolean aBoolean) {
		super.setFdkCursorEnabled(aBoolean);
		if(this.widgetFdkCursor != null) {
			this.widgetFdkCursor.setVisibility(View.INVISIBLE);
		}
	}

	@Override
	protected void setup() {
		super.setup();
		if(hasLabel()) {
			getWidgetFdkCursorView(child_index__EDIT_TEXT - 1);
			int theViewIndex = this.widgetFdkCursor == null ? child_index__EDIT_TEXT - 1 : child_index__EDIT_TEXT;
			this.editText = (GcgEditText) this.widgetContainer.getChildAt(theViewIndex);
		} else {
			getWidgetFdkCursorView(0);
			int theViewIndex = this.widgetFdkCursor == null ? 0 : 1;
			this.editText = (GcgEditText) this.widgetContainer.getChildAt(theViewIndex);
		}
		if(this.widgetFdkCursor != null) {
			this.editText.setOnFocusChangeListener(new OnFocusChangeListener() {

				@Override
				public void onFocusChange(View aView, boolean bHasFocus) {
					if(bHasFocus) {
						GcgWidgetEditText.this.setWidgetCursor(VISIBLE);
					} else {
						GcgWidgetEditText.this.setWidgetCursor(INVISIBLE);
					}
				}
			});
		}
		this.editText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				GcgWidgetEditText.this.manageBackgroundState();
			}
		});
		if(! this.inputEnabled) {
			this.editText.setEnabled(false);
		}
		setInitialValue();
		manageBackgroundState();
	}
	
	@Override
	public void setGcgActivity(GcgActivity aLibraryActivity) {
		super.setGcgActivity(aLibraryActivity);
		this.editText.setGcgWidgetEditText(this);
	}

	public void manageBackgroundState() {
		setDataStatus(isMinimumInput());
	}

	protected void clearInput() {
		this.editText.setText("");
	}

	public boolean isMinimumInput() {
		return inputRequired() ? isMinimumLength() : true;
	}
	
	public boolean isMinimumLength() {
		if(this.editText.getText().length() == 0 && getFdkListener() != null) {
			getFdkListener().fdkResetMultiShiftState();
		}
		return this.editText.getText().length() >= this.minimumDataLength || ! this.inputEnabled;
	}

	@Override
	public void setHint(int aResourceId) {
		this.editText.setHint(aResourceId);
	}

	@Override
	public void setHint(String aString) {
		this.editText.setHint(aString);
	}

	@Override
	public String getData() {
		return this.editText.getText().toString();
	}

	@Override
	public void setData(Object aGcgGuiable) {
		this.editText.setText((String) aGcgGuiable);
	}

	public CharSequence getText() {
		return this.editText.getText();
	}

	public void setText(CharSequence aString) {
		this.editText.setText(aString);
		notifyOnSetTextListenerList(aString.toString());
	}

	public void setText(int aStringResourceId) {
		this.editText.setText(aStringResourceId);
		notifyOnSetTextListenerList(this.editText.getText().toString());
	}

	protected GcgGuiable[] getGuiableList() {
		return CompletableWorkStatus.values();
	}

	@Override
	protected int getWidgetLayoutResourceId() {
		int theResourceId = R.layout.gcg__widget__edit_text__horizontal;
		if(this.containerLayout.equals(container_layout__VERTICAL)) {
			theResourceId = R.layout.gcg__widget__edit_text__vertical;
		} else if(this.containerLayout.equals(container_layout__VERTICAL__NO_LABEL_DRAWABLE)) {
			theResourceId = R.layout.gcg__widget__edit_text__vertical;
		} else if(this.containerLayout.equals(container_layout__NO_LABEL)) {
			theResourceId = R.layout.gcg__widget__edit_text__no_label;
		}
		return theResourceId;
	}

	@Override
	public String toString() {
		return this.editText.getText().toString();
	}

	public void addTextChangedListener(TextWatcher aListener) {
		this.editText.addTextChangedListener(aListener);
	}

	public void setDataStatus(boolean bIsMinimumInput) {
		this.editText.setBackgroundResource(bIsMinimumInput ? R.drawable.gcg__background_state_list__edit_text : R.drawable.gcg__background_state_list__edit_text__invalid);
	}

	public void setDataStatus() {
		setDataStatus(isMinimumInput());
	}

	public EditText getDataView() {
		return this.editText;
	}

	@Override
	public void onDictationResults(ArrayList<String> aDictationResultsList) {
		FdkHostSupport.insertDictationResultsIntoEditText(aDictationResultsList, this.editText);
	}

	@Override
	public View getViewToFocus() {
		return getDataView();
	}

	@Override
	public FdkKeyboardStyle getKeyboardStyle() {
		return FdkKeyboardStyle.widget_input__EDIT_TEXT;
	}

	@Override
	public GcgMultiShiftState getMultiShiftState() {
		if(this.activeMultiShiftState == null) {
			this.activeMultiShiftState = getInitialMultiShiftState();
		}
		return this.activeMultiShiftState;
	}
	
	@Override
	public void setMultiShiftState(GcgMultiShiftState aGcgMultiShiftState) {
		this.activeMultiShiftState = aGcgMultiShiftState;
	}
	
	public void setSelection(int aPosition) {
		this.editText.setSelection(aPosition);
	}

	public void setSelectionAtEnd() {
		this.editText.setSelection(this.editText.getText().toString().length());
	}

	public int getSelectionStart() {
		return this.editText.getSelectionStart();
	}

	public void insert(int aSelectionStart, CharSequence aCharSequence) {
		this.editText.getText().insert(aSelectionStart, aCharSequence);
	}
	
	@Override
	public void setEnabled(boolean bEnable) {
		this.editText.setEnabled(bEnable);
		this.labelTextView.setEnabled(bEnable);
		setDataStatus(bEnable == false ? true : isMinimumInput());
		if(! bEnable) {
			this.editText.setBackgroundResource(R.color.silver);
		}
	}
	
	public void inputEnabled(boolean bEnabled) {
		this.inputEnabled = bEnabled;
	}

}
