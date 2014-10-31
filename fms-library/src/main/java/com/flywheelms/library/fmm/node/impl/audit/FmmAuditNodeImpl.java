/* @(#)FmmAuditNodeImpl.java
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

package com.flywheelms.library.fmm.node.impl.audit;

import com.flywheelms.gcongui.gcg.widget.date.GcgDateHelper;
import com.flywheelms.library.fca.FlywheelCommunityAuthentication;
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.enumerator.FmmLockStatus;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.FmmNodeImpl;
import com.flywheelms.library.fmm.node.impl.governable.CommunityMember;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragAuditBlock;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmNodeAudit;

import org.json.JSONObject;

import java.util.Date;

public abstract class FmmAuditNodeImpl extends FmmNodeImpl implements FmmNodeAudit {
	
	private NodeFragAuditBlock nodeFragAuditBlock; 

	public FmmAuditNodeImpl(NodeId aNodeId) {
		super(aNodeId);
	}
	
	public FmmAuditNodeImpl(Class<? extends FmmAuditNodeImpl> aClass, JSONObject aJsonObject) {
		this(NodeId.rehydrate(aClass, aJsonObject));
	}
	
	@Override
	public NodeFragAuditBlock getNodeFragAuditBlock() {
		if(this.nodeFragAuditBlock == null) {
			this.nodeFragAuditBlock = FmmDatabaseMediator.getActiveMediator().getNodeFragAuditBlockForParent(getNodeIdString());
		}
		return this.nodeFragAuditBlock;
	}

	@Override
	public void setNodeFragAuditBlock(NodeFragAuditBlock anAuditBlock) {
		this.nodeFragAuditBlock = anAuditBlock;
	}
	
	//  Created  //
	
	@Override
	public String getCreatedByNodeIdString() {
		return getNodeFragAuditBlock().getCreatedByNodeIdString();
	}
	
	@Override
	public void setCreatedBy(String aNodeIdString) {
		getNodeFragAuditBlock().setCreatedBy(aNodeIdString);
	}
	
	@Override
	public CommunityMember getCreatedByCommunityMember() {
		return getNodeFragAuditBlock().getCreatedByCommunityMember();
	}

	@Override
	public void setCreatedBy(CommunityMember aCommunityMember) {
		getNodeFragAuditBlock().setCreatedBy(aCommunityMember);
	}

	@Override
	public Date getCreatedTimestamp() {
		return getNodeFragAuditBlock().getCreatedTimestamp();
	}
	
	@Override
	public void setCreatedTimestamp(Date aCreatedTimestamp) {
		getNodeFragAuditBlock().setCreatedTimestamp(aCreatedTimestamp);
	}
	
	//  Updated  //

	@Override
	public String getLastUpdatedByNodeIdString() {
		return getNodeFragAuditBlock().getLastUpdatedByNodeIdString();
	}
	
	@Override
	public void setLastUpdatedBy(String aNodeIdString) {
		getNodeFragAuditBlock().setLastUpdatedBy(aNodeIdString);
	}
	
	@Override
	public CommunityMember getLastUpdatedByCommunityMember() {
		return getNodeFragAuditBlock().getLastUpdatedByCommunityMember();
	}

	@Override
	public void setLastUpdatedBy(CommunityMember aCommunityMember) {
		getNodeFragAuditBlock().setLastUpdatedBy(aCommunityMember);
	}

	@Override
	public Date getLastUpdatedTimestamp() {
		return getNodeFragAuditBlock().getRowTimestamp();
	}
	
	//  Locked  //

	@Override
	public String getLockedByNodeIdString() {
		return getNodeFragAuditBlock().getLockedByNodeId().getNodeIdString();
	}

	@Override
	public NodeId getLockedByNodeId() {
		return getNodeFragAuditBlock().getLockedByNodeId();
	}

	@Override
	public CommunityMember getLockedByCommunityMember() {
		return getNodeFragAuditBlock().getLockedByCommunityMember();
	}
	
	@Override
	public void setLockedBy(String aNodeIdString) {
		getNodeFragAuditBlock().setLockedBy(aNodeIdString);
	}

	@Override
	public void setLockedBy(NodeId aNodeId) {
		getNodeFragAuditBlock().setLockedBy(aNodeId);
	}

	@Override
	public void setLockedBy(CommunityMember aCommunityMember) {
		getNodeFragAuditBlock().setLockedBy(aCommunityMember);
	}

	@Override
	public Date getLockedTimestamp() {
		return getNodeFragAuditBlock().getLockedTimestamp();
	}
	
	@Override
	public void setLockedTimestamp(Date aLockedTimestamp) {
		getNodeFragAuditBlock().setLockedTimestamp(aLockedTimestamp);
	}

	@Override
	public void lock() {
		getNodeFragAuditBlock().setLockedBy(FlywheelCommunityAuthentication.getInstance().getFcaUserCredentials().getCommunityMemberNodeIdString());
		getNodeFragAuditBlock().setLockedTimestamp(GcgDateHelper.getCurrentDateTime());
		getNodeFragAuditBlock().setIsLocked(true);
	}
	
	////  Unlock  ////

	@Override
	public String getUnlockedByNodeIdString() {
		return getNodeFragAuditBlock().getUnlockedByNodeId().getNodeIdString();
	}

	@Override
	public NodeId getUnlockedByNodeId() {
		return getNodeFragAuditBlock().getUnlockedByNodeId();
	}

	@Override
	public CommunityMember getUnlockedByCommunityMember() {
		return getNodeFragAuditBlock().getUnlockedByCommunityMember();
	}
	
	@Override
	public void setUnlockedBy(String aNodeIdString) {
		getNodeFragAuditBlock().setUnlockedBy(aNodeIdString);
	}

	@Override
	public void setUnlockedBy(NodeId aNodeId) {
		getNodeFragAuditBlock().setUnlockedBy(aNodeId);
	}

	@Override
	public void setUnlockedBy(CommunityMember aCommunityMember) {
		getNodeFragAuditBlock().setUnlockedBy(aCommunityMember);
	}

	@Override
	public Date getUnlockedTimestamp() {
		return getNodeFragAuditBlock().getUnlockedTimestamp();
	}
	
	@Override
	public void setUnlockedTimestamp(Date anUnlockedTimestamp) {
		getNodeFragAuditBlock().setUnlockedTimestamp(anUnlockedTimestamp);
	}

	@Override
	public void unlock() {
		getNodeFragAuditBlock().setLockedBy(FlywheelCommunityAuthentication.getInstance().getFcaUserCredentials().getCommunityMemberNodeIdString());
		getNodeFragAuditBlock().setLockedTimestamp(GcgDateHelper.getCurrentDateTime());
		getNodeFragAuditBlock().setIsLocked(false);
	}
	
	/////  Locking Support  ////
	
	@Override
	public void setIsLocked(boolean aBoolean) {
		getNodeFragAuditBlock().setIsLocked(aBoolean);
	}

	@Override
	public boolean isLocked() {
		return getNodeFragAuditBlock().isLocked();
	}

	@Override
	public FmmLockStatus getLockStatus() {
		return getNodeFragAuditBlock().getLockStatus();
	}
	
}
