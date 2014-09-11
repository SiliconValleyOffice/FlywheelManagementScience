/* @(#)Organization.java
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

package com.flywheelms.library.fmm.node.impl.governable;

import android.content.Intent;

import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.meta_data.FmsOrganizationMetaData;
import com.flywheelms.library.fmm.node.FmmHeadlineNodeShallow;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.headline.FmmHeadlineNodeImpl;
import com.flywheelms.library.fms.helper.FmsActivityHelper;
import com.flywheelms.library.gcg.GcgActivity;
import com.flywheelms.library.util.JsonHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FmsOrganization extends FmmGovernableNodeImpl {

	private static final long serialVersionUID = -6457964942340301997L;

	public static final String SERIALIZATION_FORMAT_VERSION = "0.1";

	private String authenticationUrlString;
	private String authenticationType;

	public FmsOrganization(NodeId aNodeId) {
		super(aNodeId);
	}

	public FmsOrganization(String aHeadline, @SuppressWarnings("unused") boolean aSignatureArg) {
		super(new NodeId(FmsOrganization.class));
		this.headline = aHeadline;
	}
	
	public FmsOrganization(String aExistingNodeIdString) {
		super(NodeId.hydrate(FmsOrganization.class, aExistingNodeIdString ));
	}
	
	public FmsOrganization(JSONObject aJsonObject) {
		super(FmsOrganization.class, aJsonObject);
		try {
			validateSerializationFormatVersion(aJsonObject.getString(JsonHelper.key__SERIALIZATION_FORMAT_VERSION));
			setAuthenticationUrl(aJsonObject.getString(FmsOrganizationMetaData.column_AUTHENTICATION_URL));
			setAuthenticationType(aJsonObject.getString(FmsOrganizationMetaData.column_AUTHENTICATION_TYPE));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public JSONObject getJsonObject() {
		JSONObject theJsonObject = super.getJsonObject();
		try {
			theJsonObject.put(JsonHelper.key__SERIALIZATION_FORMAT_VERSION, SERIALIZATION_FORMAT_VERSION);
			theJsonObject.put(FmsOrganizationMetaData.column_AUTHENTICATION_URL, getAuthenticationUrlString());
			theJsonObject.put(FmsOrganizationMetaData.column_AUTHENTICATION_TYPE, getAuthenticationType());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return theJsonObject;
	}

	@Override
	public FmsOrganization getClone() {
		return new FmsOrganization(getJsonObject());
	}
	
	public String getAuthenticationUrlString() {
		return this.authenticationUrlString;
	}

	public void setAuthenticationUrl(String string) {
		this.authenticationUrlString = string;
	}
	
	public String getAuthenticationType() {
		return this.authenticationType;
	}

	public void setAuthenticationType(String string) {
		this.authenticationType = string;
	}

	public String getName() {
		return this.headline;
	}
	
	public boolean ownsThisFmm() {
		return FmmDatabaseMediator.getActiveMediator().ownsThisFmm(this);
	}
	
	public static void startNodeEditorActivity(GcgActivity anActivity, String aNodeListParentNodeId, ArrayList<FmmHeadlineNodeShallow> aHeadlineNodeShallowList, String anInitialNodeIdToDisplay) {
		FmmHeadlineNodeImpl.startNodeEditorActivity(
				anActivity,
				aNodeListParentNodeId,
				aHeadlineNodeShallowList,
				anInitialNodeIdToDisplay,
				FmmNodeDefinition.FMS_ORGANIZATION );
	}
	
	public static void startNodePickerActivity(GcgActivity anActivity, ArrayList<String> aNodeIdExclusionList, String aWhereClause, String aListActionLabel) {
		FmsActivityHelper.startHeadlineNodePickerActivity(anActivity, FmmNodeDefinition.FMS_ORGANIZATION, aNodeIdExclusionList, aWhereClause, aListActionLabel);
	}
	
	public static FmsOrganization getFmmConfiguration(Intent anIntent) {
		return FmmDatabaseMediator.getActiveMediator().getFmsOrganization(NodeId.getNodeIdString(anIntent));
	}

}
