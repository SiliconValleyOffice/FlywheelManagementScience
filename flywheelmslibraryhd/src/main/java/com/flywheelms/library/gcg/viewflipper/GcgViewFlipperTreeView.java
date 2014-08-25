/* @(#)GcgViewFlipperTreeView.java
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

package com.flywheelms.library.gcg.viewflipper;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.flywheelms.library.R;
import com.flywheelms.library.gcg.GcgActivity;
import com.flywheelms.library.gcg.menu.GcgSpinnableMenu;
import com.flywheelms.library.gcg.treeview.GcgTreeView;
import com.flywheelms.library.gcg.treeview.GcgTreeViewAdapter;
import com.flywheelms.library.gcg.treeview.GcgTreeViewMediator;
import com.flywheelms.library.gcg.treeview.interfaces.GcgTreeViewParent;
import com.flywheelms.library.gcg.treeview.node.GcgTreeNodeInfo;

public abstract class GcgViewFlipperTreeView extends GcgViewFlipperView implements GcgTreeViewParent {

	protected GcgTreeView treeView;
	protected GcgTreeViewMediator treeViewMediator = null;
	protected GcgTreeViewAdapter treeViewAdapter;
	protected boolean collapsibleTree;
	protected LinearLayout rightMenuLayout;
	protected GcgSpinnableMenu rightSpinnableMenu;

	public GcgViewFlipperTreeView(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
		this.treeViewMediator = createGcgTreeViewMediator();
	}

	@Override
	protected int getViewLayoutResourceId() {
		return R.layout.gcg__view_flipper__tree_view;
	}

	@Override
	public void initialize(GcgActivity anGcgActivity, GcgViewFlipper aViewFlipper, int aSpinnableMenuIndex, int aPageNumber) {
		super.initialize(anGcgActivity, aViewFlipper, aSpinnableMenuIndex, aPageNumber);
		initGuiElements();
		guiPreferencesRestoreAll();
		initializeTreeView();
		guiPreferencesApply();
	}
	
	@Override
	public void guiPreferencesApply() { return; }

	protected void guiPreferencesRestoreAll() { return; }

	protected void initGuiElements() { return; }

	protected abstract GcgTreeViewMediator createGcgTreeViewMediator();

	public boolean isCollapsibleTree() {
		return this.collapsibleTree;
	}
	
	public void setCollapsibleTree(boolean aBoolean) {
		this.collapsibleTree = aBoolean;
	}
	
	public void setCollapsibleTree() {
		this.collapsibleTree = true;
	}
	
	private void initializeTreeView() {
		this.treeViewAdapter = newTreeViewAdapter();
//        this.treeView = (GcgTreeView) findViewById(R.id.gcg_tree_view);
//        this.treeView.setAdapter(this.treeViewAdapter);
		TextView theTreeViewBackgroundMenuTarget = (TextView) findViewById(R.id.gcg_tree_view__background_menu_target);
		theTreeViewBackgroundMenuTarget.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View aView) {
				PopupMenu thePopupMenu = GcgViewFlipperTreeView.this.getTreeViewBackgroundPopupMenu(
						aView, GcgViewFlipperTreeView.this.treeViewAdapter );
				if(thePopupMenu != null) {
					thePopupMenu.show();
				}
			}
		});
	}

	protected abstract PopupMenu getTreeViewBackgroundPopupMenu(View aView, final GcgTreeViewAdapter aTreeViewAdapter);

	protected abstract GcgTreeViewAdapter newTreeViewAdapter();
	
	@Override
	public GcgTreeViewAdapter rebuildTreeView() {
		int currentPosition = this.treeView.getSelectedItemPosition();
		this.treeViewMediator = createGcgTreeViewMediator();
		this.treeViewAdapter = newTreeViewAdapter();
		this.treeView.setAdapter(this.treeViewAdapter);
		this.treeView.setSelection(currentPosition);
		return this.treeViewAdapter;
	}
	
	@Override
	public void refreshDataDisplay() {
		getGcgActivity().startOrangeActivityStatusAnimation();
		rebuildTreeView();
		getGcgActivity().stopActivityStatusAnimation();
	}
	
	public GcgTreeViewMediator getTreeStateMediator() {
		return this.treeViewMediator;
	}

	public GcgTreeViewAdapter getTreeViewAdapter() {
		return this.treeViewAdapter;
	}

	public int getNumberOfTreeLevels() {
		return getFontSizeArray().length;
	}

	public abstract int[] getFontSizeArray();

	@Override
	public int getShowEmphasisLevel() {
		return 99;
	}

	@Override
	public int getFrameMenuSpacerBackgroundResourceId() {
		return R.color.gcg__tree_view__background;
	}

	@Override
	public GcgTreeViewAdapter getGcgTreeViewAdapter() {
		return this.treeViewAdapter;
	}

	@Override
	public GcgTreeViewMediator getGcgTreeViewMediator() {
		return this.treeViewMediator;
	}
	
	@Override
	public void setSelection(int anAdapterPosition) {
		this.treeView.setSelection(anAdapterPosition);
	}

	@Override
	public void setSelection(GcgTreeNodeInfo aFirstVisibleTreeNodeInfo) {
		this.treeView.setSelection(this.treeViewMediator.getPosition(aFirstVisibleTreeNodeInfo));
	}

	@Override
	public void setSelection(GcgTreeNodeInfo aFirstVisibleTreeNodeInfo, GcgTreeNodeInfo aTreeNodeInfoThatMustBeVisible) {
		this.treeView.setSelection(this.treeViewMediator.getPosition(aFirstVisibleTreeNodeInfo));
		// TODO
	}
	
	@Override
	public int getFirstPosition() {
		return this.treeView.getFirstVisiblePosition();
	}
	
	@Override
	public GcgTreeNodeInfo getFirstVisibleTreeNodeInfo() {
		return this.treeViewAdapter.getVisibleTreeNodeInfo(getFirstPosition());
	}

    protected void activateView() {
        super.activateView();
        this.treeView = (GcgTreeView) findViewById(R.id.gcg_tree_view);
        this.treeView.setAdapter(this.treeViewAdapter);
        if(this.rightMenuLayout != null) {
            LinearLayout theViewFlipperBody = (LinearLayout) getGcgActivity().findViewById(R.id.gcg_view_flipper__body);
            LinearLayout theExistingTreeViewRightMenu = (LinearLayout) getGcgActivity().findViewById(R.id.tree_view__right_menu);
            if(theExistingTreeViewRightMenu != null) {
                theViewFlipperBody.removeView(theExistingTreeViewRightMenu);
            }
            theViewFlipperBody.addView(this.rightMenuLayout, new LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT, android.view.ViewGroup.LayoutParams.MATCH_PARENT));
        }
    }

}
