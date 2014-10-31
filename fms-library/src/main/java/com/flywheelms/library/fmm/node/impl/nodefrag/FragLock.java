/* @(#)FragLock.java
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

import com.flywheelms.gcongui.gcg.widget.date.GcgDateHelper;
import com.flywheelms.library.fca.FlywheelCommunityAuthentication;
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.enumerator.FmmLockStatus;
import com.flywheelms.library.fmm.meta_data.FragLockMetaData;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.governable.CommunityMember;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmNodeLocking;
import com.flywheelms.library.util.JsonHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class FragLock extends FmmNodeFragImpl implements FmmNodeLocking {

	protected String grandparentNodeIdString;
	protected String grandparentNodeTypeCode;
	private boolean locked = false;
	private String lockedByNodeIdString;
	private CommunityMember lockedByCommunityMember;
	private Date lockedTimestamp = GcgDateHelper.NULL_DATE;
	private String unlockedByNodeIdString;
	private CommunityMember unlockedByCommunityMember;
	private Date unlockedTimestamp = GcgDateHelper.NULL_DATE;

	// a new FragLock
	public FragLock(String aParentNodeIdString, String aGrandparentNodeIdString) {
		super(FragLock.class, aParentNodeIdString);
		this.grandparentNodeIdString = aGrandparentNodeIdString;
		this.grandparentNodeTypeCode = NodeId.getNodeTypeCodeFromNodeIdString(aGrandparentNodeIdString);
	}

	// rehydrate
	public FragLock(String anExistingNodeFragNodeIdString, String aParentNodeIdString, String aGrandparentNodeIdString) {
		super(NodeId.hydrate(FragLock.class, anExistingNodeFragNodeIdString), aParentNodeIdString);
		this.grandparentNodeIdString = aGrandparentNodeIdString;
		this.grandparentNodeTypeCode = NodeId.getNodeTypeCodeFromNodeIdString(aGrandparentNodeIdString);
	}

	// rehydrate from serialization
	public FragLock(JSONObject aJsonObject) {
		super(FragLock.class, aJsonObject);
		try {
			setGrandparentNodeIdString(aJsonObject.getString(FragLockMetaData.column_GRANDPARENT_ID));
			setGrandparentNodeTypeCode(aJsonObject.getString(FragLockMetaData.column_GRANDPARENT_NODE_TYPE_CODE));
			setLockedBy(aJsonObject.getString(FragLockMetaData.column_LOCKED_BY));
			setLockedTimestamp(JsonHelper.getDate(aJsonObject, FragLockMetaData.column_LOCKED_TIMESTAMP));
			setUnlockedBy(aJsonObject.getString(FragLockMetaData.column_UNLOCKED_BY));
			setUnlockedTimestamp(JsonHelper.getDate(aJsonObject, FragLockMetaData.column_UNLOCKED_TIMESTAMP));
			setIsLocked(aJsonObject.getBoolean(FragLockMetaData.column_LOCKED));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public JSONObject getJsonObject() {
		JSONObject theJsonObject = super.getJsonObject();
		try {
			theJsonObject.put(FragLockMetaData.column_GRANDPARENT_ID, getGrandparentNodeIdString());
			theJsonObject.put(FragLockMetaData.column_GRANDPARENT_NODE_TYPE_CODE, getGrandparentNodeTypeCode());
			theJsonObject.put(FragLockMetaData.column_LOCKED_BY, getLockedByNodeIdString());
			JsonHelper.putDate(theJsonObject, FragLockMetaData.column_LOCKED_TIMESTAMP, getLockedTimestamp());
			theJsonObject.put(FragLockMetaData.column_UNLOCKED_BY, getUnlockedByNodeIdString());
			JsonHelper.putDate(theJsonObject, FragLockMetaData.column_UNLOCKED_TIMESTAMP, getUnlockedTimestamp());
			theJsonObject.put(FragLockMetaData.column_LOCKED, isLocked());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return theJsonObject;
	}
	
	@Override
	public FragLock getClone() {
		return new FragLock(getJsonObject());
	}

	public String getGrandparentNodeIdString() {
		return this.grandparentNodeIdString;
	}

	public void setGrandparentNodeIdString(String grandparentNodeIdString) {
		this.grandparentNodeIdString = grandparentNodeIdString;
	}

	public String getGrandparentNodeTypeCode() {
		return this.grandparentNodeTypeCode;
	}

	public void setGrandparentNodeTypeCode(String grandparentNodeTypeCode) {
		this.grandparentNodeTypeCode = grandparentNodeTypeCode;
	}
	
	@Override
	public void lock() {
		this.locked = true;
		setLockedBy(FlywheelCommunityAuthentication.getInstance().getFcaUserCredentials().getCommunityMemberNodeIdString());
		this.setLockedTimestamp(GcgDateHelper.getCurrentDateTime());
	}

	@Override
	public void unlock() {
		setUnlockedBy(FlywheelCommunityAuthentication.getInstance().getFcaUserCredentials().getCommunityMemberNodeIdString());
		setUnlockedTimestamp(GcgDateHelper.getCurrentDateTime());
		this.locked = false;
	}

	@Override
	public boolean isLocked() {
		return this.locked;
	}

	public void setIsLocked(int anInteger) {
		setIsLocked(anInteger != 0);
	}

	@Override
	public String getLockedByNodeIdString() {
		return this.lockedByNodeIdString;
	}

	@Override
	public void setLockedBy(String aNodeIdString) {
		this.lockedByNodeIdString = aNodeIdString;
	}

	@Override
	public CommunityMember getLockedByCommunityMember() {
		if(this.lockedByCommunityMember == null) {
			if(this.lockedByNodeIdString == null || this.lockedByNodeIdString.length() == 0) {
				setLockedBy(CommunityMember.getNullValue());
			} else {
				setLockedBy(FmmDatabaseMediator.getActiveMediator().retrieveCommunityMember(this.lockedByNodeIdString));
			}
		}
		return this.lockedByCommunityMember;
	}

	@Override
	public void setLockedBy(CommunityMember aCommunityMember) {
		this.lockedByCommunityMember = aCommunityMember;
		this.lockedByNodeIdString = aCommunityMember.getNodeIdString();
	}

	@Override
	public Date getLockedTimestamp() {
		return this.lockedTimestamp;
	}

	@Override
	public void setLockedTimestamp(Date aTimestamp) {
		this.lockedTimestamp = aTimestamp;
	}

	@Override
	public String getUnlockedByNodeIdString() {
		return this.unlockedByNodeIdString;
	}

	@Override
	public void setUnlockedBy(String aNodeIdString) {
		this.unlockedByNodeIdString = aNodeIdString;
	}

	@Override
	public CommunityMember getUnlockedByCommunityMember() {
		if(this.unlockedByCommunityMember == null) {
			this.unlockedByCommunityMember = FmmDatabaseMediator.getActiveMediator().retrieveCommunityMember(this.unlockedByNodeIdString);
		}
		return this.unlockedByCommunityMember;
	}

	@Override
	public void setUnlockedBy(CommunityMember aCommunityMember) {
		this.unlockedByCommunityMember = aCommunityMember;
		this.unlockedByNodeIdString = aCommunityMember.getNodeIdString();
	}

	@Override
	public Date getUnlockedTimestamp() {
		return this.unlockedTimestamp;
	}

	@Override
	public void setUnlockedTimestamp(Date unlockedTimestamp) {
		this.unlockedTimestamp = unlockedTimestamp;
	}

	@Override
	public NodeId getLockedByNodeId() {
		return getLockedByCommunityMember().getNodeId();
	}

	@Override
	public void setLockedBy(NodeId aNodeId) {
		this.setLockedBy(aNodeId.getNodeIdString());
	}

	@Override
	public NodeId getUnlockedByNodeId() {
		return getUnlockedByCommunityMember().getNodeId();
	}

	@Override
	public void setUnlockedBy(NodeId aNodeId) {
		setUnlockedBy(aNodeId.getNodeIdString());
	}

	@Override
	public void setIsLocked(boolean aBoolean) {
		this.locked = aBoolean;
	}

	@Override
	public FmmLockStatus getLockStatus() {
		if(isLocked()) {
			return FmmLockStatus.LOCKED;
		}
		if(! isLocked() && 
				( getLockedByNodeIdString() == null ||
				getLockedByNodeIdString().equals("") ||
				getLockedByNodeIdString().equals(CommunityMember.getNullValue().getNodeIdString()) ) ) {
			return FmmLockStatus.NEVER_LOCKED;
		}
		return FmmLockStatus.UNLOCKED;
	}

}
