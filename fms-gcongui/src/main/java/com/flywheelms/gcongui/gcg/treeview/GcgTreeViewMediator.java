/* @(#)GcgTreeViewMediator.java
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

package com.flywheelms.gcongui.gcg.treeview;

import android.database.DataSetObserver;

import com.flywheelms.gcongui.gcg.activity.GcgActivity;
import com.flywheelms.gcongui.gcg.treeview.node.GcgTreeNodeInfo;
import com.flywheelms.gcongui.gcg.treeview.node.GcgTreeNodePersistentState;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface GcgTreeViewMediator extends Serializable {

    Integer[] getNodeLocationInHierarchy(GcgTreeNodeInfo aNodeKey);

    int getLevel(GcgTreeNodeInfo aTreeNodeInfo);

//    TreeNodeInfo getTreeNodeInfo(TreeNodeInfo aNodeKey);

    List<GcgTreeNodeInfo> getChildren(GcgTreeNodeInfo aParentTreeNodeInfo);

    GcgTreeNodeInfo getParent(GcgTreeNodeInfo aChildTreeNodeInfo);

    void addBeforeChild(GcgTreeNodeInfo aNewTreeNodeInfo, GcgTreeNodeInfo parentTreeNodeInfo, GcgTreeNodeInfo anExistingChildTreeNodeInfo);

    void addAfterChild(GcgTreeNodeInfo aNewTreeNodeInfo, GcgTreeNodeInfo parentTreeNodeInfo, GcgTreeNodeInfo anExistingChildTreeNodeInfo);

    void removeNodeRecursively(GcgTreeNodeInfo aTreeNodeInfo);

    void expandDirectChildren(GcgTreeNodeInfo aTreeNodeInfo);

    void expandEverythingBelow(GcgTreeNodeInfo aTreeNodeInfo);

    void collapseChildren(GcgTreeNodeInfo aTreeNodeInfo);
    
    void applyTreeFilterCriteria();
    
    void collapseToTreeLevel(int aTreeLevel);

    GcgTreeNodeInfo getNextSibling(GcgTreeNodeInfo aTreeNodeInfo);

    GcgTreeNodeInfo getPreviousSibling(GcgTreeNodeInfo aTreeNodeInfo);

    ArrayList<GcgTreeNodeInfo> getSiblingList(GcgTreeNodeInfo aTreeNodeInfo, boolean bRemoveSelf);

    boolean isInTree(GcgTreeNodeInfo aTreeNodeInfo);
    
    GcgTreeNodeInfo getTreeNodeInfoForObject(Object anObject);
    
    GcgTreeNodeInfo getTreeNodeInfoForString(String aNodeIdString);
    
    ArrayList<GcgTreeNodeInfo> getAllVisibleTreeNodeInfoList();
    
    int getVisibleCount();
	
	void restoreTreeNodeStates(HashMap<String, GcgTreeNodeInfo> aSavedTreeNodeInfoMap);
    
    GcgTreeNodeInfo setFirstVisibleTreeNodeInfo();
    
    void setFirstVisibleTreeNodeInfo(GcgTreeNodeInfo aTreeNodeInfo);
    
    GcgTreeNodeInfo getFirstVisibleTreeNodeInfo();

    List<GcgTreeNodeInfo> getVisibleTreeNodeInfoList();

    void registerDataSetObserver(final DataSetObserver aTreeStateObserver);

    void unregisterDataSetObserver(final DataSetObserver aTreeStateObserver);

    void clearTree();

    void refreshViews();
    
    boolean showInTree(GcgTreeNodeInfo aTreeNodeInfo);

	boolean verifyNodeOrder(String aFirstOccurrenceTag, String aSecondOccurrenceTag);
	
	boolean verifyNodeOrder(Integer[] aFirstNodeLocation, Integer[] aSecondNodeLocation);
	
	int getPosition(GcgTreeNodeInfo aTreeNodeInfo);

    HashMap<String, GcgTreeNodePersistentState> getTreeNodePersistentStateMap();

    void applyTreeNodePersistentStateList(HashMap<String, GcgTreeNodePersistentState> aPersistentStateMap);

    HashMap<String, GcgTreeNodePersistentState>  writeTreeNodePersistenceState(GcgActivity aGcgActivity, String aTreeNodePeristentStateBundleKey);
}
