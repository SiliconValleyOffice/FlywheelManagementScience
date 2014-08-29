/* @(#)GcgWizardStepView.java
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

package com.flywheelms.library.gcg.wizard.step;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TableRow;
import android.widget.TextView;

import com.flywheelms.library.R;
import com.flywheelms.library.fms.activity.FmsNodeWizardActivity;
import com.flywheelms.library.gcg.GcgActivity;
import com.flywheelms.library.gcg.helper.GcgHelper;
import com.flywheelms.library.gcg.listeners.GcgFlingListener;
import com.flywheelms.library.gcg.viewflipper.GcgViewFlipper;
import com.flywheelms.library.gcg.viewflipper.GcgViewFlipperChildView;
import com.flywheelms.library.gcg.wizard.GcgWizardStepFlipper;

public abstract class GcgWizardStepView extends GcgViewFlipperChildView {
	
	private int stepNumber;
	private GestureDetector theFlingDetector;
	private Button guiPreferences;

	public GcgWizardStepView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void initialize(GcgActivity anGcgActivity, GcgViewFlipper aViewFlipper, int aPageNumber) {
		super.initialize(anGcgActivity, aViewFlipper, aPageNumber);
		this.theFlingDetector = new GestureDetector(this.context, new GcgFlingListener(getWizardStepFlipper()));
		initializeNavigationBar();
		initializeGuiPreferencesButton();
	}

	private void initializeGuiPreferencesButton() {
		if(! hasGuiPreferencesButton()) {
			return;
		}
		this.guiPreferences = (Button) findViewById(R.id.gui_preferences);
		this.guiPreferences.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				launchSaveGuiPreferencesDialog();
			}
		});
		this.guiPreferences.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
		    	v.playSoundEffect(SoundEffectConstants.CLICK);
				guiPreferencesRestore();
				return true;
			}
		});
	}
	
	protected boolean hasGuiPreferencesButton() {
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent aMotionEvent) {
        boolean bFlingDetector = this.theFlingDetector.onTouchEvent(aMotionEvent);
        if(aMotionEvent.getAction() == MotionEvent.ACTION_UP) {
        	return bFlingDetector;
        }
        return false;
	}

	private void initializeNavigationBar() {
		this.stepNumber = getWizardStepFlipper().indexOfChild(this);
		if(this.stepNumber == 0) {
			this.stepNumber = 1;
		} else {  // wacky ViewFlipper child index order
			this.stepNumber = getStepCount() - this.stepNumber + 1;
		}
		Button theNavigateLeftButton = (Button) findViewById(R.id.navigate_left_button);
		if(this.stepNumber == 1) {
			GcgHelper.enableNavigationButton(theNavigateLeftButton, false);
		} else {
			theNavigateLeftButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					getWizardStepFlipper().flipPrevious();
				}
			});
		}
		Button theNavigateRightButton = (Button) findViewById(R.id.navigate_right_button);
		if(this.stepNumber == getStepCount()) {  // wacky ViewFlipper child index order
			GcgHelper.enableNavigationButton(theNavigateRightButton, false);
		} else  {
			theNavigateRightButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					getWizardStepFlipper().flipNext();
				}
			});
		}
		TextView theNavigationPositionTextView = (TextView) findViewById(R.id.navigation_position);
		theNavigationPositionTextView.setText("Step " + this.stepNumber + " of " + getStepCount());
	}

	private int getStepCount() {
		return getWizardStepFlipper().getChildCount();
	}

	public GcgWizardStepFlipper getWizardStepFlipper() {
		return (GcgWizardStepFlipper) this.viewFlipper;
	}
	
	public FmsNodeWizardActivity getWizardActivity() {
		return getWizardStepFlipper().getWizardActivity();
	}
	
	@Override
	protected void activateView() {
		super.activateView();
		if(this.stepNumber == getStepCount()) {
			getWizardStepFlipper().enableDoItNowButton();
		}
	}
	
	@Override
	protected void deactivateView() {
		super.deactivateView();
		if(this.stepNumber == getStepCount()) {
			getWizardStepFlipper().disableDoItNowButton();
		}
	}

	protected void initializeTableRowClickListener(final CompoundButton aCompoundButton, int aRowResourceId) {
		TableRow theTableRow = (TableRow) findViewById(aRowResourceId);
		theTableRow.setOnTouchListener(GcgHelper.getFlingListener(this.context, getWizardStepFlipper()));
		theTableRow.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(aCompoundButton.isClickable()) {
					aCompoundButton.performClick();
				}
			}
		});
	}
	
	protected GcgActivity getActivity() {
		return getWizardStepFlipper().getGcgActivity();
	}

	public abstract String getSummaryText();

	public abstract boolean validWizardStepData();

}
