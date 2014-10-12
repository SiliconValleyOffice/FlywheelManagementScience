package com.flywheelms.library.fmm.node.impl.governable;

import com.flywheelms.library.fmm.node.NodeId;

import org.json.JSONObject;

public class StrategicAsset extends ProjectAsset {

    public StrategicAsset() {
        super();
        setStrategic(true);
    }

    public StrategicAsset(JSONObject aJsonObject) {
        super(aJsonObject);
        setStrategic(true);
    }

    public StrategicAsset(NodeId aNodeId) {
        super(aNodeId);
        setStrategic(true);
    }

    public StrategicAsset(String anExistingNodeIdString) {
        super(anExistingNodeIdString);
        setStrategic(true);
    }
}
