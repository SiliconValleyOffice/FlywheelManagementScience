/* @(#)FmsNodePublishingDoItNowStepView.java
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
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.flywheelms.gcongui.gcg.activity.GcgActivity;
import com.flywheelms.gcongui.gcg.helper.GcgHelper;
import com.flywheelms.gcongui.gcg.viewflipper.GcgViewFlipper;
import com.flywheelms.gcongui.gcg.wizard.step.GcgWizardStepView;
import com.flywheelms.library.R;
import com.flywheelms.library.fms.helper.FmsHelpIndex;
import com.flywheelms.library.fms.wizard.FmsNodePublishingWizardStepFlipper;

public class FmsNodePublishingDoItNowStepView extends GcgWizardStepView {
	
	private TextView contentSummary;
	private TextView destinationSummary_1;
	private TextView destinationSummary_2;

	public FmsNodePublishingDoItNowStepView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected int getPageTitleResourceId() {
		return R.string.publish_pdf__do_it_now__wizard_step;
	}

	@Override
	protected int getViewLayoutResourceId() {
		return R.layout.fmm_node__publication__wizard_step__do_it_now;
	}

	@Override
	public void initialize(GcgActivity anGcgActivity, GcgViewFlipper aViewFlipper, int aPageNumber) {
		super.initialize(anGcgActivity, aViewFlipper, aPageNumber);
        ((TextView) findViewById(R.id.label__content_summary)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View aView) {
                FmsNodePublishingDoItNowStepView.this.getGcgViewFlipper().flipToIndex(0);
            }
        });
		this.contentSummary = (TextView) findViewById(R.id.summary__content);
		GcgHelper.initializeViewFlingListener(this.context, getGcgViewFlipper(), this.contentSummary);
        ((TextView) findViewById(R.id.label__destination_summary)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View aView) {
                FmsNodePublishingDoItNowStepView.this.getGcgViewFlipper().flipToIndex(2);
            }
        });
		this.destinationSummary_1 = (TextView) findViewById(R.id.summary__destination_1);
		GcgHelper.initializeViewFlingListener(this.context, getGcgViewFlipper(), this.destinationSummary_1);
		this.destinationSummary_2 = (TextView) findViewById(R.id.summary__destination_2);
		GcgHelper.initializeViewFlingListener(this.context, getGcgViewFlipper(), this.destinationSummary_2);
	}
	
	@Override
	protected void activateView() {
		super.activateView();
		FmsNodePublishingWizardStepFlipper theWizardStepFlipper = (FmsNodePublishingWizardStepFlipper) getGcgViewFlipper();
		this.contentSummary.setText(theWizardStepFlipper.getWizardStepView1().getSummaryText());
		this.destinationSummary_1.setText(theWizardStepFlipper.getWizardStepView2().getSummaryText());
		this.destinationSummary_2.setText(theWizardStepFlipper.getWizardStepView2().getSummaryText_2());
	}

	@Override
	public String getSummaryText() { return null; }

	@Override
	public boolean validWizardStepData() { return true; }

	@Override
	protected String getHelpContextUrlString() {
		return FmsHelpIndex.NODE_PUBLISHING__DO_IT_NOW;
	}

}
