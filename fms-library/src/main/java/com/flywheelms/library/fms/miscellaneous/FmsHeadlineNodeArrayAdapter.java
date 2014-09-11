/* @(#)FmsHeadlineNodeArrayAdapter.java
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

package com.flywheelms.library.fms.miscellaneous;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.flywheelms.library.R;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;
import com.flywheelms.library.gcg.helper.GuiHelper;

import java.util.ArrayList;

public class FmsHeadlineNodeArrayAdapter extends ArrayAdapter<FmmHeadlineNode> {

	private static final int ROW_LAYOUT_RESOURCE_ID = R.layout.gcg__spinner__row_layout;
	private static final int SELECTION_LAYOUT_RESOURCE_ID = R.layout.gcg__spinner__selection_layout;
	private Context context;
	private ArrayList<FmmHeadlineNode> headlineNodeList;

	public FmsHeadlineNodeArrayAdapter(Context aContext, ArrayList<FmmHeadlineNode> aHeadlineNodeList) {
		super(aContext, ROW_LAYOUT_RESOURCE_ID, aHeadlineNodeList);
		this.context = aContext;
		this.headlineNodeList = aHeadlineNodeList;
	}

	@Override
	public View getView(int aListPosition, View anExistingRowView, ViewGroup aViewGroupParent) {
		View theView = anExistingRowView;
		if (theView == null) {
			LayoutInflater theLayoutInflater = LayoutInflater.from(this.context);
			theView = theLayoutInflater.inflate(SELECTION_LAYOUT_RESOURCE_ID, null);  
		}
		FmmHeadlineNode theListItem = this.headlineNodeList.get(aListPosition);
		TextView theTextView = (TextView) theView.findViewById(R.id.selection_text);
		theTextView.setText(theListItem.getHeadline());
		Drawable theDrawable = theListItem.getDecKanGlElementNounStateBitmapDrawable();
		theTextView.setCompoundDrawablesWithIntrinsicBounds(theDrawable, null, null, null);
		GuiHelper.setCompoundDrawablePadding(theTextView, theDrawable);
		return theView;
	}

	@Override
	public View getDropDownView(int aListPosition, View anExistingRowView, ViewGroup aViewGroupParent) {
		View theView = anExistingRowView;
		if (theView == null) {
			LayoutInflater theLayoutInflater = LayoutInflater.from(this.context);
			theView = theLayoutInflater.inflate(ROW_LAYOUT_RESOURCE_ID, null);  
		}
		FmmHeadlineNode theListItem = this.headlineNodeList.get(aListPosition);
		TextView theTextView = (TextView) theView.findViewById(R.id.row_text);
		theTextView.setText(theListItem.getHeadline());
		Drawable theDrawable = theListItem.getDecKanGlElementNounStateBitmapDrawable();
		theTextView.setCompoundDrawablesWithIntrinsicBounds(theDrawable, null, null, null);
		GuiHelper.setCompoundDrawablePadding(theTextView, theDrawable);
		return theView;
	}

}
