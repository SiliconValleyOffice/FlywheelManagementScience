/* @(#)FseDocumentHistoryBrowserActivity.java
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

package com.flywheelms.library.fse.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.flywheelms.library.R;
import com.flywheelms.library.fdk.widget.FdkKeyboard;
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fms.activity.FmsActivity;
import com.flywheelms.library.fms.helper.FmsActivityHelper;
import com.flywheelms.library.fms.helper.FmsHelpIndex;
import com.flywheelms.library.fms.perspective_flipper.ContextPerspectiveFlipper;
import com.flywheelms.library.fms.perspective_flipper.TribKnPerspectiveFlipper;
import com.flywheelms.library.fse.interfaces.FmsPerspectiveFlipperParent;
import com.flywheelms.library.fse.model.FseDocument;
import com.flywheelms.library.fse.model.FseDocumentTransaction;
import com.flywheelms.library.fse.perspective_flipper.FseHistoryBrowserPerspectiveFlipper;
import com.flywheelms.library.fse.perspective_flipper.FsePerspectiveFlipper;
import com.flywheelms.library.fse.perspective_flipper.perspective.FseDocumentSectionNotesBrowserPerspective;
import com.flywheelms.library.fse.perspective_flipper.perspective.FseDocumentSectionStoryBrowserPerspective;
import com.flywheelms.library.gcg.helper.GcgButtonMenu;
import com.flywheelms.library.gcg.helper.GcgHelper;

import java.util.ArrayList;
import java.util.Collections;

public class FseDocumentHistoryBrowserActivity extends FmsActivity
		implements FmsPerspectiveFlipperParent {
	
	private String fseDocumentNodeIdString;
	private FseDocument fseDocument;
	private int initialTransactionToDisplay = 0;
	private int displayedTransactionIndex = 999;
//	protected ArrayList<FseDocumentTransaction> nodeSpinnerRowList;
//	protected ArrayAdapter<FseDocumentTransaction> nodeSpinnerAdapter;
	private ArrayList<FseDocumentTransaction> transactionList = new ArrayList<FseDocumentTransaction>();
	private ArrayAdapter<FseDocumentTransaction> transactionArrayAdapter;
	private FseHistoryBrowserPerspectiveFlipper documentHistoryBrowserPerspectiveFlipper;
	private FseDocumentSectionStoryBrowserPerspective storyPerspective;
	private FseDocumentSectionNotesBrowserPerspective notesPerspective;
	protected Button navigateFirstButton;
	protected Button navigateLeftButton;
	protected Button navigateRightButton;
	protected Button navigateLastButton;
	protected Spinner headlineNavigationSpinner;
	protected TextView navigationListPosition;
//	private LinearLayout leftKeypadPeerView;
//	private GcgSpinnableMenu leftSpinnableMenu;
	protected GcgButtonMenu fseButtonMenu;
//	protected TextView fseButtonMenuFiller;

	public FseDocumentHistoryBrowserActivity() {
		super(FmsHelpIndex.FSE__HISTORY_BROWSER_ACTIVITY);
	}

	@Override
	protected int getContentViewResourceId() {
		return R.layout.fse__history_browser__activity;
	}

	@Override
	protected String getBreadcrumbHeadline() {
		return "";
	}

	@Override
	protected FmmNodeDefinition getDisplayedFmmNodeDefinition() {
		return FmmNodeDefinition.FSE_DOCUMENT;
	}

	@Override
	protected String getBreadcrumbTargetNodeIdString() {
		return this.fseDocumentNodeIdString;
	}
	
	//////////////////////////////////////////////////

	@Override
	public void onCreate(Bundle aSavedInstanceState) {
	    super.onCreate(aSavedInstanceState);
		this.headlineNavigationSpinner.setSelection(this.initialTransactionToDisplay);
	}

	@Override
	protected void processExtras() {
		super.processExtras();
		this.fseDocumentNodeIdString = getIntent().getExtras().getString(FmsActivityHelper.bundle_key__FSE_DOCUMENT_NODE_ID);
		this.fseDocument = FmmDatabaseMediator.getActiveMediator().getFseDocument(this.fseDocumentNodeIdString);
		ArrayList<FseDocumentTransaction> theReversedList = new ArrayList<FseDocumentTransaction>(this.fseDocument.getTransactionList());
		Collections.reverse(theReversedList);
		this.transactionList = theReversedList;
		this.initialTransactionToDisplay = getIntent().getExtras().getInt(FmsActivityHelper.bundle_key__FSE_DOCUMENT_TRANSACTION_INDEX);
	}
	
	//////////////////////////////////////////////////
	
//	private void initializeNodeViewFlippers() {
//		// initialize in the same order as the spinner headings
//		this.documentHistoryBrowserView = (FseHistoryBrowserView) findViewById(R.id.fse_document__view_flipper);
//		this.documentHistoryBrowserView.initialize(this, this.fseButtonMenu);
//		this.leftSpinnableMenu.getViewBodyArray().add(this.documentHistoryBrowserView);
//		this.storyView = (FseDocumentSectionStoryView) findViewById(R.id.fse_document_section__story_view);
//		this.storyView.initialize(this, this.documentHistoryBrowserView, 0, 0);
//		this.notesView = (FseDocumentSectionNotesView) findViewById(R.id.fse_document_section__notes_view);
//		this.notesView.initialize(this, this.documentHistoryBrowserView, 0, 1);
//	}

	private void initFseMenu() {
		ArrayList<Button> theButtonList = new ArrayList<Button>();
		theButtonList.add((Button) findViewById(R.id.fse__history_browser__story__button));
		theButtonList.add((Button) findViewById(R.id.fse_button__notes));
    	this.fseButtonMenu = new GcgButtonMenu(theButtonList, 0);
    	TextView theFseButtonMenuFiller = (TextView) findViewById(R.id.left_menu__filler);
    	theFseButtonMenuFiller.setBackgroundResource(R.drawable.left_menu__filler_1);
	}
	
//	private void initLeftMenu() {
//		int[] theMenuBodyResourceIdArray = {
//				R.id.fse_menu__body,
//				R.id.tribkn_menu__body,
//				R.id.context_menu__body };
//		this.leftKeypadPeerView = (LinearLayout) findViewById(R.id.fdk__left_keypad__peer_view);
//		this.leftSpinnableMenu = new GcgSpinnableMenu(
//				this,
//				this.leftKeypadPeerView,
//				GcgSpinnableMenu.DECORATORS_LEFT,
//				R.id.left_menu__heading_spinner,
//				R.array.fms_node_activity__left_menu__heading_array,
//				theMenuBodyResourceIdArray );
//		initFseMenu();
//	}

	private void initializeNavigationPanel() {
		this.navigateFirstButton = (Button) findViewById(R.id.navigate_beginning_button);
		this.navigateFirstButton.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				navigateToBeginning();
			}
		});
		this.navigateLeftButton = (Button) findViewById(R.id.navigate_left_button);
		this.navigateLeftButton.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				navigateLeft();
			}
		});
		this.navigateRightButton = (Button) findViewById(R.id.navigate_right_button);
		this.navigateRightButton.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				navigateRight();
			}
		});
		this.navigateLastButton = (Button) findViewById(R.id.navigate_end_button);
		this.navigateLastButton.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				navigateToEnd();
			}
		});
		this.headlineNavigationSpinner = (Spinner) findViewById(R.id.navigation_headline__spinner);
		this.transactionArrayAdapter = new ArrayAdapter<FseDocumentTransaction>(this, android.R.layout.simple_spinner_item, this.transactionList);
		this.headlineNavigationSpinner.setAdapter(this.transactionArrayAdapter);
		this.headlineNavigationSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View aListViewItemView, int aListViewItemPosition, long anItemId) {
				navigateToSpinnerRow(aListViewItemPosition);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		this.navigationListPosition = (TextView) findViewById(R.id.navigation_position);
	}

	@Override
	protected void initializeGui() {
		super.initializeGui();
		initializeGcgApplicationContextHeader();
		initializeNavigationPanel();
		this.documentHistoryBrowserPerspectiveFlipper = (FseHistoryBrowserPerspectiveFlipper) findViewById(R.id.fse_frame__perspective_flipper);
		this.storyPerspective = (FseDocumentSectionStoryBrowserPerspective) findViewById(R.id.fse_browser__story);
		this.storyPerspective.initialize(this, this.documentHistoryBrowserPerspectiveFlipper, 0, "");
		this.notesPerspective = (FseDocumentSectionNotesBrowserPerspective) findViewById(R.id.fse_browser__notes);
		this.notesPerspective.initialize(this, this.documentHistoryBrowserPerspectiveFlipper, 1, "");
		initFseMenu();
		this.documentHistoryBrowserPerspectiveFlipper.initialize(this, this.fseButtonMenu);
//		initializeNodeViewFlippers();
	}
	
	//////////////////////////////////////////////////

	public FseDocumentSectionStoryBrowserPerspective getStoryPerspective() {
		return this.storyPerspective;
	}

	public FseDocumentSectionNotesBrowserPerspective getNotesPerspective() {
		return this.notesPerspective;
	}

	protected void navigateToSpinnerRow(int aListViewItemPosition) {
		if(aListViewItemPosition == this.displayedTransactionIndex) {
			return;
		}
		startOrangeActivityStatusAnimation();
		this.displayedTransactionIndex = aListViewItemPosition;
		this.documentHistoryBrowserPerspectiveFlipper.viewDocument(this.transactionList.get(this.displayedTransactionIndex).getFseDocument());
		updateNavigationControls();
		stopActivityStatusAnimation();
	}

	protected void navigateToBeginning() {
//		if(protectDataChanges(FmsSaveChangesDialog.next_action__NAVIGATE_TO_BEGINNING)) {
//			return;
//		}
//		this.discardDataChanges = false;
//		int theCurrentIndex = this.navigationNodeIdStringList.indexOf(getDisplayedFmmNodeIdString());
		if(this.displayedTransactionIndex == 0) {
			return;
		}
		this.headlineNavigationSpinner.setSelection(0);
	}

	protected void navigateLeft() {
//		if(protectDataChanges(FmsSaveChangesDialog.next_action__NAVIGATE_LEFT)) {
//			return;
//		}
//		this.discardDataChanges = false;
//		int theCurrentIndex = this.navigationNodeIdStringList.indexOf(getDisplayedFmmNodeIdString());
		if(this.displayedTransactionIndex <= 0) {
			return;
		}
		this.headlineNavigationSpinner.setSelection(this.displayedTransactionIndex - 1);
	}

	protected void navigateRight() {
//		if(protectDataChanges(FmsSaveChangesDialog.next_action__NAVIGATE_RIGHT)) {
//			return;
//		}
//		this.discardDataChanges = false;
//		int theCurrentIndex = this.navigationNodeIdStringList.indexOf(getDisplayedFmmNodeIdString());
		if(this.displayedTransactionIndex >= this.transactionList.size() - 1) {
			return;
		}
		this.headlineNavigationSpinner.setSelection(this.displayedTransactionIndex + 1);
	}

	protected void navigateToEnd() {
//		if(protectDataChanges(FmsSaveChangesDialog.next_action__NAVIGATE_TO_END)) {
//			return;
//		}
//		this.discardDataChanges = false;
		if(this.displayedTransactionIndex >= this.transactionList.size() - 1) {
			return;
		}
		this.headlineNavigationSpinner.setSelection(this.transactionList.size() - 1);
	}

	protected void updateNavigationControls() {
		this.navigationListPosition.setText((this.displayedTransactionIndex + 1) +
					"  of " + this.transactionList.size() );
		if(this.transactionList.size() == 1) {
			GcgHelper.enableNavigationButton(this.navigateFirstButton, false);
			GcgHelper.enableNavigationButton(this.navigateLeftButton, false);
			GcgHelper.enableNavigationButton(this.navigateRightButton, false);
			GcgHelper.enableNavigationButton(this.navigateLastButton, false);
			return;
		}
		if(this.displayedTransactionIndex == 0) {
			GcgHelper.enableNavigationButton(this.navigateFirstButton, false);
			GcgHelper.enableNavigationButton(this.navigateLeftButton, false);
			GcgHelper.enableNavigationButton(this.navigateRightButton, true);
			GcgHelper.enableNavigationButton(this.navigateLastButton, true);
		} else if(this.displayedTransactionIndex == this.transactionList.size() - 1) {
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

	public FseDocumentTransaction getDisplayedDocumentTransaction() {
		return this.transactionList.get(this.displayedTransactionIndex);
	}

	@Override
	public FsePerspectiveFlipper getFsePerspectiveFlipper() {
		// TODO Auto-generated method stub
		return this.documentHistoryBrowserPerspectiveFlipper;
	}

	@Override
	public void setFdkKeyboard(FdkKeyboard aKeyboard) {
		return;
	}

	public void leftMenuClickItem(Integer aMenuItem) {
		int theMenuItemCount = 2;
		if(aMenuItem <= theMenuItemCount) {
			if(aMenuItem == 1) {
				this.documentHistoryBrowserPerspectiveFlipper.flipToIndex(0);
			} else {
				int theChildIndex = theMenuItemCount - aMenuItem + 1;
				this.documentHistoryBrowserPerspectiveFlipper.flipToIndex(theChildIndex);
			}
		}
	}

	public void navigationSpinnerSelect() {
		this.headlineNavigationSpinner.performClick();
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

	@Override
	public TribKnPerspectiveFlipper getTribKnPerspectiveFlipper() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContextPerspectiveFlipper getContextPerspectiveFlipper() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void onClickDocumentStory(@SuppressWarnings("unused") View v) {
		this.fseButtonMenu.onClickMenuButton(this.documentHistoryBrowserPerspectiveFlipper, 0);
	}
	
	@SuppressWarnings("unused")
	public void onClickDocumentNotes(View v) {
		this.fseButtonMenu.onClickMenuButton(this.documentHistoryBrowserPerspectiveFlipper, 1);
	}

}
