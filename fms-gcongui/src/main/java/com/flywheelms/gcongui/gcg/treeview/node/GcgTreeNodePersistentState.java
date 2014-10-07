/* @(#)GcgTreeNodePersistentState.java
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

package com.flywheelms.gcongui.gcg.treeview.node;

import com.flywheelms.gcongui.gcg.interfaces.GcgSerialization;

import org.json.JSONException;
import org.json.JSONObject;

public class GcgTreeNodePersistentState implements GcgSerialization {

    private static final String key__TREE_NODE__OBJECT_ID = "gcg__tree_node__object_id";
    private static final String key__TREE_NODE__EXPANDED = "gcg__tree_node__expanded";
    private String treeNodeObjectId;
    private boolean expanded;

    public GcgTreeNodePersistentState(String aTreeNodeObjectId, boolean bExpanded) {
        this.treeNodeObjectId = aTreeNodeObjectId;
        this.expanded = bExpanded;
    }

    public GcgTreeNodePersistentState(GcgTreeNodeInfo aTreeNodeInfo) {
        this.treeNodeObjectId = aTreeNodeInfo.getObjectId();
        this.expanded = aTreeNodeInfo.isExpanded();
    }

    public GcgTreeNodePersistentState(JSONObject aJsonObject) {
        try {
            this.treeNodeObjectId = aJsonObject.getString(key__TREE_NODE__OBJECT_ID);
            this.expanded = aJsonObject.getBoolean(key__TREE_NODE__EXPANDED);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getTreeNodeObjectId() {
        return this.treeNodeObjectId;
    }

    public boolean isExpanded() {
        return this.expanded;
    }

    @Override
    public String getSerialized() {
        return getJsonObject().toString();
    }

    public JSONObject getJsonObject() {
        JSONObject theJsonObject = new JSONObject();
        try {
            theJsonObject.put(key__TREE_NODE__OBJECT_ID, getTreeNodeObjectId());
            theJsonObject.put(key__TREE_NODE__EXPANDED, isExpanded());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return theJsonObject;
    }

    @Override
    public boolean validateSerializationFormatVersion(String aString) {
        return true;
    }
}
