/* @(#)GcgWidgetTextView.java
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

package com.flywheelms.gcongui.gcg.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.flywheelms.gcongui.R;
import com.flywheelms.gcongui.fdk.enumerator.FdkKeyboardStyle;
import com.flywheelms.gcongui.gcg.helper.GcgHelper;
import com.flywheelms.gcongui.gcg.interfaces.GcgGuiable;

import java.util.ArrayList;

public abstract class GcgWidgetTextView extends GcgWidget {

	private static final int resource_id__WIDGET_DATA = R.id.widget_data;
	protected TextView textView;

	public GcgWidgetTextView(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
		setInitialMultiShiftState(null);
		resetMultiShiftStateAfterDictation(false);
	}

	@Override
	protected void setup() {
		super.setup();
		this.textView = (TextView) this.widgetContainer.findViewById(resource_id__WIDGET_DATA);
        this.textView.setEllipsize(TextUtils.TruncateAt.valueOf("END"));
		if(this.isTransparentBackground) {
			setTransparentBackground();
		}
		setInitialValue();
		manageBackgroundState();
	}

	@Override
	protected void setLabelWidth() {
		if(this.labelContainer == null) {
			super.setLabelWidth();
		} else {
			android.view.ViewGroup.LayoutParams theLayoutParams = this.labelContainer.getLayoutParams();
			theLayoutParams.width = GcgHelper.getPixelsForDp(getContext(), this.labelWidth);
			this.labelContainer.setLayoutParams(theLayoutParams);
		}
	}

	@Override
	public void setHint(int aResourceId) {
		this.textView.setHint(aResourceId);
	}

	@Override
	public void setHint(String aString) {
		this.textView.setHint(aString);
	}

	@Override
	public GcgGuiable getData() {
		return null;
	}

	@Override
	public void setData(Object anObject) {
		this.textView.setText(((GcgGuiable) anObject).toString());
	}

	protected void manageBackgroundState() {
		this.textView.setBackgroundResource(isMinimumInput() ?
				this.isTransparentBackground ? R.color.gcg__transparent : R.drawable.gcg__edit_text :
				R.drawable.gcg__edit_text__invalid);
	}

	protected boolean isMinimumInput() {
		return inputRequired() ? isMinimumLength() : true;
	}

	protected void clearInput() {
		this.textView.setText("");
		manageBackgroundState();
	}
	
	protected boolean isMinimumLength() {
		return this.minimumDataLength == 0 || this.textView.getText().toString().length() >= this.minimumDataLength;
	}
	
	public CharSequence getText() {
		return this.textView.getText();
	}
	
	public void setText(CharSequence aCharSequence) {
		this.textView.setText(aCharSequence);
	}
	
	public void setText(int aStringResourceId) {
		this.textView.setText(aStringResourceId);
	}

	@Override
	protected int getWidgetLayoutResourceId() {
		int theResourceId = R.layout.gcg__widget__text_view__horizontal;
		if(this.containerLayout.equals(container_layout__MENU_PARAMETER)) {
			theResourceId = R.layout.gcg__widget__text_view__menu_parameter;
		} else if(this.containerLayout.equals(container_layout__VERTICAL)) {
			if(this.scrollableContent) {
				theResourceId = R.layout.gcg__widget__text_view__vertical__scrollable;
			} else {
				theResourceId = R.layout.gcg__widget__text_view__vertical;
			}
		} else if(this.containerLayout.equals(container_layout__VERTICAL__NO_LABEL_DRAWABLE)) {
			theResourceId = R.layout.gcg__widget__text_view__vertical;
		} else if(this.containerLayout.equals(container_layout__NO_LABEL)) {
			theResourceId = R.layout.gcg__widget__text_view__no_label;
		}
		return theResourceId;
	}

	@Override
	public void setInitialValue(Object anObject) {
		setOriginalValue(anObject);
		setData(anObject);
	}

	@Override
	protected void setTransparentBackground() {
		this.textView.setBackgroundResource(R.color.gcg__transparent);
	}
	
	@Override
	public String toString() {
		return this.textView.getText().toString();
	}
	
	public void setText(String aString) {
		this.textView.setText(aString);
		notifyOnSetTextListenerList(aString);
	}

	@Override
	public void onDictationResults(ArrayList<String> aDictationResultsList) {
		return;
	}

	@Override
	public View getViewToFocus() {
		return this.textView;
	}

	public TextView getTextView() {
		return this.textView;
	}

	@Override
	public FdkKeyboardStyle getKeyboardStyle() {
		return null;
	}
	
    @Override
	public void setOnClickListener(OnClickListener anOnClickListener) {
    	WrappedOnClickListener theWrappedOnClickListener = new WrappedOnClickListener(this, anOnClickListener);
    	super.setOnClickListener(theWrappedOnClickListener);
        if (!this.textView.isClickable()) {
            this.textView.setClickable(true);
        }
        this.textView.setOnClickListener(theWrappedOnClickListener);
        if (this.labelTextView != null) {
            if (!this.labelTextView.isClickable()) {
            	this.labelTextView.setClickable(true);
            }
            this.labelTextView.setOnClickListener(theWrappedOnClickListener);
        }
        if(this.copyButton != null) {
        	this.copyButton.setOnClickListener(theWrappedOnClickListener);
        } else {
        	setBackgroundResource(R.drawable.gcg__background_state_list__text_view);
        	this.textView.setBackgroundResource(R.drawable.gcg__background_state_list__text_view);
            if (this.labelTextView != null) {
            	this.labelTextView.setBackgroundResource(R.drawable.gcg__background_state_list__text_view);
            }
        }
    }

}
