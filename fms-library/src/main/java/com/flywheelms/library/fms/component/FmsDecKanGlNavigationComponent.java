/* @(#)FmsDecKanGlNavigationComponent.java
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

package com.flywheelms.library.fms.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.flywheelms.gcongui.deckangl.component.DecKanGlNavigationComponent;
import com.flywheelms.gcongui.deckangl.enumerator.DecKanGlDecoratorCanvasLocation;
import com.flywheelms.library.fmm.context.FmmFrame;
import com.flywheelms.library.fmm.context.FmmPerspective;

public class FmsDecKanGlNavigationComponent extends DecKanGlNavigationComponent {
	
	public FmsDecKanGlNavigationComponent(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

    protected void initializeViewBody() {
        super.initializeViewBody();
        getDecKanGlGlyphView().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                FmsDecKanGlNavigationComponent.this.decKanGlNavigationParent.launchNounDefinitionDialog();
            }
        });
    }

	@Override
	protected void navigateToDecoratorDetails(DecKanGlDecoratorCanvasLocation aCanvasLocation) {
		FmmFrame theFrame = FmmFrame.FSE;
		FmmPerspective thePerspective = FmmPerspective.STORY;
		switch (aCanvasLocation) {
		case top_zone_LEFT:
			theFrame = FmmFrame.TRIBKN;
			thePerspective = FmmPerspective.GOVERNANCE;
			break;
		case top_zone_MIDDLE:
			break;
		case top_zone_RIGHT:
			theFrame = FmmFrame.TRIBKN;
			thePerspective = FmmPerspective.GOVERNANCE;
			break;
		case bottom_zone_LEFT:
            theFrame = FmmFrame.FSE;
            thePerspective = FmmPerspective.STORY;
			break;
		case bottom_zone_MIDDLE:
			break;
		case bottom_zone_RIGHT:
			break;
		case left_zone_TOP:
			break;
		case left_zone_MIDDLE:
			theFrame = FmmFrame.TRIBKN;
			thePerspective = FmmPerspective.COMMITMENTS;
			break;
		case left_zone_BOTTOM:
            theFrame = FmmFrame.TRIBKN;
            thePerspective = FmmPerspective.TASK_POINT_BUDGET;
			break;
		case right_zone_TOP:
			theFrame = FmmFrame.CONTEXT_FOR_NODE;
			thePerspective = FmmPerspective.STRATEGIC_PLANNING;
			break;
		case right_zone_MIDDLE:
			theFrame = FmmFrame.TRIBKN;
			thePerspective = FmmPerspective.COMMITMENTS;
			break;
		case right_zone_BOTTOM:
			theFrame = FmmFrame.TRIBKN;
			thePerspective = FmmPerspective.COMMUNITY;
			//$FALL-THROUGH$
		default:
		}
		this.decKanGlNavigationParent.activityNavigation(
                theFrame,
                thePerspective);
	}

}
