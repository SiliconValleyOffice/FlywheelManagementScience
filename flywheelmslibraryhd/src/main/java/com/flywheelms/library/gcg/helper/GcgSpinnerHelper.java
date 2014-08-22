/* @(#)SpinnerHelper.java
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.flywheelms.library.fmm.helper.FmmHelper;
import com.flywheelms.library.gcg.GcgApplication;
import com.flywheelms.library.gcg.interfaces.GcgGuiable;

public class GcgSpinnerHelper {

	public static void initializeGuiableSpinner(Activity anActivity, int aTextViewLabelId, int aSpinnerViewId, int aLabelTextResourceId, int aDrawableResourceId, Collection<? extends GcgGuiable> aGuiableCollection, GcgGuiable anInitialValue, int aMaxDrawableWidth) {
		TextView theTextViewLabel = (TextView) anActivity.findViewById(aTextViewLabelId);
		GuiHelper.initializeTextViewLabel(theTextViewLabel, aDrawableResourceId, aLabelTextResourceId);
		Spinner theSpinner = (Spinner) anActivity.findViewById(aSpinnerViewId);
		GcgGuiableSpinnerArrayAdapter theArrayAdapter = new GcgGuiableSpinnerArrayAdapter(GcgApplication.getContext(), new ArrayList<GcgGuiable>(aGuiableCollection), aMaxDrawableWidth);
		theSpinner.setAdapter(theArrayAdapter);
		if (anInitialValue != null) {
			theSpinner.setSelection(theArrayAdapter.getPosition(anInitialValue));
		}
	}

	public static void setInitialValueForSpinnerAdapter(Activity anActivity, int aSpinnerViewId, GcgGuiable anInitialValue) {
		Spinner theSpinner = (Spinner) anActivity.findViewById(aSpinnerViewId);
		@SuppressWarnings("unchecked")
		ArrayAdapter<GcgGuiable> theSpinnerAdapter = (ArrayAdapter<GcgGuiable>) theSpinner.getAdapter();
		if (anInitialValue != null) {
			theSpinner.setSelection(theSpinnerAdapter.getPosition(anInitialValue));
		}
	}

	public static void initializeGuiableSpinner(Activity anActivity, int aTextViewLabelId, int aSpinnerViewId, int aLabelTextResourceId, int aDrawableResourceId, Collection<? extends GcgGuiable> aGuiableCollection, GcgGuiable anInitialValue) {
		initializeGuiableSpinner(anActivity, aTextViewLabelId, aSpinnerViewId, aLabelTextResourceId, aDrawableResourceId, aGuiableCollection, anInitialValue, 0);
	}

	public static void initializeGuiableSpinner(Activity anActivity, int aTextViewLabelId, int aSpinnerViewId, int aLabelTextResourceId, int aDrawableResourceId, Collection<? extends GcgGuiable> aGuiableCollection, int aMaxDrawableWidth) {
		initializeGuiableSpinner(anActivity, aTextViewLabelId, aSpinnerViewId, aLabelTextResourceId, aDrawableResourceId, aGuiableCollection, (GcgGuiable) null, aMaxDrawableWidth);
	}

	public static void initializeGuiableSpinner(Activity anActivity, int aTextViewLabelId, int aSpinnerViewId, int aLabelTextResourceId, int aDrawableResourceId, Collection<? extends GcgGuiable> aGuiableCollection) {
		initializeGuiableSpinner(anActivity, aTextViewLabelId, aSpinnerViewId, aLabelTextResourceId, aDrawableResourceId, aGuiableCollection, (GcgGuiable) null);
	}

	public static void initializeGuiableSpinner(Activity anActivity, int aTextViewLabelId, int aSpinnerViewId, GcgGuiable aGuiableInstance, List<? extends GcgGuiable> aGuiableList, GcgGuiable anInitialValue, int aMaxDrawableWidth) {
		TextView theTextViewLabel = (TextView) anActivity.findViewById(aTextViewLabelId);
		GuiHelper.initializeTextViewLabelForGuiable(aGuiableInstance, theTextViewLabel);
		Spinner theSpinner = (Spinner) anActivity.findViewById(aSpinnerViewId);
		GcgGuiableSpinnerArrayAdapter theArrayAdapter = new GcgGuiableSpinnerArrayAdapter(GcgApplication.getContext(), new ArrayList<GcgGuiable>(aGuiableList), aMaxDrawableWidth);
		theSpinner.setAdapter(theArrayAdapter);
		if (anInitialValue != null) {
			theSpinner.setSelection(theArrayAdapter.getPosition(anInitialValue));
		}
	}

	public static void initializeGuiableSpinner(Activity anActivity, int aTextViewLabelId, int aSpinnerViewId, GcgGuiable aGuiableInstance, List<? extends GcgGuiable> aGuiableList, GcgGuiable anInitialValue) {
		initializeGuiableSpinner(anActivity, aTextViewLabelId, aSpinnerViewId, aGuiableInstance, aGuiableList, anInitialValue, 0);
	}

	public static void initializeGuiableSpinner(Activity anActivity, int aTextViewLabelId, int aSpinnerViewId, GcgGuiable aGuiableInstance, List<? extends GcgGuiable> aGuiableList) {
		initializeGuiableSpinner(anActivity, aTextViewLabelId, aSpinnerViewId, aGuiableInstance, aGuiableList, (GcgGuiable) null);
	}

	public static void initializeGuiableSpinner(Activity anActivity, int aTextViewLabelId, int aSpinnerViewId, GcgGuiable aGuiableInstance, GcgGuiable[] aGuiableArray, int aMaxDrawableWidth) {
		initializeGuiableSpinner(anActivity, aTextViewLabelId, aSpinnerViewId, aGuiableInstance, aGuiableArray, (GcgGuiable) null, aMaxDrawableWidth);
	}

	public static void initializeGuiableSpinner(Activity anActivity, int aTextViewLabelId, int aSpinnerViewId, GcgGuiable aGuiableInstance, GcgGuiable[] aGuiableArray, GcgGuiable anInitialValue, int aMaxDrawableWidth) {
		initializeGuiableSpinner(anActivity, aTextViewLabelId, aSpinnerViewId, aGuiableInstance, Arrays.asList(aGuiableArray), anInitialValue, aMaxDrawableWidth);
	}

	public static void initializeGuiableSpinner(Activity anActivity, int aTextViewLabelId, int aSpinnerViewId, GcgGuiable aGuiableInstance, GcgGuiable[] aGuiableArray) {
		initializeGuiableSpinner(anActivity, aTextViewLabelId, aSpinnerViewId, aGuiableInstance, aGuiableArray, (GcgGuiable) null, 0);
	}

	public static void setOnItemSelectedListener(Activity anActivity, int aSpinnerResourceId, OnItemSelectedListener anOnItemSelectedListener) {
		Spinner theSpinner = (Spinner) anActivity.findViewById(aSpinnerResourceId);
		theSpinner.setOnItemSelectedListener(anOnItemSelectedListener);
	}

	class TemplateSpinnerListener implements OnItemSelectedListener {
		Toast toast;

		@Override
		public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
			this.toast = Toast.makeText(FmmHelper.getContext(), "Item selected at position: " + position, Toast.LENGTH_LONG);
			this.toast.show();
		}

		@Override
		public void onNothingSelected(AdapterView<?> parentView){
			this.toast = Toast.makeText(FmmHelper.getContext(), "Nothing selected", Toast.LENGTH_LONG);
			this.toast.show();
		}

	}

	public class OnItemSelectedListenerWrapper implements OnItemSelectedListener {

		private int lastPosition;
		private OnItemSelectedListener listener;

		public OnItemSelectedListenerWrapper(OnItemSelectedListener aListener){
			this.lastPosition = 0;
			this.listener = aListener;
		}

		@Override
		public void onItemSelected(AdapterView<?> aParentView, View aView, int aPosition, long anId) {
			if (this.lastPosition == aPosition) {
				Toast theToast = Toast.makeText(FmmHelper.getContext(), "Ignoring onItemSelected for same position: " + aPosition, Toast.LENGTH_LONG);
				theToast.show();
			}
			else {
				Toast theToast = Toast.makeText(FmmHelper.getContext(), "Processing new item selected: " + aPosition, Toast.LENGTH_LONG);
				theToast.show();
				this.listener.onItemSelected(aParentView, aView, aPosition, anId);
			}
			this.lastPosition = aPosition;
		}

		@Override
		public void onNothingSelected(AdapterView<?> aParentView) {
			this.listener.onNothingSelected(aParentView);
		}
	}

}
