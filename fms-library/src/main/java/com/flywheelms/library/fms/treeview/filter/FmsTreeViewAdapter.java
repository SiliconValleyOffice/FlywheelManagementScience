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

import com.flywheelms.gcongui.gcg.GcgApplication;
import com.flywheelms.gcongui.gcg.interfaces.GcgPerspective;
import com.flywheelms.gcongui.gcg.treeview.GcgTreeViewAdapter;
import com.flywheelms.gcongui.gcg.treeview.GcgTreeViewMediator;
import com.flywheelms.gcongui.gcg.treeview.node.GcgTreeNodeInfo;
import com.flywheelms.library.R;
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.context.FmmFrame;
import com.flywheelms.library.fmm.context.FmmPerspective;
import com.flywheelms.library.fmm.node.FmmHeadlineNodeShallow;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.governable.FiscalYear;
import com.flywheelms.library.fmm.node.impl.governable.Portfolio;
import com.flywheelms.library.fmm.node.impl.governable.Project;
import com.flywheelms.library.fmm.node.impl.governable.ProjectAsset;
import com.flywheelms.library.fmm.node.impl.governable.StrategicAsset;
import com.flywheelms.library.fmm.node.impl.governable.WorkPackage;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmCompletionNode;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;
import com.flywheelms.library.fms.dialog.FiscalYearCreateDialog;
import com.flywheelms.library.fms.dialog.FiscalYearDeleteDialog;
import com.flywheelms.library.fms.dialog.HeadlineNodeCreateDialog;
import com.flywheelms.library.fms.dialog.HeadlineNodeHeadlineEditDialog;
import com.flywheelms.library.fms.dialog.PortfolioAdoptOrphanProjectDialog;
import com.flywheelms.library.fms.dialog.PortfolioCreateDialog;
import com.flywheelms.library.fms.dialog.PortfolioDeleteDialog;
import com.flywheelms.library.fms.dialog.ProjectAdoptOrphanProjectAssetDialog;
import com.flywheelms.library.fms.dialog.ProjectAdoptOrphanStrategicAssetDialog;
import com.flywheelms.library.fms.dialog.ProjectAssetAdoptOrphanWorkPackageDialog;
import com.flywheelms.library.fms.dialog.ProjectAssetDeleteDialog;
import com.flywheelms.library.fms.dialog.ProjectAssetMoveDialog;
import com.flywheelms.library.fms.dialog.ProjectAssetOrphanDialog;
import com.flywheelms.library.fms.dialog.ProjectDeleteDialog;
import com.flywheelms.library.fms.dialog.ProjectMoveDialog;
import com.flywheelms.library.fms.dialog.ProjectOrphanDialog;
import com.flywheelms.library.fms.dialog.StrategicAssetDeleteDialog;
import com.flywheelms.library.fms.dialog.StrategicAssetMoveDialog;
import com.flywheelms.library.fms.dialog.StrategicAssetOrphanDialog;
import com.flywheelms.library.fms.dialog.StrategicMilestoneAdoptOrphanProjectAssetDialog;
import com.flywheelms.library.fms.dialog.StrategicMilestoneDeleteDialog;
import com.flywheelms.library.fms.dialog.StrategicMilestoneMoveDialog;
import com.flywheelms.library.fms.dialog.StrategicMilestoneTargetDateEditDialog;
import com.flywheelms.library.fms.dialog.WorkPackageAdoptOrphanWorkTaskDialog;
import com.flywheelms.library.fms.dialog.WorkPackageDeleteDialog;
import com.flywheelms.library.fms.dialog.WorkPackageMoveIntoWorkAssetDialog;
import com.flywheelms.library.fms.dialog.WorkPackageOrphanDialog;
import com.flywheelms.library.fms.helper.FmsActivityHelper;
import com.flywheelms.library.fms.popup_menu.FmmHeadlineNodePopupListener;
import com.flywheelms.library.fms.popup_menu.FmmPopupBuilder;

import java.util.ArrayList;

public class FmsTreeViewAdapter extends GcgTreeViewAdapter implements FmmHeadlineNodePopupListener {

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
		super(aTreeViewParent, aTreeStateMediator, R.layout.fms__tree_view__row_target_object, aFontSizeArray);
	}

	public FmsTreeViewParent getFmsTreeViewParent() {
		return (FmsTreeViewParent) this.gcgTreeViewParent;
	}

    protected void setRowBackground(View aView, int aBackgroundResourceId) {
        View theTargetView;
        int id = aView.getId();
        if (id == R.id.tree_node__node_summary_launch_zone) {
            theTargetView = (View) aView.getParent().getParent();
        } else {
            theTargetView = (View) aView.getParent();
        }
        theTargetView.setBackgroundResource(aBackgroundResourceId);
    }

	@Override
	protected void setupTreeNodeTargetObject(GcgTreeNodeInfo aTreeNodeInfo, LinearLayout aTreeNodeRowLayout, boolean bAddChildView, View aChildView) {
        final FrameLayout theTreeNodeTargetObjectLayout = (FrameLayout) aTreeNodeRowLayout
                .findViewById(R.id.tree_node__target_object__container);
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
		return getFmsTreeViewParent().isShowNodeQuality() && aTreeNodeInfo.hasNodeQuality() &&
				(aTreeNodeInfo.getLevel() + 1 >= getFmsTreeViewParent().getShowNodeQualityLevel());
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
	protected boolean canMove(GcgTreeNodeInfo theLaunchNodeInfo, GcgTreeNodeInfo theParentNodeInfo) {
    	FmmHeadlineNode theLaunchHeadlineNode = (FmmHeadlineNode)theLaunchNodeInfo.getTargetObject();
    	FmmHeadlineNode theParentHeadlineNode = (FmmHeadlineNode)theParentNodeInfo.getTargetObject();
    	return theParentHeadlineNode.canMove(theLaunchHeadlineNode);
    }

    @Override
	protected boolean canOrphan(GcgTreeNodeInfo theLaunchNodeInfo, GcgTreeNodeInfo theParentNodeInfo) {
    	FmmHeadlineNode theLaunchHeadlineNode = (FmmHeadlineNode)theLaunchNodeInfo.getTargetObject();
    	FmmHeadlineNode theParentHeadlineNode = (FmmHeadlineNode)theParentNodeInfo.getTargetObject();
    	return theParentHeadlineNode.canOrphan(theLaunchHeadlineNode);
    }

	@Override
	public void editTreeNode(GcgTreeNodeInfo aTreeNodeInfo) {
		launchNodeEditorToStoryActivity(aTreeNodeInfo);
	}
	
	protected void launchNodeDecKanGlDialog(GcgTreeNodeInfo aTreeNodeInfo) {
		getFmsTreeViewParent().launchNodeDecKanGlNavigationDialog(aTreeNodeInfo);
	}
	
	protected void launchNodeQualityDialog(GcgTreeNodeInfo aTreeNodeInfo) {
        getFmsTreeViewParent().launchNodeQualityDialog(aTreeNodeInfo);
	}

	protected void launchNodeEditorToDecKanGlActivity(GcgTreeNodeInfo aTreeNodeInfo) {
        getFmsTreeViewParent().launchNodeEditorActivity(aTreeNodeInfo, FmmFrame.TRIBKN, FmmPerspective.DECKANGL);
	}

	protected void launchNodeEditorToStoryActivity(GcgTreeNodeInfo aTreeNodeInfo) {
        getFmsTreeViewParent().launchNodeEditorActivity(aTreeNodeInfo, FmmFrame.FSE, FmmPerspective.STORY);
	}

	protected void launchNodeEditorToChildrenActivity(GcgTreeNodeInfo aTreeNodeInfo) {
        getFmsTreeViewParent().launchNodeEditorActivity(aTreeNodeInfo, FmmFrame.CONTEXT_FOR_NODE, FmmPerspective.WORK_BREAKDOWN);
	}

	protected void launchNodeEditorToQualityActivity(GcgTreeNodeInfo aTreeNodeInfo) {
        getFmsTreeViewParent().launchNodeEditorActivity(aTreeNodeInfo, FmmFrame.TRIBKN, FmmPerspective.DECKANGL);
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
			int aLaunchNodeSequence,
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
                aLaunchNodeSequence,  // launch node sequence
				aLaunchNodeChildCount );
	}
    
	@Override
	public void onPopupMenuItemClick(
			View aView,
			MenuItem aMenuItem,
            FmmHeadlineNode aParentHeadlineNode,
            GcgTreeNodeInfo aLaunchTreeNodeInfo,
            FmmHeadlineNode aLaunchHeadlineNode,
            int aLaunchNodeSequence,
			int aLaunchNodeChildCount ) {
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
					aLaunchNodeChildCount );
        } else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__EDIT_STRATEGIC_MILESTONES)) {
            editFmmHeadlineNodeChildren(aLaunchTreeNodeInfo, FmmNodeDefinition.STRATEGIC_MILESTONE);
		} else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__DELETE_STRATEGIC_MILESTONE)) {
            deleteStrategicMilestone(aLaunchHeadlineNode);
        } else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__EDIT_STRATEGIC_MILESTONE_TARGET_DATE)) {
            editStrategicMilestoneTargetDate(aLaunchHeadlineNode);
        } else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__CREATE_ALL_FLYWHEEL_CADENCES)) {
            createAllFlywheelCadences(aLaunchHeadlineNode);
        } else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__EDIT_FLYWHEEL_CADENCES)) {
            editFmmHeadlineNodeChildren(aLaunchTreeNodeInfo, FmmNodeDefinition.FLYWHEEL_CADENCE);
        } else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__EDIT_WORK_PLANS)) {
            editFmmHeadlineNodeChildren(aLaunchTreeNodeInfo, FmmNodeDefinition.WORK_PLAN);
        } else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__CREATE_PORTFOLIO)) {
            createPortfolio();
        } else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__DELETE_PORTFOLIO)) {
            deletePortfolio(aLaunchHeadlineNode);
        } else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__ADOPT_ORPHAN_PROJECT)) {
            adoptOrphanProject(aLaunchHeadlineNode);
        } else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__CREATE_PROJECT)) {
            createFmmHeadlineNode(
                    FmmNodeDefinition.PROJECT,
                    aLaunchHeadlineNode,
                    aParentHeadlineNode,
                    aLaunchNodeSequence,
                    aLaunchNodeChildCount);
        } else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__EDIT_PROJECTS)) {
            editFmmHeadlineNodeChildren(aLaunchTreeNodeInfo, FmmNodeDefinition.PROJECT);
        } else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__DELETE_PROJECT)) {
            deleteProject(aLaunchHeadlineNode);
        } else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__ORPHAN_PROJECT)) {
            orphanProject(aLaunchHeadlineNode, aParentHeadlineNode);
        } else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__MOVE_PROJECT)) {
            moveProject(aLaunchHeadlineNode, aParentHeadlineNode);


        } else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__CREATE_STRATEGIC_ASSET)) {
            createFmmHeadlineNode(
                    FmmNodeDefinition.STRATEGIC_ASSET,
                    aLaunchHeadlineNode,
                    aParentHeadlineNode,
                    aLaunchNodeSequence,
                    aLaunchNodeChildCount);
        } else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__DELETE_STRATEGIC_ASSET)) {
            deleteStrategicAsset(aLaunchHeadlineNode);
        } else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__EDIT_STRATEGIC_ASSETS)) {
            editFmmHeadlineNodeChildren(aLaunchTreeNodeInfo, FmmNodeDefinition.STRATEGIC_ASSET);
        } else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__ADOPT_ORPHAN_STRATEGIC_ASSET)) {
            adoptOrphanStrategicAsset(aLaunchHeadlineNode);
        } else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__PROMOTE_PROJECT_ASSET_TO_STRATEGIC_ASSET)) {
            promoteProjectAssetToStrategicAsset(aLaunchHeadlineNode);
        } else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__MOVE_STRATEGIC_ASSET)) {
            moveStrategicAsset(aLaunchHeadlineNode, aParentHeadlineNode);
        } else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__ORPHAN_STRATEGIC_ASSET)) {
            orphanStrategicAsset(aLaunchHeadlineNode, aParentHeadlineNode);



		} else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__CREATE_PROJECT_ASSET)) {
			createFmmHeadlineNode(
                    FmmNodeDefinition.PROJECT_ASSET,
                    aLaunchHeadlineNode,
                    aParentHeadlineNode,
                    aLaunchNodeSequence,
                    aLaunchNodeChildCount);
        } else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__EDIT_ALL_ASSETS)) {
            editFmmHeadlineNodeChildren(aLaunchTreeNodeInfo, FmmNodeDefinition.WORK_ASSET);
		} else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__DELETE_PROJECT_ASSET)) {
			deleteProjectAsset(aLaunchHeadlineNode);
		} else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__ORPHAN_PROJECT_ASSET)) {
			orphanProjectAsset(aLaunchHeadlineNode, aParentHeadlineNode);
		} else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__ADOPT_ORPHAN_PROJECT_ASSET)) {
            adoptOrphanProjectAsset(aParentHeadlineNode, aLaunchNodeChildCount, aLaunchHeadlineNode, aLaunchNodeSequence);
        } else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__MOVE_PROJECT_ASSET)) {
            moveProjectAsset(aLaunchHeadlineNode, aParentHeadlineNode);
		} else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__CREATE_WORK_PACKAGE)) {
			createFmmHeadlineNode(
                    FmmNodeDefinition.WORK_PACKAGE,
                    aLaunchHeadlineNode,
                    aParentHeadlineNode,
                    aLaunchNodeSequence,
                    aLaunchNodeChildCount);
        } else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__EDIT_WORK_PACKAGES)) {
            editFmmHeadlineNodeChildren(aLaunchTreeNodeInfo, FmmNodeDefinition.WORK_PACKAGE);
		} else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__DELETE_WORK_PACKAGE)) {
			deleteWorkPackage(aLaunchHeadlineNode);
        } else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__ORPHAN_WORK_PACKAGE)) {
            orphanWorkPackage(aLaunchHeadlineNode, aParentHeadlineNode);
		} else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__ADOPT_ORPHAN_WORK_PACKAGE)) {
			adoptOrphanWorkPackage(aLaunchHeadlineNode);
        } else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__MOVE_WORK_PACKAGE)) {
            moveWorkPackage(aLaunchHeadlineNode, aParentHeadlineNode, aLaunchTreeNodeInfo.getGcgPerspective());
        } else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__CREATE_WORK_TASK)) {
            createFmmHeadlineNode(
                    FmmNodeDefinition.WORK_TASK,
                    aLaunchHeadlineNode,
                    aParentHeadlineNode,
                    aLaunchNodeSequence,
                    aLaunchNodeChildCount);
        } else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__EDIT_WORK_TASKS)) {
            editFmmHeadlineNodeChildren(aLaunchTreeNodeInfo, FmmNodeDefinition.WORK_TASK);
        } else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__DELETE_WORK_TASK)) {
            deleteWorkTask(aLaunchHeadlineNode);
        } else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__ORPHAN_WORK_TASK)) {
            orphanWorkTask(aLaunchHeadlineNode, aParentHeadlineNode);
        } else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__ADOPT_ORPHAN_WORK_TASK)) {
            adoptOrphanWorkTask(aLaunchHeadlineNode);
        } else if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__MOVE_WORK_TASK)) {
            moveWorkTask(aLaunchHeadlineNode, aParentHeadlineNode);
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
			moveStrategicMilestone(aLaunchHeadlineNode, aParentHeadlineNode);
		} else {  // we are FOO BAR !!!
			
		}
	}

    public FmmPerspective getFmsPerspective() {
        return (FmmPerspective) getGcgPerspective();
    }

    protected void sequenceDown(GcgTreeNodeInfo aLaunchTreeNodeInfo, FmmHeadlineNode aHeadlineNode, FmmHeadlineNode aParentHeadlineNode) {
		getGcgActivity().startGreenActivityStatusAnimation();
		FmmDatabaseMediator.getActiveMediator().swapSequence(aHeadlineNode, (FmmHeadlineNode) getNextPeerObject(aLaunchTreeNodeInfo), aParentHeadlineNode);
		rebuildTreeView();
		getGcgActivity().stopActivityStatusAnimation();
	}

	protected void sequenceUp(GcgTreeNodeInfo aLaunchTreeNodeInfo, FmmHeadlineNode aHeadlineNode, FmmHeadlineNode aParentHeadlineNode) {
		getGcgActivity().startGreenActivityStatusAnimation();
		FmmDatabaseMediator.getActiveMediator().swapSequence(aHeadlineNode, (FmmHeadlineNode) getPreviousPeerObject(aLaunchTreeNodeInfo), aParentHeadlineNode);
		rebuildTreeView();
		getGcgActivity().stopActivityStatusAnimation();
	}

	protected void sequenceFirst(GcgTreeNodeInfo aLaunchTreeNodeInfo, FmmHeadlineNode aHeadlineNode, FmmHeadlineNode aParentHeadlineNode) {
		getGcgActivity().startGreenActivityStatusAnimation();
		FmmDatabaseMediator.getActiveMediator().sequenceFirst(aHeadlineNode, aParentHeadlineNode);
		rebuildTreeView();
		getGcgActivity().stopActivityStatusAnimation();
	}

	protected void sequenceLast(GcgTreeNodeInfo aLaunchTreeNodeInfo, FmmHeadlineNode aHeadlineNode, FmmHeadlineNode aParentHeadlineNode) {
		getGcgActivity().startGreenActivityStatusAnimation();
		FmmDatabaseMediator.getActiveMediator().sequenceLast(aHeadlineNode, aParentHeadlineNode);
		rebuildTreeView();
		getGcgActivity().stopActivityStatusAnimation();
	}

	public void createFiscalYear() {
		this.getGcgActivity().startDialog(new FiscalYearCreateDialog(getGcgActivity(), this));
	}

	private void deleteFiscalYear(FmmHeadlineNode aFiscalYearHeadlineNode) {
		getGcgActivity().startDialog(new FiscalYearDeleteDialog(getGcgActivity(), this, aFiscalYearHeadlineNode));
	}

    public void createPortfolio() {
        this.getGcgActivity().startDialog(new PortfolioCreateDialog(getGcgActivity(), this));
    }

    private void deletePortfolio(FmmHeadlineNode aPortfolioHeadlineNode) {
        getGcgActivity().startDialog(new PortfolioDeleteDialog(getGcgActivity(), this, aPortfolioHeadlineNode));
    }

    private void createAllFlywheelCadences(FmmHeadlineNode aLaunchHeadlineNode) {
        FmsActivityHelper.startCreateAllFlywheelCadenceForYearWizard(getGcgActivity(), aLaunchHeadlineNode.getNodeIdString());
    }

    public static boolean isPeerLaunch(FmmNodeDefinition aTargetNodeDefinition, FmmNodeDefinition aLaunchNodeDefinition) {
        boolean isPeerLaunch = aTargetNodeDefinition == aLaunchNodeDefinition;
        if(! isPeerLaunch) {
            if(aTargetNodeDefinition == FmmNodeDefinition.PROJECT_ASSET && aLaunchNodeDefinition == FmmNodeDefinition.STRATEGIC_ASSET) {
                isPeerLaunch = true;
            }
        }
        return isPeerLaunch;
    }

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

	private void deleteStrategicMilestone(FmmHeadlineNode aStrategicMilestoneHeadlineNode) {
		getGcgActivity().startDialog(new StrategicMilestoneDeleteDialog(getGcgActivity(), this, aStrategicMilestoneHeadlineNode));
	}

	private void editStrategicMilestoneTargetDate(FmmHeadlineNode aStrategicMilestoneHeadlineNode) {
		getGcgActivity().startDialog(new StrategicMilestoneTargetDateEditDialog(getGcgActivity(), this, aStrategicMilestoneHeadlineNode));
	}

    private void orphanProject(FmmHeadlineNode aProjectHeadlineNode, FmmHeadlineNode aPortfolioHeadlineNode) {
        getGcgActivity().startDialog(new ProjectOrphanDialog(getGcgActivity(), this, (Project) aProjectHeadlineNode, (Portfolio) aPortfolioHeadlineNode));
    }

    private void moveProject(FmmHeadlineNode aProject, FmmHeadlineNode aPortfolioException) {
        getGcgActivity().startDialog(new ProjectMoveDialog(getGcgActivity(), this, (Project) aProject, (Portfolio) aPortfolioException));
    }

    private void adoptOrphanProject(FmmHeadlineNode aPortfolioNode ) {
        getGcgActivity().startDialog(new PortfolioAdoptOrphanProjectDialog(
                getGcgActivity(),
                this,
                (FmmCompletionNode) aPortfolioNode ));
    }

    private void deleteProject(FmmHeadlineNode aProjectNode) {
        getGcgActivity().startDialog(new ProjectDeleteDialog(getGcgActivity(), this, aProjectNode));
    }

	private void deleteProjectAsset(FmmHeadlineNode aProjectAssetHeadlineNode) {
		getGcgActivity().startDialog(new ProjectAssetDeleteDialog(getGcgActivity(), this, aProjectAssetHeadlineNode));
	}

	private void orphanProjectAsset(FmmHeadlineNode aProjectAssetHeadlineNode, FmmHeadlineNode aParentHeadlineNode) {
            getGcgActivity().startDialog(new ProjectAssetOrphanDialog(getGcgActivity(), this, (ProjectAsset) aProjectAssetHeadlineNode, aParentHeadlineNode));
	}

    private void orphanStrategicAsset(FmmHeadlineNode aStrategicAssetHeadlineNode, FmmHeadlineNode aProjectHeadlineNode) {
        getGcgActivity().startDialog(new StrategicAssetOrphanDialog(getGcgActivity(), this, (StrategicAsset) aStrategicAssetHeadlineNode, aProjectHeadlineNode));
    }

	private void adoptOrphanProjectAsset(FmmHeadlineNode aParentHeadlineNode, int aParentNodeChildCount, FmmHeadlineNode aLaunchNode, int aLaunchNodeSequence) {
        getGcgActivity().startDialog(new ProjectAdoptOrphanProjectAssetDialog(
                getGcgActivity(),
                this,
                FmmNodeDefinition.PROJECT_ASSET,
                aParentHeadlineNode,
                FmsTreeViewAdapter.isPeerLaunch(FmmNodeDefinition.PROJECT_ASSET, aLaunchNode.getFmmNodeDefinition()) ? 0 : aParentNodeChildCount,
                aLaunchNode,
                aLaunchNodeSequence ));
	}

    private void adoptOrphanStrategicAsset(FmmHeadlineNode aParentHeadlineNode) {
            getGcgActivity().startDialog(new ProjectAdoptOrphanStrategicAssetDialog(getGcgActivity(), this, aParentHeadlineNode));
    }

    private void promoteProjectAssetToStrategicAsset(FmmHeadlineNode aParentHeadlineNode) {
        getGcgActivity().startDialog(new StrategicMilestoneAdoptOrphanProjectAssetDialog(getGcgActivity(), this, aParentHeadlineNode));
    }

	private void deleteWorkPackage(FmmHeadlineNode aWorkPackageNode) {
        getGcgActivity().startDialog(new WorkPackageDeleteDialog(getGcgActivity(), this, aWorkPackageNode));
	}

	private void adoptOrphanWorkPackage(FmmHeadlineNode aProjectAssetHeadlineNode) {
		getGcgActivity().startDialog(new ProjectAssetAdoptOrphanWorkPackageDialog(getGcgActivity(), this, aProjectAssetHeadlineNode));
	}

    private void moveWorkPackage(FmmHeadlineNode aLaunchHeadlineNode, FmmHeadlineNode aParentHeadlineNode, GcgPerspective aGcgPerspective) {
        if(aGcgPerspective == FmmPerspective.WORK_BREAKDOWN) {
            getGcgActivity().startDialog(new WorkPackageMoveIntoWorkAssetDialog(getGcgActivity(), this, (WorkPackage) aLaunchHeadlineNode, aParentHeadlineNode));
        }
    }

    private void orphanWorkPackage(FmmHeadlineNode aWorkPackageHeadlineNode, FmmHeadlineNode aParentHeadlineNode) {
        getGcgActivity().startDialog(new WorkPackageOrphanDialog(getGcgActivity(), this, (WorkPackage) aWorkPackageHeadlineNode, aParentHeadlineNode));
    }

    private void adoptOrphanWorkTask(FmmHeadlineNode aWorkPackageHeadlineNode) {
        getGcgActivity().startDialog(new WorkPackageAdoptOrphanWorkTaskDialog(getGcgActivity(), this, aWorkPackageHeadlineNode));
    }

    private void deleteWorkTask(@SuppressWarnings("unused") FmmHeadlineNode aFiscalYearHeadlineNode) {
        // TODO
    }

    private void moveWorkTask(FmmHeadlineNode aLaunchHeadlineNode, FmmHeadlineNode aParentHeadlineNode) {

    }

    private void orphanWorkTask(FmmHeadlineNode aLaunchHeadlineNode, FmmHeadlineNode aParentHeadlineNode) {

    }
    
	private void moveStrategicMilestone(FmmHeadlineNode aHeadlineNode, FmmHeadlineNode aTargetHeadlineNodeException) {
		getGcgActivity().startDialog(new StrategicMilestoneMoveDialog(getGcgActivity(), this, aHeadlineNode, (FiscalYear) aTargetHeadlineNodeException));
	}

	private void moveProjectAsset(FmmHeadlineNode aHeadlineNode, FmmHeadlineNode aTargetHeadlineNodeException) {
		getGcgActivity().startDialog(new ProjectAssetMoveDialog(getGcgActivity(), this, (ProjectAsset) aHeadlineNode, aTargetHeadlineNodeException));
	}

    private void moveStrategicAsset(FmmHeadlineNode aHeadlineNode, FmmHeadlineNode aTargetHeadlineNodeException) {
        getGcgActivity().startDialog(new StrategicAssetMoveDialog(getGcgActivity(), this, (StrategicAsset) aHeadlineNode, aTargetHeadlineNodeException));
    }
	
	private void editHeadline(FmmHeadlineNode aLaunchHeadlineNode) {
		this.getGcgActivity().startDialog(new HeadlineNodeHeadlineEditDialog(getGcgActivity(), this, aLaunchHeadlineNode));
	}

//    protected ArrayList<FmmHeadlineNodeShallow> getPeerHeadlineNodeShallowList(GcgTreeNodeInfo aTreeNodeInfo) {
//        ArrayList<FmmHeadlineNodeShallow> thePeerHeadlineNodeShallowList = new ArrayList<FmmHeadlineNodeShallow>();
//        for(GcgTreeNodeInfo theTreeNodeInfo : this.treeViewMediator.getChildren(this.treeViewMediator.getParent(aTreeNodeInfo)) ) {
//            thePeerHeadlineNodeShallowList.add(new FmmHeadlineNodeShallow(theTreeNodeInfo));
//        }
//        return thePeerHeadlineNodeShallowList;
//    }

    protected ArrayList<FmmHeadlineNodeShallow> getChildHeadlineNodeShallowList(GcgTreeNodeInfo aTreeNodeInfo) {
        ArrayList<FmmHeadlineNodeShallow> theChildHeadlineNodeShallowList = new ArrayList<FmmHeadlineNodeShallow>();
        for(GcgTreeNodeInfo theTreeNodeInfo : this.treeViewMediator.getChildren(aTreeNodeInfo)) {
            theChildHeadlineNodeShallowList.add(new FmmHeadlineNodeShallow(theTreeNodeInfo));
        }
        return theChildHeadlineNodeShallowList;
    }

    public FmsTreeViewAdapter addNewHeadlineNode(@SuppressWarnings("unused") FmmHeadlineNode aHeadlineNode) {
        // TODO - may be able to optimize repaint using aFiscalYear argument
        return (FmsTreeViewAdapter) rebuildTreeView();
    }

    public FmsTreeViewAdapter updateHeadlineNodeHeadline(FmmHeadlineNode aHeadlineNode) {
        // TODO - may be able to optimize repaint using aFiscalYear argument
        return (FmsTreeViewAdapter) rebuildTreeView();
    }

    public GcgTreeNodeInfo getTreeNodeInfoForObject(Object anObject) {
        return null;
    }

    public void deleteHeadlineNode(@SuppressWarnings("unused") FmmHeadlineNode aHeadlineNode) {
        // TODO - may be able to optimize repaint using aHeadlineNode argument
        rebuildTreeView();
    }

    public FmsTreeViewAdapter updateSecondaryHeadline(String aSecondaryHeadline) {
        // TODO - heavy hammer.  Optimize.
        return (FmsTreeViewAdapter) rebuildTreeView();
    }

    public void editTreeNodeObject(Object anObject) {
		this.treeViewMediator = this.gcgTreeViewParent.getGcgTreeViewMediator();
		GcgTreeNodeInfo theTreeNodeInfo = this.treeViewMediator.getTreeNodeInfoForObject(anObject);
		if(theTreeNodeInfo != null) {
			editTreeNode(theTreeNodeInfo);
		}
	}

    public void editFmmHeadlineNode(FmmHeadlineNode aHeadlineNode, FmmHeadlineNode aParentHeadlineNode) {
        this.treeViewMediator = this.gcgTreeViewParent.getGcgTreeViewMediator();
        GcgTreeNodeInfo theTreeNodeInfo = this.treeViewMediator.getTreeNodeInfoForObject(aHeadlineNode);
        if(theTreeNodeInfo != null) {
            editTreeNode(theTreeNodeInfo);
        } else {  // this is a "hidden" leaf node from a node summary
            getFmsTreeViewParent().updatePerspectiveContext(this.treeViewMediator.getTreeNodeInfoForObjectId(aParentHeadlineNode.getNodeIdString()), false);
            getFmsTreeViewParent().startEditorActivityForFmmHeadlineNode(
                    aHeadlineNode.getFmmNodeDefinition(),
                    aHeadlineNode.getPeerHeadlineNodeShallowList(aParentHeadlineNode),
                    aParentHeadlineNode.getNodeIdString(),
                    aHeadlineNode.getNodeIdString());
        }
    }

    public void editFmmHeadlineNodeChildren(GcgTreeNodeInfo aLaunchTreeNodeInfo, FmmNodeDefinition aChildNodeDefinition) {
        getFmsTreeViewParent().updatePerspectiveContext(aLaunchTreeNodeInfo, false);
        // TODO - optimization - this can be optimized by passing in the shallow child list when not a leaf node
        FmsActivityHelper.startHeadlineNodeEditorActivityForChildren(getGcgActivity(), (FmmHeadlineNode) aLaunchTreeNodeInfo.getTargetObject(), aChildNodeDefinition);
    }

    protected int getLaunchNodeChildCount(GcgTreeNodeInfo theLaunchNodeInfo) {
        int theCount = getChildTreeNodeInfoList(theLaunchNodeInfo).size();
        return theCount == 0 && theLaunchNodeInfo.isLeafNode() ?
                ((FmmHeadlineNode) theLaunchNodeInfo.getTargetObject()).getChildNodeCount(theLaunchNodeInfo.getGcgPerspective()) :
                theCount;
    }



    private void deleteStrategicAsset(FmmHeadlineNode aStrategicAssetHeadlineNode) {
        getGcgActivity().startDialog(new StrategicAssetDeleteDialog(getGcgActivity(), this, aStrategicAssetHeadlineNode));
    }

}
