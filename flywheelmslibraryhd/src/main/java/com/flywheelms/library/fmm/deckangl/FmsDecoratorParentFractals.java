/* @(#)FmsDecoratorParentFractals.java
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.graphics.drawable.Drawable;

import com.flywheelms.library.R;
import com.flywheelms.library.deckangl.enumerator.DecKanGlDecoratorCanvasLocation;
import com.flywheelms.library.deckangl.enumerator.DecKanGlDecoratorColor;
import com.flywheelms.library.deckangl.glyph.DecKanGlDecoratorEnumeration;
import com.flywheelms.library.fmm.node.impl.enumerator.TribKnQualityNormalizedDescription;
import com.flywheelms.library.gcg.GcgApplication;

public class FmsDecoratorParentFractals extends DecKanGlDecoratorEnumeration {
	
	protected static String qualityMetricName = GcgApplication.getAppResources().getString(R.string.tribkn_quality_metric__parent_fractals);
	protected static String qualityMetricDescription = GcgApplication.getAppResources().getString(R.string.tribkn_quality_metric__parent_fractals__description);
	protected static Drawable qualityMetricDrawable = GcgApplication.getAppResources().getDrawable(R.drawable.tribkn_quality_metric__parent_fractals);
	protected static DecKanGlDecoratorCanvasLocation decoratorCanvasLocation = DecKanGlDecoratorCanvasLocation.left_zone_TOP;
	
	@Override
	public String getQualityMetricName() {
		return FmsDecoratorParentFractals.qualityMetricName;
	}
	
	@Override
	public String getQualityMetricDescription() {
		return FmsDecoratorParentFractals.qualityMetricDescription;
	}
	
	@Override
	public Drawable getQualityMetricDrawable() {
		return FmsDecoratorParentFractals.qualityMetricDrawable;
	}
	
	@Override
	public DecKanGlDecoratorCanvasLocation getDecoratorCanvasLocation() {
		return FmsDecoratorParentFractals.decoratorCanvasLocation;
	}
	
	protected static FmsDecoratorParentFractals staticInstance = new FmsDecoratorParentFractals();
	
	public static FmsDecoratorParentFractals NO_PARENT_FRACTALS = new FmsDecoratorParentFractals(
			"No Parent Fractals",
			R.string.decorator__parent_fractals__none,
			TribKnQualityNormalizedDescription.NONE,
			R.integer.decorator__parent_fractals__none__quality_index,
			DecKanGlDecoratorColor.RED );
	public static FmsDecoratorParentFractals PARENT_FRACTALS_SWAG = new FmsDecoratorParentFractals(
			"Parent Fractals SWAG",
			R.string.decorator__parent_fractals__swag,
			TribKnQualityNormalizedDescription.SWAG,
			R.integer.decorator__parent_fractals__swag__quality_index,
			DecKanGlDecoratorColor.YELLOW );
	public static FmsDecoratorParentFractals SUGGESTED_PARENT_FRACTALS = new FmsDecoratorParentFractals(
			"Suggested Parent Fractals",
			R.string.decorator__parent_fractals__suggested,
			TribKnQualityNormalizedDescription.SUGGESTED,
			R.integer.decorator__parent_fractals__suggested__quality_index,
			DecKanGlDecoratorColor.BLUE );
	public static FmsDecoratorParentFractals PROPOSED_PARENT_FRACTALS = new FmsDecoratorParentFractals(
			"Proposed Parent Fractals",
			R.string.decorator__parent_fractals__proposed,
			TribKnQualityNormalizedDescription.PROPOSED,
			R.integer.decorator__parent_fractals__proposed__quality_index,
			DecKanGlDecoratorColor.PINK );
	public static FmsDecoratorParentFractals ONE_CONFIRMED_PARENT_FRACTAL = new FmsDecoratorParentFractals(
			"One Confirmed Parent Fractal",
			R.string.decorator__parent_fractals__one,
			TribKnQualityNormalizedDescription.ONE_SPECIFIED,
			R.integer.decorator__parent_fractals__one__quality_index,	
			DecKanGlDecoratorColor.TRANSPARENT );
	public static FmsDecoratorParentFractals MULTIPLE_CONFIRMED_PARENT_FRACTALS = new FmsDecoratorParentFractals(
			"Multiple Confirmed Parent Fractals",
			R.string.decorator__parent_fractals__multiple,
			TribKnQualityNormalizedDescription.MULTIPLE_SPECIFIED,
			R.integer.decorator__parent_fractals__multiple__quality_index,
			DecKanGlDecoratorColor.ORANGE );
	public static FmsDecoratorParentFractals UNKNOWN_PARENT_FRACTAL_QUALITY = new FmsDecoratorParentFractals(
			"Unknown Parent Fractal Quality",
			R.string.decorator__parent_fractals__unknown_quality,
			TribKnQualityNormalizedDescription.UNKNOWN,
			R.integer.decorator__parent_fractals__unknown_quality__quality_index,
			DecKanGlDecoratorColor.GRAY );
	public static FmsDecoratorParentFractals PARENT_FRACTAL_QUALITY_NOT_APPLICABLE = new FmsDecoratorParentFractals(
			"Parent Fractal Quality Not Applicable",
			R.string.decorator__parent_fractals__not_applicable,
			TribKnQualityNormalizedDescription.NOT_APPLICABLE,
			R.integer.decorator__parent_fractals__not_applicable__quality_index,
			DecKanGlDecoratorColor.TRANSPARENT );
	public static FmsDecoratorParentFractals PARENT_FRACTAL_QUALITY_NOT_ENABLED = new FmsDecoratorParentFractals(
			"Parent Fractal Quality Not Enabled",
			R.string.decorator__parent_fractals__not_enabled,
			TribKnQualityNormalizedDescription.NOT_ENABLED,
			R.integer.decorator__parent_fractals__not_enabled__quality_index,
			DecKanGlDecoratorColor.DISABLED );
	
	protected static ArrayList<DecKanGlDecoratorEnumeration> valuesList =
			new ArrayList<DecKanGlDecoratorEnumeration>();
	static {
		valuesList.add(NO_PARENT_FRACTALS);
		valuesList.add(PARENT_FRACTALS_SWAG);
		valuesList.add(SUGGESTED_PARENT_FRACTALS);
		valuesList.add(PROPOSED_PARENT_FRACTALS);
		valuesList.add(ONE_CONFIRMED_PARENT_FRACTAL);
		valuesList.add(MULTIPLE_CONFIRMED_PARENT_FRACTALS);
		valuesList.add(UNKNOWN_PARENT_FRACTAL_QUALITY);
		valuesList.add(PARENT_FRACTAL_QUALITY_NOT_APPLICABLE);
		valuesList.add(PARENT_FRACTAL_QUALITY_NOT_ENABLED);
	}
	
	protected static HashMap<String, FmsDecoratorParentFractals> nameMap = new HashMap<String, FmsDecoratorParentFractals>();
	static {
		for(DecKanGlDecoratorEnumeration theDecKanGlDecoratorEnumeration : valuesList) {
			FmsDecoratorParentFractals.nameMap.put(theDecKanGlDecoratorEnumeration.getName(), (FmsDecoratorParentFractals) theDecKanGlDecoratorEnumeration);
		}
	}
	
	public static FmsDecoratorParentFractals getObjectForName(String aName) {
		return FmsDecoratorParentFractals.nameMap.get(aName);
	}

	private FmsDecoratorParentFractals() {
		super();
	}
	
	public static FmsDecoratorParentFractals getStaticInstance() {
		return staticInstance;
	}
	
	public FmsDecoratorParentFractals(
			String aName,
			int aDescriptionResourceId,
			TribKnQualityNormalizedDescription aNormalizedDescription,
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
		return GcgApplication.getContext().getResources().getString(R.string.tribkn_quality_metric__parent_fractals);
	}

	@Override
	public Drawable getLabelDrawable() {
		return GcgApplication.getContext().getResources().getDrawable(R.drawable.tribkn_quality_metric__parent_fractals);
	}

	@Override
	public int getLabelDrawableResourceId() {
		return R.drawable.tribkn_quality_metric__parent_fractals;
	}

}
