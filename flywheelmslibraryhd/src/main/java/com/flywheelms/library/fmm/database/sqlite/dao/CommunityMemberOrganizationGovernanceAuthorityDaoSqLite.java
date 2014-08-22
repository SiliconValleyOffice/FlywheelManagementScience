/* @(#)CommunityMemberOrganizationGovernanceAuthorityDaoSqLite.java
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

package com.flywheelms.library.fmm.database.sqlite.dao;

import java.util.HashMap;

import android.content.ContentValues;
import android.database.Cursor;

import com.flywheelms.library.fmm.meta_data.CommunityMemberOrganizationGovernanceAuthorityMetaData;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.wonky.CommunityMemberOrganizationGovernanceAuthority;

public class CommunityMemberOrganizationGovernanceAuthorityDaoSqLite extends WonkyNodeDaoSqLite<CommunityMemberOrganizationGovernanceAuthority> {

	private static CommunityMemberOrganizationGovernanceAuthorityDaoSqLite singleton;

	public static CommunityMemberOrganizationGovernanceAuthorityDaoSqLite getInstance() {
		if(CommunityMemberOrganizationGovernanceAuthorityDaoSqLite.singleton == null) {
			CommunityMemberOrganizationGovernanceAuthorityDaoSqLite.singleton = new CommunityMemberOrganizationGovernanceAuthorityDaoSqLite();
		}
		return CommunityMemberOrganizationGovernanceAuthorityDaoSqLite.singleton;
	}
	
	@Override
	public FmmNodeDefinition getFmmNodeDefinition() {
		return FmmNodeDefinition.COMMUNITY_MEMBER_ORGANIZATION_GOVERNANCE_AUTHORITY;
	}

	@Override
	protected void buildColumnIndexMap(Cursor aCursor) {
		super.buildColumnIndexMap(aCursor);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CommunityMemberOrganizationGovernanceAuthorityMetaData.column_COMMUNITY_MEMBER);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CommunityMemberOrganizationGovernanceAuthorityMetaData.column_ORGANIZATION);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CommunityMemberOrganizationGovernanceAuthorityMetaData.column_GOVERNANCE_TARGET);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CommunityMemberOrganizationGovernanceAuthorityMetaData.column_CAN_BE_SPONSOR);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CommunityMemberOrganizationGovernanceAuthorityMetaData.column_CAN_BE_CUSTOMER);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CommunityMemberOrganizationGovernanceAuthorityMetaData.column_CAN_BE_FACILITATOR);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CommunityMemberOrganizationGovernanceAuthorityMetaData.column_CAN_BE_ADMINISTRATOR);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CommunityMemberOrganizationGovernanceAuthorityMetaData.column_CAN_SPONSOR_SPONSOR);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CommunityMemberOrganizationGovernanceAuthorityMetaData.column_CAN_SPONSOR_CUSTOMER);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CommunityMemberOrganizationGovernanceAuthorityMetaData.column_CAN_SPONSOR_FACILITATOR);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CommunityMemberOrganizationGovernanceAuthorityMetaData.column_CAN_SPONSOR_ADMINISTRATOR);
	}

	@Override
	public ContentValues buildContentValues(CommunityMemberOrganizationGovernanceAuthority aCommunityMemberOrganizationGovernanceAuthority) {
		ContentValues theContentValues = super.buildContentValues(aCommunityMemberOrganizationGovernanceAuthority);
		theContentValues.put(CommunityMemberOrganizationGovernanceAuthorityMetaData.column_COMMUNITY_MEMBER, aCommunityMemberOrganizationGovernanceAuthority.getCommunityMemberNodeIdString());
		theContentValues.put(CommunityMemberOrganizationGovernanceAuthorityMetaData.column_ORGANIZATION, aCommunityMemberOrganizationGovernanceAuthority.getOrganizationNodeIdString());
		theContentValues.put(CommunityMemberOrganizationGovernanceAuthorityMetaData.column_GOVERNANCE_TARGET, aCommunityMemberOrganizationGovernanceAuthority.getGovernanceTargetName());
		theContentValues.put(CommunityMemberOrganizationGovernanceAuthorityMetaData.column_CAN_BE_SPONSOR, aCommunityMemberOrganizationGovernanceAuthority.isCanBeSponsor());
		theContentValues.put(CommunityMemberOrganizationGovernanceAuthorityMetaData.column_CAN_BE_CUSTOMER, aCommunityMemberOrganizationGovernanceAuthority.isCanBeCustomer());
		theContentValues.put(CommunityMemberOrganizationGovernanceAuthorityMetaData.column_CAN_BE_FACILITATOR, aCommunityMemberOrganizationGovernanceAuthority.isCanBeFacilitator());
		theContentValues.put(CommunityMemberOrganizationGovernanceAuthorityMetaData.column_CAN_BE_ADMINISTRATOR, aCommunityMemberOrganizationGovernanceAuthority.isCanBeAdministrator());
		return theContentValues;
	}

	@Override
	protected void getColumnValues(HashMap<String, Integer> aHashMap, Cursor aCursor, CommunityMemberOrganizationGovernanceAuthority aCommunityMemberOrganizationGovernanceAuthority) {
		super.getColumnValues(aHashMap, aCursor, aCommunityMemberOrganizationGovernanceAuthority);
		aCommunityMemberOrganizationGovernanceAuthority.setCommunityMemberNodeIdString(aCursor.getString(aHashMap.get(CommunityMemberOrganizationGovernanceAuthorityMetaData.column_COMMUNITY_MEMBER)));
		aCommunityMemberOrganizationGovernanceAuthority.setOrganizationNodeIdString(aCursor.getString(aHashMap.get(CommunityMemberOrganizationGovernanceAuthorityMetaData.column_ORGANIZATION)));
		aCommunityMemberOrganizationGovernanceAuthority.setGovernanceTargetName(aCursor.getString(aHashMap.get(CommunityMemberOrganizationGovernanceAuthorityMetaData.column_GOVERNANCE_TARGET)));
		aCommunityMemberOrganizationGovernanceAuthority.setCanBeSponsor(aCursor.getInt(aHashMap.get(CommunityMemberOrganizationGovernanceAuthorityMetaData.column_CAN_BE_SPONSOR)));
		aCommunityMemberOrganizationGovernanceAuthority.setCanBeCustomer(aCursor.getInt(aHashMap.get(CommunityMemberOrganizationGovernanceAuthorityMetaData.column_CAN_BE_CUSTOMER)));
		aCommunityMemberOrganizationGovernanceAuthority.setCanBeFacilitator(aCursor.getInt(aHashMap.get(CommunityMemberOrganizationGovernanceAuthorityMetaData.column_CAN_BE_FACILITATOR)));
		aCommunityMemberOrganizationGovernanceAuthority.setCanBeAdministrator(aCursor.getInt(aHashMap.get(CommunityMemberOrganizationGovernanceAuthorityMetaData.column_CAN_BE_ADMINISTRATOR)));
		aCommunityMemberOrganizationGovernanceAuthority.setCanSponsorSponsor(aCursor.getInt(aHashMap.get(CommunityMemberOrganizationGovernanceAuthorityMetaData.column_CAN_SPONSOR_SPONSOR)));
		aCommunityMemberOrganizationGovernanceAuthority.setCanSponsorCustomer(aCursor.getInt(aHashMap.get(CommunityMemberOrganizationGovernanceAuthorityMetaData.column_CAN_SPONSOR_CUSTOMER)));
		aCommunityMemberOrganizationGovernanceAuthority.setCanSponsorFacilitator(aCursor.getInt(aHashMap.get(CommunityMemberOrganizationGovernanceAuthorityMetaData.column_CAN_SPONSOR_FACILITATOR)));
		aCommunityMemberOrganizationGovernanceAuthority.setCanSponsorAdministrator(aCursor.getInt(aHashMap.get(CommunityMemberOrganizationGovernanceAuthorityMetaData.column_CAN_SPONSOR_ADMINISTRATOR)));
	}

	@Override
	protected CommunityMemberOrganizationGovernanceAuthority getNextObjectFromCursor(Cursor aCursor) {
		CommunityMemberOrganizationGovernanceAuthority theCommunityMemberOrganizationGovernanceAuthority = null;
		theCommunityMemberOrganizationGovernanceAuthority = new CommunityMemberOrganizationGovernanceAuthority(new NodeId(CommunityMemberOrganizationGovernanceAuthority.class));
		getColumnValues(this.columnIndexMap, aCursor, theCommunityMemberOrganizationGovernanceAuthority);
		return theCommunityMemberOrganizationGovernanceAuthority;
	}

}

