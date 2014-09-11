/* @(#)AndroidContactWidgetPickerResult.java
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

package com.flywheelms.library.gcg.widget.chooser_result;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnDismissListener;
import android.widget.PopupMenu.OnMenuItemClickListener;

import com.flywheelms.library.R;
import com.flywheelms.library.gcg.android.AndroidContact;
import com.flywheelms.library.gcg.widget.GcgWidgetTextView;

public class AndroidContactWidgetPickerResult extends GcgWidgetTextView {
	
	private static final int resource_id__WIDGET_LAUNCH_BUTTON = R.id.widget_launch_button;
	protected Button launchButton;
	protected AndroidContact androidContact;

	public AndroidContactWidgetPickerResult(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
	}

	@Override
	protected String getLabelText() {
		return this.labelTextString == null ? "Android Contact" : this.labelTextString;
	}

	@Override
	protected void setup() {
		this.isTransparentBackground = this.isTransparentBackgroundParameter = false;
		super.setup();
		this.launchButton = (Button) this.widgetContainer.findViewById(resource_id__WIDGET_LAUNCH_BUTTON);
		this.launchButton.setBackgroundResource(getLaunchButtonDrawableResourceId());
		this.launchButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AndroidContactWidgetPickerResult.this.launchPickerActivity();
				
			}
		});
		this.textView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AndroidContactWidgetPickerResult.this.launchPickerActivity();
			}
		});
		this.textView.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View aView) {
				if(AndroidContactWidgetPickerResult.this.isMinimumInput()) {
					PopupMenu thePopupMenu = new PopupMenu(getContext(), aView);
					thePopupMenu.getMenuInflater().inflate(R.menu.edit_remove__item, thePopupMenu.getMenu());
					thePopupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
						
						@Override
						public boolean onMenuItemClick(MenuItem aSelectedMenuItem) {
							onPopupMenu(aSelectedMenuItem);
							return true;
						}
					});
					thePopupMenu.setOnDismissListener(new OnDismissListener() {
						
						@Override
						public void onDismiss(PopupMenu menu) { return; }
					});
					thePopupMenu.show();
				}
				return true;
			}
		});
		manageBackgroundState();
	}

	private void onPopupMenu(MenuItem aSelectedMenuItem) {
		if(aSelectedMenuItem.getTitle().equals("Remove")) {
			this.clearInput();
		} else {
			launchEditorActivity();
		}
	}

	protected void launchPickerActivity() {
		AndroidContact.startContactPicker(getGcgActivity(), getRequestCode());
	}

	protected void launchEditorActivity() {
		if(this.androidContact != null) {
			AndroidContact.startContactEditor(getGcgActivity(), this.androidContact.getId(), getRequestCode2());
		}
	}

	protected int getLaunchButtonDrawableResourceId() {
		return R.drawable.gcg__button_state_list__object_picker__android_contact;
	}

	@Override
	protected int getWidgetLayoutResourceId() {
		int theResourceId = R.layout.gcg__widget__node_picker_result__horizontal;
		return theResourceId;
	}
	
	public void onPickerResult(Intent anIntent) {
		if(anIntent.getData() != null) {
			this.androidContact = new AndroidContact(this.gcgActivity, anIntent);
			this.textView.setText(this.androidContact.getDisplayName());
			manageBackgroundState();
		}
	}

	public void onObjectEditorResult(Intent anIntent) {
		onPickerResult(anIntent);
	}

    protected void manageBackgroundState() {
        this.textView.setBackgroundResource(isMinimumInput() ?
                R.drawable.gcg__background_state_list__edit_text :
                R.drawable.gcg__background_state_list__edit_text__invalid );
    }

	@Override
	protected boolean isMinimumInput() {
		return inputRequired() ? this.androidContact != null : true;
	}

	@Override
	protected void clearInput() {
		this.androidContact = null;
		super.clearInput();
	}

}
