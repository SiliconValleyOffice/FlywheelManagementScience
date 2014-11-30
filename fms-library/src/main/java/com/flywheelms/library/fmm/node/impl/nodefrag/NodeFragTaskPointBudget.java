/* @(#)NodeFragTaskPointBudget.java
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
import com.flywheelms.library.fmm.meta_data.NodeFragTaskPointBudgetMetaData;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.governable.CommunityMember;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmCompletionNode;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmNodeWorkTaskBudget;
import com.flywheelms.library.util.JsonHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class NodeFragTaskPointBudget extends FmmNodeFragLockableImpl implements FmmNodeWorkTaskBudget {
	
	private int estimatedTaskPoints = 0;
	private float estimatedAverageHoursPerTaskPoint = 1.5f;
	private String estimateByCommunityMemberId;
	private CommunityMember estimateByCommunityMember;
	private Date estimateTimestamp = GcgDateHelper.NULL_DATE;
    private FmmDataQuality estimateFmmDataQuality = FmmDataQuality.NONE;

	private int budgetedTaskPoints;
	private float budgetedAverageHoursPerTaskPoint = 1.5f;
	private String budgetByCommunityMemberId;
	private CommunityMember budgetByCommunityMember;
	private Date budgetTimestamp = GcgDateHelper.NULL_DATE;
    private FmmDataQuality budgetFmmDataQuality = FmmDataQuality.NONE;

	private int workBreakdownEstimatedTaskPoints;
	private float workBreakdownEstimatedAverageHoursPerTaskPoint = 0.0f;

	private int workBreakdownBudgetedTaskPoints;
	private float workBreakdownBudgetedAverageHoursPerTaskPoint = 0.0f;

	private int completedTaskPoints;
	private float completedAverageHoursPerTaskPoint = 0.0f;

    private String serialized_history;

    public NodeFragTaskPointBudget(FmmCompletionNode anFmmCompletionNode) {
        super(NodeFragTaskPointBudget.class, anFmmCompletionNode.getNodeIdString());
    }

	public NodeFragTaskPointBudget(String aParentNodeIdString) {
		super(NodeFragTaskPointBudget.class, aParentNodeIdString);
	}

	// rehydrate from database
	public NodeFragTaskPointBudget(String anExistingNodeFragNodeIdString, String aParentNodeIdString) {
		super(NodeId.hydrate(NodeFragTaskPointBudget.class, anExistingNodeFragNodeIdString), aParentNodeIdString);
	}

	// rehydrate from serialization
	public NodeFragTaskPointBudget(JSONObject aJsonObject) {
		super(NodeFragTaskPointBudget.class, aJsonObject);
		try {
			setEstimatedTaskPoints(aJsonObject.getInt(NodeFragTaskPointBudgetMetaData.column_ESTIMATED_TASK_POINTS));
			setEstimatedAverageHoursPerTask(aJsonObject.getDouble(NodeFragTaskPointBudgetMetaData.column_ESTIMATED_AVERAGE_HOURS_PER_TASK_POINT));
			setEstimateByCommunityMemberId(JsonHelper.getString(aJsonObject, NodeFragTaskPointBudgetMetaData.column_ESTIMATE_BY));
			setEstimateTimestamp(JsonHelper.getDate(aJsonObject, NodeFragTaskPointBudgetMetaData.column_ESTIMATE_TIMESTAMP));
			setEstimateFmmDataQuality(JsonHelper.getString(aJsonObject, NodeFragTaskPointBudgetMetaData.column_ESTIMATE_DATA_QUALITY));
			setBudgetedTaskPoints(aJsonObject.getInt(NodeFragTaskPointBudgetMetaData.column_BUDGETED_TASK_POINTS));
			setBudgetedAverageHoursPerTask(aJsonObject.getDouble(NodeFragTaskPointBudgetMetaData.column_BUDGETED_AVERAGE_HOURS_PER_TASK_POINT));
			setBudgetByCommunityMemberId(JsonHelper.getString(aJsonObject, NodeFragTaskPointBudgetMetaData.column_BUDGET_BY));
			setBudgetTimestamp(JsonHelper.getDate(aJsonObject, NodeFragTaskPointBudgetMetaData.column_BUDGET_TIMESTAMP));
            setBudgetFmmDataQuality(JsonHelper.getString(aJsonObject, NodeFragTaskPointBudgetMetaData.column_BUDGET_DATA_QUALITY));
			setWorkBreakdownEstimatedTaskPoints(aJsonObject.getInt(NodeFragTaskPointBudgetMetaData.column_WORK_BREAKDOWN_ESTIMATED_TASK_POINTS));
			setWorkBreakdownEstimatedAverageHoursPerTask(aJsonObject.getDouble(NodeFragTaskPointBudgetMetaData.column_WORK_BREAKDOWN_ESTIMATED_AVERAGE_HOURS_PER_TASK_POINT));
            setWorkBreakdownBudgetedTaskPoints(aJsonObject.getInt(NodeFragTaskPointBudgetMetaData.column_WORK_BREAKDOWN_BUDGETED_TASK_POINTS));
            setWorkBreakdownBudgetedAverageHoursPerTask(aJsonObject.getDouble(NodeFragTaskPointBudgetMetaData.column_WORK_BREAKDOWN_BUDGETED_AVERAGE_HOURS_PER_TASK_POINT));
			setCompletedTaskPoints(aJsonObject.getInt(NodeFragTaskPointBudgetMetaData.column_COMPLETED_TASK_POINTS));
			setCompletedAverageHoursPerTask(aJsonObject.getDouble(NodeFragTaskPointBudgetMetaData.column_COMPLETED_AVERAGE_HOURS_PER_TASK_POINT));
			setSerialized_history(aJsonObject.getString(NodeFragTaskPointBudgetMetaData.column_SERIALIZED_HISTORY));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public JSONObject getJsonObject() {
		JSONObject theJsonObject = super.getJsonObject();
		try {
			theJsonObject.put(NodeFragTaskPointBudgetMetaData.column_ESTIMATED_TASK_POINTS, getEstimatedTaskPoints());
			theJsonObject.put(NodeFragTaskPointBudgetMetaData.column_ESTIMATED_AVERAGE_HOURS_PER_TASK_POINT, getEstimatedAverageHoursPerTaskPoint());
			theJsonObject.put(NodeFragTaskPointBudgetMetaData.column_ESTIMATE_BY, getEstimateByCommunityMemberId());
			JsonHelper.putDate(theJsonObject, NodeFragTaskPointBudgetMetaData.column_ESTIMATE_TIMESTAMP, getEstimateTimestamp());
            theJsonObject.put(NodeFragTaskPointBudgetMetaData.column_ESTIMATE_DATA_QUALITY, getEstimateFmmDataQuality().getName());
			theJsonObject.put(NodeFragTaskPointBudgetMetaData.column_BUDGETED_TASK_POINTS, getBudgetedTaskPoints());
			theJsonObject.put(NodeFragTaskPointBudgetMetaData.column_BUDGETED_AVERAGE_HOURS_PER_TASK_POINT, getBudgetedAverageHoursPerTaskPoint());
			theJsonObject.put(NodeFragTaskPointBudgetMetaData.column_BUDGET_BY, getBudgetByCommunityMemberId());
			JsonHelper.putDate(theJsonObject, NodeFragTaskPointBudgetMetaData.column_BUDGET_TIMESTAMP, getBudgetTimestamp());
            theJsonObject.put(NodeFragTaskPointBudgetMetaData.column_BUDGET_DATA_QUALITY, getBudgetFmmDataQuality().getName());
			theJsonObject.put(NodeFragTaskPointBudgetMetaData.column_WORK_BREAKDOWN_ESTIMATED_TASK_POINTS, getWorkBreakdownEstimatedTaskPoints());
			theJsonObject.put(NodeFragTaskPointBudgetMetaData.column_WORK_BREAKDOWN_ESTIMATED_AVERAGE_HOURS_PER_TASK_POINT, getWorkBreakdownEstimatedAverageHoursPerTaskPoint());
            theJsonObject.put(NodeFragTaskPointBudgetMetaData.column_WORK_BREAKDOWN_BUDGETED_TASK_POINTS, getWorkBreakdownBudgetedTaskPoints());
            theJsonObject.put(NodeFragTaskPointBudgetMetaData.column_WORK_BREAKDOWN_BUDGETED_AVERAGE_HOURS_PER_TASK_POINT, getWorkBreakdownBudgetedAverageHoursPerTaskPoint());
			theJsonObject.put(NodeFragTaskPointBudgetMetaData.column_COMPLETED_TASK_POINTS, getCompletedTaskPoints());
			theJsonObject.put(NodeFragTaskPointBudgetMetaData.column_COMPLETED_AVERAGE_HOURS_PER_TASK_POINT, getCompletedAverageHoursPerTaskPoint());
			theJsonObject.put(NodeFragTaskPointBudgetMetaData.column_SERIALIZED_HISTORY, getSerialized_history());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return theJsonObject;
	}

    public NodeFragTaskPointBudget getClone() {
        return new NodeFragTaskPointBudget(getJsonObject());
    }

    //-->

	public int getEstimatedTaskPoints() {
		return this.estimatedTaskPoints;
	}

	public void setEstimatedTaskPoints(int estimatedTaskPoints) {
		this.estimatedTaskPoints = estimatedTaskPoints;
	}

	public float getEstimatedAverageHoursPerTaskPoint() {
		return this.estimatedAverageHoursPerTaskPoint;
	}

	public void setEstimatedAverageHoursPerTaskPoint(float estimatedAverageHoursPerTaskPoint) {
		this.estimatedAverageHoursPerTaskPoint = estimatedAverageHoursPerTaskPoint;
	}

	private void setEstimatedAverageHoursPerTask(double aDouble) {
		setEstimatedAverageHoursPerTaskPoint((float) aDouble);
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

	public int getBudgetedTaskPoints() {
		return this.budgetedTaskPoints;
	}

	public void setBudgetedTaskPoints(int aBudgetedTaskCount) {
		this.budgetedTaskPoints = aBudgetedTaskCount;
	}

	public float getBudgetedAverageHoursPerTaskPoint() {
		return this.budgetedAverageHoursPerTaskPoint;
	}

	public void setBudgetedAverageHoursPerTaskPoint(float aBudgetedAverageHoursPerTask) {
		this.budgetedAverageHoursPerTaskPoint = aBudgetedAverageHoursPerTask;
	}

	private void setBudgetedAverageHoursPerTask(double aDouble) {
		setBudgetedAverageHoursPerTaskPoint((float) aDouble);
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

    public int getWorkBreakdownEstimatedTaskPoints() {
        return this.workBreakdownEstimatedTaskPoints;
    }

    public void setWorkBreakdownEstimatedTaskPoints(int aWorkBreakdownEstimatedTaskCount) {
        this.workBreakdownEstimatedTaskPoints = aWorkBreakdownEstimatedTaskCount;
    }

    public float getWorkBreakdownEstimatedAverageHoursPerTaskPoint() {
        return this.workBreakdownEstimatedAverageHoursPerTaskPoint;
    }

    public void setWorkBreakdownEstimatedAverageHoursPerTaskPoint(float aWorkBreakdownEstimatedAverageHoursPerTask) {
        this.workBreakdownEstimatedAverageHoursPerTaskPoint = aWorkBreakdownEstimatedAverageHoursPerTask;
    }

    private void setWorkBreakdownEstimatedAverageHoursPerTask(double aDouble) {
        setWorkBreakdownEstimatedAverageHoursPerTaskPoint((float) aDouble);
    }

    //-->

	public int getWorkBreakdownBudgetedTaskPoints() {
		return this.workBreakdownBudgetedTaskPoints;
	}

	public void setWorkBreakdownBudgetedTaskPoints(int aWorkBreakdownTaskCount) {
		this.workBreakdownBudgetedTaskPoints = aWorkBreakdownTaskCount;
	}

	public float getWorkBreakdownBudgetedAverageHoursPerTaskPoint() {
		return this.workBreakdownBudgetedAverageHoursPerTaskPoint;
	}

	public void setWorkBreakdownBudgetedAverageHoursPerTaskPoint(float aWorkBreakdownAverageHoursPerTask) {
		this.workBreakdownBudgetedAverageHoursPerTaskPoint = aWorkBreakdownAverageHoursPerTask;
	}

	private void setWorkBreakdownBudgetedAverageHoursPerTask(double aDouble) {
		setWorkBreakdownBudgetedAverageHoursPerTaskPoint((float) aDouble);
	}

    //-->

	public int getCompletedTaskPoints() {
		return this.completedTaskPoints;
	}

	public void setCompletedTaskPoints(int aTaskCountAfterCompletion) {
		this.completedTaskPoints = aTaskCountAfterCompletion;
	}

	public float getCompletedAverageHoursPerTaskPoint() {
		return this.completedAverageHoursPerTaskPoint;
	}

	public void setCompletedAverageHoursPerTaskPoint(float anAverageHoursPerTaskAfterCompletion) {
		this.completedAverageHoursPerTaskPoint = anAverageHoursPerTaskAfterCompletion;
	}

	private void setCompletedAverageHoursPerTask(double aDouble) {
		setCompletedAverageHoursPerTaskPoint((float) aDouble);
	}

    //-->

    public int getPhantomTasks() {
        int theCount = this.estimatedTaskPoints - this.workBreakdownBudgetedTaskPoints;
        return theCount < 0 ? 0 : theCount;
    }

    public String getSerialized_history() {
        return serialized_history;
    }

    public void setSerialized_history(String serialized_history) {
        this.serialized_history = serialized_history;
    }
}
