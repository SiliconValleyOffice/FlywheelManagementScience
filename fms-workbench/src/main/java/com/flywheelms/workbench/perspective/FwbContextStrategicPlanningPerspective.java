/* @(#)FwbContextStrategicPlanningPerspective.java
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

package com.flywheelms.workbench.perspective;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;

import com.flywheelms.gcongui.gcg.interfaces.GcgPerspective;
import com.flywheelms.gcongui.gcg.treeview.GcgTreeViewAdapter;
import com.flywheelms.gcongui.gcg.treeview.GcgTreeViewMediator;
import com.flywheelms.gcongui.gcg.treeview.node.GcgTreeNodeInfo;
import com.flywheelms.library.R;
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.context.FmmPerspective;
import com.flywheelms.library.fmm.node.impl.governable.FiscalYear;
import com.flywheelms.library.fmm.node.impl.governable.ProjectAsset;
import com.flywheelms.library.fmm.node.impl.governable.StrategicMilestone;
import com.flywheelms.library.fms.helper.FmsHelpIndex;
import com.flywheelms.library.fms.popup_menu.FmmPopupBuilder;
import com.flywheelms.library.fms.preferences.GuiPreferencesBundle;
import com.flywheelms.library.fms.tree_view_flipper.tree_view.FmsPerspectiveFlipperTreeView;
import com.flywheelms.library.fms.treeview.FmsTreeViewMediatorMemoryResident;
import com.flywheelms.library.fms.treeview.filter.FmsTreeViewAdapter;
import com.flywheelms.library.fms.treeview.filter.StrategicPlanningTreeFilter;
import com.flywheelms.library.fwb.treeview.treebuilder.FmsTreeBuilder;

import java.util.Collection;

public class FwbContextStrategicPlanningPerspective extends FmsPerspectiveFlipperTreeView {

	@Override
	public GcgPerspective getGcgPerspective() {
		return FmmPerspective.STRATEGIC_PLANNING;
	}

	public FwbContextStrategicPlanningPerspective(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
	}

	@Override
	protected int getPageTitleResourceId() {
		return R.string.fmm_perspective__strategic_planning;
	}

	@Override
	protected String getHelpContextUrlString() {
		return FmsHelpIndex.PERSPECTIVE__CONTEXT__STRATEGIC_PLANNING;
	}

    @Override
    protected int getRightMenuHeadingArrayResourceId() {
        return R.array.context__strategic_planning__right_menu__heading_array;
    }

    @Override
    protected int getRightMenuLayoutResourceId() {
        return R.layout.context__strategic_planning__right_menu;
    }

    @Override
    protected int[] getRightMenuBodyResourceIdArray() {
        return new int[]{
                R.id.show_menu__body,
                R.id.governance__menu_body,
                R.id.work_status__menu_body,
                R.id.team__menu_body};
    }

	@Override
	protected GcgTreeViewMediator createGcgTreeViewMediator() {
		GcgTreeViewMediator theTreeContentMediator =
				new FmsTreeViewMediatorMemoryResident(new StrategicPlanningTreeFilter(this));
		final FmsTreeBuilder theTreeBuilder = new FmsTreeBuilder(theTreeContentMediator);
		Collection<FiscalYear> theFiscalYearCollection = FmmDatabaseMediator.getActiveMediator().getFiscalYearList(
				FmmDatabaseMediator.getActiveMediator().getFmmOwner() );
		for(FiscalYear theFiscalYear : theFiscalYearCollection) {
			Collection<StrategicMilestone> theStrategicMilestoneCollection =
					FmmDatabaseMediator.getActiveMediator().getStrategicMilestoneList(theFiscalYear);
			GcgTreeNodeInfo theFiscalYearTreeNodeInfo = theTreeBuilder.addTopNode(
					theFiscalYear, theStrategicMilestoneCollection.size()>0, FmmPerspective.STRATEGIC_PLANNING );
			for(StrategicMilestone theStrategicMilestone : theStrategicMilestoneCollection) {
				Collection<ProjectAsset> theProjectAssetCollection =
						FmmDatabaseMediator.getActiveMediator().listProjectAsset(theStrategicMilestone);
				GcgTreeNodeInfo theStrategicMilestoneTreeNodeInfo = theTreeBuilder.addChildNode(
						theStrategicMilestone, theProjectAssetCollection.size()>0, theFiscalYearTreeNodeInfo, FmmPerspective.STRATEGIC_PLANNING);
				for(ProjectAsset theProjectAsset : theProjectAssetCollection) {
					theTreeBuilder.addLeafNode(
							theProjectAsset, theStrategicMilestoneTreeNodeInfo, FmmPerspective.STRATEGIC_PLANNING);
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
				if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__CREATE_FISCAL_YEAR)) {
					((FmsTreeViewAdapter) aTreeViewAdapter).createFiscalYear();
				}
				return true;
			}
		});
		thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__CREATE_FISCAL_YEAR);
		return thePopupMenu;
	}

    @Override
    public boolean startButtonEnabled() {
        return true;
    }

    @Override
    protected int getStartButtonBackgroundResourceId() {
        return R.drawable.fms__button_state_list__start__fiscal_year;
    }

    @Override
    protected void initializeStartButtonListener() {
        this.startButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View aView) {
                ((FmsTreeViewAdapter) FwbContextStrategicPlanningPerspective.this.getGcgTreeViewAdapter()).createFiscalYear();
            }
        });
    }

	@Override
	public String getPreferencesBundleNameShowMenu() {
		return GuiPreferencesBundle.FWB__CONTEXT__STRATEGIC_PLANNING__SHOW.getKey();
	}

	@Override
	public String getPreferencesBundleNameGovernanceMenu() {
		return GuiPreferencesBundle.FWB__CONTEXT__STRATEGIC_PLANNING__GOVERNANCE.getKey();
	}

	@Override
	public String getPreferencesBundleNameWorkStatusMenu() {
		return GuiPreferencesBundle.FWB__CONTEXT__STRATEGIC_PLANNING__WORK_STATUS.getKey();
	}

	@Override
	public String getPreferencesBundleNameTeamMenu() {
		return GuiPreferencesBundle.FWB__CONTEXT__STRATEGIC_PLANNING__TEAM.getKey();
	}

}