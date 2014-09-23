/* @(#)FmmConfigurationDaoSqLite.java
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
import com.flywheelms.library.fmm.meta_data.FmmConfigurationMetaData;
import com.flywheelms.library.fmm.meta_data.IdNodeMetaData;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.repository.FmmAccessScope;
import com.flywheelms.library.fmm.repository.FmmConfiguration;
import com.flywheelms.library.fmm.repository.FmmConfigurationPrivate;
import com.flywheelms.library.fmm.repository.FmmConfigurationShared;
import com.flywheelms.library.fmm.repository.FmmConfigurationTeam;

import java.util.HashMap;

public class FmmConfigurationDaoSqLite extends HeadlineNodeDaoSqLite<FmmConfiguration> {

	private static FmmConfigurationDaoSqLite singleton;

	public static FmmConfigurationDaoSqLite getInstance() {
		if(FmmConfigurationDaoSqLite.singleton == null) {
			FmmConfigurationDaoSqLite.singleton = new FmmConfigurationDaoSqLite();
		}
		return FmmConfigurationDaoSqLite.singleton;
	}
	
	@Override
	public FmmNodeDefinition getFmmNodeDefinition() {
		return FmmNodeDefinition.FMM_CONFIGURATION;
	}

	@Override
	protected void buildColumnIndexMap(Cursor aCursor) {
		super.buildColumnIndexMap(aCursor);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, FmmConfigurationMetaData.column_ORGANIZATION_ID);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, FmmConfigurationMetaData.column_FOR_THIS_FMM);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, FmmConfigurationMetaData.column_LAST_SYNC_DATE_TIME);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, FmmConfigurationMetaData.column_ACCESS_SCOPE);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, FmmConfigurationMetaData.column_DB_VERSION);
	}

	@Override
	public ContentValues buildContentValues(FmmConfiguration anFmmConfiguration) {
		ContentValues theContentValues = super.buildContentValues(anFmmConfiguration);
		theContentValues.put(FmmConfigurationMetaData.column_ORGANIZATION_ID, anFmmConfiguration.getOrganizationNodeIdString());
		theContentValues.put(FmmConfigurationMetaData.column_FOR_THIS_FMM, anFmmConfiguration.getForThisFmm());
		theContentValues.put(FmmConfigurationMetaData.column_LAST_SYNC_DATE_TIME, GcgDateHelper.getIso8601DateString(anFmmConfiguration.getLastSyncDate()));
		theContentValues.put(FmmConfigurationMetaData.column_ACCESS_SCOPE, anFmmConfiguration.getFmmAccessScope().getName());
		theContentValues.put(FmmConfigurationMetaData.column_DB_VERSION, anFmmConfiguration.getDbVersion());
		return theContentValues;
	}

	@Override
	protected void getColumnValues(HashMap<String, Integer> aHashMap, Cursor aCursor, FmmConfiguration anFmmConfiguration) {
		super.getColumnValues(aHashMap, aCursor, anFmmConfiguration);
		anFmmConfiguration.setOrganizationNodeIdString(aCursor.getString(aHashMap.get(FmmConfigurationMetaData.column_ORGANIZATION_ID)));
		anFmmConfiguration.setForThisFmm(aCursor.getInt(aHashMap.get(FmmConfigurationMetaData.column_FOR_THIS_FMM)));
		anFmmConfiguration.setLastSyncDate(GcgDateHelper.getDateFromIso8601DateString(aCursor.getString(aHashMap.get(FmmConfigurationMetaData.column_LAST_SYNC_DATE_TIME))));
		anFmmConfiguration.setFmmAccessScope(FmmAccessScope.getObjectForName(aCursor.getString(aHashMap.get(FmmConfigurationMetaData.column_ACCESS_SCOPE))));
		anFmmConfiguration.setDbVersion(aCursor.getInt(aHashMap.get(FmmConfigurationMetaData.column_DB_VERSION)));
	}

	@Override
	public FmmConfiguration getNextObjectFromCursor(Cursor aCursor) {
		FmmConfiguration theFmmConfiguration = null;
		switch(FmmAccessScope.getObjectForName(aCursor.getString(this.columnIndexMap.get(FmmConfigurationMetaData.column_ACCESS_SCOPE)))) {
		case PRIVATE:
			theFmmConfiguration = new FmmConfigurationPrivate(
					aCursor.getString(this.columnIndexMap.get(IdNodeMetaData.column_ID)) );
			break;
		case SHARED:
			theFmmConfiguration = new FmmConfigurationShared(
					aCursor.getString(this.columnIndexMap.get(IdNodeMetaData.column_ID)) );
			break;
		case TEAM:
			default:
				theFmmConfiguration = new FmmConfigurationTeam(
						aCursor.getString(this.columnIndexMap.get(IdNodeMetaData.column_ID)) );
				
		}
		getColumnValues(this.columnIndexMap, aCursor, theFmmConfiguration);
		return theFmmConfiguration;
	}

}
