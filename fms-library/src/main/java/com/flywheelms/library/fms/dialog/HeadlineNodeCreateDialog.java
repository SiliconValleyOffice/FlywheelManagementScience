/* @(#)HeadlineNodeCreateDialog.java
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
import android.widget.CheckBox;
import android.widget.RadioButton;

import com.flywheelms.library.R;
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;
import com.flywheelms.library.fms.treeview.filter.FmsTreeViewAdapter;
import com.flywheelms.library.fms.widget.edit_text.HeadlineWidgetEditText;
import com.flywheelms.library.fms.widget.text_view.FmmNodeTypeWidgetTextView;
import com.flywheelms.library.fms.widget.text_view.HeadlineWidgetTextView;
import com.flywheelms.library.gcg.activity.GcgActivity;
import com.flywheelms.library.gcg.container.GcgContainerGroupBoxLinear;
import com.flywheelms.library.gcg.helper.GcgHelper;

public class HeadlineNodeCreateDialog extends FmsCancelOkApplyFdkDialog {

	FmsTreeViewAdapter treeViewAdapter;
	protected final FmmHeadlineNode launchHeadlineNode;
	protected final FmmHeadlineNode logicalParentHeadlineNode;
	protected final int launchNodeSequence;
	protected final int launchNodeChildCount;
	protected FmmNodeTypeWidgetTextView fmmNodeTypeWidget;
	protected HeadlineWidgetEditText headlineWidget;
	protected FmmNodeTypeWidgetTextView parentFmmNodeTypeWidget;
	protected HeadlineWidgetTextView parentHeadlineWidget;
	protected GcgContainerGroupBoxLinear sequenceLayout;
	protected RadioButton firstRadioButton;
	protected RadioButton lastRadioButton;
	protected HeadlineWidgetTextView launchHeadlineWidget;
	protected CheckBox editNewHeadlineNode;

	public HeadlineNodeCreateDialog(
			GcgActivity aLibraryActivity,
			FmsTreeViewAdapter aTreeViewAdapter,
			FmmNodeDefinition anFmmNodeDefinition,
			FmmHeadlineNode aLaunchHeadlineNode,
			FmmHeadlineNode aParentHeadlineNode,
			int aLaunchNodeSequence,
			int aLaunchNodeChildCount ) {
		super(aLibraryActivity, anFmmNodeDefinition);
		this.treeViewAdapter = aTreeViewAdapter;
		this.launchHeadlineNode = aLaunchHeadlineNode;
		this.fmsDialogExtension.parentHeadlineNode = aParentHeadlineNode;
		this.logicalParentHeadlineNode = getLogicalParentHeadlineNode();
		this.launchNodeSequence = aLaunchNodeSequence;
		this.launchNodeChildCount = aLaunchNodeChildCount;
		initializeDialogBodyLate();
		initFdkHostSupport();
		manageButtonState();
	}

    @Override
    protected int getDialogTitleStringResourceId() {
        return R.string.fms__create;
    }

	@Override
	protected int getCustomDialogContentsResourceId() {
		return R.layout.fmm__headline_node__create__dialog;
	}

	@Override
	protected void initializeDialogBody() {
		return;
	}

	protected void initializeDialogBodyLate() {
		super.initializeDialogBody();
		this.fmmNodeTypeWidget = (FmmNodeTypeWidgetTextView) this.dialogBodyView.findViewById(R.id.fmm_node__type);
		this.fmmNodeTypeWidget.setText(getFmmNodeDefinition().getLabelTextResourceId());
		this.headlineWidget = (HeadlineWidgetEditText) this.dialogBodyView.findViewById(R.id.headline);
		this.headlineWidget.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
                return;
            }

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                return;
            }

			@Override
			public void afterTextChanged(Editable s) {
				HeadlineNodeCreateDialog.this.manageButtonState();
			}
		});
		this.parentFmmNodeTypeWidget = (FmmNodeTypeWidgetTextView) this.dialogBodyView.findViewById(R.id.fmm_node__type__parent_node);
		this.parentHeadlineWidget = (HeadlineWidgetTextView) this.dialogBodyView.findViewById(R.id.headline__parent_node);
		this.launchHeadlineWidget = (HeadlineWidgetTextView) this.dialogBodyView.findViewById(R.id.headline__launch_node);
        this.sequenceLayout = (GcgContainerGroupBoxLinear) this.dialogBodyView.findViewById(R.id.group_box__sequence);
        if(getFmmNodeDefinition().isAlphaSort()) {
            this.sequenceLayout.setVisibility(View.GONE);
        } else {
            if(this.launchNodeChildCount == 0 && !isPeerLaunch()) {
                this.sequenceLayout.setVisibility(View.GONE);
            } else {
                this.firstRadioButton = (RadioButton) this.dialogBodyView.findViewById(R.id.first__radio_button);
                this.lastRadioButton = (RadioButton) this.dialogBodyView.findViewById(R.id.last__radio_button);
            }
        }
		this.editNewHeadlineNode = (CheckBox) this.dialogBodyView.findViewById(R.id.edit_new_headline_node);
        this.editNewHeadlineNode.setText("Edit new " + getFmmNodeDefinition().getLabelText());
	}
	
	@Override
	public void refreshDialog() {
		this.parentFmmNodeTypeWidget.setText(this.logicalParentHeadlineNode.getFmmNodeDefinition().getLabelTextResourceId());
		this.parentHeadlineWidget.setText(this.logicalParentHeadlineNode.getHeadline());
		if(isPeerLaunch() && ! alphaSort()) {
			this.launchHeadlineWidget.setLabelText(this.launchHeadlineNode.getFmmNodeDefinition().getLabelText());
			this.launchHeadlineWidget.setText(this.launchHeadlineNode.getHeadline());
			if(isPeerLaunch() && ! getFmmNodeDefinition().isAlphaSort()) {
				this.firstRadioButton.setText("before");
				this.lastRadioButton.setText("after");
			}
		} else {
			this.launchHeadlineWidget.setVisibility(View.GONE);
			if(this.launchNodeChildCount > 0 && ! getFmmNodeDefinition().isAlphaSort()) {
				this.firstRadioButton.setText("as first " + getFmmNodeDefinition().getLabelText());
				this.lastRadioButton.setText("as last " + getFmmNodeDefinition().getLabelText());
			}
		}
	}

    private boolean alphaSort() {
        return getFmmNodeDefinition().isAlphaSort();
    }

    private boolean isPeerLaunch() {
		return getFmmNodeDefinition() == this.launchHeadlineNode.getFmmNodeDefinition();
	}
	
	private FmmHeadlineNode getLogicalParentHeadlineNode() {
		return isPeerLaunch() ?
				getParentFmmHeadlineNode() : this.launchHeadlineNode;
	}

	@Override
	protected void manageButtonState() {
		if(this.buttonOk == null) {  // to support late initialization
			return;
		}
		this.buttonOk.setVisibility(this.headlineWidget.isMinimumInput() ? View.VISIBLE : View.INVISIBLE);
		this.buttonApply.setVisibility(this.headlineWidget.isMinimumInput() ? View.VISIBLE : View.INVISIBLE);
		this.headlineWidget.setDataStatus(this.headlineWidget.isMinimumInput());
	}

	private void createHeadlineNode(boolean bOkButtonEvent) {
		FmmHeadlineNode theNewHeadlineNode = FmmDatabaseMediator.getActiveMediator().newChildHeadlineNode(
			getFmmNodeDefinition(),  // type to create
			this.headlineWidget.getText().toString(),
			this.logicalParentHeadlineNode,  // link to this parent node
			getPeerNode(),
			this.lastRadioButton == null ? false : this.lastRadioButton.isChecked() );  // bSequenceBeforeFlag
		if(theNewHeadlineNode != null) {
			this.treeViewAdapter.addNewHeadlineNode(theNewHeadlineNode);
			GcgHelper.makeToast(this.fmmNodeTypeWidget.getText() + " created.");
			if(bOkButtonEvent && this.editNewHeadlineNode.isChecked()) {
				this.treeViewAdapter.editTreeNode(theNewHeadlineNode);
			}
		} else {
			GcgHelper.makeToast("ERROR:  Unable to create " + this.fmmNodeTypeWidget.getText() + " " + this.headlineWidget.getText().toString());
		}
	}

	private FmmHeadlineNode getPeerNode() {
		if(isPeerLaunch()) {
			return this.launchHeadlineNode;
		}
		return null;
	}

	@Override
	protected void onClickButtonOk() {
		createHeadlineNode(true);
		this.gcgActivity.stopDialog();
	}

	@Override
	protected void onClickButtonApply() {
		createHeadlineNode(false);
		this.headlineWidget.setText("");
		if(this.sequenceLayout.getVisibility() == View.GONE && ! getFmmNodeDefinition().isAlphaSort()) {
			this.sequenceLayout.setVisibility(View.VISIBLE);
			this.firstRadioButton = (RadioButton) this.dialogBodyView.findViewById(R.id.first__radio_button);
			this.firstRadioButton.setText("as first " + getFmmNodeDefinition().getLabelText());
			this.lastRadioButton = (RadioButton) this.dialogBodyView.findViewById(R.id.last__radio_button);
			this.lastRadioButton.setText("as last " + getFmmNodeDefinition().getLabelText());
		}
	}

	@Override
	public void initFdkDictationResultsConsumerMap() {
		addFdkDictationResultsConsumer(this.headlineWidget);
		this.currentFdkDictationResultsConsumer = this.headlineWidget;
		fdkFocusConsumer(this.currentFdkDictationResultsConsumer);
	}

}
