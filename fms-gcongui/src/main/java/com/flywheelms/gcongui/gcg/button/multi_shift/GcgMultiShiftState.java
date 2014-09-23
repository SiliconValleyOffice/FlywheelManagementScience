/* @(#)GcgMultiShiftState.java
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

package com.flywheelms.gcongui.gcg.button.multi_shift;



import com.flywheelms.gcongui.R;

import java.util.ArrayList;

public enum GcgMultiShiftState {
	
	OFF (1, false, false, R.drawable.gcg__multi_shift_state_list__off, R.color.x11__Brown), // gray
	CTL (2, false, true, R.drawable.gcg__multi_shift_state_list__ctl, R.color.x11__Magenta),
	SHIFT (3, false, true, R.drawable.gcg__multi_shift_state_list__shift, R.color.x11__Blue),
	ALT (4, false, false, R.drawable.gcg__multi_shift_state_list__alt, R.color.x11__Yellow),
	CANCEL_SHIFT (5, true, false, R.drawable.gcg__multi_shift_state_list__cancel, R.color.x11__Brown), // red X
//	ALT_SHIFT (5, false, R.drawable.gcg__multi_shift_state_list__alt_shift, R.color.Orange),
//	CTL_SHIFT (6, false, R.drawable.gcg__multi_shift_state_list__ctl_shift, R.color.Green),
	LOWER_CASE (1, false, false, R.drawable.gcg__multi_shift_state_list__gray, R.color.x11__Brown), // gray
	INITIAL_CAPS (2, false, false, R.drawable.gcg__multi_shift_state_list__blue, R.color.x11__Blue),
	CAMEL_CASE (3, false, false, R.drawable.gcg__multi_shift_state_list__yellow, R.color.x11__Yellow),
	ALL_CAPS (4, false, false, R.drawable.gcg__multi_shift_state_list__orange, R.color.x11__Orange),
	CANCEL_CASE (5, true, false, R.drawable.gcg__multi_shift_state_list__cancel, R.color.x11__Brown); // red X
	
	static {
		OFF.setNextState(CTL);
		CTL.setNextState(SHIFT);
		SHIFT.setNextState(ALT);
		ALT.setNextState(CTL);
		CANCEL_SHIFT.setNextState(OFF);
//		ALT_SHIFT.setNextState(CTL);
//		CTL_SHIFT.setNextState(CTL);
		LOWER_CASE.setNextState(INITIAL_CAPS);
		INITIAL_CAPS.setNextState(CAMEL_CASE);
		CAMEL_CASE.setNextState(ALL_CAPS);
		ALL_CAPS.setNextState(INITIAL_CAPS);
		CANCEL_CASE.setNextState(LOWER_CASE);
	}
	
	private int level;
	private boolean isItemSelectionState;
	private int drawableResourceId;
	private int textColorResourceId;
	private GcgMultiShiftState nextState;
	private final boolean isCancelState;

	private GcgMultiShiftState(
			int aStateIndex, boolean bIsCancelState, boolean bIsItemSelectionState, int aDrawableResourceId, int aTextColorResourceId) {
		this.level = aStateIndex;
		this.isCancelState = bIsCancelState;
		this.isItemSelectionState = bIsItemSelectionState;
		this.drawableResourceId = aDrawableResourceId;
		this.textColorResourceId = aTextColorResourceId;
	}
	
	private void setNextState(GcgMultiShiftState aMultiShiftState) {
		this.nextState = aMultiShiftState;
	}

	public int getLevel() {
		return this.level;
	}
	
	public boolean isCancelState() {
		return this.isCancelState;
	}
	
	public boolean isItemSelectionState() {
		return this.isItemSelectionState;
	}
	
	public GcgMultiShiftState getNextState() {
		return this.nextState;
	}
	
	public GcgMultiShiftState getNextEnabledState(
			ArrayList<GcgMultiShiftState> aSupportedMultiShiftStateList,
			boolean isMultipleSelections ) {
		if(aSupportedMultiShiftStateList.contains(this.nextState)) {
			if(isMultipleSelections) {
				if(this.nextState.supportsMultipleSelections()) {
					return this.nextState;
				}
			} else {
				return this.nextState;
			}
		}
		return recursiveNextState(aSupportedMultiShiftStateList, this.nextState, isMultipleSelections);
	}

	private boolean supportsMultipleSelections() {
		return this.isItemSelectionState();
	}

	private GcgMultiShiftState recursiveNextState(
			ArrayList<GcgMultiShiftState> aSupportedMultiShiftStateList,
			GcgMultiShiftState aMultiShiftState,
			boolean isMultipleSelections ) {
		if(aSupportedMultiShiftStateList.contains(aMultiShiftState.nextState)) {
			if(isMultipleSelections) {
				if(aMultiShiftState.nextState.supportsMultipleSelections()) {
					return aMultiShiftState.nextState;
				}
			} else {
				return aMultiShiftState.nextState;
			}
		}
		return recursiveNextState(
				aSupportedMultiShiftStateList, aMultiShiftState.nextState, isMultipleSelections);
	}

	public int getDrawableResourceId() {
		return this.drawableResourceId;
	}
	
	public int getTextColorResourceId() {
		return this.textColorResourceId;
	}
	
}
