/* @(#)FmmConfigurationHelper.java
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

package com.flywheelms.library.fms.helper;

import com.flywheelms.gcongui.gcg.interfaces.GcgGuiable;
import com.flywheelms.library.fmm.helper.FmmAssetsHelper;
import com.flywheelms.library.fmm.repository.FmmAccessScope;
import com.flywheelms.library.fmm.repository.FmmConfiguration;
import com.flywheelms.library.fmm.repository.FmmConfigurationPrivate;
import com.flywheelms.library.fmm.repository.FmmConfigurationTemplateSource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

public class FmmConfigurationHelper {
	
	private static final String key__FMM_CONFIGURATION__LIST = "fmmConfigurationList";
	
	public static ArrayList<FmmConfigurationPrivate> getPrivateConfigurationList() {
		ArrayList<FmmConfigurationPrivate> theConfigurationList = new ArrayList<FmmConfigurationPrivate>();
		String theString = FmsFileHelper.readStringFromApplicationFile(FmmAccessScope.PRIVATE.getConfigurationFileName());
		if(theString != null && theString.length() > 0) {
			try {
				JSONObject theJsonObject = new JSONObject(theString);
				JSONArray theJsonArray = theJsonObject.getJSONArray(key__FMM_CONFIGURATION__LIST);
				JSONObject theJsonConfiguration;
				for(int i = 0; i < theJsonArray.length(); i++) {
					theJsonConfiguration = theJsonArray.getJSONObject(i);
					theConfigurationList.add(new FmmConfigurationPrivate(theJsonConfiguration));
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return theConfigurationList;
	}

	private static Collection<? extends GcgGuiable> getAssetsConfigurationTemplateList() {
		ArrayList<FmmConfiguration> theConfigurationList = FmmAssetsHelper.getFmmConfigurationTemplateList();
		return theConfigurationList;
	}

//	private static String readConfigurationListFile(String aFileName) {
//		StringBuffer theStringBuffer = new StringBuffer(); 
//		File theFile = GcgApplication.getContext().getFileStreamPath(aFileName);
//	    if (theFile.exists()) {
//	        FileInputStream theFileInputStream;
//	        try {
//	            theFileInputStream = GcgApplication.getContext().openFileInput("test.txt");
//	    		byte[] theReadBuffer = new byte[1024];
//	            while(theFileInputStream.read(theReadBuffer) != -1) {
//	            	theStringBuffer.append(new String(theReadBuffer));
//	            }
//	            theFileInputStream.close();
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        } 
//	    }
//	    return theStringBuffer.toString();
	//	}

//	private static void createDemoPersistedPrivateConfigurationListFile() {
//		JSONObject theJsonObject = new JSONObject();
//		try {
//			JSONArray theJsonArray = new JSONArray();
//			theJsonArray.put(new FmmConfigurationPrivate("FMM Empty Template", "EmptyTemplate").getJsonObject());
//			theJsonObject.put(key__FMM_CONFIGURATION__LIST, theJsonArray);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		FmsFileHelper.writeApplicationFile(file_name__PRIVATE_FMM_CONFIGURATION__LIST, theJsonObject.toString());
//	}

	public static ArrayList<FmmConfiguration> getSharedRepositories() {
		return null;
	}
	
	public static boolean importSerializedSharedConfigurationProfile(@SuppressWarnings("unused") String aConfigurationProfile) {
		// TODO
		return true;
	}
	
	public static boolean importPrivateConfigurationFile(@SuppressWarnings("unused") File aFile) {
		// TODO
		return true;
	}
	
	public static boolean emailPrivateConfigurationFile(@SuppressWarnings("unused") File aFile) {
		// TODO - Email intent with file attached
		return true;
	}
	
	public static boolean emailSharedConfigurationProfile(@SuppressWarnings("unused") String aSerializedSharedConfigurationConfiguration) {
		// TODO - Email intent with string imbedded in message body
		return true;
	}
	
	public static ArrayList<GcgGuiable> getTemplateConfigurationGuiableList(FmmConfigurationTemplateSource aTemplateSource) {
		ArrayList<GcgGuiable>  theGuiableList;
		switch (aTemplateSource) {
			case PRIVATE:
				theGuiableList = new ArrayList<GcgGuiable>(getPrivateConfigurationList());
				break;
			case ASSETS:
				theGuiableList = new ArrayList<GcgGuiable>(getAssetsConfigurationTemplateList());
				break;
			case CLOUD:
			case TEAM:
			default:
				theGuiableList = new ArrayList<GcgGuiable>();
		}
		return theGuiableList;
	}

	public static ArrayList<GcgGuiable> getConfigurationGuiableList(FmmAccessScope anAccessScope) {
		ArrayList<GcgGuiable>  theGuiableList;
		switch (anAccessScope) {
			case PRIVATE:
				theGuiableList = new ArrayList<GcgGuiable>(getPrivateConfigurationList());
				break;
			case TEAM:
			default:
				theGuiableList = new ArrayList<GcgGuiable>();
		}
		return theGuiableList;
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends FmmConfiguration> void newFmmConfiguration(T anFmmConfiguration) {
		String theFileName;
		switch(anFmmConfiguration.getFmmAccessScope()) {
			case PRIVATE:
				theFileName = FmmAccessScope.PRIVATE.getConfigurationFileName();
				break;
			case TEAM:
				//$FALL-THROUGH$
			default:
				theFileName = FmmAccessScope.TEAM.getConfigurationFileName();
		}
		ArrayList<T> theConfigurationList;
		if(FmsFileHelper.applicationFileExists(theFileName)) {
			theConfigurationList = (ArrayList<T>) getPrivateConfigurationList();
		} else {
			theConfigurationList = new ArrayList<T>();
		}
		theConfigurationList.add(anFmmConfiguration);
		JSONObject theJsonObject = new JSONObject();
		try {
			JSONArray theJsonArray = new JSONArray();
			for(FmmConfiguration theFmmConfiguration : theConfigurationList) {
				theJsonArray.put(theFmmConfiguration.getJsonObject());
			}
			theJsonObject.put(key__FMM_CONFIGURATION__LIST, theJsonArray);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FmsFileHelper.writeApplicationFile(anFmmConfiguration.getFmmAccessScope().getConfigurationFileName(), theJsonObject.toString());
	}

	@SuppressWarnings("unchecked")
	public static <T extends FmmConfiguration> void deleteFmmConfiguration(T anFmmConfiguration) {
		ArrayList<T> theConfigurationList = new ArrayList<T>();
		switch(anFmmConfiguration.getFmmAccessScope()) {
			case PRIVATE:
				theConfigurationList = (ArrayList<T>) getPrivateConfigurationList();
				break;
			case TEAM:
				//$FALL-THROUGH$
			default:
		}
		for(FmmConfiguration theFmmConfiguration : theConfigurationList) {
			if(theFmmConfiguration.getNodeIdString().equals(anFmmConfiguration.getNodeIdString())) {
				theConfigurationList.remove(theFmmConfiguration);
				break;
			}
		}
		writeFmmConfigurationFile(anFmmConfiguration.getFmmAccessScope(), theConfigurationList);
	}
	
	private static  <T extends FmmConfiguration> void writeFmmConfigurationFile(FmmAccessScope anFmmAccessScope, ArrayList<T> anFmmConfigurationList) {
		JSONObject theJsonObject = new JSONObject();
		try {
			JSONArray theJsonArray = new JSONArray();
			for(FmmConfiguration theFmmConfiguration : anFmmConfigurationList) {
				theJsonArray.put(theFmmConfiguration.getJsonObject());
			}
			theJsonObject.put(key__FMM_CONFIGURATION__LIST, theJsonArray);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FmsFileHelper.writeApplicationFile(anFmmAccessScope.getConfigurationFileName(), theJsonObject.toString());
	}

	public static ArrayList<FmmConfiguration> getFmmConfigurationList(FmmAccessScope anAccessScope) {
		ArrayList<FmmConfiguration>  theFmmConfiguration;
		switch (anAccessScope) {
			case PRIVATE:
				theFmmConfiguration = new ArrayList<FmmConfiguration>(getPrivateConfigurationList());
				break;
			case TEAM:
			default:
				theFmmConfiguration = new ArrayList<FmmConfiguration>();
		}
		return theFmmConfiguration;
	}

}
