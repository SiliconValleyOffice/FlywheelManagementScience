/* @(#)FwbContextWorkBreakdownPerspective.java
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

package com.flywheelms.library.fwb.view_flipper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;

import com.flywheelms.library.R;
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.context.FmmPerspective;
import com.flywheelms.library.fmm.node.impl.governable.Portfolio;
import com.flywheelms.library.fmm.node.impl.governable.Project;
import com.flywheelms.library.fmm.node.impl.governable.ProjectAsset;
import com.flywheelms.library.fms.helper.FmsHelpIndex;
import com.flywheelms.library.fms.popup_menu.FmmPopupBuilder;
import com.flywheelms.library.fms.preferences.GuiPreferencesBundle;
import com.flywheelms.library.fms.tree_view_flipper.tree_view.FmsPerspectiveFlipperTreeView;
import com.flywheelms.library.fms.treeview.FmsTreeViewMediatorMemoryResident;
import com.flywheelms.library.fms.treeview.filter.FmsTreeViewAdapter;
import com.flywheelms.library.fms.treeview.filter.StrategicPlanningTreeFilter;
import com.flywheelms.library.fwb.treeview.treebuilder.TreeBuilderStrategicPlanning;
import com.flywheelms.library.gcg.interfaces.GcgPerspective;
import com.flywheelms.library.gcg.treeview.GcgTreeViewAdapter;
import com.flywheelms.library.gcg.treeview.GcgTreeViewMediator;
import com.flywheelms.library.gcg.treeview.node.GcgTreeNodeInfo;

import java.util.Collection;

public class FwbContextWorkBreakdownPerspective extends FmsPerspectiveFlipperTreeView {
	
	@Override
	public GcgPerspective getGcgPerspective() {
		return FmmPerspective.WORK_BREAKDOWN;
	}

	public FwbContextWorkBreakdownPerspective(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void guiPreferencesApply() {
		// TODO - get rid of this over ride when class is fully implemented - SDS
//        getTreeViewAdapter().applyShowCollapseToTreeLevel(getShowCollapseToTreeLevel());
//        getTreeStateMediator().refreshViews();
	}

	@Override
	protected int getPageTitleResourceId() {
		return R.string.fmm_perspective__work_breakdown;
	}

	@Override
	protected String getHelpContextUrlString() {
		return FmsHelpIndex.PERSPECTIVE__CONTEXT__WORK_BREAKDOWN;
	}

    @Override
    protected int getRightMenuHeadingArrayResourceId() {
        return R.array.context__work_breakdown__right_menu__heading_array;
    }

    @Override
    protected int getRightMenuLayoutResourceId() {
        return R.layout.context__work_breakdown__right_menu;
    }

	@Override
	protected GcgTreeViewMediator createGcgTreeViewMediator() {
        GcgTreeViewMediator theTreeContentMediator =
                new FmsTreeViewMediatorMemoryResident(new StrategicPlanningTreeFilter(this));
        final TreeBuilderStrategicPlanning theTreeBuilder = new TreeBuilderStrategicPlanning(theTreeContentMediator);
        Collection<Portfolio> thePortfolioCollection = FmmDatabaseMediator.getActiveMediator().getPortfolioList(
                FmmDatabaseMediator.getActiveMediator().getFmmOwner() );
        for(Portfolio thePortfolio : thePortfolioCollection) {
            Collection<Project> theProjectCollection =
                    FmmDatabaseMediator.getActiveMediator().getProjectList(thePortfolio);
            GcgTreeNodeInfo thePortfolioTreeNodeInfo = theTreeBuilder.addTopNode(
                    thePortfolio, theProjectCollection.size()>0, FmmPerspective.WORK_BREAKDOWN );
            for(Project theProject : theProjectCollection) {
                Collection<ProjectAsset> theProjectAssetCollection =
                        FmmDatabaseMediator.getActiveMediator().listProjectAsset(theProject);
                GcgTreeNodeInfo theProjectTreeNodeInfo = theTreeBuilder.addChildNode(
                        theProject, theProjectAssetCollection.size()>0, thePortfolioTreeNodeInfo, FmmPerspective.WORK_BREAKDOWN);
                for(ProjectAsset theProjectAsset : theProjectAssetCollection) {
                    theTreeBuilder.addLeafNode(
                            theProjectAsset, theProjectTreeNodeInfo, FmmPerspective.WORK_BREAKDOWN);
                }
            }
        }
        return theTreeContentMediator;
	}

	@Override
	protected PopupMenu getTreeViewBackgroundPopupMenu(View aView, final GcgTreeViewAdapter aTreeViewAdapter) {
        PopupMenu thePopupMenu = new PopupMenu(getContext(), aView);
        // TODO - conditional for API 19 and above
//		PopupMenu thePopupMenu = new PopupMenu(getContext(), aView, Gravity.CENTER | Gravity,LEFT);  // target API 19
        thePopupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem aMenuItem) {
                if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__CREATE_PORTFOLIO)) {
                    ((FmsTreeViewAdapter) aTreeViewAdapter).createPortfolio();
                }
                return true;
            }
        });
        thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__CREATE_PORTFOLIO);
        return thePopupMenu;
	}

    @Override
    public String getPreferencesBundleNameShowMenu() {
        return GuiPreferencesBundle.FWB__CONTEXT__WORK_BREAKDOWN__SHOW.getKey();
    }

    @Override
    public String getPreferencesBundleNameGovernanceMenu() {
        return GuiPreferencesBundle.FWB__CONTEXT__WORK_BREAKDOWN__GOVERNANCE.getKey();
    }

    @Override
    public String getPreferencesBundleNameWorkStatusMenu() {
        return GuiPreferencesBundle.FWB__CONTEXT__WORK_BREAKDOWN__WORK_STATUS.getKey();
    }

    @Override
    public String getPreferencesBundleNameTeamMenu() {
        return GuiPreferencesBundle.FWB__CONTEXT__WORK_BREAKDOWN__TEAM.getKey();
    }

    protected void initWorkStatusMenu() {
        return;
    }

}