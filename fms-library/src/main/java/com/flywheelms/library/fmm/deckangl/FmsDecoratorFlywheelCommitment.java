/* @(#)FmsDecoratorCommitment.java
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

public class FmsDecoratorFlywheelCommitment extends DecKanGlDecoratorEnumeration {
	
	protected static String qualityMetricName = GcgApplication.getAppResources().getString(R.string.tribkn_quality_metric__flywheel_commitment);
	protected static String qualityMetricDescription = GcgApplication.getAppResources().getString(R.string.tribkn_quality_metric__flywheel_commitment__description);
	protected static Drawable qualityMetricDrawable = GcgApplication.getAppResources().getDrawable(R.drawable.tribkn_quality_metric__flywheel_commitment);
	protected static DecKanGlDecoratorCanvasLocation decoratorCanvasLocation = DecKanGlDecoratorCanvasLocation.right_zone_MIDDLE;
	
	@Override
	public String getQualityMetricName() {
		return FmsDecoratorFlywheelCommitment.qualityMetricName;
	}
	
	@Override
	public String getQualityMetricDescription() {
		return FmsDecoratorFlywheelCommitment.qualityMetricDescription;
	}
	
	@Override
	public Drawable getQualityMetricDrawable() {
		return FmsDecoratorFlywheelCommitment.qualityMetricDrawable;
	}
	
	@Override
	public DecKanGlDecoratorCanvasLocation getDecoratorCanvasLocation() {
		return FmsDecoratorFlywheelCommitment.decoratorCanvasLocation;
	}
	
	protected static FmsDecoratorFlywheelCommitment staticInstance = new FmsDecoratorFlywheelCommitment();
	
	public static FmsDecoratorFlywheelCommitment NO_FLYWHEEL_COMMITMENT = new FmsDecoratorFlywheelCommitment(
			"No Flywheel Commitment",
			R.string.decorator__flywheel_commitment__none,
			TribKnQualityNormalizedDescription.NONE,
			R.integer.fmm__decorator__flywheel_commitment__none__quality_index,
			DecKanGlDecoratorColor.RED );
	public static FmsDecoratorFlywheelCommitment SUGGESTED_FLYWHEEL_COMMITMENT = new FmsDecoratorFlywheelCommitment(
			"Suggested Flywheel Commitment",
			R.string.decorator__flywheel_commitment__suggested,
			TribKnQualityNormalizedDescription.SUGGESTED,
			R.integer.fmm__decorator__flywheel_commitment__suggested__quality_index,
			DecKanGlDecoratorColor.BLUE );
	public static FmsDecoratorFlywheelCommitment PROPOSED_FLYWHEEL_COMMITMENT = new FmsDecoratorFlywheelCommitment(
			"Proposed Flywheel Commitment",
			R.string.decorator__flywheel_commitment__proposed,
			TribKnQualityNormalizedDescription.PROPOSED,
			R.integer.fmm__decorator__flywheel_commitment__proposed__quality_index,
			DecKanGlDecoratorColor.PINK );
	public static FmsDecoratorFlywheelCommitment CONFIRMED_FLYWHEEL_COMMITMENT = new FmsDecoratorFlywheelCommitment(
			"Confirmed Flywheel Commitment",
			R.string.decorator__flywheel_commitment__confirmed,
			TribKnQualityNormalizedDescription.CONFIRMED,
			R.integer.fmm__decorator__flywheel_commitment__confirmed__quality_index,
			DecKanGlDecoratorColor.TRANSPARENT );
	public static FmsDecoratorFlywheelCommitment FUTURE_FLYWHEEL_COMMITMENT = new FmsDecoratorFlywheelCommitment(
			"Future Flywheel Commitment",
			R.string.decorator__flywheel_commitment__future,
			TribKnQualityNormalizedDescription.FUTURE,
			R.integer.fmm__decorator__flywheel_commitment__future__quality_index,
			DecKanGlDecoratorColor.YELLOW );
	public static FmsDecoratorFlywheelCommitment MISSED_FLYWHEEL_COMMITMENT = new FmsDecoratorFlywheelCommitment(
			"Missed Flywheel Commitment",
			R.string.decorator__flywheel_commitment__missed,
			TribKnQualityNormalizedDescription.MISSED,
			R.integer.fmm__decorator__flywheel_commitment__missed__quality_index,
			DecKanGlDecoratorColor.ORANGE );
	public static FmsDecoratorFlywheelCommitment UNKNOWN_FLYWHEEL_COMMITMENT_QUALITY = new FmsDecoratorFlywheelCommitment(
			"Unknown Flywheel Commitment Quality",
			R.string.decorator__flywheel_commitment__unknown_quality,
			TribKnQualityNormalizedDescription.UNKNOWN,
			R.integer.fmm__decorator__flywheel_commitment__unknown_quality__quality_index,
			DecKanGlDecoratorColor.GRAY );
	public static FmsDecoratorFlywheelCommitment FLYWHEEL_COMMITMENT_QUALITY_NOT_APPLICABLE = new FmsDecoratorFlywheelCommitment(
			"Flywheel Commitment Quality Not Applicable",
			R.string.decorator__flywheel_commitment__not_applicable,
			TribKnQualityNormalizedDescription.NOT_APPLICABLE,
			R.integer.fmm__decorator__flywheel_commitment__not_applicable__quality_index,
			DecKanGlDecoratorColor.TRANSPARENT );
	public static FmsDecoratorFlywheelCommitment FLYWHEEL_COMMITMENT_QUALITY_NOT_ENABLED = new FmsDecoratorFlywheelCommitment(
			"Flywheel Commitment Quality Not Enabled",
			R.string.decorator__flywheel_commitment__not_enabled,
			TribKnQualityNormalizedDescription.NOT_ENABLED,
			R.integer.fmm__decorator__flywheel_commitment__not_enabled__quality_index,
			DecKanGlDecoratorColor.DISABLED );
	
	protected static ArrayList<DecKanGlDecoratorEnumeration> valuesList =
			new ArrayList<DecKanGlDecoratorEnumeration>();
	static {
		valuesList.add(NO_FLYWHEEL_COMMITMENT);
		valuesList.add(SUGGESTED_FLYWHEEL_COMMITMENT);
		valuesList.add(PROPOSED_FLYWHEEL_COMMITMENT);
		valuesList.add(CONFIRMED_FLYWHEEL_COMMITMENT);
		valuesList.add(FUTURE_FLYWHEEL_COMMITMENT);
		valuesList.add(MISSED_FLYWHEEL_COMMITMENT);
		valuesList.add(UNKNOWN_FLYWHEEL_COMMITMENT_QUALITY);
		valuesList.add(FLYWHEEL_COMMITMENT_QUALITY_NOT_APPLICABLE);
		valuesList.add(FLYWHEEL_COMMITMENT_QUALITY_NOT_ENABLED);
	}
	
	protected static HashMap<String, FmsDecoratorFlywheelCommitment> nameMap = new HashMap<String, FmsDecoratorFlywheelCommitment>();
	static {
		for(DecKanGlDecoratorEnumeration theDecKanGlDecoratorEnumeration : valuesList) {
			FmsDecoratorFlywheelCommitment.nameMap.put(theDecKanGlDecoratorEnumeration.getName(), (FmsDecoratorFlywheelCommitment) theDecKanGlDecoratorEnumeration);
		}
	}
	
	public static FmsDecoratorFlywheelCommitment getObjectForName(String aName) {
		return FmsDecoratorFlywheelCommitment.nameMap.get(aName);
	}

	private FmsDecoratorFlywheelCommitment() {
		super();
	}
	
	public static FmsDecoratorFlywheelCommitment getStaticInstance() {
		return staticInstance;
	}
	
	public FmsDecoratorFlywheelCommitment(
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
		return GcgApplication.getContext().getResources().getString(R.string.tribkn_quality_metric__flywheel_commitment);
	}

	@Override
	public Drawable getLabelDrawable() {
		return GcgApplication.getContext().getResources().getDrawable(R.drawable.tribkn_quality_metric__flywheel_commitment);
	}

	@Override
	public int getLabelDrawableResourceId() {
		return R.drawable.tribkn_quality_metric__flywheel_commitment;
	}

}