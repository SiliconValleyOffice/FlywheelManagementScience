/* @(#)FseDocumentSectionHistoryPerspective.java
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

package com.flywheelms.library.fse.perspective_flipper.perspective;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.flywheelms.library.R;
import com.flywheelms.library.fmm.context.FmmPerspective;
import com.flywheelms.library.fms.dialog.FseRevertCancelOkDialog;
import com.flywheelms.library.fms.helper.FmsActivityHelper;
import com.flywheelms.library.fms.helper.FmsHelpIndex;
import com.flywheelms.library.fms.listeners.FmsNodeActivityGenericKeyListener;
import com.flywheelms.library.fse.FseDocumentSectionType;
import com.flywheelms.library.fse.model.FseDocument;
import com.flywheelms.library.fse.model.FseDocumentSection;
import com.flywheelms.library.fse.model.FseDocumentSectionHistory;
import com.flywheelms.library.fse.model.FseDocumentSectionParagraphAudit;
import com.flywheelms.library.fse.model.FseDocumentTransactionType;
import com.flywheelms.library.fse.perspective_flipper.FsePerspectiveFlipper;
import com.flywheelms.library.fse.views.FseTransactionTableView;
import com.flywheelms.library.gcg.GcgActivity;
import com.flywheelms.library.gcg.dialog.GcgSaveChangesDialog;
import com.flywheelms.library.gcg.interfaces.GcgPerspective;
import com.flywheelms.library.gcg.viewflipper.GcgViewFlipper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class FseDocumentSectionHistoryPerspective extends FseDocumentSectionPerspective {
	
	private GcgPerspective gcgPerspective = FmmPerspective.HISTORY;

	@Override
	public GcgPerspective getGcgPerspective() {
		return this.gcgPerspective;
	}
	
	protected LinearLayout documentSectionLayout;
	protected FseDocumentSectionHistory documentSectionHistory = new FseDocumentSectionHistory();
	protected FseTransactionTableView transactionTableView;
	protected EditText hiddenInput;
	protected static int selectedRowIndex;

	public FseDocumentSectionHistoryPerspective(Context context, AttributeSet attrs) {
		super(context, attrs);
		setSectionType(FseDocumentSectionType.HISTORY);
		this.leftMenuSpacerResourceId = R.drawable.left_menu__filler_1;
		initializeHiddenInput();
	}
	
	@Override
	public void initialize(GcgActivity anGcgActivity, GcgViewFlipper aViewFlipper, int aSpinnableMenu, int aPageNumber) {
		this.gcgActivity = anGcgActivity;
		initialize(getFseDocumentViewParent().getFsePerspectiveFlipper());
		super.initialize(anGcgActivity, aViewFlipper, aSpinnableMenu, aPageNumber);
	}

	@Override
	protected void initLocalFdkKeyboard() {
		return;
	}

	@Override
	public void fdkDictationResults(ArrayList<String> aWordList) {
		return;
	}

	private void initializeHiddenInput() {
		this.hiddenInput = (EditText) findViewById(R.id.hidden_input);
		this.hiddenInput.setKeyListener(new FmsNodeActivityGenericKeyListener(getDocumentPerspectiveFlipper(), true));
//		this.hiddenInput.setOnFocusChangeListener(new OnFocusChangeListener() {
//			
//			@Override
//			public void onFocusChange(View v, boolean hasFocus) {
//				if(! FseDocumentSectionHistoryView.this.hiddenInput.hasFocus()) {
//					FseDocumentSectionHistoryView.this.hiddenInput.requestFocus();
//				}
//			}
//		});
	}

	@Override
	protected int getPageTitleResourceId() {
		return R.string.fse__document_section__history;
	}

	@Override
	protected int getViewLayoutResourceId() {
		return R.layout.fms_view__document_section__history;
	}
	
	@Override
	protected void initialize(FsePerspectiveFlipper aDocumentView) {
		super.initialize(aDocumentView);
		initializeHiddenInput();
		setDocumentSectionLayout();
	}

	@Override
	public String getHelpContextUrlString() {
		return FmsHelpIndex.FSE__DOCUMENT_SECTION__HISTORY;
	}

	@Override
	public ViewGroup getDocumentSectionLayout() {
		return this.documentSectionLayout;
	}

	@Override
	public void setDocumentSectionLayout() {
				inflate(this.context, getDocumentSectionViewResourceId(), null);
		this.transactionTableView =
				(FseTransactionTableView) findViewById(R.id.fse__transaction_table__view);
		this.transactionTableView.initialize(this);
	}

	@Override
	public
	void viewDocumentSection(FseDocumentSection aDocumentSection) {
		this.documentSectionHistory = (FseDocumentSectionHistory) aDocumentSection;
		this.transactionTableView.viewDocumentSection(this.documentSectionHistory);
	}

	@Override
	protected String sectionIsModified() {
		return null;
	}

	public FseDocumentSectionHistory getDocumentSectionClone() {
		return this.documentSectionHistory.getClone();
	}

	public FseDocumentSectionHistory getDocumentSectionHistory() {
		return this.documentSectionHistory;
	}

	public void updateHistory(
			Date aTimestamp,
			FseDocumentTransactionType aDocumentTransactionType,
			String aComment,
			FseDocumentSectionParagraphAudit aStoryAudit,
			FseDocumentSectionParagraphAudit aNotesAudit,
			FseDocument anFseDocument ) {
		this.documentSectionHistory.updateHistory(aTimestamp, aDocumentTransactionType, aComment, aStoryAudit, aNotesAudit, anFseDocument);
	}

	public void refresh() {
		viewDocumentSection(this.documentSectionHistory);
	}
	
	@Override
	protected void activateView() {
		super.activateView();
		this.hiddenInput.requestFocus();
	}

	public void launchFseHistoryBrowser() {
		if(getGcgActivity().protectDataChanges(GcgSaveChangesDialog.next_action__BROWSE_TRANSACTION_HISTORY, "launch FSE Document History browser")) {
			return;
		}
		int theTransactionListIndex = this.transactionTableView.getSelectedRowIndex();
		FmsActivityHelper.startFseDocumentHistoryBrowserActivity(
				getGcgActivity(),
				getFseDocumentViewParent().getFsePerspectiveFlipper().getDocumentId(),
				theTransactionListIndex );
	}

	public void getReadyToRevertDocumentToSelectedTransactionRow() {
		FseDocumentSectionHistoryPerspective.selectedRowIndex = this.transactionTableView.getSelectedRowIndex();
		if(getGcgActivity().protectDataChanges(GcgSaveChangesDialog.next_action__REVERT_DOCUMENT_TO_TRANSACTION, "revert FSE Document\r\nto an earlier version")) {
			return;
		}
		revertDocumentToSelectedTransactionRow();
	}

	public void revertDocumentToSelectedTransactionRow() {
		FseRevertCancelOkDialog theOkCancelDialog = new FseRevertCancelOkDialog(
				getGcgActivity(),
				"Revert to document transaction #" + 
						(this.transactionTableView.getSelectedRowIndex() + 1) + "\n\r           " +
						this.documentSectionHistory.getDocumentTransactionList().get(FseDocumentSectionHistoryPerspective.selectedRowIndex) );
		getGcgActivity().startDialog(theOkCancelDialog);
	}
	
	public FseTransactionTableView getTransactionTableView() {
		return this.transactionTableView;
	}

	public JSONObject getJsonObject() {
		return this.documentSectionHistory.getJsonObject();
	}

	@Override
	protected void viewDocumentSectionAsHistory(
			FseDocumentSection aDocumentSection) { /* N/A */ }
	
}
