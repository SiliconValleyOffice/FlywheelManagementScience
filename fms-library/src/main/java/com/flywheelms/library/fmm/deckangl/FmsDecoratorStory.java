/* @(#)FmsDecoratorStory.java
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

public class FmsDecoratorStory extends DecKanGlDecoratorEnumeration {
	
	protected static String qualityMetricName = GcgApplication.getAppResources().getString(R.string.tribkn_quality_metric__story);
	protected static String qualityMetricDescription = GcgApplication.getAppResources().getString(R.string.tribkn_quality_metric__story__description);
	protected static Drawable qualityMetricDrawable = GcgApplication.getAppResources().getDrawable(R.drawable.tribkn_quality_metric__story);
	protected static DecKanGlDecoratorCanvasLocation decoratorCanvasLocation = DecKanGlDecoratorCanvasLocation.bottom_zone_LEFT;
	
	@Override
	public String getQualityMetricName() {
		return FmsDecoratorStory.qualityMetricName;
	}
	
	@Override
	public String getQualityMetricDescription() {
		return FmsDecoratorStory.qualityMetricDescription;
	}
	
	@Override
	public Drawable getQualityMetricDrawable() {
		return FmsDecoratorStory.qualityMetricDrawable;
	}
	
	@Override
	public DecKanGlDecoratorCanvasLocation getDecoratorCanvasLocation() {
		return FmsDecoratorStory.decoratorCanvasLocation;
	}
	
	protected static FmsDecoratorStory staticInstance = new FmsDecoratorStory();
	
	public static FmsDecoratorStory NO_STORY = new FmsDecoratorStory(
			"No Story",
			R.string.decorator__story__none,
			FmmTribKnQualityNormalizedDescription.NONE,
			R.integer.fmm__fmm__decorator__story__none__quality_index,
			DecKanGlDecoratorColor.RED );
	public static FmsDecoratorStory STORY_SWAG = new FmsDecoratorStory(
			"Story SWAG",
			R.string.decorator__story__swag,
			FmmTribKnQualityNormalizedDescription.SWAG,
			R.integer.fmm__decorator__story__swag__quality_index,
			DecKanGlDecoratorColor.YELLOW );
	public static FmsDecoratorStory SUGGESTED_STORY = new FmsDecoratorStory(
			"Suggested Story",
			R.string.decorator__story__suggested,
			FmmTribKnQualityNormalizedDescription.SUGGESTED,
			R.integer.fmm__decorator__story__suggested__quality_index,
			DecKanGlDecoratorColor.BLUE );
	public static FmsDecoratorStory PROPOSED_STORY = new FmsDecoratorStory(
			"Proposed Story",
			R.string.decorator__story__proposed,
			FmmTribKnQualityNormalizedDescription.PROPOSED,
			R.integer.fmm__decorator__story__proposed__quality_index,
			DecKanGlDecoratorColor.PINK );
	public static FmsDecoratorStory CONFIRMED_STORY = new FmsDecoratorStory(
			"Confirmed Story",
			R.string.decorator__story__confirmed,
			FmmTribKnQualityNormalizedDescription.CONFIRMED,
			R.integer.fmm__decorator__story__confirmed__quality_index,
			DecKanGlDecoratorColor.TRANSPARENT );
	public static FmsDecoratorStory MODIFIED_SINCE_CONFIRMED_STORY = new FmsDecoratorStory(
			"Story Modified Since Confirmed",
			R.string.decorator__story__modified_since_confirmed,
			FmmTribKnQualityNormalizedDescription.MODIFIED_SINCE_CONFIRMED,
			R.integer.fmm__decorator__story__modified_since_confirmed__quality_index,
			DecKanGlDecoratorColor.ORANGE );
	public static FmsDecoratorStory UNKNOWN_STORY_QUALITY = new FmsDecoratorStory(
			"Unknown Story Quality",
			R.string.decorator__story__unknown_quality,
			FmmTribKnQualityNormalizedDescription.UNKNOWN,
			R.integer.fmm__decorator__story__unknown_quality__quality_index,
			DecKanGlDecoratorColor.GRAY );
	public static FmsDecoratorStory STORY_QUALITY_NOT_APPLICABLE = new FmsDecoratorStory(
			"Story Quality Not Applicable",
			R.string.decorator__story__not_applicable,
			FmmTribKnQualityNormalizedDescription.NOT_APPLICABLE,
			R.integer.fmm__decorator__story__not_applicable__quality_index,
			DecKanGlDecoratorColor.GRAY );
	public static FmsDecoratorStory STORY_QUALITY_NOT_ENABLED = new FmsDecoratorStory(
			"Story Quality Not Enabled",
			R.string.decorator__story__not_enabled,
			FmmTribKnQualityNormalizedDescription.NOT_ENABLED,
			R.integer.fmm__decorator__story__not_enabled__quality_index,
			DecKanGlDecoratorColor.DISABLED );
	
	protected static ArrayList<DecKanGlDecoratorEnumeration> valuesList =
			new ArrayList<DecKanGlDecoratorEnumeration>();
	static {
		valuesList.add(NO_STORY);
		valuesList.add(STORY_SWAG);
		valuesList.add(SUGGESTED_STORY);
		valuesList.add(PROPOSED_STORY);
		valuesList.add(CONFIRMED_STORY);
		valuesList.add(MODIFIED_SINCE_CONFIRMED_STORY);
		valuesList.add(UNKNOWN_STORY_QUALITY);
		valuesList.add(STORY_QUALITY_NOT_APPLICABLE);
		valuesList.add(STORY_QUALITY_NOT_ENABLED);
	}
	
	protected static HashMap<String, FmsDecoratorStory> nameMap = new HashMap<String, FmsDecoratorStory>();
	static {
		for(DecKanGlDecoratorEnumeration theDecKanGlDecoratorEnumeration : valuesList) {
			FmsDecoratorStory.nameMap.put(theDecKanGlDecoratorEnumeration.getName(), (FmsDecoratorStory) theDecKanGlDecoratorEnumeration);
		}
	}
	
	public static FmsDecoratorStory getObjectForName(String aName) {
		return FmsDecoratorStory.nameMap.get(aName);
	}
	
	private FmsDecoratorStory() {
		super();
	}
	
	public static FmsDecoratorStory getStaticInstance() {
		return staticInstance;
	}
	
	public FmsDecoratorStory(
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
		return GcgApplication.getContext().getResources().getString(R.string.tribkn_quality_metric__story);
	}

	@Override
	public Drawable getLabelDrawable() {
		return GcgApplication.getContext().getResources().getDrawable(R.drawable.tribkn_quality_metric__story);
	}

	@Override
	public int getLabelDrawableResourceId() {
		return R.drawable.tribkn_quality_metric__story;
	}

}