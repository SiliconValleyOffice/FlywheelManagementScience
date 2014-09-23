/* @(#)GcgFrameSpinner.java
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

package com.flywheelms.gcongui.gcg.menu;

import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.Button;

import com.flywheelms.gcongui.gcg.activity.GcgActivity;
import com.flywheelms.gcongui.gcg.context.GcgFrame;
import com.flywheelms.gcongui.gcg.helper.GcgPerspectiveMenu;
import com.flywheelms.gcongui.gcg.interfaces.GcgPerspective;
import com.flywheelms.gcongui.gcg.viewflipper.GcgPerspectiveFlipper;
import com.flywheelms.gcongui.gcg.viewflipper.GcgViewFlipperChildView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class GcgFrameSpinner extends GcgSpinnableMenu {
	
	private final GcgActivity libraryActivity;
	private final ArrayList<GcgFrame> frameList;
	private HashMap<GcgFrame, GcgPerspectiveMenu> perspectiveMenuMap = new HashMap<GcgFrame, GcgPerspectiveMenu>();
	private HashMap<GcgFrame, GcgPerspectiveFlipper> perspectiveFlipperMap = new HashMap<GcgFrame, GcgPerspectiveFlipper>();

	public GcgFrameSpinner(
			GcgActivity aLibraryActivity,
			ViewGroup aMenuContainer,
			int aMenuHeadingSpinnerResourceId,
			ArrayList<GcgFrame> aFrameList,
			int[] aFrameBodyResourceIdArray) {
		super(aLibraryActivity, aMenuContainer, GcgSpinnableMenu.DECORATORS_LEFT, aMenuHeadingSpinnerResourceId,
				getFrameMenuHeadingCharSequenceArray(aFrameList), aFrameBodyResourceIdArray);
		this.libraryActivity = aLibraryActivity;
		this.frameList = aFrameList;
	}
	
	public void setFrameMenuSpacer(GcgFrame aFrame, int aResourceId, Drawable aDrawable) {
		if(aDrawable == null) {
			this.perspectiveMenuMap.get(aFrame).setMenuSpacerBackgroundResourceId(aResourceId);
		} else {
			this.perspectiveMenuMap.get(aFrame).setMenuSpacerBackgroundDrawable(aDrawable);
		}
	}

	private static CharSequence[] getFrameMenuHeadingCharSequenceArray(ArrayList<GcgFrame> aFrameList) {
		CharSequence[] theHeadingCharSequenceArray = new CharSequence[aFrameList.size()];
		int theIndex = 0;
		for(GcgFrame theGcgFrame : aFrameList) {
			theHeadingCharSequenceArray[theIndex] = theGcgFrame.getHeadingCharSequence();
			++theIndex;
		}
		return theHeadingCharSequenceArray;
	}

	public void initializePerspectiveButtonList(ArrayList<Button> aPerspectiveButtonList) {
		for(Button theButton : aPerspectiveButtonList) {
			((GcgPerspectiveMenuButton)theButton).initialize(this);
		}
	}
	
	public ArrayList<GcgFrame> getFrameList() {
		return this.frameList;
	}
	
	public int getFrameIndex(GcgFrame aFrame) {
		return this.frameList.indexOf(aFrame);
	}
	
	public GcgFrame getFrame() {
		return this.frameList.get(getHeadingSelectionIndex());
	}

	public void setFrame(GcgFrame aGcgFrame) {
		setSelection(this.frameList.indexOf(aGcgFrame));
	}
	
	public void putFrame(GcgFrame aFrame, GcgPerspectiveMenu aPerspectiveMenu, GcgPerspectiveFlipper aPerspectiveFlipper) {
		aPerspectiveFlipper.initialize(this.libraryActivity, aPerspectiveMenu, aFrame, this);
		putPerspectiveMenu(aFrame, aPerspectiveMenu);
		putPerspectiveFlipper(aFrame, aPerspectiveFlipper);
	}
	
	public Collection<GcgPerspectiveFlipper> getPerspectiveFlipperCollection() {
		return this.perspectiveFlipperMap.values();
	}
	
	public void putPerspectiveFlipper(GcgFrame aFrame, GcgPerspectiveFlipper aPerspectiveFlipper) {
		this.perspectiveFlipperMap.put(aFrame, aPerspectiveFlipper);
		getViewBodyArray().add(aPerspectiveFlipper);
	}
	
	public GcgPerspectiveFlipper getPerspectiveFlipper(GcgFrame aFrame) {
		return this.perspectiveFlipperMap.get(aFrame);
	}
	
	public void putPerspectiveMenu(GcgFrame aFrame, GcgPerspectiveMenu aPerspectiveMenu) {
		initializePerspectiveButtonList(aPerspectiveMenu.getButtonArray());
		this.perspectiveMenuMap.put(aFrame, aPerspectiveMenu);
	}
	
	public GcgPerspective getPerspective() {
		return getGcgPerspectiveFlipper().getGcgPerspective();
	}

	public GcgPerspectiveFlipper getGcgPerspectiveFlipper() {
		return this.perspectiveFlipperMap.get(getFrame());
	}

	public GcgViewFlipperChildView getGcgViewFlipperView() {
		return this.perspectiveFlipperMap.get(getFrame()).getGcgViewFlipperView();
	}
	
	private GcgPerspectiveMenu getPerspectiveMenu() {
		return this.perspectiveMenuMap.get(getFrame());
	}

	public void setPerspective(GcgPerspective aPerspective) {
		getPerspectiveMenu().onClickMenuButton(
				getGcgPerspectiveFlipper(), getFrame().getPerspectiveMenuItemPosition(aPerspective));
	}
	
	public int getFramePerspectiveMenuItemIndex(GcgPerspective aPerspective) {
		return getFrame().getPerspectiveMenuItemPosition(aPerspective);
	}

	public void onClickMenuButton(int aFrameNumber, int aPerspectiveNumber) {
		if(getHeadingSelectionIndex() != aFrameNumber) {
			setSelection(aFrameNumber);
		}
		getPerspectiveMenu().onClickMenuButton(getGcgPerspectiveFlipper(), aPerspectiveNumber);
	}

	@Override
	public void processMenuHeader() {
		super.processMenuHeader();
		this.libraryActivity.updateGcgApplicationContext();
		getGcgPerspectiveFlipper().reDisplayChild();
	}
	
	public void restorePerspectiveFlippers() {
		return;
	}

	public void saveGuiState() {
		for(GcgPerspectiveFlipper thePerspectiveFlipper : this.perspectiveFlipperMap.values()) {
			thePerspectiveFlipper.saveGuiState();
		}
	}

	public void restoreGuiState() {
		for(GcgPerspectiveFlipper thePerspectiveFlipper : this.perspectiveFlipperMap.values()) {
			thePerspectiveFlipper.restoreGuiState();
		}
	}

}
