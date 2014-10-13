/* @(#)FmmNodeFragImpl.java
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

package com.flywheelms.library.fmm.node.impl.nodefrag;

import com.flywheelms.library.fmm.meta_data.NodeFragMetaData;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.FmmNodeImpl;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.interfaces.FmmNodeFrag;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class FmmNodeFragImpl extends FmmNodeImpl implements FmmNodeFrag {
	
	protected String parentNodeIdString;
	protected String parentNodeTypeCode;

	public FmmNodeFragImpl(Class<? extends FmmNodeFragImpl> aNodeFragClass, String aParentNodeIdString) {
		super(new NodeId(NodeId.getNodeTypeCodeForClass(aNodeFragClass)));
		this.parentNodeIdString = aParentNodeIdString;
		this.parentNodeTypeCode = NodeId.getNodeTypeCodeFromNodeIdString(this.parentNodeIdString);
	}

	// rehydrate
	public FmmNodeFragImpl(NodeId anExistingNodeFragNodeId, String aParentNodeIdString) {
		super(anExistingNodeFragNodeId);
		this.parentNodeIdString = aParentNodeIdString;
		this.parentNodeTypeCode = NodeId.getNodeTypeCodeFromNodeIdString(this.parentNodeIdString);
	}
	
	public FmmNodeFragImpl(Class<? extends FmmNodeFragImpl> aClass, JSONObject aJsonObject) {
		super(aClass, aJsonObject);
		try {
			setParentNodeIdString(aJsonObject.getString(NodeFragMetaData.column_PARENT_ID));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setParentNodeIdString(String aNodeIdString) {
		this.parentNodeIdString = aNodeIdString;
		this.parentNodeTypeCode = NodeId.getNodeTypeCodeFromNodeIdString(this.parentNodeIdString);
	}

	@Override
	public String getParentNodeIdString() {
		return this.parentNodeIdString;
	}
	
	@Override
	public String getParentNodeTypeName() {
		return NodeId.getNodeTypeNameFromNodeIdString(this.parentNodeIdString);
	}
	
	@Override
	public String getParentNodeTypeCode() {
		return this.parentNodeTypeCode;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Class<? extends FmmNodeFragImpl> getParentNodeClass() {
		return (Class<? extends FmmNodeFragImpl>)
				(FmmNodeDefinition.getEntryForNodeTypeCode(getParentNodeTypeCode()).getNodeClass());
	}
	
	protected static String getNodeFragTypeCode(Class<? extends FmmNodeFragImpl> aNodeFragClass) {
		return FmmNodeDefinition.getEntryForClass(aNodeFragClass).getTypeCodeForNodeId();
	}
	
	@Override
	public JSONObject getJsonObject() {
		JSONObject theJsonObject = super.getJsonObject();
		try {
			theJsonObject.put(NodeFragMetaData.column_PARENT_ID, getParentNodeIdString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return theJsonObject;
	}
	
}
