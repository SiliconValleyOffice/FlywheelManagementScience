/* @(#)StrategicMilestoneMoveDialog.java
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

import com.flywheelms.library.R;
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.node.impl.governable.FiscalYear;
import com.flywheelms.library.fmm.node.impl.governable.StrategicMilestone;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;
import com.flywheelms.library.fms.widget.spinner.FiscalYearWidgetSpinner;
import com.flywheelms.library.gcg.activity.GcgActivity;
import com.flywheelms.library.gcg.treeview.GcgTreeViewAdapter;

public class StrategicMilestoneMoveDialog extends HeadlineNodeMoveDialog {
	
	private String currentFiscalYearNumberString;
	private int currentFiscalYearNumber;

	public StrategicMilestoneMoveDialog(GcgActivity aLibraryActivity, GcgTreeViewAdapter aTreeViewAdapter, FmmHeadlineNode aHeadlineNode, FiscalYear aTargetHeadlineNodeException) {
		super(aLibraryActivity, aTreeViewAdapter, aHeadlineNode, aTargetHeadlineNodeException);
		this.currentFiscalYearNumberString = ((StrategicMilestone) getFmmHeadlineNode()).getFiscalYear().getYearString();
		this.currentFiscalYearNumber = ((StrategicMilestone) getFmmHeadlineNode()).getFiscalYear().getYearAsInt();
		initializeDialogBodyLate();
	}

	@Override
	protected int getMoveDispositionLayoutResourceId() {
		return R.layout.strategic_milestone__move_into__fiscal_year;
	}

	@Override
	protected void setInitialDispositionTargetSpinnerData() {
		((FiscalYearWidgetSpinner) this.dispositionTargetWidgetSpinner).updateSpinnerData((FiscalYear) this.targetHeadlineNodeException);
	}

	@Override
	protected void setInitialDispositionTargetSpinnerSelection() {
		((FiscalYearWidgetSpinner) this.dispositionTargetWidgetSpinner).selectNextYear(this.currentFiscalYearNumberString);
	}

	@Override
	protected void updateSequencePositionWidgetSpinner() {
		boolean sequenceAtEnd = true;
		if(this.currentFiscalYearNumber < ((FiscalYearWidgetSpinner) this.dispositionTargetWidgetSpinner).getFiscalYearNumberAsInt()) {
			sequenceAtEnd = false;
		}
		this.sequencePositionSpinner.setSequenceAtEnd(sequenceAtEnd);
	}

	@Override
	protected void initializeDispositionTargetSpinnerListener() {
		this.dispositionTargetWidgetSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				StrategicMilestoneMoveDialog.this.updateSequencePositionWidgetSpinner();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) { return; }
		});
	}
	
	@Override
	protected boolean moveHeadlineNode() {
		boolean theMoveStatus = FmmDatabaseMediator.getActiveMediator().moveSingleStrategicMilestoneIntoFiscalYear(
                getFmmHeadlineNode().getNodeIdString(),
                ((StrategicMilestone) getFmmHeadlineNode()).getFiscalYearNodeIdString(),
                this.dispositionTargetWidgetSpinner.getFmmNode().getNodeIdString(),
                this.sequencePositionSpinner.sequenceAtEnd(),
                true);
		toastMoveResult(theMoveStatus);
		return theMoveStatus;
	}

}
