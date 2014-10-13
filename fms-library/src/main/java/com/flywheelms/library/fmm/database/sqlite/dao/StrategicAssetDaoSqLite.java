package com.flywheelms.library.fmm.database.sqlite.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.flywheelms.library.fmm.meta_data.CompletableNodeMetaData;
import com.flywheelms.library.fmm.meta_data.IdNodeMetaData;
import com.flywheelms.library.fmm.meta_data.StrategicAssetMetaData;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.governable.StrategicAsset;

import java.util.HashMap;

public class StrategicAssetDaoSqLite extends CompletableNodeDaoSqLite<StrategicAsset> {

    private static StrategicAssetDaoSqLite singleton;

    public static StrategicAssetDaoSqLite getInstance() {
        if(StrategicAssetDaoSqLite.singleton == null) {
            StrategicAssetDaoSqLite.singleton = new StrategicAssetDaoSqLite();
        }
        return StrategicAssetDaoSqLite.singleton;
    }

    @Override
    public FmmNodeDefinition getFmmNodeDefinition() {
        return FmmNodeDefinition.PROJECT_ASSET;
    }

    @Override
    protected void buildColumnIndexMap(Cursor aCursor) {
        super.buildColumnIndexMap(aCursor);
        putColumnIndexMapEntry(this.columnIndexMap, aCursor, StrategicAssetMetaData.column_PROJECT_ID);
        putColumnIndexMapEntry(this.columnIndexMap, aCursor, StrategicAssetMetaData.column_IS_STRATEGIC);
        putColumnIndexMapEntry(this.columnIndexMap, aCursor, CompletableNodeMetaData.column_SEQUENCE);
    }

    @Override
    protected void getColumnValues(HashMap<String, Integer> aHashMap, Cursor aCursor, StrategicAsset aStrategicAsset) {
        super.getColumnValues(aHashMap, aCursor, aStrategicAsset);
        aStrategicAsset.setProjectNodeIdString(aCursor.getString(aHashMap.get(StrategicAssetMetaData.column_PROJECT_ID)));
        aStrategicAsset.setStrategic(aCursor.getInt(aHashMap.get(StrategicAssetMetaData.column_IS_STRATEGIC)));
        aStrategicAsset.setSequence(aCursor.getInt(aHashMap.get(CompletableNodeMetaData.column_SEQUENCE)));
    }

    @Override
    public ContentValues buildContentValues(StrategicAsset aStrategicAsset) {
        ContentValues theContentValues = super.buildContentValues(aStrategicAsset);
        theContentValues.put(StrategicAssetMetaData.column_PROJECT_ID, aStrategicAsset.getProjectNodeIdString());
        theContentValues.put(StrategicAssetMetaData.column_IS_STRATEGIC, aStrategicAsset.getStrategicAsInt());
        theContentValues.put(CompletableNodeMetaData.column_SEQUENCE, aStrategicAsset.getSequence());
        return theContentValues;
    }

    @Override
    public ContentValues buildUpdateContentValues(StrategicAsset aStrategicAsset) {
        return buildContentValues(aStrategicAsset);
    }

    @Override
    protected StrategicAsset getNextObjectFromCursor(Cursor aCursor) {
        StrategicAsset theStrategicAsset = null;
        theStrategicAsset = new StrategicAsset(aCursor.getString(this.columnIndexMap.get(IdNodeMetaData.column_ID)) );
        getColumnValues(this.columnIndexMap, aCursor, theStrategicAsset);
        return theStrategicAsset;
    }

}
