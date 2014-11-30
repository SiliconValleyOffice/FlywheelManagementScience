/* @(#)NodeFragTribKnQualityDaoSqLite.java
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

package com.flywheelms.library.fmm.database.sqlite.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.flywheelms.gcongui.gcg.widget.date.GcgDateHelper;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorCadenceCommitment;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorCompletion;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorFacilitationIssue;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorFacilitator;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorGovernance;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorProjectManagement;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorSequence;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorStory;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorStrategicCommitment;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorWorkBreakdown;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorWorkTaskBudget;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorWorkTeam;
import com.flywheelms.library.fmm.meta_data.IdNodeMetaData;
import com.flywheelms.library.fmm.meta_data.NodeFragMetaData;
import com.flywheelms.library.fmm.meta_data.NodeFragTribKnQualityMetaData;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragTribKnQuality;

import java.util.HashMap;

public class NodeFragTribKnQualityDaoSqLite extends NodeFragDaoSqLite<NodeFragTribKnQuality> {

	private static NodeFragTribKnQualityDaoSqLite singleton;

	public static NodeFragTribKnQualityDaoSqLite getInstance() {
		if(NodeFragTribKnQualityDaoSqLite.singleton == null) {
			NodeFragTribKnQualityDaoSqLite.singleton = new NodeFragTribKnQualityDaoSqLite();
		}
		return NodeFragTribKnQualityDaoSqLite.singleton;
	}
	
	@Override
	public FmmNodeDefinition getFmmNodeDefinition() {
		return FmmNodeDefinition.NODE_FRAG__TRIBKN_QUALITY;
	}

	@Override
	protected void buildColumnIndexMap(Cursor aCursor) {
		super.buildColumnIndexMap(aCursor);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTribKnQualityMetaData.column_GOVERNANCE_QUALITY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTribKnQualityMetaData.column_GOVERNANCE_QUALITY_TIMESTAMP);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTribKnQualityMetaData.column_FACILITATION_ISSUE_QUALITY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTribKnQualityMetaData.column_FACILITATION_ISSUE_QUALITY_TIMESTAMP);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTribKnQualityMetaData.column_FACILITATOR_QUALITY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTribKnQualityMetaData.column_FACILITATOR_QUALITY_TIMESTAMP);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTribKnQualityMetaData.column_PARENT_FRACTALS_QUALITY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTribKnQualityMetaData.column_PARENT_FRACTALS_QUALITY_TIMESTAMP);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTribKnQualityMetaData.column_CHILD_FRACTALS_QUALITY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTribKnQualityMetaData.column_CHILD_FRACTALS_QUALITY_TIMESTAMP);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTribKnQualityMetaData.column_CADENCE_COMMITMENT_QUALITY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTribKnQualityMetaData.column_CADENCE_COMMITMENT_QUALITY_TIMESTAMP);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTribKnQualityMetaData.column_TASK_POINTS_BUDGET_QUALITY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTribKnQualityMetaData.column_TASK_POINTS_BUDGET_QUALITY_TIMESTAMP);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTribKnQualityMetaData.column_WORK_TEAM_QUALITY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTribKnQualityMetaData.column_WORK_TEAM_QUALITY_TIMESTAMP);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTribKnQualityMetaData.column_STRATEGIC_COMMITMENT_QUALITY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTribKnQualityMetaData.column_STRATEGIC_COMMITMENT_QUALITY_TIMESTAMP);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTribKnQualityMetaData.column_STORY_QUALITY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTribKnQualityMetaData.column_STORY_QUALITY_TIMESTAMP);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTribKnQualityMetaData.column_SEQUENCE_QUALITY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTribKnQualityMetaData.column_SEQUENCE_QUALITY_TIMESTAMP);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTribKnQualityMetaData.column_COMPLETION_QUALITY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTribKnQualityMetaData.column_COMPLETION_QUALITY_TIMESTAMP);
	}

	@Override
	protected void getColumnValues(HashMap<String, Integer> aHashMap, Cursor aCursor, NodeFragTribKnQuality aNodeFragTribKnQuality) {
		aNodeFragTribKnQuality.setGovernanceQuality(FmsDecoratorGovernance.getObjectForName(aCursor.getString(aHashMap.get(NodeFragTribKnQualityMetaData.column_GOVERNANCE_QUALITY))));
		aNodeFragTribKnQuality.setGovernanceQualityTimestamp(GcgDateHelper.getDateFromIso8601DateString(aCursor.getString(aHashMap.get(NodeFragTribKnQualityMetaData.column_GOVERNANCE_QUALITY_TIMESTAMP))));
		aNodeFragTribKnQuality.setFacilitationIssueQuality(FmsDecoratorFacilitationIssue.getObjectForName(aCursor.getString(aHashMap.get(NodeFragTribKnQualityMetaData.column_FACILITATION_ISSUE_QUALITY))));
		aNodeFragTribKnQuality.setFacilitationIssueQualityTimestamp(GcgDateHelper.getDateFromIso8601DateString(aCursor.getString(aHashMap.get(NodeFragTribKnQualityMetaData.column_FACILITATION_ISSUE_QUALITY_TIMESTAMP))));
		aNodeFragTribKnQuality.setFacilitatorQuality(FmsDecoratorFacilitator.getObjectForName(aCursor.getString(aHashMap.get(NodeFragTribKnQualityMetaData.column_FACILITATOR_QUALITY))));
		aNodeFragTribKnQuality.setFacilitatorQualityTimestamp(GcgDateHelper.getDateFromIso8601DateString(aCursor.getString(aHashMap.get(NodeFragTribKnQualityMetaData.column_FACILITATOR_QUALITY_TIMESTAMP))));
		aNodeFragTribKnQuality.setParentFractalsQuality(FmsDecoratorProjectManagement.getObjectForName(aCursor.getString(aHashMap.get(NodeFragTribKnQualityMetaData.column_PARENT_FRACTALS_QUALITY))));
		aNodeFragTribKnQuality.setParentFractalsQualityTimestamp(GcgDateHelper.getDateFromIso8601DateString(aCursor.getString(aHashMap.get(NodeFragTribKnQualityMetaData.column_PARENT_FRACTALS_QUALITY_TIMESTAMP))));
		aNodeFragTribKnQuality.setChildFractalsQuality(FmsDecoratorWorkBreakdown.getObjectForName(aCursor.getString(aHashMap.get(NodeFragTribKnQualityMetaData.column_CHILD_FRACTALS_QUALITY))));
		aNodeFragTribKnQuality.setChildFractalsQualityTimestamp(GcgDateHelper.getDateFromIso8601DateString(aCursor.getString(aHashMap.get(NodeFragTribKnQualityMetaData.column_CHILD_FRACTALS_QUALITY_TIMESTAMP))));
		aNodeFragTribKnQuality.setCadenceCommitmentQuality(FmsDecoratorCadenceCommitment.getObjectForName(aCursor.getString(aHashMap.get(NodeFragTribKnQualityMetaData.column_CADENCE_COMMITMENT_QUALITY))));
		aNodeFragTribKnQuality.setCadenceCommitmentQualityTimestamp(GcgDateHelper.getDateFromIso8601DateString(aCursor.getString(aHashMap.get(NodeFragTribKnQualityMetaData.column_CADENCE_COMMITMENT_QUALITY_TIMESTAMP))));
		aNodeFragTribKnQuality.setTaskPointsBudgetQuality(FmsDecoratorWorkTaskBudget.getObjectForName(aCursor.getString(aHashMap.get(NodeFragTribKnQualityMetaData.column_TASK_POINTS_BUDGET_QUALITY))));
		aNodeFragTribKnQuality.setTaskPointsBudgetQualityTimestamp(GcgDateHelper.getDateFromIso8601DateString(aCursor.getString(aHashMap.get(NodeFragTribKnQualityMetaData.column_TASK_POINTS_BUDGET_QUALITY_TIMESTAMP))));
		aNodeFragTribKnQuality.setWorkTeamQuality(FmsDecoratorWorkTeam.getObjectForName(aCursor.getString(aHashMap.get(NodeFragTribKnQualityMetaData.column_WORK_TEAM_QUALITY))));
		aNodeFragTribKnQuality.setWorkTeamQualityTimestamp(GcgDateHelper.getDateFromIso8601DateString(aCursor.getString(aHashMap.get(NodeFragTribKnQualityMetaData.column_WORK_TEAM_QUALITY_TIMESTAMP))));
		aNodeFragTribKnQuality.setStrategicCommitmentQuality(FmsDecoratorStrategicCommitment.getObjectForName(aCursor.getString(aHashMap.get(NodeFragTribKnQualityMetaData.column_STRATEGIC_COMMITMENT_QUALITY))));
		aNodeFragTribKnQuality.setStrategicCommitmentQualityTimestamp(GcgDateHelper.getDateFromIso8601DateString(aCursor.getString(aHashMap.get(NodeFragTribKnQualityMetaData.column_STRATEGIC_COMMITMENT_QUALITY_TIMESTAMP))));
		aNodeFragTribKnQuality.setStoryQuality(FmsDecoratorStory.getObjectForName(aCursor.getString(aHashMap.get(NodeFragTribKnQualityMetaData.column_STORY_QUALITY))));
		aNodeFragTribKnQuality.setStoryQualityTimestamp(GcgDateHelper.getDateFromIso8601DateString(aCursor.getString(aHashMap.get(NodeFragTribKnQualityMetaData.column_STORY_QUALITY_TIMESTAMP))));
		aNodeFragTribKnQuality.setSequenceQuality(FmsDecoratorSequence.getObjectForName(aCursor.getString(aHashMap.get(NodeFragTribKnQualityMetaData.column_SEQUENCE_QUALITY))));
		aNodeFragTribKnQuality.setSequenceQualityTimestamp(GcgDateHelper.getDateFromIso8601DateString(aCursor.getString(aHashMap.get(NodeFragTribKnQualityMetaData.column_SEQUENCE_QUALITY_TIMESTAMP))));
		aNodeFragTribKnQuality.setCompletionQuality(FmsDecoratorCompletion.getObjectForName(aCursor.getString(aHashMap.get(NodeFragTribKnQualityMetaData.column_COMPLETION_QUALITY))));
		aNodeFragTribKnQuality.setCompletionQualityTimestamp(GcgDateHelper.getDateFromIso8601DateString(aCursor.getString(aHashMap.get(NodeFragTribKnQualityMetaData.column_COMPLETION_QUALITY_TIMESTAMP))));
	}

	@Override
	protected NodeFragTribKnQuality getNextObjectFromCursor(Cursor aCursor) {
		NodeFragTribKnQuality theNodeFragTribKnQuality = null;
		theNodeFragTribKnQuality = new NodeFragTribKnQuality(
				aCursor.getString(this.columnIndexMap.get(IdNodeMetaData.column_ID)),
				aCursor.getString(this.columnIndexMap.get(NodeFragMetaData.column_PARENT_ID)) );
		getColumnValues(this.columnIndexMap, aCursor, theNodeFragTribKnQuality);
		return theNodeFragTribKnQuality;
	}

	@Override
	public ContentValues buildContentValues(NodeFragTribKnQuality aNodeFragTribKnQuality) {
		ContentValues theContentValues = super.buildContentValues(aNodeFragTribKnQuality);
		theContentValues.put(NodeFragTribKnQualityMetaData.column_GOVERNANCE_QUALITY, aNodeFragTribKnQuality.getGovernanceQuality().getName());
		GcgDateHelper.setIso8601DateContentValue(theContentValues, NodeFragTribKnQualityMetaData.column_GOVERNANCE_QUALITY_TIMESTAMP, aNodeFragTribKnQuality.getGovernanceQualityTimestamp());
		theContentValues.put(NodeFragTribKnQualityMetaData.column_FACILITATION_ISSUE_QUALITY, aNodeFragTribKnQuality.getFacilitationIssueQuality().getName());
		GcgDateHelper.setIso8601DateContentValue(theContentValues, NodeFragTribKnQualityMetaData.column_FACILITATION_ISSUE_QUALITY_TIMESTAMP, aNodeFragTribKnQuality.getFacilitationIssueQualityTimestamp());
		theContentValues.put(NodeFragTribKnQualityMetaData.column_FACILITATOR_QUALITY, aNodeFragTribKnQuality.getFacilitatorQuality().getName());
		GcgDateHelper.setIso8601DateContentValue(theContentValues, NodeFragTribKnQualityMetaData.column_FACILITATOR_QUALITY_TIMESTAMP, aNodeFragTribKnQuality.getFacilitatorQualityTimestamp());
		theContentValues.put(NodeFragTribKnQualityMetaData.column_PARENT_FRACTALS_QUALITY, aNodeFragTribKnQuality.getParentFractalsQuality().getName());
		GcgDateHelper.setIso8601DateContentValue(theContentValues, NodeFragTribKnQualityMetaData.column_PARENT_FRACTALS_QUALITY_TIMESTAMP, aNodeFragTribKnQuality.getParentFractalsQualityTimestamp());
		theContentValues.put(NodeFragTribKnQualityMetaData.column_CHILD_FRACTALS_QUALITY, aNodeFragTribKnQuality.getChildFractalsQuality().getName());
		GcgDateHelper.setIso8601DateContentValue(theContentValues, NodeFragTribKnQualityMetaData.column_CHILD_FRACTALS_QUALITY_TIMESTAMP, aNodeFragTribKnQuality.getChildFractalsQualityTimestamp());
		theContentValues.put(NodeFragTribKnQualityMetaData.column_CADENCE_COMMITMENT_QUALITY, aNodeFragTribKnQuality.getCadenceCommitmentQuality().getName());
		GcgDateHelper.setIso8601DateContentValue(theContentValues, NodeFragTribKnQualityMetaData.column_CADENCE_COMMITMENT_QUALITY_TIMESTAMP, aNodeFragTribKnQuality.getCadenceCommitmentQualityTimestamp());
		theContentValues.put(NodeFragTribKnQualityMetaData.column_TASK_POINTS_BUDGET_QUALITY, aNodeFragTribKnQuality.getTaskPointsBudgetQuality().getName());
		GcgDateHelper.setIso8601DateContentValue(theContentValues, NodeFragTribKnQualityMetaData.column_TASK_POINTS_BUDGET_QUALITY_TIMESTAMP, aNodeFragTribKnQuality.getTaskPointsBudgetQualityTimestamp());
		theContentValues.put(NodeFragTribKnQualityMetaData.column_WORK_TEAM_QUALITY, aNodeFragTribKnQuality.getWorkTeamQuality().getName());
		GcgDateHelper.setIso8601DateContentValue(theContentValues, NodeFragTribKnQualityMetaData.column_WORK_TEAM_QUALITY_TIMESTAMP, aNodeFragTribKnQuality.getWorkTeamQualityTimestamp());
		theContentValues.put(NodeFragTribKnQualityMetaData.column_STRATEGIC_COMMITMENT_QUALITY, aNodeFragTribKnQuality.getStrategicCommitmentQuality().getName());
		GcgDateHelper.setIso8601DateContentValue(theContentValues, NodeFragTribKnQualityMetaData.column_STRATEGIC_COMMITMENT_QUALITY_TIMESTAMP, aNodeFragTribKnQuality.getStrategicCommitmentQualityTimestamp());
		theContentValues.put(NodeFragTribKnQualityMetaData.column_STORY_QUALITY, aNodeFragTribKnQuality.getStoryQuality().getName());
		GcgDateHelper.setIso8601DateContentValue(theContentValues, NodeFragTribKnQualityMetaData.column_STORY_QUALITY_TIMESTAMP, aNodeFragTribKnQuality.getStoryQualityTimestamp());
		theContentValues.put(NodeFragTribKnQualityMetaData.column_SEQUENCE_QUALITY, aNodeFragTribKnQuality.getSequenceQuality().getName());
		GcgDateHelper.setIso8601DateContentValue(theContentValues, NodeFragTribKnQualityMetaData.column_SEQUENCE_QUALITY_TIMESTAMP, aNodeFragTribKnQuality.getSequenceQualityTimestamp());
		theContentValues.put(NodeFragTribKnQualityMetaData.column_COMPLETION_QUALITY, aNodeFragTribKnQuality.getCompletionQuality().getName());
		GcgDateHelper.setIso8601DateContentValue(theContentValues, NodeFragTribKnQualityMetaData.column_COMPLETION_QUALITY_TIMESTAMP, aNodeFragTribKnQuality.getCompletionQualityTimestamp());
		return theContentValues;
	}

}
