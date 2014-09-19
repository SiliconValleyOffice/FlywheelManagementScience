/* @(#)FsePerspectiveFlipper.java
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

package com.flywheelms.library.fse.perspective_flipper;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.flywheelms.library.R;
import com.flywheelms.library.fca.FlywheelCommunityAuthentication;
import com.flywheelms.library.fdk.widget.FdkLeftKeypad;
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.enumerator.FmmLockStatus;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.governable.CommunityMember;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragAuditBlock;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmNodeAudit;
import com.flywheelms.library.fms.activity.FmmNodeEditorActivity;
import com.flywheelms.library.fms.perspective_flipper.perspective.FmsPerspectiveFlipper;
import com.flywheelms.library.fse.FseDocumentSectionType;
import com.flywheelms.library.fse.enumerator.FseContentModificationState;
import com.flywheelms.library.fse.model.FseCollaboratorSummary;
import com.flywheelms.library.fse.model.FseDocument;
import com.flywheelms.library.fse.model.FseDocumentSectionParagraphAudit;
import com.flywheelms.library.fse.model.FseDocumentTransaction;
import com.flywheelms.library.fse.model.FseDocumentTransactionType;
import com.flywheelms.library.fse.perspective_flipper.perspective.FseDocumentSectionCommunityPerspective;
import com.flywheelms.library.fse.perspective_flipper.perspective.FseDocumentSectionHistoryPerspective;
import com.flywheelms.library.fse.perspective_flipper.perspective.FseDocumentSectionNotesPerspective;
import com.flywheelms.library.fse.perspective_flipper.perspective.FseDocumentSectionParagraphEditorPerspective;
import com.flywheelms.library.fse.perspective_flipper.perspective.FseDocumentSectionPerspective;
import com.flywheelms.library.fse.perspective_flipper.perspective.FseDocumentSectionStoryPerspective;
import com.flywheelms.library.fse.perspective_flipper.perspective.FseDocumentSectionTribKnPerspective;
import com.flywheelms.library.gcg.helper.GcgHelper;
import com.flywheelms.library.gcg.interfaces.GcgSerialization;
import com.flywheelms.library.gcg.menu.GcgSpinnableMenu;
import com.flywheelms.library.gcg.widget.date.GcgDateHelper;

import org.json.JSONObject;

import java.util.Date;

public class FsePerspectiveFlipper extends FmsPerspectiveFlipper implements GcgSerialization, FmmNodeAudit {

	protected String nodeFragNodeIdString;
	protected FseDocument displayedFseDocument;
	protected FseDocumentSectionType activeDocumentSectionType;
	private FrameLayout documentViewLayout;
	private FseDocument baselineDocument;
	protected String documentId;
	private boolean forExport = false;
	protected int documentMarginLeft;
	private FseDocumentSectionType initialDocumentSectionType;
	private FseDocumentSectionTribKnPerspective tribKnSectionView;
	protected FseDocumentSectionStoryPerspective storySectionPerspective;
	protected FseDocumentSectionNotesPerspective notesSectionPerspective;
	private FseDocumentSectionHistoryPerspective historySectionPerspective;
	private FseDocumentSectionCommunityPerspective collaboratorsSectionPerspective;
//	private boolean documentDataHasBeenModified = false;
	private static final int menu_position__STORY = 0;
	private static final int menu_position__NOTES = 1;
	private static final int menu_position__HISTORY = 2;
	private static final int menu_position__COLLABORATORS = 3;

	public FsePerspectiveFlipper(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
	}

	@Override
	public void initializeViewFlipperViews() {
		this.storySectionPerspective = (FseDocumentSectionStoryPerspective) getGcgActivity().findViewById(R.id.fse_perspective__story);
		this.storySectionPerspective.initialize(getGcgActivity(), this, this.spinnnableMenuIndex, FsePerspectiveFlipper.menu_position__STORY);
		this.notesSectionPerspective = (FseDocumentSectionNotesPerspective) getGcgActivity().findViewById(R.id.fse_perspective__notes);
		this.notesSectionPerspective.initialize(getGcgActivity(), this, this.spinnnableMenuIndex, FsePerspectiveFlipper.menu_position__NOTES);
		this.historySectionPerspective = (FseDocumentSectionHistoryPerspective) getGcgActivity().findViewById(R.id.fse_perspective__history);
		this.historySectionPerspective.initialize(getGcgActivity(), this, this.spinnnableMenuIndex, FsePerspectiveFlipper.menu_position__HISTORY);
		this.collaboratorsSectionPerspective = (FseDocumentSectionCommunityPerspective) getGcgActivity().findViewById(R.id.fse_perspective__collaborators);
		this.collaboratorsSectionPerspective.initialize(getGcgActivity(), this, this.spinnnableMenuIndex, FsePerspectiveFlipper.menu_position__COLLABORATORS);
		super.initializeViewFlipperViews();
	}
	
	public void viewDocument(FseDocument anFseDocument) {
		viewDocument(anFseDocument, anFseDocument);
	}
	
	public void viewDocument(FseDocument anFseDocument, FseDocument aDocumentBaseline) {
		FseDocument theDocumentClone = anFseDocument.getClone();
		this.nodeFragNodeIdString = anFseDocument.getNodeFragNodeIdString();
		this.baselineDocument = aDocumentBaseline;
		this.documentId = theDocumentClone.getDocumentId();
		setParentNodeIdString(theDocumentClone.getParentNodeIdString());
		this.forExport = theDocumentClone.forExport();
		this.documentMarginLeft = theDocumentClone.getDocumentMarginLeft();
		this.initialDocumentSectionType = theDocumentClone.getInitialDocumentSectionType();
		if(this.forExport) {
			this.tribKnSectionView.viewDocumentSection(theDocumentClone.getDocumentSectionTribKn());
		}
		this.collaboratorsSectionPerspective.viewDocumentSection(theDocumentClone.getDocumentSectionCollaborators());
		this.historySectionPerspective.viewDocumentSection(theDocumentClone.getDocumentSectionHistory());
		this.storySectionPerspective.viewDocumentSection(theDocumentClone.getDocumentSectionStory());
		this.notesSectionPerspective.viewDocumentSection(theDocumentClone.getDocumentSectionNotes());
//		displayDocumentSection(FseDocumentSectionType.STORY);
		displayDocumentSection(this.initialDocumentSectionType);
	}
	
	public void displayDocumentSection(FseDocumentSectionType aDocumentSectionType) {
		setDisplayedChild(aDocumentSectionType.getViewFlipperIndex());
		setActiveDocumentSectionType(aDocumentSectionType);
	}

	@Override
	public void discardDataChanges() {
		GcgHelper.makeToast("Reverting document...");
		viewDocument(this.baselineDocument);
		setAsNewBaseline();
	}

	public void saveFseDocument() {
		FseContentModificationState theDocumentModificationState = getModificationState();
		FseDocumentTransactionType theDocumentTransactionType = null;
		if(theDocumentModificationState == FseContentModificationState.UNCHANGED) {
			return;
		}
		if(theDocumentModificationState == FseContentModificationState.NEW) {
			theDocumentTransactionType = FseDocumentTransactionType.CREATE;
		} else if(theDocumentModificationState == FseContentModificationState.MODIFIED) {
			theDocumentTransactionType = FseDocumentTransactionType.MODIFY;
		} else {
			theDocumentTransactionType = FseDocumentTransactionType.CHECKPOINT;
		}
		Date theTimestamp = GcgDateHelper.getCurrentDateTime();
		updateHistory(theTimestamp, theDocumentTransactionType, "");
		updateAuditBlock(theTimestamp, theDocumentTransactionType);
		updateCollaboratorSummary();
		FmmDatabaseMediator.getActiveMediator().updateFseDocument(generateFseDocument(), true);
		postPersistenceProcessing();
		setDisplayedFseDocument(generateFseDocument());
		setAsNewBaseline();
	}
	
	public FrameLayout getDocumentViewLayout() {
		return this.documentViewLayout;
	}
	
	public FseDocumentSectionType getCurrentPerspectiveDocumentSectionType() {
		return ((FseDocumentSectionPerspective) getCurrentView()).getSectionType();
	}
	
	public FseDocumentSectionPerspective getActiveDocumentSectionView() {
		return (FseDocumentSectionPerspective) getCurrentView();
	}

	public FseDocumentSectionTribKnPerspective getTribKnSectionView() {
		return this.tribKnSectionView;
	}

	public FseDocumentSectionCommunityPerspective getCollaboratorsSectionPerspective() {
		return this.collaboratorsSectionPerspective;
	}

	public FseDocumentSectionHistoryPerspective getHistorySectionPerspective() {
		return this.historySectionPerspective;
	}

	public FseDocumentSectionParagraphEditorPerspective getStorySectionPerspective() {
		return this.storySectionPerspective;
	}

	public FseDocumentSectionParagraphEditorPerspective getNotesSectionPerspective() {
		return this.notesSectionPerspective;
	}

	public FseDocument getBaselineDocument() {
		return this.baselineDocument;
	}
	
	public String getDocumentId() {
		return this.documentId;
	}
	
	public String getNodeFragNodeIdString() {
		return this.nodeFragNodeIdString;
	}
	
	public boolean documentIsModified() {
		if(this.storySectionPerspective.isDocumentSectionModified() || this.notesSectionPerspective.isDocumentSectionModified() ) {
			return true;
		}
		if(! generateFseDocument().equalsContent(this.baselineDocument)) {
			return true;
		}
		return false;
	}

	public int getDocumentMarginLeft() {
		return this.documentMarginLeft;
	}

	public FseDocumentSectionParagraphEditorPerspective getActiveParagraphEditorView() {
		switch (((FseDocumentSectionPerspective) getCurrentView()).getSectionType()) {
		case STORY:
			return getStorySectionPerspective();
		case NOTES:
			return getNotesSectionPerspective();
		default:
			return null;
		}
	}

	@Override
	public String getSerialized() {
		return getJsonObject().toString();
	}

	public String getSerializedBaseline() {
		return this.baselineDocument.getSerialized();
	}

	@Override
	public JSONObject getJsonObject() {
		return generateFseDocument().getJsonObject();
	}

	// TODO - unfinished work
//	@Override
//	public JSONObject getJsonObject() {
//		JSONObject theJsonObject = new JSONObject();
//		try {
//			theJsonObject.put(FseDocumentSerialization.key__SERIALIZATION_FORMAT_VERSION, FseDocumentSerialization.SERIALIZATION_FORMAT_VERSION);
//			theJsonObject.put(FseDocumentSerialization.key__DOCUMENT_ID, getDocumentId());
//			theJsonObject.put(FseDocumentSerialization.key__PARENT_ID, getParentNodeIdString());
//			theJsonObject.put(FseDocumentSerialization.key__DOCUMENT_MARGIN__LEFT, this.documentMarginLeft);
//			theJsonObject.put(FseDocumentSerialization.key__DOCUMENT_INITIAL_SECTION_TAB, this.initialDocumentSectionType.toString());
//			theJsonObject.put(FseDocumentSerialization.key__DOCUMENT_SECTION__COLLABORATORS, getCollaboratorsSectionView().getJsonObject());
//			theJsonObject.put(FseDocumentSerialization.key__DOCUMENT_SECTION__STORY, getStorySectionView().getJsonObject());
//			theJsonObject.put(FseDocumentSerialization.key__DOCUMENT_SECTION__NOTES, getNotesSectionView().getJsonObject());
//			theJsonObject.put(FseDocumentSerialization.key__DOCUMENT_SECTION__HISTORY, getHistorySectionView().getJsonObject());
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		return theJsonObject;
//	}

	/**
	 * done here instead of FseDocument, in order to keep the "model" decoupled from the view 
	 */
	public FseDocument generateFseDocument() {
		FseDocument theDocument = new FseDocument(this.documentId, getParentNodeIdString(), this.documentMarginLeft);
		theDocument.setNodeFragNodeIdString(this.nodeFragNodeIdString);
		theDocument.setForExport(this.forExport);
		theDocument.setInitialDocumentSectionType(((FseDocumentSectionPerspective) getCurrentView()).getSectionType());
		if(this.forExport) {
			theDocument.setDocumentSectionTribKn(this.tribKnSectionView);
		}
		theDocument.setDocumentSectionCollaborators(this.collaboratorsSectionPerspective);
		theDocument.setDocumentSectionHistory(this.historySectionPerspective);
		this.storySectionPerspective.getFseParagraphEditor().updateInitialFocus();
		theDocument.setDocumentSectionStory(this.storySectionPerspective);
		this.notesSectionPerspective.getFseParagraphEditor().updateInitialFocus();
		theDocument.setDocumentSectionNotes(this.notesSectionPerspective);
		return theDocument;
	}
	
	public void setDocumentBaseline(JSONObject aJsonObject) {
		this.baselineDocument = new FseDocument(aJsonObject.toString());
	}

	////  START Audit Block  Wrapper ////

	@Override
	public NodeFragAuditBlock getNodeFragAuditBlock() {
		return this.collaboratorsSectionPerspective.getDocumentSectionCollaborators().getAuditBlock();
	}

	@Override
	public void setNodeFragAuditBlock(NodeFragAuditBlock anAuditBlock) {
		this.collaboratorsSectionPerspective.getDocumentSectionCollaborators().setAuditBlock(anAuditBlock);
	}

	//Created  //

	@Override
	public String getCreatedByNodeIdString() {
		return getNodeFragAuditBlock().getCreatedByNodeIdString();
	}

	@Override
	public void setCreatedBy(String aNodeIdString) {
		getNodeFragAuditBlock().setCreatedBy(aNodeIdString);
	}

	@Override
	public CommunityMember getCreatedByCommunityMember() {
		return getNodeFragAuditBlock().getCreatedByCommunityMember();
	}

	@Override
	public void setCreatedBy(CommunityMember aCommunityMember) {
		getNodeFragAuditBlock().setCreatedBy(aCommunityMember);
	}

	@Override
	public Date getCreatedTimestamp() {
		return getNodeFragAuditBlock().getCreatedTimestamp();
	}

	@Override
	public void setCreatedTimestamp(Date aCreatedTimestamp) {
		getNodeFragAuditBlock().setCreatedTimestamp(aCreatedTimestamp);
	}

	//Updated  //

	@Override
	public String getLastUpdatedByNodeIdString() {
		return getNodeFragAuditBlock().getLastUpdatedByNodeIdString();
	}

	@Override
	public void setLastUpdatedBy(String aNodeIdString) {
		getNodeFragAuditBlock().setLastUpdatedBy(aNodeIdString);
	}

	@Override
	public CommunityMember getLastUpdatedByCommunityMember() {
		return getNodeFragAuditBlock().getLastUpdatedByCommunityMember();
	}

	@Override
	public void setLastUpdatedBy(CommunityMember aCommunityMember) {
		getNodeFragAuditBlock().setLastUpdatedBy(aCommunityMember);
	}

	@Override
	public Date getLastUpdatedTimestamp() {
		return getNodeFragAuditBlock().getRowTimestamp();
	}

	public void setLastUpdatedTimestamp(Date aLastUpdate) {
		getNodeFragAuditBlock().setRowTimestamp(aLastUpdate);
	}

	//Locked  //

	@Override
	public CommunityMember getLockedByCommunityMember() {
		return getNodeFragAuditBlock().getLockedByCommunityMember();
	}

	@Override
	public void setLockedBy(CommunityMember aCommunityMember) {
		getNodeFragAuditBlock().setLockedBy(aCommunityMember);
	}

	@Override
	public NodeId getLockedByNodeId() {
		return getNodeFragAuditBlock().getLockedByNodeId();
	}

	@Override
	public String getLockedByNodeIdString() {
		return getNodeFragAuditBlock().getLockedByNodeId().getNodeIdString();
	}

	@Override
	public void setLockedBy(NodeId aNodeId) {
		getNodeFragAuditBlock().setLockedBy(aNodeId);
	}

	@Override
	public void setLockedBy(String aNodeIdString) {
		getNodeFragAuditBlock().setLockedBy(aNodeIdString);
	}

	@Override
	public Date getLockedTimestamp() {
		return getNodeFragAuditBlock().getLockedTimestamp();
	}

	@Override
	public void setLockedTimestamp(Date aLockedTimestamp) {
		getNodeFragAuditBlock().setLockedTimestamp(aLockedTimestamp);
	}

	@Override
	public void lock() {
		getNodeFragAuditBlock().setLockedBy(FlywheelCommunityAuthentication.getInstance().getFcaUserCredentials().getCommunityMemberNodeIdString());
		getNodeFragAuditBlock().setLockedTimestamp(GcgDateHelper.getCurrentDateTime());
		getNodeFragAuditBlock().setIsLocked(true);
	}
	////  Unlock  ////

	@Override
	public CommunityMember getUnlockedByCommunityMember() {
		return getNodeFragAuditBlock().getUnlockedByCommunityMember();
	}

	@Override
	public void setUnlockedBy(CommunityMember aCommunityMember) {
		getNodeFragAuditBlock().setUnlockedBy(aCommunityMember);
	}

	@Override
	public NodeId getUnlockedByNodeId() {
		return getNodeFragAuditBlock().getUnlockedByNodeId();
	}

	@Override
	public String getUnlockedByNodeIdString() {
		return getNodeFragAuditBlock().getUnlockedByNodeId().getNodeIdString();
	}

	@Override
	public void setUnlockedBy(NodeId aNodeId) {
		getNodeFragAuditBlock().setUnlockedBy(aNodeId);
	}
	
	@Override
	public void setUnlockedBy(String aNodeIdString) {
		getNodeFragAuditBlock().setUnlockedBy(aNodeIdString);
	}

	@Override
	public Date getUnlockedTimestamp() {
		return getNodeFragAuditBlock().getUnlockedTimestamp();
	}
	
	@Override
	public void setUnlockedTimestamp(Date anUnlockedTimestamp) {
		getNodeFragAuditBlock().setUnlockedTimestamp(anUnlockedTimestamp);
	}

	@Override
	public void unlock() {
		// we want to know who unlocked it, and when
		getNodeFragAuditBlock().setUnlockedBy(FlywheelCommunityAuthentication.getInstance().getFcaUserCredentials().getCommunityMemberNodeIdString());
		getNodeFragAuditBlock().setUnlockedTimestamp(GcgDateHelper.getCurrentDateTime());
		getNodeFragAuditBlock().setIsLocked(false);
	}
	
	
	/////////////////

	@Override
	public void setIsLocked(boolean aBoolean) {
		getNodeFragAuditBlock().setIsLocked(aBoolean);
	}

	@Override
	public boolean isLocked() {
		return getNodeFragAuditBlock().isLocked();
	}
	
	@Override
	public FmmLockStatus getLockStatus() {
		return getNodeFragAuditBlock().getLockStatus();
	}

	////  END Audit Block Wrapper ////

	@Override
	public void onFlingUp() {
		return;
	}

	@Override
	public void onFlingDown() {
		return;
	}

	@Override
	public void onFlingRight() {
//		this.getActiveDocumentSectionView().onFlingRight();
		
	}

	@Override
	public void onFlingLeft() {
//		this.getActiveDocumentSectionView().onFlingLeft();
	}
	
	///////  end Fling  /////////

	public void postPersistenceProcessing() {
		this.historySectionPerspective.refresh();
		this.collaboratorsSectionPerspective.invalidate();
	}

	public void setAsNewBaseline() {
		this.baselineDocument = new FseDocument(getJsonObject().toString(), FseDocumentTransaction.getBogusArg());
		this.storySectionPerspective.setAsNewBaseline();
		this.notesSectionPerspective.setAsNewBaseline();
		// no persistent state to reset
	}

	public void updateHistory(Date aTimestamp, FseDocumentTransactionType aDocumentTransactionType, String aComment) {
		FseDocumentSectionParagraphAudit theStoryAudit = this.storySectionPerspective.getParagraphAudit();
		FseDocumentSectionParagraphAudit theNotesAudit = this.notesSectionPerspective.getParagraphAudit();
		getHistorySectionPerspective().updateHistory(aTimestamp, aDocumentTransactionType, aComment, theStoryAudit, theNotesAudit, generateFseDocument());
	}

	public void updateCollaboratorSummary() {
		this.collaboratorsSectionPerspective.getCollaboratorSummaryView().viewCollaboratorSummary(
				new FseCollaboratorSummary(getHistorySectionPerspective().getDocumentSectionHistory()));
		
	}

	public FseContentModificationState getModificationState() {
		return FseContentModificationState.MODIFIED;
//		if(getStorySectionView().getContentModificationState() == FseContentModificationState.NEW) {
//			return FseContentModificationState.NEW;
//		}
//		if(getStorySectionView().getModificationState() != FseContentModificationState.UNCHANGED || getNotesSectionView().getModificationState() != FseContentModificationState.UNCHANGED) {
//			return FseContentModificationState.MODIFIED;
//		}
//		/*
//		 *  TODO - someday will have to check other document sections, such as
//		 *  a change to the history compression parameret in History
//		 */
//		return FseContentModificationState.UNCHANGED;
	}

	public void updateAuditBlock(Date aTimestamp, FseDocumentTransactionType aDocumentTransactionType) {
		getCollaboratorsSectionPerspective().updateAuditBlock(aTimestamp, aDocumentTransactionType);
		
	}

	public FmmNodeEditorActivity getFmsNodeActivity() {
		return (FmmNodeEditorActivity) getGcgActivity();
	}

	public View getLeftMenuInclude() {
		// TODO Auto-generated method stub
		return null;
	}

	public GcgSpinnableMenu getLeftSpinnableMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	public LinearLayout getLeftFdkKeypad() {
		// TODO Auto-generated method stub
		return null;
	}

	public FdkLeftKeypad getFdkLeftKeypad() {
		return getFmsNodeActivity().getFdkKeyboard().getLeftKeypad();
	}

	public boolean isDataModified() {
		return documentIsModified();
	}
	
	public void onDocumentStateChange() {
		getGcgActivity().onDataStateChange();
	}

	@Override
	public boolean validateSerializationFormatVersion(String aString) {
		// TODO Auto-generated method stub
		return false;
	}

	public void leftMenuClickItem(Integer aMenuItem) {
		getFmsNodeActivity().leftMenuClickItem(aMenuItem);
	}

	public void revertAllModifiedData() {
		getFmsNodeActivity().requestRevertAllModifiedData();
	}

	public void newFractal() {
		getFmsNodeActivity().newFractal();
	}

	public void navigationSpinnerSelect() {
		getFmsNodeActivity().navigationSpinnerSelect();
	}

	public void navigateLeftButtonClick() {
		getFmsNodeActivity().navigateLeftButtonClick();
	}

	public void navigateRightButtonClick() {
		getFmsNodeActivity().navigateRightButtonClick();
	}

	public void navigateFirstButtonClick() {
		getFmsNodeActivity().navigateFirstButtonClick();
	}

	public void navigateLastButtonClick() {
		getFmsNodeActivity().navigateLastButtonClick();
	}

	public void revertDocumentToTransaction(@SuppressWarnings("unused") int theTransactionListIndex) {
		// not done in the View; revert FseDocument and then redisplay in View
		return;
	}

	@Override
	public int getFrameMenuSpacerCurtainBackgroundResourceId() {
		return R.color.gcg__sands_of_time;
	}

	public FseDocument getDisplayedFseDocument() {
		return this.displayedFseDocument;
	}
	
	public void setDisplayedFseDocument(FseDocument anFseDocument) {
		this.displayedFseDocument = anFseDocument;
		this.displayedFseDocument.resetModificationState();
	}
	
	public void setDisplayedFseDocumentForParentNode(String aNodeIdString) {
		setDisplayedFseDocument(FmmDatabaseMediator.getActiveMediator().getFseDocumentForParentOrCreate(aNodeIdString));
	}

	public void revertFseDocumentToTransaction(boolean bRevertStory, boolean bRevertNotes) {
		if(!bRevertStory && !bRevertNotes) {
			return;
		}
		FseDocumentTransactionType theTransactionType = FseDocumentTransactionType.REVERT_ALL;
		if(!bRevertNotes) {
			theTransactionType = FseDocumentTransactionType.REVERT_STORY;
		} else if(!bRevertStory) {
			theTransactionType = FseDocumentTransactionType.REVERT_NOTES;
		}
		FseDocumentTransaction theDocumentTransaction = getHistorySectionPerspective().getTransactionTableView().getSelectedDocumentTransaction();
		String theComment = theDocumentTransaction.getRowTimestamp().toString();
		if(bRevertStory) {
			this.displayedFseDocument.setDocumentSectionStory(theDocumentTransaction.getFseDocument().getDocumentSectionStory());
			this.displayedFseDocument.getDocumentSectionStory().resetModificationState();
		}
		if(bRevertNotes) {
			this.displayedFseDocument.setDocumentSectionNotes(theDocumentTransaction.getFseDocument().getDocumentSectionNotes());
		}
		displayDocumentSection(this.activeDocumentSectionType);
		setAsNewBaseline();
		Date theTimestamp = GcgDateHelper.getCurrentDateTime();
		updateHistory(theTimestamp, theTransactionType, theComment);
		updateAuditBlock(theTimestamp, theTransactionType);
		updateCollaboratorSummary();
		FmmDatabaseMediator.getActiveMediator().updateFseDocument(generateFseDocument(), true);
		getHistorySectionPerspective().refresh();
		getCollaboratorsSectionPerspective().invalidate();
	}

	@Override
	public void viewData() {
		if(isStaleData()) {
			this.displayedFseDocument = FmmDatabaseMediator.getActiveMediator().getFseDocumentForParentOrCreate(getParentNodeIdString());
			setTargetNodeIdString(this.displayedFseDocument.getNodeFragNodeIdString());
		}
		viewDocument(this.displayedFseDocument);
	}

	public FseDocumentSectionType getActiveDocumentSectionType() {
		return this.activeDocumentSectionType;
	}

	public void setActiveDocumentSectionType(FseDocumentSectionType anFseDocumentSectionType) {
		this.activeDocumentSectionType = anFseDocumentSectionType;
	}

}
