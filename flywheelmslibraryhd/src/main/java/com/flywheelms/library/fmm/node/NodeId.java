/* @(#)NodeId.java
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

package com.flywheelms.library.fmm.node;

import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;

import com.flywheelms.library.fmm.meta_data.IdNodeMetaData;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.link.FmmLinkNodeImpl;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmNode;
import com.flywheelms.library.fms.helper.FmsActivityHelper;

public class NodeId {

	public static final String NULL_UUID = "null_value";
	private static final String TEMPORARY_UUID = "temporary_uuid";
	private String nodeIdString;
	private String nodeTypeCode;
	private String nodeTypeName;
//	private static final Hashtable<Class<? extends FmmNode>, NodeId> nullNodeIdTable = new Hashtable<Class<? extends FmmNode>, NodeId>();
	
	public NodeId(String aNodeTypeCode) {
		this.nodeTypeCode = aNodeTypeCode;
		this.nodeIdString = NodeId.generateNodeId(this.nodeTypeCode);
	}
	
	public NodeId(Class<? extends FmmNode> anFmmNodeClass) {
		this.nodeTypeCode = getNodeTypeCodeForClass(anFmmNodeClass);
		this.nodeTypeName = getNodeTypeNameForClass(anFmmNodeClass);
		this.nodeIdString = NodeId.generateNodeId(this.nodeTypeCode);
	}
	
	public NodeId(Class<? extends FmmNode> aClass, String aParentNodeIdString, @SuppressWarnings("unused") boolean anUnusedSignatureElement) {
		this.nodeTypeCode = getNodeTypeCodeForClass(aClass);
		this.nodeTypeName = getNodeTypeNameForClass(aClass);
		this.nodeIdString = NodeId.generateNodeIdFromParent(this.nodeTypeCode, aParentNodeIdString);
	}
	
	// only for Node Fragments
	public NodeId(String aNodeFragTypeCode, String aParentNodeIdString) {
		this.nodeTypeCode = aNodeFragTypeCode;
		this.nodeIdString  = getNodeFragIdString(aNodeFragTypeCode, aParentNodeIdString);
	}

	private static String getNodeFragIdString(String aNodeFragTypeCode, String aParentNodeIdString) {
		return aParentNodeIdString.replaceFirst(getNodeTypeCodeFromNodeIdString(aParentNodeIdString), aNodeFragTypeCode );
	}
	
	// only for enums
	public NodeId(FmmNodeDefinition anFmmNodeDefinition, String anEnumValue) {
		this.nodeTypeCode = anFmmNodeDefinition.getNodeTypeCode();
		this.nodeTypeName = anFmmNodeDefinition.getNodeTypeName();
		this.nodeIdString  = generateEnumNodeId(this.nodeTypeCode, anEnumValue);
	}
	
	// rehydrate
	private NodeId(String aNodeTypeCode, String aNodeTypeName, String anExistingNodeIdString) {
		this.nodeTypeCode = aNodeTypeCode;
		this.nodeTypeName = aNodeTypeName;
		this.nodeIdString  = anExistingNodeIdString;
	}
	
	// rehydrate
	public NodeId(Class<? extends FmmNode> anFmmNodeClass, String anExistingNodeIdString) {
		this.nodeTypeCode = getNodeTypeCodeForClass(anFmmNodeClass);
		this.nodeTypeName = getNodeTypeNameForClass(anFmmNodeClass);
		this.nodeIdString = anExistingNodeIdString;
	}
	
	// rehydrate with only NodeIdString
	public static NodeId rehydrate(String anExistingNodeIdString) {
		return new NodeId(
				getNodeTypeCodeFromNodeIdString(anExistingNodeIdString),
				getNodeTypeNameFromNodeIdString(anExistingNodeIdString),
				anExistingNodeIdString );
	}
	
	public NodeId rehydrate(String aNodeTypeCode, String aNodeTypeName, String anExistingNodeIdString) {
		if(anExistingNodeIdString.contains(NULL_UUID)) {
//			return FmmNodeDictionary.getEntryForNodeTypeCode(aNodeTypeCode).getNullValue().getNodeId();
			return new NodeId(aNodeTypeCode, aNodeTypeName, anExistingNodeIdString);
		}
		return new NodeId(aNodeTypeCode, aNodeTypeName, anExistingNodeIdString);
	}
	
	public static NodeId hydrate(Class<? extends FmmNode> anFmmNodeClass, String anExistingNodeIdString) {
		if(anExistingNodeIdString.contains(NULL_UUID)) {
//			return FmmNodeDictionary.getEntryForClass(anFmmNodeClass).getNullValue().getNodeId();
			return new NodeId(anFmmNodeClass, anExistingNodeIdString);
		}
		return new NodeId(anFmmNodeClass, anExistingNodeIdString);
	}

	public static NodeId rehydrate(Class<? extends FmmNode> anFmmNodeClass, JSONObject aJsonObject) {
		String theNodeIdString = null;
		try {
			theNodeIdString = aJsonObject.getString(IdNodeMetaData.column_ID);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new NodeId(
			getNodeTypeCodeForClass(anFmmNodeClass),
			getNodeTypeNameForClass(anFmmNodeClass),
			theNodeIdString );
	}
	
	////////////////////////
	
	private static String generateNodeId(String aNodeTypeCode) {
		StringBuilder theStringBuilder = new StringBuilder(aNodeTypeCode + "-");
		theStringBuilder.append(UUID.randomUUID());
		return theStringBuilder.toString();
	}
	
	public static String generateNodeIdFromParent(Class<? extends FmmNode> aNodeClass, String aParentNodeIdString) {
		return generateNodeIdFromParent(FmmNodeDefinition.getEntryForClass(aNodeClass).getNodeTypeCode(), aParentNodeIdString);
	}
	
	public static String generateNodeIdFromParent(String aNodeTypeCode, String aParentNodeIdString) {
		StringBuilder theStringBuilder = new StringBuilder(aNodeTypeCode + "-");
		theStringBuilder.append(aParentNodeIdString.substring(4));
		return theStringBuilder.toString();
	}
	
//	private static String generateNullNodeId(String aNodeTypeCode) {
//		StringBuilder theStringBuilder = new StringBuilder(aNodeTypeCode + "-");
//		theStringBuilder.append(NULL_UUID);
//		return theStringBuilder.toString();
//	}
	
	public static boolean isNullNodeId(String aNodeIdString) {
		return aNodeIdString.contains(NULL_UUID);
	}
	
	public static boolean isNullNodeId(NodeId aNodeId) {
		return aNodeId.getNodeIdString().contains(NULL_UUID);
	}
	
	public static String generateEnumNodeId(String aNodeTypeCode, String anEnumName) {
		return aNodeTypeCode + "-" + anEnumName;
	}
	
	public static String getNodeTypeCodeFromNodeIdString(String aNodeIdString) {
		return aNodeIdString.substring(0, 3);
	}
	
	public static String getNodeTypeNameFromNodeIdString(String aNodeIdString) {
		return FmmNodeDefinition.getEntryForNodeTypeCode(getNodeTypeCodeFromNodeIdString(aNodeIdString)).getNodeTypeName();
	}
	
	public String getNodeTypeName() {
		if(this.nodeTypeName == null) {
			this.nodeTypeName = FmmNodeDefinition.getEntryForNodeTypeCode(this.nodeTypeCode).getNodeTypeName();
		}
		return this.nodeTypeName;
	}
	
	public static String getFmmNodeClassNameFromNodeId(String aNodeId) {
		return (FmmNodeDefinition.getEntryForNodeTypeCode(aNodeId.substring(0, 2))).getName();
	}
	
	public static String getNodeTypeCodeForClass(Class<? extends FmmNode> aNodeClass) {
		return FmmNodeDefinition.getEntryForClass(aNodeClass).getNodeTypeCode();
	}
	
	public static String getNodeTypeNameForClass(Class<? extends FmmNode> aNodeClass) {
		return FmmNodeDefinition.getEntryForClass(aNodeClass).getNodeTypeName();
	}
	
	public static String getNodeTypeCodeForName(String aNodeName) {
		return FmmNodeDefinition.getEntryForNodeClassName(aNodeName).getNodeTypeCode();
	}

	public static String buildLinkNodeIdString(
			String aNodeTypeCode,
			String aParentNodeId,
			String aChildNodeId) {
		return aNodeTypeCode + "," + aParentNodeId + "," + aChildNodeId;
	}

	public static String buildLinkNodeId(
			Class<? extends FmmLinkNodeImpl> aNodeClass,
			String aParentNodeId,
			String aChildNodeId) {
		return buildLinkNodeIdString(NodeId.getNodeTypeCodeForClass(aNodeClass), aParentNodeId, aChildNodeId);
	}

	public String getNodeIdString() {
		return this.nodeIdString;
	}

	public String getAbbreviatedNodeIdString() {
		return getAbbreviatedNodeIdString(this.nodeIdString);
	}

	public static String getAbbreviatedNodeIdString(String aNodeIdString) {
		return aNodeIdString.substring(0, 4) + aNodeIdString.substring(aNodeIdString.length() - 5);
	}
	
	public void setNodeIdString(String aNodeIdString) {
		this.nodeIdString = aNodeIdString;
	}

	public String getNodeTypeCode() {
		return this.nodeTypeCode;
	}

	public void setNodeTypeCode(String aNewNodeTypeCode) {
		this.nodeTypeCode = aNewNodeTypeCode;
		this.nodeIdString = this.nodeIdString.replaceFirst(getNodeTypeCode(), aNewNodeTypeCode);
	}
	
	@Override
	public String toString() {
		return getNodeIdString();
	}

//	public static NodeId getNull(Class<? extends FmmNode> anFmmNodeClass) {
//		NodeId theNodeId = nullNodeIdTable.get(anFmmNodeClass);
//		if(theNodeId == null) {
//			String theNodeTypeCode = getNodeTypeCodeForClass(anFmmNodeClass);
//			theNodeId = new NodeId(anFmmNodeClass, generateNullNodeId(theNodeTypeCode));
//			nullNodeIdTable.put(anFmmNodeClass, theNodeId);
//		}
//		return theNodeId;
//	}

	public void replaceTemporaryNodeId(String aNodeIdString) {
		// TODO - add more checking/safety/security    SDS
		setNodeIdString(aNodeIdString);
	}

	public static String getTemporaryNodeIdString(Class<? extends FmmNode> aClass) {
		String theNodeTypeCode = getNodeTypeCodeForClass(aClass);
		StringBuilder theStringBuilder = new StringBuilder(theNodeTypeCode + "-");
		theStringBuilder.append(NodeId.TEMPORARY_UUID);
		return theStringBuilder.toString();
	}

	public static String getNodeIdString(Intent anIntent) {
		if(! anIntent.hasExtra(FmsActivityHelper.bundle_key__FMM_NODE__ID_STRING)) {
			return "";
		}
		String theNodeIdString = anIntent.getExtras().getString(FmsActivityHelper.bundle_key__FMM_NODE__ID_STRING, "");
		return theNodeIdString;
	}
	
}
