/*
 * Copyright (C) 2012 by Steven D. Stamps
 *
 *              Trademarks & Copyrights
 *  Flywheel Management Science(TM), Flywheel Management Model(TM),
 *  Flywheel Story Editor(TM) and FlywheelMS(TM) are exclusive trademarks
 *  of Steven D. Stamps and may only be used freely for the purpose of
 *  identifying the unforked version of this software.  Subsequent forks
 *  may not use these trademarks.  All other rights are reserved.
 *
 *  DecKanGL (Decorated Kanban Glyph Language) and TribKn (Tribal Knowledge)
 *  are also exclusive trademarks of Steven D. Stamps.  These may be used
 *  freely within the unforked FlywheelMS application and documentation.
 *  All other rights are reserved.
 *
 *  gConGUI (game Controller Graphical User Interface) is an exclusive
 *  trademark of Steven D. Stamps.  This trademark may be used freely
 *  within the unforked FlywheelMS application and documentation.
 *  All other rights are reserved.
 *
 * * Trademark information is available at
 * * <http://www.flywheelms.com/trademarks>
 * *
 * * Flywheel Management Science(TM) is a copyrighted body of management
 * * metaphors, governance processes, and leadership techniques that is
 * * owned by Steven D. Stamps.  These copyrighted materials may be freely
 * * used, without alteration, by the community (users and developers)
 * * surrounding this GPL3-licensed software.  Additional copyright
 * * information is available at <http://www.flywheelms.org/copyrights>
 * *
 * *              GPL3 Software License
 * * This program is free software: you can use it, redistribute it and/or
 * * modify it under the terms of the GNU General Public License, version 3,
 * * as published by the Free Software Foundation. This program is distributed
 * * in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * * PURPOSE.  See the GNU General Public License for more details. You should
 * * have received a copy of the GNU General Public License, in a file named
 * * COPYING, along with this program.  If you cannot find your copy, see
 * * <http://www.gnu.org/licenses/gpl-3.0.html>.
 */

package com.flywheelms.library.fms.wizard;

import android.content.Context;
import android.util.AttributeSet;

import com.flywheelms.gcongui.gcg.helper.GcgHelper;
import com.flywheelms.gcongui.gcg.wizard.GcgWizardStepFlipper;
import com.flywheelms.library.R;
import com.flywheelms.library.fmm.node.impl.governable.FiscalYear;
import com.flywheelms.library.fms.activity.CreateAllFlywheelCadenceForYearWizard;
import com.flywheelms.library.fms.wizard.step.CreateAllCadenceDoItNowWizardStepView;
import com.flywheelms.library.fms.wizard.step.CreateAllCadenceHolidaysWizardStepView;
import com.flywheelms.library.fms.wizard.step.CreateAllCadenceParametersWizardStepView;

public class CreateAllCadenceForYearWizardStepFlipper extends GcgWizardStepFlipper {

	private CreateAllCadenceParametersWizardStepView parametersWizardStepView;
	private CreateAllCadenceHolidaysWizardStepView holidaysWizardStepView;
	private CreateAllCadenceDoItNowWizardStepView doItNowWizardStepView;

	public CreateAllCadenceForYearWizardStepFlipper(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
	}

	@Override
	protected void initializeViewFlipperViews() {
		this.parametersWizardStepView = (CreateAllCadenceParametersWizardStepView) findViewById(R.id.wizard_step__cadence_parameters);
		this.parametersWizardStepView.initialize(getGcgActivity(), this, 0);
		this.holidaysWizardStepView = (CreateAllCadenceHolidaysWizardStepView) findViewById(R.id.wizard_step__cadence_holidays);
		this.holidaysWizardStepView.initialize(getGcgActivity(), this, 1);
		this.doItNowWizardStepView = (CreateAllCadenceDoItNowWizardStepView) findViewById(R.id.wizard_step__do_it_now);
		this.doItNowWizardStepView.initialize(getGcgActivity(), this, 2);
		super.initializeViewFlipperViews();
		this.parametersWizardStepView.activateView();
	}

	@Override
	public void doItNow() {
		GcgHelper.makeToast("Creating Flywheel Cadence for Fiscal Year " + getFiscalYear().getHeadline() + "...");
//		FmsOrganization theFmsOrganization = new FmsOrganization(this.organizationWizardStepView.getHeadline(), true);
//		FmmConfiguration theFmmConfiguration = updateFmmConfigurationFile(theFmsOrganization);
//		switch(theFmmConfiguration.getFmmAccessScope()) {
//			case PRIVATE:
//				createPrivateFmmDatabaseFromTemplate(theFmmConfiguration, theFmsOrganization);
//				break;
//			case SHARED:
//				createSharedFmmDatabaseFromTemplate(theFmmConfiguration, theFmsOrganization);
//				break;
//			case TEAM:
//				createTeamFmmDatabaseFromTemplate(theFmmConfiguration, theFmsOrganization);
//			//$FALL-THROUGH$
//			default:
//		}
		getGcgActivity().finish();
	}

	@Override
	public boolean validWizardData() {
		return false;
	}

    public CreateAllCadenceParametersWizardStepView getWizardStepView1() {
        return this.parametersWizardStepView;
    }

    public CreateAllCadenceHolidaysWizardStepView getWizardStepView2() {
        return this.holidaysWizardStepView;
    }

    public FiscalYear getFiscalYear() {
        return ((CreateAllFlywheelCadenceForYearWizard) getGcgActivity()).getFiscalYear();
    }
}
