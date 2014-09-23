/* @(#)FmmHeadlineNodePopupMenu.java
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

package com.flywheelms.library.fms.popup_menu;

import android.annotation.SuppressLint;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.flywheelms.gcongui.gcg.treeview.node.GcgTreeNodeInfo;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;

/*
 * 
 * TODO - implement our own GcgPopup super class with the following features
   - numeric keyboard shortcuts; 1 will select item #1 on the list, 3 item #3 and etc
   - menu view will be sized to show all menu items
   - if all menu items cannot be shown, keep the scroll bar(s) visible
 *
 */
@SuppressLint("ViewConstructor")
public class FmmHeadlineNodePopupMenu extends PopupMenu {

	protected final FmmHeadlineNode parentHeadlineNode;
	protected final FmmHeadlineNode launchHeadlineNode;
	protected final FmmHeadlineNodePopupListener fmmNodePopupListener;
	protected final GcgTreeNodeInfo launchTreeNodeInfo;
	protected final int launchNodeSequence;
	protected final int launchNodeChildCount;

	public FmmHeadlineNodePopupMenu(
			FmmHeadlineNodePopupListener aNodePopupListener,
			final View aView,
			FmmHeadlineNode aHeadlineNode,
			FmmHeadlineNode aParentHeadlineNode,
			final GcgTreeNodeInfo aLaunchTreeNodeInfo,
			int aLaunchNodeSequence,
			int aLaunchNodeChildNodeCount ) {
		super(aView.getContext(), aView);
		this.launchHeadlineNode = aHeadlineNode;
		this.parentHeadlineNode = aParentHeadlineNode;
		this.launchTreeNodeInfo = aLaunchTreeNodeInfo;
		this.launchNodeSequence = aLaunchNodeSequence;
		this.launchNodeChildCount = aLaunchNodeChildNodeCount;
		this.fmmNodePopupListener = aNodePopupListener;
		this.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem aMenuItem) {
				FmmHeadlineNodePopupMenu.this.fmmNodePopupListener.onPopupMenuItemClick(
						aView,
						aMenuItem,
						FmmHeadlineNodePopupMenu.this.launchHeadlineNode,
						FmmHeadlineNodePopupMenu.this.parentHeadlineNode,
						FmmHeadlineNodePopupMenu.this.launchTreeNodeInfo,
						FmmHeadlineNodePopupMenu.this.launchNodeSequence,
						FmmHeadlineNodePopupMenu.this.launchNodeChildCount );
				return true;
			}
		});
		this.setOnDismissListener(new OnDismissListener() {
			
			@Override
			public void onDismiss(PopupMenu menu) {
				FmmHeadlineNodePopupMenu.this.fmmNodePopupListener.resetRowBackground(aView);
			}
		});
	}

    public FmmHeadlineNodePopupMenu(
            FmmHeadlineNodePopupListener aNodePopupListener,
            final View aView,
            FmmHeadlineNode aHeadlineNode,
            FmmHeadlineNode aParentHeadlineNode,
            final GcgTreeNodeInfo aLaunchTreeNodeInfo,
            int aLaunchNodeChildNodeCount ) {
        super(aView.getContext(), aView);
        this.launchHeadlineNode = aHeadlineNode;
        this.parentHeadlineNode = aParentHeadlineNode;
        this.launchTreeNodeInfo = aLaunchTreeNodeInfo;
        this.launchNodeSequence = 0;
        this.launchNodeChildCount = aLaunchNodeChildNodeCount;
        this.fmmNodePopupListener = aNodePopupListener;
        this.setOnMenuItemClickListener(new OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem aMenuItem) {
                FmmHeadlineNodePopupMenu.this.fmmNodePopupListener.onPopupMenuItemClick(
                        aView,
                        aMenuItem,
                        FmmHeadlineNodePopupMenu.this.launchHeadlineNode,
                        FmmHeadlineNodePopupMenu.this.parentHeadlineNode,
                        FmmHeadlineNodePopupMenu.this.launchTreeNodeInfo,
                        FmmHeadlineNodePopupMenu.this.launchNodeSequence,
                        FmmHeadlineNodePopupMenu.this.launchNodeChildCount );
                return true;
            }
        });
        this.setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss(PopupMenu menu) {
                FmmHeadlineNodePopupMenu.this.fmmNodePopupListener.resetRowBackground(aView);
            }
        });
    }

}
