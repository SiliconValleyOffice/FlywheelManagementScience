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
import com.flywheelms.library.fmm.enumerator.FmmHoliday;
import com.flywheelms.library.fmm.meta_data.FiscalYearHolidayBreakMetaData;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.governable.FiscalYear;
import com.flywheelms.library.fms.activity.FmsActivity;
import com.flywheelms.library.util.JsonHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class FiscalYearHolidayBreak extends FmmHeadlineNodeImpl {

    private String fiscalYearId;
    private FiscalYear fiscalYear;
    private FmmHoliday fmmHoliday;
    private Date holidayDate;
    private Date firstDay;
    private Date lastDay;

    public FiscalYearHolidayBreak(NodeId aNodeId, FiscalYear aFiscalYear, Date aHolidayDate, Date aStartDate, Date anEndDate) {
        super(aNodeId);
        this.fiscalYearId = aFiscalYear.getNodeIdString();
        this.fiscalYear = aFiscalYear;
        this.holidayDate = aHolidayDate;
        this.firstDay = aStartDate;
        this.lastDay = anEndDate;
    }

    public FiscalYearHolidayBreak(Class<? extends FmmHeadlineNodeImpl> aClass, JSONObject aJsonObject) {
        super(FiscalYearHolidayBreak.class, aJsonObject);
        try {
            validateSerializationFormatVersion(aJsonObject.getString(JsonHelper.key__SERIALIZATION_FORMAT_VERSION));
            setFiscalYearId(aJsonObject.getString(FiscalYearHolidayBreakMetaData.column_FISCAL_YEAR_ID));
            setHolidayDate(aJsonObject.getLong(FiscalYearHolidayBreakMetaData.column_HOLIDAY_DATE));
            setStartDate(aJsonObject.getLong(FiscalYearHolidayBreakMetaData.column_BREAK_START_DATE));
            setEndDate(aJsonObject.getLong(FiscalYearHolidayBreakMetaData.column_BREAK_END_DATE));
            setFmmHoliday(aJsonObject.getString(FiscalYearHolidayBreakMetaData.column_HOLIDAY));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static final String SERIALIZATION_FORMAT_VERSION = "0.1";

    public FiscalYearHolidayBreak(String aNodeIdString, String aFiscalYearId, String anFmmHolidayName, long aHolidayDateLong, long aBreakStartDateLong, long aBreakEndDateLong) {
        super(new NodeId(FiscalYearHolidayBreak.class, aNodeIdString));
        this.fiscalYearId = aFiscalYearId;
        setHolidayDate(aHolidayDateLong);
        setStartDate(aBreakStartDateLong);
        setEndDate(aBreakEndDateLong);
        setFmmHoliday(anFmmHolidayName);
    }

    public FiscalYearHolidayBreak(FiscalYear aFiscalYear, FmmHoliday anFmmHoliday, Date aHolidayDate, Date aBreakFirstDay, Date aBreakLastDay) {
        super(new NodeId(FmmNodeDefinition.FISCAL_YEAR_HOLIDAY_BREAK));
        setFiscalYear(aFiscalYear);
        this.fmmHoliday = anFmmHoliday;
        this.holidayDate = aHolidayDate;
        this.firstDay = aBreakFirstDay;
        this.lastDay = aBreakLastDay;
    }

    @Override
    public JSONObject getJsonObject() {
        JSONObject theJsonObject = super.getJsonObject();
        try {
            theJsonObject.put(JsonHelper.key__SERIALIZATION_FORMAT_VERSION, SERIALIZATION_FORMAT_VERSION);
            theJsonObject.put(FiscalYearHolidayBreakMetaData.column_FISCAL_YEAR_ID, getFiscalYearId());
            theJsonObject.put(FiscalYearHolidayBreakMetaData.column_HOLIDAY_DATE, getHolidayDateFormattedUtcLong());
            theJsonObject.put(FiscalYearHolidayBreakMetaData.column_BREAK_START_DATE, getStartDateFormattedUtcLong());
            theJsonObject.put(FiscalYearHolidayBreakMetaData.column_BREAK_END_DATE, getEndDateFormattedUtcLong());
            theJsonObject.put(FiscalYearHolidayBreakMetaData.column_HOLIDAY, getFmmHolidayName());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return theJsonObject;
    }

    public String getFiscalYearId() {
        return this.fiscalYearId;
    }

    public FiscalYear getFiscalYear() {
        if(this.fiscalYear == null && this.fiscalYearId != null) {
            this.fiscalYear =
                    FmsActivity.getActiveDatabaseMediator().retrieveFiscalYear(this.fiscalYearId);
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

    public FmmHoliday getFmmHoliday() {
        return this.fmmHoliday;
    }

    public String getFmmHolidayName() {
        return this.fmmHoliday == null ? "" : this.fmmHoliday.getName();
    }

    public void setFmmHoliday(FmmHoliday anFmmHoliday) {
        this.fmmHoliday = anFmmHoliday;
    }

    public void setFmmHoliday(String anFmmHolidayName) {
        this.fmmHoliday = FmmHoliday.getObjectForName(anFmmHolidayName);
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

    public Date getFirstDay() {
        return this.firstDay;
    }

    public Long getStartDateFormattedUtcLong() {
        return GcgDateHelper.getFormattedUtcLong(this.firstDay);
    }

    public void setFirstDay(Date firstDay) {
        this.firstDay = firstDay;
    }

    public void setStartDate(Long aLongDate) {
        this.firstDay = GcgDateHelper.getDateFromFormattedUtcLong(aLongDate);
    }

    public Date getLastDay() {
        return this.lastDay;
    }

    public Long getEndDateFormattedUtcLong() {
        return GcgDateHelper.getFormattedUtcLong(this.lastDay);
    }

    public void setLastDay(Date lastDay) {
        this.lastDay = lastDay;
    }

    public void setEndDate(Long aLongDate) {
        this.lastDay = GcgDateHelper.getDateFromFormattedUtcLong(aLongDate);
    }

    public static FiscalYearHolidayBreak getHolidayBreak(FmmHoliday anFmmHoliday, ArrayList<FiscalYearHolidayBreak> aHolidayBreakList) {
        for(FiscalYearHolidayBreak theBreak : aHolidayBreakList) {
            if(theBreak.getFmmHoliday() == anFmmHoliday) {
                return theBreak;
            }
        }
        return null;
    }
}
