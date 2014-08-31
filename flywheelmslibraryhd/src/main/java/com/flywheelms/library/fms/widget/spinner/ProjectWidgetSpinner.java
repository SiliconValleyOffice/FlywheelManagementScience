/* @(#)ProjectWidgetSpinner.java
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

import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.governable.Portfolio;
import com.flywheelms.library.fmm.node.impl.governable.Project;
import com.flywheelms.library.fmm.node.impl.governable.ProjectAsset;
import com.flywheelms.library.fmm.node.impl.governable.WorkPackage;
import com.flywheelms.library.fms.widget.FmmHeadlineNodeWidgetSpinner;
import com.flywheelms.library.gcg.interfaces.GcgGuiable;

import java.util.ArrayList;

public class ProjectWidgetSpinner extends FmmHeadlineNodeWidgetSpinner {

	private Portfolio portfolio;  // primary parent
    private Project projectException;  // peer exception
    private ProjectAsset projectAssetException;  // primary child exception
    private WorkPackage workPackageException;  // primary child, primary child exception

	public ProjectWidgetSpinner(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
	}

	@Override
	protected String getLabelText() {
		return FmmNodeDefinition.PROJECT.getLabelText();
	}
	
	@Override
	protected ArrayList<GcgGuiable> getPrimaryParentGuiableList() {
		return new ArrayList<GcgGuiable>(
			FmmDatabaseMediator.getActiveMediator().getProjectList(this.portfolio, this.projectException) );
	}
	
	@Override
	protected ArrayList<GcgGuiable> getPrimaryParentPrimaryChildMoveTargetGuiableList() {
		ArrayList<GcgGuiable> theGuiableList;
		if(this.portfolio == null) {
			theGuiableList = new ArrayList<GcgGuiable>(); 
		} else {	
			theGuiableList = new ArrayList<GcgGuiable>(
				FmmDatabaseMediator.getActiveMediator().listProjectsForProjectAssetMoveTarget(
						this.portfolio, this.projectException));
		}
		return theGuiableList;
	}
	
	@Override
	protected ArrayList<GcgGuiable> getPrimaryParentPrimaryChildPrimaryChildMoveTargetGuiableList() {
		ArrayList<GcgGuiable> theGuiableList;
		if(this.portfolio == null) {
			theGuiableList = new ArrayList<GcgGuiable>(); 
		} else {	
			theGuiableList = new ArrayList<GcgGuiable>(FmmDatabaseMediator.getActiveMediator().listProjectsForWorkPackageMoveTarget(
					this.portfolio, this.projectAssetException));
		}
		return theGuiableList;
	}

    @Override
    protected ArrayList<GcgGuiable> getPrimaryParentPrimaryChildPrimaryChildPrimaryChildMoveTargetGuiableList() {
        ArrayList<GcgGuiable> theGuiableList;
        if(this.portfolio == null) {
            theGuiableList = new ArrayList<GcgGuiable>();
        } else {
            theGuiableList = new ArrayList<GcgGuiable>(FmmDatabaseMediator.getActiveMediator().listProjectsForWorkTaskMoveTarget(
                    this.portfolio, this.workPackageException));
        }
        return theGuiableList;
    }

	public void updateSpinnerData(Portfolio aPortfolio) {
		this.portfolio = aPortfolio;
		super.updateSpinnerData();
	}

	public void updateSpinnerData(Portfolio aPortfolio, Project aProjectException) {
		this.portfolio = aPortfolio;
		this.projectException = aProjectException;
		super.updateSpinnerData();
	}

	public void updateSpinnerData(Portfolio aPortfolio, ProjectAsset aProjectAssetException) {
		this.portfolio = aPortfolio;
		this.projectAssetException = aProjectAssetException;
		super.updateSpinnerData();
	}

    public void updateSpinnerData(Portfolio aPortfolio, WorkPackage aWorkPackageException) {
        this.portfolio = aPortfolio;
        this.workPackageException = aWorkPackageException;
        super.updateSpinnerData();
    }
	
}
