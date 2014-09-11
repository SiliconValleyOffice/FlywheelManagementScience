/* @(#)FmmDatabaseHelper.java
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

package com.flywheelms.library.fmm.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.flywheelms.library.fmm.database.sqlite.dao.FmmConfigurationDaoSqLite;
import com.flywheelms.library.fmm.database.sqlite.dao.FmsOrganizationDaoSqLite;
import com.flywheelms.library.fmm.meta_data.FiscalYearMetaData;
import com.flywheelms.library.fmm.meta_data.IdNodeMetaData;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.governable.FmsOrganization;
import com.flywheelms.library.fmm.repository.FmmConfiguration;
import com.flywheelms.library.gcg.GcgApplication;


public class FmmDatabaseHelper {

	public static void initializeNewFmm(FmmConfiguration anFmmConfiguration, FmsOrganization anFmsOrganization) {
		switch (anFmmConfiguration.getFmmAccessScope()) {
		case PRIVATE:
			initializeNewPrivateFmm(anFmmConfiguration, anFmsOrganization);
			break;
		case SHARED:
			break;
		case TEAM:
			default:
				
		}
	}

	private static void initializeNewPrivateFmm(FmmConfiguration anFmmConfiguration, FmsOrganization anFmsOrganization) {
		anFmmConfiguration.setOrganizationNodeIdString(anFmsOrganization.getNodeIdString());
		anFmmConfiguration.setForThisFmm(true);
		SQLiteDatabase theDatabase = GcgApplication.getContext().openOrCreateDatabase(anFmmConfiguration.getFileName(), Context.MODE_PRIVATE, null);
		insertNewFmsOrganization(anFmsOrganization, theDatabase);
		insertNewFmmConfiguration(anFmmConfiguration, theDatabase);
		deleteOldFmmConfiguration(anFmmConfiguration, theDatabase);
		setOwnerForNewFmmSqLite(theDatabase, anFmsOrganization);
		deleteOldFmsOrganization(anFmsOrganization, theDatabase);
		theDatabase.close();
	}

	private static void deleteOldFmmConfiguration(FmmConfiguration anFmmConfigurationToRetain, SQLiteDatabase aDatabase) {
		aDatabase.beginTransaction();
    	aDatabase.delete(FmmNodeDefinition.FMM_CONFIGURATION.getName(), IdNodeMetaData.column_ID + "!=?", new String[] {anFmmConfigurationToRetain.getNodeIdString()});
		aDatabase.setTransactionSuccessful();
		aDatabase.endTransaction();
	}

	private static void deleteOldFmsOrganization(FmsOrganization anFmsOrganizationToRetain, SQLiteDatabase aDatabase) {
		aDatabase.beginTransaction();
		aDatabase.delete(FmmNodeDefinition.FMS_ORGANIZATION.getName(), IdNodeMetaData.column_ID + "!=?", new String[] {anFmsOrganizationToRetain.getNodeIdString()});
		aDatabase.setTransactionSuccessful();
		aDatabase.endTransaction();
	}

	private static void insertNewFmsOrganization(FmsOrganization anFmsOrganization, SQLiteDatabase aDatabase) {
		aDatabase.beginTransaction();
		ContentValues theContentValues = FmsOrganizationDaoSqLite.getInstance().buildContentValues(anFmsOrganization);
    	aDatabase.insert(FmmNodeDefinition.FMS_ORGANIZATION.getName(), null, theContentValues);
		aDatabase.setTransactionSuccessful();
		aDatabase.endTransaction();
	}

	private static void insertNewFmmConfiguration(FmmConfiguration anFmmConfiguration, SQLiteDatabase aDatabase) {
		aDatabase.beginTransaction();
		ContentValues theContentValues = FmmConfigurationDaoSqLite.getInstance().buildContentValues(anFmmConfiguration);
    	aDatabase.insert(FmmNodeDefinition.FMM_CONFIGURATION.getName(), null, theContentValues);
		aDatabase.setTransactionSuccessful();
		aDatabase.endTransaction();
	}

	public static void setOwnerForNewFmmSqLite(SQLiteDatabase aDatabase, FmsOrganization anOrganization) {
		aDatabase.beginTransaction();
		moveRowsToOrganization(aDatabase, FmmNodeDefinition.FISCAL_YEAR, anOrganization);
		moveRowsToOrganization(aDatabase, FmmNodeDefinition.ORGANIZATION_COMMUNITY_MEMBER, anOrganization);
		moveRowsToOrganization(aDatabase, FmmNodeDefinition.FLYWHEEL_TEAM, anOrganization);
		moveRowsToOrganization(aDatabase, FmmNodeDefinition.STRATEGY_TEAM, anOrganization);
		moveRowsToOrganization(aDatabase, FmmNodeDefinition.FUNCTIONAL_TEAM, anOrganization);
		moveRowsToOrganization(aDatabase, FmmNodeDefinition.COMMUNITY_MEMBER_ORGANIZATION_GOVERNANCE_AUTHORITY, anOrganization);
		moveRowsToOrganization(aDatabase, FmmNodeDefinition.ORGANIZATION_GOVERNANCE_TARGET, anOrganization);
		moveRowsToOrganization(aDatabase, FmmNodeDefinition.ORGANIZATION_LOCK_CONFIG, anOrganization);
		aDatabase.setTransactionSuccessful();
		aDatabase.endTransaction();
	}

	private static void moveRowsToOrganization(SQLiteDatabase aDatabase, FmmNodeDefinition anFmmNodeDefinition, FmsOrganization anOrganization) {
		aDatabase.execSQL("UPDATE " + anFmmNodeDefinition.getName() +
				" SET " + FiscalYearMetaData.column_ORGANIZATION_ID + " = '" + anOrganization.getNodeIdString() + "'");
	}

//	public static void createDatabase(SQLiteDatabase aDatabase) {
//		FmmDatabaseBuilderSqLite.createDatabase(aDatabase);
//		DatabaseRowLoaderSqLite.insertRows(aDatabase, "Demo-1");
//		FmmDatabaseMediator.getActiveMediator().newFmmConfiguration(FmmDatabaseMediator.getActiveMediator().getFmmConfiguration(), true);
//	}
	
	// TODO

}
