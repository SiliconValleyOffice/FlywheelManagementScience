package com.flywheelms.library.fms.dialog;

import android.widget.TextView;

import com.flywheelms.gcongui.gcg.activity.GcgActivity;
import com.flywheelms.gcongui.gcg.dialog.GcgCancelOkDialog;
import com.flywheelms.library.R;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.repository.FmmConfiguration;
import com.flywheelms.library.fms.widget.list_view.FmmConfigurationWidgetListView;

public class ConfirmFmmDestruction  extends GcgCancelOkDialog {

    private FmmConfigurationWidgetListView configurationListView;
    private FmmConfiguration fmmConfiguration;

    public ConfirmFmmDestruction(GcgActivity aLibraryActivity, FmmConfigurationWidgetListView aConfigurationListView, FmmConfiguration anFmmConfiguration) {
        super(aLibraryActivity);
        this.configurationListView = aConfigurationListView;
        this.fmmConfiguration = anFmmConfiguration;
        initialSetup();
    }

    @Override
    protected int getDialogTitleStringResourceId() {
        return R.string.fmm_repository__destroy;
    }

    @Override
    protected int getDialogTitleIconResourceId() {
        return FmmNodeDefinition.FMM_CONFIGURATION.getDialogDrawableResourceId();
    }

    @Override
    protected void onClickButtonOk() {
        this.configurationListView.destroyFmmRepository(this.fmmConfiguration);
        this.gcgActivity.stopDialog();
    }

    @Override
    protected void initializeDialogBody() {
        super.initializeDialogBody();
        ((TextView) this.dialogBodyView.findViewById(R.id.message)).setText("Are you SURE ???.");
    }

    @Override
    protected int getDialogBodyLayoutResourceId() {
        return R.layout.gcg__dialog_layout__message__cancel_ok;
    }

}
