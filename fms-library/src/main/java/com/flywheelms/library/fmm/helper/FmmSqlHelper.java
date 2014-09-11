/* @(#)FmmSqlHelper.java
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

package com.flywheelms.library.fmm.helper;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import org.xmlpull.v1.XmlPullParser;

import java.io.BufferedReader;
import java.io.IOException;

public class FmmSqlHelper {
	
	public static void insertRowFromXmlParser(String aTableName, XmlPullParser anXmlPullParser, SQLiteDatabase aSQLiteDatabase) {
		ContentValues theContentValues = new ContentValues();
		for(int i=0; i < anXmlPullParser.getAttributeCount(); ++i) {
		    theContentValues.put(anXmlPullParser.getAttributeName(i), anXmlPullParser.getAttributeValue(i));
		}
		aSQLiteDatabase.insertOrThrow(aTableName, null, theContentValues);
	}
	
	public static int insertRowsFromCsvFile(String aTableName, BufferedReader aBufferedReader, SQLiteDatabase aSQLiteDatabase) {
		int theRowCount = 0;
	    try {
	        String theLine = aBufferedReader.readLine();
	        String[] theColumnNamesArray = theLine.split(",");
	        int theColumnCount = theColumnNamesArray.length;
			ContentValues theContentValues = new ContentValues();
			aSQLiteDatabase.beginTransaction();
	        while ((theLine = aBufferedReader.readLine()) != null) {
	             String[] theRowData = theLine.split(",");
	             for(int i=0; i < theColumnCount; ++i) {
	            	 theContentValues.put(theColumnNamesArray[i], theRowData[i]);
	             }
	     		aSQLiteDatabase.insertOrThrow(aTableName, null, theContentValues);
	     		++theRowCount;
	        }
			aSQLiteDatabase.setTransactionSuccessful();
			aSQLiteDatabase.endTransaction();
	    }
	    catch (IOException ex) {
	        // handle exception
	    }
	    finally {
	        try {
	        	aBufferedReader.close();
	        }
	        catch (IOException e) {
	            // handle exception
	        }
	    }
	    return theRowCount;
	}

}
