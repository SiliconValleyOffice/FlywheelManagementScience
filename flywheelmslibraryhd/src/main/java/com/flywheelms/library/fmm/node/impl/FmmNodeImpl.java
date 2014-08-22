/* @(#)FmmNodeImpl.java
** 
** Copyright (C) 2012 by Steven D. Stamps
**
**             Trademarks & Copyrights
** Flywheel Management Science(TM), Flywheel Management Model(TM),
** Flywheel Story Editor(TM) and FlywheelMS(TM) are exclusive trademarks
** of Steven D. Stamps and may only be used freely for the purpose of
** identifying the unforked version of this software.  Subsequent forks
** may not use these trademarks.  All other rights are reserved.
** and FlywheelMS(TM) are exclusive trademarks of Steven D. Stamps
** and may only be used freely for the purpose of identifying the
** unforked version of this software.  Subsequent forks (if any) may
** not use these trademarks.  All other rights are reserved.
**
** DecKanGL (Decorated Kanban Glyph Language) and TribKn (Tribal Knowledge)
** are also exclusive trademarks of Steven D. Stamps.  These may be used
** freely within the unforked FlywheelMS application and documentation.
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

package com.flywheelms.library.fmm.node.impl;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.drawable.Drawable;

import com.flywheelms.library.R;
import com.flywheelms.library.fmm.meta_data.IdNodeMetaData;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmNode;
import com.flywheelms.library.gcg.GcgApplication;
import com.flywheelms.library.gcg.interfaces.GcgSerialization;
import com.flywheelms.library.gcg.widget.date.GcgDateHelper;
import com.flywheelms.library.util.JsonHelper;

public abstract class FmmNodeImpl implements FmmNode, GcgSerialization {

	private NodeId nodeId;
	private Date rowTimestamp = new Date(0);
	private FmmNodeDefinition fmmNodeDictionaryEntry;
	
	public FmmNodeImpl(NodeId aNodeId) {
		this.nodeId = aNodeId;
	}
	
	public FmmNodeImpl(Class<? extends FmmNodeImpl> aClass, JSONObject aJsonObject) {
		this(NodeId.rehydrate(aClass, aJsonObject));
		setRowTimestamp(JsonHelper.getDate(aJsonObject, IdNodeMetaData.column_ROW_TIMESTAMP));
	}
	
	@Override
	public NodeId getNodeId() {
		return this.nodeId;
	}

	@Override
	public String getNodeIdString() {
		return this.nodeId.toString();
	}
	
	@Override
	public String toString() {
		return getNodeIdString();
	}

	@Override
	public String getAbbreviatedNodeIdString() {
		return this.nodeId.getAbbreviatedNodeIdString();
	}

	@Override
	public Date getRowTimestamp() {
		return this.rowTimestamp;
	}

	@Override
	public void setRowTimestamp(Date aTimestamp) {
		this.rowTimestamp = aTimestamp;
	}

	@Override
	public String getNodeTypeName() {
		return this.nodeId.getNodeTypeName();
	}

	@Override
	public String getNodeTypeCode() {
		return this.nodeId.getNodeTypeCode();
	}
	
	@Override
	public String getLabelText() {
		return getFmmNodeDefinition().getLabelText();
	}
	
	public int getLabelTextResourceId() {
		return getFmmNodeDefinition().getLabelTextResourceId();
	}
	
	@Override
	public Drawable getLabelDrawable() {
		return GcgApplication.getContext().getResources().getDrawable(R.drawable.gcg__null_drawable);
	}

	@Override
	public int getLabelDrawableResourceId() {
		return getFmmNodeDefinition().getLabelDrawableResourceId();
	}

	@Override
	public int getDataDrawableResourceId() {
		return R.drawable.gcg__null_drawable;
	}
	
	@Override
	public String getDataText() {
		return "";
	}
	
	@Override
	public Drawable getDataDrawable() {
		return GcgApplication.getContext().getResources().getDrawable(R.drawable.gcg__null_drawable);
	}
	
	@Override
	public FmmNodeDefinition getFmmNodeDefinition() {
		if(this.fmmNodeDictionaryEntry == null) {
			this.fmmNodeDictionaryEntry = FmmNodeDefinition.getEntryForClass(this.getClass());
		}
		return this.fmmNodeDictionaryEntry;
	}
	
	@Override
	public int getNodeEditorActivityRequestCode() {
		return getFmmNodeDefinition().getNodeEditorActivityRequestCode();
	}
	
	@Override
	public int getNodeCreateActivityRequestCode() {
		return getFmmNodeDefinition().getNodeCreateActivityRequestCode();
	}
	
	@Override
	public int getNodePickerActivityRequestCode() {
		return getFmmNodeDefinition().getNodePickerActivityRequestCode();
	}
	
	public static FmmNode getNullValue() {
		return null;
	}

	@Override
	public String getSerialized() {
		return getJsonObject().toString();
	}
	
	@Override
	public JSONObject getJsonObject() {
		JSONObject theJsonObject = new JSONObject();
		try {
			theJsonObject.put(IdNodeMetaData.column_ID, getNodeIdString());
			JsonHelper.putDate(theJsonObject, IdNodeMetaData.column_ROW_TIMESTAMP, getRowTimestamp());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return theJsonObject;
	}

	@Override
	public boolean validateSerializationFormatVersion(String aString) {
		return true;
	}

	@Override
	public boolean isModified(String aSerializedObject) {
		return ! getSerialized().equals(aSerializedObject);
	}

	@Override
	public FmmNodeImpl getClone() {
        return null;
    }
	
	public void replaceWithNew(NodeId aNodeId) {
		this.nodeId = aNodeId;
	}
	
	@Override
	public Date updateRowTimestamp() {
		this.rowTimestamp = GcgDateHelper.getCurrentDateTime();
		return this.rowTimestamp;
	}

}
