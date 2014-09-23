/* @(#)FdkKeyboardStyle.java
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

package com.flywheelms.gcongui.fdk.enumerator;


import com.flywheelms.gcongui.R;
import com.flywheelms.gcongui.gcg.button.multi_shift.GcgMultiShiftState;

import java.util.ArrayList;
import java.util.Arrays;

public enum FdkKeyboardStyle {
	
	widget_input__PARAGRAPH (R.layout.fdk__left_keypad__widget_input__paragraph, R.layout.fdk__right_keypad__widget_input__paragraph, new ArrayList<GcgMultiShiftState>(Arrays.asList(
			GcgMultiShiftState.LOWER_CASE,
			GcgMultiShiftState.INITIAL_CAPS,
			GcgMultiShiftState.CAMEL_CASE,
			GcgMultiShiftState.ALL_CAPS,
			GcgMultiShiftState.CANCEL_CASE )), GcgMultiShiftState.CANCEL_CASE),
	EXTENDED (R.layout.fdk__left_keypad__extended, R.layout.fdk__right_keypad__extended, new ArrayList<GcgMultiShiftState>(Arrays.asList(
			GcgMultiShiftState.OFF,
			GcgMultiShiftState.CTL,
			GcgMultiShiftState.SHIFT,
			GcgMultiShiftState.ALT,
//			GcgMultiShiftState.ALT_SHIFT,
//			GcgMultiShiftState.CTL_SHIFT,
			GcgMultiShiftState.CANCEL_SHIFT )), GcgMultiShiftState.CANCEL_SHIFT),
	widget_input__SEARCH (R.layout.fdk__left_keypad__widget_input__search, R.layout.fdk__right_keypad__widget_input__search, new ArrayList<GcgMultiShiftState>(), null),
	widget_input__SPINNER (R.layout.fdk__left_keypad__widget_input__spinner, R.layout.fdk__right_keypad__widget_input__spinner, new ArrayList<GcgMultiShiftState>(), null),
	widget_input__EDIT_TEXT (R.layout.fdk__left_keypad__widget_input__edit_text, R.layout.fdk__right_keypad__widget_input__edit_text, new ArrayList<GcgMultiShiftState>(Arrays.asList(
			GcgMultiShiftState.LOWER_CASE,
			GcgMultiShiftState.INITIAL_CAPS,
			GcgMultiShiftState.CAMEL_CASE,
			GcgMultiShiftState.ALL_CAPS,
			GcgMultiShiftState.CANCEL_CASE )), GcgMultiShiftState.CANCEL_CASE),
	widget_input__FILE_NAME (R.layout.fdk__left_keypad__widget_input__file_name, R.layout.fdk__right_keypad__widget_input__file_name, new ArrayList<GcgMultiShiftState>(Arrays.asList(
			GcgMultiShiftState.LOWER_CASE,
			GcgMultiShiftState.INITIAL_CAPS,
			GcgMultiShiftState.CAMEL_CASE,
			GcgMultiShiftState.ALL_CAPS,
			GcgMultiShiftState.CANCEL_CASE )), GcgMultiShiftState.CANCEL_CASE);
	
	private final int leftKeypadResourceId;
	private final int rightKeypadResourceId;
	private final ArrayList<GcgMultiShiftState> supportedMultiShiftStateList;
	private final GcgMultiShiftState defaultMultiShiftState;
	
	private FdkKeyboardStyle(int aLeftKeypadResourceId, int aRightKeypadResourceId, ArrayList<GcgMultiShiftState> aSupportedMultiShiftStateList, GcgMultiShiftState aDefaultMultiShiftState) {
		this.leftKeypadResourceId = aLeftKeypadResourceId;
		this.rightKeypadResourceId = aRightKeypadResourceId;
		this.supportedMultiShiftStateList = aSupportedMultiShiftStateList;
		this.defaultMultiShiftState = aDefaultMultiShiftState;
	}

	public int getLeftKeypadResourceId() {
		return this.leftKeypadResourceId;
	}

	public int getRightKeypadResourceId() {
		return this.rightKeypadResourceId;
	}
	
	public boolean hasMultiShiftKeyset() {
		return this.supportedMultiShiftStateList.size() > 0;
	}

	public ArrayList<GcgMultiShiftState> getSupportedMultiShiftStateList() {
		return this.supportedMultiShiftStateList;
	}

	public GcgMultiShiftState getDefaultMultiShiftState() {
		return this.defaultMultiShiftState;
	}

}
