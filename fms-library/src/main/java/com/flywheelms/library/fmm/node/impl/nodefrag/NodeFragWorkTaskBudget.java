/* @(#)NodeFragWorkTaskBudget.java
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

import com.flywheelms.gcongui.gcg.widget.date.GcgDateHelper;
import com.flywheelms.library.fmm.meta_data.NodeFragWorkTaskBudgetMetaData;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.governable.CommunityMember;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmNodeWorkTaskBudget;
import com.flywheelms.library.util.JsonHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class NodeFragWorkTaskBudget extends FmmNodeFragLockableImpl
		implements FmmNodeWorkTaskBudget {
	
	private int estimatedTotalTaskCount = 0;
	private float estimatedAverageHoursPerTask = 1.5f;
	private String swagByNodeIdString;
	private CommunityMember swagByCommunityMember;
	private Date swagTimestamp = GcgDateHelper.NULL_DATE;
	private String estimateConfirmedByNodeIdString;
	private CommunityMember estimateConfirmedByCommunityMember;
	private Date estimateConfirmedTimestamp = GcgDateHelper.NULL_DATE;
	private int budgetedTotalTaskCount;
	private float budgetedAverageHoursPerTask = 1.5f;
	private String budgetProposedByNodeIdString;
	private CommunityMember budgetProposedByCommunityMember;
	private Date budgetProposedTimestamp = GcgDateHelper.NULL_DATE;
	private String budgetConfirmedByNodeIdString;
	private CommunityMember budgetConfirmedByCommunityMember;
	private Date budgetConfirmedTimestamp = GcgDateHelper.NULL_DATE;
	private int workBreakdownTotalTaskCount;
	private float workBreakdownAverageHoursPerTask = 0.0f;
	private int taskCountAfterCompletion;
	private float averageHoursPerTaskAfterCompletion = 0.0f;

	public NodeFragWorkTaskBudget(String aParentNodeIdString) {
		super(NodeFragWorkTaskBudget.class, aParentNodeIdString);
	}

	// rehydrate from database
	public NodeFragWorkTaskBudget(String anExistingNodeFragNodeIdString, String aParentNodeIdString) {
		super(NodeId.hydrate(NodeFragWorkTaskBudget.class, anExistingNodeFragNodeIdString), aParentNodeIdString);
	}

	// rehydrate from serialization
	public NodeFragWorkTaskBudget(JSONObject aJsonObject) {
		super(NodeFragWorkTaskBudget.class, aJsonObject);
		try {
			setEstimatedTotalTaskCount(aJsonObject.getInt(NodeFragWorkTaskBudgetMetaData.column_ESTIMATED_TOTAL_TASK_COUNT));
			setEstimatedAverageHoursPerTask(aJsonObject.getDouble(NodeFragWorkTaskBudgetMetaData.column_ESTIMATED_AVERAGE_HOURS_PER_TASK));
			setSwagBy(JsonHelper.getString(aJsonObject, NodeFragWorkTaskBudgetMetaData.column_SWAG_BY));
			setSwagTimestamp(JsonHelper.getDate(aJsonObject, NodeFragWorkTaskBudgetMetaData.column_SWAG_TIMESTAMP));
			setEstimateConfirmedBy(JsonHelper.getString(aJsonObject, NodeFragWorkTaskBudgetMetaData.column_ESTIMATE_CONFIRMED_BY));
			setEstimateConfirmedTimestamp(JsonHelper.getDate(aJsonObject, NodeFragWorkTaskBudgetMetaData.column_ESTIMATE_CONFIRMED_TIMESTAMP));
			setBudgetedTotalTaskCount(aJsonObject.getInt(NodeFragWorkTaskBudgetMetaData.column_BUDGETED_TOTAL_TASK_COUNT));
			setBudgetedAverageHoursPerTask(aJsonObject.getDouble(NodeFragWorkTaskBudgetMetaData.column_BUDGETED_AVERAGE_HOURS_PER_TASK));
			setBudgetProposedBy(JsonHelper.getString(aJsonObject, NodeFragWorkTaskBudgetMetaData.column_BUDGET_PROPOSED_BY));
			setBudgetProposedTimestamp(JsonHelper.getDate(aJsonObject, NodeFragWorkTaskBudgetMetaData.column_BUDGET_PROPOSED_TIMESTAMP));
			setBudgetConfirmedBy(JsonHelper.getString(aJsonObject, NodeFragWorkTaskBudgetMetaData.column_BUDGET_CONFIRMED_BY));
			setBudgetConfirmedTimestamp(JsonHelper.getDate(aJsonObject, NodeFragWorkTaskBudgetMetaData.column_BUDGET_CONFIRMED_TIMESTAMP));
			setWorkBreakdownTotalTaskCount(aJsonObject.getInt(NodeFragWorkTaskBudgetMetaData.column_WORK_BREAKDOWN_TOTAL_TASK_COUNT));
			setWorkBreakdownAverageHoursPerTask(aJsonObject.getDouble(NodeFragWorkTaskBudgetMetaData.column_WORK_BREAKDOWN_AVERAGE_HOURS_PER_TASK));
			setTaskCountAfterCompletion(aJsonObject.getInt(NodeFragWorkTaskBudgetMetaData.column_TASK_COUNT_AFTER_COMPLETION));
			setAverageHoursPerTaskAfterCompletion(aJsonObject.getDouble(NodeFragWorkTaskBudgetMetaData.column_AVERAGE_HOURS_PER_TASK_AFTER_COMPLETION));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public JSONObject getJsonObject() {
		JSONObject theJsonObject = super.getJsonObject();
		try {
			theJsonObject.put(NodeFragWorkTaskBudgetMetaData.column_ESTIMATED_TOTAL_TASK_COUNT, getEstimatedTotalTaskCount());
			theJsonObject.put(NodeFragWorkTaskBudgetMetaData.column_ESTIMATED_AVERAGE_HOURS_PER_TASK, getEstimatedAverageHoursPerTask());
			theJsonObject.put(NodeFragWorkTaskBudgetMetaData.column_SWAG_BY, getSwagByNodeIdString());
			JsonHelper.putDate(theJsonObject, NodeFragWorkTaskBudgetMetaData.column_SWAG_TIMESTAMP, getSwagTimestamp());
			theJsonObject.put(NodeFragWorkTaskBudgetMetaData.column_ESTIMATE_CONFIRMED_BY, getEstimateConfirmedByNodeIdString());
			JsonHelper.putDate(theJsonObject, NodeFragWorkTaskBudgetMetaData.column_ESTIMATE_CONFIRMED_TIMESTAMP, getEstimateConfirmedTimestamp());
			theJsonObject.put(NodeFragWorkTaskBudgetMetaData.column_BUDGETED_TOTAL_TASK_COUNT, getBudgetedTotalTaskCount());
			theJsonObject.put(NodeFragWorkTaskBudgetMetaData.column_BUDGETED_AVERAGE_HOURS_PER_TASK, getBudgetedAverageHoursPerTask());
			theJsonObject.put(NodeFragWorkTaskBudgetMetaData.column_BUDGET_PROPOSED_BY, getBudgetProposedByNodeIdString());
			JsonHelper.putDate(theJsonObject, NodeFragWorkTaskBudgetMetaData.column_BUDGET_PROPOSED_TIMESTAMP, getBudgetProposedTimestamp());
			theJsonObject.put(NodeFragWorkTaskBudgetMetaData.column_BUDGET_CONFIRMED_BY, getBudgetConfirmedByNodeIdString());
			JsonHelper.putDate(theJsonObject, NodeFragWorkTaskBudgetMetaData.column_BUDGET_CONFIRMED_TIMESTAMP, getBudgetConfirmedTimestamp());
			theJsonObject.put(NodeFragWorkTaskBudgetMetaData.column_WORK_BREAKDOWN_TOTAL_TASK_COUNT, getWorkBreakdownTotalTaskCount());
			theJsonObject.put(NodeFragWorkTaskBudgetMetaData.column_WORK_BREAKDOWN_AVERAGE_HOURS_PER_TASK, getWorkBreakdownAverageHoursPerTask());
			theJsonObject.put(NodeFragWorkTaskBudgetMetaData.column_TASK_COUNT_AFTER_COMPLETION, getTaskCountAfterCompletion());
			theJsonObject.put(NodeFragWorkTaskBudgetMetaData.column_AVERAGE_HOURS_PER_TASK_AFTER_COMPLETION, getAverageHoursPerTaskAfterCompletion());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return theJsonObject;
	}

	public int getEstimatedTotalTaskCount() {
		return this.estimatedTotalTaskCount;
	}

	public void setEstimatedTotalTaskCount(int estimatedTotalTaskCount) {
		this.estimatedTotalTaskCount = estimatedTotalTaskCount;
	}

	public float getEstimatedAverageHoursPerTask() {
		return this.estimatedAverageHoursPerTask;
	}

	public void setEstimatedAverageHoursPerTask(float estimatedAverageHoursPerTask) {
		this.estimatedAverageHoursPerTask = estimatedAverageHoursPerTask;
	}

	private void setEstimatedAverageHoursPerTask(double aDouble) {
		setEstimatedAverageHoursPerTask((float) aDouble);
	}

	public String getSwagByNodeIdString() {
		return this.swagByNodeIdString;
	}

	public void setSwagBy(String aNodeIdString) {
		this.swagByNodeIdString = aNodeIdString;
	}

	public CommunityMember getSwagByCommunityMember() {
		return this.swagByCommunityMember;
	}

	public void setSwagBy(CommunityMember aCommunityMember) {
		this.swagByCommunityMember = aCommunityMember;
	}

	public Date getSwagTimestamp() {
		return this.swagTimestamp;
	}

	public void setSwagTimestamp(Date aTmestamp) {
		this.swagTimestamp = aTmestamp;
	}

	public String getEstimateConfirmedByNodeIdString() {
		return this.estimateConfirmedByNodeIdString;
	}

	public void setEstimateConfirmedBy(String aNodeIdString) {
		this.estimateConfirmedByNodeIdString = aNodeIdString;
	}

	public CommunityMember getEstimateConfirmedByCommunityMember() {
		return this.estimateConfirmedByCommunityMember;
	}

	public void setEstimateConfirmedBy(CommunityMember aCommunityMember) {
		this.estimateConfirmedByCommunityMember = aCommunityMember;
	}

	public Date getEstimateConfirmedTimestamp() {
		return this.estimateConfirmedTimestamp;
	}

	public void setEstimateConfirmedTimestamp(Date aTimestamp) {
		this.estimateConfirmedTimestamp = aTimestamp;
	}

	public int getBudgetedTotalTaskCount() {
		return this.budgetedTotalTaskCount;
	}

	public void setBudgetedTotalTaskCount(int aBudgetedTotalTaskCount) {
		this.budgetedTotalTaskCount = aBudgetedTotalTaskCount;
	}

	public float getBudgetedAverageHoursPerTask() {
		return this.budgetedAverageHoursPerTask;
	}

	public void setBudgetedAverageHoursPerTask(float aBudgetedAverageHoursPerTask) {
		this.budgetedAverageHoursPerTask = aBudgetedAverageHoursPerTask;
	}

	private void setBudgetedAverageHoursPerTask(double aDouble) {
		setBudgetedAverageHoursPerTask((float) aDouble);
	}

	public String getBudgetProposedByNodeIdString() {
		return this.budgetProposedByNodeIdString;
	}

	public void setBudgetProposedBy(String aNodeIdString) {
		this.budgetProposedByNodeIdString = aNodeIdString;
	}

	public CommunityMember getBudgetProposedByCommunityMember() {
		return this.budgetProposedByCommunityMember;
	}

	public void setBudgetProposedBy(CommunityMember aCommunityMember) {
		this.budgetProposedByCommunityMember = aCommunityMember;
	}

	public Date getBudgetProposedTimestamp() {
		return this.budgetProposedTimestamp;
	}

	public void setBudgetProposedTimestamp(Date aTimestamp) {
		this.budgetProposedTimestamp = aTimestamp;
	}

	public String getBudgetConfirmedByNodeIdString() {
		return this.budgetConfirmedByNodeIdString;
	}

	public void setBudgetConfirmedBy(String aNodeIdString) {
		this.budgetConfirmedByNodeIdString = aNodeIdString;
	}

	public CommunityMember getBudgetConfirmedByCommunityMember() {
		return this.budgetConfirmedByCommunityMember;
	}

	public void setBudgetConfirmedBy(CommunityMember aCommunityMember) {
		this.budgetConfirmedByCommunityMember = aCommunityMember;
	}

	public Date getBudgetConfirmedTimestamp() {
		return this.budgetConfirmedTimestamp;
	}

	public void setBudgetConfirmedTimestamp(Date aTimestamp) {
		this.budgetConfirmedTimestamp = aTimestamp;
	}

	public int getWorkBreakdownTotalTaskCount() {
		return this.workBreakdownTotalTaskCount;
	}

	public void setWorkBreakdownTotalTaskCount(int aWorkBreakdownTotalTaskCount) {
		this.workBreakdownTotalTaskCount = aWorkBreakdownTotalTaskCount;
	}

	public float getWorkBreakdownAverageHoursPerTask() {
		return this.workBreakdownAverageHoursPerTask;
	}

	public void setWorkBreakdownAverageHoursPerTask(float aWorkBreakdownAverageHoursPerTask) {
		this.workBreakdownAverageHoursPerTask = aWorkBreakdownAverageHoursPerTask;
	}

	private void setWorkBreakdownAverageHoursPerTask(double aDouble) {
		setWorkBreakdownAverageHoursPerTask((float) aDouble);
	}

	public int getTaskCountAfterCompletion() {
		return this.taskCountAfterCompletion;
	}

	public void setTaskCountAfterCompletion(int aTaskCountAfterCompletion) {
		this.taskCountAfterCompletion = aTaskCountAfterCompletion;
	}

	public float getAverageHoursPerTaskAfterCompletion() {
		return this.averageHoursPerTaskAfterCompletion;
	}

	public void setAverageHoursPerTaskAfterCompletion(float anAverageHoursPerTaskAfterCompletion) {
		this.averageHoursPerTaskAfterCompletion = anAverageHoursPerTaskAfterCompletion;
	}

	private void setAverageHoursPerTaskAfterCompletion(double aDouble) {
		setAverageHoursPerTaskAfterCompletion((float) aDouble);
	}

}
