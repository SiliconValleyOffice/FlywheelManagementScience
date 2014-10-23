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

package com.flywheelms.library.fmm.enumerator;

import android.graphics.drawable.Drawable;

import com.flywheelms.gcongui.gcg.GcgApplication;
import com.flywheelms.gcongui.gcg.interfaces.GcgGuiable;
import com.flywheelms.library.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

public enum FmmHoliday implements GcgGuiable {

    NEW_YEARS_DAY(R.string.holiday__new_years_day,R.drawable.holiday__new_years_day, new GregorianCalendar[] {
            new GregorianCalendar(2014, 0, 1),
            new GregorianCalendar(2015, 0, 1),
            new GregorianCalendar(2016, 0, 1),
            new GregorianCalendar(2017, 0, 1),
            new GregorianCalendar(2018, 0, 1),
            new GregorianCalendar(2019, 0, 1),
            new GregorianCalendar(2020, 0, 1),
            new GregorianCalendar(2021, 0, 1),
            new GregorianCalendar(2022, 0, 1),
            new GregorianCalendar(2023, 0, 1)
    }),
    PRESIDENTS_DAY(R.string.holiday__presidents_day, R.drawable.holiday__presidents_day, new GregorianCalendar[] {
            new GregorianCalendar(2014, 1, 17),
            new GregorianCalendar(2015, 1, 16),
            new GregorianCalendar(2016, 1, 15),
            new GregorianCalendar(2017, 1, 20),
            new GregorianCalendar(2018, 1, 19),
            new GregorianCalendar(2019, 1, 18),
            new GregorianCalendar(2020, 1, 17),
            new GregorianCalendar(2021, 1, 15),
            new GregorianCalendar(2022, 1, 21),
            new GregorianCalendar(2023, 1, 20)
    }),
    MEMORIAL_DAY(R.string.holiday__memorial_day, R.drawable.holiday__memorial_day, new GregorianCalendar[] {
            new GregorianCalendar(2014, 4, 26),
            new GregorianCalendar(2015, 4, 25),
            new GregorianCalendar(2016, 4, 30),
            new GregorianCalendar(2017, 4, 29),
            new GregorianCalendar(2018, 4, 28),
            new GregorianCalendar(2019, 4, 27),
            new GregorianCalendar(2020, 4, 25),
            new GregorianCalendar(2021, 4, 31),
            new GregorianCalendar(2022, 4, 30),
            new GregorianCalendar(2023, 4, 29)
    }),
    INDEPENDENCE_DAY(R.string.holiday__independence_day,R.drawable.holiday__independence_day, new GregorianCalendar[] {
            new GregorianCalendar(2014, 6, 4),
            new GregorianCalendar(2015, 6, 4),
            new GregorianCalendar(2016, 6, 4),
            new GregorianCalendar(2017, 6, 4),
            new GregorianCalendar(2018, 6, 4),
            new GregorianCalendar(2019, 6, 4),
            new GregorianCalendar(2020, 6, 4),
            new GregorianCalendar(2021, 6, 4),
            new GregorianCalendar(2022, 6, 4),
            new GregorianCalendar(2023, 6, 4)
    }),
    LABOR_DAY(R.string.holiday__labor_day,R.drawable.holiday__labor_day, new GregorianCalendar[] {
            new GregorianCalendar(2014, 8, 1),
            new GregorianCalendar(2015, 8, 7),
            new GregorianCalendar(2016, 8, 5),
            new GregorianCalendar(2017, 8, 4),
            new GregorianCalendar(2018, 8, 3),
            new GregorianCalendar(2019, 8, 2),
            new GregorianCalendar(2020, 8, 7),
            new GregorianCalendar(2021, 8, 6),
            new GregorianCalendar(2022, 8, 5),
            new GregorianCalendar(2023, 8, 4)
    }),
    THANKSGIVING(R.string.holiday__thanksgiving,R.drawable.holiday__thanksgiving, new GregorianCalendar[] {
            new GregorianCalendar(2014, 10, 27),
            new GregorianCalendar(2015, 10, 26),
            new GregorianCalendar(2016, 10, 24),
            new GregorianCalendar(2017, 10, 23),
            new GregorianCalendar(2018, 10, 22),
            new GregorianCalendar(2019, 10, 28),
            new GregorianCalendar(2020, 10, 26),
            new GregorianCalendar(2021, 10, 25),
            new GregorianCalendar(2022, 10, 24),
            new GregorianCalendar(2023, 10, 23)
    }),
    CHRISTMAS(R.string.holiday__christmas,R.drawable.holiday__christmas, new GregorianCalendar[] {
            new GregorianCalendar(2014, 11, 25),
            new GregorianCalendar(2015, 11, 25),
            new GregorianCalendar(2016, 11, 25),
            new GregorianCalendar(2017, 11, 25),
            new GregorianCalendar(2018, 11, 25),
            new GregorianCalendar(2019, 11, 25),
            new GregorianCalendar(2020, 11, 25),
            new GregorianCalendar(2021, 11, 25),
            new GregorianCalendar(2022, 11, 25),
            new GregorianCalendar(2023, 11, 25)
    });

    public static FmmHoliday getObjectForName(String aName) {
        for(FmmHoliday aHoliday : values()) {
            if(aHoliday.getName().equals(aName)) {
                return aHoliday;
            }
        }
        return null;
    }

    private int nameResourceId;
    private String name;
    private int drawableResourceId;
    private Date firstDayOfHolidayBreak;
    private Date lastDayOfHolidayBreak;
    private HashMap<Integer, GregorianCalendar> dateMap = new HashMap<Integer, GregorianCalendar>();

    private FmmHoliday(int aNameResourceId, int aDrawableResourceId, GregorianCalendar[] aDateArray) {
        this.nameResourceId = aNameResourceId;
        this.name = GcgApplication.getAppResources().getString(this.nameResourceId);
        this.drawableResourceId = aDrawableResourceId;
        for(GregorianCalendar aDate : aDateArray) {
            this.dateMap.put(aDate.get(GregorianCalendar.YEAR), aDate);
        }
    }

    @Override
    public String getLabelText() {
        return "Holiday";
    }

    @Override
    public Drawable getLabelDrawable() {
        return null;
    }

    @Override
    public int getLabelDrawableResourceId() {
        return 0;
    }

    @Override
    public String getDataText() {
        return this.name;
    }

    @Override
    public Drawable getDataDrawable() {
        return null;
    }

    @Override
    public int getDataDrawableResourceId() {
        return this.drawableResourceId;
    }

    public String getName() {
        return this.name;
    }

    public Date getLastDayOfHolidayBreak() {
        return this.lastDayOfHolidayBreak;
    }

    public void setLastDayOfHolidayBreak(Date lastDayOfHolidayBreak) {
        this.lastDayOfHolidayBreak = lastDayOfHolidayBreak;
    }

    public GregorianCalendar getRecommendedLastDayOfHolidayBreak(Integer aYear) {
        int theAdjustment = 0;
        switch (getHolidayDate(aYear).get(GregorianCalendar.DAY_OF_WEEK)) {
            case GregorianCalendar.SUNDAY:
                theAdjustment = 1;
                break;
            case GregorianCalendar.MONDAY:
                theAdjustment = 0;
                break;
            case GregorianCalendar.TUESDAY:
                theAdjustment = 0;
                break;
            case GregorianCalendar.WEDNESDAY:
                theAdjustment = 4;
                break;
            case GregorianCalendar.THURSDAY:
                theAdjustment = 3;
                break;
            case GregorianCalendar.FRIDAY:
                theAdjustment = 2;
                break;
            case GregorianCalendar.SATURDAY:
                theAdjustment = 1;
                break;
        }
        GregorianCalendar theAdjustedDate = getHolidayDate(aYear);
        theAdjustedDate.add(Calendar.DATE, theAdjustment);
        return theAdjustedDate;
    }

    public Date getFirstDayOfHolidayBreak() {
        return this.firstDayOfHolidayBreak;
    }

    public GregorianCalendar getRecommendedFirstDayOfHolidayBreak(Integer aYear) {
        int theAdjustment = 0;
        switch (getHolidayDate(aYear).get(GregorianCalendar.DAY_OF_WEEK)) {
            case GregorianCalendar.SUNDAY:
                theAdjustment = -1;
                break;
            case GregorianCalendar.MONDAY:
                theAdjustment = -2;
                break;
            case GregorianCalendar.TUESDAY:
                theAdjustment = -3;
                break;
            case GregorianCalendar.WEDNESDAY:
                theAdjustment = -4;
                break;
            case GregorianCalendar.THURSDAY:
                theAdjustment = 0;
                break;
            case GregorianCalendar.FRIDAY:
                theAdjustment = 0;
                break;
            case GregorianCalendar.SATURDAY:
                theAdjustment = 0;
                break;
        }
        GregorianCalendar theAdjustedDate = getHolidayDate(aYear);
        theAdjustedDate.add(Calendar.DATE, theAdjustment);
        return theAdjustedDate;
    }

    public void setFirstDayOfHolidayBreak(Date firstDayOfHolidayBreak) {
        this.firstDayOfHolidayBreak = firstDayOfHolidayBreak;
    }

    public GregorianCalendar getHolidayDate(Integer aYear) {
        return this.dateMap.get(aYear);
    }
}
