/* @(#)HeadlineNodeMoveDialog.java
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
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.flywheelms.library.R;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;
import com.flywheelms.library.fms.widget.FmmHeadlineNodeWidgetSpinner;
import com.flywheelms.library.fms.widget.spinner.SequencePositionWidgetSpinner;
import com.flywheelms.library.fms.widget.text_view.FmmNodeTypeWidgetTextView;
import com.flywheelms.library.fms.widget.text_view.HeadlineWidgetTextView;
import com.flywheelms.library.gcg.GcgActivity;
import com.flywheelms.library.gcg.helper.GcgHelper;
import com.flywheelms.library.gcg.helper.GuiHelper;
import com.flywheelms.library.gcg.treeview.GcgTreeViewAdapter;

public abstract class HeadlineNodeMoveDialog extends FmsCancelOkDialog {

	GcgTreeViewAdapter treeViewAdapter;
	protected FmmNodeTypeWidgetTextView fmmNodeTypeWidget;
	protected HeadlineWidgetTextView headlineWidgetTextView;
	protected LinearLayout dispositionContainerLayout;
	protected FmmHeadlineNode targetHeadlineNodeException;
	protected LinearLayout dispositionLayout;
	protected FmmHeadlineNodeWidgetSpinner dispositionTargetGrandparentWidgetSpinner;
	protected FmmHeadlineNodeWidgetSpinner dispositionTargetParentWidgetSpinner;
	protected FmmHeadlineNodeWidgetSpinner dispositionTargetWidgetSpinner;
	protected SequencePositionWidgetSpinner sequencePositionSpinner;
	protected boolean isLinked = false;

	public HeadlineNodeMoveDialog(GcgActivity aLibraryActivity, GcgTreeViewAdapter aTreeViewAdapter, FmmHeadlineNode aHeadlineNode, FmmHeadlineNode aTargetHeadlineNodeException) {
		super(aLibraryActivity, aHeadlineNode);
		this.treeViewAdapter = aTreeViewAdapter;
		this.targetHeadlineNodeException = aTargetHeadlineNodeException;
	}

	@Override
	protected int getDialogTitleIconResourceId() {
		return this.headlineNode.getFmmNodeDefinition().getDialogDrawableResourceId();
	}

	@Override
	protected int getDialogTitleStringResourceId() {
		return R.string.fms__move;
	}

	@Override
	protected int getDialogBodyLayoutResourceId() {
		return R.layout.fmm__headline_node__move__dialog;
	}

	protected abstract int getMoveDispositionLayoutResourceId();

	@Override
	protected void initializeDialogBody() {
		return;
	}

	protected void initializeDialogBodyLate() {
		super.initializeDialogBody();
		this.fmmNodeTypeWidget = (FmmNodeTypeWidgetTextView) this.dialogBodyView.findViewById(R.id.fmm_node__type);
		this.fmmNodeTypeWidget.setText(this.fmmNodeDefinition.getLabelTextResourceId());
		this.headlineWidgetTextView = (HeadlineWidgetTextView) this.dialogBodyView.findViewById(R.id.headline);
		this.headlineWidgetTextView.setText(GuiHelper.getColorString(this.headlineNode.getDataText(), Color.RED));
		initializeDispositionLayout();
		manageButtonState();
	}

	private void initializeDispositionLayout() {
		LinearLayout theDispositionLayout = (LinearLayout) LayoutInflater.from(this.gcgActivity).inflate(getMoveDispositionLayoutResourceId(), this.dialogBodyView, false);
		this.dialogBodyView.addView(theDispositionLayout, this.dialogBodyView.getChildCount() - 1);
		findDispositionTargetGrandparentSpinner();
		findDispositionTargetParentSpinner();
		findDispositionTargetSpinner();
		findSequencePositionSpinner();
		if(this.dispositionTargetGrandparentWidgetSpinner != null) {
			setInitialDispositionTargetGrandparentSpinnerData();
			setInitialDispositionTargetGrandparentSpinnerSelection();
			initializeDispositionTargetGrandparentSpinnerListener();
		}
		if(this.dispositionTargetParentWidgetSpinner != null) {
			setInitialDispositionTargetParentSpinnerData();
			setInitialDispositionTargetParentSpinnerSelection();
			initializeDispositionTargetParentSpinnerListener();
		}
		setInitialDispositionTargetSpinnerData();
		setInitialDispositionTargetSpinnerSelection();
		initializeSequencePositionSpinner();
		initializeDispositionTargetSpinnerListener();
	}

	protected FmmHeadlineNodeWidgetSpinner findDispositionTargetGrandparentSpinner() {
		FmmHeadlineNodeWidgetSpinner theFmmHeadlineNodeWidgetSpinner = (FmmHeadlineNodeWidgetSpinner) this.dialogBodyView.findViewById(R.id.disposition_target__grandparent__spinner);
		if(theFmmHeadlineNodeWidgetSpinner != null) {
			theFmmHeadlineNodeWidgetSpinner.setGcgActivity(this.gcgActivity);
		}
		return this.dispositionTargetGrandparentWidgetSpinner = theFmmHeadlineNodeWidgetSpinner;
	}

	protected FmmHeadlineNodeWidgetSpinner findDispositionTargetParentSpinner() {
		FmmHeadlineNodeWidgetSpinner theFmmHeadlineNodeWidgetSpinner = (FmmHeadlineNodeWidgetSpinner) this.dialogBodyView.findViewById(R.id.disposition_target__parent__spinner);
		if(theFmmHeadlineNodeWidgetSpinner != null) {
			theFmmHeadlineNodeWidgetSpinner.setGcgActivity(this.gcgActivity);
		}
		return this.dispositionTargetParentWidgetSpinner = theFmmHeadlineNodeWidgetSpinner;
	}

	protected FmmHeadlineNodeWidgetSpinner findDispositionTargetSpinner() {
		FmmHeadlineNodeWidgetSpinner theFmmHeadlineNodeWidgetSpinner = (FmmHeadlineNodeWidgetSpinner) this.dialogBodyView.findViewById(R.id.disposition_target__spinner);
		if(theFmmHeadlineNodeWidgetSpinner != null) {
			theFmmHeadlineNodeWidgetSpinner.setGcgActivity(this.gcgActivity);
		}
		return this.dispositionTargetWidgetSpinner = theFmmHeadlineNodeWidgetSpinner;
	}
	
	protected void findSequencePositionSpinner() {
		this.sequencePositionSpinner = (SequencePositionWidgetSpinner) this.dialogBodyView.findViewById(R.id.list_position__spinner);
	}
	
	protected void initializeSequencePositionSpinner() {
		updateSequencePositionWidgetSpinner();
	}
	
	protected void setInitialDispositionTargetGrandparentSpinnerData() { return; }

	protected void setInitialDispositionTargetParentSpinnerData() { return; }

	protected abstract void setInitialDispositionTargetSpinnerData();

	protected void setInitialDispositionTargetGrandparentSpinnerSelection() { return; }

	protected void setInitialDispositionTargetParentSpinnerSelection() { return; }

	protected abstract void setInitialDispositionTargetSpinnerSelection();

	protected void initializeDispositionTargetGrandparentSpinnerListener() { return; }

	protected void initializeDispositionTargetParentSpinnerListener() { return; }

	protected void initializeDispositionTargetSpinnerListener() { return; }

	protected void updateDispositionTargetParentWidgetSpinner() { return; }

	protected void updateDispositionTargetWidgetSpinner() { return; }
	
	protected abstract void updateSequencePositionWidgetSpinner();

	protected abstract boolean moveHeadlineNode();
	
	protected void toastMoveResult(boolean bIsSuccessful) {
		StringBuffer theStringBuffer = new StringBuffer();
		if(bIsSuccessful) {
			theStringBuffer.append("Moved ");
		} else {
			theStringBuffer.append("Fatal Error:  Could not move ");
		}
		theStringBuffer.append(this.fmmNodeDefinition.getLabelText() + " to" +
					((FmmHeadlineNode) this.dispositionTargetWidgetSpinner.getSelectedItem()).getFmmNodeDefinition().getLabelText() +
					":  " + this.dispositionTargetWidgetSpinner.getSelectedItem().getDataText());
		GcgHelper.makeToast(theStringBuffer.toString());
	}
	
	@Override
	protected void onClickButtonOk() {
		if(moveHeadlineNode()) {
			this.treeViewAdapter.rebuildTreeView();
		}
		super.onClickButtonOk();
	}

}
