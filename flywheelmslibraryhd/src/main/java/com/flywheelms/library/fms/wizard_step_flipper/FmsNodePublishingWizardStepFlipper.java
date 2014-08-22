/* @(#)FmsNodePublishingWizardStepFlipper.java
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

package com.flywheelms.library.fms.wizard_step_flipper;

import java.io.File;
import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;

import com.flywheelms.library.R;
import com.flywheelms.library.fmm.node.impl.governable.CommunityMember;
import com.flywheelms.library.fms.activity.FmsNodeWizardActivity;
import com.flywheelms.library.fms.helper.FmsEmailHelper;
import com.flywheelms.library.fms.miscellaneous.HpEprint;
import com.flywheelms.library.fms.pdf.FmsPdfHelper;
import com.flywheelms.library.fms.wizard_step_flipper.step.FmsNodePublishingContentSelectionWizardStepView;
import com.flywheelms.library.fms.wizard_step_flipper.step.FmsNodePublishingDestinationWizardStepView;
import com.flywheelms.library.fms.wizard_step_flipper.step.FmsNodePublishingDoItNowStepView;
import com.flywheelms.library.gcg.android.AndroidContact;
import com.flywheelms.library.gcg.helper.GcgHelper;
import com.flywheelms.library.gcg.wizard.GcgWizardStepFlipper;

public class FmsNodePublishingWizardStepFlipper extends GcgWizardStepFlipper {
	
	private FmsNodePublishingContentSelectionWizardStepView contentSelectionWizardStep;
	private FmsNodePublishingDestinationWizardStepView destinationWizardStep;
	private FmsNodePublishingDoItNowStepView doItNowhWizardStep;
	private File pdfFile;

	public FmsNodePublishingWizardStepFlipper(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
	}

	@Override
	protected void initializeViewFlipperViews() {
		this.contentSelectionWizardStep = (FmsNodePublishingContentSelectionWizardStepView) findViewById(R.id.wizard_step__content_selection);
		this.contentSelectionWizardStep.initialize(getGcgActivity(), this, 0);
		this.destinationWizardStep = (FmsNodePublishingDestinationWizardStepView) findViewById(R.id.wizard_step__destination);
		this.destinationWizardStep.initialize(getGcgActivity(), this, 1);
		this.doItNowhWizardStep = (FmsNodePublishingDoItNowStepView) findViewById(R.id.wizard_step__publish);
		this.doItNowhWizardStep.initialize(getGcgActivity(), this, 2);
		super.initializeViewFlipperViews();
	}
	
	@Override
	public void doItNow() {
		this.pdfFile = FmsPdfHelper.generatePdfFile(getWizardStepView1(), getWizardActivity().getFmmNodeIdString(), getWizardActivity().getAbbreviatedFmmNodeIdString());
		if(getWizardStepView2().getPrint().isChecked()) {
			HpEprint.print(getGcgActivity(), this.pdfFile);
		}
		String theEmailSubject = FmsEmailHelper.getStandardNodeSubjectLine(
				((FmsNodeWizardActivity)getGcgActivity()).getFmmHeadlineNode() );
		String theEmailMessageBody = FmsEmailHelper.getStandardMessageBodyPreamble(
				((FmsNodeWizardActivity)getGcgActivity()).getFmmHeadlineNode() ) +
				getWizardStepView2().getEmailMessageBody();
		ArrayList<String> theEmailAddressList = new ArrayList<String>();
		for(AndroidContact theAndroidContact : getWizardStepView2().getAndroidContactList()) {
			theEmailAddressList.add(theAndroidContact.getEmailAddress());
		}
		for(CommunityMember theCommunityMember : getWizardStepView2().getCommunityMemberList()) {
			theEmailAddressList.add(theCommunityMember.getEmailAddress());
		}
		if(theEmailAddressList.size() > 0) {
			FmsEmailHelper.sendMail(
					getGcgActivity(),
					theEmailAddressList.toArray(new String[theEmailAddressList.size()]),
					theEmailSubject,
					theEmailMessageBody,
					this.pdfFile);
		}
		GcgHelper.makeToast("Publication complete.");
		getGcgActivity().finish();
	}

	@Override
	public boolean validWizardData() {
		return this.contentSelectionWizardStep.validWizardStepData() && this.destinationWizardStep.validWizardStepData();
	}

	public FmsNodePublishingContentSelectionWizardStepView getWizardStepView1() {
		return this.contentSelectionWizardStep;
	}

	public FmsNodePublishingDestinationWizardStepView getWizardStepView2() {
		return this.destinationWizardStep;
	}

	public FmsNodePublishingDoItNowStepView getWizardStepViewDoItNow() {
		return this.doItNowhWizardStep;
	}

	public void postPrinterInstallationPrint() {
		HpEprint.print(getGcgActivity(), this.pdfFile);
	}

}
