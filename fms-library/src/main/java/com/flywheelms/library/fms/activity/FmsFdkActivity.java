package com.flywheelms.library.fms.activity;

import android.content.Intent;

import com.flywheelms.library.fdk.activity.FdkActivity;
import com.flywheelms.library.fmm.enumerator.FmmNodeTransactionType;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.transaction.FmmDataRefreshNotice;
import com.flywheelms.library.fms.helper.FmsActivityHelper;
import com.flywheelms.library.gcg.context.GcgNavigationTarget;

import java.util.ArrayList;
import java.util.Hashtable;

public abstract class FmsFdkActivity extends FdkActivity {

    protected ArrayList<FmmDataRefreshNotice> dataRefreshList;
    protected ArrayList<FmmDataRefreshNotice> parentDataRefreshList;
    protected Hashtable<String, FmmNodeTransactionType> modifiedFmmNodeIdList = new Hashtable<String, FmmNodeTransactionType>();
    protected Hashtable<String, FmmNodeTransactionType> queuedChildModifiedFmmNodeIdTable;

    public FmsFdkActivity(String anInitialHelpContextUrlString) {
        super(anInitialHelpContextUrlString);
    }

    @Override
    public void onPostResume() {
        super.onPostResume();
        if(this.dataRefreshList != null) {
            refreshDataDisplay();
            this.dataRefreshList = null;
        }
    }

    @Override
    protected void onActivityResult(int aRequestCode, int aResultCode, Intent anIntent) {
        if(anIntent == null) {
            return;
        }
        this.queuedChildModifiedFmmNodeIdTable = FmsActivityHelper.getModifiedNodeHashTable(anIntent);
        if(aResultCode != GcgNavigationTarget.request_code__NAVIGATE) {
            this.queuedChildModifiedFmmNodeIdTable.putAll(FmsActivityHelper.getModifiedNodeHashTable(anIntent));
            updateDataRefreshInfo(anIntent);
            super.onActivityResult(aRequestCode, aResultCode, anIntent);
        } else {
            updateDataModificationListForContextNavigation(anIntent);
            processGcgApplicationContextNavigationIntent(anIntent);
        }
    }

    private void processDataRefreshNoticeList() {
        this.dataRefreshList = new ArrayList<FmmDataRefreshNotice>();
    }

    protected void updateDataModificationListForContextNavigation(Intent anIntent) {
        Intent theIntent = anIntent;
        if(anIntent == null) {
            if(this.modifiedFmmNodeIdList.size() < 1) {
                return;
            }
            theIntent = new Intent();
        }
        Hashtable<String, FmmNodeTransactionType> theModifiedNodeIdTable = FmsActivityHelper.getModifiedNodeHashTable(theIntent);
        if(theModifiedNodeIdTable.size() > 0) {
            this.modifiedFmmNodeIdList.putAll(theModifiedNodeIdTable);
            theIntent.putExtra(FmsActivityHelper.bundle_key__MODIFIED_TREE_NODE__LIST, FmsActivityHelper.getSerializedModifiedNodeTable(this.modifiedFmmNodeIdList));
        }
    }

    protected String getSerializedDataRefreshNoticeList() {
        return FmsActivityHelper.getSerializedModifiedNodeTable(this.modifiedFmmNodeIdList);
    }

    protected boolean hasParentDataRefreshList() {
        return this.parentDataRefreshList != null;
    }

    protected boolean hasDataRefreshList() {
        return this.modifiedFmmNodeIdList.size() > 0;
    }

    protected String getSerializedParentDataRefreshNoticeList() {
        String theString = "";
        for(@SuppressWarnings("unused") FmmDataRefreshNotice theDataRefreshNotice : this.parentDataRefreshList) {
            // TODO - unfinished
        }
        return theString;
    }

    public Hashtable<String, FmmNodeTransactionType> getModifiedFmmNodeIdTable() {
        return this.modifiedFmmNodeIdList;
    }

    public void setModifiedFmmNodeIdTable(Hashtable<String, FmmNodeTransactionType> aLocallyModifiedFmmNodeIdTable) {
        this.modifiedFmmNodeIdList = aLocallyModifiedFmmNodeIdTable;
    }

    public Hashtable<String, FmmNodeTransactionType> getQueuedChildModifiedFmmNodeIdTable() {
        return this.queuedChildModifiedFmmNodeIdTable;
    }

    public void setQueuedChildModifiedFmmNodeIdTable(Hashtable<String, FmmNodeTransactionType> aChildModifiedFmmNodeIdTable) {
        this.queuedChildModifiedFmmNodeIdTable = aChildModifiedFmmNodeIdTable;
    }

    public ArrayList<FmmDataRefreshNotice> getDataRefreshList() {
        return this.dataRefreshList;
    }

    public void setDataRefreshList(ArrayList<FmmDataRefreshNotice> aDataRefreshList) {
        this.dataRefreshList = aDataRefreshList;
    }

    public ArrayList<FmmDataRefreshNotice> getParentDataRefreshList() {
        return this.parentDataRefreshList;
    }

    public void seParenttDataRefreshList(ArrayList<FmmDataRefreshNotice> aDataRefreshList) {
        this.parentDataRefreshList = aDataRefreshList;
    }

    public void updateParentDataRefreshList(FmmDataRefreshNotice aDataRefreshNotice) {
        if(this.parentDataRefreshList == null) {
            processDataRefreshNoticeList();
        }
        this.parentDataRefreshList.add(aDataRefreshNotice);
    }

    protected FmmNodeDefinition getDisplayedFmmNodeDefinition() {
        return null;
    }

    @Override
    protected int getBreadcrumbDrawableResourceId() {
        return getDisplayedFmmNodeDefinition().getLabelDrawableResourceId();
    }

    @Override
    protected String getBreadcrumbTargetNodeIdString() {
        return null;
    }
}
