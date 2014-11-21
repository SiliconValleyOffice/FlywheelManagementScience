/* @(#)FmsActivity.java
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

package com.flywheelms.library.fms.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.MenuItem;

import com.flywheelms.gcongui.gcg.activity.GcgActivity;
import com.flywheelms.gcongui.gcg.context.GcgNavigationTarget;
import com.flywheelms.library.R;
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.FmmDatabaseService;
import com.flywheelms.library.fmm.enumerator.FmmNodeTransactionType;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.transaction.FmmDataRefreshNotice;
import com.flywheelms.library.fms.dialog.FmsDictionaryDialog;
import com.flywheelms.library.fms.helper.FmsActivityHelper;

import java.util.ArrayList;
import java.util.Hashtable;

public abstract class FmsActivity extends GcgActivity{

    protected ArrayList<FmmDataRefreshNotice> mDataRefreshList;
    protected ArrayList<FmmDataRefreshNotice> mParentDataRefreshList;
    protected Hashtable<String, FmmNodeTransactionType> mModifiedFmmNodeIdList = new Hashtable<String, FmmNodeTransactionType>();
    protected Hashtable<String, FmmNodeTransactionType> mQueuedChildModifiedFmmNodeIdTable;
    protected boolean mRefreshAllData = false;
    protected boolean mParentRefreshAllData = false;
    protected static FmmDatabaseService mFmmDatabaseService;  //  Just 1 for the whole app.  Each activity tries to start/bind.
    private ServiceConnection mFmmDatabaseServiceConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName aClassName, IBinder aService) {
            FmmDatabaseService.LocalBinder theLocalBinder = (FmmDatabaseService.LocalBinder) aService;
            FmsActivity.mFmmDatabaseService = theLocalBinder.getService();
        }

        public void onServiceDisconnected(ComponentName className) {
            FmsActivity.mFmmDatabaseService = null;
        }
    };

    public static FmmDatabaseService getFmmDatabaseService() {
        return FmsActivity.mFmmDatabaseService;
    }

    public static FmmDatabaseMediator getActiveDatabaseMediator() {
        return FmsActivity.mFmmDatabaseService == null ? null : FmmDatabaseMediator.getActiveMediator();
    }

    public FmsActivity getFmsActivity() {
        return this;
    }

    public FmsActivity(String anInitialHelpContextUrlString) {
        super(anInitialHelpContextUrlString);
    }

    protected String getFrameworkCode() {
        return "FMS";
    }

    @Override
    public void onResume() {
        super.onResume();
        bindFmmDatabaseService();
    }

    private void bindFmmDatabaseService() {
        Intent theIntent = new Intent(this, FmmDatabaseService.class);
        bindService(theIntent, mFmmDatabaseServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onPostResume() {
        super.onPostResume();
        if(this.mDataRefreshList != null || this.mRefreshAllData) {
            refreshDataDisplay();
            this.mDataRefreshList = null;
            this.mRefreshAllData = false;
        }
    }

    @Override
    protected void onActivityResult(int aRequestCode, int aResultCode, Intent anIntent) {
        if(anIntent == null) {
            return;
        }
        this.mQueuedChildModifiedFmmNodeIdTable = FmsActivityHelper.getModifiedNodeHashTable(anIntent);
        if(aResultCode != GcgNavigationTarget.request_code__NAVIGATE) {
            this.mQueuedChildModifiedFmmNodeIdTable.putAll(FmsActivityHelper.getModifiedNodeHashTable(anIntent));
            updateDataRefreshInfo(anIntent);
            super.onActivityResult(aRequestCode, aResultCode, anIntent);
        } else {
            updateDataModificationListForContextNavigation(anIntent);
            processGcgApplicationContextNavigationIntent(anIntent);
        }
    }

    private void initializeDataRefreshNoticeList() {
        this.mDataRefreshList = new ArrayList<FmmDataRefreshNotice>();
    }

    private void initializeParentDataRefreshNoticeList() {
        this.mParentDataRefreshList = new ArrayList<FmmDataRefreshNotice>();
    }

    protected void updateDataModificationListForContextNavigation(Intent anIntent) {
        Intent theIntent = anIntent;
        if(anIntent == null) {
            if(this.mModifiedFmmNodeIdList.size() < 1) {
                return;
            }
            theIntent = new Intent();
        }
        Hashtable<String, FmmNodeTransactionType> theModifiedNodeIdTable = FmsActivityHelper.getModifiedNodeHashTable(theIntent);
        if(theModifiedNodeIdTable.size() > 0) {
            this.mModifiedFmmNodeIdList.putAll(theModifiedNodeIdTable);
            theIntent.putExtra(FmsActivityHelper.bundle_key__MODIFIED_TREE_NODE__LIST, FmsActivityHelper.getSerializedModifiedNodeTable(this.mModifiedFmmNodeIdList));
        }
    }

    protected String getSerializedDataRefreshNoticeList() {
        return FmsActivityHelper.getSerializedModifiedNodeTable(this.mModifiedFmmNodeIdList);
    }

    protected boolean hasParentDataRefreshList() {
        return this.mParentDataRefreshList != null;
    }

    protected boolean hasDataRefreshList() {
        return this.mModifiedFmmNodeIdList.size() > 0;
    }

    protected String getSerializedParentDataRefreshNoticeList() {
        return FmsActivityHelper.getSerializedModifiedNodeTable(this.mModifiedFmmNodeIdList);
    }

    public Hashtable<String, FmmNodeTransactionType> getModifiedFmmNodeIdTable() {
        return this.mModifiedFmmNodeIdList;
    }

    public void setModifiedFmmNodeIdTable(Hashtable<String, FmmNodeTransactionType> aLocallyModifiedFmmNodeIdTable) {
        this.mModifiedFmmNodeIdList = aLocallyModifiedFmmNodeIdTable;
    }

    public Hashtable<String, FmmNodeTransactionType> getQueuedChildModifiedFmmNodeIdTable() {
        return this.mQueuedChildModifiedFmmNodeIdTable;
    }

    public void setQueuedChildModifiedFmmNodeIdTable(Hashtable<String, FmmNodeTransactionType> aChildModifiedFmmNodeIdTable) {
        this.mQueuedChildModifiedFmmNodeIdTable = aChildModifiedFmmNodeIdTable;
    }

    public ArrayList<FmmDataRefreshNotice> getDataRefreshList() {
        return this.mDataRefreshList;
    }

    public void setDataRefreshList(ArrayList<FmmDataRefreshNotice> aDataRefreshList) {
        this.mDataRefreshList = aDataRefreshList;
    }

    public ArrayList<FmmDataRefreshNotice> getmParentDataRefreshList() {
        return this.mParentDataRefreshList;
    }

    public void setmParentDataRefreshList(ArrayList<FmmDataRefreshNotice> aDataRefreshList) {
        this.mParentDataRefreshList = aDataRefreshList;
    }

    public void updateParentDataRefreshList(FmmDataRefreshNotice aDataRefreshNotice) {
        this.parentDataRefreshAll = true;  // TODO - HACK ALERT !!!
        if(this.mParentDataRefreshList == null) {
            initializeParentDataRefreshNoticeList();
        }
        this.mParentDataRefreshList.add(aDataRefreshNotice);
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

    public boolean isRefreshAllData() {
        return this.mRefreshAllData;
    }

    public void setRefreshAllData(boolean refreshAllData) {
        this.mRefreshAllData = refreshAllData;
    }

    public boolean isParentRefreshAllData() {
        return this.mParentRefreshAllData;
    }

    public void setParentRefreshAllData(boolean parentRefreshAllData) {
        this.mParentRefreshAllData = parentRefreshAllData;
    }

    protected int getActivityOptionsMenuResourceId() {
        return R.menu.fms__activity_menu__default;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem aMenuItem) {
        int i = aMenuItem.getItemId();
        if (i == android.R.id.home) {
            finish();
            return true;
        } else if (i == R.id.action__dictionary) {
            launchDictionary();
            return true;
        }
        return super.onOptionsItemSelected(aMenuItem);
    }

    public void launchDictionary() {
        this.startDialog(new FmsDictionaryDialog(this, getDisplayedFmmNodeDefinition(), getGcgFrame(), getGcgPerspective()));
    }
}
