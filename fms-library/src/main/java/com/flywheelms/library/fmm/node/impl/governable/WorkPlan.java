/* @(#)WorkPlan.java
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
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.enumerator.FmmHoliday;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.completable.FmmCompletableNodeImpl;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;

import java.util.Date;

public class WorkPlan extends FmmCompletableNodeImpl {

	private static final long serialVersionUID = -5268115673220940748L;
    private String flywheelCadenceId;
    private FlywheelCadence flywheelCadence;
    private Date scheduledStartDate;
    private Date scheduledEndDate;
    private FmmHoliday fmmHoliday;

    public WorkPlan(NodeId aNodeId) {
		super(aNodeId);
		// TODO Auto-generated constructor stub
	}

    public WorkPlan(FlywheelCadence aFlywheelCadence, Date aStartDate, Date anEndDate) {
        super(new NodeId(FmmNodeDefinition.WORK_PLAN));
        setFlywheelCadence(aFlywheelCadence);
        setScheduledStartDate(aStartDate);
        setScheduledEndDate(anEndDate);
    }

    public WorkPlan(String anExistingNodeId) {
        super(new NodeId(FmmNodeDefinition.WORK_PLAN, anExistingNodeId, true));
    }

    public String getFlywheelCadenceId() {
        return this.flywheelCadenceId;
    }

    public FlywheelCadence getFlywheelCadence() {
        if(this.flywheelCadence == null && this.flywheelCadenceId != null) {
            this.flywheelCadence =
                    FmmDatabaseMediator.getActiveMediator().retrieveFlywheelCadence(this.flywheelCadenceId);
        }
        return this.flywheelCadence;
    }

    public void setFlywheelCadenceId(String aNodeIdString) {
        this.flywheelCadenceId = aNodeIdString;
        if(this.flywheelCadence != null && !this.flywheelCadence.getNodeIdString().equals(aNodeIdString)) {
            this.flywheelCadence = null;
        }
    }

    public void setFlywheelCadence(FlywheelCadence aFlywheelCadence) {
        this.flywheelCadence = aFlywheelCadence;
        this.flywheelCadenceId = aFlywheelCadence.getNodeId().getNodeIdString();
    }

	public boolean isWorkTaskMoveTarget() {
		return true;
	}

    public Date getScheduledStartDate() {
        return this.scheduledStartDate;
    }

    public Long getScheduledStartDateFormattedUtcLong() {
        return GcgDateHelper.getFormattedUtcLong(this.scheduledStartDate);
    }

    public void setScheduledStartDate(Date aStartDate) {
        this.scheduledStartDate = aStartDate;
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

    public void setScheduledEndDate(Date anEndDate) {
        this.scheduledEndDate = anEndDate;
    }

    public void setScheduledEndDate(Long aLongDate) {
        this.scheduledEndDate = GcgDateHelper.getDateFromFormattedUtcLong(aLongDate);
    }

    @Override
    public String getSecondaryHeadline() {
        return "ending " + GcgDateHelper.getGuiDateString5(getScheduledEndDate());
    }

    @Override
    public String getHeadline() {
      return this.fmmHoliday != null ?
              this.headline + " (includes " + this.fmmHoliday.getName() + " break)" :
              this.headline;
    }

    public FmmHoliday getFmmHoliday() {
        return this.fmmHoliday;
    }

    public void setFmmHoliday(FmmHoliday fmmHoliday) {
        this.fmmHoliday = fmmHoliday;
    }
}
