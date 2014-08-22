/* @(#)CommunityMemberOrganizationGovernanceAuthority.java
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

package com.flywheelms.library.fmm.node.impl.wonky;

import org.json.JSONException;
import org.json.JSONObject;

import com.flywheelms.library.fmm.meta_data.CommunityMemberOrganizationGovernanceAuthorityMetaData;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.FmmNodeImpl;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.util.JsonHelper;

public class CommunityMemberOrganizationGovernanceAuthority extends FmmNodeImpl {

	public static final String SERIALIZATION_FORMAT_VERSION = "0.1";
	private String communityMemberNodeIdString;
	private String organizationNodeIdString;
	private String governanceTargetName;
	private boolean canBeSponsor;
	private boolean canBeCustomer;
	private boolean canBeFacilitator;
	private boolean canBeAdministrator;
	private boolean canSponsorSponsor;
	private boolean canSponsorCustomer;
	private boolean canSponsorFacilitator;
	private boolean canSponsorAdministrator;

	public CommunityMemberOrganizationGovernanceAuthority(NodeId aNodeId) {
		super(aNodeId);
	}

	public CommunityMemberOrganizationGovernanceAuthority(
			String aCommunityMemberNodeIdString, String anOrganizationNodeIdString, String aGovernanceTargetName ) {
		super(new NodeId(FmmNodeDefinition.COMMUNITY_MEMBER_ORGANIZATION_GOVERNANCE_AUTHORITY.getNodeTypeCode()));
		this.communityMemberNodeIdString = aCommunityMemberNodeIdString;
		this.organizationNodeIdString = anOrganizationNodeIdString;
		this.governanceTargetName = aGovernanceTargetName;
	}
	
	public CommunityMemberOrganizationGovernanceAuthority(JSONObject aJsonObject) {
		super(CommunityMemberOrganizationGovernanceAuthority.class, aJsonObject);
		try {
			validateSerializationFormatVersion(aJsonObject.getString(JsonHelper.key__SERIALIZATION_FORMAT_VERSION));
			setCommunityMemberNodeIdString(aJsonObject.getString(CommunityMemberOrganizationGovernanceAuthorityMetaData.column_COMMUNITY_MEMBER));
			setOrganizationNodeIdString(aJsonObject.getString(CommunityMemberOrganizationGovernanceAuthorityMetaData.column_ORGANIZATION));
			setGovernanceTargetName(aJsonObject.getString(CommunityMemberOrganizationGovernanceAuthorityMetaData.column_GOVERNANCE_TARGET));
			setCanBeSponsor(aJsonObject.getBoolean(CommunityMemberOrganizationGovernanceAuthorityMetaData.column_CAN_BE_SPONSOR));
			setCanBeCustomer(aJsonObject.getBoolean(CommunityMemberOrganizationGovernanceAuthorityMetaData.column_CAN_BE_CUSTOMER));
			setCanBeFacilitator(aJsonObject.getBoolean(CommunityMemberOrganizationGovernanceAuthorityMetaData.column_CAN_BE_FACILITATOR));
			setCanBeAdministrator(aJsonObject.getBoolean(CommunityMemberOrganizationGovernanceAuthorityMetaData.column_CAN_BE_ADMINISTRATOR));
			setCanSponsorSponsor(aJsonObject.getBoolean(CommunityMemberOrganizationGovernanceAuthorityMetaData.column_CAN_SPONSOR_SPONSOR));
			setCanSponsorCustomer(aJsonObject.getBoolean(CommunityMemberOrganizationGovernanceAuthorityMetaData.column_CAN_SPONSOR_CUSTOMER));
			setCanSponsorFacilitator(aJsonObject.getBoolean(CommunityMemberOrganizationGovernanceAuthorityMetaData.column_CAN_SPONSOR_FACILITATOR));
			setCanSponsorAdministrator(aJsonObject.getBoolean(CommunityMemberOrganizationGovernanceAuthorityMetaData.column_CAN_SPONSOR_ADMINISTRATOR));
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
			theJsonObject.put(CommunityMemberOrganizationGovernanceAuthorityMetaData.column_COMMUNITY_MEMBER, getCommunityMemberNodeIdString());
			theJsonObject.put(CommunityMemberOrganizationGovernanceAuthorityMetaData.column_ORGANIZATION, getOrganizationNodeIdString());
			theJsonObject.put(CommunityMemberOrganizationGovernanceAuthorityMetaData.column_GOVERNANCE_TARGET, getGovernanceTargetName());
			theJsonObject.put(CommunityMemberOrganizationGovernanceAuthorityMetaData.column_CAN_BE_SPONSOR, isCanBeSponsor());
			theJsonObject.put(CommunityMemberOrganizationGovernanceAuthorityMetaData.column_CAN_BE_CUSTOMER, isCanBeCustomer());
			theJsonObject.put(CommunityMemberOrganizationGovernanceAuthorityMetaData.column_CAN_BE_FACILITATOR, isCanBeFacilitator());
			theJsonObject.put(CommunityMemberOrganizationGovernanceAuthorityMetaData.column_CAN_BE_ADMINISTRATOR, isCanBeAdministrator());
			theJsonObject.put(CommunityMemberOrganizationGovernanceAuthorityMetaData.column_CAN_SPONSOR_SPONSOR, isCanSponsorSponsor());
			theJsonObject.put(CommunityMemberOrganizationGovernanceAuthorityMetaData.column_CAN_SPONSOR_CUSTOMER, isCanSponsorCustomer());
			theJsonObject.put(CommunityMemberOrganizationGovernanceAuthorityMetaData.column_CAN_SPONSOR_FACILITATOR, isCanSponsorFacilitator());
			theJsonObject.put(CommunityMemberOrganizationGovernanceAuthorityMetaData.column_CAN_SPONSOR_ADMINISTRATOR, isCanSponsorAdministrator());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return theJsonObject;
	}

	public boolean isCanBeSponsor() {
		return this.canBeSponsor;
	}

	public void setCanBeSponsor(boolean aBoolean) {
		this.canBeSponsor = aBoolean;
	}

	public void setCanBeSponsor(int bCanBeSponsor) {
		this.canBeSponsor = bCanBeSponsor > 0;
	}

	public boolean isCanBeCustomer() {
		return this.canBeCustomer;
	}

	public void setCanBeCustomer(boolean aBoolean) {
		this.canBeCustomer = aBoolean;
	}

	public void setCanBeCustomer(int bCanBeCustomer) {
		this.canBeCustomer = bCanBeCustomer > 0;
	}

	public boolean isCanBeFacilitator() {
		return this.canBeFacilitator;
	}

	public void setCanBeFacilitator(boolean aBoolean) {
		this.canBeFacilitator = aBoolean;
	}

	public void setCanBeFacilitator(int bCanBeFacilitator) {
		this.canBeFacilitator = bCanBeFacilitator > 0;
	}

	public boolean isCanBeAdministrator() {
		return this.canBeAdministrator;
	}

	public void setCanBeAdministrator(boolean aBoolean) {
		this.canBeAdministrator = aBoolean;
	}

	public void setCanBeAdministrator(int bCanBeAdministrator) {
		this.canBeAdministrator = bCanBeAdministrator > 0;
	}

	public String getCommunityMemberNodeIdString() {
		return this.communityMemberNodeIdString;
	}
	
	public void setCommunityMemberNodeIdString(String aNodeIdString) {
		this.communityMemberNodeIdString = aNodeIdString;
	}

	public String getOrganizationNodeIdString() {
		return this.organizationNodeIdString;
	}
	
	public void setOrganizationNodeIdString(String aNodeIdString) {
		this.organizationNodeIdString = aNodeIdString;
	}

	public String getGovernanceTargetName() {
		return this.governanceTargetName;
	}
	
	public void setGovernanceTargetName(String aGovernanceTargetName) {
		this.governanceTargetName = aGovernanceTargetName;
	}

	public boolean isCanSponsorSponsor() {
		return this.canSponsorSponsor;
	}

	public void setCanSponsorSponsor(boolean aBoolean) {
		this.canSponsorSponsor = aBoolean;
	}

	public void setCanSponsorSponsor(int canSponsorSponsor) {
		this.canSponsorSponsor = canSponsorSponsor > 0;
	}

	public boolean isCanSponsorCustomer() {
		return this.canSponsorCustomer;
	}

	public void setCanSponsorCustomer(boolean aBoolean) {
		this.canSponsorCustomer = aBoolean;
	}

	public void setCanSponsorCustomer(int canSponsorCustomer) {
		this.canSponsorCustomer = canSponsorCustomer > 0;
	}

	public boolean isCanSponsorFacilitator() {
		return this.canSponsorFacilitator;
	}

	public void setCanSponsorFacilitator(boolean aBoolean) {
		this.canSponsorFacilitator = aBoolean;
	}

	public void setCanSponsorFacilitator(int canSponsorFacilitator) {
		this.canSponsorFacilitator = canSponsorFacilitator > 0;
	}

	public boolean isCanSponsorAdministrator() {
		return this.canSponsorAdministrator;
	}

	public void setCanSponsorAdministrator(boolean aBoolean) {
		this.canSponsorAdministrator = aBoolean;
	}

	public void setCanSponsorAdministrator(int canSponsorAdministrator) {
		this.canSponsorAdministrator = canSponsorAdministrator > 0;
	}
	
}
