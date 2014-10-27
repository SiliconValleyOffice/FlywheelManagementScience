/* @(#)DiscussionTopic.java
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
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorChildFractals;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorCompletion;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorFacilitationIssue;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorFacilitator;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorFlywheelCommitment;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorGovernance;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorParentFractals;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorSequence;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorStory;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorStrategicCommitment;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorWorkTaskBudget;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorWorkTeam;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.headline.FmmHeadlineNodeImpl;
import com.flywheelms.library.fmm.node.impl.link.DiscussionTopicLinkToNodeFragAuditBlock;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;
import com.flywheelms.library.util.JsonHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class DiscussionTopic extends FmmGovernableNodeImpl {

	private static final long serialVersionUID = 4484750862419032893L;
    public static final String SERIALIZATION_FORMAT_VERSION = "0.1";
    private String notebookId;
    private Notebook notebook;
    private ArrayList<DiscussionTopicLinkToNodeFragAuditBlock> discussionTopicLinkToNodeFragAuditBlockList;


    public DiscussionTopic() {
        super(new NodeId(FmmNodeDefinition.DISCUSSION_TOPIC));
    }

    public DiscussionTopic(String anExistingNodeIdString) {
        super(NodeId.hydrate(
                DiscussionTopic.class,
                anExistingNodeIdString));
    }

    public DiscussionTopic(NodeId aNodeId, String aHeadline, String aNotebookId) {
        super(aNodeId);
        setHeadline(aHeadline);
        setNotebookId(aNotebookId);
    }

    public DiscussionTopic(NodeId aNodeId, String aHeadline, Notebook aNotebook) {
        super(aNodeId);
        setHeadline(aHeadline);
        setNotebook(aNotebook);
    }

    public DiscussionTopic(JSONObject aJsonObject) {
        super(DiscussionTopic.class, aJsonObject);
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

    public JSONArray getDiscussionTopicLinkToNodeFragAuditBlockIdJsonArray() {
        JSONArray theJsonArray = new JSONArray();
        for(DiscussionTopicLinkToNodeFragAuditBlock theLink : getDiscussionTopicLinkToNodeFragAuditBlockList()) {
            theJsonArray.put(theLink.getNodeIdString());
        }
        return theJsonArray;
    }

    public ArrayList<DiscussionTopicLinkToNodeFragAuditBlock> getDiscussionTopicLinkToNodeFragAuditBlockList() {
        if(this.discussionTopicLinkToNodeFragAuditBlockList == null) {
            this.discussionTopicLinkToNodeFragAuditBlockList = FmmDatabaseMediator.getActiveMediator().getDiscussionTopicLinkToNodeFragAuditBlockList(this);
        }
        return this.discussionTopicLinkToNodeFragAuditBlockList;
    }

    @Override
    public DiscussionTopic getClone() {
        return new DiscussionTopic(getJsonObject());
    }

    public String getNotebookId() {
        return this.notebookId;
    }

    public void setNotebookId(String anNotebookNodeId) {
        this.notebookId = anNotebookNodeId;
        if(this.notebook != null && ! this.notebook.getNodeIdString().equals(anNotebookNodeId)) {
            this.notebook = null;
        }
    }

    public Notebook getNotebook() {
        if(this.notebook == null && this.notebookId != null) {
            this.notebook = FmmDatabaseMediator.getActiveMediator().retrieveNotebook(this.notebookId);
        }
        return this.notebook;
    }

    public void setNotebook(Notebook anNotebook) {
        this.notebook = anNotebook;
        this.notebookId = anNotebook.getNodeIdString();
    }

    @Override
    public ArrayList<? extends FmmHeadlineNode> getChildList(FmmNodeDefinition aChildNodeDefinition) {
        ArrayList<? extends FmmHeadlineNodeImpl> theList = null;
        return theList;
    }

    public void setPrimaryParentId(String aNodeIdString) {
        setNotebookId(aNodeIdString);
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
                FmsDecoratorParentFractals.ONE_CONFIRMED_PARENT_FRACTAL.getDecoratorCanvasLocation(),
                FmsDecoratorParentFractals.ONE_CONFIRMED_PARENT_FRACTAL );
        theDecKanGlDecoratorMap.put(
                FmsDecoratorChildFractals.PROPOSED_CHILD_FRACTALS.getDecoratorCanvasLocation(),
                FmsDecoratorChildFractals.PROPOSED_CHILD_FRACTALS );
        theDecKanGlDecoratorMap.put(
                FmsDecoratorStrategicCommitment.PROPOSED_STRATEGIC_COMMITMENT.getDecoratorCanvasLocation(),
                FmsDecoratorStrategicCommitment.PROPOSED_STRATEGIC_COMMITMENT );
        theDecKanGlDecoratorMap.put(
                FmsDecoratorFlywheelCommitment.PROPOSED_FLYWHEEL_COMMITMENT.getDecoratorCanvasLocation(),
                FmsDecoratorFlywheelCommitment.PROPOSED_FLYWHEEL_COMMITMENT );
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

}
