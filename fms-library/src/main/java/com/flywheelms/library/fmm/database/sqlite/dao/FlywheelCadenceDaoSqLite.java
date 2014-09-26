/* @(#)FlywheelCadenceDaoSqLite.java
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

package com.flywheelms.library.fmm.database.sqlite.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.flywheelms.library.fmm.meta_data.CompletableNodeMetaData;
import com.flywheelms.library.fmm.meta_data.FlywheelCadenceMetaData;
import com.flywheelms.library.fmm.meta_data.IdNodeMetaData;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.governable.FlywheelCadence;

import java.util.HashMap;

public class FlywheelCadenceDaoSqLite extends CompletableNodeDaoSqLite<FlywheelCadence> {

    private static FlywheelCadenceDaoSqLite singleton;

    public static FlywheelCadenceDaoSqLite getInstance() {
        if(FlywheelCadenceDaoSqLite.singleton == null) {
            FlywheelCadenceDaoSqLite.singleton = new FlywheelCadenceDaoSqLite();
        }
        return FlywheelCadenceDaoSqLite.singleton;
    }

    @Override
    public FmmNodeDefinition getFmmNodeDefinition() {
        return FmmNodeDefinition.FLYWHEEL_CADENCE;
    }

    @Override
    protected void buildColumnIndexMap(Cursor aCursor) {
        super.buildColumnIndexMap(aCursor);
        putColumnIndexMapEntry(this.columnIndexMap, aCursor, FlywheelCadenceMetaData.column_FISCAL_YEAR_ID);
        putColumnIndexMapEntry(this.columnIndexMap, aCursor, CompletableNodeMetaData.column_SEQUENCE);
        putColumnIndexMapEntry(this.columnIndexMap, aCursor, FlywheelCadenceMetaData.column_SCHEDULED_START_DATE);
        putColumnIndexMapEntry(this.columnIndexMap, aCursor, FlywheelCadenceMetaData.column_SCHEDULED_END_DATE);
    }

    @Override
    protected void getColumnValues(HashMap<String, Integer> aHashMap, Cursor aCursor, FlywheelCadence aFlywheelCadence) {
        super.getColumnValues(aHashMap, aCursor, aFlywheelCadence);
    }

    @Override
    public ContentValues buildContentValues(FlywheelCadence aFlywheelCadence) {
        ContentValues theContentValues = super.buildContentValues(aFlywheelCadence);
        theContentValues.put(FlywheelCadenceMetaData.column_FISCAL_YEAR_ID, aFlywheelCadence.getFiscalYearId());
        theContentValues.put(FlywheelCadenceMetaData.column_SCHEDULED_START_DATE, aFlywheelCadence.getScheduledStartDateFormattedUtcLong());
        theContentValues.put(FlywheelCadenceMetaData.column_SCHEDULED_END_DATE, aFlywheelCadence.getScheduledEndDateFormattedUtcLong());
        return theContentValues;
    }

    @Override
    public ContentValues buildUpdateContentValues(FlywheelCadence aFlywheelCadence) {
        return buildContentValues(aFlywheelCadence);
    }

    @Override
    protected FlywheelCadence getNextObjectFromCursor(Cursor aCursor) {
        FlywheelCadence theFlywheelCadence = new FlywheelCadence(
                aCursor.getString(this.columnIndexMap.get(IdNodeMetaData.column_ID)),
                aCursor.getString(this.columnIndexMap.get(FlywheelCadenceMetaData.column_FISCAL_YEAR_ID)),
                aCursor.getLong(this.columnIndexMap.get(FlywheelCadenceMetaData.column_SCHEDULED_START_DATE)),
                aCursor.getLong(this.columnIndexMap.get(FlywheelCadenceMetaData.column_SCHEDULED_START_DATE)) );
        getColumnValues(this.columnIndexMap, aCursor, theFlywheelCadence);
        return theFlywheelCadence;
    }

}