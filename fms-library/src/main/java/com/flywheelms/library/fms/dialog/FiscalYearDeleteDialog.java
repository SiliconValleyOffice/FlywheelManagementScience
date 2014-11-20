/* @(#)FiscalYearDeleteDialog.java
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

import android.widget.LinearLayout;

import com.flywheelms.gcongui.gcg.activity.GcgActivity;
import com.flywheelms.library.R;
import com.flywheelms.library.fmm.FmmDatabaseService;
import com.flywheelms.library.fmm.node.impl.governable.FiscalYear;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;
import com.flywheelms.library.fms.treeview.filter.FmsTreeViewAdapter;
import com.flywheelms.library.fms.widget.FmmHeadlineNodeWidgetSpinner;
import com.flywheelms.library.fms.widget.spinner.FiscalYearWidgetSpinner;

import java.util.ArrayList;

public class FiscalYearDeleteDialog extends HeadlineNodeDeleteDialog {

	public FiscalYearDeleteDialog(GcgActivity aLibraryActivity, FmsTreeViewAdapter aTreeViewAdapter, FmmHeadlineNode aHeadlineNode) {
		super(aLibraryActivity, aTreeViewAdapter, aHeadlineNode);
	}

	@Override
	protected int getPrimaryChildrenDispositionLayoutResourceId() {
		return R.layout.strategic_milestone__disposition;
	}

    @Override
    protected int getSecondaryChildrenDispositionLayoutResourceId() {
        return R.layout.flywheel_cadence__disposition;
    }

	@Override
	protected ArrayList<? extends FmmHeadlineNode> getPrimaryChildHeadlineNodeList() {
		return FmmDatabaseService.getActiveMediator().retrieveStrategicMilestoneListForFiscalYear(getFmmHeadlineNode().getNodeIdString(), null);
	}
	
	@Override
	protected boolean canOrphanPrimaryChild() {
		return false;
	}

	@Override
	protected ArrayList<? extends FmmHeadlineNode> getSecondaryChildHeadlineNodeList() {
		return FmmDatabaseService.getActiveMediator().retrieveCadenceListForFiscalYear(getFmmHeadlineNode().getNodeIdString());
	}

    protected boolean alwaysDeleteSecondaryChild() {
        return true;
    }
	
	@Override
	protected boolean canOrphanSecondaryChild() {
		return false;
	}

	@Override
	protected FmmHeadlineNodeWidgetSpinner findTargetParentWidgetSpinner(LinearLayout aLinearLayout) {
		return null;
	}

	@Override
	protected FiscalYearWidgetSpinner findTargetWidgetSpinner(LinearLayout aLinearLayout) {
		FiscalYearWidgetSpinner theFiscalYearWidgetSpinner = (FiscalYearWidgetSpinner) aLinearLayout.findViewById(R.id.disposition_target__spinner);
		theFiscalYearWidgetSpinner.setGcgActivity(this.gcgActivity);
		return theFiscalYearWidgetSpinner;
	}

	@Override
	protected void setInitialTargetSpinnerSelection(DeleteDisposition aDeleteDisposition) {
        ((FiscalYearWidgetSpinner) aDeleteDisposition.getTargetWidgetSpinner()).updateSpinnerData((FiscalYear) getFmmHeadlineNode());
		((FiscalYearWidgetSpinner) aDeleteDisposition.getTargetWidgetSpinner()).selectNextYear(this.headlineWidgetTextView.getText().toString());
	}

	@Override
	protected boolean deleteHeadlineNode() {
		return FmmDatabaseService.getActiveMediator().deleteFiscalYear(getFiscalYear(), false);
	}

	@Override
	protected boolean deletePrimaryChildren() {
		return FmmDatabaseService.getActiveMediator().deleteStrategicMilestones(getFiscalYear(), false);
	}

    @Override
    protected boolean deleteSecondaryChildren() {
        return FmmDatabaseService.getActiveMediator().deleteAllCadences(getFiscalYear(), false);
    }

	@Override
	protected boolean movePrimaryChildrenToNewParent() {
		return FmmDatabaseService.getActiveMediator().moveAllStrategicMilestonesIntoFiscalYear(
                getFmmHeadlineNode().getNodeIdString(),
                this.primaryChildDeleteDisposition.getTargetWidgetSpinner().getFmmNode().getNodeIdString(),
                this.primaryChildDeleteDisposition.isSequencePositionSpinnerAtEnd(),
                false);
	}

    private FiscalYear getFiscalYear() {
        return (FiscalYear) getFmmHeadlineNode();
    }

}
