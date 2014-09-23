/* @(#)FmmDatabaseTemplate.java
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

package com.flywheelms.library.fmm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.flywheelms.gcongui.gcg.GcgApplication;
import com.flywheelms.library.fmm.database.sqlite.DatabaseRowLoaderSqLite;
import com.flywheelms.library.fmm.database.sqlite.FmmDatabaseBuilderSqLite;

import java.io.File;
import java.io.IOException;

public enum FmmDatabaseTemplate {
	
//	DEMO_1 ("Demo1"),
//	DEMO_2 ("Demo2"),
//	EMPTY_FMM ("EmptyFmm"),
//	FLYWHEEL_SPINUP ("FlywheelSpinup");
	EMPTY_FMM ("EmptyFmm");
	
	private final String name;
	
	private FmmDatabaseTemplate(String aName) {
		this.name = aName;
	}
	
	public String getName() {
		return this.name;
	}
	
	/*
	 * used by developers to generate new templates when the DDL or rows have been changed.
	 * 1) delete the old templates from assets
	 * 2) deploy APK to test device
	 * 3) run application
	 * 4) copy the newly created template databases from the test device "databases" directory to assets in development machine
	 * 5) deploy updated APK to test device again and new templates will exist in assets
	 */
	public static boolean initializeTemplates() {
		for(FmmDatabaseTemplate theTemplate : values()) {
			if(templateExists(theTemplate.getName())) {
				continue;
			}
			SQLiteDatabase theDatabase = GcgApplication.getContext().openOrCreateDatabase(theTemplate.getName() + ".db", Context.MODE_PRIVATE, null);
			FmmDatabaseBuilderSqLite.createDatabaseTables(theDatabase);
			DatabaseRowLoaderSqLite.insertTemplateRows(theDatabase, theTemplate.getName());
			theDatabase.close();
		}
		return true;
	}

	public static boolean templateExists(String aTemplateName) {
		try {
			GcgApplication.getAssetManager().open(getTemplateDirectoryPath(aTemplateName) + ".db");
		} catch (IOException ex) {
		  return false;
		}
		return true;
	}

	public static String getTemplateDirectoryPath(String aRowsDirectoryName) {
		return "database" + File.separator + "template" + File.separator + aRowsDirectoryName;
	}

}
