/* @(#)FiscalYearWidgetSpinner.java
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

package com.flywheelms.library.fms.widget.spinner;

import android.content.Context;
import android.util.AttributeSet;

import com.flywheelms.gcongui.gcg.interfaces.GcgGuiable;
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.interfaces.WorkAsset;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.governable.FmsOrganization;
import com.flywheelms.library.fmm.node.impl.governable.Portfolio;
import com.flywheelms.library.fmm.node.impl.governable.Project;
import com.flywheelms.library.fmm.node.impl.governable.WorkPackage;
import com.flywheelms.library.fms.widget.FmmHeadlineNodeWidgetSpinner;

import java.util.ArrayList;

public class PortfolioWidgetSpinner extends FmmHeadlineNodeWidgetSpinner {

	// primary parent is FmsOrganization, obtained using FmmDatabaseMediator.getActiveMediator().getFmmOwner()
	private Project projectException;  // primary child
	private WorkAsset projectAssetException; // primary child, primary child
	private WorkPackage workPackageException;  // primary child, primary child, primary child
	private Portfolio portfolioException; // peer exception

	public PortfolioWidgetSpinner(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
	}

	@Override
	protected String getLabelText() {
		return FmmNodeDefinition.PORTFOLIO.getLabelText();
	}

    public Portfolio getPortfolio() {
        return (Portfolio) getSelectedItem();
    }

    //////  START  /////////////////////
	// filter_id__PRIMARY_PARENT
	// filter_id__PRIMARY_PARENT__PRIMARY_CHILD__MOVE_TARGET
	public void updateSpinnerData(FmsOrganization anFmsOrganization, Portfolio aPortfolioException) {
		this.portfolioException = aPortfolioException;
		super.updateSpinnerData();
	}

    public void updateSpinnerData(Portfolio aPortfolioException) {
        this.portfolioException = aPortfolioException;
        super.updateSpinnerData();
    }

	@Override
	//filter_id__PRIMARY_PARENT
    protected ArrayList<? extends GcgGuiable> getPrimaryParentGuiableList() {
		return FmmDatabaseMediator.getActiveMediator().retrievePortfolioList(
                FmmDatabaseMediator.getActiveMediator().getFmmOwner(), this.portfolioException);
	}
	
	
	// filter_id__PRIMARY_PARENT__PRIMARY_CHILD__MOVE_TARGET
	@Override
	protected ArrayList<? extends GcgGuiable> getPrimaryParentPrimaryChildMoveTargetGuiableList() {  // Project move target
		return FmmDatabaseMediator.getActiveMediator().retrievePortfolioList(
                FmmDatabaseMediator.getActiveMediator().getFmmOwner(), this.portfolioException);
	}

    //////  START  /////////////////////
    // filter_id__PRIMARY_PARENT__PRIMARY_CHILD__PRIMARY_CHILD__MOVE_TARGET

    @Override
    protected ArrayList<? extends GcgGuiable> getPrimaryParentPrimaryChildPrimaryChildMoveTargetGuiableList() { // WorkAsset move target
        return FmmDatabaseMediator.getActiveMediator().retrievePortfolioForWorkAssetMoveTarget(
                FmmDatabaseMediator.getActiveMediator().getFmmOwner(), this.projectException);
    }

	public void updateSpinnerData(Project aProjectException) {
		this.projectException = aProjectException;
		super.updateSpinnerData();
	}

    //////  START  /////////////////////
	// filter_id__PRIMARY_PARENT__PRIMARY_CHILD__PRIMARY_CHILD__PRIMARY_CHILD__MOVE_TARGET  -  WorkPackage move target

	@Override
	protected ArrayList<? extends GcgGuiable> getPrimaryParentPrimaryChildPrimaryChildPrimaryChildMoveTargetGuiableList() { // WorkPackage move target
		return FmmDatabaseMediator.getActiveMediator().retrievePortfolioForWorkPackageMoveTarget(
                FmmDatabaseMediator.getActiveMediator().getFmmOwner(), this.projectAssetException);
	}

    public void updateSpinnerData(WorkAsset aWorkAssetException) {
        this.projectAssetException = aWorkAssetException;
        super.updateSpinnerData();
    }

    //////  START  /////////////////////

    // filter_id__PRIMARY_PARENT__PRIMARY_CHILD__PRIMARY_CHILD__PRIMARY_CHILD__PRIMARY_CHILD__MOVE_TARGET  -  WorkTask move target

    @Override
    protected ArrayList<? extends GcgGuiable> getPrimaryParentPrimaryChildPrimaryChildPrimaryChildPrimaryChildMoveTargetGuiableList() { // WorkTask move target
        return FmmDatabaseMediator.getActiveMediator().retrievePortfolioForWorkTaskMoveTarget(
                FmmDatabaseMediator.getActiveMediator().getFmmOwner(), this.workPackageException);
    }

    public void updateSpinnerData(WorkPackage aWorkPackageException) {
        this.workPackageException = aWorkPackageException;
        super.updateSpinnerData();
    }


}
