/* @(#)FmmNodeFragLockableImpl.java
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

import java.util.Date;

import org.json.JSONObject;

import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.enumerator.FmmLockStatus;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.governable.CommunityMember;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmNodeLocking;

public class FmmNodeFragLockableImpl extends FmmNodeFragImpl implements FmmNodeLocking {

	private FragLock fragLock;

	public FmmNodeFragLockableImpl(Class<? extends FmmNodeFragLockableImpl> aClass, String aParentNodeIdString) {
		super(aClass, aParentNodeIdString);
		this.fragLock = new FragLock(getNodeIdString(), aParentNodeIdString);
	}

	// rehydrate from database
	public FmmNodeFragLockableImpl(NodeId anExistingNodeFragNodeId, String aParentNodeIdString) {
		super(anExistingNodeFragNodeId, aParentNodeIdString);
	}

	// rehydrate from serialization
	public FmmNodeFragLockableImpl(Class<? extends FmmNodeFragLockableImpl> aClass, JSONObject aJsonObject) {
		super(aClass, aJsonObject);
//		try {
//			if(aJsonObject.has(NodeFragColumnDefinition.key__FRAG_LOCK)) {
//				setFragLock(new FragLock(aJsonObject.getJSONObject(NodeFragColumnDefinition.key__FRAG_LOCK)));
//			}
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	@Override
	public JSONObject getJsonObject() {
		JSONObject theJsonObject = super.getJsonObject();
//		try {
//			theJsonObject.put(NodeFragColumnDefinition.key__FRAG_LOCK, getFragLock().getJsonObject());
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return theJsonObject;
	}

	public FragLock getFragLock() {
		if(this.fragLock == null) {
			this.fragLock = FmmDatabaseMediator.getActiveMediator().getFragLockForParentOrCreate(getNodeIdString(), getParentNodeIdString());
		}
		return this.fragLock;
	}
	
	public void setFragLock(FragLock aFragLock) {
		this.fragLock = aFragLock;
	}

	@Override
	public CommunityMember getLockedByCommunityMember() {
		return getFragLock().getLockedByCommunityMember();
	}

	@Override
	public NodeId getLockedByNodeId() {
		return getFragLock().getLockedByCommunityMember().getNodeId();
	}

	@Override
	public String getLockedByNodeIdString() {
		return getFragLock().getLockedByCommunityMember().getNodeIdString();
	}

	@Override
	public void setLockedBy(NodeId aNodeId) {
		getFragLock().setLockedBy(aNodeId.getNodeIdString());
	}

	@Override
	public void setLockedBy(String aNodeIdString) {
		getFragLock().setLockedBy(aNodeIdString);
	}

	@Override
	public void setLockedBy(CommunityMember aCommunityMember) {
		getFragLock().setLockedBy(aCommunityMember);
	}

	@Override
	public Date getLockedTimestamp() {
		return getFragLock().getLockedTimestamp();
	}

	@Override
	public void setLockedTimestamp(Date aTimestamp) {
		getFragLock().setLockedTimestamp(aTimestamp);
	}

	@Override
	public boolean isLocked() {
		return getFragLock().isLocked();
	}

	@Override
	public void lock() {
		getFragLock().lock();
	}

	@Override
	public String getUnlockedByNodeIdString() {
		return getFragLock().getUnlockedByNodeIdString();
	}

	@Override
	public NodeId getUnlockedByNodeId() {
		return getFragLock().getUnlockedByCommunityMember().getNodeId();
	}

	@Override
	public CommunityMember getUnlockedByCommunityMember() {
		return getFragLock().getUnlockedByCommunityMember();
	}

	@Override
	public void setUnlockedBy(String aNodeIdString) {
		getFragLock().setUnlockedBy(aNodeIdString);
	}

	@Override
	public void setUnlockedBy(NodeId aNodeId) {
		getFragLock().setUnlockedBy(aNodeId.getNodeIdString());
	}

	@Override
	public void setUnlockedBy(CommunityMember aCommunityMember) {
		getFragLock().setUnlockedBy(aCommunityMember);
	}

	@Override
	public Date getUnlockedTimestamp() {
		return getFragLock().getUnlockedTimestamp();
	}

	@Override
	public void setUnlockedTimestamp(Date aTimestamp) {
		getFragLock().setUnlockedTimestamp(aTimestamp);
	}

	public boolean isUnlocked() {
		return ! isLocked();
	}
	
	@Override
	public void unlock() {
		getFragLock().unlock();
	}

	@Override
	public void setIsLocked(boolean aBoolean) {
		if(aBoolean) {
			lock();
		} else {
			unlock();
		}
	}

	@Override
	public FmmLockStatus getLockStatus() {
		return getFragLock().getLockStatus();
	}
	
}
