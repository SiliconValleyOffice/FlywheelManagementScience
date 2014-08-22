/* @(#)FseRevertCancelOkDialog.java
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
import android.widget.CheckBox;
import android.widget.TextView;

import com.flywheelms.library.R;
import com.flywheelms.library.fms.activity.FmmNodeEditorActivity;
import com.flywheelms.library.gcg.GcgActivity;

public class FseRevertCancelOkDialog extends FmsCancelOkDialog {
	
	private TextView targetTransactionSummary;
	private CheckBox revertStoryCheckBox;
	private CheckBox revertNotesCheckBox;

	public FseRevertCancelOkDialog(GcgActivity aLibraryActivity, String aMessageString) {
		super(aLibraryActivity);
		this.messageString = aMessageString;
	}

	@Override
	protected int getDialogTitleStringResourceId() {
		return R.string.fse__revert__ok_cancel__title;
	}

	@Override
	protected int getDialogTitleIconResourceId() {
		return R.drawable.revert__32;
	}

	@Override
	protected void manageButtonState() {
		this.buttonOk.setVisibility(isRevertStory() || isRevertNotes() ? View.VISIBLE : View.INVISIBLE);
	}

	private boolean isRevertStory() {
		return this.revertStoryCheckBox.isChecked();
	}

	private boolean isRevertNotes() {
		return this.revertNotesCheckBox.isChecked();
	}

	@Override
	protected void onClickButtonOk() {
		((FmmNodeEditorActivity) this.gcgActivity).getFsePerspectiveFlipper().revertFseDocumentToTransaction(isRevertStory(), isRevertNotes());
		this.gcgActivity.stopDialog();
	}

	@Override
	protected void initializeDialogBody() {
		super.initializeDialogBody();
		this.targetTransactionSummary = ((TextView) this.dialogBodyView.findViewById(R.id.fse__target_transaction_summary__text));
		this.targetTransactionSummary.setText(this.messageString);
		this.revertStoryCheckBox = ((CheckBox) this.dialogBodyView.findViewById(R.id.fse__story__checkbox));
		this.revertStoryCheckBox.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FseRevertCancelOkDialog.this.manageButtonState();
			}
		});
		this.revertNotesCheckBox = ((CheckBox) this.dialogBodyView.findViewById(R.id.fse__notes__checkbox));
		this.revertNotesCheckBox.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FseRevertCancelOkDialog.this.manageButtonState();
			}
		});
	}

	@Override
	protected int getCustomDialogContentsResourceId() {
		return R.layout.fse__revert_document__dialog;
	}

}
