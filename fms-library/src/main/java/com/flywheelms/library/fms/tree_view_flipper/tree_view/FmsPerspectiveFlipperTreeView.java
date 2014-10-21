/* @(#)FmsPerspectiveFlipperTreeView.java
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

package com.flywheelms.library.fms.tree_view_flipper.tree_view;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

import com.flywheelms.gcongui.gcg.button.GcgIncrementalButton;
import com.flywheelms.gcongui.gcg.dialog.GcgGuiPreferencesDialog;
import com.flywheelms.gcongui.gcg.interfaces.GcgGuiable;
import com.flywheelms.gcongui.gcg.menu.GcgSpinnableMenu;
import com.flywheelms.gcongui.gcg.preferences.GcgPreferencesHelper;
import com.flywheelms.gcongui.gcg.treeview.GcgTreeViewAdapter;
import com.flywheelms.gcongui.gcg.treeview.node.GcgTreeNodeInfo;
import com.flywheelms.gcongui.gcg.viewflipper.GcgPerspectiveFlipperTreeView;
import com.flywheelms.library.R;
import com.flywheelms.library.fmm.context.FmmFrame;
import com.flywheelms.library.fmm.context.FmmPerspective;
import com.flywheelms.library.fmm.node.FmmHeadlineNodeShallow;
import com.flywheelms.library.fmm.node.impl.enumerator.CompletableWorkStatus;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.governable.CommunityMember;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmNode;
import com.flywheelms.library.fms.dialog.DecKanGlNavigationDialog;
import com.flywheelms.library.fms.helper.FmsActivityHelper;
import com.flywheelms.library.fms.preferences.GuiPreferenceAttribute;
import com.flywheelms.library.fms.preferences.GuiPreferencesBundleCategory;
import com.flywheelms.library.fms.treeview.filter.FmsTreeViewAdapter;
import com.flywheelms.library.fms.treeview.filter.FmsTreeViewParent;
import com.flywheelms.library.fms.widget.spinner.CompletableWorkStatusWidgetSpinner;
import com.flywheelms.library.fms.widget.spinner.FlywheelTeamWidgetSpinner;
import com.flywheelms.library.fms.widget.spinner.FunctionalTeamWidgetSpinner;
import com.flywheelms.library.fms.widget.spinner.StrategyTeamWidgetSpinner;
import com.flywheelms.library.fms.widget.text_view.CustomerNicknameWidgetTextView;
import com.flywheelms.library.fms.widget.text_view.FacilitatorNicknameWidgetTextView;
import com.flywheelms.library.fms.widget.text_view.SponsorNicknameWidgetTextView;

import java.util.ArrayList;
import java.util.Collections;

public abstract class FmsPerspectiveFlipperTreeView extends GcgPerspectiveFlipperTreeView implements FmsTreeViewParent {

	protected GcgIncrementalButton nodeChildSummaryMenuItem;
	protected GcgIncrementalButton nodeQualityMenuItem;
	protected GcgIncrementalButton treeDepthMenuItem;
	protected GcgIncrementalButton emphasisLevelMenuItem;
	protected CheckBox sponsorFilterCheckBox;
	protected SponsorNicknameWidgetTextView sponsorWidget;
	protected CheckBox facilitatorFilterCheckBox;
	protected FacilitatorNicknameWidgetTextView facilitatorWidget;
	protected CheckBox customerFilterCheckBox;
	protected CustomerNicknameWidgetTextView customerWidget;
	protected CheckBox fiscalYearWorkStateCheckBox;
	private CompletableWorkStatusWidgetSpinner fiscalYearWorkStateSpinner;
	protected CheckBox strategicMilestoneWorkStateCheckBox;
	private CompletableWorkStatusWidgetSpinner strategicMilestoneWorkStateSpinner;
	protected CheckBox projectAssetWorkStateCheckBox;
	private CompletableWorkStatusWidgetSpinner projectAssetWorkStateSpinner;
	protected CheckBox strategyTeamCheckBox;
	private StrategyTeamWidgetSpinner strategyTeamWidgetSpinner;
	protected CheckBox flywheelTeamCheckBox;
	private FlywheelTeamWidgetSpinner flywheelTeamWidgetSpinner;
	protected CheckBox functionalTeamCheckBox;
	private FunctionalTeamWidgetSpinner functionalTeamWidgetSpinner;
	protected Button showMenuGuiPreferencesButton;
	protected Button governanceMenuGuiPreferencesButton;
	protected Button workStatusMenuGuiPreferencesButton;
	protected Button teamMenuGuiPreferencesButton;
	private GuiPreferencesBundleCategory queuedGuiPreferencesBundleCategory;
	private SharedPreferences transientGuiPreferencesShowMenu;
	private SharedPreferences transientGuiPreferencesGovernanceMenu;
	private SharedPreferences transientGuiPreferencesWorkStatusMenu;
	private SharedPreferences transientGuiPreferencesTeamMenu;

	public FmsPerspectiveFlipperTreeView(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
		this.transientGuiPreferencesShowMenu = GcgPreferencesHelper.newTransientPreferences(getPreferencesBundleNameShowMenu());
		this.transientGuiPreferencesGovernanceMenu = GcgPreferencesHelper.newTransientPreferences(getPreferencesBundleNameGovernanceMenu());
		this.transientGuiPreferencesWorkStatusMenu = GcgPreferencesHelper.newTransientPreferences(getPreferencesBundleNameWorkStatusMenu());
		this.transientGuiPreferencesTeamMenu = GcgPreferencesHelper.newTransientPreferences(getPreferencesBundleNameTeamMenu());
	}
	
	@Override
	protected FmsTreeViewAdapter newTreeViewAdapter() {
		return new FmsTreeViewAdapter(this, this.treeViewMediator, getFontSizeArray());
	}

	@Override
    protected void initializeRightMenu() {
        this.rightMenuContainer = (LinearLayout) findViewById(R.id.gcg__right_menu__container);
        LayoutInflater theLayoutInflater = LayoutInflater.from(getContext());
//        theLayoutInflater.inflate(getRightMenuLayoutResourceId(), this.rightMenuContainer, false);
        this.rightMenuLayout = (LinearLayout) theLayoutInflater.inflate(getRightMenuLayoutResourceId(), this.rightMenuContainer, false);
        this.rightMenuContainer.addView(this.rightMenuLayout);
//        LinearLayout theViewFlipperBody = (LinearLayout) getGcgActivity().findViewById(R.id.gcg_view_flipper__body);
//		theViewFlipperBody.addView(this.rightMenuLayout, new LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT, android.view.ViewGroup.LayoutParams.MATCH_PARENT));
		this.rightSpinnableMenu = new GcgSpinnableMenu(
				getContext(),
				this.rightMenuLayout,
				GcgSpinnableMenu.DECORATORS_RIGHT,
				getRightMenuHeadingSpinnerResourceId(),
				getRightMenuHeadingArrayResourceId(),
                getRightMenuBodyResourceIdArray() );
		initShowMenu();
		initGovernanceMenu();
		initWorkStatusMenu();
		initTeamMenu();
	}

    protected int[] getRightMenuBodyResourceIdArray() {
        return new int[]{
                R.id.show_menu__body,
                R.id.governance__menu_body,
                R.id.work_status__menu_body,
                R.id.team__menu_body};
    }

    // TODO - make abstract
	protected int getRightMenuHeadingArrayResourceId() {
		return R.array.context__strategic_planning__right_menu__heading_array;
	}

	// TODO - make abstract
	protected int getRightMenuHeadingSpinnerResourceId() {
		return R.id.right_menu__heading_spinner;
	}

	// TODO - make abstract
	protected int getRightMenuLayoutResourceId() {
		return R.layout.context__strategic_planning__right_menu;
	}

	@Override
	public FmsTreeViewAdapter getTreeViewAdapter() {
		return (FmsTreeViewAdapter) this.treeViewAdapter;
	}

	protected void initShowMenu() {
		this.nodeChildSummaryMenuItem = (GcgIncrementalButton) this.rightMenuLayout.findViewById(R.id.show_button__node_child_summary);
		this.nodeChildSummaryMenuItem.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getTreeViewAdapter().refresh();
			}
		});
		this.nodeChildSummaryMenuItem.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				getTreeViewAdapter().refresh();
				return true;
			}
		});
		this.nodeQualityMenuItem = (GcgIncrementalButton) this.rightMenuLayout.findViewById(R.id.show_button__node_quality);
		this.nodeQualityMenuItem.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getTreeViewAdapter().refresh();
			}
		});
		this.nodeQualityMenuItem.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				getTreeViewAdapter().refresh();
				return true;
			}
		});
		this.treeDepthMenuItem = (GcgIncrementalButton) this.rightMenuLayout.findViewById(R.id.show_button__collapse_to_tree_depth);
		this.treeDepthMenuItem.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getTreeViewAdapter().applyShowCollapseToTreeLevel(getShowCollapseToTreeLevel());
				getTreeViewAdapter().refresh();
			}
		});
		this.treeDepthMenuItem.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				getTreeViewAdapter().applyShowCollapseToTreeLevel(getShowCollapseToTreeLevel());
				getTreeViewAdapter().refresh();
				return true;
			}
		});
		this.emphasisLevelMenuItem = (GcgIncrementalButton) this.rightMenuLayout.findViewById(R.id.show_button__emphasis_level);
		this.emphasisLevelMenuItem.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getTreeViewAdapter().refresh();
			}
		});
		this.emphasisLevelMenuItem.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				getTreeViewAdapter().refresh();
				return true;
			}
		});
		this.showMenuGuiPreferencesButton = (Button) this.rightMenuLayout.findViewById(R.id.gui_preferences__show);
		this.showMenuGuiPreferencesButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				launchShowMenuGuiPreferencesDialog();
			}
		});
		this.showMenuGuiPreferencesButton.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
		    	v.playSoundEffect(SoundEffectConstants.CLICK);
		    	FmsPerspectiveFlipperTreeView.this.queuedGuiPreferencesBundleCategory = GuiPreferencesBundleCategory.SHOW_MENU;
				guiPreferencesRestore();
				return true;
			}
		});
        guiPreferencesRestoreShowMenu();
	}

	private void launchShowMenuGuiPreferencesDialog() {
		// TODO - use perspective info to set queuedGuiPreferencesBundleCategory
		this.queuedGuiPreferencesBundleCategory = GuiPreferencesBundleCategory.SHOW_MENU;
		getGcgActivity().startDialog(new GcgGuiPreferencesDialog(getGcgActivity(), getFrameNameNotation() + getPageTitle() + " -> Show menu", this));
	}
	
	private String getFrameNameNotation() {
		String theFrameName = getGcgActivity().getGcgFrameName();
		String theFrameNotation = "";
		if(theFrameName != null && theFrameName.length() > 0) {
			theFrameNotation = theFrameName + " -> ";
		}
		return theFrameNotation;
	}

	protected void initGovernanceMenu() {
		this.sponsorFilterCheckBox = (CheckBox) this.rightMenuLayout.findViewById(R.id.governance_checkbox__sponsor);
		this.sponsorFilterCheckBox.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				getTreeViewAdapter().applyGovernanceFilter();
//				getTreeViewAdapter().refresh();
			}
		});
		this.sponsorWidget = (SponsorNicknameWidgetTextView) this.rightMenuLayout.findViewById(R.id.governance_text__sponsor);
		this.sponsorWidget.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				CommunityMember.startNodePickerActivity(
						getGcgActivity(),
						null,
						null,
						"Sponsor" );
			}
		});
		this.facilitatorFilterCheckBox = (CheckBox) this.rightMenuLayout.findViewById(R.id.governance_checkbox__facilitator);
		this.facilitatorFilterCheckBox.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				getTreeViewAdapter().applyGovernanceFilter();
//				getTreeViewAdapter().refresh();
			}
		});
		this.facilitatorWidget = (FacilitatorNicknameWidgetTextView) this.rightMenuLayout.findViewById(R.id.governance_text__facilitator);
		this.facilitatorWidget.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				CommunityMember.startNodePickerActivity(
						getGcgActivity(),
						null,
						null,
						"Facilitator" );
			}
		});
		this.customerFilterCheckBox = (CheckBox) this.rightMenuLayout.findViewById(R.id.governance_checkbox__customer);
		this.customerFilterCheckBox.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				getTreeViewAdapter().applyGovernanceFilter();
//				getTreeViewAdapter().refresh();
			}
		});
		this.customerWidget = (CustomerNicknameWidgetTextView) this.rightMenuLayout.findViewById(R.id.governance_text__customer);
		this.customerWidget.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				CommunityMember.startNodePickerActivity(
						getGcgActivity(),
						null,
						null,
						"Customer" );
			}
		});
		this.governanceMenuGuiPreferencesButton = (Button) this.rightMenuLayout.findViewById(R.id.gui_preferences__governance);
		this.governanceMenuGuiPreferencesButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				launchShowMenuGuiPreferencesDialog();
			}
		});
		this.governanceMenuGuiPreferencesButton.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
		    	v.playSoundEffect(SoundEffectConstants.CLICK);
		    	FmsPerspectiveFlipperTreeView.this.queuedGuiPreferencesBundleCategory = GuiPreferencesBundleCategory.GOVERNANCE_MENU;
				guiPreferencesRestore();
				return true;
			}
		});
        guiPreferencesRestoreGovernanceMenu();
	}

	protected void launchGovernanceMenuGuiPreferencesDialog() {
		this.queuedGuiPreferencesBundleCategory = GuiPreferencesBundleCategory.GOVERNANCE_MENU;
		getGcgActivity().startDialog(new GcgGuiPreferencesDialog(getGcgActivity(), getFrameNameNotation() + getPageTitle() + " -> Governance menu", this));
	}

	protected void initWorkStatusMenu() {
		this.fiscalYearWorkStateCheckBox = (CheckBox) this.rightMenuLayout.findViewById(R.id.work_status__fiscal_year__checkbox);
		this.fiscalYearWorkStateCheckBox.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FmsPerspectiveFlipperTreeView.this.applyWorkStatusFilter();
			}
		});
		this.fiscalYearWorkStateSpinner = (CompletableWorkStatusWidgetSpinner) this.rightMenuLayout.findViewById(R.id.work_status__fiscal_year__spinner);
		this.fiscalYearWorkStateSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if(FmsPerspectiveFlipperTreeView.this.fiscalYearWorkStateCheckBox.isChecked()) {
					FmsPerspectiveFlipperTreeView.this.applyWorkStatusFilter();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				return;
			}
		});
		this.strategicMilestoneWorkStateCheckBox = (CheckBox) this.rightMenuLayout.findViewById(R.id.work_status__strategic_milestone__checkbox);
		this.strategicMilestoneWorkStateCheckBox.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FmsPerspectiveFlipperTreeView.this.applyWorkStatusFilter();
			}
		});
		this.strategicMilestoneWorkStateSpinner = (CompletableWorkStatusWidgetSpinner) this.rightMenuLayout.findViewById(R.id.work_status__strategic_milestone__spinner);
		this.strategicMilestoneWorkStateSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if(FmsPerspectiveFlipperTreeView.this.strategicMilestoneWorkStateCheckBox.isChecked()) {
					FmsPerspectiveFlipperTreeView.this.applyWorkStatusFilter();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				return;
			}
		});
		this.projectAssetWorkStateCheckBox = (CheckBox) this.rightMenuLayout.findViewById(R.id.work_status__project_asset__checkbox);
		this.projectAssetWorkStateCheckBox.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FmsPerspectiveFlipperTreeView.this.applyWorkStatusFilter();
			}
		});
		this.projectAssetWorkStateSpinner = (CompletableWorkStatusWidgetSpinner) this.rightMenuLayout.findViewById(R.id.work_status__project_asset__spinner);
		this.projectAssetWorkStateSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if(FmsPerspectiveFlipperTreeView.this.projectAssetWorkStateCheckBox.isChecked()) {
					FmsPerspectiveFlipperTreeView.this.applyWorkStatusFilter();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				return;
			}
		});
		this.workStatusMenuGuiPreferencesButton = (Button) this.rightMenuLayout.findViewById(R.id.gui_preferences__work_status);
		this.workStatusMenuGuiPreferencesButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				launchShowMenuGuiPreferencesDialog();
			}
		});
		this.workStatusMenuGuiPreferencesButton.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
		    	v.playSoundEffect(SoundEffectConstants.CLICK);
		    	FmsPerspectiveFlipperTreeView.this.queuedGuiPreferencesBundleCategory = GuiPreferencesBundleCategory.WORK_STATUS_MENU;
				guiPreferencesRestore();
				return true;
			}
		});
        guiPreferencesRestoreWorkStatusMenu();
	}

	protected void launchWorkStatusMenuGuiPreferencesDialog() {
		this.queuedGuiPreferencesBundleCategory = GuiPreferencesBundleCategory.WORK_STATUS_MENU;
		getGcgActivity().startDialog(new GcgGuiPreferencesDialog(getGcgActivity(), getFrameNameNotation() + getPageTitle() + " -> WorkStatus menu", this));
	}
	
	private void applyWorkStatusFilter() {
		// TODO
	}

	protected void initTeamMenu() {
		this.strategyTeamCheckBox = (CheckBox) this.rightMenuLayout.findViewById(R.id.strategy_team__checkbox);
		this.strategyTeamCheckBox.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FmsPerspectiveFlipperTreeView.this.applyTeamFilter();
			}
		});
		this.strategyTeamWidgetSpinner = (StrategyTeamWidgetSpinner) this.rightMenuLayout.findViewById(R.id.strategy_team__spinner);
		this.strategyTeamWidgetSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if(FmsPerspectiveFlipperTreeView.this.strategyTeamCheckBox.isChecked()) {
					FmsPerspectiveFlipperTreeView.this.applyTeamFilter();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				return;
			}
		});
		this.flywheelTeamCheckBox = (CheckBox) this.rightMenuLayout.findViewById(R.id.flywheel_team__checkbox);
		this.flywheelTeamCheckBox.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FmsPerspectiveFlipperTreeView.this.applyTeamFilter();
			}
		});
		this.flywheelTeamWidgetSpinner = (FlywheelTeamWidgetSpinner) this.rightMenuLayout.findViewById(R.id.flywheel_team__spinner);
		this.flywheelTeamWidgetSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if(FmsPerspectiveFlipperTreeView.this.flywheelTeamCheckBox.isChecked()) {
					FmsPerspectiveFlipperTreeView.this.applyTeamFilter();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				return;
			}
		});
		this.functionalTeamCheckBox = (CheckBox) this.rightMenuLayout.findViewById(R.id.functional_team__checkbox);
		this.functionalTeamCheckBox.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FmsPerspectiveFlipperTreeView.this.applyTeamFilter();
			}
		});
		this.functionalTeamWidgetSpinner = (FunctionalTeamWidgetSpinner) this.rightMenuLayout.findViewById(R.id.functional_team__spinner);
		this.functionalTeamWidgetSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if(FmsPerspectiveFlipperTreeView.this.functionalTeamCheckBox.isChecked()) {
					FmsPerspectiveFlipperTreeView.this.applyTeamFilter();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				return;
			}
		});
		this.teamMenuGuiPreferencesButton = (Button) this.rightMenuLayout.findViewById(R.id.gui_preferences__team);
//		this.teamMenuGuiPreferencesButton.setOnTouchListener(new OnTouchListener() {
//			
//			@Override
//			public boolean onTouch(View aView, MotionEvent aMotionEvent) {
//				if(aMotionEvent.getAction() == MotionEvent.ACTION_UP) {
//					launchTeamMenuGuiPreferencesDialog();
//				}
//				return true;
//			}
//		});
		this.teamMenuGuiPreferencesButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				launchShowMenuGuiPreferencesDialog();
			}
		});
		this.teamMenuGuiPreferencesButton.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
		    	v.playSoundEffect(SoundEffectConstants.CLICK);
		    	FmsPerspectiveFlipperTreeView.this.queuedGuiPreferencesBundleCategory = GuiPreferencesBundleCategory.TEAM_MENU;
				guiPreferencesRestore();
				return true;
			}
		});
        guiPreferencesRestoreTeamMenu();
	}

	protected void launchTeamMenuGuiPreferencesDialog() {
		this.queuedGuiPreferencesBundleCategory = GuiPreferencesBundleCategory.TEAM_MENU;
		getGcgActivity().startDialog(new GcgGuiPreferencesDialog(getGcgActivity(), getFrameNameNotation() + getPageTitle() + " -> Team menu", this));
	}
	
	private void applyTeamFilter() {
		// TODO
	}

	@Override
	public int getShowEmphasisLevel() {
		int theState = this.emphasisLevelMenuItem.getState();
		int theMaxStates = this.emphasisLevelMenuItem.getNumberOfStates();
		return theState == theMaxStates ? 99 : theState;
	}

	public int getShowCollapseToTreeLevel() {
		if(this.treeDepthMenuItem == null) {
			return 99;
		}
		int theState = this.treeDepthMenuItem.getState();
		int theMaxStates = this.treeDepthMenuItem.getNumberOfStates();
		int theTreeLevel;
		switch (theState) {
			case 2:
				theTreeLevel = theMaxStates - 1;
				break;
			case 3:
				theTreeLevel = theMaxStates - 2;
				break;
			case 4:
				theTreeLevel = theMaxStates - 3;
				break;
			case 5:
				theTreeLevel = theMaxStates - 4;
				break;
			case 6:
				theTreeLevel = theMaxStates - 5;
				break;
			case 7:
				theTreeLevel = theMaxStates - 6;
				break;
			default:
				theTreeLevel = 99;  // case 1 and default
		}
		return theTreeLevel;
	}
	
	@Override
	public void guiPreferencesSave() {
		if(this.queuedGuiPreferencesBundleCategory.equals(GuiPreferencesBundleCategory.SHOW_MENU)) {
			guiPreferencesSaveShowMenu();
			getTreeViewAdapter().applyShowCollapseToTreeLevel(getShowCollapseToTreeLevel());
		} else if(this.queuedGuiPreferencesBundleCategory.equals(GuiPreferencesBundleCategory.GOVERNANCE_MENU)) {
			guiPreferencesSaveGovernanceMenu();
		} else if(this.queuedGuiPreferencesBundleCategory.equals(GuiPreferencesBundleCategory.WORK_STATUS_MENU)) {
			guiPreferencesSaveWorkStatusMenu();
		} else {
			guiPreferencesSaveTeamMenu();
		}
	}
	
	@Override
	public void guiPreferencesRestore() {
		if(this.queuedGuiPreferencesBundleCategory.equals(GuiPreferencesBundleCategory.SHOW_MENU)) {
			guiPreferencesRestoreShowMenu();
			getTreeViewAdapter().applyShowCollapseToTreeLevel(getShowCollapseToTreeLevel());
		} else if(this.queuedGuiPreferencesBundleCategory.equals(GuiPreferencesBundleCategory.GOVERNANCE_MENU)) {
			guiPreferencesRestoreGovernanceMenu();
		} else if(this.queuedGuiPreferencesBundleCategory.equals(GuiPreferencesBundleCategory.WORK_STATUS_MENU)) {
			guiPreferencesRestoreWorkStatusMenu();
		} else {
			guiPreferencesRestoreTeamMenu();
		}
		getTreeStateMediator().refreshViews();
	}
	
	@Override
	public void guiPreferencesClear() {
		if(this.queuedGuiPreferencesBundleCategory.equals(GuiPreferencesBundleCategory.SHOW_MENU)) {
			guiPreferencesClearShowMenu();
			getTreeViewAdapter().applyShowCollapseToTreeLevel(getShowCollapseToTreeLevel());
		} else if(this.queuedGuiPreferencesBundleCategory.equals(GuiPreferencesBundleCategory.GOVERNANCE_MENU)) {
			guiPreferencesClearGovernanceMenu();
		} else if(this.queuedGuiPreferencesBundleCategory.equals(GuiPreferencesBundleCategory.WORK_STATUS_MENU)) {
			guiPreferencesClearWorkStatusMenu();
		} else {
			guiPreferencesClearTeamMenu();
		}
		getTreeStateMediator().refreshViews();
	}
	
	@Override
	public void guiPreferencesSaveTransient() {
        super.guiPreferencesSaveTransient();
//		guiPreferencesSaveShowMenuTransient();
//		guiPreferencesSaveGovernanceMenuTransient();
//		guiPreferencesSaveWorkStatusMenuTransient();
//		guiPreferencesSaveTeamMenuTransient();
	}
	
	@Override
	public void guiPreferencesRestoreTransient() {
		guiPreferencesRestoreShowMenuTransient();
        guiPreferencesRestoreGovernanceMenuTransient();
        guiPreferencesRestoreWorkStatusMenuTransient();
        guiPreferencesRestoreTeamMenuTransient();
        super.guiPreferencesRestoreTransient();
    }

	protected void guiPreferencesSaveShowMenu() {
		guiPreferencesSaveShowMenu(GcgPreferencesHelper.getGuiPreferences(
				getGcgViewFlipper().getGcgActivity(), getPreferencesBundleNameShowMenu() ));
	}

	protected void guiPreferencesSaveShowMenuTransient() {
		guiPreferencesSaveShowMenu(this.transientGuiPreferencesShowMenu);
	}

	protected void guiPreferencesSaveShowMenu(SharedPreferences aGuiPreferences) {
		if(this.nodeChildSummaryMenuItem != null) {
			aGuiPreferences.edit().putInt(GuiPreferenceAttribute.CHILD_SUMMARY.getKey(), this.nodeChildSummaryMenuItem.getState()).commit();
		}
		if(this.nodeQualityMenuItem != null) {
			aGuiPreferences.edit().putInt(GuiPreferenceAttribute.NODE_QUALITY.getKey(), this.nodeQualityMenuItem.getState()).commit();
		}
		if(this.treeDepthMenuItem != null) {
			aGuiPreferences.edit().putInt(GuiPreferenceAttribute.TREE_DEPTH.getKey(), this.treeDepthMenuItem.getState()).commit();
		}
		if(this.emphasisLevelMenuItem != null) {
			aGuiPreferences.edit().putInt(GuiPreferenceAttribute.EMPHASIS_LEVEL.getKey(), this.emphasisLevelMenuItem.getState()).commit();
		}
	}

	protected void guiPreferencesSaveGovernanceMenu() {
		guiPreferencesSaveGovernanceMenu(GcgPreferencesHelper.getGuiPreferences(
				getGcgViewFlipper().getGcgActivity(), getPreferencesBundleNameGovernanceMenu() ));
	}

	protected void guiPreferencesSaveGovernanceMenuTransient() {
		guiPreferencesSaveGovernanceMenu(this.transientGuiPreferencesGovernanceMenu);
	}

	protected void guiPreferencesSaveGovernanceMenu(SharedPreferences aGuiPreferences) {
		if(this.sponsorFilterCheckBox != null) {
			aGuiPreferences.edit().putBoolean(GuiPreferenceAttribute.SPONSOR__FILTER__CHECKED.getKey(), this.sponsorFilterCheckBox.isChecked()).commit();
		}
		if(this.sponsorWidget != null) {
			aGuiPreferences.edit().putString(GuiPreferenceAttribute.SPONSOR__NODE_ID.getKey(), this.sponsorWidget.toString()).commit();
		}
		if(this.facilitatorFilterCheckBox != null) {
			aGuiPreferences.edit().putBoolean(GuiPreferenceAttribute.FACILITATOR__FILTER__CHECKED.getKey(), this.facilitatorFilterCheckBox.isChecked()).commit();
		}
		if(this.facilitatorWidget != null) {
			aGuiPreferences.edit().putString(GuiPreferenceAttribute.FACILITATOR__NODE_ID.getKey(), this.facilitatorWidget.toString()).commit();
		}
		if(this.customerFilterCheckBox != null) {
			aGuiPreferences.edit().putBoolean(GuiPreferenceAttribute.CUSTOMER__FILTER__CHECKED.getKey(), this.customerFilterCheckBox.isChecked()).commit();
		}
		if(this.customerWidget != null) {
			aGuiPreferences.edit().putString(GuiPreferenceAttribute.CUSTOMER__NODE_ID.getKey(), this.customerWidget.toString()).commit();
		}
	}

	protected void guiPreferencesSaveWorkStatusMenu() {
		guiPreferencesSaveWorkStatusMenu(GcgPreferencesHelper.getGuiPreferences(
				getGcgViewFlipper().getGcgActivity(), getPreferencesBundleNameWorkStatusMenu() ));
	}

	protected void guiPreferencesSaveWorkStatusMenuTransient() {
		guiPreferencesSaveWorkStatusMenu(this.transientGuiPreferencesWorkStatusMenu);
	}

	protected void guiPreferencesSaveWorkStatusMenu(SharedPreferences aGuiPreferences) {
		if(this.fiscalYearWorkStateCheckBox != null) {
			aGuiPreferences.edit().putBoolean(GuiPreferenceAttribute.FISCAL_YEAR__WORK_STATE__CHECKED.getKey(), this.fiscalYearWorkStateCheckBox.isChecked()).commit();
		}
		if(this.fiscalYearWorkStateSpinner != null) {
			aGuiPreferences.edit().putString(GuiPreferenceAttribute.FISCAL_YEAR__WORK_STATE.getKey(), ((CompletableWorkStatus) this.fiscalYearWorkStateSpinner.getSelectedItem()).getWorkStatusCode()).commit();
		}
		if(this.strategicMilestoneWorkStateCheckBox != null) {
			aGuiPreferences.edit().putBoolean(GuiPreferenceAttribute.STRATEGIC_MILESTONE__WORK_STATE__CHECKED.getKey(), this.strategicMilestoneWorkStateCheckBox.isChecked()).commit();
		}
		if(this.strategicMilestoneWorkStateSpinner != null) {
			aGuiPreferences.edit().putString(GuiPreferenceAttribute.STRATEGIC_MILESTONE__WORK_STATE.getKey(), ((CompletableWorkStatus) this.strategicMilestoneWorkStateSpinner.getSelectedItem()).getWorkStatusCode()).commit();
		}
		if(this.projectAssetWorkStateCheckBox != null) {
			aGuiPreferences.edit().putBoolean(GuiPreferenceAttribute.PROJECT_ASSET__WORK_STATE__CHECKED.getKey(), this.projectAssetWorkStateCheckBox.isChecked()).commit();
		}
		if(this.projectAssetWorkStateSpinner != null) {
			aGuiPreferences.edit().putString(GuiPreferenceAttribute.PROJECT_ASSET__WORK_STATE.getKey(), ((CompletableWorkStatus) this.projectAssetWorkStateSpinner.getSelectedItem()).getWorkStatusCode()).commit();
		}
	}

	protected void guiPreferencesSaveTeamMenu() {
		guiPreferencesSaveTeamMenu(GcgPreferencesHelper.getGuiPreferences(
				getGcgViewFlipper().getGcgActivity(), getPreferencesBundleNameTeamMenu() ));
	}

	protected void guiPreferencesSaveTeamMenuTransient() {
		guiPreferencesSaveTeamMenu(this.transientGuiPreferencesTeamMenu);
	}

	protected void guiPreferencesSaveTeamMenu(SharedPreferences aGuiPreferences) {
		if(this.strategyTeamCheckBox != null) {
			aGuiPreferences.edit().putBoolean(GuiPreferenceAttribute.STRATEGY_TEAM__CHECKBOX.getKey(), this.strategyTeamCheckBox.isChecked()).commit();
		}
		if(this.strategyTeamWidgetSpinner != null) {
			aGuiPreferences.edit().putString(GuiPreferenceAttribute.STRATEGY_TEAM__NAME.getKey(), this.strategyTeamWidgetSpinner.toString()).commit();
		}
		if(this.flywheelTeamCheckBox != null) {
			aGuiPreferences.edit().putBoolean(GuiPreferenceAttribute.FLYWHEEL_TEAM__CHECKBOX.getKey(), this.flywheelTeamCheckBox.isChecked()).commit();
		}
		if(this.flywheelTeamWidgetSpinner != null) {
			aGuiPreferences.edit().putString(GuiPreferenceAttribute.FLYWHEEL_TEAM__NAME.getKey(), this.flywheelTeamWidgetSpinner.toString()).commit();
		}
		if(this.functionalTeamCheckBox != null) {
			aGuiPreferences.edit().putBoolean(GuiPreferenceAttribute.FUNCTIONAL_TEAM__CHECKBOX.getKey(), this.functionalTeamCheckBox.isChecked()).commit();
		}
		if(this.functionalTeamWidgetSpinner != null) {
			aGuiPreferences.edit().putString(GuiPreferenceAttribute.FUNCTIONAL_TEAM__NAME.getKey(), this.functionalTeamWidgetSpinner.toString()).commit();
		}
	}
	
	public void guiPreferencesRestoreShowMenu() {
		guiPreferencesRestoreShowMenu(GcgPreferencesHelper.getGuiPreferences(
			getGcgViewFlipper().getGcgActivity(), getPreferencesBundleNameShowMenu() ));
	}
	
	public void guiPreferencesRestoreShowMenuTransient() {
		guiPreferencesRestoreShowMenu(this.transientGuiPreferencesShowMenu);
	}
	
	public void guiPreferencesRestoreShowMenu(SharedPreferences aGuiPreferences) {
		if(this.nodeChildSummaryMenuItem == null) {  // TODO - HACK ALERT
			return;
		}
		this.nodeChildSummaryMenuItem.setState(aGuiPreferences.getInt(GuiPreferenceAttribute.CHILD_SUMMARY.getKey(), 1));
		this.nodeQualityMenuItem.setState(aGuiPreferences.getInt(GuiPreferenceAttribute.NODE_QUALITY.getKey(), 1));
		this.treeDepthMenuItem.setState(aGuiPreferences.getInt(GuiPreferenceAttribute.TREE_DEPTH.getKey(), 1));
		this.emphasisLevelMenuItem.setState(aGuiPreferences.getInt(GuiPreferenceAttribute.EMPHASIS_LEVEL.getKey(), 1));
	}
	
	public void guiPreferencesRestoreGovernanceMenu() {
		guiPreferencesRestoreGovernanceMenu(GcgPreferencesHelper.getGuiPreferences(
                getGcgViewFlipper().getGcgActivity(), getPreferencesBundleNameGovernanceMenu()));
	}
	
	public void guiPreferencesRestoreGovernanceMenuTransient() {
		guiPreferencesRestoreGovernanceMenu(this.transientGuiPreferencesGovernanceMenu);
	}
	
	public void guiPreferencesRestoreGovernanceMenu(SharedPreferences aGuiPreferences) {
		if(this.sponsorFilterCheckBox == null) {  // TODO - HACK ALERT
			return;
		}
		this.sponsorFilterCheckBox.setChecked(aGuiPreferences.getBoolean(GuiPreferenceAttribute.SPONSOR__FILTER__CHECKED.getKey(), false));
//		this.sponsorWidget.setText(aGuiPreferences.getString(GuiPreferenceAttribute.SPONSOR_FILTER_NODE_ID, null__SPONSOR));
		this.facilitatorFilterCheckBox.setChecked(aGuiPreferences.getBoolean(GuiPreferenceAttribute.FACILITATOR__FILTER__CHECKED.getKey(), false));
//		this.facilitatorWidget.setText(aGuiPreferences.getString(GuiPreferenceAttribute.FACILITATOR_FILTER_NODE_ID, null__FACILITATOR));
		this.customerFilterCheckBox.setChecked(aGuiPreferences.getBoolean(GuiPreferenceAttribute.CUSTOMER__FILTER__CHECKED.getKey(), false));
//		this.customerWidget.setText(aGuiPreferences.getString(GuiPreferenceAttribute.CUSTOMER_FILTER_NODE_ID, null__CUSTOMER));
	}
	
	public void guiPreferencesRestoreWorkStatusMenu() {
		guiPreferencesRestoreWorkStatusMenu(GcgPreferencesHelper.getGuiPreferences(
				getGcgViewFlipper().getGcgActivity(), getPreferencesBundleNameWorkStatusMenu() ));
	}
	
	public void guiPreferencesRestoreWorkStatusMenuTransient() {
		guiPreferencesRestoreWorkStatusMenu(this.transientGuiPreferencesWorkStatusMenu);
	}
	
	public void guiPreferencesRestoreWorkStatusMenu(SharedPreferences aGuiPreferences) {
		if(this.fiscalYearWorkStateCheckBox == null) {  // TODO - HACK ALERT
			return;
		}
		this.fiscalYearWorkStateCheckBox.setChecked(aGuiPreferences.getBoolean(GuiPreferenceAttribute.FISCAL_YEAR__WORK_STATE__CHECKED.getKey(), false));
		this.fiscalYearWorkStateSpinner.setSelection(CompletableWorkStatus.getObjectForCode(
				aGuiPreferences.getString(GuiPreferenceAttribute.FISCAL_YEAR__WORK_STATE.getKey(), CompletableWorkStatus.STARTED.getWorkStatusCode())) );
		this.strategicMilestoneWorkStateCheckBox.setChecked(aGuiPreferences.getBoolean(GuiPreferenceAttribute.STRATEGIC_MILESTONE__WORK_STATE__CHECKED.getKey(), false));
		this.strategicMilestoneWorkStateSpinner.setSelection(CompletableWorkStatus.getObjectForCode(
				aGuiPreferences.getString(GuiPreferenceAttribute.STRATEGIC_MILESTONE__WORK_STATE.getKey(), CompletableWorkStatus.STARTED.getWorkStatusCode())) );
		this.projectAssetWorkStateCheckBox.setChecked(aGuiPreferences.getBoolean(GuiPreferenceAttribute.PROJECT_ASSET__WORK_STATE__CHECKED.getKey(), false));
		this.projectAssetWorkStateSpinner.setSelection(CompletableWorkStatus.getObjectForCode(
				aGuiPreferences.getString(GuiPreferenceAttribute.PROJECT_ASSET__WORK_STATE.getKey(), CompletableWorkStatus.STARTED.getWorkStatusCode())) );
	}
	
	public void guiPreferencesRestoreTeamMenu() {
		guiPreferencesRestoreTeamMenu(GcgPreferencesHelper.getGuiPreferences(
				getGcgViewFlipper().getGcgActivity(), getPreferencesBundleNameTeamMenu() ));
	}
	
	public void guiPreferencesRestoreTeamMenuTransient() {
		guiPreferencesRestoreTeamMenu(this.transientGuiPreferencesTeamMenu);
	}
	
	public void guiPreferencesRestoreTeamMenu(SharedPreferences aGuiPreferences) {
		if(this.strategyTeamCheckBox == null) {  // TODO - HACK ALERT
			return;
		}
		this.strategyTeamCheckBox.setChecked(aGuiPreferences.getBoolean(GuiPreferenceAttribute.STRATEGY_TEAM__CHECKBOX.getKey(), false));
//		this.strategyTeamWidgetSpinner.setSelection(????.getObjectForCode(
//				aGuiPreferences.getString(GuiPreferenceAttribute.STRATEGY_TEAM_NAME, "")) );

		this.flywheelTeamCheckBox.setChecked(aGuiPreferences.getBoolean(GuiPreferenceAttribute.FLYWHEEL_TEAM__CHECKBOX.getKey(), false));
//		this.flywheelTeamWidgetSpinner.setSelection(????.getObjectForCode(
//				aGuiPreferences.getString(GuiPreferenceAttribute.FLYWHEEL_TEAM_NAME, "")) );

		this.functionalTeamCheckBox.setChecked(aGuiPreferences.getBoolean(GuiPreferenceAttribute.FUNCTIONAL_TEAM__CHECKBOX.getKey(), false));
		this.functionalTeamWidgetSpinner.setSelection(CompletableWorkStatus.getObjectForCode(
				aGuiPreferences.getString(GuiPreferenceAttribute.FUNCTIONAL_TEAM__NAME.getKey(), "")) );
	}
	
	public void guiPreferencesClearShowMenu() {
		GcgPreferencesHelper.guiPreferencesClear(getGcgViewFlipper().getGcgActivity(), getPreferencesBundleNameShowMenu());
		guiPreferencesRestoreShowMenu();
	}
	
	public void guiPreferencesClearGovernanceMenu() {
		GcgPreferencesHelper.guiPreferencesClear(getGcgViewFlipper().getGcgActivity(), getPreferencesBundleNameGovernanceMenu());
		guiPreferencesRestoreGovernanceMenu();
	}
	
	public void guiPreferencesClearWorkStatusMenu() {
		GcgPreferencesHelper.guiPreferencesClear(getGcgViewFlipper().getGcgActivity(), getPreferencesBundleNameWorkStatusMenu());
		guiPreferencesRestoreWorkStatusMenu();
	}
	
	public void guiPreferencesClearTeamMenu() {
		GcgPreferencesHelper.guiPreferencesClear(getGcgViewFlipper().getGcgActivity(), getPreferencesBundleNameTeamMenu());
		guiPreferencesRestoreTeamMenu();
	}
	
	@Override
	protected void guiPreferencesRestoreAll() {
		guiPreferencesRestoreShowMenu();
		guiPreferencesRestoreGovernanceMenu();
		guiPreferencesRestoreWorkStatusMenu();
		guiPreferencesRestoreTeamMenu();
	}

	public String getPreferencesBundleNameShowMenu() {
		return "";
	}

	public String getPreferencesBundleNameGovernanceMenu() {
		return "";
	}

	public String getPreferencesBundleNameWorkStatusMenu() {
		return "";
	}

	public String getPreferencesBundleNameTeamMenu() {
		return "";
	}
	
	////////////////////////////////////

	@Override
	public void launchNodeEditorActivity(GcgTreeNodeInfo aTreeNodeInfo, FmmFrame aFrame, FmmPerspective aPerspective) {
		FmmNodeDefinition theFmmNodeDefinition = ((FmmNode) aTreeNodeInfo.getTargetObject()).getFmmNodeDefinition();
		String theNodeIdToDisplay = ((FmmNode) aTreeNodeInfo.getTargetObject()).getNodeIdString();
		String theParentNodeId = ((FmmNode) this.treeViewMediator.getParent(aTreeNodeInfo).getTargetObject()).getNodeIdString();
		ArrayList<FmmHeadlineNodeShallow> thePeerHeadlineNodeShallowList = getPeerHeadlineNodeShallowList(aTreeNodeInfo);
		updatePerspectiveContext(aTreeNodeInfo);
		FmsActivityHelper.startHeadlineNodeEditorActivity(getGcgActivity(), theFmmNodeDefinition, thePeerHeadlineNodeShallowList, theParentNodeId, theNodeIdToDisplay);
	}

    private void updatePerspectiveContext(GcgTreeNodeInfo aTreeNodeInfo) {
        updatePerspectiveContext(aTreeNodeInfo, true);
    }

    @Override
	public void updatePerspectiveContext(GcgTreeNodeInfo aTreeNodeInfo, boolean bPrunePerspectiveContext) {
		ArrayList<GcgGuiable> theContextList = new ArrayList<GcgGuiable>();
		GcgTreeNodeInfo theParentTreeNodeInfo = bPrunePerspectiveContext ?
                this.treeViewMediator.getParent(aTreeNodeInfo) :
                aTreeNodeInfo;
		GcgGuiable theParentObject;
		while(theParentTreeNodeInfo != null) {
			theParentObject = ((GcgGuiable) theParentTreeNodeInfo.getTargetObject());
			theContextList.add(theParentObject);
			theParentTreeNodeInfo = this.treeViewMediator.getParent(theParentTreeNodeInfo);
		}
		Collections.reverse(theContextList);
        theContextList.remove(0);
        getGcgActivity().updatePerspectiveContext(theContextList);
	}

	@Override
	public void launchNodeQualityDialog(GcgTreeNodeInfo aTreeNodeInfo) {
		launchNodeDecKanGlNavigationDialog(aTreeNodeInfo);
	}

	@Override
	public void launchNodeDecKanGlNavigationDialog(GcgTreeNodeInfo aTreeNodeInfo) {
		ArrayList<FmmHeadlineNodeShallow> thePeerHeadlineNodeShallowList = getPeerHeadlineNodeShallowList(aTreeNodeInfo);
		String theParentNodeId = ((FmmNode) this.treeViewMediator.getParent(aTreeNodeInfo).getTargetObject()).getNodeIdString();
		DecKanGlNavigationDialog theDecKanGlGlyphNavigationDialog = new DecKanGlNavigationDialog(
				getGcgActivity(),
				thePeerHeadlineNodeShallowList,
				theParentNodeId,
				(FmmHeadlineNode) aTreeNodeInfo.getTargetObject() );
		getGcgActivity().startDialog(theDecKanGlGlyphNavigationDialog);
	}

	private ArrayList<FmmHeadlineNodeShallow> getPeerHeadlineNodeShallowList(GcgTreeNodeInfo aTreeNodeInfo) {
		ArrayList<FmmHeadlineNodeShallow> thePeerHeadlineNodeShallowList = new ArrayList<FmmHeadlineNodeShallow>();
		for(GcgTreeNodeInfo theTreeNodeInfo : this.treeViewMediator.getChildren(this.treeViewMediator.getParent(aTreeNodeInfo)) ) {
			thePeerHeadlineNodeShallowList.add(new FmmHeadlineNodeShallow(theTreeNodeInfo));
		}
		return thePeerHeadlineNodeShallowList;
	}

	@Override
	public boolean isShowNodeChildSummary() {
		if(this.nodeChildSummaryMenuItem != null) {
			return ! this.nodeChildSummaryMenuItem.isOff();
		}
		return false;
	}

	@SuppressWarnings("unused")
	public boolean showTreeLevelWithWorkState(int aTreeLevel, CompletableWorkStatus aWorkStatus) {
		// TODO
		return true;
	}

	public boolean isFilterNodesForSponsor() {
		return this.sponsorFilterCheckBox.isChecked();
	}
	
	public String getFilterSponsorId() {
		return this.sponsorWidget.toString();
	}

	public boolean isFilterNodesForFacilitator() {
		return this.facilitatorFilterCheckBox.isChecked();
	}
	
	public String getFilterFacilitatorId() {
		return this.facilitatorWidget.toString();
	}

	public boolean isFilterNodesForCustomer() {
		return this.customerFilterCheckBox.isChecked();
	}
	
	public String getFilterCustomerId() {
		return this.customerWidget.toString();
	}

	@Override
	public int getShowNodeChildSummaryLevel() {
		return this.nodeChildSummaryMenuItem.getState();
	}

	@Override
	public boolean isShowNodeQuality() {
		if(this.nodeQualityMenuItem != null && !this.nodeQualityMenuItem.isOff()) {
			return true;
		}
		return false;
	}

	@Override
	public int getShowNodeQualityLevel() {
		return this.nodeQualityMenuItem.getState();
	}

	@Override
	public int getShowNodeCompletionLevel() {
		return this.emphasisLevelMenuItem.getState();
	}
	
	@Override
	public boolean isShowAllNodeCompletions() {
		return getShowNodeCompletionLevel() == this.emphasisLevelMenuItem.getStateOn();
	}

	@Override
	public int[] getFontSizeArray() {
		int[] theFontSizeArray =  {18,22,20,16};
		return theFontSizeArray;
	}
	
	@Override
	public void startEditorActivityForFmmHeadlineNode(
            FmmNodeDefinition anFmmNodeDefinition,
            ArrayList<? extends FmmHeadlineNode> aPeerHeadlineNodeShallowList,
            String aParentNodeIdString,
            String aNodeIdStringToDisplay) {
		FmsActivityHelper.startHeadlineNodeEditorActivity(
				getGcgActivity(),
                anFmmNodeDefinition,
                aPeerHeadlineNodeShallowList,
				aParentNodeIdString,
				aNodeIdStringToDisplay );
	}

	@Override
	protected PopupMenu getTreeViewBackgroundPopupMenu(View aView, GcgTreeViewAdapter aTreeViewAdapter) {
		return null;
	}

	@Override
	public void guiPreferencesApply() {
		getTreeViewAdapter().applyShowCollapseToTreeLevel(getShowCollapseToTreeLevel());
		getTreeStateMediator().refreshViews();
	}

}
