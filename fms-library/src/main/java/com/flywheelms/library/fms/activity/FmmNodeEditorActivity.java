/* @(#)FmmNodeEditorActivity.java
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

package com.flywheelms.library.fms.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.flywheelms.gcongui.gcg.context.GcgApplicationContext;
import com.flywheelms.gcongui.gcg.context.GcgFrame;
import com.flywheelms.gcongui.gcg.context.GcgFrameBreadcrumb;
import com.flywheelms.gcongui.gcg.dialog.GcgSaveChangesDialog;
import com.flywheelms.gcongui.gcg.helper.GcgActivityHelper;
import com.flywheelms.gcongui.gcg.helper.GcgPerspectiveMenu;
import com.flywheelms.gcongui.gcg.menu.GcgFrameSpinner;
import com.flywheelms.gcongui.gcg.menu.GcgPerspectiveMenuButton;
import com.flywheelms.gcongui.gcg.viewflipper.GcgPerspectiveFlipper;
import com.flywheelms.gcongui.gcg.viewflipper.GcgViewFlipper;
import com.flywheelms.library.R;
import com.flywheelms.library.fmm.context.FmmFrame;
import com.flywheelms.library.fmm.context.FmmPerspective;
import com.flywheelms.library.fmm.enumerator.FmmNodeTransactionType;
import com.flywheelms.library.fmm.node.FmmHeadlineNodeShallow;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmNode;
import com.flywheelms.library.fmm.transaction.FmmDataRefreshNotice;
import com.flywheelms.library.fmm.transaction.FmmDataRefreshType;
import com.flywheelms.library.fms.dialog.HeadlineNodeHeadlineEditDialog;
import com.flywheelms.library.fms.helper.FmsActivityHelper;
import com.flywheelms.library.fms.perspective_flipper.ContextPerspectiveFlipper;
import com.flywheelms.library.fms.perspective_flipper.TribKnPerspectiveFlipper;
import com.flywheelms.library.fse.FseDocumentSectionType;
import com.flywheelms.library.fse.interfaces.FmsPerspectiveFlipperParent;
import com.flywheelms.library.fse.model.FseDocument;
import com.flywheelms.library.fse.perspective_flipper.FsePerspectiveFlipper;
import com.flywheelms.library.fse.widget.FseMultiShiftButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public abstract class FmmNodeEditorActivity extends FmsHorizontalNodeNavigatorActivity
		implements FmsPerspectiveFlipperParent {
	
	protected FmmNodeDefinition fmmNodeDefinition;
	protected String navigationParentNodeIdString;
	protected FmmNodeDefinition navigationParentFmmNodeDefinition;
	protected FseMultiShiftButton leftMultiShiftControl;
	protected FmmFrame initialFrame;
	protected FmmPerspective initialPerspective;

	public FmmNodeEditorActivity(FmmNodeDefinition anFmmNodeDefinition, String aHelpContextUrlString) {
		super(aHelpContextUrlString);
		this.fmmNodeDefinition = anFmmNodeDefinition;
	}
	
	@Override
	public void onCreate(Bundle aSavedInstanceState) {
	    super.onCreate(aSavedInstanceState);
	}

	@Override
	protected void processExtras() {
		super.processExtras();
		this.navigationParentNodeIdString = getIntent().getExtras().getString(FmsActivityHelper.bundle_key__NAVIGATION_PARENT_NODE_ID);
		if(this.navigationParentNodeIdString != null) {
			this.navigationParentFmmNodeDefinition = FmmNodeDefinition.getEntryForNodeIdString(this.navigationParentNodeIdString);
		}
		this.initialFrame = FmmFrame.getFmmObjectForName(getIntent().getExtras().getString(FmsActivityHelper.bundle_key__INITIAL_FRAME_TO_DISPLAY));
		this.initialPerspective = FmmPerspective.getFmmObjectForName(getIntent().getExtras().getString(FmsActivityHelper.bundle_key__INITIAL_PERSPECTIVE_TO_DISPLAY));
	}

	@Override
	protected void processSavedInstanceState(Bundle aSavedInstanceState) {
		super.processSavedInstanceState(aSavedInstanceState);
		this.navigationParentNodeIdString = aSavedInstanceState.getString(FmsActivityHelper.bundle_key__NAVIGATION_PARENT_NODE_ID);
		String theClassName = aSavedInstanceState.getString(FmsActivityHelper.bundle_key__NAVIGATION_PARENT_CLASS_NAME);
		this.navigationParentFmmNodeDefinition = FmmNodeDefinition.getEntryForNodeClassName(theClassName);
		try {
			setGcgApplicationContext(new GcgApplicationContext(new JSONObject(aSavedInstanceState.getString(GcgActivityHelper.bundle_key__GCG__APPLICATION_CONTEXT))));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		initializeFrameSpinner();
		String theSerializedFseDocument = aSavedInstanceState.getString(FmsActivityHelper.bundle_key__FSE_DOCUMENT);
		getFsePerspectiveFlipper().setDisplayedFseDocument(new FseDocument(theSerializedFseDocument));
		getFsePerspectiveFlipper().setActiveDocumentSectionType(FseDocumentSectionType.getTypeForName(
				aSavedInstanceState.getString(FmsActivityHelper.bundle_key__FSE_DOCUMENT_SECTION_TYPE) ));
	}

	// TODO - needs more work to accurately restore paragraph markup; content modification
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		this.frameSpinner.restorePerspectiveFlippers();
		resetActionBarSave();
		displayFmmNode();
//		getActivePerspectiveFlipper().viewData();
		for(GcgPerspectiveFlipper thePerspectiveFlipper : this.frameSpinner.getPerspectiveFlipperCollection()) {
			thePerspectiveFlipper.viewData();
		}
		// TODO - restore parentNodeId for each PerspectiveFlipper
	}
	
	@Override
	public FsePerspectiveFlipper getFsePerspectiveFlipper() {
		return (FsePerspectiveFlipper) this.frameSpinner.getPerspectiveFlipper(FmmFrame.FSE);
	}
	
	@Override
	public TribKnPerspectiveFlipper getTribKnPerspectiveFlipper() {
		return (TribKnPerspectiveFlipper) this.frameSpinner.getPerspectiveFlipper(FmmFrame.TRIBKN);
	}
	
	@Override
	public ContextPerspectiveFlipper getContextPerspectiveFlipper() {
		return (ContextPerspectiveFlipper) this.frameSpinner.getPerspectiveFlipper(FmmFrame.CONTEXT_FOR_NODE);
	}

	public FseDocument getDisplayedFseDocument() {
		return getFsePerspectiveFlipper().getDisplayedFseDocument();
	}

	@Override
	public void onSaveInstanceState(Bundle theBundle) {
		super.onSaveInstanceState(theBundle);
		theBundle.putString(FmsActivityHelper.bundle_key__NAVIGATION_PARENT_NODE_ID, this.navigationParentNodeIdString);
		theBundle.putString(FmsActivityHelper.bundle_key__FMM_NODE__NAME, getSerializedFmmHeadlineNodeFromView());
		theBundle.putString(FmsActivityHelper.bundle_key__DISPLAYED_FSE_DOCUMENT, getFsePerspectiveFlipper().getDisplayedFseDocument().getSerialized());
		theBundle.putString(FmsActivityHelper.bundle_key__FSE_DOCUMENT, getFsePerspectiveFlipper().generateFseDocument().getSerialized());
		theBundle.putString(FmsActivityHelper.bundle_key__FSE_DOCUMENT_SECTION_TYPE, getFsePerspectiveFlipper().getActiveDocumentSectionType().getName());
	}

	@Override
	protected String getSerializedFmmHeadlineNodeFromView() {
		return getDisplayedFmmHeadlineNode().getSerialized();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem aMenuItem) {
		switch (aMenuItem.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		default:
			break;
		}
		return super.onOptionsItemSelected(aMenuItem);
	}
	
	@Override
	protected int getContentViewResourceId() {
		return R.layout.fmm_node__editor__activity;
	}
	
	@Override
	protected int getActivityRootViewResourceId() {
		return R.id.fms_activity__layout_root;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent aTouchevent) {  // for detecting page swipes

		switch (aTouchevent.getAction()) {
			case MotionEvent.ACTION_DOWN:
				FmmNodeEditorActivity.this.lastXposition = aTouchevent.getX();
				return true;
			case MotionEvent.ACTION_UP:
				float theCurrentX = aTouchevent.getX();
				GcgViewFlipper theViewFlipper = (GcgViewFlipper) FmmNodeEditorActivity.this.frameSpinner.getViewBodyArray().get(
						FmmNodeEditorActivity.this.frameSpinner.getActiveMenuIndex()); 
				if (FmmNodeEditorActivity.this.lastXposition < theCurrentX) {
					if (theViewFlipper.getDisplayedChild()==0) break;
					theViewFlipper.setInAnimation(
							theViewFlipper.getFlipInFromLeftAnimation());
					theViewFlipper.setOutAnimation(
							theViewFlipper.getFlipOutToRightAnimation());
					theViewFlipper.showNext();
				}
				if (FmmNodeEditorActivity.this.lastXposition > theCurrentX) {
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
		initializeFrameSpinner();
		super.initializeGui();
    	this.leftMultiShiftControl = (FseMultiShiftButton) getFdkKeypadPeerViewLeft().findViewById(R.id.multi_shift__left_button);
    	this.frameSpinner.setFrame(this.initialFrame);
    	this.frameSpinner.setPerspective(this.initialPerspective);
	}

	private void initializeFrameSpinner() {
		ArrayList<GcgFrame> theFrameList = new ArrayList<GcgFrame>();
		// MUST add in the same sequence as desired in the spinnable menu heading
		theFrameList.add(FmmFrame.FSE);
		theFrameList.add(FmmFrame.TRIBKN);
		theFrameList.add(FmmFrame.CONTEXT_FOR_NODE);
		int[] thePerspectiveMenuBodyResourceIdArray = {
				R.id.perspective_menu__fse__frame_0,
				R.id.perspective_menu__tribkn__frame_1,
				R.id.perspective_menu__context__frame_2 };
		this.frameSpinner = new GcgFrameSpinner(
				this,
				getFdkKeypadPeerViewLeft(),
				R.id.frame_spinner,
				theFrameList,
				thePerspectiveMenuBodyResourceIdArray );
		// MUST initialize in the same sequence as desired in theFrameList
		initFseFrame();
		initTribKnFrame();
		initContextFrame();
	}

	private void initFseFrame() {
		ArrayList<Button> thePerspectiveButtonList = new ArrayList<Button>();
		thePerspectiveButtonList.add((GcgPerspectiveMenuButton) getFdkKeypadPeerViewLeft().findViewById(R.id.fse_button__story));
		thePerspectiveButtonList.add((GcgPerspectiveMenuButton) getFdkKeypadPeerViewLeft().findViewById(R.id.fse_button__notes));
		thePerspectiveButtonList.add((GcgPerspectiveMenuButton) getFdkKeypadPeerViewLeft().findViewById(R.id.fse_button__history));
		thePerspectiveButtonList.add((GcgPerspectiveMenuButton) getFdkKeypadPeerViewLeft().findViewById(R.id.fse_button__community));
		TextView theMenuSpacer = (TextView) getFdkKeypadPeerViewLeft().findViewById(R.id.left_menu__filler);
		GcgPerspectiveMenu theGcgPerspectiveMenu = new GcgPerspectiveMenu(thePerspectiveButtonList, theMenuSpacer);
		FsePerspectiveFlipper theFsePerspectiveFlipper = (FsePerspectiveFlipper) findViewById(R.id.fse_frame__perspective_flipper);
		this.frameSpinner.putFrame(FmmFrame.FSE, theGcgPerspectiveMenu, theFsePerspectiveFlipper);
	}

	private void initTribKnFrame() {
		ArrayList<Button> thePerspectiveButtonList = new ArrayList<Button>();
		thePerspectiveButtonList.add((GcgPerspectiveMenuButton) getFdkKeypadPeerViewLeft().findViewById(R.id.tribkn_button__deckangl));
		thePerspectiveButtonList.add((GcgPerspectiveMenuButton) getFdkKeypadPeerViewLeft().findViewById(R.id.tribkn_button__governance));
		thePerspectiveButtonList.add((GcgPerspectiveMenuButton) getFdkKeypadPeerViewLeft().findViewById(R.id.tribkn_button__commitments));
		thePerspectiveButtonList.add((GcgPerspectiveMenuButton) getFdkKeypadPeerViewLeft().findViewById(R.id.tribkn_button__community));
		TextView theMenuSpacer = (TextView) getFdkKeypadPeerViewLeft().findViewById(R.id.left_menu__filler);
		GcgPerspectiveMenu theGcgPerspectiveMenu = new GcgPerspectiveMenu(thePerspectiveButtonList, theMenuSpacer);
		TribKnPerspectiveFlipper theTribKnPerspectiveFlipper = (TribKnPerspectiveFlipper) findViewById(R.id.tribkn_frame__perspective_flipper);
		this.frameSpinner.putFrame(FmmFrame.TRIBKN, theGcgPerspectiveMenu, theTribKnPerspectiveFlipper);
	}

	private void initContextFrame() {
		ArrayList<Button> thePerspectiveButtonList = new ArrayList<Button>();
		thePerspectiveButtonList.add((GcgPerspectiveMenuButton) getFdkKeypadPeerViewLeft().findViewById(R.id.context_button__strategic_planning));
		thePerspectiveButtonList.add((GcgPerspectiveMenuButton) getFdkKeypadPeerViewLeft().findViewById(R.id.context_button__work_breakdown));
		thePerspectiveButtonList.add((GcgPerspectiveMenuButton) getFdkKeypadPeerViewLeft().findViewById(R.id.context_button__work_planning));
		thePerspectiveButtonList.add((GcgPerspectiveMenuButton) getFdkKeypadPeerViewLeft().findViewById(R.id.context_button__analysis));
		TextView theMenuSpacer = (TextView) getFdkKeypadPeerViewLeft().findViewById(R.id.left_menu__filler);
		GcgPerspectiveMenu theGcgPerspectiveMenu = new GcgPerspectiveMenu(thePerspectiveButtonList, theMenuSpacer);
		ContextPerspectiveFlipper theContextPerspectiveFlipper = (ContextPerspectiveFlipper) findViewById(R.id.context_frame__perspective_flipper);
		this.frameSpinner.putFrame(FmmFrame.CONTEXT_FOR_NODE, theGcgPerspectiveMenu, theContextPerspectiveFlipper);
	}

	public FmmNodeDefinition getFmmNodeDefinition() {
		if(this.fmmNodeDefinition == null) {
			this.fmmNodeDefinition = FmmNodeDefinition.getEntryForClass(getNodeClass());
		}
		return this.fmmNodeDefinition;
	}

	protected abstract Class<? extends FmmNode> getNodeClass();
	
	protected void displayFmmNode() {
		setStaleData();
		getFsePerspectiveFlipper().setParentNodeIdString(this.displayedFmmHeadlineNodeShallow.getNodeIdString());
//		getActivePerspectiveFlipper().viewData();
		for(GcgPerspectiveFlipper thePerspectiveFlipper : this.frameSpinner.getPerspectiveFlipperCollection()) {
			thePerspectiveFlipper.viewData();
		}
//		stopActivityStatusAnimation();
	}
	
	@Override
	public void setStaleData() {
		setStaleData(true);
	}
	
	@Override
	public void setStaleData(boolean aBoolean) {
		getFsePerspectiveFlipper().setStaleData(aBoolean);
	}

	@Override
	public void saveAllDataModifications() {
		if(getFsePerspectiveFlipper().isDataModified()) {
			getFsePerspectiveFlipper().saveFseDocument();
			this.modifiedFmmNodeIdList.put(getFsePerspectiveFlipper().getDocumentId(), FmmNodeTransactionType.MODIFY);
		}
		super.saveAllDataModifications();
	}
	
	@Override
	protected void initiateNextAction(int aNextAction) {
		super.initiateNextAction(aNextAction);
		switch(aNextAction) {
			case GcgSaveChangesDialog.next_action__NAVIGATE_LEFT:
				navigateLeft();
				return;
			case GcgSaveChangesDialog.next_action__NAVIGATE_RIGHT:
				navigateRight();
				return;
			case GcgSaveChangesDialog.next_action__NAVIGATE_TO_BEGINNING:
				navigateToBeginning();
				return;
			case GcgSaveChangesDialog.next_action__NAVIGATE_TO_END:
				navigateToEnd();
				return;
			case GcgSaveChangesDialog.next_action__BROWSE_TRANSACTION_HISTORY:
				getFsePerspectiveFlipper().getHistorySectionPerspective().launchFseHistoryBrowser();
				return;
			case GcgSaveChangesDialog.next_action__REVERT_DOCUMENT_TO_TRANSACTION:
				getFsePerspectiveFlipper().getHistorySectionPerspective().revertDocumentToSelectedTransactionRow();
				return;
			default:
		}
	}

	@Override
	protected boolean isPdfPublisher() {
		return true;
	}

	@Override
	protected void launchPdfPublishingWizard() {
		FmsActivityHelper.startPdfPublicationWizard(
				this,
				getDisplayedFmmNodeIdString() );
	}

	@Override
	public void enableMultiShiftControls(boolean aBoolean) {
		this.leftMultiShiftControl.setVisibility(
				aBoolean ? View.VISIBLE : View.INVISIBLE );
	}
	
	public void leftMenuClickSpinner() {
		this.frameSpinner.clickSpinner();
	}

	public void leftMenuClickItem(int anItemNumber) {
		GcgViewFlipper theViewFlipper = (GcgViewFlipper) this.frameSpinner.getViewBodyArray().get(
				this.frameSpinner.getActiveMenuIndex()); 
		int theMenuItemCount = theViewFlipper.getChildCount();
		if(anItemNumber <= theMenuItemCount) {
			if(anItemNumber == 1) {
				theViewFlipper.flipToIndex(0);
			} else {
				int theChildIndex = theMenuItemCount - anItemNumber + 1;
				theViewFlipper.flipToIndex(theChildIndex);
			}
		}
	}

	public void newFractal() {
		// TODO Auto-generated method stub
	}

	@Override
	public void revertAllDataModifications() {
		super.revertAllDataModifications();
		displayFmmNode();
		setAsNewBaseline();
	}

	private void setAsNewBaseline() {
		getFsePerspectiveFlipper().setAsNewBaseline();
		// TODO - this.anotherPerspective.setAsNewBaseline();
		this.dataHasBeenModified = false;
		resetActionBarSave();
	}

	@Override
	public void onDataStateChange() {
		this.dataHasBeenModified = getFsePerspectiveFlipper().isDataModified();
//		this.dataHasBeenModified = getFsePerspectiveFlipper().isDataModified() || this.anotherView.isDataModified();
		super.onDataStateChange();
	}

	@Override
	protected String getBreadcrumbHeadline() {
		return getDisplayedFmmHeadlineNode().getHeadline();
	}

	@Override
	protected FmmNodeDefinition getDisplayedFmmNodeDefinition() {
		return this.fmmNodeDefinition;
	}

	@Override
	protected String getBreadcrumbTargetNodeIdString() {
		return getDisplayedFmmNodeIdString();
	}

	@Override
	protected void navigateToListItem(FmmHeadlineNodeShallow aHeadlineNodeShallow) {
		super.navigateToListItem(aHeadlineNodeShallow);
		displayFmmNode();
	}

	@Override
	protected GcgFrameBreadcrumb getFrameBreadcrumb() {
		if(getGcgFrame() == null) {
			return null;
		}
		GcgFrameBreadcrumb theFrameBreadcrumb = new GcgFrameBreadcrumb(R.drawable.gcg__frame, getGcgFrameName());
		return theFrameBreadcrumb;
	}
	
	@Override
	protected boolean headlineEditingEnabled() {
		return this.fmmNodeDefinition != FmmNodeDefinition.FISCAL_YEAR &&
                this.fmmNodeDefinition != FmmNodeDefinition.FLYWHEEL_CADENCE &&
                this.fmmNodeDefinition != FmmNodeDefinition.WORK_PLAN;
	}
	
	@Override
	protected void editHeadline() {
		startDialog(new HeadlineNodeHeadlineEditDialog(this, getDisplayedFmmHeadlineNode()));
	}

	public void updateHeadlineNodeHeadline(FmmHeadlineNode aHeadlineNode) {
		int theSpinnerPosition = this.nodeNavigationSpinner.getSelectedItemPosition();
		this.fmmHeadlineNodeShallowList.get(theSpinnerPosition).setHeadline(aHeadlineNode.getHeadline());
		this.nodeSpinnerAdapter.notifyDataSetChanged();
		updateParentDataRefreshList(new FmmDataRefreshNotice(aHeadlineNode, FmmDataRefreshType.HEADLINE));
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		finishOk();
	}

	public void updateTargetDate(FmmHeadlineNode aHeadlineNode) {
		// TODO Auto-generated method stub
		
	}

}
