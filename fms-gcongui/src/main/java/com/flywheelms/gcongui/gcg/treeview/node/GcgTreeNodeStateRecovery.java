package com.flywheelms.gcongui.gcg.treeview.node;

import com.flywheelms.gcongui.gcg.interfaces.GcgSerialization;

import org.json.JSONException;
import org.json.JSONObject;

public class GcgTreeNodeStateRecovery implements GcgSerialization {

    private static final String key__TREE_NODE__OBJECT_ID = "gcg__tree_node__object_id";
    private static final String key__TREE_NODE__EXPANDED = "gcg__tree_node__expanded";
    private String treeNodeObjectId;
    private boolean expanded;

    public GcgTreeNodeStateRecovery(String aTreeNodeObjectId, boolean bExpanded) {
        this.treeNodeObjectId = aTreeNodeObjectId;
        this.expanded = bExpanded;
    }

    public GcgTreeNodeStateRecovery(JSONObject aJsonObject) {
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
