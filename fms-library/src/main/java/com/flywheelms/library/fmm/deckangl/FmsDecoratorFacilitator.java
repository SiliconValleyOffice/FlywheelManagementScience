/* @(#)FmsDecoratorFacilitator.java
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

import com.flywheelms.library.R;
import com.flywheelms.library.deckangl.enumerator.DecKanGlDecoratorCanvasLocation;
import com.flywheelms.library.deckangl.enumerator.DecKanGlDecoratorColor;
import com.flywheelms.library.deckangl.glyph.DecKanGlDecoratorEnumeration;
import com.flywheelms.library.fmm.node.impl.enumerator.TribKnQualityNormalizedDescription;
import com.flywheelms.library.gcg.GcgApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FmsDecoratorFacilitator extends DecKanGlDecoratorEnumeration {
	
	protected static String qualityMetricName = GcgApplication.getAppResources().getString(R.string.tribkn_quality_metric__facilitator);
	protected static String qualityMetricDescription = GcgApplication.getAppResources().getString(R.string.tribkn_quality_metric__facilitator__description);
	protected static Drawable qualityMetricDrawable = GcgApplication.getAppResources().getDrawable(R.drawable.tribkn_quality_metric__facilitator);
	protected static DecKanGlDecoratorCanvasLocation decoratorCanvasLocation = DecKanGlDecoratorCanvasLocation.top_zone_RIGHT;
	
	@Override
	public String getQualityMetricName() {
		return FmsDecoratorFacilitator.qualityMetricName;
	}
	
	@Override
	public String getQualityMetricDescription() {
		return FmsDecoratorFacilitator.qualityMetricDescription;
	}
	
	@Override
	public Drawable getQualityMetricDrawable() {
		return FmsDecoratorFacilitator.qualityMetricDrawable;
	}
	
	@Override
	public DecKanGlDecoratorCanvasLocation getDecoratorCanvasLocation() {
		return FmsDecoratorFacilitator.decoratorCanvasLocation;
	}
	
	protected static FmsDecoratorFacilitator staticInstance = new FmsDecoratorFacilitator();
	
	public static FmsDecoratorFacilitator NO_FACILITATOR = new FmsDecoratorFacilitator(
			"No Facilitator",
			R.string.decorator__facilitator__no_facilitator,
			TribKnQualityNormalizedDescription.NONE,
			R.integer.decorator__facilitator__no_facilitator__quality_index,
			DecKanGlDecoratorColor.RED );
	public static FmsDecoratorFacilitator INACTIVE_FACILITATOR = new FmsDecoratorFacilitator(
			"Inactive Facilitator",
			R.string.decorator__facilitator__inactive_facilitator,
			TribKnQualityNormalizedDescription.INACTIVE,
			R.integer.decorator__facilitator__inactive_facilitator__quality_index,
			DecKanGlDecoratorColor.ORANGE );
	public static FmsDecoratorFacilitator SUGGESTED_FACILITATOR = new FmsDecoratorFacilitator(
			"Suggested Facilitator",
			R.string.decorator__facilitator__suggested_facilitator,
			TribKnQualityNormalizedDescription.SUGGESTED,
			R.integer.decorator__facilitator__suggested_facilitator__quality_index,
			DecKanGlDecoratorColor.BLUE );
	public static FmsDecoratorFacilitator PROPOSED_FACILITATOR = new FmsDecoratorFacilitator(
			"Proposed Facilitator",
			R.string.decorator__facilitator__proposed_facilitator,
			TribKnQualityNormalizedDescription.PROPOSED,
			R.integer.decorator__facilitator__proposed_facilitator__quality_index,
			DecKanGlDecoratorColor.PINK );
	public static FmsDecoratorFacilitator CONFIRMED_FACILITATOR = new FmsDecoratorFacilitator(
			"Confirmed Facilitator",
			R.string.decorator__facilitator__confirmed,
			TribKnQualityNormalizedDescription.CONFIRMED,
			R.integer.decorator__facilitator__confirmed__quality_index,
			DecKanGlDecoratorColor.TRANSPARENT );
	public static FmsDecoratorFacilitator UNKNOWN_FACILITATOR_QUALITY = new FmsDecoratorFacilitator(
			"Unknown Facilitator Quality",
			R.string.decorator__facilitator__unknown_quality,
			TribKnQualityNormalizedDescription.UNKNOWN,
			R.integer.decorator__facilitator__unknown_quality__quality_index,
			DecKanGlDecoratorColor.GRAY );
	public static FmsDecoratorFacilitator FACILITATOR_QUALITY_NOT_APPLICABLE = new FmsDecoratorFacilitator(
			"Facilitator Quality Not Applicable",
			R.string.decorator__facilitator__not_applicable,
			TribKnQualityNormalizedDescription.NOT_APPLICABLE,
			R.integer.decorator__facilitator__not_applicable__quality_index,
			DecKanGlDecoratorColor.TRANSPARENT );
	public static FmsDecoratorFacilitator FACILITATOR_QUALITY_NOT_ENABLED = new FmsDecoratorFacilitator(
			"Facilitator Quality Not Enabled",
			R.string.decorator__facilitator__not_enabled,
			TribKnQualityNormalizedDescription.NOT_ENABLED,
			R.integer.decorator__facilitator__not_enabled__quality_index,
			DecKanGlDecoratorColor.DISABLED );
	
	protected static ArrayList<DecKanGlDecoratorEnumeration> valuesList =
			new ArrayList<DecKanGlDecoratorEnumeration>();
	static {
		valuesList.add(NO_FACILITATOR);
		valuesList.add(SUGGESTED_FACILITATOR);
		valuesList.add(PROPOSED_FACILITATOR);
		valuesList.add(CONFIRMED_FACILITATOR);
		valuesList.add(INACTIVE_FACILITATOR);
		valuesList.add(UNKNOWN_FACILITATOR_QUALITY);
		valuesList.add(FACILITATOR_QUALITY_NOT_APPLICABLE);
		valuesList.add(FACILITATOR_QUALITY_NOT_ENABLED);
	}
	
	protected static HashMap<String, FmsDecoratorFacilitator> nameMap = new HashMap<String, FmsDecoratorFacilitator>();
	static {
		for(DecKanGlDecoratorEnumeration theDecKanGlDecoratorEnumeration : valuesList) {
			FmsDecoratorFacilitator.nameMap.put(theDecKanGlDecoratorEnumeration.getName(), (FmsDecoratorFacilitator) theDecKanGlDecoratorEnumeration);
		}
	}
	
	public static FmsDecoratorFacilitator getObjectForName(String aName) {
		return FmsDecoratorFacilitator.nameMap.get(aName);
	}

	private FmsDecoratorFacilitator() {
		super();
	}
	public static FmsDecoratorFacilitator getStaticInstance() {
		return staticInstance;
	}
	
	public FmsDecoratorFacilitator(
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
		return GcgApplication.getContext().getResources().getString(R.string.tribkn_quality_metric__facilitator);
	}

	@Override
	public Drawable getLabelDrawable() {
		return GcgApplication.getContext().getResources().getDrawable(R.drawable.tribkn_quality_metric__facilitator);
	}

	@Override
	public int getLabelDrawableResourceId() {
		return R.drawable.tribkn_quality_metric__facilitator;
	}

}
