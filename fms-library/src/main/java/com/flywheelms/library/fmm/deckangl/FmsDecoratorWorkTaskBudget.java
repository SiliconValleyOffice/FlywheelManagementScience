/* @(#)FmsDecoratorTaskBudget.java
** 
** Copyright (C) 2012 by Steven D. Stamps
**
**             Trademarks & Copyrights
** Flywheel Management Science(TM), Flywheel Management Model(TM),
** Flywheel Story Editor(TM) and FlywheelMS(TM) are exclusive trademarks
** of Steven D. Stamps and may only be used freely for the purpose of
** identifying the unforked version of this software.  Subsequent forks
** may not use these trademarks.  All other rights are reserved.
** and FlywheelMS(TM) are exclusive trademarks of Steven D. Stamps
** and may only be used freely for the purpose of identifying the
** unforked version of this software.  Subsequent forks (if any) may
** not use these trademarks.  All other rights are reserved.
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

package com.flywheelms.library.fmm.deckangl;

import android.graphics.drawable.Drawable;

import com.flywheelms.gcongui.deckangl.enumerator.DecKanGlDecoratorCanvasLocation;
import com.flywheelms.gcongui.deckangl.enumerator.DecKanGlDecoratorColor;
import com.flywheelms.gcongui.deckangl.glyph.DecKanGlDecoratorEnumeration;
import com.flywheelms.gcongui.gcg.GcgApplication;
import com.flywheelms.library.R;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmTribKnQualityNormalizedDescription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FmsDecoratorWorkTaskBudget extends DecKanGlDecoratorEnumeration {
	
	protected static String qualityMetricName = GcgApplication.getAppResources().getString(R.string.tribkn_quality_metric__task_points_budget);
	protected static String qualityMetricDescription = GcgApplication.getAppResources().getString(R.string.tribkn_quality_metric__task_points_budget__description);
	protected static Drawable qualityMetricDrawable = GcgApplication.getAppResources().getDrawable(R.drawable.tribkn_quality_metric__work_task_budget);
	protected static DecKanGlDecoratorCanvasLocation decoratorCanvasLocation = DecKanGlDecoratorCanvasLocation.left_zone_BOTTOM;
	
	@Override
	public String getQualityMetricName() {
		return FmsDecoratorWorkTaskBudget.qualityMetricName;
	}
	
	@Override
	public String getQualityMetricDescription() {
		return FmsDecoratorWorkTaskBudget.qualityMetricDescription;
	}
	
	@Override
	public Drawable getQualityMetricDrawable() {
		return FmsDecoratorWorkTaskBudget.qualityMetricDrawable;
	}
	
	@Override
	public DecKanGlDecoratorCanvasLocation getDecoratorCanvasLocation() {
		return FmsDecoratorWorkTaskBudget.decoratorCanvasLocation;
	}
	
	protected static FmsDecoratorWorkTaskBudget staticInstance = new FmsDecoratorWorkTaskBudget();
	
	public static FmsDecoratorWorkTaskBudget NO_TASK_POINT_BUDGET = new FmsDecoratorWorkTaskBudget(
			"No Task Point Budget",
			R.string.decorator__task_point_budget__none,
			FmmTribKnQualityNormalizedDescription.NONE,
			R.integer.fmm__decorator__task_budget__none__quality_index,
			DecKanGlDecoratorColor.RED );
	public static FmsDecoratorWorkTaskBudget TASK_POINT_BUDGET_SWAG = new FmsDecoratorWorkTaskBudget(
			"Task Point Budget SWAG",
			R.string.decorator__task_point_budget__swag,
			FmmTribKnQualityNormalizedDescription.SWAG,
			R.integer.fmm__decorator__task_budget__swag__quality_index,
			DecKanGlDecoratorColor.YELLOW );
	public static FmsDecoratorWorkTaskBudget SUGGESTED_TASK_POINT_BUDGET = new FmsDecoratorWorkTaskBudget(
			"Suggested Task Point Budget",
			R.string.decorator__task_point_budget__suggested,
			FmmTribKnQualityNormalizedDescription.SUGGESTED,
			R.integer.fmm__decorator__task_budget__suggested__quality_index,
			DecKanGlDecoratorColor.BLUE );
	public static FmsDecoratorWorkTaskBudget PROPOSED_TASK_POINT_BUDGET = new FmsDecoratorWorkTaskBudget(
			"Proposed Task Point Budget",
			R.string.decorator__task_point_budget__proposed,
			FmmTribKnQualityNormalizedDescription.PROPOSED,
			R.integer.fmm__decorator__task_budget__proposed__quality_index,
			DecKanGlDecoratorColor.PINK );
	public static FmsDecoratorWorkTaskBudget CONFIRMED_TASK_POINT_BUDGET = new FmsDecoratorWorkTaskBudget(
			"Confirmed Task Point Budget",
			R.string.decorator__task_point_budget__confirmed,
			FmmTribKnQualityNormalizedDescription.CONFIRMED,
			R.integer.fmm__decorator__task_budget__confirmed__quality_index,
			DecKanGlDecoratorColor.TRANSPARENT );
	public static FmsDecoratorWorkTaskBudget TASK_POINT_OVER_BUDGET = new FmsDecoratorWorkTaskBudget(
			"Tasks Over Point Budget",
			R.string.decorator__task_point_budget__over_budget,
			FmmTribKnQualityNormalizedDescription.OVER_BUDGET,
			R.integer.fmm__decorator__task_budget__over_budget__quality_index,
			DecKanGlDecoratorColor.ORANGE );
	public static FmsDecoratorWorkTaskBudget UNKNOWN_TASK_POINT_BUDGET_QUALITY = new FmsDecoratorWorkTaskBudget(
			"Unknown Task Point Budget Quality",
			R.string.decorator__task_point_budget__unknown_quality,
			FmmTribKnQualityNormalizedDescription.UNKNOWN,
			R.integer.fmm__decorator__task_budget__unknown_quality__quality_index,
			DecKanGlDecoratorColor.GRAY );
	public static FmsDecoratorWorkTaskBudget TASK_POINT_BUDGET_QUALITY_NOT_APPLICABLE = new FmsDecoratorWorkTaskBudget(
			"Task Point Budget Quality Not Applicable",
			R.string.decorator__task_point_budget__not_applicable,
			FmmTribKnQualityNormalizedDescription.NOT_APPLICABLE,
			R.integer.fmm__decorator__task_budget__not_applicable__quality_index,
			DecKanGlDecoratorColor.TRANSPARENT );
	public static FmsDecoratorWorkTaskBudget TASK_POINT_BUDGET_QUALITY_NOT_ENABLED = new FmsDecoratorWorkTaskBudget(
			"Task Point Budget Quality Not Enabled",
			R.string.decorator__task_point_budget__not_enabled,
			FmmTribKnQualityNormalizedDescription.NOT_ENABLED,
			R.integer.fmm__decorator__task_budget__not_enabled__quality_index,
			DecKanGlDecoratorColor.DISABLED );
	
	protected static ArrayList<DecKanGlDecoratorEnumeration> valuesList =
			new ArrayList<DecKanGlDecoratorEnumeration>();
	static {
		valuesList.add(NO_TASK_POINT_BUDGET);
		valuesList.add(TASK_POINT_BUDGET_SWAG);
		valuesList.add(SUGGESTED_TASK_POINT_BUDGET);
		valuesList.add(PROPOSED_TASK_POINT_BUDGET);
		valuesList.add(CONFIRMED_TASK_POINT_BUDGET);
		valuesList.add(TASK_POINT_OVER_BUDGET);
		valuesList.add(UNKNOWN_TASK_POINT_BUDGET_QUALITY);
		valuesList.add(TASK_POINT_BUDGET_QUALITY_NOT_APPLICABLE);
		valuesList.add(TASK_POINT_BUDGET_QUALITY_NOT_ENABLED);
	}
	
	protected static HashMap<String, FmsDecoratorWorkTaskBudget> nameMap = new HashMap<String, FmsDecoratorWorkTaskBudget>();
	static {
		for(DecKanGlDecoratorEnumeration theDecKanGlDecoratorEnumeration : valuesList) {
			FmsDecoratorWorkTaskBudget.nameMap.put(theDecKanGlDecoratorEnumeration.getName(), (FmsDecoratorWorkTaskBudget) theDecKanGlDecoratorEnumeration);
		}
	}
	
	public static FmsDecoratorWorkTaskBudget getObjectForName(String aName) {
		return FmsDecoratorWorkTaskBudget.nameMap.get(aName);
	}

	private FmsDecoratorWorkTaskBudget() {
		super();
	}
	
	public static FmsDecoratorWorkTaskBudget getStaticInstance() {
		return staticInstance;
	}
	
	public FmsDecoratorWorkTaskBudget(
			String aName,
			int aDescriptionResourceId,
			FmmTribKnQualityNormalizedDescription aNormalizedDescription,
			int aTribKnQualityIndexResourceId,
			DecKanGlDecoratorColor aDecKanGlDecoratorColor ) {
		super(
				aName,
				GcgApplication.getAppResources().getString(aDescriptionResourceId),
				aNormalizedDescription,
				GcgApplication.getAppResources().getInteger(aTribKnQualityIndexResourceId),
				aDecKanGlDecoratorColor);
	}
	
	public static List<DecKanGlDecoratorEnumeration> values() {
		return valuesList;
	}

	@Override
	public String getLabelText() {
		return GcgApplication.getContext().getResources().getString(R.string.tribkn_quality_metric__task_points_budget);
	}

	@Override
	public Drawable getLabelDrawable() {
		return GcgApplication.getContext().getResources().getDrawable(R.drawable.tribkn_quality_metric__work_task_budget);
	}

	@Override
	public int getLabelDrawableResourceId() {
		return R.drawable.tribkn_quality_metric__work_task_budget;
	}

}
