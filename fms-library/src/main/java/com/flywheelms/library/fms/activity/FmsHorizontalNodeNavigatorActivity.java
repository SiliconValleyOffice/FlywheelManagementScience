/* @(#)FmsHorizontalNodeNavigatorActivity.java
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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.flywheelms.gcongui.gcg.dialog.GcgSaveChangesDialog;
import com.flywheelms.gcongui.gcg.helper.GcgHelper;
import com.flywheelms.library.R;
import com.flywheelms.library.fmm.node.FmmHeadlineNodeShallow;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;
import com.flywheelms.library.fms.helper.FmsActivityHelper;
import com.flywheelms.library.fms.miscellaneous.FmsHeadlineNodeShallowArrayAdapter;

import java.util.ArrayList;

public abstract class FmsHorizontalNodeNavigatorActivity extends FmsActivity {

	protected FmmHeadlineNodeShallow displayedFmmHeadlineNodeShallow;
	protected ArrayList<FmmHeadlineNodeShallow> fmmHeadlineNodeShallowList;
	protected FmsHeadlineNodeShallowArrayAdapter nodeSpinnerAdapter;
	protected String initialNodeIdStringToDisplay;
	protected String serializedDisplayedNodeBaseline = "";
	protected Button navigateFirstButton;
	protected Button navigateLeftButton;
	protected Button navigateRightButton;
	protected Button navigateLastButton;
	protected Spinner nodeNavigationSpinner;
	protected Button headlineEditButton;
	protected TextView navigationListPosition;

	public FmsHorizontalNodeNavigatorActivity(String anInitialHelpContextUrlString) {
		super(anInitialHelpContextUrlString);
	}
	
	@Override
	public void onCreate(Bundle aSavedInstanceState) {
	    super.onCreate(aSavedInstanceState);
	    if(aSavedInstanceState == null) {
	    	this.nodeNavigationSpinner.setSelection(getIndexOfNodeIdString(this.initialNodeIdStringToDisplay));
	    }
	}

	@Override
	protected void processExtras() {
		super.processExtras();
		this.fmmHeadlineNodeShallowList = FmmHeadlineNodeShallow.getHeadlineNodeShallowListFromSerializationList(getIntent().getExtras().getStringArrayList(FmsActivityHelper.bundle_key__FMM_HEADLINE_NODE_SHALLOW_LIST));
		this.initialNodeIdStringToDisplay = getIntent().getExtras().getString(FmsActivityHelper.bundle_key__INITIAL_NODE_TO_DISPLAY);
	}

	@Override
	protected void processSavedInstanceState(Bundle aSavedInstanceState) {
		super.processSavedInstanceState(aSavedInstanceState);
		ArrayList<String> theSerializedHeadlineNodeShallowList = aSavedInstanceState.getStringArrayList(FmsActivityHelper.bundle_key__FMM_HEADLINE_NODE_SHALLOW_LIST);
		this.fmmHeadlineNodeShallowList = FmmHeadlineNodeShallow.getHeadlineNodeShallowListFromSerializationList(theSerializedHeadlineNodeShallowList);
		this.initialNodeIdStringToDisplay = aSavedInstanceState.getString(FmsActivityHelper.bundle_key__INITIAL_NODE_TO_DISPLAY);
		String theSerializedFmmNode = aSavedInstanceState.getString(FmsActivityHelper.bundle_key__FMM_NODE__NAME);
		FmmHeadlineNode theHeadlineNode = getFmmHeadlineNodeFromSerialized(theSerializedFmmNode); 
		FmmHeadlineNodeShallow theHeadlineNodeShallow = getFmmHeadlineNodeShallowFromList(theHeadlineNode.getNodeIdString());
		theHeadlineNodeShallow.setFmmHeadlineNode(theHeadlineNode);
		setDisplayedFmmNodeShallow(theHeadlineNodeShallow);
	}
	
	protected abstract FmmHeadlineNode getFmmHeadlineNodeFromSerialized(String aSerializedFmmNode);

	@Override
	public void onSaveInstanceState(Bundle theBundle) {
		super.onSaveInstanceState(theBundle);
		theBundle.putStringArrayList(FmsActivityHelper.bundle_key__FMM_HEADLINE_NODE_SHALLOW_LIST, FmmHeadlineNodeShallow.getSerializationArrayList(this.fmmHeadlineNodeShallowList));
		theBundle.putString(FmsActivityHelper.bundle_key__INITIAL_NODE_TO_DISPLAY, this.initialNodeIdStringToDisplay);
	}
	
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		updateNavigationListPosition();
	}

	@Override
	protected void initializeGui() {
		super.initializeGui();
		initializeGcgApplicationContextHeader();
		initializeNavigationPanel();
	}

	@Override
	protected int getContentViewResourceId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected String getBreadcrumbHeadline() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected FmmNodeDefinition getDisplayedFmmNodeDefinition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getBreadcrumbTargetNodeIdString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private int getIndexOfNodeIdString(String aNodeIdString) {
		return this.fmmHeadlineNodeShallowList.indexOf(getFmmHeadlineNodeShallowFromList(aNodeIdString));
	}

	private FmmHeadlineNodeShallow getFmmHeadlineNodeShallowFromList(String aNodeIdString) {
		for(FmmHeadlineNodeShallow theHeadlineNodeShallow : this.fmmHeadlineNodeShallowList) {
			if(theHeadlineNodeShallow.getNodeIdString().equals(aNodeIdString)) {
				return theHeadlineNodeShallow;
			}
		}
		return null;
	}

	protected String getSerializedDisplayedNodeBaseline() {
		return this.serializedDisplayedNodeBaseline;
	}

	private void initializeNavigationPanel() {
		getLayoutInflater().inflate(R.layout.gcg__horizontal_node_navigation_panel, this.activityLayout, true);
		this.navigateFirstButton = (Button) findViewById(R.id.navigate_beginning_button);
		this.navigateFirstButton.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FmsHorizontalNodeNavigatorActivity.this.navigateToBeginning();
			}
		});
		this.navigateLeftButton = (Button) findViewById(R.id.navigate_left_button);
		this.navigateLeftButton.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FmsHorizontalNodeNavigatorActivity.this.navigateLeft();
			}
		});
		this.navigateRightButton = (Button) findViewById(R.id.navigate_right_button);
		this.navigateRightButton.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FmsHorizontalNodeNavigatorActivity.this.navigateRight();
			}
		});
		this.navigateLastButton = (Button) findViewById(R.id.navigate_end_button);
		this.navigateLastButton.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FmsHorizontalNodeNavigatorActivity.this.navigateToEnd();
			}
		});
		this.nodeNavigationSpinner = (Spinner) findViewById(R.id.navigation_headline__spinner);
		this.nodeSpinnerAdapter = new FmsHeadlineNodeShallowArrayAdapter(this, this.fmmHeadlineNodeShallowList);
		this.nodeNavigationSpinner.setAdapter(this.nodeSpinnerAdapter);
		this.nodeNavigationSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View aListViewItemView, int aListViewItemPosition, long anItemId) {
				FmsHorizontalNodeNavigatorActivity.this.navigateToSpinnerRow(aListViewItemPosition);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		this.navigationListPosition = (TextView) findViewById(R.id.navigation_position);
		this.headlineEditButton = (Button) findViewById(R.id.headline_edit__button);
		if(this.headlineEditingEnabled()) {

			this.headlineEditButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					FmsHorizontalNodeNavigatorActivity.this.editHeadline();
				}
			});
		} else {
			this.headlineEditButton.setVisibility(View.GONE);
		}
	}
	
	protected boolean headlineEditingEnabled() {
		return false;
	}
	
	protected void editHeadline() {  return;  }

	protected void updateNavigationControls() {
		updateNavigationListPosition();
		if(this.fmmHeadlineNodeShallowList.size() == 1) {
			GcgHelper.enableNavigationButton(this.navigateFirstButton, false);
			GcgHelper.enableNavigationButton(this.navigateLeftButton, false);
			GcgHelper.enableNavigationButton(this.navigateRightButton, false);
			GcgHelper.enableNavigationButton(this.navigateLastButton, false);
		} else {
			int theCurrentIndex = this.fmmHeadlineNodeShallowList.indexOf(getDisplayedFmmHeadlineNodeShallow());
			if(theCurrentIndex == 0) {
				GcgHelper.enableNavigationButton(this.navigateFirstButton, false);
				GcgHelper.enableNavigationButton(this.navigateLeftButton, false);
				GcgHelper.enableNavigationButton(this.navigateRightButton, true);
				GcgHelper.enableNavigationButton(this.navigateLastButton, true);
			} else if(theCurrentIndex == this.fmmHeadlineNodeShallowList.size() - 1) {
				GcgHelper.enableNavigationButton(this.navigateFirstButton, true);
				GcgHelper.enableNavigationButton(this.navigateLeftButton, true);
				GcgHelper.enableNavigationButton(this.navigateRightButton, false);
				GcgHelper.enableNavigationButton(this.navigateLastButton, false);
			} else {
				GcgHelper.enableNavigationButton(this.navigateFirstButton, true);
				GcgHelper.enableNavigationButton(this.navigateLeftButton, true);
				GcgHelper.enableNavigationButton(this.navigateRightButton, true);
				GcgHelper.enableNavigationButton(this.navigateLastButton, true);
			}
		}
	}

	private void updateNavigationListPosition() {
		this.navigationListPosition.setText(
				this.fmmHeadlineNodeShallowList.indexOf(getDisplayedFmmHeadlineNodeShallow()) + 1 +
					"  of " + this.fmmHeadlineNodeShallowList.size() );
	}

	public void navigationSpinnerSelect() {
		this.nodeNavigationSpinner.performClick();
	}

	protected void navigateToBeginning() {
		if(protectDataChanges(GcgSaveChangesDialog.next_action__NAVIGATE_TO_BEGINNING, "Navigate to another FMM node")) {
			return;
		}
		this.discardDataChanges = false;
		int theCurrentIndex = this.fmmHeadlineNodeShallowList.indexOf(getDisplayedFmmHeadlineNodeShallow());
		if(theCurrentIndex == 0) {
			return;
		}
		this.nodeNavigationSpinner.setSelection(0);
	}

	protected void navigateLeft() {
		if(protectDataChanges(GcgSaveChangesDialog.next_action__NAVIGATE_LEFT, "Navigate to another FMM node")) {
			return;
		}
		this.discardDataChanges = false;
		int theCurrentIndex = this.fmmHeadlineNodeShallowList.indexOf(getDisplayedFmmHeadlineNodeShallow());
		if(theCurrentIndex == 0) {
			return;
		}
		this.nodeNavigationSpinner.setSelection(theCurrentIndex - 1);
	}

	protected void navigateRight() {
		if(protectDataChanges(GcgSaveChangesDialog.next_action__NAVIGATE_RIGHT, "Navigate to another FMM node")) {
			return;
		}
		this.discardDataChanges = false;
		int theCurrentIndex = this.fmmHeadlineNodeShallowList.indexOf(getDisplayedFmmHeadlineNodeShallow());
		if(theCurrentIndex == this.fmmHeadlineNodeShallowList.size() - 1) {
			return;
		}
		this.nodeNavigationSpinner.setSelection(theCurrentIndex + 1);
	}

	protected void navigateToEnd() {
		if(protectDataChanges(GcgSaveChangesDialog.next_action__NAVIGATE_TO_END, "Navigate to another FMM node")) {
			return;
		}
		this.discardDataChanges = false;
		int theCurrentIndex = this.fmmHeadlineNodeShallowList.indexOf(getDisplayedFmmHeadlineNodeShallow());
		if(theCurrentIndex == this.fmmHeadlineNodeShallowList.size() - 1) {
			return;
		}
		this.nodeNavigationSpinner.setSelection(this.fmmHeadlineNodeShallowList.size() - 1);
	}

	protected void navigateToListItem(FmmHeadlineNodeShallow aHeadlineNodeShallow) {
		if(getDisplayedFmmHeadlineNode() != null && aHeadlineNodeShallow.getNodeIdString().equals(getDisplayedFmmNodeIdString())) {
			return;
		}
		setDisplayedFmmNodeShallow(aHeadlineNodeShallow);
		updateNavigationControls();
	}
    
	protected void navigateToSpinnerRow(int aListViewItemPosition) {
		FmsHorizontalNodeNavigatorActivity.this.startOrangeActivityStatusAnimation();
		navigateToListItem(this.fmmHeadlineNodeShallowList.get(aListViewItemPosition));
		FmsHorizontalNodeNavigatorActivity.this.stopActivityStatusAnimation();
	}
	
	public void navigateLeftButtonClick() {
		if(this.navigateLeftButton.isEnabled()) {
			this.navigateLeftButton.performClick();
		}
	}
	
	public void navigateRightButtonClick() {
		if(this.navigateRightButton.isEnabled()) {
			this.navigateRightButton.performClick();
		}
	}
	
	public void navigateFirstButtonClick() {
		if(this.navigateFirstButton.isEnabled()) {
			this.navigateFirstButton.performClick();
		}
	}
	
	public void navigateLastButtonClick() {
		if(this.navigateLastButton.isEnabled()) {
			this.navigateLastButton.performClick();
		}
	}
	
	protected void setDisplayedFmmNodeShallow(FmmHeadlineNodeShallow aHeadlineNodeShallow) {
		if(this.displayedFmmHeadlineNodeShallow == null || ! this.displayedFmmHeadlineNodeShallow.getNodeIdString().equals(aHeadlineNodeShallow.getNodeIdString())) {
			setStaleData();
			this.displayedFmmHeadlineNodeShallow = aHeadlineNodeShallow;
		}
	}
	
	public abstract void setStaleData();
	
	public abstract void setStaleData(boolean aBoolean);

	protected FmmHeadlineNode getDisplayedFmmHeadlineNode() {
		return this.displayedFmmHeadlineNodeShallow == null ? null : this.displayedFmmHeadlineNodeShallow.getFmmHeadlineNode();
	}

	protected FmmHeadlineNodeShallow getDisplayedFmmHeadlineNodeShallow() {
		return this.displayedFmmHeadlineNodeShallow;
	}
	
	protected String getDisplayedFmmNodeIdString() {
		return this.displayedFmmHeadlineNodeShallow == null ? null : this.displayedFmmHeadlineNodeShallow.getNodeIdString();
	}

	@Override
	public void saveAllDataModifications() {
		super.saveAllDataModifications();
		this.serializedDisplayedNodeBaseline = getSerializedFmmHeadlineNodeFromView();
	}

	protected abstract String getSerializedFmmHeadlineNodeFromView();
	
	public FmmHeadlineNode getTargetFmmHeadlineNode() {
		return getDisplayedFmmHeadlineNode();
	}

}
