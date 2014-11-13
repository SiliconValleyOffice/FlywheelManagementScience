/* @(#)FmmDecKanGlDictionary.java
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

import com.flywheelms.gcongui.deckangl.glyph.DecKanGlDictionary;
import com.flywheelms.gcongui.deckangl.glyph.DecKanGlElementNoun;
import com.flywheelms.gcongui.deckangl.glyph.DecKanGlElementNounQualityMetric;
import com.flywheelms.gcongui.deckangl.glyph.DecKanGlElementNounState;
import com.flywheelms.library.fmm.node.impl.enumerator.CompletableWorkStatus;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;

import java.util.HashMap;

/*
 * define the application-specific DecKanGL Dictionary language elements:
 *   - noun
 *   - noun state
 *   - noun quality metric (and the package containing their decorator implementations)
 */
public class FmmDecKanGlDictionary extends DecKanGlDictionary {

	/*
	 * WARNING - update this static variable when changing package structure for the application's decorator classes
	 */
	private static final String DECORATOR_PACKAGE_NAME = "com.flywheelms.library.fmm.deckangl";
	private static HashMap<String, DecKanGlElementNounState> decKanGlNounStateMap;
	private static HashMap<DecKanGlElementNounState, CompletableWorkStatus> fmmWorkStatusMap;
	
	private FmmDecKanGlDictionary() {
		super();
	}
	
	public static DecKanGlDictionary getInstance() {
		if (DecKanGlDictionary.instance == null) {
			DecKanGlDictionary.instance = new FmmDecKanGlDictionary();
		}
		return instance;
	}

	@Override
	public String getDecoratorsPackageName() {
		return FmmDecKanGlDictionary.DECORATOR_PACKAGE_NAME;
	}

	@Override
	protected HashMap<String, DecKanGlElementNoun> buildNounMap() {
		HashMap<String, DecKanGlElementNoun> theNounDefinitionMap = new HashMap<String, DecKanGlElementNoun>();
		for(FmmNodeDefinition theFmmNodeDefinition : FmmNodeDefinition.getDecKanGlNodeList()) {
			theNounDefinitionMap.put(
				theFmmNodeDefinition.getName(),
				new DecKanGlElementNoun(
						theFmmNodeDefinition.getName(),
						theFmmNodeDefinition.getNounStateBitmapTableTiny(), 
						theFmmNodeDefinition.getNounStateBitmapTableSmall(), 
						theFmmNodeDefinition.getNounStateBitmapTableMedium(), 
						theFmmNodeDefinition.getNounStateBitmapTableLarge() ) );
		}
		return theNounDefinitionMap;
	}

	// color is the 'meta' noun state information, we are just mapping it to the FMM work state description
	@Override
	public HashMap<String, DecKanGlElementNounState> getNounStateMap() {
		return getStaticNounStateMap();
	}
	
	public static HashMap<String, DecKanGlElementNounState> getStaticNounStateMap() {
		if(FmmDecKanGlDictionary.decKanGlNounStateMap == null) {
			buildStaticMaps();
		}
		return FmmDecKanGlDictionary.decKanGlNounStateMap;
	}
	
	public static CompletableWorkStatus getCompletableWorkStatus(DecKanGlElementNounState aDecKanGlElementNounState) {
		if(FmmDecKanGlDictionary.fmmWorkStatusMap == null) {
			buildStaticMaps();
		}
		return FmmDecKanGlDictionary.fmmWorkStatusMap.get(aDecKanGlElementNounState);
	}
	
	public static void buildStaticMaps() {
		FmmDecKanGlDictionary.decKanGlNounStateMap = new HashMap<String, DecKanGlElementNounState>();
		FmmDecKanGlDictionary.fmmWorkStatusMap = new HashMap<DecKanGlElementNounState, CompletableWorkStatus>();
		DecKanGlElementNounState theElementNounState = new DecKanGlElementNounState(
				CompletableWorkStatus.NOT_STARTED.getWorkStatusCode(),
				CompletableWorkStatus.NOT_STARTED.getWorkStateColor() );
		FmmDecKanGlDictionary.decKanGlNounStateMap.put(CompletableWorkStatus.NOT_STARTED.getWorkStatusCode(), theElementNounState);
		FmmDecKanGlDictionary.fmmWorkStatusMap.put(theElementNounState, CompletableWorkStatus.NOT_STARTED);
        //
		theElementNounState = new DecKanGlElementNounState(
				CompletableWorkStatus.STARTED.getWorkStatusCode(),
				CompletableWorkStatus.STARTED.getWorkStateColor() );
		FmmDecKanGlDictionary.decKanGlNounStateMap.put(CompletableWorkStatus.STARTED.getWorkStatusCode(), theElementNounState);
		FmmDecKanGlDictionary.fmmWorkStatusMap.put(theElementNounState, CompletableWorkStatus.STARTED);
		//
		theElementNounState = new DecKanGlElementNounState(
				CompletableWorkStatus.ON_HOLD.getWorkStatusCode(),
				CompletableWorkStatus.ON_HOLD.getWorkStateColor() );
		FmmDecKanGlDictionary.decKanGlNounStateMap.put(CompletableWorkStatus.ON_HOLD.getWorkStatusCode(), theElementNounState);
		FmmDecKanGlDictionary.fmmWorkStatusMap.put(theElementNounState, CompletableWorkStatus.ON_HOLD);
		//
        theElementNounState = new DecKanGlElementNounState(
                CompletableWorkStatus.BLOCKED.getWorkStatusCode(),
                CompletableWorkStatus.BLOCKED.getWorkStateColor() );
        FmmDecKanGlDictionary.decKanGlNounStateMap.put(CompletableWorkStatus.BLOCKED.getWorkStatusCode(), theElementNounState);
        FmmDecKanGlDictionary.fmmWorkStatusMap.put(theElementNounState, CompletableWorkStatus.BLOCKED);
        //
		theElementNounState = new DecKanGlElementNounState(
				CompletableWorkStatus.READY_FOR_REVIEW.getWorkStatusCode(),
				CompletableWorkStatus.READY_FOR_REVIEW.getWorkStateColor() );
		FmmDecKanGlDictionary.decKanGlNounStateMap.put(CompletableWorkStatus.READY_FOR_REVIEW.getWorkStatusCode(), theElementNounState);
		FmmDecKanGlDictionary.fmmWorkStatusMap.put(theElementNounState, CompletableWorkStatus.READY_FOR_REVIEW);
		//
		theElementNounState = new DecKanGlElementNounState(
				CompletableWorkStatus.COMPLETE.getWorkStatusCode(),
				CompletableWorkStatus.COMPLETE.getWorkStateColor() );
		FmmDecKanGlDictionary.decKanGlNounStateMap.put(CompletableWorkStatus.COMPLETE.getWorkStatusCode(), theElementNounState);
		FmmDecKanGlDictionary.fmmWorkStatusMap.put(theElementNounState, CompletableWorkStatus.COMPLETE);
	}
	
	@Override
	public HashMap<String, DecKanGlElementNounQualityMetric> buildNounQualityMetricMap() {
		HashMap<String, DecKanGlElementNounQualityMetric> theNounQualityMetricMap = new HashMap<String, DecKanGlElementNounQualityMetric>();
		theNounQualityMetricMap.put(FmsDecoratorGovernance.getStaticInstance().getQualityMetricName(), new DecKanGlElementNounQualityMetric(
				FmsDecoratorGovernance.getStaticInstance().getQualityMetricName(),
				FmsDecoratorGovernance.getStaticInstance().getQualityMetricDescription(),
				FmsDecoratorGovernance.getStaticInstance().getQualityMetricDrawable(),
				FmsDecoratorGovernance.getStaticInstance().getDecoratorCanvasLocation(),
				FmsDecoratorGovernance.class ));
		theNounQualityMetricMap.put(FmsDecoratorFacilitationIssue.getStaticInstance().getQualityMetricName(), new DecKanGlElementNounQualityMetric(
				FmsDecoratorFacilitationIssue.getStaticInstance().getQualityMetricName(),
				FmsDecoratorFacilitationIssue.getStaticInstance().getQualityMetricDescription(),
				FmsDecoratorFacilitationIssue.getStaticInstance().getQualityMetricDrawable(),
				FmsDecoratorFacilitationIssue.getStaticInstance().getDecoratorCanvasLocation(),
				FmsDecoratorFacilitationIssue.class ));
		theNounQualityMetricMap.put(FmsDecoratorFacilitator.getStaticInstance().getQualityMetricName(), new DecKanGlElementNounQualityMetric(
				FmsDecoratorFacilitator.getStaticInstance().getQualityMetricName(),
				FmsDecoratorFacilitator.getStaticInstance().getQualityMetricDescription(),
				FmsDecoratorFacilitator.getStaticInstance().getQualityMetricDrawable(),
				FmsDecoratorFacilitator.getStaticInstance().getDecoratorCanvasLocation(),
				FmsDecoratorFacilitator.class ));
		theNounQualityMetricMap.put(FmsDecoratorProjectManagement.getStaticInstance().getQualityMetricName(), new DecKanGlElementNounQualityMetric(
				FmsDecoratorProjectManagement.getStaticInstance().getQualityMetricName(),
				FmsDecoratorProjectManagement.getStaticInstance().getQualityMetricDescription(),
				FmsDecoratorProjectManagement.getStaticInstance().getQualityMetricDrawable(),
				FmsDecoratorProjectManagement.getStaticInstance().getDecoratorCanvasLocation(),
				FmsDecoratorProjectManagement.class ));
		theNounQualityMetricMap.put(FmsDecoratorStrategicCommitment.getStaticInstance().getQualityMetricName(), new DecKanGlElementNounQualityMetric(
				FmsDecoratorStrategicCommitment.getStaticInstance().getQualityMetricName(),
				FmsDecoratorStrategicCommitment.getStaticInstance().getQualityMetricDescription(),
				FmsDecoratorStrategicCommitment.getStaticInstance().getQualityMetricDrawable(),
				FmsDecoratorStrategicCommitment.getStaticInstance().getDecoratorCanvasLocation(),
				FmsDecoratorStrategicCommitment.class ));
		theNounQualityMetricMap.put(FmsDecoratorWorkTaskBudget.getStaticInstance().getQualityMetricName(), new DecKanGlElementNounQualityMetric(
				FmsDecoratorWorkTaskBudget.getStaticInstance().getQualityMetricName(),
				FmsDecoratorWorkTaskBudget.getStaticInstance().getQualityMetricDescription(),
				FmsDecoratorWorkTaskBudget.getStaticInstance().getQualityMetricDrawable(),
				FmsDecoratorWorkTaskBudget.getStaticInstance().getDecoratorCanvasLocation(),
				FmsDecoratorWorkTaskBudget.class ));
		theNounQualityMetricMap.put(FmsDecoratorWorkBreakdown.getStaticInstance().getQualityMetricName(), new DecKanGlElementNounQualityMetric(
				FmsDecoratorWorkBreakdown.getStaticInstance().getQualityMetricName(),
				FmsDecoratorWorkBreakdown.getStaticInstance().getQualityMetricDescription(),
				FmsDecoratorWorkBreakdown.getStaticInstance().getQualityMetricDrawable(),
				FmsDecoratorWorkBreakdown.getStaticInstance().getDecoratorCanvasLocation(),
				FmsDecoratorWorkBreakdown.class ));
		theNounQualityMetricMap.put(FmsDecoratorCadenceCommitment.getStaticInstance().getQualityMetricName(), new DecKanGlElementNounQualityMetric(
				FmsDecoratorCadenceCommitment.getStaticInstance().getQualityMetricName(),
				FmsDecoratorCadenceCommitment.getStaticInstance().getQualityMetricDescription(),
				FmsDecoratorCadenceCommitment.getStaticInstance().getQualityMetricDrawable(),
				FmsDecoratorCadenceCommitment.getStaticInstance().getDecoratorCanvasLocation(),
				FmsDecoratorCadenceCommitment.class ));
		theNounQualityMetricMap.put(FmsDecoratorWorkTeam.getStaticInstance().getQualityMetricName(), new DecKanGlElementNounQualityMetric(
				FmsDecoratorWorkTeam.getStaticInstance().getQualityMetricName(),
				FmsDecoratorWorkTeam.getStaticInstance().getQualityMetricDescription(),
				FmsDecoratorWorkTeam.getStaticInstance().getQualityMetricDrawable(),
				FmsDecoratorWorkTeam.getStaticInstance().getDecoratorCanvasLocation(),
				FmsDecoratorWorkTeam.class ));
		theNounQualityMetricMap.put(FmsDecoratorSequence.getStaticInstance().getQualityMetricName(), new DecKanGlElementNounQualityMetric(
				FmsDecoratorSequence.getStaticInstance().getQualityMetricName(),
				FmsDecoratorSequence.getStaticInstance().getQualityMetricDescription(),
				FmsDecoratorSequence.getStaticInstance().getQualityMetricDrawable(),
				FmsDecoratorSequence.getStaticInstance().getDecoratorCanvasLocation(),
				FmsDecoratorSequence.class ));
		theNounQualityMetricMap.put(FmsDecoratorStory.getStaticInstance().getQualityMetricName(), new DecKanGlElementNounQualityMetric(
				FmsDecoratorStory.getStaticInstance().getQualityMetricName(),
				FmsDecoratorStory.getStaticInstance().getQualityMetricDescription(),
				FmsDecoratorStory.getStaticInstance().getQualityMetricDrawable(),
				FmsDecoratorStory.getStaticInstance().getDecoratorCanvasLocation(),
				FmsDecoratorStory.class ));
		theNounQualityMetricMap.put(FmsDecoratorCompletion.getStaticInstance().getQualityMetricName(), new DecKanGlElementNounQualityMetric(
				FmsDecoratorCompletion.getStaticInstance().getQualityMetricName(),
				FmsDecoratorCompletion.getStaticInstance().getQualityMetricDescription(),
				FmsDecoratorCompletion.getStaticInstance().getQualityMetricDrawable(),
				FmsDecoratorCompletion.getStaticInstance().getDecoratorCanvasLocation(),
				FmsDecoratorCompletion.class ));
		return theNounQualityMetricMap;
	}
	
}