/* @(#)FmmCommitmentNodeImpl.java
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

package com.flywheelms.library.fmm.node.impl.commitment;

import com.flywheelms.gcongui.gcg.widget.date.GcgDateHelper;
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.FmmHistoryNodeImpl;
import com.flywheelms.library.fmm.node.impl.enumerator.CompletionCommitmentType;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.governable.CommunityMember;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmCommitmentNode;

import java.util.Date;

public abstract class FmmCommitmentNodeImpl extends FmmHistoryNodeImpl implements FmmCommitmentNode {
	
	protected String parentNodeIdString;
	protected String childNodeIdString;
//	private String fmmMapIndex;
	private String completionCommitmentTypeCode;
	private CompletionCommitmentType completionCommitmentType;
	private int sequence;
	private String suggestedByNodeIdString;
	private CommunityMember suggestedBy;
	private Date suggestedTimestamp;
	private String proposedByNodeIdString;
	private CommunityMember proposedBy;
	private Date proposedTimestamp;
	private String confirmedByNodeId;
	private CommunityMember confirmedBy;
	private Date confirmedTimestamp;
	private String decinedByNodeId;
	private CommunityMember declinedBy;
	private Date declinedTimestamp;
	private String canceledByNodeId;
	private CommunityMember canceledBy;
	private Date canceledTimestamp;

    public FmmCommitmentNodeImpl(NodeId aNodeId, String aParentNodeIdString, String aChildNodeIdString) {
        super(aNodeId);
        this.parentNodeIdString = aParentNodeIdString;
        this.childNodeIdString = aChildNodeIdString;
//        this.fmmMapIndex = buildFmmMapIndex();
    }

	public FmmCommitmentNodeImpl(Class<? extends FmmCommitmentNodeImpl> aLinkNodeClass, String aParentNodeIdString, String aChildNodeIdString) {
		this(new NodeId(aLinkNodeClass), aParentNodeIdString, aChildNodeIdString);
	}
	
	@Override
	public CompletionCommitmentType getCompletionCommitmentType() {
		if(this.completionCommitmentType == null && this.completionCommitmentTypeCode != null) {
			this.completionCommitmentType = CompletionCommitmentType.getObjectForName(this.completionCommitmentTypeCode);
		}
		return this.completionCommitmentType;
	}

	@Override
	public String getCompletionCommitmentTypeCode() {
		return this.completionCommitmentTypeCode;
	}

	@Override
	public void setCompletionCommitmentType(String completionCommitmentTypeCode) {
		this.completionCommitmentTypeCode = completionCommitmentTypeCode;
		this.completionCommitmentType = null;
	}

	@Override
	public void setCompletionCommitmentType(CompletionCommitmentType aCompletionCommitmentType) {
		this.completionCommitmentType = aCompletionCommitmentType;
		this.completionCommitmentTypeCode = aCompletionCommitmentType.getName();
	}

	@Override
	public String getParentNodeIdString() {
		return this.parentNodeIdString;
	}

	@Override
	public void setParentNodeIdString(String aNodeIdString) {
		this.parentNodeIdString = aNodeIdString;
	}

	@Override
	public String getChildNodeIdString() {
		return this.childNodeIdString;
	}

	@Override
	public void setChildNodeIdString(String aNodeIdString) {
		this.childNodeIdString = aNodeIdString;
	}

	@Override
	public int getSequence() {
		return this.sequence;
	}

	@Override
	public void setSequence(int aSequence) {
		this.sequence = aSequence;
	}

	@Override
	public int getSequence(FmmNodeDefinition anFmmNodeDefinition) {
		return this.sequence;
	}

	@Override
	public void setSequence(int aSequence, FmmNodeDefinition anFmmNodeDefinition) {
		this.sequence = aSequence;
	}

	@Override
	public String getSuggestedByNodeIdString() {
		return this.suggestedByNodeIdString;
	}

	@Override
	public void setSuggestedBy(String aSuggestedByNodeId) {
		this.suggestedByNodeIdString = aSuggestedByNodeId;
		this.suggestedBy = null;
	}

	@Override
	public CommunityMember getSuggestedBy() {
		if(this.suggestedBy == null && this.suggestedByNodeIdString != null) {
			this.suggestedBy = FmmDatabaseMediator.getActiveMediator().retrieveCommunityMember(this.suggestedByNodeIdString);
		}
		return this.suggestedBy;
	}

	@Override
	public void setSuggestedBy(CommunityMember aCommunityMember) {
		this.suggestedBy = aCommunityMember;
		this.suggestedByNodeIdString = aCommunityMember.getNodeIdString();
	}

	@Override
	public Date getSuggestedTimestamp() {
		return this.suggestedTimestamp;
	}

	@Override
	public String getSuggestedTimestampString() {
		return this.suggestedTimestamp == null ? "" : GcgDateHelper.getIso8601DateString(this.suggestedTimestamp);
	}

	@Override
	public void setSuggestedTimestamp(Date SuggestedTimestamp) {
		this.suggestedTimestamp = SuggestedTimestamp;
	}

	@Override
	public String getProposedByNodeIdString() {
		return this.proposedByNodeIdString;
	}

	@Override
	public void setProposedBy(String aProposedByNodeId) {
		this.proposedByNodeIdString = aProposedByNodeId;
		this.proposedBy = null;
	}

	@Override
	public CommunityMember getProposedBy() {
		if(this.proposedBy == null && this.proposedByNodeIdString != null) {
			this.proposedBy = FmmDatabaseMediator.getActiveMediator().retrieveCommunityMember(this.proposedByNodeIdString);
		}
		return this.proposedBy;
	}

	@Override
	public void setProposedBy(CommunityMember aCommunityMember) {
		this.proposedBy = aCommunityMember;
		this.proposedByNodeIdString = aCommunityMember.getNodeIdString();
	}

	@Override
	public Date getProposedTimestamp() {
		return this.proposedTimestamp;
	}

	@Override
	public String getProposedTimestampString() {
		return GcgDateHelper.getIso8601DateString(this.proposedTimestamp);
	}

	@Override
	public void setProposedTimestamp(Date proposedTimestamp) {
		this.proposedTimestamp = proposedTimestamp;
	}

	@Override
	public String getConfirmedByNodeIdString() {
		return this.confirmedByNodeId;
	}

	@Override
	public void setConfirmedBy(String confirmedByNodeId) {
		this.confirmedByNodeId = confirmedByNodeId;
		this.confirmedBy = null;
	}

	@Override
	public CommunityMember getConfirmedBy() {
		if(this.confirmedBy == null && this.confirmedByNodeId != null) {
			this.confirmedBy = FmmDatabaseMediator.getActiveMediator().retrieveCommunityMember(this.confirmedByNodeId);
		}
		return this.confirmedBy;
	}

	@Override
	public void setConfirmedBy(CommunityMember aCommunityMember) {
		this.confirmedBy = aCommunityMember;
		this.confirmedByNodeId = aCommunityMember.getNodeIdString();
	}

	@Override
	public Date getConfirmedTimestamp() {
		return this.confirmedTimestamp;
	}

	@Override
	public String getConfirmedTimestampString() {
		return GcgDateHelper.getIso8601DateString(this.confirmedTimestamp);
	}

	@Override
	public void setConfirmedTimestamp(Date confirmedTimestamp) {
		this.confirmedTimestamp = confirmedTimestamp;
	}

	@Override
	public String getDeclinedByNodeIdString() {
		return this.decinedByNodeId;
	}

	@Override
	public void setDeclinedBy(String declinedByNodeId) {
		this.decinedByNodeId = declinedByNodeId;
		this.declinedBy = null;
	}

	@Override
	public CommunityMember getDeclinedBy() {
		if(this.declinedBy == null && this.decinedByNodeId != null) {
			this.declinedBy = FmmDatabaseMediator.getActiveMediator().retrieveCommunityMember(this.decinedByNodeId);
		}
		return this.declinedBy;
	}

	@Override
	public void setDeclinedBy(CommunityMember aCommunityMember) {
		this.declinedBy = aCommunityMember;
		this.decinedByNodeId = aCommunityMember.getNodeIdString();
	}

	@Override
	public Date getDeclinedTimestamp() {
		return this.declinedTimestamp;
	}

	@Override
	public String getDeclinedTimestampString() {
		return GcgDateHelper.getIso8601DateString(this.declinedTimestamp);
	}

	@Override
	public void setDeclinedTimestamp(Date declinedTimestamp) {
		this.declinedTimestamp = declinedTimestamp;
	}

	@Override
	public String getCanceledByNodeIdString() {
		return this.canceledByNodeId;
	}

	@Override
	public CommunityMember getCanceledBy() {
		if(this.canceledBy == null && this.canceledByNodeId != null) {
			this.canceledBy = FmmDatabaseMediator.getActiveMediator().retrieveCommunityMember(this.canceledByNodeId);
		}
		return this.canceledBy;
	}

	@Override
	public void setCanceledBy(String canceledByNodeId) {
		this.canceledByNodeId = canceledByNodeId;
		this.canceledBy = null;
	}

	@Override
	public void setCanceledBy(CommunityMember aCommunityMember) {
		this.canceledBy = aCommunityMember;
		this.canceledByNodeId = aCommunityMember.getNodeIdString();
	}

	@Override
	public Date getCanceledTimestamp() {
		return this.canceledTimestamp;
	}

	@Override
	public String getCanceledTimestampString() {
		return GcgDateHelper.getIso8601DateString(this.canceledTimestamp);
	}

	@Override
	public void setCanceledTimestamp(Date canceledTimestamp) {
		this.canceledTimestamp = canceledTimestamp;
	}

//	@Override
//	public String buildFmmMapIndex() {
//		return getParentNodeIdString() + "-" + getChildNodeIdString();
//	}
//
//	@Override
//	public String getFmmMapIndex() {
//		return this.fmmMapIndex;
//	}

	@Override
	public String getLinkedByNodeId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getLinkedDateTime() {
		// TODO Auto-generated method stub
		return null;
	}

}
