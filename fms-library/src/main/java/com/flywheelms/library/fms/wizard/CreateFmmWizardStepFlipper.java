/* @(#)CreateFmmWizardStepFlipper.java
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

package com.flywheelms.library.fms.wizard;

import android.content.Context;
import android.util.AttributeSet;

import com.flywheelms.gcongui.gcg.helper.GcgHelper;
import com.flywheelms.gcongui.gcg.interfaces.GcgFileNameValidator;
import com.flywheelms.gcongui.gcg.wizard.GcgWizardStepFlipper;
import com.flywheelms.library.R;
import com.flywheelms.library.fmm.FmmDatabaseService;
import com.flywheelms.library.fmm.database.FmmDatabaseHelper;
import com.flywheelms.library.fmm.helper.FmmAssetsHelper;
import com.flywheelms.library.fmm.node.impl.governable.FmsOrganization;
import com.flywheelms.library.fmm.repository.FmmAccessScope;
import com.flywheelms.library.fmm.repository.FmmConfiguration;
import com.flywheelms.library.fmm.repository.FmmConfigurationPrivate;
import com.flywheelms.library.fmm.repository.FmmConfigurationTeam;
import com.flywheelms.library.fms.activity.CreateFmmWizard;
import com.flywheelms.library.fms.helper.FmmConfigurationHelper;
import com.flywheelms.library.fms.helper.FmsFileHelper;
import com.flywheelms.library.fms.wizard.step.CreateFmmChooseTemplateWizardStepView;
import com.flywheelms.library.fms.wizard.step.CreateFmmDoItNowWizardStepView;
import com.flywheelms.library.fms.wizard.step.CreateFmmGovernanceWizardStepView;
import com.flywheelms.library.fms.wizard.step.CreateFmmHeadlineWizardStepView;
import com.flywheelms.library.fms.wizard.step.CreateFmmOrganizationWizardStepView;

import java.io.File;

public class CreateFmmWizardStepFlipper extends GcgWizardStepFlipper implements GcgFileNameValidator {
	
	private CreateFmmHeadlineWizardStepView headlineWizardStepView;
	CreateFmmOrganizationWizardStepView organizationWizardStepView;
	CreateFmmGovernanceWizardStepView governanceWizardStepView;
	CreateFmmChooseTemplateWizardStepView fmmTemplateWizardStepView;
	CreateFmmDoItNowWizardStepView doItNowWizardStepView;

	public CreateFmmWizardStepFlipper(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
	}

	@Override
	protected void initializeViewFlipperViews() {
		this.headlineWizardStepView = (CreateFmmHeadlineWizardStepView) findViewById(R.id.wizard_step__headline);
		this.headlineWizardStepView.initialize(getGcgActivity(), this, 0);
		this.fmmTemplateWizardStepView = (CreateFmmChooseTemplateWizardStepView) findViewById(R.id.wizard_step__fmm_template);
		this.fmmTemplateWizardStepView.initialize(getGcgActivity(), this, 3);
		this.organizationWizardStepView = (CreateFmmOrganizationWizardStepView) findViewById(R.id.wizard_step__organization);
		this.organizationWizardStepView.initialize(getGcgActivity(), this, 1);
		this.governanceWizardStepView = (CreateFmmGovernanceWizardStepView) findViewById(R.id.wizard_step__governance);
		this.governanceWizardStepView.initialize(getGcgActivity(), this, 2);
		this.doItNowWizardStepView = (CreateFmmDoItNowWizardStepView) findViewById(R.id.wizard_step__summary);
		this.doItNowWizardStepView.initialize(getGcgActivity(), this, 4);
		super.initializeViewFlipperViews();
		this.headlineWizardStepView.activateView();
	}

	@Override
	public void doItNow() {
		GcgHelper.makeToast("Creating new FMM Repository...");
		FmsOrganization theFmsOrganization = new FmsOrganization(this.organizationWizardStepView.getHeadline(), true);
		FmmConfiguration theFmmConfiguration = updateFmmConfigurationFile(theFmsOrganization);
		switch(theFmmConfiguration.getFmmAccessScope()) {
			case PRIVATE:
				createPrivateFmmDatabaseFromTemplate(theFmmConfiguration, theFmsOrganization);
				break;
			case TEAM:
				createTeamFmmDatabaseFromTemplate(theFmmConfiguration, theFmsOrganization);
			//$FALL-THROUGH$
			default:
		}
		// initialize Repository row
		// initialize Community
		// initialize Governance
		// initialize Organization row
		getGcgActivity().finish();
	}

	private FmmConfiguration updateFmmConfigurationFile(FmsOrganization anFmsOrganization) {
		FmmConfiguration theFmmConfiguration = null;
		switch(this.headlineWizardStepView.getFmmAccessScope()) {
			case PRIVATE:
				theFmmConfiguration = new FmmConfigurationPrivate(this.headlineWizardStepView.getHeadline(), this.fmmTemplateWizardStepView.getDbName());
				theFmmConfiguration.setOrganizationNodeIdString(anFmsOrganization.getNodeIdString());
				FmmConfigurationHelper.newFmmConfiguration(theFmmConfiguration);
				break;
			case TEAM:
				theFmmConfiguration = new FmmConfigurationTeam(this.headlineWizardStepView.getHeadline());
				FmmConfigurationHelper.newFmmConfiguration(theFmmConfiguration);
				//$FALL-THROUGH$
			default:
		}
		return theFmmConfiguration;
	}

	private void createPrivateFmmDatabaseFromTemplate(FmmConfiguration anFmmConfiguration, FmsOrganization anFmsOrganization) {
		File theSourceFile;
		File theDestinationFile;
		Boolean theResults = false;
		switch(this.fmmTemplateWizardStepView.getFmmTemplateSource()) {
			case PRIVATE:
				FmmDatabaseService.closeActiveFmm();  // can't copy an open database
				theSourceFile = FmsFileHelper.getDatabaseFile(this.fmmTemplateWizardStepView.getTemplateFileName());
				theDestinationFile = FmsFileHelper.getDatabaseFile(this.fmmTemplateWizardStepView.getDbFileName());
				theResults = FmsFileHelper.copyFile(theSourceFile, theDestinationFile);
				if(! theResults) {
					GcgHelper.makeToast("FATAL ERROR - Could not copy " + this.fmmTemplateWizardStepView.getTemplateFileName() + " to " + this.fmmTemplateWizardStepView.getDbFileName());
					return;
				}
				FmmDatabaseHelper.initializeNewFmm(anFmmConfiguration, anFmsOrganization);
				break;
			case TEAM:
			case ASSETS:
				theDestinationFile = FmsFileHelper.getDatabaseFile(this.fmmTemplateWizardStepView.getDbFileName());
				theResults = FmmAssetsHelper.copyFmmTemplateToFile(this.fmmTemplateWizardStepView.getTemplateFileName(), theDestinationFile);
				if(! theResults) {
					GcgHelper.makeToast("FATAL ERROR - Could not copy " + this.fmmTemplateWizardStepView.getTemplateFileName() + " to " + this.fmmTemplateWizardStepView.getDbFileName());
					return;
				}
				FmmDatabaseHelper.initializeNewFmm(anFmmConfiguration, anFmsOrganization);
				//$FALL-THROUGH$
			case CLOUD:
			default:
		}
	}

	@SuppressWarnings("unused")
	private void createSharedFmmDatabaseFromTemplate(FmmConfiguration theFmmConfiguration, FmsOrganization theFmsOrganization) {
		File theSourceFile;
		File theDestinationFile;
		switch(this.fmmTemplateWizardStepView.getFmmTemplateSource()) {
			case PRIVATE:
	//			theSourceFile = FmsFileHelper.getDatabaseFile(this.fmmTemplateWizardStepView.getTemplateFileName());
	//			theDestinationFile = FmsFileHelper.getDatabaseFile(this.fmmTemplateWizardStepView.getDbFileName());
	//			Boolean theResults = FmsFileHelper.copyFile(theSourceFile, theDestinationFile);
	//			if(! theResults) {
	//				GcgHelper.makeToast("FATAL ERROR - Could not copy " + this.fmmTemplateWizardStepView.getTemplateFileName() + " to " + this.fmmTemplateWizardStepView.getDbFileName());
	//			}
				break;
			case TEAM:
			case ASSETS:
			case CLOUD:
			default:
		}
	}

	@SuppressWarnings("unused")
	private void createTeamFmmDatabaseFromTemplate(FmmConfiguration theFmmConfiguration, FmsOrganization theFmsOrganization) {
		File theSourceFile;
		File theDestinationFile;
		switch(this.fmmTemplateWizardStepView.getFmmTemplateSource()) {
			case PRIVATE:
	//			theSourceFile = FmsFileHelper.getDatabaseFile(this.fmmTemplateWizardStepView.getTemplateFileName());
	//			theDestinationFile = FmsFileHelper.getDatabaseFile(this.fmmTemplateWizardStepView.getDbFileName());
	//			Boolean theResults = FmsFileHelper.copyFile(theSourceFile, theDestinationFile);
	//			if(! theResults) {
	//				GcgHelper.makeToast("FATAL ERROR - Could not copy " + this.fmmTemplateWizardStepView.getTemplateFileName() + " to " + this.fmmTemplateWizardStepView.getDbFileName());
	//			}
				break;
			case TEAM:
			case ASSETS:
			case CLOUD:
			default:
		}
	}

	@Override
	public boolean validWizardData() {
		return this.headlineWizardStepView.validWizardStepData() &&
				this.fmmTemplateWizardStepView.validWizardStepData() &&
				this.organizationWizardStepView.validWizardStepData() &&
				this.governanceWizardStepView.validWizardStepData();
	}

	public FmmAccessScope getAccessScope() {
		return ((CreateFmmWizard) getGcgActivity()).getFmmAccessScope();
	}

	public CreateFmmOrganizationWizardStepView getOrganizationWizardStep() {
		return this.organizationWizardStepView;
	}

	public CreateFmmGovernanceWizardStepView getGovernanceWizardStep() {
		return this.governanceWizardStepView;
	}

	public CreateFmmHeadlineWizardStepView getWizardStepView1() {
		return this.headlineWizardStepView;
	}

	public CreateFmmChooseTemplateWizardStepView getWizardStepView2() {
		return this.fmmTemplateWizardStepView;
	}

	public CreateFmmOrganizationWizardStepView getWizardStepView3() {
		return this.organizationWizardStepView;
	}

	public CreateFmmGovernanceWizardStepView getWizardStepView4() {
		return this.governanceWizardStepView;
	}

	@Override
	public boolean fileNameExists(String aFileName) {
		Boolean aBoolean = false;
		switch(this.fmmTemplateWizardStepView.getFmmTemplateSource()) {
			case PRIVATE:
				aBoolean = FmsFileHelper.dbFileExists(aFileName); 
				break;
			case TEAM:
			default:
				aBoolean = false;
		}
		this.fmmTemplateWizardStepView.setErrorTextFileNameExists(aBoolean);
		return aBoolean;
	}

}
