/* @(#)FmsDecoratorGovernance.java
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
import com.flywheelms.library.fmm.node.impl.enumerator.FmmTribKnQualityNormalizedDescription;
import com.flywheelms.library.gcg.GcgApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FmsDecoratorGovernance extends DecKanGlDecoratorEnumeration {
	
	protected static String qualityMetricName = GcgApplication.getAppResources().getString(R.string.tribkn_quality_metric__governance);
	protected static String qualityMetricDescription = GcgApplication.getAppResources().getString(R.string.tribkn_quality_metric__governance__description);
	protected static Drawable qualityMetricDrawable = GcgApplication.getAppResources().getDrawable(R.drawable.tribkn_quality_metric__governance);
	protected static DecKanGlDecoratorCanvasLocation decoratorCanvasLocation = DecKanGlDecoratorCanvasLocation.top_zone_LEFT;
	
	@Override
	public String getQualityMetricName() {
		return FmsDecoratorGovernance.qualityMetricName;
	}
	
	@Override
	public String getQualityMetricDescription() {
		return FmsDecoratorGovernance.qualityMetricDescription;
	}
	
	@Override
	public Drawable getQualityMetricDrawable() {
		return FmsDecoratorGovernance.qualityMetricDrawable;
	}
	
	@Override
	public DecKanGlDecoratorCanvasLocation getDecoratorCanvasLocation() {
		return FmsDecoratorGovernance.decoratorCanvasLocation;
	}
	
	protected static FmsDecoratorGovernance staticInstance = new FmsDecoratorGovernance();
	
	public static FmsDecoratorGovernance NO_GOVERNANCE = new FmsDecoratorGovernance(
			"No Governance",
			R.string.decorator__governance__none,
			FmmTribKnQualityNormalizedDescription.NONE,
			R.integer.fmm__decorator__governance__none__quality_index,
			DecKanGlDecoratorColor.RED );
	public static FmsDecoratorGovernance SUGGESTED_GOVERNANCE = new FmsDecoratorGovernance(
			"Suggested Governance",
			R.string.decorator__governance__suggested,
			FmmTribKnQualityNormalizedDescription.SUGGESTED,
			R.integer.fmm__decorator__governance__suggested__quality_index,
			DecKanGlDecoratorColor.BLUE );
	public static FmsDecoratorGovernance PROPOSED_GOVERNANCE = new FmsDecoratorGovernance(
			"Proposed Governance",
			R.string.decorator__governance__proposed,
			FmmTribKnQualityNormalizedDescription.PROPOSED,
			R.integer.fmm__decorator__governance__proposed__quality_index,
			DecKanGlDecoratorColor.PINK );
	public static FmsDecoratorGovernance INCOMPLETE_CONFIRMED_GOVERNANCE = new FmsDecoratorGovernance(
			"Incomplete Governance",
			R.string.decorator__governance__incomplete,
			FmmTribKnQualityNormalizedDescription.INCOMPLETE,
			R.integer.fmm__decorator__governance__incomplete__quality_index,
			DecKanGlDecoratorColor.ORANGE );
	public static FmsDecoratorGovernance CONFIRMED_GOVERNANCE = new FmsDecoratorGovernance(
			"Confirmed Governance",
			R.string.decorator__governance__confirmed,
			FmmTribKnQualityNormalizedDescription.CONFIRMED,
			R.integer.fmm__decorator__governance__confirmed__quality_index,
			DecKanGlDecoratorColor.TRANSPARENT );
	public static FmsDecoratorGovernance INACTIVE_CONFIRMED_GOVERNANCE = new FmsDecoratorGovernance(
			"Inactive Confirmed Governance",
			R.string.decorator__governance__inactive,
			FmmTribKnQualityNormalizedDescription.INACTIVE,
			R.integer.fmm__decorator__governance__inactive__quality_index,
			DecKanGlDecoratorColor.BLUE );
	public static FmsDecoratorGovernance UNKNOWN_GOVERNANCE_QUALITY = new FmsDecoratorGovernance(
			"Unknown Governance Quality",
			R.string.decorator__governance__unknown_quality,
			FmmTribKnQualityNormalizedDescription.UNKNOWN,
			R.integer.fmm__decorator__governance__unknown_quality__quality_index,
			DecKanGlDecoratorColor.GRAY );
	public static FmsDecoratorGovernance GOVERNANCE_QUALITY_NOT_APPLICABLE = new FmsDecoratorGovernance(
			"Governance Quality Not Applicable",
			R.string.decorator__governance__not_applicable,
			FmmTribKnQualityNormalizedDescription.NOT_APPLICABLE,
			R.integer.fmm__decorator__governance__not_applicable__quality_index,
			DecKanGlDecoratorColor.TRANSPARENT );
	public static FmsDecoratorGovernance GOVERNANCE_QUALITY_NOT_ENABLED = new FmsDecoratorGovernance(
			"Governance Quality Not Enabled",
			R.string.decorator__governance__not_enabled,
			FmmTribKnQualityNormalizedDescription.NOT_ENABLED,
			R.integer.fmm__decorator__governance__not_enabled__quality_index,
			DecKanGlDecoratorColor.DISABLED );
	
	protected static ArrayList<DecKanGlDecoratorEnumeration> valuesList =
			new ArrayList<DecKanGlDecoratorEnumeration>();
	static {
		valuesList.add(NO_GOVERNANCE);
		valuesList.add(SUGGESTED_GOVERNANCE);
		valuesList.add(PROPOSED_GOVERNANCE);
		valuesList.add(INCOMPLETE_CONFIRMED_GOVERNANCE);
		valuesList.add(CONFIRMED_GOVERNANCE);
		valuesList.add(INACTIVE_CONFIRMED_GOVERNANCE);
		valuesList.add(UNKNOWN_GOVERNANCE_QUALITY);
		valuesList.add(GOVERNANCE_QUALITY_NOT_APPLICABLE);
		valuesList.add(GOVERNANCE_QUALITY_NOT_ENABLED);
	}
	
	protected static HashMap<String, FmsDecoratorGovernance> nameMap = new HashMap<String, FmsDecoratorGovernance>();
	static {
		for(DecKanGlDecoratorEnumeration theDecKanGlDecoratorEnumeration : valuesList) {
			FmsDecoratorGovernance.nameMap.put(theDecKanGlDecoratorEnumeration.getName(), (FmsDecoratorGovernance) theDecKanGlDecoratorEnumeration);
		}
	}
	
	public static FmsDecoratorGovernance getObjectForName(String aName) {
		return FmsDecoratorGovernance.nameMap.get(aName);
	}
	
	private FmsDecoratorGovernance() {
		super();
	}
	
	public static FmsDecoratorGovernance getStaticInstance() {
		return staticInstance;
	}
	
	public FmsDecoratorGovernance(
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
		return GcgApplication.getContext().getResources().getString(R.string.tribkn_quality_metric__governance);
	}

	@Override
	public Drawable getLabelDrawable() {
		return GcgApplication.getContext().getResources().getDrawable(R.drawable.tribkn_quality_metric__governance);
	}

	@Override
	public int getLabelDrawableResourceId() {
		return R.drawable.tribkn_quality_metric__governance;
	}

}