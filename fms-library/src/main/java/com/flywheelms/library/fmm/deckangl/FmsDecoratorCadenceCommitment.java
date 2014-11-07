/* @(#)FmsDecoratorCadenceCommitment.java
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

public class FmsDecoratorCadenceCommitment extends DecKanGlDecoratorEnumeration {
	
	protected static String qualityMetricName = GcgApplication.getAppResources().getString(R.string.tribkn_quality_metric__cadence_commitment);
	protected static String qualityMetricDescription = GcgApplication.getAppResources().getString(R.string.tribkn_quality_metric__cadence_commitment__description);
	protected static Drawable qualityMetricDrawable = GcgApplication.getAppResources().getDrawable(R.drawable.tribkn_quality_metric__cadence_commitment);
	protected static DecKanGlDecoratorCanvasLocation decoratorCanvasLocation = DecKanGlDecoratorCanvasLocation.right_zone_MIDDLE;
	
	@Override
	public String getQualityMetricName() {
		return FmsDecoratorCadenceCommitment.qualityMetricName;
	}
	
	@Override
	public String getQualityMetricDescription() {
		return FmsDecoratorCadenceCommitment.qualityMetricDescription;
	}
	
	@Override
	public Drawable getQualityMetricDrawable() {
		return FmsDecoratorCadenceCommitment.qualityMetricDrawable;
	}
	
	@Override
	public DecKanGlDecoratorCanvasLocation getDecoratorCanvasLocation() {
		return FmsDecoratorCadenceCommitment.decoratorCanvasLocation;
	}
	
	protected static FmsDecoratorCadenceCommitment staticInstance = new FmsDecoratorCadenceCommitment();
	
	public static FmsDecoratorCadenceCommitment NO_CADENCE_COMMITMENT = new FmsDecoratorCadenceCommitment(
			"No Cadence Commitment",
			R.string.decorator__cadence_commitment__none,
			FmmTribKnQualityNormalizedDescription.NONE,
			R.integer.fmm__decorator__cadence_commitment__none__quality_index,
			DecKanGlDecoratorColor.RED );
	public static FmsDecoratorCadenceCommitment SUGGESTED_CADENCE_COMMITMENT = new FmsDecoratorCadenceCommitment(
			"Suggested Cadence Commitment",
			R.string.decorator__cadence_commitment__suggested,
			FmmTribKnQualityNormalizedDescription.SUGGESTED,
			R.integer.fmm__decorator__cadence_commitment__suggested__quality_index,
			DecKanGlDecoratorColor.BLUE );
	public static FmsDecoratorCadenceCommitment PROPOSED_CADENCE_COMMITMENT = new FmsDecoratorCadenceCommitment(
			"Proposed Cadence Commitment",
			R.string.decorator__cadence_commitment__proposed,
			FmmTribKnQualityNormalizedDescription.PROPOSED,
			R.integer.fmm__decorator__cadence_commitment__proposed__quality_index,
			DecKanGlDecoratorColor.PINK );
	public static FmsDecoratorCadenceCommitment CONFIRMED_CADENCE_COMMITMENT = new FmsDecoratorCadenceCommitment(
			"Confirmed Cadence Commitment",
			R.string.decorator__cadence_commitment__confirmed,
			FmmTribKnQualityNormalizedDescription.CONFIRMED,
			R.integer.fmm__decorator__cadence_commitment__confirmed__quality_index,
			DecKanGlDecoratorColor.TRANSPARENT );
	public static FmsDecoratorCadenceCommitment FUTURE_CADENCE_COMMITMENT = new FmsDecoratorCadenceCommitment(
			"Future Cadence Commitment",
			R.string.decorator__cadence_commitment__future,
			FmmTribKnQualityNormalizedDescription.FUTURE,
			R.integer.fmm__decorator__cadence_commitment__future__quality_index,
			DecKanGlDecoratorColor.YELLOW );
	public static FmsDecoratorCadenceCommitment MISSED_CADENCE_COMMITMENT = new FmsDecoratorCadenceCommitment(
			"Missed Cadence Commitment",
			R.string.decorator__cadence_commitment__missed,
			FmmTribKnQualityNormalizedDescription.MISSED,
			R.integer.fmm__decorator__cadence_commitment__missed__quality_index,
			DecKanGlDecoratorColor.ORANGE );
	public static FmsDecoratorCadenceCommitment UNKNOWN_CADENCE_COMMITMENT_QUALITY = new FmsDecoratorCadenceCommitment(
			"Unknown Cadence Commitment Quality",
			R.string.decorator__cadence_commitment__unknown_quality,
			FmmTribKnQualityNormalizedDescription.UNKNOWN,
			R.integer.fmm__decorator__cadence_commitment__unknown_quality__quality_index,
			DecKanGlDecoratorColor.GRAY );
	public static FmsDecoratorCadenceCommitment CADENCE_COMMITMENT_QUALITY_NOT_APPLICABLE = new FmsDecoratorCadenceCommitment(
			"Cadence Commitment Quality Not Applicable",
			R.string.decorator__cadence_commitment__not_applicable,
			FmmTribKnQualityNormalizedDescription.NOT_APPLICABLE,
			R.integer.fmm__decorator__cadence_commitment__not_applicable__quality_index,
			DecKanGlDecoratorColor.TRANSPARENT );
	public static FmsDecoratorCadenceCommitment CADENCE_COMMITMENT_QUALITY_NOT_ENABLED = new FmsDecoratorCadenceCommitment(
			"Cadence Commitment Quality Not Enabled",
			R.string.decorator__cadence_commitment__not_enabled,
			FmmTribKnQualityNormalizedDescription.NOT_ENABLED,
			R.integer.fmm__decorator__cadence_commitment__not_enabled__quality_index,
			DecKanGlDecoratorColor.DISABLED );
	
	protected static ArrayList<DecKanGlDecoratorEnumeration> valuesList =
			new ArrayList<DecKanGlDecoratorEnumeration>();
	static {
		valuesList.add(NO_CADENCE_COMMITMENT);
		valuesList.add(SUGGESTED_CADENCE_COMMITMENT);
		valuesList.add(PROPOSED_CADENCE_COMMITMENT);
		valuesList.add(CONFIRMED_CADENCE_COMMITMENT);
		valuesList.add(FUTURE_CADENCE_COMMITMENT);
		valuesList.add(MISSED_CADENCE_COMMITMENT);
		valuesList.add(UNKNOWN_CADENCE_COMMITMENT_QUALITY);
		valuesList.add(CADENCE_COMMITMENT_QUALITY_NOT_APPLICABLE);
		valuesList.add(CADENCE_COMMITMENT_QUALITY_NOT_ENABLED);
	}
	
	protected static HashMap<String, FmsDecoratorCadenceCommitment> nameMap = new HashMap<String, FmsDecoratorCadenceCommitment>();
	static {
		for(DecKanGlDecoratorEnumeration theDecKanGlDecoratorEnumeration : valuesList) {
			FmsDecoratorCadenceCommitment.nameMap.put(theDecKanGlDecoratorEnumeration.getName(), (FmsDecoratorCadenceCommitment) theDecKanGlDecoratorEnumeration);
		}
	}
	
	public static FmsDecoratorCadenceCommitment getObjectForName(String aName) {
		return FmsDecoratorCadenceCommitment.nameMap.get(aName);
	}

	private FmsDecoratorCadenceCommitment() {
		super();
	}
	
	public static FmsDecoratorCadenceCommitment getStaticInstance() {
		return staticInstance;
	}
	
	public FmsDecoratorCadenceCommitment(
            String aName,
            int aDescriptionResourceId,
            FmmTribKnQualityNormalizedDescription aNormalizedDescription,
            int aTribKnQualityIndexResourceId,
            DecKanGlDecoratorColor aDecKanGlDecoratorColor) {
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
		return GcgApplication.getContext().getResources().getString(R.string.tribkn_quality_metric__cadence_commitment);
	}

	@Override
	public Drawable getLabelDrawable() {
		return GcgApplication.getContext().getResources().getDrawable(R.drawable.tribkn_quality_metric__cadence_commitment);
	}

	@Override
	public int getLabelDrawableResourceId() {
		return R.drawable.tribkn_quality_metric__cadence_commitment;
	}

}