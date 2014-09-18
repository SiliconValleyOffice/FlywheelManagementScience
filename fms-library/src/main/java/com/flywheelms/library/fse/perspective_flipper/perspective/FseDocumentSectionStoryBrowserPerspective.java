/* @(#)FseDocumentSectionStoryBrowserPerspective.java
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.flywheelms.library.R;
import com.flywheelms.library.fms.helper.FmsHelpIndex;
import com.flywheelms.library.fse.FseDocumentSectionType;
import com.flywheelms.library.fse.enumerator.FseContentModificationState;
import com.flywheelms.library.fse.enumerator.FseNumberingModificationState;
import com.flywheelms.library.fse.interfaces.FmsPerspectiveFlipperParent;
import com.flywheelms.library.fse.model.FseDocumentSection;
import com.flywheelms.library.gcg.activity.GcgActivity;
import com.flywheelms.library.gcg.viewflipper.GcgViewFlipper;

public class FseDocumentSectionStoryBrowserPerspective extends FseDocumentSectionStoryPerspective {

	public FseDocumentSectionStoryBrowserPerspective(Context context,
			AttributeSet attrs) {
		super(context, attrs);
		this.isBrowserMode = true;
		setSectionType(FseDocumentSectionType.STORY);
	}
	
	public void initialize(FmsPerspectiveFlipperParent anGcgActivity, GcgViewFlipper aViewFlipper, int aPageNumber, @SuppressWarnings("unused") String aJunkSignatureArg) {
		initialize(anGcgActivity.getFsePerspectiveFlipper());
		this.gcgActivity = (GcgActivity) anGcgActivity;
		this.viewFlipper = aViewFlipper;
		this.spinnableMenuIndex = -1;
		this.pageNumber = aPageNumber;
		this.pageTitle = this.gcgActivity.getResources().getString(getPageTitleResourceId());
		initializeTitleBarNavigation();
		initializeTitleBar();
		LinearLayout theRightKeypadContainer = (LinearLayout) findViewById(R.id.fdk__right_keypad__container);
		theRightKeypadContainer.setBackgroundResource(R.color.gcg__menu_background);
		this.fdkKeypadPeerRight = (RelativeLayout) findViewById(R.id.fdk__right_keypad_peer);
		this.fdkKeypadPeerRight.removeAllViewsInLayout();
	}

	@Override
	public String getHelpContextUrlString() {
		return FmsHelpIndex.FSE__HISTORY_BROWSER_ACTIVITY;
	}
	
	@Override
	public void setHelpContextUrlString() {
		this.gcgActivity.setHelpContextUrlString(getHelpContextUrlString());
	}

	@Override
	public void updateContentModificationState(FseContentModificationState aModificationState) {
		return;
	}
	
	@Override
	public FseNumberingModificationState determineParagraphNumberingModificationState() {
		return this.numberingModificationState;
	}

	@Override
	public void viewDocumentSection(FseDocumentSection aDocumentSection) {
		setNumberingModificationState(FseNumberingModificationState.UNCHANGED);
		super.viewDocumentSection(aDocumentSection);
		updateSequenceModificationState();
	}

}
