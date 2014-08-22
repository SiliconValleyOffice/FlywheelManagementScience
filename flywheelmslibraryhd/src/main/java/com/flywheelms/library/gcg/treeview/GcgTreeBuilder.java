/* @(#)GcgTreeBuilder.java
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

package com.flywheelms.library.gcg.treeview;

import com.flywheelms.library.gcg.treeview.exception.TreeConfigurationException;
import com.flywheelms.library.gcg.treeview.node.GcgTreeNodeInfo;

public class GcgTreeBuilder {

    protected final GcgTreeViewMediator treeStateManager;

    private GcgTreeNodeInfo lastAddedTreeNodeInfo = null;
    private int lastLevel = -1;

    public GcgTreeBuilder(final GcgTreeViewMediator aTreeStateManager) {
        this.treeStateManager = aTreeStateManager;
    }

    public void clear() {
    	this.treeStateManager.clearTree();
    }

    public synchronized void addRelation(final GcgTreeNodeInfo aParentTreeNodeInfo, final GcgTreeNodeInfo aChildTreeNodeInfo) {
        this.treeStateManager.addAfterChild(aChildTreeNodeInfo, aParentTreeNodeInfo, null);
        this.lastAddedTreeNodeInfo = aChildTreeNodeInfo;
        this.lastLevel = this.treeStateManager.getLevel(aChildTreeNodeInfo);
    }

    public synchronized void sequentiallyAddNextNode(final GcgTreeNodeInfo aTreeNodeInfo, final int aLevel ) {
        if (this.lastAddedTreeNodeInfo == null) {
            addNodeToParentOneLevelDown(null, aTreeNodeInfo, aLevel);
        } else {
            if (aLevel <= this.lastLevel) {
                final GcgTreeNodeInfo parent = findParentAtLevel(this.lastAddedTreeNodeInfo, aLevel - 1);
                addNodeToParentOneLevelDown(parent, aTreeNodeInfo, aLevel);
            } else {
                addNodeToParentOneLevelDown(this.lastAddedTreeNodeInfo, aTreeNodeInfo, aLevel);
            }
        }
    }

    private GcgTreeNodeInfo findParentAtLevel(final GcgTreeNodeInfo aTreeNodeInfo, final int aLevelToFind) {
        GcgTreeNodeInfo parent = this.treeStateManager.getParent(aTreeNodeInfo);
        while (parent != null) {
            if (this.treeStateManager.getLevel(parent) == aLevelToFind) {
                break;
            }
            parent = this.treeStateManager.getParent(parent);
        }
        return parent;
    }

    private void addNodeToParentOneLevelDown(final GcgTreeNodeInfo aParentTreeNodeInfo, final GcgTreeNodeInfo aChildTreeNodeInfo, final int level) {
        if (aParentTreeNodeInfo == null && level != 0) {
            throw new TreeConfigurationException("Trying to add new TreeNodeInfo " + aChildTreeNodeInfo
                    + " to top level with level != 0 (" + level + ")");
        }
        if (aParentTreeNodeInfo != null && this.treeStateManager.getLevel(aParentTreeNodeInfo) != level - 1) {
            throw new TreeConfigurationException("Trying to add new TreeNodeInfo " + aChildTreeNodeInfo
                    + " <" + level + "> to " + aParentTreeNodeInfo + " <"
                    + this.treeStateManager.getLevel(aParentTreeNodeInfo)
                    + ">. The difference in levels up is bigger than 1.");
        }
        this.treeStateManager.addAfterChild(aChildTreeNodeInfo, aParentTreeNodeInfo, null);
        setLastAdded(aChildTreeNodeInfo, level);
    }

    protected void setLastAdded(final GcgTreeNodeInfo aTreeNodeInfo, final int aLevel) {
    	this.lastAddedTreeNodeInfo = aTreeNodeInfo;
    	this.lastLevel = aLevel;
    }

}
