/* @(#)DecKanGlNavigationDialog.java
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

package com.flywheelms.library.fms.dialog;

import com.flywheelms.gcongui.deckangl.component.DecKanGlNavigationComponentParent;
import com.flywheelms.gcongui.deckangl.glyph.DecKanGlGlyph;
import com.flywheelms.gcongui.gcg.activity.GcgActivity;
import com.flywheelms.gcongui.gcg.context.GcgFrame;
import com.flywheelms.gcongui.gcg.interfaces.GcgPerspective;
import com.flywheelms.library.R;
import com.flywheelms.library.fmm.node.FmmHeadlineNodeShallow;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;
import com.flywheelms.library.fms.component.FmsDecKanGlNavigationComponent;
import com.flywheelms.library.fms.interfaces.FmsDecKanGlNavigationDialogParent;
import com.flywheelms.library.fms.widget.text_view.HeadlineWidgetTextView;

import java.util.ArrayList;

public class DecKanGlNavigationDialog extends FmsCancelDialog implements DecKanGlNavigationComponentParent {
	
	private final ArrayList<FmmHeadlineNodeShallow> peerHeadlineNodeShallowList;
	private final String parentNodeIdString;
	private FmsDecKanGlNavigationComponent decKanGlGlyphNavigationWidget;

	public DecKanGlNavigationDialog(
			GcgActivity aLibraryActivity,
			ArrayList<FmmHeadlineNodeShallow> athePeerHeadlineNodeShallowList,
			String aParentNodeIdString,
			FmmHeadlineNode aHeadlineNode ) {
		super(aLibraryActivity, aHeadlineNode);
		this.peerHeadlineNodeShallowList = athePeerHeadlineNodeShallowList;
		this.parentNodeIdString = aParentNodeIdString;
		this.decKanGlGlyphNavigationWidget = (FmsDecKanGlNavigationComponent) this.dialogBodyView.findViewById(R.id.deckangl__navigation_widget);
		this.decKanGlGlyphNavigationWidget.setParent(this);
		this.decKanGlGlyphNavigationWidget.viewData();
	}

	@Override
	protected void initializeDialogBody() {
		super.initializeDialogBody();
		((HeadlineWidgetTextView) this.dialogBodyView.findViewById(R.id.headline_widget)).setText(getFmmHeadlineNode().getHeadline());
	}

	@Override
	protected int getDialogTitleIconResourceId() {
		return R.drawable.deckangl;
	}

	@Override
	protected int getDialogTitleStringResourceId() {
		return R.string.deckangl_tm__glyph_navigation;
	}

	@Override
	protected int getCustomDialogContentsResourceId() {
		return R.layout.deckangl__navigation__dialog;
	}
	
	@Override
	public void activityNavigation(GcgFrame aFrame, GcgPerspective aPerspective) {
		((FmsDecKanGlNavigationDialogParent) this.gcgActivity).decKanGlNavigation(
			aFrame,
			aPerspective,
			this.parentNodeIdString,
			this.peerHeadlineNodeShallowList,
			getFmmHeadlineNode().getNodeIdString() );
		this.gcgActivity.stopDialog();
	}

    @Override
	public DecKanGlGlyph getDecKanGlGlyph() {
		return getFmmHeadlineNode().getDecKanGlGlyph();
	}

    public void launchNounDefinitionDialog() {
        this.gcgActivity.startDialog(new FmmNounDictionaryDialog(this.gcgActivity, getFmmNodeDefinition()));
    }

}
