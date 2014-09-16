/* @(#)HeadlineNodeOrphanDialog.java
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

import android.graphics.Color;
import android.widget.LinearLayout;

import com.flywheelms.library.R;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;
import com.flywheelms.library.fms.widget.FmmHeadlineNodeWidgetSpinner;
import com.flywheelms.library.fms.widget.spinner.SequencePositionWidgetSpinner;
import com.flywheelms.library.fms.widget.text_view.FmmNodeTypeWidgetTextView;
import com.flywheelms.library.fms.widget.text_view.HeadlineWidgetTextView;
import com.flywheelms.library.gcg.activity.GcgActivity;
import com.flywheelms.library.gcg.helper.GcgHelper;
import com.flywheelms.library.gcg.helper.GuiHelper;
import com.flywheelms.library.gcg.treeview.GcgTreeViewAdapter;

public abstract class HeadlineNodeOrphanDialog extends FmsCancelOkDialog {

	GcgTreeViewAdapter treeViewAdapter;
	protected FmmNodeTypeWidgetTextView fmmNodeTypeWidget;
	protected HeadlineWidgetTextView headlineWidgetTextView;
	protected LinearLayout dispositionContainerLayout;
	protected LinearLayout dispositionLayout;
	protected FmmHeadlineNodeWidgetSpinner dispositionTargetGrandparentWidgetSpinner;
	protected FmmHeadlineNodeWidgetSpinner dispositionTargetParentWidgetSpinner;
	protected FmmHeadlineNodeWidgetSpinner dispositionTargetWidgetSpinner;
	protected SequencePositionWidgetSpinner sequencePositionSpinner;
	protected boolean isLinked = false;

	public HeadlineNodeOrphanDialog(GcgActivity aLibraryActivity, GcgTreeViewAdapter aTreeViewAdapter, FmmHeadlineNode aHeadlineNode) {
		super(aLibraryActivity, aHeadlineNode);
		this.treeViewAdapter = aTreeViewAdapter;
	}

	@Override
	protected int getDialogTitleStringResourceId() {
		return R.string.fms__orphan;
	}
	
	@Override
	protected int getDialogTitleIconResourceId() {
		return getFmmHeadlineNode().getFmmNodeDefinition().getDialogDrawableResourceId();
	}

	@Override
	protected int getDialogBodyLayoutResourceId() {
		return R.layout.fmm__headline_node__orphan__dialog;
	}

	@Override
	protected void initializeDialogBody() {
		return;
	}

	protected void initializeDialogBodyLate() {
		super.initializeDialogBody();
		this.fmmNodeTypeWidget = (FmmNodeTypeWidgetTextView) this.dialogBodyView.findViewById(R.id.fmm_node__type);
		this.fmmNodeTypeWidget.setText(getFmmNodeDefinition().getLabelTextResourceId());
		this.headlineWidgetTextView = (HeadlineWidgetTextView) this.dialogBodyView.findViewById(R.id.headline);
		this.headlineWidgetTextView.setText(GuiHelper.getColorString(getFmmHeadlineNode().getDataText(), Color.RED));
		manageButtonState();
	}

	protected abstract boolean orphanHeadlineNode();
	
	protected void toastMoveResult(boolean bIsSuccessful) {
		StringBuffer theStringBuffer = new StringBuffer();
		if(bIsSuccessful) {
			theStringBuffer.append("Orphaned ");
		} else {
			theStringBuffer.append("Fatal Error:  Could not orphan ");
		}
		theStringBuffer.append(getFmmNodeDefinition().getLabelText() + " to" +
					((FmmHeadlineNode) this.dispositionTargetWidgetSpinner.getSelectedItem()).getFmmNodeDefinition().getLabelText() +
					":  " + this.dispositionTargetWidgetSpinner.getSelectedItem().getDataText());
		GcgHelper.makeToast(theStringBuffer.toString());
	}
	
	@Override
	protected void onClickButtonOk() {
		if(orphanHeadlineNode()) {
			this.treeViewAdapter.rebuildTreeView();
		}
		super.onClickButtonOk();
	}

}
