/* @(#)FmmGovernableNodeImpl.java
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

package com.flywheelms.library.fmm.node.impl.governable;

import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorFacilitationIssue;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorFacilitator;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorGovernance;
import com.flywheelms.library.fmm.meta_data.HeadlineNodeMetaData;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.enumerator.GovernanceRole;
import com.flywheelms.library.fmm.node.impl.headline.FmmHeadlineNodeImpl;
import com.flywheelms.library.fmm.node.impl.nodefrag.GovernanceTeamMember;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragGovernance;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmGovernableNode;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class FmmGovernableNodeImpl extends FmmHeadlineNodeImpl implements FmmGovernableNode {
	
	private transient NodeFragGovernance nodeFragGovernance;
	
	private static final long serialVersionUID = -3367631872229170309L;

	public FmmGovernableNodeImpl(NodeId aNodeId) {
		super(aNodeId);
	}
	
	public FmmGovernableNodeImpl(Class<? extends FmmGovernableNodeImpl> aClass, JSONObject aJsonObject) {
		super(aClass, aJsonObject);
		try {
			if(aJsonObject.has(HeadlineNodeMetaData.node_frag_GOVERNANCE)) {
				setGovernance(aJsonObject.getJSONObject(HeadlineNodeMetaData.node_frag_GOVERNANCE));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public NodeFragGovernance getNodeFragGovernance() {
		if(this.nodeFragGovernance == null) {
			this.nodeFragGovernance = FmmDatabaseMediator.getActiveMediator().getNodeFragGovernanceForParentOrCreate(getNodeIdString());
		}
		return this.nodeFragGovernance;
	}

	@Override
	public GovernanceTeamMember getGovernanceTeamMember(
			GovernanceRole aGovernanceRole) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FmsDecoratorGovernance getDecoratorGovernance() {
		return getNodeFragGovernance().getDecoratorGovernance();
	}

	@Override
	public FmsDecoratorFacilitationIssue getDecoratorFacilitationIssue() {
		return FmsDecoratorFacilitationIssue.FACILITATION_ISSUE_QUALITY_NOT_ENABLED;
	}

	@Override
	public FmsDecoratorFacilitator getDecoratorFacilitator() {
		return FmsDecoratorFacilitator.FACILITATOR_QUALITY_NOT_ENABLED;
	}

	public void setGovernance(JSONObject aJsonObject) {
		this.nodeFragGovernance = new NodeFragGovernance(aJsonObject);
	}

	@Override
	public JSONObject getJsonObject() {
		JSONObject theJsonObject = super.getJsonObject();
		try {
			theJsonObject.put(HeadlineNodeMetaData.node_frag_GOVERNANCE, getNodeFragGovernance().getJsonObject());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return theJsonObject;
	}
	
}
