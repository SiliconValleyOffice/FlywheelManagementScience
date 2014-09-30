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

package com.flywheelms.library.fms.wizard.step;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.Button;

import com.flywheelms.gcongui.gcg.activity.GcgActivity;
import com.flywheelms.gcongui.gcg.viewflipper.GcgViewFlipper;
import com.flywheelms.gcongui.gcg.widget.GcgWidgetTextViewSummaryBox;
import com.flywheelms.gcongui.gcg.wizard.step.GcgWizardStepView;
import com.flywheelms.library.R;
import com.flywheelms.library.fmm.node.impl.governable.FiscalYear;
import com.flywheelms.library.fms.helper.FmsHelpIndex;
import com.flywheelms.library.fms.widget.text_view.FiscalYearWidgetTextView;
import com.flywheelms.library.fms.wizard.CreateAllCadenceForYearWizardStepFlipper;

public class CreateAllCadenceDoItNowWizardStepView extends GcgWizardStepView {

    protected FiscalYearWidgetTextView fiscalYearWidgetTextView;
	private GcgWidgetTextViewSummaryBox summaryBoxStep1;
	private GcgWidgetTextViewSummaryBox summaryBoxStep2;
    protected Button previewButton;

	public CreateAllCadenceDoItNowWizardStepView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected int getPageTitleResourceId() {
		return R.string.create__fmm_repository__do_it_now__wizard_step;
	}

	@Override
	protected int getViewLayoutResourceId() {
		return R.layout.create__all_cadence_for_year__do_id_now__wizard_step;
	}

	@Override
	public void initialize(GcgActivity anGcgActivity, GcgViewFlipper aViewFlipper, int aPageNumber) {
		super.initialize(anGcgActivity, aViewFlipper, aPageNumber);
        this.fiscalYearWidgetTextView = (FiscalYearWidgetTextView) findViewById(R.id.fiscal_year__text_view);
        this.fiscalYearWidgetTextView.setFiscalYear(getFiscalYear());
		this.summaryBoxStep1 = (GcgWidgetTextViewSummaryBox) findViewById(R.id.summary__step_1);
		this.summaryBoxStep2 = (GcgWidgetTextViewSummaryBox) findViewById(R.id.summary__step_2);
	}
	
	@Override
	protected void activateView() {
		super.activateView();
        CreateAllCadenceForYearWizardStepFlipper theWizardStepFlipper = (CreateAllCadenceForYearWizardStepFlipper) getViewFlipper();
		this.summaryBoxStep1.setText(Html.fromHtml(theWizardStepFlipper.getWizardStepView1().getSummaryText()));
		this.summaryBoxStep2.setText(Html.fromHtml(theWizardStepFlipper.getWizardStepView2().getSummaryText()));
	}

    private FiscalYear getFiscalYear() {
        return ((CreateAllCadenceForYearWizardStepFlipper) getViewFlipper()).getFiscalYear();
    }

	@Override
	public String getSummaryText() { return null; }

	@Override
	public boolean validWizardStepData() { return true; }

	@Override
	protected String getHelpContextUrlString() {
		return FmsHelpIndex.CREATE__ALL_CADENCE_FOR_YEAR__DO_IT_NOW__WIZARD_STEP;
	}

}
