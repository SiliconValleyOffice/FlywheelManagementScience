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

package com.flywheelms.library.gcg.widget.date;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.graphics.drawable.Drawable;

import com.flywheelms.library.gcg.interfaces.GcgGuiable;

public class GcgMonth implements GcgGuiable {
	
	public static GcgMonth JAN = new GcgMonth(1, "Jan", "January");
	public static GcgMonth FEB = new GcgMonth(2, "Feb", "February");
	public static GcgMonth MAR = new GcgMonth(3, "Mar", "March");
	public static GcgMonth APR = new GcgMonth(4, "Apr", "April");
	public static GcgMonth MAY = new GcgMonth(5, "May", "May");
	public static GcgMonth JUN = new GcgMonth(6, "Jun", "June");
	public static GcgMonth JUL = new GcgMonth(7, "Jul", "July");
	public static GcgMonth AUG = new GcgMonth(8, "Aug", "August");
	public static GcgMonth SEP = new GcgMonth(9, "Sep", "September");
	public static GcgMonth OCT = new GcgMonth(10, "Oct", "October");
	public static GcgMonth NOV = new GcgMonth(11, "Nov", "November");
	public static GcgMonth DEC = new GcgMonth(12, "Dec", "December");
	
	protected static GcgMonth staticInstance = JAN;
	
	public static GcgMonth getStaticInstance() {
		return GcgMonth.staticInstance;
	}
	
	protected static ArrayList<GcgMonth> valuesList =
			new ArrayList<GcgMonth>();
	static {
		GcgMonth.valuesList.add(JAN);
		GcgMonth.valuesList.add(FEB);
		GcgMonth.valuesList.add(MAR);
		GcgMonth.valuesList.add(APR);
		GcgMonth.valuesList.add(MAY);
		GcgMonth.valuesList.add(JUN);
		GcgMonth.valuesList.add(JUL);
		GcgMonth.valuesList.add(AUG);
		GcgMonth.valuesList.add(SEP);
		GcgMonth.valuesList.add(OCT);
		GcgMonth.valuesList.add(NOV);
		GcgMonth.valuesList.add(DEC);
	}
	
	public static List<GcgMonth> values() {
		return GcgMonth.valuesList;
	}

	public static GcgMonth getMonthForNumber(int aMonthNumber) {
		return GcgMonth.valuesList.get(aMonthNumber - 1);
	}
	
	protected static HashMap<String, GcgMonth> nameMap = new HashMap<String, GcgMonth>();
	static {
		for(GcgMonth theGcgMonth : GcgMonth.valuesList) {
			GcgMonth.nameMap.put(theGcgMonth.getMonthName(), theGcgMonth);
		}
	}
	
	public static GcgMonth getObjectForName(String aName) {
		return GcgMonth.nameMap.get(aName);
	}
	
	private final int monthNumber;
	private final String monthNameShort;
	private final String monthName;
	
	private GcgMonth(int aMonthNumber, String aMonthNameShort, String aMonthName) {
		this.monthNumber = aMonthNumber;
		this.monthNameShort = aMonthNameShort;
		this.monthName = aMonthName;
	}

	public int getMonthNumber() {
		return this.monthNumber;
	}

	public String getMonthNameShort() {
		return this.monthNameShort;
	}

	public String getMonthName() {
		return this.monthName;
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
		return this.monthName;
	}

	@Override
	public Drawable getDataDrawable() {
		return null;
	}

	@Override
	public int getDataDrawableResourceId() {
		return 0;
	}

}