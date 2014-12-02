/* @(#)FmsTaskPointBudgetPerspective.java
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

package com.flywheelms.library.fms.perspective_flipper.perspective;

import android.content.Context;
import android.util.AttributeSet;

import com.flywheelms.gcongui.gcg.interfaces.GcgPerspective;
import com.flywheelms.gcongui.gcg.widget.text_view.GcgWidgetGenericTextView;
import com.flywheelms.library.R;
import com.flywheelms.library.fmm.context.FmmPerspective;
import com.flywheelms.library.fms.helper.FmsHelpIndex;
import com.flywheelms.library.fms.widget.edit_float.AverageHoursPerTaskPointWidgetEditFloat;
import com.flywheelms.library.fms.widget.edit_integer.TaskPointsWidgetEditInteger;
import com.flywheelms.library.fms.widget.spinner.FmmDataQualityWidgetSpinner;
import com.flywheelms.library.fms.widget.text_view.ByCommunityMemberWidgetTextView;
import com.flywheelms.library.fms.widget.text_view.TimestampWidgetTextView;


public class FmsTaskPointBudgetPerspective extends FmsPerspectiveFlipperView {

    private GcgPerspective gcgPerspective = FmmPerspective.TASK_POINT_BUDGET;
    
    private TaskPointsWidgetEditInteger estimateTaskPointsWidget;
    private AverageHoursPerTaskPointWidgetEditFloat estimateAverageHoursPerTaskPointWidget;
    private ByCommunityMemberWidgetTextView estimateByCommunityMemberWidget;
    private TimestampWidgetTextView estimateTimestampWidget;
    private FmmDataQualityWidgetSpinner estimateDataQualityWidget;
    private TaskPointsWidgetEditInteger budgetTaskPointsWidget;
    private AverageHoursPerTaskPointWidgetEditFloat budgetAverageHoursPerTaskPointWidget;
    private ByCommunityMemberWidgetTextView budgetByCommunityMemberWidget;
    private TimestampWidgetTextView budgetTimestampWidget;
    private FmmDataQualityWidgetSpinner budgetDataQualityWidget;
    private GcgWidgetGenericTextView workBreakdownEstimateTaskPoints;
    private GcgWidgetGenericTextView workBreakdownEstimateAverageHoursPerTaskPoint;
    private GcgWidgetGenericTextView workBreakdownBudgetTaskPoints;
    private GcgWidgetGenericTextView workBreakdownBudgetAverageHoursPerTaskPoint;
    private GcgWidgetGenericTextView completionTaskPoints;
    private GcgWidgetGenericTextView completionAverageHoursPerTaskPoint;

    public FmsTaskPointBudgetPerspective(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    private void setup() {
        this.estimateTaskPointsWidget = (TaskPointsWidgetEditInteger) findViewById(R.id.estimate__task_points);
        this.estimateAverageHoursPerTaskPointWidget = (AverageHoursPerTaskPointWidgetEditFloat) findViewById(R.id.estimate__average_hours_per_task_point);
        this.estimateByCommunityMemberWidget = (ByCommunityMemberWidgetTextView) findViewById(R.id.estimate__by_community_member);
        this.estimateTimestampWidget = (TimestampWidgetTextView) findViewById(R.id.estimate__timestamp);
        this.estimateDataQualityWidget= (FmmDataQualityWidgetSpinner) findViewById(R.id.estimate__data_quality);

        this.budgetTaskPointsWidget = (TaskPointsWidgetEditInteger) findViewById(R.id.budget__task_points);
        this.budgetAverageHoursPerTaskPointWidget = (AverageHoursPerTaskPointWidgetEditFloat) findViewById(R.id.budget__average_hours_per_task_point);
        this.budgetByCommunityMemberWidget = (ByCommunityMemberWidgetTextView) findViewById(R.id.budget__by_community_member);
        this.budgetTimestampWidget = (TimestampWidgetTextView) findViewById(R.id.budget__timestamp);
        this.budgetDataQualityWidget= (FmmDataQualityWidgetSpinner) findViewById(R.id.budget__data_quality);
    }

    @Override
	public GcgPerspective getGcgPerspective() {
		return this.gcgPerspective;
	}

	@Override
	protected int getPageTitleResourceId() {
		return this.gcgPerspective.getNameStringResourceId();
	}

	@Override
	protected int getViewLayoutResourceId() {
		return R.layout.fms_view__task_point_budget;
	}

    @Override
    public int getFrameMenuSpacerBackgroundResourceId() {
        return R.color.gcg__perspective__background;
    }

	@Override
	protected String getHelpContextUrlString() {
		return FmsHelpIndex.PERSPECTIVE__WORK_TASK_BUDGET;
	}
    
    

}
