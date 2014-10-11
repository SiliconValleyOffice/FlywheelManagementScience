package com.flywheelms.gcongui.gcg.dialog;

import com.flywheelms.gcongui.R;
import com.flywheelms.gcongui.gcg.GcgApplication;
import com.flywheelms.gcongui.gcg.activity.GcgActivity;
import com.flywheelms.gcongui.gcg.enumerator.GcgPhysicalKeyboardShortcutProfile;
import com.flywheelms.gcongui.gcg.widget.GcgWidgetTextViewSummaryBox;

public class GcgKeyboardShortcutsDialog extends GcgCancelDialog {

    private GcgPhysicalKeyboardShortcutProfile gcgPhysicalKeyboardShortcutProfile;

    public GcgKeyboardShortcutsDialog(GcgActivity aGcgActivity, GcgPhysicalKeyboardShortcutProfile aKeyboardShortcutProfile) {
        super(aGcgActivity);
        this.gcgPhysicalKeyboardShortcutProfile = aKeyboardShortcutProfile;
        initialSetup();
    }

    @Override
    protected int getDialogTitleStringResourceId() {
        return R.string.gcg__keyboard_shortcuts;
    }

    @Override
    protected int getDialogTitleIconResourceId() {
        return R.drawable.gcg_dialog__keyboard_shortcuts;
    }

    @Override
    protected int getCustomDialogContentsResourceId() {
        return R.layout.gcg__keyboard_shortcuts__dialog;
    }

    @Override
    protected int getDialogWidth() {
        return 950;
    }

    @Override
    protected void initializeDialogBody() {
        super.initializeDialogBody();
        ((GcgWidgetTextViewSummaryBox) this.dialogBodyView.findViewById(R.id.keyboard_shortcuts)).setText(
                GcgApplication.getAppResources().getString(this.gcgPhysicalKeyboardShortcutProfile.getDefinitionResourceId()));
    }

}
