/* @(#)ProjectAssetDeleteDialog.java
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

import com.flywheelms.library.R;
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.node.impl.governable.Portfolio;
import com.flywheelms.library.fmm.node.impl.governable.Project;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;
import com.flywheelms.library.fms.widget.spinner.FiscalYearWidgetSpinner;
import com.flywheelms.library.fms.widget.spinner.PortfolioWidgetSpinner;
import com.flywheelms.library.fms.widget.spinner.ProjectWidgetSpinner;
import com.flywheelms.library.fms.widget.spinner.StrategicMilestoneWidgetSpinner;
import com.flywheelms.library.gcg.activity.GcgActivity;
import com.flywheelms.library.gcg.treeview.GcgTreeViewAdapter;

import java.util.ArrayList;

public class ProjectDeleteDialog extends HeadlineNodeDeleteDialog {

	public ProjectDeleteDialog(GcgActivity aLibraryActivity, GcgTreeViewAdapter aTreeViewAdapter, FmmHeadlineNode aHeadlineNode) {
		super(aLibraryActivity, aTreeViewAdapter, aHeadlineNode);
	}

	@Override
	protected int getPrimaryChildrenDispositionLayoutResourceId() {
		return R.layout.project__project_asset__disposition;
	}

	@Override
	protected ArrayList<? extends FmmHeadlineNode> getPrimaryChildHeadlineNodeList() {
		return FmmDatabaseMediator.getActiveMediator().listProjectAssetsForProject(this.headlineNode.getNodeIdString());
	}

	@Override
	protected FiscalYearWidgetSpinner findTargetGrandparentWidgetSpinner(LinearLayout aLinearLayout) {
		FiscalYearWidgetSpinner theWidgetSpinner =
				(FiscalYearWidgetSpinner) aLinearLayout.findViewById(R.id.disposition_target__grandparent__spinner);
		theWidgetSpinner.setGcgActivity(this.gcgActivity);
		return theWidgetSpinner;
	}

	@Override
	protected StrategicMilestoneWidgetSpinner findTargetParentWidgetSpinner(LinearLayout aLinearLayout) {
		StrategicMilestoneWidgetSpinner theWidgetSpinner =
				(StrategicMilestoneWidgetSpinner) aLinearLayout.findViewById(R.id.disposition_target__parent__spinner);
		theWidgetSpinner.setGcgActivity(this.gcgActivity);
		return theWidgetSpinner;
	}

	@Override
	protected ProjectWidgetSpinner findTargetWidgetSpinner(LinearLayout aLinearLayout) {
		ProjectWidgetSpinner theWidgetSpinner =
				(ProjectWidgetSpinner) aLinearLayout.findViewById(R.id.disposition_target__spinner);
		theWidgetSpinner.setGcgActivity(this.gcgActivity);
		return theWidgetSpinner;
	}

	@Override
	protected void updateTargetParentWidgetSpinner(final DeleteDisposition aDeleteDisposition) {
		((PortfolioWidgetSpinner) aDeleteDisposition.getTargetParentWidgetSpinner()).updateSpinnerData(
				FmmDatabaseMediator.getActiveMediator().getFmmOwner(),
				(Portfolio) aDeleteDisposition.getTargetHeadlineNodeException() );
	}

	@Override
	protected void updateTargetWidgetSpinner(final DeleteDisposition aDeleteDisposition) {
		((ProjectWidgetSpinner) aDeleteDisposition.getTargetWidgetSpinner()).updateSpinnerData(
				(Portfolio) aDeleteDisposition.getTargetParentWidgetSpinner().getSelectedItem(),
				(Project) aDeleteDisposition.getTargetHeadlineNodeException() );
	}

	@Override
	protected boolean deleteHeadlineNode() {
		return FmmDatabaseMediator.getActiveMediator().deleteProject((Project) this.headlineNode, false);
	}

	@Override
	protected boolean orphanPrimaryChildren() {
		return FmmDatabaseMediator.getActiveMediator().orphanAllProjectAssetsFromProject(this.headlineNode.getNodeIdString(), false);
	}

	@Override
	protected boolean movePrimaryChildrenToNewParent() {
		return FmmDatabaseMediator.getActiveMediator().moveAllProjectAssetsIntoProject(
                this.headlineNode.getNodeIdString(),
                this.primaryChildDeleteDisposition.getTargetWidgetSpinner().getFmmNode().getNodeIdString(),
                this.primaryChildDeleteDisposition.isSequencePositionSpinnerAtEnd(),
                false);
	}

}
