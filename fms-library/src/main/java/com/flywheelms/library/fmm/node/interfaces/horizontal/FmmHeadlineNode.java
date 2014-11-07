/* @(#)FmmHeadlineNode.java
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

package com.flywheelms.library.fmm.node.interfaces.horizontal;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import com.flywheelms.gcongui.deckangl.enumerator.DecKanGlDecoratorCanvasLocation;
import com.flywheelms.gcongui.deckangl.enumerator.DecKanGlNounStateColor;
import com.flywheelms.gcongui.deckangl.enumerator.DecKanGlNounStateDrawableSize;
import com.flywheelms.gcongui.deckangl.glyph.DecKanGlGlyph;
import com.flywheelms.gcongui.deckangl.interfaces.DecKanGlDecorator;
import com.flywheelms.gcongui.gcg.interfaces.GcgPerspective;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorCadenceCommitment;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorChildFractals;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorCompletion;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorFacilitationIssue;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorFacilitator;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorGovernance;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorParentFractals;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorSequence;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorStory;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorStrategicCommitment;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorWorkTaskBudget;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorWorkTeam;
import com.flywheelms.library.fmm.interfaces.FmmHistory;
import com.flywheelms.library.fmm.node.FmmHeadlineNodeShallow;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragFseDocument;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragTribKnQuality;
import com.flywheelms.library.fse.model.FseDocument;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public interface FmmHeadlineNode extends FmmNode, FmmNodeAudit, FmmHistory {
	
	FmmHeadlineNodeShallow getFmmHeadlineNodeShallow();

    String getSerializedNodeForTrash();
	
	String getHeadline();
	
	void setHeadline(String aHeadline);
	
	FseDocument getFseDocument();
	
	FseDocument getFseDocumentForPublication();

	boolean canDelete();

	boolean canDelete(FmmHeadlineNode aContextHeadlineNode);

	boolean canMove();

	boolean canMove(FmmHeadlineNode aContextHeadlineNode);

	boolean canOrphan();

	boolean canOrphan(FmmHeadlineNode aContextHeadlineNode);

	BitmapDrawable getDecKanGlElementNounStateBitmapDrawable();  // default of SMALL
	
	BitmapDrawable getDecKanGlElementNounStateBitmapDrawable(DecKanGlNounStateDrawableSize aDrawableSize);

	BitmapDrawable getUpdatedDecKanGlElementNounStateBitmapDrawable();  // default of SMALL

	BitmapDrawable getUpdatedDecKanGlElementNounStateBitmapDrawable(DecKanGlNounStateDrawableSize aDrawableSize);
	
	Bitmap getAnnotatedDecKanGlBitmap();
	
	void updateDecKanGlElementNounState();
	
	DecKanGlGlyph getDecKanGlGlyph();

	DecKanGlNounStateColor getDecKanGlNounStateColor();
	
	HashMap<DecKanGlDecoratorCanvasLocation, DecKanGlDecorator> getUpdatedDecKanGlDecoratorMap();
	
	FmsDecoratorGovernance getDecoratorGovernance();
	
	FmsDecoratorFacilitationIssue getDecoratorFacilitationIssue();

	FmsDecoratorFacilitator getDecoratorFacilitator();

	FmsDecoratorStrategicCommitment getDecoratorStrategicCommitment();

	FmsDecoratorCadenceCommitment getDecoratorFlywheelCommitment();
	
	FmsDecoratorParentFractals getDecoratorParentFractals();

	FmsDecoratorChildFractals getDecoratorChildFractals();

	FmsDecoratorWorkTaskBudget getDecoratorWorkTaskBudget();

	FmsDecoratorWorkTeam getDecoratorWorkTeam();
	
	FmsDecoratorStory getDecoratorStory();
	
	FmsDecoratorSequence getDecoratorSequence();
	
	FmsDecoratorCompletion getDecoratorCompletion();
	
	void replaceWithNew(NodeId aNodeId, String aHeadline);

    Date updateRowTimestamp();

    void updateNodeFragAuditBlock();

    void setNodeFragFseDocument(NodeFragFseDocument aNodeFragFseDocument);

    NodeFragFseDocument getNodeFragFseDocument();

    NodeFragFseDocument getUpdatedNodeFragFseDocument();
	
	NodeFragTribKnQuality getNodeFragTribKnQuality();

    void setNodeFragTribKnQuality(NodeFragTribKnQuality aNodeFragTribKnQuality);

    NodeFragTribKnQuality getUpdatedNodeFragTribKnQuality();

	String getSponsorName();

	String getCustomerName();

	String getFacilitatorName();

	String getAdministratorName();
	
	String getTargetDateString();

    ArrayList<? extends FmmHeadlineNode> getPeerHeadlineNodeShallowList(FmmHeadlineNode aParentHeadlineNode);

    int getChildNodeCount(GcgPerspective aGcgPerspective);

    ArrayList<? extends FmmHeadlineNode> getChildList(FmmNodeDefinition aChildNodeDefinition);

    ArrayList<FmmHeadlineNodeShallow> getChildListShallow(FmmNodeDefinition aChildNodeDefinition);
}
