/* @(#)GcgContextBreadcrumb.java
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

package com.flywheelms.library.gcg.context;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.drawable.Drawable;

import com.flywheelms.library.R;
import com.flywheelms.library.gcg.interfaces.GcgSerialization;

public class GcgContextBreadcrumb implements GcgSerialization {

	private static final String key__DRAWABLE_RESOURCE_ID = "DrawableResourceId";
	private static final String key_TEXT = "Text";
	public static final GcgContextBreadcrumb NULL = new GcgContextBreadcrumb(R.drawable.gcg__null_drawable, "");
	private int drawableResourceId;
	private Drawable drawable;
	private String text;
	
	public GcgContextBreadcrumb(int aBreadcrumbDrawableResourceId, String aBreadcrumbText) {
		this.drawableResourceId = aBreadcrumbDrawableResourceId;
		this.text = aBreadcrumbText;
	}
	
	public GcgContextBreadcrumb(JSONObject aJsonObject) {
		try {
			this.drawableResourceId = aJsonObject.getInt(key__DRAWABLE_RESOURCE_ID);
			this.text = aJsonObject.getString(key_TEXT);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}         
	}
	
	@Override
	public JSONObject getJsonObject() {
		JSONObject theJsonObject = new JSONObject();
		try {
			theJsonObject.put(key__DRAWABLE_RESOURCE_ID, this.drawableResourceId);
			theJsonObject.put(key_TEXT, this.text);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return theJsonObject;
	}

	@Override
	public String getSerialized() {
		return getJsonObject().toString();
	}

	@Override
	public boolean validateSerializationFormatVersion(String aString) {
		return true;
	}

	public int getDrawableResourceId() {
		return this.drawableResourceId;
	}

	public Drawable getDrawable() {
		return this.drawable;
	}

	public String getText() {
		return this.text;
	}
	
	public boolean hasText() {
		return this.text != null && ! this.text.equals("");
	}
	
	public boolean hasDrawable() {
		return this.drawableResourceId != 0 && this.drawableResourceId != R.drawable.gcg__null_drawable;
	}

}
