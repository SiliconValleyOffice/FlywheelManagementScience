/* @(#)CommunityMemberDaoSqLite.java
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

import com.flywheelms.library.fmm.meta_data.CommunityMemberMetaData;
import com.flywheelms.library.fmm.meta_data.IdNodeMetaData;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.governable.CommunityMember;

import java.util.HashMap;

public class CommunityMemberDaoSqLite extends HeadlineNodeDaoSqLite<CommunityMember> {

	private static CommunityMemberDaoSqLite singleton;

	public static CommunityMemberDaoSqLite getInstance() {
		if(CommunityMemberDaoSqLite.singleton == null) {
			CommunityMemberDaoSqLite.singleton = new CommunityMemberDaoSqLite();
		}
		return CommunityMemberDaoSqLite.singleton;
	}
	
	@Override
	public FmmNodeDefinition getFmmNodeDefinition() {
		return FmmNodeDefinition.COMMUNITY_MEMBER;
	}

	@Override
	protected void buildColumnIndexMap(Cursor aCursor) {
		super.buildColumnIndexMap(aCursor);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CommunityMemberMetaData.column_COMMUNITY_MEMBER_STATUS);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CommunityMemberMetaData.column_AGGREGATE_TEAM_ALLOCATION);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CommunityMemberMetaData.column_AUTHENTICATION_UID);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CommunityMemberMetaData.column_GIVEN_NAME);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CommunityMemberMetaData.column_FAMILY_NAME);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CommunityMemberMetaData.column_NICK_NAME);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CommunityMemberMetaData.column_EMAIL_ADDRESS);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CommunityMemberMetaData.column_CELL_PHONE);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, CommunityMemberMetaData.column_WORK_PHONE);
	}

	@Override
	public ContentValues buildContentValues(CommunityMember aCommunityMember) {
		ContentValues theContentValues = super.buildContentValues(aCommunityMember);
		theContentValues.put(CommunityMemberMetaData.column_COMMUNITY_MEMBER_STATUS, aCommunityMember.getCommunityMemberStatusCode());
		theContentValues.put(CommunityMemberMetaData.column_AGGREGATE_TEAM_ALLOCATION, aCommunityMember.getAggregateTeamAllocation());
		theContentValues.put(CommunityMemberMetaData.column_AUTHENTICATION_UID, aCommunityMember.getAuthenticationUid());
		theContentValues.put(CommunityMemberMetaData.column_GIVEN_NAME, aCommunityMember.getGivenName());
		theContentValues.put(CommunityMemberMetaData.column_FAMILY_NAME, aCommunityMember.getFamilyName());
		theContentValues.put(CommunityMemberMetaData.column_NICK_NAME, aCommunityMember.getNickName());
		theContentValues.put(CommunityMemberMetaData.column_EMAIL_ADDRESS, aCommunityMember.getEmailAddress());
		theContentValues.put(CommunityMemberMetaData.column_CELL_PHONE, aCommunityMember.getCellPhone());
		theContentValues.put(CommunityMemberMetaData.column_WORK_PHONE, aCommunityMember.getWorkPhone());
		return theContentValues;
	}

	@Override
	protected void getColumnValues(HashMap<String, Integer> aHashMap, Cursor aCursor, CommunityMember aCommunityMember) {
		super.getColumnValues(aHashMap, aCursor, aCommunityMember);
		aCommunityMember.setCommunityMemberStatusCode(aCursor.getString(aHashMap.get(CommunityMemberMetaData.column_COMMUNITY_MEMBER_STATUS)));
		aCommunityMember.setAggregateTeamAllocation(aCursor.getFloat(aHashMap.get(CommunityMemberMetaData.column_AGGREGATE_TEAM_ALLOCATION)));
		aCommunityMember.setAuthenticationUid(aCursor.getString(aHashMap.get(CommunityMemberMetaData.column_AUTHENTICATION_UID)));
		aCommunityMember.setGivenName(aCursor.getString(aHashMap.get(CommunityMemberMetaData.column_GIVEN_NAME)));
		aCommunityMember.setFamilyName(aCursor.getString(aHashMap.get(CommunityMemberMetaData.column_FAMILY_NAME)));
		aCommunityMember.setNickName(aCursor.getString(aHashMap.get(CommunityMemberMetaData.column_NICK_NAME)));
		aCommunityMember.setEmailAddress(aCursor.getString(aHashMap.get(CommunityMemberMetaData.column_EMAIL_ADDRESS)));
		aCommunityMember.setCellPhone(aCursor.getString(aHashMap.get(CommunityMemberMetaData.column_CELL_PHONE)));
		aCommunityMember.setWorkPhone(aCursor.getString(aHashMap.get(CommunityMemberMetaData.column_WORK_PHONE)));
	}

	@Override
	protected CommunityMember getNextObjectFromCursor(Cursor aCursor) {
		CommunityMember theCommunityMember = null;
		theCommunityMember = new CommunityMember(
				aCursor.getString(this.columnIndexMap.get(IdNodeMetaData.column_ID)) );
		getColumnValues(this.columnIndexMap, aCursor, theCommunityMember);
		return theCommunityMember;
	}

}
