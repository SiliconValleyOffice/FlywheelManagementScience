/* @(#)FmsAuditBlockView.java
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

package com.flywheelms.library.fms.view;

import java.util.Date;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flywheelms.library.R;
import com.flywheelms.library.fca.FlywheelCommunityAuthentication;
import com.flywheelms.library.fmm.enumerator.FmmLockStatus;
import com.flywheelms.library.fmm.node.impl.governable.CommunityMember;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragAuditBlock;
import com.flywheelms.library.fse.model.FseDocumentTransactionType;
import com.flywheelms.library.gcg.widget.date.GcgDateHelper;

// com.flywheelms.library.fwb.widget.FmsAuditBlockView
public class FmsAuditBlockView extends LinearLayout {
	
	NodeFragAuditBlock auditBlock;
	private final LinearLayout viewLayout;
	private final TextView createdBy;
	private final TextView createdTimestamp;
	private final TextView updatedBy;
	private final TextView updatedTimestamp;
	private final TextView lockedBy;
	private final TextView lockedTimestamp;
	private final TextView lockedStatus;
	private final TextView lockedByLabel;
	private final TextView lockedTimestampLabel;

	public FmsAuditBlockView(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
		this.viewLayout = (LinearLayout) LayoutInflater.from(aContext).inflate(R.layout.fms__node_frag_audit_block__view, (ViewGroup) getParent());
		addView(this.viewLayout);
		this.createdBy = (TextView) this.viewLayout.findViewById(R.id.audit_block__created_by__data);
		this.createdTimestamp = (TextView) this.viewLayout.findViewById(R.id.audit_block__created_timestamp__data);
		this.updatedBy = (TextView) this.viewLayout.findViewById(R.id.audit_block__last_updated_by__data);
		this.updatedTimestamp = (TextView) this.viewLayout.findViewById(R.id.audit_block__last_updated_timestamp__data);
		this.lockedBy = (TextView) this.viewLayout.findViewById(R.id.audit_block__locked_by__data);
		this.lockedTimestamp = (TextView) this.viewLayout.findViewById(R.id.audit_block__locked_timestamp__data);
		this.lockedStatus = (TextView) this.viewLayout.findViewById(R.id.audit_block__locked_status__data);
		this.lockedByLabel = (TextView) this.viewLayout.findViewById(R.id.audit_block__locked_by__label);
		this.lockedTimestampLabel = (TextView) this.viewLayout.findViewById(R.id.audit_block__locked_timestamp__label);
	}
	
	public LinearLayout getViewLayout() {
		return this.viewLayout;
	}
	
	public void viewAuditBlock(NodeFragAuditBlock anAuditBlock) {
		this.auditBlock = anAuditBlock;
		this.createdBy.setText(FlywheelCommunityAuthentication.getInstance().getCommunityMember(anAuditBlock.getCreatedByNodeIdString()).getName());
		this.createdTimestamp.setText(GcgDateHelper.getGuiDateString2(anAuditBlock.getCreatedTimestamp()));
		this.updatedBy.setText(anAuditBlock.getLastUpdatedByCommunityMember().getName());
		this.updatedTimestamp.setText(GcgDateHelper.getGuiDateString2(anAuditBlock.getRowTimestamp()));
		this.lockedBy.setText(anAuditBlock.getLockedByCommunityMember().getName());
		this.lockedTimestamp.setText(GcgDateHelper.getGuiDateString2(anAuditBlock.getLockedTimestamp()));
		this.lockedStatus.setText(getLockedStatus().toString());
		updateLockedLabels();
	}

	private void updateLockedLabels() {
		if(this.auditBlock.isUnlocked() && GcgDateHelper.isNotNullDate(this.auditBlock.getLockedTimestamp())) {
			this.lockedByLabel.setText("Unlocked By");
			this.lockedTimestampLabel.setText("Unlocked Timestamp");
		} else {
			this.lockedByLabel.setText("Locked By");
			this.lockedTimestampLabel.setText("Locked Timestamp");
		}
	}

//	private static CharSequence getLockedByNodeIdString(NodeFragAuditBlock anAuditBlock) {
//		if(FmmDateHelper.isNullDate(anAuditBlock.getLockedTimestamp())) {
//			return "";
//		}
//		return anAuditBlock.getLockedByNodeIdString();
//	}

	private FmmLockStatus getLockedStatus() {
		// TODO Boolean.toString(anAuditBlock.isLocked())
		if(this.auditBlock.isLocked()) {
			return FmmLockStatus.LOCKED;
		}
		if(GcgDateHelper.isNullDate(this.auditBlock.getLockedTimestamp())) {
			return FmmLockStatus.NEVER_LOCKED;
		}
		return FmmLockStatus.UNLOCKED;
	}

	public void update(Date aTimestamp, FseDocumentTransactionType aDocumentTransactionType) {
		CommunityMember theCommunityMember = FlywheelCommunityAuthentication.getInstance().getCommunityMember();
		this.updatedBy.setText(theCommunityMember.getName());
		this.auditBlock.setLastUpdatedBy(theCommunityMember.getNodeIdString());
		this.updatedTimestamp.setText(GcgDateHelper.getGuiDateString2(aTimestamp));
		this.auditBlock.setRowTimestamp(aTimestamp);
		if(aDocumentTransactionType == FseDocumentTransactionType.LOCK) {
			this.lockedBy.setText(theCommunityMember.getName());
			this.auditBlock.setLockedBy(theCommunityMember.getNodeId());
			this.lockedTimestamp.setText(GcgDateHelper.getGuiDateString2(aTimestamp));
			this.auditBlock.setLockedTimestamp(aTimestamp);
			this.lockedStatus.setText(getLockedStatus().toString());
			updateLockedLabels();
		}
	}

}
