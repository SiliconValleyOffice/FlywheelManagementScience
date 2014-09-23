/* @(#)GcgGuiPreferencesDialog.java
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

package com.flywheelms.gcongui.gcg.dialog;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.flywheelms.gcongui.R;
import com.flywheelms.gcongui.gcg.activity.GcgActivity;
import com.flywheelms.gcongui.gcg.interfaces.GcgGuiPreferencesClient;

public class GcgGuiPreferencesDialog extends GcgCancelDialog {

	private final GcgGuiPreferencesClient guiPreferencesClient;

	public GcgGuiPreferencesDialog(GcgActivity aGcgActivity, String aViewGroupName, GcgGuiPreferencesClient aGuiPreferencesClient) {
		super(aGcgActivity, aViewGroupName, "");
		this.guiPreferencesClient = aGuiPreferencesClient;
        initialSetup();
	}

	@Override
	protected int getDialogTitleStringResourceId() {
		return R.string.gcg__save_gui_preferences;
	}

	@Override
	protected int getDialogTitleIconResourceId() {
		return R.drawable.gcg__gui_preferences__32;
	}

	@Override
	protected int getCustomDialogContentsResourceId() {
		return R.layout.gcg__gui_preferences__dialog;
	}

	@Override
	protected void initializeDialogBody() {
		super.initializeDialogBody();
		((TextView) this.dialogBodyView.findViewById(R.id.activity_name__data)).setText(this.gcgActivity.getActivityLabel());
		if(this.targetDetail == null) {
			TableLayout theTableLayout = (TableLayout) this.dialogBodyView.findViewById(R.id.gui_target);
			TableRow theTableRow = (TableRow) theTableLayout.findViewById(R.id.row__a2);
			theTableLayout.removeView(theTableRow);
		} else {
			((TextView) this.dialogBodyView.findViewById(R.id.target_detail__data)).setText(this.targetDetail);
		}
		((Button) this.dialogBodyView.findViewById(R.id.button__clear)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				GcgGuiPreferencesDialog.this.gcgActivity.stopDialog();
				GcgGuiPreferencesDialog.this.guiPreferencesClient.guiPreferencesClear();
			}
		});
		((Button) this.dialogBodyView.findViewById(R.id.button__restore)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				GcgGuiPreferencesDialog.this.gcgActivity.stopDialog();
				GcgGuiPreferencesDialog.this.guiPreferencesClient.guiPreferencesRestore();
			}
		});
		((Button) this.dialogBodyView.findViewById(R.id.button__save)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				GcgGuiPreferencesDialog.this.gcgActivity.stopDialog();
				GcgGuiPreferencesDialog.this.guiPreferencesClient.guiPreferencesSave();
			}
		});
	}

}
