/* @(#)HeadlineNodeListZoomDialog.java
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

package com.flywheelms.library.fms.dialog;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.flywheelms.library.R;
import com.flywheelms.library.deckangl.enumerator.DecKanGlAnnotatedGlyphSize;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;
import com.flywheelms.library.fms.miscellaneous.FmsHeadlineNodeArrayAdapter;
import com.flywheelms.library.fms.widget.FmmHeadlineNodeWidgetSpinner;
import com.flywheelms.library.fms.widget.text_view.CustomerWidgetTextView;
import com.flywheelms.library.fms.widget.text_view.FacilitatorWidgetTextView;
import com.flywheelms.library.fms.widget.text_view.FseStoryWidget;
import com.flywheelms.library.fms.widget.text_view.SponsorWidgetTextView;
import com.flywheelms.library.gcg.activity.GcgActivity;
import com.flywheelms.library.gcg.container.GcgContainerTabbedLayout;
import com.flywheelms.library.gcg.container.tabbed.GcgTabSpec;
import com.flywheelms.library.gcg.helper.GcgHelper;

import java.util.ArrayList;

public class HeadlineNodeListZoomDialog extends FmsCancelOkDialog {
	
	private FmmHeadlineNodeWidgetSpinner fmmHeadlineNodeWidgetSpinner;
	private ArrayList<? extends FmmHeadlineNode> headlineNodeList;
	private int originalSelection;
	private GcgContainerTabbedLayout tabbedLayout;
	private ImageView decKanGlImageView;
	private SponsorWidgetTextView sponsorWidgetTextView;
	private CustomerWidgetTextView customerWidgetTextView;
	private FacilitatorWidgetTextView facilitatorWidgetTextView;
	private FseStoryWidget storyWidgetTextView;
	protected FmmHeadlineNode displayedFmmHeadlineNode;
	protected ArrayList<FmmHeadlineNode> fmmHeadlineNodeList;
	protected FmsHeadlineNodeArrayAdapter nodeSpinnerAdapter;
	protected String initialNodeIdStringToDisplay;
	protected String serializedDisplayedNodeBaseline = "";
	protected Button navigateFirstButton;
	protected Button navigateLeftButton;
	protected Button navigateRightButton;
	protected Button navigateLastButton;
	protected Spinner nodeNavigationSpinner;
	protected TextView navigationListPosition;
	protected boolean okButtonAlwaysOff = false;

	public HeadlineNodeListZoomDialog(GcgActivity aGcgActivity, FmmHeadlineNodeWidgetSpinner anFmmHeadlineNodeWidgetSpinner, ArrayList<? extends FmmHeadlineNode> aHeadlineNodeList, int anOriginalSelection) {
		super(aGcgActivity, aHeadlineNodeList.get(anOriginalSelection));
		this.fmmHeadlineNodeWidgetSpinner = anFmmHeadlineNodeWidgetSpinner;
		this.headlineNodeList = aHeadlineNodeList;
		this.originalSelection = anOriginalSelection;
		initializeNodeNavigationPanel();
		initializeDecKanGlTab();
		initializeGovernanceTab();
		initializeStoryTab();
		navigateToSpinnerRow(anOriginalSelection);
		this.tabbedLayout.setCurrentTab(0);
	}

	public HeadlineNodeListZoomDialog(GcgActivity aGcgActivity, ArrayList<? extends FmmHeadlineNode> aHeadlineNodeList) {
		super(aGcgActivity, aHeadlineNodeList.get(0));
		this.headlineNodeList = aHeadlineNodeList;
		this.okButtonAlwaysOff = true;
        this.buttonOk.setVisibility(View.GONE);
		initializeNodeNavigationPanel();
		initializeDecKanGlTab();
		initializeGovernanceTab();
		initializeStoryTab();
		this.tabbedLayout.setCurrentTab(0);
	}

	@Override
	protected int getDialogTitleStringResourceId() {
		return R.string.gcg__action__zoom_browser;
	}

	@Override
	protected int getDialogTitleIconResourceId() {
		return getFmmHeadlineNode().getLabelDrawableResourceId();
	}

	@Override
	protected void initializeDialogBody() {
		super.initializeDialogBody();
		this.tabbedLayout = (GcgContainerTabbedLayout) this.dialogBodyView.findViewById(R.id.tabbed_layout);
		this.tabbedLayout.setup();
	}

	@Override
	protected void manageButtonState() {
		if(this.okButtonAlwaysOff) {
			this.buttonOk.setVisibility(View.GONE);
		} else {
			this.buttonOk.setVisibility(View.VISIBLE);
		}
	}

	private void initializeDecKanGlTab() {
		LinearLayout theLinearLayout = (LinearLayout) this.gcgActivity.getLayoutInflater().inflate(R.layout.deckangl__zoom_panel, this.tabbedLayout, false);
		this.decKanGlImageView = (ImageView) theLinearLayout.findViewById(R.id.annotated_deckangl);
		this.decKanGlImageView.setImageBitmap(getFmmHeadlineNode().getDecKanGlGlyph().getAnnotatedGlyphBitmap(DecKanGlAnnotatedGlyphSize.MEDIUM));
		GcgTabSpec theGcgTabSpec = new GcgTabSpec(theLinearLayout, R.drawable.deckangl__32, R.string.deckangl_tm__noun, false);
		this.tabbedLayout.addTab(theGcgTabSpec);
	}

	private void initializeGovernanceTab() {
		LinearLayout theLinearLayout = (LinearLayout) this.gcgActivity.getLayoutInflater().inflate(R.layout.governance__zoom_panel, this.tabbedLayout, false);
		this.sponsorWidgetTextView = (SponsorWidgetTextView) theLinearLayout.findViewById(R.id.sponsor);
		this.sponsorWidgetTextView.setText(getFmmHeadlineNode().getSponsorName());
		this.customerWidgetTextView = (CustomerWidgetTextView) theLinearLayout.findViewById(R.id.customer);
		this.customerWidgetTextView.setText(getFmmHeadlineNode().getCustomerName());
		this.facilitatorWidgetTextView = (FacilitatorWidgetTextView) theLinearLayout.findViewById(R.id.facilitator);
		this.facilitatorWidgetTextView.setText(getFmmHeadlineNode().getFacilitatorName());
		GcgTabSpec theGcgTabSpec = new GcgTabSpec(theLinearLayout, R.drawable.governance__32, R.string.fmm_perspective__governance, false);
		this.tabbedLayout.addTab(theGcgTabSpec);
	}

	private void initializeStoryTab() {
		LinearLayout theLinearLayout = (LinearLayout) this.gcgActivity.getLayoutInflater().inflate(R.layout.story__zoom_panel, this.tabbedLayout, false);
		this.storyWidgetTextView = (FseStoryWidget) theLinearLayout.findViewById(R.id.story);
		this.storyWidgetTextView.setText("Not implemented.");
		GcgTabSpec theGcgTabSpec = new GcgTabSpec(theLinearLayout, R.drawable.fse__story__32, R.string.fse__document_section__story, false);
		this.tabbedLayout.addTab(theGcgTabSpec);
	}

	@Override
	protected int getCustomDialogContentsResourceId() {
		return R.layout.fms__zoom__dialog;
	}

	public ArrayList<? extends FmmHeadlineNode> getHeadlineNodeList() {
		return this.headlineNodeList;
	}

	public int getOriginalSelection() {
		return this.originalSelection;
	}

	protected String getSerializedDisplayedNodeBaseline() {
		return this.serializedDisplayedNodeBaseline;
	}

	private void initializeNodeNavigationPanel() {
		this.navigateFirstButton = (Button) this.dialogBodyView.findViewById(R.id.navigate_beginning_button);
		this.navigateFirstButton.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				HeadlineNodeListZoomDialog.this.navigateToBeginning();
			}
		});
		this.navigateLeftButton = (Button) this.dialogBodyView.findViewById(R.id.navigate_left_button);
		this.navigateLeftButton.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				HeadlineNodeListZoomDialog.this.navigateLeft();
			}
		});
		this.navigateRightButton = (Button) this.dialogBodyView.findViewById(R.id.navigate_right_button);
		this.navigateRightButton.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				HeadlineNodeListZoomDialog.this.navigateRight();
			}
		});
		this.navigateLastButton = (Button) this.dialogBodyView.findViewById(R.id.navigate_end_button);
		this.navigateLastButton.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				HeadlineNodeListZoomDialog.this.navigateToEnd();
			}
		});
		this.nodeNavigationSpinner = (Spinner) this.dialogBodyView.findViewById(R.id.navigation_headline__spinner);
		this.nodeSpinnerAdapter = new FmsHeadlineNodeArrayAdapter(this.gcgActivity, new ArrayList<FmmHeadlineNode>(this.headlineNodeList));
		this.nodeNavigationSpinner.setAdapter(this.nodeSpinnerAdapter);
		this.nodeNavigationSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View aListViewItemView, int aListViewItemPosition, long anItemId) {
				HeadlineNodeListZoomDialog.this.navigateToSpinnerRow(aListViewItemPosition);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		this.navigationListPosition = (TextView) this.dialogBodyView.findViewById(R.id.navigation_position);
	}
	
	protected boolean headlineEditingEnabled() {
		return false;
	}
	
	protected void editHeadline() {  return;  }

	protected void updateNavigationControls() {
		updateNavigationListPosition();
		if(this.headlineNodeList.size() == 1) {
			GcgHelper.enableNavigationButton(this.navigateFirstButton, false);
			GcgHelper.enableNavigationButton(this.navigateLeftButton, false);
			GcgHelper.enableNavigationButton(this.navigateRightButton, false);
			GcgHelper.enableNavigationButton(this.navigateLastButton, false);
		} else {
			int theCurrentIndex = getCurrentSelectionIndex();
			if(theCurrentIndex == 0) {
				GcgHelper.enableNavigationButton(this.navigateFirstButton, false);
				GcgHelper.enableNavigationButton(this.navigateLeftButton, false);
				GcgHelper.enableNavigationButton(this.navigateRightButton, true);
				GcgHelper.enableNavigationButton(this.navigateLastButton, true);
			} else if(theCurrentIndex == this.headlineNodeList.size() - 1) {
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

	private int getCurrentSelectionIndex() {
		return this.nodeNavigationSpinner.getSelectedItemPosition();
	}

	private void updateNavigationListPosition() {
		this.navigationListPosition.setText(
				getCurrentSelectionIndex() + 1 +
					"  of " + this.headlineNodeList.size() );
	}

	public void navigationSpinnerSelect() {
		this.nodeNavigationSpinner.performClick();
	}

	protected void navigateToBeginning() {
		int theCurrentIndex = getCurrentSelectionIndex();
		if(theCurrentIndex == 0) {
			return;
		}
		this.nodeNavigationSpinner.setSelection(0);
	}

	protected void navigateLeft() {
		int theCurrentIndex = getCurrentSelectionIndex();
		if(theCurrentIndex == 0) {
			return;
		}
		this.nodeNavigationSpinner.setSelection(theCurrentIndex - 1);
	}

	protected void navigateRight() {
		int theCurrentIndex = getCurrentSelectionIndex();
		if(theCurrentIndex == this.headlineNodeList.size() - 1) {
			return;
		}
		this.nodeNavigationSpinner.setSelection(theCurrentIndex + 1);
	}

	protected void navigateToEnd() {
		int theCurrentIndex = getCurrentSelectionIndex();
		if(theCurrentIndex == this.headlineNodeList.size() - 1) {
			return;
		}
		this.nodeNavigationSpinner.setSelection(this.headlineNodeList.size() - 1);
	}

	protected void navigateToListItem(FmmHeadlineNode aHeadlineNode) {
		if(getDisplayedFmmHeadlineNode() != null && aHeadlineNode.getNodeIdString().equals(getDisplayedFmmNodeIdString())) {
			return;
		}
		setDisplayedFmmHeadlineNode(aHeadlineNode);
		updateNavigationControls();
	}
    
	protected void navigateToSpinnerRow(int aListViewItemPosition) {
		HeadlineNodeListZoomDialog.this.gcgActivity.startOrangeActivityStatusAnimation();
		this.nodeNavigationSpinner.setSelection(aListViewItemPosition);
		updateNavigationControls();
//		navigateToListItem(this.headlineNodeList.get(aListViewItemPosition));
		HeadlineNodeListZoomDialog.this.gcgActivity.stopActivityStatusAnimation();
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
	
	protected void setDisplayedFmmHeadlineNode(FmmHeadlineNode aHeadlineNode) {
		if(this.displayedFmmHeadlineNode == null || ! this.displayedFmmHeadlineNode.getNodeIdString().equals(aHeadlineNode.getNodeIdString())) {
			this.displayedFmmHeadlineNode = aHeadlineNode;
		}
	}

	protected FmmHeadlineNode getDisplayedFmmHeadlineNode() {
		return this.displayedFmmHeadlineNode;
	}
	
	protected String getDisplayedFmmNodeIdString() {
		return this.displayedFmmHeadlineNode == null ? null : this.displayedFmmHeadlineNode.getNodeIdString();
	}

	@Override
	protected void onClickButtonOk() {
		if(this.fmmHeadlineNodeWidgetSpinner != null) {
			this.fmmHeadlineNodeWidgetSpinner.setSelection(getCurrentSelectionIndex());
		}
		this.gcgActivity.stopDialog();
	}

}
