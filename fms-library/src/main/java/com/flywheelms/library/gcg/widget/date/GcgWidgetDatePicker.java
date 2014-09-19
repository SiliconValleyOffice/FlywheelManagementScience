/* @(#)GcgWidgetDatePicker.java
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

package com.flywheelms.library.gcg.widget.date;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;

import com.flywheelms.library.R;
import com.flywheelms.library.gcg.activity.GcgActivity;
import com.flywheelms.library.gcg.widget.GcgWidgetTextView;

import java.util.Date;
import java.util.GregorianCalendar;

// com.flywheelms.library.gcg.widget.date.GcgWidgetDatePicker
public class GcgWidgetDatePicker extends GcgWidgetTextView {
	
	private boolean enabled = true;
	private final GregorianCalendar gregorianCalendar;
	private Date originalDate;
	private boolean mustBeDifferentFromOriginal = false;
	private GregorianCalendar minDate = null;
	private GregorianCalendar maxDate = null;
	
	public GcgWidgetDatePicker(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
		this.gregorianCalendar = new GregorianCalendar();
	}
	
	@Override
	protected boolean deferredSetup() {
		return true;
	}

	@Override
	protected void setup() {
		super.setup();
        if (!this.textView.isClickable()) {
            this.textView.setClickable(true);
        }
        this.textView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				GcgWidgetDatePicker.this.getDatePickerDialog().show();
			}
		});
	}
	
	private DatePickerDialog getDatePickerDialog() {
		DatePickerDialog theDatePickerDialog =  new DatePickerDialog(this.gcgActivity, new DatePickerDialog.OnDateSetListener() {
			 
            @Override
            public void onDateSet(DatePicker aDatePicker, int aYear, int aMonth, int aDay) {
            	GcgWidgetDatePicker.this.setSelectedDate(aYear, aMonth, aDay);
            }
        },
        GcgDateHelper.getYear(this.gregorianCalendar.getTime()),
        GcgDateHelper.getMonth(this.gregorianCalendar.getTime()) - 1,
        GcgDateHelper.getDay(this.gregorianCalendar.getTime()) );
		if(this.minDate != null) {
			theDatePickerDialog.getDatePicker().setMinDate(this.minDate.getTimeInMillis());
		}
		if(this.maxDate != null) {
			theDatePickerDialog.getDatePicker().setMaxDate(this.maxDate.getTimeInMillis());
		}
		return theDatePickerDialog;
	}

	@Override
	protected int getWidgetLayoutResourceId() {
		int theResourceId = R.layout.gcg__widget__date_picker__horizontal;
//		if(this.containerLayout.equals(container_layout__MENU_PARAMETER)) {
//			theResourceId = R.layout.gcg__widget__date_picker__menu_parameter;
//		} else if(this.containerLayout.equals(container_layout__VERTICAL)) {
//			if(this.scrollableContent) {
//				theResourceId = R.layout.gcg__widget__date_picker__vertical__scrollable;
//			} else {
//				theResourceId = R.layout.gcg__widget__date_picker__vertical;
//			}
//		} else if(this.containerLayout.equals(container_layout__VERTICAL__NO_LABEL_DRAWABLE)) {
//			theResourceId = R.layout.gcg__widget__date_picker__vertical;
//		} else if(this.containerLayout.equals(container_layout__NO_LABEL)) {
//			theResourceId = R.layout.gcg__widget__date_picker__no_label;
//		}
		return theResourceId;
	}
	
	@Override
	public void setGcgActivity(GcgActivity aGcgActivity) {
		super.setGcgActivity(aGcgActivity);
		setup();
	}
	
	protected void setSelectedDate(int aYear, int aMonth, int aDay) {
		this.gregorianCalendar.set(aYear, aMonth, aDay);
		setText(GcgDateHelper.getGuiDateString3(this.gregorianCalendar.getTime()));
		manageBackgroundState();
	}
	
	public Date getOriginalDate() {
		return this.originalDate;
	}

	public void setOriginalDate(Date anOriginalDate) {
		setOriginalDate(anOriginalDate, false);
	}

	public void setOriginalDate(Date anOriginalDate, boolean bMustBeDifferentFromOriginal) {
		this.mustBeDifferentFromOriginal = bMustBeDifferentFromOriginal;
		this.originalDate = anOriginalDate;
		setSelectedDate(GcgDateHelper.getYear(anOriginalDate), GcgDateHelper.getMonth(anOriginalDate) - 1, GcgDateHelper.getDay(anOriginalDate));
	}

	public boolean mustBeDifferentFromOriginal() {
		return this.mustBeDifferentFromOriginal;
	}

	public void setMustBeDifferentFromOriginal(boolean mustBeDifferentFromOriginal) {
		this.mustBeDifferentFromOriginal = mustBeDifferentFromOriginal;
	}

	@Override
	public void setEnabled(boolean bEnabled) {
		this.enabled = bEnabled;
		this.textView.setEnabled(bEnabled);
		this.textView.setClickable(bEnabled);
		manageBackgroundState();
	}
	
	public boolean enabled() {
		return this.enabled;
	}

	@Override
	protected void manageBackgroundState() {
		this.textView.setBackgroundResource(enabled() ? isMinimumInput() ?
					R.drawable.gcg__date_picker__background : R.drawable.gcg__date_picker__background__invalid : R.color.w3c__silver);
	}

	@Override
	public boolean isMinimumInput() {
		return this.textView.getText().length() > 3 && ! dateChangeNeeded();
	}

	private boolean dateChangeNeeded() {
		return this.mustBeDifferentFromOriginal && GcgDateHelper.sameDate(this.gregorianCalendar, this.originalDate);
	}

	public Date getSelectedDate() {
		return this.gregorianCalendar.getTime();
	}
	
	public void setYearRange(int aYear) {
		setYearRange(aYear, aYear);
	}
	
	public void setYearRange(int aMinYear, int aMaxYear) {
		this.minDate = new GregorianCalendar(aMinYear, 0, 1);
		this.maxDate = new GregorianCalendar(aMaxYear, 11, 31);
	}
	
	public void setRemainderOfYearRange(int aYear) {
		if(aYear == GcgDateHelper.getCurrentYear()) {
			this.minDate = new GregorianCalendar();
			this.maxDate = new GregorianCalendar(aYear, 11, 31);
		} else {
			setYearRange(aYear, aYear);
		}
	}

	@Override
	public void setInitialValue() {
		// TODO - add GCG custom attribute(s) to manage initialization
//		this.textView.setText(GcgDateHelper.getGuiDateString0(this.gregorianCalendar.getTime()));
	}

}
