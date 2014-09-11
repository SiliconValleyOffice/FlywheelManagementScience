/* @(#)ContextPerspectiveFlipper.java
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

package com.flywheelms.library.fms.perspective_flipper;

import android.content.Context;
import android.util.AttributeSet;

import com.flywheelms.library.R;
import com.flywheelms.library.fms.perspective_flipper.perspective.FmsAnalysisPerspective;
import com.flywheelms.library.fms.perspective_flipper.perspective.FmsPerspectiveFlipper;
import com.flywheelms.library.fms.perspective_flipper.perspective.FmsStrategicPlanningPerspective;
import com.flywheelms.library.fms.perspective_flipper.perspective.FmsWorkBreakdownPerspective;
import com.flywheelms.library.fms.perspective_flipper.perspective.FmsWorkPlanningPerspective;

public class ContextPerspectiveFlipper extends FmsPerspectiveFlipper {

	public static final int menu_position__STRATEGIC_PLANNING = 0;
	public static final int menu_position__WORK_BREAKDOWN = 1;
	public static final int menu_position__WORK_PLANNING = 2;
	public static final int menu_position__ANALYSIS = 3;
	protected FmsStrategicPlanningPerspective fmsStrategicPlanningView;
	protected FmsWorkBreakdownPerspective fmsWorkBreakdownView;
	protected FmsWorkPlanningPerspective fmsWorkPlanningView;
	protected FmsAnalysisPerspective fmsAnalysisView;

	public ContextPerspectiveFlipper(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
	}

	@Override
	public void initializeViewFlipperViews() {
		this.fmsStrategicPlanningView = (FmsStrategicPlanningPerspective) getGcgActivity().findViewById(R.id.context_frame__strategic_planning);
		this.fmsStrategicPlanningView.initialize(getGcgActivity(), this, this.spinnnableMenuIndex, menu_position__STRATEGIC_PLANNING);
		this.fmsWorkBreakdownView = (FmsWorkBreakdownPerspective) getGcgActivity().findViewById(R.id.context_frame__work_breakdown);
		this.fmsWorkBreakdownView.initialize(getGcgActivity(), this, this.spinnnableMenuIndex, menu_position__WORK_BREAKDOWN);
		this.fmsWorkPlanningView = (FmsWorkPlanningPerspective) getGcgActivity().findViewById(R.id.context_frame__work_planning);
		this.fmsWorkPlanningView.initialize(getGcgActivity(), this, this.spinnnableMenuIndex, menu_position__WORK_PLANNING);
		this.fmsAnalysisView = (FmsAnalysisPerspective) getGcgActivity().findViewById(R.id.context_frame__analysis);
		this.fmsAnalysisView.initialize(getGcgActivity(), this, this.spinnnableMenuIndex, menu_position__ANALYSIS);
		super.initializeViewFlipperViews();
	}

	public FmsStrategicPlanningPerspective getFmsStrategicPlanningView() {
		return this.fmsStrategicPlanningView;
	}

	public FmsWorkBreakdownPerspective getFmsWorkBreakdownView() {
		return this.fmsWorkBreakdownView;
	}

	public FmsWorkPlanningPerspective getFmsWorkPlanningView() {
		return this.fmsWorkPlanningView;
	}

	public FmsAnalysisPerspective getFmsAnalysisView() {
		return this.fmsAnalysisView;
	}

	@Override
	public int getFrameMenuSpacerCurtainBackgroundResourceId() {
		return R.color.sands_of_time;
	}

	@Override
	public void discardDataChanges() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewData() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void saveGuiState() { 
		this.fmsStrategicPlanningView.guiPreferencesSaveTransient();
		this.fmsWorkBreakdownView.guiPreferencesSaveTransient();
		this.fmsWorkPlanningView.guiPreferencesSaveTransient();
		this.fmsAnalysisView.guiPreferencesSaveTransient();
	}
	
	@Override
	public void restoreGuiState() {
		this.fmsStrategicPlanningView.guiPreferencesRestoreTransient();
		this.fmsWorkBreakdownView.guiPreferencesRestoreTransient();
		this.fmsWorkPlanningView.guiPreferencesRestoreTransient();
		this.fmsAnalysisView.guiPreferencesRestoreTransient();
	}

}
