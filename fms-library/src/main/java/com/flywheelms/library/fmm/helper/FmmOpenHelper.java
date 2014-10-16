/* @(#)FmmOpenHelper.java
 ** 
 ** Copyright (C) 2012 by Steven D. Stamps
 **
 **             Trademarks & Copyrights
 ** Flywheel Management Science(TM), Flywheel Management Model(TM),
** Flywheel Story Editor(TM) and FlywheelMS(TM) are exclusive trademarks
** of Steven D. Stamps and may only be used freely for the purpose of
** identifying the unforked version of this software.  Subsequent forks
** may not use these trademarks.  All other rights are reserved.
 ** and FlywheelMS(TM) are exclusive trademarks of Steven D. Stamps
 ** and may only be used freely for the purpose of identifying the
 ** unforked version of this software.  Subsequent forks (if any) may
 ** not use these trademarks.  All other rights are reserved.
 **
 ** DecKanGL (Decorated Kanban Glyph Language) and TribKn (Tribal Knowledge)
 ** are also exclusive trademarks of Steven D. Stamps.  These may be used
 ** freely within the unforked FlywheelMS application and documentation.
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
package com.flywheelms.library.fmm.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

public class FmmOpenHelper extends SQLiteOpenHelper {
	
	private static HashMap<String, FmmOpenHelper> openHelperMap = new HashMap<String, FmmOpenHelper>(); 
	
	public static final int DATABASE_VERSION = 1;
	
//	public static final boolean START_WITH_NEW_DATABASE = true;
	public int writableDatabaseCount = 0;
	
	public boolean newDatabase = false;

	public FmmOpenHelper(Context aContext, String aDatabaseFileName, CursorFactory aCursorFactory, int aVersion) {
		super(aContext, aDatabaseFileName, aCursorFactory, aVersion);
	}
	
	public static FmmOpenHelper getInstance(String aFileName) {
		FmmOpenHelper theFmmOpenHelper = FmmOpenHelper.openHelperMap.get(aFileName);
		if (theFmmOpenHelper == null) {
			theFmmOpenHelper = new FmmOpenHelper(
				FmmHelper.getContext(),
				aFileName,
				null,
				DATABASE_VERSION);
			FmmOpenHelper.openHelperMap.put(aFileName, theFmmOpenHelper);
		}
		return theFmmOpenHelper;
	}

	@Override
	public void onCreate(SQLiteDatabase aDatabase) {
		this.newDatabase = true;
//		FmmDatabaseBuilderSqLite.createDatabaseTables(aDatabase);
//		DatabaseRowLoaderSqLite.insertTemplateRows(aDatabase, "EmptyFmm");
//		insertFmmConfigurationRow(aDatabase);
	}

//	private static void insertFmmConfigurationRow(SQLiteDatabase aDatabase) {
//		FmsOrganization theFmsOrganization = PersistenceTechnologyDelegateSqLite.getFirstOrganizationRow(aDatabase);
//		FmmConfiguration theFmmConfiguration = FmmDatabaseMediator.getRequestedFmmConfiguration();
//		theFmmConfiguration.setForThisFmm(true);
//		if(theFmmConfiguration.getOrganizationNodeIdString() == null || theFmmConfiguration.getOrganizationNodeIdString().equals("")) {
//			theFmmConfiguration.setOrganizationId(theFmsOrganization.getNodeIdString());
//		}
//		ContentValues theContentValues = FmmConfigurationDaoSqLite.getInstance().buildContentValues(theFmmConfiguration);
//    	aDatabase.insert(FmmNodeDefinition.FMM_CONFIGURATION.getTableName(), null, theContentValues);
////    	long theRowNumber = aDatabase.insert(FmmNodeDefinition.FMM_CONFIGURATION.getTableName(), null, theContentValues);
//    	FmmDatabaseHelper.setOwnerForNewFmmSqLite(aDatabase, theFmsOrganization);
//	}

	@Override
	public void onUpgrade(SQLiteDatabase aDatabase, int arg1, int arg2) {
		// TODO Auto-generated method stub
	}
	
//    @Override
//    public synchronized SQLiteDatabase getWritableDatabase() {
//    	if(START_WITH_NEW_DATABASE && this.writableDatabaseCount == 0) {
//    		close();
//    		FmmHelper.getContext().getDatabasePath(getDatabaseName()).delete();  // development hack
//    	}
//    	++this.writableDatabaseCount;
//    	return super.getWritableDatabase();
//    }
    
	public static SQLiteDatabase createFmmEmptyTemplate(String aDatabaseName, boolean bReplaceIfExists) {
			FmmOpenHelper theFmmOpenHelper = new FmmOpenHelper(
				FmmHelper.getContext(),
				aDatabaseName,
				null,
				DATABASE_VERSION);
		theFmmOpenHelper.close();  // close any open databases
		if(bReplaceIfExists) {
			FmmHelper.getContext().getDatabasePath(theFmmOpenHelper.getDatabaseName()).delete();  // replace if already exists
		}
		return theFmmOpenHelper.getWritableDatabase();
	}

}
