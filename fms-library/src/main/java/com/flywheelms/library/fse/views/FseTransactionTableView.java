/* @(#)FseTransactionTableView.java
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

package com.flywheelms.library.fse.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TableLayout;

import com.flywheelms.library.R;
import com.flywheelms.library.fse.model.FseDocumentSectionHistory;
import com.flywheelms.library.fse.model.FseDocumentTransaction;
import com.flywheelms.library.fse.perspective_flipper.perspective.FseDocumentSectionHistoryPerspective;

import java.util.ArrayList;
import java.util.Collections;

public class FseTransactionTableView extends TableLayout {
	
	private Context context;
	private FseDocumentSectionHistoryPerspective documentSectionHistoryView;
	private ArrayList<FseDocumentTransactionRowView> documentTransactionViewList = new ArrayList<FseDocumentTransactionRowView>();
	private View selectedRowView;

	public FseTransactionTableView(Context aContext, AttributeSet attrs) {
		super(aContext, attrs);
		this.context = aContext;
	}
	
	public void initialize(FseDocumentSectionHistoryPerspective aDocumentSectionHistoryView) {
		this.documentSectionHistoryView = aDocumentSectionHistoryView;
	}

	// create new transaction row in table
	private FseDocumentTransactionRowView newDocumentTransactionView(FseDocumentTransaction aDocumentTransaction) {
		FseDocumentTransactionRowView theDocumentTransactionView;
		theDocumentTransactionView = (FseDocumentTransactionRowView) LayoutInflater.from(getContext()).inflate(R.layout.fse__history__document_transaction_row, null);
		theDocumentTransactionView.initialize(aDocumentTransaction);
		return theDocumentTransactionView;
	}

	public FseDocumentSectionHistoryPerspective getDocumentSectionHistoryView() {
		return this.documentSectionHistoryView;
	}

	public ArrayList<FseDocumentTransactionRowView> getDocumentTransactionViewList() {
		return this.documentTransactionViewList;
	}

	public void viewDocumentSection(FseDocumentSectionHistory aDocumentSectionHistory) {
		removeAllViewsInLayout();
		this.documentTransactionViewList.clear();
		ArrayList<FseDocumentTransaction> theReversedList = new ArrayList<FseDocumentTransaction>(aDocumentSectionHistory.getDocumentTransactionList());
		Collections.reverse(theReversedList);
		for(FseDocumentTransaction theDocumentTransaction : theReversedList) {
			FseDocumentTransactionRowView theDocumentTransactionView = newDocumentTransactionView(theDocumentTransaction); 
			theDocumentTransactionView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View aView) {
					FseTransactionTableView.this.selectedRowView = aView;	
					PopupMenu thePopupMenu = new PopupMenu(FseTransactionTableView.this.context, aView);
					thePopupMenu.getMenuInflater().inflate(R.menu.fse__transaction_list__popup_menu, thePopupMenu.getMenu());
					thePopupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
						
						@Override
						public boolean onMenuItemClick(MenuItem aMenuItem) {
							int theItemId = aMenuItem.getItemId();
							if(theItemId == R.id.action__browse) {
								launchFseHistoryBrowser();
							} else if(theItemId == R.id.action__revert) {
								revertDocumentToSelectedTransactionRow();
							}
							return true;
						}
					});
					thePopupMenu.show();
				}
			});
			this.documentTransactionViewList.add(theDocumentTransactionView);
			addView(theDocumentTransactionView);
		}
		invalidate();
	}

	private void launchFseHistoryBrowser() {
		this.documentSectionHistoryView.launchFseHistoryBrowser();
	}

	private void revertDocumentToSelectedTransactionRow() {
		this.documentSectionHistoryView.getReadyToRevertDocumentToSelectedTransactionRow();
	}

	public int getSelectedRowIndex() {
		return this.documentTransactionViewList.indexOf(FseTransactionTableView.this.selectedRowView);
	}
	
	public FseDocumentTransaction getSelectedDocumentTransaction() {
		return this.documentTransactionViewList.get(getSelectedRowIndex()).getDocumentTransaction();
	}

}
