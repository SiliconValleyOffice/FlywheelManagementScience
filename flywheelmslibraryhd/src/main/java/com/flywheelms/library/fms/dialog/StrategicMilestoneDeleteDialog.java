/* @(#)StrategicMilestoneDeleteDialog.java
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

import java.util.ArrayList;

import android.widget.LinearLayout;

import com.flywheelms.library.R;
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.node.impl.governable.FiscalYear;
import com.flywheelms.library.fmm.node.impl.governable.StrategicMilestone;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;
import com.flywheelms.library.fms.widget.spinner.FiscalYearWidgetSpinner;
import com.flywheelms.library.fms.widget.spinner.StrategicMilestoneWidgetSpinner;
import com.flywheelms.library.gcg.GcgActivity;
import com.flywheelms.library.gcg.treeview.GcgTreeViewAdapter;

public class StrategicMilestoneDeleteDialog extends HeadlineNodeDeleteDialog {

	public StrategicMilestoneDeleteDialog(GcgActivity aLibraryActivity, GcgTreeViewAdapter aTreeViewAdapter, FmmHeadlineNode aHeadlineNode) {
		super(aLibraryActivity, aTreeViewAdapter, aHeadlineNode);
	}

	@Override
	protected int getPrimaryLinkDispositionLayoutResourceId() {
		return R.layout.project_asset__disposition;
	}

	@Override
	protected ArrayList<? extends FmmHeadlineNode> getPrimaryLinkHeadlineNodeList() {
		return FmmDatabaseMediator.getActiveMediator().listProjectAssetForStrategicMilestone(this.headlineNode.getNodeIdString());
	}

	@Override
	protected FiscalYearWidgetSpinner findTargetParentWidgetSpinner(LinearLayout aLinearLayout) {
		FiscalYearWidgetSpinner theWidgetSpinner = (FiscalYearWidgetSpinner) aLinearLayout.findViewById(R.id.disposition_target__parent__spinner);
		theWidgetSpinner.updateSpinnerData((StrategicMilestone) this.headlineNode);
		theWidgetSpinner.setGcgActivity(this.gcgActivity);
		return theWidgetSpinner;
	}

	@Override
	protected StrategicMilestoneWidgetSpinner findTargetWidgetSpinner(LinearLayout aLinearLayout) {
		StrategicMilestoneWidgetSpinner theStrategicMilestoneWidgetSpinner = (StrategicMilestoneWidgetSpinner) aLinearLayout.findViewById(R.id.disposition_target__spinner);
		theStrategicMilestoneWidgetSpinner.setGcgActivity(this.gcgActivity);
		return theStrategicMilestoneWidgetSpinner;
	}

	@Override
	protected void setInitialTargetParentSpinnerData(DeleteDisposition aDeleteDisposition) {
		((FiscalYearWidgetSpinner) aDeleteDisposition.getTargetParentWidgetSpinner()).updateSpinnerData(
				(StrategicMilestone) aDeleteDisposition.getTargetHeadlineNodeException());
	}

	@Override
	protected void updateTargetWidgetSpinner(final DeleteDisposition aDeleteDisposition) {
		((StrategicMilestoneWidgetSpinner) aDeleteDisposition.getTargetWidgetSpinner()).updateSpinnerData(
				(FiscalYear) aDeleteDisposition.getTargetParentWidgetSpinner().getSelectedItem(),
				(StrategicMilestone) aDeleteDisposition.getTargetHeadlineNodeException() );
	}

	@Override
	protected boolean deleteHeadlineNode() {
		return FmmDatabaseMediator.getActiveMediator().deleteStrategicMilestone((StrategicMilestone) this.headlineNode, false);
	}

	@Override
	protected boolean deletePrimaryLinkNodes() {
		return FmmDatabaseMediator.getActiveMediator().orphanAllProjectAssetsFromStrategicMilestone(this.headlineNode.getNodeIdString(), false);
	}

	@Override
	protected boolean orphanPrimaryLinkNodes() {
		return FmmDatabaseMediator.getActiveMediator().orphanAllProjectAssetsFromStrategicMilestone(this.headlineNode.getNodeIdString(), false);
	}

	@Override
	protected boolean movePrimaryLinkNodes() {
		return FmmDatabaseMediator.getActiveMediator().moveAllProjectAssetsToStrategicMilestone(
				this.headlineNode.getNodeIdString(),
				this.primaryLinkDeleteDisposition.getTargetWidgetSpinner().getFmmNode().getNodeIdString(),
				this.primaryLinkDeleteDisposition.getSequencePositionSpinner().getSelectedItem().getDataText().equals("End"),
				false );
	}

}
