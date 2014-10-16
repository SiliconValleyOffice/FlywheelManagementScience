/* @(#)HeadlineNodeAdoptOrphanDialog.java
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
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.flywheelms.gcongui.gcg.activity.GcgActivity;
import com.flywheelms.gcongui.gcg.container.GcgContainerGroupBoxLinear;
import com.flywheelms.gcongui.gcg.helper.GcgHelper;
import com.flywheelms.gcongui.gcg.interfaces.GcgGuiable;
import com.flywheelms.gcongui.gcg.treeview.GcgTreeViewAdapter;
import com.flywheelms.gcongui.gcg.widget.edit_text.GcgWidgetGenericEditText;
import com.flywheelms.library.R;
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.enumerator.ChildNodeType;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmCompletionNode;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;
import com.flywheelms.library.fms.helper.FmsSearchHelper;
import com.flywheelms.library.fms.widget.FmmHeadlineNodeWidgetSpinner;
import com.flywheelms.library.fms.widget.text_view.FmmNodeTypeWidgetTextView;
import com.flywheelms.library.fms.widget.text_view.HeadlineWidgetTextView;

import java.util.ArrayList;

public abstract class HeadlineNodeAdoptOrphanDialog extends FmsCancelOkApplyFdkDialog {

	protected GcgTreeViewAdapter treeViewAdapter;
    protected FmmNodeDefinition orphanFmmNodeDefinition;
    protected FmmCompletionNode parentNode;
    protected int parentNodeChildCount;
    protected FmmCompletionNode launchNode;
    protected int launchNodeSequence;
    protected FmmNodeTypeWidgetTextView fmmNodeTypeWidget;
	protected HeadlineWidgetTextView headlineWidgetTextView;
	protected GcgContainerGroupBoxLinear orphanSpinnerParentViewGroup;
	protected FmmHeadlineNodeWidgetSpinner adoptionCandidateWidgetSpinner;
	protected CheckBox filterHeadlinesCheckbox;
	protected GcgWidgetGenericEditText filterHeadlineCriteria;
    protected RadioButton firstRadioButton;
    protected RadioButton lastRadioButton;
	protected ArrayList<GcgGuiable> unfilteredGcgGuiableList;
	protected ArrayList<GcgGuiable> filterResultsNodeList = new ArrayList<GcgGuiable>();
    protected boolean isParentNodeLaunch;

    public HeadlineNodeAdoptOrphanDialog(
            GcgActivity aLibraryActivity,
            GcgTreeViewAdapter aTreeViewAdapter,
            FmmNodeDefinition anOrphanFmmNodeDefinition,
            FmmHeadlineNode aParentNode ) {
        this(
                aLibraryActivity,
                aTreeViewAdapter,
                anOrphanFmmNodeDefinition,
                aParentNode,
                0,
                aParentNode,
                0 );
    }

	public HeadlineNodeAdoptOrphanDialog(
            GcgActivity aLibraryActivity,
            GcgTreeViewAdapter aTreeViewAdapter,
            FmmNodeDefinition anOrphanFmmNodeDefinition,
            FmmHeadlineNode aParentNode,
            int aParentNodeChildCount,
            FmmHeadlineNode aLaunchNode,
            int aLaunchNodeSequence ) {
		super(aLibraryActivity, aParentNode);
		this.treeViewAdapter = aTreeViewAdapter;
        this.orphanFmmNodeDefinition = anOrphanFmmNodeDefinition;
        this.launchNode = (FmmCompletionNode) aLaunchNode;
        this.launchNodeSequence = aLaunchNodeSequence;
        this.parentNode = (FmmCompletionNode) aParentNode;
        this.parentNodeChildCount = aParentNodeChildCount;
        this.isParentNodeLaunch = this.parentNode == this.launchNode;
		initializeDialogBodyLate();
		initFdkHostSupport();
	}

	@Override
	protected int getCustomDialogContentsResourceId() {
		return R.layout.fmm__headline_node__adopt_orphan__dialog;
	}

    protected abstract ChildNodeType getOrphanType();

    protected abstract void initializeOrphanSpinner(LinearLayout aFilterPanelLayout);

	@Override
	protected void initializeDialogBody() {
		return;
	}

	protected void initializeDialogBodyLate() {
		super.initializeDialogBody();
        initializeParentGroupBox();
        initializeSequenceGroupBox();
		initializeAdoptionCandidateGroupBox();
		manageButtonState();
	}

    private void initializeSequenceGroupBox() {
        this.firstRadioButton = (RadioButton) this.dialogBodyView.findViewById(R.id.first__radio_button);
        this.lastRadioButton = (RadioButton) this.dialogBodyView.findViewById(R.id.last__radio_button);
        this.headlineWidgetTextView = (HeadlineWidgetTextView) this.dialogBodyView.findViewById(R.id.headline__launch_node);
        if(getOrphanType() == ChildNodeType.PRIMARY__ALPHA_SORT) {
            this.firstRadioButton.setVisibility(View.GONE);
            this.lastRadioButton.setVisibility(View.GONE);
            this.headlineWidgetTextView.setVisibility(View.GONE);
        } else {
            this.dialogBodyView.findViewById(R.id.alert__alpha_sort).setVisibility(View.GONE);
            if(this.isParentNodeLaunch) {
                this.firstRadioButton.setText("as first " + this.orphanFmmNodeDefinition.getName());
                if(this.parentNodeChildCount > 0) {
                    this.lastRadioButton.setVisibility(View.GONE);
                } else {
                    this.lastRadioButton.setText("as last " + this.orphanFmmNodeDefinition.getName());
                }
                this.headlineWidgetTextView.setVisibility(View.GONE);
            } else {
                this.firstRadioButton.setText(R.string.fms__radio_before);
                this.lastRadioButton.setText(R.string.fms__radio_after);
                this.headlineWidgetTextView.setFmmHeadlineNode(this.launchNode);
            }
        }
    }

    private void initializeParentGroupBox() {
        this.fmmNodeTypeWidget = (FmmNodeTypeWidgetTextView) this.dialogBodyView.findViewById(R.id.fmm_node__type__parent_node);
        this.fmmNodeTypeWidget.setText(getFmmHeadlineNode().getFmmNodeDefinition().getLabelTextResourceId());
        this.headlineWidgetTextView = (HeadlineWidgetTextView) this.dialogBodyView.findViewById(R.id.headline__parent_node);
        this.headlineWidgetTextView.setText(getFmmHeadlineNode().getDataText());
    }

    private void initializeAdoptionCandidateGroupBox() {
//		LinearLayout theDispositionLayout = (LinearLayout) LayoutInflater.from(this.gcgActivity).inflate(getAdoptionCandidateLayoutResourceId(), this.customContentsContainer, false);
//		this.customContentsContainer.addView(theDispositionLayout, 0);
		this.filterHeadlinesCheckbox = (CheckBox) this.dialogBodyView.findViewById(R.id.filter_headlines__checkbox);
		this.filterHeadlinesCheckbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton aButtonView, boolean bChecked) {
                HeadlineNodeAdoptOrphanDialog.this.toggleHeadlineSearch(bChecked);
            }
        });
		this.filterHeadlineCriteria = (GcgWidgetGenericEditText) this.dialogBodyView.findViewById(R.id.filter_criteria);
		this.filterHeadlineCriteria.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);
		this.filterHeadlineCriteria.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {  /*  N/A */ }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {  /*  N/A  */}

            @Override
            public void afterTextChanged(Editable s) {
                HeadlineNodeAdoptOrphanDialog.this.updateFilterResults();
            }
        });
		this.filterHeadlineCriteria.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int aKeyCode, KeyEvent aKeyEvent) {
	    		if(aKeyCode==KeyEvent.KEYCODE_ENTER && aKeyEvent.getAction() == KeyEvent.ACTION_UP) {
	    			HeadlineNodeAdoptOrphanDialog.this.updateFilterResults();
	    			return true;
	    		}
				return false;
			}
		});
		this.filterHeadlineCriteria.setEnabled(false);
        LinearLayout theAdoptionCandidateGroupBox = (LinearLayout) this.dialogBodyView.findViewById(R.id.group_box__adoption_candidate);
        initializeOrphanSpinner(theAdoptionCandidateGroupBox);
        this.adoptionCandidateWidgetSpinner.setGcgActivity(this.gcgActivity);
	}

    protected void updateFilterResults() {
		if(this.unfilteredGcgGuiableList.size() == 0) {
			return;
		}
		if(! this.filterHeadlineCriteria.isMinimumInput()) {
			this.adoptionCandidateWidgetSpinner.updateSpinnerData(this.unfilteredGcgGuiableList);
			return;
		}
		this.filterResultsNodeList.clear();
		ArrayList<String> theTokenList = FmsSearchHelper.tokenizeSearchCriteriaInLowerCase(this.filterHeadlineCriteria.getText().toString());
		for(GcgGuiable theGcgGuiable : this.unfilteredGcgGuiableList) {
			if(FmsSearchHelper.stringMatchesCriteria(theTokenList, theGcgGuiable.getDataText())) {
				this.filterResultsNodeList.add(theGcgGuiable);
			}
		}
		this.adoptionCandidateWidgetSpinner.updateSpinnerData(this.filterResultsNodeList);
		manageButtonState();
	}
	
	@SuppressWarnings("unchecked")
	protected void toggleHeadlineSearch(boolean bChecked) {
		this.adoptionCandidateWidgetSpinner.isFiltered(bChecked);
		this.filterHeadlineCriteria.setEnabled(bChecked);
		this.filterHeadlineCriteria.inputEnabled(bChecked);
		((View) this.filterHeadlineCriteria.getParent()).setBackgroundResource(
				bChecked ? R.color.gcg__dialog__background : R.color.gcg__sand_dollar);
		if(bChecked) {
			this.filterHeadlineCriteria.setDataStatus();
			this.unfilteredGcgGuiableList = (ArrayList<GcgGuiable>) this.adoptionCandidateWidgetSpinner.getGcgGuiableList();
			updateFilterResults();
			addFdkDictationResultsConsumer(this.filterHeadlineCriteria, 0);
			fdkFocusConsumer(this.filterHeadlineCriteria);
		} else {
			this.adoptionCandidateWidgetSpinner.updateSpinnerData(this.unfilteredGcgGuiableList);
			removeFdkDictationResultsConsumer(this.filterHeadlineCriteria);
			fdkFocusConsumer(this.adoptionCandidateWidgetSpinner);
		}
	}

	protected void toastAdoptionResult(boolean bIsSuccessful) {
		StringBuffer theStringBuffer = new StringBuffer();
		if(bIsSuccessful) {
			theStringBuffer.append("Adopted orphan ");
		} else {
			theStringBuffer.append("Fatal Error:  Could not adopt orphan ");
		}
		theStringBuffer.append(
			((FmmHeadlineNode) this.adoptionCandidateWidgetSpinner.getSelectedItem()).getFmmNodeDefinition().getLabelText() +
			":  " + ((FmmHeadlineNode) this.adoptionCandidateWidgetSpinner.getSelectedItem()).getFmmNodeDefinition().getDataText() +
				" into " + getFmmNodeDefinition().getLabelText() + ": " + getFmmHeadlineNode().getDataText() );
		GcgHelper.makeToast(theStringBuffer.toString());
	}
	
	@Override
	protected void onClickButtonOk() {
		if(adoptOrphanHeadlineNode()) {
			this.treeViewAdapter.rebuildTreeView();
		}
		super.onClickButtonOk();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onClickButtonApply() {
		adoptOrphanHeadlineNode();
		this.treeViewAdapter.rebuildTreeView();
		this.adoptionCandidateWidgetSpinner.updateSpinnerData();
		this.unfilteredGcgGuiableList = (ArrayList<GcgGuiable>) this.adoptionCandidateWidgetSpinner.getGcgGuiableList();
		updateFilterResults();
		manageButtonState();
	}

	@Override
	protected void manageButtonState() {
		if(this.buttonApply == null || this.buttonOk == null) {
			return;
		}
		if(this.adoptionCandidateWidgetSpinner.getCount() > 0) {
			this.buttonOk.setVisibility(View.VISIBLE);
			this.buttonApply.setVisibility(View.VISIBLE);
		} else {
			this.buttonOk.setVisibility(View.INVISIBLE);
			this.buttonApply.setVisibility(View.INVISIBLE);
		}
	}

	@Override
	public void initFdkDictationResultsConsumerMap() {
		addFdkDictationResultsConsumer(this.adoptionCandidateWidgetSpinner);
		this.currentFdkDictationResultsConsumer = this.adoptionCandidateWidgetSpinner;
		fdkFocusConsumer(this.currentFdkDictationResultsConsumer);
	}

    protected boolean adoptOrphanHeadlineNode() {
        boolean theAdoptionStatus;
        switch(getOrphanType()) {
            case PRIMARY:
                theAdoptionStatus = adoptPrimaryOrphanIntoParent();
                break;
            case PRIMARY__ALPHA_SORT:
                theAdoptionStatus = adoptPrimaryOrphanIntoParentAlphaSort();
                break;
            default:
                theAdoptionStatus = false;
        }
        toastAdoptionResult(theAdoptionStatus);
        return theAdoptionStatus;
    }

    public boolean adoptPrimaryOrphanIntoParent () {
        return FmmDatabaseMediator.getActiveMediator().adoptPrimaryOrphanIntoParent(
                (FmmCompletionNode) this.adoptionCandidateWidgetSpinner.getFmmNode(),
                this.parentNode,
                this.parentNode == this.launchNode ? null : this.launchNode,
                this.lastRadioButton == null ? false : this.lastRadioButton.isChecked(),
                true);  // atomic transaction
    }

    public boolean adoptPrimaryOrphanIntoParentAlphaSort () {
        return FmmDatabaseMediator.getActiveMediator().adoptPrimaryOrphanIntoParentAlphaSort(
                (FmmCompletionNode) this.adoptionCandidateWidgetSpinner.getFmmNode(),
                this.parentNode,
                true);  // atomic transaction
    }

}
