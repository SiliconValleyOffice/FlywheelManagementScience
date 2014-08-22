/* @(#)CreateFmmGovernanceWizardStepView.java
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

package com.flywheelms.library.fms.wizard_step_flipper.step;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;

import com.flywheelms.library.R;
import com.flywheelms.library.fms.helper.FmsHelpIndex;
import com.flywheelms.library.gcg.GcgActivity;
import com.flywheelms.library.gcg.helper.GcgHelper;
import com.flywheelms.library.gcg.viewflipper.GcgViewFlipper;
import com.flywheelms.library.gcg.widget.chooser_result.AndroidContactWidgetPickerResult;
import com.flywheelms.library.gcg.wizard.step.GcgWizardStepView;

public class CreateFmmGovernanceWizardStepView extends GcgWizardStepView {
	
	private AndroidContactWidgetPickerResult repositorySponsor;
	private AndroidContactWidgetPickerResult repositoryCustomer;
	private AndroidContactWidgetPickerResult repositoryFacilitator;
	private AndroidContactWidgetPickerResult repositoryAdministrator;

	public CreateFmmGovernanceWizardStepView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected int getPageTitleResourceId() {
		return R.string.create__fmm_repository__governance__wizard_step;
	}

	@Override
	protected int getViewLayoutResourceId() {
		return R.layout.create__fmm_repository__governance__wizard_step;
	}
	
	@Override
	public void initialize(GcgActivity aGcgActivity, GcgViewFlipper aViewFlipper, int aPageNumber) {
		super.initialize(aGcgActivity, aViewFlipper, aPageNumber);
		initGuiWidgets();
	}

	protected void initGuiWidgets() {
		this.repositorySponsor = (AndroidContactWidgetPickerResult) findViewById(R.id.node_picker__sponsor);
		this.repositorySponsor.setGcgActivity(getGcgActivity());
		this.repositoryCustomer = (AndroidContactWidgetPickerResult) findViewById(R.id.node_picker__customer);
		this.repositoryCustomer.setGcgActivity(getGcgActivity());
		this.repositoryFacilitator = (AndroidContactWidgetPickerResult) findViewById(R.id.node_picker__facilitator);
		this.repositoryFacilitator.setGcgActivity(getGcgActivity());
		this.repositoryAdministrator = (AndroidContactWidgetPickerResult) findViewById(R.id.node_picker__administrator);
		this.repositoryAdministrator.setGcgActivity(getGcgActivity());
	}

	public AndroidContactWidgetPickerResult getRepositorySponsor() {
		return this.repositorySponsor;
	}

	public AndroidContactWidgetPickerResult getRepositoryCustomer() {
		return this.repositoryCustomer;
	}

	public AndroidContactWidgetPickerResult getRepositoryFacilitator() {
		return this.repositoryFacilitator;
	}

	public AndroidContactWidgetPickerResult getRepositoryAdministrator() {
		return this.repositoryAdministrator;
	}
	
	public void onSponsorPickerResult(Intent anIntent) {
		this.repositorySponsor.onPickerResult(anIntent);
	}
	
	public void onCustomerPickerResult(Intent anIntent) {
		this.repositoryCustomer.onPickerResult(anIntent);
	}
	
	public void onFacilitatorPickerResult(Intent anIntent) {
		this.repositoryFacilitator.onPickerResult(anIntent);
	}
	
	public void onAdministratorPickerResult(Intent anIntent) {
		this.repositoryAdministrator.onPickerResult(anIntent);
	}

	@Override
	public String getSummaryText() {
		StringBuilder theStringBuilder = new StringBuilder();
		theStringBuilder.append("<font color='#0000FF'>Sponsor</font><br/>");
		if(this.repositorySponsor.getText().toString().length() > 0) {
			theStringBuilder.append(GcgHelper.html__INDENT + this.repositorySponsor.getText() + "<br/>");
		} else {
			theStringBuilder.append(GcgHelper.html__INDENT);
			theStringBuilder.append(GcgHelper.html__ERROR);
			theStringBuilder.append(" - Sponsor is required<br/>");
		}
		theStringBuilder.append("<font color='#0000FF'>Customer</font><br/>");
		if(this.repositoryCustomer.getText().toString().length() > 0) {
			theStringBuilder.append(GcgHelper.html__INDENT + this.repositoryCustomer.getText() + "<br/>");
		} else {
			theStringBuilder.append(GcgHelper.html__INDENT);
			theStringBuilder.append(GcgHelper.html__ERROR);
			theStringBuilder.append(" - Customer is required<br/>");
		}
		theStringBuilder.append("<font color='#0000FF'>Facilitator</font><br/>");
		if(this.repositoryFacilitator.getText().toString().length() > 0) {
			theStringBuilder.append(GcgHelper.html__INDENT + this.repositoryFacilitator.getText() + "<br/>");
		} else {
			theStringBuilder.append(GcgHelper.html__INDENT);
			theStringBuilder.append(GcgHelper.html__ERROR);
			theStringBuilder.append(" - Facilitator is required<br/>");
		}
		if(this.repositoryAdministrator.getText().toString().length() > 0) {
			theStringBuilder.append("<font color='#0000FF'>Administrator</font><br/>");
			theStringBuilder.append(GcgHelper.html__INDENT + this.repositoryAdministrator.getText() + "<br/>");
		}
		return theStringBuilder.toString();
	}

	@Override
	public boolean validWizardStepData() {
		return this.repositorySponsor.getText().toString().length() > 0 &&
				this.repositoryCustomer.getText().toString().length() > 0 &&
				this.repositoryFacilitator.getText().toString().length() > 0;
	}

	@Override
	protected String getHelpContextUrlString() {
		return FmsHelpIndex.CREATE_FMM__GOVERNANCE;
	}

}
