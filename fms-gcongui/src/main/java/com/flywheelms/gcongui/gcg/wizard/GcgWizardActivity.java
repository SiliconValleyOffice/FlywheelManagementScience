/* @(#)GcgWizardActivity.java
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

package com.flywheelms.gcongui.gcg.wizard;

import android.os.Bundle;
import android.view.KeyEvent;

import com.flywheelms.gcongui.R;
import com.flywheelms.gcongui.gcg.activity.GcgActivity;
import com.flywheelms.gcongui.gcg.context.GcgFrameBreadcrumb;

public abstract class GcgWizardActivity extends GcgActivity {

	protected GcgWizardStepFlipper gcgWizardStepFlipper;

	public GcgWizardActivity(String anInitialHelpContextUrlString) {
		super(anInitialHelpContextUrlString);
	}

	protected void initializeWizardView() {
		this.gcgWizardStepFlipper = (GcgWizardStepFlipper) findViewById(R.id.gcg__wizard__step_flipper);
		this.gcgWizardStepFlipper.initialize(this);
	}

	@Override
	public void onCreate(Bundle aSavedInstanceState) {
		super.onCreate(aSavedInstanceState);
	}

	@Override
	protected void processExtras() {
		super.processExtras();
	}

	@Override
	protected void initializeGui() {
		super.initializeGui();
		initializeWizardView();
		initializeGcgApplicationContextHeader();
	}

	@Override
	public boolean onKeyDown(int aKeyCode, KeyEvent aKeyEvent) {
		if(aKeyCode==KeyEvent.KEYCODE_CTRL_LEFT || aKeyCode==KeyEvent.KEYCODE_CTRL_RIGHT) {
			return super.onKeyDown(aKeyCode, aKeyEvent);
		}
		switch(aKeyCode) {
		case KeyEvent.KEYCODE_RIGHT_BRACKET:
			if(aKeyEvent.isCtrlPressed()) {
				this.gcgWizardStepFlipper.flipNext();
				return true;
			}
			return false;
		case KeyEvent.KEYCODE_LEFT_BRACKET:
			if(aKeyEvent.isCtrlPressed()) {
				this.gcgWizardStepFlipper.flipPrevious();
				return true;
			}
			return false;
		default:
		}
		return super.onKeyDown(aKeyCode, aKeyEvent);
	}

	@Override
	protected GcgFrameBreadcrumb getFrameBreadcrumb() {
		return null;
	}

}
