/* @(#)CompletionNodeTrashDaoSqLite.java
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

import com.flywheelms.library.fmm.meta_data.CompletionNodeTrashMetaData;
import com.flywheelms.library.fmm.meta_data.IdNodeMetaData;
import com.flywheelms.library.fmm.meta_data.NodeFragMetaData;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.nodefrag.CompletionNodeTrash;
import com.flywheelms.library.gcg.widget.date.GcgDateHelper;

import java.util.HashMap;

public class CompletionNodeTrashDaoSqLite extends NodeFragDaoSqLite<CompletionNodeTrash> {

	private static CompletionNodeTrashDaoSqLite singleton;

	public static CompletionNodeTrashDaoSqLite getInstance() {
		if(CompletionNodeTrashDaoSqLite.singleton == null) {
			CompletionNodeTrashDaoSqLite.singleton = new CompletionNodeTrashDaoSqLite();
		}
		return CompletionNodeTrashDaoSqLite.singleton;
	}
	
	@Override
	public FmmNodeDefinition getFmmNodeDefinition() {
		return FmmNodeDefinition.COMPLETION_NODE_TRASH;
	}

	@Override
	protected void buildColumnIndexMap(Cursor aCursor) {
		super.buildColumnIndexMap(aCursor);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CompletionNodeTrashMetaData.column_SEARCHABLE_HEADLINE);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CompletionNodeTrashMetaData.column_DELETED_BY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CompletionNodeTrashMetaData.column_DELETED_TIMESTAMP);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CompletionNodeTrashMetaData.column_PARENT_ABBREVIATED_NODE_ID);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CompletionNodeTrashMetaData.column_SERIALIZED_AUDIT_BLOCK);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CompletionNodeTrashMetaData.column_SERIALIZED_COMPLETION);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CompletionNodeTrashMetaData.column_SERIALIZED_COMPLETION_NODE);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CompletionNodeTrashMetaData.column_SERIALIZED_FSE_DOCUMENT);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CompletionNodeTrashMetaData.column_SERIALIZED_GOVERNANCE);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CompletionNodeTrashMetaData.column_SERIALIZED_TRIBKN_QUALITY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CompletionNodeTrashMetaData.column_SERIALIZED_WORK_TASK_BUDGET);
	}

	@Override
	protected void getColumnValues(HashMap<String, Integer> aHashMap, Cursor aCursor, CompletionNodeTrash aCompletionNodeTrash) {
		super.getColumnValues(aHashMap, aCursor, aCompletionNodeTrash);
		aCompletionNodeTrash.setSearchableHeadline(aCursor.getString(aHashMap.get(CompletionNodeTrashMetaData.column_SEARCHABLE_HEADLINE)));
		aCompletionNodeTrash.setDeletedBy(aCursor.getString(aHashMap.get(CompletionNodeTrashMetaData.column_DELETED_BY)));
		aCompletionNodeTrash.setDeletedTimestamp(GcgDateHelper.getDateFromFormattedUtcLong(aCursor.getLong(aHashMap.get(CompletionNodeTrashMetaData.column_DELETED_TIMESTAMP))));
		aCompletionNodeTrash.setParentAbbreviatedNodeIdString(aCursor.getString(aHashMap.get(CompletionNodeTrashMetaData.column_PARENT_ABBREVIATED_NODE_ID)));
		aCompletionNodeTrash.setSerializedAuditBlock(aCursor.getString(aHashMap.get(CompletionNodeTrashMetaData.column_SERIALIZED_AUDIT_BLOCK)));
		aCompletionNodeTrash.setSerializedCompletion(aCursor.getString(aHashMap.get(CompletionNodeTrashMetaData.column_SERIALIZED_COMPLETION)));
		aCompletionNodeTrash.setSerializedCompletionNode(aCursor.getString(aHashMap.get(CompletionNodeTrashMetaData.column_SERIALIZED_COMPLETION_NODE)));
		aCompletionNodeTrash.setSerializedFseDocument(aCursor.getString(aHashMap.get(CompletionNodeTrashMetaData.column_SERIALIZED_FSE_DOCUMENT)));
		aCompletionNodeTrash.setSerializedGovernance(aCursor.getString(aHashMap.get(CompletionNodeTrashMetaData.column_SERIALIZED_GOVERNANCE)));
		aCompletionNodeTrash.setSerializedTribKnQuality(aCursor.getString(aHashMap.get(CompletionNodeTrashMetaData.column_SERIALIZED_TRIBKN_QUALITY)));
		aCompletionNodeTrash.setSerializedWorkTaskBudget(aCursor.getString(aHashMap.get(CompletionNodeTrashMetaData.column_SERIALIZED_WORK_TASK_BUDGET)));
	}

	@Override
	public ContentValues buildContentValues(CompletionNodeTrash aCompletionNodeTrash) {
		ContentValues theContentValues = super.buildContentValues(aCompletionNodeTrash);
		theContentValues.put(CompletionNodeTrashMetaData.column_SEARCHABLE_HEADLINE, aCompletionNodeTrash.getSearchableHeadline());
		theContentValues.put(CompletionNodeTrashMetaData.column_DELETED_BY, aCompletionNodeTrash.getDeletedByNodeIdString());
		theContentValues.put(CompletionNodeTrashMetaData.column_DELETED_TIMESTAMP, GcgDateHelper.getFormattedUtcLong(aCompletionNodeTrash.getDeletedTimestamp()));
		theContentValues.put(CompletionNodeTrashMetaData.column_PARENT_ABBREVIATED_NODE_ID, aCompletionNodeTrash.getParentAbbreviatedNodeIdString());
		theContentValues.put(CompletionNodeTrashMetaData.column_SERIALIZED_AUDIT_BLOCK, aCompletionNodeTrash.getSerializedAuditBlock());
		theContentValues.put(CompletionNodeTrashMetaData.column_SERIALIZED_COMPLETION, aCompletionNodeTrash.getSerializedCompletion());
		theContentValues.put(CompletionNodeTrashMetaData.column_SERIALIZED_COMPLETION_NODE, aCompletionNodeTrash.getSerializedCompletionNode());
		theContentValues.put(CompletionNodeTrashMetaData.column_SERIALIZED_FSE_DOCUMENT, aCompletionNodeTrash.getSerializedFseDocument());
		theContentValues.put(CompletionNodeTrashMetaData.column_SERIALIZED_GOVERNANCE, aCompletionNodeTrash.getSerializedGovernance());
		theContentValues.put(CompletionNodeTrashMetaData.column_SERIALIZED_TRIBKN_QUALITY, aCompletionNodeTrash.getSerializedTribKnQuality());
		theContentValues.put(CompletionNodeTrashMetaData.column_SERIALIZED_WORK_TASK_BUDGET, aCompletionNodeTrash.getSerializedWorkTaskBudget());
		return theContentValues;
	}

	@Override
	protected CompletionNodeTrash getNextObjectFromCursor(Cursor aCursor) {
		CompletionNodeTrash theCompletionNodeTrash = new CompletionNodeTrash(
				aCursor.getString(this.columnIndexMap.get(IdNodeMetaData.column_ID)),
				aCursor.getString(this.columnIndexMap.get(NodeFragMetaData.column_PARENT_ID)) );
		getColumnValues(this.columnIndexMap, aCursor, theCompletionNodeTrash);
		return theCompletionNodeTrash;
	}

}
