/* @(#)FlywheelCadence.java
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

import com.flywheelms.gcongui.gcg.widget.date.GcgDateHelper;
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.enumerator.FmmHoliday;
import com.flywheelms.library.fmm.meta_data.FlywheelCadenceMetaData;
import com.flywheelms.library.fmm.meta_data.SequencedLinkNodeMetaData;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.completable.FmmCompletableNodeImpl;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.util.JsonHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class FlywheelCadence extends FmmCompletableNodeImpl {

	private static final long serialVersionUID = -7058798359923528163L;
	private String fiscalYearId;
    private FiscalYear fiscalYear;
    private FmmHoliday fmmHoliday;
    private ArrayList<WorkPlan> workPlanList;
    private ArrayList<WorkPackage> workPackageList;
    private Date scheduledEndDate;

	public FlywheelCadence(NodeId aNodeId, String aFiscalYearId) {
		super(aNodeId);
        setFiscalYearId(aFiscalYearId);
	}

    public FlywheelCadence(String aFiscalYearId, Date aScheduledStartDate, Date aScheduledEndDate) {
        this(new NodeId(FmmNodeDefinition.FLYWHEEL_CADENCE), aFiscalYearId);
    }

    public FlywheelCadence(String aNodeIdString, String aFiscalYearId) {
        super(FlywheelCadence.class, aNodeIdString);
        this.fiscalYearId = aFiscalYearId;
    }

    public FlywheelCadence(FiscalYear aFiscalYear) {
        super(new NodeId(FmmNodeDefinition.FLYWHEEL_CADENCE));
        setFiscalYear(aFiscalYear);
    }

    public FlywheelCadence(JSONObject aJsonObject) {
        super(FlywheelCadence.class, aJsonObject);
        try {
            validateSerializationFormatVersion(aJsonObject.getString(JsonHelper.key__SERIALIZATION_FORMAT_VERSION));
            setSequence(aJsonObject.getInt(SequencedLinkNodeMetaData.column_SEQUENCE));
            setFiscalYearId(aJsonObject.getString(FlywheelCadenceMetaData.column_FISCAL_YEAR_ID));
            setScheduledEndDate(aJsonObject.getLong(FlywheelCadenceMetaData.column_SCHEDULED_END_DATE));
            setWorkPlanList(aJsonObject.getJSONArray(FlywheelCadenceMetaData.child_fractals_WORK_PLAN_LIST));
            setWorkPackageList(aJsonObject.getJSONArray(FlywheelCadenceMetaData.child_fractals_WORK_PACKAGE_LIST));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void setWorkPlanList(JSONArray aJsonArray) {
        if(aJsonArray == null) {
            return;
        }
        this.workPlanList = new ArrayList<WorkPlan>();
        for(int i=0; i < aJsonArray.length(); ++i) {
            try {
                this.workPlanList.add(FmmDatabaseMediator.getActiveMediator().retrieveWorkPlan(
                        aJsonArray.getString(i) ));
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void setWorkPackageList(JSONArray aJsonArray) {
        if(aJsonArray == null) {
            return;
        }
        this.workPackageList = new ArrayList<WorkPackage>();
        for(int i=0; i < aJsonArray.length(); ++i) {
            try {
                this.workPackageList.add(FmmDatabaseMediator.getActiveMediator().retrieveWorkPackage(
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
            theJsonObject.put(FlywheelCadenceMetaData.column_FISCAL_YEAR_ID, getFiscalYearId());
            theJsonObject.put(FlywheelCadenceMetaData.column_SCHEDULED_END_DATE, getScheduledEndDateFormattedUtcLong());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return theJsonObject;
    }

    private JSONArray getWorkPlanNodeIdStringJsonArray() {
        JSONArray theJsonArray = new JSONArray();
        for(WorkPlan theWorkPlan : getWorkPlanList()) {
            theJsonArray.put(theWorkPlan.getNodeIdString());
        }
        return theJsonArray;
    }

    private JSONArray getWorkPackageNodeIdStringJsonArray() {
        JSONArray theJsonArray = new JSONArray();
        for(WorkPackage theWorkPackage : getWorkPackageList()) {
            theJsonArray.put(theWorkPackage.getNodeIdString());
        }
        return theJsonArray;
    }

    @Override
    public FlywheelCadence getClone() {
        return new FlywheelCadence(getJsonObject());
    }

    public String getFiscalYearId() {
        return this.fiscalYearId;
    }

    public FiscalYear getFiscalYear() {
        if(this.fiscalYear == null && this.fiscalYearId != null) {
            this.fiscalYear =
                    FmmDatabaseMediator.getActiveMediator().getFiscalYear(this.fiscalYearId);
        }
        return this.fiscalYear;
    }

    public void setFiscalYearId(String aNodeIdString) {
        this.fiscalYearId = aNodeIdString;
        if(this.fiscalYear != null && !this.fiscalYear.getNodeIdString().equals(aNodeIdString)) {
            this.fiscalYear = null;
        }
    }

    public void setFiscalYear(FiscalYear aFiscalYear) {
        this.fiscalYear = aFiscalYear;
        this.fiscalYearId = aFiscalYear.getNodeId().getNodeIdString();
    }

	public Date getScheduledStartDate() {
        Date theStartDate = null;
        if(this.workPlanList != null && this.workPlanList.size() > 0) {
            return this.workPlanList.get(0).getScheduledStartDate();
        }
		return theStartDate;
	}

    public Date getScheduledEndDate() {
        return this.scheduledEndDate;
    }

    public Long getScheduledEndDateFormattedUtcLong() {
        return GcgDateHelper.getFormattedUtcLong(this.scheduledEndDate);
    }

	public Date getScheduledEndDateFromWorkPlanList() {
        Date theEndDate = null;
        if(this.workPlanList != null && this.workPlanList.size() > 0) {
            return this.workPlanList.get(this.workPlanList.size() - 1).getScheduledEndDate();
        }
        return theEndDate;
	}

    public void setScheduledEndDate(Long aLongDate) {
        this.scheduledEndDate = GcgDateHelper.getDateFromFormattedUtcLong(aLongDate);
    }

	public boolean isWorkPackageMoveTarget() {
		return true;
	}

	public boolean isWorkPlanMoveTarget() {
		return true;
	}

    public boolean isHolidaySlack() {
        return this.fmmHoliday != null;
    }

    public FmmHoliday getHolidaySlack() {
        return this.fmmHoliday;
    }

    public void setHolidaySlack(FmmHoliday aHoliday) {
        this.fmmHoliday = aHoliday;
    }

    public ArrayList<WorkPlan> getWorkPlanList() {
        return this.workPlanList;
    }

    public void setWorkPlanList(ArrayList<WorkPlan> aWorkPlanList) {
        this.workPlanList = aWorkPlanList;
        this.scheduledEndDate = getScheduledEndDateFromWorkPlanList();
    }

    public ArrayList<WorkPackage> getWorkPackageList() {
        return this.workPackageList;
    }

    public void setWorkPackageList(ArrayList<WorkPackage> aWorkPackageList) {
        this.workPackageList = aWorkPackageList;
    }

    public String getSecondaryHeadline() {
        return "ending " + GcgDateHelper.getGuiDateString5(getScheduledEndDate());
    }
}
