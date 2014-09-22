/* @(#)GcgActivityHelper.java
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
package com.flywheelms.library.gcg.helper;

import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.provider.ContactsContract;

import com.flywheelms.library.gcg.activity.GcgActivity;

import java.io.File;

public class GcgActivityHelper {

    public static final String bundle_key__INITIAL_FRAME_TO_DISPLAY = "InitialFrameToDisplay";
    public static final String bundle_key__INITIAL_PERSPECTIVE_TO_DISPLAY = "InitialPerspectiveToDisplay";
    public static final String bundle_key__LIST_ACTION_LABEL = "ListActionLabel";
    public static final String bundle_key__MUST_SELECT_DATA_SOURCE = "MustSelectDataSource";
    public static final String bundle_key__GCG__APPLICATION_CONTEXT = "GcgApplicationContext";
    public static final String bundle_key__DATA_REFRESH__ALL = "DataRefreshAll";
    public static final String bundle_key__DATA_REFRESH__NOTICE_LIST = "DataRefreshNoticeList";
    public static final int request_code__ANDROID_CONTACT_PICKER = 100;
    public static final int request_code__ANDROID_CONTACT_EDITOR = 200;
    public static final int request_code__PRINT = 111;
    public static final int request_code__PRINTER_INSTALLATION = 112;

    public static void startContactPicker(GcgActivity aParentActivity ) {
        aParentActivity.startBlueActivityStatusAnimation();
        Intent theIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        aParentActivity.startActivityForResult(theIntent, request_code__ANDROID_CONTACT_PICKER);
    }

    public static void startContactPicker(GcgActivity aParentActivity, int aRequestCode) {
        aParentActivity.startBlueActivityStatusAnimation();
        Intent theIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        aParentActivity.startActivityForResult(theIntent, aRequestCode);
    }

    public static void startContactEditor(GcgActivity aParentActivity, long anId, int aRequestCode) {
        aParentActivity.startBlueActivityStatusAnimation();
        Intent theIntent = new Intent(Intent.ACTION_EDIT);
        Uri theContactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, anId);
        theIntent.setData(theContactUri);
        theIntent.putExtra("finishActivityOnSaveCompleted", true);  // Sets the special extended data for navigation
        aParentActivity.startActivityForResult(theIntent, aRequestCode);
    }

    public static void startPrintJob(GcgActivity aParentActivity, File aFile, String aMimeType) {
        aParentActivity.startBlueActivityStatusAnimation();
        Uri theUri = Uri.fromFile(aFile);
        Intent theIntent = new Intent ("org.androidprinting.intent.action.PRINT");
        theIntent.setDataAndType( theUri, aMimeType );
        if(intentServiceIsAvailable(aParentActivity, theIntent)) {
            aParentActivity.startActivityForResult(theIntent, request_code__PRINT);
        } else {
            GcgHelper.makeToast("ERROR: No printing application installed");
            installHpEprint(aParentActivity);
            if(intentServiceIsAvailable(aParentActivity, theIntent)) {
                aParentActivity.startActivityForResult(theIntent, request_code__PRINT);
            }
        }
    }

    public static boolean intentServiceIsAvailable(GcgActivity aParentActivity, Intent anIntent) {
        ResolveInfo theResolveInfo = aParentActivity.getPackageManager().resolveActivity(
                anIntent,
                0 );
        return theResolveInfo != null;
    }

    public static void installHpEprint(GcgActivity aParentActivity) {
        aParentActivity.startBlueActivityStatusAnimation();
        installMarketplaceApplication(aParentActivity, "com.hp.android.print&hl=en");
    }

    public static void installMarketplaceApplication(GcgActivity aParentActivity, String anApplicationName) {
        aParentActivity.startBlueActivityStatusAnimation();
        Intent theIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + anApplicationName));
        aParentActivity.startActivityForResult(theIntent, request_code__PRINTER_INSTALLATION);
    }

}
