/* @(#)FmsNodePublishingContentSelectionWizardStepView.java
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

package com.flywheelms.library.fms.wizard.step;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.flywheelms.library.R;
import com.flywheelms.library.fms.activity.FmsNodeWizardActivity;
import com.flywheelms.library.fms.helper.FmsHelpIndex;
import com.flywheelms.library.fms.pdf.FmsPdfHelper;
import com.flywheelms.library.fms.preferences.GuiPreferenceAttribute;
import com.flywheelms.library.fms.preferences.GuiPreferencesBundle;
import com.flywheelms.library.fse.model.FseDocumentTransaction;
import com.flywheelms.library.gcg.activity.GcgActivity;
import com.flywheelms.library.gcg.helper.GcgHelper;
import com.flywheelms.library.gcg.preferences.GcgPreferencesHelper;
import com.flywheelms.library.gcg.viewflipper.GcgViewFlipper;
import com.flywheelms.library.gcg.widget.date.GcgDateHelper;
import com.flywheelms.library.gcg.wizard.step.GcgWizardStepView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FmsNodePublishingContentSelectionWizardStepView extends GcgWizardStepView {
	
	private CheckBox titlePage;
	private CheckBox tableOfContents;
	private CheckBox locks;
	private CheckBox paragraphNumbering;
	private CheckBox contentModification;
	private Spinner changesSinceDate;
	private ArrayAdapter<String> transactionDateArrayAdapter;
	private CheckBox story;
	private CheckBox notes;
	private CheckBox history;
	private CheckBox contributors;
	private CheckBox decKanGl;
	private CheckBox governance;
	private CheckBox commitments;
	private CheckBox community;
	private CheckBox strategicPlanning;
	private CheckBox workBreakdown;
	private CheckBox workPlanning;
	private CheckBox analysis;
	private Button previewPdf;
	private Button guiPreferences;

	public FmsNodePublishingContentSelectionWizardStepView(Context context,
			AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(GcgActivity anGcgActivity, GcgViewFlipper aViewFlipper, int aPageNumber) {
		super.initialize(anGcgActivity, aViewFlipper, aPageNumber);
		this.titlePage = (CheckBox) findViewById(R.id.pdf__title_page__check_box);
		initializeTableRowClickListener(this.titlePage, R.id.row__a1);
		this.tableOfContents = (CheckBox) findViewById(R.id.pdf__table_of_contents__check_box);
		initializeTableRowClickListener(this.tableOfContents, R.id.row__a2);
		this.locks = (CheckBox) findViewById(R.id.pdf__locks__check_box);
		initializeTableRowClickListener(this.locks, R.id.row__a3);
		this.paragraphNumbering = (CheckBox) findViewById(R.id.pdf__paragraph_numbering__check_box);
		initializeTableRowClickListener(this.paragraphNumbering, R.id.row__a4);
		this.contentModification = (CheckBox) findViewById(R.id.pdf__content_modification__check_box);
		initializeTableRowClickListener(this.contentModification, R.id.row__a5);
		this.changesSinceDate = (Spinner) findViewById(R.id.pdf__changes_since_date);
		this.transactionDateArrayAdapter = new ArrayAdapter<String>(this.context, android.R.layout.simple_spinner_item, getTransactionDateListForSpinner());
		this.changesSinceDate.setAdapter(this.transactionDateArrayAdapter);
		this.story = (CheckBox) findViewById(R.id.fse__document_section__story__check_box);
		// cannot uncheck story
		this.notes = (CheckBox) findViewById(R.id.fse__document_section__notes__check_box);
		initializeTableRowClickListener(this.notes, R.id.row__b2);
		this.history = (CheckBox) findViewById(R.id.fse__document_section__history__check_box);
		initializeTableRowClickListener(this.history, R.id.row__b3);
		this.contributors = (CheckBox) findViewById(R.id.fse__document_section__collaborators__check_box);
		initializeTableRowClickListener(this.contributors, R.id.row__b4);
		this.decKanGl = (CheckBox) findViewById(R.id.tribkn__deckangl__check_box);
		initializeTableRowClickListener(this.decKanGl, R.id.row__c1);
		this.governance = (CheckBox) findViewById(R.id.tribkn__governance__check_box);
		initializeTableRowClickListener(this.governance, R.id.row__c2);
		this.commitments = (CheckBox) findViewById(R.id.tribkn__commitments__check_box);
		initializeTableRowClickListener(this.commitments, R.id.row__c3);
		this.community = (CheckBox) findViewById(R.id.tribkn__community__check_box);
		initializeTableRowClickListener(this.community, R.id.row__c4);
		this.strategicPlanning = (CheckBox) findViewById(R.id.context__strategic_planning__check_box);
		initializeTableRowClickListener(this.strategicPlanning, R.id.row__d1);
		this.workBreakdown = (CheckBox) findViewById(R.id.context__work_breakdown__check_box);
		initializeTableRowClickListener(this.workBreakdown, R.id.row__d2);
		this.workPlanning = (CheckBox) findViewById(R.id.context__work_planning__check_box);
		initializeTableRowClickListener(this.workPlanning, R.id.row__d3);
		this.analysis = (CheckBox) findViewById(R.id.context__analysis__check_box);
		initializeTableRowClickListener(this.analysis, R.id.row__d4);
		this.previewPdf = (Button) findViewById(R.id.preview_pdf);
		this.previewPdf.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FmsNodePublishingContentSelectionWizardStepView.this.previewPdf();
			}
		});
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
		guiPreferencesRestore();
	}

	@Override
	public void guiPreferencesRestore() {
		SharedPreferences theGuiPreferences = GcgPreferencesHelper.getGuiPreferences(
				getViewFlipper().getGcgActivity(), GuiPreferencesBundle.PUBLISH_PDF__CONTENT.getKey());
		this.titlePage.setChecked(theGuiPreferences.getBoolean(GuiPreferenceAttribute.SHOW__TITLE_PAGE.getKey(), false));
		this.tableOfContents.setChecked(theGuiPreferences.getBoolean(GuiPreferenceAttribute.SHOW__TABLE_OF_CONTENTS.getKey(), false));
		this.locks.setChecked(theGuiPreferences.getBoolean(GuiPreferenceAttribute.SHOW__LOCKS.getKey(), false));
		this.paragraphNumbering.setChecked(theGuiPreferences.getBoolean(GuiPreferenceAttribute.SHOW__PARAGRAPH_NUMBERING.getKey(), false));
		if(! this.transactionDateArrayAdapter.isEmpty()) {
			this.contentModification.setChecked(theGuiPreferences.getBoolean(GuiPreferenceAttribute.SHOW__CONTENT_MODIFICATION.getKey(), false));
		}
		this.story.setChecked(true); // always selected
		this.notes.setChecked(theGuiPreferences.getBoolean(GuiPreferenceAttribute.SHOW__NOTES.getKey(), true));
		this.history.setChecked(theGuiPreferences.getBoolean(GuiPreferenceAttribute.SHOW__HISTORY.getKey(), false));
		this.contributors.setChecked(theGuiPreferences.getBoolean(GuiPreferenceAttribute.SHOW__CONTRIBUTORS.getKey(), false));
		this.decKanGl.setChecked(theGuiPreferences.getBoolean(GuiPreferenceAttribute.SHOW__DECKANGL.getKey(), false));
		this.governance.setChecked(theGuiPreferences.getBoolean(GuiPreferenceAttribute.SHOW__GOVERNANCE.getKey(), false));
		this.commitments.setChecked(theGuiPreferences.getBoolean(GuiPreferenceAttribute.SHOW__COMMITMENTS.getKey(), false));
		this.community.setChecked(theGuiPreferences.getBoolean(GuiPreferenceAttribute.SHOW__COMMUNITY.getKey(), false));
		this.strategicPlanning.setChecked(theGuiPreferences.getBoolean(GuiPreferenceAttribute.SHOW__STRATEGIC_PLANNING.getKey(), false));
		this.workBreakdown.setChecked(theGuiPreferences.getBoolean(GuiPreferenceAttribute.SHOW__WORK_BREAKDOWN.getKey(), false));
		this.workPlanning.setChecked(theGuiPreferences.getBoolean(GuiPreferenceAttribute.SHOW__WORK_PLANNING.getKey(), false));
		this.analysis.setChecked(theGuiPreferences.getBoolean(GuiPreferenceAttribute.SHOW__ANALYSIS.getKey(), false));
	}

	@Override
	public void guiPreferencesSave() {
		SharedPreferences theGuiPreferences = GcgPreferencesHelper.getGuiPreferences(
				getViewFlipper().getGcgActivity(), GuiPreferencesBundle.PUBLISH_PDF__CONTENT.getKey());
		theGuiPreferences.edit().putBoolean(GuiPreferenceAttribute.SHOW__TITLE_PAGE.getKey(), this.titlePage.isChecked()).commit();
		theGuiPreferences.edit().putBoolean(GuiPreferenceAttribute.SHOW__TABLE_OF_CONTENTS.getKey(), this.tableOfContents.isChecked()).commit();
		theGuiPreferences.edit().putBoolean(GuiPreferenceAttribute.SHOW__LOCKS.getKey(), this.locks.isChecked()).commit();
		theGuiPreferences.edit().putBoolean(GuiPreferenceAttribute.SHOW__PARAGRAPH_NUMBERING.getKey(), this.paragraphNumbering.isChecked()).commit();
		theGuiPreferences.edit().putBoolean(GuiPreferenceAttribute.SHOW__CONTENT_MODIFICATION.getKey(), this.contentModification.isChecked()).commit();
		// Story is always selected
		theGuiPreferences.edit().putBoolean(GuiPreferenceAttribute.SHOW__NOTES.getKey(), this.notes.isChecked()).commit();
		theGuiPreferences.edit().putBoolean(GuiPreferenceAttribute.SHOW__HISTORY.getKey(), this.history.isChecked()).commit();
		theGuiPreferences.edit().putBoolean(GuiPreferenceAttribute.SHOW__CONTRIBUTORS.getKey(), this.contributors.isChecked()).commit();
		theGuiPreferences.edit().putBoolean(GuiPreferenceAttribute.SHOW__DECKANGL.getKey(), this.decKanGl.isChecked()).commit();
		theGuiPreferences.edit().putBoolean(GuiPreferenceAttribute.SHOW__GOVERNANCE.getKey(), this.governance.isChecked()).commit();
		theGuiPreferences.edit().putBoolean(GuiPreferenceAttribute.SHOW__COMMITMENTS.getKey(), this.commitments.isChecked()).commit();
		theGuiPreferences.edit().putBoolean(GuiPreferenceAttribute.SHOW__COMMUNITY.getKey(), this.community.isChecked()).commit();
		theGuiPreferences.edit().putBoolean(GuiPreferenceAttribute.SHOW__STRATEGIC_PLANNING.getKey(), this.strategicPlanning.isChecked()).commit();
		theGuiPreferences.edit().putBoolean(GuiPreferenceAttribute.SHOW__WORK_BREAKDOWN.getKey(), this.workBreakdown.isChecked()).commit();
		theGuiPreferences.edit().putBoolean(GuiPreferenceAttribute.SHOW__WORK_PLANNING.getKey(), this.workPlanning.isChecked()).commit();
		theGuiPreferences.edit().putBoolean(GuiPreferenceAttribute.SHOW__ANALYSIS.getKey(), this.analysis.isChecked()).commit();
	}
	
	@Override
	public void guiPreferencesClear() {
		SharedPreferences theGuiPreferences = GcgPreferencesHelper.getGuiPreferences(
				getViewFlipper().getGcgActivity(), GuiPreferencesBundle.PUBLISH_PDF__CONTENT.getKey());
		theGuiPreferences.edit().clear().commit();
		guiPreferencesRestore();
	}

	protected void previewPdf() {
		getGcgActivity().startBlueActivityStatusAnimation(true);
		File theReportFile = FmsPdfHelper.generatePdfFile(this, ((FmsNodeWizardActivity) getWizardActivity()).getFmmNodeIdString(), ((FmsNodeWizardActivity) getWizardActivity()).getAbbreviatedFmmNodeIdString());
		FmsPdfHelper.viewPdfFile(this.context, theReportFile);
	}

	@Override
	protected int getViewLayoutResourceId() {
		return R.layout.fmm_node__publication__wizard_step__content_selection;
	}

	@Override
	protected int getPageTitleResourceId() {
		return R.string.publish_pdf__content_selection__wizard_step;
	}

	public CheckBox getTitlePage() {
		return this.titlePage;
	}

	public CheckBox getTableOfContents() {
		return this.tableOfContents;
	}

	public CheckBox getLocks() {
		return this.locks;
	}

	public CheckBox getParagraphNumbering() {
		return this.paragraphNumbering;
	}

	public CheckBox getContentModification() {
		return this.contentModification;
	}

	public Spinner getChangesSinceDate() {
		return this.changesSinceDate;
	}

	public CheckBox getStory() {
		return this.story;
	}

	public CheckBox getNotes() {
		return this.notes;
	}

	public CheckBox getHistory() {
		return this.history;
	}

	public CheckBox getContributors() {
		return this.contributors;
	}

	public CheckBox getDecKanGl() {
		return this.decKanGl;
	}

	public CheckBox getGovernance() {
		return this.governance;
	}

	public CheckBox getCommitments() {
		return this.commitments;
	}

	public CheckBox getCommunity() {
		return this.community;
	}

	public CheckBox getStrategicPlanning() {
		return this.strategicPlanning;
	}

	public CheckBox getWorkBreakdown() {
		return this.workBreakdown;
	}

	public CheckBox getWorkPlanning() {
		return this.workPlanning;
	}

	public CheckBox getAnalysis() {
		return this.analysis;
	}

	@Override
	public boolean validWizardStepData() {
		return true;  // can't screw this step up  :-)
	}

	@Override
	public String getSummaryText() {
		StringBuffer theStringBuffer = new StringBuffer();
		if(this.titlePage.isChecked() || this.tableOfContents.isChecked() || this.locks.isChecked() || this.paragraphNumbering.isChecked() || this.contentModification.isChecked() ){
			theStringBuffer.append("PDF Enhancements" + GcgHelper.text__NEW_LINE);
		}
		if(this.titlePage.isChecked()) {
			theStringBuffer.append(GcgHelper.text__INDENT + "Title Page" + GcgHelper.text__NEW_LINE);
		}
		if(this.tableOfContents.isChecked()) {
			theStringBuffer.append(GcgHelper.text__INDENT + "Table of Contents" + GcgHelper.text__NEW_LINE);
		}
		if(this.locks.isChecked()) {
			theStringBuffer.append(GcgHelper.text__INDENT + "Paragraph Markup - Locks" + GcgHelper.text__NEW_LINE);
		}
		if(this.paragraphNumbering.isChecked()) {
			theStringBuffer.append(GcgHelper.text__INDENT + "Paragraph Markup - Paragraph IDs" + GcgHelper.text__NEW_LINE);
		}
		if(this.contentModification.isChecked()) {
			theStringBuffer.append(GcgHelper.text__INDENT + "Paragraph Markup - Content Modification" + GcgHelper.text__NEW_LINE);
			theStringBuffer.append(GcgHelper.text__INDENT + GcgHelper.text__INDENT + "changes since " + this.changesSinceDate.getSelectedItem() + GcgHelper.text__NEW_LINE);
		}
		theStringBuffer.append("FSE Document Sections" + GcgHelper.text__NEW_LINE);
		theStringBuffer.append(GcgHelper.text__INDENT + "Story" + GcgHelper.text__NEW_LINE);
		if(this.notes.isChecked()) {
			theStringBuffer.append(GcgHelper.text__INDENT + "Notes" + GcgHelper.text__NEW_LINE);
		}
		if(this.history.isChecked()) {
			theStringBuffer.append(GcgHelper.text__INDENT + "History" + GcgHelper.text__NEW_LINE);
		}
		if(this.contributors.isChecked()) {
			theStringBuffer.append(GcgHelper.text__INDENT + "Contributors" + GcgHelper.text__NEW_LINE);
		}
		if(this.decKanGl.isChecked() || this.governance.isChecked() || this.commitments.isChecked() || this.community.isChecked()) {
			theStringBuffer.append(getResources().getString(R.string.tribkn) + GcgHelper.text__NEW_LINE);
		}
		if(this.decKanGl.isChecked()) {
			theStringBuffer.append(GcgHelper.text__INDENT + getResources().getString(R.string.deckangl_tm) + GcgHelper.text__NEW_LINE);
		}
		if(this.governance.isChecked()) {
			theStringBuffer.append(GcgHelper.text__INDENT + "Governance" + GcgHelper.text__NEW_LINE);
		}
		if(this.commitments.isChecked()) {
			theStringBuffer.append(GcgHelper.text__INDENT + "Commitments" + GcgHelper.text__NEW_LINE);
		}
		if(this.community.isChecked()) {
			theStringBuffer.append(GcgHelper.text__INDENT + "Community" + GcgHelper.text__NEW_LINE);
		}
		if(this.strategicPlanning.isChecked() || this.workBreakdown.isChecked() || this.workPlanning.isChecked() || this.analysis.isChecked()) {
			theStringBuffer.append("Context" + GcgHelper.text__NEW_LINE);
		}
		if(this.strategicPlanning.isChecked()) {
			theStringBuffer.append(GcgHelper.text__INDENT + "Strategic Planning" + GcgHelper.text__NEW_LINE);
		}
		if(this.workBreakdown.isChecked()) {
			theStringBuffer.append(GcgHelper.text__INDENT + "Work Breakdown" + GcgHelper.text__NEW_LINE);
		}
		if(this.workPlanning.isChecked()) {
			theStringBuffer.append(GcgHelper.text__INDENT + "Work Planning" + GcgHelper.text__NEW_LINE);
		}
		if(this.analysis.isChecked()) {
			theStringBuffer.append(GcgHelper.text__INDENT + "Analysis");
		}
		return theStringBuffer.toString();
	}
	
	// TODO - unfinished
	private List<String> getTransactionDateListForSpinner() {
		ArrayList<String> theDateStringList = new ArrayList<String>();
		List<FseDocumentTransaction> theTransactionList = ((FmsNodeWizardActivity) getWizardActivity()).getFmmHeadlineNode().getFseDocument().getDocumentSectionHistory().getDocumentTransactionList();
		if(theTransactionList.size() < 2) {
			this.contentModification.setEnabled(false);
			this.contentModification.setChecked(false);
			this.contentModification.setClickable(false);
		} else {
			for(FseDocumentTransaction theFseDocumentTransaction : theTransactionList) {
				theDateStringList.add(GcgDateHelper.getGuiDateString0(theFseDocumentTransaction.getRowTimestamp()));
			}
		}
		if(theDateStringList.size() == 0) {
			this.changesSinceDate.setEnabled(false);  // TODO - just until we implement synthetic documents based upon an earlier (not N-1) transaction
		}
		return theDateStringList;
	}

	@Override
	protected String getHelpContextUrlString() {
		return FmsHelpIndex.NODE_PUBLISHING__CONTENT_SELECTION;
	}

}
