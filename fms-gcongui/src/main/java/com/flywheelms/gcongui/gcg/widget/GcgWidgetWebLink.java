package com.flywheelms.gcongui.gcg.widget;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.flywheelms.gcongui.R;
import com.flywheelms.gcongui.fdk.enumerator.FdkKeyboardStyle;

import java.util.ArrayList;

// com.flywheelms.gcongui.gcg.widget.GcgWidgetWebLink
public class GcgWidgetWebLink extends GcgWidget {

    private String webLink;
    private Uri uri;
    private Intent intent;
    private Button button;

    public GcgWidgetWebLink(Context aContext, AttributeSet anAttributeSet) {
        super(aContext, anAttributeSet);
    }

    @Override
    protected int getWidgetLayoutResourceId() {
        return R.layout.gcg__widget__web_link__horizontal;
    }

    protected void processCustomAttributes(Context aContext, AttributeSet anAttributeSet) {
        super.processCustomAttributes(aContext, anAttributeSet);
        processWebLinkAttributes(aContext, anAttributeSet);
    }

    private void processWebLinkAttributes(Context aContext, AttributeSet anAttributeSet) {
        TypedArray aTypedArray = aContext.obtainStyledAttributes(anAttributeSet, R.styleable.GcgWebLink);
        int theArraySize = aTypedArray.getIndexCount();
        for (int theIndex = 0; theIndex < theArraySize; ++theIndex) {
            int theAttributeIndex = aTypedArray.getIndex(theIndex);
            if(theAttributeIndex == R.styleable.GcgWebLink_uri) {
                this.webLink = aTypedArray.getString(theAttributeIndex);
                this.uri = Uri.parse(this.webLink);
                this.intent = new Intent(Intent.ACTION_VIEW, this.uri);
            }
        }
        aTypedArray.recycle();
    }

    @Override
    public void setHint(int aResourceId) {

    }

    @Override
    public void setHint(String aString) {

    }

    @Override
    public Object getData() {
        return null;
    }

    @Override
    public void setData(Object anObject) {

    }

    @Override
    public void setup() {
        super.setup();
        this.button = (Button) this.widgetContainer.findViewById(R.id.web_link__button);
        this.button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View aView) {
                GcgWidgetWebLink.this.gcgActivity.startActivity(GcgWidgetWebLink.this.intent);
            }
        });
        this.labelTextView.setText(this.labelTextString);
        this.labelTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View aView) {
                GcgWidgetWebLink.this.gcgActivity.startActivity(GcgWidgetWebLink.this.intent);
            }
        });
    }

    @Override
    public View getViewToFocus() {
        return null;
    }

    @Override
    public FdkKeyboardStyle getKeyboardStyle() {
        return null;
    }

    @Override
    public void onDictationResults(ArrayList<String> aDictationResultsList) {

    }
}
