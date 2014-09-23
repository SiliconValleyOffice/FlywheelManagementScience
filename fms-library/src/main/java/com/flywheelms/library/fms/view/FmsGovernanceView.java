/* @(#)FmsGovernanceView.java
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

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.flywheelms.gcongui.gcg.helper.GcgHelper;
import com.flywheelms.gcongui.gcg.widget.date.GcgDateHelper;
import com.flywheelms.library.R;
import com.flywheelms.library.fmm.enumerator.FmmLockStatus;
import com.flywheelms.library.fmm.node.impl.enumerator.AssignmentCommitmentType;
import com.flywheelms.library.fmm.node.impl.nodefrag.GovernanceTeamMember;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragGovernance;

public class FmsGovernanceView extends LinearLayout {
	
	private LinearLayout viewLayout;
	private TableLayout tableLayout;
	private LayoutInflater layoutInflater;

	public FmsGovernanceView(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
		this.viewLayout = (LinearLayout) LayoutInflater.from(aContext).inflate(R.layout.fms__node_frag_governance__view, (ViewGroup) getParent());
		addView(this.viewLayout);
		this.tableLayout = (TableLayout) this.viewLayout.findViewById(R.id.governance_table);
		this.layoutInflater = LayoutInflater.from(aContext);
	}
	
	public LinearLayout getViewLayout() {
		return this.viewLayout;
	}
	
	public void viewGovernance(NodeFragGovernance aNodeFragGovernance) {
		for(GovernanceTeamMember theGovernanceTeamMember : aNodeFragGovernance.getGovernanceTeamCollection()) {
			TableRow theTableRow = (TableRow) this.layoutInflater.inflate(R.layout.fms__node_frag_governance__row, null);
			((TextView) theTableRow.findViewById(R.id.governance__role__data)).setText(theGovernanceTeamMember.getRole().toString());
			((ImageView) theTableRow.findViewById(R.id.governance__role__image)).setImageDrawable(theGovernanceTeamMember.getRole().getDataDrawable());
			((TextView) theTableRow.findViewById(R.id.governance__community_member__data)).setText(theGovernanceTeamMember.getCommunityMember().toString());
			((TextView) theTableRow.findViewById(R.id.governance__commitment__data)).setText(theGovernanceTeamMember.getCommitmentType().toString());
			((TextView) theTableRow.findViewById(R.id.governance__commitment_timestamp__data)).setText(getCommitmentTimestampString(theGovernanceTeamMember));
			((TextView) theTableRow.findViewById(R.id.governance__proposed_by__data)).setText(theGovernanceTeamMember.getProposedByCommunityMember().toString());
			if(GcgHelper.isLandscapeMode()) {
				((TextView) theTableRow.findViewById(R.id.governance__proposed_timestamp__data)).setText(GcgDateHelper.getGuiDateString1(theGovernanceTeamMember.getProposedTimestamp()));
//				((TextView) theTableRow.findViewById(R.id.governance__locked_status__data)).setText(getLockedStatus(theGovernanceTeamMember).toString());
//				((TextView) theTableRow.findViewById(R.id.governance__locked_by__data)).setText(theGovernanceTeamMember.getLockedByCommunityMember().toString());
//				((TextView) theTableRow.findViewById(R.id.governance__locked_timestamp__data)).setText(
//						FmmDateHelper.getGuiDateString1(theGovernanceTeamMember.getLockedTimestamp()));
			}
			this.tableLayout.addView(theTableRow);
			TableRow theTableRowDivider = (TableRow) this.layoutInflater.inflate(R.layout.fms__table_row_divider, null);
			this.tableLayout.addView(theTableRowDivider);
		}
	}

	private static CharSequence getCommitmentTimestampString(GovernanceTeamMember aGovernanceTeamMember) {
		if(aGovernanceTeamMember.getCommitmentType() == AssignmentCommitmentType.PROPOSED) {
			return "";
		}
		return GcgDateHelper.getGuiDateString1(aGovernanceTeamMember.getCommitmentTimestamp());
	}

	@SuppressWarnings("unused")
	private static FmmLockStatus getLockedStatus(GovernanceTeamMember aGovernanceTeamMember) {
		// TODO Boolean.toString(anAuditBlock.isLocked())
//		if(aGovernanceTeamMember.isLocked()) {
//			return FmmLockStatus.LOCKED;
//		}
//		if(FmmDateHelper.isNullDate(aGovernanceTeamMember.getLockedTimestamp())) {
//			return FmmLockStatus.NEVER_LOCKED;
//		}
		return FmmLockStatus.UNLOCKED;
	}

}
