/* @(#)LockConfig.java
** 
** Copyright (C) 2012 by Steven D. Stamps
**
**             Trademarks & Copyrights
** Flywheel Management Science(TM), Flywheel Management Model(TM),
** Flywheel Story Editor(TM) and FlywheelMS(TM) are exclusive trademarks
** of Steven D. Stamps and may only be used freely for the purpose of
** identifying the unforked version of this software.  Subsequent forks
** may not use these trademarks.  All other rights are reserved.
** and FlywheelMS(TM) are exclusive trademarks of Steven D. Stamps
** and may only be used freely for the purpose of identifying the
** unforked version of this software.  Subsequent forks (if any) may
** not use these trademarks.  All other rights are reserved.
**
** DecKanGL (Decorated Kanban Glyph Language) and TribKn (Tribal Knowledge)
** are also exclusive trademarks of Steven D. Stamps.  These may be used
** freely within the unforked FlywheelMS application and documentation.
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

package com.flywheelms.library.fmm.node.impl.enumerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.graphics.drawable.Drawable;

import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.FmmNodeImpl;
import com.flywheelms.library.fmm.node.interfaces.FmmEnumNode;
import com.flywheelms.library.gcg.interfaces.GcgGuiable;

public enum LockConfig implements FmmEnumNode {

	BOOKSHELF_NODE (
			FmmNodeDefinition.BOOKSHELF,
			LockType.NODE,
			true, true, false, true ),
	BOOKSHELF_STORY (
			FmmNodeDefinition.BOOKSHELF,
			LockType.STORY,
			true, true, false, true ),
	BOOKSHELF_NOTES (
			FmmNodeDefinition.BOOKSHELF,
			LockType.NOTES,
			true, true, false, true ),
	COMMUNITY_MEMBER_NODE (
			FmmNodeDefinition.COMMUNITY_MEMBER,
			LockType.NODE,
			true, true, false, true ),
	COMMUNITY_MEMBER_STORY (
			FmmNodeDefinition.COMMUNITY_MEMBER,
			LockType.STORY,
			true, true, false, true ),
	COMMUNITY_MEMBER_NOTES (
			FmmNodeDefinition.COMMUNITY_MEMBER,
			LockType.NOTES,
			true, true, false, true ),
	DISCUSSION_TOPIC_NODE (
			FmmNodeDefinition.DISCUSSION_TOPIC,
			LockType.NODE,
			true, true, false, true ),
	DISCUSSION_TOPIC_STORY (
			FmmNodeDefinition.DISCUSSION_TOPIC,
			LockType.STORY,
			true, true, false, true ),
	DISCUSSION_TOPIC_NOTES (
			FmmNodeDefinition.DISCUSSION_TOPIC,
			LockType.NOTES,
			true, true, false, true ),
	FACILITATION_ISSUE_NODE (
			FmmNodeDefinition.FACILITATION_ISSUE,
			LockType.NODE,
			true, true, false, true ),
	FACILITATION_ISSUE_STORY (
			FmmNodeDefinition.FACILITATION_ISSUE,
			LockType.STORY,
			true, true, false, true ),
	FACILITATION_ISSUE_NOTES (
			FmmNodeDefinition.FACILITATION_ISSUE,
			LockType.NOTES,
			true, true, false, true ),
	FISCAL_YEAR_NODE (
			FmmNodeDefinition.FISCAL_YEAR,
			LockType.NODE,
			true, true, false, true ),
	FISCAL_YEAR_STORY (
			FmmNodeDefinition.FISCAL_YEAR,
			LockType.STORY,
			true, true, false, true ),
	FISCAL_YEAR_NOTES (
			FmmNodeDefinition.FISCAL_YEAR,
			LockType.NOTES,
			true, true, false, true ),
	FISCAL_YEAR_STRATEGIC_MILESTONES (
			FmmNodeDefinition.FISCAL_YEAR,
			LockType.STRATEGIC_MILESTONES,
			true, true, false, true ),
	FISCAL_YEAR_TACTICAL_MILESTONES (
			FmmNodeDefinition.FISCAL_YEAR,
			LockType.TACTICAL_MILESTONES,
			true, true, false, true ),
	FLYWHEEL_COMMITMENT_NODE (
			FmmNodeDefinition.FLYWHEEL_WORK_PACKAGE_COMMITMENT,
			LockType.NODE,
			true, true, false, true ),
	FLYWHEEL_COMMITMENT_STORY (
			FmmNodeDefinition.FLYWHEEL_WORK_PACKAGE_COMMITMENT,
			LockType.STORY,
			true, true, false, true ),
	FLYWHEEL_COMMITMENT_NOTES (
			FmmNodeDefinition.FLYWHEEL_WORK_PACKAGE_COMMITMENT,
			LockType.NOTES,
			true, true, false, true ),
	FLYWHEEL_MILESTONE_NODE (
			FmmNodeDefinition.FLYWHEEL_MILESTONE,
			LockType.NODE,
			true, true, false, true ),
	FLYWHEEL_MILESTONE_STORY (
			FmmNodeDefinition.FLYWHEEL_MILESTONE,
			LockType.STORY,
			true, true, false, true ),
	FLYWHEEL_MILESTONE_NOTES (
			FmmNodeDefinition.FLYWHEEL_MILESTONE,
			LockType.NOTES,
			true, true, false, true ),
	FLYWHEEL_MILESTONE_WORK_PLANS (
			FmmNodeDefinition.FLYWHEEL_MILESTONE,
			LockType.WORK_PLANS,
			true, true, false, true ),
	FLYWHEEL_MILESTONE_WORK_PACKAGES (
			FmmNodeDefinition.FLYWHEEL_MILESTONE,
			LockType.WORK_PACKAGES,
			true, true, false, true ),
	FLYWHEEL_MILESTONE_SERVICE_REQUESTS (
			FmmNodeDefinition.FLYWHEEL_MILESTONE,
			LockType.SERVICE_REQUESTS,
			true, true, false, true ),
	FLYWHEEL_TEAM_NODE (
			FmmNodeDefinition.FLYWHEEL_TEAM,
			LockType.NODE,
			true, true, false, true ),
	FLYWHEEL_TEAM_STORY (
			FmmNodeDefinition.FLYWHEEL_TEAM,
			LockType.STORY,
			true, true, false, true ),
	FLYWHEEL_TEAM_NOTES (
			FmmNodeDefinition.FLYWHEEL_TEAM,
			LockType.NOTES,
			true, true, false, true ),
	FUNCTIONAL_TEAM_NODE (
			FmmNodeDefinition.FUNCTIONAL_TEAM,
			LockType.NODE,
			true, true, false, true ),
	FUNCTIONAL_TEAM_STORY (
			FmmNodeDefinition.FUNCTIONAL_TEAM,
			LockType.STORY,
			true, true, false, true ),
	FUNCTIONAL_TEAM_NOTES (
			FmmNodeDefinition.FUNCTIONAL_TEAM,
			LockType.NOTES,
			true, true, false, true ),
	NOTEBOOK_NODE (
			FmmNodeDefinition.NOTEBOOK,
			LockType.NODE,
			true, true, false, true ),
	NOTEBOOK_STORY (
			FmmNodeDefinition.NOTEBOOK,
			LockType.STORY,
			true, true, false, true ),
	NOTEBOOK_NOTES (
			FmmNodeDefinition.NOTEBOOK,
			LockType.NOTES,
			true, true, false, true ),
	ORGANIZATION_NODE (
			FmmNodeDefinition.FMS_ORGANIZATION,
			LockType.NODE,
			true, true, false, true ),
	ORGANIZATION_STORY (
			FmmNodeDefinition.FMS_ORGANIZATION,
			LockType.STORY,
			true, true, false, true ),
	ORGANIZATION_NOTES (
			FmmNodeDefinition.FMS_ORGANIZATION,
			LockType.NOTES,
			true, true, false, true ),
	ORGANIZATION_FISCAL_YEARS (
			FmmNodeDefinition.FMS_ORGANIZATION,
			LockType.FISCAL_YEARS,
			true, true, false, true ),
	PORTFOLIO_NODE (
			FmmNodeDefinition.PORTFOLIO,
			LockType.NODE,
			true, true, false, true ),
	PORTFOLIO_STORY (
			FmmNodeDefinition.PORTFOLIO,
			LockType.STORY,
			true, true, false, true ),
	PORTFOLIO_NOTES (
			FmmNodeDefinition.PORTFOLIO,
			LockType.NOTES,
			true, true, false, true ),
	PROJECT_NODE (
			FmmNodeDefinition.PROJECT,
			LockType.NODE,
			true, true, false, true ),
	PROJECT_STORY (
			FmmNodeDefinition.PROJECT,
			LockType.STORY,
			true, true, false, true ),
	PROJECT_NOTES (
			FmmNodeDefinition.PROJECT,
			LockType.NOTES,
			true, true, false, true ),
	PROJECT_PROJECT_ASSETS (
			FmmNodeDefinition.PROJECT,
			LockType.PROJECT_ASSETS,
			true, true, false, true ),
	PROJECT_ASSET_NODE (
			FmmNodeDefinition.PROJECT_ASSET,
			LockType.NODE,
			true, true, false, true ),
	PROJECT_ASSET_STORY (
			FmmNodeDefinition.PROJECT_ASSET,
			LockType.STORY,
			true, true, false, true ),
	PROJECT_ASSET_NOTES (
			FmmNodeDefinition.PROJECT_ASSET,
			LockType.NOTES,
			true, true, false, true ),
	PROJECT_ASSET_WORK_PACKAGES (
			FmmNodeDefinition.PROJECT_ASSET,
			LockType.WORK_PACKAGES,
			true, true, false, true ),
	REPOSITORY_NODE (
			FmmNodeDefinition.FMM_CONFIGURATION,
			LockType.NODE,
			true, true, false, true ),
	REPOSITORY_STORY (
			FmmNodeDefinition.FMM_CONFIGURATION,
			LockType.STORY,
			true, true, false, true ),
	REPOSITORY_NOTES (
			FmmNodeDefinition.FMM_CONFIGURATION,
			LockType.NOTES,
			true, true, false, true ),
	SERVICE_DELIVERY_COMMITMENT_NODE (
			FmmNodeDefinition.SERVICE_DELIVERY_COMMITMENT,
			LockType.NODE,
			true, true, false, true ),
	SERVICE_DELIVERY_COMMITMENT_STORY (
			FmmNodeDefinition.SERVICE_DELIVERY_COMMITMENT,
			LockType.STORY,
			true, true, false, true ),
	SERVICE_DELIVERY_COMMITMENT_NOTES (
			FmmNodeDefinition.SERVICE_DELIVERY_COMMITMENT,
			LockType.NOTES,
			true, true, false, true ),
	SERVICE_OFFERING_NODE (
			FmmNodeDefinition.SERVICE_OFFERING,
			LockType.NODE,
			true, true, false, true ),
	SERVICE_OFFERING_STORY (
			FmmNodeDefinition.SERVICE_OFFERING,
			LockType.STORY,
			true, true, false, true ),
	SERVICE_OFFERING_NOTES (
			FmmNodeDefinition.SERVICE_OFFERING,
			LockType.NOTES,
			true, true, false, true ),
	SERVICE_OFFERING_SLA_NODE (
			FmmNodeDefinition.SERVICE_OFFERING_SLA,
			LockType.NODE,
			true, true, false, true ),
	SERVICE_OFFERING_SLA_STORY (
			FmmNodeDefinition.SERVICE_OFFERING_SLA,
			LockType.STORY,
			true, true, false, true ),
	SERVICE_OFFERING_SLA_NOTES (
			FmmNodeDefinition.SERVICE_OFFERING_SLA,
			LockType.NOTES,
			true, true, false, true ),
	SERVICE_REQUEST_NODE (
			FmmNodeDefinition.SERVICE_REQUEST,
			LockType.NODE,
			true, true, false, true ),
	SERVICE_REQUEST_STORY (
			FmmNodeDefinition.SERVICE_REQUEST,
			LockType.STORY,
			true, true, false, true ),
	SERVICE_REQUEST_NOTES (
			FmmNodeDefinition.SERVICE_REQUEST,
			LockType.NOTES,
			true, true, false, true ),
	SERVICE_REQUEST_WORK_TASKS (
			FmmNodeDefinition.SERVICE_REQUEST,
			LockType.WORK_TASKS,
			true, true, false, true ),
	STRATEGIC_COMMITMENT_NODE (
			FmmNodeDefinition.STRATEGIC_COMMITMENT,
			LockType.NODE,
			true, true, false, true ),
	STRATEGIC_COMMITMENT_STORY (
			FmmNodeDefinition.STRATEGIC_COMMITMENT,
			LockType.STORY,
			true, true, false, true ),
	STRATEGIC_COMMITMENT_NOTES (
			FmmNodeDefinition.STRATEGIC_COMMITMENT,
			LockType.NOTES,
			true, true, false, true ),
	STRATEGIC_MILESTONE_NODE (
			FmmNodeDefinition.STRATEGIC_MILESTONE,
			LockType.NODE,
			true, true, false, true ),
	STRATEGIC_MILESTONE_STORY (
			FmmNodeDefinition.STRATEGIC_MILESTONE,
			LockType.STORY,
			true, true, false, true ),
	STRATEGIC_MILESTONE_NOTES (
			FmmNodeDefinition.STRATEGIC_MILESTONE,
			LockType.NOTES,
			true, true, false, true ),
	STRATEGIC_MILESTONE_PROJECT_ASSETS (
			FmmNodeDefinition.STRATEGIC_MILESTONE,
			LockType.PROJECT_ASSETS,
			true, true, false, true ),
	STRATEGIC_TEAM_NODE (
			FmmNodeDefinition.STRATEGY_TEAM,
			LockType.NODE,
			true, true, false, true ),
	STRATEGIC_TEAM_STORY (
			FmmNodeDefinition.STRATEGY_TEAM,
			LockType.STORY,
			true, true, false, true ),
	STRATEGIC_TEAM_NOTES (
			FmmNodeDefinition.STRATEGY_TEAM,
			LockType.NOTES,
			true, true, false, true ),
	WORK_PACKAGE_NODE (
			FmmNodeDefinition.WORK_PACKAGE,
			LockType.NODE,
			true, true, false, true ),
	WORK_PACKAGE_STORY (
			FmmNodeDefinition.WORK_PACKAGE,
			LockType.STORY,
			true, true, false, true ),
	WORK_PACKAGE_NOTES (
			FmmNodeDefinition.WORK_PACKAGE,
			LockType.NOTES,
			true, true, false, true ),
	WORK_PACKAGE_WORK_TASKS (
			FmmNodeDefinition.WORK_PACKAGE,
			LockType.WORK_TASKS,
			true, true, false, true ),
	WORK_PLAN_NODE (
			FmmNodeDefinition.WORK_PLAN,
			LockType.NODE,
			true, true, false, true ),
	WORK_PLAN_STORY (
			FmmNodeDefinition.WORK_PLAN,
			LockType.STORY,
			true, true, false, true ),
	WORK_PLAN_NOTES (
			FmmNodeDefinition.WORK_PLAN,
			LockType.NOTES,
			true, true, false, true ),
	WORK_PLAN_WORK_TASKS (
			FmmNodeDefinition.WORK_PLAN,
			LockType.WORK_TASKS,
			true, true, false, true ),
	WORK_TASK_NODE (
			FmmNodeDefinition.WORK_TASK,
			LockType.NODE,
			true, true, false, true ),
	WORK_TASK_STORY (
			FmmNodeDefinition.WORK_TASK,
			LockType.STORY,
			true, true, false, true ),
	WORK_TASK_NOTES (
			FmmNodeDefinition.WORK_TASK,
			LockType.NOTES,
			true, true, false, true ),
	WORK_TASK_ASSIGNMENT_NODE (
			FmmNodeDefinition.WORK_TASK_ASSIGNMENT,
			LockType.NODE,
			true, true, false, true ),
	WORK_TASK_ASSIGNMENT_STORY (
			FmmNodeDefinition.WORK_TASK_ASSIGNMENT,
			LockType.STORY,
			true, true, false, true ),
	WORK_TASK_ASSIGNMENT_NOTES (
			FmmNodeDefinition.WORK_TASK_ASSIGNMENT,
			LockType.NOTES,
			true, true, false, true );
	
	public static final String name_COLUMN_1 = "fmm_node_name";
	public static final String name_COLUMN_2 = "lock_type";
	public static final String name_COLUMN_3 = "sponsor_can_lock";
	public static final String name_COLUMN_4 = "facilitator_can_lock";
	public static final String name_COLUMN_5 = "customer_can_lock";
	public static final String name_COLUMN_6 = "administrator_can_lock";
	
	public static LockConfig getObjectForPrimaryKey(String aTargetNodeName, String aLockTypeName) {
		for(LockConfig theLockConfig : LockConfig.values()) {
			if(theLockConfig.getTargetNode().getName().equals(aTargetNodeName) && theLockConfig.getLockType().getName().equals(aLockTypeName)) {
				return theLockConfig;
			}
		}
		return null;
	}

	public static LockConfig getLockConfigForPrimaryKey(FmmNodeDefinition aTargetNodeDefinition, LockType aLockType) {
		for(LockConfig theLockConfig : LockConfig.values()) {
			if(theLockConfig.getTargetNode().equals(aTargetNodeDefinition) && theLockConfig.getLockType().equals(aLockType)) {
				return theLockConfig;
			}
		}
		return null;
	}

	public static List<LockConfig> getLockConfigListForTargetNode(String aTargetNodeName) {
		ArrayList<LockConfig> theLockConfigList = new ArrayList<LockConfig>();
		for(LockConfig theLockConfig : LockConfig.values()) {
			if(theLockConfig.getTargetNode().getName().equals(aTargetNodeName)) {
				theLockConfigList.add(theLockConfig);
			}
		}
		return theLockConfigList;
	}
	

	public static List<LockConfig> getLockConfigListForTargetNode(FmmNodeDefinition aTargetNodeDefinition) {
		ArrayList<LockConfig> theLockConfigList = new ArrayList<LockConfig>();
		for(LockConfig theLockConfig : LockConfig.values()) {
			if(theLockConfig.getTargetNode().equals(aTargetNodeDefinition)) {
				theLockConfigList.add(theLockConfig);
			}
		}
		return theLockConfigList;
	}

	private static FmmNodeDefinition fmmNodeDictionaryEntry = FmmNodeDefinition.getEntryForClass(LockConfig.class);
	private static String labelText = fmmNodeDictionaryEntry.getLabelText();
	private static Drawable labelDrawable = fmmNodeDictionaryEntry.getLabelDrawable();
	
	public static GcgGuiable getStaticInstance() {
		return BOOKSHELF_NODE;
	}

	@Override
	public String getLabelText() {
		return labelText;
	}

	@Override
	public Drawable getLabelDrawable() {
		return labelDrawable;
	}

	@Override
	public int getLabelDrawableResourceId() {
		return fmmNodeDictionaryEntry.getLabelDrawableResourceId();
	}

	@Override
	public int getDataDrawableResourceId() {
		return getTargetNode().getDataDrawableResourceId();
	}

	private final NodeId nodeId;
	private Date timestamp = new Date(0);
	private final FmmNodeDefinition targetNode;
	private final LockType lockType;
	private final boolean sponsorCanLock;
	private final boolean facilitatorCanLock;
	private final boolean customerCanLock;
	private final boolean administratorCanLock;
	
	LockConfig(
			FmmNodeDefinition aTargetNode,
			LockType aLockType,
			boolean bSponsorCanLock,
			boolean bFacilitatorCanLock,
			boolean bCustomerCanLock,
			boolean bAdministratorCanLock ) {
		this.targetNode = aTargetNode;
		this.lockType = aLockType;
		this.sponsorCanLock = bSponsorCanLock;
		this.facilitatorCanLock = bFacilitatorCanLock;
		this.customerCanLock = bCustomerCanLock;
		this.administratorCanLock = bAdministratorCanLock;
		this.nodeId = new NodeId(
				FmmNodeDefinition.getEntryForClass(LockConfig.class),
				getName() );
	}
	
	public String getName() {
		return getTargetNode().toString() + "/" + getLockType().toString(); 
	}
	
	@Override
	public String getNodeIdString() {
		return getNodeId().getNodeIdString();
	}
	
	@Override
	public String getAbbreviatedNodeIdString() {
		return getNodeId().getAbbreviatedNodeIdString();
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	public FmmNodeDefinition getTargetNode() {
		return this.targetNode;
	}

	public LockType getLockType() {
		return this.lockType;
	}

	public boolean isSponsorCanLock() {
		return this.sponsorCanLock;
	}

	public boolean isFacilitatorCanLock() {
		return this.facilitatorCanLock;
	}

	public boolean isCustomerCanLock() {
		return this.customerCanLock;
	}

	public boolean isAdministratorCanLock() {
		return this.administratorCanLock;
	}

	@Override
	public Drawable getDataDrawable() {
		return getTargetNode().getDataDrawable();
	}

	@Override
	public String getDataText() {
		return getTargetNode().getDataText();
	}

	@Override
	public NodeId getNodeId() {
		return this.nodeId;
	}

	@Override
	public Date getRowTimestamp() {
		return this.timestamp;
	}

	@Override
	public void setRowTimestamp(Date aTimestamp) {
		this.timestamp = aTimestamp;
	}

	@Override
	public FmmNodeDefinition getFmmNodeDefinition() {
		return fmmNodeDictionaryEntry;
	}

	@Override
	public String getNodeTypeCode() {
		return getFmmNodeDefinition().getNodeTypeCode();
	}

	@Override
	public String getNodeTypeName() {
		return getFmmNodeDefinition().getNodeTypeName();
	}
	
	@Override
	public int getNodeEditorActivityRequestCode() {
		return getFmmNodeDefinition().getNodeEditorActivityRequestCode();
	}
	
	@Override
	public int getNodeCreateActivityRequestCode() {
		return getFmmNodeDefinition().getNodeCreateActivityRequestCode();
	}
	
	@Override
	public int getNodePickerActivityRequestCode() {
		return getFmmNodeDefinition().getNodePickerActivityRequestCode();
	}

	@Override
	public boolean isModified(String aSerializedObject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public FmmNodeImpl getClone() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Date updateRowTimestamp() { return null; }
	
}
