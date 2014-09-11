/* @(#)GuiableSpinnerArrayAdapter.java
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

package com.flywheelms.library.gcg.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flywheelms.library.R;
import com.flywheelms.library.gcg.interfaces.GcgGuiable;

import java.util.ArrayList;
import java.util.List;

public class GcgGuiableSpinnerArrayAdapter extends ArrayAdapter<GcgGuiable> {

	private static final int row_view_layout__DRAWABLE_AND_DATA = R.layout.gcg__spinner__row_layout;
	private static final int selection_view_layout__DRAWABLE_AND_DATA = R.layout.gcg__spinner__selection_layout;
	private static final int view_group_position_TEXT = 0;
	private int maxDrawawbleWidth = 0;
	private boolean onlyDrawableSelection = false;
	
	public GcgGuiableSpinnerArrayAdapter(Context aContext, ArrayList<GcgGuiable> aGuiableArray) {
		super(aContext, row_view_layout__DRAWABLE_AND_DATA, aGuiableArray);
	}
	
	public GcgGuiableSpinnerArrayAdapter(Context aContext, ArrayList<GcgGuiable> aGuiableArray, boolean aBoolean) {
		super(aContext, row_view_layout__DRAWABLE_AND_DATA, aGuiableArray);
		this.onlyDrawableSelection = aBoolean;
	}
	
	public GcgGuiableSpinnerArrayAdapter(Context aContext, GcgGuiable[] aGuiableArray) {
		super(aContext, row_view_layout__DRAWABLE_AND_DATA, aGuiableArray);
	}
	
	public GcgGuiableSpinnerArrayAdapter(Context aContext, GcgGuiable[] aGuiableArray, boolean aBoolean) {
		super(aContext, row_view_layout__DRAWABLE_AND_DATA, aGuiableArray);
		this.onlyDrawableSelection = aBoolean;
	}
	
	public GcgGuiableSpinnerArrayAdapter(Context aContext, List<GcgGuiable> aGuiableList) {
		super(aContext, row_view_layout__DRAWABLE_AND_DATA, aGuiableList);
	}
	
	public GcgGuiableSpinnerArrayAdapter(Context aContext, List<GcgGuiable> aGuiableList, int aMaxDrawableWidth) {
		super(aContext, row_view_layout__DRAWABLE_AND_DATA, aGuiableList);
		this.maxDrawawbleWidth = aMaxDrawableWidth;
	}
	
	@Override
	public View getView(int aPosition, View anExistingView, ViewGroup aParentView) {
		if(this.onlyDrawableSelection) {
			return getDrawableView(aPosition, anExistingView);
		}
		LinearLayout theRowView;
		if(anExistingView == null) {
			theRowView = new LinearLayout(getContext());
			LayoutInflater theLayoutInflater =
					(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			theLayoutInflater.inflate(selection_view_layout__DRAWABLE_AND_DATA, theRowView, true);
		} else {
			theRowView = (LinearLayout)anExistingView;
		}
		GcgGuiable theGuiable = getItem(aPosition);
		TextView theTextView = (TextView)theRowView.getChildAt(view_group_position_TEXT);
		theTextView.setText(theGuiable.getDataText());
		theTextView.setCompoundDrawablesWithIntrinsicBounds(theGuiable.getDataDrawable(), null, null, null);
		if(this.maxDrawawbleWidth == 0) {
			GuiHelper.setCompoundDrawablePadding(theTextView);
		} else {
			GuiHelper.setCompoundDrawablePadding(theTextView, theGuiable.getDataDrawable());
		}
		return theRowView;
	}

	private View getDrawableView(int aPosition, View anExistingView) {
		LinearLayout theRowView;
		if(anExistingView == null) {
			theRowView = new LinearLayout(getContext());
			LayoutInflater theLayoutInflater =
					(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			theLayoutInflater.inflate(row_view_layout__DRAWABLE_AND_DATA, theRowView, true);
		} else {
			theRowView = (LinearLayout)anExistingView;
		}
		GcgGuiable theGuiable = getItem(aPosition);
		TextView theTextView = (TextView)theRowView.getChildAt(view_group_position_TEXT);
		theTextView.setText("");
		theTextView.setCompoundDrawablesWithIntrinsicBounds(theGuiable.getDataDrawable(), null, null, null);
		if(this.maxDrawawbleWidth == 0) {
			GuiHelper.setCompoundDrawablePadding(theTextView);
		} else {
			GuiHelper.setCompoundDrawablePadding(theTextView, theGuiable.getDataDrawable());
		}
		return theRowView;
	}

	@Override
	public View getDropDownView (int aPosition, View anExistingView, ViewGroup aParentView) {
		LinearLayout theRowView;
		if(anExistingView == null) {
			theRowView = new LinearLayout(getContext());
			LayoutInflater theLayoutInflater =
					(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			theLayoutInflater.inflate(row_view_layout__DRAWABLE_AND_DATA, theRowView, true);
		} else {
			theRowView = (LinearLayout)anExistingView;
		}
		GcgGuiable theGuiable = getItem(aPosition);
		TextView theTextView = (TextView)theRowView.getChildAt(view_group_position_TEXT);
		theTextView.setText(theGuiable.getDataText());
		theTextView.setCompoundDrawablesWithIntrinsicBounds(theGuiable.getDataDrawable(), null, null, null);
		if(this.maxDrawawbleWidth == 0) {
			GuiHelper.setCompoundDrawablePadding(theTextView);
		} else {
			GuiHelper.setCompoundDrawablePadding(theTextView, theGuiable.getDataDrawable());
		}
		return theRowView;
	}
	
}
