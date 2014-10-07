/* @(#)GcgTreeNodeStateBundle.java
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

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class GcgTreeNodeStateBundle {

    protected static final ArrayList<GcgTreeNodeStateBundle> VALUES = new ArrayList<GcgTreeNodeStateBundle>();

    public static ArrayList<GcgTreeNodeStateBundle> values() {
        return GcgTreeNodeStateBundle.VALUES;
    }

    private static final String key__TREE_NODE_PERSISTENT_STATE__SET = "key__tree_node_persistent_state__set";

    public static HashMap<String, GcgTreeNodePersistentState> readGcgTreeNodeStatePreferences(Activity anActivity, String aBundleName) {
        HashMap<String, GcgTreeNodePersistentState> thePersistentStateMap = new HashMap<String, GcgTreeNodePersistentState>();
        SharedPreferences theSharedPreferences = anActivity.getSharedPreferences(aBundleName, Context.MODE_PRIVATE);
        Set<String> theSerializedPersistentStateList = theSharedPreferences.getStringSet(key__TREE_NODE_PERSISTENT_STATE__SET, null);
        if(theSerializedPersistentStateList != null) {
            for (String theString : theSerializedPersistentStateList) {
                try {
                    GcgTreeNodePersistentState thePersistentState = new GcgTreeNodePersistentState(new JSONObject(theString));
                    thePersistentStateMap.put(thePersistentState.getTreeNodeObjectId(), thePersistentState);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return thePersistentStateMap;
    }

    public static void writeGcgTreeNodeStatePreferences(Activity anActivity, String aBundleName, HashMap<String, GcgTreeNodePersistentState> aPersistentStateMap) {
        SharedPreferences theSharedPreferences = anActivity.getSharedPreferences(aBundleName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = theSharedPreferences.edit();
        editor.clear();
        editor.commit();
        LinkedHashSet<String> theStringSet = new LinkedHashSet<String>();
        for(GcgTreeNodePersistentState thePersistentState : aPersistentStateMap.values()) {
            theStringSet.add(thePersistentState.getSerialized());
        }
        editor.putStringSet(GcgTreeNodeStateBundle.key__TREE_NODE_PERSISTENT_STATE__SET, theStringSet);
        editor.commit();
    }

    private final String key;

    public GcgTreeNodeStateBundle(String aKey) {
        this.key = aKey;
    }

    public String getKey() {
        return this.key;
    }
}
