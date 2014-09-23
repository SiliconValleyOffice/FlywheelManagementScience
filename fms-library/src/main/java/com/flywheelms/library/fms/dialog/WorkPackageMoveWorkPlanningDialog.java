/* @(#)WorkPackageMoveDialog.java
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
import com.flywheelms.library.fmm.node.impl.governable.WorkPackage;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;

public class WorkPackageMoveWorkPlanningDialog extends HeadlineNodeMoveDialog {

	public WorkPackageMoveWorkPlanningDialog(GcgActivity aLibraryActivity, GcgTreeViewAdapter aTreeViewAdapter, WorkPackage aWorkPackage, FmmHeadlineNode aTargetHeadlineNodeException) {
		super(aLibraryActivity, aTreeViewAdapter, aWorkPackage, aTargetHeadlineNodeException);
		initializeDialogBodyLate();
	}

	@Override
	protected int getMoveDispositionLayoutResourceId() {
        return R.layout.work_package__move_into__flywheel_milestone;
	}

	@Override
	protected void setInitialDispositionTargetParentSpinnerData() {
//        if(this.fmmPerspective == FmmPerspective.STRATEGIC_PLANNING) {
//            ((FiscalYearWidgetSpinner) this.dispositionTargetParentWidgetSpinner).updateSpinnerData(
//                    (StrategicMilestone) this.targetHeadlineNodeException);
//            ((StrategicMilestoneWidgetSpinner) this.dispositionTargetParentWidgetSpinner).updateSpinnerData(
//                    (StrategicMilestone) this.targetHeadlineNodeException);
//            ((FiscalYearWidgetSpinner) this.dispositionTargetParentWidgetSpinner).updateSpinnerData(
//                    (StrategicMilestone) this.targetHeadlineNodeException);
//        } else {
//            ((PortfolioWidgetSpinner) this.dispositionTargetParentWidgetSpinner).updateSpinnerData(
//                    (Project) this.targetHeadlineNodeException);
//        }
	}

	@Override
	protected void setInitialDispositionTargetSpinnerData() {
		updateDispositionTargetWidgetSpinner();
	}

	@Override
	protected void updateDispositionTargetWidgetSpinner() {
//        if(this.fmmPerspective == FmmPerspective.STRATEGIC_PLANNING) {
//            ((StrategicMilestoneWidgetSpinner) this.dispositionTargetWidgetSpinner).updateSpinnerData(
//                    (FiscalYear) this.dispositionTargetParentWidgetSpinner.getSelectedItem(),
//                    (StrategicMilestone) this.targetHeadlineNodeException );
//        } else {
//            ((ProjectWidgetSpinner) this.dispositionTargetWidgetSpinner).updateSpinnerData(
//                    (Portfolio) this.dispositionTargetParentWidgetSpinner.getSelectedItem(),
//                    (Project) this.targetHeadlineNodeException );
//        }
	}

	@Override
	protected void setInitialDispositionTargetSpinnerSelection() {
		this.dispositionTargetWidgetSpinner.setSelection(0);
	}

	@Override
	protected void updateSequencePositionWidgetSpinner() {
		boolean sequenceAtEnd = true;
//		if(this.currentFiscalYearNumber < ((FiscalYearWidgetSpinner) this.dispositionTargetWidgetSpinner).getFiscalYearNumberAsInt()) {
//			sequenceAtEnd = false;
//		}
		this.sequencePositionSpinner.setSequenceAtEnd(sequenceAtEnd);
	}

	@Override
	protected void initializeDispositionTargetParentSpinnerListener() {
		this.dispositionTargetParentWidgetSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				WorkPackageMoveWorkPlanningDialog.this.updateDispositionTargetWidgetSpinner();
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
				WorkPackageMoveWorkPlanningDialog.this.updateSequencePositionWidgetSpinner();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) { return; }
		});
	}
	
	@Override
	protected boolean moveHeadlineNode() {
        boolean theMoveStatus = false;
//        if(this.fmmPerspective == FmmPerspective.STRATEGIC_PLANNING) {
//            theMoveStatus = FmmDatabaseMediator.getActiveMediator().moveSingleWorkPackageIntoStrategicMilestone(
//                    getFmmHeadlineNode().getNodeIdString(),
//                    ((WorkPackage) getFmmHeadlineNode()).getStrategicMilestoneNodeId(),
//                    this.dispositionTargetWidgetSpinner.getFmmNode().getNodeIdString(),
//                    this.sequencePositionSpinner.sequenceAtEnd(),
//                    true);
//        } else {
//            theMoveStatus = FmmDatabaseMediator.getActiveMediator().moveSingleWorkPackageIntoProject(
//                    getFmmHeadlineNode().getNodeIdString(),
//                    ((WorkPackage) getFmmHeadlineNode()).getProjectNodeIdString(),
//                    this.dispositionTargetWidgetSpinner.getFmmNode().getNodeIdString(),
//                    this.sequencePositionSpinner.sequenceAtEnd(),
//                    true);
//        }
//		toastMoveResult(theMoveStatus);
		return theMoveStatus;
	}

}
