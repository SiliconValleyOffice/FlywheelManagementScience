/* @(#)NodeFragTaskPointBudgetDaoSqLite.java
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
import com.flywheelms.library.fmm.meta_data.NodeFragTaskPointBudgetMetaData;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragTaskPointBudget;

import java.util.HashMap;

public class NodeFragTaskPointBudgetDaoSqLite extends NodeFragDaoSqLite<NodeFragTaskPointBudget> {

	private static NodeFragTaskPointBudgetDaoSqLite singleton;

	public static NodeFragTaskPointBudgetDaoSqLite getInstance() {
		if(NodeFragTaskPointBudgetDaoSqLite.singleton == null) {
			NodeFragTaskPointBudgetDaoSqLite.singleton = new NodeFragTaskPointBudgetDaoSqLite();
		}
		return NodeFragTaskPointBudgetDaoSqLite.singleton;
	}
	
	@Override
	public FmmNodeDefinition getFmmNodeDefinition() {
		return FmmNodeDefinition.NODE_FRAG__TASK_POINT_BUDGET;
	}

	@Override
	protected void buildColumnIndexMap(Cursor aCursor) {
		super.buildColumnIndexMap(aCursor);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTaskPointBudgetMetaData.column_ESTIMATED_TASK_POINTS);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTaskPointBudgetMetaData.column_ESTIMATED_AVERAGE_HOURS_PER_TASK_POINT);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTaskPointBudgetMetaData.column_ESTIMATE_BY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTaskPointBudgetMetaData.column_ESTIMATE_TIMESTAMP);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTaskPointBudgetMetaData.column_ESTIMATE_DATA_QUALITY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTaskPointBudgetMetaData.column_BUDGETED_TASK_POINTS);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTaskPointBudgetMetaData.column_BUDGETED_AVERAGE_HOURS_PER_TASK_POINT);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTaskPointBudgetMetaData.column_BUDGET_BY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTaskPointBudgetMetaData.column_BUDGET_TIMESTAMP);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTaskPointBudgetMetaData.column_BUDGET_DATA_QUALITY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTaskPointBudgetMetaData.column_WORK_BREAKDOWN_ESTIMATED_TASK_POINTS);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTaskPointBudgetMetaData.column_WORK_BREAKDOWN_ESTIMATED_AVERAGE_HOURS_PER_TASK_POINT);
        putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTaskPointBudgetMetaData.column_WORK_BREAKDOWN_BUDGETED_TASK_POINTS);
        putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTaskPointBudgetMetaData.column_WORK_BREAKDOWN_BUDGETED_AVERAGE_HOURS_PER_TASK_POINT);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTaskPointBudgetMetaData.column_COMPLETED_TASK_POINTS);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTaskPointBudgetMetaData.column_COMPLETED_AVERAGE_HOURS_PER_TASK_POINT);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragTaskPointBudgetMetaData.column_SERIALIZED_HISTORY);
	}

	@Override
	protected void getColumnValues(HashMap<String, Integer> aHashMap, Cursor aCursor, NodeFragTaskPointBudget aNodeFragTaskPointBudget) {
		// we don't call super because the node is constructed with the NodeId
		aNodeFragTaskPointBudget.setEstimatedTaskPoints(aCursor.getInt(aHashMap.get(NodeFragTaskPointBudgetMetaData.column_ESTIMATED_TASK_POINTS)));
		aNodeFragTaskPointBudget.setEstimatedAverageHoursPerTaskPoint(aCursor.getFloat(aHashMap.get(NodeFragTaskPointBudgetMetaData.column_ESTIMATED_AVERAGE_HOURS_PER_TASK_POINT)));
		aNodeFragTaskPointBudget.setEstimateByCommunityMemberId(aCursor.getString(aHashMap.get(NodeFragTaskPointBudgetMetaData.column_ESTIMATE_BY)));
		aNodeFragTaskPointBudget.setEstimateTimestamp(GcgDateHelper.getDateFromIso8601DateString(aCursor.getString(aHashMap.get(NodeFragTaskPointBudgetMetaData.column_ESTIMATE_TIMESTAMP))));
		aNodeFragTaskPointBudget.setEstimateFmmDataQuality(aCursor.getString(aHashMap.get(NodeFragTaskPointBudgetMetaData.column_ESTIMATE_DATA_QUALITY)));
		aNodeFragTaskPointBudget.setBudgetedTaskPoints(aCursor.getInt(aHashMap.get(NodeFragTaskPointBudgetMetaData.column_BUDGETED_TASK_POINTS)));
		aNodeFragTaskPointBudget.setBudgetedAverageHoursPerTaskPoint(aCursor.getFloat(aHashMap.get(NodeFragTaskPointBudgetMetaData.column_BUDGETED_AVERAGE_HOURS_PER_TASK_POINT)));
		aNodeFragTaskPointBudget.setBudgetByCommunityMemberId(aCursor.getString(aHashMap.get(NodeFragTaskPointBudgetMetaData.column_BUDGET_BY)));
		aNodeFragTaskPointBudget.setBudgetTimestamp(GcgDateHelper.getDateFromIso8601DateString(aCursor.getString(aHashMap.get(NodeFragTaskPointBudgetMetaData.column_BUDGET_TIMESTAMP))));
		aNodeFragTaskPointBudget.setBudgetFmmDataQuality(aCursor.getString(aHashMap.get(NodeFragTaskPointBudgetMetaData.column_BUDGET_DATA_QUALITY)));
		aNodeFragTaskPointBudget.setWorkBreakdownEstimatedTaskPoints(aCursor.getInt(aHashMap.get(NodeFragTaskPointBudgetMetaData.column_WORK_BREAKDOWN_ESTIMATED_TASK_POINTS)));
		aNodeFragTaskPointBudget.setWorkBreakdownEstimatedAverageHoursPerTaskPoint(aCursor.getFloat(aHashMap.get(NodeFragTaskPointBudgetMetaData.column_WORK_BREAKDOWN_ESTIMATED_AVERAGE_HOURS_PER_TASK_POINT)));
		aNodeFragTaskPointBudget.setWorkBreakdownBudgetedTaskPoints(aCursor.getInt(aHashMap.get(NodeFragTaskPointBudgetMetaData.column_WORK_BREAKDOWN_BUDGETED_TASK_POINTS)));
		aNodeFragTaskPointBudget.setWorkBreakdownBudgetedAverageHoursPerTaskPoint(aCursor.getFloat(aHashMap.get(NodeFragTaskPointBudgetMetaData.column_WORK_BREAKDOWN_BUDGETED_AVERAGE_HOURS_PER_TASK_POINT)));
		aNodeFragTaskPointBudget.setCompletedTaskPoints(aCursor.getInt(aHashMap.get(NodeFragTaskPointBudgetMetaData.column_COMPLETED_TASK_POINTS)));
		aNodeFragTaskPointBudget.setCompletedAverageHoursPerTaskPoint(aCursor.getFloat(aHashMap.get(NodeFragTaskPointBudgetMetaData.column_COMPLETED_AVERAGE_HOURS_PER_TASK_POINT)));
		aNodeFragTaskPointBudget.setSerialized_history(aCursor.getString(aHashMap.get(NodeFragTaskPointBudgetMetaData.column_SERIALIZED_HISTORY)));
	}

	@Override
	protected NodeFragTaskPointBudget getNextObjectFromCursor(Cursor aCursor) {
		NodeFragTaskPointBudget theNodeFragTaskPointBudget;
		theNodeFragTaskPointBudget = new NodeFragTaskPointBudget(
				aCursor.getString(this.columnIndexMap.get(IdNodeMetaData.column_ID)),
				aCursor.getString(this.columnIndexMap.get(NodeFragMetaData.column_PARENT_ID)) );
		getColumnValues(this.columnIndexMap, aCursor, theNodeFragTaskPointBudget);
		return theNodeFragTaskPointBudget;
	}

	@Override
	public ContentValues buildContentValues(NodeFragTaskPointBudget aNodeFragTaskPointBudget) {
		ContentValues theContentValues = super.buildContentValues(aNodeFragTaskPointBudget);
		theContentValues.put(NodeFragTaskPointBudgetMetaData.column_ESTIMATED_TASK_POINTS, aNodeFragTaskPointBudget.getEstimatedTaskPoints());
		theContentValues.put(NodeFragTaskPointBudgetMetaData.column_ESTIMATED_AVERAGE_HOURS_PER_TASK_POINT, aNodeFragTaskPointBudget.getEstimatedAverageHoursPerTaskPoint());
		theContentValues.put(NodeFragTaskPointBudgetMetaData.column_ESTIMATE_BY, aNodeFragTaskPointBudget.getEstimateByCommunityMemberId());
		GcgDateHelper.setIso8601DateContentValue(theContentValues, NodeFragTaskPointBudgetMetaData.column_ESTIMATE_TIMESTAMP, aNodeFragTaskPointBudget.getEstimateTimestamp());
		theContentValues.put(NodeFragTaskPointBudgetMetaData.column_ESTIMATE_DATA_QUALITY, aNodeFragTaskPointBudget.getEstimateFmmDataQuality().getName());

		theContentValues.put(NodeFragTaskPointBudgetMetaData.column_BUDGETED_TASK_POINTS, aNodeFragTaskPointBudget.getBudgetedTaskPoints());
		theContentValues.put(NodeFragTaskPointBudgetMetaData.column_BUDGETED_AVERAGE_HOURS_PER_TASK_POINT, aNodeFragTaskPointBudget.getBudgetedAverageHoursPerTaskPoint());
		theContentValues.put(NodeFragTaskPointBudgetMetaData.column_BUDGET_BY, aNodeFragTaskPointBudget.getBudgetByCommunityMemberId());
		GcgDateHelper.setIso8601DateContentValue(theContentValues, NodeFragTaskPointBudgetMetaData.column_BUDGET_TIMESTAMP, aNodeFragTaskPointBudget.getBudgetTimestamp());
		theContentValues.put(NodeFragTaskPointBudgetMetaData.column_BUDGET_DATA_QUALITY, aNodeFragTaskPointBudget.getBudgetFmmDataQuality().getName());

		theContentValues.put(NodeFragTaskPointBudgetMetaData.column_WORK_BREAKDOWN_ESTIMATED_TASK_POINTS, aNodeFragTaskPointBudget.getWorkBreakdownEstimatedTaskPoints());
		theContentValues.put(NodeFragTaskPointBudgetMetaData.column_WORK_BREAKDOWN_ESTIMATED_AVERAGE_HOURS_PER_TASK_POINT, aNodeFragTaskPointBudget.getWorkBreakdownEstimatedAverageHoursPerTaskPoint());

		theContentValues.put(NodeFragTaskPointBudgetMetaData.column_WORK_BREAKDOWN_BUDGETED_TASK_POINTS, aNodeFragTaskPointBudget.getWorkBreakdownBudgetedTaskPoints());
		theContentValues.put(NodeFragTaskPointBudgetMetaData.column_WORK_BREAKDOWN_BUDGETED_AVERAGE_HOURS_PER_TASK_POINT, aNodeFragTaskPointBudget.getWorkBreakdownBudgetedAverageHoursPerTaskPoint());

		theContentValues.put(NodeFragTaskPointBudgetMetaData.column_COMPLETED_TASK_POINTS, aNodeFragTaskPointBudget.getCompletedTaskPoints());
		theContentValues.put(NodeFragTaskPointBudgetMetaData.column_COMPLETED_AVERAGE_HOURS_PER_TASK_POINT, aNodeFragTaskPointBudget.getCompletedAverageHoursPerTaskPoint());

		theContentValues.put(NodeFragTaskPointBudgetMetaData.column_SERIALIZED_HISTORY, aNodeFragTaskPointBudget.getSerialized_history());
		return theContentValues;
	}

}
