/* @(#)GcgButtonMenu.java
** 
** Copyright (C) 2012 by Steven D. Stamps
**
**             Trademarks & Copyrights
** Flywheel Management Science(TM), Flywheel Management Model(TM),
** Flywheel Story Editor(TM) and FlywheelMS(TM) are exclusive trademarks
** of Steven D. Stamps and may only be used freely for the purpose of
** identifying the unforked version of this software.  Subsequent forks
** may not use these trademarks.  All other rights are reserved.
** and FlywheelMS(TM) are exclusive trademarks of Steven D. Stamps
** and may only be used freely for the purpose of identifying the
** unforked version of this software.  Subsequent forks (if any) may
** not use these trademarks.  All other rights are reserved.
**
** DecKanGL (Decorated Kanban Glyph Language) and TribKn (Tribal Knowledge)
** are also exclusive trademarks of Steven D. Stamps.  These may be used
** freely within the unforked FlywheelMS application and documentation.
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

import android.widget.Button;

import com.flywheelms.library.gcg.viewflipper.GcgViewFlipper;
import com.flywheelms.library.gcg.viewflipper.GcgViewFlipperListener;

public class GcgButtonMenu implements GcgViewFlipperListener {
	
	private ArrayList<Button> buttonArray;
	private int initialButtonSelection = 0;
	private int buttonSelectedColorResourceId = GcgButtonHelper.color_resource_DEFAULT_BUTTON_SELECTED;
	private int buttonNotSelectedColorRid = GcgButtonHelper.color_resource_DEFAULT_BUTTON_NOT_SELECTED;
	private int currentButtonSelection = 0;
	
	public GcgButtonMenu(ArrayList<Button> aButtonArray) {
		this(aButtonArray, 0, GcgButtonHelper.color_resource_DEFAULT_BUTTON_SELECTED, GcgButtonHelper.color_resource_DEFAULT_BUTTON_NOT_SELECTED);
	}
	
	public GcgButtonMenu(ArrayList<Button> aButtonArray, int anInitialButtonSelection) {
		this(aButtonArray, anInitialButtonSelection, GcgButtonHelper.color_resource_DEFAULT_BUTTON_SELECTED, GcgButtonHelper.color_resource_DEFAULT_BUTTON_NOT_SELECTED);
	}
	
	public GcgButtonMenu(ArrayList<Button> aButtonArray, int aButtonSelectedColorResourceId, int aButtonNotSelectedResourceId) {
		this(aButtonArray, 0, aButtonSelectedColorResourceId, aButtonNotSelectedResourceId);
	}
	
	public GcgButtonMenu(ArrayList<Button> aButtonArray, int anInitialButtonSelection, int aButtonSelectedColorResourceId, int aButtonNotSelectedResourceId) {
		this.buttonArray = aButtonArray;
		this.initialButtonSelection = anInitialButtonSelection;
		this.currentButtonSelection = this.initialButtonSelection;
		this.buttonSelectedColorResourceId = aButtonSelectedColorResourceId;
		this.buttonNotSelectedColorRid = aButtonNotSelectedResourceId;
		setButtonStateSelected(this.initialButtonSelection);
	}
	
	public ArrayList<Button> getButtonArray() {
		return this.buttonArray;
	}

	public void setButtonArray(ArrayList<Button> buttonArray) {
		this.buttonArray = buttonArray;
	}

	public int getInitialButtonSelection() {
		return this.initialButtonSelection;
	}

	public void setInitialButtonSelection(int initialButtonSelection) {
		this.initialButtonSelection = initialButtonSelection;
	}

	public int getButtonSelectedColorRid() {
		return this.buttonSelectedColorResourceId;
	}

	public void setButtonSelectedColorRid(int buttonSelectedColorRid) {
		this.buttonSelectedColorResourceId = buttonSelectedColorRid;
	}

	public int getButtonNotSelectedColorRid() {
		return this.buttonNotSelectedColorRid;
	}

	public void setButtonNotSelectedColorRid(int buttonNotSelectedColorRid) {
		this.buttonNotSelectedColorRid = buttonNotSelectedColorRid;
	}
	
	public void setButtonStateSelected(int aSelectedButton) {
		this.currentButtonSelection = aSelectedButton;
		for (Button theButton : this.buttonArray) {
			theButton.setBackgroundColor(this.buttonNotSelectedColorRid);
		}
		this.buttonArray.get(this.currentButtonSelection).setBackgroundColor(this.buttonSelectedColorResourceId);
	}
	
//	public void initializeMenuState() {
//		setButtonStateSelected(this.initialButtonSelection);
//	}
    
	// first year legacy
//    public void onClickMenuButton(ViewPager aViewPager, int aButtonSequence ) {
//    	aViewPager.setCurrentItem(aButtonSequence, true);
//    	this.setButtonStateSelected(aButtonSequence);
//    }
    
    public void onClickMenuButton(GcgViewFlipper aViewFlipper, int aButtonSequence) {
    	boolean theFlipDirection = aButtonSequence > this.currentButtonSelection ? GcgViewFlipper.FLIP_IN_FROM_RIGHT : GcgViewFlipper.FLIP_IN_FROM_LEFT;
    	int theViewIndex = aButtonSequence == 0 ? 0 : this.buttonArray.size() - aButtonSequence;
    	aViewFlipper.flipToIndex(theViewIndex, theFlipDirection);
    	this.setButtonStateSelected(aButtonSequence);
    }

	@Override
	public void onViewFlip(int aPreviousChildIndex, int aCurrentChildIndex) {
		if(aCurrentChildIndex == 0) {
			setButtonStateSelected(aCurrentChildIndex);
		} else {
			setButtonStateSelected(this.buttonArray.size() - aCurrentChildIndex);
		}
	}
	
}