/* @(#)FmsNodePublishingDestinationWizardStepView.java
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

package com.flywheelms.library.fms.wizard.step;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.flywheelms.library.R;
import com.flywheelms.library.fmm.node.impl.governable.CommunityMember;
import com.flywheelms.library.fms.activity.FmsNodeWizardActivity;
import com.flywheelms.library.fms.helper.FmsHelpIndex;
import com.flywheelms.library.fms.helper.FmsTextHelper;
import com.flywheelms.library.fms.preferences.GuiPreferenceAttribute;
import com.flywheelms.library.fms.preferences.GuiPreferencesBundle;
import com.flywheelms.library.fms.widget.list_view.CommunityMemberWidgetListView;
import com.flywheelms.library.gcg.activity.GcgActivity;
import com.flywheelms.library.gcg.android.AndroidContact;
import com.flywheelms.library.gcg.helper.GcgHelper;
import com.flywheelms.library.gcg.preferences.GcgPreferencesHelper;
import com.flywheelms.library.gcg.viewflipper.GcgViewFlipper;
import com.flywheelms.library.gcg.widget.list_view.AndroidContactsWidgetListView;
import com.flywheelms.library.gcg.wizard.step.GcgWizardStepView;

import java.util.ArrayList;

public class FmsNodePublishingDestinationWizardStepView extends GcgWizardStepView {
	
	private CheckBox androidDownloadsDirectory;
	private TextView fileName;
	private CheckBox dropBox;
	private EditText dropBoxDirectory;
	private CheckBox print;
	private AndroidContactsWidgetListView androidContactListViewWidget;
	private CommunityMemberWidgetListView communityMemberListViewWidget;
	private EditText emailMessageBody;

	public FmsNodePublishingDestinationWizardStepView(Context context,
			AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void initialize(GcgActivity anGcgActivity, GcgViewFlipper aViewFlipper, int aPageNumber) {
		super.initialize(anGcgActivity, aViewFlipper, aPageNumber);
		this.androidDownloadsDirectory = (CheckBox) findViewById(R.id.file_storage__android__check_box);
		this.fileName = (TextView) findViewById(R.id.file_name__data);
		this.fileName.setText(((FmsNodeWizardActivity) getWizardStepFlipper().getWizardActivity()).getAbbreviatedFmmNodeIdString() + ".pdf");
		this.dropBox = (CheckBox) findViewById(R.id.file_storage__dropbox__check_box);
		initializeTableRowClickListener(this.dropBox, R.id.row__a2);
		this.dropBoxDirectory = (EditText) findViewById(R.id.dropbox_directory__data);
		GcgHelper.initializeViewFlingListener(this.context, getViewFlipper(), this.dropBoxDirectory);
		this.print = (CheckBox) findViewById(R.id.printer__check_box);
		initializeTableRowClickListener(this.print, R.id.row__b1);
		initializeAndroidContactListViewWidget();
		initializeFlywheelCommunityMemberListViewWidget();
		this.emailMessageBody = (EditText) findViewById(R.id.email_message_body__data);
		guiPreferencesRestore();
	}
	
	@Override
	protected boolean hasGuiPreferencesButton() {
		return true;
	}

	private void initializeFlywheelCommunityMemberListViewWidget() {
		this.communityMemberListViewWidget = (CommunityMemberWidgetListView) findViewById(R.id.community_members__widget);
		this.communityMemberListViewWidget.setGcgActivity(getGcgActivity());
	}

	private void initializeAndroidContactListViewWidget() {
		this.androidContactListViewWidget = (AndroidContactsWidgetListView) findViewById(R.id.android_contacts__widget);
		this.androidContactListViewWidget.setGcgActivity(getGcgActivity());
	}

	@Override
	protected int getViewLayoutResourceId() {
		return R.layout.fmm_node__publication__wizard_step__destination;
	}

	@Override
	protected int getPageTitleResourceId() {
		return R.string.publish_pdf__destination__wizard_step;
	}

	public CheckBox getAndroidDownloadsDirectory() {
		return this.androidDownloadsDirectory;
	}

	public CheckBox getDropBox() {
		return this.dropBox;
	}

	public EditText getDropBoxDirectory() {
		return this.dropBoxDirectory;
	}

	public CheckBox getPrint() {
		return this.print;
	}

	public String getFileName() {
		return this.fileName.getText().toString();
	}
	
	public ArrayList<AndroidContact> getAndroidContactList() {
		return this.androidContactListViewWidget.getObjectList();
	}
	
	public AndroidContactsWidgetListView getAndroidContactWidgetListView() {
		return this.androidContactListViewWidget;
	}
	
	public CommunityMemberWidgetListView getCommunityMemberWidgetListView() {
		return this.communityMemberListViewWidget;
	}

	@Override
	public boolean validWizardStepData() {
		if(this.dropBox.isChecked() && this.dropBoxDirectory.getText().length() < 1) {
			return false;
		}
		if(!this.androidDownloadsDirectory.isChecked() &&
				!this.dropBox.isChecked() &&
				!this.print.isChecked() &&
				this.androidContactListViewWidget.getCount() == 0 &&
				this.communityMemberListViewWidget.getCount() == 0 ) {
			return false;
		}
		for(AndroidContact theAndroidContact : this.androidContactListViewWidget.getObjectList()) {
			if(theAndroidContact.getEmailAddress() == "" || !theAndroidContact.getEmailAddress().contains("@")) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String getSummaryText() {
		StringBuffer theStringBuffer = new StringBuffer();
		theStringBuffer.append("File Storage" + FmsTextHelper.NEW_LINE);
		theStringBuffer.append(FmsTextHelper.INDENT + "Android - downloads directory" + FmsTextHelper.NEW_LINE);
		theStringBuffer.append(FmsTextHelper.INDENT + this.fileName.getText().toString() + FmsTextHelper.NEW_LINE);
		if(this.dropBox.isChecked()) {
			String theString = this.dropBoxDirectory.getText().length() < 1 ? "ERROR: no subdirectory name" : this.dropBoxDirectory.getText().toString();
			theStringBuffer.append(FmsTextHelper.INDENT + "DropBox: " + theString + " directory" + FmsTextHelper.NEW_LINE);
		}
		if(this.print.isChecked()) {
			theStringBuffer.append("Print PDF" + FmsTextHelper.NEW_LINE);
		}
		return theStringBuffer.toString();
	}

	public CharSequence getSummaryText_2() {
		StringBuffer theStringBuffer = new StringBuffer();
		for(AndroidContact theAndroidContact : this.androidContactListViewWidget.getObjectList()) {
			theStringBuffer.append(theAndroidContact.getDisplayName());
			if(theAndroidContact.getEmailAddress() == "") {
				theStringBuffer.append(FmsTextHelper.NEW_LINE + FmsTextHelper.INDENT + "ERROR: no email address" + FmsTextHelper.NEW_LINE);
			} else if(! theAndroidContact.getEmailAddress().contains("@")) {
				theStringBuffer.append(FmsTextHelper.NEW_LINE + FmsTextHelper.INDENT + "ERROR: invalid email address" + FmsTextHelper.NEW_LINE);
			} else {
				theStringBuffer.append(FmsTextHelper.NEW_LINE + FmsTextHelper.INDENT + theAndroidContact.getEmailAddress() + FmsTextHelper.NEW_LINE);
			}
		}
		for(CommunityMember theCommunityyMember : this.communityMemberListViewWidget.getObjectList()) {
			theStringBuffer.append(theCommunityyMember.getHeadline());
			if(theCommunityyMember.getEmailAddress() == "") {
				theStringBuffer.append(FmsTextHelper.NEW_LINE + FmsTextHelper.INDENT + "ERROR: no email address" + FmsTextHelper.NEW_LINE);
			} else if(! theCommunityyMember.getEmailAddress().contains("@")) {
				theStringBuffer.append(FmsTextHelper.NEW_LINE + FmsTextHelper.INDENT + "ERROR: invalid email address" + FmsTextHelper.NEW_LINE);
			} else {
				theStringBuffer.append(FmsTextHelper.NEW_LINE + FmsTextHelper.INDENT + theCommunityyMember.getEmailAddress() + FmsTextHelper.NEW_LINE);
			}
		}
		return theStringBuffer.toString();
	}
	
	@Override
	public void guiPreferencesClear() {
		SharedPreferences theGuiPreferences = GcgPreferencesHelper.getGuiPreferences(
				getViewFlipper().getGcgActivity(), GuiPreferencesBundle.PUBLISH_PDF__DESTINATION.getKey());
		theGuiPreferences.edit().clear().commit();
		guiPreferencesRestore();
	}

	@Override
	public void guiPreferencesRestore() {
		SharedPreferences theGuiPreferences = GcgPreferencesHelper.getGuiPreferences(
				getViewFlipper().getGcgActivity(), GuiPreferencesBundle.PUBLISH_PDF__DESTINATION.getKey());
		this.dropBox.setChecked(theGuiPreferences.getBoolean(GuiPreferenceAttribute.DROPBOX__PERSISTENCE.getKey(), false));
		this.dropBoxDirectory.setText(theGuiPreferences.getString(GuiPreferenceAttribute.DROPBOX__DIRECTORY_NAME.getKey(), ""));
		this.print.setChecked(theGuiPreferences.getBoolean(GuiPreferenceAttribute.PRINT.getKey(), false));
//		Set<String> theStringSet = theGuiPreferences.getStringSet(GuiPreferenceAttribute.CONTACTS__ANDROID.getKey(), null);
//		if(theStringSet != null) {
//			this.androidContacts = someMethod(theStringSet);
//		}
	}

	@Override
	public void guiPreferencesSave() {
		SharedPreferences theGuiPreferences = GcgPreferencesHelper.getGuiPreferences(
				getViewFlipper().getGcgActivity(), GuiPreferencesBundle.PUBLISH_PDF__DESTINATION.getKey());
		theGuiPreferences.edit().putBoolean(GuiPreferenceAttribute.DROPBOX__PERSISTENCE.getKey(), this.dropBox.isChecked()).commit();
		theGuiPreferences.edit().putString(GuiPreferenceAttribute.DROPBOX__DIRECTORY_NAME.getKey(), this.dropBoxDirectory.getText().toString()).commit();
		theGuiPreferences.edit().putBoolean(GuiPreferenceAttribute.PRINT.getKey(), this.print.isChecked()).commit();
//		theGuiPreferences.edit().putStringSet(GuiPreferenceAttribute.CONTACTS__ANDROID.getKey(), new HashSet<AndroidContact>(this.androidContactList)).commit();
	}

	@SuppressWarnings("unused")
	public void onCommunityMemberEditorResult(Activity anActivity, Intent anIntent) {
		// TODO - reflect a name change
	}

	public String getEmailMessageBody() {
		return this.emailMessageBody.getText().toString();
	}

	@Override
	protected String getHelpContextUrlString() {
		return FmsHelpIndex.NODE_PUBLISHING__DESTINATION;
	}

	public ArrayList<CommunityMember> getCommunityMemberList() {
		return this.communityMemberListViewWidget.getObjectList();
	}

}
