/* @(#)FlywheelCadence.java
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

package com.flywheelms.library.fmm.node.impl.governable;

import com.flywheelms.gcongui.gcg.widget.date.GcgDateHelper;
import com.flywheelms.library.fmm.enumerator.FmmHoliday;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.completable.FmmCompletableNodeImpl;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;

import java.util.Date;

public class FlywheelCadence extends FmmCompletableNodeImpl {

	private static final long serialVersionUID = -7058798359923528163L;
	private String fiscalYearId;
	private Date scheduledStartDate;
	private Date scheduledEndDate;
    private FmmHoliday fmmHoliday;

	public FlywheelCadence(NodeId aNodeId, String aFiscalYearId, Date aScheduledStartDate, Date aScheduledEndDate) {
		super(aNodeId);
        this.fiscalYearId = aFiscalYearId;
        this.scheduledStartDate = aScheduledStartDate;
        this.scheduledEndDate = aScheduledEndDate;
	}

    public FlywheelCadence(String aFiscalYearId, Date aScheduledStartDate, Date aScheduledEndDate) {
        this(new NodeId(FmmNodeDefinition.FLYWHEEL_CADENCE.getNodeTypeCode()), aFiscalYearId, aScheduledStartDate, aScheduledEndDate);
    }

    public FlywheelCadence(String aNodeIdString, String aFiscalYearId, long aScheduledStartDate, long aScheduledEndDate) {
        super(FlywheelCadence.class, aNodeIdString);
        this.fiscalYearId = aFiscalYearId;
        setScheduledStartDate(aScheduledStartDate);
        setScheduledEndDate(aScheduledEndDate);
    }

	public String getFiscalYearId() {
		return this.fiscalYearId;
	}

	public void setFiscalYearId(String fiscalYearId) {
		this.fiscalYearId = fiscalYearId;
	}

	public Date getScheduledStartDate() {
		return this.scheduledStartDate;
	}

    public Long getScheduledStartDateFormattedUtcLong() {
        return GcgDateHelper.getFormattedUtcLong(this.scheduledStartDate);
    }

	public void setScheduledStartDate(Date scheduledStartDate) {
		this.scheduledStartDate = scheduledStartDate;
	}

    public void setScheduledStartDate(Long aLongDate) {
        this.scheduledStartDate = GcgDateHelper.getDateFromFormattedUtcLong(aLongDate);
    }

	public Date getScheduledEndDate() {
		return this.scheduledEndDate;
	}

    public Long getScheduledEndDateFormattedUtcLong() {
        return GcgDateHelper.getFormattedUtcLong(this.scheduledEndDate);
    }

	public void setScheduledEndDate(Date scheduledEndDate) {
		this.scheduledEndDate = scheduledEndDate;
	}

    public void setScheduledEndDate(Long aLongDate) {
        this.scheduledEndDate = GcgDateHelper.getDateFromFormattedUtcLong(aLongDate);
    }

	public boolean isWorkPackageMoveTarget() {
		return true;
	}

	public boolean isWorkPlanMoveTarget() {
		return true;
	}

    public boolean isHolidaySlack() {
        return this.fmmHoliday != null;
    }

    public FmmHoliday getHolidaySlack() {
        return this.fmmHoliday;
    }

    public void setHolidaySlack(FmmHoliday aHoliday) {
        this.fmmHoliday = aHoliday;
    }
}
