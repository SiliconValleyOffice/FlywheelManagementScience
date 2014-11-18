/* @(#)FmsGovernanceComponent.java
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

package com.flywheelms.library.fms.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flywheelms.library.R;
import com.flywheelms.library.fmm.node.impl.enumerator.AssignmentCommitmentType;
import com.flywheelms.library.fmm.node.impl.enumerator.GovernanceRole;
import com.flywheelms.library.fmm.node.impl.enumerator.GovernanceTarget;
import com.flywheelms.library.fmm.node.impl.governable.CommunityMember;
import com.flywheelms.library.fmm.node.impl.nodefrag.GovernanceTeamMember;
import com.flywheelms.library.fms.helper.LockManagementHelper;
import com.flywheelms.library.fms.widget.spinner.AssignmentCommitmentTypeWidgetSpinner;
import com.flywheelms.library.fms.widget.spinner.GovernanceTeamMemberWidgetSpinner;

// com.flywheelms.library.fms.component.FmsGovernanceComponent
public class FmsGovernanceComponent extends LinearLayout {
	
	private boolean isThumbPadRight = true;
	private FmsGovernanceComponentParent governanceComponentParent;
	private GovernanceTarget governanceTarget;
	private GovernanceRole governanceRole;
	private TextView governanceRoleTextView;
	private Button governanceHistoryButton;
	private Button governanceLockManagementButton;
	private Button governanceConfigurationButton;
	private GovernanceTeamMemberWidgetSpinner governanceTeamMemberWidgetSpinner;
	private AssignmentCommitmentTypeWidgetSpinner assignmentCommitmentTypeWidgetSpinner;

	public FmsGovernanceComponent(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
		processCustomAttributes(aContext, anAttributeSet);
		int theLayoutResourceId = this.isThumbPadRight ? R.layout.governance__component__right : R.layout.governance__component__left;
		inflate(aContext, theLayoutResourceId, this);
		initializeViewBody();
	}
	
	public void setParent(FmsGovernanceComponentParent aGovernanceComponentParent) {
		this.governanceComponentParent = aGovernanceComponentParent;
		this.governanceTarget = this.governanceComponentParent.getGovernanceTarget();
	}

	protected void initializeViewBody() {
		this.governanceRoleTextView = (TextView) findViewById(R.id.governance_role___text_view);
		this.governanceRoleTextView.setCompoundDrawablesWithIntrinsicBounds(this.governanceRole.getDataDrawableResourceId(), 0, 0, 0);
		this.governanceRoleTextView.setText(this.governanceRole.getDataText());
		this.governanceHistoryButton = (Button) findViewById(R.id.governance__history__button);
		this.governanceLockManagementButton = (Button) findViewById(R.id.governance__lock_management__button);
		this.governanceConfigurationButton = (Button) findViewById(R.id.governance__configuration__button);
		this.governanceTeamMemberWidgetSpinner = (GovernanceTeamMemberWidgetSpinner) findViewById(R.id.governance__community_member__spinner);
		this.assignmentCommitmentTypeWidgetSpinner = (AssignmentCommitmentTypeWidgetSpinner) findViewById(R.id.governance__commitment__spinner);
	}

	@SuppressWarnings("incomplete-switch")
	protected void processCustomAttributes(Context aContext, AttributeSet anAttributeSet) {
		TypedArray aTypedArray = aContext.obtainStyledAttributes(anAttributeSet, R.styleable.FmsGovernance);
		int theArraySize = aTypedArray.getIndexCount();
		for (int theIndex = 0; theIndex < theArraySize; ++theIndex) {
			int theAttributeIndex = aTypedArray.getIndex(theIndex);
			switch (theAttributeIndex) {
				case R.styleable.FmsGovernance_governanceRole:
					this.governanceRole = GovernanceRole.getObjectForName(aTypedArray.getString(theAttributeIndex));
					break;
			}
		}
		aTypedArray.recycle();
		aTypedArray = aContext.obtainStyledAttributes(anAttributeSet, R.styleable.GcgComponent);
		theArraySize = aTypedArray.getIndexCount();
		for (int theIndex = 0; theIndex < theArraySize; ++theIndex) {
			int theAttributeIndex = aTypedArray.getIndex(theIndex);
			switch (theAttributeIndex) {
				case R.styleable.GcgComponent_thumbPad:
					this.isThumbPadRight = aTypedArray.getString(theAttributeIndex).equals("right");
					break;
			}
		}
		aTypedArray.recycle();
	}

	public void setGovernanceComponentParent(FmsGovernanceComponentParent governanceComponentParent) {
		this.governanceComponentParent = governanceComponentParent;
	}

	public GovernanceRole getGovernanceRole() {
		return this.governanceRole;
	}

	public void setGovernanceRole(GovernanceRole governanceRole) {
		this.governanceRole = governanceRole;
	}

	public CommunityMember getGovernanceTeamMember() {
		return this.governanceTeamMemberWidgetSpinner.getGovernanceTeamMember();
	}

	public void setGovernanceTeamMember(CommunityMember aGovernanceCandidateCommunityMember) {
		this.governanceTeamMemberWidgetSpinner.setGovernanceTeamMember(aGovernanceCandidateCommunityMember);
	}

    public void isRequired(boolean bRequired) {
        this.governanceTeamMemberWidgetSpinner.setInputRequired(bRequired);
    }

	public AssignmentCommitmentType getAssignmentCommitmentType() {
		return (AssignmentCommitmentType) this.assignmentCommitmentTypeWidgetSpinner.getSelectedItem();
	}

	public void setAssignmentCommitmentType(AssignmentCommitmentType anAssignmentCommitmentType) {
		this.assignmentCommitmentTypeWidgetSpinner.setAssignmentCommitmentType(anAssignmentCommitmentType);
	}

	public void viewData() {
		GovernanceTeamMember theGovernanceTeamMember =
				this.governanceComponentParent.getNodeFragGovernance().getGovernanceTeamMember(this.governanceRole);
		this.governanceLockManagementButton.setBackgroundResource(
				LockManagementHelper.getDrawableResourceId(theGovernanceTeamMember.isLocked()));
		setGovernanceTeamMember(theGovernanceTeamMember.getCommunityMember());
		setAssignmentCommitmentType(theGovernanceTeamMember.getCommitmentType());
	}

    public void setGovernanceRequired(boolean bGovernanceRequired) {
        this.governanceTeamMemberWidgetSpinner.setInputRequired(bGovernanceRequired);
    }
}
