/* @(#)GcgTreeViewMediatorMemoryResident.java
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
import com.flywheelms.gcongui.gcg.treeview.exception.NodeAlreadyInTreeException;
import com.flywheelms.gcongui.gcg.treeview.exception.NodeNotInTreeException;
import com.flywheelms.gcongui.gcg.treeview.node.GcgTreeNode;
import com.flywheelms.gcongui.gcg.treeview.node.GcgTreeNodeInfo;
import com.flywheelms.gcongui.gcg.treeview.node.GcgTreeNodePersistentState;
import com.flywheelms.gcongui.gcg.treeview.node.GcgTreeNodeStateBundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class GcgTreeViewMediatorMemoryResident implements GcgTreeViewMediator {
	
    private static final long serialVersionUID = 1L;
    protected final Map<GcgTreeNodeInfo, GcgTreeNode> treeNodeMap = new HashMap<GcgTreeNodeInfo, GcgTreeNode>();
    private final GcgTreeNodeInfo rootTreeNodeInfo = createRootTreeNodeInfo();

    protected abstract GcgTreeNodeInfo createRootTreeNodeInfo();

    private final GcgTreeNode rootTreeNode = new GcgTreeNode(this.rootTreeNodeInfo, null, -1, true);
    private GcgTreeNodeInfo firstVisibleTreeNodeInfo;
    private transient List<GcgTreeNodeInfo> visibleTreeNodeInfoList = null;
    private transient List<GcgTreeNodeInfo> unmodifiableVisibleTreeNodeInfoList = null;
    private boolean visibleByDefault = true;
    private final transient Set<DataSetObserver> treeStateObservers = new HashSet<DataSetObserver>();
    private transient GcgTreeFilter treeFilter;
    
    public GcgTreeViewMediatorMemoryResident(GcgTreeFilter aTreeFilter) {
    	super();
    	this.treeNodeMap.put(this.rootTreeNodeInfo, this.rootTreeNode);
    	this.treeFilter = aTreeFilter;
    }
    
    @Override
	public ArrayList<GcgTreeNodeInfo> getAllVisibleTreeNodeInfoList() {
    	return new ArrayList<GcgTreeNodeInfo>(this.visibleTreeNodeInfoList);
    }
	
	@Override
	public void restoreTreeNodeStates(HashMap<String, GcgTreeNodeInfo> aSavedTreeNodeInfoMap) {
		GcgTreeNodeInfo theLiveTreeNodeInfo;
		for(GcgTreeNodeInfo theTreeNodeInfo : this.visibleTreeNodeInfoList) {
			String theNodeIdString = theTreeNodeInfo.getTargetObject().getIdString();
			theLiveTreeNodeInfo = aSavedTreeNodeInfoMap.get(theNodeIdString);
			if(theLiveTreeNodeInfo != null) {
				if(! theLiveTreeNodeInfo.isExpanded() && theLiveTreeNodeInfo.hasChildren()) {
					collapseChildren(theTreeNodeInfo);
				}
			}
		}
	}
	
    protected synchronized void treeStateChanged() {
    	this.visibleTreeNodeInfoList = null;
    	this.unmodifiableVisibleTreeNodeInfoList = null;
        for (final DataSetObserver theTreeObserver : this.treeStateObservers) {
            theTreeObserver.onChanged();
        }
    }

    public void setVisibleByDefault(final boolean bVisibleByDefault) {
        this.visibleByDefault = bVisibleByDefault;
    }

    private GcgTreeNode getNodeFromTreeMapOrThrow(final GcgTreeNodeInfo aTreeNodeInfo) {
        if (aTreeNodeInfo == null) {
            throw new NodeNotInTreeException("(null)");
        }
        final GcgTreeNode theTreeNode = this.treeNodeMap.get(aTreeNodeInfo);
        if (theTreeNode == null) {
            throw new NodeNotInTreeException(aTreeNodeInfo.toString());
        }
        return theTreeNode;
    }

    private GcgTreeNode getNodeFromTreeMapOrRootNode(final GcgTreeNodeInfo aTreeNodeInfo) {
        if (aTreeNodeInfo == null) {
            return this.rootTreeNode;
        }
        return getNodeFromTreeMapOrThrow(aTreeNodeInfo);
    }

    private void validateNodeNotInTreeMap(final GcgTreeNodeInfo aTreeNodeInfo) {
        final GcgTreeNode theTreeNode = this.treeNodeMap.get(aTreeNodeInfo);
        if (theTreeNode != null) {
            throw new NodeAlreadyInTreeException(aTreeNodeInfo.toString(), theTreeNode.toString());
        }
    }

    @Override
    public synchronized List<GcgTreeNodeInfo> getChildren(final GcgTreeNodeInfo aTreeNodeInfo) {
        final GcgTreeNode theTreeNode = getNodeFromTreeMapOrRootNode(aTreeNodeInfo);
        return theTreeNode.getChildTreeNodeInfoList();
    }

    @Override
    public synchronized GcgTreeNodeInfo getParent(final GcgTreeNodeInfo aTreeNodeInfo) {
        final GcgTreeNode theTreeNode = getNodeFromTreeMapOrRootNode(aTreeNodeInfo);
        return theTreeNode.getParentTreeNodeInfo();
    }

    private boolean getChildrenVisibility(final GcgTreeNode aTreeNode) {
        final List<GcgTreeNode> theChildTreeNodeList = aTreeNode.getChildTreeNodeList();
        if (theChildTreeNodeList.isEmpty()) {
            return this.visibleByDefault;
        }
		/*
		 *  new logic to deal with the fact that not all children have the same visibility at a level.
		 *  old logic just checked the visibility of item [0] in the list
		 */
		for(GcgTreeNode theTreeNode : theChildTreeNodeList) {
			if(theTreeNode.isVisible() == true) {
				return true;
			}
		}
        return false;
    }

    @Override
    public synchronized void addBeforeChild(
    		final GcgTreeNodeInfo aNewChildTreeNodeInfo,
    		final GcgTreeNodeInfo aParentTreeNodeInfo,
            final GcgTreeNodeInfo anExistingChildTreeNodeInfo) {
        validateNodeNotInTreeMap(aNewChildTreeNodeInfo);
        final GcgTreeNode theParentTreeNode = 
        		getNodeFromTreeMapOrRootNode(aParentTreeNodeInfo);
        final boolean theVisibility = getChildrenVisibility(theParentTreeNode);
        if (anExistingChildTreeNodeInfo == null) {
            final GcgTreeNode theNewChildTreeNode = 
            		theParentTreeNode.addChild(0, aNewChildTreeNodeInfo, theVisibility);
            this.treeNodeMap.put(aNewChildTreeNodeInfo, theNewChildTreeNode);
        } else {
            final int theIndex = 
            		theParentTreeNode.indexOfChildTreeNodeInfoList(anExistingChildTreeNodeInfo);
            final GcgTreeNode theNewTreeNode = 
            		theParentTreeNode.addChild(
            				theIndex == -1 ? 0 : theIndex, aNewChildTreeNodeInfo, theVisibility );
            this.treeNodeMap.put(aNewChildTreeNodeInfo, theNewTreeNode);
        }
        if (theVisibility) {
            treeStateChanged();
        }
    }

    @Override
    public synchronized void addAfterChild(
    		final GcgTreeNodeInfo aNewChildTreeNodeInfo,
    		final GcgTreeNodeInfo aParentTreeNodeInfo,
            final GcgTreeNodeInfo anExistingChildTreeNodeInfo) {
        validateNodeNotInTreeMap(aNewChildTreeNodeInfo);
        final GcgTreeNode theParentTreeNode = getNodeFromTreeMapOrRootNode(aParentTreeNodeInfo);
        final boolean theVisibility = getChildrenVisibility(theParentTreeNode);
        if (anExistingChildTreeNodeInfo == null) {
            final GcgTreeNode theNewTreeNode = theParentTreeNode.addChild(
                    theParentTreeNode.getChildTreeNodeListSize(), aNewChildTreeNodeInfo, aNewChildTreeNodeInfo.isVisible() );
            this.treeNodeMap.put(aNewChildTreeNodeInfo, theNewTreeNode);
        } else {
            final int theTreeNodeIndex = theParentTreeNode.indexOfChildTreeNodeInfoList(anExistingChildTreeNodeInfo);
            final GcgTreeNode theNewTreeNode = theParentTreeNode.addChild(
                    theTreeNodeIndex == -1 ? theParentTreeNode.getChildTreeNodeListSize() : theTreeNodeIndex + 1,
                    aNewChildTreeNodeInfo,
                    aNewChildTreeNodeInfo.isVisible() );
            this.treeNodeMap.put(aNewChildTreeNodeInfo, theNewTreeNode);
        }
        if (theVisibility) {
            treeStateChanged();
        }
    }

    @Override
    public synchronized void removeNodeRecursively(final GcgTreeNodeInfo aTreeNodeInfo) {
        final GcgTreeNode theTreeNode = getNodeFromTreeMapOrRootNode(aTreeNodeInfo);
        final boolean isVisibleNodeChanged = removeNodeRecursively(theTreeNode);
        final GcgTreeNodeInfo theParentTreeNodeInfo = theTreeNode.getParentTreeNodeInfo();
        final GcgTreeNode theParentTreeNode = getNodeFromTreeMapOrRootNode(theParentTreeNodeInfo);
        theParentTreeNode.removeChild(aTreeNodeInfo);
        if (isVisibleNodeChanged) {
            treeStateChanged();
        }
    }

    private boolean removeNodeRecursively(final GcgTreeNode aTreeNode) {
        boolean isVisibleNodeChanged = false;
        for (final GcgTreeNode theChildTreeNode : aTreeNode.getChildTreeNodeList()) {
            if (removeNodeRecursively(theChildTreeNode)) {
                isVisibleNodeChanged = true;
            }
        }
        aTreeNode.clearChildren();
        if (aTreeNode.getTreeNodeInfo() != null) {
        	this.treeNodeMap.remove(aTreeNode.getTreeNodeInfo());
            if (aTreeNode.isVisible()) {
                isVisibleNodeChanged = true;
            }
        }
        return isVisibleNodeChanged;
    }

    private void setChildrenVisibility(
    		final GcgTreeNode aTreeNode,
            final boolean bVisible,
            final boolean bRecursive ) {
        for (final GcgTreeNode theChildTreeNode : aTreeNode.getChildTreeNodeList()) {
        	theChildTreeNode.setVisible(bVisible == false || this.treeFilter == null ? bVisible
        			: this.treeFilter.showInTree(aTreeNode));
            if (bRecursive) {
                setChildrenVisibility(theChildTreeNode, bVisible, true);
            }
        }
    }
    
	@Override
	public synchronized void expandDirectChildren(final GcgTreeNodeInfo aTreeNodeInfo) {
		final GcgTreeNode theParentTreeNode = getNodeFromTreeMapOrRootNode(aTreeNodeInfo);
		//setChildrenVisibility(theParentTreeNode, true, false);
		restoreTreeNodeState(theParentTreeNode);
		theParentTreeNode.getTreeNodeInfo().setExpanded(true); // Hack Alert !!! - SDS
		treeStateChanged();
	}

	public void restoreTreeNodeState(GcgTreeNodeInfo aTreeNodeInfo) {
		restoreTreeNodeState(this.treeNodeMap.get(aTreeNodeInfo));
	}

	private void restoreTreeNodeState(GcgTreeNode aTreeNode) {
		for (final GcgTreeNode theChildTreeNode : aTreeNode.getChildTreeNodeList()) {
			theChildTreeNode.setVisible(this.treeFilter != null ? this.treeFilter.showInTree(theChildTreeNode) : true);
			if(theChildTreeNode.getTreeNodeInfo().isExpanded()) {
				restoreTreeNodeState(theChildTreeNode);
			}
		}
	}
	
	@Override
	public void applyTreeFilterCriteria() {
		restoreTreeNodeState(this.rootTreeNode);
	}
    
	@Override
	public void collapseToTreeLevel(int aTreeDepth) {
		int theTreeDepth = aTreeDepth == getMaxTreeDepth() ? 99 : aTreeDepth;
		switch (theTreeDepth) {
			case 1:
				for(GcgTreeNodeInfo theTreeNodeInfoLevel1 : getChildren(this.rootTreeNodeInfo)) {
					collapseChildren(theTreeNodeInfoLevel1);
				}						
				break;
			case 2:
				for(GcgTreeNodeInfo theTreeNodeInfoLevel1 : getChildren(this.rootTreeNodeInfo)) {
					expandDirectChildren(theTreeNodeInfoLevel1);
					for(GcgTreeNodeInfo theTreeNodeInfoLevel2 : getChildren(theTreeNodeInfoLevel1)) {
						collapseChildren(theTreeNodeInfoLevel2);
					}	
				}	
				break;
			case 3:
				for(GcgTreeNodeInfo theTreeNodeInfoLevel1 : getChildren(this.rootTreeNodeInfo)) {
					expandDirectChildren(theTreeNodeInfoLevel1);
					for(GcgTreeNodeInfo theTreeNodeInfoLevel2 : getChildren(theTreeNodeInfoLevel1)) {
						expandDirectChildren(theTreeNodeInfoLevel2);
						for(GcgTreeNodeInfo theTreeNodeInfoLevel3 : getChildren(theTreeNodeInfoLevel2)) {
							collapseChildren(theTreeNodeInfoLevel3);
						}	
					}	
				}	
				break;
			case 4:
				for(GcgTreeNodeInfo theTreeNodeInfoLevel1 : getChildren(this.rootTreeNodeInfo)) {
					expandDirectChildren(theTreeNodeInfoLevel1);
					for(GcgTreeNodeInfo theTreeNodeInfoLevel2 : getChildren(theTreeNodeInfoLevel1)) {
						expandDirectChildren(theTreeNodeInfoLevel2);
						for(GcgTreeNodeInfo theTreeNodeInfoLevel3 : getChildren(theTreeNodeInfoLevel2)) {
							expandDirectChildren(theTreeNodeInfoLevel3);
							for(GcgTreeNodeInfo theTreeNodeInfoLevel4 : getChildren(theTreeNodeInfoLevel3)) {
								collapseChildren(theTreeNodeInfoLevel4);
							}	
						}	
					}	
				}	
				break;
			case 99:
				for(GcgTreeNodeInfo theTreeNodeInfoLevel1 : getChildren(this.rootTreeNodeInfo)) {
					expandEverythingBelow(theTreeNodeInfoLevel1);
				}	
				break;
			default:
		}
		treeStateChanged();
	}

    protected int getMaxTreeDepth() {
		return 0;
	}

	@Override
    public synchronized void expandEverythingBelow(final GcgTreeNodeInfo aTreeNodeInfo) {
        final GcgTreeNode theParentTreeNode = getNodeFromTreeMapOrRootNode(aTreeNodeInfo);
        setChildrenVisibility(theParentTreeNode, true, true);
        treeStateChanged();
    }

    @Override
    public synchronized void collapseChildren(final GcgTreeNodeInfo aTreeNodeInfo) {
        final GcgTreeNode theParentTreeNode = getNodeFromTreeMapOrRootNode(aTreeNodeInfo);
        if (theParentTreeNode == this.rootTreeNode) {
            for (final GcgTreeNode theChildTreeNode : this.rootTreeNode.getChildTreeNodeList()) {
                setChildrenVisibility(theChildTreeNode, false, true);
            }
        } else {
            setChildrenVisibility(theParentTreeNode, false, true);
        }
        theParentTreeNode.getTreeNodeInfo().setExpanded(false);  // Hack Alert  -  SDS
        treeStateChanged();
    }

    @Override
    public synchronized GcgTreeNodeInfo getNextSibling(final GcgTreeNodeInfo aTreeNodeInfo) {
        final GcgTreeNodeInfo theParentTreeNodeInfo = getParent(aTreeNodeInfo);
        final GcgTreeNode theParentTreeNode = getNodeFromTreeMapOrRootNode(theParentTreeNodeInfo);
        boolean isReturnNextNode = false;
        for (final GcgTreeNode theChildTreeNode : theParentTreeNode.getChildTreeNodeList()) {
            if (isReturnNextNode) {
                return theChildTreeNode.getTreeNodeInfo();
            }
            if (theChildTreeNode.getTreeNodeInfo().equals(aTreeNodeInfo)) {
                isReturnNextNode = true;
            }
        }
        return null;
    }

    @Override
    public synchronized GcgTreeNodeInfo getPreviousSibling(final GcgTreeNodeInfo aTreeNodeInfo) {
        final GcgTreeNodeInfo theParentTreeNodeInfo = getParent(aTreeNodeInfo);
        final GcgTreeNode theParentTreeNode = getNodeFromTreeMapOrRootNode(theParentTreeNodeInfo);
        GcgTreeNodeInfo thePreviousSiblingTreeNodeInfo = null;
        for (final GcgTreeNode theChildTreeNode : theParentTreeNode.getChildTreeNodeList()) {
            if (theChildTreeNode.getTreeNodeInfo().equals(aTreeNodeInfo)) {
                return thePreviousSiblingTreeNodeInfo;
            }
            thePreviousSiblingTreeNodeInfo = theChildTreeNode.getTreeNodeInfo();
        }
        return null;
    }

    public ArrayList<GcgTreeNodeInfo> getSiblingList(GcgTreeNodeInfo aTreeNodeInfo, boolean bRemoveSelf) {
        ArrayList<GcgTreeNodeInfo> theSiblingList = new ArrayList<GcgTreeNodeInfo>();
        theSiblingList.addAll(getChildren(getParent(aTreeNodeInfo)));
        if(bRemoveSelf) {
            theSiblingList.remove(aTreeNodeInfo);
        }
        return theSiblingList;
    }

    @Override
    public synchronized boolean isInTree(final GcgTreeNodeInfo aTreeNodeInfo) {
        return this.treeNodeMap.containsKey(aTreeNodeInfo);
    }

    @Override
    public synchronized int getVisibleCount() {
        return getVisibleTreeNodeInfoList().size();
    }
    
    @Override
	public GcgTreeNodeInfo setFirstVisibleTreeNodeInfo() {
    	return this.firstVisibleTreeNodeInfo = this.visibleTreeNodeInfoList.get(0);
    }
    
    @Override
	public void setFirstVisibleTreeNodeInfo(GcgTreeNodeInfo aTreeNodeInfo) {
    	this.firstVisibleTreeNodeInfo = aTreeNodeInfo;
    	
    }
    
    @Override
	public GcgTreeNodeInfo getFirstVisibleTreeNodeInfo() {
    	return this.firstVisibleTreeNodeInfo;
    }

	@Override
	public synchronized List<GcgTreeNodeInfo> getVisibleTreeNodeInfoList() {
		if (this.visibleTreeNodeInfoList == null) {
			initializeVisibleTreeNodeInfoList();
		}
		if (this.unmodifiableVisibleTreeNodeInfoList == null) {
			this.unmodifiableVisibleTreeNodeInfoList = Collections.unmodifiableList(this.visibleTreeNodeInfoList);
		}
		return this.unmodifiableVisibleTreeNodeInfoList;
	}

	protected void initializeVisibleTreeNodeInfoList() {
		this.visibleTreeNodeInfoList = new ArrayList<GcgTreeNodeInfo>();
		LinkedList<GcgTreeNode> theQueue = new LinkedList<GcgTreeNode>();
		theQueue.push(this.rootTreeNode);
		while (!theQueue.isEmpty()) {
			GcgTreeNode theNode = theQueue.pop();
			if (theNode.getLevel() == -1) {
				theQueue.addAll(0, theNode.getChildTreeNodeList());
			}
			else {
				if (theNode.isVisible()) {
					this.visibleTreeNodeInfoList.add(theNode.getTreeNodeInfo());
					theQueue.addAll(0, theNode.getChildTreeNodeList());
				}
			}
		}
	}

    @Override
    public synchronized void registerDataSetObserver(
            final DataSetObserver aTreeObserver) {
    	this.treeStateObservers.add(aTreeObserver);
    }

    @Override
    public synchronized void unregisterDataSetObserver(
            final DataSetObserver aTreeObserver) {
    	this.treeStateObservers.remove(aTreeObserver);
    }

    @Override
    public int getLevel(final GcgTreeNodeInfo aTreeNodeInfo) {
        return getNodeFromTreeMapOrThrow(aTreeNodeInfo).getLevel();
    }
    
    /**
     * The location corresponds to heading numbering.
     * Examples for 3-level node:
     * {0,0,0} the first leaf of first subnode of the first node
     * {0,0,1} is second leaf of first subnode of the first node
     */
    @Override
    public Integer[] getNodeLocationInHierarchy(final GcgTreeNodeInfo aTreeNodeInfo) {
        final int theLevel = getLevel(aTreeNodeInfo);
        final Integer[] theHierarchyArray = new Integer[theLevel + 1];
        int theCurrentLevel = theLevel;
        GcgTreeNodeInfo theCurrentTreeNodeInfo = aTreeNodeInfo;
        GcgTreeNodeInfo theParentTreeNodeInfo = getParent(theCurrentTreeNodeInfo);
        while (theCurrentLevel >= 0) {
            theHierarchyArray[theCurrentLevel--] = getChildren(theParentTreeNodeInfo).indexOf(theCurrentTreeNodeInfo);
            theCurrentTreeNodeInfo = theParentTreeNodeInfo;
            theParentTreeNodeInfo = getParent(theParentTreeNodeInfo);
        }
        return theHierarchyArray;
    }
    
    @Override
    public synchronized void clearTree() {
    	this.treeNodeMap.clear();
    	this.rootTreeNode.clearChildren();
        treeStateChanged();
    }

    @Override
    public void refreshViews() {
        treeStateChanged();
    }
    
    @Override
    public boolean showInTree(GcgTreeNodeInfo aTreeNodeInfo) {
    	GcgTreeNode theTreeNodeMemoryResident = getNodeFromTreeMapOrThrow(aTreeNodeInfo);
    	return this.treeFilter.showInTree(theTreeNodeMemoryResident);
    }

	@Override
	public GcgTreeNodeInfo getTreeNodeInfoForObject(Object anObject) {
		GcgTreeNodeInfo theObjectTreeNodeInfo = null;
		for(GcgTreeNodeInfo theTreeNodeInfo : this.treeNodeMap.keySet()) {
			Object theTargetObject = theTreeNodeInfo.getTargetObject();
			if(theTargetObject.toString().equals(anObject.toString())) {
				theObjectTreeNodeInfo = theTreeNodeInfo;
				break;
			}
		}
		return theObjectTreeNodeInfo;
	}

	@Override
	public abstract GcgTreeNodeInfo getTreeNodeInfoForString(String aString);

	@Override
	public boolean verifyNodeOrder(Integer[] aFirstNodeLocation, Integer[] aSecondNodeLocation) {
		boolean isVerified = false;
		int theMaxDepth = Math.min(aFirstNodeLocation.length, aSecondNodeLocation.length);
		for(int theIndex = 0; theIndex < theMaxDepth; ++ theIndex) {
			if(aFirstNodeLocation[theIndex] < aSecondNodeLocation[theIndex]){
				isVerified = true;
				break;
			}
		}
		return isVerified;
	}

	@Override
	public boolean verifyNodeOrder(String aFirstOccurrenceTag, String aSecondOccurrenceTag) {
		GcgTreeNodeInfo theFirstTreeNodeInfo = getTreeNodeInfoForString(aFirstOccurrenceTag);
		GcgTreeNodeInfo theSecondTreeNodeInfo = getTreeNodeInfoForString(aSecondOccurrenceTag);
		return verifyNodeOrder(getNodeLocationInHierarchy(theFirstTreeNodeInfo), getNodeLocationInHierarchy(theSecondTreeNodeInfo));
	}
	
	@Override
	public int getPosition(GcgTreeNodeInfo aTreeNodeInfo) {
		int thePosition = -1;
		for(GcgTreeNode theTreeNodeInfo : this.treeNodeMap.values()) {
			++thePosition;
			if(theTreeNodeInfo.getTreeNodeInfo().getTargetObject().toString().equals(aTreeNodeInfo.getTargetObject().toString())) {
				break;
			}
		}
		return thePosition == -1 ? 0 : thePosition;
	}

    @Override
    public HashMap<String, GcgTreeNodePersistentState> getTreeNodePersistentStateMap() {
        HashMap<String, GcgTreeNodePersistentState> thePersistentStateMap = new HashMap<String, GcgTreeNodePersistentState>();
        for(GcgTreeNodeInfo theTreeNodeInfo : this.treeNodeMap.keySet()) {
            GcgTreeNodePersistentState thePersistentState =new GcgTreeNodePersistentState(theTreeNodeInfo);
            thePersistentStateMap.put(thePersistentState.getTreeNodeObjectId(), thePersistentState);
        }
        return thePersistentStateMap;
    }

    @Override
    public void applyTreeNodePersistentStateList(HashMap<String, GcgTreeNodePersistentState> aPersistentStateMap) {
        if(aPersistentStateMap == null) {
            return;
        }
        for(GcgTreeNodeInfo theTreeNodeInfo : this.treeNodeMap.keySet()) {
            GcgTreeNodePersistentState thePersistentState = aPersistentStateMap.get(theTreeNodeInfo.getObjectId());
            if(thePersistentState != null) {
                if(thePersistentState.isExpanded()) {
                    expandDirectChildren(theTreeNodeInfo);
                } else {
                    collapseChildren(theTreeNodeInfo);
                }
            }
        }
        treeStateChanged();
    }

    @Override
    public HashMap<String, GcgTreeNodePersistentState>  writeTreeNodePersistenceState(GcgActivity aGcgActivity, String aTreeNodePeristentStateBundleKey) {
        HashMap<String, GcgTreeNodePersistentState> theStateMap = getTreeNodePersistentStateMap();
        if(aTreeNodePeristentStateBundleKey != null) {
            GcgTreeNodeStateBundle.writeGcgTreeNodeStatePreferences(
                    aGcgActivity,
                    aTreeNodePeristentStateBundleKey,
                    theStateMap );
        }
        return theStateMap;
    }

}
