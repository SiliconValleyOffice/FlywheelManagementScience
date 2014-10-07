/* @(#)GcgTreeView.java
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

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.flywheelms.gcongui.R;

public class GcgTreeView extends ListView {

    private static final int default__EXPANDED_NODE_BUTTON__RESOURCE_ID = R.drawable.gcg__background_state_list__tree_view__collapsed_node_button;
    private static final int default__COLLAPSED_NODE_BUTTON__RESOURCE_ID = R.drawable.gcg__background_state_list__tree_view__expanded_node_button;
    private static final int default__NODE_ROW_INDENT = 0;
    private Drawable expandedNodeButtonDrawable;
    private Drawable collapsedNodeButtonDrawable;
    private int indentWidth = 0;
    private GcgTreeViewAdapter treeViewAdapter;
    private boolean collapsible;

    // TODO - TreeView control is taking up too much space when height is wrap-contents
    public GcgTreeView(final Context aContext, final AttributeSet anAttributeSet) {
        this(aContext, anAttributeSet, R.style.GcgTreeViewRowStyle);
    }

    public GcgTreeView(final Context aContext, final AttributeSet anAttributeSet, final int defStyle) {
        super(aContext, anAttributeSet);
        parseAttributes(aContext, anAttributeSet);
        setDivider(null);
        setDividerHeight(0);
    }

    private void parseAttributes(final Context aContext, final AttributeSet anAttributeSet) {
        final TypedArray theStyledAttributesArray =
                aContext.obtainStyledAttributes(anAttributeSet, R.styleable.GcgTreeViewList);
        if (this.expandedNodeButtonDrawable == null) {
            this.expandedNodeButtonDrawable = aContext.getResources().getDrawable(
                    default__COLLAPSED_NODE_BUTTON__RESOURCE_ID);
        }
        if (this.collapsedNodeButtonDrawable == null) {
            this.collapsedNodeButtonDrawable = aContext.getResources().getDrawable(
                    default__EXPANDED_NODE_BUTTON__RESOURCE_ID);
        }
        this.indentWidth = theStyledAttributesArray.getDimensionPixelSize(
                R.styleable.GcgTreeViewList_indent_width, default__NODE_ROW_INDENT);
        this.collapsible = theStyledAttributesArray.getBoolean(R.styleable.GcgTreeViewList_collapsible, true);
    }

    @Override
    public void setAdapter(final ListAdapter aListAdapter) {
        this.treeViewAdapter = (GcgTreeViewAdapter) aListAdapter;
        syncTreeViewAdapter();
        super.setAdapter(this.treeViewAdapter);
    }

    private void syncTreeViewAdapter() {
        this.treeViewAdapter.setCollapsedNodeButtonDrawable(this.collapsedNodeButtonDrawable);
        this.treeViewAdapter.setExpandedNodeButtonDrawable(this.expandedNodeButtonDrawable);
        this.treeViewAdapter.setIndentWidth(this.indentWidth);
        this.treeViewAdapter.setCollapsible(this.collapsible);
    }

    public void setCollapsible(final boolean bCollapsible) {
        this.collapsible = bCollapsible;
        syncTreeViewAdapter();
        this.treeViewAdapter.refresh();
    }

    public boolean isCollapsible() {
        return this.collapsible;
    }

}
