/* @(#)FragLockDaoSqLite.java
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

import com.flywheelms.library.fmm.meta_data.FragLockMetaData;
import com.flywheelms.library.fmm.meta_data.IdNodeMetaData;
import com.flywheelms.library.fmm.meta_data.NodeFragMetaData;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.nodefrag.FragLock;
import com.flywheelms.library.gcg.widget.date.GcgDateHelper;

import java.util.HashMap;

public class FragLockDaoSqLite extends NodeFragDaoSqLite<FragLock> {

	private static FragLockDaoSqLite singleton;

	public static FragLockDaoSqLite getInstance() {
		if(FragLockDaoSqLite.singleton == null) {
			FragLockDaoSqLite.singleton = new FragLockDaoSqLite();
		}
		return FragLockDaoSqLite.singleton;
	}
	
	@Override
	public FmmNodeDefinition getFmmNodeDefinition() {
		return FmmNodeDefinition.FRAG_LOCK;
	}

	@Override
	protected void buildColumnIndexMap(Cursor aCursor) {
		super.buildColumnIndexMap(aCursor);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, FragLockMetaData.column_GRANDPARENT_ID);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, FragLockMetaData.column_GRANDPARENT_NODE_TYPE_CODE);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, FragLockMetaData.column_LOCKED);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, FragLockMetaData.column_LOCKED_BY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, FragLockMetaData.column_LOCKED_TIMESTAMP);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, FragLockMetaData.column_UNLOCKED_BY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, FragLockMetaData.column_UNLOCKED_TIMESTAMP);
	}

	@Override
	protected void getColumnValues(HashMap<String, Integer> aHashMap, Cursor aCursor, FragLock aFragLock) {
		aFragLock.setGrandparentNodeIdString(aCursor.getString(aHashMap.get(FragLockMetaData.column_GRANDPARENT_ID)));
		aFragLock.setGrandparentNodeTypeCode(aCursor.getString(aHashMap.get(FragLockMetaData.column_GRANDPARENT_NODE_TYPE_CODE)));
		aFragLock.setIsLocked(aCursor.getInt(aHashMap.get(FragLockMetaData.column_LOCKED)));
		aFragLock.setLockedBy(aCursor.getString(aHashMap.get(FragLockMetaData.column_LOCKED_BY)));
		aFragLock.setLockedTimestamp(GcgDateHelper.getDateFromIso8601DateString(aCursor.getString(aHashMap.get(FragLockMetaData.column_LOCKED_TIMESTAMP))));
		aFragLock.setUnlockedBy(aCursor.getString(aHashMap.get(FragLockMetaData.column_UNLOCKED_BY)));
		aFragLock.setUnlockedTimestamp(GcgDateHelper.getDateFromIso8601DateString(aCursor.getString(aHashMap.get(FragLockMetaData.column_UNLOCKED_TIMESTAMP))));
	}

	@Override
	protected FragLock getNextObjectFromCursor(Cursor aCursor) {
		FragLock theFragLock = null;
		theFragLock = new FragLock(
				aCursor.getString(this.columnIndexMap.get(IdNodeMetaData.column_ID)),
				aCursor.getString(this.columnIndexMap.get(NodeFragMetaData.column_PARENT_ID)) );
		getColumnValues(this.columnIndexMap, aCursor, theFragLock);
		return theFragLock;
	}

	@Override
	public ContentValues buildContentValues(FragLock aFragLock) {
		ContentValues theContentValues = super.buildContentValues(aFragLock);
		theContentValues.put(FragLockMetaData.column_GRANDPARENT_ID, aFragLock.getGrandparentNodeIdString());
		theContentValues.put(FragLockMetaData.column_GRANDPARENT_NODE_TYPE_CODE, aFragLock.getGrandparentNodeTypeCode());
		theContentValues.put(FragLockMetaData.column_LOCKED, aFragLock.isLocked());
		theContentValues.put(FragLockMetaData.column_LOCKED_BY, aFragLock.getLockedByNodeIdString());
		GcgDateHelper.setIso8601DateContentValue(theContentValues, FragLockMetaData.column_LOCKED_TIMESTAMP, aFragLock.getLockedTimestamp());
		theContentValues.put(FragLockMetaData.column_UNLOCKED_BY, aFragLock.getUnlockedByNodeIdString());
		GcgDateHelper.setIso8601DateContentValue(theContentValues, FragLockMetaData.column_UNLOCKED_TIMESTAMP, aFragLock.getUnlockedTimestamp());
		return theContentValues;
	}

}
