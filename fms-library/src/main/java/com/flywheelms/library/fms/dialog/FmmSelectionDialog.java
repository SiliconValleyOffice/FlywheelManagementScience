/* @(#)FmmSelectionDialog.java
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
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;

import com.flywheelms.gcongui.gcg.activity.GcgActivity;
import com.flywheelms.library.R;
import com.flywheelms.library.fms.widget.spinner.FmmAccessScopeWidgetSpinner;
import com.flywheelms.library.fms.widget.spinner.FmmConfigurationWidgetSpinner;

public class FmmSelectionDialog extends FmsCancelOkDialog {
	
	private FmmAccessScopeWidgetSpinner accessScopeSpinner;
	private FmmConfigurationWidgetSpinner repositorySpinner;
	private Button configurationWrenchButton;
	@SuppressWarnings("unused")
	private CheckBox useAsDefault;
	
	public FmmSelectionDialog(GcgActivity aLibraryActivity) {
		super(aLibraryActivity);
	}

	@Override
	protected int getDialogTitleStringResourceId() {
		return R.string.fmm_repository__selection;
	}

	@Override
	protected int getDialogTitleIconResourceId() {
		return R.drawable.fmm_repository;
	}

	@Override
	protected void initializeDialogBody() {
		super.initializeDialogBody();
		this.accessScopeSpinner = (FmmAccessScopeWidgetSpinner) this.dialogBodyView.findViewById(R.id.fmm_repository__scope__spinner);
		this.accessScopeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				FmmSelectionDialog.this.repositorySpinner.setAccessScope(
						FmmSelectionDialog.this.accessScopeSpinner.getFmmAccessScope());
				FmmSelectionDialog.this.repositorySpinner.updateSpinnerData();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) { /* N/A */ }
		});
		this.repositorySpinner = (FmmConfigurationWidgetSpinner) this.dialogBodyView.findViewById(R.id.fmm_repository__spinner);
		this.repositorySpinner.setAccessScope(this.accessScopeSpinner.getFmmAccessScope());
		this.repositorySpinner.updateSpinnerData();
		this.configurationWrenchButton = (Button) this.dialogBodyView.findViewById(R.id.configuration_wrench__button);
		this.configurationWrenchButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FmmSelectionDialog.this.gcgActivity.startDialog(
						new FmmManagementDialog(FmmSelectionDialog.this.gcgActivity) );
			}
		});
		this.useAsDefault = (CheckBox) this.dialogBodyView.findViewById(R.id.use_as_default);
	}
	
	@Override
	public void refreshDialog() {
		this.repositorySpinner.setAccessScope(this.accessScopeSpinner.getFmmAccessScope());
		this.repositorySpinner.updateSpinnerData();
		this.repositorySpinner.setSelection(this.repositorySpinner.getCount() - 1);
		manageButtonState();
	}

	@Override
	protected void manageButtonState() {
		super.manageButtonState();
		if(this.repositorySpinner.getListSize() > 0) {
			this.buttonOk.setVisibility(View.VISIBLE);
		} else {
			this.buttonOk.setVisibility(View.INVISIBLE);
		}
	}

	@Override
	protected int getDialogBodyLayoutResourceId() {
		return R.layout.fmm__selection__dialog;
	}

	@Override
	protected void onClickButtonOk() {
		this.gcgActivity.dataSourceSelected(this.repositorySpinner.getSelectedItem());
		this.gcgActivity.stopDialog();
	}

	@Override
	protected void onClickButtonCancel() {
		this.gcgActivity.stopDialog();
	}

}
