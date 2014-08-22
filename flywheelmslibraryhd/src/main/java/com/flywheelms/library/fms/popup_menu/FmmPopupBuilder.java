/* @(#)FmmPopupBuilder.java
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

package com.flywheelms.library.fms.popup_menu;

import android.view.View;
import android.widget.PopupMenu;

import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;
import com.flywheelms.library.gcg.treeview.node.GcgTreeNodeInfo;

/*
 * on the surface, the purpose of this class is to build context-sensitive popup menus for FMM Nodes.
 * the important "magic sauce" of this class is validating whether a menu option is appropriate, given
 * the rules of the management model and the state of parent(s), peer(s) and children
 */

public class FmmPopupBuilder {

	public static final String menu_item__CREATE_BOOKSHELF = "Create Bookshelf...";
	public static final String menu_item__DELETE_BOOKSHELF = "Delete Bookshelf...";
	public static final String menu_item__ADOPT_ORPHAN_DISCUSSION_TOPIC = "Adopt orphan Discussion Topic...";
	public static final String menu_item__CREATE_DISCUSSION_TOPIC = "Create Discussion Topic...";
	public static final String menu_item__DELETE_DISCUSSION_TOPIC = "Delete Discussion Topic...";
	public static final String menu_item__MOVE_DISCUSSION_TOPIC = "Move Discussion Topic...";
	public static final String menu_item__REMOVE_DISCUSSION_TOPIC = "Remove Discussion Topic...";
	public static final String menu_item__CREATE_FISCAL_YEAR = "Create Fiscal Year...";
	public static final String menu_item__DELETE_FISCAL_YEAR = "Delete Fiscal Year...";
	public static final String menu_item__CREATE_FLYWHEEL_MILESTONE = "Create Flywheel Milestone...";
	public static final String menu_item__DELETE_FLYWHEEL_MILESTONE = "Delete Flywheel Milestone...";
	public static final String menu_item__MOVE_FLYWHEEL_MILESTONE = "Move Flywheel Milestone...";
	public static final String menu_item__ADOPT_ORPHAN_NOTEBOOK = "Adopt orphan Notebook...";
	public static final String menu_item__CREATE_NOTEBOOK = "Create Notebook...";
	public static final String menu_item__DELETE_NOTEBOOK = "Delete Notebook...";
	public static final String menu_item__MOVE_NOTEBOOK = "Move Notebook...";
	public static final String menu_item__ORPHAN_NOTEBOOK = "Orphan Notebook...";
	public static final String menu_item__CREATE_PORTFOLIO = "Create Portfolio...";
	public static final String menu_item__DELETE_PORTFOLIO = "Delete Portfolio...";
	public static final String menu_item__ADOPT_ORPHAN_PROJECT = "Adopt orphan Project...";
	public static final String menu_item__CREATE_PROJECT = "Create Project...";
	public static final String menu_item__DELETE_PROJECT = "Delete Project...";
	public static final String menu_item__MOVE_PROJECT = "Move Project...";
	public static final String menu_item__ORPHAN_PROJECT = "Orphan Project...";
	public static final String menu_item__ADOPT_ORPHAN_PROJECT_ASSET = "Adopt orphan Project Asset...";
	public static final String menu_item__CREATE_PROJECT_ASSET = "Create Project Asset...";
	public static final String menu_item__DELETE_PROJECT_ASSET = "Delete Project Asset...";
	public static final String menu_item__MOVE_PROJECT_ASSET = "Move Project Asset...";
	public static final String menu_item__ORPHAN_PROJECT_ASSET = "Orphan Project Asset...";
	public static final String menu_item__CREATE_STRATEGIC_MILESTONE = "Create Strategic Milestone...";
	public static final String menu_item__DELETE_STRATEGIC_MILESTONE = "Delete Strategic Milestone...";
	public static final String menu_item__MOVE_STRATEGIC_MILESTONE = "Move Strategic Milestone...";
	public static final String menu_item__ADOPT_ORPHAN_WORK_PACKAGE = "Adopt orphan Work Package...";
	public static final String menu_item__CREATE_WORK_PACKAGE = "Create Work Package...";
	public static final String menu_item__DELETE_WORK_PACKAGE = "Delete Work Package...";
	public static final String menu_item__MOVE_WORK_PACKAGE = "Move Work Package...";
	public static final String menu_item__ORPHAN_WORK_PACKAGE = "Orphan Work Package...";
	public static final String menu_item__CREATE_WORK_PLAN = "Create Work Plan...";
	public static final String menu_item__DELETE_WORK_PLAN = "Delete Work Plan...";
	public static final String menu_item__MOVE_WORK_PLAN = "Move Work Plan...";
	public static final String menu_item__ADOPT_ORPHAN_WORK_TASK= "Adopt orphan Work Task...";
	public static final String menu_item__CREATE_WORK_TASK= "Create Work Task...";
	public static final String menu_item__DELETE_WORK_TASK= "Delete Work Task...";
	public static final String menu_item__MOVE_WORK_TASK = "Move Work Task...";
	public static final String menu_item__ORPHAN_WORK_TASK = "Orphan Work Task...";
	
	public static final String menu_item__SEQUENCE_DOWN = "Sequence Down";
	public static final String menu_item__SEQUENCE_UP = "Sequence Up";
	public static final String menu_item__SEQUENCE_FIRST = "Sequence First";
	public static final String menu_item__SEQUENCE_LAST = "Sequence Last";

	public static final String menu_item__EDIT_HEADLINE = "Edit Headline...";
	public static final String menu_item__EDIT_STRATEGIC_MILESTONE_TARGET_DATE = "Edit Target Date...";

	public static PopupMenu createPopupMenu(
			FmmHeadlineNodePopupListener aNodePopupListener,
			GcgTreeNodeInfo aLaunchTreeNodeInfo,
			GcgTreeNodeInfo aParentTreeNodeInfo,
			View aView,
			boolean bCanDelete,
			boolean bCanMove,
			boolean bCanOrphan,
			boolean bCanSequenceUp,
			boolean bCanSequenceDown,
			int aLauncNodeSequence,
			int aLaunchNodeChildCount ) {
		FmmHeadlineNode theLaunchHeadlineNode = (FmmHeadlineNode) aLaunchTreeNodeInfo.getTargetObject();
		FmmHeadlineNode theParentHeadlineNode = (FmmHeadlineNode) aParentTreeNodeInfo.getTargetObject();
		switch (theLaunchHeadlineNode.getFmmNodeDefinition()) {
		case FISCAL_YEAR:
			return createFiscalYearPopupMenu(aNodePopupListener, aLaunchTreeNodeInfo, theLaunchHeadlineNode, theParentHeadlineNode, aView, bCanDelete, aLauncNodeSequence, aLaunchNodeChildCount);
		case PROJECT_ASSET:
			return createProjectAssetPopupMenu(aNodePopupListener, aLaunchTreeNodeInfo, theLaunchHeadlineNode, theParentHeadlineNode, aView, bCanDelete, bCanMove, bCanOrphan, bCanSequenceDown, bCanSequenceUp, aLauncNodeSequence, aLaunchNodeChildCount);
		case STRATEGIC_MILESTONE:
			return createStrategicMilestonePopupMenu(aNodePopupListener, aLaunchTreeNodeInfo, theLaunchHeadlineNode, theParentHeadlineNode, aView, bCanDelete, bCanMove, bCanOrphan, bCanSequenceDown, bCanSequenceUp, aLauncNodeSequence, aLaunchNodeChildCount);
		default:
			return null;
		}
	}

	private static PopupMenu createFiscalYearPopupMenu(
			FmmHeadlineNodePopupListener aNodePopupListener,
			GcgTreeNodeInfo aLaunchTreeNodeInfo,
			FmmHeadlineNode aLaunchHeadlineNode,
			FmmHeadlineNode aParentHeadlineNode,
			View aView,
			boolean bCanDelete,
			int aLaunchNodeSequence,
			int aLaunchNodeChildCount ) {
		FmmHeadlineNodePopupMenu thePopupMenu = new FmmHeadlineNodePopupMenu(
				aNodePopupListener, aView, aLaunchHeadlineNode, aParentHeadlineNode, aLaunchTreeNodeInfo, aLaunchNodeSequence, aLaunchNodeChildCount );
		thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__CREATE_FISCAL_YEAR);
		if(bCanDelete) {
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__DELETE_FISCAL_YEAR);
		}
		thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__CREATE_STRATEGIC_MILESTONE);
		return thePopupMenu;
	}

	private static PopupMenu createStrategicMilestonePopupMenu(
			FmmHeadlineNodePopupListener aNodePopupListener,
			GcgTreeNodeInfo aLaunchTreeNodeInfo,
			FmmHeadlineNode aLaunchHeadlineNode,
			FmmHeadlineNode aParentHeadlineNode,
			View aView,
			boolean bCanDelete,
			boolean bCanMove,
			boolean bCanOrphan,
			boolean bCanSequenceUp,
			boolean bCanSequenceDown,
			int aLaunchNodeSequence,
			int aLaunchNodeChildCount ) {
		FmmHeadlineNodePopupMenu thePopupMenu = new FmmHeadlineNodePopupMenu(
				aNodePopupListener, aView, aLaunchHeadlineNode, aParentHeadlineNode, aLaunchTreeNodeInfo, aLaunchNodeSequence, aLaunchNodeChildCount );
		thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__CREATE_STRATEGIC_MILESTONE);
		if(bCanDelete) {
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__DELETE_STRATEGIC_MILESTONE);
		}
		if(bCanMove) {
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__MOVE_STRATEGIC_MILESTONE);
		}
		if(bCanSequenceUp) {
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__SEQUENCE_FIRST);
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__SEQUENCE_UP);
		}
		if(bCanSequenceDown) {
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__SEQUENCE_DOWN);
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__SEQUENCE_LAST);
		}
		thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__EDIT_HEADLINE);
		thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__EDIT_STRATEGIC_MILESTONE_TARGET_DATE);
		thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__CREATE_PROJECT_ASSET);
		thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__ADOPT_ORPHAN_PROJECT_ASSET);
		return thePopupMenu;
	}

	private static PopupMenu createProjectPopupMenu(
			FmmHeadlineNodePopupListener aNodePopupListener,
			GcgTreeNodeInfo aLaunchTreeNodeInfo,
			FmmHeadlineNode aLaunchHeadlineNode,
			FmmHeadlineNode aParentHeadlineNode,
			View aView,
			boolean bCanSequenceUp,
			boolean bCanSequenceDown,
			boolean bCanDelete,
			boolean bCanMove,
			boolean bCanOrphan,
			int aLaunchNodeSequence,
			int aLaunchNodeChildCount ) {
		FmmHeadlineNodePopupMenu thePopupMenu = new FmmHeadlineNodePopupMenu(
				aNodePopupListener, aView, aLaunchHeadlineNode, aParentHeadlineNode, aLaunchTreeNodeInfo, aLaunchNodeSequence, aLaunchNodeChildCount );
		thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__CREATE_PROJECT);
		if(bCanDelete) {
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__DELETE_PROJECT);
		}
		if(bCanMove) {
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__MOVE_PROJECT);
		}
		if(bCanOrphan) {
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__ORPHAN_PROJECT);
		}
		if(bCanSequenceUp) {
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__SEQUENCE_FIRST);
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__SEQUENCE_UP);
		}
		if(bCanSequenceDown) {
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__SEQUENCE_DOWN);
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__SEQUENCE_LAST);
		}
		thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__EDIT_HEADLINE);
		thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__CREATE_PROJECT_ASSET);
		thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__ADOPT_ORPHAN_PROJECT_ASSET);
		return thePopupMenu;
	}

	private static PopupMenu createProjectAssetPopupMenu(
			FmmHeadlineNodePopupListener aNodePopupListener,
			GcgTreeNodeInfo aLaunchTreeNodeInfo,
			FmmHeadlineNode aLaunchHeadlineNode,
			FmmHeadlineNode aParentHeadlineNode,
			View aView,
			boolean bCanDelete,
			boolean bCanMove,
			boolean bCanOrphan,
			boolean bCanSequenceUp,
			boolean bCanSequenceDown,
			int aLaunchNodeSequence,
			int aLaunchNodeChildCount ) {
		FmmHeadlineNodePopupMenu thePopupMenu = new FmmHeadlineNodePopupMenu(
				aNodePopupListener, aView, aLaunchHeadlineNode, aParentHeadlineNode, aLaunchTreeNodeInfo, aLaunchNodeSequence, aLaunchNodeChildCount );
		thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__CREATE_PROJECT_ASSET);
		if(bCanDelete) {
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__DELETE_PROJECT_ASSET);
		}
		if(bCanMove) {
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__MOVE_PROJECT_ASSET);
		}
		if(bCanOrphan) {
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__ORPHAN_PROJECT_ASSET);
		}
		if(bCanSequenceUp) {
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__SEQUENCE_FIRST);
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__SEQUENCE_UP);
		}
		if(bCanSequenceDown) {
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__SEQUENCE_DOWN);
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__SEQUENCE_LAST);
		}
		thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__EDIT_HEADLINE);
		thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__CREATE_WORK_PACKAGE);
		thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__ADOPT_ORPHAN_WORK_PACKAGE);
		return thePopupMenu;
	}

	private static PopupMenu createFlywheelMilestonePopupMenu(
			FmmHeadlineNodePopupListener aNodePopupListener,
			GcgTreeNodeInfo aLaunchTreeNodeInfo,
			FmmHeadlineNode aLaunchHeadlineNode,
			FmmHeadlineNode aParentHeadlineNode,
			View aView,
			boolean bCanDelete,
			boolean bCanMove,
			boolean bCanOrphan,
			boolean bCanSequenceUp,
			boolean bCanSequenceDown,
			int aLaunchNodeSequence,
			int aLaunchNodeChildCount ) {
		FmmHeadlineNodePopupMenu thePopupMenu = new FmmHeadlineNodePopupMenu(
				aNodePopupListener, aView, aLaunchHeadlineNode, aParentHeadlineNode, aLaunchTreeNodeInfo, aLaunchNodeSequence, aLaunchNodeChildCount );
		thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__CREATE_FLYWHEEL_MILESTONE);
		if(bCanDelete) {
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__DELETE_FLYWHEEL_MILESTONE);
		}
		if(bCanMove) {
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__MOVE_FLYWHEEL_MILESTONE);
		}
		if(bCanSequenceUp) {
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__SEQUENCE_FIRST);
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__SEQUENCE_UP);
		}
		if(bCanSequenceDown) {
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__SEQUENCE_DOWN);
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__SEQUENCE_LAST);
		}
		thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__EDIT_HEADLINE);
		thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__CREATE_WORK_PLAN);
		return thePopupMenu;
	}

	private static PopupMenu createWorkPackagePopupMenu(
			FmmHeadlineNodePopupListener aNodePopupListener,
			GcgTreeNodeInfo aLaunchTreeNodeInfo,
			FmmHeadlineNode aLaunchHeadlineNode,
			FmmHeadlineNode aParentHeadlineNode,
			View aView,
			boolean bCanDelete,
			boolean bCanMove,
			boolean bCanOrphan,
			boolean bCanSequenceUp,
			boolean bCanSequenceDown,
			int aLaunchNodeSequence,
			int aLaunchNodeChildCount ) {
		FmmHeadlineNodePopupMenu thePopupMenu = new FmmHeadlineNodePopupMenu(
				aNodePopupListener, aView, aLaunchHeadlineNode, aParentHeadlineNode, aLaunchTreeNodeInfo, aLaunchNodeSequence, aLaunchNodeChildCount );
		thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__CREATE_WORK_PACKAGE);
		if(bCanDelete) {
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__DELETE_WORK_PACKAGE);
		}
		if(bCanMove) {
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__MOVE_WORK_PACKAGE);
		}
		if(bCanOrphan) {
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__ORPHAN_WORK_PACKAGE);
		}
		if(bCanSequenceUp) {
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__SEQUENCE_FIRST);
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__SEQUENCE_UP);
		}
		if(bCanSequenceDown) {
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__SEQUENCE_DOWN);
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__SEQUENCE_LAST);
		}
		thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__EDIT_HEADLINE);
		thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__CREATE_WORK_TASK);
		thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__ADOPT_ORPHAN_WORK_TASK);
		return thePopupMenu;
	}

	private static PopupMenu createWorkPlanPopupMenu(
			FmmHeadlineNodePopupListener aNodePopupListener,
			GcgTreeNodeInfo aLaunchTreeNodeInfo,
			FmmHeadlineNode aLaunchHeadlineNode,
			FmmHeadlineNode aParentHeadlineNode,
			View aView,
			boolean bCanDelete,
			boolean bCanMove,
			boolean bCanOrphan,
			boolean bCanSequenceUp,
			boolean bCanSequenceDown,
			int aLaunchNodeSequence,
			int aLaunchNodeChildCount ) {
		FmmHeadlineNodePopupMenu thePopupMenu = new FmmHeadlineNodePopupMenu(
				aNodePopupListener, aView, aLaunchHeadlineNode, aParentHeadlineNode, aLaunchTreeNodeInfo, aLaunchNodeSequence, aLaunchNodeChildCount );
		thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__CREATE_WORK_PLAN);
		if(bCanDelete) {
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__DELETE_WORK_PLAN);
		}
		if(bCanMove) {
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__MOVE_WORK_PLAN);
		}
		if(bCanSequenceUp) {
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__SEQUENCE_FIRST);
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__SEQUENCE_UP);
		}
		if(bCanSequenceDown) {
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__SEQUENCE_DOWN);
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__SEQUENCE_LAST);
		}
		thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__EDIT_HEADLINE);
		thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__CREATE_WORK_TASK);
		thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__ADOPT_ORPHAN_WORK_TASK);
		return thePopupMenu;
	}

	private static PopupMenu createWorkTaskPopupMenu(
			FmmHeadlineNodePopupListener aNodePopupListener,
			GcgTreeNodeInfo aLaunchTreeNodeInfo,
			FmmHeadlineNode aLaunchHeadlineNode,
			FmmHeadlineNode aParentHeadlineNode,
			View aView,
			boolean bCanDelete,
			boolean bCanMove,
			boolean bCanOrphan,
			boolean bCanSequenceUp,
			boolean bCanSequenceDown,
			int aLaunchNodeSequence,
			int aLaunchNodeChildCount ) {
		FmmHeadlineNodePopupMenu thePopupMenu = new FmmHeadlineNodePopupMenu(
				aNodePopupListener, aView, aLaunchHeadlineNode, aParentHeadlineNode, aLaunchTreeNodeInfo, aLaunchNodeSequence, aLaunchNodeChildCount );
		thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__CREATE_WORK_TASK);
		if(bCanDelete) {
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__DELETE_WORK_TASK);
		}
		if(bCanMove) {
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__MOVE_WORK_TASK);
		}
		if(bCanOrphan) {
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__ORPHAN_WORK_TASK);
		}
		if(bCanSequenceUp) {
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__SEQUENCE_FIRST);
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__SEQUENCE_UP);
		}
		if(bCanSequenceDown) {
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__SEQUENCE_DOWN);
			thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__SEQUENCE_LAST);
		}
		thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__EDIT_HEADLINE);
		return thePopupMenu;
	}

}
