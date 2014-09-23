/* @(#)OrganizationCommunityMemberDaoSqLite.java
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

import android.content.ContentValues;
import android.database.Cursor;

import com.flywheelms.gcongui.gcg.widget.date.GcgDateHelper;
import com.flywheelms.library.fmm.meta_data.OrganizationCommunityMemberMetaData;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.enumerator.OrganizationParticipation;
import com.flywheelms.library.fmm.node.impl.enumerator.TeamMemberStatus;
import com.flywheelms.library.fmm.node.impl.link.OrganizationCommunityMember;

import java.util.HashMap;

public class OrganizationCommunityMemberDaoSqLite extends LinkNodeDaoSqLite<OrganizationCommunityMember> {

	private static OrganizationCommunityMemberDaoSqLite singleton;

	public static OrganizationCommunityMemberDaoSqLite getInstance() {
		if(OrganizationCommunityMemberDaoSqLite.singleton == null) {
			OrganizationCommunityMemberDaoSqLite.singleton = new OrganizationCommunityMemberDaoSqLite();
		}
		return OrganizationCommunityMemberDaoSqLite.singleton;
	}
	
	@Override
	public FmmNodeDefinition getFmmNodeDefinition() {
		return FmmNodeDefinition.ORGANIZATION_COMMUNITY_MEMBER;
	}
	
	@Override
	protected String getParentColumnName() {
		return OrganizationCommunityMemberMetaData.column_ORGANIZATION_ID;
	}
	
	@Override
	protected String getChildColumnName() {
		return OrganizationCommunityMemberMetaData.column_COMMUNITY_MEMBER_ID;
	}

	@Override
	protected void buildColumnIndexMap(Cursor aCursor) {
		super.buildColumnIndexMap(aCursor);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, OrganizationCommunityMemberMetaData.column_ORGANIZATION_PARTICIPATION);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, OrganizationCommunityMemberMetaData.column_TEAM_MEMBER_STATUS);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, OrganizationCommunityMemberMetaData.column_PROPOSED_BY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, OrganizationCommunityMemberMetaData.column_PROPOSED_TIMESTAMP);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, OrganizationCommunityMemberMetaData.column_CONFIRMED_BY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, OrganizationCommunityMemberMetaData.column_CONFIRMED_TIMESTAMP);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, OrganizationCommunityMemberMetaData.column_AUTHENTICATION_UID);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, OrganizationCommunityMemberMetaData.column_IS_SUPER_USER);
	}

	@Override
	protected void getColumnValues(HashMap<String, Integer> aHashMap, Cursor aCursor, OrganizationCommunityMember anOrganizationCommunityMember) {
		super.getColumnValues(aHashMap, aCursor, anOrganizationCommunityMember);
		anOrganizationCommunityMember.setOrganizationParticipation(OrganizationParticipation.getObjectForName(
				aCursor.getString(aHashMap.get(OrganizationCommunityMemberMetaData.column_ORGANIZATION_PARTICIPATION)) ));
		anOrganizationCommunityMember.setTeamMemberStatus(TeamMemberStatus.getObjectForName(
				aCursor.getString(aHashMap.get(OrganizationCommunityMemberMetaData.column_TEAM_MEMBER_STATUS)) ));
		anOrganizationCommunityMember.setProposedByNodeIdString(aCursor.getString(aHashMap.get(OrganizationCommunityMemberMetaData.column_PROPOSED_BY)));
		anOrganizationCommunityMember.setProposedTimestamp(GcgDateHelper.getDateFromFormattedUtcLong(aCursor.getLong(aHashMap.get(OrganizationCommunityMemberMetaData.column_PROPOSED_TIMESTAMP))));
		anOrganizationCommunityMember.setConfirmedByNodeIdString(aCursor.getString(aHashMap.get(OrganizationCommunityMemberMetaData.column_CONFIRMED_BY)));
		anOrganizationCommunityMember.setConfirmedTimestamp(GcgDateHelper.getDateFromFormattedUtcLong(aCursor.getLong(aHashMap.get(OrganizationCommunityMemberMetaData.column_CONFIRMED_TIMESTAMP))));
		anOrganizationCommunityMember.setAuthenticationUid(aCursor.getString(aHashMap.get(OrganizationCommunityMemberMetaData.column_AUTHENTICATION_UID)));
		anOrganizationCommunityMember.setSuperUser(aCursor.getInt(aHashMap.get(OrganizationCommunityMemberMetaData.column_IS_SUPER_USER)));
	}

	@Override
	public ContentValues buildContentValues(OrganizationCommunityMember anOrganizationCommunityMember) {
		ContentValues theContentValues = super.buildContentValues(anOrganizationCommunityMember);
		theContentValues.put(OrganizationCommunityMemberMetaData.column_ORGANIZATION_PARTICIPATION, anOrganizationCommunityMember.getOrganizationParticipation().getName());
		theContentValues.put(OrganizationCommunityMemberMetaData.column_TEAM_MEMBER_STATUS, anOrganizationCommunityMember.getTeamMemberStatus().getName());
		theContentValues.put(OrganizationCommunityMemberMetaData.column_PROPOSED_BY, anOrganizationCommunityMember.getProposedByNodeIdString());
		theContentValues.put(OrganizationCommunityMemberMetaData.column_PROPOSED_TIMESTAMP, GcgDateHelper.getFormattedUtcLong(anOrganizationCommunityMember.getProposedTimestamp()));
		theContentValues.put(OrganizationCommunityMemberMetaData.column_CONFIRMED_BY, anOrganizationCommunityMember.getConfirmedByNodeIdString());
		theContentValues.put(OrganizationCommunityMemberMetaData.column_CONFIRMED_TIMESTAMP, GcgDateHelper.getFormattedUtcLong(anOrganizationCommunityMember.getConfirmedTimestamp()));
		theContentValues.put(OrganizationCommunityMemberMetaData.column_AUTHENTICATION_UID, anOrganizationCommunityMember.getAuthenticationUid());
		theContentValues.put(OrganizationCommunityMemberMetaData.column_IS_SUPER_USER, anOrganizationCommunityMember.isSuperUser());
		return theContentValues;
	}

	@Override
	public ContentValues buildUpdateContentValues(OrganizationCommunityMember anOrganizationCommunityMember) {
		ContentValues theContentValues = super.buildUpdateContentValues(anOrganizationCommunityMember);
		theContentValues.put(OrganizationCommunityMemberMetaData.column_ORGANIZATION_PARTICIPATION, anOrganizationCommunityMember.getOrganizationParticipation().getName());
		theContentValues.put(OrganizationCommunityMemberMetaData.column_TEAM_MEMBER_STATUS, anOrganizationCommunityMember.getTeamMemberStatus().getName());
		theContentValues.put(OrganizationCommunityMemberMetaData.column_PROPOSED_BY, anOrganizationCommunityMember.getProposedByNodeIdString());
		theContentValues.put(OrganizationCommunityMemberMetaData.column_PROPOSED_TIMESTAMP, GcgDateHelper.getFormattedUtcLong(anOrganizationCommunityMember.getProposedTimestamp()));
		theContentValues.put(OrganizationCommunityMemberMetaData.column_CONFIRMED_BY, anOrganizationCommunityMember.getConfirmedByNodeIdString());
		theContentValues.put(OrganizationCommunityMemberMetaData.column_CONFIRMED_TIMESTAMP, GcgDateHelper.getFormattedUtcLong(anOrganizationCommunityMember.getConfirmedTimestamp()));
		theContentValues.put(OrganizationCommunityMemberMetaData.column_AUTHENTICATION_UID, anOrganizationCommunityMember.getAuthenticationUid());
		theContentValues.put(OrganizationCommunityMemberMetaData.column_IS_SUPER_USER, anOrganizationCommunityMember.isSuperUser());
		return theContentValues;
	}

	@Override
	protected OrganizationCommunityMember getNextObjectFromCursor(Cursor aCursor) {
		OrganizationCommunityMember theOrganizationCommunityMember = null;
		theOrganizationCommunityMember = new OrganizationCommunityMember(
				aCursor.getString(this.columnIndexMap.get(OrganizationCommunityMemberMetaData.column_ORGANIZATION_ID)),
				aCursor.getString(this.columnIndexMap.get(OrganizationCommunityMemberMetaData.column_COMMUNITY_MEMBER_ID)) );
		getColumnValues(this.columnIndexMap, aCursor, theOrganizationCommunityMember);
		return theOrganizationCommunityMember;
	}

}
