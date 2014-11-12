/* @(#)Notebook.java
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
import com.flywheelms.library.fmm.FmmDatabaseMediator;
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
import com.flywheelms.library.fmm.node.FmmHeadlineNodeShallow;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.completable.FmmCompletionNodeImpl;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.headline.FmmHeadlineNodeImpl;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;
import com.flywheelms.library.fms.helper.FmsActivityHelper;
import com.flywheelms.library.util.JsonHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Notebook extends FmmCompletionNodeImpl {

    private static final long serialVersionUID = -4572739802256638560L;
    public static final String SERIALIZATION_FORMAT_VERSION = "0.1";
    private ArrayList<Bookshelf> bookshelfList;
    private ArrayList<DiscussionTopic> discussionTopicList;

    public Notebook() {
        super(new NodeId(FmmNodeDefinition.NOTEBOOK));
    }

    public Notebook(String anExistingNodeIdString) {
        super(NodeId.hydrate(
                Notebook.class,
                anExistingNodeIdString));
    }

    public Notebook(NodeId aNodeId, String aHeadline) {
        super(aNodeId);
        setHeadline(aHeadline);
    }

    public Notebook(JSONObject aJsonObject) {
        super(Notebook.class, aJsonObject);
        try {
            validateSerializationFormatVersion(aJsonObject.getString(JsonHelper.key__SERIALIZATION_FORMAT_VERSION));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public JSONObject getJsonObject() {
        JSONObject theJsonObject = super.getJsonObject();
        try {
            theJsonObject.put(JsonHelper.key__SERIALIZATION_FORMAT_VERSION, SERIALIZATION_FORMAT_VERSION);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return theJsonObject;
    }

    public JSONArray getDiscussionTopicIdJsonArray() {
        JSONArray theJsonArray = new JSONArray();
        for(DiscussionTopic theDiscussionTopic : getDiscussionTopicList()) {
            theJsonArray.put(theDiscussionTopic.getNodeIdString());
        }
        return theJsonArray;
    }

    @Override
    public Notebook getClone() {
        return new Notebook(getJsonObject());
    }

    public Collection<Bookshelf> getBookshelfCollection() {
        return getBookshelfList();
    }

    public ArrayList<Bookshelf> getBookshelfList() {
        if(this.bookshelfList == null) {
            this.bookshelfList = FmmDatabaseMediator.getActiveMediator().retrieveBookshelfList(this);
        }
        return this.bookshelfList;
    }

    public Collection<DiscussionTopic> getDiscussionTopicCollection() {
        return getDiscussionTopicList();
    }

    public ArrayList<DiscussionTopic> getDiscussionTopicList() {
        if(this.discussionTopicList == null) {
            this.discussionTopicList = FmmDatabaseMediator.getActiveMediator().retrieveDiscussionTopicList(this);
        }
        return this.discussionTopicList;
    }

    /////////////////////////////////////////////////////////////////////////
    //////  TEMPORARY for development scaffolding  //////////////////////////
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
                FmsDecoratorWorkBreakdown.PROPOSED_CHILD_FRACTALS.getDecoratorCanvasLocation(),
                FmsDecoratorWorkBreakdown.PROPOSED_CHILD_FRACTALS );
        theDecKanGlDecoratorMap.put(
                FmsDecoratorStrategicCommitment.PROPOSED_STRATEGIC_COMMITMENT.getDecoratorCanvasLocation(),
                FmsDecoratorStrategicCommitment.PROPOSED_STRATEGIC_COMMITMENT );
        theDecKanGlDecoratorMap.put(
                FmsDecoratorCadenceCommitment.PROPOSED_CADENCE_COMMITMENT.getDecoratorCanvasLocation(),
                FmsDecoratorCadenceCommitment.PROPOSED_CADENCE_COMMITMENT);
        theDecKanGlDecoratorMap.put(
                FmsDecoratorWorkTaskBudget.CONFIRMED_TASK_BUDGET.getDecoratorCanvasLocation(),
                FmsDecoratorWorkTaskBudget.CONFIRMED_TASK_BUDGET );
        theDecKanGlDecoratorMap.put(
                FmsDecoratorWorkTeam.CONFIRMED_TEAM.getDecoratorCanvasLocation(),
                FmsDecoratorWorkTeam.CONFIRMED_TEAM );
        theDecKanGlDecoratorMap.put(
                FmsDecoratorStory.PROPOSED_STORY.getDecoratorCanvasLocation(),
                FmsDecoratorStory.PROPOSED_STORY );
        theDecKanGlDecoratorMap.put(
                FmsDecoratorSequence.PROPOSED_SEQUENCE.getDecoratorCanvasLocation(),
                FmsDecoratorSequence.PROPOSED_SEQUENCE );
        theDecKanGlDecoratorMap.put(
                FmsDecoratorCompletion.NO_COMPLETION_ISSUES.getDecoratorCanvasLocation(),
                FmsDecoratorCompletion.NO_COMPLETION_ISSUES );
        return theDecKanGlDecoratorMap;
    }

    public static void startNodeEditorActivity(GcgActivity anActivity, String aNodeListParentNodeId, ArrayList<FmmHeadlineNodeShallow> aHeadlineNodeShallowList, String anInitialNodeIdToDisplay) {
        FmmHeadlineNodeImpl.startNodeEditorActivity(
                anActivity,
                aNodeListParentNodeId,
                aHeadlineNodeShallowList,
                anInitialNodeIdToDisplay,
                FmmNodeDefinition.BOOKSHELF);
    }

    public static void startNodePickerActivity(GcgActivity anActivity, ArrayList<String> aNodeIdExclusionList, String aWhereClause, String aListActionLabel) {
        FmsActivityHelper.startHeadlineNodePickerActivity(anActivity, FmmNodeDefinition.BOOKSHELF, aNodeIdExclusionList, aWhereClause, aListActionLabel);
    }

    @Override
    public ArrayList<? extends FmmHeadlineNode> getChildList(FmmNodeDefinition aChildNodeDefinition) {
        ArrayList<? extends FmmHeadlineNodeImpl> theList = null;
        switch(aChildNodeDefinition) {
            case DISCUSSION_TOPIC:
                theList = FmmDatabaseMediator.getActiveMediator().retrieveDiscussionTopicList(this);
                break;
        }
        return theList;
    }

    @Override
    public void setPrimaryParentId(String aNodeIdString) {

    }
}
