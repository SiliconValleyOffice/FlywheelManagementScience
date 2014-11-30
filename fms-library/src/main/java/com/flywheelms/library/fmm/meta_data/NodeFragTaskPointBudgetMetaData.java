/* @(#)NodeFragTaskPointBudgetMetaData.java
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

package com.flywheelms.library.fmm.meta_data;

public class NodeFragTaskPointBudgetMetaData extends NodeFragMetaData {

	public static final String key__NODE_FRAG_WORK_TASK_BUDGET = "NodeFragTaskPointBudget";
	
	public static final String column_ESTIMATED_TASK_POINTS = "estimated_task_points";
	public static final String column_ESTIMATED_AVERAGE_HOURS_PER_TASK_POINT = "estimated_average_hours_per_task_point";
	public static final String column_ESTIMATE_BY = "estimate_by";
	public static final String column_ESTIMATE_TIMESTAMP = "estimate_timestamp";
	public static final String column_ESTIMATE_DATA_QUALITY = "estimate_data_quality";

	public static final String column_BUDGETED_TASK_POINTS = "budgeted_task_points";
	public static final String column_BUDGETED_AVERAGE_HOURS_PER_TASK_POINT = "budgeted_average_hours_per_task_point";
	public static final String column_BUDGET_BY = "budget_by";
	public static final String column_BUDGET_TIMESTAMP = "budget_timestamp";
	public static final String column_BUDGET_DATA_QUALITY = "budget_data_quality";

	public static final String column_WORK_BREAKDOWN_ESTIMATED_TASK_POINTS = "work_breakdown_estimated_task_points";
	public static final String column_WORK_BREAKDOWN_ESTIMATED_AVERAGE_HOURS_PER_TASK_POINT = "work_breakdown_estimated_average_hours_per_task_point";

    public static final String column_WORK_BREAKDOWN_BUDGETED_TASK_POINTS = "work_breakdown_budgeted_task_points";
    public static final String column_WORK_BREAKDOWN_BUDGETED_AVERAGE_HOURS_PER_TASK_POINT = "work_breakdown_budgeted_average_hours_per_task_point";

	public static final String column_COMPLETED_TASK_POINTS = "completed_task_points";
	public static final String column_COMPLETED_AVERAGE_HOURS_PER_TASK_POINT = "completed_average_hours_per_task_point";

	public static final String column_SERIALIZED_HISTORY = "serialized_history";
	
	public static void init() { return; }

}
