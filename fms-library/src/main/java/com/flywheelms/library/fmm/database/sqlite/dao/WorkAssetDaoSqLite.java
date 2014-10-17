/* @(#)ProjectAssetDaoSqLite.java
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

import com.flywheelms.library.fmm.interfaces.WorkAsset;
import com.flywheelms.library.fmm.meta_data.CompletableNodeMetaData;
import com.flywheelms.library.fmm.meta_data.IdNodeMetaData;
import com.flywheelms.library.fmm.meta_data.ProjectAssetMetaData;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.governable.ProjectAsset;
import com.flywheelms.library.fmm.node.impl.governable.StrategicAsset;

import java.util.HashMap;

public class WorkAssetDaoSqLite extends CompletableNodeDaoSqLite<WorkAsset> {

	private static WorkAssetDaoSqLite singleton;

	public static WorkAssetDaoSqLite getInstance() {
		if(WorkAssetDaoSqLite.singleton == null) {
			WorkAssetDaoSqLite.singleton = new WorkAssetDaoSqLite();
		}
		return WorkAssetDaoSqLite.singleton;
	}
	
	@Override
	public FmmNodeDefinition getFmmNodeDefinition() {
		return FmmNodeDefinition.WORK_ASSET;
	}
	
	@Override
	protected void buildColumnIndexMap(Cursor aCursor) {
		super.buildColumnIndexMap(aCursor);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, ProjectAssetMetaData.column_PROJECT_ID);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, ProjectAssetMetaData.column_IS_STRATEGIC);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CompletableNodeMetaData.column_SEQUENCE);
	}

	@Override
	protected void getColumnValues(HashMap<String, Integer> aHashMap, Cursor aCursor, WorkAsset aProjectAsset) {
		super.getColumnValues(aHashMap, aCursor, aProjectAsset);
		aProjectAsset.setProjectId(aCursor.getString(aHashMap.get(ProjectAssetMetaData.column_PROJECT_ID)));
		aProjectAsset.setStrategic(aCursor.getInt(aHashMap.get(ProjectAssetMetaData.column_IS_STRATEGIC)));
		aProjectAsset.setSequence(aCursor.getInt(aHashMap.get(CompletableNodeMetaData.column_SEQUENCE)));
	}

	@Override
	public ContentValues buildContentValues(WorkAsset aWorkAsset) {
		ContentValues theContentValues = super.buildContentValues(aWorkAsset);
		theContentValues.put(ProjectAssetMetaData.column_PROJECT_ID, aWorkAsset.getProjectNodeIdString());
		theContentValues.put(ProjectAssetMetaData.column_IS_STRATEGIC, aWorkAsset.getStrategicAsInt());
		theContentValues.put(CompletableNodeMetaData.column_SEQUENCE, aWorkAsset.getSequence());
		return theContentValues;
	}
	
	@Override
	public ContentValues buildUpdateContentValues(WorkAsset aProjectAsset) {
		return buildContentValues(aProjectAsset);
	}

	@Override
	protected WorkAsset getNextObjectFromCursor(Cursor aCursor) {
        if(aCursor.getInt(this.columnIndexMap.get(ProjectAssetMetaData.column_IS_STRATEGIC)) > 0) {
            StrategicAsset theStrategicAsset = new StrategicAsset(aCursor.getString(this.columnIndexMap.get(IdNodeMetaData.column_ID)) );
            getColumnValues(this.columnIndexMap, aCursor, theStrategicAsset);
            return theStrategicAsset;
        } else {
            ProjectAsset theProjectAsset = new ProjectAsset(aCursor.getString(this.columnIndexMap.get(IdNodeMetaData.column_ID)) );
            getColumnValues(this.columnIndexMap, aCursor, theProjectAsset);
            return theProjectAsset;
        }
	}
}
