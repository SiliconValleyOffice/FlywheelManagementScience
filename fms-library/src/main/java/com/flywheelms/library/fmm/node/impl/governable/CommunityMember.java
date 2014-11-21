/* @(#)CommunityMember.java
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
import android.graphics.drawable.Drawable;

import com.flywheelms.gcongui.gcg.GcgApplication;
import com.flywheelms.gcongui.gcg.activity.GcgActivity;
import com.flywheelms.library.R;
import com.flywheelms.library.fmm.meta_data.CommunityMemberMetaData;
import com.flywheelms.library.fmm.node.FmmHeadlineNodeShallow;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.enumerator.CommunityMemberStatus;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.headline.FmmHeadlineNodeImpl;
import com.flywheelms.library.fmm.node.interfaces.FmmSupportingNode;
import com.flywheelms.library.fms.activity.FmsActivity;
import com.flywheelms.library.fms.helper.FmsActivityHelper;
import com.flywheelms.library.util.JsonHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CommunityMember extends FmmGovernableNodeImpl implements FmmSupportingNode, Comparable<CommunityMember> {

	private static final long serialVersionUID = -4151339832738764317L;
	public static final String SERIALIZATION_FORMAT_VERSION = "0.1";
	private static CommunityMember NULL_COMMUNITY_MEMBER;
	private static String null_COMMUNITY_MEMBER_NODE_ID_STRING = "CMR-null_value_4_CommunityMember";

	public static CommunityMember getNullValue() {
		if(CommunityMember.NULL_COMMUNITY_MEMBER == null) {
			CommunityMember.NULL_COMMUNITY_MEMBER = FmsActivity.getActiveDatabaseMediator().retrieveCommunityMember(null_COMMUNITY_MEMBER_NODE_ID_STRING);
			if(CommunityMember.NULL_COMMUNITY_MEMBER == null) {
				CommunityMember.NULL_COMMUNITY_MEMBER = new CommunityMember(null_COMMUNITY_MEMBER_NODE_ID_STRING);
				CommunityMember.NULL_COMMUNITY_MEMBER.setFamilyName("");
				CommunityMember.NULL_COMMUNITY_MEMBER.setGivenName("");
				CommunityMember.NULL_COMMUNITY_MEMBER.setHeadline("");
				FmsActivity.getActiveDatabaseMediator().insertCommunityMember(CommunityMember.NULL_COMMUNITY_MEMBER, true);
			}
		}
		return CommunityMember.NULL_COMMUNITY_MEMBER;
	}
	
	private String communityMemberStatusCode = CommunityMemberStatus.ACTIVE.getName();
	private CommunityMemberStatus communityMemberStatus;
	private float aggregateTeamAllocation = 0;
	private String authenticationUid;
	private String givenName;
	private String familyName;
	private String nickName;
	private String emailAddress;
	private String cellPhone;
	private String workPhone;

	public CommunityMember(NodeId aNodeId) {
		super(aNodeId);
	}
	
	public CommunityMember(String anExistingNodeIdString) {
		super(NodeId.hydrate(
				CommunityMember.class,
				anExistingNodeIdString ));
	}
	
	public CommunityMember(JSONObject aJsonObject) {
		super(CommunityMember.class, aJsonObject);
		try {
			validateSerializationFormatVersion(aJsonObject.getString(JsonHelper.key__SERIALIZATION_FORMAT_VERSION));
			setCommunityMemberStatusCode(aJsonObject.getString(CommunityMemberMetaData.column_COMMUNITY_MEMBER_STATUS));
			setAggregateTeamAllocation(aJsonObject.getLong(CommunityMemberMetaData.column_AGGREGATE_TEAM_ALLOCATION));
			setAuthenticationUid(aJsonObject.getString(CommunityMemberMetaData.column_AUTHENTICATION_UID));
			setGivenName(aJsonObject.getString(CommunityMemberMetaData.column_GIVEN_NAME));
			setFamilyName(aJsonObject.getString(CommunityMemberMetaData.column_FAMILY_NAME));
			setNickName(aJsonObject.getString(CommunityMemberMetaData.column_NICK_NAME));
			setEmailAddress(aJsonObject.getString(CommunityMemberMetaData.column_EMAIL_ADDRESS));
			setCellPhone(aJsonObject.getString(CommunityMemberMetaData.column_CELL_PHONE));
			setWorkPhone(aJsonObject.getString(CommunityMemberMetaData.column_WORK_PHONE));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isNullValue() {
		return this.getNodeId().getNodeIdString().contains(NodeId.NULL_UUID);
	}
	
	public static CommunityMember getCommunityMember(Intent anIntent) {
		return FmsActivity.getActiveDatabaseMediator().retrieveCommunityMember(NodeId.getNodeIdString(anIntent));
	}

	public String getCommunityMemberStatusCode() {
		return this.communityMemberStatusCode;
	}
	
	public void setCommunityMemberStatusCode(String aCommunityMemberStatusCode) {
		this.communityMemberStatusCode = aCommunityMemberStatusCode;
		this.communityMemberStatus = CommunityMemberStatus.getObjectForCode(aCommunityMemberStatusCode);
	}

	public CommunityMemberStatus getCommunityMemberStatus() {
		return this.communityMemberStatus;
	}

	public void setCommunityMemberStatus(CommunityMemberStatus aCommunityMemberStatus) {
		this.communityMemberStatus = aCommunityMemberStatus;
		this.communityMemberStatusCode = aCommunityMemberStatus.getCommunityMemberStatusCode();
	}

	public float getAggregateTeamAllocation() {
		return this.aggregateTeamAllocation;
	}

	public void setAggregateTeamAllocation(float anAggregateTeamAllocation) {
		this.aggregateTeamAllocation = anAggregateTeamAllocation;
	}

	public String getAuthenticationUid() {
		return this.authenticationUid;
	}

	public void setAuthenticationUid(String anAuthenticationUid) {
		this.authenticationUid = anAuthenticationUid;
	}

	public String getGivenName() {
		return this.givenName;
	}

	public void setGivenName(String aGivenName) {
		this.givenName = aGivenName;
	}

	public String getFamilyName() {
		return this.familyName;
	}

	public void setFamilyName(String aFamilyName) {
		this.familyName = aFamilyName;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String aNickName) {
		this.nickName = aNickName;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String anEmailAddress) {
		this.emailAddress = anEmailAddress;
	}

	public String getCellPhone() {
		return this.cellPhone;
	}

	public void setCellPhone(String aCellPhone) {
		this.cellPhone = aCellPhone;
	}

	public String getWorkPhone() {
		return this.workPhone;
	}

	public void setWorkPhone(String aWorkPhone) {
		this.workPhone = aWorkPhone;
	}

	@Override
	protected void initializeNodeCompletionSummaryMap() { /*  N/A  */ }

	@Override
	public String toString() {
		return getGivenName() + " " + getFamilyName();
	}

	public String getName() {
		return toString();
	}
	
	@Override
	public String getHeadline() {
		return toString();
	}
	
	public static void startNodePickerActivity(GcgActivity anActivity, ArrayList<String> aNodeIdExclusionList, String aWhereClause, String aListActionLabel) {
		FmsActivityHelper.startHeadlineNodePickerActivity(anActivity, FmmNodeDefinition.COMMUNITY_MEMBER, aNodeIdExclusionList, aWhereClause, aListActionLabel);
	}
	
	public static void startNodeEditorActivity(GcgActivity anActivity, String aNodeListParentNodeId, ArrayList<FmmHeadlineNodeShallow> aHeadlineNodeShallowList, String anInitialNodeIdToDisplay) {
		FmmHeadlineNodeImpl.startNodeEditorActivity(
				anActivity,
				aNodeListParentNodeId,
				aHeadlineNodeShallowList,
				anInitialNodeIdToDisplay,
				FmmNodeDefinition.COMMUNITY_MEMBER );
	}
	
	public static void startNodeCreationDialog(@SuppressWarnings("unused") GcgActivity anActivity) {
		// TODO
	}

	@Override
	public JSONObject getJsonObject() {
		JSONObject theJsonObject = super.getJsonObject();
		try {
			theJsonObject.put(JsonHelper.key__SERIALIZATION_FORMAT_VERSION, SERIALIZATION_FORMAT_VERSION);
			theJsonObject.put(CommunityMemberMetaData.column_COMMUNITY_MEMBER_STATUS, getCommunityMemberStatusCode());
			theJsonObject.put(CommunityMemberMetaData.column_AGGREGATE_TEAM_ALLOCATION, getAggregateTeamAllocation());
			theJsonObject.put(CommunityMemberMetaData.column_AUTHENTICATION_UID, getAuthenticationUid());
			theJsonObject.put(CommunityMemberMetaData.column_GIVEN_NAME, getGivenName());
			theJsonObject.put(CommunityMemberMetaData.column_FAMILY_NAME, getFamilyName());
			theJsonObject.put(CommunityMemberMetaData.column_NICK_NAME, getNickName());
			theJsonObject.put(CommunityMemberMetaData.column_EMAIL_ADDRESS, getEmailAddress());
			theJsonObject.put(CommunityMemberMetaData.column_CELL_PHONE, getCellPhone());
			theJsonObject.put(CommunityMemberMetaData.column_WORK_PHONE, getWorkPhone());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return theJsonObject;
	}

	@Override
	public CommunityMember getClone() {
		return new CommunityMember(getJsonObject());
	}

	public boolean isMemberOfOrganization(@SuppressWarnings("unused") String nodeIdString) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int compareTo(CommunityMember aCommunityMember) {
		int theReturnValue;
		if(this.getNodeIdString().equals(aCommunityMember.getNodeIdString())) {
			theReturnValue = 0;
		} else if((theReturnValue = this.getFamilyName().compareTo(aCommunityMember.getFamilyName())) == 0) {
			if((theReturnValue = this.getGivenName().compareTo(aCommunityMember.getGivenName())) == 0) {
				theReturnValue = this.getNickName().compareTo(aCommunityMember.getNickName());
			}
		}
		return theReturnValue;
	}
	
	///////   GUIABLE  ////////////////
	
	@Override
	public String getLabelText() {
		return "Community Member";
	}
	
	@Override
	public Drawable getLabelDrawable() {
		return GcgApplication.getContext().getResources().getDrawable(R.drawable.community_member__16);
	}
	
	@Override
	public String getDataText() {
		return getName();
	}
	
	@Override
	public Drawable getDataDrawable() {
		return getCommunityMemberStatus().getDataDrawable();
	}

}
