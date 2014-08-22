/* @(#)GcgTreeNode.java
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

package com.flywheelms.library.gcg.treeview.node;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


public class GcgTreeNode implements Serializable {
	
    private static final long serialVersionUID = 1L;
    private final GcgTreeNodeInfo treeNodeInfo;
	private final GcgTreeNodeTargetObject targetObject;
    private final GcgTreeNodeInfo parentTreeNodeInfo;
    private final int level;
    private boolean visible = true;
    private final List<GcgTreeNode> childTreeNodeList = new LinkedList<GcgTreeNode>();
    private List<GcgTreeNodeInfo> childTreeNodeInfoList = null;  // a performance optimization ?

    public GcgTreeNode(
    		final GcgTreeNodeInfo aTreeNodeInfo,
    		final GcgTreeNodeInfo aParentTreeNodeInfo,
    		final int aLevel,
            final boolean bBisible) {
        super();
        this.treeNodeInfo = aTreeNodeInfo;
        this.targetObject = this.treeNodeInfo.getTargetObject();
        this.parentTreeNodeInfo = aParentTreeNodeInfo;
        this.level = aLevel;
        this.visible = bBisible;
    }

    public int indexOfChildTreeNodeInfoList(final GcgTreeNodeInfo aChildTreeNodeInfo) {
        return getChildTreeNodeInfoList().indexOf(aChildTreeNodeInfo);
    }

    public synchronized List<GcgTreeNodeInfo> getChildTreeNodeInfoList() {
        if (this.childTreeNodeInfoList == null) {
        	this.childTreeNodeInfoList = new LinkedList<GcgTreeNodeInfo>();
            for (final GcgTreeNode theTreeNode : this.childTreeNodeList) {
            	this.childTreeNodeInfoList.add(theTreeNode.getTreeNodeInfo());
            }
        }
        return this.childTreeNodeInfoList;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public void setVisible(final boolean bVisible) {
        this.visible = bVisible;
    }

    public int getChildTreeNodeListSize() {
        return this.childTreeNodeList.size();
    }

    public synchronized GcgTreeNode addChild(
    		final int anIndex,
    		final GcgTreeNodeInfo aChildTreeNodeInfo,
            final boolean bVisible ) {
    	this.childTreeNodeInfoList = null;  // will lazy init later
        final GcgTreeNode theNewChildTreeNode = new GcgTreeNode(
        		aChildTreeNodeInfo,
                getTreeNodeInfo(),  // parent (this)
                getLevel() + 1,		// next level below (this)
                getTreeNodeInfo() == null ? true : bVisible);  // level 0 tree nodes are always visible
        this.childTreeNodeList.add(anIndex, theNewChildTreeNode);  // will _insert_ into LinkedList
        return theNewChildTreeNode;
    }

    public List<GcgTreeNode> getChildTreeNodeList() {
        return this.childTreeNodeList;
    }

    public synchronized void clearChildren() {
    	this.childTreeNodeList.clear();
    	this.childTreeNodeInfoList = null;
    }

    public synchronized void removeChild(final GcgTreeNodeInfo aChildTreeNodeInfo) {
        final int theChildIndex = indexOfChildTreeNodeInfoList(aChildTreeNodeInfo);
        if (theChildIndex != -1) {
        	this.childTreeNodeList.remove(theChildIndex);
        	this.childTreeNodeInfoList = null;
        }
    }

    @Override
    public String toString() {
        return "InMemoryTreeNode [nodeKey=" + getTreeNodeInfo() + ", parentNodeKey=" + getParentTreeNodeInfo()
                + ", level=" + getLevel() + ", visible=" + this.visible
                + ", childTreeNodeList=" + this.childTreeNodeList + ", childNodeKeyList="
                + this.childTreeNodeInfoList + "]";
    }

    public GcgTreeNodeInfo getTreeNodeInfo() {
        return this.treeNodeInfo;  // this object is a root node if null
    }

    public GcgTreeNodeInfo getParentTreeNodeInfo() {
        return this.parentTreeNodeInfo;
    }

    public int getLevel() {
        return this.level;
    }
    
    public GcgTreeNodeTargetObject getTargetObject() {
    	return this.targetObject;
    }

}
