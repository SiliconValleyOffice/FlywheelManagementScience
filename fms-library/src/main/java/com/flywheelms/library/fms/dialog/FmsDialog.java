/* @(#)FmsDialog.java
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

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.flywheelms.library.R;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;
import com.flywheelms.library.gcg.GcgActivity;

public abstract class FmsDialog {
	
	protected AlertDialog.Builder dialogBuilder;
	protected final GcgActivity gcgActivity;
	protected String targetDetail = null;
	protected String messageString;
	protected AlertDialog alertDialog;
    protected ViewGroup dialogBodyView;
	protected FmmNodeDefinition fmmNodeDefinition;
	protected FmmHeadlineNode headlineNode;
	protected FmmHeadlineNode parentHeadlineNode;
	protected LinearLayout customContentsContainer;

	public FmsDialog(GcgActivity aGcgActivity) {
		this(aGcgActivity, "", "", null, null, null);
	}

	public FmsDialog(GcgActivity aGcgActivity, String aTargetDetail) {
		this(aGcgActivity, aTargetDetail, "", null, null, null);
	}
	
	public FmsDialog(GcgActivity aGcgActivity, String aTargetDetail, String aMessageString) {
		this(aGcgActivity, aTargetDetail, aMessageString, null, null, null);
	}
	
	public FmsDialog(GcgActivity aGcgActivity, FmmNodeDefinition anFmmNodeDefinition) {
		this(aGcgActivity, "", "", anFmmNodeDefinition, null, null);
	}
	
	public FmsDialog(GcgActivity aGcgActivity, FmmHeadlineNode aHeadlineNode) {
		this(aGcgActivity, "", "", aHeadlineNode.getFmmNodeDefinition(), aHeadlineNode, null);
	}
	
	public FmsDialog(GcgActivity aGcgActivity, FmmHeadlineNode aHeadlineNode, FmmHeadlineNode aParentHeadlineNode) {
		this(aGcgActivity, "", "", aHeadlineNode.getFmmNodeDefinition(), aHeadlineNode, aParentHeadlineNode);
	}
	
	public FmsDialog(
			GcgActivity aGcgActivity,
			String aTargetDetail,
			String aMessageString,
			FmmNodeDefinition anFmmNodeDefinition,
			FmmHeadlineNode aHeadlineNode,
			FmmHeadlineNode aParentHeadlineNode ) {
		this.gcgActivity = aGcgActivity;
		this.targetDetail = aTargetDetail;
		this.messageString = aMessageString;
		this.fmmNodeDefinition = anFmmNodeDefinition;
		this.headlineNode = aHeadlineNode;
		this.parentHeadlineNode = aParentHeadlineNode;
		this.dialogBuilder = new AlertDialog.Builder(this.gcgActivity);
		this.dialogBuilder.setTitle(getDialogTitleString());
		this.dialogBuilder.setIcon(getDialogTitleIconResourceId());
		if(! deferredDialogInitialization()) {
			initializeDialogBody();
			manageButtonState();
		}
	}

	protected boolean deferredDialogInitialization() {
		return false;
	}

	protected void manageButtonState() { return; }

	protected String getDialogTitleString() {
		return this.gcgActivity.getResources().getString(getDialogTitleStringResourceId());
	}

	protected abstract int getDialogTitleStringResourceId();

    protected int getDialogTitleIconResourceId() {
        if(this.fmmNodeDefinition != null) {
            return this.fmmNodeDefinition.getDialogDrawableResourceId();
        }
        return 0;
    }

	protected View inflateDialogBody(int aLayoutResourceId) {
		LayoutInflater theLayoutInflater = LayoutInflater.from(this.dialogBuilder.getContext());
		ViewGroup theDialogBody = (ViewGroup) theLayoutInflater.inflate(aLayoutResourceId, null);
		setMinimumWidth(theDialogBody);
		setMinimumHeight(theDialogBody);
		if(getCustomDialogContentsResourceId() != 0) {
			this.customContentsContainer = (LinearLayout) theDialogBody.findViewById(R.id.gcg_dialog__custom_contents_container);
			theLayoutInflater.inflate(getCustomDialogContentsResourceId(), this.customContentsContainer, true);
		}
		this.dialogBuilder.setView(theDialogBody);
		return theDialogBody;
	}

	protected int getCustomDialogContentsResourceId() {
		return 0;
	}
	
	protected void setMinimumHeight(@SuppressWarnings("unused") View theCustomView) { return; }

	protected void setMinimumWidth(@SuppressWarnings("unused") View theCustomView) { return; }

	protected abstract void initializeDialogBody();
	
	public void processDialog() {
		if(this.alertDialog == null) {
			this.alertDialog = this.dialogBuilder.create();
			this.alertDialog.setOnKeyListener(new AlertDialog.OnKeyListener() {

				@Override
				public boolean onKey(DialogInterface arg0, int aKeyCode, KeyEvent aKeyEvent) {
					if (aKeyCode == KeyEvent.KEYCODE_BACK && aKeyEvent.getAction() == KeyEvent.ACTION_DOWN) {
						FmsDialog.this.gcgActivity.stopDialog();
						return true;
					}
					return false;
				}
			});
			this.alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		}
		refreshDialog();
		this.alertDialog.show();
	}

	public void restartDialog() {
		this.alertDialog.show();
	}
	
	public void refreshDialog() { return; }

	// optional support method
	protected void initializeDialogTargetInfo(View theDialogBodyView) {
		((TextView) theDialogBodyView.findViewById(R.id.activity_name__data)).setText(this.gcgActivity.getActivityLabel());
		if(this.targetDetail == null) {
			TableLayout theTableLayout = (TableLayout) theDialogBodyView.findViewById(R.id.gui_target);
			TableRow theTableRow = (TableRow) theTableLayout.findViewById(R.id.row__a2);
			theTableLayout.removeView(theTableRow);
		} else {
			((TextView) theDialogBodyView.findViewById(R.id.target_detail__data)).setText(this.targetDetail);
		}
	}

	public void dismiss() {
		this.alertDialog.dismiss();
	}
	
	public void onWidgetDataChangeListener(@SuppressWarnings("unused") int aResourceId) { return; }

}
