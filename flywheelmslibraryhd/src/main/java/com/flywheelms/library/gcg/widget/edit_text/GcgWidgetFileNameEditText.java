/* @(#)GcgWidgetFileNameEditText.java
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

package com.flywheelms.library.gcg.widget.edit_text;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.flywheelms.library.R;
import com.flywheelms.library.fdk.FdkHostSupport;
import com.flywheelms.library.fdk.enumerator.FdkKeyboardStyle;
import com.flywheelms.library.gcg.button.multi_shift.GcgMultiShiftState;
import com.flywheelms.library.gcg.interfaces.GcgFileNameValidator;
import com.flywheelms.library.gcg.widget.GcgWidgetEditText;

public class GcgWidgetFileNameEditText extends GcgWidgetEditText {
	
	private String fileNameExtension;
	private int minimumBaseFileNameLength;
	private GcgFileNameValidator fileNameValidator;

	public GcgWidgetFileNameEditText(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
		setInitialMultiShiftState(GcgMultiShiftState.CAMEL_CASE);
		resetMultiShiftStateAfterDictation(false);
	}

	@Override
	protected String getLabelText() {
		return this.labelTextString == null ? "File Name" : this.labelTextString;
	}

	@Override
	@SuppressWarnings("incomplete-switch")
	protected void processCustomAttributes(Context aContext, AttributeSet anAttributeSet) {
		super.processCustomAttributes(aContext, anAttributeSet);
		processGcgLabelAttributes(aContext, anAttributeSet);
		TypedArray aTypedArray = aContext.obtainStyledAttributes(anAttributeSet, R.styleable.GcgFile);
		final int theArraySize = aTypedArray.getIndexCount();
		for (int theIndex = 0; theIndex < theArraySize; ++theIndex) {
			int theAttributeIndex = aTypedArray.getIndex(theIndex);
			switch (theAttributeIndex) {
				case R.styleable.GcgFile_fileNameExtension:
					this.fileNameExtension = aTypedArray.getString(theAttributeIndex);
					break;
				case R.styleable.GcgFile_minimumBaseFileNameLength:
					this.minimumBaseFileNameLength = aTypedArray.getInteger(theAttributeIndex, 3);
					break;
			}
		}
		aTypedArray.recycle();
	}

	@Override
	protected void setup() {
		super.setup();
		resetMultiShiftStateAfterDictation(false);
	}

	@Override
	public void setInitialValue() {
		if(mustHaveFileNameExtension()) {
			this.editText.setText("." + this.fileNameExtension);
		}
	}
	
	private boolean mustHaveFileNameExtension() {
		return this.fileNameExtension != null && this.fileNameExtension.length() > 0;
	}
	
	@Override
	public boolean isMinimumLength() {
		boolean isMinimumLength = super.isMinimumLength();
		if(mustHaveFileNameExtension()) {
			isMinimumLength = this.editText.getText().length() > this.fileNameExtension.length() + this.minimumBaseFileNameLength;  // +1 for '.'
			if(this.editText.getText().length() == 0) {
				this.editText.setText("." + this.fileNameExtension);
				this.editText.setSelection(0);
				getFdkListener().fdkResetMultiShiftState();
			}
		} else {
			isMinimumLength = this.editText.getText().length() >= this.minimumBaseFileNameLength;
		}
		return isMinimumLength;
	}

	@Override
	public boolean isMinimumInput() {
		return super.isMinimumInput() && hasFileNameExtension() && ! fileNameExists();
	}

	private boolean fileNameExists() {
		if(this.fileNameValidator == null) {
			return false;
		}
		boolean aBoolean = this.fileNameValidator.fileNameExists(this.editText.getText().toString());
		return aBoolean;
	}

	private boolean hasFileNameExtension() {
		boolean hasFileNameExtension = true;
		if(mustHaveFileNameExtension()) {
			String theString = this.editText.getText().toString();
			String theExtensionString = "." + this.fileNameExtension;
			String theSubString = (String) theString.subSequence(theString.length() - (theExtensionString.length()), theString.length());
			hasFileNameExtension = theSubString.equals(theExtensionString);
		}
		return hasFileNameExtension;
	}

	@Override
	public void onDictationResults(ArrayList<String> aDictationResultsList) {
		FdkHostSupport.insertDictationResultsIntoEditText(aDictationResultsList, this.editText, false, true);
	}

	@Override
	public FdkKeyboardStyle getKeyboardStyle() {
		return FdkKeyboardStyle.widget_input__FILE_NAME;
	}
	
	public void setFileNameValidator(GcgFileNameValidator aGcgFileNameValidator) {
		this.fileNameValidator = aGcgFileNameValidator;
	}

}
