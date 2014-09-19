/* @(#)FmsDecoratorWorkTeam.java
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

public class FmsDecoratorWorkTeam extends DecKanGlDecoratorEnumeration {
	
	protected static String qualityMetricName = GcgApplication.getAppResources().getString(R.string.tribkn_quality_metric__work_team);
	protected static String qualityMetricDescription = GcgApplication.getAppResources().getString(R.string.tribkn_quality_metric__work_team__description);
	protected static Drawable qualityMetricDrawable = GcgApplication.getAppResources().getDrawable(R.drawable.tribkn_quality_metric__work_team);
	protected static DecKanGlDecoratorCanvasLocation decoratorCanvasLocation = DecKanGlDecoratorCanvasLocation.right_zone_BOTTOM;
	
	@Override
	public String getQualityMetricName() {
		return FmsDecoratorWorkTeam.qualityMetricName;
	}
	
	@Override
	public String getQualityMetricDescription() {
		return FmsDecoratorWorkTeam.qualityMetricDescription;
	}
	
	@Override
	public Drawable getQualityMetricDrawable() {
		return FmsDecoratorWorkTeam.qualityMetricDrawable;
	}
	
	@Override
	public DecKanGlDecoratorCanvasLocation getDecoratorCanvasLocation() {
		return FmsDecoratorWorkTeam.decoratorCanvasLocation;
	}
	
	protected static FmsDecoratorWorkTeam staticInstance = new FmsDecoratorWorkTeam();
	
	public static FmsDecoratorWorkTeam NO_WORK_TEAM = new FmsDecoratorWorkTeam(
			"No Work Team",
			R.string.decorator__work_team__none,
			TribKnQualityNormalizedDescription.NONE,
			R.integer.fmm__decorator__work_team__none__quality_index,
			DecKanGlDecoratorColor.RED );
	public static FmsDecoratorWorkTeam WORK_TEAM_SWAG = new FmsDecoratorWorkTeam(
			"Work Team SWAG",
			R.string.decorator__work_team__swag,
			TribKnQualityNormalizedDescription.SWAG,
			R.integer.fmm__decorator__work_team__swag__quality_index,
			DecKanGlDecoratorColor.YELLOW );
	public static FmsDecoratorWorkTeam SUGGESTED_TEAM = new FmsDecoratorWorkTeam(
			"Suggested Work Team",
			R.string.decorator__work_team__suggested,
			TribKnQualityNormalizedDescription.SUGGESTED,
			R.integer.fmm__decorator__work_team__suggested__quality_index,
			DecKanGlDecoratorColor.BLUE );
	public static FmsDecoratorWorkTeam PROPOSED_TEAM = new FmsDecoratorWorkTeam(
			"Proposed Work Team",
			R.string.decorator__work_team__proposed,
			TribKnQualityNormalizedDescription.PROPOSED,
			R.integer.fmm__decorator__work_team__proposed__quality_index,
			DecKanGlDecoratorColor.PINK );
	public static FmsDecoratorWorkTeam CONFIRMED_TEAM = new FmsDecoratorWorkTeam(
			"Confirmed Team",
			R.string.decorator__work_team__confirmed,
			TribKnQualityNormalizedDescription.CONFIRMED,
			R.integer.fmm__decorator__work_team__confirmed__quality_index,
			DecKanGlDecoratorColor.TRANSPARENT );
	public static FmsDecoratorWorkTeam INACTIVE_CONFIRMED_WORK_TEAM_MEMBER = new FmsDecoratorWorkTeam(
			"Inactive Confirmed Work Team Member(s)",
			R.string.decorator__work_team__inactive,
			TribKnQualityNormalizedDescription.INACTIVE,
			R.integer.fmm__decorator__work_team__inactive__quality_index,
			DecKanGlDecoratorColor.ORANGE );
	public static FmsDecoratorWorkTeam UNKNOWN_TEAM_QUALITY = new FmsDecoratorWorkTeam(
			"Unknown Team Quality",
			R.string.decorator__work_team__unknown_quality,
			TribKnQualityNormalizedDescription.UNKNOWN,
			R.integer.fmm__decorator__work_team__unknown_quality__quality_index,
			DecKanGlDecoratorColor.GRAY );
	public static FmsDecoratorWorkTeam TEAM_QUALITY_NOT_APPLICABLE = new FmsDecoratorWorkTeam(
			"Team Quality Not Applicable",
			R.string.decorator__work_team__not_applicable,
			TribKnQualityNormalizedDescription.NOT_APPLICABLE,
			R.integer.fmm__decorator__work_team__not_applicable__quality_index,
			DecKanGlDecoratorColor.TRANSPARENT );
	public static FmsDecoratorWorkTeam TEAM_QUALITY_NOT_ENABLED = new FmsDecoratorWorkTeam(
			"Team Quality Not Enabled",
			R.string.decorator__work_team__not_enabled,
			TribKnQualityNormalizedDescription.NOT_ENABLED,
			R.integer.fmm__decorator__work_team__not_enabled__quality_index,
			DecKanGlDecoratorColor.DISABLED );
	
	protected static ArrayList<DecKanGlDecoratorEnumeration> valuesList =
			new ArrayList<DecKanGlDecoratorEnumeration>();
	static {
		valuesList.add(NO_WORK_TEAM);
		valuesList.add(WORK_TEAM_SWAG);
		valuesList.add(SUGGESTED_TEAM);
		valuesList.add(PROPOSED_TEAM);
		valuesList.add(CONFIRMED_TEAM);
		valuesList.add(INACTIVE_CONFIRMED_WORK_TEAM_MEMBER);
		valuesList.add(UNKNOWN_TEAM_QUALITY);
		valuesList.add(TEAM_QUALITY_NOT_APPLICABLE);
		valuesList.add(TEAM_QUALITY_NOT_ENABLED);
	}
	
	protected static HashMap<String, FmsDecoratorWorkTeam> nameMap = new HashMap<String, FmsDecoratorWorkTeam>();
	static {
		for(DecKanGlDecoratorEnumeration theDecKanGlDecoratorEnumeration : valuesList) {
			FmsDecoratorWorkTeam.nameMap.put(theDecKanGlDecoratorEnumeration.getName(), (FmsDecoratorWorkTeam) theDecKanGlDecoratorEnumeration);
		}
	}
	
	public static FmsDecoratorWorkTeam getObjectForName(String aName) {
		return FmsDecoratorWorkTeam.nameMap.get(aName);
	}

	private FmsDecoratorWorkTeam() {
		super();
	}
	
	public static FmsDecoratorWorkTeam getStaticInstance() {
		return staticInstance;
	}
	
	public FmsDecoratorWorkTeam(
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
		return GcgApplication.getContext().getResources().getString(R.string.tribkn_quality_metric__work_team);
	}

	@Override
	public Drawable getLabelDrawable() {
		return GcgApplication.getContext().getResources().getDrawable(R.drawable.tribkn_quality_metric__work_team);
	}

	@Override
	public int getLabelDrawableResourceId() {
		return R.drawable.tribkn_quality_metric__work_team;
	}

}