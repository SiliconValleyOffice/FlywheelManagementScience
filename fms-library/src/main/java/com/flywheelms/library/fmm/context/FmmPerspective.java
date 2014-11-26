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

import com.flywheelms.gcongui.gcg.interfaces.GcgPerspective;
import com.flywheelms.library.R;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;

import java.util.ArrayList;
import java.util.Arrays;

public class FmmPerspective extends GcgPerspective {

    public static FmmPerspective BUDGETING = new FmmPerspective(
            R.string.fmm_perspective__budgeting,
            R.string.fmm_perspective_definition__budgeting,
            R.drawable.perspective__work_task_budget,
//			R.id.perspective_button__analysis,
            0,
                    3,
                    true,
                    true,
                    false,
                    new ArrayList<FmmNodeDefinition>(Arrays.asList(
                        FmmNodeDefinition.BOOKSHELF,
                        FmmNodeDefinition.NOTEBOOK,
                        FmmNodeDefinition.DISCUSSION_TOPIC,
                        FmmNodeDefinition.PROJECT_ASSET,
                        FmmNodeDefinition.WORK_PACKAGE,
                        FmmNodeDefinition.WORK_TASK,
                        FmmNodeDefinition.FACILITATION_ISSUE)

    ));

    public static FmmPerspective COMMITMENTS = new FmmPerspective(
            R.string.fmm_perspective__commitments,
            R.string.fmm_perspective_definition__commitments,
            R.drawable.perspective__commitments,
//			R.id.perspective_button__commitments,
            0,
                    6,
                    true,
                    true,
                    false,
                    new ArrayList<FmmNodeDefinition>(Arrays.asList(
                        FmmNodeDefinition.FMS_ORGANIZATION,
                        FmmNodeDefinition.STRATEGY_TEAM,
                        FmmNodeDefinition.FLYWHEEL_TEAM,
                        FmmNodeDefinition.FUNCTIONAL_TEAM,
                        FmmNodeDefinition.COMMUNITY_MEMBER)

    ));

    public static FmmPerspective COMMUNITY = new FmmPerspective(
            R.string.fmm_perspective__community,
            R.string.fmm_perspective_definition__community,
            R.drawable.perspective__community,
//			R.id.perspective_button__community,
            0,
                    6,
                    true,
                    true,
                    false,
                    new ArrayList<FmmNodeDefinition>(Arrays.asList(
                        FmmNodeDefinition.FMS_ORGANIZATION,
                        FmmNodeDefinition.STRATEGY_TEAM,
                        FmmNodeDefinition.FLYWHEEL_TEAM,
                        FmmNodeDefinition.FUNCTIONAL_TEAM,
                        FmmNodeDefinition.COMMUNITY_MEMBER)

    ));

    public static FmmPerspective COMPLETION = new FmmPerspective(
            R.string.fmm_perspective__completion,
            R.string.fmm_perspective_definition__completion,
            R.drawable.perspective__completion,
//			R.id.perspective_button__community,
            0,
                    6,
                    true,
                    true,
                    false,
                    new ArrayList<FmmNodeDefinition>(Arrays.asList(
                        FmmNodeDefinition.FMS_ORGANIZATION,
                        FmmNodeDefinition.STRATEGY_TEAM,
                        FmmNodeDefinition.FLYWHEEL_TEAM,
                        FmmNodeDefinition.FUNCTIONAL_TEAM,
                        FmmNodeDefinition.COMMUNITY_MEMBER)

    ));

    public static FmmPerspective CONFIRMED_COMMITMENTS = new FmmPerspective(
            R.string.fmm_perspective__confirmed_commitments,
            R.string.fmm_perspective_definition__confirmed_commitments,
            R.drawable.perspective__confirmed_commitments,
//			R.id.perspective_button__community,
            0,
                    6,
                    true,
                    true,
                    false,
                    new ArrayList<FmmNodeDefinition>(Arrays.asList(
                        FmmNodeDefinition.FMS_ORGANIZATION,
                        FmmNodeDefinition.STRATEGY_TEAM,
                        FmmNodeDefinition.FLYWHEEL_TEAM,
                        FmmNodeDefinition.FUNCTIONAL_TEAM,
                        FmmNodeDefinition.COMMUNITY_MEMBER)

    ));

    public static FmmPerspective DECLINED_COMMITMENTS = new FmmPerspective(
            R.string.fmm_perspective__declined_commitments,
            R.string.fmm_perspective_definition__declined_commitments,
            R.drawable.perspective__declined_commitment,
//			R.id.perspective_button__community,
            0,
                    6,
                    true,
                    true,
                    false,
                    new ArrayList<FmmNodeDefinition>(Arrays.asList(
                        FmmNodeDefinition.FMS_ORGANIZATION,
                        FmmNodeDefinition.STRATEGY_TEAM,
                        FmmNodeDefinition.FLYWHEEL_TEAM,
                        FmmNodeDefinition.FUNCTIONAL_TEAM,
                        FmmNodeDefinition.COMMUNITY_MEMBER)

    ));

    public static FmmPerspective DECKANGL = new FmmPerspective(
            R.string.fmm_perspective__deckangl,
            R.string.fmm_perspective_definition__deckangl,
            R.drawable.perspective__deckangl,
//			R.id.perspective_button__community,
            0,
                    6,
                    true,
                    true,
                    false,
                    new ArrayList<FmmNodeDefinition>(
    ));

    public static FmmPerspective FACILITATION_ISSUES = new FmmPerspective(
            R.string.fmm_perspective__facilitation_issues,
            R.string.fmm_perspective_definition__facilitation_issues,
            R.drawable.perspective__facilitation_issues,
//			R.id.perspective_button__facilitation_issues,
            0,
                    7,
                    true,
                    true,
                    false,
                    new ArrayList<FmmNodeDefinition>(Arrays.asList(
                        FmmNodeDefinition.GOVERNANCE_TARGET,
                        FmmNodeDefinition.FACILITATION_ISSUE)

    ));

    public static FmmPerspective FLYWHEEL_TEAMS = new FmmPerspective(
            R.string.fmm_perspective__flywheel_team,
            R.string.fmm_perspective_definition__flywheel_teams,
            R.drawable.community_button__flywheel_teams,
//			R.id.perspective_button__facilitation_issues,
            0,
                    7,
                    true,
                    true,
                    false,
                    new ArrayList<FmmNodeDefinition>(Arrays.asList(
                        FmmNodeDefinition.GOVERNANCE_TARGET,
                        FmmNodeDefinition.FACILITATION_ISSUE)

    ));

    public static FmmPerspective FUNCTIONAL_TEAMS = new FmmPerspective(
            R.string.fmm_perspective__functional_team,
            R.string.fmm_perspective_definition__functional_teams,
            R.drawable.community_button__functional_teams,
//			R.id.perspective_button__facilitation_issues,
            0,
                    7,
                    true,
                    true,
                    false,
                    new ArrayList<FmmNodeDefinition>(Arrays.asList(
                        FmmNodeDefinition.GOVERNANCE_TARGET,
                        FmmNodeDefinition.FACILITATION_ISSUE)

    ));

    public static FmmPerspective GOVERNANCE = new FmmPerspective(
            R.string.fmm_perspective__governance,
            R.string.fmm_perspective_definition__governance,
            R.drawable.perspective__governance,
//			R.id.perspective_button__governance,
            0,
                    5,
                    true,
                    true,
                    false,
                    new ArrayList<FmmNodeDefinition>(Arrays.asList(
                        FmmNodeDefinition.GOVERNANCE_ROLE,
                        FmmNodeDefinition.COMMUNITY_MEMBER,
                        FmmNodeDefinition.GOVERNANCE_TARGET)

    ));

    public static FmmPerspective GOVERNANCE_TEAMS = new FmmPerspective(
            R.string.fmm_perspective__governance_team,
            R.string.fmm_perspective_definition__governance_teams,
            R.drawable.community_button__governance_teams,
//			R.id.perspective_button__governance,
            0,
                    5,
                    true,
                    true,
                    false,
                    new ArrayList<FmmNodeDefinition>(Arrays.asList(
                        FmmNodeDefinition.GOVERNANCE_ROLE,
                        FmmNodeDefinition.COMMUNITY_MEMBER,
                        FmmNodeDefinition.GOVERNANCE_TARGET)

    ));

    public static FmmPerspective HISTORY = new FmmPerspective(
            R.string.fmm_perspective__history,
            R.string.fmm_perspective_definition__history,
            R.drawable.gcg__history,
//			R.id.perspective_button__facilitation_issues,
            0,
                    7,
                    true,
                    true,
                    false,
                    new ArrayList<FmmNodeDefinition>(Arrays.asList(
                        FmmNodeDefinition.GOVERNANCE_TARGET,
                        FmmNodeDefinition.FACILITATION_ISSUE)

    ));

    public static FmmPerspective NOTEBOOK = new FmmPerspective(
            R.string.fmm_perspective__notebook,
            R.string.fmm_perspective_definition__notebook,
            R.drawable.perspective__notebook,
//		R.id.perspective_button__notebook,
            0,
            3,
            true,
            true,
            false,
            new ArrayList<FmmNodeDefinition>(Arrays.asList(
                    FmmNodeDefinition.BOOKSHELF,
                    FmmNodeDefinition.NOTEBOOK,
                    FmmNodeDefinition.DISCUSSION_TOPIC,
                    FmmNodeDefinition.PROJECT_ASSET,
                    FmmNodeDefinition.WORK_PACKAGE,
                    FmmNodeDefinition.WORK_TASK,
                    FmmNodeDefinition.FACILITATION_ISSUE)

            ));

    public static FmmPerspective NOTES = new FmmPerspective(
            R.string.fmm_perspective__notes,
            R.string.fmm_perspective_definition__notes,
            R.drawable.fse__button__notes,
//			R.id.perspective_button__facilitation_issues,
            0,
                    7,
                    true,
                    true,
                    false,
                    new ArrayList<FmmNodeDefinition>(Arrays.asList(
                        FmmNodeDefinition.GOVERNANCE_TARGET,
                        FmmNodeDefinition.FACILITATION_ISSUE)

    ));

    public static FmmPerspective PROPOSED_COMMITMENTS = new FmmPerspective(
            R.string.fmm_perspective__proposed_commitments,
            R.string.fmm_perspective_definition__proposed_commitments,
            R.drawable.commitment_button__proposed,
//			R.id.perspective_button__facilitation_issues,
            0,
                    7,
                    true,
                    true,
                    false,
                    new ArrayList<FmmNodeDefinition>(Arrays.asList(
                        FmmNodeDefinition.GOVERNANCE_TARGET,
                        FmmNodeDefinition.FACILITATION_ISSUE)

    ));

    public static FmmPerspective SERVICE_DELIVERY = new FmmPerspective(
            R.string.fmm_perspective__service_delivery,
            R.string.fmm_perspective_definition__service_delivery,
            R.drawable.context_button__service_delivery,
//			R.id.perspective_button__service_delivery,
            0,
                    5,
                    true,
                    true,
                    false,
                    new ArrayList<FmmNodeDefinition>(Arrays.asList(
                        FmmNodeDefinition.FISCAL_YEAR,
                        FmmNodeDefinition.CADENCE,
                        FmmNodeDefinition.SERVICE_REQUEST_TRIAGE_LOG,
                        FmmNodeDefinition.SERVICE_OFFERING,
                        FmmNodeDefinition.SERVICE_REQUEST)

    ));

    public static FmmPerspective STRATEGIC_PLANNING = new FmmPerspective(
            R.string.fmm_perspective__strategic_planning,
            R.string.fmm_perspective_definition__strategic_planning,
            R.drawable.perspective__strategic_planning,
            R.id.context_button__strategic_planning,
            0,
                    true,
                    true,
                    false,
                    new ArrayList<FmmNodeDefinition>(Arrays.asList(
                        FmmNodeDefinition.FISCAL_YEAR,
                        FmmNodeDefinition.STRATEGIC_MILESTONE,
                        FmmNodeDefinition.PROJECT_ASSET)

    ));

    public static FmmPerspective STRATEGY_TEAMS = new FmmPerspective(
            R.string.fmm_perspective__strategy_team,
            R.string.fmm_perspective_definition__strategy_teams,
            R.drawable.community_button__strategy_teams,
            R.id.context_button__strategic_planning,
            0,
                    true,
                    true,
                    false,
                    new ArrayList<FmmNodeDefinition>(Arrays.asList(
                        FmmNodeDefinition.FISCAL_YEAR,
                        FmmNodeDefinition.STRATEGIC_MILESTONE,
                        FmmNodeDefinition.STRATEGIC_ASSET)

    ));

    public static FmmPerspective STORY = new FmmPerspective(
            R.string.fmm_perspective__story,
            R.string.fmm_perspective_definition__story,
            R.drawable.perspective__story,
            R.id.context_button__strategic_planning,
            0,
                    true,
                    true,
                    false,
                    new ArrayList<FmmNodeDefinition>(Arrays.asList(
                        FmmNodeDefinition.FISCAL_YEAR,
                        FmmNodeDefinition.STRATEGIC_MILESTONE,
                        FmmNodeDefinition.PROJECT_ASSET)

    ));

    public static FmmPerspective SUGGESTED_COMMITMENTS = new FmmPerspective(
            R.string.fmm_perspective__suggested_commitments,
            R.string.fmm_perspective_definition__suggested_commitments,
            R.drawable.commitment__suggested,
//			R.id.perspective_button__facilitation_issues,
            0,
                    7,
                    true,
                    true,
                    false,
                    new ArrayList<FmmNodeDefinition>(Arrays.asList(
                        FmmNodeDefinition.GOVERNANCE_TARGET,
                        FmmNodeDefinition.FACILITATION_ISSUE)

    ));

    public static FmmPerspective TRIBKN = new FmmPerspective(
            R.string.fmm_perspective__tribkn,
            R.string.fmm_perspective_definition__tribkn,
            R.drawable.perspective__tribkn,
            0,
                    0,
                    false,
                    false,
                    false,
                    new ArrayList<FmmNodeDefinition>(Arrays.asList(
                        FmmNodeDefinition.GOVERNANCE_TARGET,
                        FmmNodeDefinition.FACILITATION_ISSUE)

    ));

    public static FmmPerspective WITHDRAWN_COMMITMENTS = new FmmPerspective(
            R.string.fmm_perspective__withdrawn_commitments,
            R.string.fmm_perspective_definition__withdrawn_commitments,
            R.drawable.commitment__withdrawn,
            R.id.context_button__strategic_planning,
            0,
                    true,
                    true,
                    false,
                    new ArrayList<FmmNodeDefinition>(Arrays.asList(
                        FmmNodeDefinition.FISCAL_YEAR,
                        FmmNodeDefinition.STRATEGIC_MILESTONE,
                        FmmNodeDefinition.PROJECT_ASSET)

    ));

    public static FmmPerspective WORK_BREAKDOWN = new FmmPerspective(
            R.string.fmm_perspective__work_breakdown,
            R.string.fmm_perspective_definition__work_breakdown,
            R.drawable.perspective__work_breakdown,
//		R.id.perspective_button__work_breakdown,
            0,
                    1,
                    true,
                    true,
                    false,
                    new ArrayList<FmmNodeDefinition>(Arrays.asList(
                        FmmNodeDefinition.PORTFOLIO,
                        FmmNodeDefinition.PROJECT,
                        FmmNodeDefinition.PROJECT_ASSET,
                        FmmNodeDefinition.WORK_PACKAGE)

    ));

    public static FmmPerspective WORK_PLANNING = new FmmPerspective(
            R.string.fmm_perspective__work_planning,
            R.string.fmm_perspective_definition__work_planning,
            R.drawable.fmm_noun__flywheel_cadence__yellow,
//		R.id.perspective_button__work_planning,
            0,
                    3,
                    true,
                    true,
                    false,
                    new ArrayList<FmmNodeDefinition>(Arrays.asList(
                        FmmNodeDefinition.FISCAL_YEAR,
                        FmmNodeDefinition.CADENCE,
                        FmmNodeDefinition.WORK_PLAN,
                        FmmNodeDefinition.WORK_PACKAGE)

    ));

    public static FmmPerspective WORK_TASK_BUDGET = new FmmPerspective(
            R.string.fmm_perspective__work_task_budget,
            R.string.fmm_perspective_definition__work_task_budget,
            R.drawable.perspective__work_task_budget,
//			R.id.perspective_button__community,
            0,
            6,
            true,
            true,
            false,
            new ArrayList<FmmNodeDefinition>(
            ));

    static {
        GcgPerspective.VALUES.add(NOTEBOOK);
        GcgPerspective.VALUES.add(BUDGETING);
        GcgPerspective.VALUES.add(COMMITMENTS);
        GcgPerspective.VALUES.add(COMMUNITY);
        GcgPerspective.VALUES.add(COMPLETION);
        GcgPerspective.VALUES.add(CONFIRMED_COMMITMENTS);
        GcgPerspective.VALUES.add(DECLINED_COMMITMENTS);
        GcgPerspective.VALUES.add(DECKANGL);
        GcgPerspective.VALUES.add(FACILITATION_ISSUES);
        GcgPerspective.VALUES.add(FLYWHEEL_TEAMS);
        GcgPerspective.VALUES.add(FUNCTIONAL_TEAMS);
        GcgPerspective.VALUES.add(GOVERNANCE);
        GcgPerspective.VALUES.add(GOVERNANCE_TEAMS);
        GcgPerspective.VALUES.add(HISTORY);
        GcgPerspective.VALUES.add(NOTES);
        GcgPerspective.VALUES.add(PROPOSED_COMMITMENTS);
        GcgPerspective.VALUES.add(SERVICE_DELIVERY);
        GcgPerspective.VALUES.add(STRATEGIC_PLANNING);
        GcgPerspective.VALUES.add(STRATEGY_TEAMS);
        GcgPerspective.VALUES.add(STORY);
        GcgPerspective.VALUES.add(SUGGESTED_COMMITMENTS);
        GcgPerspective.VALUES.add(TRIBKN);
        GcgPerspective.VALUES.add(WITHDRAWN_COMMITMENTS);
        GcgPerspective.VALUES.add(WORK_BREAKDOWN);
        GcgPerspective.VALUES.add(WORK_PLANNING);
    }

    private static final ArrayList<FmmPerspective> FMM_VALUES = new ArrayList<FmmPerspective>();

    public static ArrayList<FmmPerspective> getFmmValues() {
        if(FmmPerspective.FMM_VALUES.size() == 0) {
            for(GcgPerspective theGcgPerspective : GcgPerspective.values()) {
                FmmPerspective.FMM_VALUES.add((FmmPerspective) theGcgPerspective);
            }
        }
        return FMM_VALUES;
    }

    public static FmmPerspective getFmmObjectForName(String aName) {
        return (FmmPerspective) getObjectForName(aName);
    }

    private ArrayList<FmmNodeDefinition> treeNodeTypeList = new ArrayList<FmmNodeDefinition>();

    private FmmPerspective(
            int aNameStringResourceId,
            int aDictionaryDefinitionResourceId,
            int anIconDrawableResourceId,
            int aButtonResourceId,
            int aMenuSequence,
            boolean bEnabled,
            boolean bShowPerspective,
            boolean bShowNoOpPrototype,
            ArrayList<FmmNodeDefinition> aTreeNodeTypeList) {
        super(aNameStringResourceId, aDictionaryDefinitionResourceId, anIconDrawableResourceId, aButtonResourceId, aMenuSequence, bEnabled, bShowPerspective, bShowNoOpPrototype);
        this.treeNodeTypeList = aTreeNodeTypeList;
    }

    public ArrayList<FmmNodeDefinition> getTreeNodeTypeList() {
        return this.treeNodeTypeList;
    }

    public FmmNodeDefinition getRootNodeDefinition() {
        return this.treeNodeTypeList.get(0);
    }
}