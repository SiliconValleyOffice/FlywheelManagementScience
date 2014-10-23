/* @(#)GcgMonth.java
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

package com.flywheelms.gcongui.gcg.widget.date;

import android.graphics.drawable.Drawable;

import com.flywheelms.gcongui.gcg.interfaces.GcgGuiable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

public class GcgDayOfWeek implements GcgGuiable {

	public static GcgDayOfWeek SUNDAY = new GcgDayOfWeek(GregorianCalendar.SUNDAY, "Sun", "Sunday");
	public static GcgDayOfWeek MONDAY = new GcgDayOfWeek(GregorianCalendar.MONDAY, "Mon", "Monday");
	public static GcgDayOfWeek TUESDAY = new GcgDayOfWeek(GregorianCalendar.TUESDAY, "Tue", "Tuesday");
	public static GcgDayOfWeek WEDNESDAY = new GcgDayOfWeek(GregorianCalendar.WEDNESDAY, "Wed", "Wednesday");
	public static GcgDayOfWeek THURSDAY = new GcgDayOfWeek(GregorianCalendar.THURSDAY, "Thu", "Thursday");
	public static GcgDayOfWeek FRIDAY = new GcgDayOfWeek(GregorianCalendar.FRIDAY, "Fri", "Friday");
	public static GcgDayOfWeek SATURDAY = new GcgDayOfWeek(GregorianCalendar.SATURDAY, "Sat", "Saturday");

	protected static GcgDayOfWeek staticInstance = SUNDAY;

	public static GcgDayOfWeek getStaticInstance() {
		return GcgDayOfWeek.staticInstance;
	}

	protected static ArrayList<GcgDayOfWeek> valuesList =
			new ArrayList<GcgDayOfWeek>();
	static {
		GcgDayOfWeek.valuesList.add(SUNDAY);
		GcgDayOfWeek.valuesList.add(MONDAY);
		GcgDayOfWeek.valuesList.add(TUESDAY);
		GcgDayOfWeek.valuesList.add(WEDNESDAY);
		GcgDayOfWeek.valuesList.add(THURSDAY);
		GcgDayOfWeek.valuesList.add(FRIDAY);
		GcgDayOfWeek.valuesList.add(SATURDAY);
	}

	public static List<GcgDayOfWeek> values() {
		return GcgDayOfWeek.valuesList;
	}

	public static GcgDayOfWeek getDayOfWeekForNumber(int aDayNumber) {
		return GcgDayOfWeek.valuesList.get(aDayNumber - 1);
	}

	protected static HashMap<String, GcgDayOfWeek> nameMap = new HashMap<String, GcgDayOfWeek>();
	static {
		for(GcgDayOfWeek theGcgMonth : GcgDayOfWeek.valuesList) {
			GcgDayOfWeek.nameMap.put(theGcgMonth.getDayOfWeekName(), theGcgMonth);
		}
	}

	public static GcgDayOfWeek getObjectForName(String aName) {
		return GcgDayOfWeek.nameMap.get(aName);
	}

    static {
        SUNDAY.setPreviousDayOfWeek(SATURDAY);
        SUNDAY.setNextDayOfWeek(MONDAY);
        MONDAY.setPreviousDayOfWeek(SUNDAY);
        MONDAY.setNextDayOfWeek(TUESDAY);
        TUESDAY.setPreviousDayOfWeek(MONDAY);
        TUESDAY.setNextDayOfWeek(WEDNESDAY);
        WEDNESDAY.setPreviousDayOfWeek(TUESDAY);
        WEDNESDAY.setNextDayOfWeek(THURSDAY);
        THURSDAY.setPreviousDayOfWeek(WEDNESDAY);
        THURSDAY.setNextDayOfWeek(FRIDAY);
        FRIDAY.setPreviousDayOfWeek(THURSDAY);
        FRIDAY.setNextDayOfWeek(SATURDAY);
        SATURDAY.setPreviousDayOfWeek(FRIDAY);
        SATURDAY.setNextDayOfWeek(SUNDAY);
    }
    
    public static Date getDateForNextDayOfWeek(Date aDate, GcgDayOfWeek aDayOfWeek) {
        GregorianCalendar theGregorianCalendar = new GregorianCalendar();
        theGregorianCalendar.setTime(aDate);
        return getDateForNextDayOfWeek(theGregorianCalendar, aDayOfWeek).getTime();
    }

    public static GregorianCalendar getDateForNextDayOfWeek(GregorianCalendar aGregorianCalendar, GcgDayOfWeek aDayOfWeek) {
        GregorianCalendar theNextGregorianCalendar = new GregorianCalendar();
        theNextGregorianCalendar.setTime(aGregorianCalendar.getTime());
        theNextGregorianCalendar.add(Calendar.DATE, 1);
        while (theNextGregorianCalendar.get(Calendar.DAY_OF_WEEK) != aDayOfWeek.getCalendarConstant()) {
            theNextGregorianCalendar.add(Calendar.DATE, 1);
        }
        return theNextGregorianCalendar;
    }

	private final int dayOfWeekNumber;
	private final String dayOfWeekNameShort;
	private final String dayOfWeekName;
    private GcgDayOfWeek previousDayOfWeek;
    private GcgDayOfWeek nextDayOfWeek;

	private GcgDayOfWeek(int aDayOfWeekNumber, String aDayOfWeekNameShort, String aDayOfWeekName) {
		this.dayOfWeekNumber = aDayOfWeekNumber;
		this.dayOfWeekNameShort = aDayOfWeekNameShort;
		this.dayOfWeekName = aDayOfWeekName;
	}

	public int getDayOfWeekNumber() {
		return this.dayOfWeekNumber;
	}

	public String getDayOfWeekNameShort() {
		return this.dayOfWeekNameShort;
	}

	public String getDayOfWeekName() {
		return this.dayOfWeekName;
	}

	@Override
	public String getLabelText() {
		return "Month";
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
		return this.dayOfWeekName;
	}

	@Override
	public Drawable getDataDrawable() {
		return null;
	}

	@Override
	public int getDataDrawableResourceId() {
		return 0;
	}

    public GcgDayOfWeek getPreviousDayOfWeek() {
        return this.previousDayOfWeek;
    }

    public void setPreviousDayOfWeek(GcgDayOfWeek previousDayOfWeek) {
        this.previousDayOfWeek = previousDayOfWeek;
    }

    public GcgDayOfWeek getNextDayOfWeek() {
        return nextDayOfWeek;
    }

    public void setNextDayOfWeek(GcgDayOfWeek nextDayOfWeek) {
        this.nextDayOfWeek = nextDayOfWeek;
    }

    public int getCalendarConstant() {
        return getDayOfWeekNumber();
    }
}