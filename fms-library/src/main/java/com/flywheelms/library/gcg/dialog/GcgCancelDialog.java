package com.flywheelms.library.gcg.dialog;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.flywheelms.library.R;
import com.flywheelms.library.gcg.activity.GcgActivity;

/**
 * Created by sstamps on 9/15/14.
 */
public class GcgCancelDialog  extends GcgDialog {

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

    protected void wrappedButtonSetup() {
        return;
    }

    protected void customButtonSetup() {
        return;
    }

    protected int getDialogBodyLayoutResourceId() {
        return R.layout.gcg__dialog_body__cancel;
    }

    protected void onClickButtonCancel() {
        this.gcgActivity.stopDialog();
    }

}
