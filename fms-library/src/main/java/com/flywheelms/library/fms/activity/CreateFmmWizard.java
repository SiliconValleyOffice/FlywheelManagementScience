/* @(#)CreateFmmWizard.java
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

package com.flywheelms.library.fms.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.flywheelms.library.R;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.repository.FmmAccessScope;
import com.flywheelms.library.fms.helper.FmsActivityHelper;
import com.flywheelms.library.fms.helper.FmsHelpIndex;
import com.flywheelms.library.fms.wizard_step_flipper.CreateFmmWizardStepFlipper;
import com.flywheelms.library.gcg.wizard.GcgWizardActivity;

public class CreateFmmWizard extends GcgWizardActivity {
	
	private CreateFmmWizardStepFlipper wizardStepFlipper;
//	private FmmPrivateRepository privateFmmConfiguration;
//	private FmmTeamRepository sharedFmmConfiguration;
	private FmmAccessScope accessScope;

	public CreateFmmWizard() {
		super(FmsHelpIndex.CREATE__FMM_CONFIGURATION_WIZARD);
	}
	
	@Override
	public void onCreate(Bundle aSavedInstanceState) {
	    super.onCreate(aSavedInstanceState);
	    this.wizardStepFlipper = (CreateFmmWizardStepFlipper) findViewById(R.id.gcg__wizard__step_flipper);
		this.setDisplayHome = false;
	    removeNavigationButton();
	}

	@Override
	protected void processExtras() {
		super.processExtras();
		this.accessScope = FmmAccessScope.getObjectForName(getIntent().getExtras().getString(FmsActivityHelper.bundle_key__FMM_CONFIGURATION_SCOPE));
	}

	@Override
	protected int getContentViewResourceId() {
		return R.layout.create__fmm_repository__wizard;
	}

	@Override
	protected String getBreadcrumbHeadline() {
		return "Create FMM Repository";
	}

	@Override
	protected FmmNodeDefinition getDisplayedFmmNodeDefinition() {
		return FmmNodeDefinition.FMM_CONFIGURATION;
	}

	@Override
	protected String getBreadcrumbTargetNodeIdString() {
		return "";
	}
	
	public FmmAccessScope getFmmAccessScope() {
		return this.accessScope;
	}

	@Override
	protected void onActivityResult(int aRequestCode, int aResultCode, Intent anIntent) {
		if(anIntent == null) {
			return;
		}
		if(aRequestCode == FmsActivityHelper.request_code__ANDROID_CONTACT_PICKER_FOR_ORGANIZATION) {
			if(aResultCode != Activity.RESULT_CANCELED) {
				this.wizardStepFlipper.getOrganizationWizardStep().getAndroidContactsWidget().onObjectPickerResult(anIntent);
			}
			return;
		} else if(aRequestCode == FmsActivityHelper.request_code__ANDROID_CONTACT_EDITOR_FOR_ORGANIZATION) {
			if(aResultCode != Activity.RESULT_CANCELED) {
				this.wizardStepFlipper.getOrganizationWizardStep().getAndroidContactsWidget().onObjectEditorResult(anIntent);
			}
			return;
		} else if(aRequestCode == FmsActivityHelper.request_code__ANDROID_CONTACT_EDITOR_FOR_SPONSOR) {
			if(aResultCode != Activity.RESULT_CANCELED) {
				this.wizardStepFlipper.getGovernanceWizardStep().getRepositorySponsor().onObjectEditorResult(anIntent);
			}
			return;
		} else if(aRequestCode == FmsActivityHelper.request_code__ANDROID_CONTACT_EDITOR_FOR_CUSTOMER) {
			if(aResultCode != Activity.RESULT_CANCELED) {
				this.wizardStepFlipper.getGovernanceWizardStep().getRepositoryCustomer().onObjectEditorResult(anIntent);
			}
			return;
		} else if(aRequestCode == FmsActivityHelper.request_code__ANDROID_CONTACT_EDITOR_FOR_FACILITATOR) {
			if(aResultCode != Activity.RESULT_CANCELED) {
				this.wizardStepFlipper.getGovernanceWizardStep().getRepositoryFacilitator().onObjectEditorResult(anIntent);
			}
			return;
		} else if(aRequestCode == FmsActivityHelper.request_code__ANDROID_CONTACT_EDITOR_FOR_ADMINISTRATOR) {
			if(aResultCode != Activity.RESULT_CANCELED) {
				this.wizardStepFlipper.getGovernanceWizardStep().getRepositoryAdministrator().onObjectEditorResult(anIntent);
			}
			return;
		} else if(aRequestCode == FmsActivityHelper.request_code__ANDROID_CONTACT_PICKER_FOR_SPONSOR) {
			if(aResultCode != Activity.RESULT_CANCELED) {
				this.wizardStepFlipper.getGovernanceWizardStep().onSponsorPickerResult(anIntent);
			}
			return;
		} else if(aRequestCode == FmsActivityHelper.request_code__ANDROID_CONTACT_PICKER_FOR_CUSTOMER) {
			if(aResultCode != Activity.RESULT_CANCELED) {
				this.wizardStepFlipper.getGovernanceWizardStep().onCustomerPickerResult(anIntent);
			}
			return;
		} else if(aRequestCode == FmsActivityHelper.request_code__ANDROID_CONTACT_PICKER_FOR_FACILITATOR) {
			if(aResultCode != Activity.RESULT_CANCELED) {
				this.wizardStepFlipper.getGovernanceWizardStep().onFacilitatorPickerResult(anIntent);
			}
			return;
		} else if(aRequestCode == FmsActivityHelper.request_code__ANDROID_CONTACT_PICKER_FOR_ADMINISTRATOR) {
			if(aResultCode != Activity.RESULT_CANCELED) {
				this.wizardStepFlipper.getGovernanceWizardStep().onAdministratorPickerResult(anIntent);
			}
			return;
		}
		super.onActivityResult(aRequestCode, aResultCode, anIntent);
	}

}
