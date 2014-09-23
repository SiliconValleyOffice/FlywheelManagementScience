/* @(#)StrategicMilestoneWidgetSpinner.java
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
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.governable.FiscalYear;
import com.flywheelms.library.fmm.node.impl.governable.ProjectAsset;
import com.flywheelms.library.fmm.node.impl.governable.StrategicMilestone;
import com.flywheelms.library.fms.widget.FmmHeadlineNodeWidgetSpinner;

import java.util.ArrayList;

public class StrategicMilestoneWidgetSpinner extends FmmHeadlineNodeWidgetSpinner {
	
	private FiscalYear fiscalYear;  // primary parent
	private StrategicMilestone strategicMilestoneException;  // peer exception
    private ProjectAsset projectAssetException;  // primary child exception

	public StrategicMilestoneWidgetSpinner(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
	}

	@Override
	protected String getLabelText() {
		return FmmNodeDefinition.STRATEGIC_MILESTONE.getLabelText();
	}
	
	@Override
	protected ArrayList<GcgGuiable> getPrimaryParentGuiableList() {
		ArrayList<GcgGuiable> theGuiableList = new ArrayList<GcgGuiable>(
			FmmDatabaseMediator.getActiveMediator().getStrategicMilestoneList(
					this.fiscalYear, this.strategicMilestoneException) );
		return theGuiableList;
	}
	
	@Override
	protected ArrayList<GcgGuiable> getPrimaryParentPrimaryChildMoveTargetGuiableList() {
		ArrayList<GcgGuiable> theGuiableList;
		if(this.fiscalYear == null) {
			theGuiableList = new ArrayList<GcgGuiable>(); 
		} else {	
			theGuiableList = new ArrayList<GcgGuiable>(
				FmmDatabaseMediator.getActiveMediator().listStrategicMilestoneForProjectAssetMoveTarget(
						this.fiscalYear, this.strategicMilestoneException));
		}
		return theGuiableList;
	}
	
	@Override
	protected ArrayList<GcgGuiable> getPrimaryParentPrimaryChildPrimaryChildMoveTargetGuiableList() {
		ArrayList<GcgGuiable> theGuiableList;
		if(this.fiscalYear == null) {
			theGuiableList = new ArrayList<GcgGuiable>(); 
		} else {	
			theGuiableList = new ArrayList<GcgGuiable>(FmmDatabaseMediator.getActiveMediator().listStrategicMilestoneForWorkPackageMoveTarget(
					this.fiscalYear, this.projectAssetException));
		}
		return theGuiableList;
	}

	public void updateSpinnerData(FiscalYear aFiscalYear) {
		this.fiscalYear = aFiscalYear;
		super.updateSpinnerData();
	}

	public void updateSpinnerData(FiscalYear aFiscalYear, StrategicMilestone aStrategicMilestoneException) {
		this.fiscalYear = aFiscalYear;
		this.strategicMilestoneException = aStrategicMilestoneException;
		super.updateSpinnerData();
	}

	public void updateSpinnerData(FiscalYear aFiscalYear, ProjectAsset aProjectAssetException) {
		this.fiscalYear = aFiscalYear;
		this.projectAssetException = aProjectAssetException;
		super.updateSpinnerData();
	}
	
}
