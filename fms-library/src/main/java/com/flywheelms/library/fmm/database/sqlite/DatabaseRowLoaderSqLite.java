/* @(#)DatabaseRowLoaderSqLite.java
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

package com.flywheelms.library.fmm.database.sqlite;

import android.database.sqlite.SQLiteDatabase;

import com.flywheelms.library.fmm.helper.FmmAssetsHelper;
import com.flywheelms.library.fmm.helper.FmmSqlHelper;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmNode;

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseRowLoaderSqLite {
	
	private static DatabaseRowLoaderSqLite singleton = null;

	public DatabaseRowLoaderSqLite() {
	}
	
	public static DatabaseRowLoaderSqLite getInstance() {
		if(DatabaseRowLoaderSqLite.singleton == null){
			DatabaseRowLoaderSqLite.singleton = new DatabaseRowLoaderSqLite();
		}
		return DatabaseRowLoaderSqLite.singleton;
	}

	public static boolean insertTemplateRows(SQLiteDatabase aSQLiteDatabase, String aTemplateName) {
		if(! templateRowsExist(aTemplateName)) {
			return false;
		}
		aSQLiteDatabase.execSQL("PRAGMA foreign_keys = OFF ");
		for(Class<? extends FmmNode> theNodeClass : getTablesToLoad(aTemplateName)) {
			initializeTableFromCsvAsset(aSQLiteDatabase, getTemplateRowsDirectoryPath(aTemplateName), theNodeClass);
		}
		aSQLiteDatabase.execSQL("PRAGMA foreign_keys = ON ");
		return true;
	}

	private static ArrayList<Class<? extends FmmNode>> getTablesToLoad(String aRowsDirectoryName) {
		String[] theFileList = FmmAssetsHelper.getFileNameArray(getTemplateRowsDirectoryPath(aRowsDirectoryName));
		ArrayList<Class<? extends FmmNode>> theTablesToLoad = new ArrayList<Class<? extends FmmNode>>();
		for(FmmNodeDefinition theFmmNodeDefinition : FmmNodeDefinition.DATABASE_LOAD_ORDER) {
			if(fileExistsForNode(theFileList, theFmmNodeDefinition)) {
				theTablesToLoad.add(theFmmNodeDefinition.getNodeClass());
			}
		}
		return theTablesToLoad;
	}

	public static boolean templateRowsExist(String aTemplateName) {
		return FmmAssetsHelper.getFileNameList(getTemplateRowsDirectoryPath(aTemplateName)).size() > 0;
	}

	public static String getTemplateRowsDirectoryPath(String aRowsDirectoryName) {
		return "database" + File.separator + "rows" + File.separator + aRowsDirectoryName;
	}
	
	private static boolean fileExistsForNode(String[] theFileList, FmmNodeDefinition theFmmNodeDefinition) {
		for(String theFileName : Arrays.asList(theFileList)) {
			if(theFileName.split("\\.")[0].equals(theFmmNodeDefinition.getClassName())) {
				return true;
			}
		}
		return false;
	}

	public static String getRowsFilePath(String aRowsDirectoryPath, Class<? extends FmmNode> aClass) {
		return aRowsDirectoryPath + File.separator + aClass.getSimpleName() + ".rows";
	}

	private static void initializeTableFromCsvAsset(SQLiteDatabase aSQLiteDatabase, 
			String aRowsDirectoryPath, Class<? extends FmmNode> theNodeClass ) {
		@SuppressWarnings("resource")
		BufferedReader theBufferedReader = FmmAssetsHelper.getBufferedReader(getRowsFilePath(aRowsDirectoryPath, theNodeClass));
		FmmSqlHelper.insertRowsFromCsvFile(theNodeClass.getSimpleName(), theBufferedReader, aSQLiteDatabase);
	}

}
