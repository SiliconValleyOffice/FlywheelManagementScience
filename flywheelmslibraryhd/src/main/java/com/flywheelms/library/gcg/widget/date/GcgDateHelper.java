/* @(#)GcgDateHelper.java
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

import android.annotation.SuppressLint;
import android.content.ContentValues;

import com.flywheelms.library.gcg.interfaces.GcgGuiable;
import com.flywheelms.library.gcg.interfaces.GcgGuiableImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

@SuppressLint("SimpleDateFormat")
public class GcgDateHelper {

	public static final Date NULL_DATE = new Date(0);
	private static SimpleDateFormat simpleDateFormatGui_0 = new SimpleDateFormat("yyyy-LL-dd  HH:mm  z", Locale.US);
	private static SimpleDateFormat simpleDateFormatGui_1 = new SimpleDateFormat("yyyy-LLL-dd  HH:mm  z", Locale.US);
	private static SimpleDateFormat simpleDateFormatGui_2 = new SimpleDateFormat("EEE  LLL dd  yyyy  HH:mm  z", Locale.US);
	private static SimpleDateFormat simpleDateFormatGui_3 = new SimpleDateFormat("EEEE, d LLL yyyy", Locale.US);
	private static SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.US);
	private static SimpleDateFormat monthFormat = new SimpleDateFormat("M", Locale.US);
	private static SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.US);

	private static final SimpleDateFormat iso8601DateFormat =
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat longIntegerUtcDateFormat =
			new SimpleDateFormat("yyyyMMddHHmmssSSS");
	static {
		// since the long is for persistence, it should be in gmt
		GcgDateHelper.longIntegerUtcDateFormat.setTimeZone(TimeZone.getTimeZone("gmt"));
	}

	public static long getFormattedUtcLong(Calendar aCalendar){
		if(aCalendar == null) {
			return 0;
		}
		return Long.parseLong(longIntegerUtcDateFormat.format(aCalendar.getTime()));
	}

	public static long getFormattedUtcLong(Date aDate){
		if(aDate == null) {
			return 0;
		}
		return Long.parseLong(longIntegerUtcDateFormat.format(aDate.getTime()));
	}

	public static Calendar getCalendarFromFormattedUtcLong(long aLongDate){
		try {
			Calendar theCalendar = Calendar.getInstance();
			theCalendar.setTime(longIntegerUtcDateFormat.parse(String.valueOf(aLongDate)));
			return theCalendar;

		} catch (ParseException e) {
			return null;
		}
	}

	public static Date getDateFromFormattedUtcLong(long aLongDate){
		try {
			Date theDate = longIntegerUtcDateFormat.parse(String.valueOf(aLongDate));
			return theDate;

		} catch (ParseException e) {
			return GcgDateHelper.NULL_DATE;
		}
	}

	public static Date getDateFromIso8601DateString(String aDateString) {
		if(aDateString == null || aDateString.equals("null")) {
			return null;
		}
		try {
			return GcgDateHelper.iso8601DateFormat.parse(aDateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String getIso8601DateString(Date aDate) {
		if(aDate == null) {
			return iso8601DateFormat.format(NULL_DATE);
		}
		return iso8601DateFormat.format(aDate);
	}

	public static void setIso8601DateContentValue(ContentValues aContentValues, String aColumnName, Date aDate) {
		if(aDate == null) {
			return;
		}
		aContentValues.put(aColumnName, GcgDateHelper.getIso8601DateString(aDate));
	}

	public static String getGuiDateString0(Date aDate) {
		if(aDate == null || aDate.equals(NULL_DATE)) {
			return "";
		}
		return simpleDateFormatGui_0.format(aDate);
	}

	public static String getGuiDateString1(Date aDate) {
		if(aDate == null || aDate.equals(NULL_DATE)) {
			return "";
		}
		return simpleDateFormatGui_1.format(aDate);
	}

	public static String getGuiDateString2(Date aDate) {
		if(aDate == null || aDate.equals(NULL_DATE)) {
			return "";
		}
		return simpleDateFormatGui_2.format(aDate);
	}

	public static String getGuiDateString3(Date aDate) {
		if(aDate == null || aDate.equals(NULL_DATE)) {
			return "";
		}
		return simpleDateFormatGui_3.format(aDate);
	}

	public static int getYear(Date aDate) {
		if(aDate == null) {
			return 0;
		}
		return Integer.parseInt(yearFormat.format(aDate));
	}

	public static int getMonth(Date aDate) {
		if(aDate == null) {
			return 0;
		}
		return Integer.parseInt(monthFormat.format(aDate));
	}

	public static int getDay(Date aDate) {
		if(aDate == null) {
			return 0;
		}
		return Integer.parseInt(dayFormat.format(aDate));
	}

	public static Date getNullDate() {
		return new Date(0);
	}

	public static boolean isNullDate(Date aDate) {
		return aDate.equals(getNullDate());
	}

	public static boolean isNotNullDate(Date aDate) {
		return ! isNullDate(aDate);
	}
	
	public static Date getCurrentDateTime() {
		return new Date(System.currentTimeMillis());
	}

	public static ArrayList<GcgGuiable> getMonthGuiableList() {
		ArrayList<GcgGuiable> theGuiableList = new ArrayList<GcgGuiable>();
		for(GcgMonth theGcgMonth : GcgMonth.values()) {
			theGuiableList.add(new GcgGuiableImpl(
				"Month",
				null,
				0,
				theGcgMonth.getMonthName(),
				null,
				0 ));
		}
		return theGuiableList;
	}

	public static GcgMonth getGcgMonthForName(String dataText) {
		GcgMonth theGcgMonthForName = null;
		for(GcgMonth theGcgMonth : GcgMonth.values()) {
			if(theGcgMonth.getMonthName().equals(dataText)) {
				theGcgMonthForName = theGcgMonth;
				break;
			}
		}
		return theGcgMonthForName;
	}

	public static boolean sameDate(GregorianCalendar aGregorianCalendar, Date aDate) {
		return sameDate(aGregorianCalendar.getTime(), aDate);
	}

	public static boolean sameDate(Date aDate1, Date aDate2) {
		return getGuiDateString3(aDate1).equals(getGuiDateString3(aDate2));
	}

	public static int getCurrentYear() {
		return getYear(getCurrentDateTime());
	}

	public static int getCurrentMonth() {
		return getMonth(getCurrentDateTime());
	}

}
