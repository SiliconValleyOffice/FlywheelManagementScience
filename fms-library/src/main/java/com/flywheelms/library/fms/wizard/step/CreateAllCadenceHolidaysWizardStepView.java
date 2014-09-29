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
import android.widget.CheckBox;

import com.flywheelms.gcongui.gcg.activity.GcgActivity;
import com.flywheelms.gcongui.gcg.viewflipper.GcgViewFlipper;
import com.flywheelms.gcongui.gcg.widget.date.GcgWidgetDatePicker;
import com.flywheelms.gcongui.gcg.wizard.step.GcgWizardStepView;
import com.flywheelms.library.R;
import com.flywheelms.library.fmm.node.impl.governable.FiscalYear;
import com.flywheelms.library.fmm.node.impl.headline.FiscalYearHolidayBreak;
import com.flywheelms.library.fms.helper.FmsHelpIndex;
import com.flywheelms.library.fms.wizard.CreateAllCadenceForYearWizardStepFlipper;

import java.util.ArrayList;

public class CreateAllCadenceHolidaysWizardStepView extends GcgWizardStepView {

    private CheckBox checkboxNewYearsDay;
    private GcgWidgetDatePicker holidayDateNewYearsDay;
    private GcgWidgetDatePicker breakFirstDayNewYearsDay;
    private GcgWidgetDatePicker breakLastDayNewYearsDay;

    private CheckBox checkboxPresidentsDay;
    private GcgWidgetDatePicker holidayDatePresidentsDay;
    private GcgWidgetDatePicker breakFirstDayPresidentsDay;
    private GcgWidgetDatePicker breakLastDayPresidentsDay;

    private CheckBox checkboxMemorialDay;
    private GcgWidgetDatePicker holidayDateMemorialDay;
    private GcgWidgetDatePicker breakFirstDayMemorialDay;
    private GcgWidgetDatePicker breakLastDayMemorialDay;

    private CheckBox checkboxIndependenceDay;
    private GcgWidgetDatePicker holidayDateIndependenceDay;
    private GcgWidgetDatePicker breakFirstDayIndependenceDay;
    private GcgWidgetDatePicker breakLastDayIndependenceDay;

    private CheckBox checkboxLaborDay;
    private GcgWidgetDatePicker holidayDateLaborDay;
    private GcgWidgetDatePicker breakFirstDayLaborDay;
    private GcgWidgetDatePicker breakLastDayLaborDay;

    private CheckBox checkboxThanksgivingDay;
    private GcgWidgetDatePicker holidayDateThanksgivingDay;
    private GcgWidgetDatePicker breakFirstDayThanksgivingDay;
    private GcgWidgetDatePicker breakLastDayThanksgivingDay;

    private CheckBox checkboxChristmasDay;
    private GcgWidgetDatePicker holidayDateChristmasDay;
    private GcgWidgetDatePicker breakFirstDayChristmasDay;
    private GcgWidgetDatePicker breakLastDayChristmasDay;

	public CreateAllCadenceHolidaysWizardStepView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected int getPageTitleResourceId() {
		return R.string.holiday_parameters;
	}

	@Override
	protected int getViewLayoutResourceId() {
		return R.layout.create__all_cadence_for_year__holidays__wizard_step;
	}

	@Override
	public void initialize(GcgActivity anGcgActivity, GcgViewFlipper aViewFlipper, int aPageNumber) {
		super.initialize(anGcgActivity, aViewFlipper, aPageNumber);
        this.checkboxNewYearsDay = (CheckBox) findViewById(R.id.holiday__new_years_day__checkbox);
        this.holidayDateNewYearsDay = (GcgWidgetDatePicker) findViewById(R.id.holiday__new_years_day__day);
        this.holidayDateNewYearsDay.setGcgActivity(this.gcgActivity);
        this.breakFirstDayNewYearsDay = (GcgWidgetDatePicker) findViewById(R.id.holiday__new_years_day__break_first_day);
        this.breakFirstDayNewYearsDay.setGcgActivity(this.gcgActivity);
        this.breakLastDayNewYearsDay = (GcgWidgetDatePicker) findViewById(R.id.holiday__new_years_day__break_last_day);
        this.breakLastDayNewYearsDay.setGcgActivity(this.gcgActivity);

        this.checkboxPresidentsDay = (CheckBox) findViewById(R.id.holiday__presidents_day__checkbox);
        this.holidayDatePresidentsDay = (GcgWidgetDatePicker) findViewById(R.id.holiday__presidents_day__day);
        this.holidayDatePresidentsDay.setGcgActivity(this.gcgActivity);
        this.breakFirstDayPresidentsDay = (GcgWidgetDatePicker) findViewById(R.id.holiday__presidents_day__break_first_day);
        this.breakFirstDayPresidentsDay.setGcgActivity(this.gcgActivity);
        this.breakLastDayPresidentsDay = (GcgWidgetDatePicker) findViewById(R.id.holiday__presidents_day__break_last_day);
        this.breakLastDayPresidentsDay.setGcgActivity(this.gcgActivity);

        this.checkboxMemorialDay = (CheckBox) findViewById(R.id.holiday__memorial_day__checkbox);
        this.holidayDateMemorialDay = (GcgWidgetDatePicker) findViewById(R.id.holiday__memorial_day__day);
        this.holidayDateMemorialDay.setGcgActivity(this.gcgActivity);
        this.breakFirstDayMemorialDay = (GcgWidgetDatePicker) findViewById(R.id.holiday__memorial_day__break_first_day);
        this.breakFirstDayMemorialDay.setGcgActivity(this.gcgActivity);
        this.breakLastDayMemorialDay = (GcgWidgetDatePicker) findViewById(R.id.holiday__memorial_day__break_last_day);
        this.breakLastDayMemorialDay.setGcgActivity(this.gcgActivity);

        this.checkboxIndependenceDay = (CheckBox) findViewById(R.id.holiday__independence_day__checkbox);
        this.holidayDateIndependenceDay = (GcgWidgetDatePicker) findViewById(R.id.holiday__independence_day__day);
        this.holidayDateIndependenceDay.setGcgActivity(this.gcgActivity);
        this.breakFirstDayIndependenceDay = (GcgWidgetDatePicker) findViewById(R.id.holiday__independence_day__break_first_day);
        this.breakFirstDayIndependenceDay.setGcgActivity(this.gcgActivity);
        this.breakLastDayIndependenceDay = (GcgWidgetDatePicker) findViewById(R.id.holiday__independence_day__break_last_day);
        this.breakLastDayIndependenceDay.setGcgActivity(this.gcgActivity);

        this.checkboxLaborDay = (CheckBox) findViewById(R.id.holiday__labor_day__checkbox);
        this.holidayDateLaborDay = (GcgWidgetDatePicker) findViewById(R.id.holiday__labor_day__day);
        this.holidayDateLaborDay.setGcgActivity(this.gcgActivity);
        this.breakFirstDayLaborDay = (GcgWidgetDatePicker) findViewById(R.id.holiday__labor_day__break_first_day);
        this.breakFirstDayLaborDay.setGcgActivity(this.gcgActivity);
        this.breakLastDayLaborDay = (GcgWidgetDatePicker) findViewById(R.id.holiday__labor_day__break_last_day);
        this.breakLastDayLaborDay.setGcgActivity(this.gcgActivity);

        this.checkboxThanksgivingDay = (CheckBox) findViewById(R.id.holiday__thanksgiving__checkbox);
        this.holidayDateThanksgivingDay = (GcgWidgetDatePicker) findViewById(R.id.holiday__thanksgiving__day);
        this.holidayDateThanksgivingDay.setGcgActivity(this.gcgActivity);
        this.breakFirstDayThanksgivingDay = (GcgWidgetDatePicker) findViewById(R.id.holiday__thanksgiving__break_first_day);
        this.breakFirstDayThanksgivingDay.setGcgActivity(this.gcgActivity);
        this.breakLastDayThanksgivingDay = (GcgWidgetDatePicker) findViewById(R.id.holiday__thanksgiving__break_last_day);
        this.breakLastDayThanksgivingDay.setGcgActivity(this.gcgActivity);

        this.checkboxChristmasDay = (CheckBox) findViewById(R.id.holiday__christmas__checkbox);
        this.holidayDateChristmasDay = (GcgWidgetDatePicker) findViewById(R.id.holiday__christmas__day);
        this.holidayDateChristmasDay.setGcgActivity(this.gcgActivity);
        this.breakFirstDayChristmasDay = (GcgWidgetDatePicker) findViewById(R.id.holiday__christmas__break_first_day);
        this.breakFirstDayChristmasDay.setGcgActivity(this.gcgActivity);
        this.breakLastDayChristmasDay = (GcgWidgetDatePicker) findViewById(R.id.holiday__christmas__break_last_day);
        this.breakLastDayChristmasDay.setGcgActivity(this.gcgActivity);
	}
	
	@Override
	protected void activateView() {
		super.activateView();
	}

	@Override
	public String getSummaryText() { return "Not implemented."; }

	@Override
	public boolean validWizardStepData() { return true; }

	@Override
	protected String getHelpContextUrlString() {
		return FmsHelpIndex.HOLIDAY_PARAMETERS__WIZARD_STEP;
	}

    private FiscalYear getFiscalYear() {
        return ((CreateAllCadenceForYearWizardStepFlipper) getViewFlipper()).getFiscalYear();
    }
    
    public ArrayList<FiscalYearHolidayBreak> getHolidayBreakList() {
        ArrayList<FiscalYearHolidayBreak> theBreakList = new ArrayList<FiscalYearHolidayBreak>();
        return theBreakList;
    }

}
