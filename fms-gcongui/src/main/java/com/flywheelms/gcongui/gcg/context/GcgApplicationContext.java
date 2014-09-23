/* @(#)GcgApplicationContext.java
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

package com.flywheelms.gcongui.gcg.context;

import android.graphics.drawable.Drawable;

import com.flywheelms.gcongui.gcg.interfaces.GcgPerspective;
import com.flywheelms.gcongui.gcg.interfaces.GcgSerialization;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GcgApplicationContext implements GcgSerialization {
	
	private static final String key__DATA_SOURCE_BREADCRUMB = "DataSourceBreadcrumb";
	private static final String key__ACTIVITY_BREADCRUMB_LIST = "ActivityBreadcrumbList";
	private GcgContextBreadcrumb dataSourceBreadcrumb;
	private ArrayList<GcgActivityBreadcrumb> activityBreadcrumbList = new ArrayList<GcgActivityBreadcrumb>();
	
	// create a context root
	public GcgApplicationContext(int aDataSourceDrawableResourceId, String aDataSourceName) {
		this.dataSourceBreadcrumb = new GcgContextBreadcrumb(aDataSourceDrawableResourceId, aDataSourceName);
	}

	public GcgApplicationContext(GcgApplicationContext anApplicationContext, GcgActivityBreadcrumb anActivityBreadcrumb) {
		this(anApplicationContext.getJsonObject());
		this.activityBreadcrumbList.add(anActivityBreadcrumb);
	}
	
	public GcgApplicationContext(JSONObject aJsonObject) {
		try {
			this.dataSourceBreadcrumb = new GcgContextBreadcrumb(
					aJsonObject.getJSONObject(key__DATA_SOURCE_BREADCRUMB));
			JSONArray theJsonArray = aJsonObject.getJSONArray(key__ACTIVITY_BREADCRUMB_LIST);
			for(int i=0; i < theJsonArray.length(); i++){                                     
				JSONObject theJsonObject = theJsonArray.getJSONObject(i);
				this.activityBreadcrumbList.add(new GcgActivityBreadcrumb(theJsonObject));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}         
	}

	public int getDataSourceDrawableResourceId() {
		return this.dataSourceBreadcrumb.getDrawableResourceId();
	}

	public Drawable getDataSourceDrawable() {
		return this.dataSourceBreadcrumb.getDrawable();
	}

	public String getDataSourceName() {
		return this.dataSourceBreadcrumb.getText();
	}

	@Override
	public String getSerialized() {
		return getJsonObject().toString();
	}
	
	@Override
	public JSONObject getJsonObject() {
		JSONObject theJsonObject = new JSONObject();
		try {
			theJsonObject.put(key__DATA_SOURCE_BREADCRUMB, this.dataSourceBreadcrumb.getJsonObject());
			JSONArray theJsonArray = new JSONArray();
			for (GcgActivityBreadcrumb theActivityBreadcrumb : this.activityBreadcrumbList) {
				theJsonArray.put(theActivityBreadcrumb.getJsonObject());
			}
			theJsonObject.put(key__ACTIVITY_BREADCRUMB_LIST, theJsonArray);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return theJsonObject;
	}

	public GcgApplicationContext getClone() {
		return new GcgApplicationContext(getJsonObject());
	}
	
	@Override
	public String toString() {
		StringBuffer theStringBuffer = new StringBuffer();
		theStringBuffer.append(this.dataSourceBreadcrumb.getText());
		for(GcgActivityBreadcrumb theActivityBreadcrumb : this.activityBreadcrumbList) {
			theStringBuffer.append(" --> ");
			theStringBuffer.append(theActivityBreadcrumb.getActivityName());
			theStringBuffer.append(" :: ");
			theStringBuffer.append(theActivityBreadcrumb.getFrameBreadcrumb().getFrameName());
			theStringBuffer.append(" :: ");
			theStringBuffer.append(theActivityBreadcrumb.getFrameBreadcrumb().getPerspectiveName());
			for(GcgContextBreadcrumb theBreadcrumb : theActivityBreadcrumb.getFrameBreadcrumb().getPerspectiveContext()) {
				theStringBuffer.append(" <> ");
				theStringBuffer.append(theBreadcrumb.getText());
			}
		}
		return theStringBuffer.toString();
	}
	
	public ArrayList<GcgActivityBreadcrumb> getActivityBreadcrumbList() {
		return this.activityBreadcrumbList;
	}

	@Override
	public boolean validateSerializationFormatVersion(String aString) {
		// TODO Auto-generated method stub
		return false;
	}

	public GcgFrame getRootActivityFrame() {
		if(this.activityBreadcrumbList == null || this.activityBreadcrumbList.size() < 1) {
			return null;
		}
		GcgActivityBreadcrumb theFirstActivityBreakcrumb = this.activityBreadcrumbList.get(0);
		return GcgFrame.getObjectForName(theFirstActivityBreakcrumb.getFrameBreadcrumb().getText());
	}

	public GcgPerspective getRootActivityPerspective() {
		if(this.activityBreadcrumbList == null || this.activityBreadcrumbList.size() < 1) {
			return null;
		}
		GcgActivityBreadcrumb theFirstActivityBreakcrumb = this.activityBreadcrumbList.get(0);
		return GcgPerspective.getObjectForName(theFirstActivityBreakcrumb.getFrameBreadcrumb().getPerspectiveName().toString());
	}
}
