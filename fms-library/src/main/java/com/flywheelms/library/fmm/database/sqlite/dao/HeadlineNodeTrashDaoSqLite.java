/* @(#)HeadlineNodeTrashDaoSqLite.java
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
import com.flywheelms.library.fmm.meta_data.HeadlineNodeTrashMetaData;
import com.flywheelms.library.fmm.meta_data.IdNodeMetaData;
import com.flywheelms.library.fmm.meta_data.NodeFragMetaData;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.nodefrag.HeadlineNodeTrash;

import java.util.HashMap;

public class HeadlineNodeTrashDaoSqLite extends NodeFragDaoSqLite<HeadlineNodeTrash> {

	private static HeadlineNodeTrashDaoSqLite singleton;

	public static HeadlineNodeTrashDaoSqLite getInstance() {
		if(HeadlineNodeTrashDaoSqLite.singleton == null) {
			HeadlineNodeTrashDaoSqLite.singleton = new HeadlineNodeTrashDaoSqLite();
		}
		return HeadlineNodeTrashDaoSqLite.singleton;
	}
	
	@Override
	public FmmNodeDefinition getFmmNodeDefinition() {
		return FmmNodeDefinition.HEADLINE_NODE_TRASH;
	}

	@Override
	protected void buildColumnIndexMap(Cursor aCursor) {
		super.buildColumnIndexMap(aCursor);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, HeadlineNodeTrashMetaData.column_SEARCHABLE_HEADLINE);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, HeadlineNodeTrashMetaData.column_DELETED_BY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, HeadlineNodeTrashMetaData.column_DELETED_TIMESTAMP);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, HeadlineNodeTrashMetaData.column_PARENT_ABBREVIATED_NODE_ID);
        putColumnIndexMapEntry(this.columnIndexMap, aCursor, HeadlineNodeTrashMetaData.column_SERIALIZED_HEADLINE_NODE);
        putColumnIndexMapEntry(this.columnIndexMap, aCursor, HeadlineNodeTrashMetaData.column_SERIALIZED_AUDIT_BLOCK);
        putColumnIndexMapEntry(this.columnIndexMap, aCursor, HeadlineNodeTrashMetaData.column_SERIALIZED_COMPLETION);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, HeadlineNodeTrashMetaData.column_SERIALIZED_FSE_DOCUMENT);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, HeadlineNodeTrashMetaData.column_SERIALIZED_GOVERNANCE);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, HeadlineNodeTrashMetaData.column_SERIALIZED_TRIBKN_QUALITY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, HeadlineNodeTrashMetaData.column_SERIALIZED_WORK_TASK_BUDGET);
	}

	@Override
	protected void getColumnValues(HashMap<String, Integer> aHashMap, Cursor aCursor, HeadlineNodeTrash aHeadlineNodeTrash) {
		super.getColumnValues(aHashMap, aCursor, aHeadlineNodeTrash);
		aHeadlineNodeTrash.setSearchableHeadline(aCursor.getString(aHashMap.get(HeadlineNodeTrashMetaData.column_SEARCHABLE_HEADLINE)));
		aHeadlineNodeTrash.setDeletedBy(aCursor.getString(aHashMap.get(HeadlineNodeTrashMetaData.column_DELETED_BY)));
		aHeadlineNodeTrash.setDeletedTimestamp(GcgDateHelper.getDateFromFormattedUtcLong(aCursor.getLong(aHashMap.get(HeadlineNodeTrashMetaData.column_DELETED_TIMESTAMP))));
		aHeadlineNodeTrash.setParentAbbreviatedNodeIdString(aCursor.getString(aHashMap.get(HeadlineNodeTrashMetaData.column_PARENT_ABBREVIATED_NODE_ID)));
        aHeadlineNodeTrash.setSerializedHeadlineNode(aCursor.getString(aHashMap.get(HeadlineNodeTrashMetaData.column_SERIALIZED_HEADLINE_NODE)));
        aHeadlineNodeTrash.setSerializedAuditBlock(aCursor.getString(aHashMap.get(HeadlineNodeTrashMetaData.column_SERIALIZED_AUDIT_BLOCK)));
        aHeadlineNodeTrash.setSerializedCompletion(aCursor.getString(aHashMap.get(HeadlineNodeTrashMetaData.column_SERIALIZED_COMPLETION)));
		aHeadlineNodeTrash.setSerializedFseDocument(aCursor.getString(aHashMap.get(HeadlineNodeTrashMetaData.column_SERIALIZED_FSE_DOCUMENT)));
		aHeadlineNodeTrash.setSerializedGovernance(aCursor.getString(aHashMap.get(HeadlineNodeTrashMetaData.column_SERIALIZED_GOVERNANCE)));
		aHeadlineNodeTrash.setSerializedTribKnQuality(aCursor.getString(aHashMap.get(HeadlineNodeTrashMetaData.column_SERIALIZED_TRIBKN_QUALITY)));
		aHeadlineNodeTrash.setSerializedWorkTaskBudget(aCursor.getString(aHashMap.get(HeadlineNodeTrashMetaData.column_SERIALIZED_WORK_TASK_BUDGET)));
	}

	@Override
	public ContentValues buildContentValues(HeadlineNodeTrash aHeadlineNodeTrash) {
		ContentValues theContentValues = super.buildContentValues(aHeadlineNodeTrash);
		theContentValues.put(HeadlineNodeTrashMetaData.column_SEARCHABLE_HEADLINE, aHeadlineNodeTrash.getSearchableHeadline());
		theContentValues.put(HeadlineNodeTrashMetaData.column_DELETED_BY, aHeadlineNodeTrash.getDeletedByNodeIdString());
		theContentValues.put(HeadlineNodeTrashMetaData.column_DELETED_TIMESTAMP, GcgDateHelper.getFormattedUtcLong(aHeadlineNodeTrash.getDeletedTimestamp()));
		theContentValues.put(HeadlineNodeTrashMetaData.column_PARENT_ABBREVIATED_NODE_ID, aHeadlineNodeTrash.getParentAbbreviatedNodeIdString());
        theContentValues.put(HeadlineNodeTrashMetaData.column_SERIALIZED_HEADLINE_NODE, aHeadlineNodeTrash.getSerializedHeadlineNode());
        theContentValues.put(HeadlineNodeTrashMetaData.column_SERIALIZED_AUDIT_BLOCK, aHeadlineNodeTrash.getSerializedAuditBlock());
        theContentValues.put(HeadlineNodeTrashMetaData.column_SERIALIZED_COMPLETION, aHeadlineNodeTrash.getSerializedCompletion());
		theContentValues.put(HeadlineNodeTrashMetaData.column_SERIALIZED_FSE_DOCUMENT, aHeadlineNodeTrash.getSerializedFseDocument());
		theContentValues.put(HeadlineNodeTrashMetaData.column_SERIALIZED_GOVERNANCE, aHeadlineNodeTrash.getSerializedGovernance());
		theContentValues.put(HeadlineNodeTrashMetaData.column_SERIALIZED_TRIBKN_QUALITY, aHeadlineNodeTrash.getSerializedTribKnQuality());
		theContentValues.put(HeadlineNodeTrashMetaData.column_SERIALIZED_WORK_TASK_BUDGET, aHeadlineNodeTrash.getSerializedWorkTaskBudget());
		return theContentValues;
	}

	@Override
	protected HeadlineNodeTrash getNextObjectFromCursor(Cursor aCursor) {
		HeadlineNodeTrash theHeadlineNodeTrash = new HeadlineNodeTrash(
				aCursor.getString(this.columnIndexMap.get(IdNodeMetaData.column_ID)),
				aCursor.getString(this.columnIndexMap.get(NodeFragMetaData.column_PARENT_ID)) );
		getColumnValues(this.columnIndexMap, aCursor, theHeadlineNodeTrash);
		return theHeadlineNodeTrash;
	}

}
