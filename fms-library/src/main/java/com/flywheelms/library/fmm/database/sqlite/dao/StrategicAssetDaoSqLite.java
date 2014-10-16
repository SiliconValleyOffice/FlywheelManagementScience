/* @(#)StrategicAssetDaoSqLite.java
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

import com.flywheelms.library.fmm.meta_data.CompletableNodeMetaData;
import com.flywheelms.library.fmm.meta_data.IdNodeMetaData;
import com.flywheelms.library.fmm.meta_data.StrategicAssetMetaData;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.governable.StrategicAsset;

import java.util.HashMap;

public class StrategicAssetDaoSqLite extends CompletableNodeDaoSqLite<StrategicAsset> {

	private static StrategicAssetDaoSqLite singleton;

	public static StrategicAssetDaoSqLite getInstance() {
		if(StrategicAssetDaoSqLite.singleton == null) {
			StrategicAssetDaoSqLite.singleton = new StrategicAssetDaoSqLite();
		}
		return StrategicAssetDaoSqLite.singleton;
	}
	
	@Override
	public FmmNodeDefinition getFmmNodeDefinition() {
		return FmmNodeDefinition.STRATEGIC_ASSET;
	}
	
	@Override
	protected void buildColumnIndexMap(Cursor aCursor) {
		super.buildColumnIndexMap(aCursor);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, StrategicAssetMetaData.column_PROJECT_ID);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, StrategicAssetMetaData.column_IS_STRATEGIC);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CompletableNodeMetaData.column_SEQUENCE);
	}

	@Override
	protected void getColumnValues(HashMap<String, Integer> aHashMap, Cursor aCursor, StrategicAsset aStrategicAsset) {
		super.getColumnValues(aHashMap, aCursor, aStrategicAsset);
		aStrategicAsset.setProjectId(aCursor.getString(aHashMap.get(StrategicAssetMetaData.column_PROJECT_ID)));
		aStrategicAsset.setStrategic(aCursor.getInt(aHashMap.get(StrategicAssetMetaData.column_IS_STRATEGIC)));
		aStrategicAsset.setSequence(aCursor.getInt(aHashMap.get(CompletableNodeMetaData.column_SEQUENCE)));
	}

	@Override
	public ContentValues buildContentValues(StrategicAsset aStrategicAsset) {
		ContentValues theContentValues = super.buildContentValues(aStrategicAsset);
		theContentValues.put(StrategicAssetMetaData.column_PROJECT_ID, aStrategicAsset.getProjectNodeIdString());
		theContentValues.put(StrategicAssetMetaData.column_IS_STRATEGIC, aStrategicAsset.getStrategicAsInt());
		theContentValues.put(CompletableNodeMetaData.column_SEQUENCE, aStrategicAsset.getSequence());
		return theContentValues;
	}
	
	@Override
	public ContentValues buildUpdateContentValues(StrategicAsset aStrategicAsset) {
		return buildContentValues(aStrategicAsset);
	}

	@Override
	protected StrategicAsset getNextObjectFromCursor(Cursor aCursor) {
		StrategicAsset theStrategicAsset = null;
		theStrategicAsset = new StrategicAsset(aCursor.getString(this.columnIndexMap.get(IdNodeMetaData.column_ID)) );
		getColumnValues(this.columnIndexMap, aCursor, theStrategicAsset);
		return theStrategicAsset;
	}

}
