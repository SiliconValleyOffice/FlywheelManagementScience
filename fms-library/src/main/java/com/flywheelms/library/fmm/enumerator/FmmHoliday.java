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

import java.util.Date;

public enum FmmHoliday implements GcgGuiable {

    NEW_YEARS_DAY(R.string.holiday__new_years_day,R.drawable.holiday__new_years_day),
    PRESIDENTS_DAY(R.string.holiday__presidents_day, R.drawable.holiday__presidents_day),
    MEMORIAL_DAY(R.string.holiday__memorial_day, R.drawable.holiday__memorial_day),
    INDEPENDENCE_DAY(R.string.holiday__independence_day,R.drawable.holiday__independence_day),
    LABOR_DAY(R.string.holiday__labor_day,R.drawable.holiday__labor_day),
    THANKSGIVING(R.string.holiday__thanksgiving,R.drawable.holiday__thanksgiving),
    CHRISTMAS(R.string.holiday__christmas,R.drawable.holiday__christmas);

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
    private int year;
    private Date date;
    private Date firstDayOfHolidayBreak;
    private Date lastDayOfHolidayBreak;

    private FmmHoliday(int aNameResourceId, int aDrawableResourceId) {
        this.nameResourceId = aNameResourceId;
        this.name = GcgApplication.getAppResources().getString(this.nameResourceId);
        this.drawableResourceId = aDrawableResourceId;
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
        return 0;
    }

    public String getName() {
        return this.name;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Date getLastDayOfHolidayBreak() {
        return this.lastDayOfHolidayBreak;
    }

    public void setLastDayOfHolidayBreak(Date lastDayOfHolidayBreak) {
        this.lastDayOfHolidayBreak = lastDayOfHolidayBreak;
    }

    public Date getFirstDayOfHolidayBreak() {

        return this.firstDayOfHolidayBreak;
    }

    public void setFirstDayOfHolidayBreak(Date firstDayOfHolidayBreak) {
        this.firstDayOfHolidayBreak = firstDayOfHolidayBreak;
    }

    public Date getDate() {

        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
