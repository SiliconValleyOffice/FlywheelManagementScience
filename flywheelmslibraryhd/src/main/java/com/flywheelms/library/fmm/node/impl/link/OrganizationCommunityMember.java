/* @(#)OrganizationLinkCommunityMember.java
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

package com.flywheelms.library.fmm.node.impl.link;

import java.util.Date;

import com.flywheelms.library.fmm.node.impl.enumerator.OrganizationParticipation;
import com.flywheelms.library.fmm.node.impl.enumerator.TeamMemberStatus;
import com.flywheelms.library.fmm.node.impl.governable.CommunityMember;
import com.flywheelms.library.fmm.node.impl.governable.FmsOrganization;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmNode;

public class OrganizationCommunityMember extends FmmLinkNodeImpl {

	private OrganizationParticipation organizationParticipation;
	private TeamMemberStatus teamMemberStatus;
	private String proposedByNodeIdString;
	private CommunityMember proposedBy;
	private Date proposedTimestamp;
	private String confirmedByNodeIdString;
	private CommunityMember confirmedBy;
	private Date confirmedTimestamp;
	private String authenticationUid;
	private boolean isSuperUser;

	public OrganizationCommunityMember(
			String aParentNodeId,
			String aChildNodeId ) {
		super(
				OrganizationCommunityMember.class,
				aParentNodeId,
				aChildNodeId );
	}

	@Override
	public Class<? extends FmmNode> getParentClass() {
		return FmsOrganization.class;
	}

	@Override
	public Class<? extends FmmNode> getChildClass() {
		return CommunityMember.class;
	}

	public OrganizationParticipation getOrganizationParticipation() {
		return this.organizationParticipation;
	}

	public void setOrganizationParticipation(
			OrganizationParticipation organizationParticipation) {
		this.organizationParticipation = organizationParticipation;
	}
	
	public String getOrganizationNodeIdString() {
		return getParentNodeIdString();
	}
	
	public String getCommunityMemberNodeIdString() {
		return getChildNodeIdString();
	}

	public TeamMemberStatus getTeamMemberStatus() {
		return this.teamMemberStatus;
	}

	public void setTeamMemberStatus(TeamMemberStatus teamMemberStatus) {
		this.teamMemberStatus = teamMemberStatus;
	}

	public String getProposedByNodeIdString() {
		return this.proposedByNodeIdString;
	}

	public void setProposedByNodeIdString(String proposedByNodeIdString) {
		this.proposedByNodeIdString = proposedByNodeIdString;
	}

	public Date getProposedTimestamp() {
		return this.proposedTimestamp;
	}

	public void setProposedTimestamp(Date proposedTimestamp) {
		this.proposedTimestamp = proposedTimestamp;
	}

	public CommunityMember getProposedBy() {
		return this.proposedBy;
	}

	public void setProposedBy(CommunityMember proposedBy) {
		this.proposedBy = proposedBy;
	}

	public String getConfirmedByNodeIdString() {
		return this.confirmedByNodeIdString;
	}

	public void setConfirmedByNodeIdString(String confirmedByNodeIdString) {
		this.confirmedByNodeIdString = confirmedByNodeIdString;
	}

	public CommunityMember getConfirmedBy() {
		return this.confirmedBy;
	}

	public void setConfirmedBy(CommunityMember confirmedBy) {
		this.confirmedBy = confirmedBy;
	}

	public Date getConfirmedTimestamp() {
		return this.confirmedTimestamp;
	}

	public void setConfirmedTimestamp(Date confirmedTimestamp) {
		this.confirmedTimestamp = confirmedTimestamp;
	}

	public String getAuthenticationUid() {
		return this.authenticationUid;
	}

	public void setAuthenticationUid(String authenticationUid) {
		this.authenticationUid = authenticationUid;
	}

	public boolean isSuperUser() {
		return this.isSuperUser;
	}

	public void setSuperUser(boolean bIsSuperUser) {
		this.isSuperUser = bIsSuperUser;
	}

	public void setSuperUser(int anInteger) {
		setSuperUser(anInteger != 0);
	}
	
}
