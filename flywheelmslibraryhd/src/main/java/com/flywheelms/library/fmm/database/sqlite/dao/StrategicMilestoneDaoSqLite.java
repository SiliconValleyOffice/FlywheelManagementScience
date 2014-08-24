/* @(#)StrategicMilestoneDaoSqLite.java
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
import com.flywheelms.library.fmm.meta_data.IdNodeMetaData;
import com.flywheelms.library.fmm.meta_data.StrategicMilestoneMetaData;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.governable.StrategicMilestone;

import java.util.HashMap;

public class StrategicMilestoneDaoSqLite extends CompletableNodeDaoSqLite<StrategicMilestone> {

	private static StrategicMilestoneDaoSqLite singleton;

	public static StrategicMilestoneDaoSqLite getInstance() {
		if(StrategicMilestoneDaoSqLite.singleton == null) {
			StrategicMilestoneDaoSqLite.singleton = new StrategicMilestoneDaoSqLite();
		}
		return StrategicMilestoneDaoSqLite.singleton;
	}
	
	@Override
	public FmmNodeDefinition getFmmNodeDefinition() {
		return FmmNodeDefinition.STRATEGIC_MILESTONE;
	}

	@Override
	protected void buildColumnIndexMap(Cursor aCursor) {
		super.buildColumnIndexMap(aCursor);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, StrategicMilestoneMetaData.column_FISCAL_YEAR_ID);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CompletableNodeMetaData.column_SEQUENCE);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, StrategicMilestoneMetaData.column_TARGET_MONTH_END);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, StrategicMilestoneMetaData.column_TARGET_DATE);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, StrategicMilestoneMetaData.column_TARGET_IS_REVERSE_PLANNING);
	}

	@Override
	protected void getColumnValues(HashMap<String, Integer> aHashMap, Cursor aCursor, StrategicMilestone aStrategicMilestone) {
		super.getColumnValues(aHashMap, aCursor, aStrategicMilestone);
		aStrategicMilestone.setFiscalYearNodeIdString(aCursor.getString(aHashMap.get(StrategicMilestoneMetaData.column_FISCAL_YEAR_ID)));
		aStrategicMilestone.setSequence(aCursor.getInt(aHashMap.get(CompletableNodeMetaData.column_SEQUENCE)));
		aStrategicMilestone.setTargetMonthEnd(aCursor.getInt(aHashMap.get(StrategicMilestoneMetaData.column_TARGET_MONTH_END)));
		aStrategicMilestone.setTargetDate(aCursor.getLong(aHashMap.get(StrategicMilestoneMetaData.column_TARGET_DATE)));
		aStrategicMilestone.setTargetIsReversePlanning(aCursor.getInt(aHashMap.get(StrategicMilestoneMetaData.column_TARGET_IS_REVERSE_PLANNING)));
	}

	@Override
	public ContentValues buildContentValues(StrategicMilestone aStrategicMilestone) {
		ContentValues theContentValues = super.buildContentValues(aStrategicMilestone);
		theContentValues.put(StrategicMilestoneMetaData.column_FISCAL_YEAR_ID, aStrategicMilestone.getFiscalYearNodeIdString());
		theContentValues.put(CompletableNodeMetaData.column_SEQUENCE, aStrategicMilestone.getSequence());
		theContentValues.put(StrategicMilestoneMetaData.column_TARGET_MONTH_END, aStrategicMilestone.getTargetMonthEnd());
		theContentValues.put(StrategicMilestoneMetaData.column_TARGET_DATE, aStrategicMilestone.getTargetDateFormattedUtcLong());
		theContentValues.put(StrategicMilestoneMetaData.column_TARGET_IS_REVERSE_PLANNING, aStrategicMilestone.targetIsReversePlanningAsInt());
		return theContentValues;
	}

	@Override
	public ContentValues buildUpdateContentValues(StrategicMilestone aStrategicMilestone) {
		return buildContentValues(aStrategicMilestone);
	}

	@Override
	protected StrategicMilestone getNextObjectFromCursor(Cursor aCursor) {
		StrategicMilestone theStrategicMilestone = null;
		theStrategicMilestone = new StrategicMilestone(
				aCursor.getString(this.columnIndexMap.get(IdNodeMetaData.column_ID)),
				aCursor.getString(this.columnIndexMap.get(StrategicMilestoneMetaData.column_FISCAL_YEAR_ID)) );
		getColumnValues(this.columnIndexMap, aCursor, theStrategicMilestone);
		return theStrategicMilestone;
	}
	
}