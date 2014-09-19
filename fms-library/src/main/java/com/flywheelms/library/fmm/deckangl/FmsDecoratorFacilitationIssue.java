/* @(#)FmsDecoratorFacilitationIssue.java
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

public class FmsDecoratorFacilitationIssue extends DecKanGlDecoratorEnumeration {
	
	protected static String qualityMetricName = GcgApplication.getAppResources().getString(R.string.tribkn_quality_metric__facilitation_issue);
	protected static String qualityMetricDescription = GcgApplication.getAppResources().getString(R.string.tribkn_quality_metric__facilitation_issue__description);
	protected static Drawable qualityMetricDrawable = GcgApplication.getAppResources().getDrawable(R.drawable.tribkn_quality_metric__facilitation_issue);
	protected static DecKanGlDecoratorCanvasLocation decoratorCanvasLocation = DecKanGlDecoratorCanvasLocation.top_zone_MIDDLE;
	
	@Override
	public String getQualityMetricName() {
		return FmsDecoratorFacilitationIssue.qualityMetricName;
	}
	
	@Override
	public String getQualityMetricDescription() {
		return FmsDecoratorFacilitationIssue.qualityMetricDescription;
	}
	
	@Override
	public Drawable getQualityMetricDrawable() {
		return FmsDecoratorFacilitationIssue.qualityMetricDrawable;
	}
	
	@Override
	public DecKanGlDecoratorCanvasLocation getDecoratorCanvasLocation() {
		return FmsDecoratorFacilitationIssue.decoratorCanvasLocation;
	}
	
	protected static FmsDecoratorFacilitationIssue staticInstance = new FmsDecoratorFacilitationIssue();
	
	public static FmsDecoratorFacilitationIssue PROPOSED_FACILITATION_ISSUE = new FmsDecoratorFacilitationIssue(
			"Proposed Facilitation Issue",
			R.string.decorator__facilitation_issue__proposed,
			TribKnQualityNormalizedDescription.PROPOSED,
			R.integer.fmm__decorator__facilitation_issue__proposed__quality_index,
			DecKanGlDecoratorColor.PINK );
	public static FmsDecoratorFacilitationIssue CONFIRMED_FACILITATION_ISSUE = new FmsDecoratorFacilitationIssue(
			"Confirmed Facilitation Issue",
			R.string.decorator__facilitation_issue__confirmed,
			TribKnQualityNormalizedDescription.CONFIRMED,
			R.integer.fmm__decorator__facilitation_issue__confirmed__quality_index,
			DecKanGlDecoratorColor.ORANGE );
	public static FmsDecoratorFacilitationIssue NO_FACILITATION_ISSUE = new FmsDecoratorFacilitationIssue(
			"No Facilitation Issue",
			R.string.decorator__facilitation_issue__none,
			TribKnQualityNormalizedDescription.NONE,
			R.integer.fmm__decorator__facilitation_issue__none__quality_index,
			DecKanGlDecoratorColor.TRANSPARENT );
	public static FmsDecoratorFacilitationIssue UNKNOWN_FACILITATION_ISSUE_QUALITY = new FmsDecoratorFacilitationIssue(
			"Unknown Facilitation Issue Quality",
			R.string.decorator__facilitation_issue__unknown_quality,
			TribKnQualityNormalizedDescription.UNKNOWN,
			R.integer.fmm__decorator__facilitation_issue__unknown_quality__quality_index,
			DecKanGlDecoratorColor.GRAY );
	public static FmsDecoratorFacilitationIssue FACILITATION_ISSUE_QUALITY_NOT_APPLICABLE = new FmsDecoratorFacilitationIssue(
			"Facilitation Issue Quality Not Applicable",
			R.string.decorator__facilitation_issue__not_applicable,
			TribKnQualityNormalizedDescription.NOT_APPLICABLE,
			R.integer.fmm__decorator__facilitation_issue__not_applicable__quality_index,
			DecKanGlDecoratorColor.TRANSPARENT );
	public static FmsDecoratorFacilitationIssue FACILITATION_ISSUE_QUALITY_NOT_ENABLED = new FmsDecoratorFacilitationIssue(
			"Facilitation Issue Quality Not Enabled",
			R.string.decorator__facilitation_issue__not_enabled,
			TribKnQualityNormalizedDescription.NOT_ENABLED,
			R.integer.fmm__decorator__facilitation_issue__not_enabled__quality_index,
			DecKanGlDecoratorColor.DISABLED );
	
	protected static ArrayList<DecKanGlDecoratorEnumeration> valuesList =
			new ArrayList<DecKanGlDecoratorEnumeration>();
	static {
		valuesList.add(PROPOSED_FACILITATION_ISSUE);
		valuesList.add(CONFIRMED_FACILITATION_ISSUE);
		valuesList.add(NO_FACILITATION_ISSUE);
		valuesList.add(UNKNOWN_FACILITATION_ISSUE_QUALITY);
		valuesList.add(FACILITATION_ISSUE_QUALITY_NOT_APPLICABLE);
		valuesList.add(FACILITATION_ISSUE_QUALITY_NOT_ENABLED);
	}
	
	protected static HashMap<String, FmsDecoratorFacilitationIssue> nameMap = new HashMap<String, FmsDecoratorFacilitationIssue>();
	static {
		for(DecKanGlDecoratorEnumeration theDecKanGlDecoratorEnumeration : valuesList) {
			FmsDecoratorFacilitationIssue.nameMap.put(theDecKanGlDecoratorEnumeration.getName(), (FmsDecoratorFacilitationIssue) theDecKanGlDecoratorEnumeration);
		}
	}
	
	public static FmsDecoratorFacilitationIssue getObjectForName(String aName) {
		return FmsDecoratorFacilitationIssue.nameMap.get(aName);
	}

	private FmsDecoratorFacilitationIssue() {
		super();
	}
	
	public static FmsDecoratorFacilitationIssue getStaticInstance() {
		return staticInstance;
	}
	
	public FmsDecoratorFacilitationIssue(
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
		return GcgApplication.getContext().getResources().getString(R.string.tribkn_quality_metric__facilitation_issue);
	}

	@Override
	public Drawable getLabelDrawable() {
		return GcgApplication.getContext().getResources().getDrawable(R.drawable.tribkn_quality_metric__facilitation_issue);
	}

	@Override
	public int getLabelDrawableResourceId() {
		return R.drawable.tribkn_quality_metric__facilitation_issue;
	}

}