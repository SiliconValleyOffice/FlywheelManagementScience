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

package com.flywheelms.library.fms.wizard.step;

import android.content.Context;
import android.util.AttributeSet;

import com.flywheelms.gcongui.gcg.GcgApplication;
import com.flywheelms.gcongui.gcg.activity.GcgActivity;
import com.flywheelms.gcongui.gcg.container.GcgContainerGroupBoxLinear;
import com.flywheelms.gcongui.gcg.helper.GcgHelper;
import com.flywheelms.gcongui.gcg.viewflipper.GcgViewFlipper;
import com.flywheelms.gcongui.gcg.wizard.step.GcgWizardStepView;
import com.flywheelms.library.R;
import com.flywheelms.library.fmm.node.impl.governable.FiscalYear;
import com.flywheelms.library.fms.helper.FmsHelpIndex;
import com.flywheelms.library.fms.widget.spinner.CadenceDurationWidgetSpinner;
import com.flywheelms.library.fms.widget.spinner.WorkPlanFirstDayOfWeekWidgetSpinner;
import com.flywheelms.library.fms.widget.text_view.FiscalYearWidgetTextView;
import com.flywheelms.library.fms.wizard.CreateAllCadenceForYearWizardStepFlipper;

public class CreateAllCadenceParametersWizardStepView extends GcgWizardStepView {

    protected FiscalYearWidgetTextView fiscalYearWidgetTextView;
    protected GcgContainerGroupBoxLinear cadenceParametersLayout;
    protected CadenceDurationWidgetSpinner cadenceDurationSpinner;
    protected WorkPlanFirstDayOfWeekWidgetSpinner workPlanFirstDayOfWeekWidgetSpinner;

	public CreateAllCadenceParametersWizardStepView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected int getPageTitleResourceId() {
		return R.string.cadence_parameters;
	}

	@Override
	protected int getViewLayoutResourceId() {
		return R.layout.create__all_cadence_for_year__parameters__wizard_step;
	}

	@Override
	public void initialize(GcgActivity anGcgActivity, GcgViewFlipper aViewFlipper, int aPageNumber) {
		super.initialize(anGcgActivity, aViewFlipper, aPageNumber);
        this.fiscalYearWidgetTextView = (FiscalYearWidgetTextView) findViewById(R.id.fiscal_year__text_view);
        this.fiscalYearWidgetTextView.setFiscalYear(getFiscalYear());
        this.cadenceDurationSpinner = (CadenceDurationWidgetSpinner) findViewById(R.id.cadence_duration__spinner);
        this.workPlanFirstDayOfWeekWidgetSpinner = (WorkPlanFirstDayOfWeekWidgetSpinner) findViewById(R.id.work_plan__first_day_of_week__spinner);
	}
	
	@Override
	public void activateView() {
		super.activateView();
        CreateAllCadenceForYearWizardStepFlipper theWizardStepFlipper = (CreateAllCadenceForYearWizardStepFlipper) getViewFlipper();
	}

    private FiscalYear getFiscalYear() {
        return ((CreateAllCadenceForYearWizardStepFlipper) getViewFlipper()).getFiscalYear();
    }

    @Override
    public String getSummaryText() {
        StringBuffer theStringBuffer = new StringBuffer();
        theStringBuffer.append("<font color='#0000FF'>" + GcgApplication.getAppResources().getString(R.string.cadence_duration) + "</font><br/>");
        theStringBuffer.append(GcgHelper.html__INDENT + this.cadenceDurationSpinner.getSelectedItem().getDataText() + GcgHelper.html__NEW_LINE + GcgHelper.html__NEW_LINE);
        theStringBuffer.append("<font color='#0000FF'>" + GcgApplication.getAppResources().getString(R.string.work_plan__first_day_of_week) + "</font><br/>");
        theStringBuffer.append(GcgHelper.html__INDENT + this.workPlanFirstDayOfWeekWidgetSpinner.getSelectedItem().getDataText());
        return theStringBuffer.toString();
    }

	@Override
	public boolean validWizardStepData() { return true; }

	@Override
	protected String getHelpContextUrlString() {
		return FmsHelpIndex.CADENCE_PARAMETERS__WIZARD_STEP;
	}

    public int getCadenceDuration() {
        return Integer.decode(this.cadenceDurationSpinner.getSelectedItem().getDataText());
    }

    public String getGregorianDayOfWeek() {
        return this.workPlanFirstDayOfWeekWidgetSpinner.getGregorianDayOfWeek();
    }

}
