/* @(#)GcgActivity.java
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

package com.flywheelms.gcongui.gcg.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flywheelms.gcongui.R;
import com.flywheelms.gcongui.fdk.FdkHostSupport;
import com.flywheelms.gcongui.fdk.enumerator.FdkKeyboardState;
import com.flywheelms.gcongui.fdk.interfaces.FdkHost;
import com.flywheelms.gcongui.fdk.widget.FdkKeyboard;
import com.flywheelms.gcongui.gcg.GcgApplication;
import com.flywheelms.gcongui.gcg.context.GcgActivityBreadcrumb;
import com.flywheelms.gcongui.gcg.context.GcgApplicationContext;
import com.flywheelms.gcongui.gcg.context.GcgApplicationContextHeader;
import com.flywheelms.gcongui.gcg.context.GcgFrame;
import com.flywheelms.gcongui.gcg.context.GcgFrameBreadcrumb;
import com.flywheelms.gcongui.gcg.context.GcgNavigationTarget;
import com.flywheelms.gcongui.gcg.dialog.GcgDialog;
import com.flywheelms.gcongui.gcg.dialog.GcgRevertDataOkCancelDialog;
import com.flywheelms.gcongui.gcg.dialog.GcgSaveChangesDialog;
import com.flywheelms.gcongui.gcg.enumerator.GcgDoItNowMenuItemState;
import com.flywheelms.gcongui.gcg.enumerator.GcgPhysicalKeyboardShortcutProfile;
import com.flywheelms.gcongui.gcg.helper.GcgActivityHelper;
import com.flywheelms.gcongui.gcg.interfaces.GcgDoItNowClient;
import com.flywheelms.gcongui.gcg.interfaces.GcgGuiable;
import com.flywheelms.gcongui.gcg.interfaces.GcgPerspective;
import com.flywheelms.gcongui.gcg.listeners.GcgDoItNowListener;
import com.flywheelms.gcongui.gcg.menu.GcgFrameSpinner;
import com.flywheelms.gcongui.gcg.treeview.GcgTreeViewAdapter;
import com.flywheelms.gcongui.gcg.view.GcgActivityStatusIndicator;
import com.flywheelms.gcongui.gcg.view.GcgDoItNowView;
import com.flywheelms.gcongui.gcg.viewflipper.GcgPerspectiveFlipper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Stack;
import java.util.UUID;

public abstract class GcgActivity extends Activity implements FdkHost, GcgDoItNowClient {

    public static final boolean REFRESH_DATA = true;
    public static final boolean RESTORE_GUI_STATE = true;
	protected boolean isMainGcgApplicationActivity = false;
	protected TextView activityCurtain;
	protected Stack<GcgDialog> modalGcgDialogStack = new Stack<GcgDialog>();
	protected boolean mustSelectDataSource;
	protected boolean dataRefreshAll = false;
	protected boolean parentDataRefreshAll = false;
    protected boolean restoreGuiState = false;
	private Bundle savedInstanceState;
	private String activityLabel;
	protected GcgFrameSpinner frameSpinner;
	protected boolean setDisplayHome = true;
	private String activityNodeIdString;
	private String helpContextUrlString = "";
	public FdkHostSupport fdkHostSupport;
	private MenuItem publishPdfMenuItem;
	protected MenuItem activityStatusIndicatorMenuItem;
	protected GcgActivityStatusIndicator activityStatusIndicator;
	protected LinearLayout activityLayout;
	private GcgApplicationContext gcgApplicationContext;
	private GcgApplicationContextHeader gcgApplicationContextHeader;
	private ArrayList<GcgGuiable> perspectiveContext;
	private MenuItem doItNowMenuItem;
	private GcgDoItNowView doItNowView;
	private OnLongClickListener saveDataLongClickListener;
	private GcgDoItNowMenuItemState doItNowMenuItemState = GcgDoItNowMenuItemState.DISABLED;
	private GcgDoItNowListener doItNowListener;
	protected boolean dataHasBeenModified = false;
	protected boolean discardDataChanges = false;
	private String activeViewGroupName = null;
	private GcgTreeViewAdapter activeTreeViewAdapter;
    protected float lastXposition;  // for detecting page swipes

	public GcgActivity(String anInitialHelpContextUrlString) {
		super();
		this.helpContextUrlString = anInitialHelpContextUrlString;
		this.activityNodeIdString = generateActivityId();
	}

    private String generateActivityId() {
        StringBuilder theStringBuilder = new StringBuilder(getFrameworkCode() + "-");
        theStringBuilder.append(UUID.randomUUID());
        return theStringBuilder.toString();
    }

    protected String getFrameworkCode() {
        return "GCG";
    }
	
	@Override
	public void onResume() {
        super.onResume();
        this.fdkHostSupport.initSpeechRecognition();
    }

	@Override
	public void onPostResume() {
		super.onPostResume();
		if(this.dataRefreshAll) {
			refreshDataDisplay();
			this.dataRefreshAll = false;
		}
		if(this.restoreGuiState) {
            restoreGuiState();
			this.restoreGuiState = false;
		}
		resetSoftKeyboard();
	}
	
	@Override
	public void onPause() {
		super.onPause();
        this.fdkHostSupport.destroyServices();
		resetSoftKeyboard();
	}

    private void resetSoftKeyboard() {
		if(this.fdkHostSupport != null) {
			this.fdkHostSupport.resetSoftKeyboard(getWindowToken());
		}
	}

	// override to implement granular, application-specific updates
//	private void processDataRefreshList() {
//		buildContentViewForDataSource();
//	}

	protected void processExtras() {
		try {
			if(getIntent().getExtras() != null) {
				if(getIntent().hasExtra(GcgActivityHelper.bundle_key__GCG__APPLICATION_CONTEXT)) {
					setGcgApplicationContext(new GcgApplicationContext(new JSONObject(getIntent().getExtras().getString(GcgActivityHelper.bundle_key__GCG__APPLICATION_CONTEXT))));
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public Context getContext() {
		return this;
	}

	public GcgApplicationContext getGcgApplicationContext() {
		return this.gcgApplicationContext;
	}

	public void setGcgApplicationContext(GcgApplicationContext anApplicationContext) {
		this.gcgApplicationContext = anApplicationContext;
	}

	public void updateGcgApplicationContext() {
		return;
	}
	
	public void resetApplicationContext() {
        if(this.gcgApplicationContextHeader != null) {
            this.gcgApplicationContextHeader.resetApplicationContext();
        }
	}

	protected void initializeGcgApplicationContextHeader() {
		setGcgApplicationContextHeader( (GcgApplicationContextHeader) findViewById(R.id.gcg__application_context__header));
		getGcgApplicationContextHeader().initialize(this);
	}

	private void setGcgApplicationContextHeader(GcgApplicationContextHeader anApplicationContextHeader) {
		this.gcgApplicationContextHeader = anApplicationContextHeader;
	}

	protected void initializeGcgApplicationContext() {
		setGcgApplicationContext(new GcgApplicationContext(R.drawable.gcg__unspecified_glyph, "Unknown Data Source"));
	}
	
	protected ArrayList<GcgGuiable> getPerspectiveContextGuiableList() {
		return new ArrayList<GcgGuiable>();
	}

	protected void removeNavigationButton() {
		Button theButton = (Button) getGcgApplicationContextHeader().findViewById(R.id.gcg__application_context__navigation_button);
		theButton.setVisibility(View.GONE);
	}

	protected GcgApplicationContextHeader getGcgApplicationContextHeader() {
		return this.gcgApplicationContextHeader;
	}

	@Override
	public void onSaveInstanceState(Bundle theBundle) {
		super.onSaveInstanceState(theBundle);
		if(getGcgApplicationContext() != null) {
			theBundle.putString(GcgActivityHelper.bundle_key__GCG__APPLICATION_CONTEXT, getGcgApplicationContext().getSerialized());
		}
        saveGuiState();
	}
	
	@Override
	public void onRestoreInstanceState(Bundle aSavedInstanceState) {
		super.onRestoreInstanceState(aSavedInstanceState);
	}

	@Override
	public void onWindowFocusChanged(boolean bHasFocus) {
		if(bHasFocus && ! this.modalGcgDialogStack.empty()) {
			this.modalGcgDialogStack.peek().restartDialog();
		}
	}

	@Override
	protected void onCreate(Bundle aSavedInstanceState) {
		super.onCreate(aSavedInstanceState);
		this.savedInstanceState = aSavedInstanceState;
		this.fdkHostSupport = new FdkHostSupport(this, false);
		buildContentViewForDataSource();
	}
	
	@Override
	public void onBackPressed() {
	  finish();
	}
	
	protected View getRootView() {
		return findViewById(android.R.id.content);
	}

	protected void selectDataSource() {
		return;
	}

	protected void buildContentViewForDataSource() {
		setContentView(R.layout.gcg__library_activity);
		this.activityLayout = (LinearLayout) findViewById(R.id.gcg_library_activity__layout_root);
		this.activityLayout.setBackgroundResource(getRootLayoutBackgroundResourceId());
		layoutInflation();
	}
	
	protected void enableActivityCurtain(boolean aBoolean) {
		if(this.activityCurtain == null) {
			return;
		}
		if(aBoolean) {
			this.activityCurtain.setVisibility(View.VISIBLE);
			this.activityCurtain.bringToFront();
		} else {
			this.activityCurtain.setVisibility(View.GONE);
		}
	}

	protected boolean activityCurtainEnabled() {
		return this.activityCurtain == null ? false : this.activityCurtain.getVisibility() == View.VISIBLE;
	}

	public void layoutInflation() {
		getLayoutInflater().inflate(getContentViewResourceId(), this.activityLayout, true);
		initializeActivityCurtain();
		getActionBar().setDisplayHomeAsUpEnabled(this.setDisplayHome);
		if(this.savedInstanceState != null) {
			processSavedInstanceState(this.savedInstanceState);
		} else {
			processExtras();
		}
		initializeGui();
	}
	
	public void dataSourceSelected(@SuppressWarnings("unused") Object aDataSourceObject) {
		setMustSelectDataSource(false);
		buildContentViewForDataSource();
		initializeGcgApplicationContext();
		initializeGcgApplicationContextHeader();
	}
	
	protected void disableDisplayHome() {
		getActionBar().setDisplayHomeAsUpEnabled(false);
	}
	
	protected void enableDisplayHome() {
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	protected int getRootLayoutBackgroundResourceId() {
		return R.color.gcg__portobello;
	}

	protected void initializeGui() {
		initializeActivityCurtain();
	}

	private void initializeActivityCurtain() {
		this.activityCurtain = (TextView) findViewById(R.id.gcg__activity_curtain);
	}

	protected void processSavedInstanceState(@SuppressWarnings("unused") Bundle aSavedInstanceState) { return; }

	protected abstract int getContentViewResourceId();

	protected int getActivityRootViewResourceId() {
		return 0;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.fdkHostSupport.destroyServices();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu aMenu) {
		MenuInflater aMenuInflater = getMenuInflater();
		aMenuInflater.inflate(getActivityOptionsMenuResourceId(), aMenu);
		this.fdkHostSupport.initOptionsMenu(aMenu);
		this.doItNowView = new GcgDoItNowView(this);
		this.saveDataLongClickListener = new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View aView) {
				GcgActivity.this.requestRevertAllModifiedData();
				GcgActivity.this.doItNowView.drawSaveDataBitmap();
				return true;
			}
		};
		this.doItNowMenuItem = aMenu.findItem(R.id.action__do_it_now);
		this.doItNowMenuItem.setActionView(this.doItNowView);
		this.activityStatusIndicatorMenuItem = aMenu.findItem(R.id.activity_status_indicator);
		this.activityStatusIndicatorMenuItem.setActionView(getActivityStatusIndicator());
		this.publishPdfMenuItem = aMenu.findItem(R.id.action__publish_pdf);
		if(! isPdfPublisher()) {
			aMenu.removeItem(R.id.action__publish_pdf);
		}
		resetActionBarSave();
		return true;
	}

    protected int getActivityOptionsMenuResourceId() {
        return R.menu.gcg_activity__default_options_menu;
    }

    protected GcgActivityStatusIndicator getActivityStatusIndicator() {
		if(this.activityStatusIndicator == null) {
			this.activityStatusIndicator = new GcgActivityStatusIndicator(this);
		}
		return this.activityStatusIndicator;
	}
	
	protected void setDoItNowState(GcgDoItNowMenuItemState aDoItNowState) {
		this.doItNowMenuItemState = aDoItNowState;
		switch (aDoItNowState) {
			case DISABLED:
				this.doItNowView.clear();
				this.doItNowView.setOnLongClickListener(null);
				break;
			case DO_IT_NOW:
				this.doItNowView.drawDoItNowBitmap();
				this.doItNowView.setOnLongClickListener(null);
				break;
			case SAVE:
				this.doItNowView.drawSaveDataBitmap();
				this.doItNowView.setOnLongClickListener(this.saveDataLongClickListener);
				break;
			default:
		}
	}
	
	protected GcgDoItNowMenuItemState getGcgDoItNowMenuItemState() {
		return this.doItNowMenuItemState;
	}
	
	protected void setDoItNowListener(GcgDoItNowListener aDoItNowListener) {
		this.doItNowListener = aDoItNowListener;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem aMenuItem) {
		int itemId = aMenuItem.getItemId();  // cannot use switch statement because R data members are from a library
		if (itemId == android.R.id.home) {
			this.finish();
		} else if (itemId == R.id.action__help) {
			Intent theIntent = new Intent(Intent.ACTION_VIEW, 
					Uri.parse(getHelpContextUrlString()));
			startActivity(theIntent);
		} else if (itemId == FdkHostSupport.menu_item__FDK) {
			switch(this.fdkHostSupport.getKeyboardState()) {
			case AVAILABLE:
				this.fdkHostSupport.setKeyboardState(FdkKeyboardState.ACTIVE);
				this.fdkHostSupport.setMenuItemIcon();
				this.fdkHostSupport.activateFdkKeyboard(true);
				break;
			case ACTIVE:
				this.fdkHostSupport.setKeyboardState(FdkKeyboardState.AVAILABLE);
				this.fdkHostSupport.setMenuItemIcon();
				this.fdkHostSupport.activateFdkKeyboard(false);
				break;
			case DISABLED:
				break;
			default:
			}
		} else if (itemId == R.id.action__do_it_now) {
			clickDoItNowMenuItem();
		} else if (itemId == R.id.action__publish_pdf) {
			launchPdfPublishingWizard();
		} else if (itemId == R.id.action__keyboard_shortcuts) {
            GcgActivityHelper.startGcgKeyboardShortcutsActivity(this, GcgPhysicalKeyboardShortcutProfile.NAVIGATION_ONLY);
        }
		return true;
	}

    @Override
	public void clickDoItNowMenuItem() {
		switch (this.doItNowMenuItemState) {
			case DO_IT_NOW:
				startGreenActivityStatusAnimation();
            	setDoItNowState(GcgDoItNowMenuItemState.DISABLED);
				this.doItNowListener.doItNow();
				break;
			case SAVE:
				startMagentaActivityStatusAnimation();
            	setDoItNowState(GcgDoItNowMenuItemState.DISABLED);
				saveAllDataModifications();
				stopActivityStatusAnimation();
				break;
			case DISABLED:
				break;
			default:
		}
	}
	
	public void startGreenActivityStatusAnimation() {  // Do It Now processing
		getActivityStatusIndicator().startGreenAnimation();
	}
	
	public void startMagentaActivityStatusAnimation() {  // saving data
		getActivityStatusIndicator().startMagentaAnimation();
	}
	
	public void startOrangeActivityStatusAnimation() {  // retrieving data
		getActivityStatusIndicator().startOrangeAnimation();
	}
	
	public void startBlueActivityStatusAnimation() {  // starting an activity
		getActivityStatusIndicator().startBlueAnimation(false);
	}

	public void startBlueActivityStatusAnimation(boolean aBoolean) {
		getActivityStatusIndicator().startBlueAnimation(aBoolean);
	}
	
	public void startYellowActivityStatusAnimation() {  // auto-updates from database mediator
		getActivityStatusIndicator().startYellowAnimation();
	}
	
	public void stopActivityStatusAnimation() {
		if(this.activityStatusIndicator != null) {
			getActivityStatusIndicator().stopAnimation();
		}
	}

	protected void launchPdfPublishingWizard() {
		return;
	}

	protected void saveAllDataModifications() {
		resetDataHasBeenModified();
	}
	
	public void setMustSelectDataSource(boolean aBoolean) {
		this.mustSelectDataSource = aBoolean;
	}
	
	public boolean mustSelectDataSource() {
		return this.mustSelectDataSource;
	}
	
	public void requestRevertAllModifiedData() {
		this.modalGcgDialogStack.push(new GcgRevertDataOkCancelDialog(this));
		this.modalGcgDialogStack.peek().processDialog();
	}
	
	public void revertAllDataModifications() {  return;  }

	protected boolean isPdfPublisher() {
		return false;
	}
	
	protected File generatePdfFile() {
		return null;
	}
	
	protected boolean isDataModified() {
		return this.dataHasBeenModified;
	}

	public void onDataStateChange() {
		resetActionBarSave();
	}
	
	private void resetDataHasBeenModified() {
		this.dataHasBeenModified = false;
		resetActionBarSave();
	}
	
	public void refreshDataDisplay() {
/*
		startOrangeActivityStatusAnimation();
		// do what you gotta do
		stopActivityStatusAnimation();
 */
	}

	public void resetActionBarSave() {
		if(this.doItNowMenuItem != null) {
			if(this.dataHasBeenModified) {
				setDoItNowState(GcgDoItNowMenuItemState.SAVE);
			} else {
				setDoItNowState(GcgDoItNowMenuItemState.DISABLED);
			}
		}
		if(this.publishPdfMenuItem != null) {  // Hack Alert - for onRestoreInstanceState()
			this.publishPdfMenuItem.setEnabled(! this.dataHasBeenModified);
		}
	}

	public void setHelpContextUrlString(String aHelpContextUrlString) {
		this.helpContextUrlString = aHelpContextUrlString;
	}

	public String getHelpContextUrlString() {
		return this.helpContextUrlString;
	}
	
	public String getActivityNodeIdString() {
		return this.activityNodeIdString;
	}

	public void enableMultiShiftControls(@SuppressWarnings("unused") boolean aBoolean) { return; }

	public void enableDoItNowButton(GcgDoItNowListener aDoItNowListener) {
		this.doItNowListener = aDoItNowListener;
		setDoItNowState(GcgDoItNowMenuItemState.DO_IT_NOW);
	}

	public void disableDoItNowButton() {
		setDoItNowState(GcgDoItNowMenuItemState.DISABLED);
	}
	
	public GcgActivityBreadcrumb getGcgActivityBreadcrumb() {
		GcgActivityBreadcrumb theActivityBreadcrumb = new GcgActivityBreadcrumb(
				getBreadcrumbDrawableResourceId(),
				getBreadcrumbHeadline(),
				getActivityNodeIdString(),
				getBreadcrumbTargetNodeIdString() );
		theActivityBreadcrumb.setFrameBreadcrumb(getFrameBreadcrumb());
		return theActivityBreadcrumb;
	}

	protected GcgFrameBreadcrumb getFrameBreadcrumb() {
		if(getGcgFrame() == null) {
			return null;
		}
		GcgFrameBreadcrumb theFrameBreadcrumb = new GcgFrameBreadcrumb(R.drawable.gcg__frame, getGcgFrameName());
		if(getGcgPerspective() != null) {
			theFrameBreadcrumb.setPerspective(getGcgPerspective());
			theFrameBreadcrumb.setPerspectiveContext(this.perspectiveContext);
		}
		return theFrameBreadcrumb;
	}

	protected abstract int getBreadcrumbDrawableResourceId();

	protected abstract String getBreadcrumbHeadline();

	protected abstract String getBreadcrumbTargetNodeIdString();


	@Override
	protected void onActivityResult(int aRequestCode, int aResultCode, Intent anIntent) {
		if(anIntent == null) {
			return;
		}
		if(aResultCode != GcgNavigationTarget.request_code__NAVIGATE) {
			updateDataRefreshInfo(anIntent);
			super.onActivityResult(aRequestCode, aResultCode, anIntent);
		} else {
			updateDataModificationListForContextNavigation(anIntent);
			processGcgApplicationContextNavigationIntent(anIntent);
		}
        if(anIntent.getExtras().containsKey(GcgActivityHelper.bundle_key__RESTORE_GUI_STATE)) {
            this.restoreGuiState = true;
        }
	}

	protected void updateDataRefreshInfo(Intent anIntent) {
		if(anIntent.getExtras().containsKey(GcgActivityHelper.bundle_key__DATA_REFRESH__ALL)) {
			this.dataRefreshAll = true;
		} else if (anIntent.getExtras().containsKey(GcgActivityHelper.bundle_key__DATA_REFRESH__NOTICE_LIST)) {
            processDataRefreshNoticeList();
        }
	}

    private void processDataRefreshNoticeList() { }

    protected void updateDataModificationListForContextNavigation(Intent anIntent) { }

	protected void processGcgApplicationContextNavigationIntent(Intent anIntent) {
		String theString = anIntent.getStringExtra(GcgNavigationTarget.bundle_key__GCG_NAVIGATION_TARGET);
		GcgNavigationTarget theNavigationTarget = null;
		try {
			theNavigationTarget = new GcgNavigationTarget(new JSONObject(theString));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gcgApplicationContextNavigation(theNavigationTarget);
	}
	
	public void gcgApplicationContextNavigation(GcgNavigationTarget aGcgNavigationTarget) {
		if(this.isMainGcgApplicationActivity) {
			navigateToCurrentFramePerspective(aGcgNavigationTarget);
			return;
		}
		if(aGcgNavigationTarget.getGcgApplicationContextBreadcrumb() == null || ! this.activityNodeIdString.equals(
				aGcgNavigationTarget.getGcgApplicationContextBreadcrumb().getActivityIdString()) ) {
			// create data intent
			Intent theIntent = new Intent();   
			theIntent.putExtra(GcgNavigationTarget.bundle_key__GCG_NAVIGATION_TARGET, aGcgNavigationTarget.getJsonObject().toString());
			finish(GcgNavigationTarget.request_code__NAVIGATE, theIntent);
		} 	// else stop on this activity
	}

	private void navigateToCurrentFramePerspective(GcgNavigationTarget aGcgNavigationTarget) {
		if(getGcgFrame() == null || getGcgPerspective() == null) {
			return;
		}
		if(getGcgPerspective() != aGcgNavigationTarget.getGcgPerspective()) {
			activityNavigation(getGcgFrame(), aGcgNavigationTarget.getGcgPerspective());
		}
	}

	public void activityNavigation(GcgFrame aFrame, GcgPerspective aPerspective) {
		setGcgFrame(aFrame);
		setGcgPerspective(aPerspective);
	}

	@Override
	public void finish() {
		finishOk();
	}

    public void finish(boolean bParentDataRefreshAll) {
        this.parentDataRefreshAll = bParentDataRefreshAll;
        finishOk();
    }

    public void finish(boolean bParentDataRefreshAll, boolean bRestoreGuiState) {
        this.parentDataRefreshAll = bParentDataRefreshAll;
        this.restoreGuiState = bRestoreGuiState;
        finishOk();
    }

	public void finish(int anActivityResultCode) {
		finish(anActivityResultCode, null);
	}

	public void finish(Intent anIntent) {
		finish(Activity.RESULT_OK, anIntent);
	}
	
	public void finishOk() {
		finish(Activity.RESULT_OK, null);
	}
	
	public void finish(int anActivityResultCode, Intent anIntent) {
		if(protectDataChanges(GcgSaveChangesDialog.next_action__FINISH, getActiveViewGroupName())) {
			return;
		}
		Intent theIntent = anIntent;
		if(theIntent == null) {
			theIntent = new Intent();
		}
		if(hasDataRefreshList()) {
			theIntent.putExtra(GcgActivityHelper.bundle_key__MODIFIED_TREE_NODE__LIST, getSerializedDataRefreshNoticeList());
		}
		if(this.parentDataRefreshAll) {
			theIntent.putExtra(GcgActivityHelper.bundle_key__DATA_REFRESH__ALL, "");
		}
        if(this.restoreGuiState) {
            theIntent.putExtra(GcgActivityHelper.bundle_key__RESTORE_GUI_STATE, "");
        }
		if(hasParentDataRefreshList()) {
			theIntent.putExtra(GcgActivityHelper.bundle_key__DATA_REFRESH__NOTICE_LIST, getSerializedParentDataRefreshNoticeList());
		}
		if(anIntent == null && theIntent.getExtras() == null) {
			setResult(anActivityResultCode);
		} else {
			setResult(anActivityResultCode, theIntent);
		}
		super.finish();
	}

    protected String getSerializedDataRefreshNoticeList() {
        return "";
    }

    protected boolean hasParentDataRefreshList() {
        return false;
    }

    protected boolean hasDataRefreshList() {
        return false;
    }

    protected String getSerializedParentDataRefreshNoticeList() {
		return "";
	}

	public boolean protectDataChanges(int aNextAction, String aTargetDetail) {
		if(this.discardDataChanges) {
			return false;
		}
		if(isDataModified() && ! automaticallySaveDataModifications()) {
			startDialog(new GcgSaveChangesDialog(this, aTargetDetail, aNextAction));
			return true;
		}
		if(isDataModified()) {
			saveAllDataModifications();
		}
		return false;
	}

	private static boolean automaticallySaveDataModifications() {
		return GcgApplication.getInstance().automaticallySaveDataModifications();
	}

	public void saveDataChangesDialogResults(int aButtonChoice, boolean bAutoSaveNextTime, int aNextAction) {
		setAutomaticallySaveDataModifications(bAutoSaveNextTime);
		switch (aButtonChoice) {
			case GcgSaveChangesDialog.button_choice__DISCARD:
				this.discardDataChanges = true;
				resetDataHasBeenModified();
				initiateNextAction(aNextAction);
				break;
			case GcgSaveChangesDialog.button_choice__SAVE:
				saveAllDataModifications();
				initiateNextAction(aNextAction);
				break;
			case GcgSaveChangesDialog.button_choice__CANCEL_NAVIGATION:
				break;
			default:
		}
	}
	
	protected void initiateNextAction(int aNextAction) {
		switch(aNextAction) {
			case GcgSaveChangesDialog.next_action__FINISH:
				finish();
				return;
			default:
		}
	}

	private static void setAutomaticallySaveDataModifications(boolean aBoolean) {
		GcgApplication.getInstance().setAutomaticallySaveDataModifications(aBoolean);
	}

	public String getActiveViewGroupName() {
		return this.activeViewGroupName;
	}
	
	protected void setActiveViewGroupName(String aName) {
		this.activeViewGroupName = aName;
	}
	
	@SuppressWarnings("null")
	public String getActivityLabel() {
		if(this.activityLabel == null) {
			PackageManager thePackageManager = getPackageManager();
			ActivityInfo theActivityInfo = null;
			try {
				theActivityInfo = thePackageManager.getActivityInfo(getComponentName(), 0);
			} catch (NameNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.activityLabel = theActivityInfo.loadLabel(thePackageManager).toString();
		}
		return this.activityLabel;
	}

	public GcgFrame getGcgFrame() {
		return this.frameSpinner == null ? null : this.frameSpinner.getFrame();
	}

	public String getGcgFrameName() {
		return this.getGcgFrame() == null ? "" : this.getGcgFrame().getName();
	}
	
	public void setGcgFrame(GcgFrame aGcgFrame) {
        if(this.frameSpinner != null) {
            this.frameSpinner.setFrame(aGcgFrame);
        }
	}

	protected GcgPerspective getGcgPerspective() {
		return this.frameSpinner == null ? null : this.frameSpinner.getPerspective();
	}

	public String getGcgPerspectiveName() {
		return getGcgPerspective() == null ? "" : getGcgPerspective().getName();
	}

	public void setGcgPerspective(GcgPerspective aGcgPerspective) {
        if(this.frameSpinner != null) {
            this.frameSpinner.setPerspective(aGcgPerspective);
        }
	}

	public String getOrganizationName() {
		return "";
	}

	public boolean isApplicationRootActivity() {
		return false;
	}
	
	protected GcgPerspectiveFlipper getGcgPerspectiveFlipper() {
		return null;
	}

	public GcgApplicationContext getChildGcgApplicationContext() {
		if(getGcgApplicationContext() == null) {
			return null;
		}
		GcgApplicationContext theChildContext = new GcgApplicationContext(getGcgApplicationContext().getJsonObject());
		theChildContext.getActivityBreadcrumbList().add(getGcgActivityBreadcrumb());
		return theChildContext;
	}

    protected boolean pruneLastApplicationContextBreadcrumb() {
        return false;
    }

    public void pruneLastApplicationContextBreadcrumb(GcgActivityBreadcrumb theLastActivityBreadcrumb) {
       if(pruneLastApplicationContextBreadcrumb()) {
           theLastActivityBreadcrumb.setFrameBreadcrumb(null);
       }
    }

	public void updatePerspectiveContext(ArrayList<GcgGuiable> aContextList) {
		this.perspectiveContext = aContextList;
	}

	protected void saveGuiState() {
		if(this.frameSpinner != null) {
			this.frameSpinner.saveGuiState();
		}
	}

	protected void restoreGuiState() {
		if(this.frameSpinner != null) {
			this.frameSpinner.restoreGuiState();
		}
	}

	public void startDialog(GcgDialog anGcgDialog) {
		startDialog(anGcgDialog, activityCurtainEnabled());
	}

	public void startDialog(GcgDialog anGcgDialog, boolean bActivityCurtain) {
		enableActivityCurtain(bActivityCurtain);
		this.modalGcgDialogStack.push(anGcgDialog);
		this.modalGcgDialogStack.peek().processDialog();
	}

	public void replaceDialog(GcgDialog anGcgDialog) {
		this.modalGcgDialogStack.peek().dismiss();
		this.modalGcgDialogStack.pop();
		this.modalGcgDialogStack.push(anGcgDialog);
		this.modalGcgDialogStack.peek().processDialog();
	}

	public void stopDialog() {
		stopDialog(false);
	}

	public void stopDialog(boolean bRefreshPreviousDialogOrActivity) {
		this.modalGcgDialogStack.peek().dismiss();
		this.modalGcgDialogStack.pop();
		if(this.modalGcgDialogStack.size() == 0) {
			if(this.mustSelectDataSource) {
				finish();
			} else if(bRefreshPreviousDialogOrActivity) {
				buildContentViewForDataSource();
			}
			enableActivityCurtain(false);
		} else if(bRefreshPreviousDialogOrActivity) {
			refreshDialog();
		}
	}

    public void stopAllDialogs() {
        while(this.modalGcgDialogStack.size() > 0) {
            this.modalGcgDialogStack.peek().dismiss();
            this.modalGcgDialogStack.pop();
        }
        enableActivityCurtain(false);
    }
	
	public void refreshDialog() {
		this.modalGcgDialogStack.peek().refreshDialog();
	}

	@Override
	public boolean fdkSpeechRecognitionSupported() {
		return this.fdkHostSupport.fdkSpeechRecognitionSupported();
	}

	@Override
	public FdkKeyboard getFdkKeyboard() {
		return this.fdkHostSupport.getFdkKeyboard();
	}

	@Override
	public void startDictation() {
		this.fdkHostSupport.startDictation();
	}

	@Override
	public void stopDictation() {
		this.fdkHostSupport.stopDictation();
	}

	@Override
	public void cancelDictation() {
		this.fdkHostSupport.cancelDictation();
	}
	
	@Override
	public void setFdkKeyboard(FdkKeyboard anFdkKeyboard) {
		this.fdkHostSupport.setFdkKeyboard(anFdkKeyboard);
	}

	@Override
	public void activateFdkKeyboard(boolean bActivate) {
		this.fdkHostSupport.activateFdkKeyboard(bActivate);
	}

	public FrameLayout getGcgThumbpadLeft() {
		this.fdkHostSupport.initGcgThumbpadLeft(getWindow());
		return this.fdkHostSupport.getGcgThumbpadLeft();
	}

	public ViewGroup getFdkKeypadPeerViewLeft() {
		this.fdkHostSupport.initFdkKeypadPeerViewLeft(getWindow());
		return this.fdkHostSupport.getFdkKeypadPeerViewLeft();
	}
	
	@Override
	public void toggleSoftKeyboardActive() {
		if(this.fdkHostSupport != null) {
			this.fdkHostSupport.toggleSoftKeyboardActive();
		}
	}
	
	public IBinder getWindowToken() {
		ViewGroup theContentView = (ViewGroup) this.findViewById(android.R.id.content);
		return theContentView.getWindowToken();
	}

	public boolean isDataRefreshAll() {
		return this.dataRefreshAll;
	}

	public void setDataRefreshAll(boolean dataRefreshAll) {
		this.dataRefreshAll = dataRefreshAll;
	}

    public boolean isParentDataRefreshAll() {
        return this.parentDataRefreshAll;
    }

    public void setParentDataRefreshAll(boolean dataRefreshAll) {
        this.parentDataRefreshAll = dataRefreshAll;
    }
	
	public GcgTreeViewAdapter getActiveGcgTreeViewAdapter() {
		return this.activeTreeViewAdapter;
	}
	
	public void onWidgetDataChangeListener(@SuppressWarnings("unused") int aResourceId) { return; }
}
