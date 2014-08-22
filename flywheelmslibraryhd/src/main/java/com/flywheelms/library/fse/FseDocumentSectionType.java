/* @(#)FseDocumentSectionType.java
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

package com.flywheelms.library.fse;

import android.view.KeyEvent;

import com.flywheelms.library.R;

public enum FseDocumentSectionType {
	
	// TODO - finish design around layout resource ID  -  SDS
	
	STORY("Story", R.layout.fms_view__document_section__story, KeyEvent.KEYCODE_S, 0),
	NOTES("Notes", R.layout.fms_view__document_section__notes, KeyEvent.KEYCODE_N, 3),
	HISTORY("History", R.layout.fms_view__document_section__story, KeyEvent.KEYCODE_H, 2),
	COMMUNITY("Community", R.layout.fms_view__document_section__community, KeyEvent.KEYCODE_C, 1),
	TRIBKN("TribKn", R.id.document_section__tribkn_view, KeyEvent.KEYCODE_T, 99);

	private final String name;
	private final int layoutResourceId;
	private final int tabNavigationKeyCode;
	private final int viewFlipperIndex;
	
	private FseDocumentSectionType(String aLabel, int aViewId, int aTabNavigationKeyCode, int aViewFlipperIndex) {
		this.name = aLabel;
		this.layoutResourceId = aViewId;
		this.tabNavigationKeyCode = aTabNavigationKeyCode;
		this.viewFlipperIndex = aViewFlipperIndex;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getLayoutResourceId() {
		return this.layoutResourceId;
	}
	
	public int getTabNavigationKeyCode() {
		return this.tabNavigationKeyCode;
	}
	
	@Override
	public String toString() {
		return getName();
	}

	public static FseDocumentSectionType getTypeForName(String aSectionTypeLabel) {
		for(FseDocumentSectionType theSectionType : values()) {
			if(theSectionType.toString().equals(aSectionTypeLabel)) {
				return theSectionType;
			}
		}
		return null;  // we want it to blow up
	}
	
	public boolean isStory(){
		return this.equals(STORY);
	}
	
	public int getViewFlipperIndex() {
		return this.viewFlipperIndex;
	}

}
