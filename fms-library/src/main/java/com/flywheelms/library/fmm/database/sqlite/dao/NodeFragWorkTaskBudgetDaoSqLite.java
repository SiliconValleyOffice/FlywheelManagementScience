/* @(#)NodeFragWorkTaskBudgetDaoSqLite.java
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

import com.flywheelms.gcongui.gcg.widget.date.GcgDateHelper;
import com.flywheelms.library.fmm.meta_data.IdNodeMetaData;
import com.flywheelms.library.fmm.meta_data.NodeFragMetaData;
import com.flywheelms.library.fmm.meta_data.NodeFragWorkTaskBudgetMetaData;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragWorkTaskBudget;

import java.util.HashMap;

public class NodeFragWorkTaskBudgetDaoSqLite extends NodeFragDaoSqLite<NodeFragWorkTaskBudget> {

	private static NodeFragWorkTaskBudgetDaoSqLite singleton;

	public static NodeFragWorkTaskBudgetDaoSqLite getInstance() {
		if(NodeFragWorkTaskBudgetDaoSqLite.singleton == null) {
			NodeFragWorkTaskBudgetDaoSqLite.singleton = new NodeFragWorkTaskBudgetDaoSqLite();
		}
		return NodeFragWorkTaskBudgetDaoSqLite.singleton;
	}
	
	@Override
	public FmmNodeDefinition getFmmNodeDefinition() {
		return FmmNodeDefinition.NODE_FRAG__WORK_TASK_BUDGET;
	}

	@Override
	protected void buildColumnIndexMap(Cursor aCursor) {
		super.buildColumnIndexMap(aCursor);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragWorkTaskBudgetMetaData.column_ESTIMATED_TASK_COUNT);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragWorkTaskBudgetMetaData.column_ESTIMATED_AVERAGE_HOURS_PER_TASK);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragWorkTaskBudgetMetaData.column_ESTIMATE_BY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragWorkTaskBudgetMetaData.column_ESTIMATE_TIMESTAMP);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragWorkTaskBudgetMetaData.column_ESTIMATE_DATA_QUALITY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragWorkTaskBudgetMetaData.column_BUDGETED_TASK_COUNT);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragWorkTaskBudgetMetaData.column_BUDGETED_AVERAGE_HOURS_PER_TASK);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragWorkTaskBudgetMetaData.column_BUDGET_BY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragWorkTaskBudgetMetaData.column_BUDGET_TIMESTAMP);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragWorkTaskBudgetMetaData.column_BUDGET_DATA_QUALITY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragWorkTaskBudgetMetaData.column_WORK_BREAKDOWN_ESTIMATED_TASK_COUNT);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragWorkTaskBudgetMetaData.column_WORK_BREAKDOWN_ESTIMATED_AVERAGE_HOURS_PER_TASK);
        putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragWorkTaskBudgetMetaData.column_WORK_BREAKDOWN_BUDGETED_TASK_COUNT);
        putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragWorkTaskBudgetMetaData.column_WORK_BREAKDOWN_BUDGETED_AVERAGE_HOURS_PER_TASK);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragWorkTaskBudgetMetaData.column_COMPLETED_TASK_COUNT);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragWorkTaskBudgetMetaData.column_COMPLETED_AVERAGE_HOURS_PER_TASK);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragWorkTaskBudgetMetaData.column_SERIALIZED_HISTORY);
	}

	@Override
	protected void getColumnValues(HashMap<String, Integer> aHashMap, Cursor aCursor, NodeFragWorkTaskBudget aNodeFragWorkTaskBudget) {
		// we don't call super because the node is constructed with the NodeId
		aNodeFragWorkTaskBudget.setEstimatedTaskCount(aCursor.getInt(aHashMap.get(NodeFragWorkTaskBudgetMetaData.column_ESTIMATED_TASK_COUNT)));
		aNodeFragWorkTaskBudget.setEstimatedAverageHoursPerTask(aCursor.getFloat(aHashMap.get(NodeFragWorkTaskBudgetMetaData.column_ESTIMATED_AVERAGE_HOURS_PER_TASK)));
		aNodeFragWorkTaskBudget.setEstimateByCommunityMemberId(aCursor.getString(aHashMap.get(NodeFragWorkTaskBudgetMetaData.column_ESTIMATE_BY)));
		aNodeFragWorkTaskBudget.setEstimateTimestamp(GcgDateHelper.getDateFromIso8601DateString(aCursor.getString(aHashMap.get(NodeFragWorkTaskBudgetMetaData.column_ESTIMATE_TIMESTAMP))));
		aNodeFragWorkTaskBudget.setEstimateFmmDataQuality(aCursor.getString(aHashMap.get(NodeFragWorkTaskBudgetMetaData.column_ESTIMATE_DATA_QUALITY)));
		aNodeFragWorkTaskBudget.setBudgetedTaskCount(aCursor.getInt(aHashMap.get(NodeFragWorkTaskBudgetMetaData.column_BUDGETED_TASK_COUNT)));
		aNodeFragWorkTaskBudget.setBudgetedAverageHoursPerTask(aCursor.getFloat(aHashMap.get(NodeFragWorkTaskBudgetMetaData.column_BUDGETED_AVERAGE_HOURS_PER_TASK)));
		aNodeFragWorkTaskBudget.setBudgetByCommunityMemberId(aCursor.getString(aHashMap.get(NodeFragWorkTaskBudgetMetaData.column_BUDGET_BY)));
		aNodeFragWorkTaskBudget.setBudgetTimestamp(GcgDateHelper.getDateFromIso8601DateString(aCursor.getString(aHashMap.get(NodeFragWorkTaskBudgetMetaData.column_BUDGET_TIMESTAMP))));
		aNodeFragWorkTaskBudget.setBudgetFmmDataQuality(aCursor.getString(aHashMap.get(NodeFragWorkTaskBudgetMetaData.column_BUDGET_DATA_QUALITY)));
		aNodeFragWorkTaskBudget.setWorkBreakdownEstimatedTaskCount(aCursor.getInt(aHashMap.get(NodeFragWorkTaskBudgetMetaData.column_WORK_BREAKDOWN_ESTIMATED_TASK_COUNT)));
		aNodeFragWorkTaskBudget.setWorkBreakdownEstimatedAverageHoursPerTask(aCursor.getFloat(aHashMap.get(NodeFragWorkTaskBudgetMetaData.column_WORK_BREAKDOWN_ESTIMATED_AVERAGE_HOURS_PER_TASK)));
		aNodeFragWorkTaskBudget.setWorkBreakdownBudgetedTaskCount(aCursor.getInt(aHashMap.get(NodeFragWorkTaskBudgetMetaData.column_WORK_BREAKDOWN_BUDGETED_TASK_COUNT)));
		aNodeFragWorkTaskBudget.setWorkBreakdownBudgetedAverageHoursPerTask(aCursor.getFloat(aHashMap.get(NodeFragWorkTaskBudgetMetaData.column_WORK_BREAKDOWN_BUDGETED_AVERAGE_HOURS_PER_TASK)));
		aNodeFragWorkTaskBudget.setCompletedTaskCount(aCursor.getInt(aHashMap.get(NodeFragWorkTaskBudgetMetaData.column_COMPLETED_TASK_COUNT)));
		aNodeFragWorkTaskBudget.setCompletedAverageHoursPerTask(aCursor.getFloat(aHashMap.get(NodeFragWorkTaskBudgetMetaData.column_COMPLETED_AVERAGE_HOURS_PER_TASK)));
		aNodeFragWorkTaskBudget.setSerialized_history(aCursor.getString(aHashMap.get(NodeFragWorkTaskBudgetMetaData.column_SERIALIZED_HISTORY)));
	}

	@Override
	protected NodeFragWorkTaskBudget getNextObjectFromCursor(Cursor aCursor) {
		NodeFragWorkTaskBudget theNodeFragWorkTaskBudget;
		theNodeFragWorkTaskBudget = new NodeFragWorkTaskBudget(
				aCursor.getString(this.columnIndexMap.get(IdNodeMetaData.column_ID)),
				aCursor.getString(this.columnIndexMap.get(NodeFragMetaData.column_PARENT_ID)) );
		getColumnValues(this.columnIndexMap, aCursor, theNodeFragWorkTaskBudget);
		return theNodeFragWorkTaskBudget;
	}

	@Override
	public ContentValues buildContentValues(NodeFragWorkTaskBudget aNodeFragWorkTaskBudget) {
		ContentValues theContentValues = super.buildContentValues(aNodeFragWorkTaskBudget);
		theContentValues.put(NodeFragWorkTaskBudgetMetaData.column_ESTIMATED_TASK_COUNT, aNodeFragWorkTaskBudget.getEstimatedTaskCount());
		theContentValues.put(NodeFragWorkTaskBudgetMetaData.column_ESTIMATED_AVERAGE_HOURS_PER_TASK, aNodeFragWorkTaskBudget.getEstimatedAverageHoursPerTask());
		theContentValues.put(NodeFragWorkTaskBudgetMetaData.column_ESTIMATE_BY, aNodeFragWorkTaskBudget.getEstimateByCommunityMemberId());
		GcgDateHelper.setIso8601DateContentValue(theContentValues, NodeFragWorkTaskBudgetMetaData.column_ESTIMATE_TIMESTAMP, aNodeFragWorkTaskBudget.getEstimateTimestamp());
		theContentValues.put(NodeFragWorkTaskBudgetMetaData.column_ESTIMATE_DATA_QUALITY, aNodeFragWorkTaskBudget.getEstimateFmmDataQuality().getName());

		theContentValues.put(NodeFragWorkTaskBudgetMetaData.column_BUDGETED_TASK_COUNT, aNodeFragWorkTaskBudget.getBudgetedTaskCount());
		theContentValues.put(NodeFragWorkTaskBudgetMetaData.column_BUDGETED_AVERAGE_HOURS_PER_TASK, aNodeFragWorkTaskBudget.getBudgetedAverageHoursPerTask());
		theContentValues.put(NodeFragWorkTaskBudgetMetaData.column_BUDGET_BY, aNodeFragWorkTaskBudget.getBudgetByCommunityMemberId());
		GcgDateHelper.setIso8601DateContentValue(theContentValues, NodeFragWorkTaskBudgetMetaData.column_BUDGET_TIMESTAMP, aNodeFragWorkTaskBudget.getBudgetTimestamp());
		theContentValues.put(NodeFragWorkTaskBudgetMetaData.column_BUDGET_DATA_QUALITY, aNodeFragWorkTaskBudget.getBudgetFmmDataQuality().getName());

		theContentValues.put(NodeFragWorkTaskBudgetMetaData.column_WORK_BREAKDOWN_ESTIMATED_TASK_COUNT, aNodeFragWorkTaskBudget.getWorkBreakdownEstimatedTaskCount());
		theContentValues.put(NodeFragWorkTaskBudgetMetaData.column_WORK_BREAKDOWN_ESTIMATED_AVERAGE_HOURS_PER_TASK, aNodeFragWorkTaskBudget.getWorkBreakdownEstimatedAverageHoursPerTask());

		theContentValues.put(NodeFragWorkTaskBudgetMetaData.column_WORK_BREAKDOWN_BUDGETED_TASK_COUNT, aNodeFragWorkTaskBudget.getWorkBreakdownBudgetedTaskCount());
		theContentValues.put(NodeFragWorkTaskBudgetMetaData.column_WORK_BREAKDOWN_BUDGETED_AVERAGE_HOURS_PER_TASK, aNodeFragWorkTaskBudget.getWorkBreakdownBudgetedAverageHoursPerTask());

		theContentValues.put(NodeFragWorkTaskBudgetMetaData.column_COMPLETED_TASK_COUNT, aNodeFragWorkTaskBudget.getCompletedTaskCount());
		theContentValues.put(NodeFragWorkTaskBudgetMetaData.column_COMPLETED_AVERAGE_HOURS_PER_TASK, aNodeFragWorkTaskBudget.getCompletedAverageHoursPerTask());

		theContentValues.put(NodeFragWorkTaskBudgetMetaData.column_SERIALIZED_HISTORY, aNodeFragWorkTaskBudget.getSerialized_history());
		return theContentValues;
	}

}
