/* @(#)FiscalYearCreateDialog.java
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
import android.widget.CheckBox;

import com.flywheelms.library.R;
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.governable.FiscalYear;
import com.flywheelms.library.fms.widget.spinner.FiscalYearForCreateWidgetSpinner;
import com.flywheelms.library.fms.widget.text_view.FmmNodeTypeWidgetTextView;
import com.flywheelms.library.gcg.GcgActivity;
import com.flywheelms.library.gcg.helper.GcgHelper;
import com.flywheelms.library.gcg.treeview.GcgTreeViewAdapter;

public class FiscalYearCreateDialog extends FmsCancelOkApplyDialog {

	GcgTreeViewAdapter treeViewAdapter;
	protected FmmNodeTypeWidgetTextView fmmNodeTypeWidget;
	protected FiscalYearForCreateWidgetSpinner fiscalYearWidget;
	protected CheckBox editNewFiscalYear;

	public FiscalYearCreateDialog(GcgActivity aLibraryActivity, GcgTreeViewAdapter aTreeViewAdapter) {
		super(aLibraryActivity, FmmNodeDefinition.FISCAL_YEAR);
		this.treeViewAdapter = aTreeViewAdapter;
	}

	@Override
	protected int getDialogTitleStringResourceId() {
		return R.string.fms__create;
	}

	@Override
	protected int getDialogTitleIconResourceId() {
		return this.fmmNodeDefinition.getDialogDrawableResourceId();
	}

	@Override
	protected void initializeDialogBody() {
		super.initializeDialogBody();
		this.fmmNodeTypeWidget = (FmmNodeTypeWidgetTextView) this.dialogBodyView.findViewById(R.id.fmm_node__type);
		this.fmmNodeTypeWidget.setText(this.fmmNodeDefinition.getLabelTextResourceId());
		this.fiscalYearWidget = (FiscalYearForCreateWidgetSpinner) this.dialogBodyView.findViewById(R.id.fiscal_year);
		this.editNewFiscalYear = (CheckBox) this.dialogBodyView.findViewById(R.id.edit_new_fiscal_year);
	}

	@Override
	protected int getDialogBodyLayoutResourceId() {
		return R.layout.fiscal_year__create__dialog;
	}

	@Override
	protected void manageButtonState() {
		this.buttonApply.setVisibility(isMinimumInput() ? View.VISIBLE : View.INVISIBLE);
		this.buttonOk.setVisibility(isMinimumInput() ? View.VISIBLE : View.INVISIBLE);
	}

	protected boolean isMinimumInput() {
		return this.fiscalYearWidget.getCount() > 0;
	}

	@Override
	protected void onClickButtonApply() {
		FiscalYear theFiscalYear =
				FmmDatabaseMediator.getActiveMediator().createFiscalYear(this.fiscalYearWidget.getData().getDataText());
		if(theFiscalYear != null) {
			this.treeViewAdapter.addNewHeadlineNode(theFiscalYear);
			GcgHelper.makeToast(this.fmmNodeTypeWidget.getText() + " created: " + this.fiscalYearWidget.getData().getDataText());
			this.fiscalYearWidget.removeCurrentSpinnerSelection();
			manageButtonState();
		} else {
			GcgHelper.makeToast("ERROR:  Unable to create " + this.fmmNodeTypeWidget.getText() + " " + this.fiscalYearWidget.getData().getDataText());
		}
	}

	@Override
	protected void onClickButtonOk() {
		FiscalYear theFiscalYear =
				FmmDatabaseMediator.getActiveMediator().createFiscalYear(this.fiscalYearWidget.getData().getDataText()); 
		if(theFiscalYear != null) {
			this.treeViewAdapter = this.treeViewAdapter.addNewHeadlineNode(theFiscalYear);
			GcgHelper.makeToast(this.fmmNodeTypeWidget.getText() + " created: " + this.fiscalYearWidget.getData().getDataText());
			if(this.editNewFiscalYear.isChecked()) {
				this.treeViewAdapter.editTreeNode(theFiscalYear);
			}
		} else {
			GcgHelper.makeToast("ERROR:  Unable to create " + this.fmmNodeTypeWidget.getText() + " " + this.fiscalYearWidget.getData().getDataText());
		}
		this.gcgActivity.stopDialog();
	}

}
