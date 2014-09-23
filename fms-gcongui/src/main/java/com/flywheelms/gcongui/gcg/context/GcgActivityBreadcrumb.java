/* @(#)GcgActivityBreadcrumb.java
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

import org.json.JSONException;
import org.json.JSONObject;

public class GcgActivityBreadcrumb extends GcgContextBreadcrumb {

	private static final String key__ACTIVITY_ID_STRING = "ActivityIdString";
	private static final String key__DATA_ID_STRING = "DataIdString";
	private static final String key__FRAME_BREADCRUMB = "FrameBreadcrumb";
	private GcgFrameBreadcrumb frameBreadcrumb;
	private String activityIdString;
	private String dataIdString;
	
	public GcgActivityBreadcrumb(int aDrawableResourceId, String aTextString, String anActivityIdString, String aDataIdString) {
		super(aDrawableResourceId, aTextString);
		this.activityIdString = anActivityIdString;
		this.dataIdString = aDataIdString;
	}
	
	public GcgActivityBreadcrumb(JSONObject aJsonObject) {
		super(aJsonObject);
		try {
			this.activityIdString = aJsonObject.getString(key__ACTIVITY_ID_STRING);
			this.dataIdString = aJsonObject.getString(key__DATA_ID_STRING);
			if(aJsonObject.has(key__FRAME_BREADCRUMB)) {
				this.frameBreadcrumb = new GcgFrameBreadcrumb(
						aJsonObject.getJSONObject(key__FRAME_BREADCRUMB));
			} else {
				this.frameBreadcrumb = GcgFrameBreadcrumb.NULL;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}         
	}
	
	@Override
	public JSONObject getJsonObject() {
		JSONObject theJsonObject = super.getJsonObject();
		try {
			theJsonObject.put(key__ACTIVITY_ID_STRING, this.activityIdString);
			theJsonObject.put(key__DATA_ID_STRING, this.dataIdString);
			if(hasFrameBreadcrumb()) {
				theJsonObject.put(key__FRAME_BREADCRUMB, this.frameBreadcrumb.getJsonObject());
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return theJsonObject;
	}

	public boolean hasFrameBreadcrumb() {
		return this.frameBreadcrumb != null && this.frameBreadcrumb.getText() != null && ! this.frameBreadcrumb.getText().equals("");
	}

	public GcgFrameBreadcrumb getFrameBreadcrumb() {
		return this.frameBreadcrumb;
	}

	public void setFrameBreadcrumb(GcgFrameBreadcrumb frameBreadcrumb) {
		this.frameBreadcrumb = frameBreadcrumb;
	}
	
	public int getActivityDrawableResourceId() {
		return getDrawableResourceId();
	}
	
	public Drawable getActivityDrawable() {
		return getDrawable();
	}

	public CharSequence getActivityName() {
		return getText();
	}

	public boolean hasPerspectiveContext() {
		return this.frameBreadcrumb.hasPerspectiveContext();
	}

	public String getActivityIdString() {
		return this.activityIdString;
	}

	public String getDataIdString() {
		return this.dataIdString;
	}

}
