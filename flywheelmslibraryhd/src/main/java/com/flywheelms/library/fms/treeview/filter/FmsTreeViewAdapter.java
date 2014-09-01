/* @(#)FmsTreeViewAdapter.java
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

package com.flywheelms.library.fms.treeview.filter;

import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.flywheelms.library.R;
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.context.FmmFrame;
import com.flywheelms.library.fmm.context.FmmPerspective;
import com.flywheelms.library.fmm.node.FmmHeadlineNodeShallow;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.governable.FiscalYear;
import com.flywheelms.library.fmm.node.impl.governable.ProjectAsset;
import com.flywheelms.library.fmm.node.impl.governable.StrategicMilestone;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;
import com.flywheelms.library.fms.dialog.FiscalYearCreateDialog;
import com.flywheelms.library.fms.dialog.FiscalYearDeleteDialog;
import com.flywheelms.library.fms.dialog.HeadlineNodeCreateDialog;
import com.flywheelms.library.fms.dialog.HeadlineNodeHeadlineEditDialog;
import com.flywheelms.library.fms.dialog.PortfolioCreateDialog;
import com.flywheelms.library.fms.dialog.PortfolioDeleteDialog;
import com.flywheelms.library.fms.dialog.ProjectAssetAdoptOrphanWorkPackageDialog;
import com.flywheelms.library.fms.dialog.ProjectAssetDeleteDialog;
import com.flywheelms.library.fms.dialog.ProjectAssetMoveDialog;
import com.flywheelms.library.fms.dialog.ProjectAssetOrphanDialog;
import com.flywheelms.library.fms.dialog.ProjectDeleteDialog;
import com.flywheelms.library.fms.dialog.StrategicMilestoneAdoptOrphanProjectAssetDialog;
import com.flywheelms.library.fms.dialog.StrategicMilestoneDeleteDialog;
import com.flywheelms.library.fms.dialog.StrategicMilestoneMoveDialog;
import com.flywheelms.library.fms.dialog.StrategicMilestoneTargetDateEditDialog;
import com.flywheelms.library.fms.popup_menu.FmmPopupBuilder;
import com.flywheelms.library.gcg.GcgApplication;
import com.flywheelms.library.gcg.treeview.GcgTreeViewAdapter;
import com.flywheelms.library.gcg.treeview.GcgTreeViewMediator;
import com.flywheelms.library.gcg.treeview.node.GcgTreeNodeInfo;

import java.util.ArrayList;

public class FmsTreeViewAdapter extends GcgTreeViewAdapter {

    private final OnClickListener launchNodeDecKanGlDialogListener = new OnClickListener() {
		
		@Override
		public void onClick(View aView) {
			final GcgTreeNodeInfo theTreeNodeInfo = (GcgTreeNodeInfo) aView.getTag();
			launchNodeDecKanGlDialog(theTreeNodeInfo);
		}
	};
    private final OnClickListener launchNodeEditorToStoryActivityListener = new OnClickListener() {
		
		@Override
		public void onClick(View aView) {
			final GcgTreeNodeInfo theTreeNodeInfo = (GcgTreeNodeInfo) aView.getTag();
			launchNodeEditorToStoryActivity(theTreeNodeInfo);
		}
	};
    private final OnClickListener launchNodeEditorToChildrenActivityListener = new OnClickListener() {
		
		@Override
		public void onClick(View aView) {
			final GcgTreeNodeInfo theTreeNodeInfo = (GcgTreeNodeInfo) aView.getTag();
			launchNodeEditorToChildrenActivity(theTreeNodeInfo);
		}
	};
    private final OnClickListener launchNodeQualityDialogListener = new OnClickListener() {
		
		@Override
		public void onClick(View aView) {
			final GcgTreeNodeInfo theTreeNodeInfo = (GcgTreeNodeInfo) aView.getTag();
			launchNodeQualityDialog(theTreeNodeInfo);
		}
	};

	public FmsTreeViewAdapter(FmsTreeViewParent aTreeViewParent, GcgTreeViewMediator aTreeStateMediator, int[] aFontSizeArray) {
		super(aTreeViewParent, aTreeStateMediator, aFontSizeArray);
	}

	protected FmsTreeViewParent getFmsTreeViewParent() {
		return (FmsTreeViewParent) this.gcgTreeViewParent;
	}

	@Override
	protected void setupTreeNodeTargetObject(GcgTreeNodeInfo aTreeNodeInfo, LinearLayout aTreeNodeRowLayout, boolean bAddChildView, View aChildView) {
        final FrameLayout theTreeNodeTargetObjectLayout = (FrameLayout) aTreeNodeRowLayout
                .findViewById(R.id.tree_node__target_object_layout);
        if (bAddChildView) {
            theTreeNodeTargetObjectLayout.addView(aChildView);
        }
        final ImageView theDecKanGlLaunchZone = (ImageView) theTreeNodeTargetObjectLayout.findViewById(R.id.tree_node__deckangl_bitmap);
        theDecKanGlLaunchZone.setOnClickListener(this.launchNodeDecKanGlDialogListener);
        theDecKanGlLaunchZone.setOnLongClickListener(this.launchPopupMenuListener);
        theDecKanGlLaunchZone.setTag(aTreeNodeInfo);
        final LinearLayout theActivityLaunchZone = (LinearLayout) theTreeNodeTargetObjectLayout.findViewById(R.id.tree_node__edit_activity_launch_zone);
        theActivityLaunchZone.setOnClickListener(this.launchNodeEditorToStoryActivityListener);
        theActivityLaunchZone.setOnLongClickListener(this.launchPopupMenuListener);
        theActivityLaunchZone.setTag(aTreeNodeInfo);
        final LinearLayout theSummaryLaunchZone = (LinearLayout) theTreeNodeTargetObjectLayout.findViewById(R.id.tree_node__node_summary_launch_zone);
        theSummaryLaunchZone.setOnClickListener(this.launchNodeEditorToChildrenActivityListener);
        theSummaryLaunchZone.setOnLongClickListener(this.launchPopupMenuListener);
        theSummaryLaunchZone.setTag(aTreeNodeInfo);
        final LinearLayout theQualityLaunchZone = (LinearLayout) theTreeNodeTargetObjectLayout.findViewById(R.id.tree_node__quality_activity_launch_zone);
        theQualityLaunchZone.setOnClickListener(this.launchNodeQualityDialogListener);
        theQualityLaunchZone.setOnLongClickListener(this.launchPopupMenuListener);
        theQualityLaunchZone.setTag(aTreeNodeInfo);
	}

	@Override
	public View updateNodeView(final LinearLayout aRowLayout, final GcgTreeNodeInfo aTreeNodeInfo ) {
		final ImageView theImageView = (ImageView)aRowLayout.findViewById(R.id.tree_node__deckangl_bitmap);
		aTreeNodeInfo.setDecKanGlGlyphSize(this.gcgTreeViewParent.getShowEmphasisLevel());
		theImageView.setImageBitmap(aTreeNodeInfo.getDecKanGlBitmap());
		android.view.ViewGroup.LayoutParams theLayoutParams = theImageView.getLayoutParams();
		theLayoutParams.width = aTreeNodeInfo.getDecKanGlBitmap().getWidth() + 10;
		theImageView.setLayoutParams(theLayoutParams);
        final TextView theTreeNodeHeadlineView =
        	(TextView) aRowLayout.findViewById(R.id.tree_node_headline);
        theTreeNodeHeadlineView.setText(aTreeNodeInfo.getTargetObject().getHeadline());
        final TextView theTreeNodeSecondaryHeadlineView =
                (TextView) aRowLayout.findViewById(R.id.tree_node_secondary_headline);
        if(aTreeNodeInfo.getTargetObject().hasSecondaryHeadline()) {
            theTreeNodeSecondaryHeadlineView.setVisibility(View.VISIBLE);
            theTreeNodeSecondaryHeadlineView.setText(aTreeNodeInfo.getTargetObject().getSecondaryHeadline());
        } else {
            theTreeNodeSecondaryHeadlineView.setVisibility(View.GONE);
        }
        buildNodeSummary(aTreeNodeInfo, aRowLayout);
        buildNodeQuality(aTreeNodeInfo, aRowLayout);
        if(showNodeQuality(aTreeNodeInfo) || aTreeNodeInfo.hasChildren()) {
            setRowFillerVisible(true, aRowLayout);
        } else {
        	setRowFillerVisible(false, aRowLayout);
        }
		return aRowLayout;
	}

	private boolean showNodeSummary(final GcgTreeNodeInfo aTreeNodeInfo) {
		return this.gcgTreeViewParent.isShowNodeChildSummary() && aTreeNodeInfo.hasNodeSummary() &&
				( aTreeNodeInfo.getLevel() + 1 >= this.gcgTreeViewParent.getShowNodeChildSummaryLevel() );
	}

	private void buildNodeSummary(
			final GcgTreeNodeInfo aTreeNodeInfo,
			final View theParentView ) {
        final LinearLayout theSummaryLaunchZone = (LinearLayout) theParentView.findViewById(R.id.tree_node__node_summary_launch_zone);
        final TextView theNodeSummaryPrefixView =
        		(TextView) theParentView.findViewById(R.id.tree_node_summary_prefix);
        final ImageView theNodeSummaryBitmapView =
        		(ImageView)theParentView.findViewById(R.id.tree_node_summary_glyph);
        final TextView theNodeSummarySuffixView =
        		(TextView) theParentView.findViewById(R.id.tree_node_summary_suffix);
		if(showNodeSummary(aTreeNodeInfo)) {
			theSummaryLaunchZone.setVisibility(View.VISIBLE);
        	theNodeSummaryPrefixView.setText(aTreeNodeInfo.getNodeSummaryPrefix());
        	theNodeSummaryBitmapView.setImageResource(aTreeNodeInfo.getNodeSummaryDrawableResourceId());
        	theNodeSummarySuffixView.setText(aTreeNodeInfo.getNodeSummarySuffix());
        } else {
			theSummaryLaunchZone.setVisibility(View.GONE);
        }
	}

	private boolean showNodeQuality(final GcgTreeNodeInfo aTreeNodeInfo) {
		return this.gcgTreeViewParent.isShowNodeQuality() && aTreeNodeInfo.hasNodeQuality() &&
				(aTreeNodeInfo.getLevel() + 1 >= this.gcgTreeViewParent.getShowNodeQualityLevel());
	}

	private void buildNodeQuality(
			final GcgTreeNodeInfo aTreeNodeInfo,
			final View aParentView ) {
		final ImageView theNodeQualityIndexImageView =
				(ImageView) aParentView.findViewById(R.id.tree_node__quality_index__glyph);
		final TextView theNodeQualityIndexTextView =
				(TextView) aParentView.findViewById(R.id.tree_node__quality_index__data);
		final LinearLayout theNodeQualityLaunchZone =
				(LinearLayout) aParentView.findViewById(R.id.tree_node__quality_activity_launch_zone);
		if(showNodeQuality(aTreeNodeInfo)) {
			theNodeQualityLaunchZone.setVisibility(View.VISIBLE);
			int theNodeQualityIndex = aTreeNodeInfo.getTargetObject().getNodeQualityIndex();
			theNodeQualityIndexTextView.setTextColor(GcgApplication.getInstance().getResources().getColor(R.color.gcg__widget_data__text_color));
			if(theNodeQualityIndex < 0) {
				theNodeQualityIndexImageView.setImageResource(R.drawable.deckangl__noun_quality__bad);
			} else {
				theNodeQualityIndexImageView.setImageResource(R.drawable.deckangl__noun_quality__good);
			}
			theNodeQualityIndexTextView.setText(Integer.toString(aTreeNodeInfo.getTargetObject().getNodeQualityIndex()));
		} else {
			theNodeQualityLaunchZone.setVisibility(View.GONE);
        }
	}
    
    public void applyShowNodeCompletion() {
    	this.treeViewMediator.applyTreeFilterCriteria();
    }
    
    public void applyShowCollapseToTreeLevel(int aTreeLevel) {
    	this.treeViewMediator.collapseToTreeLevel(aTreeLevel);
    }

	@Override
	protected ArrayList<FmmHeadlineNodeShallow> getPeerHeadlineNodeShallowList(GcgTreeNodeInfo aTreeNodeInfo) {
		ArrayList<FmmHeadlineNodeShallow> thePeerHeadlineNodeShallowList = new ArrayList<FmmHeadlineNodeShallow>();
		for(GcgTreeNodeInfo theTreeNodeInfo : this.treeViewMediator.getChildren(this.treeViewMediator.getParent(aTreeNodeInfo)) ) {
			thePeerHeadlineNodeShallowList.add(new FmmHeadlineNodeShallow(theTreeNodeInfo));
		}
		return thePeerHeadlineNodeShallowList;
	}
    
	@Override
	protected boolean canDelete(GcgTreeNodeInfo theLaunchNodeInfo, GcgTreeNodeInfo theTargetNodeInfo) {
    	FmmHeadlineNode theLaunchHeadlineNode = (FmmHeadlineNode)theLaunchNodeInfo.getTargetObject();
    	FmmHeadlineNode theTargetHeadlineNode = (FmmHeadlineNode)theTargetNodeInfo.getTargetObject();
    	return theTargetHeadlineNode.canDelete(theLaunchHeadlineNode);
    }

    @Override
	protected boolean canMove(GcgTreeNodeInfo theLaunchNodeInfo, GcgTreeNodeInfo theTargetNodeInfo) {
    	FmmHeadlineNode theLaunchHeadlineNode = (FmmHeadlineNode)theLaunchNodeInfo.getTargetObject();
    	FmmHeadlineNode theTargetHeadlineNode = (FmmHeadlineNode)theTargetNodeInfo.getTargetObject();
    	return theTargetHeadlineNode.canMove(theLaunchHeadlineNode);
    }

    @Override
	protected boolean canOrphan(GcgTreeNodeInfo theLaunchNodeInfo, GcgTreeNodeInfo theTargetNodeInfo) {
    	FmmHeadlineNode theLaunchHeadlineNode = (FmmHeadlineNode)theLaunchNodeInfo.getTargetObject();
    	FmmHeadlineNode theTargetHeadlineNode = (FmmHeadlineNode)theTargetNodeInfo.getTargetObject();
    	return theTargetHeadlineNode.canOrphan(theLaunchHeadlineNode);
    }

	@Override
	protected void launchDefaultNodeEditorActivity(GcgTreeNodeInfo aTreeNodeInfo) {
		launchNodeEditorToStoryActivity(aTreeNodeInfo);
	}
	
	protected void launchNodeDecKanGlDialog(GcgTreeNodeInfo aTreeNodeInfo) {
		this.gcgTreeViewParent.launchNodeDecKanGlNavigationDialog(aTreeNodeInfo);
	}
	
	protected void launchNodeQualityDialog(GcgTreeNodeInfo aTreeNodeInfo) {
		this.gcgTreeViewParent.launchNodeQualityDialog(aTreeNodeInfo);
	}

	protected void launchNodeEditorToDecKanGlActivity(GcgTreeNodeInfo aTreeNodeInfo) {
		this.gcgTreeViewParent.launchNodeEditorActivity(aTreeNodeInfo, FmmFrame.TRIBKN, FmmPerspective.DECKANGL);
	}

	protected void launchNodeEditorToStoryActivity(GcgTreeNodeInfo aTreeNodeInfo) {
		this.gcgTreeViewParent.launchNodeEditorActivity(aTreeNodeInfo, FmmFrame.FSE, FmmPerspective.STORY);
	}

	protected void launchNodeEditorToChildrenActivity(GcgTreeNodeInfo aTreeNodeInfo) {
		this.gcgTreeViewParent.launchNodeEditorActivity(aTreeNodeInfo, FmmFrame.CONTEXT_NODE, FmmPerspective.WORK_BREAKDOWN);
	}

	protected void launchNodeEditorToQualityActivity(GcgTreeNodeInfo aTreeNodeInfo) {
		this.gcgTreeViewParent.launchNodeEditorActivity(aTreeNodeInfo, FmmFrame.TRIBKN, FmmPerspective.DECKANGL);
	}

	@Override
	protected PopupMenu createPopupMenu(
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
		return FmmPopupBuilder.createPopupMenu(
				this,
				aLaunchTreeNodeInfo,
				aParentTreeNodeInfo,
				aView,
				bCanDelete,
				bCanMove,
				bCanOrphan,
				bCanSequenceUp,
				bCanSequenceDown,
				aLauncNodeSequence,  // launch node sequence
				aLaunchNodeChildCount );
	}
    
	@Override
	public void onPopupMenuItemClick(
			View aView,
			MenuItem aMenuItem,
			FmmHeadlineNode aLaunchHeadlineNode,
			FmmHeadlineNode aParentHeadlineNode,
			GcgTreeNodeInfo aLaunchTreeNodeInfo,
			int aLaunchNodeSequence,
			int aLaunchNodeCount ) {
		setRowBackground(aView, R.drawable.gcg__background_state_list__tree_row);
		if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__CREATE_FISCAL_YEAR)) {
			createFiscalYear();
		} else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__DELETE_FISCAL_YEAR)) {
			deleteFiscalYear(aLaunchHeadlineNode);
		} else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__CREATE_STRATEGIC_MILESTONE)) {
			createFmmHeadlineNode(
					FmmNodeDefinition.STRATEGIC_MILESTONE,
					aLaunchHeadlineNode,
					aParentHeadlineNode,
					aLaunchNodeSequence,
					aLaunchNodeCount );
		} else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__DELETE_STRATEGIC_MILESTONE)) {
			deleteStrategicMilestone(aLaunchHeadlineNode);
        } else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__EDIT_STRATEGIC_MILESTONE_TARGET_DATE)) {
            editStrategicMilestoneTargetDate(aLaunchHeadlineNode);
        } else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__CREATE_PORTFOLIO)) {
            createPortfolio();
        } else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__DELETE_PORTFOLIO)) {
            deletePortfolio(aLaunchHeadlineNode);
        } else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__CREATE_PROJECT)) {
            createFmmHeadlineNode(
                    FmmNodeDefinition.PROJECT,
                    aLaunchHeadlineNode,
                    aParentHeadlineNode,
                    aLaunchNodeSequence,
                    aLaunchNodeCount);
        } else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__DELETE_PROJECT)) {
            deleteProject(aLaunchHeadlineNode);
		} else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__CREATE_PROJECT_ASSET)) {
			createFmmHeadlineNode(
                    FmmNodeDefinition.PROJECT_ASSET,
                    aLaunchHeadlineNode,
                    aParentHeadlineNode,
                    aLaunchNodeSequence,
                    aLaunchNodeCount);
		} else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__DELETE_PROJECT_ASSET)) {
			deleteProjectAsset(aLaunchHeadlineNode);
		} else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__ORPHAN_PROJECT_ASSET)) {
			orphanProjectAsset(aLaunchHeadlineNode, aParentHeadlineNode);
		} else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__ADOPT_ORPHAN_PROJECT_ASSET)) {
			adoptOrphanProjectAsset(aLaunchHeadlineNode);
		} else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__CREATE_WORK_PACKAGE)) {
			createFmmHeadlineNode(
                    FmmNodeDefinition.WORK_PACKAGE,
                    aLaunchHeadlineNode,
                    aParentHeadlineNode,
                    aLaunchNodeSequence,
                    aLaunchNodeCount);
		} else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__DELETE_WORK_PACKAGE)) {
			deleteWorkPackage(aLaunchHeadlineNode);
		} else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__ADOPT_ORPHAN_WORK_PACKAGE)) {
			adoptOrphanWorkPackage(aLaunchHeadlineNode);
        } else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__CREATE_WORK_TASK)) {
            createFmmHeadlineNode(
                    FmmNodeDefinition.WORK_TASK,
                    aLaunchHeadlineNode,
                    aParentHeadlineNode,
                    aLaunchNodeSequence,
                    aLaunchNodeCount);
        } else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__DELETE_WORK_TASK)) {
            deleteWorkTask(aLaunchHeadlineNode);
        } else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__ADOPT_ORPHAN_WORK_TASK)) {
            adoptOrphanWorkTask(aLaunchHeadlineNode);
		} else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__SEQUENCE_DOWN)) {
			sequenceDown(aLaunchTreeNodeInfo, aLaunchHeadlineNode, aParentHeadlineNode);
		} else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__SEQUENCE_UP)) {
			sequenceUp(aLaunchTreeNodeInfo, aLaunchHeadlineNode, aParentHeadlineNode);
		} else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__SEQUENCE_FIRST)) {
			sequenceFirst(aLaunchTreeNodeInfo, aLaunchHeadlineNode, aParentHeadlineNode);
		} else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__SEQUENCE_LAST)) {
			sequenceLast(aLaunchTreeNodeInfo, aLaunchHeadlineNode, aParentHeadlineNode);
		} else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__EDIT_HEADLINE)) {
			editHeadline(aLaunchHeadlineNode);
		} else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__MOVE_STRATEGIC_MILESTONE)) {
			moveToFiscalYear(aLaunchHeadlineNode, aParentHeadlineNode);
		} else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__MOVE_PROJECT_ASSET)) {
			moveToStrategicMilestone(aLaunchHeadlineNode, aParentHeadlineNode);
		} else {  // we are FOO BAR !!!
			
		}
	}

	// logical validation of this operation was already done in FmmPopupBuilder
	@Override
	protected void sequenceDown(GcgTreeNodeInfo aLaunchTreeNodeInfo, FmmHeadlineNode aHeadlineNode, FmmHeadlineNode aParentHeadlineNode) {
		getGcgActivity().startGreenActivityStatusAnimation();
		FmmDatabaseMediator.getActiveMediator().swapSequence(aHeadlineNode, (FmmHeadlineNode) getNextPeerObject(aLaunchTreeNodeInfo), aParentHeadlineNode);
		rebuildTreeView();
		getGcgActivity().stopActivityStatusAnimation();
	}

	// logical validation of this operation was already done in FmmPopupBuilder
	@Override
	protected void sequenceUp(GcgTreeNodeInfo aLaunchTreeNodeInfo, FmmHeadlineNode aHeadlineNode, FmmHeadlineNode aParentHeadlineNode) {
		getGcgActivity().startGreenActivityStatusAnimation();
		FmmDatabaseMediator.getActiveMediator().swapSequence(aHeadlineNode, (FmmHeadlineNode) getPreviousPeerObject(aLaunchTreeNodeInfo), aParentHeadlineNode);
		rebuildTreeView();
		getGcgActivity().stopActivityStatusAnimation();
	}

	// logical validation of this operation was already done in FmmPopupBuilder
	@Override
	protected void sequenceFirst(GcgTreeNodeInfo aLaunchTreeNodeInfo, FmmHeadlineNode aHeadlineNode, FmmHeadlineNode aParentHeadlineNode) {
		getGcgActivity().startGreenActivityStatusAnimation();
		FmmDatabaseMediator.getActiveMediator().sequenceFirst(aHeadlineNode, aParentHeadlineNode);
		rebuildTreeView();
		getGcgActivity().stopActivityStatusAnimation();
	}

	// logical validation of this operation was already done in FmmPopupBuilder
	@Override
	protected void sequenceLast(GcgTreeNodeInfo aLaunchTreeNodeInfo, FmmHeadlineNode aHeadlineNode, FmmHeadlineNode aParentHeadlineNode) {
		getGcgActivity().startGreenActivityStatusAnimation();
		FmmDatabaseMediator.getActiveMediator().sequenceLast(aHeadlineNode, aParentHeadlineNode);
		rebuildTreeView();
		getGcgActivity().stopActivityStatusAnimation();
	}

	public void createFiscalYear() {
		this.getGcgActivity().startDialog(new FiscalYearCreateDialog(getGcgActivity(), this));
	}

	// logical validation of this operation was already done in FmmPopupBuilder
	private void deleteFiscalYear(FmmHeadlineNode aFiscalYearHeadlineNode) {
		getGcgActivity().startDialog(new FiscalYearDeleteDialog(getGcgActivity(), this, aFiscalYearHeadlineNode));
	}

    public void createPortfolio() {
        this.getGcgActivity().startDialog(new PortfolioCreateDialog(getGcgActivity(), this));
    }

    // logical validation of this operation was already done in FmmPopupBuilder
    private void deletePortfolio(FmmHeadlineNode aPortfolioHeadlineNode) {
        getGcgActivity().startDialog(new PortfolioDeleteDialog(getGcgActivity(), this, aPortfolioHeadlineNode));
    }

	// logical validation of this operation was already done in FmmPopupBuilder
	private void createFmmHeadlineNode(
			FmmNodeDefinition anFmmNodeDefinition,
			FmmHeadlineNode aLaunchHeadlineNode,
			FmmHeadlineNode aParentHeadlineNode,
			int aLaunchNodeSequence,
			int aLaunchNodeChildNodeCount ) {
		this.getGcgActivity().startDialog(new HeadlineNodeCreateDialog(
				getGcgActivity(),
				this,
				anFmmNodeDefinition,
				aLaunchHeadlineNode,
				aParentHeadlineNode,
				aLaunchNodeSequence,
				aLaunchNodeChildNodeCount ));
	}

	// logical validation of this operation was already done in FmmPopupBuilder
	private void deleteStrategicMilestone(FmmHeadlineNode aStrategicMilestoneHeadlineNode) {
		getGcgActivity().startDialog(new StrategicMilestoneDeleteDialog(getGcgActivity(), this, aStrategicMilestoneHeadlineNode));
	}

	// logical validation of this operation was already done in FmmPopupBuilder
	private void editStrategicMilestoneTargetDate(FmmHeadlineNode aStrategicMilestoneHeadlineNode) {
		getGcgActivity().startDialog(new StrategicMilestoneTargetDateEditDialog(getGcgActivity(), this, aStrategicMilestoneHeadlineNode));
	}

    // logical validation of this operation was already done in FmmPopupBuilder
    private void deleteProject(FmmHeadlineNode aProjectNode) {
        getGcgActivity().startDialog(new ProjectDeleteDialog(getGcgActivity(), this, aProjectNode));
    }

	// logical validation of this operation was already done in FmmPopupBuilder
	private void deleteProjectAsset(FmmHeadlineNode aProjectAssetHeadlineNode) {
		getGcgActivity().startDialog(new ProjectAssetDeleteDialog(getGcgActivity(), this, aProjectAssetHeadlineNode));
	}

	// logical validation of this operation was already done in FmmPopupBuilder
	private void orphanProjectAsset(FmmHeadlineNode aProjectAssetHeadlineNode, FmmHeadlineNode aStrategicMilestonetHeadlineNode) {
		getGcgActivity().startDialog(new ProjectAssetOrphanDialog(getGcgActivity(), this, (ProjectAsset) aProjectAssetHeadlineNode, (StrategicMilestone) aStrategicMilestonetHeadlineNode));
	}

	// TODO !!! push down into subclass for StrategicPlanningTreeViewAdapter
	// logical validation of this operation was already done in FmmPopupBuilder
	private void adoptOrphanProjectAsset(FmmHeadlineNode aStrategicMilestonetHeadlineNode) {
		getGcgActivity().startDialog(new StrategicMilestoneAdoptOrphanProjectAssetDialog(getGcgActivity(), this, aStrategicMilestonetHeadlineNode));
	}

	// logical validation of this operation was already done in FmmPopupBuilder
	private void deleteWorkPackage(@SuppressWarnings("unused") FmmHeadlineNode aFiscalYearHeadlineNode) {
		// TODO
	}

	// TODO !!! push down into subclass for StrategicPlanningTreeViewAdapter
	// logical validation of this operation was already done in FmmPopupBuilder
	private void adoptOrphanWorkPackage(FmmHeadlineNode aProjectAssetHeadlineNode) {
		getGcgActivity().startDialog(new ProjectAssetAdoptOrphanWorkPackageDialog(getGcgActivity(), this, aProjectAssetHeadlineNode));
	}




    // logical validation of this operation was already done in FmmPopupBuilder
    private void deleteWorkTask(@SuppressWarnings("unused") FmmHeadlineNode aFiscalYearHeadlineNode) {
        // TODO
    }

    // TODO !!! push down into subclass for StrategicPlanningTreeViewAdapter
    // logical validation of this operation was already done in FmmPopupBuilder
    private void adoptOrphanWorkTask(FmmHeadlineNode aProjectAssetHeadlineNode) {
    }
    
    
    

	// logical validation of this operation was already done in FmmPopupBuilder
	private void moveToFiscalYear(FmmHeadlineNode aHeadlineNode, FmmHeadlineNode aTargetHeadlineNodeException) {
		getGcgActivity().startDialog(new StrategicMilestoneMoveDialog(getGcgActivity(), this, aHeadlineNode, (FiscalYear) aTargetHeadlineNodeException));
	}

	// logical validation of this operation was already done in FmmPopupBuilder
	private void moveToStrategicMilestone(FmmHeadlineNode aHeadlineNode, FmmHeadlineNode aTargetHeadlineNodeException) {
		getGcgActivity().startDialog(new ProjectAssetMoveDialog(getGcgActivity(), this, (ProjectAsset) aHeadlineNode, (StrategicMilestone) aTargetHeadlineNodeException));
	}
	
	private void editHeadline(FmmHeadlineNode aLaunchHeadlineNode) {
		this.getGcgActivity().startDialog(new HeadlineNodeHeadlineEditDialog(getGcgActivity(), this, aLaunchHeadlineNode));
	}

}
