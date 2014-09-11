/* @(#)GcgMultiShiftKeysetController.java
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

package com.flywheelms.library.gcg.button.multi_shift;

import com.flywheelms.library.gcg.interfaces.GcgMultiShiftControllerParent;
import com.flywheelms.library.gcg.interfaces.GcgMultiShiftStateListener;

import java.util.ArrayList;

public class GcgMultiShiftKeysetController {
	
	private final GcgMultiShiftControllerParent multiShiftControllerParent;
	private ArrayList<? extends GcgMultiShiftButton> multiShiftKeyset;
	private GcgMultiShiftButton leftMultiShiftButton;
	private GcgMultiShiftButton rightMultiShiftButton;
	private GcgMultiShiftState multiShiftState = GcgMultiShiftState.OFF;
	private ArrayList<GcgMultiShiftClientButton> buttonListenerList =
			new ArrayList<GcgMultiShiftClientButton>();
	private ArrayList<GcgMultiShiftStateListener> dualTouchStateListenerList =
			new ArrayList<GcgMultiShiftStateListener>();
	private ArrayList<GcgMultiShiftState> enabledMultiShiftStateList;
	private GcgMultiShiftState cancelState;
	private GcgMultiShiftState firstState;
	private GcgMultiShiftState offState;
	private boolean resetStateAfterDictation = true;
	
	public GcgMultiShiftKeysetController(GcgMultiShiftControllerParent aShiftControllerParent ) {
		this.multiShiftControllerParent = aShiftControllerParent;
		this.multiShiftKeyset = this.multiShiftControllerParent.getShiftButtonList();
		this.cancelState = this.multiShiftControllerParent.getSupportedMultiShiftStateList().get(
				this.multiShiftControllerParent.getSupportedMultiShiftStateList().size() - 1 );
		this.offState = this.multiShiftControllerParent.getSupportedMultiShiftStateList().get(0);
		this.firstState = this.multiShiftControllerParent.getSupportedMultiShiftStateList().get(1);
		this.multiShiftState = this.firstState;
		/*
		 * in the future, this controller may need to support 2 OR MORE MultiShift buttons  -  SDS
		 */
		this.leftMultiShiftButton = this.multiShiftKeyset.get(0);
		this.rightMultiShiftButton = this.multiShiftKeyset.get(1);
		this.enabledMultiShiftStateList = this.multiShiftControllerParent.getSupportedMultiShiftStateList();
	}
	
	public GcgMultiShiftState getCancelState() {
		return this.cancelState;
	}
	
	public boolean isCancelState(GcgMultiShiftButton aMultiShiftButton) {
		return aMultiShiftButton.isGcgMultiShiftState(getCancelState());
	}
	
	public GcgMultiShiftState getFirstState() {
		return this.firstState;
	}
	
	public GcgMultiShiftState getOffState() {
		return this.offState;
	}

	public GcgMultiShiftButton getLeftMultiShiftButton() {
		return this.leftMultiShiftButton;
	}

	public GcgMultiShiftButton getRightMultiShiftButton() {
		return this.rightMultiShiftButton;
	}
	
	public boolean isOff() {
		return this.leftMultiShiftButton.isOff() || this.rightMultiShiftButton.isOff();
	}
	
	public boolean isGcgMultiShiftState(GcgMultiShiftState aMultiShiftState) {
		return this.leftMultiShiftButton.isGcgMultiShiftState(aMultiShiftState) || this.rightMultiShiftButton.isGcgMultiShiftState(aMultiShiftState);
	}
	
	public void resetState() {
		this.leftMultiShiftButton.setState(getOffState().getLevel());
		this.rightMultiShiftButton.setState(getOffState().getLevel());
		onStateChange(getOffState());
	}
	
	public GcgMultiShiftState getMultiShiftState() {
		return this.multiShiftState;
	}
	
	public boolean isItemSelectionState() {
		return this.multiShiftState.isItemSelectionState();
	}
	
	private void onStateChange(GcgMultiShiftState aMultiShiftState) {
		for(GcgMultiShiftClientButton aMultiShiftClientButton : this.buttonListenerList) {
			aMultiShiftClientButton.setState(aMultiShiftState);
		}
		for(GcgMultiShiftStateListener aMultiShiftStateListener : this.dualTouchStateListenerList) {
			aMultiShiftStateListener.stateChange(aMultiShiftState);
		}
		this.multiShiftState = aMultiShiftState;
	}

	public void onLeftButtonClick() {
		if(isCancelState(this.leftMultiShiftButton)) {
			resetState();
		} else if(this.leftMultiShiftButton.isOff()) {
					this.leftMultiShiftButton.setState(getFirstState().getLevel());
					this.rightMultiShiftButton.setState(getCancelState().getLevel());
					onStateChange(getFirstState());
		} else {
			onStateChange(
					this.leftMultiShiftButton.incrementMultiShiftState(this.enabledMultiShiftStateList,
					this.multiShiftControllerParent.isMultipleSelections() ) );
		}
	}
	
	public void onRightButtonClick() {
		if(isCancelState(this.rightMultiShiftButton)) {
			resetState();
		} else if(this.rightMultiShiftButton.isOff()) {
					this.rightMultiShiftButton.setState(getFirstState().getLevel());
					this.leftMultiShiftButton.setState(getCancelState().getLevel());
					onStateChange(getFirstState());
		} else {
			onStateChange(this.rightMultiShiftButton.incrementMultiShiftState(this.enabledMultiShiftStateList,
					this.multiShiftControllerParent.isMultipleSelections() ) );
		}
	}

	public void addButtonListener(GcgMultiShiftClientButton aMultiShiftClientButton) {
		this.buttonListenerList.add(aMultiShiftClientButton);
	}
	
	public void addMultiShiftStateListener(GcgMultiShiftStateListener aMultiShiftStateListener) {
		this.dualTouchStateListenerList.add(aMultiShiftStateListener);
	}

//	private boolean enabledMultiShiftState(GcgMultiShiftState aMultiShiftState) {
//		return this.enabledMultiShiftStateList.contains(aMultiShiftState);
//	}

	public void setState(GcgMultiShiftState aMultiShiftState) {
		if(aMultiShiftState == null) {
			return;
		}
		if(aMultiShiftState.isCancelState() || aMultiShiftState == getOffState()) {
			resetState();
			return;
		}
		this.leftMultiShiftButton.setState(this.cancelState.getLevel());
		this.rightMultiShiftButton.setState(aMultiShiftState.getLevel());
		this.multiShiftState = aMultiShiftState;
	}

	public void resetStateAfterDictation() {
		if(this.resetStateAfterDictation && this.multiShiftState == this.firstState) {
			resetState();
		}
	}
	
	public void resetStateAfterDictation(boolean aBoolean) {
		this.resetStateAfterDictation = aBoolean;
	}
	
	public void setFirstState() {
		setState(this.firstState);
	}

}
