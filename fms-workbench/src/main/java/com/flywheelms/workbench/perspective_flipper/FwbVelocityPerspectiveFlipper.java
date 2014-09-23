/* @(#)FwbVelocityPerspectiveFlipper.java
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

package com.flywheelms.workbench.perspective_flipper;

import android.content.Context;
import android.util.AttributeSet;

import com.flywheelms.gcongui.gcg.activity.GcgActivity;
import com.flywheelms.gcongui.gcg.menu.GcgFrameSpinner;
import com.flywheelms.gcongui.gcg.viewflipper.GcgPerspectiveFlipper;
import com.flywheelms.gcongui.gcg.viewflipper.GcgViewFlipperListener;
import com.flywheelms.library.fmm.context.FmmFrame;
import com.flywheelms.workbench.R;
import com.flywheelms.workbench.perspective.FwbVelocityBudgetingPerspective;
import com.flywheelms.workbench.perspective.FwbVelocityCompletionPerspective;
import com.flywheelms.workbench.perspective.FwbVelocityGovernancePerspective;
import com.flywheelms.workbench.perspective.FwbVelocityStoryPerspective;
import com.flywheelms.workbench.perspective.FwbVelocityWorkBreakdownPerspective;
import com.flywheelms.workbench.perspective.FwbVelocityWorkPlanningPerspective;

public class FwbVelocityPerspectiveFlipper extends GcgPerspectiveFlipper {

	public static final int menu_position__COMPLETION = 0; 
	public static final int menu_position__GOVERNANCE = 1; 
	public static final int menu_position__STORY = 2; 
	public static final int menu_position__WORK_BREAKDOWN = 3; 
	public static final int menu_position__BUDGETING = 4; 
	public static final int menu_position__WORK_PLANNING = 5; 
	private FwbVelocityCompletionPerspective completionPerspective;
	private FwbVelocityGovernancePerspective governancePerspective;
	private FwbVelocityStoryPerspective storyPerspective;
	private FwbVelocityWorkBreakdownPerspective workBreakdownPerspective;
	private FwbVelocityBudgetingPerspective budgetingPerspective;
	private FwbVelocityWorkPlanningPerspective workPlanningPerspective;

	public FwbVelocityPerspectiveFlipper(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
	}
	
	public void initialize(GcgActivity aLibraryActivity, GcgViewFlipperListener aViewFlipListener, GcgFrameSpinner aFrameSpinner, int aSpinnnableMenuIndex) {
		super.initialize(aLibraryActivity, aViewFlipListener, FmmFrame.VELOCITY, aFrameSpinner);
		this.spinnnableMenuIndex = aSpinnnableMenuIndex;
		initializeViewFlipperViews();
	}

	@Override
	public void initializeViewFlipperViews() {
		this.completionPerspective = (FwbVelocityCompletionPerspective) getGcgActivity().findViewById(R.id.fwb_velocity__completion);
		this.completionPerspective.initialize(getGcgActivity(), this, this.spinnnableMenuIndex, FwbVelocityPerspectiveFlipper.menu_position__COMPLETION);
		this.governancePerspective = (FwbVelocityGovernancePerspective) getGcgActivity().findViewById(R.id.fwb_velocity__governance);
		this.governancePerspective.initialize(getGcgActivity(), this, this.spinnnableMenuIndex, FwbVelocityPerspectiveFlipper.menu_position__GOVERNANCE);
		this.storyPerspective = (FwbVelocityStoryPerspective) getGcgActivity().findViewById(R.id.fwb_velocity__story);
		this.storyPerspective.initialize(getGcgActivity(), this, this.spinnnableMenuIndex, FwbVelocityPerspectiveFlipper.menu_position__STORY);
		this.workBreakdownPerspective = (FwbVelocityWorkBreakdownPerspective) getGcgActivity().findViewById(R.id.fwb_velocity__work_breakdown);
		this.workBreakdownPerspective.initialize(getGcgActivity(), this, this.spinnnableMenuIndex, FwbVelocityPerspectiveFlipper.menu_position__WORK_BREAKDOWN);
		this.budgetingPerspective = (FwbVelocityBudgetingPerspective) getGcgActivity().findViewById(R.id.fwb_velocity__budgeting);
		this.budgetingPerspective.initialize(getGcgActivity(), this, this.spinnnableMenuIndex, FwbVelocityPerspectiveFlipper.menu_position__BUDGETING);
		this.workPlanningPerspective = (FwbVelocityWorkPlanningPerspective) getGcgActivity().findViewById(R.id.fwb_velocity__work_planning);
		this.workPlanningPerspective.initialize(getGcgActivity(), this, this.spinnnableMenuIndex, FwbVelocityPerspectiveFlipper.menu_position__WORK_PLANNING);
		super.initializeViewFlipperViews();
	}

	public FwbVelocityCompletionPerspective getCompletionView() {
		return this.completionPerspective;
	}

	public FwbVelocityGovernancePerspective getGovernanceView() {
		return this.governancePerspective;
	}

	public FwbVelocityStoryPerspective getStoryView() {
		return this.storyPerspective;
	}

	public FwbVelocityWorkBreakdownPerspective getWorkBreakdownView() {
		return this.workBreakdownPerspective;
	}

	public FwbVelocityBudgetingPerspective getBudgetingView() {
		return this.budgetingPerspective;
	}

	public FwbVelocityWorkPlanningPerspective getWorkPlanningView() {
		return this.workPlanningPerspective;
	}

	@Override
	public void discardDataChanges() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewData() {
		// TODO Auto-generated method stub
		
	}

}
