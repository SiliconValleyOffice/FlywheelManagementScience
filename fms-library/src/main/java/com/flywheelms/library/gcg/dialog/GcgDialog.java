package com.flywheelms.library.gcg.dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.WindowManager;

import com.flywheelms.library.gcg.activity.GcgActivity;

/**
 * Created by sstamps on 9/14/14.
 */
public class GcgDialog {

    protected final GcgActivity gcgActivity;
    protected AlertDialog.Builder dialogBuilder;
    protected AlertDialog alertDialog;

    public GcgDialog(GcgActivity aGcgActivity) {
        this.gcgActivity = aGcgActivity;
    }

    public void processDialog() {
        if(this.alertDialog == null) {
            this.alertDialog = this.dialogBuilder.create();
            this.alertDialog.setOnKeyListener(new AlertDialog.OnKeyListener() {

                @Override
                public boolean onKey(DialogInterface arg0, int aKeyCode, KeyEvent aKeyEvent) {
                    if (aKeyCode == KeyEvent.KEYCODE_BACK && aKeyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                        GcgDialog.this.gcgActivity.stopDialog();
                        return true;
                    }
                    return false;
                }
            });
            this.alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        }
        refreshDialog();
        this.alertDialog.show();
    }

    public void restartDialog() {
        this.alertDialog.show();
    }

    public void refreshDialog() { return; }

    public void dismiss() {
        this.alertDialog.dismiss();
    }

    public void onWidgetDataChangeListener(@SuppressWarnings("unused") int aResourceId) { return; }
}
