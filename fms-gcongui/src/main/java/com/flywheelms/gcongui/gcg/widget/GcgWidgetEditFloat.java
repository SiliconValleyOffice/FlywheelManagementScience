/* @(#)GcgWidgetEditInteger.java
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

package com.flywheelms.gcongui.gcg.widget;

import android.content.Context;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.util.AttributeSet;
import android.view.Gravity;

public abstract class GcgWidgetEditFloat extends GcgWidgetEditText {

	public GcgWidgetEditFloat(Context aContext) {
		super(aContext);
	}

	public GcgWidgetEditFloat(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
	}

	public GcgWidgetEditFloat(Context aContext, AttributeSet anAttributeSet, int aStyleDefinition) {
		super(aContext, anAttributeSet, aStyleDefinition);
	}

	@Override
	public int getInputType() {
		return InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL;
	}

    protected void setup() {
        super.setup();
        this.editText.setGravity(Gravity.RIGHT);
        InputFilter theInputFilter = new InputFilter() {

            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                for (int theIndex = start; theIndex < end; theIndex++) {
                    Character theCharacter = source.charAt(theIndex);
                    if (!Character.isDigit(theCharacter)) {
                        if(theCharacter.equals('.')) {
                            if(hasPoint(dest)) {
                                return "";
                            }
                        } else {
                            return "";
                        }
                    }
                }
                return null;
            }

            private boolean hasPoint(Spanned dest) {
                return dest.length() < 1 ? false : dest.toString().indexOf('.') > -1;
            }
        };
        this.editText.setFilters(new InputFilter[]{theInputFilter});
    }

    public void setInitialValue() {
        setBaselineValue("0.0");
    }

    protected void manageEmptyContents() {
//        if(this.editText.getText().toString().length() == 0) {
//            this.editText.setText("0.0");
//        }
    }

	protected void clearInput() {
		this.editText.setText("0.0");
	}

	public boolean isMinimumInput() {
//		return inputRequired() ? isMinimumLength() : true;
        return true;
	}

	public String getStringData() {
		return getData();
	}

	public float getFloatData() {
        float theFloat = 0;
        if (this.editText.getText().toString().length() > 0) {
            theFloat = Float.parseFloat(this.editText.getText().toString());
        }
        return theFloat;
    }

	public void setData(float aFloat) {
		this.editText.setText(aFloat + "");
	}

}
