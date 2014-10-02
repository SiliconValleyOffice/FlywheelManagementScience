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
import com.flywheelms.gcongui.gcg.widget.date.GcgDateHelper;
import com.flywheelms.gcongui.gcg.wizard.GcgWizardStepFlipper;
import com.flywheelms.library.R;
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.node.impl.governable.FiscalYear;
import com.flywheelms.library.fmm.node.impl.governable.FlywheelCadence;
import com.flywheelms.library.fmm.node.impl.governable.WorkPlan;
import com.flywheelms.library.fms.activity.CreateAllFlywheelCadenceForYearWizard;
import com.flywheelms.library.fms.wizard.step.CreateAllCadenceDoItNowWizardStepView;
import com.flywheelms.library.fms.wizard.step.CreateAllCadenceHolidaysWizardStepView;
import com.flywheelms.library.fms.wizard.step.CreateAllCadenceParametersWizardStepView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CreateAllCadenceForYearWizardStepFlipper extends GcgWizardStepFlipper {

	private CreateAllCadenceParametersWizardStepView parametersWizardStepView;
	private CreateAllCadenceHolidaysWizardStepView holidaysWizardStepView;
	private CreateAllCadenceDoItNowWizardStepView doItNowWizardStepView;
    private Date lastDayOfTheYear;
    private GregorianCalendar theLastDayOfThePreviousWorkPlan;

    public CreateAllCadenceForYearWizardStepFlipper(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
	}

    private Date getLastDayOfTheYear() {
        if(this.lastDayOfTheYear == null) {
            this.lastDayOfTheYear = new GregorianCalendar(getFiscalYear().getYearAsInt(), Calendar.DECEMBER, 31 ).getTime();
        }
        return this.lastDayOfTheYear;
    }

    private GregorianCalendar getLastDayOfThePreviousWorkPlan() {
        if(this.theLastDayOfThePreviousWorkPlan == null) {
            this.theLastDayOfThePreviousWorkPlan = new GregorianCalendar(getFiscalYear().getYearAsInt() - 1, Calendar.DECEMBER, 31 );
        }
        return this.theLastDayOfThePreviousWorkPlan;
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
		GcgHelper.makeToast("Creating all Flywheel Cadence for Fiscal Year " + getFiscalYear().getHeadline() + "...");
        getFiscalYear().setCadenceDuration(getWizardStepView1().getCadenceDuration());
        getFiscalYear().setWorkPlanFirstDayOfWeek(getWizardStepView1().getWorkPlanFirstDayOfWeek());
        FmmDatabaseMediator.getActiveMediator().updateFiscalYear(getFiscalYear(), true);
        FmmDatabaseMediator.getActiveMediator().insertFiscalYearHolidayBreakList(getWizardStepView2().getFiscalYearHolidayBreakList(), true);
        FmmDatabaseMediator.getActiveMediator().insertFlywheelCadenceList(generateFlywheelCadenceList(), true);
		getGcgActivity().finish();
	}

    private ArrayList<FlywheelCadence> generateFlywheelCadenceList() {
        ArrayList<FlywheelCadence> theCadenceList = new ArrayList<FlywheelCadence>();
        int theSequence = 1;
        while (! getLastDayOfThePreviousWorkPlan().getTime().equals(getLastDayOfTheYear())) {
            theCadenceList.add(getNextFlywheelCadence(theSequence));
            ++ theSequence;
        }
        return theCadenceList;
    }

    private Date getFirstWorkPlanStartDate() {
        GregorianCalendar theFirstDate = new GregorianCalendar(
            getFiscalYear().getYearAsInt(),
            0,
            1 );

        return theFirstDate.getTime();
    }

    private Date getFirstWorkPlanEndDate() {
        GregorianCalendar theFirstDate = new GregorianCalendar(
                getFiscalYear().getYearAsInt(),
                0,
                getWizardStepView2().getDayAfterNewYearsBreakEnd() );

        while(theFirstDate.get(Calendar.DAY_OF_WEEK) != getWizardStepView1().getWorkPlanFirstDayOfWeekAsCalendarConstant()) {
            theFirstDate.add(Calendar.DATE, 1);
        }
        return new Date();
    }

    @Override
	public boolean validWizardData() {
        return this.parametersWizardStepView.validWizardStepData() &&
                this.holidaysWizardStepView.validWizardStepData();
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

    public FlywheelCadence getNextFlywheelCadence(int aSequence) {
        FlywheelCadence theFlywheelCadence = new FlywheelCadence(getFiscalYear());
        theFlywheelCadence.setSequence(aSequence);
        theFlywheelCadence.setHeadline("Cadence " + aSequence);
        ArrayList<WorkPlan> theWorkPlanList = new ArrayList<WorkPlan>();
        GregorianCalendar thePlanStartDate;
        GregorianCalendar thePlanEndDate;
        for(int theIndex = 0; theIndex < getWizardStepView1().getCadenceDuration(); ++theIndex) {
            thePlanStartDate = GcgDateHelper.cloneCalendar(getLastDayOfThePreviousWorkPlan());
            thePlanStartDate.add(Calendar.DATE, 1);
            thePlanEndDate = GcgDateHelper.cloneCalendar(thePlanStartDate);
            thePlanEndDate.add(Calendar.DATE, 6);
            WorkPlan theWorkPlan = new WorkPlan(theFlywheelCadence, thePlanStartDate.getTime(), thePlanEndDate.getTime());
            theWorkPlan.setHeadline("Work Plan " + aSequence + "-" + theIndex + 1);
            adjustWorkPlanForHolidays(theWorkPlan, thePlanEndDate);
            theWorkPlanList.add(theWorkPlan);
            getLastDayOfThePreviousWorkPlan().setTime(theWorkPlan.getScheduledEndDate());
            if(theWorkPlan.getScheduledEndDate().equals(getLastDayOfTheYear())) {
                break;
            }
        }
        theFlywheelCadence.setWorkPlanList(theWorkPlanList);
        return theFlywheelCadence;
    }

    private void adjustWorkPlanForHolidays(WorkPlan theWorkPlan, GregorianCalendar thePlanEndDate) {
        if(theWorkPlan.getScheduledEndDate().after(getLastDayOfTheYear())) {
            theWorkPlan.setScheduledEndDate(getLastDayOfTheYear());
        }
        // adjust headline and end date
    }
}
