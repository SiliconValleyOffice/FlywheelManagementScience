/* @(#)GcgContainerGroupBoxLinear.java
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

package com.flywheelms.gcongui.gcg.container;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flywheelms.gcongui.R;


// TODO - needs to extend and re-use GcgContainer
public class GcgContainerGroupBoxLinear extends LinearLayout {
	
	private String headingText;
    private TextView headingTextView;
	private String headingGravity;
	private int headingTextColorResourceId;
	private int headingBackgroundResourceId;
	private int borderStyle;

	public GcgContainerGroupBoxLinear(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
		processCustomAttributes(aContext, anAttributeSet);
		setup();
	}

	private void processCustomAttributes(Context aContext, AttributeSet anAttributeSet) {
		TypedArray aTypedArray = aContext.obtainStyledAttributes(anAttributeSet, R.styleable.GcgGroupBox);
		int theArraySize = aTypedArray.getIndexCount();
		for (int theIndex = 0; theIndex < theArraySize; ++theIndex) {
			int theAttributeIndex = aTypedArray.getIndex(theIndex);
			switch (theAttributeIndex) {
				case R.styleable.GcgGroupBox_borderStyle:
					this.borderStyle = aTypedArray.getInt(theAttributeIndex, 2);
					setBackgroundResourceId();
					break;
				case R.styleable.GcgGroupBox_headingText:
					this.headingText = aTypedArray.getString(theAttributeIndex);
					break;
				case R.styleable.GcgGroupBox_headingGravity:
					this.headingGravity = aTypedArray.getString(theAttributeIndex);
					break;
				default:
			}
		}
		aTypedArray.recycle();
	}

	private void setBackgroundResourceId() {
		int theBackgroundResourceId;
		switch(this.borderStyle) {
			case 1:
				theBackgroundResourceId = R.drawable.gcg__group_box__border__1;
				this.headingBackgroundResourceId = R.color.gcg__group_box__heading__background_1;
				this.headingTextColorResourceId = R.color.gcg__group_box__heading__text_1;
				break;
			case 2:
				theBackgroundResourceId = R.drawable.gcg__group_box__border__2;
				this.headingBackgroundResourceId = R.color.gcg__group_box__heading__background_2;
				this.headingTextColorResourceId = R.color.gcg__group_box__heading__text_3;
				break;
			case 3:
				theBackgroundResourceId = R.drawable.gcg__group_box__border__3;
				this.headingBackgroundResourceId = R.color.gcg__group_box__heading__background_3;
				this.headingTextColorResourceId = R.color.gcg__group_box__heading__text_3;
				break;
			default:
				theBackgroundResourceId = R.drawable.gcg__group_box__border__2;
		}
		setBackgroundResource(theBackgroundResourceId);  // will overwrite any background specified in the XML
	}

	private void setup() {
		if(this.headingText != null && this.headingText.length() > 0) {
			setPadding(3, 0, 3, 3);
			inflate(getContext(), R.layout.gcg__container__group_box__title, this);
			int theTextViewIndex = getChildCount() - 1;
			this.headingTextView = (TextView) getChildAt(theTextViewIndex);
            this.headingTextView.setBackgroundResource(this.headingBackgroundResourceId);
            this.headingTextView.setText(this.headingText);
            this.headingTextView.setTextColor(this.headingTextColorResourceId);
			if(this.headingGravity != null && this.headingGravity.equals("left")) {
                this.headingTextView.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
			}
			removeViewAt(theTextViewIndex);
			addView(this.headingTextView, 0);
		} else {
			setPadding(3, 3, 3, 3);
		}
	}

    public void setHeadingText(String aHeadingTextString) {
        this.headingTextView.setText(aHeadingTextString);
    }
}
