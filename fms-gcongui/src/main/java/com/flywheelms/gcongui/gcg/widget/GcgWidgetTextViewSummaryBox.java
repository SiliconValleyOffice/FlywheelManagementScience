/* @(#)GcgWidgetTextViewSummaryBox.java
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
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import com.flywheelms.gcongui.R;
import com.flywheelms.gcongui.fdk.enumerator.FdkKeyboardStyle;

import java.util.ArrayList;

// com.flywheelms.gcongui.gcg.widget.GcgWidgetTextViewSummaryBox
public class GcgWidgetTextViewSummaryBox extends GcgWidget {
    
    private EditText editText;

	public GcgWidgetTextViewSummaryBox(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
	}

	@Override
	protected String getLabelText() {
		return this.labelTextString == null ? "Summary" : this.labelTextString;
	}

	@Override
	protected int getWidgetLayoutResourceId() {
		int theResourceId = R.layout.gcg__widget__text_view__summary_box;
		return theResourceId;
	}

    @Override
    protected void setup() {
        super.setup();
        this.editText = (EditText) this.widgetContainer.findViewById(R.id.widget_data);
        if(this.isTransparentBackground) {
            setTransparentBackground();
        }
        setInitialValue();
        manageBackgroundState();
    }

    protected void manageBackgroundState() {
        this.editText.setBackgroundResource(isMinimumInput() ?
                this.isTransparentBackground ? R.color.gcg__transparent : R.drawable.gcg__edit_text :
                R.drawable.gcg__edit_text__invalid);
    }

    protected boolean isMinimumInput() {
        return isMinimumLength();
    }

    protected boolean isMinimumLength() {
        return true;
    }

    @Override
    protected void setTransparentBackground() {
        this.editText.setBackgroundResource(R.color.gcg__transparent);
    }

    @Override
    public void setHint(int aResourceId) {

    }

    @Override
    public void setHint(String aString) {

    }

    @Override
    public Object getData() {
        return null;
    }

    @Override
    public void setData(Object anObject) {

    }

    public CharSequence getText() {
        return this.editText.getText();
    }

    public void setText(CharSequence aCharSequence) {
        this.editText.setText(aCharSequence);
    }

    public void setText(String aString) {
        this.editText.setText(aString);
    }

    public void setText(int aStringResourceId) {
        this.editText.setText(aStringResourceId);
    }

    @Override
    public void setOnClickListener(OnClickListener anOnClickListener) {
        WrappedOnClickListener theWrappedOnClickListener = new WrappedOnClickListener(this, anOnClickListener);
        super.setOnClickListener(theWrappedOnClickListener);
        if (!this.editText.isClickable()) {
            this.editText.setClickable(true);
        }
        this.editText.setOnClickListener(theWrappedOnClickListener);
        if (this.labelTextView != null) {
            if (!this.labelTextView.isClickable()) {
                this.labelTextView.setClickable(true);
            }
            this.labelTextView.setOnClickListener(theWrappedOnClickListener);
        }
        if(this.copyButton != null) {
            this.copyButton.setOnClickListener(theWrappedOnClickListener);
        } else {
            setBackgroundResource(R.drawable.gcg__background_state_list__text_view);
            this.editText.setBackgroundResource(R.drawable.gcg__background_state_list__text_view);
            if (this.labelTextView != null) {
                this.labelTextView.setBackgroundResource(R.drawable.gcg__background_state_list__text_view);
            }
        }
    }

    @Override
    public View getViewToFocus() {
        return null;
    }

    @Override
    public FdkKeyboardStyle getKeyboardStyle() {
        return null;
    }

    @Override
    public void onDictationResults(ArrayList<String> aDictationResultsList) {

    }

    public void setHeadingText(String aHeadingText) {
        setLabelText(aHeadingText);
    }
}
