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

package com.flywheelms.library.fmm.database.sqlite.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.flywheelms.library.fmm.meta_data.FiscalYearHolidayBreakMetaData;
import com.flywheelms.library.fmm.meta_data.IdNodeMetaData;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.headline.FiscalYearHolidayBreak;

import java.util.HashMap;

public class FiscalYearHolidayBreakDaoSqLite extends HeadlineNodeDaoSqLite<FiscalYearHolidayBreak> {

    private static FiscalYearHolidayBreakDaoSqLite singleton;

    public static FiscalYearHolidayBreakDaoSqLite getInstance() {
        if(FiscalYearHolidayBreakDaoSqLite.singleton == null) {
            FiscalYearHolidayBreakDaoSqLite.singleton = new FiscalYearHolidayBreakDaoSqLite();
        }
        return FiscalYearHolidayBreakDaoSqLite.singleton;
    }

    @Override
    public FmmNodeDefinition getFmmNodeDefinition() {
        return FmmNodeDefinition.FISCAL_YEAR_HOLIDAY_BREAK;
    }

    @Override
    protected void buildColumnIndexMap(Cursor aCursor) {
        super.buildColumnIndexMap(aCursor);
        putColumnIndexMapEntry(this.columnIndexMap, aCursor, FiscalYearHolidayBreakMetaData.column_FISCAL_YEAR_ID);
        putColumnIndexMapEntry(this.columnIndexMap, aCursor, FiscalYearHolidayBreakMetaData.column_HOLIDAY_DATE);
        putColumnIndexMapEntry(this.columnIndexMap, aCursor, FiscalYearHolidayBreakMetaData.column_BREAK_START_DATE);
        putColumnIndexMapEntry(this.columnIndexMap, aCursor, FiscalYearHolidayBreakMetaData.column_BREAK_END_DATE);
    }

    @Override
    protected void getColumnValues(HashMap<String, Integer> aHashMap, Cursor aCursor, FiscalYearHolidayBreak aFiscalYearHolidayBreak) {
        super.getColumnValues(aHashMap, aCursor, aFiscalYearHolidayBreak);
        // everything is initialized in the re-hydrating constructor
    }

    @Override
    public ContentValues buildContentValues(FiscalYearHolidayBreak aFiscalYearHolidayBreak) {
        ContentValues theContentValues = super.buildContentValues(aFiscalYearHolidayBreak);
        theContentValues.put(FiscalYearHolidayBreakMetaData.column_FISCAL_YEAR_ID, aFiscalYearHolidayBreak.getFiscalYearId());
        theContentValues.put(FiscalYearHolidayBreakMetaData.column_HOLIDAY_DATE, aFiscalYearHolidayBreak.getHolidayDateFormattedUtcLong());
        theContentValues.put(FiscalYearHolidayBreakMetaData.column_BREAK_START_DATE, aFiscalYearHolidayBreak.getStartDateFormattedUtcLong());
        theContentValues.put(FiscalYearHolidayBreakMetaData.column_BREAK_END_DATE, aFiscalYearHolidayBreak.getEndDateFormattedUtcLong());
        return theContentValues;
    }

    @Override
    public ContentValues buildUpdateContentValues(FiscalYearHolidayBreak aFiscalYearHolidayBreak) {
        return buildContentValues(aFiscalYearHolidayBreak);
    }

    @Override
    protected FiscalYearHolidayBreak getNextObjectFromCursor(Cursor aCursor) {
        FiscalYearHolidayBreak theFiscalYearHolidayBreak = new FiscalYearHolidayBreak(
            aCursor.getString(this.columnIndexMap.get(IdNodeMetaData.column_ID)),
            aCursor.getString(this.columnIndexMap.get(FiscalYearHolidayBreakMetaData.column_FISCAL_YEAR_ID)) ,
            aCursor.getLong(this.columnIndexMap.get(FiscalYearHolidayBreakMetaData.column_HOLIDAY_DATE)),
            aCursor.getLong(this.columnIndexMap.get(FiscalYearHolidayBreakMetaData.column_BREAK_START_DATE)),
            aCursor.getLong(this.columnIndexMap.get(FiscalYearHolidayBreakMetaData.column_BREAK_END_DATE)) );
        getColumnValues(this.columnIndexMap, aCursor, theFiscalYearHolidayBreak);
        return theFiscalYearHolidayBreak;
    }

}