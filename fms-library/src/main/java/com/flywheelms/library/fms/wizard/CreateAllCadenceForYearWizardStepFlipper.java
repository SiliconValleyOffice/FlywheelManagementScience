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

import com.flywheelms.gcongui.gcg.activity.GcgActivity;
import com.flywheelms.gcongui.gcg.helper.GcgHelper;
import com.flywheelms.gcongui.gcg.widget.date.GcgDateHelper;
import com.flywheelms.gcongui.gcg.widget.date.GcgDayOfWeek;
import com.flywheelms.gcongui.gcg.wizard.GcgWizardStepFlipper;
import com.flywheelms.library.R;
import com.flywheelms.library.fmm.FmmDatabaseService;
import com.flywheelms.library.fmm.node.impl.governable.Cadence;
import com.flywheelms.library.fmm.node.impl.governable.FiscalYear;
import com.flywheelms.library.fmm.node.impl.governable.WorkPlan;
import com.flywheelms.library.fmm.node.impl.headline.FiscalYearHolidayBreak;
import com.flywheelms.library.fms.activity.CreateAllCadenceForYearWizard;
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
    private GregorianCalendar lastDayOfTheYear;
    private GregorianCalendar lastDayOfTheYearToSchedule;
    private GregorianCalendar theLastDayOfThePreviousWorkPlan;

    public CreateAllCadenceForYearWizardStepFlipper(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
	}

    private GregorianCalendar getLastDayOfTheYear() {
        if(this.lastDayOfTheYear == null) {
            this.lastDayOfTheYear = new GregorianCalendar(getFiscalYear().getYearAsInt(), Calendar.DECEMBER, 31 );
        }
        return this.lastDayOfTheYear;
    }

    private GregorianCalendar getLastDayOfTheYearToSchedule() {
        if(this.lastDayOfTheYearToSchedule == null) {
            this.lastDayOfTheYearToSchedule = new GregorianCalendar(getFiscalYear().getYearAsInt(), Calendar.DECEMBER, 23 );
        }
        return this.lastDayOfTheYearToSchedule;
    }

    private GregorianCalendar getLastDayOfThePreviousWorkPlan() {
        if(this.theLastDayOfThePreviousWorkPlan == null) {
            this.theLastDayOfThePreviousWorkPlan = getLastDayOfPreviousYear();
        }
        return this.theLastDayOfThePreviousWorkPlan;
    }

    private GregorianCalendar getLastDayOfPreviousYear() {
        return new GregorianCalendar(getFiscalYear().getYearAsInt() - 1, Calendar.DECEMBER, 31 );
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
		GcgHelper.makeToast("Creating all Cadence for Fiscal Year " + getFiscalYear().getHeadline() + "...");
        getFiscalYear().setCadenceDuration(getWizardStepView1().getCadenceDuration());
        getFiscalYear().setWorkPlanFirstDayOfWeek(getWizardStepView1().getWorkPlanFirstDayOfWeek().getDayOfWeekName());
        FmmDatabaseService.getActiveMediator().updateFiscalYear(getFiscalYear(), true);
        FmmDatabaseService.getActiveMediator().insertFiscalYearHolidayBreakList(getWizardStepView2().getFiscalYearHolidayBreakList(), true);
        FmmDatabaseService.getActiveMediator().insertCadenceList(generateCadenceList(), true);
		getGcgActivity().finish(GcgActivity.REFRESH_DATA, GcgActivity.RESTORE_GUI_STATE);
	}

    private ArrayList<Cadence> generateCadenceList() {
        ArrayList<Cadence> theCadenceList = new ArrayList<Cadence>();
        theCadenceList.add(getFirstCadence());
        if(GcgDateHelper.sameDay(theCadenceList.get(0).getScheduledEndDate(), getLastDayOfTheYear())) {
            return theCadenceList;
        }
        int theSequence = 2;
        while (! GcgDateHelper.sameDay(getLastDayOfThePreviousWorkPlan(), getLastDayOfTheYear())) {
            theCadenceList.add(getNextCadence(theSequence));
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

    private GregorianCalendar getDateOfFirstPlanEndDayOfWeek() {
        GregorianCalendar theGregorianCalendar = GcgDayOfWeek.getDateForNextDayOfWeek(getLastDayOfPreviousYear(), this.parametersWizardStepView.getWorkPlanLasttDayOfWeek());
        return GcgDayOfWeek.getDateForNextDayOfWeek(theGregorianCalendar, this.parametersWizardStepView.getWorkPlanLasttDayOfWeek());

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
        return ((CreateAllCadenceForYearWizard) getGcgActivity()).getFiscalYear();
    }

    public Cadence getFirstCadence() {
        Cadence theCadence = new Cadence(getFiscalYear());
        theCadence.setSequence(1);
        theCadence.setHeadline("Cadence 1");
        ArrayList<WorkPlan> theWorkPlanList = new ArrayList<WorkPlan>();
        // create first work plan
        GregorianCalendar thePlanStartDate = getLastDayOfPreviousYear();
        thePlanStartDate.add(Calendar.DATE, 1);
        GregorianCalendar thePlanEndDate = getDateOfFirstPlanEndDayOfWeek();
        WorkPlan theFirstWorkPlan = new WorkPlan(theCadence, thePlanStartDate.getTime(), thePlanEndDate.getTime());
        theFirstWorkPlan.setHeadline("Work Plan 1-1");
        theFirstWorkPlan.setSequence(1);
        adjustWorkPlanForHolidays(theFirstWorkPlan, thePlanStartDate, thePlanEndDate);
        theWorkPlanList.add(theFirstWorkPlan);
        getLastDayOfThePreviousWorkPlan().setTime(theFirstWorkPlan.getScheduledEndDate());
        // create the rest of the work plans
        for(int theIndex = 1; theIndex < getWizardStepView1().getCadenceDuration(); ++theIndex) {
            thePlanStartDate = GcgDateHelper.cloneCalendar(getLastDayOfThePreviousWorkPlan());
            thePlanStartDate.add(Calendar.DATE, 1);
            thePlanEndDate = GcgDateHelper.cloneCalendar(thePlanStartDate);
            thePlanEndDate.add(Calendar.DATE, 6);
            WorkPlan theWorkPlan = new WorkPlan(theCadence, thePlanStartDate.getTime(), thePlanEndDate.getTime());
            theWorkPlan.setHeadline("Work Plan 1-" + Integer.toString(theIndex + 1));
            theWorkPlan.setSequence(theIndex + 1);
            adjustWorkPlanForHolidays(theWorkPlan, thePlanStartDate, thePlanEndDate);
            theWorkPlanList.add(theWorkPlan);
            getLastDayOfThePreviousWorkPlan().setTime(theWorkPlan.getScheduledEndDate());
        }
        theCadence.setWorkPlanList(theWorkPlanList);
        return theCadence;
    }

    public Cadence getNextCadence(int aCadenceNumber) {
        Cadence theCadence = new Cadence(getFiscalYear());
        theCadence.setSequence(aCadenceNumber);
        theCadence.setHeadline("Cadence " + aCadenceNumber);
        ArrayList<WorkPlan> theWorkPlanList = new ArrayList<WorkPlan>();
        GregorianCalendar thePlanStartDate;
        GregorianCalendar thePlanEndDate;
        for(int theIndex = 0; theIndex < getWizardStepView1().getCadenceDuration(); ++theIndex) {
            thePlanStartDate = GcgDateHelper.cloneCalendar(getLastDayOfThePreviousWorkPlan());
            thePlanStartDate.add(Calendar.DATE, 1);
            thePlanEndDate = GcgDateHelper.cloneCalendar(thePlanStartDate);
            thePlanEndDate.add(Calendar.DATE, 6);
            WorkPlan theWorkPlan = new WorkPlan(theCadence, thePlanStartDate.getTime(), thePlanEndDate.getTime());
            theWorkPlan.setHeadline("Work Plan " + aCadenceNumber + "-" + Integer.toString(theIndex + 1));
            theWorkPlan.setSequence(theIndex + 1);
            adjustWorkPlanForHolidays(theWorkPlan, thePlanStartDate, thePlanEndDate);
            theWorkPlanList.add(theWorkPlan);
            getLastDayOfThePreviousWorkPlan().setTime(theWorkPlan.getScheduledEndDate());
            if(GcgDateHelper.sameDay(theWorkPlan.getScheduledEndDate(), getLastDayOfTheYear())) {
                break;
            }
        }
        addDanglingWorkPlan(theCadence, theWorkPlanList, getWizardStepView1().getCadenceDuration() + 1);
        theCadence.setWorkPlanList(theWorkPlanList);
        return theCadence;
    }

    private void addDanglingWorkPlan(Cadence aCadence, ArrayList<WorkPlan> theWorkPlanList, int aWorkPlanNumber) {
        WorkPlan theLastWorkPlan = theWorkPlanList.get(theWorkPlanList.size() - 1);
        GregorianCalendar theLastWorkPlanEndDate = new GregorianCalendar();
        theLastWorkPlanEndDate.setTime(theLastWorkPlan.getScheduledEndDate());
        int theDaysRemainingInTheYear = getLastDayOfTheYear().get(GregorianCalendar.DAY_OF_YEAR) - theLastWorkPlanEndDate.get(GregorianCalendar.DAY_OF_YEAR);
        if(! GcgDateHelper.sameDay(theLastWorkPlanEndDate, getLastDayOfTheYear()) && theDaysRemainingInTheYear < 14) {
            GregorianCalendar thePlanStartDate = GcgDateHelper.cloneCalendar(getLastDayOfThePreviousWorkPlan());
            thePlanStartDate.add(Calendar.DATE, 1);
            WorkPlan theWorkPlan = new WorkPlan(aCadence, thePlanStartDate.getTime(), getLastDayOfTheYear().getTime());
            theWorkPlan.setHeadline("Work Plan " + aCadence.getSequence() + "-" + Integer.toString(aWorkPlanNumber));
            theWorkPlan.setSequence(aWorkPlanNumber);
            adjustWorkPlanForHolidays(theWorkPlan, thePlanStartDate, getLastDayOfTheYear());
            theWorkPlanList.add(theWorkPlan);
            getLastDayOfThePreviousWorkPlan().setTime(theWorkPlan.getScheduledEndDate());

        }
    }

    private void adjustWorkPlanForHolidays(WorkPlan theWorkPlan, GregorianCalendar aPlanStartDate, GregorianCalendar aPlanEndDate) {
        FiscalYearHolidayBreak theHolidayBreak = FmmDatabaseService.getActiveMediator().getFmsOrganization().includesFiscalYearHolidayBreak(getFiscalYear().getNodeIdString(), aPlanStartDate, aPlanEndDate);
        if(theHolidayBreak != null) {
            aPlanEndDate.add(Calendar.DATE, 7);
            theWorkPlan.setScheduledEndDate(aPlanEndDate.getTime());
            theWorkPlan.setFmmHoliday(theHolidayBreak.getFmmHoliday());
        }
        if(theWorkPlan.getScheduledEndDate().after(getLastDayOfTheYearToSchedule().getTime())) {
            theWorkPlan.setScheduledEndDate(getLastDayOfTheYear().getTime());
        }
    }
}
