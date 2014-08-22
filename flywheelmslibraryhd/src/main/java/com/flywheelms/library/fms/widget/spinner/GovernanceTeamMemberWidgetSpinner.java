/* @(#)GovernanceTeamMemberWidgetSpinner.java
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

package com.flywheelms.library.fms.widget.spinner;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.flywheelms.library.R;
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.node.impl.enumerator.GovernanceRole;
import com.flywheelms.library.fmm.node.impl.enumerator.GovernanceTarget;
import com.flywheelms.library.fmm.node.impl.governable.CommunityMember;
import com.flywheelms.library.fmm.node.impl.governable.FmsOrganization;
import com.flywheelms.library.gcg.GcgApplication;
import com.flywheelms.library.gcg.interfaces.GcgGuiable;
import com.flywheelms.library.gcg.widget.GcgWidgetSpinner;

public class GovernanceTeamMemberWidgetSpinner extends GcgWidgetSpinner {
	
	private FmsOrganization fmsOrganization;
	private GovernanceTarget governanceTarget;
	private GovernanceRole governanceRole;
	ArrayList<GcgGuiable> communityMemberCollection;

	public GovernanceTeamMemberWidgetSpinner(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
	}

	@Override
	@SuppressWarnings("incomplete-switch")
	protected void processCustomAttributes(Context aContext, AttributeSet anAttributeSet) {
		super.processCustomAttributes(aContext, anAttributeSet);
		TypedArray aTypedArray = aContext.obtainStyledAttributes(anAttributeSet, R.styleable.FmsGovernance);
		final int theArraySize = aTypedArray.getIndexCount();
		for (int theIndex = 0; theIndex < theArraySize; ++theIndex) {
			int theAttributeIndex = aTypedArray.getIndex(theIndex);
			switch (theAttributeIndex) {
				case R.styleable.FmsGovernance_governanceRole:
					this.governanceRole = GovernanceRole.getObjectForName(aTypedArray.getString(theAttributeIndex));
					break;
			}
		}
		aTypedArray.recycle();
	}

	@Override
	protected String getLabelText() {
		return GcgApplication.getAppResources().getString(R.string.fmm_node_definition__community_member__label_text);
	}

	@Override
	protected ArrayList<GcgGuiable> updateGuiableList() {
		if(this.fmsOrganization == null || this.governanceTarget == null || this.governanceRole == null) {
			this.communityMemberCollection = new ArrayList<GcgGuiable>();
		} else {
			this.communityMemberCollection = new ArrayList<GcgGuiable>(FmmDatabaseMediator.getActiveMediator().getGovernanceCandidates(
					this.fmsOrganization, this.governanceTarget, this.governanceRole));
		}
		return this.communityMemberCollection;
	}

	public FmsOrganization getFmsOrganization() {
		return this.fmsOrganization;
	}
	
	public void initialize(FmsOrganization anFmsOrganization, GovernanceTarget aGovernanceTarget) {
		this.fmsOrganization = anFmsOrganization;
		this.governanceTarget = aGovernanceTarget;
		updateSpinnerData();
	}

	public void setFmsOrganization(FmsOrganization anFmsOrganization) {
		this.fmsOrganization = anFmsOrganization;
	}

	public GovernanceTarget getGovernanceTarget() {
		return this.governanceTarget;
	}

	public void setGovernanceTarget(GovernanceTarget aGovernanceTarget) {
		this.governanceTarget = aGovernanceTarget;
	}

	public GovernanceRole getGovernanceRole() {
		return this.governanceRole;
	}

	public void setGovernanceRole(GovernanceRole aGovernanceRole) {
		this.governanceRole = aGovernanceRole;
	}
	
	public CommunityMember getGovernanceTeamMember() {
		return (CommunityMember) getSelectedItem();
	}

	public void setGovernanceTeamMember(CommunityMember aGovernanceCandidateCommunityMember) {
		setSelection(aGovernanceCandidateCommunityMember);
	}

}
