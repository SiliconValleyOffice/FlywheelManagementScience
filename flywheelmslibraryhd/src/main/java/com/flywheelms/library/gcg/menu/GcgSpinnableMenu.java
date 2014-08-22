/* @(#)GcgSpinnableMenu.java
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
package com.flywheelms.library.gcg.menu;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.flywheelms.library.R;
import com.flywheelms.library.gcg.interfaces.GcgSpinnableMenuKeycodeListener;

public class GcgSpinnableMenu {
	
	private final Context context;
	private Spinner headingSpinner;
	protected ViewGroup menuContainer;
	private int menuHeadingArrayResourceId = 0;
	private CharSequence[] menuHeadingCharSequenceArray;
	private int headingLayoutResourceId;
	private boolean manageMenuCurtain = true;
	private View menuCurtain;
	private int decoratorOrientation;
	private final int[] menuBodyResourceIdArray;
	private final ArrayList<RelativeLayout> menuBodyArray = new ArrayList<RelativeLayout>();
	private final ArrayList<Integer> spinnerBackgroundDrawableResourceIdArray = new ArrayList<Integer>();
	public static final int DECORATORS_LEFT = 1;
	public static final int DECORATORS_RIGHT = 2;
	private final ArrayList<View> viewBodyArray = new ArrayList<View>();
	private final GcgSpinnableMenuKeycodeListener keycodeListener;
	
	public GcgSpinnableMenu(
			Context aContext,
			ViewGroup aMenuContainer,
			int aDecoratorOrientation,
			int aMenuHeadingSpinnerResourceId,
			int aMenuHeadingArrayResourceId,
			int[] aMenuBodyResourceIdArray ) {
		this(aContext, aMenuContainer, aDecoratorOrientation, aMenuHeadingSpinnerResourceId, aMenuHeadingArrayResourceId, null, aMenuBodyResourceIdArray, null);
	}

	
	// currently used for Frame menus
	public GcgSpinnableMenu(
			Context aContext,
			ViewGroup aMenuContainer,
			int aDecoratorOrientation,
			int aMenuHeadingSpinnerResourceId,
			CharSequence[] aMenuHeadingStringList,
			int[] aMenuBodyResourceIdArray ) {
		this(aContext, aMenuContainer, aDecoratorOrientation, aMenuHeadingSpinnerResourceId, 0, aMenuHeadingStringList, aMenuBodyResourceIdArray, null);
	}
	
	public GcgSpinnableMenu(
			Context aContext,
			ViewGroup aMenuContainer,
			int aDecoratorOrientation,
			int aMenuHeadingSpinnerResourceId,
			int aMenuHeadingArrayResourceId,
			CharSequence[] aMenuHeadingCharSequenceArray,
			int[] aMenuBodyResourceIdArray,
			GcgSpinnableMenuKeycodeListener aKeycodeListener ) {
		this.context = aContext;
		this.menuContainer = aMenuContainer;
		this.decoratorOrientation = aDecoratorOrientation;
		this.menuBodyResourceIdArray = aMenuBodyResourceIdArray;
		spinnerBackgroundSetup();
		this.headingSpinner = (Spinner) this.menuContainer.findViewById(aMenuHeadingSpinnerResourceId);
		this.menuHeadingArrayResourceId = aMenuHeadingArrayResourceId;
		this.menuHeadingCharSequenceArray = aMenuHeadingCharSequenceArray;
		menuSetup();
		this.keycodeListener = aKeycodeListener;
	}

	private void spinnerBackgroundSetup() {
		if(this.decoratorOrientation == DECORATORS_LEFT) {
			switch(this.menuBodyResourceIdArray.length) {
			case 2:
				this.spinnerBackgroundDrawableResourceIdArray.add(R.drawable.background_state_list__spinnable_menu__1_of_2__left);
				this.spinnerBackgroundDrawableResourceIdArray.add(R.drawable.background_state_list__spinnable_menu__2_of_2__left);
				break;
			case 3:
				this.spinnerBackgroundDrawableResourceIdArray.add(R.drawable.background_state_list__spinnable_menu__1_of_3__left);
				this.spinnerBackgroundDrawableResourceIdArray.add(R.drawable.background_state_list__spinnable_menu__2_of_3__left);
				this.spinnerBackgroundDrawableResourceIdArray.add(R.drawable.background_state_list__spinnable_menu__3_of_3__left);
				break;
			case 4:
				this.spinnerBackgroundDrawableResourceIdArray.add(R.drawable.background_state_list__spinnable_menu__1_of_4__left);
				this.spinnerBackgroundDrawableResourceIdArray.add(R.drawable.background_state_list__spinnable_menu__2_of_4__left);
				this.spinnerBackgroundDrawableResourceIdArray.add(R.drawable.background_state_list__spinnable_menu__3_of_4__left);
				this.spinnerBackgroundDrawableResourceIdArray.add(R.drawable.background_state_list__spinnable_menu__4_of_4__left);
				break;
			case 5:
				this.spinnerBackgroundDrawableResourceIdArray.add(R.drawable.background_state_list__spinnable_menu__1_of_5__left);
				this.spinnerBackgroundDrawableResourceIdArray.add(R.drawable.background_state_list__spinnable_menu__2_of_5__left);
				this.spinnerBackgroundDrawableResourceIdArray.add(R.drawable.background_state_list__spinnable_menu__3_of_5__left);
				this.spinnerBackgroundDrawableResourceIdArray.add(R.drawable.background_state_list__spinnable_menu__4_of_5__left);
				this.spinnerBackgroundDrawableResourceIdArray.add(R.drawable.background_state_list__spinnable_menu__5_of_5__left);
				break;
			default:
				break;
			}
		} else {
			switch(this.menuBodyResourceIdArray.length) {
			case 2:
				this.spinnerBackgroundDrawableResourceIdArray.add(R.drawable.background_state_list__spinnable_menu__1_of_2__right);
				this.spinnerBackgroundDrawableResourceIdArray.add(R.drawable.background_state_list__spinnable_menu__2_of_2__right);
				break;
			case 3:
				this.spinnerBackgroundDrawableResourceIdArray.add(R.drawable.background_state_list__spinnable_menu__1_of_3__right);
				this.spinnerBackgroundDrawableResourceIdArray.add(R.drawable.background_state_list__spinnable_menu__2_of_3__right);
				this.spinnerBackgroundDrawableResourceIdArray.add(R.drawable.background_state_list__spinnable_menu__3_of_3__right);
				break;
			case 4:
				this.spinnerBackgroundDrawableResourceIdArray.add(R.drawable.background_state_list__spinnable_menu__1_of_4__right);
				this.spinnerBackgroundDrawableResourceIdArray.add(R.drawable.background_state_list__spinnable_menu__2_of_4__right);
				this.spinnerBackgroundDrawableResourceIdArray.add(R.drawable.background_state_list__spinnable_menu__3_of_4__right);
				this.spinnerBackgroundDrawableResourceIdArray.add(R.drawable.background_state_list__spinnable_menu__4_of_4__right);
				break;
			case 5:
				this.spinnerBackgroundDrawableResourceIdArray.add(R.drawable.background_state_list__spinnable_menu__1_of_5__right);
				this.spinnerBackgroundDrawableResourceIdArray.add(R.drawable.background_state_list__spinnable_menu__2_of_5__right);
				this.spinnerBackgroundDrawableResourceIdArray.add(R.drawable.background_state_list__spinnable_menu__3_of_5__right);
				this.spinnerBackgroundDrawableResourceIdArray.add(R.drawable.background_state_list__spinnable_menu__4_of_5__right);
				this.spinnerBackgroundDrawableResourceIdArray.add(R.drawable.background_state_list__spinnable_menu__5_of_5__right);
				break;
			default:
				break;
			}
		}
	}

	protected void menuSetup() {
		this.headingLayoutResourceId = this.decoratorOrientation == DECORATORS_LEFT ?
				R.layout.spinnable_menu__heading__left : R.layout.spinnable_menu__heading__right;
	    ArrayAdapter<CharSequence> theSpinnerAdapter = getSpinnerAdapter();
	    theSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    getHeadingSpinnner().setAdapter(theSpinnerAdapter);
	    getHeadingSpinnner().setOnItemSelectedListener(new OnItemSelectedListener() {
	    	
	    	@Override
	    	public void onItemSelected(AdapterView<?> aParentView, View aSelectedItemView, int aPosition, long anId) {
	    		processMenuHeader();
	    	}

	    	@Override
	    	public void onNothingSelected(AdapterView<?> arg0) {
	    		return;
	    	} 
	    });
		for(int theMenuBodyResourceId : this.menuBodyResourceIdArray) {
			this.menuBodyArray.add(
					(RelativeLayout) this.menuContainer.findViewById(theMenuBodyResourceId) );
		}
	}

	private ArrayAdapter<CharSequence> getSpinnerAdapter() {
		ArrayAdapter<CharSequence> theAdapter;
		if(this.menuHeadingArrayResourceId != 0) {
			theAdapter = ArrayAdapter.createFromResource(
					this.context,
					this.menuHeadingArrayResourceId,
					this.headingLayoutResourceId );
		} else {
			theAdapter = new ArrayAdapter<CharSequence>(this.context, this.headingLayoutResourceId, this.menuHeadingCharSequenceArray);
		}
		return theAdapter;
	}

	public void processMenuHeader() {
		int theHeadingSelection = getHeadingSelectionIndex();
		for(int thePosition = 0; thePosition < this.menuBodyArray.size(); ++thePosition) {
				this.menuBodyArray.get(thePosition).setVisibility(View.GONE);
		}
		this.menuBodyArray.get(theHeadingSelection).setVisibility(View.VISIBLE);
		getHeadingSpinnner().setBackgroundResource(this.spinnerBackgroundDrawableResourceIdArray.get(theHeadingSelection));
		if(this.viewBodyArray.size() > 0) {
			for(int thePosition = 0; thePosition < this.viewBodyArray.size(); ++thePosition) {
					this.viewBodyArray.get(thePosition).setVisibility(View.GONE);
			}
			this.viewBodyArray.get(theHeadingSelection).setVisibility(View.VISIBLE);
		}
		if(this.manageMenuCurtain) {
			curtainOff();
			this.manageMenuCurtain = false;
		}
	}
	
	public View getMenuCurtain() {
		if(this.menuCurtain == null) {
			this.menuCurtain = this.menuContainer.findViewById(R.id.menu_curtain);
		}
		return this.menuCurtain;
	}
	
	public void curtainOff() {
		if(getMenuCurtain() != null) {
			this.menuCurtain.setVisibility(View.GONE);
		}
	}
	
	public void curtainOn() {
		if(getMenuCurtain() != null) {
			this.menuCurtain.setVisibility(View.VISIBLE);
		}
	}
	
	public void menuCurtain(boolean aBoolean) {
		if(aBoolean) {
			curtainOn();
		} else {
			curtainOff();
		}
	}
	
	public ViewGroup getMenuContainer() {
		return this.menuContainer;
	}
	
	public int getHeadingSelectionIndex() {
		return getHeadingSpinnner().getSelectedItemPosition();
	}
	
	public int getActiveMenuIndex() {
		return getHeadingSelectionIndex();
	}
	
	public Spinner getHeadingSpinnner() {
		return this.headingSpinner;
	}

	public ArrayList<RelativeLayout> getMenuBodyArray() {
		return this.menuBodyArray;
	}

	public ArrayList<View> getViewBodyArray() {
		return this.viewBodyArray;
	}
	
	public void clickSpinner() {
		this.headingSpinner.performClick();
	}

	public void clickItem(int anItemNumber) {
		if(this.keycodeListener != null) {
			this.keycodeListener.performMenuItem(this, this.headingSpinner.getSelectedItemPosition(), anItemNumber);
		}
	}
	
	public void setSelection(int anItemNumber) {
		if(this.keycodeListener != null) {
			this.keycodeListener.performMenuItem(this, this.headingSpinner.getSelectedItemPosition(), anItemNumber);
		} else {
			this.headingSpinner.setSelection(anItemNumber);
		}
	}

}
