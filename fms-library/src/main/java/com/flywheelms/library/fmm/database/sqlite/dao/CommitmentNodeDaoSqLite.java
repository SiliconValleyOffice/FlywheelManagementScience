/* @(#)CommitmentNodeDaoSqLite.java
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
import com.flywheelms.library.fmm.meta_data.CommitmentNodeMetaData;
import com.flywheelms.library.fmm.meta_data.SequencedLinkNodeMetaData;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmCommitmentNode;

import java.util.HashMap;

public abstract class CommitmentNodeDaoSqLite<T extends FmmCommitmentNode> extends IdNodeDaoSqLite<T> {

	protected abstract String getParentIdColumnName();

	protected abstract String getChildIdColumnName();

	@Override
	protected void buildColumnIndexMap(Cursor aCursor) {
		super.buildColumnIndexMap(aCursor);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, getParentIdColumnName());
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, getChildIdColumnName());
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, SequencedLinkNodeMetaData.column_SEQUENCE);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CommitmentNodeMetaData.column_COMPLETION_COMMITMENT_TYPE_CODE);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CommitmentNodeMetaData.column_SUGGESTED_BY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CommitmentNodeMetaData.column_SUGGESTED_TIMESTAMP);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CommitmentNodeMetaData.column_PROPOSED_BY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CommitmentNodeMetaData.column_PROPOSED_TIMESTAMP);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CommitmentNodeMetaData.column_CONFIRMED_BY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CommitmentNodeMetaData.column_CONFIRMED_TIMESTAMP);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CommitmentNodeMetaData.column_DECLINED_BY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CommitmentNodeMetaData.column_DECLINED_TIMESTAMP);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CommitmentNodeMetaData.column_CANCELED_BY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CommitmentNodeMetaData.column_CANCELED_TIMESTAMP);
	}
	
	@Override
	protected void getColumnValues(HashMap<String, Integer> aHashMap, Cursor aCursor, T aCommitmentNode) {
		super.getColumnValues(aHashMap, aCursor, aCommitmentNode);
		aCommitmentNode.setSequence(aCursor.getInt(aHashMap.get(SequencedLinkNodeMetaData.column_SEQUENCE)));
		aCommitmentNode.setCompletionCommitmentType(aCursor.getString(aHashMap.get(CommitmentNodeMetaData.column_COMPLETION_COMMITMENT_TYPE_CODE)));
		aCommitmentNode.setSuggestedBy(aCursor.getString(aHashMap.get(CommitmentNodeMetaData.column_SUGGESTED_BY)));
		aCommitmentNode.setSuggestedTimestamp(GcgDateHelper.getDateFromFormattedUtcLong(aCursor.getLong(aHashMap.get(CommitmentNodeMetaData.column_SUGGESTED_TIMESTAMP))));
		aCommitmentNode.setProposedBy(aCursor.getString(aHashMap.get(CommitmentNodeMetaData.column_PROPOSED_BY)));
		aCommitmentNode.setProposedTimestamp(GcgDateHelper.getDateFromFormattedUtcLong(aCursor.getLong(aHashMap.get(CommitmentNodeMetaData.column_PROPOSED_TIMESTAMP))));
		aCommitmentNode.setConfirmedBy(aCursor.getString(aHashMap.get(CommitmentNodeMetaData.column_CONFIRMED_BY)));
		aCommitmentNode.setConfirmedTimestamp(GcgDateHelper.getDateFromFormattedUtcLong(aCursor.getLong(aHashMap.get(CommitmentNodeMetaData.column_CONFIRMED_TIMESTAMP))));
		aCommitmentNode.setDeclinedBy(aCursor.getString(aHashMap.get(CommitmentNodeMetaData.column_DECLINED_BY)));
		aCommitmentNode.setDeclinedTimestamp(GcgDateHelper.getDateFromFormattedUtcLong(aCursor.getLong(aHashMap.get(CommitmentNodeMetaData.column_DECLINED_TIMESTAMP))));
		aCommitmentNode.setCanceledBy(aCursor.getString(aHashMap.get(CommitmentNodeMetaData.column_CANCELED_BY)));
		aCommitmentNode.setCanceledTimestamp(GcgDateHelper.getDateFromFormattedUtcLong(aCursor.getLong(aHashMap.get(CommitmentNodeMetaData.column_CANCELED_TIMESTAMP))));
	}

	@Override
	public ContentValues buildContentValues(T aCommitmentNode) {
		ContentValues theContentValues = super.buildContentValues(aCommitmentNode);
		theContentValues.put(getParentIdColumnName(), aCommitmentNode.getParentNodeIdString());
		theContentValues.put(getChildIdColumnName(), aCommitmentNode.getChildNodeIdString());
		theContentValues.put(SequencedLinkNodeMetaData.column_SEQUENCE, aCommitmentNode.getSequence());
		theContentValues.put(CommitmentNodeMetaData.column_COMPLETION_COMMITMENT_TYPE_CODE, aCommitmentNode.getCompletionCommitmentTypeCode());
		theContentValues.put(CommitmentNodeMetaData.column_SUGGESTED_BY, aCommitmentNode.getSuggestedByNodeIdString());
		GcgDateHelper.setIso8601DateContentValue(theContentValues, CommitmentNodeMetaData.column_SUGGESTED_TIMESTAMP, aCommitmentNode.getSuggestedTimestamp());
		theContentValues.put(CommitmentNodeMetaData.column_PROPOSED_BY, aCommitmentNode.getProposedByNodeIdString());
		GcgDateHelper.setIso8601DateContentValue(theContentValues, CommitmentNodeMetaData.column_PROPOSED_TIMESTAMP, aCommitmentNode.getProposedTimestamp());
		theContentValues.put(CommitmentNodeMetaData.column_CONFIRMED_BY, aCommitmentNode.getConfirmedByNodeIdString());
		GcgDateHelper.setIso8601DateContentValue(theContentValues, CommitmentNodeMetaData.column_CONFIRMED_TIMESTAMP, aCommitmentNode.getConfirmedTimestamp());
		theContentValues.put(CommitmentNodeMetaData.column_DECLINED_BY, aCommitmentNode.getDeclinedByNodeIdString());
		GcgDateHelper.setIso8601DateContentValue(theContentValues, CommitmentNodeMetaData.column_DECLINED_TIMESTAMP, aCommitmentNode.getDeclinedTimestamp());
		theContentValues.put(CommitmentNodeMetaData.column_CANCELED_BY, aCommitmentNode.getCanceledByNodeIdString());
		GcgDateHelper.setIso8601DateContentValue(theContentValues, CommitmentNodeMetaData.column_CANCELED_TIMESTAMP, aCommitmentNode.getCanceledTimestamp());
		return theContentValues;
	}

	@Override
	public ContentValues buildUpdateContentValues(T aCommitmentNode) {
		return buildContentValues(aCommitmentNode);
	}

}
