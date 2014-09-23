/* @(#)FseDocumentSectionPerspective.java
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

package com.flywheelms.library.fse.perspective_flipper.perspective;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.flywheelms.gcongui.fdk.enumerator.FdkKeyboardStyle;
import com.flywheelms.gcongui.fdk.viewflipper.GcgViewFlipperChildFdkView;
import com.flywheelms.gcongui.gcg.activity.GcgActivity;
import com.flywheelms.gcongui.gcg.button.multi_shift.GcgMultiShiftKeysetController;
import com.flywheelms.gcongui.gcg.interfaces.GcgGuiable;
import com.flywheelms.gcongui.gcg.interfaces.GcgPerspectiveView;
import com.flywheelms.gcongui.gcg.viewflipper.GcgViewFlipper;
import com.flywheelms.library.R;
import com.flywheelms.library.fse.FseDocumentSectionType;
import com.flywheelms.library.fse.interfaces.FmsPerspectiveFlipperParent;
import com.flywheelms.library.fse.model.FseDocumentSection;
import com.flywheelms.library.fse.perspective_flipper.FsePerspectiveFlipper;

import java.util.ArrayList;

public abstract class FseDocumentSectionPerspective extends GcgViewFlipperChildFdkView implements GcgPerspectiveView {

	protected FsePerspectiveFlipper documentPerspectiveFlipper;
	// will be assigned by concrete implementation
	protected FseDocumentSectionType sectionType;
	protected int leftMenuSpacerResourceId = 0;

	public FseDocumentSectionPerspective(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
		this.menuSpacerDrawable = getResources().getDrawable(getFrameMenuSpacerBackgroundResourceId());
	}
	
	protected void initialize(FsePerspectiveFlipper aDocumentView) {
		this.documentPerspectiveFlipper = aDocumentView;
	}

	@Override
	protected FdkKeyboardStyle getFdkKeyboardStyle() {
		return FdkKeyboardStyle.widget_input__PARAGRAPH;
	}
	
	@Override
	public void initialize(GcgActivity anGcgActivity, GcgViewFlipper aViewFlipper, int aSpinnableMenu, int aPageNumber) {
		this.gcgActivity = anGcgActivity;
		initialize((FsePerspectiveFlipper) aViewFlipper);
		super.initialize(anGcgActivity, aViewFlipper, aSpinnableMenu, aPageNumber);
	}
	
	public FmsPerspectiveFlipperParent getFseDocumentViewParent() {
		return (FmsPerspectiveFlipperParent) this.gcgActivity;
	}

	public FsePerspectiveFlipper getDocumentPerspectiveFlipper() {
		return this.documentPerspectiveFlipper;
	}
	
	public int getDocumentSectionViewResourceId() {
		return this.sectionType.getLayoutResourceId();
	}
	
	public abstract ViewGroup getDocumentSectionLayout();

	public abstract void setDocumentSectionLayout();

	public FseDocumentSectionType getSectionType() {
		return this.sectionType;
	}

	protected void setSectionType(FseDocumentSectionType aDocumentSectionType) {
		this.sectionType = aDocumentSectionType;
	}
	
	protected abstract void viewDocumentSection(FseDocumentSection aDocumentSection);
	
	protected abstract void viewDocumentSectionAsHistory(FseDocumentSection aDocumentSection);
	
	protected abstract String sectionIsModified();

	public int getLeftMenuSpacer() {
		return this.leftMenuSpacerResourceId;
	}

	public GcgMultiShiftKeysetController getMultiShiftController() {
		return null;
	}

	public void rightMenuActivateSpinner() {
		return;
	}

	@Override
	protected View getFdkKeypadPeerViewRight() {
		return null;
	}

	@Override
	protected FrameLayout getGcgThumbpadRight() {
		return null;
	}

	@Override
	protected View getFdkKeypadPeerViewLeft() {
		return null;
	}

	@Override
	protected FrameLayout getGcgThumbpadLeft() {
		return null;
	}

	@Override
	public int getFrameMenuSpacerBackgroundResourceId() {
		return R.drawable.left_menu__filler_1;
	}

	@Override
	public void fdkResetMultiShiftState() {
		return;
	}
	
	@Override
	public ArrayList<GcgGuiable> getPerspectiveContextGuiableList() {
		return new ArrayList<GcgGuiable>();
	}

	@Override
	public void initFdkDictationResultsConsumerMap() { return; }

}
