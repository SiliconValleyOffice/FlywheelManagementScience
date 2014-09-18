/* @(#)AndroidContactsWidgetListView.java
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

package com.flywheelms.library.gcg.widget.list_view;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;

import com.flywheelms.library.R;
import com.flywheelms.library.gcg.android.AndroidContact;
import com.flywheelms.library.gcg.helper.GcgActivityHelper;
import com.flywheelms.library.gcg.helper.GcgHelper;
import com.flywheelms.library.gcg.widget.GcgWidgetListView;

import java.util.ArrayList;

public class AndroidContactsWidgetListView extends GcgWidgetListView <AndroidContact> {

	public AndroidContactsWidgetListView(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
	}
	
	@Override
	protected ArrayList<AndroidContact> instantiateArrayList() {
		return new ArrayList<AndroidContact>();
	}
	
	@Override
	protected ArrayAdapter<AndroidContact> instantiateArrayAdapter() {
		return new ArrayAdapter<AndroidContact>(getContext(), android.R.layout.simple_list_item_1, this.objectList);
	}
	
	@Override
	protected void launchObjectEditorActivity(AndroidContact anAndroidContact) {
		GcgActivityHelper.startContactEditor(this.gcgActivity, anAndroidContact.getId(), getRequestCode2());
	}
	
	@Override
	protected int getRequestCode() {
		if(this.requestCode == 0) {
			this.requestCode = GcgActivityHelper.request_code__ANDROID_CONTACT_PICKER;
		}
		return this.requestCode;
	}
	
	@Override
	protected int getRequestCode2() {
		if(this.requestCode2 == 0) {
			this.requestCode2 = GcgActivityHelper.request_code__ANDROID_CONTACT_EDITOR;
		}
		return this.requestCode2;
	}

	@Override
	protected void refreshObjectAfterEditorResult(Intent anIntent, AndroidContact theObject) {
		theObject.refreshFromContactsDatabase(this.gcgActivity, anIntent);
	}

	@Override
	protected void launchObjectAddActivity() {
		AndroidContact.startContactPicker(this.gcgActivity, getRequestCode());
	}
	
	@Override
	protected AndroidContact instantiateObject(Intent anIntent) {
		return new AndroidContact(this.gcgActivity, anIntent);
	}

	@Override
	protected CharSequence getPrimaryLabelText() {
		return "Android Contacts";
	}

	@Override
	protected Drawable getPrimaryLabelDrawable() {
		return getResources().getDrawable(R.drawable.android__16);
	}

	public String getText() {
		if(getObjectList().size() == 0) {
			return "";
		}
		StringBuilder theStringBuilder = new StringBuilder();
		for(AndroidContact theAndroidContact : getObjectList()) {
			theStringBuilder.append(theAndroidContact.getDisplayName());
			theStringBuilder.append(GcgHelper.text__NEW_LINE);
		}
		return theStringBuilder.substring(0, theStringBuilder.length() - GcgHelper.text__NEW_LINE.length());
	}

	public String getHtml() {
		if(getObjectList().size() == 0) {
			return "";
		}
		StringBuilder theStringBuilder = new StringBuilder();
		for(AndroidContact theAndroidContact : getObjectList()) {
			theStringBuilder.append(GcgHelper.html__INDENT);
			theStringBuilder.append(theAndroidContact.getDisplayName());
			theStringBuilder.append(GcgHelper.html__NEW_LINE);
		}
		return theStringBuilder.substring(0, theStringBuilder.length() - GcgHelper.text__NEW_LINE.length());
	}

}
