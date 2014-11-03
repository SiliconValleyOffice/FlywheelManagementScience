/* @(#)WorkPackageWidgetSpinner.java
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
import com.flywheelms.library.fmm.node.impl.governable.WorkPackage;
import com.flywheelms.library.fmm.node.impl.governable.WorkPlan;
import com.flywheelms.library.fmm.node.impl.governable.WorkTask;
import com.flywheelms.library.fms.widget.FmmHeadlineNodeWidgetSpinner;

import java.util.ArrayList;

public class WorkTaskWidgetSpinner extends FmmHeadlineNodeWidgetSpinner {

	private WorkPackage workPackage; // primary parent
	private WorkPlan workPlan; // secondary parent
	private WorkTask workTaskException;  // a peer that should be ignored

	public WorkTaskWidgetSpinner(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
	}

	@Override
	protected String getLabelText() {
		return FmmNodeDefinition.WORK_TASK.getLabelText();
	}
	
	@Override
	protected ArrayList<GcgGuiable> getPrimaryParentGuiableList() {
		ArrayList<GcgGuiable> theGuiableList;
		if(this.workPackage == null) {
			theGuiableList = new ArrayList<GcgGuiable>(); 
		} else {	
			theGuiableList = new ArrayList<GcgGuiable>(
					FmmDatabaseMediator.getActiveMediator().retrieveWorkTaskList(this.workPackage, this.workTaskException) );
		}
		return theGuiableList;
	}

    public void updateSpinnerData(WorkPackage aWorkPackage) {
        this.workPackage = aWorkPackage;
        super.updateSpinnerData();
    }

    public void updateSpinnerData(WorkPackage aWorkPackage, WorkTask aWorkTaskException) {
        this.workPackage = aWorkPackage;
        this.workTaskException = aWorkTaskException;
        super.updateSpinnerData();
    }
	
	@Override
	protected ArrayList<GcgGuiable> getSecondaryParentGuiableList() {
		ArrayList<GcgGuiable> theGuiableList;
		if(this.workPlan == null) {
			theGuiableList = new ArrayList<GcgGuiable>(); 
		} else {	
			theGuiableList = new ArrayList<GcgGuiable>(
					FmmDatabaseMediator.getActiveMediator().retrieveWorkTaskList(this.workPlan, this.workTaskException) );
		}
		return theGuiableList;
	}

    public void updateSpinnerData(WorkPlan aWorkPlan) {
        this.workPlan = aWorkPlan;
        super.updateSpinnerData();
    }

    public void updateSpinnerData(WorkPlan aWorkPlan, WorkTask aWorkTaskException) {
        this.workPlan = aWorkPlan;
        this.workTaskException = aWorkTaskException;
        super.updateSpinnerData();
    }
	
	@Override
	protected ArrayList<? extends GcgGuiable> getOrphanNodesPrimaryParentGuiableList() {
		ArrayList<GcgGuiable> theGuiableList =
				new ArrayList<GcgGuiable>(FmmDatabaseMediator.getActiveMediator().listWorkTaskOrphansFromWorkPackage());
		return theGuiableList;
	}
	
	@Override
	protected ArrayList<? extends GcgGuiable> getOrphanNodesSecondaryParentGuiableList() {
		ArrayList<GcgGuiable> theGuiableList =
				new ArrayList<GcgGuiable>(FmmDatabaseMediator.getActiveMediator().listWorkTaskOrphansFromWorkPlan());
		return theGuiableList;
	}
	
}
