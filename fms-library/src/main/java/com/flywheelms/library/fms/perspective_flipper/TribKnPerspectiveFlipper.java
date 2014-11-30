/* @(#)TribKnPerspectiveFlipper.java
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

package com.flywheelms.library.fms.perspective_flipper;

import android.content.Context;
import android.util.AttributeSet;

import com.flywheelms.library.R;
import com.flywheelms.library.fmm.node.impl.enumerator.GovernanceTarget;
import com.flywheelms.library.fms.activity.FmmNodeEditorActivity;
import com.flywheelms.library.fms.perspective_flipper.perspective.FmsCommitmentsPerspective;
import com.flywheelms.library.fms.perspective_flipper.perspective.FmsCommunityPerspective;
import com.flywheelms.library.fms.perspective_flipper.perspective.FmsDecKanGlPerspective;
import com.flywheelms.library.fms.perspective_flipper.perspective.FmsGovernancePerspective;
import com.flywheelms.library.fms.perspective_flipper.perspective.FmsPerspectiveFlipper;
import com.flywheelms.library.fms.perspective_flipper.perspective.FmsTaskPointBudgetPerspective;

public class TribKnPerspectiveFlipper extends FmsPerspectiveFlipper {

	public static final int menu_position__DECKANGL = 0; 
	public static final int menu_position__GOVERNANCE = 1; 
	public static final int menu_position__WORK_TASK_BUDGET = 2;
	public static final int menu_position__COMMITMENTS = 3;
	public static final int menu_position__COMMUNITY = 4;
	private FmsDecKanGlPerspective mFmsDecKanGlPerspective;
	private FmsGovernancePerspective mFmsGovernancePerspective;
    private FmsTaskPointBudgetPerspective mFmsWorkTaskBudgetPerspective;
	private FmsCommitmentsPerspective mFmsCommitmentsPerspective;
	private FmsCommunityPerspective mFmsCommunityPerspective;

	public TribKnPerspectiveFlipper(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
	}

	@Override
	public void initializeViewFlipperViews() {
		this.mFmsDecKanGlPerspective = (FmsDecKanGlPerspective) getGcgActivity().findViewById(R.id.tribkn_frame__deckangl);
		this.mFmsDecKanGlPerspective.initialize(getGcgActivity(), this, this.spinnnableMenuIndex, menu_position__DECKANGL);
		this.mFmsGovernancePerspective = (FmsGovernancePerspective) getGcgActivity().findViewById(R.id.tribkn_frame__governance);
		this.mFmsGovernancePerspective.initialize(getGcgActivity(), this, this.spinnnableMenuIndex, menu_position__GOVERNANCE);
		this.mFmsWorkTaskBudgetPerspective = (FmsTaskPointBudgetPerspective) getGcgActivity().findViewById(R.id.tribkn_frame__work_task_budget);
		this.mFmsWorkTaskBudgetPerspective.initialize(getGcgActivity(), this, this.spinnnableMenuIndex, menu_position__WORK_TASK_BUDGET);
		this.mFmsCommitmentsPerspective = (FmsCommitmentsPerspective) getGcgActivity().findViewById(R.id.tribkn_frame__commitments);
		this.mFmsCommitmentsPerspective.initialize(getGcgActivity(), this, this.spinnnableMenuIndex, menu_position__COMMITMENTS);
		this.mFmsCommunityPerspective = (FmsCommunityPerspective) getGcgActivity().findViewById(R.id.tribkn_frame__community);
		this.mFmsCommunityPerspective.initialize(getGcgActivity(), this, this.spinnnableMenuIndex, menu_position__COMMUNITY);
		super.initializeViewFlipperViews();
	}

	public GovernanceTarget getGovernanceTarget() {
		return GovernanceTarget.getObjectForFmmDictionaryEntry(((FmmNodeEditorActivity) getGcgActivity()).getFmmNodeDefinition());
	}

	public FmsDecKanGlPerspective getFmsDecKanGlPerspective() {
		return this.mFmsDecKanGlPerspective;
	}

	public FmsGovernancePerspective getFmsGovernancePerspective() {
		return this.mFmsGovernancePerspective;
	}

    public FmsTaskPointBudgetPerspective getFmsWorkTaskBudgetPerspective() {
        return this.mFmsWorkTaskBudgetPerspective;
    }

	public FmsCommitmentsPerspective getFmsCommitmentsPerspective() {
		return this.mFmsCommitmentsPerspective;
	}

	public FmsCommunityPerspective getFmsCommunityPerspective() {
		return this.mFmsCommunityPerspective;
	}

	@Override
	public int getFrameMenuSpacerCurtainBackgroundResourceId() {
		return R.color.gcg__sands_of_time;
	}

	@Override
	public void discardDataChanges() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewData() {
		this.mFmsDecKanGlPerspective.viewData();
	}

}
