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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.flywheelms.gcongui.gcg.activity.GcgActivity;
import com.flywheelms.gcongui.gcg.container.GcgContainerGroupBoxLinear;
import com.flywheelms.gcongui.gcg.helper.GcgHelper;
import com.flywheelms.gcongui.gcg.viewflipper.GcgViewFlipper;
import com.flywheelms.gcongui.gcg.widget.date.GcgDateHelper;
import com.flywheelms.gcongui.gcg.widget.date.GcgWidgetDatePicker;
import com.flywheelms.gcongui.gcg.wizard.step.GcgWizardStepView;
import com.flywheelms.library.R;
import com.flywheelms.library.fmm.enumerator.FmmHoliday;
import com.flywheelms.library.fmm.node.impl.governable.FiscalYear;
import com.flywheelms.library.fmm.node.impl.headline.FiscalYearHolidayBreak;
import com.flywheelms.library.fms.helper.FmsHelpIndex;
import com.flywheelms.library.fms.widget.text_view.FiscalYearWidgetTextView;
import com.flywheelms.library.fms.wizard.CreateAllCadenceForYearWizardStepFlipper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CreateAllCadenceHolidaysWizardStepView extends GcgWizardStepView {

    protected FiscalYearWidgetTextView fiscalYearWidgetTextView;
    private GcgContainerGroupBoxLinear holidayParametersGroupbox;
    private HashMap<FmmHoliday, HolidayRow> holidayRowTable = new HashMap<FmmHoliday, HolidayRow>();
    private int dayAfterNewYearsBreakEnd;

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
        this.fiscalYearWidgetTextView = (FiscalYearWidgetTextView) findViewById(R.id.fiscal_year__text_view);
        this.fiscalYearWidgetTextView.setFiscalYear(getFiscalYear());
        this.holidayParametersGroupbox = (GcgContainerGroupBoxLinear) findViewById(R.id.group_box__holiday_parameters);
        for(FmmHoliday aHoliday : FmmHoliday.values()) {
            this.holidayRowTable.put(aHoliday, new HolidayRow(
                    aHoliday,
                    this.gcgActivity,
                    this.holidayParametersGroupbox ));
        }
	}
	
	@Override
	protected void activateView() {
		super.activateView();
	}

	@Override
	public String getSummaryText() {
        StringBuffer theStringBuffer = new StringBuffer();
        for(FmmHoliday theFmmHoliday : FmmHoliday.values()) {
            HolidayRow theHolidayRow = this.holidayRowTable.get(theFmmHoliday);
            if(theHolidayRow != null && theHolidayRow.isEnabled()) {
                theStringBuffer.append(GcgHelper.html__INDENT + theHolidayRow.getHeadline() + GcgHelper.html__NEW_LINE + GcgHelper.html__NEW_LINE);
            }
        }
//        theStringBuffer.append("<font color='#0000FF'>" + GcgApplication.getAppResources().getString(R.string.work_plan__first_day_of_week) + "</font><br/>");
//        theStringBuffer.append(GcgHelper.html__INDENT + this.workPlanFirstDayOfWeekWidgetSpinner.getSelectedItem().getDataText());
        return theStringBuffer.toString();
    }

	@Override
	public boolean validWizardStepData() { return true; }

	@Override
	protected String getHelpContextUrlString() {
		return FmsHelpIndex.HOLIDAY_PARAMETERS__WIZARD_STEP;
	}

    private FiscalYear getFiscalYear() {
        return ((CreateAllCadenceForYearWizardStepFlipper) getViewFlipper()).getFiscalYear();
    }
    
    public ArrayList<FiscalYearHolidayBreak> getFiscalYearHolidayBreakList() {
        ArrayList<FiscalYearHolidayBreak> theBreakList = new ArrayList<FiscalYearHolidayBreak>();
        for(HolidayRow theHolidayRow : this.holidayRowTable.values()) {
            if(theHolidayRow.isEnabled()) {
                theBreakList.add(new FiscalYearHolidayBreak(
                        getFiscalYear(),
                        theHolidayRow.fmmHoliday,
                        theHolidayRow.holidayDate.getSelectedDate(),
                        theHolidayRow.breakFirstDay.getSelectedDate(),
                        theHolidayRow.breakLastDay.getSelectedDate()
                ));
            }
        }
        return theBreakList;
    }

    public int getDayAfterNewYearsBreakEnd() {
        int theDayAfterNewYearsBreak = 1;
        HolidayRow theHolidayRow = this.holidayRowTable.get(FmmHoliday.NEW_YEARS_DAY);
        if(theHolidayRow != null) {
            Calendar theCalendar = Calendar.getInstance();
            theCalendar.setTime(theHolidayRow.breakLastDay.getSelectedDate());
            theCalendar.add(Calendar.DATE, 1);
            theDayAfterNewYearsBreak = GcgDateHelper.getDayOfMonth(theCalendar.getTime());
        }
        return theDayAfterNewYearsBreak;
    }

    private class HolidayRow {

        private FmmHoliday fmmHoliday;
        private CheckBox checkbox;
        private ImageView imageView;
        private TextView textView;
        private GcgWidgetDatePicker holidayDate;
        private GcgWidgetDatePicker breakFirstDay;
        private GcgWidgetDatePicker breakLastDay;

        protected HolidayRow(
                FmmHoliday anFmmHoliday,
                GcgActivity anActivity,
                GcgContainerGroupBoxLinear aGroupBox ) {
            this.fmmHoliday = anFmmHoliday;
            LinearLayout theRowsContainer = (LinearLayout) ((ScrollView) aGroupBox.getChildAt(1)).getChildAt(0);
            LayoutInflater theLayoutInflater = LayoutInflater.from(getContext());
            LinearLayout theRowLayout = (LinearLayout) theLayoutInflater.inflate(R.layout.fmm_holiday__row, theRowsContainer, false);
            theRowsContainer.addView(theRowLayout);
            this.checkbox = (CheckBox) theRowLayout.getChildAt(0);
            this.imageView = (ImageView) theRowLayout.getChildAt(1);
            this.imageView.setImageResource(anFmmHoliday.getDataDrawableResourceId());
            this.textView = (TextView) theRowLayout.getChildAt(2);
            this.textView.setText(anFmmHoliday.getDataText());
            this.holidayDate = (GcgWidgetDatePicker) theRowLayout.getChildAt(3);
            this.holidayDate.setGcgActivity(anActivity);
            this.holidayDate.setOriginalDate(anFmmHoliday.getHolidayDate(getFiscalYear().getYearAsInt()));
            this.breakFirstDay = (GcgWidgetDatePicker) theRowLayout.getChildAt(4);
            this.breakFirstDay.setGcgActivity(anActivity);
            this.breakFirstDay.setOriginalDate(anFmmHoliday.getRecommendedFirstDayOfHolidayBreak(getFiscalYear().getYearAsInt()));
            this.breakLastDay = (GcgWidgetDatePicker) theRowLayout.getChildAt(5);
            this.breakLastDay.setGcgActivity(anActivity);
            this.breakLastDay.setOriginalDate(anFmmHoliday.getRecommendedLastDayOfHolidayBreak(getFiscalYear().getYearAsInt()));
            this.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton aCompoundButton, boolean bChecked) {
                    HolidayRow.this.holidayDate.setVisibility(bChecked ? View.VISIBLE : View.INVISIBLE);
                    HolidayRow.this.breakFirstDay.setVisibility(bChecked ? View.VISIBLE : View.INVISIBLE);
                    HolidayRow.this.breakLastDay.setVisibility(bChecked ? View.VISIBLE : View.INVISIBLE);
                }
            });
            this.imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    HolidayRow.this.checkbox.performClick();
                }
            });
            this.textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    HolidayRow.this.checkbox.performClick();
                }
            });
        }

        public boolean isEnabled() {
            return this.checkbox.isChecked();
        }

        public String getHeadline() {
            return this.textView.getText().toString();
        }
    }

}
