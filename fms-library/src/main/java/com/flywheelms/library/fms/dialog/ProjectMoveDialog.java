/* @(#)ProjectMoveDialog.java
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.flywheelms.gcongui.gcg.activity.GcgActivity;
import com.flywheelms.gcongui.gcg.treeview.GcgTreeViewAdapter;
import com.flywheelms.library.R;
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.node.impl.governable.Portfolio;
import com.flywheelms.library.fmm.node.impl.governable.Project;
import com.flywheelms.library.fms.widget.spinner.PortfolioWidgetSpinner;

public class ProjectMoveDialog extends HeadlineNodeMoveDialog {

	public ProjectMoveDialog(GcgActivity aLibraryActivity, GcgTreeViewAdapter aTreeViewAdapter, Project aHeadlineNode, Portfolio aTargetHeadlineNodeException) {
		super(aLibraryActivity, aTreeViewAdapter, aHeadlineNode, aTargetHeadlineNodeException);
		initializeDialogBodyLate();
	}

	@Override
	protected int getMoveDispositionLayoutResourceId() {
		return R.layout.project__move_into__portfolio;
	}

	@Override
	protected void setInitialDispositionTargetSpinnerData() {
		updateDispositionTargetWidgetSpinner();
	}

	@Override
	protected void updateDispositionTargetWidgetSpinner() {
        ((PortfolioWidgetSpinner) this.dispositionTargetWidgetSpinner).updateSpinnerData(
                (Portfolio) this.targetHeadlineNodeException);
	}

	@Override
	protected void setInitialDispositionTargetSpinnerSelection() {
		this.dispositionTargetWidgetSpinner.setSelection(0);
	}

	@Override
	protected void initializeDispositionTargetParentSpinnerListener() {
		this.dispositionTargetParentWidgetSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				ProjectMoveDialog.this.updateDispositionTargetWidgetSpinner();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) { return; }
		});
	}

	@Override
	protected void initializeDispositionTargetSpinnerListener() {
		this.dispositionTargetWidgetSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				ProjectMoveDialog.this.updateSequencePositionWidgetSpinner();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) { return; }
		});
	}
	
	@Override
	protected boolean moveHeadlineNode() {
		boolean theMoveStatus = FmmDatabaseMediator.getActiveMediator().moveSingleProjectIntoPortfolio(
                getFmmHeadlineNode().getNodeIdString(),
                this.dispositionTargetWidgetSpinner.getFmmNode().getNodeIdString(),
                true);
		toastMoveResult(theMoveStatus);
		return theMoveStatus;
	}

}
