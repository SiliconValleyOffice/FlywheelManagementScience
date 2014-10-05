/* @(#)FmmHeadlineNodeShallow.java
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

package com.flywheelms.library.fmm.node;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.flywheelms.gcongui.deckangl.enumerator.DecKanGlDecoratorCanvasLocation;
import com.flywheelms.gcongui.deckangl.enumerator.DecKanGlNounStateColor;
import com.flywheelms.gcongui.deckangl.enumerator.DecKanGlNounStateDrawableSize;
import com.flywheelms.gcongui.deckangl.glyph.DecKanGlGlyph;
import com.flywheelms.gcongui.deckangl.interfaces.DecKanGlDecorator;
import com.flywheelms.gcongui.gcg.interfaces.GcgPerspective;
import com.flywheelms.gcongui.gcg.treeview.node.GcgTreeNodeInfo;
import com.flywheelms.library.R;
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
import com.flywheelms.library.fmm.enumerator.FmmLockStatus;
import com.flywheelms.library.fmm.meta_data.HeadlineNodeMetaData;
import com.flywheelms.library.fmm.meta_data.IdNodeMetaData;
import com.flywheelms.library.fmm.node.impl.FmmNodeImpl;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.governable.CommunityMember;
import com.flywheelms.library.fmm.node.impl.nodefrag.FmmHistoryEvent.HistoryEventData;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragAuditBlock;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragTribKnQuality;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;
import com.flywheelms.library.fse.model.FseDocument;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class FmmHeadlineNodeShallow implements FmmHeadlineNode {

	public static final String json_key__DECKANGL_NOUN_STATE_COLOR = "DecKanGlNounStateColor";
	public static final int NULL_WORK_STATE_DRAWABLE_RESOURCE_ID = R.drawable.gcg__null_drawable_15;
	private String nodeIdString;
	private String headline;
	private DecKanGlNounStateColor nounStateColor;
	private BitmapDrawable workStateDrawable = null;
	private DecKanGlNounStateDrawableSize workStateDrawableSize = DecKanGlNounStateDrawableSize.TINY;
	private FmmNodeDefinition fmmNodeDictionaryEntry;
	private FmmHeadlineNode fmmHeadlineNode;
	
	public FmmHeadlineNodeShallow(String aNodeIdString) {
		this.nodeIdString = aNodeIdString;
	}
	
	public FmmHeadlineNodeShallow(String aNodeIdString, String aHeadline) {
		this.nodeIdString = aNodeIdString;
		this.headline = aHeadline;
	}
	
	public FmmHeadlineNodeShallow(String aNodeIdString, String aHeadline, BitmapDrawable aWorkStateDrawable) {
		this.nodeIdString = aNodeIdString;
		this.headline = aHeadline;
		this.workStateDrawable = aWorkStateDrawable;
	}

	public FmmHeadlineNodeShallow(FmmHeadlineNode aHeadlineNode) {
		this.fmmHeadlineNode = aHeadlineNode;
		this.nodeIdString = aHeadlineNode.getNodeIdString();
		this.headline = aHeadlineNode.getHeadline();
		this.workStateDrawable = aHeadlineNode.getDecKanGlElementNounStateBitmapDrawable();
	}
	
	public FmmHeadlineNodeShallow(JSONObject aJsonObject) {
		try {
			this.nodeIdString = aJsonObject.getString(IdNodeMetaData.column_ID);
			this.headline = aJsonObject.getString(HeadlineNodeMetaData.column_HEADLINE);
			String theNounStateColorName = aJsonObject.getString(json_key__DECKANGL_NOUN_STATE_COLOR); 
			this.nounStateColor = DecKanGlNounStateColor.getObjectForName(theNounStateColorName);
			this.workStateDrawable = getFmmNodeDefinition().getWorkStateDrawableForColor(theNounStateColorName);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public FmmHeadlineNodeShallow(GcgTreeNodeInfo theTreeNodeInfo) {
		this.nodeIdString = theTreeNodeInfo.getTargetObject().getIdString();
		this.headline = theTreeNodeInfo.getHeadline();
		this.workStateDrawable = theTreeNodeInfo.getTargetObject().getDecKanGlElementNounStateDrawable(DecKanGlNounStateDrawableSize.SMALL);
	}
	
	@Override
	public DecKanGlNounStateColor getDecKanGlNounStateColor() {
		if(this.nounStateColor == null) {
			this.nounStateColor = getFmmHeadlineNode().getDecKanGlNounStateColor();
		}
		return this.nounStateColor;
	}

	@Override
	public String getNodeIdString() {
		return this.nodeIdString;
	}
	
	@Override
	public FmmNodeDefinition getFmmNodeDefinition() {
		if(this.fmmNodeDictionaryEntry == null) {
			this.fmmNodeDictionaryEntry = FmmNodeDefinition.getEntryForNodeIdString(this.nodeIdString);
		}
		return this.fmmNodeDictionaryEntry;
	}
	
	@Override
	public int getLabelDrawableResourceId() {
		return getFmmNodeDefinition().getLabelDrawableResourceId();
	}
	
	@Override
	public String getNodeTypeName() {
		return getFmmNodeDefinition().getNodeTypeName();
	}
	
	@Override
	public String getHeadline() {
		if(this.headline == null) {
			this.headline = getFmmHeadlineNode().getHeadline();
		}
		return this.headline;
	}
	
	@Override
	public FseDocument getFseDocument() {
		return getFmmHeadlineNode().getFseDocument();
	}

	@Override
	public FseDocument getFseDocumentForPublication() {
		return getFmmHeadlineNode().getFseDocumentForPublication();
	}
	
	public FmmHeadlineNode getFmmHeadlineNode() {
		if(this.fmmHeadlineNode == null) {
			this.fmmHeadlineNode = FmmDatabaseMediator.getActiveMediator().getHeadlineNode(this.nodeIdString);
		}
		return this.fmmHeadlineNode;
	}
	
	public void setFmmHeadlineNode(FmmHeadlineNode aHeadlineNode) {
		this.fmmHeadlineNode = aHeadlineNode;
	}
	
	@Override
	public String toString() {
		return  getNodeIdString();
	}

	@Override
	public NodeId getNodeId() {
		return getFmmHeadlineNode().getNodeId();
	}

	@Override
	public String getAbbreviatedNodeIdString() {
		return getFmmHeadlineNode().getAbbreviatedNodeIdString();
	}

	@Override
	public Date getRowTimestamp() {
		return getFmmHeadlineNode().getRowTimestamp();
	}

	@Override
	public void setRowTimestamp(Date aTimestamp) {
		getFmmHeadlineNode().setRowTimestamp(aTimestamp);
	}

	@Override
	public String getNodeTypeCode() {
		return getFmmHeadlineNode().getNodeTypeCode();
	}

	@Override
	public int getNodeEditorActivityRequestCode() {
		return getFmmHeadlineNode().getNodeEditorActivityRequestCode();
	}

	@Override
	public int getNodeCreateActivityRequestCode() {
		return getFmmHeadlineNode().getNodeCreateActivityRequestCode();
	}
	
	@Override
	public int getNodePickerActivityRequestCode() {
		return getFmmNodeDefinition().getNodePickerActivityRequestCode();
	}

	@Override
	public boolean isModified(String aSerializedObject) {
		return getFmmHeadlineNode().isModified(aSerializedObject);
	}

	@Override
	public FmmNodeImpl getClone() {
		return getFmmHeadlineNode().getClone();
	}

	@Override
	public String getLabelText() {
		return getFmmHeadlineNode().getLabelText();
	}

	@Override
	public Drawable getLabelDrawable() {
		return getFmmHeadlineNode().getLabelDrawable();
	}

	@Override
	public String getDataText() {
		return getFmmHeadlineNode().getDataText();
	}

	@Override
	public Drawable getDataDrawable() {
		return getFmmHeadlineNode().getDataDrawable();
	}

	@Override
	public int getDataDrawableResourceId() {
		return getFmmHeadlineNode().getDataDrawableResourceId();
	}

	@Override
	public void setHeadline(String aHeadline) {
		this.headline = aHeadline;
		if(this.fmmHeadlineNode != null) {
			getFmmHeadlineNode().setHeadline(aHeadline);
		}
	}

	@Override
	public String getSerialized() {
		return getJsonObject().toString();
	}

	@Override
	public JSONObject getJsonObject() {
		JSONObject theJsonObject = new JSONObject();
		try {
			theJsonObject.put(IdNodeMetaData.column_ID, this.nodeIdString);
			theJsonObject.put(HeadlineNodeMetaData.column_HEADLINE, this.headline);
			theJsonObject.put(json_key__DECKANGL_NOUN_STATE_COLOR, getDecKanGlNounStateColor().getName());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return theJsonObject;
	}

	@Override
	public boolean validateSerializationFormatVersion(String aString) {
		// TODO Auto-generated method stub
		return false;
	}

	public static ArrayList<String> getSerializationArrayList(ArrayList<? extends FmmHeadlineNode> aHeadlineNodeShallowList) {
		ArrayList<String> theStringList = new ArrayList<String>();
		for(FmmHeadlineNode theHeadlineNodeShallow : aHeadlineNodeShallowList) {
			theStringList.add(theHeadlineNodeShallow.getFmmHeadlineNodeShallow().getSerialized());
		}
		return theStringList;
	}
	
	public static ArrayList<FmmHeadlineNodeShallow> getHeadlineNodeShallowListFromSerializationList(ArrayList<String> aSerializedHeadlineNodeList) {
		ArrayList<FmmHeadlineNodeShallow> theHeadlineNodeShallowList = new ArrayList<FmmHeadlineNodeShallow>();
		for(String theSerializedNode : aSerializedHeadlineNodeList) {
			try {
				theHeadlineNodeShallowList.add(new FmmHeadlineNodeShallow(new JSONObject(theSerializedNode)));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return theHeadlineNodeShallowList;
	}

	@Override
	public FmmHeadlineNodeShallow getFmmHeadlineNodeShallow() {
		return this;
	}

	@Override
	public NodeFragAuditBlock getNodeFragAuditBlock() {
		return getFmmHeadlineNode().getNodeFragAuditBlock();
	}

	@Override
	public void setNodeFragAuditBlock(NodeFragAuditBlock anAuditBlock) {
		getFmmHeadlineNode().setNodeFragAuditBlock(anAuditBlock);
	}

	@Override
	public String getCreatedByNodeIdString() {
		return getFmmHeadlineNode().getCreatedByNodeIdString();
	}

	@Override
	public void setCreatedBy(String aNodeIdString) {
		getFmmHeadlineNode().setCreatedBy(aNodeIdString);
	}
	
	@Override
	public CommunityMember getCreatedByCommunityMember() {
		return getFmmHeadlineNode().getCreatedByCommunityMember();
	}

	@Override
	public void setCreatedBy(CommunityMember aCommunityMember) {
		getFmmHeadlineNode().setCreatedBy(aCommunityMember);
	}

	@Override
	public Date getCreatedTimestamp() {
		return getFmmHeadlineNode().getCreatedTimestamp();
	}

	@Override
	public void setCreatedTimestamp(Date aTimestamp) {
		getFmmHeadlineNode().setCreatedTimestamp(aTimestamp);
	}

	@Override
	public String getLastUpdatedByNodeIdString() {
		return getFmmHeadlineNode().getLastUpdatedByNodeIdString();
	}

	@Override
	public void setLastUpdatedBy(String aNodeIdString) {
		getFmmHeadlineNode().setLastUpdatedBy(aNodeIdString);
	}

	@Override
	public CommunityMember getLastUpdatedByCommunityMember() {
		return getFmmHeadlineNode().getLastUpdatedByCommunityMember();
	}

	@Override
	public void setLastUpdatedBy(CommunityMember aCommunityMember) {
		getFmmHeadlineNode().setLastUpdatedBy(aCommunityMember);
	}

	@Override
	public Date getLastUpdatedTimestamp() {
		return getFmmHeadlineNode().getLastUpdatedTimestamp();
	}

	public void setLastUpdatedTimestamp(Date aTimestamp) {
		getFmmHeadlineNode().setRowTimestamp(aTimestamp);
	}

	@Override
	public CommunityMember getLockedByCommunityMember() {
		return getFmmHeadlineNode().getLockedByCommunityMember();
	}

	@Override
	public NodeId getLockedByNodeId() {
		return getFmmHeadlineNode().getLockedByNodeId();
	}

	@Override
	public String getLockedByNodeIdString() {
		return getFmmHeadlineNode().getLockedByNodeIdString();
	}

	@Override
	public void setLockedBy(CommunityMember aCommunityMember) {
		getFmmHeadlineNode().setLockedBy(aCommunityMember);
	}

	@Override
	public void setLockedBy(NodeId aNodeId) {
		getFmmHeadlineNode().setLockedBy(aNodeId);
	}

	@Override
	public void setLockedBy(String aNodeIdString) {
		getFmmHeadlineNode().setLockedBy(aNodeIdString);
	}

	@Override
	public Date getLockedTimestamp() {
		return getFmmHeadlineNode().getLockedTimestamp();
	}

	@Override
	public void setLockedTimestamp(Date aTimestamp) {
		getFmmHeadlineNode().setLockedTimestamp(aTimestamp);
	}

	@Override
	public CommunityMember getUnlockedByCommunityMember() {
		return getFmmHeadlineNode().getUnlockedByCommunityMember();
	}

	@Override
	public NodeId getUnlockedByNodeId() {
		return getFmmHeadlineNode().getUnlockedByNodeId();
	}

	@Override
	public String getUnlockedByNodeIdString() {
		return getFmmHeadlineNode().getUnlockedByNodeIdString();
	}

	@Override
	public void setUnlockedBy(CommunityMember aCommunityMember) {
		getFmmHeadlineNode().setUnlockedBy(aCommunityMember);
	}

	@Override
	public void setUnlockedBy(NodeId aNodeId) {
		getFmmHeadlineNode().setUnlockedBy(aNodeId);
	}

	@Override
	public void setUnlockedBy(String aNodeIdString) {
		getFmmHeadlineNode().setUnlockedBy(aNodeIdString);
	}

	@Override
	public Date getUnlockedTimestamp() {
		return getFmmHeadlineNode().getUnlockedTimestamp();
	}

	@Override
	public void setUnlockedTimestamp(Date aTimestamp) {
		getFmmHeadlineNode().setUnlockedTimestamp(aTimestamp);
	}

	@Override
	public void lock() {
		getFmmHeadlineNode().lock();
	}

	@Override
	public void unlock() {
		getFmmHeadlineNode().unlock();
	}

	@Override
	public boolean isLocked() {
		return getFmmHeadlineNode().isLocked();
	}

	@Override
	public void setIsLocked(boolean bIsLocked) {
		getFmmHeadlineNode().setIsLocked(bIsLocked);
	}

	@Override
	public FmmLockStatus getLockStatus() {
		return getFmmHeadlineNode().getLockStatus();
	}

	@Override
	public Bitmap getAnnotatedDecKanGlBitmap() {
		return getFmmHeadlineNode().getAnnotatedDecKanGlBitmap();
	}

	@Override
	public boolean canDelete() {
		return ! isLocked();
	}

	@Override
	public BitmapDrawable getDecKanGlElementNounStateBitmapDrawable() {
		if(this.workStateDrawable == null) {
			this.workStateDrawable = getFmmHeadlineNode().getDecKanGlElementNounStateBitmapDrawable(this.workStateDrawableSize);
		}
		return this.workStateDrawable;
	}

	@Override
	public BitmapDrawable getDecKanGlElementNounStateBitmapDrawable(DecKanGlNounStateDrawableSize aDrawableSize) {
		if(this.workStateDrawable == null || this.workStateDrawableSize != aDrawableSize) {
			this.workStateDrawable = getFmmHeadlineNode().getDecKanGlElementNounStateBitmapDrawable(this.workStateDrawableSize);
		}
		return this.workStateDrawable;
	}

	@Override
	public BitmapDrawable getUpdatedDecKanGlElementNounStateBitmapDrawable() {
		this.workStateDrawableSize = DecKanGlNounStateDrawableSize.SMALL;
		this.workStateDrawable = getFmmHeadlineNode().getUpdatedDecKanGlElementNounStateBitmapDrawable(this.workStateDrawableSize);
		return this.workStateDrawable;
	}

	@Override
	public BitmapDrawable getUpdatedDecKanGlElementNounStateBitmapDrawable(DecKanGlNounStateDrawableSize aDrawableSize) {
		this.workStateDrawableSize = aDrawableSize;
		this.workStateDrawable = getFmmHeadlineNode().getUpdatedDecKanGlElementNounStateBitmapDrawable(this.workStateDrawableSize);
		return this.workStateDrawable;
	}

	@Override
	public void updateDecKanGlElementNounState() {
		this.workStateDrawable = getFmmHeadlineNode().getUpdatedDecKanGlElementNounStateBitmapDrawable(this.workStateDrawableSize);
	}

	@Override
	public DecKanGlGlyph getDecKanGlGlyph() {
		return getFmmHeadlineNode().getDecKanGlGlyph();
	}

	@Override
	public FmsDecoratorGovernance getDecoratorGovernance() {
		return getFmmHeadlineNode().getDecoratorGovernance();
	}

	@Override
	public FmsDecoratorFacilitationIssue getDecoratorFacilitationIssue() {
		return getFmmHeadlineNode().getDecoratorFacilitationIssue();
	}

	@Override
	public FmsDecoratorFacilitator getDecoratorFacilitator() {
		return getFmmHeadlineNode().getDecoratorFacilitator();
	}

	@Override
	public FmsDecoratorStrategicCommitment getDecoratorStrategicCommitment() {
		return getFmmHeadlineNode().getDecoratorStrategicCommitment();
	}

	@Override
	public FmsDecoratorFlywheelCommitment getDecoratorFlywheelCommitment() {
		return getFmmHeadlineNode().getDecoratorFlywheelCommitment();
	}

	@Override
	public FmsDecoratorParentFractals getDecoratorParentFractals() {
		return getFmmHeadlineNode().getDecoratorParentFractals();
	}

	@Override
	public FmsDecoratorChildFractals getDecoratorChildFractals() {
		return getFmmHeadlineNode().getDecoratorChildFractals();
	}

	@Override
	public FmsDecoratorWorkTaskBudget getDecoratorWorkTaskBudget() {
		return getFmmHeadlineNode().getDecoratorWorkTaskBudget();
	}

	@Override
	public FmsDecoratorWorkTeam getDecoratorWorkTeam() {
		return getFmmHeadlineNode().getDecoratorWorkTeam();
	}

	@Override
	public FmsDecoratorStory getDecoratorStory() {
		return getFmmHeadlineNode().getDecoratorStory();
	}

	@Override
	public FmsDecoratorSequence getDecoratorSequence() {
		return getFmmHeadlineNode().getDecoratorSequence();
	}

	@Override
	public FmsDecoratorCompletion getDecoratorCompletion() {
		return getFmmHeadlineNode().getDecoratorCompletion();
	}

	@Override
	public HashMap<DecKanGlDecoratorCanvasLocation, DecKanGlDecorator> getUpdatedDecKanGlDecoratorMap() {
		return getFmmHeadlineNode().getUpdatedDecKanGlDecoratorMap();
	}

	@Override
	public void replaceWithNew(NodeId aNodeId, String aHeadline) { /* N/A */ }
	
	@Override
	public Date updateRowTimestamp() {
		return getFmmHeadlineNode().updateRowTimestamp();
	}

	@Override
	public NodeFragTribKnQuality getNodeFragTribKnQuality() {
		return getFmmHeadlineNode().getNodeFragTribKnQuality();
	}

	@Override
	public String getSerializedBaseline() {
		return null;
	}

	@Override
	public String updateSerializedBaseline() {
		return getFmmHeadlineNode().updateSerializedBaseline();
	}

	@Override
	public ArrayList<HistoryEventData> getHistoryEventDataList() {
		return getFmmHeadlineNode().getHistoryEventDataList();
	}

	@Override
	public boolean canDelete(FmmHeadlineNode aContextHeadlineNode) {
		return getFmmHeadlineNode().canDelete(aContextHeadlineNode);
	}

	@Override
	public boolean canMove() {
		return getFmmHeadlineNode().canMove();
	}

	@Override
	public boolean canMove(FmmHeadlineNode aContextHeadlineNode) {
		return getFmmHeadlineNode().canMove(aContextHeadlineNode);
	}

	@Override
	public boolean canOrphan() {
		return getFmmHeadlineNode().canOrphan();
	}

	@Override
	public boolean canOrphan(FmmHeadlineNode aContextHeadlineNode) {
		return getFmmHeadlineNode().canOrphan(aContextHeadlineNode);
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
		// TODO 
		return null;
	}

    @Override
    public ArrayList<? extends FmmHeadlineNode> getPeerHeadlineNodeShallowList(FmmHeadlineNode aParentHeadlineNode) {
        return getFmmHeadlineNode().getPeerHeadlineNodeShallowList(aParentHeadlineNode);
    }

    @Override
    public int getChildNodeCount(GcgPerspective aGcgPerspective) {
        return getFmmHeadlineNode().getChildNodeCount(aGcgPerspective);
    }

    @Override
    public ArrayList<? extends FmmHeadlineNode> getChildList(FmmNodeDefinition aChildNodeDefinition) {
        return getFmmHeadlineNode().getChildList(aChildNodeDefinition);
    }

}
