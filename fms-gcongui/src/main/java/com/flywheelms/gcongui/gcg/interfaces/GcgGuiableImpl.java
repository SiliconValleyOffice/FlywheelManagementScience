/* @(#)GcgGuiableImpl.java
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

package com.flywheelms.gcongui.gcg.interfaces;

import android.graphics.drawable.Drawable;

import com.flywheelms.gcongui.gcg.GcgApplication;

import org.json.JSONException;
import org.json.JSONObject;

public class GcgGuiableImpl implements GcgGuiable {
	
	public static final String key__LABEL_TEXT = "LabelText";
	public static final String key__LABEL_DRAWABLE_RESOURCE_ID = "LabelDrawableResourceId";
	public static final String key__DATA_TEXT = "DataText";
	public static final String key__DATA_DRAWABLE_RESOURCE_ID = "DataDrawableResourceId";
	private String labelText;
	private Drawable labelDrawable;
	private int labelDrawableResourceId;
	private String dataText;
	private Drawable dataDrawable;
	private int dataDrawableResourceId;
	
	public GcgGuiableImpl(
			String aLabelText,
			Drawable aLabelDrawable,
			int aLabelDrawableResourceId,
			String aDataText,
			Drawable aDataDrawable,
			int aDataDrawableResourceId ) {
		this.labelText = aLabelText;
		this.labelDrawable = aLabelDrawable;
		this.labelDrawableResourceId = aLabelDrawableResourceId;
		this.dataText = aDataText;
		this.dataDrawable = aDataDrawable;
		this.dataDrawableResourceId = aDataDrawableResourceId;
	}
	
	GcgGuiableImpl(GcgGuiable aGcgGuiable) {
		this.labelText = aGcgGuiable.getLabelText();
		this.labelDrawable = aGcgGuiable.getLabelDrawable();
		this.labelDrawableResourceId = aGcgGuiable.getLabelDrawableResourceId();
		this.dataText = aGcgGuiable.getLabelText();
		this.dataDrawable = aGcgGuiable.getLabelDrawable();
		this.dataDrawableResourceId = aGcgGuiable.getLabelDrawableResourceId();
	}
	
	public GcgGuiableImpl(JSONObject aJsonObject) {
		try {
			this.labelText = aJsonObject.getString(GcgGuiableImpl.key__LABEL_TEXT);
			this.labelDrawableResourceId = aJsonObject.getInt(GcgGuiableImpl.key__LABEL_DRAWABLE_RESOURCE_ID);
			this.labelDrawable = GcgApplication.getAppResources().getDrawable(this.labelDrawableResourceId);
			this.dataText = aJsonObject.getString(GcgGuiableImpl.key__DATA_TEXT);
			this.dataDrawableResourceId = aJsonObject.getInt(GcgGuiableImpl.key__DATA_DRAWABLE_RESOURCE_ID);
			this.dataDrawable = GcgApplication.getAppResources().getDrawable(this.dataDrawableResourceId);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getSerialized() {
		return getJsonObject().toString();
	}

	public JSONObject getJsonObject() {
		JSONObject theJsonObject = new JSONObject();
		try {
			theJsonObject.put(key__LABEL_TEXT, this.labelText);
			theJsonObject.put(key__LABEL_DRAWABLE_RESOURCE_ID, this.labelDrawableResourceId);
			theJsonObject.put(key__DATA_TEXT, this.dataText);
			theJsonObject.put(key__DATA_DRAWABLE_RESOURCE_ID, this.dataDrawableResourceId);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return theJsonObject;
	}

	@Override
	public String getLabelText() {
		return this.labelText;
	}

	@Override
	public Drawable getLabelDrawable() {
		return this.labelDrawable;
	}

	@Override
	public String getDataText() {
		return this.dataText;
	}

	@Override
	public Drawable getDataDrawable() {
		return this.dataDrawable;
	}

	@Override
	public int getLabelDrawableResourceId() {
		return this.labelDrawableResourceId;
	}

	@Override
	public int getDataDrawableResourceId() {
		return this.dataDrawableResourceId;
	}
	
	@Override
	public String toString() {
		return this.labelText + " --> " + this.dataText;
	}

}
