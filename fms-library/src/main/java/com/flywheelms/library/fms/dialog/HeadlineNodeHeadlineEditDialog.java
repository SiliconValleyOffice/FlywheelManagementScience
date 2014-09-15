/* @(#)HeadlineNodeHeadlineEditDialog.java
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

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;

import com.flywheelms.library.R;
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;
import com.flywheelms.library.fms.activity.FmmNodeEditorActivity;
import com.flywheelms.library.fms.widget.edit_text.HeadlineWidgetEditText;
import com.flywheelms.library.fms.widget.text_view.FmmNodeIdWidgetTextView;
import com.flywheelms.library.fms.widget.text_view.FmmNodeTypeWidgetTextView;
import com.flywheelms.library.fms.widget.text_view.HeadlineWidgetTextView;
import com.flywheelms.library.gcg.activity.GcgActivity;
import com.flywheelms.library.gcg.helper.GcgHelper;
import com.flywheelms.library.gcg.treeview.GcgTreeViewAdapter;

public class HeadlineNodeHeadlineEditDialog extends FmsCancelOkFdkDialog {

	private GcgTreeViewAdapter treeViewAdapter;
	private FmmNodeEditorActivity fmmNodeEditorActivity;
	protected FmmNodeIdWidgetTextView fmmNodeIdWidget;
	protected FmmNodeTypeWidgetTextView fmmNodeTypeWidget;
	protected HeadlineWidgetTextView originalHeadlineWidget;
	protected HeadlineWidgetEditText newHeadlineWidget;

	public HeadlineNodeHeadlineEditDialog(GcgActivity aGcgActivity, GcgTreeViewAdapter aTreeViewAdapter, FmmHeadlineNode aHeadlineNode ) {
		super(aGcgActivity, aHeadlineNode.getFmmNodeDefinition());
		this.treeViewAdapter = aTreeViewAdapter;
		this.headlineNode = aHeadlineNode;
		finalConstruction();
	}

	public HeadlineNodeHeadlineEditDialog(GcgActivity aGcgActivity, FmmHeadlineNode aHeadlineNode ) {
		super(aGcgActivity, aHeadlineNode.getFmmNodeDefinition());
		this.headlineNode = aHeadlineNode;
		finalConstruction();
	}

	public HeadlineNodeHeadlineEditDialog(GcgActivity aGcgActivity, FmmNodeDefinition anFmmNodeDefinition, FmmHeadlineNode aHeadlineNode ) {
		super(aGcgActivity, anFmmNodeDefinition);
		this.headlineNode = aHeadlineNode;
		finalConstruction();
	}

	private void finalConstruction() {
		initializeDialogBodyLate();
		initFdkHostSupport();
		manageButtonState();
	}

	@Override
	protected int getDialogTitleStringResourceId() {
		return R.string.fms__edit_headline;
	}

	@Override
	protected int getDialogTitleIconResourceId() {
		return this.fmmNodeDefinition.getDialogDrawableResourceId();
	}

	@Override
	protected int getCustomDialogContentsResourceId() {
		return R.layout.fmm__headline_node__headline_edit__dialog;
	}

	@Override
	protected void initializeDialogBody() {
		return;
	}

	protected void initializeDialogBodyLate() {
		super.initializeDialogBody();
		this.fmmNodeIdWidget = (FmmNodeIdWidgetTextView) this.dialogBodyView.findViewById(R.id.fmm_node_id);
		this.fmmNodeIdWidget.setText(this.headlineNode.getAbbreviatedNodeIdString());
		this.fmmNodeTypeWidget = (FmmNodeTypeWidgetTextView) this.dialogBodyView.findViewById(R.id.fmm_node__type);
		this.fmmNodeTypeWidget.setText(this.fmmNodeDefinition.getLabelTextResourceId());
		this.originalHeadlineWidget = (HeadlineWidgetTextView) this.dialogBodyView.findViewById(R.id.headline__original);
		this.originalHeadlineWidget.setText(this.headlineNode.getHeadline());
		this.originalHeadlineWidget.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
                String theLeadingSpace = "";
                if(HeadlineNodeHeadlineEditDialog.this.newHeadlineWidget.getSelectionStart() != 0) {
                    theLeadingSpace = " ";
                }
				HeadlineNodeHeadlineEditDialog.this.newHeadlineWidget.insert(
						HeadlineNodeHeadlineEditDialog.this.newHeadlineWidget.getSelectionStart(),
						theLeadingSpace + HeadlineNodeHeadlineEditDialog.this.originalHeadlineWidget.getText() );
				HeadlineNodeHeadlineEditDialog.this.newHeadlineWidget.setSelectionAtEnd();
			}
		});
		this.newHeadlineWidget = (HeadlineWidgetEditText) this.dialogBodyView.findViewById(R.id.headline__new);
		this.newHeadlineWidget.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) { return; }

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) { return; }

			@Override
			public void afterTextChanged(Editable s) {
				HeadlineNodeHeadlineEditDialog.this.newHeadlineWidget.manageBackgroundState();
				HeadlineNodeHeadlineEditDialog.this.manageButtonState();
			}
		});
	}

	@Override
	protected void manageButtonState() {
		if(this.buttonOk == null) {
			return;
		}
		this.buttonOk.setVisibility(this.newHeadlineWidget.isMinimumInput() && isDifferentHeadline() ? View.VISIBLE : View.INVISIBLE);
		this.newHeadlineWidget.setDataStatus(this.newHeadlineWidget.isMinimumInput() && isDifferentHeadline());
	}

	private boolean isDifferentHeadline() {
		return ! this.newHeadlineWidget.getText().toString().equals(this.originalHeadlineWidget.getText().toString());
	}

	@Override
	protected void onClickButtonOk() {
		updateHeadlineNode();
		this.gcgActivity.stopDialog();
	}

	private void updateHeadlineNode() {
		this.headlineNode.setHeadline(this.newHeadlineWidget.getText().toString());
		if(FmmDatabaseMediator.getActiveMediator().updateHeadlineNodeHeadline(this.headlineNode)) {
			if(this.treeViewAdapter != null) {
				this.treeViewAdapter.updateHeadlineNodeHeadline(this.headlineNode);
			} else {
				((FmmNodeEditorActivity) this.gcgActivity).updateHeadlineNodeHeadline(this.headlineNode);
			}
			GcgHelper.makeToast("Headline updated.");
		} else {
			GcgHelper.makeToast("ERROR:  Unable to update headline for " + this.fmmNodeTypeWidget.getText() + " " + this.headlineNode.getHeadline());
		}
	}

	@Override
	public void initFdkDictationResultsConsumerMap() {
		addFdkDictationResultsConsumer(this.newHeadlineWidget);
		this.currentFdkDictationResultsConsumer = this.newHeadlineWidget;
		fdkFocusConsumer(this.currentFdkDictationResultsConsumer);
	}

}
