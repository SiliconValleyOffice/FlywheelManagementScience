/* @(#)GovernanceTeamMember.java
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

import com.flywheelms.library.fca.FlywheelCommunityAuthentication;
import com.flywheelms.library.fmm.node.impl.enumerator.AssignmentCommitmentType;
import com.flywheelms.library.fmm.node.impl.enumerator.GovernanceParticipationType;
import com.flywheelms.library.fmm.node.impl.enumerator.GovernanceRole;
import com.flywheelms.library.fmm.node.impl.enumerator.GovernanceSatisfaction;
import com.flywheelms.library.fmm.node.impl.governable.CommunityMember;
import com.flywheelms.library.gcg.widget.date.GcgDateHelper;

import java.util.Date;

public class GovernanceTeamMember {

	protected GovernanceRole role;
	protected GovernanceParticipationType participationType;
	protected CommunityMember communityMember;
	protected CommunityMember proposedByCommunityMember;
	protected Date proposedTimestamp = GcgDateHelper.NULL_DATE;
	protected AssignmentCommitmentType commitmentType;
	protected Date commitmentTimestamp = GcgDateHelper.NULL_DATE;
	protected GovernanceSatisfaction governanceSatisfaction;
	protected FragLock fragLock;
	
	public GovernanceTeamMember(GovernanceRole aGovernanceRole, CommunityMember aCommunityMember) {
		this.role = aGovernanceRole;
		this.communityMember = aCommunityMember;
	}

	public GovernanceTeamMember(GovernanceRole aGovernanceRole, String aCommunityMemberNodeIdString) {
		this(aGovernanceRole, FlywheelCommunityAuthentication.getInstance().getCommunityMember(aCommunityMemberNodeIdString));
	}

	public void setProposed(CommunityMember aCommunityMember, Date aProposedTimestamp) {
		this.proposedByCommunityMember = aCommunityMember;
		this.proposedTimestamp = aProposedTimestamp;
	}

	public void setProposed(String aCommunityMemberNodeIdString, Date aProposedTimestamp) {
		setProposed(FlywheelCommunityAuthentication.getInstance().getCommunityMember(aCommunityMemberNodeIdString), aProposedTimestamp);
	}

	public void setCommitment(AssignmentCommitmentType aCommitmentType, Date aCommitmentTimestamp) {
		this.commitmentType = aCommitmentType;
		this.commitmentTimestamp = aCommitmentTimestamp;
	}

	public void setCommitment(String aCommitmentTypeString, Date aProposedTimestamp) {
		setCommitment(AssignmentCommitmentType.getObjectForName(aCommitmentTypeString), aProposedTimestamp);
	}

	public GovernanceRole getRole() {
		return this.role;
	}

	public GovernanceParticipationType getParticipationType() {
		return this.participationType;
	}

	public CommunityMember getCommunityMember() {
		return this.communityMember;
	}

	public AssignmentCommitmentType getCommitmentType() {
		return this.commitmentType;
	}

	public Date getCommitmentTimestamp() {
		return this.commitmentTimestamp;
	}

	public CommunityMember getProposedByCommunityMember() {
		return this.proposedByCommunityMember;
	}

	public Date getProposedTimestamp() {
		return this.proposedTimestamp;
	}

	public GovernanceSatisfaction getGovernanceSatisfaction() {
		return this.governanceSatisfaction;
	}

	public void setGovernanceSatisfaction(GovernanceSatisfaction aGovernanceSatisfaction) {
		this.governanceSatisfaction = aGovernanceSatisfaction;
	}

	public void setGovernanceSatisfaction(int aGovernanceScore) {
		this.governanceSatisfaction = GovernanceSatisfaction.getObjectForScore(aGovernanceScore);
	}

	public boolean isLocked() {
		// TODO Auto-generated method stub
		return false;
	}

}
