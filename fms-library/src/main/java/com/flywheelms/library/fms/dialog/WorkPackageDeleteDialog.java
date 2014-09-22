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
import com.flywheelms.library.fmm.node.impl.governable.ProjectAsset;
import com.flywheelms.library.fmm.node.impl.governable.WorkPackage;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;
import com.flywheelms.library.fms.treeview.filter.FmsTreeViewAdapter;
import com.flywheelms.library.fms.widget.spinner.PortfolioWidgetSpinner;
import com.flywheelms.library.fms.widget.spinner.ProjectAssetWidgetSpinner;
import com.flywheelms.library.fms.widget.spinner.ProjectWidgetSpinner;
import com.flywheelms.library.fms.widget.spinner.WorkPackageWidgetSpinner;
import com.flywheelms.library.gcg.activity.GcgActivity;

import java.util.ArrayList;

public class WorkPackageDeleteDialog extends HeadlineNodeDeleteDialog {

	public WorkPackageDeleteDialog(GcgActivity aLibraryActivity, FmsTreeViewAdapter aTreeViewAdapter, FmmHeadlineNode aHeadlineNode) {
		super(aLibraryActivity, aTreeViewAdapter, aHeadlineNode);
	}

    @Override
    protected int getDialogBodyLayoutResourceId() {
        return R.layout.gcg__dialog_body__cancel_ok__vertical;
    }

	@Override
	protected int getPrimaryChildrenDispositionLayoutResourceId() {
		return R.layout.work_package__work_task__disposition;
	}

    @Override
    protected int getCustomDialogContentsResourceId() {
        return R.layout.fmm__headline_node__delete__dialog;
    }

	@Override
	protected ArrayList<? extends FmmHeadlineNode> getPrimaryChildHeadlineNodeList() {
		return FmmDatabaseMediator.getActiveMediator().listWorkTasksForWorkPackage(getFmmHeadlineNode().getNodeIdString());
	}

    @Override
    protected PortfolioWidgetSpinner findTargetGreatGrandparentWidgetSpinner(LinearLayout aLinearLayout) {
        PortfolioWidgetSpinner theWidgetSpinner =
                (PortfolioWidgetSpinner) aLinearLayout.findViewById(R.id.disposition_target__great_grandparent__spinner);
        theWidgetSpinner.setGcgActivity(this.gcgActivity);
        return theWidgetSpinner;
    }

	@Override
	protected ProjectWidgetSpinner findTargetGrandparentWidgetSpinner(LinearLayout aLinearLayout) {
		ProjectWidgetSpinner theWidgetSpinner =
				(ProjectWidgetSpinner) aLinearLayout.findViewById(R.id.disposition_target__grandparent__spinner);
		theWidgetSpinner.setGcgActivity(this.gcgActivity);
		return theWidgetSpinner;
	}

	@Override
	protected ProjectAssetWidgetSpinner findTargetParentWidgetSpinner(LinearLayout aLinearLayout) {
		ProjectAssetWidgetSpinner theWidgetSpinner =
				(ProjectAssetWidgetSpinner) aLinearLayout.findViewById(R.id.disposition_target__parent__spinner);
		theWidgetSpinner.setGcgActivity(this.gcgActivity);
		return theWidgetSpinner;
	}

	@Override
	protected WorkPackageWidgetSpinner findTargetWidgetSpinner(LinearLayout aLinearLayout) {
		WorkPackageWidgetSpinner theWidgetSpinner =
				(WorkPackageWidgetSpinner) aLinearLayout.findViewById(R.id.disposition_target__spinner);
		theWidgetSpinner.setGcgActivity(this.gcgActivity);
		return theWidgetSpinner;
	}

    @Override
    protected void setInitialTargetGreatGrandparentSpinnerData(DeleteDisposition aDeleteDisposition) {
        ((PortfolioWidgetSpinner) aDeleteDisposition.getTargetGreatGrandparentWidgetSpinner()).updateSpinnerData(
                (WorkPackage) aDeleteDisposition.getTargetHeadlineNodeException());
    }

	@Override
	protected void setInitialTargetGrandparentSpinnerData(DeleteDisposition aDeleteDisposition) {
		((ProjectWidgetSpinner) aDeleteDisposition.getTargetGrandparentWidgetSpinner()).updateSpinnerData(
                (Portfolio) aDeleteDisposition.getTargetGreatGrandparentWidgetSpinner().getSelectedItem(),
				(WorkPackage) aDeleteDisposition.getTargetHeadlineNodeException());
	}

    @Override
    protected void updateTargetGrandparentWidgetSpinner(final DeleteDisposition aDeleteDisposition) {
        ((ProjectWidgetSpinner) aDeleteDisposition.getTargetGrandparentWidgetSpinner()).updateSpinnerData(
                (Portfolio) aDeleteDisposition.getTargetGreatGrandparentWidgetSpinner().getFmmNode(),
                (WorkPackage) aDeleteDisposition.getTargetHeadlineNodeException() );
    }

	@Override
	protected void updateTargetParentWidgetSpinner(final DeleteDisposition aDeleteDisposition) {
		((ProjectAssetWidgetSpinner) aDeleteDisposition.getTargetParentWidgetSpinner()).updateSpinnerData(
				(Project) aDeleteDisposition.getTargetGrandparentWidgetSpinner().getFmmNode(),
				(WorkPackage) aDeleteDisposition.getTargetHeadlineNodeException() );
	}

	@Override
	protected void updateTargetWidgetSpinner(final DeleteDisposition aDeleteDisposition) {
		((WorkPackageWidgetSpinner) aDeleteDisposition.getTargetWidgetSpinner()).updateSpinnerData(
				(ProjectAsset) aDeleteDisposition.getTargetParentWidgetSpinner().getSelectedItem(),
				(WorkPackage) aDeleteDisposition.getTargetHeadlineNodeException() );
	}

	@Override
	protected boolean deleteHeadlineNode() {
		return FmmDatabaseMediator.getActiveMediator().deleteWorkPackage((WorkPackage) getFmmHeadlineNode(), false);
	}

	@Override
	protected boolean orphanPrimaryChildren() {
		return FmmDatabaseMediator.getActiveMediator().orphanAllWorkTasksFromWorkPackage(getFmmHeadlineNode().getNodeIdString(), false);
	}

	@Override
	protected boolean movePrimaryChildrenToNewParent() {
		return FmmDatabaseMediator.getActiveMediator().moveAllWorkTasksIntoWorkPackage(
                getFmmHeadlineNode().getNodeIdString(),
                this.primaryChildDeleteDisposition.getTargetWidgetSpinner().getFmmNode().getNodeIdString(),
                this.primaryChildDeleteDisposition.isSequencePositionSpinnerAtEnd(),
                false);
	}

}
