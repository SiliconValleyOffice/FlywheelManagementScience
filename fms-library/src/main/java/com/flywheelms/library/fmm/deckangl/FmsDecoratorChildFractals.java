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

public class FmsDecoratorChildFractals extends DecKanGlDecoratorEnumeration {
	
	protected static String qualityMetricName = GcgApplication.getAppResources().getString(R.string.tribkn_quality_metric__child_fractals);
	protected static String qualityMetricDescription = GcgApplication.getAppResources().getString(R.string.tribkn_quality_metric__child_fractals__description);
	protected static Drawable qualityMetricDrawable = GcgApplication.getAppResources().getDrawable(R.drawable.tribkn_quality_metric__child_fractals);
	protected static DecKanGlDecoratorCanvasLocation decoratorCanvasLocation = DecKanGlDecoratorCanvasLocation.right_zone_TOP;
	
	@Override
	public String getQualityMetricName() {
		return FmsDecoratorChildFractals.qualityMetricName;
	}
	
	@Override
	public String getQualityMetricDescription() {
		return FmsDecoratorChildFractals.qualityMetricDescription;
	}
	
	@Override
	public Drawable getQualityMetricDrawable() {
		return FmsDecoratorChildFractals.qualityMetricDrawable;
	}
	
	@Override
	public DecKanGlDecoratorCanvasLocation getDecoratorCanvasLocation() {
		return FmsDecoratorChildFractals.decoratorCanvasLocation;
	}
	
	protected static FmsDecoratorChildFractals staticInstance = new FmsDecoratorChildFractals();
	
	public static FmsDecoratorChildFractals NO_CHILD_FRACTALS = new FmsDecoratorChildFractals(
			"No Child Fractals",
			R.string.decorator__child_fractals__none,
			FmmTribKnQualityNormalizedDescription.NONE,
			R.integer.fmm__decorator__child_fractals__none__quality_index,
			DecKanGlDecoratorColor.RED );
	public static FmsDecoratorChildFractals CHILD_FRACTALS_SWAG = new FmsDecoratorChildFractals(
			"Child Fractals SWAG",
			R.string.decorator__child_fractals__swag,
			FmmTribKnQualityNormalizedDescription.SWAG,
			R.integer.fmm__decorator__child_fractals__swag__quality_index,
			DecKanGlDecoratorColor.YELLOW );
	public static FmsDecoratorChildFractals SUGGESTED_CHILD_FRACTALS = new FmsDecoratorChildFractals(
			"Suggested Child Fractals",
			R.string.decorator__child_fractals__suggested,
			FmmTribKnQualityNormalizedDescription.SUGGESTED,
			R.integer.fmm__decorator__child_fractals__suggested__quality_index,
			DecKanGlDecoratorColor.BLUE );
	public static FmsDecoratorChildFractals PROPOSED_CHILD_FRACTALS = new FmsDecoratorChildFractals(
			"Proposed Child Fractals",
			R.string.decorator__child_fractals__proposed,
			FmmTribKnQualityNormalizedDescription.PROPOSED,
			R.integer.fmm__decorator__child_fractals__proposed__quality_index,
			DecKanGlDecoratorColor.PINK );
	public static FmsDecoratorChildFractals CONFIRMED_CHILD_FRACTALS = new FmsDecoratorChildFractals(
			"Confirmed Child Fractals",
			R.string.decorator__child_fractals__confirmed,
			FmmTribKnQualityNormalizedDescription.CONFIRMED,
			R.integer.fmm__decorator__child_fractals__confirmed__quality_index,
			DecKanGlDecoratorColor.TRANSPARENT );
	public static FmsDecoratorChildFractals CONFIRMED_EXCESSIVE_CHILD_FRACTALS = new FmsDecoratorChildFractals(
			"Complete Child Fractals",
			R.string.decorator__child_fractals__excessive,
			FmmTribKnQualityNormalizedDescription.EXCESSIVE,
			R.integer.fmm__decorator__child_fractals__excessive__quality_index,
			DecKanGlDecoratorColor.ORANGE );
	public static FmsDecoratorChildFractals UNKNOWN_CHILD_FRACTALS_QUALITY = new FmsDecoratorChildFractals(
			"Unknown Child Fractal Quality",
			R.string.decorator__child_fractals__unknown_quality,
			FmmTribKnQualityNormalizedDescription.UNKNOWN,
			R.integer.fmm__decorator__child_fractals__unknown_quality__quality_index,
			DecKanGlDecoratorColor.GRAY );
	public static FmsDecoratorChildFractals CHILD_FRACTALS_QUALITY_NOT_APPLICABLE = new FmsDecoratorChildFractals(
			"Child Fractals Not Applicable",
			R.string.decorator__child_fractals__not_applicable,
			FmmTribKnQualityNormalizedDescription.UNKNOWN,
			R.integer.fmm__decorator__child_fractals__not_applicable__quality_index,
			DecKanGlDecoratorColor.TRANSPARENT );
	public static FmsDecoratorChildFractals CHILD_FRACTAL_QUALITY_NOT_ENABLED = new FmsDecoratorChildFractals(
			"Child Fractal Quality Not Enabled",
			R.string.decorator__child_fractals__not_enabled,
			FmmTribKnQualityNormalizedDescription.NOT_ENABLED,
			R.integer.fmm__decorator__child_fractals__not_enabled__quality_index,
			DecKanGlDecoratorColor.DISABLED );
	
	protected static ArrayList<DecKanGlDecoratorEnumeration> valuesList =
			new ArrayList<DecKanGlDecoratorEnumeration>();
	static {
		FmsDecoratorChildFractals.valuesList.add(NO_CHILD_FRACTALS);
		FmsDecoratorChildFractals.valuesList.add(CHILD_FRACTALS_SWAG);
		FmsDecoratorChildFractals.valuesList.add(SUGGESTED_CHILD_FRACTALS);
		FmsDecoratorChildFractals.valuesList.add(PROPOSED_CHILD_FRACTALS);
		FmsDecoratorChildFractals.valuesList.add(CONFIRMED_CHILD_FRACTALS);
		FmsDecoratorChildFractals.valuesList.add(CONFIRMED_EXCESSIVE_CHILD_FRACTALS);
		FmsDecoratorChildFractals.valuesList.add(UNKNOWN_CHILD_FRACTALS_QUALITY);
		FmsDecoratorChildFractals.valuesList.add(CHILD_FRACTALS_QUALITY_NOT_APPLICABLE);
		FmsDecoratorChildFractals.valuesList.add(CHILD_FRACTAL_QUALITY_NOT_ENABLED);
	}
	
	protected static HashMap<String, FmsDecoratorChildFractals> nameMap = new HashMap<String, FmsDecoratorChildFractals>();
	static {
		for(DecKanGlDecoratorEnumeration theDecKanGlDecoratorEnumeration : valuesList) {
			FmsDecoratorChildFractals.nameMap.put(theDecKanGlDecoratorEnumeration.getName(), (FmsDecoratorChildFractals) theDecKanGlDecoratorEnumeration);
		}
	}
	
	public static FmsDecoratorChildFractals getObjectForName(String aName) {
		return FmsDecoratorChildFractals.nameMap.get(aName);
	}
	
	private FmsDecoratorChildFractals() {
		super();
	}
	
	public static FmsDecoratorChildFractals getStaticInstance() {
		return staticInstance;
	}
	
	public FmsDecoratorChildFractals(
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
		return GcgApplication.getContext().getResources().getString(R.string.tribkn_quality_metric__child_fractals);
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