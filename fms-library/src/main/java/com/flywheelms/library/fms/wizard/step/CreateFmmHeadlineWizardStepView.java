/* @(#)CreateFmmHeadlineWizardStepView.java
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
import com.flywheelms.library.fdk.enumerator.FdkKeyboardStyle;
import com.flywheelms.library.fdk.wizard.FdkWizardStepView;
import com.flywheelms.library.fmm.persistence.PersistenceServiceProvider;
import com.flywheelms.library.fmm.repository.FmmAccessScope;
import com.flywheelms.library.fms.helper.FmsHelpIndex;
import com.flywheelms.library.fms.widget.edit_text.HeadlineWidgetEditText;
import com.flywheelms.library.fms.widget.spinner.PersistenceServiceProviderWidgetSpinner;
import com.flywheelms.library.fms.widget.text_view.FmmAccessScopeTextView;
import com.flywheelms.library.fms.wizard.CreateFmmWizardStepFlipper;
import com.flywheelms.library.gcg.helper.GcgHelper;

public class CreateFmmHeadlineWizardStepView extends FdkWizardStepView {

	private HeadlineWidgetEditText headlineWidget;
	private FmmAccessScopeTextView accessScopeWidget;
	private PersistenceServiceProviderWidgetSpinner serviceProviderSpinner;

	public CreateFmmHeadlineWizardStepView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected int getPageTitleResourceId() {
		return R.string.create__fmm_repository__headline__wizard_step;
	}

	@Override
	protected int getWizardStepBodyResourceId() {
		return R.layout.create__fmm_repository__headline__wizard_step;
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
				CreateFmmHeadlineWizardStepView.this.manageButtonState();
			}
		});
		this.accessScopeWidget = (FmmAccessScopeTextView) findViewById(R.id.fmm_repository__scope);
		this.accessScopeWidget.setText(((CreateFmmWizardStepFlipper) getViewFlipper()).getAccessScope().getName());
		GcgHelper.setDrawableLeft(this.accessScopeWidget.getTextView(), ((CreateFmmWizardStepFlipper) getViewFlipper()).getAccessScope().getDrawable());
		this.serviceProviderSpinner = (PersistenceServiceProviderWidgetSpinner) findViewById(R.id.fmm_repository__service_provider__spinner);
//		this.serviceProviderSpinner.setFdkListener(this);
		this.serviceProviderSpinner.setAccessScope(((CreateFmmWizardStepFlipper) getViewFlipper()).getAccessScope());
	}
	
	@Override
	public void initFdkDictationResultsConsumerMap() {
		addFdkDictationResultsConsumer(this.headlineWidget);
//		addFdkDictationResultsConsumer(this.serviceProviderSpinner);
		this.currentFdkDictationResultsConsumer = this.headlineWidget;
		fdkFocusConsumer(this.currentFdkDictationResultsConsumer);
	}

	protected void manageButtonState() {
		this.headlineWidget.setDataStatus();
	}

	@Override
	protected FdkKeyboardStyle getFdkKeyboardStyle() {
		return FdkKeyboardStyle.widget_input__EDIT_TEXT;
	}

	@Override
	public String getSummaryText() {
		StringBuilder theStringBuilder = new StringBuilder();
		theStringBuilder.append("<font color='#0000FF'>FMM Headline</font><br/>");
		if(this.headlineWidget.isMinimumInput()) {
			theStringBuilder.append(GcgHelper.html__INDENT + this.headlineWidget.getText() + "<br/><br/>");
		} else {
			theStringBuilder.append(GcgHelper.html__INDENT);
			theStringBuilder.append(GcgHelper.html__ERROR);
			theStringBuilder.append(" - Needs a valid headline" + "<br/><br/>");
		}
		theStringBuilder.append("<font color='#0000FF'>Repository Scope</font><br/>");
		theStringBuilder.append(GcgHelper.html__INDENT + this.accessScopeWidget.getText() + "<br/><br/>");
		theStringBuilder.append("<font color='#0000FF'>Data Storage</font><br/>");
		theStringBuilder.append(GcgHelper.html__INDENT + this.serviceProviderSpinner.getText());
		return theStringBuilder.toString();
	}

	@Override
	public boolean validWizardStepData() {
		return this.headlineWidget.isMinimumInput();
	}

	public String getHeadline() {
		return this.headlineWidget.getText().toString();
	}

	public PersistenceServiceProvider getPersistenceServiceProvider() {
		return this.serviceProviderSpinner.getPersistenceServiceProvider();
	}
	
	public FmmAccessScope getFmmAccessScope() {
		return this.accessScopeWidget.getFmmAccessScope();
	}

	@Override
	protected String getHelpContextUrlString() {
		return FmsHelpIndex.CREATE_FMM__HEADLINE;
	}

}
