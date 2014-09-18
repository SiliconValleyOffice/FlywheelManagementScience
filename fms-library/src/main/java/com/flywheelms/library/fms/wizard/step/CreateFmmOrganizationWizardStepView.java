/* @(#)CreateFmmOrganizationWizardStepView.java
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

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import com.flywheelms.library.R;
import com.flywheelms.library.fdk.wizard.FdkWizardStepView;
import com.flywheelms.library.fms.helper.FmsHelpIndex;
import com.flywheelms.library.fms.widget.edit_text.HeadlineWidgetEditText;
import com.flywheelms.library.gcg.helper.GcgHelper;
import com.flywheelms.library.gcg.widget.list_view.AndroidContactsWidgetListView;

public class CreateFmmOrganizationWizardStepView extends FdkWizardStepView {

	private HeadlineWidgetEditText headlineWidget;
	private AndroidContactsWidgetListView androidContactsWidget;

	public CreateFmmOrganizationWizardStepView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected int getPageTitleResourceId() {
		return R.string.create__fmm_repository__organization__wizard_step;
	}

	@Override
	protected void initGuiWidgets() {
		this.headlineWidget = (HeadlineWidgetEditText) findViewById(R.id.headline);
		this.headlineWidget.setGcgActivity(getGcgActivity());
		this.headlineWidget.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				CreateFmmOrganizationWizardStepView.this.manageButtonState();
			}
		});
		this.androidContactsWidget = (AndroidContactsWidgetListView) findViewById(R.id.android_contacts__widget);
		this.androidContactsWidget.setGcgActivity(getGcgActivity());
	}

	protected void manageButtonState() {
		this.headlineWidget.setDataStatus();
	}

	@Override
	protected int getWizardStepBodyResourceId() {
		return R.layout.create__fmm_repository__organization__wizard_step;
	}

	@Override
	public void initFdkDictationResultsConsumerMap() {
		addFdkDictationResultsConsumer(this.headlineWidget);
		this.currentFdkDictationResultsConsumer = this.headlineWidget;
	}
	
	public AndroidContactsWidgetListView getAndroidContactsWidget() {
		return this.androidContactsWidget;
	}

	@Override
	public String getSummaryText() {
		StringBuilder theStringBuilder = new StringBuilder();
		theStringBuilder.append("<font color='#0000FF'>Organization Headline</font><br/>");
		if(this.headlineWidget.isMinimumInput()) {
			theStringBuilder.append(GcgHelper.html__INDENT + this.headlineWidget.getText() + "<br/><br/>");
		} else {
			theStringBuilder.append(GcgHelper.html__INDENT);
			theStringBuilder.append(GcgHelper.html__ERROR);
			theStringBuilder.append(" - Needs a valid headline" + "<br/><br/>");
		}
		theStringBuilder.append("<font color='#0000FF'>Android Contacts</font><br/>");
		String theAndroidContacts = this.androidContactsWidget.getHtml();
		if(theAndroidContacts.length() == 0) {
			theStringBuilder.append(GcgHelper.html__INDENT);
			theStringBuilder.append(GcgHelper.html__WARNING);
			theStringBuilder.append(" - No additional community members.");
		} else {
			theStringBuilder.append(this.androidContactsWidget.getHtml());
		}
		return theStringBuilder.toString();
	}

	@Override
	public boolean validWizardStepData() {
		return this.headlineWidget.isMinimumInput();
	}

	public String getHeadline() {
		return this.headlineWidget.getText().toString();
	}

	@Override
	protected String getHelpContextUrlString() {
		return FmsHelpIndex.CREATE_FMM__ORGANIZATION;
	}

}
