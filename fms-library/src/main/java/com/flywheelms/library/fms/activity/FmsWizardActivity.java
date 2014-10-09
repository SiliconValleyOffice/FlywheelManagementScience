/* @(#)FmsWizardActivity.java
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

import android.content.Intent;
import android.view.MenuItem;

import com.flywheelms.gcongui.gcg.context.GcgNavigationTarget;
import com.flywheelms.gcongui.gcg.wizard.GcgWizardActivity;
import com.flywheelms.library.R;
import com.flywheelms.library.fmm.enumerator.FmmNodeTransactionType;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.transaction.FmmDataRefreshNotice;
import com.flywheelms.library.fms.dialog.FmsDictionaryDialog;
import com.flywheelms.library.fms.helper.FmsActivityHelper;

import java.util.ArrayList;
import java.util.Hashtable;

public abstract class FmsWizardActivity extends GcgWizardActivity {

    protected ArrayList<FmmDataRefreshNotice> dataRefreshList;
    protected ArrayList<FmmDataRefreshNotice> parentDataRefreshList;
    protected Hashtable<String, FmmNodeTransactionType> modifiedFmmNodeIdList = new Hashtable<String, FmmNodeTransactionType>();
    protected Hashtable<String, FmmNodeTransactionType> queuedChildModifiedFmmNodeIdTable;

    public FmsWizardActivity(String anInitialHelpContextUrlString) {
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

    protected int getActivityOptionsMenuResourceId() {
        return R.menu.fms__wizard_menu__default;
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
        this.startDialog(new FmsDictionaryDialog(this, getDisplayedFmmNodeDefinition()));
    }
}
