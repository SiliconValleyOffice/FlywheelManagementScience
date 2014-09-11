/* @(#)FmmPerspective.java
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
package com.flywheelms.library.fmm.context;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;

import com.flywheelms.library.R;
import com.flywheelms.library.fmm.helper.FmmHelper;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmNode;
import com.flywheelms.library.gcg.interfaces.GcgPerspective;

import java.util.ArrayList;
import java.util.Arrays;

public enum FmmPerspective implements GcgPerspective {

	ANALYSIS (
		R.string.fmm_perspective__analysis,
		R.drawable.perspective__analysis,
//		R.id.perspective_button__analysis,
		0,
		3,
		true,
		true,
		false,
		new ArrayList<FmmNode>(Arrays.asList(
				FmmNodeDefinition.BOOKSHELF,
				FmmNodeDefinition.NOTEBOOK,
				FmmNodeDefinition.DISCUSSION_TOPIC,
				FmmNodeDefinition.PROJECT_ASSET,
				FmmNodeDefinition.WORK_PACKAGE,
				FmmNodeDefinition.WORK_TASK,
				FmmNodeDefinition.FACILITATION_ISSUE )) ),
	BUDGETING (
			R.string.fmm_perspective__budgeting,
			R.drawable.perspective__analysis,
//			R.id.perspective_button__analysis,
			0,
			3,
			true,
			true,
			false,
			new ArrayList<FmmNode>(Arrays.asList(
					FmmNodeDefinition.BOOKSHELF,
					FmmNodeDefinition.NOTEBOOK,
					FmmNodeDefinition.DISCUSSION_TOPIC,
					FmmNodeDefinition.PROJECT_ASSET,
					FmmNodeDefinition.WORK_PACKAGE,
					FmmNodeDefinition.WORK_TASK,
					FmmNodeDefinition.FACILITATION_ISSUE )) ),
	COMMITMENTS (
			R.string.fmm_perspective__commitments,
			R.drawable.perspective__commitments,
//			R.id.perspective_button__commitments,
			0,
			6,
			true,
			true,
			false,
			new ArrayList<FmmNode>(Arrays.asList(
					FmmNodeDefinition.FMS_ORGANIZATION,
					FmmNodeDefinition.STRATEGY_TEAM,
					FmmNodeDefinition.FLYWHEEL_TEAM,
					FmmNodeDefinition.FUNCTIONAL_TEAM,
					FmmNodeDefinition.COMMUNITY_MEMBER )) ),
	COMMUNITY (
			R.string.fmm_perspective__community,
			R.drawable.perspective__community,
//			R.id.perspective_button__community,
			0,
			6,
			true,
			true,
			false,
			new ArrayList<FmmNode>(Arrays.asList(
					FmmNodeDefinition.FMS_ORGANIZATION,
					FmmNodeDefinition.STRATEGY_TEAM,
					FmmNodeDefinition.FLYWHEEL_TEAM,
					FmmNodeDefinition.FUNCTIONAL_TEAM,
					FmmNodeDefinition.COMMUNITY_MEMBER )) ),
	COMPLETION (
			R.string.fmm_perspective__completion,
			R.drawable.perspective__community,
//			R.id.perspective_button__community,
			0,
			6,
			true,
			true,
			false,
			new ArrayList<FmmNode>(Arrays.asList(
					FmmNodeDefinition.FMS_ORGANIZATION,
					FmmNodeDefinition.STRATEGY_TEAM,
					FmmNodeDefinition.FLYWHEEL_TEAM,
					FmmNodeDefinition.FUNCTIONAL_TEAM,
					FmmNodeDefinition.COMMUNITY_MEMBER )) ),
	CONFIRMED_COMMITMENTS (
			R.string.fmm_perspective__confirmed_commitments,
			R.drawable.perspective__community,
//			R.id.perspective_button__community,
			0,
			6,
			true,
			true,
			false,
			new ArrayList<FmmNode>(Arrays.asList(
					FmmNodeDefinition.FMS_ORGANIZATION,
					FmmNodeDefinition.STRATEGY_TEAM,
					FmmNodeDefinition.FLYWHEEL_TEAM,
					FmmNodeDefinition.FUNCTIONAL_TEAM,
					FmmNodeDefinition.COMMUNITY_MEMBER )) ),
	DECLINED_COMMITMENTS (
			R.string.fmm_perspective__declined_commitments,
			R.drawable.perspective__community,
//			R.id.perspective_button__community,
			0,
			6,
			true,
			true,
			false,
			new ArrayList<FmmNode>(Arrays.asList(
					FmmNodeDefinition.FMS_ORGANIZATION,
					FmmNodeDefinition.STRATEGY_TEAM,
					FmmNodeDefinition.FLYWHEEL_TEAM,
					FmmNodeDefinition.FUNCTIONAL_TEAM,
					FmmNodeDefinition.COMMUNITY_MEMBER )) ),
	DECKANGL (
			R.string.fmm_perspective__deckangl,
			R.drawable.perspective__community,
//			R.id.perspective_button__community,
			0,
			6,
			true,
			true,
			false,
			new ArrayList<FmmNode>(Arrays.asList(
					FmmNodeDefinition.FMS_ORGANIZATION,
					FmmNodeDefinition.STRATEGY_TEAM,
					FmmNodeDefinition.FLYWHEEL_TEAM,
					FmmNodeDefinition.FUNCTIONAL_TEAM,
					FmmNodeDefinition.COMMUNITY_MEMBER )) ),
	FACILITATION_ISSUES (
			R.string.fmm_perspective__facilitation_issues,
			R.drawable.perspective__facilitation_issues,
//			R.id.perspective_button__facilitation_issues,
			0,
			7,
			true,
			true,
			false,
			new ArrayList<FmmNode>(Arrays.asList(
					FmmNodeDefinition.GOVERNANCE_TARGET,
					FmmNodeDefinition.FACILITATION_ISSUE )) ),
	FLYWHEEL_TEAMS (
			R.string.fmm_perspective__flywheel_team,
			R.drawable.perspective__facilitation_issues,
//			R.id.perspective_button__facilitation_issues,
			0,
			7,
			true,
			true,
			false,
			new ArrayList<FmmNode>(Arrays.asList(
					FmmNodeDefinition.GOVERNANCE_TARGET,
					FmmNodeDefinition.FACILITATION_ISSUE )) ),
	FUNCTIONAL_TEAMS (
			R.string.fmm_perspective__functional_team,
			R.drawable.perspective__facilitation_issues,
//			R.id.perspective_button__facilitation_issues,
			0,
			7,
			true,
			true,
			false,
			new ArrayList<FmmNode>(Arrays.asList(
					FmmNodeDefinition.GOVERNANCE_TARGET,
					FmmNodeDefinition.FACILITATION_ISSUE )) ),
	GOVERNANCE (
			R.string.fmm_perspective__governance,
			R.drawable.perspective__governance,
//			R.id.perspective_button__governance,
			0,
			5,
			true,
			true,
			false,
			new ArrayList<FmmNode>(Arrays.asList(
					FmmNodeDefinition.GOVERNANCE_ROLE,
					FmmNodeDefinition.COMMUNITY_MEMBER,
					FmmNodeDefinition.GOVERNANCE_TARGET )) ),
	GOVERNANCE_TEAMS (
			R.string.fmm_perspective__governance_team,
			R.drawable.perspective__governance,
//			R.id.perspective_button__governance,
			0,
			5,
			true,
			true,
			false,
			new ArrayList<FmmNode>(Arrays.asList(
					FmmNodeDefinition.GOVERNANCE_ROLE,
					FmmNodeDefinition.COMMUNITY_MEMBER,
					FmmNodeDefinition.GOVERNANCE_TARGET )) ),
	HISTORY (
			R.string.fmm_perspective__history,
			R.drawable.perspective__facilitation_issues,
//			R.id.perspective_button__facilitation_issues,
			0,
			7,
			true,
			true,
			false,
			new ArrayList<FmmNode>(Arrays.asList(
					FmmNodeDefinition.GOVERNANCE_TARGET,
					FmmNodeDefinition.FACILITATION_ISSUE )) ),
	NOTES (
			R.string.fmm_perspective__notes,
			R.drawable.perspective__facilitation_issues,
//			R.id.perspective_button__facilitation_issues,
			0,
			7,
			true,
			true,
			false,
			new ArrayList<FmmNode>(Arrays.asList(
					FmmNodeDefinition.GOVERNANCE_TARGET,
					FmmNodeDefinition.FACILITATION_ISSUE )) ),
	PROPOSED_COMMITMENTS (
			R.string.fmm_perspective__proposed_commitments,
			R.drawable.perspective__facilitation_issues,
//			R.id.perspective_button__facilitation_issues,
			0,
			7,
			true,
			true,
			false,
			new ArrayList<FmmNode>(Arrays.asList(
					FmmNodeDefinition.GOVERNANCE_TARGET,
					FmmNodeDefinition.FACILITATION_ISSUE )) ),
	SERVICE_DELIVERY (
			R.string.fmm_perspective__service_delivery,
			R.drawable.perspective__service_delivery,
//			R.id.perspective_button__service_delivery,
			0,
			5,
			true,
			true,
			false,
			new ArrayList<FmmNode>(Arrays.asList(
					FmmNodeDefinition.FISCAL_YEAR,
					FmmNodeDefinition.FLYWHEEL_MILESTONE,
					FmmNodeDefinition.SERVICE_REQUEST_TRIAGE_LOG,
					FmmNodeDefinition.SERVICE_OFFERING,
					FmmNodeDefinition.SERVICE_REQUEST )) ),
	STRATEGIC_PLANNING (
		R.string.fmm_perspective__strategic_planning,
		R.drawable.perspective__strategic_planning,
		R.id.context_button__strategic_planning,
		0,
		true,
		true,
		false,
		new ArrayList<FmmNode>(Arrays.asList(
				FmmNodeDefinition.FISCAL_YEAR,
				FmmNodeDefinition.STRATEGIC_MILESTONE,
				FmmNodeDefinition.PROJECT_ASSET )) ),
	STRATEGY_TEAMS (
			R.string.fmm_perspective__strategy_team,
			R.drawable.perspective__strategic_planning,
			R.id.context_button__strategic_planning,
			0,
			true,
			true,
			false,
			new ArrayList<FmmNode>(Arrays.asList(
					FmmNodeDefinition.FISCAL_YEAR,
					FmmNodeDefinition.STRATEGIC_MILESTONE,
					FmmNodeDefinition.PROJECT_ASSET )) ),
	STORY(
			R.string.fmm_perspective__story,
			R.drawable.perspective__strategic_planning,
			R.id.context_button__strategic_planning,
			0,
			true,
			true,
			false,
			new ArrayList<FmmNode>(Arrays.asList(
					FmmNodeDefinition.FISCAL_YEAR,
					FmmNodeDefinition.STRATEGIC_MILESTONE,
					FmmNodeDefinition.PROJECT_ASSET )) ),
	SUGGESTED_COMMITMENTS (
			R.string.fmm_perspective__suggested_commitments,
			R.drawable.perspective__facilitation_issues,
//			R.id.perspective_button__facilitation_issues,
			0,
			7,
			true,
			true,
			false,
			new ArrayList<FmmNode>(Arrays.asList(
					FmmNodeDefinition.GOVERNANCE_TARGET,
					FmmNodeDefinition.FACILITATION_ISSUE )) ),
	TRIBKN (
			R.string.fmm_perspective__tribkn,
			R.drawable.perspective__tribkn,
			0,
			0,
			false,
			false,
			false,
			new ArrayList<FmmNode>(Arrays.asList(
					FmmNodeDefinition.GOVERNANCE_TARGET,
					FmmNodeDefinition.FACILITATION_ISSUE )) ),
	WITHDRAWN_COMMITMENTS(
			R.string.fmm_perspective__withdrawn_commitments,
			R.drawable.perspective__strategic_planning,
			R.id.context_button__strategic_planning,
			0,
			true,
			true,
			false,
			new ArrayList<FmmNode>(Arrays.asList(
					FmmNodeDefinition.FISCAL_YEAR,
					FmmNodeDefinition.STRATEGIC_MILESTONE,
					FmmNodeDefinition.PROJECT_ASSET )) ),
	WORK_BREAKDOWN (
		R.string.fmm_perspective__work_breakdown,
		R.drawable.perspective__work_breakdown,
//		R.id.perspective_button__work_breakdown,
		0,
		1,
		true,
		true,
		false,
		new ArrayList<FmmNode>(Arrays.asList(
				FmmNodeDefinition.PORTFOLIO,
				FmmNodeDefinition.PROJECT,
				FmmNodeDefinition.PROJECT_ASSET,
				FmmNodeDefinition.WORK_PACKAGE )) ),
	WORK_PLANNING (
		R.string.fmm_perspective__work_planning,
		R.drawable.perspective__work_planning,
//		R.id.perspective_button__work_planning,
		0,
		2,
		true,
		true,
		false,
		new ArrayList<FmmNode>(Arrays.asList(
				FmmNodeDefinition.FISCAL_YEAR,
				FmmNodeDefinition.FLYWHEEL_MILESTONE,
				FmmNodeDefinition.WORK_PLAN,
				FmmNodeDefinition.WORK_PACKAGE )) );
	
	

	public static FmmPerspective getObjectForName(String aName) {
		for(FmmPerspective theFmmPerspective : FmmPerspective.values()) {
			if(theFmmPerspective.getName().equals(aName)) {
				return theFmmPerspective;
			}
		}
		return null;
	}
	
	private int nameStringResourceId;
	private String name;
	private int iconDrawableResourceId;
	private Drawable iconDrawable;
	private int buttonResourceId;
	private Button button;
	private int menuSequence;
	private boolean enabled;
	private boolean isShowPerspective;
	private boolean showNoOpPrototype = false;
	private int prototypeResourceId = 0;
	private ArrayList<FmmNode> treeNodeTypeList = new ArrayList<FmmNode>();
	
	private FmmPerspective(
			int aNameStringResourceId,
			int anIconDrawableResourceId,
			int aButtonResourceId,
			int aMenuSequence,
			boolean bEnabled,
			boolean bShowPerspective,
			boolean bShowNoOpPrototype,
			ArrayList<FmmNode> aTreeNodeTypeList ) {
		this.nameStringResourceId = aNameStringResourceId;
		this.name = FmmHelper.getContext().getResources().getString(this.nameStringResourceId);
		this.iconDrawableResourceId = anIconDrawableResourceId;
		this.iconDrawable = FmmHelper.getContext().getResources().getDrawable(this.iconDrawableResourceId);
		this.buttonResourceId = aButtonResourceId;
		this.menuSequence = aMenuSequence;
		this.enabled = bEnabled;
		this.isShowPerspective = bEnabled == true ? bShowPerspective : false;
		this.showNoOpPrototype = bShowNoOpPrototype;
		this.treeNodeTypeList = aTreeNodeTypeList;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getIconDrawableResourceId() {
		return this.iconDrawableResourceId;
	}
	
	@Override
	public Drawable getIconDrawable() {
		return this.iconDrawable;
	}

	@Override
	public int getButtonDrawableResourceId() {
		return this.buttonResourceId;
	}
	
	@Override
	public Button getButton(View aView) {
		if(this.button == null) {
			this.button = (Button) aView.findViewById(this.buttonResourceId);
		}
		return this.button;
	}
	
	@Override
	public int getMenuSequence() {
		return this.menuSequence;
	}
	
	@Override
	public boolean isEnabled() {
		return this.enabled;
	}
	
	@Override
	public void setEnabled(boolean aBoolean) {
		this.enabled = aBoolean;
	}
	
	@Override
	public boolean isShowPerspective() {
		return this.isShowPerspective;
	}
	
	@Override
	public void setShowPerspective(boolean aBoolean) {
		this.isShowPerspective = aBoolean;
	}
	
	@Override
	public boolean showNoOpPerspectivePrototype() {
		return this.showNoOpPrototype;
	}
	
	@Override
	public Drawable getPrototypeDrawable() {
		return FmmHelper.getContext().getResources().getDrawable(this.prototypeResourceId);
	}
	
	public ArrayList<FmmNode> getTreeNodeTypeList() {
		return this.treeNodeTypeList;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	@Override
	public FmmPerspective getPerspectiveForName(String aPerspectiveName) {
		for (FmmPerspective thePerspective : FmmPerspective.values()) {
			if (thePerspective.toString().equals(aPerspectiveName)) {
				return thePerspective;
			}
		}
		return null;
	}

}
