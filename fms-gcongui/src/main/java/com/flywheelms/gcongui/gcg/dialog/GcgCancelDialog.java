/* @(#)GcgCancelDialog.java
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
package com.flywheelms.gcongui.gcg.dialog;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.flywheelms.gcongui.R;
import com.flywheelms.gcongui.gcg.activity.GcgActivity;

public abstract class GcgCancelDialog  extends GcgDialog {

    protected Button buttonCancel;

    public GcgCancelDialog(GcgActivity aGcgActivity) {
        super(aGcgActivity);
    }

    public GcgCancelDialog(
            GcgActivity aGcgActivity,
            String aTargetDetail,
            String aMessageString ) {
        super(aGcgActivity, aTargetDetail, aMessageString);
    }

    @Override
    protected void initializeDialogBody() {
        this.dialogBodyView = (ViewGroup) inflateDialogBody(getDialogBodyLayoutResourceId());
        this.buttonCancel = (Button) this.dialogBodyView.findViewById(R.id.button__cancel);
        this.buttonCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                GcgCancelDialog.this.onClickButtonCancel();
            }
        });
        wrappedButtonSetup();
        customButtonSetup();
    }

    protected void wrappedButtonSetup() { }

    protected void customButtonSetup() { }

    protected int getDialogBodyLayoutResourceId() {
        return R.layout.gcg__dialog_body__cancel;
    }

    protected void onClickButtonCancel() {
        this.gcgActivity.stopDialog();
    }

}
