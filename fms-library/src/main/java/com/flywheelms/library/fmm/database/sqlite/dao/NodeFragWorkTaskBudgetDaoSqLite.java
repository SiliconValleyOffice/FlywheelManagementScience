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

import com.flywheelms.library.fmm.meta_data.IdNodeMetaData;
import com.flywheelms.library.fmm.meta_data.NodeFragMetaData;
import com.flywheelms.library.fmm.meta_data.NodeFragWorkTaskBudgetMetaData;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragWorkTaskBudget;
import com.flywheelms.library.gcg.widget.date.GcgDateHelper;

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
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragWorkTaskBudgetMetaData.column_ESTIMATED_TOTAL_TASK_COUNT);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragWorkTaskBudgetMetaData.column_ESTIMATED_AVERAGE_HOURS_PER_TASK);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragWorkTaskBudgetMetaData.column_SWAG_BY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragWorkTaskBudgetMetaData.column_SWAG_TIMESTAMP);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragWorkTaskBudgetMetaData.column_ESTIMATE_CONFIRMED_BY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragWorkTaskBudgetMetaData.column_ESTIMATE_CONFIRMED_TIMESTAMP);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragWorkTaskBudgetMetaData.column_BUDGETED_TOTAL_TASK_COUNT);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragWorkTaskBudgetMetaData.column_BUDGETED_AVERAGE_HOURS_PER_TASK);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragWorkTaskBudgetMetaData.column_BUDGET_PROPOSED_BY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragWorkTaskBudgetMetaData.column_BUDGET_PROPOSED_TIMESTAMP);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragWorkTaskBudgetMetaData.column_BUDGET_CONFIRMED_BY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragWorkTaskBudgetMetaData.column_BUDGET_CONFIRMED_TIMESTAMP);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragWorkTaskBudgetMetaData.column_WORK_BREAKDOWN_TOTAL_TASK_COUNT);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragWorkTaskBudgetMetaData.column_WORK_BREAKDOWN_AVERAGE_HOURS_PER_TASK);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragWorkTaskBudgetMetaData.column_TASK_COUNT_AFTER_COMPLETION);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragWorkTaskBudgetMetaData.column_AVERAGE_HOURS_PER_TASK_AFTER_COMPLETION);
	}

	@Override
	protected void getColumnValues(HashMap<String, Integer> aHashMap, Cursor aCursor, NodeFragWorkTaskBudget aNodeFragWorkTaskBudget) {
		// we don't call super because the node is constructed with the NodeId
		aNodeFragWorkTaskBudget.setEstimatedTotalTaskCount(aCursor.getInt(aHashMap.get(NodeFragWorkTaskBudgetMetaData.column_ESTIMATED_TOTAL_TASK_COUNT)));
		aNodeFragWorkTaskBudget.setEstimatedAverageHoursPerTask(aCursor.getFloat(aHashMap.get(NodeFragWorkTaskBudgetMetaData.column_ESTIMATED_AVERAGE_HOURS_PER_TASK)));
		aNodeFragWorkTaskBudget.setSwagBy(aCursor.getString(aHashMap.get(NodeFragWorkTaskBudgetMetaData.column_SWAG_BY)));
		aNodeFragWorkTaskBudget.setSwagTimestamp(GcgDateHelper.getDateFromIso8601DateString(aCursor.getString(aHashMap.get(NodeFragWorkTaskBudgetMetaData.column_SWAG_TIMESTAMP))));
		aNodeFragWorkTaskBudget.setEstimateConfirmedBy(aCursor.getString(aHashMap.get(NodeFragWorkTaskBudgetMetaData.column_ESTIMATE_CONFIRMED_BY)));
		aNodeFragWorkTaskBudget.setEstimateConfirmedTimestamp(GcgDateHelper.getDateFromIso8601DateString(aCursor.getString(aHashMap.get(NodeFragWorkTaskBudgetMetaData.column_ESTIMATE_CONFIRMED_TIMESTAMP))));
		aNodeFragWorkTaskBudget.setBudgetedTotalTaskCount(aCursor.getInt(aHashMap.get(NodeFragWorkTaskBudgetMetaData.column_BUDGETED_TOTAL_TASK_COUNT)));
		aNodeFragWorkTaskBudget.setBudgetedAverageHoursPerTask(aCursor.getFloat(aHashMap.get(NodeFragWorkTaskBudgetMetaData.column_BUDGETED_AVERAGE_HOURS_PER_TASK)));
		aNodeFragWorkTaskBudget.setBudgetProposedBy(aCursor.getString(aHashMap.get(NodeFragWorkTaskBudgetMetaData.column_BUDGET_PROPOSED_BY)));
		aNodeFragWorkTaskBudget.setBudgetProposedTimestamp(GcgDateHelper.getDateFromIso8601DateString(aCursor.getString(aHashMap.get(NodeFragWorkTaskBudgetMetaData.column_BUDGET_PROPOSED_TIMESTAMP))));
		aNodeFragWorkTaskBudget.setBudgetConfirmedBy(aCursor.getString(aHashMap.get(NodeFragWorkTaskBudgetMetaData.column_BUDGET_CONFIRMED_BY)));
		aNodeFragWorkTaskBudget.setBudgetConfirmedTimestamp(GcgDateHelper.getDateFromIso8601DateString(aCursor.getString(aHashMap.get(NodeFragWorkTaskBudgetMetaData.column_BUDGET_CONFIRMED_TIMESTAMP))));
		aNodeFragWorkTaskBudget.setWorkBreakdownTotalTaskCount(aCursor.getInt(aHashMap.get(NodeFragWorkTaskBudgetMetaData.column_WORK_BREAKDOWN_TOTAL_TASK_COUNT)));
		aNodeFragWorkTaskBudget.setWorkBreakdownAverageHoursPerTask(aCursor.getFloat(aHashMap.get(NodeFragWorkTaskBudgetMetaData.column_WORK_BREAKDOWN_AVERAGE_HOURS_PER_TASK)));
		aNodeFragWorkTaskBudget.setTaskCountAfterCompletion(aCursor.getInt(aHashMap.get(NodeFragWorkTaskBudgetMetaData.column_TASK_COUNT_AFTER_COMPLETION)));
		aNodeFragWorkTaskBudget.setAverageHoursPerTaskAfterCompletion(aCursor.getFloat(aHashMap.get(NodeFragWorkTaskBudgetMetaData.column_AVERAGE_HOURS_PER_TASK_AFTER_COMPLETION)));
	}

	@Override
	protected NodeFragWorkTaskBudget getNextObjectFromCursor(Cursor aCursor) {
		NodeFragWorkTaskBudget theNodeFragWorkTaskBudget = null;
		theNodeFragWorkTaskBudget = new NodeFragWorkTaskBudget(
				aCursor.getString(this.columnIndexMap.get(IdNodeMetaData.column_ID)),
				aCursor.getString(this.columnIndexMap.get(NodeFragMetaData.column_PARENT_ID)) );
		getColumnValues(this.columnIndexMap, aCursor, theNodeFragWorkTaskBudget);
		return theNodeFragWorkTaskBudget;
	}

	@Override
	public ContentValues buildContentValues(NodeFragWorkTaskBudget aNodeFragWorkTaskBudget) {
		ContentValues theContentValues = super.buildContentValues(aNodeFragWorkTaskBudget);
		theContentValues.put(NodeFragWorkTaskBudgetMetaData.column_ESTIMATED_TOTAL_TASK_COUNT, aNodeFragWorkTaskBudget.getEstimatedTotalTaskCount());
		theContentValues.put(NodeFragWorkTaskBudgetMetaData.column_ESTIMATED_AVERAGE_HOURS_PER_TASK, aNodeFragWorkTaskBudget.getEstimatedAverageHoursPerTask());
		theContentValues.put(NodeFragWorkTaskBudgetMetaData.column_SWAG_BY, aNodeFragWorkTaskBudget.getSwagByNodeIdString());
		GcgDateHelper.setIso8601DateContentValue(theContentValues, NodeFragWorkTaskBudgetMetaData.column_SWAG_TIMESTAMP, aNodeFragWorkTaskBudget.getSwagTimestamp());
		theContentValues.put(NodeFragWorkTaskBudgetMetaData.column_ESTIMATE_CONFIRMED_BY, aNodeFragWorkTaskBudget.getEstimateConfirmedByNodeIdString());
		GcgDateHelper.setIso8601DateContentValue(theContentValues, NodeFragWorkTaskBudgetMetaData.column_ESTIMATE_CONFIRMED_TIMESTAMP, aNodeFragWorkTaskBudget.getEstimateConfirmedTimestamp());
		theContentValues.put(NodeFragWorkTaskBudgetMetaData.column_BUDGETED_TOTAL_TASK_COUNT, aNodeFragWorkTaskBudget.getBudgetedTotalTaskCount());
		theContentValues.put(NodeFragWorkTaskBudgetMetaData.column_BUDGETED_AVERAGE_HOURS_PER_TASK, aNodeFragWorkTaskBudget.getBudgetedAverageHoursPerTask());
		theContentValues.put(NodeFragWorkTaskBudgetMetaData.column_BUDGET_PROPOSED_BY, aNodeFragWorkTaskBudget.getBudgetProposedByNodeIdString());
		GcgDateHelper.setIso8601DateContentValue(theContentValues, NodeFragWorkTaskBudgetMetaData.column_BUDGET_PROPOSED_TIMESTAMP, aNodeFragWorkTaskBudget.getBudgetProposedTimestamp());
		theContentValues.put(NodeFragWorkTaskBudgetMetaData.column_BUDGET_CONFIRMED_BY, aNodeFragWorkTaskBudget.getBudgetConfirmedByNodeIdString());
		GcgDateHelper.setIso8601DateContentValue(theContentValues, NodeFragWorkTaskBudgetMetaData.column_BUDGET_CONFIRMED_TIMESTAMP, aNodeFragWorkTaskBudget.getBudgetConfirmedTimestamp());
		theContentValues.put(NodeFragWorkTaskBudgetMetaData.column_WORK_BREAKDOWN_TOTAL_TASK_COUNT, aNodeFragWorkTaskBudget.getWorkBreakdownTotalTaskCount());
		theContentValues.put(NodeFragWorkTaskBudgetMetaData.column_WORK_BREAKDOWN_AVERAGE_HOURS_PER_TASK, aNodeFragWorkTaskBudget.getWorkBreakdownAverageHoursPerTask());
		theContentValues.put(NodeFragWorkTaskBudgetMetaData.column_TASK_COUNT_AFTER_COMPLETION, aNodeFragWorkTaskBudget.getTaskCountAfterCompletion());
		theContentValues.put(NodeFragWorkTaskBudgetMetaData.column_AVERAGE_HOURS_PER_TASK_AFTER_COMPLETION, aNodeFragWorkTaskBudget.getAverageHoursPerTaskAfterCompletion());
		return theContentValues;
	}

}
