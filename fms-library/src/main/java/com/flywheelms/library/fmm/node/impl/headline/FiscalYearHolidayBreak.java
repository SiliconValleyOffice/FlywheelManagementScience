/*
 * Copyright (C) 2012 by Steven D. Stamps
 *
 *              Trademarks & Copyrights
 *  Flywheel Management Science(TM), Flywheel Management Model(TM),
 *  Flywheel Story Editor(TM) and FlywheelMS(TM) are exclusive trademarks
 *  of Steven D. Stamps and may only be used freely for the purpose of
 *  identifying the unforked version of this software.  Subsequent forks
 *  may not use these trademarks.  All other rights are reserved.
 *
 *  DecKanGL (Decorated Kanban Glyph Language) and TribKn (Tribal Knowledge)
 *  are also exclusive trademarks of Steven D. Stamps.  These may be used
 *  freely within the unforked FlywheelMS application and documentation.
 *  All other rights are reserved.
 *
 *  gConGUI (game Controller Graphical User Interface) is an exclusive
 *  trademark of Steven D. Stamps.  This trademark may be used freely
 *  within the unforked FlywheelMS application and documentation.
 *  All other rights are reserved.
 *
 * * Trademark information is available at
 * * <http://www.flywheelms.com/trademarks>
 * *
 * * Flywheel Management Science(TM) is a copyrighted body of management
 * * metaphors, governance processes, and leadership techniques that is
 * * owned by Steven D. Stamps.  These copyrighted materials may be freely
 * * used, without alteration, by the community (users and developers)
 * * surrounding this GPL3-licensed software.  Additional copyright
 * * information is available at <http://www.flywheelms.org/copyrights>
 * *
 * *              GPL3 Software License
 * * This program is free software: you can use it, redistribute it and/or
 * * modify it under the terms of the GNU General Public License, version 3,
 * * as published by the Free Software Foundation. This program is distributed
 * * in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * * PURPOSE.  See the GNU General Public License for more details. You should
 * * have received a copy of the GNU General Public License, in a file named
 * * COPYING, along with this program.  If you cannot find your copy, see
 * * <http://www.gnu.org/licenses/gpl-3.0.html>.
 */

package com.flywheelms.library.fmm.node.impl.headline;

import com.flywheelms.gcongui.gcg.widget.date.GcgDateHelper;
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.meta_data.FiscalYearHolidayBreakMetaData;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.governable.FiscalYear;
import com.flywheelms.library.util.JsonHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class FiscalYearHolidayBreak extends FmmHeadlineNodeImpl {

    private String fiscalYearNodeIdString;
    private FiscalYear fiscalYear;
    private Date holidayDate;
    private Date startDate;
    private Date endDate;

    public FiscalYearHolidayBreak(NodeId aNodeId, FiscalYear aFiscalYear, Date aHolidayDate, Date aStartDate, Date anEndDate) {
        super(aNodeId);
        this.fiscalYearNodeIdString = aFiscalYear.getNodeIdString();
        this.fiscalYear = aFiscalYear;
        this.holidayDate = aHolidayDate;
        this.startDate = aStartDate;
        this.endDate = anEndDate;
    }

    public FiscalYearHolidayBreak(Class<? extends FmmHeadlineNodeImpl> aClass, JSONObject aJsonObject) {
        super(FiscalYearHolidayBreak.class, aJsonObject);
        try {
            validateSerializationFormatVersion(aJsonObject.getString(JsonHelper.key__SERIALIZATION_FORMAT_VERSION));
            setFiscalYearNodeIdString(aJsonObject.getString(FiscalYearHolidayBreakMetaData.column_FISCAL_YEAR_ID));
            setHolidayDate(aJsonObject.getLong(FiscalYearHolidayBreakMetaData.column_HOLIDAY_DATE));
            setStartDate(aJsonObject.getLong(FiscalYearHolidayBreakMetaData.column_BREAK_START_DATE));
            setEndDate(aJsonObject.getLong(FiscalYearHolidayBreakMetaData.column_BREAK_END_DATE));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static final String SERIALIZATION_FORMAT_VERSION = "0.1";

    public FiscalYearHolidayBreak(String aNodeIdString, String aFiscalYearId, long aHolidayDateLong, long aBreakStartDateLong, long aBreakEndDateLong) {
        super(new NodeId(FiscalYearHolidayBreak.class, aNodeIdString));
        this.fiscalYearNodeIdString = aFiscalYearId;
        setHolidayDate(aHolidayDateLong);
        setStartDate(aBreakStartDateLong);
        setEndDate(aBreakEndDateLong);
    }

    @Override
    public JSONObject getJsonObject() {
        JSONObject theJsonObject = super.getJsonObject();
        try {
            theJsonObject.put(JsonHelper.key__SERIALIZATION_FORMAT_VERSION, SERIALIZATION_FORMAT_VERSION);
            theJsonObject.put(FiscalYearHolidayBreakMetaData.column_FISCAL_YEAR_ID, getFiscalYearNodeIdString());
            theJsonObject.put(FiscalYearHolidayBreakMetaData.column_HOLIDAY_DATE, getHolidayDateFormattedUtcLong());
            theJsonObject.put(FiscalYearHolidayBreakMetaData.column_BREAK_START_DATE, getStartDateFormattedUtcLong());
            theJsonObject.put(FiscalYearHolidayBreakMetaData.column_BREAK_END_DATE, getEndDateFormattedUtcLong());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return theJsonObject;
    }

    public String getFiscalYearNodeIdString() {
        return this.fiscalYearNodeIdString;
    }

    public FiscalYear getFiscalYear() {
        if(this.fiscalYear == null && this.fiscalYearNodeIdString != null) {
            this.fiscalYear =
                    FmmDatabaseMediator.getActiveMediator().getFiscalYear(this.fiscalYearNodeIdString);
        }
        return this.fiscalYear;
    }

    public void setFiscalYearNodeIdString(String aNodeIdString) {
        this.fiscalYearNodeIdString = aNodeIdString;
        if(this.fiscalYear != null && !this.fiscalYear.getNodeIdString().equals(aNodeIdString)) {
            this.fiscalYear = null;
        }
    }

    public void setFiscalYear(FiscalYear aFiscalYear) {
        this.fiscalYear = aFiscalYear;
        this.fiscalYearNodeIdString = aFiscalYear.getNodeId().getNodeIdString();
    }

    public Date getHolidayDate() {
        return this.holidayDate;
    }

    public Long getHolidayDateFormattedUtcLong() {
        return GcgDateHelper.getFormattedUtcLong(this.holidayDate);
    }

    public void setHolidayDate(Date holidayDate) {
        this.holidayDate = holidayDate;
    }

    public void setHolidayDate(Long aLongDate) {
        this.holidayDate = GcgDateHelper.getDateFromFormattedUtcLong(aLongDate);
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public Long getStartDateFormattedUtcLong() {
        return GcgDateHelper.getFormattedUtcLong(this.startDate);
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setStartDate(Long aLongDate) {
        this.startDate = GcgDateHelper.getDateFromFormattedUtcLong(aLongDate);
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public Long getEndDateFormattedUtcLong() {
        return GcgDateHelper.getFormattedUtcLong(this.endDate);
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setEndDate(Long aLongDate) {
        this.endDate = GcgDateHelper.getDateFromFormattedUtcLong(aLongDate);
    }
}
