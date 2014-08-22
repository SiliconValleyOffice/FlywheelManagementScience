/* @(#)FmmAssetsHelper.java
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;

import com.flywheelms.library.fmm.repository.FmmConfiguration;
import com.flywheelms.library.fmm.repository.FmmConfigurationPrivate;
import com.flywheelms.library.fms.helper.FmsFileHelper;
import com.flywheelms.library.gcg.GcgApplication;

public class FmmAssetsHelper {

	private static final String directory_path__DATABASE_TEMPLATE = "database" + File.separator + "template";

	@SuppressWarnings("resource")
	public static XmlPullParser getXmlParserForAsset(String aFileName) {
	        XmlPullParserFactory theFactory;
	        XmlPullParser thePullParser;
	        InputStream theInputStream;
			try {
				theFactory = XmlPullParserFactory.newInstance();
		        theFactory.setValidating(false);
				thePullParser = theFactory.newPullParser();
				theInputStream = FmmAssetsHelper.getAssetManager().open(aFileName);
				thePullParser.setInput(theInputStream, null);
				return thePullParser;
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	}

	public static String getStringForAsset(String aFileName) {
	    InputStream theInputStream = null;
	    BufferedReader theBufferedReader = null;
	    StringBuilder theStringBuilder = new StringBuilder();
		try {
			theInputStream = FmmAssetsHelper.getAssetManager().open(aFileName);
	        theBufferedReader = new BufferedReader(new InputStreamReader(theInputStream));
	        String theLine;
	        while ((theLine = theBufferedReader.readLine()) != null) {
	        	theStringBuilder.append(theLine);
	        }
		} catch (IOException e1) {
			e1.printStackTrace();
			System.exit(-1);
		} finally {
	        try {
				if (theBufferedReader != null) {
					theBufferedReader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	   return theStringBuilder.toString(); 
	}

	public static AssetManager getAssetManager() {
		return GcgApplication.getInstance().getAssets();
	}
	
	public static BufferedReader getBufferedReader(String aFileName) {
		InputStream theInputStream;
		try {
			theInputStream = getAssetManager().open(aFileName);
			BufferedReader theBufferedReader = new BufferedReader(new InputStreamReader(theInputStream));
			return theBufferedReader;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String[] getFileNameArray(String aDirectoryPath) {
		try {
			return getAssetManager().list(aDirectoryPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new String[0];
	}

	public static ArrayList<String> getFileNameList(String aDirectoryPath) {
		return new ArrayList<String>(Arrays.asList(getFileNameArray(aDirectoryPath)));
	}
	
	public static InputStream getInputStream(String aFileName) throws IOException {
		return getAssetManager().open(aFileName); 
	}

	public static ArrayList<FmmConfiguration> getFmmConfigurationTemplateList() {
		ArrayList<FmmConfiguration> theRepositoryTemplateList = new ArrayList<FmmConfiguration>();
		for(String theDbFileName : getFileNameList(directory_path__DATABASE_TEMPLATE)) {
			String theDbName = FmmConfiguration.getDbName(theDbFileName);
			theRepositoryTemplateList.add(new FmmConfigurationPrivate(
					theDbName, theDbName));
		}
		return theRepositoryTemplateList;
	}
	
	public static boolean copyFmmTemplateToFile(String aTemplateFileName, File aDestinationFile) {
		return copyAssetToFile(directory_path__DATABASE_TEMPLATE + File.separator + aTemplateFileName, aDestinationFile);
	}
	
	@SuppressWarnings("resource")
	public static boolean copyAssetToFile(String anAssetFilePath, File aDestinationFile) {
		boolean theResults = true;
		InputStream theInputStream = null;
		OutputStream theOutputStream = null;
		File theTempDatabase = makeSureDatabasesFolderExists();
		try{
			theInputStream = getAssetManager().open(anAssetFilePath);
			theOutputStream = new FileOutputStream(aDestinationFile);
			byte theBuffer[] = new byte[1024];
			int theLength = 0;
			while((theLength=theInputStream.read(theBuffer)) > 0) {
				theOutputStream.write(theBuffer,0,theLength);
			}
		} catch (IOException e) {
			e.printStackTrace();
			theResults = false;
		} finally {
			theTempDatabase.delete();
			try {
				if(theOutputStream != null) {
					theOutputStream.close();
				}
				if(theInputStream != null) {
					theInputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				theResults = false;
			}
		}
		return theResults;
	}
	
	public static File makeSureDatabasesFolderExists() {
		// FileOutputStream throws an error when there is no 'databases' folder 
        // This is a HACK to make sure the databases folder exists
		// Return a File object so the temporary database can be deleted when the original operation is complete
		SQLiteDatabase db = GcgApplication.getContext().openOrCreateDatabase("tmp", 0, null);
        db.close();
        // leave tmp-journal file in place to prevent the databases directory from being deleted
        return FmsFileHelper.getDatabaseFile("tmp");
	}

}
