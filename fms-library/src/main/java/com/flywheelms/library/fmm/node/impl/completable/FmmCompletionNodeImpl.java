/* @(#)FmmCompletionNodeImpl.java
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

package com.flywheelms.library.fmm.node.impl.completable;

import com.flywheelms.gcongui.deckangl.enumerator.DecKanGlNounStateColor;
import com.flywheelms.gcongui.deckangl.glyph.DecKanGlDictionary;
import com.flywheelms.gcongui.deckangl.glyph.DecKanGlElementNounState;
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.context.FmmPerspective;
import com.flywheelms.library.fmm.node.FmmNodeInfo;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.enumerator.CompletableWorkStatus;
import com.flywheelms.library.fmm.node.impl.enumerator.CompletionCommitmentType;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.governable.CommunityMember;
import com.flywheelms.library.fmm.node.impl.governable.FmmGovernableNodeImpl;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragCompletion;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragWorkTaskBudget;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmCompletionNode;
import com.flywheelms.library.fmm.transaction.FmmNodeGlyphType;

import org.json.JSONObject;

import java.util.Date;

public abstract class FmmCompletionNodeImpl extends FmmGovernableNodeImpl
		implements FmmCompletionNode, FmmNodeInfo {
	
	private static final long serialVersionUID = 7670030679294983810L;
	private int sequence;
	private NodeFragWorkTaskBudget nodeFragWorkTaskBudget;
	private NodeFragCompletion nodeFragCompletion;

	public FmmCompletionNodeImpl(NodeId aNodeId) {
		super(aNodeId);
	}

    public FmmCompletionNodeImpl(NodeId aNodeId, String aHeadline) {
        super(aNodeId, aHeadline);
    }

	public FmmCompletionNodeImpl(Class<? extends FmmCompletionNodeImpl> aCompletableClass, String anExistingNodeIdString) {
		super(NodeId.hydrate(aCompletableClass, anExistingNodeIdString));
	}
	
	public FmmCompletionNodeImpl(Class<? extends FmmCompletionNodeImpl> aClass, JSONObject aJsonObject) {
		super(aClass, aJsonObject);
	}

	@Override
	public boolean getAutocompletable() {
		return getNodeFragCompletion().getAutocompletable();
	}

	@Override
	public void setAutoCompletable(short shortAutoCompletable) {
		getNodeFragCompletion().setAutoCompletable(shortAutoCompletable < 1 ? false : true);
	}

	@Override
	public void setAutoCompletable(boolean aBoolean) {
		getNodeFragCompletion().setAutoCompletable(aBoolean);
	}

	@Override
	public CompletionCommitmentType getCompletionCommitmentType() {  // wrapper
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSequence() {
		return this.sequence;
	}

	@Override
	public void setSequence(int aSequence) {
		this.sequence = aSequence;
	}

	@Override
	public int getSequence(FmmNodeDefinition anFmmNodeDefinition) {
		return this.sequence;
	}

	@Override
	public void setSequence(int aSequence, FmmNodeDefinition anFmmNodeDefinition) {
		this.sequence = aSequence;
	}

	@Override
	public String getCompletableWorkStatusCode() {
		return getNodeFragCompletion().getCompletableWorkStatusCode();
	}

	@Override
	public void setCompletableWorkStatusCode(String aWorkStatusCode) {
		getNodeFragCompletion().setCompletableWorkStatusCode(aWorkStatusCode);
	}

	@Override
	public CompletableWorkStatus getCompletableWorkStatus() {
		return getNodeFragCompletion().getCompletableWorkStatus();
	}
	
	@Override
	public void setCompletableWorkStatus(CompletableWorkStatus aWorkStatus) {
		getNodeFragCompletion().setCompletableWorkStatus(aWorkStatus);
	}

	@Override
	public boolean isGreen() {
		return getCompletableWorkStatus().isGreen();
	}

	@Override
	public boolean isPink() {
		return getCompletableWorkStatus().isPink();
	}

	@Override
	public boolean isOrange() {
		return getCompletableWorkStatus().isOrange();
	}

	@Override
	public boolean isYellow() {
		return getCompletableWorkStatus().isYellow();
	}

	@Override
	public boolean isGray() {
		return getCompletableWorkStatus().isGray();
	}

	@Override
	public DecKanGlNounStateColor getDecKanGlNounStateColor() {
		if(isGray()) {
			return DecKanGlNounStateColor.GRAY;
		} else if(isYellow()) {
				return DecKanGlNounStateColor.YELLOW;
		} else if(isOrange()) {
				return DecKanGlNounStateColor.ORANGE;
		} else if(isPink()) {
				return DecKanGlNounStateColor.PINK;
		} else if(isGreen()) {
				return DecKanGlNounStateColor.GREEN;
		}
		return null;
	}
	
	@Override
	public boolean isComplete() {
		return getCompletableWorkStatus() == CompletableWorkStatus.COMPLETE;
	}

	@Override
	public DecKanGlElementNounState getDecKanGlElementNounState() {
		return DecKanGlDictionary.getInstance().getNounStateForColor(getCompletableWorkStatus().getWorkStateColorName());
	}

	@Override
	public JSONObject getJsonObject() {
		return super.getJsonObject();
	}

	@Override
	public CommunityMember getYellowByCommunityMember() {
		return getNodeFragCompletion().getYellowByCommunityMember();
	}

	@Override
	public void setYellowBy(CommunityMember aCommunityMember) {
		getNodeFragCompletion().setYellowBy(aCommunityMember);
	}

	@Override
	public String getYellowByNodeIdString() {
		return getNodeFragCompletion().getYellowByNodeIdString();
	}

	@Override
	public void setYellowBy(String aNodeIdString) {
		getNodeFragCompletion().setYellowBy(aNodeIdString);
	}

	@Override
	public Date getYellowTimestamp() {
		return getNodeFragCompletion().getYellowTimestamp();
	}

	@Override
	public void setYellowTimestamp(Date aTimestamp) {
		getNodeFragCompletion().setYellowTimestamp(aTimestamp);
	}

	@Override
	public CommunityMember getOrangeByCommunityMember() {
		return getNodeFragCompletion().getOrangeByCommunityMember();
	}

	@Override
	public void setOrangeBy(CommunityMember aCommunityMember) {
		getNodeFragCompletion().setOrangeBy(aCommunityMember);
	}

	@Override
	public String getOrangeByNodeIdString() {
		return getNodeFragCompletion().getOrangeByNodeIdString();
	}

	@Override
	public void setOrangeBy(String aNodeIdString) {
		getNodeFragCompletion().setOrangeBy(aNodeIdString);
	}

	@Override
	public Date getOrangeTimestamp() {
		return getNodeFragCompletion().getOrangeTimestamp();
	}

	@Override
	public void setOrangeTimestamp(Date aTimestamp) {
		getNodeFragCompletion().setOrangeTimestamp(aTimestamp);
	}

	@Override
	public CommunityMember getPinkByCommunityMember() {
		return getNodeFragCompletion().getPinkByCommunityMember();
	}

	@Override
	public void setPinkBy(CommunityMember aCommunityMember) {
		getNodeFragCompletion().setPinkBy(aCommunityMember);
	}

	@Override
	public String getPinkByNodeIdString() {
		return getNodeFragCompletion().getPinkByNodeIdString();
	}

	@Override
	public void setPinkBy(String aNodeIdString) {
		getNodeFragCompletion().setPinkBy(aNodeIdString);
	}

	@Override
	public Date getPinkTimestamp() {
		return getNodeFragCompletion().getPinkTimestamp();
	}

	@Override
	public void setPinkTimestamp(Date aTimestamp) {
		getNodeFragCompletion().setPinkTimestamp(aTimestamp);
	}

	@Override
	public CommunityMember getGreenByCommunityMember() {
		return getNodeFragCompletion().getGreenByCommunityMember();
	}

	@Override
	public void setGreenBy(CommunityMember aCommunityMember) {
		getNodeFragCompletion().setGreenBy(aCommunityMember);
	}

	@Override
	public String getGreenByNodeIdString() {
		return getNodeFragCompletion().getGreenByNodeIdString();
	}

	@Override
	public void setGreenBy(String aNodeIdString) {
		getNodeFragCompletion().setGreenBy(aNodeIdString);
	}

	@Override
	public Date getGreenTimestamp() {
		return getNodeFragCompletion().getGreenTimestamp();
	}

	@Override
	public void setGreenTimestamp(Date aTimestamp) {
		getNodeFragCompletion().setGreenTimestamp(aTimestamp);
	}

	@Override
	public CommunityMember getCompletionConfirmedByCommunityMember() {
		return getNodeFragCompletion().getCompletionConfirmedByCommunityMember();
	}

	@Override
	public void setCompletionConfirmedBy(CommunityMember aCommunityMember) {
		getNodeFragCompletion().setCompletionConfirmedBy(aCommunityMember);
	}

	@Override
	public String getCompletionConfirmedByNodeIdString() {
		return getNodeFragCompletion().getCompletionConfirmedByNodeIdString();
	}

	@Override
	public void setCompletionConfirmedBy(String aNodeIdString) {
		getNodeFragCompletion().setCompletionConfirmedBy(aNodeIdString);
	}

	@Override
	public Date getCompletionConfirmedTimestamp() {
		return getNodeFragCompletion().getCompletionConfirmedTimestamp();
	}

	@Override
	public void setCompletionConfirmedTimestamp(Date aTimestamp) {
		getNodeFragCompletion().setCompletionConfirmedTimestamp(aTimestamp);
	}
	
	@Override
	public NodeFragWorkTaskBudget getNodeFragWorkTaskBudget() {
		if(this.nodeFragWorkTaskBudget == null) {
			this.nodeFragWorkTaskBudget = FmmDatabaseMediator.getActiveMediator().getNodeFragWorkTaskBudgetForParent(getNodeIdString());
		}
		return this.nodeFragWorkTaskBudget;
	}
	
	@Override
	public void setNodeFragWorkTaskBudget(NodeFragWorkTaskBudget aNodeFragWorkTaskBudget) {
		this.nodeFragWorkTaskBudget = aNodeFragWorkTaskBudget;
	}
	
	@Override
	public NodeFragCompletion getNodeFragCompletion() {
		if(this.nodeFragCompletion == null) {
			this.nodeFragCompletion = FmmDatabaseMediator.getActiveMediator().getNodeFragCompletionForParent(getNodeIdString());
		}
		return this.nodeFragCompletion;
	}
	
	@Override
	public void setNodeFragCompletion(NodeFragCompletion aNodeFragCompletion) {
		this.nodeFragCompletion = aNodeFragCompletion;
	}
	
	@Override
	public int getDataDrawableResourceId() {
		return getFmmNodeDefinition().getDataDrawableResourceId(getCompletableWorkStatus());
	}

    protected void initializeNodeCompletionSummaryMap(FmmPerspective anFmmPerspective, FmmNodeDefinition aChildNodeDefinition) {
        NodeCompletionSummary theNodeCompletionSummary = new NodeCompletionSummary();
        theNodeCompletionSummary.setSummaryDrawableResourceId(
                aChildNodeDefinition.getUndecoratedGlyphResourceId(FmmNodeGlyphType.GREEN) );
        updateNodeCompletionSummary(anFmmPerspective, theNodeCompletionSummary);
        this.nodeCompletionSummaryMap.put(anFmmPerspective, theNodeCompletionSummary);
    }

}
