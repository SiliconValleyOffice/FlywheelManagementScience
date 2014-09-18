/* @(#)CreateFmmConfigurationFmmTemplateWizardStepView.java
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
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.flywheelms.library.R;
import com.flywheelms.library.fdk.wizard.FdkWizardStepView;
import com.flywheelms.library.fmm.repository.FmmConfigurationTemplateSource;
import com.flywheelms.library.fms.helper.FmsHelpIndex;
import com.flywheelms.library.fms.widget.spinner.FmmTemplateWidgetSpinner;
import com.flywheelms.library.fms.wizard.CreateFmmWizardStepFlipper;
import com.flywheelms.library.gcg.activity.GcgActivity;
import com.flywheelms.library.gcg.helper.GcgHelper;
import com.flywheelms.library.gcg.viewflipper.GcgViewFlipper;
import com.flywheelms.library.gcg.widget.edit_text.GcgWidgetFileNameEditText;

public class CreateFmmChooseTemplateWizardStepView extends FdkWizardStepView {

	private RadioGroup radioGroup;
	private FmmTemplateWidgetSpinner fmmConfigurationTemplateWidgetSpinner;
	private GcgWidgetFileNameEditText fmmFileNameWidgetEditText;
	private TextView errorTextFileNameExists;

	public CreateFmmChooseTemplateWizardStepView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected int getPageTitleResourceId() {
		return R.string.create__fmm_repository__fmm_template__wizard_step;
	}

	@Override
	protected int getWizardStepBodyResourceId() {
		return R.layout.create__fmm_repository__fmm_template__wizard_step;
	}
	
	@Override
	public void initialize(GcgActivity aGcgActivity, GcgViewFlipper aViewFlipper, int aPageNumber) {
		super.initialize(aGcgActivity, aViewFlipper, aPageNumber);
		initGuiWidgets();
	}

	@Override
	protected void initGuiWidgets() {
		this.radioGroup = (RadioGroup) findViewById(R.id.fmm_template__source__radio);
		this.radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				CreateFmmChooseTemplateWizardStepView.this.fmmConfigurationTemplateWidgetSpinner.updateSpinnerData(getFmmTemplateSource());
			}
		});
		this.fmmConfigurationTemplateWidgetSpinner = (FmmTemplateWidgetSpinner) findViewById(R.id.fmm_repository__spinner);
		this.fmmConfigurationTemplateWidgetSpinner.setGcgActivity(getGcgActivity());
		this.fmmConfigurationTemplateWidgetSpinner.updateSpinnerData(getFmmTemplateSource());
		this.fmmFileNameWidgetEditText = (GcgWidgetFileNameEditText) findViewById(R.id.fmm_repository__file_name);
		this.fmmFileNameWidgetEditText.setGcgActivity(getGcgActivity());
		this.fmmFileNameWidgetEditText.setFileNameValidator((CreateFmmWizardStepFlipper) getViewFlipper());
		this.fmmFileNameWidgetEditText.addTextChangedListener(new TextWatcher() {

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
				CreateFmmChooseTemplateWizardStepView.this.manageButtonState();
			}
		});
		this.fmmFileNameWidgetEditText.setSelection(0);
		this.errorTextFileNameExists = (TextView) findViewById(R.id.error_message__file_name_exists);
		this.errorTextFileNameExists.setVisibility(INVISIBLE);
	}

	@Override
	public void initFdkDictationResultsConsumerMap() {
		addFdkDictationResultsConsumer(this.fmmConfigurationTemplateWidgetSpinner);
		addFdkDictationResultsConsumer(this.fmmFileNameWidgetEditText);
		this.currentFdkDictationResultsConsumer = this.fmmConfigurationTemplateWidgetSpinner;
	}
	
	public void setErrorTextFileNameExists(boolean aBoolean) {
		this.errorTextFileNameExists.setVisibility(aBoolean ? VISIBLE : INVISIBLE);
	}

	protected void manageButtonState() {
		this.fmmFileNameWidgetEditText.setDataStatus();
	}

	public FmmConfigurationTemplateSource getFmmTemplateSource() {
		FmmConfigurationTemplateSource theFmmAccessScope;
		int theSelectionId = this.radioGroup.getCheckedRadioButtonId();
		if(theSelectionId == R.id.template_source__existing_private) {
			theFmmAccessScope = FmmConfigurationTemplateSource.PRIVATE;
		} else if(theSelectionId == R.id.template_source__existing_shared) {
			theFmmAccessScope = FmmConfigurationTemplateSource.SHARED;
		} else if(theSelectionId == R.id.template_source__existing_team) {
			theFmmAccessScope = FmmConfigurationTemplateSource.TEAM;
		} else if(theSelectionId == R.id.template_source__fmm_assets) {
			theFmmAccessScope = FmmConfigurationTemplateSource.ASSETS;
		} else {
			theFmmAccessScope = FmmConfigurationTemplateSource.CLOUD;
		}
		return theFmmAccessScope;
	}
	
	public String getDbFileName() {
		return this.fmmFileNameWidgetEditText.getText().toString();
	}
	
	public String getDbName() {
		String theString = this.fmmFileNameWidgetEditText.getText().toString();
		return theString.substring(0, theString.length() - 3);
	}

	public String getTemplateFileName() {
		return this.fmmConfigurationTemplateWidgetSpinner.getFmmConfiguration().getFileName();
	}

	@Override
	public String getSummaryText() {
		StringBuilder theStringBuilder = new StringBuilder();
		theStringBuilder.append("<font color='#0000FF'>FMM Template Source</font><br/>");
		theStringBuilder.append(GcgHelper.html__INDENT + getFmmTemplateSource().toString() + "<br/><br/>");
		theStringBuilder.append("<font color='#0000FF'>FMM Template</font><br/>");
		theStringBuilder.append(GcgHelper.html__INDENT + this.fmmConfigurationTemplateWidgetSpinner.getText() + "<br/><br/>");
		theStringBuilder.append("<font color='#0000FF'>File Name</font>" + " for new FMM<br/>");
		if(this.fmmFileNameWidgetEditText.isMinimumInput()) {
			theStringBuilder.append(GcgHelper.html__INDENT + this.fmmFileNameWidgetEditText.getText() + "<br/><br/>");
		} else {
			theStringBuilder.append(GcgHelper.html__INDENT);
			theStringBuilder.append(GcgHelper.html__ERROR);
			if(((CreateFmmWizardStepFlipper) getViewFlipper()).fileNameExists(this.fmmFileNameWidgetEditText.getText().toString())) {
				theStringBuilder.append(" - File name already exists" + "<br/><br/>");
			} else {
				theStringBuilder.append(" - Invalid file name" + "<br/><br/>");
			}
		}
		return theStringBuilder.toString();
	}

	@Override
	public boolean validWizardStepData() {
		return this.fmmFileNameWidgetEditText.isMinimumInput();
	}

	@Override
	protected String getHelpContextUrlString() {
		return FmsHelpIndex.CREATE_FMM__CHOOSE_TEMPLATE;
	}

}
