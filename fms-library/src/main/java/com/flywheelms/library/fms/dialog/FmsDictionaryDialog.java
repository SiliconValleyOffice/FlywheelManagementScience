/* @(#)FmsDictionaryDialog.java
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

package com.flywheelms.library.fms.dialog;

import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.flywheelms.gcongui.gcg.activity.GcgActivity;
import com.flywheelms.gcongui.gcg.container.GcgContainerTabbedLayout;
import com.flywheelms.gcongui.gcg.container.tabbed.GcgTabSpec;
import com.flywheelms.gcongui.gcg.context.GcgFrame;
import com.flywheelms.gcongui.gcg.dialog.GcgCancelDialog;
import com.flywheelms.gcongui.gcg.interfaces.GcgPerspective;
import com.flywheelms.gcongui.gcg.widget.GcgWidgetTextViewSummaryBox;
import com.flywheelms.library.R;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fms.widget.spinner.FmmFrameWidgetSpinner;
import com.flywheelms.library.fms.widget.spinner.FmmNounWidgetSpinner;
import com.flywheelms.library.fms.widget.spinner.FmmPerspectiveWidgetSpinner;

public class FmsDictionaryDialog extends GcgCancelDialog {

    private FmmNodeDefinition initialFmmNodeDefinition;
    private FmmNounWidgetSpinner nounDefinitionWidgetSpinner;
    private GcgWidgetTextViewSummaryBox nounDefinitionText;
    private GcgFrame initialFmmFrame;
    private FmmFrameWidgetSpinner frameDefinitionWidgetSpinner;
    private GcgWidgetTextViewSummaryBox frameDefinitionText;
    private GcgPerspective initialFmmPerspective;
    private FmmPerspectiveWidgetSpinner perspectiveDefinitionWidgetSpinner;
    private GcgWidgetTextViewSummaryBox perspectiveDefinitionText;
    private GcgContainerTabbedLayout tabbedLayout;

    public FmsDictionaryDialog(GcgActivity aGcgActivity, FmmNodeDefinition aNodeDefinition, GcgFrame aFrame, GcgPerspective aPerspective) {
        super(aGcgActivity);
        this.initialFmmNodeDefinition = aNodeDefinition;
        this.initialFmmFrame = aFrame;
        this.initialFmmPerspective = aPerspective;
        initialSetup();
        initializeNounTab();
        initializePerspectiveTab();
        initializeFrameTab();
        this.tabbedLayout.setCurrentTab(0);
    }

    @Override
    protected int getDialogWidth() {
        return 950;
    }

    private void initializeNounTab() {
        LinearLayout theLinearLayout = (LinearLayout) this.gcgActivity.getLayoutInflater().inflate(R.layout.flywheel_ms__noun_dictionary__tab, this.tabbedLayout, false);
        this.nounDefinitionText = (GcgWidgetTextViewSummaryBox) theLinearLayout.findViewById(R.id.noun__definition);
        this.nounDefinitionWidgetSpinner = (FmmNounWidgetSpinner) theLinearLayout.findViewById(R.id.noun__spinner);
        this.nounDefinitionWidgetSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                FmsDictionaryDialog.this.nounDefinitionText.setText(
                        FmsDictionaryDialog.this.nounDefinitionWidgetSpinner.getSelectedFmmNodeDefinition().getDictionaryDefinitionText());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        if(this.initialFmmNodeDefinition != null) {
            this.nounDefinitionWidgetSpinner.setSelectedFmmNodeDefinition(this.initialFmmNodeDefinition);
        } else {
            this.nounDefinitionWidgetSpinner.setSelection(0);
        }
        GcgTabSpec theGcgTabSpec = new GcgTabSpec(theLinearLayout, R.drawable.deckangl__noun, R.string.deckangl__noun, false);
        this.tabbedLayout.addTab(theGcgTabSpec);
    }

    private void initializePerspectiveTab() {
        LinearLayout theLinearLayout = (LinearLayout) this.gcgActivity.getLayoutInflater().inflate(R.layout.flywheel_ms__perspective_dictionary__tab, this.tabbedLayout, false);
        this.perspectiveDefinitionText = (GcgWidgetTextViewSummaryBox) theLinearLayout.findViewById(R.id.perspective__definition);
        this.perspectiveDefinitionWidgetSpinner = (FmmPerspectiveWidgetSpinner) theLinearLayout.findViewById(R.id.perspective__spinner);
        this.perspectiveDefinitionWidgetSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                FmsDictionaryDialog.this.perspectiveDefinitionText.setText(
                        FmsDictionaryDialog.this.perspectiveDefinitionWidgetSpinner.getSelectedFmmPerspective().getDictionaryDefinitionString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        if(this.initialFmmNodeDefinition != null) {
            this.perspectiveDefinitionWidgetSpinner.setSelectedGcgPerspective(this.initialFmmPerspective);
        } else {
            this.perspectiveDefinitionWidgetSpinner.setSelection(0);
        }
        GcgTabSpec theGcgTabSpec = new GcgTabSpec(theLinearLayout, R.drawable.gcg__perspective, R.string.gcg__perspective, false);
        this.tabbedLayout.addTab(theGcgTabSpec);
    }

    private void initializeFrameTab() {
        LinearLayout theLinearLayout = (LinearLayout) this.gcgActivity.getLayoutInflater().inflate(R.layout.flywheel_ms__frame_dictionary__tab, this.tabbedLayout, false);
        this.frameDefinitionText = (GcgWidgetTextViewSummaryBox) theLinearLayout.findViewById(R.id.frame__definition);
        this.frameDefinitionWidgetSpinner = (FmmFrameWidgetSpinner) theLinearLayout.findViewById(R.id.frame__spinner);
        this.frameDefinitionWidgetSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                FmsDictionaryDialog.this.frameDefinitionText.setText(
                        FmsDictionaryDialog.this.frameDefinitionWidgetSpinner.getSelectedFmmFrame().getDictionaryDefinitionString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        if(this.initialFmmNodeDefinition != null) {
            this.frameDefinitionWidgetSpinner.setSelectedGcgFrame(this.initialFmmFrame);
        } else {
            this.frameDefinitionWidgetSpinner.setSelection(0);
        }
        GcgTabSpec theGcgTabSpec = new GcgTabSpec(theLinearLayout, R.drawable.gcg__frame__32, R.string.gcg__frame, false);
        this.tabbedLayout.addTab(theGcgTabSpec);
    }

    protected int getDialogTitleIconResourceId() {
        return R.drawable.fms__dictionary;
    }

    @Override
    protected int getDialogTitleStringResourceId() {
        return R.string.flywheel_ms__dictionary;
    }

    @Override
    protected int getDialogBodyLayoutResourceId() {
        return R.layout.flywheel_ms__dictionary__dialog;
    }

    protected  void initializeDialogBody() {
        super.initializeDialogBody();
        this.tabbedLayout = (GcgContainerTabbedLayout) this.dialogBodyView.findViewById(R.id.tabbed_layout);
        this.tabbedLayout.setup();
    }
}