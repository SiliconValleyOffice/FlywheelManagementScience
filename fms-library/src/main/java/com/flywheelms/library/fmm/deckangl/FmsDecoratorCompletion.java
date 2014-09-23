/* @(#)FmsDecoratorCompletionProgress.java
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

public class FmsDecoratorCompletion extends DecKanGlDecoratorEnumeration {
	
	protected static String qualityMetricName = GcgApplication.getAppResources().getString(R.string.tribkn_quality_metric__completion);
	protected static String qualityMetricDescription = GcgApplication.getAppResources().getString(R.string.tribkn_quality_metric__completion__description);
	protected static Drawable qualityMetricDrawable = GcgApplication.getAppResources().getDrawable(R.drawable.tribkn_quality_metric__completion);
	protected static DecKanGlDecoratorCanvasLocation decoratorCanvasLocation = DecKanGlDecoratorCanvasLocation.bottom_zone_RIGHT;
	
	@Override
	public String getQualityMetricName() {
		return FmsDecoratorCompletion.qualityMetricName;
	}
	
	@Override
	public String getQualityMetricDescription() {
		return FmsDecoratorCompletion.qualityMetricDescription;
	}
	
	@Override
	public Drawable getQualityMetricDrawable() {
		return FmsDecoratorCompletion.qualityMetricDrawable;
	}
	
	@Override
	public DecKanGlDecoratorCanvasLocation getDecoratorCanvasLocation() {
		return FmsDecoratorCompletion.decoratorCanvasLocation;
	}
	
	protected static FmsDecoratorCompletion staticInstance = new FmsDecoratorCompletion();
	
	public static FmsDecoratorCompletion COMPLETION_NOT_SCHEDULED = new FmsDecoratorCompletion(
			"Completion Not Scheduled",
			R.string.decorator__completion_progress__not_scheduled,
			FmmTribKnQualityNormalizedDescription.NOT_SCHEDULED,
			R.integer.fmm__decorator__completion_progress__not_scheduled__quality_index,
			DecKanGlDecoratorColor.RED );
	public static FmsDecoratorCompletion TIMELY_COMPLETION_AT_RISK = new FmsDecoratorCompletion(
			"Timely Completion At Risk",
			R.string.decorator__completion_progress__at_risk,
			FmmTribKnQualityNormalizedDescription.AT_RISK,
			R.integer.fmm__decorator__completion_progress__at_risk__quality_index,
			DecKanGlDecoratorColor.YELLOW );
	public static FmsDecoratorCompletion UNCONFIRMED_COMPLETION = new FmsDecoratorCompletion(
			"Unconfirmed Completion",
			R.string.decorator__completion_progress__unconfirmed,
			FmmTribKnQualityNormalizedDescription.UNCONFIRMED,
			R.integer.fmm__decorator__completion_progress__unconfirmed__quality_index,
			DecKanGlDecoratorColor.PINK );
	public static FmsDecoratorCompletion NO_COMPLETION_ISSUES = new FmsDecoratorCompletion(
			"No Completion Issues",
			R.string.decorator__completion_progress__no_issues,
			FmmTribKnQualityNormalizedDescription.NONE,
			R.integer.fmm__decorator__completion_progress__no_issues__quality_index,
			DecKanGlDecoratorColor.TRANSPARENT );
	public static FmsDecoratorCompletion COMPLETION_PAST_DUE = new FmsDecoratorCompletion(
			"Completion is Past Due",
			R.string.decorator__completion_progress__past_due,
			FmmTribKnQualityNormalizedDescription.PAST_DUE,
			R.integer.fmm__decorator__completion_progress__past_due__quality_index,
			DecKanGlDecoratorColor.ORANGE );
	public static FmsDecoratorCompletion UNKNOWN_COMPLETION_QUALITY = new FmsDecoratorCompletion(
			"Unknown Completion Quality",
			R.string.decorator__completion_progress__unknown_quality,
			FmmTribKnQualityNormalizedDescription.UNKNOWN,
			R.integer.fmm__decorator__completion_progress__unknown_quality__quality_index,
			DecKanGlDecoratorColor.GRAY );
	public static FmsDecoratorCompletion COMPLETION_QUALITY_NOT_APPLICABLE = new FmsDecoratorCompletion(
			"Completion Quality Not Applicable",
			R.string.decorator__completion_progress__not_applicable,
			FmmTribKnQualityNormalizedDescription.NOT_APPLICABLE,
			R.integer.fmm__decorator__completion_progress__not_applicable__quality_index,
			DecKanGlDecoratorColor.TRANSPARENT );
	public static FmsDecoratorCompletion COMPLETION_QUALITY_NOT_ENABLED = new FmsDecoratorCompletion(
			"Completion Quality Not Enabled",
			R.string.decorator__completion_progress__not_enabled,
			FmmTribKnQualityNormalizedDescription.NOT_ENABLED,
			R.integer.fmm__decorator__completion_progress__not_enabled__quality_index,
			DecKanGlDecoratorColor.DISABLED );
	
	protected static ArrayList<DecKanGlDecoratorEnumeration> valuesList =
			new ArrayList<DecKanGlDecoratorEnumeration>();
	static {
		valuesList.add(COMPLETION_NOT_SCHEDULED);
		valuesList.add(TIMELY_COMPLETION_AT_RISK);
		valuesList.add(UNCONFIRMED_COMPLETION);
		valuesList.add(NO_COMPLETION_ISSUES);
		valuesList.add(COMPLETION_PAST_DUE);
		valuesList.add(UNKNOWN_COMPLETION_QUALITY);
		valuesList.add(COMPLETION_QUALITY_NOT_APPLICABLE);
		valuesList.add(COMPLETION_QUALITY_NOT_ENABLED);
	}
	
	protected static HashMap<String, FmsDecoratorCompletion> nameMap = new HashMap<String, FmsDecoratorCompletion>();
	static {
		for(DecKanGlDecoratorEnumeration theDecKanGlDecoratorEnumeration : valuesList) {
			FmsDecoratorCompletion.nameMap.put(theDecKanGlDecoratorEnumeration.getName(), (FmsDecoratorCompletion) theDecKanGlDecoratorEnumeration);
		}
	}
	
	public static FmsDecoratorCompletion getObjectForName(String aName) {
		return FmsDecoratorCompletion.nameMap.get(aName);
	}
	
	private FmsDecoratorCompletion() {
		super();
	}
	
	public static FmsDecoratorCompletion getStaticInstance() {
		return staticInstance;
	}
	
	public FmsDecoratorCompletion(
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
		return GcgApplication.getContext().getResources().getString(R.string.tribkn_quality_metric__completion);
	}

	@Override
	public Drawable getLabelDrawable() {
		return GcgApplication.getContext().getResources().getDrawable(R.drawable.tribkn_quality_metric__completion);
	}

	@Override
	public int getLabelDrawableResourceId() {
		return R.drawable.tribkn_quality_metric__completion;
	}

}