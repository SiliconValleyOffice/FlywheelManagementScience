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

package com.flywheelms.library.gcg.treeview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.flywheelms.library.R;

public class GcgTreeView extends ListView {
	
    private static final int default__EXPANDED_NODE_BUTTON_RESOURCE_ID = R.drawable.gcg__background_state_list__tree_view__collapsed_node_button;
    private static final int default__COLLAPSED_NODE_BUTTON_RESOURCE_ID = R.drawable.gcg__background_state_list__tree_view__expanded_node_button;
    private static final int default__NODE_ROW_INDENT = 0;
    private static final int default__NODE_ROW_GRAVITY = Gravity.LEFT | Gravity.CENTER_VERTICAL;
    private Drawable expandedNodeButtonDrawable;
    private Drawable collapsedNodeButtonDrawable;
    private Drawable nodeRowBackgroundDrawable;
    private Drawable indicatorBackgroundDrawable;
    private int indentWidth = 0;
    private int indicatorGravity = 0;
    private GcgTreeViewAdapter treeViewAdapter;
    private boolean collapsible;
    private boolean handleTrackballPress;

    // TODO - TreeView control is taking up too much space when height is wrap-contents
    public GcgTreeView(final Context aContext, final AttributeSet anAttributeSet) {
        this(aContext, anAttributeSet, R.style.GcgTreeViewRowStyle);
    }

    public GcgTreeView(final Context aContext) {
        this(aContext, null);
    }

    public GcgTreeView(final Context aContext, final AttributeSet anAttributeSet,
            final int defStyle) {
        super(aContext, anAttributeSet, defStyle);
        parseAttributes(aContext, anAttributeSet);
    }

    private void parseAttributes(final Context aContext, final AttributeSet anAttributeSet) {
        final TypedArray theStyledAttributesArray =
        		aContext.obtainStyledAttributes(anAttributeSet, R.styleable.GcgTreeViewList);
        this.expandedNodeButtonDrawable = theStyledAttributesArray.getDrawable(R.styleable.GcgTreeViewList_src_expanded);
        if (this.expandedNodeButtonDrawable == null) {
        	this.expandedNodeButtonDrawable = aContext.getResources().getDrawable(
                    default__COLLAPSED_NODE_BUTTON_RESOURCE_ID);
        }
        this.collapsedNodeButtonDrawable = theStyledAttributesArray
                .getDrawable(R.styleable.GcgTreeViewList_src_collapsed);
        if (this.collapsedNodeButtonDrawable == null) {
        	this.collapsedNodeButtonDrawable = aContext.getResources().getDrawable(
                    default__EXPANDED_NODE_BUTTON_RESOURCE_ID);
        }
        this.indentWidth = theStyledAttributesArray.getDimensionPixelSize(
                R.styleable.GcgTreeViewList_indent_width, default__NODE_ROW_INDENT);
        this.indicatorGravity = theStyledAttributesArray.getInteger(
                R.styleable.GcgTreeViewList_indicator_gravity, default__NODE_ROW_GRAVITY);
        this.indicatorBackgroundDrawable = theStyledAttributesArray
                .getDrawable(R.styleable.GcgTreeViewList_indicator_background);
        this.nodeRowBackgroundDrawable = theStyledAttributesArray
                .getDrawable(R.styleable.GcgTreeViewList_row_background);
        this.collapsible = theStyledAttributesArray.getBoolean(R.styleable.GcgTreeViewList_collapsible, true);
        this.handleTrackballPress = theStyledAttributesArray.getBoolean(
                R.styleable.GcgTreeViewList_handle_trackball_press, true);
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
//        this.treeViewAdapter.setIndicatorBackgroundDrawable(this.indicatorBackgroundDrawable);
        this.treeViewAdapter.setNodeRowBackgroundDrawable(this.nodeRowBackgroundDrawable);
        this.treeViewAdapter.setCollapsible(this.collapsible);
        if (this.handleTrackballPress) {
            setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(final AdapterView< ? > parent,
                        final View view, final int position, final long id) {
                    GcgTreeView.this.treeViewAdapter.handleItemClick(view.getTag());
                }
            });
        } else {
            setOnClickListener(null);
        }
    }

    public void setExpandedNodeButtonDrawable(final Drawable aDrawable) {
        this.expandedNodeButtonDrawable = aDrawable;
        syncTreeViewAdapter();
        this.treeViewAdapter.refresh();
    }

    public void setCollapsedNodeButtonDrawable(final Drawable aDrawable) {
        this.collapsedNodeButtonDrawable = aDrawable;
        syncTreeViewAdapter();
        this.treeViewAdapter.refresh();
    }

    public void setRowBackgroundDrawable(final Drawable aDrawable) {
        this.nodeRowBackgroundDrawable = aDrawable;
        syncTreeViewAdapter();
        this.treeViewAdapter.refresh();
    }

    public void setIndicatorBackgroundDrawable(final Drawable aDrawable) {
        this.indicatorBackgroundDrawable = aDrawable;
        syncTreeViewAdapter();
        this.treeViewAdapter.refresh();
    }

    public void setIndentWidth(final int anIndentWidth) {
        this.indentWidth = anIndentWidth;
        syncTreeViewAdapter();
        this.treeViewAdapter.refresh();
    }

    public void setIndicatorGravity(final int anIndicatorGravity) {
        this.indicatorGravity = anIndicatorGravity;
        syncTreeViewAdapter();
        this.treeViewAdapter.refresh();
    }

    public void setCollapsible(final boolean bCollapsible) {
        this.collapsible = bCollapsible;
        syncTreeViewAdapter();
        this.treeViewAdapter.refresh();
    }

    public void setHandleTrackballPress(final boolean bHandleTrackballPress) {
        this.handleTrackballPress = bHandleTrackballPress;
        syncTreeViewAdapter();
        this.treeViewAdapter.refresh();
    }

    public Drawable getExpandedDrawable() {
        return this.expandedNodeButtonDrawable;
    }

    public Drawable getCollapsedDrawable() {
        return this.collapsedNodeButtonDrawable;
    }

    public Drawable getRowBackgroundDrawable() {
        return this.nodeRowBackgroundDrawable;
    }

    public Drawable getIndicatorBackgroundDrawable() {
        return this.indicatorBackgroundDrawable;
    }

    public int getIndentWidth() {
        return this.indentWidth;
    }

    public int getIndicatorGravity() {
        return this.indicatorGravity;
    }

    public boolean isCollapsible() {
        return this.collapsible;
    }

    public boolean isHandleTrackballPress() {
        return this.handleTrackballPress;
    }

}
