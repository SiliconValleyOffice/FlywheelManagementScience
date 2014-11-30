/* @(#)StrategicAsset.java
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

import com.flywheelms.gcongui.deckangl.enumerator.DecKanGlDecoratorCanvasLocation;
import com.flywheelms.gcongui.deckangl.interfaces.DecKanGlDecorator;
import com.flywheelms.gcongui.gcg.activity.GcgActivity;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorCadenceCommitment;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorCompletion;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorFacilitationIssue;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorFacilitator;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorGovernance;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorProjectManagement;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorSequence;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorStory;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorStrategicCommitment;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorWorkBreakdown;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorWorkTaskBudget;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorWorkTeam;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.completable.FmmCompletionNodeImpl;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fms.helper.FmsActivityHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class StrategicAsset extends WorkAsset {

    private static final long serialVersionUID = -3645381005646011918L;
    private String projectNodeIdString;
    private Project project;
    private boolean strategic = false;
    private String flywheelTeamNodeIdString;
    private FlywheelTeam flywheelTeam;
    private ArrayList<WorkPackage> workPackageList;
    private int workPackageBudget = 0;

    public StrategicAsset() {
        super(new NodeId(FmmNodeDefinition.STRATEGIC_ASSET));
        setStrategic(true);
    }

    public StrategicAsset(NodeId aNodeId) {
        super(aNodeId);
        setStrategic(true);
    }

    public StrategicAsset(String anExistingNodeIdString) {
        super(NodeId.hydrate(
                getNodeClass(),
                anExistingNodeIdString ));
        setStrategic(true);
    }

    public StrategicAsset(NodeId aNodeId, String aHeadline) {
        super(aNodeId, aHeadline);
        setStrategic(true);
    }

    public StrategicAsset(NodeId aNodeId, String aHeadline, Project aProject) {
        super(aNodeId, aHeadline);
        setStrategic(true);
        setProject(aProject);
    }

    public StrategicAsset(JSONObject aJsonObject) {
        super(aJsonObject);
    }

    @Override
    public FmmNodeDefinition getFmmNodeDefinition() {
        return FmmNodeDefinition.STRATEGIC_ASSET;
    }

    protected static Class<? extends FmmCompletionNodeImpl> getNodeClass() {
        return StrategicAsset.class;
    }

    /////////////////////////////////////////////////
    //////  TEMPORARY for TESTING  //////////////////////////
    @Override
    public HashMap<DecKanGlDecoratorCanvasLocation, DecKanGlDecorator> getUpdatedDecKanGlDecoratorMap() {
        HashMap<DecKanGlDecoratorCanvasLocation, DecKanGlDecorator> theDecKanGlDecoratorMap =
                new HashMap<DecKanGlDecoratorCanvasLocation, DecKanGlDecorator>();
        theDecKanGlDecoratorMap.put(
                FmsDecoratorGovernance.CONFIRMED_GOVERNANCE.getDecoratorCanvasLocation(),
                FmsDecoratorGovernance.CONFIRMED_GOVERNANCE );
        theDecKanGlDecoratorMap.put(
                FmsDecoratorFacilitationIssue.NO_FACILITATION_ISSUE.getDecoratorCanvasLocation(),
                FmsDecoratorFacilitationIssue.NO_FACILITATION_ISSUE );
        theDecKanGlDecoratorMap.put(
                FmsDecoratorFacilitator.CONFIRMED_FACILITATOR.getDecoratorCanvasLocation(),
                FmsDecoratorFacilitator.CONFIRMED_FACILITATOR );
        theDecKanGlDecoratorMap.put(
                FmsDecoratorProjectManagement.ONE_CONFIRMED_PARENT_FRACTAL.getDecoratorCanvasLocation(),
                FmsDecoratorProjectManagement.ONE_CONFIRMED_PARENT_FRACTAL );
        theDecKanGlDecoratorMap.put(
                FmsDecoratorWorkBreakdown.SUGGESTED_CHILD_FRACTALS.getDecoratorCanvasLocation(),
                FmsDecoratorWorkBreakdown.SUGGESTED_CHILD_FRACTALS );
        theDecKanGlDecoratorMap.put(
                FmsDecoratorStrategicCommitment.PROPOSED_STRATEGIC_COMMITMENT.getDecoratorCanvasLocation(),
                FmsDecoratorStrategicCommitment.PROPOSED_STRATEGIC_COMMITMENT );
        theDecKanGlDecoratorMap.put(
                FmsDecoratorCadenceCommitment.SUGGESTED_CADENCE_COMMITMENT.getDecoratorCanvasLocation(),
                FmsDecoratorCadenceCommitment.SUGGESTED_CADENCE_COMMITMENT);
        theDecKanGlDecoratorMap.put(
                FmsDecoratorWorkTaskBudget.SUGGESTED_TASK_POINTS_BUDGET.getDecoratorCanvasLocation(),
                FmsDecoratorWorkTaskBudget.SUGGESTED_TASK_POINTS_BUDGET);
        theDecKanGlDecoratorMap.put(
                FmsDecoratorWorkTeam.SUGGESTED_TEAM.getDecoratorCanvasLocation(),
                FmsDecoratorWorkTeam.SUGGESTED_TEAM );
        theDecKanGlDecoratorMap.put(
                FmsDecoratorStory.STORY_SWAG.getDecoratorCanvasLocation(),
                FmsDecoratorStory.STORY_SWAG );
        theDecKanGlDecoratorMap.put(
                FmsDecoratorSequence.SEQUENCE_SWAG.getDecoratorCanvasLocation(),
                FmsDecoratorSequence.SEQUENCE_SWAG );
        theDecKanGlDecoratorMap.put(
                FmsDecoratorCompletion.COMPLETION_NOT_SCHEDULED.getDecoratorCanvasLocation(),
                FmsDecoratorCompletion.COMPLETION_NOT_SCHEDULED );
        this.decKanGlDecoratorMap = theDecKanGlDecoratorMap;
        return this.decKanGlDecoratorMap;
    }


    public static void startNodePickerActivity(GcgActivity anActivity, ArrayList<String> aNodeIdExclusionList, String aWhereClause, String aListActionLabel) {
        FmsActivityHelper.startHeadlineNodePickerActivity(anActivity, FmmNodeDefinition.STRATEGIC_ASSET, aNodeIdExclusionList, aWhereClause, aListActionLabel);
    }

}
