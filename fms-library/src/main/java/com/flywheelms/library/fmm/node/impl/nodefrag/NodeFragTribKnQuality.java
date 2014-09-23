/* @(#)NodeFragTribKnQuality.java
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

package com.flywheelms.library.fmm.node.impl.nodefrag;

import com.flywheelms.gcongui.deckangl.enumerator.DecKanGlDecoratorCanvasLocation;
import com.flywheelms.gcongui.deckangl.interfaces.DecKanGlDecorator;
import com.flywheelms.gcongui.gcg.widget.date.GcgDateHelper;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorChildFractals;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorCompletion;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorFacilitationIssue;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorFacilitator;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorFlywheelCommitment;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorGovernance;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorParentFractals;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorSequence;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorStory;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorStrategicCommitment;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorWorkTaskBudget;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorWorkTeam;
import com.flywheelms.library.fmm.meta_data.NodeFragTribKnQualityMetaData;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmNodeQuality;
import com.flywheelms.library.util.JsonHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;

public class NodeFragTribKnQuality extends FmmNodeFragImpl implements FmmNodeQuality {

	private FmsDecoratorGovernance governanceQuality;
	private Date governanceQualityTimestamp;
	private FmsDecoratorFacilitationIssue facilitationIssueQuality;
	private Date facilitationIssueQualityTimestamp;
	private FmsDecoratorFacilitator facilitatorQuality;
	private Date facilitatorQualityTimestamp;
	private FmsDecoratorParentFractals parentFractalsQuality;
	private Date parentFractalsQualityTimestamp;
	private FmsDecoratorChildFractals childFractalsQuality;
	private Date childFractalsQualityTimestamp;
	private FmsDecoratorFlywheelCommitment flywheelCommitmentQuality;
	private Date flywheelCommitmentQualityTimestamp;
	private FmsDecoratorWorkTaskBudget workTaskBudgetQuality;
	private Date workTaskBudgetQualityTimestamp;
	private FmsDecoratorWorkTeam workTeamQuality;
	private Date workTeamQualityTimestamp;
	private FmsDecoratorStrategicCommitment strategicCommitmentQuality;
	private Date strategicCommitmentQualityTimestamp;
	private FmsDecoratorStory storyQuality;
	private Date storyQualityTimestamp;
	private FmsDecoratorSequence sequenceQuality;
	private Date sequenceQualityTimestamp;
	private FmsDecoratorCompletion completionQuality;
	private Date completionQualityTimestamp;

	public NodeFragTribKnQuality(String aParentNodeIdString) {
		super(NodeFragTribKnQuality.class, aParentNodeIdString);
		// TODO Auto-generated constructor stub
	}

	// rehydrate
	public NodeFragTribKnQuality(String anExistingNodeFragNodeIdString, String aParentNodeIdString) {
		super(NodeId.hydrate(NodeFragTribKnQuality.class, anExistingNodeFragNodeIdString), aParentNodeIdString);
	}

	// rehydrate from serialization
	public NodeFragTribKnQuality(JSONObject aJsonObject) {
		super(NodeFragTribKnQuality.class, aJsonObject);
		try {
			setGovernanceQuality(FmsDecoratorGovernance.getObjectForName(aJsonObject.getString(NodeFragTribKnQualityMetaData.column_GOVERNANCE_QUALITY)));
			setGovernanceQualityTimestamp(JsonHelper.getDate(aJsonObject, NodeFragTribKnQualityMetaData.column_GOVERNANCE_QUALITY_TIMESTAMP));
			setFacilitationIssueQuality(FmsDecoratorFacilitationIssue.getObjectForName(aJsonObject.getString(NodeFragTribKnQualityMetaData.column_FACILITATION_ISSUE_QUALITY)));
			setFacilitationIssueQualityTimestamp(JsonHelper.getDate(aJsonObject, NodeFragTribKnQualityMetaData.column_FACILITATION_ISSUE_QUALITY_TIMESTAMP));
			setFacilitatorQuality(FmsDecoratorFacilitator.getObjectForName(aJsonObject.getString(NodeFragTribKnQualityMetaData.column_FACILITATOR_QUALITY)));
			setFacilitatorQualityTimestamp(JsonHelper.getDate(aJsonObject, NodeFragTribKnQualityMetaData.column_FACILITATOR_QUALITY_TIMESTAMP));
			setParentFractalsQuality(FmsDecoratorParentFractals.getObjectForName(aJsonObject.getString(NodeFragTribKnQualityMetaData.column_PARENT_FRACTALS_QUALITY)));
			setParentFractalsQualityTimestamp(JsonHelper.getDate(aJsonObject, NodeFragTribKnQualityMetaData.column_PARENT_FRACTALS_QUALITY_TIMESTAMP));
			setChildFractalsQuality(FmsDecoratorChildFractals.getObjectForName(aJsonObject.getString(NodeFragTribKnQualityMetaData.column_CHILD_FRACTALS_QUALITY)));
			setChildFractalsQualityTimestamp(JsonHelper.getDate(aJsonObject, NodeFragTribKnQualityMetaData.column_CHILD_FRACTALS_QUALITY_TIMESTAMP));
			setFlywheelCommitmentQuality(FmsDecoratorFlywheelCommitment.getObjectForName(aJsonObject.getString(NodeFragTribKnQualityMetaData.column_FLYWHEEL_COMMITMENT_QUALITY)));
			setFlywheelCommitmentQualityTimestamp(JsonHelper.getDate(aJsonObject, NodeFragTribKnQualityMetaData.column_FLYWHEEL_COMMITMENT_QUALITY_TIMESTAMP));
			setWorkTaskBudgetQuality(FmsDecoratorWorkTaskBudget.getObjectForName(aJsonObject.getString(NodeFragTribKnQualityMetaData.column_WORK_TASK_BUDGET_QUALITY)));
			setWorkTaskBudgetQualityTimestamp(JsonHelper.getDate(aJsonObject, NodeFragTribKnQualityMetaData.column_WORK_TASK_BUDGET_QUALITY_TIMESTAMP));
			setWorkTeamQuality(FmsDecoratorWorkTeam.getObjectForName(aJsonObject.getString(NodeFragTribKnQualityMetaData.column_WORK_TEAM_QUALITY)));
			setWorkTeamQualityTimestamp(JsonHelper.getDate(aJsonObject, NodeFragTribKnQualityMetaData.column_WORK_TEAM_QUALITY_TIMESTAMP));
			setStrategicCommitmentQuality(FmsDecoratorStrategicCommitment.getObjectForName(aJsonObject.getString(NodeFragTribKnQualityMetaData.column_STRATEGIC_COMMITMENT_QUALITY)));
			setStrategicCommitmentQualityTimestamp(JsonHelper.getDate(aJsonObject, NodeFragTribKnQualityMetaData.column_STRATEGIC_COMMITMENT_QUALITY_TIMESTAMP));
			setStoryQuality(FmsDecoratorStory.getObjectForName(aJsonObject.getString(NodeFragTribKnQualityMetaData.column_STORY_QUALITY)));
			setStoryQualityTimestamp(JsonHelper.getDate(aJsonObject, NodeFragTribKnQualityMetaData.column_STORY_QUALITY_TIMESTAMP));
			setSequenceQuality(FmsDecoratorSequence.getObjectForName(aJsonObject.getString(NodeFragTribKnQualityMetaData.column_SEQUENCE_QUALITY)));
			setSequenceQualityTimestamp(JsonHelper.getDate(aJsonObject, NodeFragTribKnQualityMetaData.column_SEQUENCE_QUALITY_TIMESTAMP));
			setCompletionQuality(FmsDecoratorCompletion.getObjectForName(aJsonObject.getString(NodeFragTribKnQualityMetaData.column_COMPLETION_QUALITY)));
			setCompletionQualityTimestamp(JsonHelper.getDate(aJsonObject, NodeFragTribKnQualityMetaData.column_COMPLETION_QUALITY_TIMESTAMP));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public NodeFragTribKnQuality(FmmHeadlineNode aHeadlineNode) {
		super(NodeFragTribKnQuality.class, aHeadlineNode.getNodeIdString());
		HashMap<DecKanGlDecoratorCanvasLocation, DecKanGlDecorator> theDecoratorMap = aHeadlineNode.getUpdatedDecKanGlDecoratorMap();
		Date theTimestamp = GcgDateHelper.getCurrentDateTime();
		setGovernanceQuality((FmsDecoratorGovernance) theDecoratorMap.get(FmsDecoratorGovernance.getStaticInstance().getDecoratorCanvasLocation()));
		setGovernanceQualityTimestamp(theTimestamp);
		setFacilitationIssueQuality((FmsDecoratorFacilitationIssue) theDecoratorMap.get(FmsDecoratorFacilitationIssue.getStaticInstance().getDecoratorCanvasLocation()));
		setFacilitationIssueQualityTimestamp(theTimestamp);
		setFacilitatorQuality((FmsDecoratorFacilitator) theDecoratorMap.get(FmsDecoratorFacilitator.getStaticInstance().getDecoratorCanvasLocation()));
		setFacilitatorQualityTimestamp(theTimestamp);
		setParentFractalsQuality((FmsDecoratorParentFractals) theDecoratorMap.get(FmsDecoratorParentFractals.getStaticInstance().getDecoratorCanvasLocation()));
		setParentFractalsQualityTimestamp(theTimestamp);
		setChildFractalsQuality((FmsDecoratorChildFractals) theDecoratorMap.get(FmsDecoratorChildFractals.getStaticInstance().getDecoratorCanvasLocation()));
		setChildFractalsQualityTimestamp(theTimestamp);
		setFlywheelCommitmentQuality((FmsDecoratorFlywheelCommitment) theDecoratorMap.get(FmsDecoratorFlywheelCommitment.getStaticInstance().getDecoratorCanvasLocation()));
		setFlywheelCommitmentQualityTimestamp(theTimestamp);
		setWorkTaskBudgetQuality((FmsDecoratorWorkTaskBudget) theDecoratorMap.get(FmsDecoratorWorkTaskBudget.getStaticInstance().getDecoratorCanvasLocation()));
		setWorkTaskBudgetQualityTimestamp(theTimestamp);
		setWorkTeamQuality((FmsDecoratorWorkTeam) theDecoratorMap.get(FmsDecoratorWorkTeam.getStaticInstance().getDecoratorCanvasLocation()));
		setWorkTeamQualityTimestamp(theTimestamp);
		setStrategicCommitmentQuality((FmsDecoratorStrategicCommitment) theDecoratorMap.get(FmsDecoratorStrategicCommitment.getStaticInstance().getDecoratorCanvasLocation()));
		setStrategicCommitmentQualityTimestamp(theTimestamp);
		setStoryQuality((FmsDecoratorStory) theDecoratorMap.get(FmsDecoratorStory.getStaticInstance().getDecoratorCanvasLocation()));
		setStoryQualityTimestamp(theTimestamp);
		setSequenceQuality((FmsDecoratorSequence) theDecoratorMap.get(FmsDecoratorSequence.getStaticInstance().getDecoratorCanvasLocation()));
		setSequenceQualityTimestamp(theTimestamp);
		setCompletionQuality((FmsDecoratorCompletion) theDecoratorMap.get(FmsDecoratorCompletion.getStaticInstance().getDecoratorCanvasLocation()));
		setCompletionQualityTimestamp(theTimestamp);
	}

	@Override
	public JSONObject getJsonObject() {
		JSONObject theJsonObject = super.getJsonObject();
		try {
			theJsonObject.put(NodeFragTribKnQualityMetaData.column_GOVERNANCE_QUALITY, getGovernanceQuality().getName());
			JsonHelper.putDate(theJsonObject, NodeFragTribKnQualityMetaData.column_GOVERNANCE_QUALITY_TIMESTAMP, getGovernanceQualityTimestamp());
			theJsonObject.put(NodeFragTribKnQualityMetaData.column_FACILITATION_ISSUE_QUALITY, getFacilitationIssueQuality().getName());
			JsonHelper.putDate(theJsonObject, NodeFragTribKnQualityMetaData.column_FACILITATION_ISSUE_QUALITY_TIMESTAMP, getFacilitationIssueQualityTimestamp());
			theJsonObject.put(NodeFragTribKnQualityMetaData.column_FACILITATOR_QUALITY, getFacilitatorQuality().getName());
			JsonHelper.putDate(theJsonObject, NodeFragTribKnQualityMetaData.column_FACILITATOR_QUALITY_TIMESTAMP, getFacilitatorQualityTimestamp());
			theJsonObject.put(NodeFragTribKnQualityMetaData.column_PARENT_FRACTALS_QUALITY, getParentFractalsQuality().getName());
			JsonHelper.putDate(theJsonObject, NodeFragTribKnQualityMetaData.column_PARENT_FRACTALS_QUALITY_TIMESTAMP, getParentFractalsQualityTimestamp());
			theJsonObject.put(NodeFragTribKnQualityMetaData.column_CHILD_FRACTALS_QUALITY, getChildFractalsQuality().getName());
			JsonHelper.putDate(theJsonObject, NodeFragTribKnQualityMetaData.column_CHILD_FRACTALS_QUALITY_TIMESTAMP, getChildFractalsQualityTimestamp());
			theJsonObject.put(NodeFragTribKnQualityMetaData.column_FLYWHEEL_COMMITMENT_QUALITY, getFlywheelCommitmentQuality().getName());
			JsonHelper.putDate(theJsonObject, NodeFragTribKnQualityMetaData.column_FLYWHEEL_COMMITMENT_QUALITY_TIMESTAMP, getFlywheelCommitmentQualityTimestamp());
			theJsonObject.put(NodeFragTribKnQualityMetaData.column_WORK_TASK_BUDGET_QUALITY, getWorkTaskBudgetQuality().getName());
			JsonHelper.putDate(theJsonObject, NodeFragTribKnQualityMetaData.column_WORK_TASK_BUDGET_QUALITY_TIMESTAMP, getWorkTaskBudgetQualityTimestamp());
			theJsonObject.put(NodeFragTribKnQualityMetaData.column_WORK_TEAM_QUALITY, getWorkTeamQuality().getName());
			JsonHelper.putDate(theJsonObject, NodeFragTribKnQualityMetaData.column_WORK_TEAM_QUALITY_TIMESTAMP, getWorkTeamQualityTimestamp());
			theJsonObject.put(NodeFragTribKnQualityMetaData.column_STRATEGIC_COMMITMENT_QUALITY, getStrategicCommitmentQuality().getName());
			JsonHelper.putDate(theJsonObject, NodeFragTribKnQualityMetaData.column_STRATEGIC_COMMITMENT_QUALITY_TIMESTAMP, getStrategicCommitmentQualityTimestamp());
			theJsonObject.put(NodeFragTribKnQualityMetaData.column_STORY_QUALITY, getStoryQuality().getName());
			JsonHelper.putDate(theJsonObject, NodeFragTribKnQualityMetaData.column_STORY_QUALITY_TIMESTAMP, getStoryQualityTimestamp());
			theJsonObject.put(NodeFragTribKnQualityMetaData.column_SEQUENCE_QUALITY, getSequenceQuality().getName());
			JsonHelper.putDate(theJsonObject, NodeFragTribKnQualityMetaData.column_SEQUENCE_QUALITY_TIMESTAMP, getSequenceQualityTimestamp());
			theJsonObject.put(NodeFragTribKnQualityMetaData.column_COMPLETION_QUALITY, getCompletionQuality().getName());
			JsonHelper.putDate(theJsonObject, NodeFragTribKnQualityMetaData.column_COMPLETION_QUALITY_TIMESTAMP, getCompletionQualityTimestamp());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return theJsonObject;
	}
	
	public HashMap<DecKanGlDecoratorCanvasLocation, DecKanGlDecorator> getDecoratorMap() {
		HashMap<DecKanGlDecoratorCanvasLocation, DecKanGlDecorator> theDecKanGlDecoratorMap =
				new HashMap<DecKanGlDecoratorCanvasLocation, DecKanGlDecorator>();
		theDecKanGlDecoratorMap.put(
				getGovernanceQuality().getDecoratorCanvasLocation(),
				getGovernanceQuality() );
		theDecKanGlDecoratorMap.put(
				getFacilitatorQuality().getDecoratorCanvasLocation(),
				getFacilitatorQuality() );
		theDecKanGlDecoratorMap.put(
				getFacilitationIssueQuality().getDecoratorCanvasLocation(),
				getFacilitationIssueQuality() );
		theDecKanGlDecoratorMap.put(
				getParentFractalsQuality().getDecoratorCanvasLocation(),
				getParentFractalsQuality() );
		theDecKanGlDecoratorMap.put(
				getChildFractalsQuality().getDecoratorCanvasLocation(),
				getChildFractalsQuality() );
		theDecKanGlDecoratorMap.put(
				getStrategicCommitmentQuality().getDecoratorCanvasLocation(),
				getStrategicCommitmentQuality() );
		theDecKanGlDecoratorMap.put(
				getFlywheelCommitmentQuality().getDecoratorCanvasLocation(),
				getFlywheelCommitmentQuality() );
		theDecKanGlDecoratorMap.put(
				getWorkTaskBudgetQuality().getDecoratorCanvasLocation(),
				getWorkTaskBudgetQuality() );
		theDecKanGlDecoratorMap.put(
				getWorkTeamQuality().getDecoratorCanvasLocation(),
				getWorkTeamQuality() );
		theDecKanGlDecoratorMap.put(
				getStoryQuality().getDecoratorCanvasLocation(),
				getStoryQuality() );
		theDecKanGlDecoratorMap.put(
				getSequenceQuality().getDecoratorCanvasLocation(),
				getSequenceQuality() );
		theDecKanGlDecoratorMap.put(
				getCompletionQuality().getDecoratorCanvasLocation(),
				getCompletionQuality() );
		return theDecKanGlDecoratorMap;
	}

	public FmsDecoratorGovernance getGovernanceQuality() {
		return this.governanceQuality;
	}

	public void setGovernanceQuality(FmsDecoratorGovernance governanceQuality) {
		this.governanceQuality = governanceQuality;
	}

	public Date getGovernanceQualityTimestamp() {
		return this.governanceQualityTimestamp;
	}

	public void setGovernanceQualityTimestamp(Date governanceQualityTimestamp) {
		this.governanceQualityTimestamp = governanceQualityTimestamp;
	}

	public FmsDecoratorFacilitationIssue getFacilitationIssueQuality() {
		return this.facilitationIssueQuality;
	}

	public void setFacilitationIssueQuality(FmsDecoratorFacilitationIssue facilitationIssueQuality) {
		this.facilitationIssueQuality = facilitationIssueQuality;
	}

	public Date getFacilitationIssueQualityTimestamp() {
		return this.facilitationIssueQualityTimestamp;
	}

	public void setFacilitationIssueQualityTimestamp(Date facilitationIssueQualityTimestamp) {
		this.facilitationIssueQualityTimestamp = facilitationIssueQualityTimestamp;
	}

	public FmsDecoratorFacilitator getFacilitatorQuality() {
		return this.facilitatorQuality;
	}

	public void setFacilitatorQuality(FmsDecoratorFacilitator facilitatorQuality) {
		this.facilitatorQuality = facilitatorQuality;
	}

	public Date getFacilitatorQualityTimestamp() {
		return this.facilitatorQualityTimestamp;
	}

	public void setFacilitatorQualityTimestamp(Date facilitatorQualityTimestamp) {
		this.facilitatorQualityTimestamp = facilitatorQualityTimestamp;
	}

	public FmsDecoratorParentFractals getParentFractalsQuality() {
		return this.parentFractalsQuality;
	}

	public void setParentFractalsQuality(
			FmsDecoratorParentFractals parentFractalsQuality) {
		this.parentFractalsQuality = parentFractalsQuality;
	}

	public Date getParentFractalsQualityTimestamp() {
		return this.parentFractalsQualityTimestamp;
	}

	public void setParentFractalsQualityTimestamp(
			Date parentFractalsQualityTimestamp) {
		this.parentFractalsQualityTimestamp = parentFractalsQualityTimestamp;
	}

	public FmsDecoratorChildFractals getChildFractalsQuality() {
		return this.childFractalsQuality;
	}

	public void setChildFractalsQuality(
			FmsDecoratorChildFractals childFractalsQuality) {
		this.childFractalsQuality = childFractalsQuality;
	}

	public Date getChildFractalsQualityTimestamp() {
		return this.childFractalsQualityTimestamp;
	}

	public void setChildFractalsQualityTimestamp(Date childFractalsQualityTimestamp) {
		this.childFractalsQualityTimestamp = childFractalsQualityTimestamp;
	}

	public FmsDecoratorFlywheelCommitment getFlywheelCommitmentQuality() {
		return this.flywheelCommitmentQuality;
	}

	public void setFlywheelCommitmentQuality(
			FmsDecoratorFlywheelCommitment flywheelCommitmentQuality) {
		this.flywheelCommitmentQuality = flywheelCommitmentQuality;
	}

	public Date getFlywheelCommitmentQualityTimestamp() {
		return this.flywheelCommitmentQualityTimestamp;
	}

	public void setFlywheelCommitmentQualityTimestamp(
			Date flywheelCommitmentQualityTimestamp) {
		this.flywheelCommitmentQualityTimestamp = flywheelCommitmentQualityTimestamp;
	}

	public FmsDecoratorWorkTaskBudget getWorkTaskBudgetQuality() {
		return this.workTaskBudgetQuality;
	}

	public void setWorkTaskBudgetQuality(
			FmsDecoratorWorkTaskBudget workTaskBudgetQuality) {
		this.workTaskBudgetQuality = workTaskBudgetQuality;
	}

	public Date getWorkTaskBudgetQualityTimestamp() {
		return this.workTaskBudgetQualityTimestamp;
	}

	public void setWorkTaskBudgetQualityTimestamp(
			Date workTaskBudgetQualityTimestamp) {
		this.workTaskBudgetQualityTimestamp = workTaskBudgetQualityTimestamp;
	}

	public FmsDecoratorWorkTeam getWorkTeamQuality() {
		return this.workTeamQuality;
	}

	public void setWorkTeamQuality(FmsDecoratorWorkTeam workTeamQuality) {
		this.workTeamQuality = workTeamQuality;
	}

	public Date getWorkTeamQualityTimestamp() {
		return this.workTeamQualityTimestamp;
	}

	public void setWorkTeamQualityTimestamp(Date workTeamQualityTimestamp) {
		this.workTeamQualityTimestamp = workTeamQualityTimestamp;
	}

	public FmsDecoratorStrategicCommitment getStrategicCommitmentQuality() {
		return this.strategicCommitmentQuality;
	}

	public void setStrategicCommitmentQuality(
			FmsDecoratorStrategicCommitment strategicCommitmentQuality) {
		this.strategicCommitmentQuality = strategicCommitmentQuality;
	}

	public Date getStrategicCommitmentQualityTimestamp() {
		return this.strategicCommitmentQualityTimestamp;
	}

	public void setStrategicCommitmentQualityTimestamp(
			Date strategicCommitmentQualityTimestamp) {
		this.strategicCommitmentQualityTimestamp = strategicCommitmentQualityTimestamp;
	}

	public FmsDecoratorStory getStoryQuality() {
		return this.storyQuality;
	}

	public void setStoryQuality(FmsDecoratorStory storyQuality) {
		this.storyQuality = storyQuality;
	}

	public Date getStoryQualityTimestamp() {
		return this.storyQualityTimestamp;
	}

	public void setStoryQualityTimestamp(Date storyQualityTimestamp) {
		this.storyQualityTimestamp = storyQualityTimestamp;
	}
	
	public FmsDecoratorSequence getSequenceQuality() {
		return this.sequenceQuality;
	}

	public void setSequenceQuality(FmsDecoratorSequence sequenceQuality) {
		this.sequenceQuality = sequenceQuality;
	}

	public Date getSequenceQualityTimestamp() {
		return this.sequenceQualityTimestamp;
	}

	public void setSequenceQualityTimestamp(Date sequenceQualityTimestamp) {
		this.sequenceQualityTimestamp = sequenceQualityTimestamp;
	}

	public FmsDecoratorCompletion getCompletionQuality() {
		return this.completionQuality;
	}

	public void setCompletionQuality(FmsDecoratorCompletion completionQuality) {
		this.completionQuality = completionQuality;
	}

	public Date getCompletionQualityTimestamp() {
		return this.completionQualityTimestamp;
	}

	public void setCompletionQualityTimestamp(Date completionQualityTimestamp) {
		this.completionQualityTimestamp = completionQualityTimestamp;
	}

}
