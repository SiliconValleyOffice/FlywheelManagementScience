/* @(#)GovernanceTarget.java
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

package com.flywheelms.library.fmm.node.impl.enumerator;

import android.graphics.drawable.Drawable;

import com.flywheelms.gcongui.gcg.activity.GcgActivity;
import com.flywheelms.gcongui.gcg.interfaces.GcgGuiable;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.FmmNodeImpl;
import com.flywheelms.library.fmm.node.interfaces.FmmEnumNode;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmNode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public enum GovernanceTarget implements FmmEnumNode {

	BOOKSHELF (
			FmmNodeDefinition.BOOKSHELF,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.NONE,
			GovernanceParticipationType.NONE,
			false),
	COMMUNITY_MEMBER (
			FmmNodeDefinition.COMMUNITY_MEMBER,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.OPTIONAL,
			GovernanceParticipationType.NONE,
			false),
	DISCUSSION_TOPIC (
			FmmNodeDefinition.DISCUSSION_TOPIC,
			GovernanceParticipationType.OPTIONAL,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.NONE,
			GovernanceParticipationType.NONE,
			false),
	FACILITATION_ISSUE (
			FmmNodeDefinition.FACILITATION_ISSUE,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.NONE,
			GovernanceParticipationType.NONE,
			false),
	FISCAL_YEAR (
			FmmNodeDefinition.FISCAL_YEAR,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.NONE,
			GovernanceParticipationType.REQUIRED,
			false),
	FLYWHEEL_COMMITMENT (
			FmmNodeDefinition.CADENCE_WORK_PACKAGE_COMMITMENT,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.NONE,
			GovernanceParticipationType.NONE,
			false),
	FLYWHEEL_CADENCE (
			FmmNodeDefinition.CADENCE,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.NONE,
			GovernanceParticipationType.NONE,
			false),
	FLYWHEEL_TEAM (
			FmmNodeDefinition.FLYWHEEL_TEAM,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.NONE,
			GovernanceParticipationType.NONE,
			false),
	FMM_CONFIGURATION (
			FmmNodeDefinition.FMM_CONFIGURATION,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.OPTIONAL,
			false),
	FSE_DOCUMENT (
			FmmNodeDefinition.FSE_DOCUMENT,
			GovernanceParticipationType.NONE,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.OPTIONAL,
			GovernanceParticipationType.NONE,
			false),
	FUNCTIONAL_TEAM (
			FmmNodeDefinition.FUNCTIONAL_TEAM,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.NONE,
			GovernanceParticipationType.NONE,
			false),
	NOTEBOOK (
			FmmNodeDefinition.NOTEBOOK,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.NONE,
			GovernanceParticipationType.NONE,
			false),
	ORGANIZATION (
			FmmNodeDefinition.FMS_ORGANIZATION,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.NONE,
			GovernanceParticipationType.REQUIRED,
			false),
	PORTFOLIO (
			FmmNodeDefinition.PORTFOLIO,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.NONE,
			GovernanceParticipationType.NONE,
			false),
	PROJECT (
			FmmNodeDefinition.PROJECT,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.NONE,
			GovernanceParticipationType.NONE,
			false),
	PROJECT_ASSET (
			FmmNodeDefinition.PROJECT_ASSET,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.NONE,
			GovernanceParticipationType.NONE,
			false),
  SERVICE_DELIVERY_COMMITMENT (
		  FmmNodeDefinition.SERVICE_DELIVERY_COMMITMENT,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.NONE,
			GovernanceParticipationType.NONE,
			false),
	SERVICE_OFFERING (
			FmmNodeDefinition.SERVICE_OFFERING,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.NONE,
			GovernanceParticipationType.NONE,
			false),
	SERVICE_OFFERING_SLA (
			FmmNodeDefinition.SERVICE_OFFERING_SLA,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.NONE,
			GovernanceParticipationType.NONE,
			false),
	SERVICE_REQUEST (
			FmmNodeDefinition.SERVICE_REQUEST,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.NONE,
			GovernanceParticipationType.NONE,
			false),
    STRATEGIC_ASSET (
            FmmNodeDefinition.STRATEGIC_ASSET,
            GovernanceParticipationType.REQUIRED,
            GovernanceParticipationType.REQUIRED,
            GovernanceParticipationType.NONE,
            GovernanceParticipationType.NONE,
            false),
	STRATEGIC_COMMITMENT (
			FmmNodeDefinition.STRATEGIC_COMMITMENT,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.NONE,
			GovernanceParticipationType.NONE,
			false),
	STRATEGIC_MILESTONE (
			FmmNodeDefinition.STRATEGIC_MILESTONE,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.NONE,
			GovernanceParticipationType.NONE,
			false),
	STRATEGIC_TEAM (
			FmmNodeDefinition.STRATEGY_TEAM,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.NONE,
			GovernanceParticipationType.NONE,
			false),
	WORK_PACKAGE (
			FmmNodeDefinition.WORK_PACKAGE,
			GovernanceParticipationType.OPTIONAL,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.NONE,
			GovernanceParticipationType.NONE,
			false),
	WORK_PLAN (
			FmmNodeDefinition.WORK_PLAN,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.NONE,
			GovernanceParticipationType.NONE,
			false),
	WORK_TASK (
			FmmNodeDefinition.WORK_TASK,
			GovernanceParticipationType.OPTIONAL,
			GovernanceParticipationType.REQUIRED,
			GovernanceParticipationType.NONE,
			GovernanceParticipationType.NONE,
			false);
	
	public static final String name_COLUMN_1 = "governance_target";
	public static final String name_COLUMN_2 = "sponsor_requirement";
	public static final String name_COLUMN_3 = "facilitator_requirement";
	public static final String name_COLUMN_4 = "customer_requirement";
	public static final String name_COLUMN_5 = "administrator_requirement";
	public static final String name_COLUMN_6 = "auto_completable";
	
	public static GovernanceTarget getObjectForName(String aName) {
		for(GovernanceTarget theGovernanceTarget : GovernanceTarget.values()) {
			if(theGovernanceTarget.toString().equals(aName)) {
				return theGovernanceTarget;
			}
		}
		return null;
	}

	private static FmmNodeDefinition fmmNodeDictionaryEntry = FmmNodeDefinition.getEntryForClass(GovernanceTarget.class);
	private static String labelText = fmmNodeDictionaryEntry.getLabelText();
	private static Drawable labelDrawable = fmmNodeDictionaryEntry.getLabelDrawable();
	
	public static GcgGuiable getStaticInstance() {
		return BOOKSHELF;
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
		return getTargetNodeDictionaryEntry().getDataDrawableResourceId();
	}

	private NodeId nodeId;
	private Date timestamp = new Date(0);
	FmmNodeDefinition targetNodeDictionaryEntry;
	GovernanceParticipationType sponsorRequirement;
	GovernanceParticipationType facilitatorRequirement;
	GovernanceParticipationType customerRequirement;
	GovernanceParticipationType administratorRequirement;
	boolean autoCompletable;
	
	GovernanceTarget(
			FmmNodeDefinition aTargetNodeDictionaryEntry,
			GovernanceParticipationType aSponsorRequirement,
			GovernanceParticipationType aFacilitatorRequirement,
			GovernanceParticipationType aCustomerRequirement,
			GovernanceParticipationType anAdministratorRequirement,
			boolean bAutoCompletable ) {
		this.targetNodeDictionaryEntry = aTargetNodeDictionaryEntry;
		this.sponsorRequirement = aSponsorRequirement;
		this.facilitatorRequirement = aFacilitatorRequirement;
		this.customerRequirement = aCustomerRequirement;
		this.administratorRequirement = anAdministratorRequirement;
		this.autoCompletable = bAutoCompletable;
		this.nodeId = new NodeId(
				FmmNodeDefinition.getEntryForClass(GovernanceTarget.class),
				getName() );
	}
	
	public String getName() {
		return this.targetNodeDictionaryEntry.getName();
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
	
	public FmmNodeDefinition getTargetNodeDictionaryEntry(){
		return this.targetNodeDictionaryEntry;
	}
	
	public List<GovernanceRole> getRequiredGovernanceRoleList() {
		ArrayList<GovernanceRole> theGovernanceRoleList = new ArrayList<GovernanceRole>();
		if(isSponsorRequired()) {
			theGovernanceRoleList.add(GovernanceRole.SPONSOR);
		}
		if(isCustomerRequired()) {
			theGovernanceRoleList.add(GovernanceRole.CUSTOMER);
		}
		if(isFacilitatorRequired()) {
			theGovernanceRoleList.add(GovernanceRole.FACILITATOR);
		}
		if(isAdministratorRequired()) {
			theGovernanceRoleList.add(GovernanceRole.ADMINISTRATOR);
		}
		return theGovernanceRoleList;
	}
	
	public GovernanceParticipationType getSponsorRequirement() {
		return this.sponsorRequirement;
	}
	
	public boolean isSponsorRequired() {
		return getSponsorRequirement() == GovernanceParticipationType.REQUIRED ? true : false;
	}

	public GovernanceParticipationType getFacilitatorRequirement() {
		return this.facilitatorRequirement;
	}
	
	public boolean isFacilitatorRequired() {
		return getFacilitatorRequirement() == GovernanceParticipationType.REQUIRED ? true : false;
	}

	public GovernanceParticipationType getCustomerRequirement() {
		return this.customerRequirement;
	}
	
	public boolean isCustomerRequired() {
		return getCustomerRequirement() == GovernanceParticipationType.REQUIRED ? true : false;
	}
	
	public GovernanceParticipationType getAdministratorRequirement() {
		return this.administratorRequirement;
	}
	
	public boolean isAdministratorRequired() {
		return getAdministratorRequirement() == GovernanceParticipationType.REQUIRED ? true : false;
	}

	public boolean isAutoCompletable() {
		return this.autoCompletable;
	}

	@Override
	public Drawable getDataDrawable() {
		return getTargetNodeDictionaryEntry().getDataDrawable();
	}

	@Override
	public String getDataText() {
		return getName();
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
	
	public static GovernanceTarget getGovernanceTargetForNode(FmmNode anFmmNode) {
		FmmNodeDefinition theFmmNodeDefinition = FmmNodeDefinition.getEntryForClass(anFmmNode.getClass());
		for(GovernanceTarget theGovernanceTarget : GovernanceTarget.values()) {
			if(theGovernanceTarget.getFmmNodeDefinition() == theFmmNodeDefinition) {
				return theGovernanceTarget;
			}
		}
		return null;
	}

	public static GovernanceTarget getObjectForFmmDictionaryEntry(FmmNodeDefinition anFmmNodeDefinition) {
		for(GovernanceTarget theGovernanceTarget : GovernanceTarget.values()) {
			if(theGovernanceTarget.getTargetNodeDictionaryEntry() == anFmmNodeDefinition) {
				return theGovernanceTarget;
			}
		}
		return null;
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

	@SuppressWarnings("unused")
	public static void startNodePickerActivity(GcgActivity gcgActivity, Object object, Object object2, String string) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Date updateRowTimestamp() { return null; }

}
