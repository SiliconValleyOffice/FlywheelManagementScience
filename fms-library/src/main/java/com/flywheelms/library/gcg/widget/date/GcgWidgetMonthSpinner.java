/* @(#)GcgWidgetMonthSpinner.java
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

import android.content.Context;
import android.util.AttributeSet;

import com.flywheelms.library.gcg.interfaces.GcgGuiable;
import com.flywheelms.library.gcg.widget.GcgWidgetSpinner;

import java.util.ArrayList;

// com.flywheelms.library.gcg.widget.date.GcgWidgetMonthSpinner
public class GcgWidgetMonthSpinner extends GcgWidgetSpinner {
	
	private GcgMonth originalMonth = null;

	public GcgWidgetMonthSpinner(Context aContext) {
		super(aContext);
	}

	public GcgWidgetMonthSpinner(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
	}

	@Override
	protected String getLabelText() {
		return "Month";
	}

	@Override
	public void setInitialValue() {
		setSelection(0);
	}

	@Override
	protected ArrayList<GcgGuiable> updateGuiableList() {
		return GcgDateHelper.getMonthGuiableList();
	}
	
	public GcgMonth getSelectedGcgMonth() {
		return GcgMonth.getObjectForName(super.getSelectedItem().getDataText());
	}

    public int getSelectedMonthNumber() {
        return GcgMonth.getObjectForName(super.getSelectedItem().getDataText()).getMonthNumber();
    }

	public void setMonthRangeAll() {
		updateSpinnerData();
	}
	
	public void setOriginalMonth(GcgMonth aMonth) {
		this.originalMonth = aMonth;
		setSelectedMonth(aMonth);
	}

	private void setSelectedMonth(GcgMonth aMonth) {
		for(GcgGuiable theGuiable : this.gcgGuiableList) {
			if(theGuiable.getDataText().equals(aMonth.getMonthName())) {
				this.spinner.setSelection(this.gcgGuiableList.indexOf(theGuiable));
				break;
			}
		}
	}

    private void setSelectedMonth(int aMonthNumber) {
        for(GcgGuiable theGuiable : this.gcgGuiableList) {
            if(GcgMonth.getObjectForName(theGuiable.getDataText()).getMonthNumber() == aMonthNumber) {
                this.spinner.setSelection(this.gcgGuiableList.indexOf(theGuiable));
                break;
            }
        }
    }

	public void setMonthRangeYearRemaining(int aYear) {
		ArrayList<GcgGuiable> theGuiableList = new ArrayList<GcgGuiable>();
		if(aYear == GcgDateHelper.getCurrentYear()) {
			theGuiableList.addAll(GcgDateHelper.getMonthGuiableList().subList(
					GcgDateHelper.getCurrentMonth() - 1, 12));
		} else if(aYear > GcgDateHelper.getCurrentYear()) {
			theGuiableList = updateGuiableList();
		}
		updateSpinnerData(theGuiableList);
	}

    public void setClosestMonthSelection(int aMonthNumber) {
        GcgMonth theGcgMonth = GcgMonth.getObjectForName(this.gcgGuiableList.get(0).getDataText());
        int theFirstMonthInList = theGcgMonth.getMonthNumber();
        theGcgMonth = GcgMonth.getObjectForName(this.gcgGuiableList.get(this.gcgGuiableList.size() - 1).getDataText());
        int theLastMonthInList = theGcgMonth.getMonthNumber();
        if(aMonthNumber < theFirstMonthInList) {
            setSelectedMonth(theFirstMonthInList);
        } else if(aMonthNumber > theLastMonthInList) {
            setSelectedMonth(theLastMonthInList);
        } else {
            setSelectedMonth(aMonthNumber);
        }
    }
}
