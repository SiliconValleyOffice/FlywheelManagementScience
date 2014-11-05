/* @(#)FmmHeadlineNodeImpl.java
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

package com.flywheelms.library.fmm.node.impl.headline;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import com.flywheelms.gcongui.deckangl.enumerator.DecKanGlDecoratedGlyphSize;
import com.flywheelms.gcongui.deckangl.enumerator.DecKanGlDecoratorCanvasLocation;
import com.flywheelms.gcongui.deckangl.enumerator.DecKanGlNounStateColor;
import com.flywheelms.gcongui.deckangl.enumerator.DecKanGlNounStateDrawableSize;
import com.flywheelms.gcongui.deckangl.glyph.DecKanGlDictionary;
import com.flywheelms.gcongui.deckangl.glyph.DecKanGlElementNoun;
import com.flywheelms.gcongui.deckangl.glyph.DecKanGlElementNounState;
import com.flywheelms.gcongui.deckangl.glyph.DecKanGlGlyph;
import com.flywheelms.gcongui.deckangl.interfaces.DecKanGlDecorator;
import com.flywheelms.gcongui.gcg.activity.GcgActivity;
import com.flywheelms.gcongui.gcg.interfaces.GcgPerspective;
import com.flywheelms.library.R;
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.context.FmmFrame;
import com.flywheelms.library.fmm.context.FmmPerspective;
import com.flywheelms.library.fmm.deckangl.FmmDecKanGlDictionary;
import com.flywheelms.library.fmm.deckangl.FmsDecKanGlNoun;
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
import com.flywheelms.library.fmm.meta_data.HeadlineNodeMetaData;
import com.flywheelms.library.fmm.node.FmmHeadlineNodeShallow;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.FmmHistoryNodeImpl;
import com.flywheelms.library.fmm.node.impl.enumerator.CompletableWorkStatus;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragAuditBlock;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragFseDocument;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragTribKnQuality;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmTreeNodeTargetObject;
import com.flywheelms.library.fms.helper.FmsActivityHelper;
import com.flywheelms.library.fse.model.FseDocument;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class FmmHeadlineNodeImpl extends FmmHistoryNodeImpl
		implements FmmHeadlineNode, FmmTreeNodeTargetObject, FmsDecKanGlNoun {

    private static final long serialVersionUID = 1L;
    private transient DecKanGlGlyph decKanGlGlyph;
    private transient NodeFragAuditBlock nodeFragAuditBlock;
    private transient NodeFragFseDocument nodeFragFseDocument;
    private transient NodeFragTribKnQuality nodeFragTribKnQuality;
    private HashMap<DecKanGlDecoratorCanvasLocation, DecKanGlDecorator> decKanGlDecoratorMap;
    protected String headline = "";
    protected HashMap<FmmPerspective, NodeCompletionSummary> nodeCompletionSummaryMap;
    public final ArrayList<FmmPerspective> perspectiveList = new ArrayList<FmmPerspective>();

    public FmmHeadlineNodeImpl(NodeId aNodeId) {
        super(aNodeId);
    }

    public FmmHeadlineNodeImpl(NodeId aNodeId, String aHeadline) {
        super(aNodeId);
        this.headline = aHeadline;
    }

    public FmmHeadlineNodeImpl(Class<? extends FmmHeadlineNodeImpl> aClass, JSONObject aJsonObject) {
        super(aClass, aJsonObject);
        try {
            setHeadline(aJsonObject.getString(HeadlineNodeMetaData.column_HEADLINE));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public NodeFragAuditBlock getUpdatedNodeFragAuditBlock() {
        updateNodeFragAuditBlock();
        return getNodeFragAuditBlock();
    }

    @Override
    public void updateNodeFragAuditBlock() {
        getNodeFragAuditBlock().update(this);
    }

    protected void initializeNodeCompletionSummaryMap() {
        this.nodeCompletionSummaryMap = new HashMap<FmmPerspective, NodeCompletionSummary>();
    }

    @Override
    public String getIdString() {
        return getNodeIdString();
    }

    @SuppressWarnings("unused")
    public void updateNodeCompletionSummary(FmmPerspective anFmmPerspective, NodeCompletionSummary aNodeSummary) {
        // TODO
        return;
    }

    public void updateAllNodeSummaries() {
        // iterate through node summary map
    }

    @Override
    public boolean canDelete() {
        return !isLocked();
    }

    protected class NodeCompletionSummary implements Serializable {

        private static final long serialVersionUID = 1L;
        boolean showNodeSummary = false;
        String summaryPrefix = "";
        int summaryDrawableResourceId = R.drawable.gcg__empty_bitmap;
        String summarySuffix = "";

        public NodeCompletionSummary() {
            // TODO Auto-generated constructor stub
        }

        public String getSummaryPrefix() {
            return this.summaryPrefix;
        }

        public void setSummaryPrefix(String aSummaryPrefix) {
            this.summaryPrefix = aSummaryPrefix;
        }

        public int getSummaryDrawableResourceId() {
            return this.summaryDrawableResourceId;
        }

        public void setSummaryDrawableResourceId(int aSummaryDrawableResourceId) {
            this.summaryDrawableResourceId = aSummaryDrawableResourceId;
        }

        public String getSummarySuffix() {
            return this.summarySuffix;
        }

        public void setSummarySuffix(String summarySuffix) {
            this.summarySuffix = summarySuffix;
        }

        public boolean isShowNodeSummary() {
            return this.showNodeSummary;
        }

        public void setShowNodeSummary(boolean aBoolean) {
            this.showNodeSummary = aBoolean;
        }

    }

    @Override
    public FseDocument getFseDocument() {
        return FmmDatabaseMediator.getActiveMediator().getFseDocumentForParent(getNodeIdString());
    }

    @Override
    public FseDocument getFseDocumentForPublication() {
        return FmmDatabaseMediator.getActiveMediator().getFseDocumentForParent(getNodeIdString(), false).getLastTransactionWithMarkup();
    }

    @Override
    public String getHeadline() {
        return this.headline;
    }

    @Override
    public void setHeadline(String aHeadline) {
        this.headline = aHeadline;
    }

    public HashMap<FmmPerspective, NodeCompletionSummary> getNodeSummaryMap() {
        if (this.nodeCompletionSummaryMap == null) {
            initializeNodeCompletionSummaryMap();
        }
        return this.nodeCompletionSummaryMap;
    }

    @Override
    public boolean hasNodeSummary(GcgPerspective aGcgPerspective) {
        NodeCompletionSummary theNodeSummary = getNodeSummaryMap().get(aGcgPerspective);
        return theNodeSummary == null ? false : theNodeSummary.isShowNodeSummary();
    }

    public String getNodeSummaryPrefix(GcgPerspective aGcgPerspective) {
        NodeCompletionSummary theNodeSummary = getNodeSummaryMap().get(aGcgPerspective);
        return theNodeSummary == null ? "" : theNodeSummary.getSummaryPrefix();
    }

    @Override
    public int getNodeSummaryDrawableResourceId(GcgPerspective aGcgPerspective) {
        NodeCompletionSummary theNodeSummary = getNodeSummaryMap().get(aGcgPerspective);
        return theNodeSummary == null
                ? R.drawable.gcg__empty_bitmap
                : theNodeSummary.getSummaryDrawableResourceId();
    }

    @Override
    public String getNodeSummarySuffix(GcgPerspective aGcgPerspective) {
        NodeCompletionSummary theNodeSummary = getNodeSummaryMap().get(aGcgPerspective);
        return theNodeSummary == null ? "" : theNodeSummary.getSummarySuffix();
    }

    @Override
    public DecKanGlNounStateColor getDecKanGlNounStateColor() {
        return DecKanGlNounStateColor.GRAY;  // "placeholder" implementation - SDS
    }

    @Override
    public HashMap<DecKanGlDecoratorCanvasLocation, DecKanGlDecorator> getDecKanGlDecoratorMap() {
        if (this.decKanGlDecoratorMap == null) {
            this.decKanGlDecoratorMap = FmmDatabaseMediator.getActiveMediator().retrieveNodeFragTribKnQuality(this).getDecoratorMap();
        }
        return this.decKanGlDecoratorMap;
    }

    @Override
    public HashMap<DecKanGlDecoratorCanvasLocation, DecKanGlDecorator> getUpdatedDecKanGlDecoratorMap() {
        HashMap<DecKanGlDecoratorCanvasLocation, DecKanGlDecorator> theDecKanGlDecoratorMap =
                new HashMap<DecKanGlDecoratorCanvasLocation, DecKanGlDecorator>();
        theDecKanGlDecoratorMap.put(
                getDecoratorGovernance().getDecoratorCanvasLocation(),
                getDecoratorGovernance());
        theDecKanGlDecoratorMap.put(
                getDecoratorFacilitationIssue().getDecoratorCanvasLocation(),
                getDecoratorFacilitationIssue());
        theDecKanGlDecoratorMap.put(
                getDecoratorFacilitator().getDecoratorCanvasLocation(),
                getDecoratorFacilitator());
        theDecKanGlDecoratorMap.put(
                getDecoratorParentFractals().getDecoratorCanvasLocation(),
                getDecoratorParentFractals());
        theDecKanGlDecoratorMap.put(
                getDecoratorChildFractals().getDecoratorCanvasLocation(),
                getDecoratorChildFractals());
        theDecKanGlDecoratorMap.put(
                getDecoratorStrategicCommitment().getDecoratorCanvasLocation(),
                getDecoratorStrategicCommitment());
        theDecKanGlDecoratorMap.put(
                getDecoratorFlywheelCommitment().getDecoratorCanvasLocation(),
                getDecoratorFlywheelCommitment());
        theDecKanGlDecoratorMap.put(
                getDecoratorWorkTaskBudget().getDecoratorCanvasLocation(),
                getDecoratorWorkTaskBudget());
        theDecKanGlDecoratorMap.put(
                getDecoratorWorkTeam().getDecoratorCanvasLocation(),
                getDecoratorWorkTeam());
        theDecKanGlDecoratorMap.put(
                getDecoratorStory().getDecoratorCanvasLocation(),
                getDecoratorStory());
        theDecKanGlDecoratorMap.put(
                getDecoratorSequence().getDecoratorCanvasLocation(),
                getDecoratorSequence());
        theDecKanGlDecoratorMap.put(
                getDecoratorCompletion().getDecoratorCanvasLocation(),
                getDecoratorCompletion());
        this.decKanGlDecoratorMap = theDecKanGlDecoratorMap;
        return this.decKanGlDecoratorMap;
    }

    @Override
    public String getDecKanGlNounName() {
        return getDecKanGlElementNoun().getName();
    }

    @Override
    public DecKanGlGlyph getDecKanGlGlyph() {
        if (this.decKanGlGlyph == null) {
            updateDecKanGlGlyph();
        }
        return this.decKanGlGlyph;
    }

    @Override
    public void updateDecKanGlGlyph() {
        this.decKanGlGlyph = FmmDecKanGlDictionary.getInstance().getDecKanGlGlyph(this);
    }

    @Override
    public DecKanGlGlyph getUpdatedDecKanGlGlyph() {
        updateDecKanGlGlyph();
        return this.decKanGlGlyph;
    }

    @Override
    public DecKanGlElementNoun getDecKanGlElementNoun() {
        return FmmDecKanGlDictionary.getInstance().getNoun(getNodeTypeName());
    }

    @Override
    public FmsDecoratorGovernance getDecoratorGovernance() {
        return FmsDecoratorGovernance.GOVERNANCE_QUALITY_NOT_ENABLED;
    }

    @Override
    public FmsDecoratorFacilitationIssue getDecoratorFacilitationIssue() {
        return FmsDecoratorFacilitationIssue.FACILITATION_ISSUE_QUALITY_NOT_ENABLED;
    }

    @Override
    public FmsDecoratorFacilitator getDecoratorFacilitator() {
        return FmsDecoratorFacilitator.FACILITATOR_QUALITY_NOT_ENABLED;
    }

    @Override
    public FmsDecoratorStrategicCommitment getDecoratorStrategicCommitment() {
        return FmsDecoratorStrategicCommitment.STRATEGIC_COMMITMENT_QUALITY_NOT_ENABLED;
    }

    @Override
    public FmsDecoratorFlywheelCommitment getDecoratorFlywheelCommitment() {
        return FmsDecoratorFlywheelCommitment.FLYWHEEL_COMMITMENT_QUALITY_NOT_ENABLED;
    }

    @Override
    public FmsDecoratorParentFractals getDecoratorParentFractals() {
        return FmsDecoratorParentFractals.PARENT_FRACTAL_QUALITY_NOT_ENABLED;
    }

    @Override
    public FmsDecoratorChildFractals getDecoratorChildFractals() {
        return FmsDecoratorChildFractals.CHILD_FRACTAL_QUALITY_NOT_ENABLED;
    }

    @Override
    public FmsDecoratorWorkTaskBudget getDecoratorWorkTaskBudget() {
        return FmsDecoratorWorkTaskBudget.BUDGET_QUALITY_NOT_ENABLED;
    }

    @Override
    public FmsDecoratorWorkTeam getDecoratorWorkTeam() {
        return FmsDecoratorWorkTeam.TEAM_QUALITY_NOT_ENABLED;
    }

    @Override
    public FmsDecoratorStory getDecoratorStory() {
        return FmsDecoratorStory.STORY_QUALITY_NOT_ENABLED;
    }

    @Override
    public FmsDecoratorSequence getDecoratorSequence() {
        return FmsDecoratorSequence.SEQUENCE_QUALITY_NOT_ENABLED;
    }

    @Override
    public FmsDecoratorCompletion getDecoratorCompletion() {
        return FmsDecoratorCompletion.COMPLETION_QUALITY_NOT_ENABLED;
    }

    @Override
    public boolean hasNodeQuality() {
        return true;
    }

    @Override
    public int getNodeQualityIndex() {
        return getDecKanGlGlyph().getNounQualityIndex();
    }

    @Override
    public boolean isComplete() {
        return false;
    }

    /////////////////////////////////////////

    @Override
    public BitmapDrawable getDecKanGlElementNounStateBitmapDrawable() {
        return getDecKanGlGlyph().getNounStateDrawable(DecKanGlNounStateDrawableSize.SMALL);
    }

    @Override
    public BitmapDrawable getDecKanGlElementNounStateBitmapDrawable(DecKanGlNounStateDrawableSize aDrawableSize) {
        return getDecKanGlGlyph().getNounStateDrawable(aDrawableSize);
    }

    @Override
    public BitmapDrawable getUpdatedDecKanGlElementNounStateBitmapDrawable() {
        return getUpdatedDecKanGlElementNounStateBitmapDrawable(DecKanGlNounStateDrawableSize.SMALL);
    }

    @Override
    public BitmapDrawable getUpdatedDecKanGlElementNounStateBitmapDrawable(DecKanGlNounStateDrawableSize aDrawableSize) {
        updateDecKanGlElementNounState();
        return getDecKanGlElementNounStateBitmapDrawable(aDrawableSize);
    }

    @Override
    public void updateDecKanGlElementNounState() {
        // compute new Work State
        // if has DecKanGlGlyph && different from DecKanGlGlyph, get a new DecKanGlGlyph for this object
    }

    @Override
    public FmmHeadlineNodeShallow getFmmHeadlineNodeShallow() {
        return new FmmHeadlineNodeShallow(this);
    }


    @Override
    public JSONObject getJsonObject() {
        JSONObject theJsonObject = super.getJsonObject();
        try {
            theJsonObject.put(HeadlineNodeMetaData.column_HEADLINE, getHeadline());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return theJsonObject;
    }

    @Override
    public Bitmap getAnnotatedDecKanGlBitmap() {
        return getDecKanGlGlyph().getAnnotatedGlyphBitmap();
    }

    @Override
    public BitmapDrawable getDecKanGlElementNounStateDrawable() {
        return getDecKanGlElementNounStateDrawable(DecKanGlNounStateDrawableSize.SMALL);
    }

    @Override
    public BitmapDrawable getDecKanGlElementNounStateDrawable(DecKanGlNounStateDrawableSize aDrawableSize) {
        return getDecKanGlGlyph().getNounStateDrawable(aDrawableSize);
    }

    @Override
    public BitmapDrawable getUpdatedNounStatusDrawable() {
        return getUpdatedNounStatusDrawable(DecKanGlNounStateDrawableSize.SMALL);
    }

    @Override
    public BitmapDrawable getUpdatedNounStatusDrawable(DecKanGlNounStateDrawableSize aDrawableSize) {
        this.decKanGlGlyph = FmmDecKanGlDictionary.getInstance().getDecKanGlGlyph(this);
        return this.decKanGlGlyph.getNounStateDrawable(aDrawableSize);
    }

    @Override
    public CompletableWorkStatus getCompletableWorkStatus() {
        return null;
    }

    @Override
    public DecKanGlElementNounState getDecKanGlNounState() {
        return DecKanGlDictionary.getInstance().getNounStateForColor(getDecKanGlNounStateColor().getName());
    }

    @Override
    public DecKanGlElementNounState updateDecKanGlNounState() {
        // TODO
        // - retrieve from NodeFragCompletion
        // - compute until NodeFragCompletion value validated or invalidated
        // - update NodeFragCompletion
        // - update DecKanGlGlyph
        return DecKanGlDictionary.getInstance().getNounStateForColor(getDecKanGlNounStateColor().getName());
    }

    @Override
    public DecKanGlNounStateColor computeDecKanGlNounStateColor() {
        // TODO
        return DecKanGlNounStateColor.GRAY;
    }

    @Override
    public Bitmap getDecKanGlBitmap() {
        return getDecKanGlBitmap(DecKanGlDecoratedGlyphSize.SMALL);
    }

    @Override
    public Bitmap getDecKanGlBitmap(DecKanGlDecoratedGlyphSize aGlyphScaling) {
        return getDecKanGlGlyph().getDecoratedNounBitmap(aGlyphScaling);
    }

    @Override
    public void replaceWithNew(NodeId aNodeId, String aHeadline) {
        super.replaceWithNew(aNodeId);
        this.headline = aHeadline;
    }

    public static void startNodeEditorActivity(
            GcgActivity anActivity,
            String aNodeListParentNodeId,
            ArrayList<FmmHeadlineNodeShallow> aHeadlineNodeShallowList,
            String anInitialNodeIdToDisplay,
            FmmNodeDefinition anFmmNodeDefinition) {
        FmsActivityHelper.startHeadlineNodeEditorActivity(
                anActivity,
                aNodeListParentNodeId,
                aHeadlineNodeShallowList,
                anInitialNodeIdToDisplay,
                anFmmNodeDefinition,
                FmmFrame.FSE,
                FmmPerspective.STORY);
    }

    @Override
    public String getDataText() {
        return getHeadline();
    }

    @Override
    public boolean canDelete(FmmHeadlineNode aContextHeadlineNode) {
        return true;
    }

    @Override
    public boolean canMove() {
        return true;
    }

    @Override
    public boolean canMove(FmmHeadlineNode aContextHeadlineNode) {
        return true;
    }

    @Override
    public boolean canOrphan() {
        return true;
    }

    @Override
    public boolean canOrphan(FmmHeadlineNode aContextHeadlineNode) {
        return true;
    }

    @Override
    public String getSponsorName() {
        // TODO
        return null;
    }

    @Override
    public String getCustomerName() {
        // TODO
        return null;
    }

    @Override
    public String getFacilitatorName() {
        // TODO
        return null;
    }

    @Override
    public String getAdministratorName() {
        // TODO
        return null;
    }

    @Override
    public String getTargetDateString() {
        return "";
    }

    @Override
    public boolean hasSecondaryHeadline() {
        return false;
    }

    @Override
    public String getSecondaryHeadline() {
        return "";
    }

    @Override
    public ArrayList<? extends FmmHeadlineNode> getPeerHeadlineNodeShallowList(FmmHeadlineNode aParentHeadlineNode) {
        ArrayList<FmmHeadlineNode> theList = new ArrayList<FmmHeadlineNode>();
        theList.add(this.getFmmHeadlineNodeShallow());
        return theList;
    }

    @Override
    public int getChildNodeCount(GcgPerspective aGcgPerspective) {  // only implemented for TreeView leaf nodes
        return 0;
    }

    @Override
    public ArrayList<? extends FmmHeadlineNode> getChildList(FmmNodeDefinition aChildNodeDefinition) {
        ArrayList<? extends FmmHeadlineNode> theList = new ArrayList<FmmHeadlineNodeImpl>();
        return theList;
    }

    @Override
    public ArrayList<FmmHeadlineNodeShallow> getChildListShallow(FmmNodeDefinition aChildNodeDefinition) {
        ArrayList<FmmHeadlineNodeShallow> theList = new ArrayList<FmmHeadlineNodeShallow>();
        for (FmmHeadlineNode theHeadlineNode : getChildList(aChildNodeDefinition)) {
            theList.add(theHeadlineNode.getFmmHeadlineNodeShallow());
        }
        return theList;
    }


//    private transient NodeFragAuditBlock nodeFragAuditBlock;
//    private transient NodeFragFseDocument nodeFragFseDocument;
//    private transient NodeFragTribKnQuality nodeFragTribKnQuality;

    @Override
    public void setNodeFragAuditBlock(NodeFragAuditBlock aNodeFragAuditBlock) {
        this.nodeFragAuditBlock = aNodeFragAuditBlock;
    }

    @Override
    public NodeFragAuditBlock getNodeFragAuditBlock() {
        if (this.nodeFragAuditBlock == null) {
            this.nodeFragAuditBlock = FmmDatabaseMediator.getActiveMediator().retrieveNodeFragAuditBlock(this);
        }
        return this.nodeFragAuditBlock;
    }

    @Override
    public void setNodeFragFseDocument(NodeFragFseDocument aNodeFragFseDocument) {
        this.nodeFragFseDocument = aNodeFragFseDocument;
    }

    public NodeFragFseDocument getNodeFragFseDocument() {
        if(this.nodeFragFseDocument == null) {
            this.nodeFragFseDocument = FmmDatabaseMediator.getActiveMediator().retrieveNodeFragFseDocument(this);
        }
        return this.nodeFragFseDocument;
    }

    public NodeFragFseDocument getUpdatedNodeFragFseDocument() {
        NodeFragFseDocument theNodeFragFseDocument = getNodeFragFseDocument();
        if(theNodeFragFseDocument.unsavedChanges()) {
            theNodeFragFseDocument.setRowTimestamp(getRowTimestamp());
        }
        return theNodeFragFseDocument;
    }

    @Override
    public void setNodeFragTribKnQuality(NodeFragTribKnQuality aNodeFragTribKnQuality) {
        this.nodeFragTribKnQuality = aNodeFragTribKnQuality;
    }

    @Override
    public NodeFragTribKnQuality getNodeFragTribKnQuality() {
        if(this.nodeFragTribKnQuality == null) {
            this.nodeFragTribKnQuality = FmmDatabaseMediator.getActiveMediator().retrieveNodeFragTribKnQuality(this);
        }
        return this.nodeFragTribKnQuality;
    }

    public NodeFragTribKnQuality getUpdatedNodeFragTribKnQuality() {
        NodeFragTribKnQuality theNodeFragTribKnQuality = getNodeFragTribKnQuality();
        // TODO - update attributes
        return theNodeFragTribKnQuality;
    }

    @Override
    public String getSerializedNodeForTrash() {
        // some headline nodes will want to serialize a little deeper, such as
        // StrategicMilestone wanting to include its StrategicCommitment
        return getSerialized();
    }

}
