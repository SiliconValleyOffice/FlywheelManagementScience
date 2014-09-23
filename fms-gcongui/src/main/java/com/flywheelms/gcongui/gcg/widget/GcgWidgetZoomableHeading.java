/* @(#)GcgWidgetZoomableHeading.java
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
import android.widget.Button;

import com.flywheelms.gcongui.R;
import com.flywheelms.gcongui.fdk.enumerator.FdkKeyboardStyle;

import java.util.ArrayList;

// com.flywheelms.gcongui.gcg.widget.GcgWidgetZoomableHeading
public class GcgWidgetZoomableHeading extends GcgWidget {

	public GcgWidgetZoomableHeading(Context aContext) {
		super(aContext);
	}

	public GcgWidgetZoomableHeading(Context aContext,
			AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
	}

	public GcgWidgetZoomableHeading(Context aContext,
			AttributeSet anAttributeSet, int aStyleDefinition) {
		super(aContext, anAttributeSet, aStyleDefinition);
	}
	
	public void setText(CharSequence aCharSequence) {
		this.labelTextView.setText(aCharSequence);
	}

	@Override
	protected void setup() {
		super.setup();
		this.zoomButton = (Button) this.widgetContainer.findViewById(R.id.heading__zoom__button);
	}
	
    @Override
	public void setOnClickListener(OnClickListener anOnClickListener) {
    	WrappedOnClickListener theWrappedOnClickListener = new WrappedOnClickListener(this, anOnClickListener);
    	super.setOnClickListener(theWrappedOnClickListener);
		this.labelTextView.setClickable(true);
    	this.labelTextView.setOnClickListener(theWrappedOnClickListener);
    	this.zoomButton.setOnClickListener(theWrappedOnClickListener);
    }

	@Override
	protected int getWidgetLayoutResourceId() {
		return R.layout.gcg__widget__zoomable_heading;
	}

	@Override
	public View getViewToFocus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FdkKeyboardStyle getKeyboardStyle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onDictationResults(ArrayList<String> aDictationResultsList) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setHint(int aResourceId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setHint(String aString) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setData(Object anObject) {
		// TODO Auto-generated method stub

	}

}
