/* @(#)GcgWidgetCheckboxThumbpadRight.java
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
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;

import com.flywheelms.gcongui.R;
import com.flywheelms.gcongui.fdk.enumerator.FdkKeyboardStyle;

import java.util.ArrayList;

// com.flywheelms.gcongui.gcg.widget.GcgWidgetCheckboxThumbpadRight
public class GcgWidgetCheckboxThumbpadRight extends GcgWidget {

    private static final int child_index__CHECKBOX = 1;
    protected CheckBox checkBox;
    protected boolean initialValue;

    public GcgWidgetCheckboxThumbpadRight(Context aContext) {
        super(aContext);
    }

    public GcgWidgetCheckboxThumbpadRight(Context aContext, AttributeSet anAttributeSet) {
        super(aContext, anAttributeSet);
    }

    public GcgWidgetCheckboxThumbpadRight(Context aContext, AttributeSet anAttributeSet, int aStyleDefinition) {
        super(aContext, anAttributeSet, aStyleDefinition);
    }

    protected void processCustomAttributes(Context aContext, AttributeSet anAttributeSet) {
        processGcgLabelAttributes(aContext, anAttributeSet);
        TypedArray aTypedArray = aContext.obtainStyledAttributes(anAttributeSet, R.styleable.GcgCheckbox);
        final int theArraySize = aTypedArray.getIndexCount();
        for (int theIndex = 0; theIndex < theArraySize; ++theIndex) {
            int theAttributeIndex = aTypedArray.getIndex(theIndex);
            if (theAttributeIndex == R.styleable.GcgCheckbox_isChecked) {
                this.initialValue = aTypedArray.getBoolean(theAttributeIndex, false);
            }
        }
        aTypedArray.recycle();
    }

    @Override
    protected void setup() {
        super.setup();
        this.checkBox = (CheckBox) this.widgetContainer.getChildAt(child_index__CHECKBOX);
        this.checkBox.setChecked(this.initialValue);
        this.labelTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                GcgWidgetCheckboxThumbpadRight.this.checkBox.performClick();
            }
        });
        // labelText
        // enabled
        // isChecked
    }

    public void setBaselineValue(String aBaselineValue) {
        this.baselineValue = aBaselineValue;
        this.checkBox.setChecked(new Boolean(aBaselineValue));
    }

    public void setBaselineValue() {
        setBaselineValue(this.checkBox.isChecked() ? "true" : "false");
    }

    public Boolean getBaselineValueAsBoolean() {
        return new Boolean((String) this.baselineValue);
    }

    public boolean isModified() {
        return ! getBaselineValue().equals(getData());
    }

    @Override
    public String getData() {
        return this.checkBox.isChecked() ? "true" : "false";
    }

    @Override
    public void setData(Object anObject) {
        this.checkBox.setChecked(new Boolean((String) anObject));
    }

    public void setData(boolean aBoolean) {
        this.checkBox.setChecked(aBoolean);
    }

    public void setData(String aString) {
        this.checkBox.setChecked(new Boolean(aString));
    }

    @Override
    protected int getWidgetLayoutResourceId() {
        return R.layout.gcg__widget__checkbox__thumbpad_right;
    }

    @Override
    public void setHint(int aResourceId) {

    }

    @Override
    public void setHint(String aString) {

    }

    @Override
    public String toString() {
        return getData();
    }

    public CheckBox getDataView() {
        return this.checkBox;
    }

    @Override
    public View getViewToFocus() {
        return getDataView();
    }

    @Override
    public FdkKeyboardStyle getKeyboardStyle() {
        return null;
    }

    @Override
    public void onDictationResults(ArrayList<String> aDictationResultsList) {

    }

    @Override
    public void setEnabled(boolean bEnable) {
        this.checkBox.setEnabled(bEnable);
    }

}
