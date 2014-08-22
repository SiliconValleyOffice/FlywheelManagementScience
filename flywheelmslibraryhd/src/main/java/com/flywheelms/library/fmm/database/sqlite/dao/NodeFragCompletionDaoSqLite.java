/* @(#)NodeFragCompletionDaoSqLite.java
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

import java.util.HashMap;

import android.content.ContentValues;
import android.database.Cursor;

import com.flywheelms.library.fmm.meta_data.IdNodeMetaData;
import com.flywheelms.library.fmm.meta_data.NodeFragCompletionMetaData;
import com.flywheelms.library.fmm.meta_data.NodeFragMetaData;
import com.flywheelms.library.fmm.node.impl.enumerator.CompletableWorkStatus;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragCompletion;
import com.flywheelms.library.gcg.widget.date.GcgDateHelper;

public class NodeFragCompletionDaoSqLite extends NodeFragDaoSqLite<NodeFragCompletion> {

	private static NodeFragCompletionDaoSqLite singleton;

	public static NodeFragCompletionDaoSqLite getInstance() {
		if(NodeFragCompletionDaoSqLite.singleton == null) {
			NodeFragCompletionDaoSqLite.singleton = new NodeFragCompletionDaoSqLite();
		}
		return NodeFragCompletionDaoSqLite.singleton;
	}
	
	@Override
	public FmmNodeDefinition getFmmNodeDefinition() {
		return FmmNodeDefinition.NODE_FRAG__COMPLETION;
	}

	@Override
	protected void buildColumnIndexMap(Cursor aCursor) {
		super.buildColumnIndexMap(aCursor);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragCompletionMetaData.column_AUTO_COMPLETABLE);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragCompletionMetaData.column_WORK_STATUS);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragCompletionMetaData.column_YELLOW_BY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragCompletionMetaData.column_YELLOW_TIMESTAMP);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragCompletionMetaData.column_ORANGE_BY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragCompletionMetaData.column_ORANGE_TIMESTAMP);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragCompletionMetaData.column_PINK_BY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragCompletionMetaData.column_PINK_TIMESTAMP);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragCompletionMetaData.column_GREEN_BY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragCompletionMetaData.column_GREEN_TIMESTAMP);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragCompletionMetaData.column_COMPLETION_CONFIRMED_BY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragCompletionMetaData.column_COMPLETION_CONFIRMED_TIMESTAMP);
	}

	@Override
	protected void getColumnValues(HashMap<String, Integer> aHashMap, Cursor aCursor, NodeFragCompletion aNodeFragCompletion) {
		aNodeFragCompletion.setAutoCompletable(aCursor.getShort(aHashMap.get(NodeFragCompletionMetaData.column_AUTO_COMPLETABLE)));
		aNodeFragCompletion.setCompletableWorkStatus(CompletableWorkStatus.getObjectForCode(aCursor.getString(aHashMap.get(NodeFragCompletionMetaData.column_WORK_STATUS))));
		aNodeFragCompletion.setYellowBy(aCursor.getString(aHashMap.get(NodeFragCompletionMetaData.column_YELLOW_BY)));
		aNodeFragCompletion.setYellowTimestamp(GcgDateHelper.getDateFromIso8601DateString(aCursor.getString(aHashMap.get(NodeFragCompletionMetaData.column_YELLOW_TIMESTAMP))));
		aNodeFragCompletion.setOrangeBy(aCursor.getString(aHashMap.get(NodeFragCompletionMetaData.column_ORANGE_BY)));
		aNodeFragCompletion.setOrangeTimestamp(GcgDateHelper.getDateFromIso8601DateString(aCursor.getString(aHashMap.get(NodeFragCompletionMetaData.column_ORANGE_TIMESTAMP))));
		aNodeFragCompletion.setPinkBy(aCursor.getString(aHashMap.get(NodeFragCompletionMetaData.column_PINK_BY)));
		aNodeFragCompletion.setPinkTimestamp(GcgDateHelper.getDateFromIso8601DateString(aCursor.getString(aHashMap.get(NodeFragCompletionMetaData.column_PINK_TIMESTAMP))));
		aNodeFragCompletion.setGreenBy(aCursor.getString(aHashMap.get(NodeFragCompletionMetaData.column_GREEN_BY)));
		aNodeFragCompletion.setGreenTimestamp(GcgDateHelper.getDateFromIso8601DateString(aCursor.getString(aHashMap.get(NodeFragCompletionMetaData.column_GREEN_TIMESTAMP))));
		aNodeFragCompletion.setCompletionConfirmedBy(aCursor.getString(aHashMap.get(NodeFragCompletionMetaData.column_COMPLETION_CONFIRMED_BY)));
		aNodeFragCompletion.setCompletionConfirmedTimestamp(GcgDateHelper.getDateFromIso8601DateString(aCursor.getString(aHashMap.get(NodeFragCompletionMetaData.column_COMPLETION_CONFIRMED_TIMESTAMP))));
	}

	@Override
	protected NodeFragCompletion getNextObjectFromCursor(Cursor aCursor) {
		NodeFragCompletion theNodeFragCompletion = null;
		theNodeFragCompletion = new NodeFragCompletion(
				aCursor.getString(this.columnIndexMap.get(IdNodeMetaData.column_ID)),
				aCursor.getString(this.columnIndexMap.get(NodeFragMetaData.column_PARENT_ID)) );
		getColumnValues(this.columnIndexMap, aCursor, theNodeFragCompletion);
		return theNodeFragCompletion;
	}

	@Override
	public ContentValues buildContentValues(NodeFragCompletion aNodeFragCompletion) {
		ContentValues theContentValues = super.buildContentValues(aNodeFragCompletion);
		theContentValues.put(NodeFragCompletionMetaData.column_AUTO_COMPLETABLE, aNodeFragCompletion.getAutocompletable());
		theContentValues.put(NodeFragCompletionMetaData.column_WORK_STATUS, aNodeFragCompletion.getCompletableWorkStatusCode());
		theContentValues.put(NodeFragCompletionMetaData.column_YELLOW_BY, aNodeFragCompletion.getYellowByNodeIdString());
		GcgDateHelper.setIso8601DateContentValue(theContentValues, NodeFragCompletionMetaData.column_YELLOW_TIMESTAMP, aNodeFragCompletion.getYellowTimestamp());
		theContentValues.put(NodeFragCompletionMetaData.column_ORANGE_BY, aNodeFragCompletion.getOrangeByNodeIdString());
		GcgDateHelper.setIso8601DateContentValue(theContentValues, NodeFragCompletionMetaData.column_ORANGE_TIMESTAMP, aNodeFragCompletion.getOrangeTimestamp());
		theContentValues.put(NodeFragCompletionMetaData.column_PINK_BY, aNodeFragCompletion.getPinkByNodeIdString());
		GcgDateHelper.setIso8601DateContentValue(theContentValues, NodeFragCompletionMetaData.column_PINK_TIMESTAMP, aNodeFragCompletion.getPinkTimestamp());
		theContentValues.put(NodeFragCompletionMetaData.column_GREEN_BY, aNodeFragCompletion.getGreenByNodeIdString());
		GcgDateHelper.setIso8601DateContentValue(theContentValues, NodeFragCompletionMetaData.column_GREEN_TIMESTAMP, aNodeFragCompletion.getGreenTimestamp());
		theContentValues.put(NodeFragCompletionMetaData.column_COMPLETION_CONFIRMED_BY, aNodeFragCompletion.getCompletionConfirmedByNodeIdString());
		GcgDateHelper.setIso8601DateContentValue(theContentValues, NodeFragCompletionMetaData.column_COMPLETION_CONFIRMED_TIMESTAMP, aNodeFragCompletion.getCompletionConfirmedTimestamp());
		return theContentValues;
	}

}
