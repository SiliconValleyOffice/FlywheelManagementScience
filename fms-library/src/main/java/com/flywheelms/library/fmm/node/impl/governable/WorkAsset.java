/* @(#)WorkAsset.java
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

import android.content.Intent;

import com.flywheelms.gcongui.deckangl.enumerator.DecKanGlDecoratorCanvasLocation;
import com.flywheelms.gcongui.deckangl.interfaces.DecKanGlDecorator;
import com.flywheelms.gcongui.gcg.activity.GcgActivity;
import com.flywheelms.gcongui.gcg.interfaces.GcgPerspective;
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.context.FmmPerspective;
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
import com.flywheelms.library.fmm.meta_data.CompletableNodeMetaData;
import com.flywheelms.library.fmm.meta_data.SequencedLinkNodeMetaData;
import com.flywheelms.library.fmm.meta_data.WorkAssetMetaData;
import com.flywheelms.library.fmm.node.FmmHeadlineNodeShallow;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.commitment.StrategicCommitment;
import com.flywheelms.library.fmm.node.impl.completable.FmmCompletionNodeImpl;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.headline.FmmHeadlineNodeImpl;
import com.flywheelms.library.fmm.node.interfaces.FmmSequencedNode;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;
import com.flywheelms.library.fms.helper.FmsActivityHelper;
import com.flywheelms.library.util.JsonHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class WorkAsset extends FmmCompletionNodeImpl implements Comparable<WorkAsset> {

    private static final long serialVersionUID = -3645381005646011918L;
    private String projectNodeIdString;
    private Project project;
    private boolean strategic = false;
    private String flywheelTeamNodeIdString;
    private FlywheelTeam flywheelTeam;
    private ArrayList<WorkPackage> workPackageList;
    private int workPackageBudget = 0;

    public WorkAsset() {
        super(new NodeId(FmmNodeDefinition.WORK_ASSET));
    }

    public WorkAsset(NodeId aNodeId, String aHeadline) {
        super(aNodeId, aHeadline);
    }

    public WorkAsset(NodeId aNodeId, String aHeadline, Project aProject) {
        this(aNodeId, aHeadline);
        setProject(aProject);
    }

    public WorkAsset(Class<? extends FmmCompletionNodeImpl> aClass, JSONObject aJsonObject) {
        super(aClass, aJsonObject);
    }

    public WorkAsset(JSONObject aJsonObject) {
        super(getNodeClass(), aJsonObject);
        try {
            setProjectId(aJsonObject.getString(WorkAssetMetaData.column_PROJECT_ID));
            setStrategic(aJsonObject.getBoolean(WorkAssetMetaData.column_IS_STRATEGIC));
            setSequence(aJsonObject.getInt(CompletableNodeMetaData.column_SEQUENCE));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public FmmNodeDefinition getFmmNodeDefinition() {
        return FmmNodeDefinition.WORK_ASSET;
    }

    protected static Class<? extends FmmCompletionNodeImpl> getNodeClass() {
        return WorkAsset.class;
    }

    public WorkAsset(NodeId aNodeId) {
        super(aNodeId);
    }

    public WorkAsset(String anExistingNodeIdString) {
        super(NodeId.hydrate(
                getNodeClass(),
                anExistingNodeIdString ));
    }

    public String getFlywheelTeamNodeIdString() {
        return this.flywheelTeamNodeIdString;
    }

    public void setFlywheelTeamNodeIdString(String aFlywheelTeamNodeIdString) {
        this.flywheelTeamNodeIdString = aFlywheelTeamNodeIdString;
        this.flywheelTeam = null;
    }

    public FlywheelTeam getFlywheelTeam() {
        if(this.flywheelTeamNodeIdString == null || this.flywheelTeamNodeIdString.equals("")) {
            return null;
        }
        if(this.flywheelTeam == null) {
            this.flywheelTeam = FmmDatabaseMediator.getActiveMediator().getFlywheelTeam(this.flywheelTeamNodeIdString);
        }
        return this.flywheelTeam;
    }

    public void setFlywheelTeam(FlywheelTeam aFlywheelTeam) {
        this.flywheelTeam = aFlywheelTeam;
        this.flywheelTeamNodeIdString = this.flywheelTeam.getNodeIdString();
    }

    public String getStrategicMilestoneNodeId() {
        return getStrategicCommitment() == null ? null :
                getStrategicCommitment().getStrategicMilestoneNodeId();
    }

    public StrategicCommitment getStrategicCommitment() {
        return FmmDatabaseMediator.getActiveMediator().retrieveStrategicCommitmentForStrategicAsset(getNodeIdString());
    }

    ////////////////////////////////////////////////

    public Project getProject() {
        if(this.project == null) {
            this.project = FmmDatabaseMediator.getActiveMediator().retrieveProject(getNodeIdString());
        }
        return this.project;
    }

    public void setProject(Project aProject) {
        this.project = aProject;
        if(this.project == null) {
            this.projectNodeIdString = "";
        } else {
            this.projectNodeIdString = this.project.getNodeIdString();
        }
    }

    public String getProjectNodeIdString() {
        return this.projectNodeIdString;
    }

    public void setProjectId(String aNodeIdString) {
        this.projectNodeIdString = aNodeIdString;
        if(this.project == null || this.projectNodeIdString == null || this.projectNodeIdString.equals("") || this.project.getNodeIdString().equals(this.projectNodeIdString)) {
            return;
        }
        this.project = null;
    }

    @Override
    protected void initializeNodeCompletionSummaryMap() {
        super.initializeNodeCompletionSummaryMap();
        initializeNodeCompletionSummaryMap(FmmPerspective.STRATEGIC_PLANNING, FmmNodeDefinition.WORK_PACKAGE);
        this.nodeCompletionSummaryMap.put(FmmPerspective.WORK_BREAKDOWN, this.nodeCompletionSummaryMap.get(FmmPerspective.STRATEGIC_PLANNING));
    }

    @Override
    public void updateNodeCompletionSummary(FmmPerspective anFmmPerspective, NodeCompletionSummary aNodeSummary) {
        if(anFmmPerspective == FmmPerspective.WORK_BREAKDOWN || anFmmPerspective == FmmPerspective.STRATEGIC_PLANNING) {
            Collection<WorkPackage> theWorkPackageCollection = getWorkPackageCollection();
            if(theWorkPackageCollection.size() > 0) {
                aNodeSummary.setShowNodeSummary(true);
                aNodeSummary.setSummaryPrefix("( " + countGreenWorkPackages() + " ");
                aNodeSummary.setSummarySuffix(" of " + theWorkPackageCollection.size() + " )");
            } else {
                aNodeSummary.setShowNodeSummary(false);
            }
        }
    }

    @Override
    public int compareTo(WorkAsset anOtherWorkAsset) {
        return (getSequence() < anOtherWorkAsset.getSequence() ? -1 :
                (getSequence() == anOtherWorkAsset.getSequence() ? 0 : 1));
    }

    public static final String SERIALIZATION_FORMAT_VERSION = "0.1";

    @Override
    public JSONObject getJsonObject() {
        JSONObject theJsonObject = super.getJsonObject();
        try {
            theJsonObject.put(JsonHelper.key__SERIALIZATION_FORMAT_VERSION, SERIALIZATION_FORMAT_VERSION);
            theJsonObject.put(WorkAssetMetaData.column_PROJECT_ID, getProjectNodeIdString());
            theJsonObject.put(WorkAssetMetaData.column_IS_STRATEGIC, isStrategic());
            theJsonObject.put(SequencedLinkNodeMetaData.column_SEQUENCE, getSequence());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return theJsonObject;
    }

    //////////////////

    public JSONArray getWorkPackageJsonArray() {
        JSONArray theJsonArray = new JSONArray();
        for(WorkPackage theWorkPackage : getWorkPackageList()) {
            theJsonArray.put(theWorkPackage.getNodeIdString());
        }
        return theJsonArray;
    }

    public void setWorkPackageList(JSONArray aJsonArray) {
        this.workPackageList = new ArrayList<WorkPackage>();
        for(int i=0; i < aJsonArray.length(); ++i) {
            try {
                this.workPackageList.add(FmmDatabaseMediator.getActiveMediator().retrieveWorkPackage(
                        aJsonArray.getString(i)));
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public Collection<WorkPackage> getWorkPackageCollection() {
        return getWorkPackageList();
    }

    public ArrayList<WorkPackage> getWorkPackageList() {
        if(this.workPackageList == null) {
            this.workPackageList = FmmDatabaseMediator.getActiveMediator().retrieveWorkPackageList(this);
        }
        return this.workPackageList;
    }

    private int countGreenWorkPackages() {
        int theGreenCount = 0;
        for(WorkPackage theWorkPackage : getWorkPackageCollection()) {
            if(theWorkPackage.isGreen()) {
                ++theGreenCount;
            }
        }
        return theGreenCount;
    }

    /////////////////////////////////////////////////
    //////  TEMPORARY for TESTING  //////////////////////////
    @Override
    public HashMap<DecKanGlDecoratorCanvasLocation, DecKanGlDecorator> getUpdatedDecKanGlDecoratorMap() {
        HashMap<DecKanGlDecoratorCanvasLocation, DecKanGlDecorator> theDecKanGlDecoratorMap =
                new HashMap<DecKanGlDecoratorCanvasLocation, DecKanGlDecorator>();
        theDecKanGlDecoratorMap.put(
                FmsDecoratorGovernance.NO_GOVERNANCE.getDecoratorCanvasLocation(),
                FmsDecoratorGovernance.NO_GOVERNANCE );
        theDecKanGlDecoratorMap.put(
                FmsDecoratorFacilitationIssue.NO_FACILITATION_ISSUE.getDecoratorCanvasLocation(),
                FmsDecoratorFacilitationIssue.NO_FACILITATION_ISSUE );
        theDecKanGlDecoratorMap.put(
                FmsDecoratorFacilitator.SUGGESTED_FACILITATOR.getDecoratorCanvasLocation(),
                FmsDecoratorFacilitator.SUGGESTED_FACILITATOR );
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
                FmsDecoratorWorkTaskBudget.SUGGESTED_TASK_BUDGET.getDecoratorCanvasLocation(),
                FmsDecoratorWorkTaskBudget.SUGGESTED_TASK_BUDGET );
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

    public static void startNodeEditorActivity(GcgActivity anActivity, String aNodeListParentNodeId, ArrayList<FmmHeadlineNodeShallow> aHeadlineNodeShallowList, String anInitialNodeIdToDisplay) {
        FmmHeadlineNodeImpl.startNodeEditorActivity(
                anActivity,
                aNodeListParentNodeId,
                aHeadlineNodeShallowList,
                anInitialNodeIdToDisplay,
                FmmNodeDefinition.WORK_ASSET );
    }

    public static void startNodePickerActivity(GcgActivity anActivity, ArrayList<String> aNodeIdExclusionList, String aWhereClause, String aListActionLabel) {
        FmsActivityHelper.startHeadlineNodePickerActivity(anActivity, FmmNodeDefinition.PROJECT_ASSET, aNodeIdExclusionList, aWhereClause, aListActionLabel);
    }

    public static WorkAsset getFmmConfiguration(Intent anIntent) {
        return FmmDatabaseMediator.getActiveMediator().retrieveWorkAsset(NodeId.getNodeIdString(anIntent));
    }

    @Override
    public int getSequence(FmmNodeDefinition anFmmNodeDefinition) {
        FmmSequencedNode theSequencedNode = anFmmNodeDefinition == FmmNodeDefinition.STRATEGIC_MILESTONE ?
                FmmDatabaseMediator.getActiveMediator().retrieveStrategicCommitmentForStrategicAsset(getNodeIdString()) :
                this;  // within Project
        return theSequencedNode.getSequence();
    }

    public boolean hasMoveTargetWorkPackages(WorkPackage aWorkPackageException) {
        return FmmDatabaseMediator.getActiveMediator().getMoveTargetWorkPackageCount(this, aWorkPackageException) > 0;
    }

    public boolean isWorkPackageMoveTarget() {
        return true;
    }

    public int getWorkPackageBudget() {
        return this.workPackageBudget;
    }

    public void setWorkPackageBudget(int workPackageBudget) {
        this.workPackageBudget = workPackageBudget;
    }

    public int getPhantomWorkPackageCount() {
        int theCount = this.workPackageBudget - getWorkPackageList().size();
        return theCount < 0 ? 0 : theCount;
    }

    @Override
    public int getChildNodeCount(GcgPerspective aGcgPerspective) {  // only implemented for TreeView leaf nodes
        return getWorkPackageList() == null ? 0 : getWorkPackageList().size();
    }

    @Override
    public ArrayList<? extends FmmHeadlineNode> getChildList(FmmNodeDefinition aChildNodeDefinition) {
        ArrayList<? extends FmmHeadlineNodeImpl> theList = null;
        switch(aChildNodeDefinition) {
            case WORK_PACKAGE:
                theList = FmmDatabaseMediator.getActiveMediator().retrieveWorkPackageList(this);
                break;
        }
        return theList;
    }

    public boolean isStrategic() {
        return this.strategic;
    }

    public int getStrategicAsInt() {
        return this.strategic ? 1 : 0;
    }

    public void setStrategic(boolean bStrategic) {
        this.strategic = bStrategic;
    }

    public void setStrategic(int intStrategic) {
        this.strategic = intStrategic > 0;
    }

    public void setPrimaryParentId(String aNodeIdString) {
        setProjectId(aNodeIdString);
    }

}
