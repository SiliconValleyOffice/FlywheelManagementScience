/* @(#)GcgViewFlipperChildView.java
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

package com.flywheelms.library.gcg.viewflipper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flywheelms.library.R;
import com.flywheelms.library.fse.widget.FseMultiShiftButton;
import com.flywheelms.library.gcg.activity.GcgActivity;
import com.flywheelms.library.gcg.context.GcgFrame;
import com.flywheelms.library.gcg.dialog.GcgGuiPreferencesDialog;
import com.flywheelms.library.gcg.interfaces.GcgGuiPreferencesClient;
import com.flywheelms.library.gcg.interfaces.GcgPerspective;

import java.util.ArrayList;
import java.util.Date;

public abstract class GcgViewFlipperChildView extends LinearLayout implements GcgGuiPreferencesClient {

	protected Context context;
	public static final String bundle_arg_PAGE_NUMBER = "PageNumber";
	public static final String bundle_arg_PAGE_TITLE = "PageTitle";
	protected GcgActivity gcgActivity;
	protected GcgViewFlipper viewFlipper;
    protected TextView viewCurtain;
	protected int spinnableMenuIndex; // negative number means no spinnable menu
	protected int pageNumber;
	protected String pageTitle;
	protected String abreviatedPageTitle;
	protected Date fmmTimestamp;
	protected Drawable menuSpacerDrawable = null;
	protected boolean enableMultiShiftControls = false;
	protected ArrayList<FseMultiShiftButton> multiShiftButtonList;

	public GcgViewFlipperChildView(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
		this.context = aContext;
		inflate(aContext, getViewLayoutResourceId(), this);
        this.viewCurtain = (TextView) findViewById(R.id.gcg__view_curtain);
	}

	public GcgViewFlipper getViewFlipper() {
		return this.viewFlipper;
	}

	public int getFrameMenuSpacerBackgroundResourceId() {
		return R.color.gcg__sands_of_time;
	}

	public void initializeTitleBar() {
		Button thePreviousTitleTextView = (Button) findViewById(R.id.view_flipper_view__title__prev);
		thePreviousTitleTextView.setText(getPreviousPageTitle());
		if(! getPreviousPageTitle().equals("")) {
			thePreviousTitleTextView.setEnabled(true);
			thePreviousTitleTextView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					GcgViewFlipperChildView.this.getViewFlipper().flipPrevious();
				}
			});
		} else {
			thePreviousTitleTextView.setEnabled(false);
		}
		TextView theCurrentTitleTextView = (TextView) findViewById(R.id.view_flipper_view__title);
		theCurrentTitleTextView.setText(getCurrentPageTitle());
		TextView theNextTitleTextView = (TextView) findViewById(R.id.view_flipper_view__title__next);
		theNextTitleTextView.setText(getNextPageTitle());
		if(! getNextPageTitle().equals("")) {
			theNextTitleTextView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					GcgViewFlipperChildView.this.getViewFlipper().flipNext();
				}
			});
		} else {
			theNextTitleTextView.setEnabled(false);
		}
	}
	
	public void disableMultiShiftControls() {
		enableMultiShiftControls(false);
	}
	
	public void enableMultiShiftControls() {
		enableMultiShiftControls(this.enableMultiShiftControls);
	}

	public void enableMultiShiftControls(boolean aBoolean) {
		if(this.multiShiftButtonList == null) {
			return;
		}
		for(FseMultiShiftButton theMultiShiftButton : this.multiShiftButtonList) {
			theMultiShiftButton.setVisibility(aBoolean ? View.VISIBLE : View.INVISIBLE);
		}
	}

	protected String getPreviousPageTitle() {
		return getViewFlipper().getPreviousPageTitle(this);
	}

	protected String getCurrentPageTitle() {
		return this.context.getResources().getString(getPageTitleResourceId());
	}

	protected String getCurrentAbreviatedPageTitle() {
		return this.context.getResources().getString(getAbreviatedPageTitleResourceId());
	}

	protected String getNextPageTitle() {
		return getViewFlipper().getNextPageTitle(this);
	}

	public void initialize(GcgActivity anGcgActivity, GcgViewFlipper aViewFlipper, int aPageNumber) {
		initialize(anGcgActivity, aViewFlipper, -1, aPageNumber);
	}

	public void initialize(GcgActivity anGcgActivity, GcgViewFlipper aViewFlipper, int aSpinnableMenuIndex, int aPageNumber) {
		this.gcgActivity = anGcgActivity;
		this.viewFlipper = aViewFlipper;
		this.spinnableMenuIndex = aSpinnableMenuIndex;
		this.pageNumber = aPageNumber;
		this.pageTitle = anGcgActivity.getResources().getString(getPageTitleResourceId());
		this.abreviatedPageTitle = anGcgActivity.getResources().getString(getAbreviatedPageTitleResourceId());
		initializeTitleBarNavigation();
	}

	public GcgActivity getGcgActivity() {
		return this.gcgActivity;
	}

	protected void initializeTitleBarNavigation() {
		Button thePreviousButton = (Button) findViewById(R.id.view_flipper_view__title__prev);
		if(thePreviousButton != null && !thePreviousButton.getText().equals("")) {
			thePreviousButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					GcgViewFlipperChildView.this.viewFlipper.flipPrevious();
				}
			});
		} else if(thePreviousButton != null) {
			thePreviousButton.setEnabled(false);
		}
		Button theNextButton = (Button) findViewById(R.id.view_flipper_view__title__next);
		if(theNextButton != null && !theNextButton.getText().equals("")) {
			theNextButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					GcgViewFlipperChildView.this.viewFlipper.flipNext();
				}
			});
		} else if(theNextButton != null) {
			theNextButton.setEnabled(false);
		}
	}

	protected abstract int getPageTitleResourceId();
	
	protected int getAbreviatedPageTitleResourceId() {
		return getPageTitleResourceId();
	}

	protected abstract int getViewLayoutResourceId();
	
	public void setHelpContextUrlString() {
		this.gcgActivity
			.setHelpContextUrlString(getHelpContextUrlString());
	}

	protected abstract String getHelpContextUrlString();
	
	protected void activateView() {
		setHelpContextUrlString();
		enableMultiShiftControls();
        enableViewCurtain(false);
	}

	protected void deactivateView() {
		disableMultiShiftControls();
        enableViewCurtain(true);
	}

    private void enableViewCurtain(boolean bEnable) {
        if(this.viewCurtain != null) {
            this.viewCurtain.setVisibility(bEnable ? View.VISIBLE : View.INVISIBLE);
        }
    }

    public int getSpinnableMenuIndex() {
		return this.spinnableMenuIndex;
	}

	public int getPageNumber() {
		return this.pageNumber;
	}

	public String getPageTitle() {
		return this.pageTitle;
	}

	public String getAbreviatedPageTitle() {
		return this.abreviatedPageTitle == null ? "" : this.abreviatedPageTitle;
	}
	
	////  GUI Preferences  /////////////////
	
	protected void launchSaveGuiPreferencesDialog() {
		getGcgActivity().startDialog(new GcgGuiPreferencesDialog(this.gcgActivity, this.pageTitle, this));
	}

	@Override
	public void guiPreferencesRestore() {
		return;
	}

	@Override
	public void guiPreferencesSave() {
		return;
	}

	@Override
	public void guiPreferencesClear() {
		return;
	}

	@Override
	public void guiPreferencesRestoreTransient() {
		return;
	}

	@Override
	public void guiPreferencesSaveTransient() {
		return;
	}

	public void activityNavigation(GcgFrame aFrame, GcgPerspective aPerspective) {
		getGcgActivity().activityNavigation(aFrame, aPerspective);
	}
	
	public void viewData() { return; }
	
	public void refreshDataDisplay() { return; }

    public void afterViewFlip() {

    }
}
