/* @(#)FmsDecoratorChildFractals.java
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

public class FmsDecoratorWorkBreakdown extends DecKanGlDecoratorEnumeration {
	
	protected static String qualityMetricName = GcgApplication.getAppResources().getString(R.string.tribkn_quality_metric__work_breakdown);
	protected static String qualityMetricDescription = GcgApplication.getAppResources().getString(R.string.tribkn_quality_metric__work_breakdown__description);
	protected static Drawable qualityMetricDrawable = GcgApplication.getAppResources().getDrawable(R.drawable.tribkn_quality_metric__child_fractals);
	protected static DecKanGlDecoratorCanvasLocation decoratorCanvasLocation = DecKanGlDecoratorCanvasLocation.right_zone_TOP;
	
	@Override
	public String getQualityMetricName() {
		return FmsDecoratorWorkBreakdown.qualityMetricName;
	}
	
	@Override
	public String getQualityMetricDescription() {
		return FmsDecoratorWorkBreakdown.qualityMetricDescription;
	}
	
	@Override
	public Drawable getQualityMetricDrawable() {
		return FmsDecoratorWorkBreakdown.qualityMetricDrawable;
	}
	
	@Override
	public DecKanGlDecoratorCanvasLocation getDecoratorCanvasLocation() {
		return FmsDecoratorWorkBreakdown.decoratorCanvasLocation;
	}
	
	protected static FmsDecoratorWorkBreakdown staticInstance = new FmsDecoratorWorkBreakdown();
	
	public static FmsDecoratorWorkBreakdown NO_CHILD_FRACTALS = new FmsDecoratorWorkBreakdown(
			"No Work Breakdown",
			R.string.decorator__work_breakdown__none,
			FmmTribKnQualityNormalizedDescription.NONE,
			R.integer.fmm__decorator__child_fractals__none__quality_index,
			DecKanGlDecoratorColor.RED );
	public static FmsDecoratorWorkBreakdown CHILD_FRACTALS_SWAG = new FmsDecoratorWorkBreakdown(
			"Work Breakdown SWAG",
			R.string.decorator__work_breakdown__swag,
			FmmTribKnQualityNormalizedDescription.SWAG,
			R.integer.fmm__decorator__child_fractals__swag__quality_index,
			DecKanGlDecoratorColor.YELLOW );
	public static FmsDecoratorWorkBreakdown SUGGESTED_CHILD_FRACTALS = new FmsDecoratorWorkBreakdown(
			"Suggested Work Breakdown",
			R.string.decorator__work_breakdown__suggested,
			FmmTribKnQualityNormalizedDescription.SUGGESTED,
			R.integer.fmm__decorator__child_fractals__suggested__quality_index,
			DecKanGlDecoratorColor.BLUE );
	public static FmsDecoratorWorkBreakdown PROPOSED_CHILD_FRACTALS = new FmsDecoratorWorkBreakdown(
			"Proposed Work Breakdown",
			R.string.decorator__work_breakdown__proposed,
			FmmTribKnQualityNormalizedDescription.PROPOSED,
			R.integer.fmm__decorator__child_fractals__proposed__quality_index,
			DecKanGlDecoratorColor.PINK );
	public static FmsDecoratorWorkBreakdown CONFIRMED_CHILD_FRACTALS = new FmsDecoratorWorkBreakdown(
			"Confirmed Work Breakdown",
			R.string.decorator__work_breakdown__confirmed,
			FmmTribKnQualityNormalizedDescription.CONFIRMED,
			R.integer.fmm__decorator__child_fractals__confirmed__quality_index,
			DecKanGlDecoratorColor.TRANSPARENT );
	public static FmsDecoratorWorkBreakdown CONFIRMED_EXCESSIVE_CHILD_FRACTALS = new FmsDecoratorWorkBreakdown(
			"Complete Work Breakdown",
			R.string.decorator__work_breakdown__excessive,
			FmmTribKnQualityNormalizedDescription.EXCESSIVE,
			R.integer.fmm__decorator__child_fractals__excessive__quality_index,
			DecKanGlDecoratorColor.ORANGE );
	public static FmsDecoratorWorkBreakdown UNKNOWN_CHILD_FRACTALS_QUALITY = new FmsDecoratorWorkBreakdown(
			"Unknown Work Breakdown Quality",
			R.string.decorator__work_breakdown__unknown_quality,
			FmmTribKnQualityNormalizedDescription.UNKNOWN,
			R.integer.fmm__decorator__child_fractals__unknown_quality__quality_index,
			DecKanGlDecoratorColor.GRAY );
	public static FmsDecoratorWorkBreakdown CHILD_FRACTALS_QUALITY_NOT_APPLICABLE = new FmsDecoratorWorkBreakdown(
			"Work Breakdown Not Applicable",
			R.string.decorator__work_breakdown__not_applicable,
			FmmTribKnQualityNormalizedDescription.UNKNOWN,
			R.integer.fmm__decorator__child_fractals__not_applicable__quality_index,
			DecKanGlDecoratorColor.TRANSPARENT );
	public static FmsDecoratorWorkBreakdown CHILD_FRACTAL_QUALITY_NOT_ENABLED = new FmsDecoratorWorkBreakdown(
			"Work Breakdown Quality Not Enabled",
			R.string.decorator__work_breakdown__not_enabled,
			FmmTribKnQualityNormalizedDescription.NOT_ENABLED,
			R.integer.fmm__decorator__child_fractals__not_enabled__quality_index,
			DecKanGlDecoratorColor.DISABLED );
	
	protected static ArrayList<DecKanGlDecoratorEnumeration> valuesList =
			new ArrayList<DecKanGlDecoratorEnumeration>();
	static {
		FmsDecoratorWorkBreakdown.valuesList.add(NO_CHILD_FRACTALS);
		FmsDecoratorWorkBreakdown.valuesList.add(CHILD_FRACTALS_SWAG);
		FmsDecoratorWorkBreakdown.valuesList.add(SUGGESTED_CHILD_FRACTALS);
		FmsDecoratorWorkBreakdown.valuesList.add(PROPOSED_CHILD_FRACTALS);
		FmsDecoratorWorkBreakdown.valuesList.add(CONFIRMED_CHILD_FRACTALS);
		FmsDecoratorWorkBreakdown.valuesList.add(CONFIRMED_EXCESSIVE_CHILD_FRACTALS);
		FmsDecoratorWorkBreakdown.valuesList.add(UNKNOWN_CHILD_FRACTALS_QUALITY);
		FmsDecoratorWorkBreakdown.valuesList.add(CHILD_FRACTALS_QUALITY_NOT_APPLICABLE);
		FmsDecoratorWorkBreakdown.valuesList.add(CHILD_FRACTAL_QUALITY_NOT_ENABLED);
	}
	
	protected static HashMap<String, FmsDecoratorWorkBreakdown> nameMap = new HashMap<String, FmsDecoratorWorkBreakdown>();
	static {
		for(DecKanGlDecoratorEnumeration theDecKanGlDecoratorEnumeration : valuesList) {
			FmsDecoratorWorkBreakdown.nameMap.put(theDecKanGlDecoratorEnumeration.getName(), (FmsDecoratorWorkBreakdown) theDecKanGlDecoratorEnumeration);
		}
	}
	
	public static FmsDecoratorWorkBreakdown getObjectForName(String aName) {
		return FmsDecoratorWorkBreakdown.nameMap.get(aName);
	}
	
	private FmsDecoratorWorkBreakdown() {
		super();
	}
	
	public static FmsDecoratorWorkBreakdown getStaticInstance() {
		return staticInstance;
	}
	
	public FmsDecoratorWorkBreakdown(
            String aName,
            int aDescriptionResourceId,
            FmmTribKnQualityNormalizedDescription aNormalizedDescription,
            int aTribKnQualityIndexResourceId,
            DecKanGlDecoratorColor aDecKanGlDecoratorColor) {
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
		return GcgApplication.getContext().getResources().getString(R.string.tribkn_quality_metric__work_breakdown);
	}

	@Override
	public Drawable getLabelDrawable() {
		return GcgApplication.getContext().getResources().getDrawable(R.drawable.tribkn_quality_metric__child_fractals);
	}

	@Override
	public int getLabelDrawableResourceId() {
		return R.drawable.tribkn_quality_metric__child_fractals;
	}

}