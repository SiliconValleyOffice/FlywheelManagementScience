/* @(#)GcgTreeViewAdapter.java
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
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupMenu;

import com.flywheelms.gcongui.R;
import com.flywheelms.gcongui.deckangl.enumerator.DecKanGlDecoratedGlyphSize;
import com.flywheelms.gcongui.gcg.GcgApplication;
import com.flywheelms.gcongui.gcg.activity.GcgActivity;
import com.flywheelms.gcongui.gcg.helper.GcgHelper;
import com.flywheelms.gcongui.gcg.treeview.interfaces.GcgTreeViewParent;
import com.flywheelms.gcongui.gcg.treeview.node.GcgTreeNodeInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public abstract class GcgTreeViewAdapter extends BaseAdapter {
	
    protected GcgTreeViewMediator treeViewMediator;
    protected final int childNodeViewLayoutResourceId;
    private final int[] fontSizeArray;
    private int indentWidth = 0;
    private Drawable collapsedNodeButtonDrawable;
    private Drawable expandedNodeButtonDrawable;
    private Drawable emptyDrawable = GcgApplication.getInstance().getResources().getDrawable(R.drawable.gcg__empty_bitmap);
    private boolean collapsible;
    protected final GcgTreeViewParent gcgTreeViewParent;
    protected HashMap<String, GcgTreeNodeInfo> savedTreeNodeInfoMap;
    private final OnClickListener expandCollapseClickListener = new OnClickListener() {
    	
        @Override
        public void onClick(final View aView) {
            final GcgTreeNodeInfo theTreeNodeInfo = (GcgTreeNodeInfo) aView.getTag();
            expandCollapse(theTreeNodeInfo);
        }
    };
	protected final OnLongClickListener launchPopupMenuListener = new OnLongClickListener() {
		
		@Override
		public boolean onLongClick(View aView) {
			GcgTreeViewAdapter.this.setRowBackground(aView, R.color.w3c__silver);
			final GcgTreeNodeInfo theLaunchNodeInfo = (GcgTreeNodeInfo) aView.getTag();
			GcgTreeNodeInfo theParentNodeInfo = GcgTreeViewAdapter.this.treeViewMediator.getParent(theLaunchNodeInfo);
			final ArrayList<GcgTreeNodeInfo> thePeerNodeList = GcgTreeViewAdapter.this.getPeerTreeNodeInfoList(theLaunchNodeInfo);
			final int theLaunchNodeChildCount = GcgTreeViewAdapter.this.getChildTreeNodeInfoList(theLaunchNodeInfo).size();
			GcgTreeViewAdapter.this.createPopupMenu(
					theLaunchNodeInfo,
					theParentNodeInfo,
					aView,
					canDelete(theLaunchNodeInfo, theLaunchNodeInfo),
					canMove(theLaunchNodeInfo, theParentNodeInfo),
					canOrphan(theLaunchNodeInfo, theParentNodeInfo),
					canSequenceDown(theLaunchNodeInfo),
					canSequenceUp(theLaunchNodeInfo),
					thePeerNodeList.indexOf(theLaunchNodeInfo),  // launch node sequence
					theLaunchNodeChildCount ).show(); 
			return true;
		}
	};

	protected abstract PopupMenu createPopupMenu(
			GcgTreeNodeInfo aLaunchTreeNodeInfo,
			GcgTreeNodeInfo aParentTreeNodeInfo,
			View aView,
			boolean bCanDelete,
			boolean bCanMove,
			boolean bCanOrphan,
			boolean bCanSequenceUp,
			boolean bCanSequenceDown,
			int aLaunchNodeSequence,
			int aLaunchNodeChildCount );
    
    public GcgTreeViewAdapter(
    		final GcgTreeViewParent aTreeViewParent,
    		final GcgTreeViewMediator aTreeViewMediator,
            final int aChildNodeViewLayoutResourceId,
    		final int[] aFontSizeArray ) {
    	this.gcgTreeViewParent = aTreeViewParent;
    	this.treeViewMediator = aTreeViewMediator;
        this.childNodeViewLayoutResourceId = aChildNodeViewLayoutResourceId;
    	this.fontSizeArray = aFontSizeArray;
    	this.collapsedNodeButtonDrawable = null;
    	this.expandedNodeButtonDrawable = null;
    }

    protected void setRowBackground(View aView, int aBackgroundResourceId) {
    	View theTargetView;
//    	int id = aView.getId();
//		if (id == R.id.tree_node__node_summary_launch_zone) {
//			theTargetView = (View) aView.getParent().getParent();
//		} else {
			theTargetView = (View) aView.getParent();
//		}
    	theTargetView.setBackgroundResource(aBackgroundResourceId);
	}
    
	public void resetRowBackground(View aView) {
		setRowBackground(aView, R.drawable.gcg__background_state_list__tree_row);
    }

	protected boolean canSequenceDown(GcgTreeNodeInfo theTreeNodeInfo) {
    	ArrayList<GcgTreeNodeInfo> thePeerList = new ArrayList<GcgTreeNodeInfo>(this.treeViewMediator.getChildren(this.treeViewMediator.getParent(theTreeNodeInfo)));
    	return thePeerList.get(thePeerList.size() - 1) == theTreeNodeInfo ? false : true;
	}

    protected boolean canSequenceUp(GcgTreeNodeInfo theTreeNodeInfo) {
    	ArrayList<GcgTreeNodeInfo> thePeerList = new ArrayList<GcgTreeNodeInfo>(this.treeViewMediator.getChildren(this.treeViewMediator.getParent(theTreeNodeInfo)));
    	return thePeerList.get(0) == theTreeNodeInfo ? false : true;
    }
    
	protected abstract boolean canDelete(GcgTreeNodeInfo theLaunchNodeInfo, GcgTreeNodeInfo theTargetNodeInfo);

    protected abstract boolean canMove(GcgTreeNodeInfo theLaunchNodeInfo, GcgTreeNodeInfo theTargetNodeInfo);

    protected abstract boolean canOrphan(GcgTreeNodeInfo theLaunchNodeInfo, GcgTreeNodeInfo theTargetNodeInfo);

    protected GcgTreeViewMediator getTreeStateMediator() {
        return this.treeViewMediator;
    }

	protected void expandCollapse(final GcgTreeNodeInfo aTreeNodeInfo) {
		if (aTreeNodeInfo.hasChildren()) {
			if (aTreeNodeInfo.isExpanded()) {
				this.treeViewMediator.collapseChildren(aTreeNodeInfo);
			} else {
				this.treeViewMediator.expandDirectChildren(aTreeNodeInfo);
			}
		}
	}
	
	protected void saveTreeNodeStates() {
		this.savedTreeNodeInfoMap = new HashMap<String, GcgTreeNodeInfo>();
		for(GcgTreeNodeInfo theTreeNodeInfo : this.treeViewMediator.getAllVisibleTreeNodeInfoList()) {
			this.savedTreeNodeInfoMap.put(theTreeNodeInfo.getTargetObject().getIdString(), theTreeNodeInfo);
		}
	}

    private void calculateNodeIndentWidth() {
        if (this.expandedNodeButtonDrawable != null) {
        	this.indentWidth = Math.max(getIndentWidth(),
        			this.expandedNodeButtonDrawable.getIntrinsicWidth());
        }
        if (this.collapsedNodeButtonDrawable != null) {
        	this.indentWidth = Math.max(getIndentWidth(),
        			this.collapsedNodeButtonDrawable.getIntrinsicWidth());
        }
    }

    @Override
    public void registerDataSetObserver(final DataSetObserver aTreeObserver) {
    	if(this.treeViewMediator == null) {  // TODO - hack alert
    		return;
    	}
    	this.treeViewMediator.registerDataSetObserver(aTreeObserver);
    }

    @Override
    public void unregisterDataSetObserver(final DataSetObserver aTreeObserver) {
    	if(this.treeViewMediator == null) {  // TODO - hack alert
    		return;
    	}
    	this.treeViewMediator.unregisterDataSetObserver(aTreeObserver);
    }

    @Override
    public int getCount() {
    	if(this.treeViewMediator == null) {  // TODO - hack alert
    		return 0;
    	}
        return this.treeViewMediator.getVisibleCount();
    }

    @Override
    public Object getItem(final int aPosition) {
        return getItemId(aPosition);
    }

    public GcgTreeNodeInfo getVisibleTreeNodeInfo(final int aPosition) {
        return this.treeViewMediator.getVisibleTreeNodeInfoList().get(aPosition);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public int getItemViewType(final int aPosition) {
        return getVisibleTreeNodeInfo(aPosition).getLevel();
    }

    @Override
    public int getViewTypeCount() {
    	if(this.fontSizeArray == null) {  // TODO - hack alert
    		return 1;
    	}
        return this.fontSizeArray.length;
    }
    
    public int getFontSizeForLevel(int aLevel) {
    	return this.fontSizeArray[aLevel];
    }

    @Override
    public boolean isEmpty() {
        return getCount() == 0;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(final int position) {
        return true;
    }

    @Override
    public final View getView(
    		final int aPosition,
    		final View aConvertView,
            final ViewGroup aParentViewGroup ) {
    	return getTreeNodeRow(aPosition, aConvertView);
    }
    
    private static LayoutInflater getLayoutInflater() {
    	return GcgApplication.getLayoutInflater();
    }
    
    public final View getTreeNodeRow(
    		final int aPosition,
    		final View aConvertView) {
        final GcgTreeNodeInfo theTreeNodeInfo = getVisibleTreeNodeInfo(aPosition);
        if (aConvertView == null) {
            final LinearLayout theTreeNodeRowLayout = (LinearLayout) getLayoutInflater().inflate(
            		R.layout.gcg__tree_view__row_layout, null);
            return buildTreeNode(theTreeNodeRowLayout, getNewChildNodeView(theTreeNodeInfo),
                    theTreeNodeInfo, true);
        }
		final LinearLayout theLinearLayout = (LinearLayout) aConvertView;
		final FrameLayout theTreeNodeTargetObjectLayout = (FrameLayout) theLinearLayout
		        .findViewById(R.id.tree_node__target_object__container);
		final LinearLayout theChildView = (LinearLayout) theTreeNodeTargetObjectLayout.getChildAt(0);
		updateNodeView(theChildView, theTreeNodeInfo);
        setRowHeight(theLinearLayout, theTreeNodeInfo);
		return buildTreeNode(theLinearLayout, theChildView, theTreeNodeInfo, false);
    }
    
	public View getNewChildNodeView(final GcgTreeNodeInfo aTreeNodeInfo) {
        final LinearLayout theLinearLayout = (LinearLayout) 
                getLayoutInflater().inflate(this.childNodeViewLayoutResourceId, null);
        return updateNodeView(theLinearLayout, aTreeNodeInfo);
    }

    public abstract View updateNodeView(final LinearLayout aRowLayout, final GcgTreeNodeInfo aTreeNodeInfo );

    private static void setRowHeight(LinearLayout aRowLayout,GcgTreeNodeInfo aTreeNodeInfo) {
		ImageView theImageView = (ImageView) aRowLayout.findViewById(R.id.tree_node__expander_image);
        int theRowHeight = 0;
        if(aTreeNodeInfo.getDecKanGlGlyphSize() == DecKanGlDecoratedGlyphSize.MEDIUM) {
            theRowHeight = 60;
        } else {
            theRowHeight = aTreeNodeInfo.hasSecondaryHeadline() ? 55 : 45;
        }
		android.view.ViewGroup.LayoutParams theLayoutParams = theImageView.getLayoutParams();
		theLayoutParams.height = GcgHelper.getPixelsForDp(GcgApplication.getContext(), theRowHeight);
		theImageView.setLayoutParams(theLayoutParams);
	}

	protected String getDescription(final GcgTreeNodeInfo aTreeRowId) {
        final Integer[] hierarchy = getTreeStateMediator().getNodeLocationInHierarchy(aTreeRowId);
        return "Node " + aTreeRowId + Arrays.asList(hierarchy);
    }

    public final LinearLayout buildTreeNode(
    		final LinearLayout aTreeNodeRowLayout,
            final View aChildView,
            final GcgTreeNodeInfo aTreeNodeInfo,
            final boolean bAddChildView) {
    	aTreeNodeRowLayout.setTag(aTreeNodeInfo);
    	setupTreeNodeTargetObject(aTreeNodeInfo, aTreeNodeRowLayout, bAddChildView, aChildView);
    	indentTreeNodeRow(aTreeNodeInfo, aTreeNodeRowLayout);
    	setupTreeNodeExpander(aTreeNodeInfo, aTreeNodeRowLayout);
        setRowHeight(aTreeNodeRowLayout, aTreeNodeInfo);
        return aTreeNodeRowLayout;
    }

	private void indentTreeNodeRow(GcgTreeNodeInfo aTreeNodeInfo, LinearLayout aTreeNodeRowLayout) {
		LayoutParams theLayoutParams = (LayoutParams) aTreeNodeRowLayout.getChildAt(0).getLayoutParams();
		theLayoutParams.setMargins(getIndentWidth(aTreeNodeInfo), 0, 0, 0);
		aTreeNodeRowLayout.getChildAt(0).setLayoutParams(theLayoutParams);
	}

	protected abstract void setupTreeNodeTargetObject(GcgTreeNodeInfo aTreeNodeInfo, LinearLayout aTreeNodeRowLayout, boolean bAddChildView, View aChildView);

	private void setupTreeNodeExpander(GcgTreeNodeInfo aTreeNodeInfo, LinearLayout aTreeNodeRowLayout) {
        final ImageView theNodeExpanderImageView =
        		(ImageView) aTreeNodeRowLayout.findViewById(R.id.tree_node__expander_image);
        theNodeExpanderImageView.setImageDrawable(getExpandCollapseDrawable(aTreeNodeInfo));
        theNodeExpanderImageView.setScaleType(ScaleType.CENTER);
        theNodeExpanderImageView.setTag(aTreeNodeInfo);
        if (aTreeNodeInfo.hasChildren() && this.collapsible) {
            theNodeExpanderImageView.setOnClickListener(this.expandCollapseClickListener);
        } else {
            theNodeExpanderImageView.setOnClickListener(null);
        }
	}

	protected static void setRowFillerVisible(boolean bVisible, View aTreeNodeRowLayout) {
    	View theTreeNodeFillerView = aTreeNodeRowLayout.findViewById(R.id.gcg__tree_node_filler);
    	if(bVisible) {
    		theTreeNodeFillerView.setBackgroundResource(R.color.gcg__tree_view__node_filler_line);
    	} else {
    		theTreeNodeFillerView.setBackgroundResource(R.color.gcg__perspective_tree_background);
    	}
	}

	protected int calculateIndentation(final GcgTreeNodeInfo aTreeNodeInfo) {
        return getIndentWidth() * (aTreeNodeInfo.getLevel() + (this.collapsible ? 1 : 0));
    }

    protected Drawable getExpandCollapseDrawable(final GcgTreeNodeInfo aTreeNodeInfo) {
        if (!aTreeNodeInfo.hasChildren() || !this.collapsible) {
            return this.emptyDrawable;
        }
        if (aTreeNodeInfo.isExpanded()) {
            return this.expandedNodeButtonDrawable;
        }
		return this.collapsedNodeButtonDrawable;
    }

    public void setCollapsedNodeButtonDrawable(final Drawable aCollapsedNodeButtonDrawable) {
        this.collapsedNodeButtonDrawable = aCollapsedNodeButtonDrawable;
        calculateNodeIndentWidth();
    }

    public void setExpandedNodeButtonDrawable(final Drawable anExpandedNodeButtonDrawable) {
        this.expandedNodeButtonDrawable = anExpandedNodeButtonDrawable;
        calculateNodeIndentWidth();
    }

    public void setIndentWidth(final int anIndentWidth) {
        this.indentWidth = anIndentWidth;
        calculateNodeIndentWidth();
    }

    public void setNodeRowBackgroundDrawable(@SuppressWarnings("unused") final Drawable aNodeRowBackgroundDrawable) {
		// TODO
    }

    public void setCollapsible(final boolean bCollapsible) {
        this.collapsible = bCollapsible;
    }

    public void refresh() {
    	this.treeViewMediator.refreshViews();
    }

    private int getIndentWidth() {
        return this.indentWidth;
    }

    private int getIndentWidth(GcgTreeNodeInfo aTreeNodeInfo) {
        return (int) Math.round(this.indentWidth * aTreeNodeInfo.getLevel() * 1.7);
    }

    public void handleItemClick(final Object aNodeTargetObject) {
        expandCollapse((GcgTreeNodeInfo) aNodeTargetObject);
    }

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	protected ArrayList<GcgTreeNodeInfo> getPeerTreeNodeInfoList(GcgTreeNodeInfo aTreeNodeInfo) {
		ArrayList<GcgTreeNodeInfo> theGcgTreeNodeInfoList = new ArrayList<GcgTreeNodeInfo>();
		for(GcgTreeNodeInfo theTreeNodeInfo : this.treeViewMediator.getChildren(this.treeViewMediator.getParent(aTreeNodeInfo)) ) {
			theGcgTreeNodeInfoList.add(theTreeNodeInfo);
		}
		return theGcgTreeNodeInfoList;
	}

	private GcgTreeNodeInfo getNextPeerTreeNodeInfo(GcgTreeNodeInfo aTreeNodeInfo) {
		List<GcgTreeNodeInfo> thePeerList = this.treeViewMediator.getChildren(this.treeViewMediator.getParent(aTreeNodeInfo));
		return thePeerList.get(thePeerList.indexOf(aTreeNodeInfo) + 1);
	}

	protected Object getNextPeerObject(GcgTreeNodeInfo aLaunchTreeNodeInfo) {
		GcgTreeNodeInfo theNextTreeNodeInfo = getNextPeerTreeNodeInfo(aLaunchTreeNodeInfo);
		return theNextTreeNodeInfo == null ? null : theNextTreeNodeInfo.getTargetObject();
	}

	private GcgTreeNodeInfo getPreviousPeerTreeNodeInfo(GcgTreeNodeInfo aTreeNodeInfo) {
		List<GcgTreeNodeInfo> thePeerList = this.treeViewMediator.getChildren(this.treeViewMediator.getParent(aTreeNodeInfo));
		return thePeerList.get(thePeerList.indexOf(aTreeNodeInfo) - 1);
	}

	protected Object getPreviousPeerObject(GcgTreeNodeInfo aLaunchTreeNodeInfo) {
		GcgTreeNodeInfo thePreviousTreeNodeInfo = getPreviousPeerTreeNodeInfo(aLaunchTreeNodeInfo);
		return thePreviousTreeNodeInfo == null ? null : thePreviousTreeNodeInfo.getTargetObject();
	}

	protected ArrayList<GcgTreeNodeInfo> getChildTreeNodeInfoList(GcgTreeNodeInfo aTreeNodeInfo) {
		ArrayList<GcgTreeNodeInfo> theChildTreeNodeInfoList = new ArrayList<GcgTreeNodeInfo>();
		for(GcgTreeNodeInfo theTreeNodeInfo : this.treeViewMediator.getChildren(aTreeNodeInfo)) {
			theChildTreeNodeInfoList.add(theTreeNodeInfo);
		}
		return theChildTreeNodeInfoList;
	}

	/*
	 * TODO - This is a HEAVY HAMMER which is over-used during the "science project" phase of FlywheelMS
	 * Need to implement more finely targeted updates to the tree view:
	 * - cut down on database accesses
	 * - cut down on GUI/local resource utilization
	 * - decrease wall-clock latency for the end user
	 */
	public GcgTreeViewAdapter rebuildTreeView() {
		int theFirstPosition = this.gcgTreeViewParent.getFirstPosition();
		saveTreeNodeStates();
		GcgTreeViewAdapter theTreeViewAdapter = this.gcgTreeViewParent.rebuildTreeView();
		this.gcgTreeViewParent.guiPreferencesApply();
		this.gcgTreeViewParent.setSelection(theFirstPosition);
		theTreeViewAdapter.getTreeStateMediator().restoreTreeNodeStates(this.savedTreeNodeInfoMap);
		return theTreeViewAdapter;
	}

    protected GcgActivity getGcgActivity() {
        return this.gcgTreeViewParent.getGcgActivity();
    }

//	protected abstract void sequenceDown(GcgTreeNodeInfo aLaunchTreeNodeInfo);
//
//	protected abstract void sequenceUp(GcgTreeNodeInfo aLaunchTreeNodeInfo);
//
//	protected abstract void sequenceFirst(GcgTreeNodeInfo aLaunchTreeNodeInfo);
//
//	protected abstract void sequenceLast(GcgTreeNodeInfo aLaunchTreeNodeInfo);
//

	protected abstract void launchDefaultNodeEditorActivity(GcgTreeNodeInfo aTreeNodeInfo);

	public boolean verifyNodeOrder(String aFirstOccurrenceTag, String aSecondOccurrenceTag) {
		return this.treeViewMediator.verifyNodeOrder(aFirstOccurrenceTag, aSecondOccurrenceTag);
	}

	public int getPosition(GcgTreeNodeInfo aFirstVisibleTreeNodeInfo) {
		return 0;
	}

}
