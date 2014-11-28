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
import com.flywheelms.library.fmm.FmmDatabaseService;
import com.flywheelms.library.fmm.enumerator.FmmDataQuality;
import com.flywheelms.library.fmm.meta_data.NodeFragWorkTaskBudgetMetaData;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.governable.CommunityMember;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmCompletionNode;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmNodeWorkTaskBudget;
import com.flywheelms.library.util.JsonHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class NodeFragWorkTaskBudget extends FmmNodeFragLockableImpl implements FmmNodeWorkTaskBudget {
	
	private int estimatedTotalTaskCount = 0;
	private float estimatedAverageHoursPerTask = 1.5f;
	private String estimateByCommunityMemberId;
	private CommunityMember estimateByCommunityMember;
	private Date estimateTimestamp = GcgDateHelper.NULL_DATE;
    private FmmDataQuality estimateFmmDataQuality = FmmDataQuality.NONE;

	private int budgetedTotalTaskCount;
	private float budgetedAverageHoursPerTask = 1.5f;
	private String budgetByCommunityMemberId;
	private CommunityMember budgetByCommunityMember;
	private Date budgetTimestamp = GcgDateHelper.NULL_DATE;
    private FmmDataQuality budgetFmmDataQuality = FmmDataQuality.NONE;

	private int workBreakdownEstimatedTotalTaskCount;
	private float workBreakdownEstimatedAverageHoursPerTask = 0.0f;

	private int workBreakdownBudgetedTotalTaskCount;
	private float workBreakdownBudgetedAverageHoursPerTask = 0.0f;

	private int completedTotalTaskCount;
	private float completedAverageHoursPerTask = 0.0f;

    private String serialized_history;

    public NodeFragWorkTaskBudget(FmmCompletionNode anFmmCompletionNode) {
        super(NodeFragWorkTaskBudget.class, anFmmCompletionNode.getNodeIdString());
    }

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
			setEstimateByCommunityMemberId(JsonHelper.getString(aJsonObject, NodeFragWorkTaskBudgetMetaData.column_ESTIMATE_BY));
			setEstimateTimestamp(JsonHelper.getDate(aJsonObject, NodeFragWorkTaskBudgetMetaData.column_ESTIMATE_TIMESTAMP));
			setEstimateFmmDataQuality(JsonHelper.getString(aJsonObject, NodeFragWorkTaskBudgetMetaData.column_ESTIMATE_DATA_QUALITY));
			setBudgetedTotalTaskCount(aJsonObject.getInt(NodeFragWorkTaskBudgetMetaData.column_BUDGETED_TOTAL_TASK_COUNT));
			setBudgetedAverageHoursPerTask(aJsonObject.getDouble(NodeFragWorkTaskBudgetMetaData.column_BUDGETED_AVERAGE_HOURS_PER_TASK));
			setBudgetByCommunityMemberId(JsonHelper.getString(aJsonObject, NodeFragWorkTaskBudgetMetaData.column_BUDGET_BY));
			setBudgetTimestamp(JsonHelper.getDate(aJsonObject, NodeFragWorkTaskBudgetMetaData.column_BUDGET_TIMESTAMP));
            setBudgetFmmDataQuality(JsonHelper.getString(aJsonObject, NodeFragWorkTaskBudgetMetaData.column_BUDGET_DATA_QUALITY));
			setWorkBreakdownEstimatedTotalTaskCount(aJsonObject.getInt(NodeFragWorkTaskBudgetMetaData.column_WORK_BREAKDOWN_ESTIMATED_TOTAL_TASK_COUNT));
			setWorkBreakdownEstimatedAverageHoursPerTask(aJsonObject.getDouble(NodeFragWorkTaskBudgetMetaData.column_WORK_BREAKDOWN_ESTIMATED_AVERAGE_HOURS_PER_TASK));
            setWorkBreakdownBudgetedTotalTaskCount(aJsonObject.getInt(NodeFragWorkTaskBudgetMetaData.column_WORK_BREAKDOWN_BUDGETED_TOTAL_TASK_COUNT));
            setWorkBreakdownBudgetedAverageHoursPerTask(aJsonObject.getDouble(NodeFragWorkTaskBudgetMetaData.column_WORK_BREAKDOWN_BUDGETED_AVERAGE_HOURS_PER_TASK));
			setCompletedTotalTaskCount(aJsonObject.getInt(NodeFragWorkTaskBudgetMetaData.column_COMPLETED_TOTAL_TASK_COUNT));
			setCompletedAverageHoursPerTask(aJsonObject.getDouble(NodeFragWorkTaskBudgetMetaData.column_COMPLETED_AVERAGE_HOURS_PER_TASK));
			setSerialized_history(aJsonObject.getString(NodeFragWorkTaskBudgetMetaData.column_SERIALIZED_HISTORY));
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
			theJsonObject.put(NodeFragWorkTaskBudgetMetaData.column_ESTIMATE_BY, getEstimateByCommunityMemberId());
			JsonHelper.putDate(theJsonObject, NodeFragWorkTaskBudgetMetaData.column_ESTIMATE_TIMESTAMP, getEstimateTimestamp());
            theJsonObject.put(NodeFragWorkTaskBudgetMetaData.column_ESTIMATE_DATA_QUALITY, getEstimateFmmDataQuality().getName());
			theJsonObject.put(NodeFragWorkTaskBudgetMetaData.column_BUDGETED_TOTAL_TASK_COUNT, getBudgetedTotalTaskCount());
			theJsonObject.put(NodeFragWorkTaskBudgetMetaData.column_BUDGETED_AVERAGE_HOURS_PER_TASK, getBudgetedAverageHoursPerTask());
			theJsonObject.put(NodeFragWorkTaskBudgetMetaData.column_BUDGET_BY, getBudgetByCommunityMemberId());
			JsonHelper.putDate(theJsonObject, NodeFragWorkTaskBudgetMetaData.column_BUDGET_TIMESTAMP, getBudgetTimestamp());
            theJsonObject.put(NodeFragWorkTaskBudgetMetaData.column_BUDGET_DATA_QUALITY, getBudgetFmmDataQuality().getName());
			theJsonObject.put(NodeFragWorkTaskBudgetMetaData.column_WORK_BREAKDOWN_ESTIMATED_TOTAL_TASK_COUNT, getWorkBreakdownEstimatedTotalTaskCount());
			theJsonObject.put(NodeFragWorkTaskBudgetMetaData.column_WORK_BREAKDOWN_ESTIMATED_AVERAGE_HOURS_PER_TASK, getWorkBreakdownEstimatedAverageHoursPerTask());
            theJsonObject.put(NodeFragWorkTaskBudgetMetaData.column_WORK_BREAKDOWN_BUDGETED_TOTAL_TASK_COUNT, getWorkBreakdownBudgetedTotalTaskCount());
            theJsonObject.put(NodeFragWorkTaskBudgetMetaData.column_WORK_BREAKDOWN_BUDGETED_AVERAGE_HOURS_PER_TASK, getWorkBreakdownBudgetedAverageHoursPerTask());
			theJsonObject.put(NodeFragWorkTaskBudgetMetaData.column_COMPLETED_TOTAL_TASK_COUNT, getCompletedTotalTaskCount());
			theJsonObject.put(NodeFragWorkTaskBudgetMetaData.column_COMPLETED_AVERAGE_HOURS_PER_TASK, getCompletedAverageHoursPerTask());
			theJsonObject.put(NodeFragWorkTaskBudgetMetaData.column_SERIALIZED_HISTORY, getSerialized_history());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return theJsonObject;
	}

    public NodeFragWorkTaskBudget getClone() {
        return new NodeFragWorkTaskBudget(getJsonObject());
    }

    //-->

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

	public String getEstimateByCommunityMemberId() {
		return this.estimateByCommunityMemberId;
	}

	public void setEstimateByCommunityMemberId(String aNodeIdString) {
		this.estimateByCommunityMemberId = aNodeIdString;
        this.estimateByCommunityMember = null;
	}

	public CommunityMember getEstimateByCommunityMember() {
        if(this.estimateByCommunityMember == null && this.estimateByCommunityMemberId != null) {
            this.estimateByCommunityMember = FmmDatabaseService.getActiveDatabaseMediator().retrieveCommunityMember(this.estimateByCommunityMemberId);
        }
		return this.estimateByCommunityMember;
	}

	public void setEstimateByCommunityMember(CommunityMember aCommunityMember) {
		this.estimateByCommunityMember = aCommunityMember;
        this.estimateByCommunityMemberId = aCommunityMember.getNodeIdString();
	}

	public Date getEstimateTimestamp() {
		return this.estimateTimestamp;
	}

	public void setEstimateTimestamp(Date aTimestamp) {
		this.estimateTimestamp = aTimestamp;
	}

    public FmmDataQuality getEstimateFmmDataQuality() {
        return estimateFmmDataQuality;
    }

    public void setEstimateFmmDataQuality(FmmDataQuality anFmmDataQuality) {
        this.estimateFmmDataQuality = anFmmDataQuality;
    }

    public void setEstimateFmmDataQuality(String aDataQualityName) {
        this.estimateFmmDataQuality = FmmDataQuality.getObjectForName(aDataQualityName);
    }

    //-->

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

	public String getBudgetByCommunityMemberId() {
		return this.budgetByCommunityMemberId;
	}

	public void setBudgetByCommunityMemberId(String aNodeIdString) {
		this.budgetByCommunityMemberId = aNodeIdString;
	}

	public CommunityMember getBudgetByCommunityMember() {
        if(this.budgetByCommunityMember == null && this.budgetByCommunityMemberId != null) {
            this.budgetByCommunityMember = FmmDatabaseService.getActiveDatabaseMediator().retrieveCommunityMember(this.budgetByCommunityMemberId);
        }
		return this.budgetByCommunityMember;
	}

	public void setBudgetByCommunityMember(CommunityMember aCommunityMember) {
		this.budgetByCommunityMember = aCommunityMember;
        this.budgetByCommunityMemberId = aCommunityMember.getNodeIdString();
	}

	public Date getBudgetTimestamp() {
		return this.budgetTimestamp;
	}

	public void setBudgetTimestamp(Date aTimestamp) {
		this.budgetTimestamp = aTimestamp;
	}

    public FmmDataQuality getBudgetFmmDataQuality() {
        return budgetFmmDataQuality;
    }

    public void setBudgetFmmDataQuality(FmmDataQuality budgetFmmDataQuality) {
        this.budgetFmmDataQuality = budgetFmmDataQuality;
    }

    public void setBudgetFmmDataQuality(String aDataQualityName) {
        this.budgetFmmDataQuality = FmmDataQuality.getObjectForName(aDataQualityName);
    }

    //-->

    public int getWorkBreakdownEstimatedTotalTaskCount() {
        return this.workBreakdownEstimatedTotalTaskCount;
    }

    public void setWorkBreakdownEstimatedTotalTaskCount(int aWorkBreakdownEstimatedTotalTaskCount) {
        this.workBreakdownEstimatedTotalTaskCount = aWorkBreakdownEstimatedTotalTaskCount;
    }

    public float getWorkBreakdownEstimatedAverageHoursPerTask() {
        return this.workBreakdownEstimatedAverageHoursPerTask;
    }

    public void setWorkBreakdownEstimatedAverageHoursPerTask(float aWorkBreakdownEstimatedAverageHoursPerTask) {
        this.workBreakdownEstimatedAverageHoursPerTask = aWorkBreakdownEstimatedAverageHoursPerTask;
    }

    private void setWorkBreakdownEstimatedAverageHoursPerTask(double aDouble) {
        setWorkBreakdownEstimatedAverageHoursPerTask((float) aDouble);
    }

    //-->

	public int getWorkBreakdownBudgetedTotalTaskCount() {
		return this.workBreakdownBudgetedTotalTaskCount;
	}

	public void setWorkBreakdownBudgetedTotalTaskCount(int aWorkBreakdownTotalTaskCount) {
		this.workBreakdownBudgetedTotalTaskCount = aWorkBreakdownTotalTaskCount;
	}

	public float getWorkBreakdownBudgetedAverageHoursPerTask() {
		return this.workBreakdownBudgetedAverageHoursPerTask;
	}

	public void setWorkBreakdownBudgetedAverageHoursPerTask(float aWorkBreakdownAverageHoursPerTask) {
		this.workBreakdownBudgetedAverageHoursPerTask = aWorkBreakdownAverageHoursPerTask;
	}

	private void setWorkBreakdownBudgetedAverageHoursPerTask(double aDouble) {
		setWorkBreakdownBudgetedAverageHoursPerTask((float) aDouble);
	}

    //-->

	public int getCompletedTotalTaskCount() {
		return this.completedTotalTaskCount;
	}

	public void setCompletedTotalTaskCount(int aTaskCountAfterCompletion) {
		this.completedTotalTaskCount = aTaskCountAfterCompletion;
	}

	public float getCompletedAverageHoursPerTask() {
		return this.completedAverageHoursPerTask;
	}

	public void setCompletedAverageHoursPerTask(float anAverageHoursPerTaskAfterCompletion) {
		this.completedAverageHoursPerTask = anAverageHoursPerTaskAfterCompletion;
	}

	private void setCompletedAverageHoursPerTask(double aDouble) {
		setCompletedAverageHoursPerTask((float) aDouble);
	}

    //-->

    public int getPhantomTasks() {
        int theCount = this.estimatedTotalTaskCount - this.workBreakdownBudgetedTotalTaskCount;
        return theCount < 0 ? 0 : theCount;
    }

    public String getSerialized_history() {
        return serialized_history;
    }

    public void setSerialized_history(String serialized_history) {
        this.serialized_history = serialized_history;
    }
}
