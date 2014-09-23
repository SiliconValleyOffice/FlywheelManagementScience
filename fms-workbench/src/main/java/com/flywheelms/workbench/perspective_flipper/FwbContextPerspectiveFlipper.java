/* @(#)FwbContextPerspectiveFlipper.java
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
import com.flywheelms.workbench.perspective.FwbContextAnalysisPerspective;
import com.flywheelms.workbench.perspective.FwbContextServiceDeliveryPerspective;
import com.flywheelms.workbench.perspective.FwbContextStrategicPlanningPerspective;
import com.flywheelms.workbench.perspective.FwbContextWorkBreakdownPerspective;
import com.flywheelms.workbench.perspective.FwbContextWorkPlanningPerspective;

public class FwbContextPerspectiveFlipper extends GcgPerspectiveFlipper {
	
	// menu_position must be synchronized with the frame spinner layout containing the perspective flipper
	public static final int menu_position__STRATEGIC_PLANNING = 0;
	public static final int menu_position__WORK_BREAKDOWN = 1;
	public static final int menu_position__WORK_PLANNING = 2; 
	public static final int menu_position__SERVICE_DELIVERY = 3; 
	public static final int menu_position__ANALYSIS = 4; 
	private FwbContextStrategicPlanningPerspective strategicPlanningPerspective;
	private FwbContextWorkBreakdownPerspective workBreakdownPerspective;
	private FwbContextWorkPlanningPerspective workPlanningPerspective;
	private FwbContextServiceDeliveryPerspective serviceDeliveryPerspective;
	private FwbContextAnalysisPerspective analysisPerspective;

	public FwbContextPerspectiveFlipper(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
	}
	
	public void initialize(GcgActivity aLibraryActivity, GcgViewFlipperListener aViewFlipListener, GcgFrameSpinner aFrameSpinner, int aSpinnnableMenuIndex) {
		super.initialize(aLibraryActivity, aViewFlipListener, FmmFrame.CONTEXT_FOR_WORKBENCH, aFrameSpinner);
		this.spinnnableMenuIndex = aSpinnnableMenuIndex;
	}

	@Override
	public void initializeViewFlipperViews() {
        this.strategicPlanningPerspective = (FwbContextStrategicPlanningPerspective) getGcgActivity().findViewById(R.id.fwb_context__strategic_planning);
        this.strategicPlanningPerspective.initialize(getGcgActivity(), this, this.spinnnableMenuIndex, FwbContextPerspectiveFlipper.menu_position__STRATEGIC_PLANNING);
        this.workBreakdownPerspective = (FwbContextWorkBreakdownPerspective) getGcgActivity().findViewById(R.id.fwb_context__work_breakdown);
        this.workBreakdownPerspective.initialize(getGcgActivity(), this, this.spinnnableMenuIndex, FwbContextPerspectiveFlipper.menu_position__WORK_BREAKDOWN);
        this.workPlanningPerspective = (FwbContextWorkPlanningPerspective) getGcgActivity().findViewById(R.id.fwb_context__work_planning);
		this.workPlanningPerspective.initialize(getGcgActivity(), this, this.spinnnableMenuIndex, FwbContextPerspectiveFlipper.menu_position__WORK_PLANNING);
		this.serviceDeliveryPerspective = (FwbContextServiceDeliveryPerspective) getGcgActivity().findViewById(R.id.fwb_context__service_delivery);
		this.serviceDeliveryPerspective.initialize(getGcgActivity(), this, this.spinnnableMenuIndex, FwbContextPerspectiveFlipper.menu_position__SERVICE_DELIVERY);
		this.analysisPerspective = (FwbContextAnalysisPerspective) getGcgActivity().findViewById(R.id.fwb_context__analysis);
		this.analysisPerspective.initialize(getGcgActivity(), this, this.spinnnableMenuIndex, FwbContextPerspectiveFlipper.menu_position__ANALYSIS);
		super.initializeViewFlipperViews();
	}

	public FwbContextStrategicPlanningPerspective getStrategicPlanningView() {
		return this.strategicPlanningPerspective;
	}

	public FwbContextServiceDeliveryPerspective getServiceDeliveryView() {
		return this.serviceDeliveryPerspective;
	}

	public FwbContextWorkBreakdownPerspective getWorkBreakdownView() {
		return this.workBreakdownPerspective;
	}

	public FwbContextWorkPlanningPerspective getWorkPlanningView() {
		return this.workPlanningPerspective;
	}

	public FwbContextAnalysisPerspective getAnalysisView() {
		return this.analysisPerspective;
	}

	@Override
	public void discardDataChanges() { /*  N/A  */ }

	@Override
	public void viewData() { /*  TODO - use to refresh active perspective??? */ }

	@Override
	public void saveGuiState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void restoreGuiState() {
		// TODO Auto-generated method stub
		
	}

}
