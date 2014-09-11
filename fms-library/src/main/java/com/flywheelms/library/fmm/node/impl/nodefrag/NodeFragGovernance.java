/* @(#)NodeFragGovernance.java
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
import com.flywheelms.library.fmm.deckangl.FmsDecoratorGovernance;
import com.flywheelms.library.fmm.meta_data.NodeFragGovernanceMetaData;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.enumerator.AssignmentCommitmentType;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.enumerator.GovernanceParticipationType;
import com.flywheelms.library.fmm.node.impl.enumerator.GovernanceRole;
import com.flywheelms.library.fmm.node.impl.enumerator.GovernanceSatisfaction;
import com.flywheelms.library.fmm.node.impl.enumerator.GovernanceTarget;
import com.flywheelms.library.fmm.node.impl.governable.CommunityMember;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmNode;
import com.flywheelms.library.util.JsonHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

public class NodeFragGovernance extends FmmNodeFragLockableImpl {

	private GovernanceTarget governanceTarget;
	private Hashtable<GovernanceRole, GovernanceTeamMember> governanceTeam = new Hashtable<GovernanceRole, GovernanceTeamMember>();
	
	public NodeFragGovernance(String aParentNodeIdString) {
		super(NodeFragGovernance.class, aParentNodeIdString);
	}
	
	public NodeFragGovernance(Class<? extends FmmNode> aClass) {
		super(NodeFragGovernance.class, NodeId.getTemporaryNodeIdString(aClass));
	}

	// rehydrate from database
	public NodeFragGovernance(String aNodeIdString, String aParentNodeIdString) {
		super(NodeId.hydrate(NodeFragGovernance.class, aNodeIdString), aParentNodeIdString);
	}
	
	// rehydrate from serialization
	public NodeFragGovernance(JSONObject aJsonObject) {
		super(NodeFragGovernance.class, aJsonObject);
		getGovernanceAttributes(aJsonObject);
	}

	private void getGovernanceAttributes(JSONObject aJsonObject) {
		try {
			setGovernanceTarget(GovernanceTarget.getObjectForName(aJsonObject.getString(NodeFragGovernanceMetaData.column_GOVERNANCE_TARGET)));
			if(! aJsonObject.optString(NodeFragGovernanceMetaData.column_SPONSOR_ID).equals("")) {
				rehydrateGovernanceTeamMember(
					GovernanceRole.SPONSOR,
					FlywheelCommunityAuthentication.getInstance().getCommunityMember(aJsonObject.getString(NodeFragGovernanceMetaData.column_SPONSOR_ID)),
					AssignmentCommitmentType.getObjectForName(aJsonObject.getString(NodeFragGovernanceMetaData.column_SPONSOR_COMMITMENT)),
					JsonHelper.getDate(aJsonObject, NodeFragGovernanceMetaData.column_SPONSOR_COMMITMENT_TIMESTAMP),
					FlywheelCommunityAuthentication.getInstance().getCommunityMember(aJsonObject.getString(NodeFragGovernanceMetaData.column_SPONSOR_PROPOSED_BY_ID)),
					JsonHelper.getDate(aJsonObject, NodeFragGovernanceMetaData.column_SPONSOR_PROPOSED_TIMESTAMP),
					aJsonObject.getInt(NodeFragGovernanceMetaData.column_SPONSOR_GOVERNANCE_SATISFACTION) );
				
			}
			if(! aJsonObject.optString(NodeFragGovernanceMetaData.column_CUSTOMER_ID).equals("")) {
				rehydrateGovernanceTeamMember(
					GovernanceRole.CUSTOMER,
					FlywheelCommunityAuthentication.getInstance().getCommunityMember(aJsonObject.getString(NodeFragGovernanceMetaData.column_CUSTOMER_ID)),
					AssignmentCommitmentType.getObjectForName(aJsonObject.getString(NodeFragGovernanceMetaData.column_CUSTOMER_COMMITMENT)),
					JsonHelper.getDate(aJsonObject, NodeFragGovernanceMetaData.column_CUSTOMER_COMMITMENT_TIMESTAMP),
					FlywheelCommunityAuthentication.getInstance().getCommunityMember(aJsonObject.getString(NodeFragGovernanceMetaData.column_CUSTOMER_PROPOSED_BY_ID)),
					JsonHelper.getDate(aJsonObject, NodeFragGovernanceMetaData.column_CUSTOMER_PROPOSED_TIMESTAMP),
					aJsonObject.getInt(NodeFragGovernanceMetaData.column_CUSTOMER_GOVERNANCE_SATISFACTION) );
			}
			if(! aJsonObject.optString(NodeFragGovernanceMetaData.column_FACILITATOR_ID).equals("")) {
				rehydrateGovernanceTeamMember(
					GovernanceRole.FACILITATOR,
					FlywheelCommunityAuthentication.getInstance().getCommunityMember(aJsonObject.getString(NodeFragGovernanceMetaData.column_FACILITATOR_ID)),
					AssignmentCommitmentType.getObjectForName(aJsonObject.getString(NodeFragGovernanceMetaData.column_FACILITATOR_COMMITMENT)),
					JsonHelper.getDate(aJsonObject, NodeFragGovernanceMetaData.column_FACILITATOR_COMMITMENT_TIMESTAMP),
					FlywheelCommunityAuthentication.getInstance().getCommunityMember(aJsonObject.getString(NodeFragGovernanceMetaData.column_FACILITATOR_PROPOSED_BY_ID)),
					JsonHelper.getDate(aJsonObject, NodeFragGovernanceMetaData.column_FACILITATOR_PROPOSED_TIMESTAMP),
					aJsonObject.getInt(NodeFragGovernanceMetaData.column_FACILITATOR_GOVERNANCE_SATISFACTION) );
			}
			if(! aJsonObject.optString(NodeFragGovernanceMetaData.column_ADMINISTRATOR_ID).equals("")) {
				rehydrateGovernanceTeamMember(
					GovernanceRole.ADMINISTRATOR,
					FlywheelCommunityAuthentication.getInstance().getCommunityMember(aJsonObject.getString(NodeFragGovernanceMetaData.column_ADMINISTRATOR_ID)),
					AssignmentCommitmentType.getObjectForName(aJsonObject.getString(NodeFragGovernanceMetaData.column_ADMINISTRATOR_COMMITMENT)),
					JsonHelper.getDate(aJsonObject, NodeFragGovernanceMetaData.column_ADMINISTRATOR_COMMITMENT_TIMESTAMP),
					FlywheelCommunityAuthentication.getInstance().getCommunityMember(aJsonObject.getString(NodeFragGovernanceMetaData.column_ADMINISTRATOR_PROPOSED_BY_ID)),
					JsonHelper.getDate(aJsonObject, NodeFragGovernanceMetaData.column_ADMINISTRATOR_PROPOSED_TIMESTAMP),
					aJsonObject.getInt(NodeFragGovernanceMetaData.column_ADMINISTRATOR_GOVERNANCE_SATISFACTION) );
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public GovernanceTarget getGovernanceTarget() {
		if(this.governanceTarget == null) {
			FmmNodeDefinition theFmmNodeDefinition =
					FmmNodeDefinition.getEntryForNodeTypeCode(NodeId.getNodeTypeCodeFromNodeIdString(this.getParentNodeIdString()));
			this.governanceTarget = 
					GovernanceTarget.getObjectForFmmDictionaryEntry(theFmmNodeDefinition);
		}
		return this.governanceTarget;
	}

	public void setGovernanceTarget(GovernanceTarget aGovernanceTarget) {
		this.governanceTarget = aGovernanceTarget;
	}
	
	public FmmNodeDefinition getTargetFmmNodeDefinition() {
		return getGovernanceTarget().getTargetNodeDictionaryEntry();
	}
	
	public Collection<GovernanceTeamMember> getGovernanceTeamCollection() {
		return this.governanceTeam.values();
	}
	
	public Set<GovernanceRole> getExistingGovernanceTeamRoles() {
		return this.governanceTeam.keySet();
	}
	
	public List<GovernanceRole> getRequiredGovernanceTeamRoles() {
		return getGovernanceTarget().getRequiredGovernanceRoleList();
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////
	
	private boolean isExistingGovernanceTeamRole(GovernanceRole aGovernanceRole) {
		return this.governanceTeam.keySet().contains(aGovernanceRole);
	}

	public GovernanceParticipationType getParticipationType(GovernanceRole aGovernanceRole) {
		if(isExistingGovernanceTeamRole(aGovernanceRole)) {
			return this.governanceTeam.get(aGovernanceRole).participationType;
		}
		return null;
	}

	public void setParticipationType(GovernanceRole aGovernanceRole, GovernanceParticipationType aParticipationType) {
		if(isExistingGovernanceTeamRole(aGovernanceRole)) {
			this.governanceTeam.get(aGovernanceRole).participationType = aParticipationType;
		}
	}

	public String getTeamMemberNodeIdString(GovernanceRole aGovernanceRole) {
		if(isExistingGovernanceTeamRole(aGovernanceRole)) {
			return this.governanceTeam.get(aGovernanceRole).communityMember.getNodeIdString();
		}
		return null;
	}

	public CommunityMember getTeamMember(GovernanceRole aGovernanceRole) {
		if(isExistingGovernanceTeamRole(aGovernanceRole)) {
			return this.governanceTeam.get(aGovernanceRole).communityMember;
		}
		return null;
	}

	public void setTeamMember(GovernanceRole aGovernanceRole, CommunityMember aCommunityMember) {
		if(isExistingGovernanceTeamRole(aGovernanceRole)) {
			this.governanceTeam.get(aGovernanceRole).communityMember = aCommunityMember;
		}
		this.governanceTeam.put(aGovernanceRole, new GovernanceTeamMember(aGovernanceRole, aCommunityMember));
	}

	public AssignmentCommitmentType getAssignmentCommitmentType(GovernanceRole aGovernanceRole) {
		if(isExistingGovernanceTeamRole(aGovernanceRole)) {
			return this.governanceTeam.get(aGovernanceRole).commitmentType;
		}
		return null;
	}

	public void setAssignmentCommitmentType(GovernanceRole aGovernanceRole, AssignmentCommitmentType anAssignmentCommitmentType) {
		if(isExistingGovernanceTeamRole(aGovernanceRole)) {
			this.governanceTeam.get(aGovernanceRole).commitmentType = anAssignmentCommitmentType;
		}
	}

	public void setAssignmentCommitmentType(GovernanceRole aGovernanceRole, AssignmentCommitmentType anAssignmentCommitmentType, Date aCommitmentTimestamp) {
		if(isExistingGovernanceTeamRole(aGovernanceRole)) {
			this.governanceTeam.get(aGovernanceRole).commitmentType = anAssignmentCommitmentType;
			this.governanceTeam.get(aGovernanceRole).commitmentTimestamp = aCommitmentTimestamp;
		}
	}

	public Date getCommitmentTimestamp(GovernanceRole aGovernanceRole) {
		if(isExistingGovernanceTeamRole(aGovernanceRole)) {
			return this.governanceTeam.get(aGovernanceRole).commitmentTimestamp;
		}
		return null;
	}

	public void setCommitmentTimestamp(GovernanceRole aGovernanceRole, Date aCommitmentTimestamp) {
		if(isExistingGovernanceTeamRole(aGovernanceRole)) {
			this.governanceTeam.get(aGovernanceRole).commitmentTimestamp = aCommitmentTimestamp;
		}
	}

	public String getProposedByNodeIdString(GovernanceRole aGovernanceRole) {
		if(isExistingGovernanceTeamRole(aGovernanceRole)) {
			return this.governanceTeam.get(aGovernanceRole).proposedByCommunityMember.getNodeIdString();
		}
		return null;
	}

	public CommunityMember getProposedBy(GovernanceRole aGovernanceRole) {
		if(isExistingGovernanceTeamRole(aGovernanceRole)) {
			return this.governanceTeam.get(aGovernanceRole).proposedByCommunityMember;
		}
		return null;
	}

	public void setProposedBy(GovernanceRole aGovernanceRole, CommunityMember aCommunityMember) {
		if(isExistingGovernanceTeamRole(aGovernanceRole)) {
			this.governanceTeam.get(aGovernanceRole).proposedByCommunityMember = aCommunityMember;
		}
	}

	public Date getProposedTimestamp(GovernanceRole aGovernanceRole) {
		if(isExistingGovernanceTeamRole(aGovernanceRole)) {
			return this.governanceTeam.get(aGovernanceRole).proposedTimestamp;
		}
		return null;
	}

	public int getGovernanceSatisfactionScore(GovernanceRole aGovernanceRole) {
		if(isExistingGovernanceTeamRole(aGovernanceRole)) {
			return this.governanceTeam.get(aGovernanceRole).governanceSatisfaction.getScore();
		}
		return 0;
	}

	public GovernanceSatisfaction getGovernanceSatisfaction(GovernanceRole aGovernanceRole) {
		if(isExistingGovernanceTeamRole(aGovernanceRole)) {
			return this.governanceTeam.get(aGovernanceRole).governanceSatisfaction;
		}
		return GovernanceSatisfaction.UNRATED;
	}
	
	public void setProposedTimestamp(GovernanceRole aGovernanceRole, Date aProposedTimestamp) {
		if(isExistingGovernanceTeamRole(aGovernanceRole)) {
			this.governanceTeam.get(aGovernanceRole).proposedTimestamp = aProposedTimestamp;
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////

	public int getRequiredGovernanceCount() {
		return getRequiredGovernanceTeamRoles().size();
	}
	
	/*
	 *  TODO - need to OPTIMIZE by utilizing NodeFragTribKnQuality   SDS
	 *  This routine will become updateDecoratorGovernance()
	 *  New getDecoratorGovernance will just read the value from NodeFragTribKnQuality
	 */
	public FmsDecoratorGovernance getDecoratorGovernance() {
//		GovernanceParticipationType sponsorRequirement;
//		GovernanceParticipationType facilitatorRequirement;
//		GovernanceParticipationType customerRequirement;
//		GovernanceParticipationType administratorRequirement;
		////  handle outstanding governance proposals  ////
		if(isNoGovernance()) {
			return FmsDecoratorGovernance.NO_GOVERNANCE;
		}
		if(isProposedGovernance()) {
			return FmsDecoratorGovernance.PROPOSED_GOVERNANCE;
		}
		return null;
	}
	
	private boolean isProposedGovernance() {
		for(GovernanceTeamMember theTeamMember : this.governanceTeam.values()) {
			if(theTeamMember.commitmentType == AssignmentCommitmentType.PROPOSED) {
				return true;
			}
		}
		return false;
	}

	private boolean isNoGovernance() {
		for(GovernanceTeamMember theTeamMember : this.governanceTeam.values()) {
			if(theTeamMember.communityMember != null) {
				return false;
			}
		}
		return true;
	}

	public boolean isGoodGovernance() {
		for(GovernanceRole theGovernanceRole : GovernanceTarget.getGovernanceTargetForNode(getGovernanceTarget()).getRequiredGovernanceRoleList()) {
			if(this.governanceTeam.get(theGovernanceRole) == null) {
				return false;
			}
		}
		for(GovernanceTeamMember theTeamMember : this.governanceTeam.values()) {
			if(theTeamMember.commitmentType != AssignmentCommitmentType.CONFIRMED) {
				return false;
			}
		}
		return true;
	}

	public String getGovernanceTargetNodeTypeName() {
		return getTargetFmmNodeDefinition().getNodeTypeName();
	}
	
	// rehydrate
	public void rehydrateGovernanceTeamMember(
			GovernanceRole aGovernanceRole,
			CommunityMember aCommunityMember,
			AssignmentCommitmentType aCommitmentType,
			Date aCommitmentTimestamp,
			CommunityMember aProposedByCommunityMember,
			Date aProposedTimestamp,
			int aGovernanceScore) {
		GovernanceTeamMember theGovernanceTeamMember = new GovernanceTeamMember(aGovernanceRole, aCommunityMember);
		theGovernanceTeamMember.setCommitment(aCommitmentType, aCommitmentTimestamp);
		theGovernanceTeamMember.setProposed(aProposedByCommunityMember, aProposedTimestamp);
		theGovernanceTeamMember.setGovernanceSatisfaction(aGovernanceScore);
		this.governanceTeam.put(theGovernanceTeamMember.getRole(), theGovernanceTeamMember);
	}

	public AssignmentCommitmentType getCommitmentType(GovernanceRole aGovernanceRole) {
		GovernanceTeamMember theGovernanceTeamMember = this.governanceTeam.get(aGovernanceRole);
		if(theGovernanceTeamMember == null) {
			return null;
		}
		return theGovernanceTeamMember.commitmentType;
	}

	public void setCommitmentType(
			GovernanceRole aGovernanceRole,
			AssignmentCommitmentType aCommitmentType,
			Date aCommitmentTimestamp ) {
		GovernanceTeamMember theGovernanceTeamMember = this.governanceTeam.get(aGovernanceRole);
		if(theGovernanceTeamMember == null) {
			return;
		}
		theGovernanceTeamMember.commitmentType = aCommitmentType;
		theGovernanceTeamMember.commitmentTimestamp = aCommitmentTimestamp;
	}

	public boolean hasGovernanceRole(GovernanceRole aGovernanceRole) {
		return this.governanceTeam.get(aGovernanceRole) == null ? false : true;
	}
	
	@Override
	public JSONObject getJsonObject() {
		JSONObject theJsonObject = super.getJsonObject();
		try {
			theJsonObject.put(NodeFragGovernanceMetaData.column_GOVERNANCE_TARGET, getGovernanceTarget().getName());
			if(hasGovernanceRole(GovernanceRole.SPONSOR) && getTeamMember(GovernanceRole.SPONSOR) != null) {
				theJsonObject.put(NodeFragGovernanceMetaData.column_SPONSOR_ID, getTeamMember(GovernanceRole.SPONSOR).getNodeIdString());
				theJsonObject.put(NodeFragGovernanceMetaData.column_SPONSOR_COMMITMENT, getCommitmentType(GovernanceRole.SPONSOR).toString());
				JsonHelper.putDate(theJsonObject, NodeFragGovernanceMetaData.column_SPONSOR_COMMITMENT_TIMESTAMP, getCommitmentTimestamp(GovernanceRole.SPONSOR));
				theJsonObject.put(NodeFragGovernanceMetaData.column_SPONSOR_PROPOSED_BY_ID, getProposedBy(GovernanceRole.SPONSOR).getNodeIdString());
				JsonHelper.putDate(theJsonObject, NodeFragGovernanceMetaData.column_SPONSOR_PROPOSED_TIMESTAMP, getProposedTimestamp(GovernanceRole.SPONSOR));
				theJsonObject.put(NodeFragGovernanceMetaData.column_SPONSOR_GOVERNANCE_SATISFACTION, getGovernanceSatisfactionScore(GovernanceRole.SPONSOR));
			}
			if(hasGovernanceRole(GovernanceRole.CUSTOMER) && getTeamMember(GovernanceRole.CUSTOMER) != null) {
				theJsonObject.put(NodeFragGovernanceMetaData.column_CUSTOMER_ID, getTeamMember(GovernanceRole.CUSTOMER).getNodeIdString());
				theJsonObject.put(NodeFragGovernanceMetaData.column_CUSTOMER_COMMITMENT, getCommitmentType(GovernanceRole.CUSTOMER).toString());
				JsonHelper.putDate(theJsonObject, NodeFragGovernanceMetaData.column_CUSTOMER_COMMITMENT_TIMESTAMP, getCommitmentTimestamp(GovernanceRole.CUSTOMER));
				theJsonObject.put(NodeFragGovernanceMetaData.column_CUSTOMER_PROPOSED_BY_ID, getProposedBy(GovernanceRole.CUSTOMER).getNodeIdString());
				JsonHelper.putDate(theJsonObject, NodeFragGovernanceMetaData.column_CUSTOMER_PROPOSED_TIMESTAMP, getProposedTimestamp(GovernanceRole.CUSTOMER));
				theJsonObject.put(NodeFragGovernanceMetaData.column_SPONSOR_GOVERNANCE_SATISFACTION, getGovernanceSatisfactionScore(GovernanceRole.CUSTOMER));
			}
			if(hasGovernanceRole(GovernanceRole.FACILITATOR) && getTeamMember(GovernanceRole.FACILITATOR) != null) {
				theJsonObject.put(NodeFragGovernanceMetaData.column_FACILITATOR_ID, getTeamMember(GovernanceRole.FACILITATOR).getNodeIdString());
				theJsonObject.put(NodeFragGovernanceMetaData.column_FACILITATOR_COMMITMENT, getCommitmentType(GovernanceRole.FACILITATOR).toString());
				JsonHelper.putDate(theJsonObject, NodeFragGovernanceMetaData.column_FACILITATOR_COMMITMENT_TIMESTAMP, getCommitmentTimestamp(GovernanceRole.FACILITATOR));
				theJsonObject.put(NodeFragGovernanceMetaData.column_FACILITATOR_PROPOSED_BY_ID, getProposedBy(GovernanceRole.FACILITATOR).getNodeIdString());
				JsonHelper.putDate(theJsonObject, NodeFragGovernanceMetaData.column_FACILITATOR_PROPOSED_TIMESTAMP, getProposedTimestamp(GovernanceRole.FACILITATOR));
				theJsonObject.put(NodeFragGovernanceMetaData.column_SPONSOR_GOVERNANCE_SATISFACTION, getGovernanceSatisfactionScore(GovernanceRole.FACILITATOR));
			}
			if(hasGovernanceRole(GovernanceRole.ADMINISTRATOR) && getTeamMember(GovernanceRole.ADMINISTRATOR) != null) {
				theJsonObject.put(NodeFragGovernanceMetaData.column_ADMINISTRATOR_ID, getTeamMember(GovernanceRole.ADMINISTRATOR).getNodeIdString());
				theJsonObject.put(NodeFragGovernanceMetaData.column_ADMINISTRATOR_COMMITMENT, getCommitmentType(GovernanceRole.ADMINISTRATOR).toString());
				JsonHelper.putDate(theJsonObject, NodeFragGovernanceMetaData.column_ADMINISTRATOR_COMMITMENT_TIMESTAMP, getCommitmentTimestamp(GovernanceRole.ADMINISTRATOR));
				theJsonObject.put(NodeFragGovernanceMetaData.column_ADMINISTRATOR_PROPOSED_BY_ID, getProposedBy(GovernanceRole.ADMINISTRATOR).getNodeIdString());
				JsonHelper.putDate(theJsonObject, NodeFragGovernanceMetaData.column_ADMINISTRATOR_PROPOSED_TIMESTAMP, getProposedTimestamp(GovernanceRole.ADMINISTRATOR));
				theJsonObject.put(NodeFragGovernanceMetaData.column_SPONSOR_GOVERNANCE_SATISFACTION, getGovernanceSatisfactionScore(GovernanceRole.ADMINISTRATOR));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return theJsonObject;
	}
	
	@Override
	public NodeFragGovernance getClone() {
		return new NodeFragGovernance(getJsonObject());
	}

	public GovernanceTeamMember getGovernanceTeamMember(GovernanceRole aGovernanceRole) {
		return this.governanceTeam.get(aGovernanceRole);
	}

}
