/* @(#)WorkbenchActivity.java
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
package com.flywheelms.workbench.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.flywheelms.gcongui.gcg.context.GcgApplicationContext;
import com.flywheelms.gcongui.gcg.context.GcgFrame;
import com.flywheelms.gcongui.gcg.helper.GcgPerspectiveMenu;
import com.flywheelms.gcongui.gcg.interfaces.GcgGuiable;
import com.flywheelms.gcongui.gcg.interfaces.GcgPerspective;
import com.flywheelms.gcongui.gcg.menu.GcgFrameSpinner;
import com.flywheelms.gcongui.gcg.menu.GcgPerspectiveMenuButton;
import com.flywheelms.gcongui.gcg.viewflipper.GcgPerspectiveFlipper;
import com.flywheelms.gcongui.gcg.viewflipper.GcgViewFlipper;
import com.flywheelms.library.R;
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.FmmDatabaseTemplate;
import com.flywheelms.library.fmm.context.FmmFrame;
import com.flywheelms.library.fmm.deckangl.FmmDecKanGlDictionary;
import com.flywheelms.library.fmm.node.FmmHeadlineNodeShallow;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.repository.FmmConfiguration;
import com.flywheelms.library.fms.activity.FmsActivity;
import com.flywheelms.library.fms.dialog.FmmManagementDialog;
import com.flywheelms.library.fms.dialog.FmmSelectionDialog;
import com.flywheelms.library.fms.helper.FmsActivityHelper;
import com.flywheelms.library.fms.interfaces.FmsDecKanGlNavigationDialogParent;
import com.flywheelms.workbench.dialog.FlywheelMsAboutDialog;
import com.flywheelms.workbench.perspective_flipper.FwbCommitmentsPerspectiveFlipper;
import com.flywheelms.workbench.perspective_flipper.FwbContextPerspectiveFlipper;
import com.flywheelms.workbench.perspective_flipper.FwbQualityPerspectiveFlipper;
import com.flywheelms.workbench.perspective_flipper.FwbTeamPerspectiveFlipper;
import com.flywheelms.workbench.perspective_flipper.FwbVelocityPerspectiveFlipper;

import java.util.ArrayList;

// com.flywheelms.fwb.activity.WorkbenchActivity
public class WorkbenchActivity extends FmsActivity implements FmsDecKanGlNavigationDialogParent {

	private static final String HELP_CONTEXT_URL_STRING = "http://code.google.com/p/flywheelms-hd/wiki/UserDocWorkbench";
	private static WorkbenchActivity instance;
	protected FmmManagementDialog fmmManagementDialog;
    private FrameLayout perspectiveFlipperParentFrame;
    private boolean closeFmm = false;

	public WorkbenchActivity() {
		super(HELP_CONTEXT_URL_STRING);
		WorkbenchActivity.instance = this;
		this.setDisplayHome = false;
		this.isMainGcgApplicationActivity = true;
		this.mustSelectDataSource = true;
		FmmDecKanGlDictionary.getInstance();
		FmmDatabaseTemplate.initializeTemplates();
	}

    @Override
    protected int getContentViewResourceId() {
        return com.flywheelms.workbench.R.layout.workbench__frame_spinner__layout;
    }

	@Override
	protected void onCreate(Bundle aSavedInstanceState) {
		if(aSavedInstanceState != null) {
			this.mustSelectDataSource = aSavedInstanceState.getBoolean(FmsActivityHelper.bundle_key__MUST_SELECT_DATA_SOURCE);
            if(aSavedInstanceState.containsKey(FmsActivityHelper.bundle_key__FMM_CONFIGURATION)) {
                FmmDatabaseMediator.setActiveFmmConfiguration(FmmConfiguration.rehydrate(aSavedInstanceState.getString(FmsActivityHelper.bundle_key__FMM_CONFIGURATION)));
            }
        }
		if(this.mustSelectDataSource) {
			selectDataSource();
		}
		super.onCreate(aSavedInstanceState);
	}
	
	@Override
	public void onSaveInstanceState(Bundle theBundle) {
		super.onSaveInstanceState(theBundle);
		theBundle.putBoolean(FmsActivityHelper.bundle_key__MUST_SELECT_DATA_SOURCE, this.mustSelectDataSource);
        if(FmmDatabaseMediator.getActiveFmmConfiguration() != null) {
            theBundle.putString(FmsActivityHelper.bundle_key__FMM_CONFIGURATION, FmmDatabaseMediator.getActiveFmmConfiguration().getSerialized());
        }
        if(this.closeFmm) {
            closeFmm();
        }
	}
	
	@Override
	protected void buildContentViewForDataSource() {
		if(this.mustSelectDataSource == false) {
			super.buildContentViewForDataSource();
		}
	}

	@Override
	protected void initializeGcgApplicationContext() {
		setGcgApplicationContext(new GcgApplicationContext(
				R.drawable.fmm_noun__fmm_configuration, FmmDatabaseMediator.getActiveMediator().getActiveFmmConfiguration().getHeadline() ));
	}

	@Override
	protected void selectDataSource() {
		if(this.modalGcgDialogStack.size() == 0) {
			startDialog(new FmmSelectionDialog(this), true);
		}
	}

	@Override
	public void dataSourceSelected(Object aDataSourceObject) {
		startOrangeActivityStatusAnimation();
		FmmDatabaseMediator.setActiveMediator((FmmConfiguration) aDataSourceObject);
		super.dataSourceSelected(aDataSourceObject);
		stopActivityStatusAnimation();
	}

	public static WorkbenchActivity getInstance() {
		return WorkbenchActivity.instance;
	}

	@Override
	protected void processExtras() {
		return;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu aMenu) {
		getMenuInflater().inflate(com.flywheelms.workbench.R.menu.workbench_activity__options, aMenu);
		this.activityStatusIndicatorMenuItem = aMenu.findItem(R.id.activity_status_indicator);
		this.activityStatusIndicatorMenuItem.setActionView(getActivityStatusIndicator());
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem aMenuItem) {
		switch (aMenuItem.getItemId()) {
			case com.flywheelms.workbench.R.id.action__recalc_fmm:
//				buildContentViewForDataSource();
				refreshDataDisplay();
				break;
			case com.flywheelms.workbench.R.id.action__exit:
				enableActivityCurtain(true);
				closeFmm();
                finish();
				break;
			case R.id.action__publish_pdf:
				break;
			case com.flywheelms.workbench.R.id.action__switch_fmm_repository:
				this.mustSelectDataSource = true;
                setGcgApplicationContext(null);
                startDialog(new FmmSelectionDialog(this), true);
                this.closeFmm = true;  // so the activity can state first
                break;
			case com.flywheelms.workbench.R.id.action__deckangl__glyph_dictionary:
				FmsActivityHelper.startDecKanGlGlyphDictionaryActivity(this);
				break;
            case R.id.action__dictionary:
                launchDictionary();
                return true;
			case com.flywheelms.workbench.R.id.action__about_flywheelms:
				startDialog(new FlywheelMsAboutDialog(this));
				break;
			default:
				break;
		}
		return super.onOptionsItemSelected(aMenuItem);
	}

	private void closeFmm() {
        saveGuiState();
		FmmDatabaseMediator.closeActiveFmm();
		resetApplicationContext();
		this.mustSelectDataSource = true;
        this.closeFmm = false;
	}

	@Override
	protected String getBreadcrumbHeadline() {
		return FmmDatabaseMediator.getActiveMediator().getFmmOwner().getName();
	}


	@Override
	protected FmmNodeDefinition getDisplayedFmmNodeDefinition() {
		return FmmNodeDefinition.FMM_CONFIGURATION;
	}


	@Override
	protected String getBreadcrumbTargetNodeIdString() {
		return FmmDatabaseMediator.getActiveMediator().getActiveFmmConfiguration().getNodeIdString();
	}

	@Override
	public boolean onTouchEvent(MotionEvent aTouchevent) {  // for detecting page swipes
		if(this.frameSpinner == null) {
			return false;
		}
		switch (aTouchevent.getAction()) {
		case MotionEvent.ACTION_DOWN:
			WorkbenchActivity.this.lastXposition = aTouchevent.getX();
			return true;
		case MotionEvent.ACTION_UP:
			float theCurrentX = aTouchevent.getX();
			GcgViewFlipper theViewFlipper = (GcgViewFlipper) WorkbenchActivity.this.frameSpinner.getViewBodyArray().get(
					WorkbenchActivity.this.frameSpinner.getActiveMenuIndex()); 
			if (WorkbenchActivity.this.lastXposition < theCurrentX) {
				if (theViewFlipper.getDisplayedChild()==0) break;
				theViewFlipper.setInAnimation(
						theViewFlipper.getFlipInFromLeftAnimation());
				theViewFlipper.setOutAnimation(
						theViewFlipper.getFlipOutToRightAnimation());
				theViewFlipper.showNext();
			}
			if (WorkbenchActivity.this.lastXposition > theCurrentX) {
				if (theViewFlipper.getDisplayedChild()==1) break;
				theViewFlipper.setInAnimation(
						theViewFlipper.getFlipInFromRightAnimation());
				theViewFlipper.setOutAnimation(
						theViewFlipper.getFlipOutToLeftAnimation());
				theViewFlipper.showPrevious();
			}
			return true;
		default:
			return false;
		}
		return false;
	}

	@Override
	protected void initializeGui() {
		super.initializeGui();
		initializeFrameSpinner();
	}
	
	@Override
	protected ArrayList<GcgGuiable> getPerspectiveContextGuiableList() {
		return new ArrayList<GcgGuiable>();
	}

	@Override
	protected GcgPerspectiveFlipper getGcgPerspectiveFlipper() {
		return this.frameSpinner.getGcgPerspectiveFlipper();
	}

	private void initializeFrameSpinner() {
		ArrayList<GcgFrame> theFrameList = new ArrayList<GcgFrame>();
		// MUST add in the same sequence as desired in the spinnable menu heading
		theFrameList.add(FmmFrame.CONTEXT_FOR_WORKBENCH);
		theFrameList.add(FmmFrame.VELOCITY);
		theFrameList.add(FmmFrame.QUALITY);
		theFrameList.add(FmmFrame.TEAM);
		theFrameList.add(FmmFrame.COMMITMENT);
		int[] thePerspectiveMenuBodyResourceIdArray = {
				com.flywheelms.workbench.R.id.perspective_menu__context__frame_0,
				com.flywheelms.workbench.R.id.perspective_menu__velocity__frame_1,
				com.flywheelms.workbench.R.id.perspective_menu__quality__frame_2,
				com.flywheelms.workbench.R.id.perspective_menu__community__frame_3,
				com.flywheelms.workbench.R.id.perspective_menu__commitments__frame_4 };
		this.frameSpinner = new GcgFrameSpinner(
				this,
				getFdkKeypadPeerViewLeft(),
				R.id.frame_spinner,
				theFrameList,
				thePerspectiveMenuBodyResourceIdArray );
		// MUST initialize in the same sequence as desired in theFrameList
		initContextFrame();
		initVelocityFrame();
		initQualityFrame();
		initTeamsFrame();
		initCommitmentsFrame();
	}

	private void initContextFrame() {
		ArrayList<Button> thePerspectiveButtonList = new ArrayList<Button>();
		// must be added in the same order as the layout file
		thePerspectiveButtonList.add((GcgPerspectiveMenuButton) getFdkKeypadPeerViewLeft().findViewById(com.flywheelms.workbench.R.id.context_frame__strategic_planning__button));
		thePerspectiveButtonList.add((GcgPerspectiveMenuButton) getFdkKeypadPeerViewLeft().findViewById(com.flywheelms.workbench.R.id.context_frame__work_breakdown__button));
		thePerspectiveButtonList.add((GcgPerspectiveMenuButton) getFdkKeypadPeerViewLeft().findViewById(com.flywheelms.workbench.R.id.context_frame__work_planning__button));
		thePerspectiveButtonList.add((GcgPerspectiveMenuButton) getFdkKeypadPeerViewLeft().findViewById(com.flywheelms.workbench.R.id.context_frame__service_delivery__button));
		thePerspectiveButtonList.add((GcgPerspectiveMenuButton) getFdkKeypadPeerViewLeft().findViewById(com.flywheelms.workbench.R.id.context_frame__notebook__button));
		TextView theMenuSpacer = (TextView) getFdkKeypadPeerViewLeft().findViewById(com.flywheelms.workbench.R.id.fwb__context__menu_filler);
		GcgPerspectiveMenu theGcgPerspectiveMenu = new GcgPerspectiveMenu(thePerspectiveButtonList, theMenuSpacer);
		FwbContextPerspectiveFlipper theFwbContextPerspectiveFlipper = (FwbContextPerspectiveFlipper) findViewById(com.flywheelms.workbench.R.id.context_frame__perspective_flipper);
		this.frameSpinner.putFrame(FmmFrame.CONTEXT_FOR_WORKBENCH, theGcgPerspectiveMenu, theFwbContextPerspectiveFlipper);
	}

	private void initVelocityFrame() {
		ArrayList<Button> thePerspectiveButtonList = new ArrayList<Button>();
		thePerspectiveButtonList.add((GcgPerspectiveMenuButton) getFdkKeypadPeerViewLeft().findViewById(com.flywheelms.workbench.R.id.velocity_frame__completion__button));
		thePerspectiveButtonList.add((GcgPerspectiveMenuButton) getFdkKeypadPeerViewLeft().findViewById(com.flywheelms.workbench.R.id.velocity_frame__governance__button));
		thePerspectiveButtonList.add((GcgPerspectiveMenuButton) getFdkKeypadPeerViewLeft().findViewById(com.flywheelms.workbench.R.id.velocity_frame__story__button));
		thePerspectiveButtonList.add((GcgPerspectiveMenuButton) getFdkKeypadPeerViewLeft().findViewById(com.flywheelms.workbench.R.id.velocity_frame__work_breakdown__button));
		thePerspectiveButtonList.add((GcgPerspectiveMenuButton) getFdkKeypadPeerViewLeft().findViewById(com.flywheelms.workbench.R.id.velocity_frame__budgeting__button));
		thePerspectiveButtonList.add((GcgPerspectiveMenuButton) getFdkKeypadPeerViewLeft().findViewById(com.flywheelms.workbench.R.id.velocity_frame__work_planning__button));
		TextView theMenuSpacer = (TextView) getFdkKeypadPeerViewLeft().findViewById(com.flywheelms.workbench.R.id.fwb__velocity__menu_filler);
		GcgPerspectiveMenu theGcgPerspectiveMenu = new GcgPerspectiveMenu(thePerspectiveButtonList, theMenuSpacer);
		FwbVelocityPerspectiveFlipper theFwbVelocityPerspectiveFlipper = (FwbVelocityPerspectiveFlipper) findViewById(com.flywheelms.workbench.R.id.velocity_frame__perspective_flipper);
		this.frameSpinner.putFrame(FmmFrame.VELOCITY, theGcgPerspectiveMenu, theFwbVelocityPerspectiveFlipper);
	}

	private void initQualityFrame() {
		ArrayList<Button> thePerspectiveButtonList = new ArrayList<Button>();
		thePerspectiveButtonList.add((GcgPerspectiveMenuButton) getFdkKeypadPeerViewLeft().findViewById(com.flywheelms.workbench.R.id.quality_frame__strategic_planning__button));
		thePerspectiveButtonList.add((GcgPerspectiveMenuButton) getFdkKeypadPeerViewLeft().findViewById(com.flywheelms.workbench.R.id.quality_frame__work_breakdown__button));
		thePerspectiveButtonList.add((GcgPerspectiveMenuButton) getFdkKeypadPeerViewLeft().findViewById(com.flywheelms.workbench.R.id.quality_frame__work_planning__button));
		thePerspectiveButtonList.add((GcgPerspectiveMenuButton) getFdkKeypadPeerViewLeft().findViewById(com.flywheelms.workbench.R.id.quality_frame__service_delivery__button));
		TextView theMenuSpacer = (TextView) getFdkKeypadPeerViewLeft().findViewById(com.flywheelms.workbench.R.id.fwb__quality__menu_filler);
		GcgPerspectiveMenu theGcgPerspectiveMenu = new GcgPerspectiveMenu(thePerspectiveButtonList, theMenuSpacer);
		FwbQualityPerspectiveFlipper theFwbQualityPerspectiveFlipper = (FwbQualityPerspectiveFlipper) findViewById(com.flywheelms.workbench.R.id.quality_frame__perspective_flipper);
		this.frameSpinner.putFrame(FmmFrame.QUALITY, theGcgPerspectiveMenu, theFwbQualityPerspectiveFlipper);
	}


	private void initTeamsFrame() {
		ArrayList<Button> thePerspectiveButtonList = new ArrayList<Button>();
		thePerspectiveButtonList.add((GcgPerspectiveMenuButton) getFdkKeypadPeerViewLeft().findViewById(com.flywheelms.workbench.R.id.team_frame__strategy_teams__button));
		thePerspectiveButtonList.add((GcgPerspectiveMenuButton) getFdkKeypadPeerViewLeft().findViewById(com.flywheelms.workbench.R.id.team_frame__flywheel_teams__button));
		thePerspectiveButtonList.add((GcgPerspectiveMenuButton) getFdkKeypadPeerViewLeft().findViewById(com.flywheelms.workbench.R.id.team_frame__functional_teams__button));
		thePerspectiveButtonList.add((GcgPerspectiveMenuButton) getFdkKeypadPeerViewLeft().findViewById(com.flywheelms.workbench.R.id.team_frame__governance_teams__button));
		TextView theMenuSpacer = (TextView) getFdkKeypadPeerViewLeft().findViewById(com.flywheelms.workbench.R.id.fwb__team__menu_filler);
		GcgPerspectiveMenu theGcgPerspectiveMenu = new GcgPerspectiveMenu(thePerspectiveButtonList, theMenuSpacer);
		FwbTeamPerspectiveFlipper theFwbTeamPerspectiveFlipper = (FwbTeamPerspectiveFlipper) findViewById(com.flywheelms.workbench.R.id.team_frame__perspective_flipper);
		this.frameSpinner.putFrame(FmmFrame.TEAM, theGcgPerspectiveMenu, theFwbTeamPerspectiveFlipper);
	}

	private void initCommitmentsFrame() {
		ArrayList<Button> thePerspectiveButtonList = new ArrayList<Button>();
		thePerspectiveButtonList.add((GcgPerspectiveMenuButton) getFdkKeypadPeerViewLeft().findViewById(com.flywheelms.workbench.R.id.commitments_frame__confirmed__button));
		thePerspectiveButtonList.add((GcgPerspectiveMenuButton) getFdkKeypadPeerViewLeft().findViewById(com.flywheelms.workbench.R.id.commitments_frame__proposed__button));
		thePerspectiveButtonList.add((GcgPerspectiveMenuButton) getFdkKeypadPeerViewLeft().findViewById(com.flywheelms.workbench.R.id.commitments_frame__suggested__button));
		thePerspectiveButtonList.add((GcgPerspectiveMenuButton) getFdkKeypadPeerViewLeft().findViewById(com.flywheelms.workbench.R.id.commitments_frame__declined__button));
		thePerspectiveButtonList.add((GcgPerspectiveMenuButton) getFdkKeypadPeerViewLeft().findViewById(com.flywheelms.workbench.R.id.commitments_frame__withdrawn__button));
		TextView theMenuSpacer = (TextView) getFdkKeypadPeerViewLeft().findViewById(com.flywheelms.workbench.R.id.fwb__commitments__menu_filler);
		GcgPerspectiveMenu theGcgPerspectiveMenu = new GcgPerspectiveMenu(thePerspectiveButtonList, theMenuSpacer);
		FwbCommitmentsPerspectiveFlipper theFwbCommitmentsPerspectiveFlipper = (FwbCommitmentsPerspectiveFlipper) findViewById(com.flywheelms.workbench.R.id.commitments_frame__perspective_flipper);
		this.frameSpinner.putFrame(FmmFrame.COMMITMENT, theGcgPerspectiveMenu, theFwbCommitmentsPerspectiveFlipper);
	}

	@Override
	protected void onActivityResult(int aRequestCode, int aResultCode, Intent anIntent) {
		if(aRequestCode == FmmNodeDefinition.FMM_CONFIGURATION.getNodeEditorActivityRequestCode() || aRequestCode == FmmNodeDefinition.FMM_CONFIGURATION.getNodeCreateActivityRequestCode()) {
//			if(this.mustSelectDataSource) {
//				selectDataSource();
//			} else {
//				enableActivityCurtain(false);
//			}
			refreshDialog();
			return;
		}
		super.onActivityResult(aRequestCode, aResultCode, anIntent);
	}

	@Override
	public String getOrganizationName() {
		return FmmDatabaseMediator.getActiveMediator().getFmmOwner() == null ? "Null FMM Owner" : FmmDatabaseMediator.getActiveMediator().getFmmOwner().getName();
	}

	@Override
	public boolean isApplicationRootActivity() {
		return true;
	}

	@Override
	public void decKanGlNavigation(
            FmmNodeDefinition anFmmNodeDefinition,
			GcgFrame aFrame,
			GcgPerspective aPerspective,
			String aParentNodeIdString,
			ArrayList<FmmHeadlineNodeShallow> aPeerHeadlineNodeShallowList,
			String aTargetNodeIdString ) {
		FmsActivityHelper.startHeadlineNodeEditorActivity(
				this,
                anFmmNodeDefinition,
				aPeerHeadlineNodeShallowList,
				aParentNodeIdString,
				aTargetNodeIdString,
				aFrame,
				aPerspective );
	}

	@Override
	public void finish() {
		super.finish();
	}
	
	// TODO - needs to be optimized??
	@Override
	public void refreshDataDisplay() {
		this.frameSpinner.getGcgViewFlipperView().refreshDataDisplay();
	}

}
