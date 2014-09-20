/* @(#)FmsDecoratorStrategicCommitment.java
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
import com.flywheelms.library.fmm.node.impl.enumerator.FmmTribKnQualityNormalizedDescription;
import com.flywheelms.library.gcg.GcgApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FmsDecoratorStrategicCommitment extends DecKanGlDecoratorEnumeration {
	
	protected static String qualityMetricName = GcgApplication.getAppResources().getString(R.string.tribkn_quality_metric__strategic_commitment);
	protected static String qualityMetricDescription = GcgApplication.getAppResources().getString(R.string.tribkn_quality_metric__strategic_commitment__description);
	protected static Drawable qualityMetricDrawable = GcgApplication.getAppResources().getDrawable(R.drawable.tribkn_quality_metric__strategic_commitment);
	protected static DecKanGlDecoratorCanvasLocation decoratorCanvasLocation = DecKanGlDecoratorCanvasLocation.left_zone_MIDDLE;
	
	@Override
	public String getQualityMetricName() {
		return FmsDecoratorStrategicCommitment.qualityMetricName;
	}
	
	@Override
	public String getQualityMetricDescription() {
		return FmsDecoratorStrategicCommitment.qualityMetricDescription;
	}
	
	@Override
	public Drawable getQualityMetricDrawable() {
		return FmsDecoratorStrategicCommitment.qualityMetricDrawable;
	}
	
	@Override
	public DecKanGlDecoratorCanvasLocation getDecoratorCanvasLocation() {
		return FmsDecoratorStrategicCommitment.decoratorCanvasLocation;
	}
	
	protected static FmsDecoratorStrategicCommitment staticInstance = new FmsDecoratorStrategicCommitment();
	
	public static FmsDecoratorStrategicCommitment NO_STRATEGIC_COMMITMENT = new FmsDecoratorStrategicCommitment(
			"No Strategic Commitment",
			R.string.decorator__strategic_commitment__none,
			FmmTribKnQualityNormalizedDescription.NONE,
			R.integer.fmm__decorator__strategic_commitment__none__quality_index,
			DecKanGlDecoratorColor.RED );
	public static FmsDecoratorStrategicCommitment SUGGESTED_STRATEGIC_COMMITMENT = new FmsDecoratorStrategicCommitment(
			"Suggested Strategic Commitment",
			R.string.decorator__strategic_commitment__suggested,
			FmmTribKnQualityNormalizedDescription.SUGGESTED,
			R.integer.fmm__decorator__strategic_commitment__suggested__quality_index,
			DecKanGlDecoratorColor.BLUE );
	public static FmsDecoratorStrategicCommitment PROPOSED_STRATEGIC_COMMITMENT = new FmsDecoratorStrategicCommitment(
			"Proposed Strategic Commitment",
			R.string.decorator__strategic_commitment__proposed,
			FmmTribKnQualityNormalizedDescription.PROPOSED,
			R.integer.fmm__decorator__strategic_commitment__proposed__quality_index,
			DecKanGlDecoratorColor.PINK );
	public static FmsDecoratorStrategicCommitment CONFIRMED_STRATEGIC_COMMITMENT = new FmsDecoratorStrategicCommitment(
			"Confirmed Strategic Commitment",
			R.string.decorator__strategic_commitment__confirmed,
			FmmTribKnQualityNormalizedDescription.CONFIRMED,
			R.integer.fmm__decorator__strategic_commitment__confirmed__quality_index,
			DecKanGlDecoratorColor.TRANSPARENT );
	public static FmsDecoratorStrategicCommitment FUTURE_CONFIRMED_STRATEGIC_COMMITMENT = new FmsDecoratorStrategicCommitment(
			"Future Confirmed Strategic Commitment",
			R.string.decorator__strategic_commitment__future,
			FmmTribKnQualityNormalizedDescription.FUTURE,
			R.integer.fmm__decorator__strategic_commitment__future__quality_index,
			DecKanGlDecoratorColor.YELLOW );
	public static FmsDecoratorStrategicCommitment MISSED_STRATEGIC_COMMITMENT = new FmsDecoratorStrategicCommitment(
			"Missed Strategic Commitment",
			R.string.decorator__strategic_commitment__missed,
			FmmTribKnQualityNormalizedDescription.MISSED,
			R.integer.fmm__decorator__strategic_commitment__missed__quality_index,
			DecKanGlDecoratorColor.ORANGE );
	public static FmsDecoratorStrategicCommitment UNKNOWN_STRATEGIC_COMMITMENT_QUALITY = new FmsDecoratorStrategicCommitment(
			"Unknown Strategic Commitment Quality",
			R.string.decorator__strategic_commitment__unknown_quality,
			FmmTribKnQualityNormalizedDescription.UNKNOWN,
			R.integer.fmm__decorator__strategic_commitment__unknown_quality__quality_index,
			DecKanGlDecoratorColor.GRAY );
	public static FmsDecoratorStrategicCommitment STRATEGIC_COMMITMENT_QUALITY_NOT_APPLICABLE = new FmsDecoratorStrategicCommitment(
			"Strategic Commitment Quality Not Applicable",
			R.string.decorator__strategic_commitment__not_applicable,
			FmmTribKnQualityNormalizedDescription.NOT_APPLICABLE,
			R.integer.fmm__decorator__strategic_commitment__not_applicable__quality_index,
			DecKanGlDecoratorColor.TRANSPARENT );
	public static FmsDecoratorStrategicCommitment STRATEGIC_COMMITMENT_QUALITY_NOT_ENABLED = new FmsDecoratorStrategicCommitment(
			"Strategic Commitment Quality Not Enabled",
			R.string.decorator__strategic_commitment__not_enabled,
			FmmTribKnQualityNormalizedDescription.NOT_ENABLED,
			R.integer.fmm__decorator__strategic_commitment__not_enabled__quality_index,
			DecKanGlDecoratorColor.DISABLED );
	
	protected static ArrayList<DecKanGlDecoratorEnumeration> valuesList =
			new ArrayList<DecKanGlDecoratorEnumeration>();
	static {
		valuesList.add(NO_STRATEGIC_COMMITMENT);
		valuesList.add(SUGGESTED_STRATEGIC_COMMITMENT);
		valuesList.add(PROPOSED_STRATEGIC_COMMITMENT);
		valuesList.add(CONFIRMED_STRATEGIC_COMMITMENT);
		valuesList.add(FUTURE_CONFIRMED_STRATEGIC_COMMITMENT);
		valuesList.add(MISSED_STRATEGIC_COMMITMENT);
		valuesList.add(UNKNOWN_STRATEGIC_COMMITMENT_QUALITY);
		valuesList.add(STRATEGIC_COMMITMENT_QUALITY_NOT_APPLICABLE);
		valuesList.add(STRATEGIC_COMMITMENT_QUALITY_NOT_ENABLED);
	}
	
	protected static HashMap<String, FmsDecoratorStrategicCommitment> nameMap = new HashMap<String, FmsDecoratorStrategicCommitment>();
	static {
		for(DecKanGlDecoratorEnumeration theDecKanGlDecoratorEnumeration : valuesList) {
			FmsDecoratorStrategicCommitment.nameMap.put(theDecKanGlDecoratorEnumeration.getName(), (FmsDecoratorStrategicCommitment) theDecKanGlDecoratorEnumeration);
		}
	}
	
	public static FmsDecoratorStrategicCommitment getObjectForName(String aName) {
		return FmsDecoratorStrategicCommitment.nameMap.get(aName);
	}

	private FmsDecoratorStrategicCommitment() {
		super();
	}
	
	public static FmsDecoratorStrategicCommitment getStaticInstance() {
		return staticInstance;
	}
	
	public FmsDecoratorStrategicCommitment(
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
		return GcgApplication.getContext().getResources().getString(R.string.tribkn_quality_metric__strategic_commitment);
	}

	@Override
	public Drawable getLabelDrawable() {
		return GcgApplication.getContext().getResources().getDrawable(R.drawable.tribkn_quality_metric__strategic_commitment);
	}

	@Override
	public int getLabelDrawableResourceId() {
		return R.drawable.tribkn_quality_metric__strategic_commitment;
	}

}