/* @(#)WorkTask.java
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

package com.flywheelms.library.fmm.node.impl.governable;

import android.content.Intent;

import com.flywheelms.gcongui.gcg.activity.GcgActivity;
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.meta_data.WorkTaskMetaData;
import com.flywheelms.library.fmm.node.FmmHeadlineNodeShallow;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.completable.FmmCompletableNodeImpl;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.headline.FmmHeadlineNodeImpl;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;
import com.flywheelms.library.fms.helper.FmsActivityHelper;
import com.flywheelms.library.util.JsonHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WorkTask extends FmmCompletableNodeImpl {

	private static final long serialVersionUID = -85408705838458405L;
	
	private String workPackageNodeIdString;
	// WorkPackage sequence is the sequence in the super class
	private String workPlanNodeIdString;
	private int workPlanSequence;
	private int budgetedPersonHours;
	private int actualPersonHours;

    public WorkTask() {
        super(new NodeId(FmmNodeDefinition.WORK_TASK.getNodeTypeCode()));
    }

	public WorkTask(NodeId aNodeId) {
		super(aNodeId);
		setAutoCompletable(false);
	}

	public WorkTask(String anExistingNodeIdString) {
		super(NodeId.hydrate(WorkTask.class, anExistingNodeIdString));
		setAutoCompletable(false);
	}

    public WorkTask(JSONObject aJsonObject) {
        super(WorkTask.class, aJsonObject);
        try {
            validateSerializationFormatVersion(aJsonObject.getString(JsonHelper.key__SERIALIZATION_FORMAT_VERSION));
            setWorkPackageNodeIdString(aJsonObject.getString(WorkTaskMetaData.column_WORK_PACKAGE__ID));
            setWorkPlanNodeIdString(aJsonObject.getString(WorkTaskMetaData.column_WORK_PLAN__ID));
            setWorkPlanSequence(aJsonObject.getInt(WorkTaskMetaData.column_WORK_PLAN_SEQUENCE));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
	public static void startNodeEditorActivity(GcgActivity anActivity, String aNodeListParentNodeId, ArrayList<FmmHeadlineNodeShallow> aHeadlineNodeShallowList, String anInitialNodeIdToDisplay) {
		FmmHeadlineNodeImpl.startNodeEditorActivity(
				anActivity,
				aNodeListParentNodeId,
				aHeadlineNodeShallowList,
				anInitialNodeIdToDisplay,
				FmmNodeDefinition.WORK_TASK );
	}
	
	public static void startNodePickerActivity(GcgActivity anActivity, ArrayList<String> aNodeIdExclusionList, String aWhereClause, String aListActionLabel) {
		FmsActivityHelper.startHeadlineNodePickerActivity(anActivity, FmmNodeDefinition.WORK_TASK, aNodeIdExclusionList, aWhereClause, aListActionLabel);
	}
	
	public static WorkTask getWorkTask(Intent anIntent) {
		return FmmDatabaseMediator.getActiveMediator().retrieveWorkTask(NodeId.getNodeIdString(anIntent));
	}

	public String getWorkPackageNodeIdString() {
		return this.workPackageNodeIdString;
	}

	public void setWorkPackageNodeIdString(String aWorkPackageNodeIdString) {
		this.workPackageNodeIdString = aWorkPackageNodeIdString;
	}

	public String getWorkPlanNodeIdString() {
		return this.workPlanNodeIdString;
	}

	public void setWorkPlanNodeIdString(String aWorkPlanNodeIdString) {
		this.workPlanNodeIdString = aWorkPlanNodeIdString;
	}

	public int getWorkPlanSequence() {
		return this.workPlanSequence;
	}

	public void setWorkPlanSequence(int aSequence) {
		this.workPlanSequence = aSequence;
	}

	public int getBudgetedPersonHours() {
		return this.budgetedPersonHours;
	}

	public void setBudgetedPersonHours(int budgetedPersonHours) {
		this.budgetedPersonHours = budgetedPersonHours;
	}

	public int getActualPersonHours() {
		return this.actualPersonHours;
	}

	public void setActualPersonHours(int actualPersonHours) {
		this.actualPersonHours = actualPersonHours;
	}

    @Override
    public ArrayList<? extends FmmHeadlineNode> getPeerHeadlineNodeShallowList(FmmHeadlineNode aParentHeadlineNode) {
        ArrayList<WorkTask> theList;
        switch(aParentHeadlineNode.getFmmNodeDefinition()) {
            case WORK_PACKAGE:
                theList = FmmDatabaseMediator.getActiveMediator().listWorkTasksForWorkPackage(aParentHeadlineNode.getNodeIdString());
                break;
            case WORK_PLAN:
                theList = FmmDatabaseMediator.getActiveMediator().listWorkTasksForWorkPlan(aParentHeadlineNode.getNodeIdString());
                break;
            default:
                theList = new ArrayList<WorkTask>();
                theList.add(this);
        }
        return theList;
    }

    public void setPrimaryParentId(String aNodeIdString) {
        setWorkPackageNodeIdString(aNodeIdString);
    }

}
