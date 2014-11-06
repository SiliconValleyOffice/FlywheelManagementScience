/* @(#)FseHistoryBrowserViewFlipper.java
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

import com.flywheelms.gcongui.gcg.widget.date.GcgDateHelper;
import com.flywheelms.library.fca.FlywheelCommunityAuthentication;
import com.flywheelms.library.fmm.enumerator.FmmLockStatus;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.governable.CommunityMember;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragAuditBlock;
import com.flywheelms.library.fse.FseDocumentSectionType;
import com.flywheelms.library.fse.activity.FseDocumentHistoryBrowserActivity;
import com.flywheelms.library.fse.enumerator.FseContentModificationState;
import com.flywheelms.library.fse.model.FseDocument;
import com.flywheelms.library.fse.perspective_flipper.perspective.FseDocumentSectionParagraphEditorPerspective;
import com.flywheelms.library.fse.perspective_flipper.perspective.FseDocumentSectionPerspective;

import org.json.JSONObject;

import java.util.Date;

public class FseHistoryBrowserPerspectiveFlipper extends FsePerspectiveFlipper {

	public FseHistoryBrowserPerspectiveFlipper(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
	}
	
	@Override
	public void initializeViewFlipperViews() {
		this.storySectionPerspective = getHistoryBrowserActivity().getStoryPerspective();
		this.notesSectionPerspective = getHistoryBrowserActivity().getNotesPerspective();
		initializePageTitles();
	}
	
	@Override
	public void viewDocument(FseDocument anFseDocument, FseDocument aDocumentBaseline) {
		this.documentId = anFseDocument.getDocumentId();
		this.documentMarginLeft = anFseDocument.getDocumentMarginLeft();
		getStorySectionPerspective().viewDocumentSectionAsHistory(anFseDocument.getDocumentSectionStory());
		getNotesSectionPerspective().viewDocumentSectionAsHistory(anFseDocument.getDocumentSectionNotes());
		displayDocumentSection(FseDocumentSectionType.STORY);
	}

	@Override
	public void displayDocumentSection(FseDocumentSectionType aDocumentSectionType) {
		switch (aDocumentSectionType) {
			case STORY:
				setDisplayedChild(0);
				break;
			case NOTES:
				setDisplayedChild(1);
				break;
			default:
		}
	}
	
	@Override
	public FseDocumentSectionType getCurrentPerspectiveDocumentSectionType() {
		return ((FseDocumentSectionPerspective) getCurrentView()).getSectionType();
	}
	
	@Override
	public FseDocumentSectionPerspective getActiveDocumentSectionView() {
		return (FseDocumentSectionPerspective) getCurrentView();
	}

	@Override
	public FseDocumentSectionParagraphEditorPerspective getStorySectionPerspective() {
		return getHistoryBrowserActivity().getStoryPerspective();
	}

	@Override
	public FseDocumentSectionParagraphEditorPerspective getNotesSectionPerspective() {
		return getHistoryBrowserActivity().getNotesPerspective();
	}

	@Override
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

	@Override
	public void setLastUpdatedTimestamp(Date aLastUpdate) {
		getNodeFragAuditBlock().setRowTimestamp(aLastUpdate);
	}

	//Locked  //

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

	@Override
	public void unlock() {
		// we want to know who unlocked it, and when
		getNodeFragAuditBlock().setLockedBy(FlywheelCommunityAuthentication.getInstance().getFcaUserCredentials().getCommunityMemberNodeIdString());
		getNodeFragAuditBlock().setLockedTimestamp(GcgDateHelper.getCurrentDateTime());
		getNodeFragAuditBlock().setIsLocked(false);
	}

	@Override
	public void setIsLocked(boolean aBoolean) {
//		getFseParagraphAuditBlock().setIsLocked(isLocked);
		return;
	}

	@Override
	public boolean isLocked() {
//		return getFseParagraphAuditBlock().isLocked();
		return false;
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


	@Override
	public FseContentModificationState getModificationState() {
		return FseContentModificationState.UNCHANGED;
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

//	public FseDocumentT getFmsNodeActivity() {
//		return (FmsNodeActivity) getGcgActivity();
//	}
	
	public FseDocumentHistoryBrowserActivity getHistoryBrowserActivity() {
		return (FseDocumentHistoryBrowserActivity) getGcgActivity();
	}

//	public View getLeftMenuInclude() {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	public GcgSpinnableMenu getLeftSpinnableMenu() {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	public LinearLayout getLeftFdkKeypad() {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	public FdkLeftKeypad getFdkLeftKeypad() {
//		return null;
//	}

	@Override
	public boolean isDataModified() {
		return false;
	}
	
	@Override
	public void onDocumentStateChange() {
		return;
	}

	@Override
	public boolean validateSerializationFormatVersion(String aString) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public NodeFragAuditBlock getNodeFragAuditBlock() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setNodeFragAuditBlock(NodeFragAuditBlock auditBlock) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getSerializedBaseline() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSerialized() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject getJsonObject() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean documentIsModified() {
		return false;
	}

	@Override
	public void leftMenuClickItem(Integer aMenuItem) {
		getHistoryBrowserActivity().leftMenuClickItem(aMenuItem);
	}

	@Override
	public void revertAllModifiedData() {
		return;
	}

	@Override
	public void newFractal() {
		return;
	}

	@Override
	public void navigationSpinnerSelect() {
		getHistoryBrowserActivity().navigationSpinnerSelect();
	}

	@Override
	public void navigateLeftButtonClick() {
		getHistoryBrowserActivity().navigateLeftButtonClick();
	}

	@Override
	public void navigateRightButtonClick() {
		getHistoryBrowserActivity().navigateRightButtonClick();
	}

}
