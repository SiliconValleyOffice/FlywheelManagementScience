/* @(#)FmsGovernancePerspective.java
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
import android.view.View;

import com.flywheelms.gcongui.gcg.activity.GcgActivity;
import com.flywheelms.gcongui.gcg.interfaces.GcgPerspective;
import com.flywheelms.gcongui.gcg.viewflipper.GcgViewFlipper;
import com.flywheelms.library.R;
import com.flywheelms.library.fmm.context.FmmPerspective;
import com.flywheelms.library.fmm.node.impl.enumerator.GovernanceRole;
import com.flywheelms.library.fmm.node.impl.enumerator.GovernanceTarget;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragGovernance;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmGovernableNode;
import com.flywheelms.library.fms.component.FmsGovernanceComponent;
import com.flywheelms.library.fms.component.FmsGovernanceComponentParent;
import com.flywheelms.library.fms.helper.FmsHelpIndex;

public class FmsGovernancePerspective extends FmsPerspectiveFlipperView implements FmsGovernanceComponentParent {
	
	private GcgPerspective gcgPerspective = FmmPerspective.GOVERNANCE;
	private FmsGovernanceComponent administratorComponent;
	private FmsGovernanceComponent customerComponent;
	private FmsGovernanceComponent facilitatorComponent;
	private FmsGovernanceComponent sponsorComponent;
    private FmmGovernableNode fmmGovernableNode;
    private NodeFragGovernance nodeFragGovernance;

	@Override
	public GcgPerspective getGcgPerspective() {
		return this.gcgPerspective;
	}

	public FmsGovernancePerspective(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
	}

	@Override
	protected int getPageTitleResourceId() {
		return R.string.tribkn_quality_metric__governance;
	}

	@Override
	protected int getViewLayoutResourceId() {
		return R.layout.fms_view__governance;
	}

	@Override
	public int getFrameMenuSpacerBackgroundResourceId() {
		return R.color.gcg__sand_dollar;
	}

	@Override
	public void initialize(GcgActivity anGcgActivity, GcgViewFlipper aViewFlipper, int aSpinnableMenuIndex, int aPageNumber) {
		super.initialize(anGcgActivity, aViewFlipper, aSpinnableMenuIndex, aPageNumber);
		this.administratorComponent = (FmsGovernanceComponent) findViewById(R.id.governance_component__administrator);
		this.administratorComponent.setParent(this);
        this.administratorComponent.setVisibility(View.INVISIBLE);
		this.customerComponent = (FmsGovernanceComponent) findViewById(R.id.governance_component__customer);
        this.customerComponent.setParent(this);
        this.customerComponent.setVisibility(View.INVISIBLE);
        this.facilitatorComponent = (FmsGovernanceComponent) findViewById(R.id.governance_component__facilitator);
        this.facilitatorComponent.setParent(this);
        this.facilitatorComponent.setVisibility(View.INVISIBLE);
        this.sponsorComponent = (FmsGovernanceComponent) findViewById(R.id.governance_component__sponsor);
        this.sponsorComponent.setParent(this);
        this.sponsorComponent.setVisibility(View.INVISIBLE);
    }

	@Override
	protected String getHelpContextUrlString() {
		return FmsHelpIndex.PERSPECTIVE__GOVERNANCE;
	}

	@Override
	public NodeFragGovernance getNodeFragGovernance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GovernanceTarget getGovernanceTarget() {
		// TODO Auto-generated method stub
		return null;
	}

    protected void activateView() {
        viewData();
        super.activateView();
    }

    public void viewData() {
        this.nodeFragGovernance = ((FmmGovernableNode) getFmmHeadlineNode()).getNodeFragGovernance();
        this.facilitatorComponent.setVisibility(
                this.nodeFragGovernance.mayHave(GovernanceRole.FACILITATOR) ? View.VISIBLE : View.GONE);
        this.facilitatorComponent.setGovernanceRequired(
                this.nodeFragGovernance.isRequired(GovernanceRole.FACILITATOR));
        this.sponsorComponent.setVisibility(
                this.nodeFragGovernance.mayHave(GovernanceRole.SPONSOR) ? View.VISIBLE : View.GONE);
        this.sponsorComponent.setGovernanceRequired(
                this.nodeFragGovernance.isRequired(GovernanceRole.SPONSOR));
        this.customerComponent.setVisibility(
                this.nodeFragGovernance.mayHave(GovernanceRole.CUSTOMER) ? View.VISIBLE : View.GONE);
        this.customerComponent.setGovernanceRequired(
                this.nodeFragGovernance.isRequired(GovernanceRole.CUSTOMER));
        this.administratorComponent.setVisibility(
                this.nodeFragGovernance.mayHave(GovernanceRole.ADMINISTRATOR) ? View.VISIBLE : View.GONE);
        this.administratorComponent.setGovernanceRequired(
                this.nodeFragGovernance.isRequired(GovernanceRole.ADMINISTRATOR));
    }

}
