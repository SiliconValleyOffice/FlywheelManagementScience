/* @(#)GcgSaveChangesDialog.java
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

package com.flywheelms.library.gcg.dialog;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

import com.flywheelms.library.R;
import com.flywheelms.library.fms.dialog.FmsDialog;
import com.flywheelms.library.gcg.activity.GcgActivity;

public class GcgSaveChangesDialog extends FmsDialog {
	
	public static final int button_choice__DISCARD = 0;
	public static final int button_choice__CANCEL_NAVIGATION = 1;
	public static final int button_choice__SAVE = 2;
	private CheckBox checkBox;
	private int nextAction;
	public static final int next_action__NONE = 0;
	public static final int next_action__FINISH = 1;
	public static final int next_action__NAVIGATE_RIGHT = 2;
	public static final int next_action__NAVIGATE_LEFT =3;
	public static final int next_action__NAVIGATE_TO_BEGINNING = 4;
	public static final int next_action__NAVIGATE_TO_END = 5;
	public static final int next_action__BROWSE_TRANSACTION_HISTORY = 6;
	public static final int next_action__REVERT_DOCUMENT_TO_TRANSACTION = 7;

	public GcgSaveChangesDialog(GcgActivity aLibraryActivity, String aViewGroupName, int aNextAction) {
		super(aLibraryActivity, aViewGroupName);
		this.nextAction = aNextAction;
	}

	@Override
	protected int getDialogTitleStringResourceId() {
		return R.string.fms__save_data_changes;
	}

	@Override
	protected int getDialogTitleIconResourceId() {
		return R.drawable.gcg__action__save;
	}

	@Override
	protected void initializeDialogBody() {
		View theDialogBodyView = inflateDialogBody(R.layout.gcg__save_changes_dialog);
		initializeDialogTargetInfo(theDialogBodyView);
		((Button) theDialogBodyView.findViewById(R.id.discard_changes__button)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				GcgSaveChangesDialog.this.gcgActivity.stopDialog();
				GcgSaveChangesDialog.this.gcgActivity.saveDataChangesDialogResults(button_choice__DISCARD, getCheckboxValue(), GcgSaveChangesDialog.this.nextAction);
			}
		});
		((Button) theDialogBodyView.findViewById(R.id.cancel_navigation__button)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				GcgSaveChangesDialog.this.gcgActivity.stopDialog();
				GcgSaveChangesDialog.this.gcgActivity.saveDataChangesDialogResults(button_choice__CANCEL_NAVIGATION, getCheckboxValue(), GcgSaveChangesDialog.this.nextAction);
			}
		});
		Button theSaveButton = (Button) theDialogBodyView.findViewById(R.id.save_changes__button);
		theSaveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				GcgSaveChangesDialog.this.gcgActivity.stopDialog();
				GcgSaveChangesDialog.this.gcgActivity.startMagentaActivityStatusAnimation();
				GcgSaveChangesDialog.this.gcgActivity.saveDataChangesDialogResults(button_choice__SAVE, getCheckboxValue(), GcgSaveChangesDialog.this.nextAction);
				GcgSaveChangesDialog.this.gcgActivity.stopActivityStatusAnimation();
			}
		});
		theSaveButton.requestFocus();
		this.checkBox = (CheckBox) theDialogBodyView.findViewById(R.id.auto_save__checkbox);
	}

	private boolean getCheckboxValue() {
		return this.checkBox.isChecked();
	}

}
