/* @(#)WorkTaskDaoSqLite.java
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

import com.flywheelms.library.fmm.meta_data.CompletableNodeMetaData;
import com.flywheelms.library.fmm.meta_data.IdNodeMetaData;
import com.flywheelms.library.fmm.meta_data.WorkTaskMetaData;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.governable.WorkTask;

public class WorkTaskDaoSqLite  extends CompletableNodeDaoSqLite<WorkTask> {

	private static WorkTaskDaoSqLite singleton;

	public static WorkTaskDaoSqLite getInstance() {
		if(WorkTaskDaoSqLite.singleton == null) {
			WorkTaskDaoSqLite.singleton = new WorkTaskDaoSqLite();
		}
		return WorkTaskDaoSqLite.singleton;
	}
	
	@Override
	public FmmNodeDefinition getFmmNodeDefinition() {
		return FmmNodeDefinition.WORK_TASK;
	}

	@Override
	protected void buildColumnIndexMap(Cursor aCursor) {
		/*
		 * 
		 
	public static final String column_WORK_PLAN__ID = "WorkPlan__id";
	public static final String column_WORK_PLAN_SEQUENCE = "work_plan_sequence";
	public static final String column_BUDGETED_PERSON_HOURS = "budgeted_person_hours";
	public static final String column_ACTUAL_PERSON_HOURS = "actual_person_hours";
		 */
		super.buildColumnIndexMap(aCursor);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, WorkTaskMetaData.column_WORK_PACKAGE__ID);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CompletableNodeMetaData.column_SEQUENCE);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, WorkTaskMetaData.column_WORK_PLAN__ID);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, WorkTaskMetaData.column_WORK_PLAN_SEQUENCE);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, WorkTaskMetaData.column_BUDGETED_PERSON_HOURS);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, WorkTaskMetaData.column_ACTUAL_PERSON_HOURS);
	}

	@Override
	protected void getColumnValues(HashMap<String, Integer> aHashMap, Cursor aCursor, WorkTask aWorkTask) {
		super.getColumnValues(aHashMap, aCursor, aWorkTask);
		aWorkTask.setWorkPackageNodeIdString(aCursor.getString(aHashMap.get(WorkTaskMetaData.column_WORK_PACKAGE__ID)));
		aWorkTask.setSequence(aCursor.getInt(aHashMap.get(CompletableNodeMetaData.column_SEQUENCE)));
		aWorkTask.setWorkPlanNodeIdString(aCursor.getString(aHashMap.get(WorkTaskMetaData.column_WORK_PLAN__ID)));
		aWorkTask.setWorkPlanSequence(aCursor.getInt(aHashMap.get(WorkTaskMetaData.column_WORK_PLAN_SEQUENCE)));
		aWorkTask.setBudgetedPersonHours(aCursor.getInt(aHashMap.get(WorkTaskMetaData.column_BUDGETED_PERSON_HOURS)));
		aWorkTask.setActualPersonHours(aCursor.getInt(aHashMap.get(WorkTaskMetaData.column_ACTUAL_PERSON_HOURS)));
	}

	@Override
	public ContentValues buildContentValues(WorkTask aWorkTask) {
		ContentValues theContentValues = super.buildContentValues(aWorkTask);
		theContentValues.put(WorkTaskMetaData.column_WORK_PACKAGE__ID, aWorkTask.getWorkPlanNodeIdString());
		theContentValues.put(CompletableNodeMetaData.column_SEQUENCE, aWorkTask.getSequence());
		theContentValues.put(WorkTaskMetaData.column_WORK_PLAN__ID, aWorkTask.getWorkPlanNodeIdString());
		theContentValues.put(WorkTaskMetaData.column_WORK_PLAN_SEQUENCE, aWorkTask.getWorkPlanSequence());
		theContentValues.put(WorkTaskMetaData.column_BUDGETED_PERSON_HOURS, aWorkTask.getBudgetedPersonHours());
		theContentValues.put(WorkTaskMetaData.column_ACTUAL_PERSON_HOURS, aWorkTask.getActualPersonHours());
		return theContentValues;
	}

	@Override
	public ContentValues buildUpdateContentValues(WorkTask aWorkTask) {
		return buildContentValues(aWorkTask);
	}

	@Override
	protected WorkTask getNextObjectFromCursor(Cursor aCursor) {
		WorkTask theWorkTask = null;
		theWorkTask = new WorkTask(
				aCursor.getString(this.columnIndexMap.get(IdNodeMetaData.column_ID)) );
		getColumnValues(this.columnIndexMap, aCursor, theWorkTask);
		return theWorkTask;
	}

}
