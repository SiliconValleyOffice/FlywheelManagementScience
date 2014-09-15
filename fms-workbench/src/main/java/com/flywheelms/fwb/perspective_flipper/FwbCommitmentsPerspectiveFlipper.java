/* @(#)FwbCommitmentsPerspectiveFlipper.java
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

package com.flywheelms.fwb.perspective_flipper;

import android.content.Context;
import android.util.AttributeSet;

import com.flywheelms.fwb.R;
import com.flywheelms.fwb.perspective.FwbCommitmentsConfirmedPerspective;
import com.flywheelms.fwb.perspective.FwbCommitmentsDeclinedPerspective;
import com.flywheelms.fwb.perspective.FwbCommitmentsProposedPerspective;
import com.flywheelms.fwb.perspective.FwbCommitmentsSuggestedPerspective;
import com.flywheelms.fwb.perspective.FwbCommitmentsWithdrawnPerspective;
import com.flywheelms.library.fmm.context.FmmFrame;
import com.flywheelms.library.gcg.activity.GcgActivity;
import com.flywheelms.library.gcg.menu.GcgFrameSpinner;
import com.flywheelms.library.gcg.viewflipper.GcgPerspectiveFlipper;
import com.flywheelms.library.gcg.viewflipper.GcgViewFlipperListener;

public class FwbCommitmentsPerspectiveFlipper extends GcgPerspectiveFlipper {

	public static final int menu_position__CONFIRMED = 0; 
	public static final int menu_position__PROPOSED = 1; 
	public static final int menu_position__SUGGESTED = 2; 
	public static final int menu_position__PROPOSED_AND_DECLINED = 3; 
	public static final int menu_position__CONFIRMED_AND_WITHDRAWN = 4; 
	private FwbCommitmentsConfirmedPerspective confirmedPerspective;
	private FwbCommitmentsProposedPerspective proposedPerspective;
	private FwbCommitmentsSuggestedPerspective suggestedPerspective;
	private FwbCommitmentsDeclinedPerspective declinedPerspective;
	private FwbCommitmentsWithdrawnPerspective withdrawnPerspective;

	public FwbCommitmentsPerspectiveFlipper(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
	}
	
	public void initialize(GcgActivity aLibraryActivity, GcgViewFlipperListener aViewFlipListener, GcgFrameSpinner aFrameSpinner, int aSpinnnableMenuIndex) {
		super.initialize(aLibraryActivity, aViewFlipListener, FmmFrame.COMMITMENT, aFrameSpinner);
		this.spinnnableMenuIndex = aSpinnnableMenuIndex;
		initializeViewFlipperViews();
	}

	@Override
	public void initializeViewFlipperViews() {
		this.confirmedPerspective = (FwbCommitmentsConfirmedPerspective) getGcgActivity().findViewById(R.id.fwb_commitments__confirmed);
		this.confirmedPerspective.initialize(getGcgActivity(), this, this.spinnnableMenuIndex, FwbCommitmentsPerspectiveFlipper.menu_position__CONFIRMED);
		this.proposedPerspective = (FwbCommitmentsProposedPerspective) getGcgActivity().findViewById(R.id.fwb_commitments__proposed);
		this.proposedPerspective.initialize(getGcgActivity(), this, this.spinnnableMenuIndex, FwbCommitmentsPerspectiveFlipper.menu_position__PROPOSED);
		this.suggestedPerspective = (FwbCommitmentsSuggestedPerspective) getGcgActivity().findViewById(R.id.fwb_commitments__suggested);
		this.suggestedPerspective.initialize(getGcgActivity(), this, this.spinnnableMenuIndex, FwbCommitmentsPerspectiveFlipper.menu_position__SUGGESTED);
		this.declinedPerspective = (FwbCommitmentsDeclinedPerspective) getGcgActivity().findViewById(R.id.fwb_commitments__proposed_and_declined);
		this.declinedPerspective.initialize(getGcgActivity(), this, this.spinnnableMenuIndex, FwbCommitmentsPerspectiveFlipper.menu_position__PROPOSED_AND_DECLINED);
		this.withdrawnPerspective= (FwbCommitmentsWithdrawnPerspective) getGcgActivity().findViewById(R.id.fwb_commitments__confirmed_and_withdrawn);
		this.withdrawnPerspective.initialize(getGcgActivity(), this, this.spinnnableMenuIndex, FwbCommitmentsPerspectiveFlipper.menu_position__CONFIRMED_AND_WITHDRAWN);
		super.initializeViewFlipperViews();
	}

	public FwbCommitmentsConfirmedPerspective getConfirmedView() {
		return this.confirmedPerspective;
	}

	public FwbCommitmentsProposedPerspective getProposedView() {
		return this.proposedPerspective;
	}

	public FwbCommitmentsSuggestedPerspective getSuggestedView() {
		return this.suggestedPerspective;
	}

	public FwbCommitmentsDeclinedPerspective getProposedAndDeclinedView() {
		return this.declinedPerspective;
	}

	public FwbCommitmentsWithdrawnPerspective getConfirmedAndWithdrawnView() {
		return this.withdrawnPerspective;
	}

	@Override
	public void discardDataChanges() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewData() {
		// TODO Auto-generated method stub
		
	}

}
