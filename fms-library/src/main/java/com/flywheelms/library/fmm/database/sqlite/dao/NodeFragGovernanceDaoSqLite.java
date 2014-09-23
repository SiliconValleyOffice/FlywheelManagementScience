/* @(#)NodeFragGovernanceDaoSqLite.java
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

package com.flywheelms.library.fmm.database.sqlite.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.flywheelms.gcongui.gcg.widget.date.GcgDateHelper;
import com.flywheelms.library.fca.FlywheelCommunityAuthentication;
import com.flywheelms.library.fmm.meta_data.IdNodeMetaData;
import com.flywheelms.library.fmm.meta_data.NodeFragGovernanceMetaData;
import com.flywheelms.library.fmm.meta_data.NodeFragMetaData;
import com.flywheelms.library.fmm.node.impl.enumerator.AssignmentCommitmentType;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.enumerator.GovernanceRole;
import com.flywheelms.library.fmm.node.impl.enumerator.GovernanceTarget;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragGovernance;

import java.util.HashMap;

public class NodeFragGovernanceDaoSqLite extends NodeFragDaoSqLite<NodeFragGovernance> {

	private static NodeFragGovernanceDaoSqLite singleton;

	public static NodeFragGovernanceDaoSqLite getInstance() {
		if(NodeFragGovernanceDaoSqLite.singleton == null) {
			NodeFragGovernanceDaoSqLite.singleton = new NodeFragGovernanceDaoSqLite();
		}
		return NodeFragGovernanceDaoSqLite.singleton;
	}
	
	@Override
	public FmmNodeDefinition getFmmNodeDefinition() {
		return FmmNodeDefinition.NODE_FRAG__GOVERNANCE;
	}

	@Override
	protected void buildColumnIndexMap(Cursor aCursor) {
		super.buildColumnIndexMap(aCursor);

		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragGovernanceMetaData.column_GOVERNANCE_TARGET);

		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragGovernanceMetaData.column_SPONSOR_ID);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragGovernanceMetaData.column_SPONSOR_COMMITMENT);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragGovernanceMetaData.column_SPONSOR_COMMITMENT_TIMESTAMP);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragGovernanceMetaData.column_SPONSOR_PROPOSED_BY_ID);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragGovernanceMetaData.column_SPONSOR_PROPOSED_TIMESTAMP);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragGovernanceMetaData.column_SPONSOR_LOCKED_BY_ID);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragGovernanceMetaData.column_SPONSOR_LOCKED_TIMESTAMP);

		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragGovernanceMetaData.column_CUSTOMER_ID);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragGovernanceMetaData.column_CUSTOMER_COMMITMENT);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragGovernanceMetaData.column_CUSTOMER_COMMITMENT_TIMESTAMP);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragGovernanceMetaData.column_CUSTOMER_PROPOSED_BY_ID);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragGovernanceMetaData.column_CUSTOMER_PROPOSED_TIMESTAMP);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragGovernanceMetaData.column_CUSTOMER_LOCKED_BY_ID);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragGovernanceMetaData.column_CUSTOMER_LOCKED_TIMESTAMP);

		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragGovernanceMetaData.column_FACILITATOR_ID);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragGovernanceMetaData.column_FACILITATOR_COMMITMENT);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragGovernanceMetaData.column_FACILITATOR_COMMITMENT_TIMESTAMP);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragGovernanceMetaData.column_FACILITATOR_PROPOSED_BY_ID);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragGovernanceMetaData.column_FACILITATOR_PROPOSED_TIMESTAMP);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragGovernanceMetaData.column_FACILITATOR_LOCKED_BY_ID);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragGovernanceMetaData.column_FACILITATOR_LOCKED_TIMESTAMP);

		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragGovernanceMetaData.column_ADMINISTRATOR_ID);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragGovernanceMetaData.column_ADMINISTRATOR_COMMITMENT);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragGovernanceMetaData.column_ADMINISTRATOR_COMMITMENT_TIMESTAMP);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragGovernanceMetaData.column_ADMINISTRATOR_PROPOSED_BY_ID);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragGovernanceMetaData.column_ADMINISTRATOR_PROPOSED_TIMESTAMP);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragGovernanceMetaData.column_ADMINISTRATOR_LOCKED_BY_ID);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragGovernanceMetaData.column_ADMINISTRATOR_LOCKED_TIMESTAMP);
	}

	@Override
	protected void getColumnValues(HashMap<String, Integer> aHashMap, Cursor aCursor, NodeFragGovernance aNodeFragGovernance) {
		// we don't call super because the node is constructed with the NodeId
		aNodeFragGovernance.setGovernanceTarget(GovernanceTarget.getObjectForName(aCursor.getString(aHashMap.get(NodeFragGovernanceMetaData.column_GOVERNANCE_TARGET))));
		if(aCursor.getString(aHashMap.get(NodeFragGovernanceMetaData.column_SPONSOR_ID)) != null) {
			aNodeFragGovernance.rehydrateGovernanceTeamMember(GovernanceRole.SPONSOR,
					FlywheelCommunityAuthentication.getInstance().getCommunityMember(aCursor.getString(aHashMap.get(NodeFragGovernanceMetaData.column_SPONSOR_ID))),
					AssignmentCommitmentType.getObjectForName(aCursor.getString(aHashMap.get(NodeFragGovernanceMetaData.column_SPONSOR_COMMITMENT))),
					GcgDateHelper.getDateFromIso8601DateString(aCursor.getString(aHashMap.get(NodeFragGovernanceMetaData.column_SPONSOR_COMMITMENT_TIMESTAMP))),
					FlywheelCommunityAuthentication.getInstance().getCommunityMember(aCursor.getString(aHashMap.get(NodeFragGovernanceMetaData.column_SPONSOR_PROPOSED_BY_ID))),
					GcgDateHelper.getDateFromIso8601DateString(aCursor.getString(aHashMap.get(NodeFragGovernanceMetaData.column_SPONSOR_PROPOSED_TIMESTAMP))),
					aCursor.getInt(aHashMap.get(NodeFragGovernanceMetaData.column_SPONSOR_GOVERNANCE_SATISFACTION)) );
		}
		if(aCursor.getString(aHashMap.get(NodeFragGovernanceMetaData.column_CUSTOMER_ID)) != null) {
			aNodeFragGovernance.rehydrateGovernanceTeamMember(GovernanceRole.CUSTOMER,
					FlywheelCommunityAuthentication.getInstance().getCommunityMember(aCursor.getString(aHashMap.get(NodeFragGovernanceMetaData.column_CUSTOMER_ID))),
					AssignmentCommitmentType.getObjectForName(aCursor.getString(aHashMap.get(NodeFragGovernanceMetaData.column_CUSTOMER_COMMITMENT))),
					GcgDateHelper.getDateFromIso8601DateString(aCursor.getString(aHashMap.get(NodeFragGovernanceMetaData.column_CUSTOMER_COMMITMENT_TIMESTAMP))),
					FlywheelCommunityAuthentication.getInstance().getCommunityMember(aCursor.getString(aHashMap.get(NodeFragGovernanceMetaData.column_CUSTOMER_PROPOSED_BY_ID))),
					GcgDateHelper.getDateFromIso8601DateString(aCursor.getString(aHashMap.get(NodeFragGovernanceMetaData.column_CUSTOMER_PROPOSED_TIMESTAMP))),
					aCursor.getInt(aHashMap.get(NodeFragGovernanceMetaData.column_CUSTOMER_GOVERNANCE_SATISFACTION)) );
		}
		if(aCursor.getString(aHashMap.get(NodeFragGovernanceMetaData.column_FACILITATOR_ID)) != null) {
			aNodeFragGovernance.rehydrateGovernanceTeamMember(GovernanceRole.FACILITATOR,
					FlywheelCommunityAuthentication.getInstance().getCommunityMember(aCursor.getString(aHashMap.get(NodeFragGovernanceMetaData.column_FACILITATOR_ID))),
					AssignmentCommitmentType.getObjectForName(aCursor.getString(aHashMap.get(NodeFragGovernanceMetaData.column_FACILITATOR_COMMITMENT))),
					GcgDateHelper.getDateFromIso8601DateString(aCursor.getString(aHashMap.get(NodeFragGovernanceMetaData.column_FACILITATOR_COMMITMENT_TIMESTAMP))),
					FlywheelCommunityAuthentication.getInstance().getCommunityMember(aCursor.getString(aHashMap.get(NodeFragGovernanceMetaData.column_FACILITATOR_PROPOSED_BY_ID))),
					GcgDateHelper.getDateFromIso8601DateString(aCursor.getString(aHashMap.get(NodeFragGovernanceMetaData.column_FACILITATOR_PROPOSED_TIMESTAMP))),
					aCursor.getInt(aHashMap.get(NodeFragGovernanceMetaData.column_FACILITATOR_GOVERNANCE_SATISFACTION)) );
		}
		if(aCursor.getString(aHashMap.get(NodeFragGovernanceMetaData.column_ADMINISTRATOR_ID)) != null) {
			aNodeFragGovernance.rehydrateGovernanceTeamMember(GovernanceRole.ADMINISTRATOR,
					FlywheelCommunityAuthentication.getInstance().getCommunityMember(aCursor.getString(aHashMap.get(NodeFragGovernanceMetaData.column_ADMINISTRATOR_ID))),
					AssignmentCommitmentType.getObjectForName(aCursor.getString(aHashMap.get(NodeFragGovernanceMetaData.column_ADMINISTRATOR_COMMITMENT))),
					GcgDateHelper.getDateFromIso8601DateString(aCursor.getString(aHashMap.get(NodeFragGovernanceMetaData.column_ADMINISTRATOR_COMMITMENT_TIMESTAMP))),
					FlywheelCommunityAuthentication.getInstance().getCommunityMember(aCursor.getString(aHashMap.get(NodeFragGovernanceMetaData.column_ADMINISTRATOR_PROPOSED_BY_ID))),
					GcgDateHelper.getDateFromIso8601DateString(aCursor.getString(aHashMap.get(NodeFragGovernanceMetaData.column_ADMINISTRATOR_PROPOSED_TIMESTAMP))),
					aCursor.getInt(aHashMap.get(NodeFragGovernanceMetaData.column_ADMINISTRATOR_GOVERNANCE_SATISFACTION)) );
		}
	}

	@Override
	protected NodeFragGovernance getNextObjectFromCursor(Cursor aCursor) {
		NodeFragGovernance theNodeFragGovernance = null;
		theNodeFragGovernance = new NodeFragGovernance(
				aCursor.getString(this.columnIndexMap.get(IdNodeMetaData.column_ID)),
				aCursor.getString(this.columnIndexMap.get(NodeFragMetaData.column_PARENT_ID)) );
		getColumnValues(this.columnIndexMap, aCursor, theNodeFragGovernance);
		return theNodeFragGovernance;
	}

	@Override
	public ContentValues buildContentValues(NodeFragGovernance aNodeFragGovernance) {
		ContentValues theContentValues = super.buildContentValues(aNodeFragGovernance);
		theContentValues.put(NodeFragGovernanceMetaData.column_GOVERNANCE_TARGET, aNodeFragGovernance.getGovernanceTargetNodeTypeName());
		if(aNodeFragGovernance.hasGovernanceRole(GovernanceRole.SPONSOR) && aNodeFragGovernance.getTeamMemberNodeIdString(GovernanceRole.SPONSOR) != null) {
			theContentValues.put(NodeFragGovernanceMetaData.column_SPONSOR_ID, aNodeFragGovernance.getTeamMemberNodeIdString(GovernanceRole.SPONSOR));
			theContentValues.put(NodeFragGovernanceMetaData.column_SPONSOR_COMMITMENT, aNodeFragGovernance.getCommitmentType(GovernanceRole.SPONSOR).toString());
			GcgDateHelper.setIso8601DateContentValue(theContentValues, NodeFragGovernanceMetaData.column_SPONSOR_COMMITMENT_TIMESTAMP, aNodeFragGovernance.getCommitmentTimestamp(GovernanceRole.SPONSOR));
			theContentValues.put(NodeFragGovernanceMetaData.column_SPONSOR_PROPOSED_BY_ID, aNodeFragGovernance.getProposedByNodeIdString(GovernanceRole.SPONSOR));
			GcgDateHelper.setIso8601DateContentValue(theContentValues, NodeFragGovernanceMetaData.column_SPONSOR_PROPOSED_TIMESTAMP, aNodeFragGovernance.getProposedTimestamp(GovernanceRole.SPONSOR));
		}
		if(aNodeFragGovernance.hasGovernanceRole(GovernanceRole.CUSTOMER) && aNodeFragGovernance.getTeamMemberNodeIdString(GovernanceRole.CUSTOMER) != null) {
			theContentValues.put(NodeFragGovernanceMetaData.column_CUSTOMER_ID, aNodeFragGovernance.getTeamMemberNodeIdString(GovernanceRole.CUSTOMER));
			theContentValues.put(NodeFragGovernanceMetaData.column_CUSTOMER_COMMITMENT, aNodeFragGovernance.getCommitmentType(GovernanceRole.CUSTOMER).toString());
			GcgDateHelper.setIso8601DateContentValue(theContentValues, NodeFragGovernanceMetaData.column_CUSTOMER_COMMITMENT_TIMESTAMP, aNodeFragGovernance.getCommitmentTimestamp(GovernanceRole.CUSTOMER));
			theContentValues.put(NodeFragGovernanceMetaData.column_CUSTOMER_PROPOSED_BY_ID, aNodeFragGovernance.getProposedByNodeIdString(GovernanceRole.CUSTOMER));
			GcgDateHelper.setIso8601DateContentValue(theContentValues, NodeFragGovernanceMetaData.column_CUSTOMER_PROPOSED_TIMESTAMP, aNodeFragGovernance.getProposedTimestamp(GovernanceRole.CUSTOMER));
		}
		if(aNodeFragGovernance.hasGovernanceRole(GovernanceRole.FACILITATOR) && aNodeFragGovernance.getTeamMemberNodeIdString(GovernanceRole.FACILITATOR) != null) {
			theContentValues.put(NodeFragGovernanceMetaData.column_FACILITATOR_ID, aNodeFragGovernance.getTeamMemberNodeIdString(GovernanceRole.FACILITATOR));
			theContentValues.put(NodeFragGovernanceMetaData.column_FACILITATOR_COMMITMENT, aNodeFragGovernance.getCommitmentType(GovernanceRole.FACILITATOR).toString());
			GcgDateHelper.setIso8601DateContentValue(theContentValues, NodeFragGovernanceMetaData.column_FACILITATOR_COMMITMENT_TIMESTAMP, aNodeFragGovernance.getCommitmentTimestamp(GovernanceRole.FACILITATOR));
			theContentValues.put(NodeFragGovernanceMetaData.column_FACILITATOR_PROPOSED_BY_ID, aNodeFragGovernance.getProposedByNodeIdString(GovernanceRole.FACILITATOR));
			GcgDateHelper.setIso8601DateContentValue(theContentValues, NodeFragGovernanceMetaData.column_FACILITATOR_PROPOSED_TIMESTAMP, aNodeFragGovernance.getProposedTimestamp(GovernanceRole.FACILITATOR));
		}
		if(aNodeFragGovernance.hasGovernanceRole(GovernanceRole.ADMINISTRATOR) && aNodeFragGovernance.getTeamMemberNodeIdString(GovernanceRole.ADMINISTRATOR) != null) {
			theContentValues.put(NodeFragGovernanceMetaData.column_ADMINISTRATOR_ID, aNodeFragGovernance.getTeamMemberNodeIdString(GovernanceRole.ADMINISTRATOR));
			theContentValues.put(NodeFragGovernanceMetaData.column_ADMINISTRATOR_COMMITMENT, aNodeFragGovernance.getCommitmentType(GovernanceRole.ADMINISTRATOR).toString());
			GcgDateHelper.setIso8601DateContentValue(theContentValues, NodeFragGovernanceMetaData.column_ADMINISTRATOR_COMMITMENT_TIMESTAMP, aNodeFragGovernance.getCommitmentTimestamp(GovernanceRole.ADMINISTRATOR));
			theContentValues.put(NodeFragGovernanceMetaData.column_ADMINISTRATOR_PROPOSED_BY_ID, aNodeFragGovernance.getProposedByNodeIdString(GovernanceRole.ADMINISTRATOR));
			GcgDateHelper.setIso8601DateContentValue(theContentValues, NodeFragGovernanceMetaData.column_ADMINISTRATOR_PROPOSED_TIMESTAMP, aNodeFragGovernance.getProposedTimestamp(GovernanceRole.ADMINISTRATOR));
		}
		return theContentValues;
	}

}
