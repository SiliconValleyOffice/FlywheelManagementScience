/* @(#)WorkPlan.java
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

import com.flywheelms.gcongui.gcg.interfaces.GcgPerspective;
import com.flywheelms.gcongui.gcg.widget.date.GcgDateHelper;
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.context.FmmPerspective;
import com.flywheelms.library.fmm.enumerator.FmmHoliday;
import com.flywheelms.library.fmm.meta_data.SequencedLinkNodeMetaData;
import com.flywheelms.library.fmm.meta_data.WorkPlanMetaData;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.completable.FmmCompletionNodeImpl;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.headline.FmmHeadlineNodeImpl;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;
import com.flywheelms.library.util.JsonHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class WorkPlan extends FmmCompletionNodeImpl {

	private static final long serialVersionUID = -5268115673220940748L;
    private String flywheelCadenceId;
    private Cadence flywheelCadence;
    private Date scheduledStartDate;
    private Date scheduledEndDate;
    private FmmHoliday fmmHoliday;
    private ArrayList<WorkTask> workTaskList;

    public WorkPlan(NodeId aNodeId) {
		super(aNodeId);
		// TODO Auto-generated constructor stub
	}

    public WorkPlan(Cadence aCadence, Date aStartDate, Date anEndDate) {
        super(new NodeId(FmmNodeDefinition.WORK_PLAN));
        setCadence(aCadence);
        setScheduledStartDate(aStartDate);
        setScheduledEndDate(anEndDate);
    }

    public WorkPlan(String anExistingNodeId) {
        super(new NodeId(FmmNodeDefinition.WORK_PLAN, anExistingNodeId, true));
    }

    public WorkPlan(JSONObject aJsonObject) {
        super(WorkPlan.class, aJsonObject);
        try {
            validateSerializationFormatVersion(aJsonObject.getString(JsonHelper.key__SERIALIZATION_FORMAT_VERSION));
            setSequence(aJsonObject.getInt(SequencedLinkNodeMetaData.column_SEQUENCE));
            setCadenceId(aJsonObject.getString(WorkPlanMetaData.column_CADENCE_ID));
            setFmmHoliday(aJsonObject.getString(WorkPlanMetaData.column_HOLIDAY));
            setScheduledStartDate(aJsonObject.getLong(WorkPlanMetaData.column_SCHEDULED_START_DATE));
            setScheduledEndDate(aJsonObject.getLong(WorkPlanMetaData.column_SCHEDULED_END_DATE));
            setWorkTaskList(aJsonObject.getJSONArray(WorkPlanMetaData.child_fractals_WORK_TASK_LIST));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void setWorkTaskList(JSONArray aJsonArray) {
        if(aJsonArray == null) {
            return;
        }
        this.workTaskList = new ArrayList<WorkTask>();
        for(int i=0; i < aJsonArray.length(); ++i) {
            try {
                this.workTaskList.add(FmmDatabaseMediator.getActiveMediator().retrieveWorkTask(
                        aJsonArray.getString(i)));
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static final String SERIALIZATION_FORMAT_VERSION = "0.1";

    @Override
    public JSONObject getJsonObject() {
        JSONObject theJsonObject = super.getJsonObject();
        try {
            theJsonObject.put(JsonHelper.key__SERIALIZATION_FORMAT_VERSION, SERIALIZATION_FORMAT_VERSION);
            theJsonObject.put(SequencedLinkNodeMetaData.column_SEQUENCE, getSequence());
            theJsonObject.put(WorkPlanMetaData.column_CADENCE_ID, getCadenceId());
            theJsonObject.put(WorkPlanMetaData.column_SCHEDULED_START_DATE, getScheduledStartDateFormattedUtcLong());
            theJsonObject.put(WorkPlanMetaData.column_SCHEDULED_END_DATE, getScheduledEndDateFormattedUtcLong());
            theJsonObject.put(WorkPlanMetaData.column_SCHEDULED_END_DATE, getFmmHolidayName());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return theJsonObject;
    }

    private JSONArray getWorkTaskNodeIdStringJsonArray() {
        JSONArray theJsonArray = new JSONArray();
        for(WorkTask theWorkTask : getWorkTaskList()) {
            theJsonArray.put(theWorkTask.getNodeIdString());
        }
        return theJsonArray;
    }

    @Override
    public WorkPlan getClone() {
        return new WorkPlan(getJsonObject());
    }

    public String getCadenceId() {
        return this.flywheelCadenceId;
    }

    public Cadence getCadence() {
        if(this.flywheelCadence == null && this.flywheelCadenceId != null) {
            this.flywheelCadence =
                    FmmDatabaseMediator.getActiveMediator().retrieveCadence(this.flywheelCadenceId);
        }
        return this.flywheelCadence;
    }

    public void setCadenceId(String aNodeIdString) {
        this.flywheelCadenceId = aNodeIdString;
        if(this.flywheelCadence != null && !this.flywheelCadence.getNodeIdString().equals(aNodeIdString)) {
            this.flywheelCadence = null;
        }
    }

    public void setCadence(Cadence aCadence) {
        this.flywheelCadence = aCadence;
        this.flywheelCadenceId = aCadence.getNodeId().getNodeIdString();
    }

	public boolean isWorkTaskMoveTarget() {
		return true;
	}

    public Date getScheduledStartDate() {
        return this.scheduledStartDate;
    }

    public Long getScheduledStartDateFormattedUtcLong() {
        return GcgDateHelper.getFormattedUtcLong(this.scheduledStartDate);
    }

    public void setScheduledStartDate(Date aStartDate) {
        this.scheduledStartDate = aStartDate;
    }

    public void setScheduledStartDate(Long aLongDate) {
        this.scheduledStartDate = GcgDateHelper.getDateFromFormattedUtcLong(aLongDate);
    }

    public Date getScheduledEndDate() {
        return this.scheduledEndDate;
    }

    public Long getScheduledEndDateFormattedUtcLong() {
        return GcgDateHelper.getFormattedUtcLong(this.scheduledEndDate);
    }

    public void setScheduledEndDate(Date anEndDate) {
        this.scheduledEndDate = anEndDate;
    }

    public void setScheduledEndDate(Long aLongDate) {
        this.scheduledEndDate = GcgDateHelper.getDateFromFormattedUtcLong(aLongDate);
    }

    @Override
    public boolean hasSecondaryHeadline() { return true; }

    @Override
    public String getSecondaryHeadline() {
        String theSecondaryHeadline = "ending " + GcgDateHelper.getGuiDateString5(getScheduledEndDate());
        if(this.fmmHoliday != null) {
            theSecondaryHeadline += "        (includes " + this.fmmHoliday.getName() + " break)";
        }
        return theSecondaryHeadline;
    }

    public FmmHoliday getFmmHoliday() {
        return this.fmmHoliday;
    }

    public String getFmmHolidayName() {
        return this.fmmHoliday == null ? "" : this.fmmHoliday.getName();
    }

    public void setFmmHoliday(FmmHoliday fmmHoliday) {
        this.fmmHoliday = fmmHoliday;
    }

    public void setFmmHoliday(String anFmmHolidayName) {
        this.fmmHoliday = FmmHoliday.getObjectForName(anFmmHolidayName);
    }

    public void setWorkTaskList(ArrayList<WorkTask> workTaskList) {
        this.workTaskList = workTaskList;
    }

    @Override
    protected void initializeNodeCompletionSummaryMap() {
        super.initializeNodeCompletionSummaryMap();
        initializeNodeCompletionSummaryMap(FmmPerspective.WORK_PLANNING, FmmNodeDefinition.WORK_TASK);
    }

    @Override
    public void updateNodeCompletionSummary(FmmPerspective anFmmPerspective, NodeCompletionSummary aNodeSummary) {
        if(anFmmPerspective == FmmPerspective.WORK_PLANNING) {
            Collection<WorkTask> theWorkTaskCollection = getWorkTaskCollection();
            if(theWorkTaskCollection.size() > 0) {
                aNodeSummary.setShowNodeSummary(true);
                aNodeSummary.setSummaryPrefix("( " + countGreenWorkTasks(theWorkTaskCollection) + " ");
                aNodeSummary.setSummarySuffix(" of " + theWorkTaskCollection.size() + " )");
            } else {
                aNodeSummary.setShowNodeSummary(false);
            }
        }
    }

    private Collection<WorkTask> getWorkTaskCollection() {
        return FmmDatabaseMediator.getActiveMediator().retrieveWorkTaskList(this);
    }

    public ArrayList<WorkTask> getWorkTaskList() {
        if(this.workTaskList == null) {
            this.workTaskList = new ArrayList<WorkTask>(
                    FmmDatabaseMediator.getActiveMediator().retrieveWorkTaskList(this) );
        }
        return this.workTaskList;
    }

    private static int countGreenWorkTasks(Collection<WorkTask> aWorkTaskCollection) {
        int theGreenCount = 0;
        for(WorkTask theWorkTask : aWorkTaskCollection) {
            if(theWorkTask.isGreen()) {
                ++theGreenCount;
            }
        }
        return theGreenCount;
    }

    @Override
    public int getChildNodeCount(GcgPerspective aGcgPerspective) {  // only implemented for TreeView leaf nodes
        return getWorkTaskList() == null ? 0 : getWorkTaskList().size();
    }

    @Override
    public ArrayList<? extends FmmHeadlineNode> getChildList(FmmNodeDefinition aChildNodeDefinition) {
        ArrayList<? extends FmmHeadlineNodeImpl> theList = null;
        switch(aChildNodeDefinition) {
            case WORK_TASK:
                theList = FmmDatabaseMediator.getActiveMediator().retrieveWorkTaskList(this);
                break;
        }
        return theList;
    }

    public void setPrimaryParentId(String aNodeIdString) {
        setCadenceId(aNodeIdString);
    }
}
