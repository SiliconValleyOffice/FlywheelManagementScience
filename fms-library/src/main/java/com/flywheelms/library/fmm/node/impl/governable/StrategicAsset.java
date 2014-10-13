package com.flywheelms.library.fmm.node.impl.governable;

import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;

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

    // must override because StrategicAsset is an override of ProjectAsset
    @Override
    public FmmNodeDefinition getFmmNodeDefinition() {
        return FmmNodeDefinition.STRATEGIC_ASSET;
    }
}
