/* @(#)CompletionNodeTrash.java
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
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.governable.CommunityMember;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmCompletionNode;

import java.util.Date;

public class CompletionNodeTrash extends FmmNodeFragImpl {
	
	private String searchableHeadline = "";
	private String parentAbbreviatedNodeIdString;
	private String deletedByNodeIdString;
	private Date deletedTimestamp = GcgDateHelper.getCurrentDateTime();
	private String serializedAuditBlock;
	private String serializedCompletion;
	private String serializedFseDocument;
	private String serializedGovernance;
	private String serializedCompletionNode;
	private String serializedTribKnQuality;
	private String serializedWorkTaskBudget;
	private String serializedStrategicCommitment;
	private String serializedTacticalCommitment;
	
	public CompletionNodeTrash(FmmCompletionNode aCompletionNode) {
		super(CompletionNodeTrash.class, aCompletionNode.getNodeIdString());
		this.searchableHeadline = aCompletionNode.getHeadline();
		this.parentAbbreviatedNodeIdString = aCompletionNode.getAbbreviatedNodeIdString();
		this.deletedByNodeIdString = FlywheelCommunityAuthentication.getInstance().getCommunityMember().getNodeIdString();
		this.deletedTimestamp = GcgDateHelper.getCurrentDateTime();
		this.serializedAuditBlock = aCompletionNode.getNodeFragAuditBlock().getSerialized();
		this.serializedCompletion = aCompletionNode.getNodeFragCompletion().getSerialized();
		this.serializedFseDocument = aCompletionNode.getFseDocument().getSerialized();
		this.serializedGovernance = aCompletionNode.getNodeFragGovernance().getSerialized();
		this.serializedCompletionNode = aCompletionNode.getSerialized();
		this.serializedTribKnQuality = aCompletionNode.getNodeFragTribKnQuality().getSerialized();
		this.serializedWorkTaskBudget = aCompletionNode.getNodeFragWorkTaskBudget().getSerialized();
		// commitments do not always apply
	}

	// rehydrate from database
	public CompletionNodeTrash(String aNodeIdString, String aParentNodeIdString) {
		super(NodeId.hydrate(NodeFragAuditBlock.class, aNodeIdString), aParentNodeIdString);
	}

	public String getSearchableHeadline() {
		return this.searchableHeadline;
	}

	public void setSearchableHeadline(String headline) {
		this.searchableHeadline = headline;
	}

	public String getParentAbbreviatedNodeIdString() {
		if((this.parentAbbreviatedNodeIdString == null || this.parentAbbreviatedNodeIdString.length() == 0)) {
			this.parentAbbreviatedNodeIdString = NodeId.getAbbreviatedNodeIdString(getNodeIdString());
		}
		return this.parentAbbreviatedNodeIdString;
	}

	public void setParentAbbreviatedNodeIdString(String abbreviatedNodeIdString) {
		this.parentAbbreviatedNodeIdString = abbreviatedNodeIdString;
	}

	public String getDeletedByNodeIdString() {
		return this.deletedByNodeIdString;
	}

	public void setDeletedBy(String aNodeIdString) {
		this.deletedByNodeIdString = aNodeIdString;
	}

	public CommunityMember getDeletedByCommunityMember() {
		return FmmDatabaseMediator.getActiveMediator().getCommunityMember(this.deletedByNodeIdString);
	}

	public void setDeletedBy(CommunityMember aCommunityMember) {
		this.deletedByNodeIdString = aCommunityMember.getNodeIdString();
	}

	public Date getDeletedTimestamp() {
		return this.deletedTimestamp;
	}

	public void setDeletedTimestamp(Date aTimestamp) {
		this.deletedTimestamp = aTimestamp;
	}

	public String getCreatedByNodeIdString() {
		return this.deletedByNodeIdString;
	}

	public void setCreatedByNodeIdString(String deletedByNodeIdString) {
		this.deletedByNodeIdString = deletedByNodeIdString;
	}

	public String getSerializedAuditBlock() {
		return this.serializedAuditBlock;
	}

	public void setSerializedAuditBlock(String serializedAuditBlock) {
		this.serializedAuditBlock = serializedAuditBlock;
	}

	public String getSerializedCompletion() {
		return this.serializedCompletion;
	}

	public void setSerializedCompletion(String serializedCompletion) {
		this.serializedCompletion = serializedCompletion;
	}

	public String getSerializedFseDocument() {
		return this.serializedFseDocument;
	}

	public void setSerializedFseDocument(String serializedFseDocument) {
		this.serializedFseDocument = serializedFseDocument;
	}

	public String getSerializedGovernance() {
		return this.serializedGovernance;
	}

	public void setSerializedGovernance(String serializedGovernance) {
		this.serializedGovernance = serializedGovernance;
	}

	public String getSerializedCompletionNode() {
		return this.serializedCompletionNode;
	}

	public void setSerializedCompletionNode(String aSerializedCompletionNode) {
		this.serializedCompletionNode = aSerializedCompletionNode;
	}

	public String getSerializedTribKnQuality() {
		return this.serializedTribKnQuality;
	}

	public void setSerializedTribKnQuality(String serializedTribKnQuality) {
		this.serializedTribKnQuality = serializedTribKnQuality;
	}

	public String getSerializedWorkTaskBudget() {
		return this.serializedWorkTaskBudget;
	}

	public void setSerializedWorkTaskBudget(String serializedWorkTaskBudget) {
		this.serializedWorkTaskBudget = serializedWorkTaskBudget;
	}

	public String getSerializedStrategicCommitment() {
		return this.serializedStrategicCommitment;
	}

	public void setSerializedStrategicCommitment(
			String serializedStrategicCommitment) {
		this.serializedStrategicCommitment = serializedStrategicCommitment;
	}

	public String getSerializedTacticalCommitment() {
		return this.serializedTacticalCommitment;
	}

	public void setSerializedTacticalCommitment(String serializedTacticalCommitment) {
		this.serializedTacticalCommitment = serializedTacticalCommitment;
	}

}
