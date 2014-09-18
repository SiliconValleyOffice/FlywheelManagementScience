/* @(#)CreateFmmDoItNowWizardStepView.java
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

package com.flywheelms.library.fms.wizard.step;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;

import com.flywheelms.library.R;
import com.flywheelms.library.fms.helper.FmsHelpIndex;
import com.flywheelms.library.fms.wizard.CreateFmmWizardStepFlipper;
import com.flywheelms.library.gcg.activity.GcgActivity;
import com.flywheelms.library.gcg.viewflipper.GcgViewFlipper;
import com.flywheelms.library.gcg.widget.GcgWidgetTextViewSummaryBox;
import com.flywheelms.library.gcg.wizard.step.GcgWizardStepView;

public class CreateFmmDoItNowWizardStepView extends GcgWizardStepView {
	
	private GcgWidgetTextViewSummaryBox summaryBoxStep1;
	private GcgWidgetTextViewSummaryBox summaryBoxStep2;
	private GcgWidgetTextViewSummaryBox summaryBoxStep3;
	private GcgWidgetTextViewSummaryBox summaryBoxStep4;

	public CreateFmmDoItNowWizardStepView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected int getPageTitleResourceId() {
		return R.string.create__fmm_repository__do_it_now__wizard_step;
	}

	@Override
	protected int getViewLayoutResourceId() {
		return R.layout.create__fmm_repository__do_id_now__wizard_step;
	}

	@Override
	public void initialize(GcgActivity anGcgActivity, GcgViewFlipper aViewFlipper, int aPageNumber) {
		super.initialize(anGcgActivity, aViewFlipper, aPageNumber);
		this.summaryBoxStep1 = (GcgWidgetTextViewSummaryBox) findViewById(R.id.summary__step_1);
		this.summaryBoxStep2 = (GcgWidgetTextViewSummaryBox) findViewById(R.id.summary__step_2);
		this.summaryBoxStep3 = (GcgWidgetTextViewSummaryBox) findViewById(R.id.summary__step_3);
		this.summaryBoxStep4 = (GcgWidgetTextViewSummaryBox) findViewById(R.id.summary__step_4);
	}
	
	@Override
	protected void activateView() {
		super.activateView();
		CreateFmmWizardStepFlipper theWizardStepFlipper = (CreateFmmWizardStepFlipper) getViewFlipper();
		this.summaryBoxStep1.setText(Html.fromHtml(theWizardStepFlipper.getWizardStepView1().getSummaryText()));
		this.summaryBoxStep2.setText(Html.fromHtml(theWizardStepFlipper.getWizardStepView2().getSummaryText()));
		this.summaryBoxStep3.setText(Html.fromHtml(theWizardStepFlipper.getWizardStepView3().getSummaryText()));
		this.summaryBoxStep4.setText(Html.fromHtml(theWizardStepFlipper.getWizardStepView4().getSummaryText()));
	}

	@Override
	public String getSummaryText() { return null; }

	@Override
	public boolean validWizardStepData() { return true; }

	@Override
	protected String getHelpContextUrlString() {
		return FmsHelpIndex.CREATE_FMM__DO_IT_NOW;
	}

}
