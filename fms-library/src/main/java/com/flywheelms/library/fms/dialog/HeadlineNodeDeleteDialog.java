/* @(#)HeadlineNodeDeleteDialog.java
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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.flywheelms.gcongui.gcg.activity.GcgActivity;
import com.flywheelms.gcongui.gcg.container.GcgContainerTabbedLayout;
import com.flywheelms.gcongui.gcg.container.tabbed.GcgTabSpec;
import com.flywheelms.gcongui.gcg.helper.GcgGuiHelper;
import com.flywheelms.gcongui.gcg.helper.GcgHelper;
import com.flywheelms.gcongui.gcg.widget.GcgWidgetZoomableHeading;
import com.flywheelms.library.R;
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;
import com.flywheelms.library.fms.treeview.filter.FmsTreeViewAdapter;
import com.flywheelms.library.fms.widget.FmmHeadlineNodeWidgetSpinner;
import com.flywheelms.library.fms.widget.spinner.SequencePositionWidgetSpinner;
import com.flywheelms.library.fms.widget.text_view.FmmNodeTypeWidgetTextView;
import com.flywheelms.library.fms.widget.text_view.HeadlineWidgetTextView;

import java.util.ArrayList;

public abstract class HeadlineNodeDeleteDialog  extends FmsCancelOkDialog {

	FmsTreeViewAdapter treeViewAdapter;
	protected FmmNodeTypeWidgetTextView fmmNodeTypeWidget;
	protected HeadlineWidgetTextView headlineWidgetTextView;
	protected DeleteDisposition primaryChildDeleteDisposition;
	protected DeleteDisposition secondaryChildDeleteDisposition;
	protected DeleteDisposition primaryLinkDeleteDisposition;
	protected LinearLayout dispositionContainerLayout;
	protected int dispositionTabCount;

	public HeadlineNodeDeleteDialog(GcgActivity aLibraryActivity, FmsTreeViewAdapter aTreeViewAdapter, FmmHeadlineNode aHeadlineNode) {
		super(aLibraryActivity, aHeadlineNode);
		this.treeViewAdapter = aTreeViewAdapter;
	}

	@Override
	protected int getDialogTitleIconResourceId() {
		return getFmmNodeDefinition().getDialogDrawableResourceId();
	}

	@Override
	protected int getDialogTitleStringResourceId() {
		return R.string.fms__delete;
	}

	@Override
	protected int getCustomDialogContentsResourceId() {
		return R.layout.fmm__headline_node__delete__dialog;
	}

	protected int getPrimaryChildrenDispositionLayoutResourceId() { return 0; }

	protected int getSecondaryChildrenDispositionLayoutResourceId() { return 0; }

	protected int getPrimaryLinkDispositionLayoutResourceId() { return 0; }

	@Override
	protected void initializeDialogBody() {
		super.initializeDialogBody();
		this.fmmNodeTypeWidget = (FmmNodeTypeWidgetTextView) this.dialogBodyView.findViewById(R.id.fmm_node__type);
		this.fmmNodeTypeWidget.setText(getFmmNodeDefinition().getLabelTextResourceId());
		this.headlineWidgetTextView = (HeadlineWidgetTextView) this.dialogBodyView.findViewById(R.id.headline);
		this.headlineWidgetTextView.setText(GcgGuiHelper.getColorString(getFmmHeadlineNode().getDataText(), Color.RED));
		initializeDispositionLayout();
	}

	private void initializeDispositionLayout() {
		this.primaryChildDeleteDisposition = new DeleteDisposition();
		this.secondaryChildDeleteDisposition = new DeleteDisposition();
		this.primaryLinkDeleteDisposition = new DeleteDisposition();
		this.primaryLinkDeleteDisposition.setLinked(true);
        this.primaryChildDeleteDisposition.setDispositionHeadlineNodeList(getPrimaryChildHeadlineNodeList());
        this.secondaryChildDeleteDisposition.setDispositionHeadlineNodeList(getSecondaryChildHeadlineNodeList());
		this.primaryLinkDeleteDisposition.setDispositionHeadlineNodeList(getPrimaryLinkHeadlineNodeList());
		this.primaryChildDeleteDisposition.setCanOrphan(canOrphanPrimaryChild());
		this.secondaryChildDeleteDisposition.setCanOrphan(canOrphanSecondaryChild());
		this.primaryLinkDeleteDisposition.setCanOrphan(canOrphanPrimaryLink());
		this.dispositionTabCount = this.primaryChildDeleteDisposition.getCount() > 0 ? 1 : 0;
		this.dispositionTabCount += this.secondaryChildDeleteDisposition.getCount() > 0 ? 1 : 0;
		this.dispositionTabCount += this.primaryLinkDeleteDisposition.getCount() > 0 ? 1 : 0;
		if(this.dispositionTabCount == 0) {  // nothing to initialize
			return;
		}
		this.dispositionContainerLayout = new GcgContainerTabbedLayout(this.dialogBodyView.getContext());
		((GcgContainerTabbedLayout) this.dispositionContainerLayout).setup();
//		LinearLayout theTargetNodeLayout = (LinearLayout) this.dialogBodyView.findViewById(R.id.target_node);
		this.customContentsContainer.addView(this.dispositionContainerLayout);
//		this.dialogBodyView.addView(this.dispositionContainerLayout, 1);
		if(this.primaryChildDeleteDisposition.getCount() > 0) {
			initializeDispositionOfPrimaryChildrenLayout();
		}
		if(this.secondaryChildDeleteDisposition.getCount() > 0) {
			initializeDispositionOfSecondaryChildrenLayout();
		}
		if(this.primaryLinkDeleteDisposition.getCount() > 0) {
			initializeDispositionOfPrimaryLinkNodesLayout();
		}
		((GcgContainerTabbedLayout) this.dispositionContainerLayout).setCurrentTab(0);
	}

	protected void initializeDispositionOfPrimaryChildrenLayout() {
		buildDispositionView(
				getPrimaryChildrenDispositionLayoutResourceId(),
				getFmmHeadlineNode().getFmmNodeDefinition().getPrimaryChildNodeDefinition(),
				this.primaryChildDeleteDisposition );
	}

	protected void initializeDispositionOfSecondaryChildrenLayout() {
		buildDispositionView(
				getSecondaryChildrenDispositionLayoutResourceId(),
				getFmmHeadlineNode().getFmmNodeDefinition().getSecondaryChildNodeDefinition(),
				this.secondaryChildDeleteDisposition );
	}
	
	protected void initializeDispositionOfPrimaryLinkNodesLayout() {
		buildDispositionView(
				getPrimaryLinkDispositionLayoutResourceId(),
				getFmmHeadlineNode().getFmmNodeDefinition().getPrimaryLinkNodeDefinition(),
				this.primaryLinkDeleteDisposition );
	}
	
	private void buildDispositionView(
			int aDispositionLayoutResourceId,
			FmmNodeDefinition aDependentNodeDefinition,
			final DeleteDisposition aDeleteDisposition ) {
		aDeleteDisposition.setDispositionLayout((LinearLayout) this.gcgActivity.getLayoutInflater().inflate(aDispositionLayoutResourceId, this.dispositionContainerLayout, false));
		GcgTabSpec theGcgTabSpec = new GcgTabSpec(aDeleteDisposition.getDispositionLayout(), aDependentNodeDefinition.getLabelDrawableResourceId(), aDependentNodeDefinition.getLabelTextResourceId(), true);
		((GcgContainerTabbedLayout) this.dispositionContainerLayout).addTab(theGcgTabSpec);
		GcgWidgetZoomableHeading theHeadingWidget = (GcgWidgetZoomableHeading) aDeleteDisposition.getDispositionLayout().findViewById(R.id.disposition__heading);
		theHeadingWidget.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				HeadlineNodeDeleteDialog.this.gcgActivity.startDialog(new HeadlineNodeListZoomDialog(
							HeadlineNodeDeleteDialog.this.gcgActivity,
							aDeleteDisposition.getDispositionNodeIdStringList() ));
			}
		});
		aDeleteDisposition.setChoiceDeleteButton((RadioButton) aDeleteDisposition.getDispositionLayout().findViewById(R.id.choice__delete));
		aDeleteDisposition.setChoiceOrphanButton((RadioButton) aDeleteDisposition.getDispositionLayout().findViewById(R.id.choice__orphan));
		aDeleteDisposition.setChoiceMoveButton((RadioButton) aDeleteDisposition.getDispositionLayout().findViewById(R.id.choice__move));
		String theNodeLabelText = aDependentNodeDefinition.getLabelText();
		if(aDeleteDisposition.getCount() == 1) {
			theHeadingWidget.setText("Disposition of 1 " + theNodeLabelText);
			aDeleteDisposition.getChoiceDeleteButton().setText("Delete the " + theNodeLabelText);
			if(aDeleteDisposition.hasChoiceOrphanButton()) {
				aDeleteDisposition.getChoiceOrphanButton().setText("Orphan the " + theNodeLabelText);
			}
			aDeleteDisposition.getChoiceMoveButton().setText("Move the " + theNodeLabelText + " to another " + getFmmHeadlineNode().getFmmNodeDefinition().getLabelText());
		} else {
			theHeadingWidget.setText("Disposition of " + aDeleteDisposition.getCount() + " " + theNodeLabelText + "s");
			aDeleteDisposition.getChoiceDeleteButton().setText("Delete the " + aDeleteDisposition.getCount() + " " + theNodeLabelText + "s");
			if(aDeleteDisposition.hasChoiceOrphanButton()) {
				aDeleteDisposition.getChoiceOrphanButton().setText("Orphan the " + aDeleteDisposition.getCount() + " " + theNodeLabelText + "s");
			}
			aDeleteDisposition.getChoiceMoveButton().setText("Move the " + aDeleteDisposition.getCount() + " " + theNodeLabelText + "s to another " + getFmmHeadlineNode().getFmmNodeDefinition().getLabelText());
		}
		aDeleteDisposition.setTargetWidgetSpinner(findTargetWidgetSpinner(aDeleteDisposition.getDispositionLayout()));
		aDeleteDisposition.setTargetParentWidgetSpinner(findTargetParentWidgetSpinner(aDeleteDisposition.getDispositionLayout()));
		aDeleteDisposition.setTargetGrandparentWidgetSpinner(findTargetGrandparentWidgetSpinner(aDeleteDisposition.getDispositionLayout()));
		aDeleteDisposition.setTargetGreatGrandparentWidgetSpinner(findTargetGreatGrandparentWidgetSpinner(aDeleteDisposition.getDispositionLayout()));
		initializeDispositionSpinners(aDeleteDisposition);
		aDeleteDisposition.setSequencePositionSpinner((SequencePositionWidgetSpinner) this.dialogBodyView.findViewById(R.id.list_position__spinner));
		pruneDispositionOptions();
		if(aDeleteDisposition.isSequencePositionSpinnerVisibile()) {
			initializeSequencePositionSpinner(aDeleteDisposition);
		}
	}

	private void initializeDispositionSpinners(DeleteDisposition aDeleteDisposition) {
        if(aDeleteDisposition.hasTargetGreatGrandparentWidgetSpinner()) {
            setInitialTargetGreatGrandparentSpinnerData(aDeleteDisposition);
            setInitialTargetGreatGrandparentSpinnerSelection(aDeleteDisposition);
            initializeTargetGreatGrandparentSpinnerListener(aDeleteDisposition);
        }
		if(aDeleteDisposition.hasTargetGrandparentWidgetSpinner()) {
			setInitialTargetGrandparentSpinnerData(aDeleteDisposition);
			setInitialTargetGrandparentSpinnerSelection(aDeleteDisposition);
			initializeTargetGrandparentSpinnerListener(aDeleteDisposition);
		}
		if(aDeleteDisposition.hasTargetParentWidgetSpinner()) {
			setInitialTargetParentSpinnerData(aDeleteDisposition);
			setInitialTargetParentSpinnerSelection(aDeleteDisposition);
			initializeTargetParentSpinnerListener(aDeleteDisposition);
		}
		setInitialTargetSpinnerData(aDeleteDisposition);
		initializeTargetSpinnerListener(aDeleteDisposition);  // want it to fire when we make the initial selection
		setInitialTargetSpinnerSelection(aDeleteDisposition);
	}

	private void pruneDispositionOptions() {
		if(this.primaryChildDeleteDisposition.getCount() > 0) {
			pruneDispositionOptions(this.primaryChildDeleteDisposition);
		}
		if(this.secondaryChildDeleteDisposition.getCount() > 0) {
			pruneDispositionOptions(this.secondaryChildDeleteDisposition);
		}
		if(this.primaryLinkDeleteDisposition.getCount() > 0) {
			pruneDispositionOptions(this.primaryLinkDeleteDisposition);
		}
	}

	private static void initializeSequencePositionSpinner(final DeleteDisposition aDeleteDisposition) {
		aDeleteDisposition.setSequencePositionSpinnerOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                aDeleteDisposition.checkChoiceMoveButtonIfVisible();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                aDeleteDisposition.checkChoiceMoveButtonIfVisible();
            }
        });
	}

	private static void pruneDispositionOptions(DeleteDisposition aDeleteDisposition ) {
		aDeleteDisposition.getChoiceMoveButton().setChecked(true);
        if(aDeleteDisposition.hasTargetGreatGrandparentWidgetSpinner()) {
            if(aDeleteDisposition.getTargetWidgetSpinner().getListSize() == 0) {
                aDeleteDisposition.getChoiceDeleteButton().setChecked(true);
                aDeleteDisposition.getChoiceMoveButton().setVisibility(View.GONE);
                aDeleteDisposition.getTargetGreatGrandparentWidgetSpinner().setVisibility(View.GONE);
                aDeleteDisposition.getTargetGrandparentWidgetSpinner().setVisibility(View.GONE);
                aDeleteDisposition.getTargetParentWidgetSpinner().setVisibility(View.GONE);
                aDeleteDisposition.getTargetWidgetSpinner().setVisibility(View.GONE);
                aDeleteDisposition.setSequencePositionSpinnerVisibility(View.GONE);
                aDeleteDisposition.enableNoMoveTargetTextView();
                aDeleteDisposition.selectChoiceOrphanOrDelete();
            }
        } else if(aDeleteDisposition.hasTargetGrandparentWidgetSpinner()) {
			if(aDeleteDisposition.getTargetWidgetSpinner().getListSize() == 0) {
				aDeleteDisposition.getChoiceDeleteButton().setChecked(true);
				aDeleteDisposition.getChoiceMoveButton().setVisibility(View.GONE);
				aDeleteDisposition.getTargetGrandparentWidgetSpinner().setVisibility(View.GONE);
				aDeleteDisposition.getTargetParentWidgetSpinner().setVisibility(View.GONE);
				aDeleteDisposition.getTargetWidgetSpinner().setVisibility(View.GONE);
				aDeleteDisposition.setSequencePositionSpinnerVisibility(View.GONE);
                aDeleteDisposition.enableNoMoveTargetTextView();
				aDeleteDisposition.selectChoiceOrphanOrDelete();
			}
		} else if(aDeleteDisposition.hasTargetParentWidgetSpinner()) {
			if(aDeleteDisposition.getTargetWidgetSpinner().getListSize() == 0) {
				aDeleteDisposition.getChoiceDeleteButton().setChecked(true);
				aDeleteDisposition.getChoiceMoveButton().setVisibility(View.GONE);
				aDeleteDisposition.getTargetParentWidgetSpinner().setVisibility(View.GONE);
				aDeleteDisposition.getTargetWidgetSpinner().setVisibility(View.GONE);
				aDeleteDisposition.setSequencePositionSpinnerVisibility(View.GONE);
                aDeleteDisposition.enableNoMoveTargetTextView();
				aDeleteDisposition.selectChoiceOrphanOrDelete();
			}
		} else if(aDeleteDisposition.getTargetWidgetSpinner().getListSize() == 0) {
			aDeleteDisposition.getChoiceDeleteButton().setChecked(true);
			aDeleteDisposition.getChoiceMoveButton().setVisibility(View.GONE);
			aDeleteDisposition.getTargetWidgetSpinner().setVisibility(View.GONE);
			aDeleteDisposition.setSequencePositionSpinnerVisibility(View.GONE);
            aDeleteDisposition.enableNoMoveTargetTextView();
			aDeleteDisposition.selectChoiceOrphanOrDelete();
		}
	}

//	private static void setDeleteOrOrphanOption(DeleteDisposition aDeleteDisposition) {
//		if(aDeleteDisposition.hasChoiceOrphanButton()) {
//			if(! aDeleteDisposition.canOrphan()) {
//				aDeleteDisposition.getChoiceOrphanButton().setVisibility(View.INVISIBLE);
//				aDeleteDisposition.getChoiceDeleteButton().setChecked(true);
//			} else {
//				aDeleteDisposition.getChoiceOrphanButton().setChecked(true);
//			}
//		} else {
//			aDeleteDisposition.getChoiceDeleteButton().setChecked(true);
//		}
//	}

    protected FmmHeadlineNodeWidgetSpinner findTargetGreatGrandparentWidgetSpinner(@SuppressWarnings("unused") LinearLayout aLinearLayout) {
        return null;
    }

	protected FmmHeadlineNodeWidgetSpinner findTargetGrandparentWidgetSpinner(@SuppressWarnings("unused") LinearLayout aLinearLayout) {
		return null;
	}

	protected abstract FmmHeadlineNodeWidgetSpinner findTargetParentWidgetSpinner(LinearLayout aLinearLayout);

	protected abstract FmmHeadlineNodeWidgetSpinner findTargetWidgetSpinner(LinearLayout aLinearLayout);

	protected ArrayList<? extends FmmHeadlineNode> getPrimaryChildHeadlineNodeList() { return new ArrayList<FmmHeadlineNode>(); }
	
	protected ArrayList<? extends FmmHeadlineNode> getSecondaryChildHeadlineNodeList() { return new ArrayList<FmmHeadlineNode>(); }
	
	protected ArrayList<? extends FmmHeadlineNode> getPrimaryLinkHeadlineNodeList() { return new ArrayList<FmmHeadlineNode>(); }
	
	protected boolean canOrphanPrimaryChild() {
		return true;
	}

    protected boolean alwaysDeletePrimaryChild() {
        return false;
    }
	
	protected boolean canOrphanSecondaryChild() {
		return true;
	}

    protected boolean alwaysDeleteSecondaryChild() {
        return false;
    }
	
	protected boolean canOrphanPrimaryLink() {
		return true;
	}

    protected void setInitialTargetGreatGrandparentSpinnerData(@SuppressWarnings("unused") DeleteDisposition aDeleteDisposition) { return; }

	protected void setInitialTargetGrandparentSpinnerData(@SuppressWarnings("unused") DeleteDisposition aDeleteDisposition) { return; }

	protected void setInitialTargetParentSpinnerData(DeleteDisposition aDeleteDisposition) {
		updateTargetParentWidgetSpinner(aDeleteDisposition);
	}

	protected void setInitialTargetSpinnerData(DeleteDisposition aDeleteDisposition) {
		updateTargetWidgetSpinner(aDeleteDisposition);
	}

    protected void setInitialTargetGreatGrandparentSpinnerSelection(DeleteDisposition aDeleteDisposition) {
        aDeleteDisposition.getTargetGreatGrandparentWidgetSpinner().setSelection(0);
        updateTargetGrandparentWidgetSpinner(aDeleteDisposition);
        aDeleteDisposition.checkChoiceMoveButtonIfVisible();
    }

	protected void setInitialTargetGrandparentSpinnerSelection(DeleteDisposition aDeleteDisposition) {
		aDeleteDisposition.getTargetGrandparentWidgetSpinner().setSelection(0);
		updateTargetParentWidgetSpinner(aDeleteDisposition);
		aDeleteDisposition.checkChoiceMoveButtonIfVisible();
	}

	protected void setInitialTargetParentSpinnerSelection(DeleteDisposition aDeleteDisposition) {
		aDeleteDisposition.getTargetParentWidgetSpinner().setSelection(0);
		updateTargetWidgetSpinner(aDeleteDisposition);
		aDeleteDisposition.checkChoiceMoveButtonIfVisible();
	}

	protected void setInitialTargetSpinnerSelection(DeleteDisposition aDeleteDisposition) {
		aDeleteDisposition.getTargetWidgetSpinner().setSelection(0);
	}

    protected void initializeTargetGreatGrandparentSpinnerListener(final DeleteDisposition aDeleteDisposition) {
        aDeleteDisposition.getTargetGreatGrandparentWidgetSpinner().setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                HeadlineNodeDeleteDialog.this.updateTargetGrandparentWidgetSpinner(aDeleteDisposition);
                aDeleteDisposition.checkChoiceMoveButtonIfVisible();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                aDeleteDisposition.checkChoiceMoveButtonIfVisible();
            }
        });
    }

	protected void initializeTargetGrandparentSpinnerListener(final DeleteDisposition aDeleteDisposition) {
		aDeleteDisposition.getTargetGrandparentWidgetSpinner().setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				HeadlineNodeDeleteDialog.this.updateTargetParentWidgetSpinner(aDeleteDisposition);
				aDeleteDisposition.checkChoiceMoveButtonIfVisible();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				aDeleteDisposition.checkChoiceMoveButtonIfVisible();
			}
		});
	}

	protected void initializeTargetParentSpinnerListener(final DeleteDisposition aDeleteDisposition) {
		aDeleteDisposition.getTargetParentWidgetSpinner().setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				HeadlineNodeDeleteDialog.this.updateTargetWidgetSpinner(aDeleteDisposition);
				aDeleteDisposition.checkChoiceMoveButtonIfVisible();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				aDeleteDisposition.checkChoiceMoveButtonIfVisible();
			}
		});
	}

	protected void initializeTargetSpinnerListener(final DeleteDisposition aDeleteDisposition) {
		aDeleteDisposition.getTargetWidgetSpinner().setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				HeadlineNodeDeleteDialog.this.updateSequencePositionSpinnerWidgetSpinner(aDeleteDisposition);
				aDeleteDisposition.checkChoiceMoveButtonIfVisible();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				aDeleteDisposition.checkChoiceMoveButtonIfVisible();
			}
		});
	}

	protected void updateSequencePositionSpinnerWidgetSpinner(DeleteDisposition aDeleteDisposition) {
		aDeleteDisposition.setSequencePositionSpinnerAtEnd(validateTargetBeforeExisting(aDeleteDisposition));
	}

	private boolean validateTargetBeforeExisting(DeleteDisposition aDeleteDisposition) {
		return this.treeViewAdapter.verifyNodeOrder(
				aDeleteDisposition.getTargetNodeIdString(), getFmmHeadlineNode().getNodeIdString());
	}

//	protected void pruneTargetSpinnerData(final DeleteDisposition aDeleteDisposition) {
//		aDeleteDisposition.getTargetHeadlineNodeWidgetSpinner().removeFmmHeadlineNode(getFmmHeadlineNode().getNodeIdString(), GcgWidgetSpinner.logical_position__NEXT);
//	}
    protected void updateTargetGrandparentWidgetSpinner(@SuppressWarnings("unused") final DeleteDisposition aDeleteDisposition) { return; }

	protected void updateTargetParentWidgetSpinner(@SuppressWarnings("unused") final DeleteDisposition aDeleteDisposition) { return; }

	protected void updateTargetWidgetSpinner(@SuppressWarnings("unused") final DeleteDisposition aDeleteDisposition) { return; }

	@Override
	protected void onClickButtonOk() {
		FmmDatabaseMediator.getActiveMediator().startTransaction();
		boolean isTransactionSuccess = true;
		if(this.primaryChildDeleteDisposition.getCount() > 0) {
			isTransactionSuccess = disposeOfPrimaryChildren();
		}
		if(isTransactionSuccess && this.secondaryChildDeleteDisposition.getCount() > 0) {
			isTransactionSuccess = disposeOfSecondaryChildren();
		}
		if(isTransactionSuccess && this.primaryLinkDeleteDisposition.getCount() > 0) {
			isTransactionSuccess = disposeOfPrimaryLinkNodes();
		}
		if(isTransactionSuccess) {
			isTransactionSuccess = deleteHeadlineNode();
		}
		if(isTransactionSuccess) { 
			this.treeViewAdapter.deleteHeadlineNode(getFmmHeadlineNode());
			GcgHelper.makeToast("Deleted " + this.fmmNodeTypeWidget.getText() + ":  " + this.headlineWidgetTextView.getText());
		} else {
			GcgHelper.makeToast("Faral Error:  Failed to delete " + this.fmmNodeTypeWidget.getText() + ":  " + this.headlineWidgetTextView.getText());
		}
		FmmDatabaseMediator.getActiveMediator().endTransaction(isTransactionSuccess);
		this.gcgActivity.stopDialog();
	}
	
	protected abstract boolean deleteHeadlineNode();

	protected boolean disposeOfPrimaryChildren() {
		boolean isTransactionSuccess;
		String theChildNodeLabelText = getFmmHeadlineNode().getFmmNodeDefinition().getPrimaryChildNodeDefinition().getLabelText();
		if(this.primaryChildDeleteDisposition.getChoiceDeleteButton().isChecked()) {
			isTransactionSuccess = deletePrimaryChildren();
			if(isTransactionSuccess) {
				if(this.primaryChildDeleteDisposition.getCount() == 1) {
					GcgHelper.makeToast("Deleted " + theChildNodeLabelText + " for" + this.fmmNodeTypeWidget.getText() + ":  " + this.headlineWidgetTextView.getText());
				} else {
					GcgHelper.makeToast("Deleted " + theChildNodeLabelText + "s for" + this.fmmNodeTypeWidget.getText() + ":  " + this.headlineWidgetTextView.getText());
				}
			} else {
				if(this.primaryChildDeleteDisposition.getCount() == 1) {
					GcgHelper.makeToast("Fatal Error:  Failed to delete " + theChildNodeLabelText + " for " + this.fmmNodeTypeWidget.getText() + ":  " + this.headlineWidgetTextView.getText());
				} else {
					GcgHelper.makeToast("Fatal Error:  Failed to delete " + theChildNodeLabelText + "s for " + this.fmmNodeTypeWidget.getText() + ":  " + this.headlineWidgetTextView.getText());
				}
			}
		} else if(this.primaryChildDeleteDisposition.hasChoiceOrphanButton() && this.primaryChildDeleteDisposition.getChoiceOrphanButton().isChecked()) {
			isTransactionSuccess = orphanPrimaryChildren();
			if(isTransactionSuccess) {
				if(this.primaryChildDeleteDisposition.getCount() == 1) {
					GcgHelper.makeToast("Orphaned " + theChildNodeLabelText + " for" + this.fmmNodeTypeWidget.getText() + ":  " + this.headlineWidgetTextView.getText());
				} else {
					GcgHelper.makeToast("Orphaned " + theChildNodeLabelText + "s for" + this.fmmNodeTypeWidget.getText() + ":  " + this.headlineWidgetTextView.getText());
				}
			} else {
				if(this.primaryChildDeleteDisposition.getCount() == 1) {
					GcgHelper.makeToast("Fatal Error:  Failed to orphan " + theChildNodeLabelText + " for " + this.fmmNodeTypeWidget.getText() + ":  " + this.headlineWidgetTextView.getText());
				} else {
					GcgHelper.makeToast("Fatal Error:  Failed to orphan " + theChildNodeLabelText + "s for " + this.fmmNodeTypeWidget.getText() + ":  " + this.headlineWidgetTextView.getText());
				}
			}
		} else {
			isTransactionSuccess = movePrimaryChildrenToNewParent();
			if(isTransactionSuccess) {
				if(this.primaryChildDeleteDisposition.getCount() == 1) {
					GcgHelper.makeToast("Moved " + theChildNodeLabelText + " to" + this.fmmNodeTypeWidget.getText() + ":  " + this.headlineWidgetTextView.getText());
				} else {
					GcgHelper.makeToast("Moved " + theChildNodeLabelText + "s to" + this.fmmNodeTypeWidget.getText() + ":  " + this.headlineWidgetTextView.getText());
				}
			} else {
				if(this.primaryChildDeleteDisposition.getCount() == 1) {
					GcgHelper.makeToast("Fatal Error:  Could not move " + theChildNodeLabelText + " to " + this.fmmNodeTypeWidget.getText() + ":  " + this.headlineWidgetTextView.getText());
				} else {
					GcgHelper.makeToast("Fatal Error:  Failed to delete " + theChildNodeLabelText + "s to " + this.fmmNodeTypeWidget.getText() + ":  " + this.headlineWidgetTextView.getText());
				}
			}
		}
		return isTransactionSuccess;
	}

	protected boolean deletePrimaryChildren() { return true; }
	
	protected boolean orphanPrimaryChildren() { return true; }

	protected boolean movePrimaryChildrenToNewParent() { return true; }
	
	protected boolean disposeOfSecondaryChildren() {
		boolean isTransactionSuccess;
		String theChildNodeLabelText = getFmmHeadlineNode().getFmmNodeDefinition().getPrimaryChildNodeDefinition().getLabelText();
		if(this.secondaryChildDeleteDisposition.getChoiceDeleteButton().isChecked()) {
			isTransactionSuccess = deleteSecondaryChildren();
			if(isTransactionSuccess) {
				if(this.secondaryChildDeleteDisposition.getCount() == 1) {
					GcgHelper.makeToast("Deleted " + theChildNodeLabelText + " for" + this.fmmNodeTypeWidget.getText() + ":  " + this.headlineWidgetTextView.getText());
				} else {
					GcgHelper.makeToast("Deleted " + theChildNodeLabelText + "s for" + this.fmmNodeTypeWidget.getText() + ":  " + this.headlineWidgetTextView.getText());
				}
			} else {
				if(this.secondaryChildDeleteDisposition.getCount() == 1) {
					GcgHelper.makeToast("Fatal Error:  Failed to delete " + theChildNodeLabelText + " for " + this.fmmNodeTypeWidget.getText() + ":  " + this.headlineWidgetTextView.getText());
				} else {
					GcgHelper.makeToast("Fatal Error:  Failed to delete " + theChildNodeLabelText + "s for " + this.fmmNodeTypeWidget.getText() + ":  " + this.headlineWidgetTextView.getText());
				}
			}
		} else if(this.secondaryChildDeleteDisposition.hasChoiceOrphanButton() && this.secondaryChildDeleteDisposition.getChoiceOrphanButton().isChecked()) {
			isTransactionSuccess = orphanSecondaryChildren();
			if(isTransactionSuccess) {
				if(this.secondaryChildDeleteDisposition.getCount() == 1) {
					GcgHelper.makeToast("Orphaned " + theChildNodeLabelText + " for" + this.fmmNodeTypeWidget.getText() + ":  " + this.headlineWidgetTextView.getText());
				} else {
					GcgHelper.makeToast("Orphaned " + theChildNodeLabelText + "s for" + this.fmmNodeTypeWidget.getText() + ":  " + this.headlineWidgetTextView.getText());
				}
			} else {
				if(this.secondaryChildDeleteDisposition.getCount() == 1) {
					GcgHelper.makeToast("Fatal Error:  Failed to orphan " + theChildNodeLabelText + " for " + this.fmmNodeTypeWidget.getText() + ":  " + this.headlineWidgetTextView.getText());
				} else {
					GcgHelper.makeToast("Fatal Error:  Failed to orphan " + theChildNodeLabelText + "s for " + this.fmmNodeTypeWidget.getText() + ":  " + this.headlineWidgetTextView.getText());
				}
			}
		} else {
			isTransactionSuccess = moveSecondaryChildrenToNewParent();
			if(isTransactionSuccess) {
				if(this.secondaryChildDeleteDisposition.getCount() == 1) {
					GcgHelper.makeToast("Moved " + theChildNodeLabelText + " to" + this.fmmNodeTypeWidget.getText() + ":  " + this.headlineWidgetTextView.getText());
				} else {
					GcgHelper.makeToast("Moved " + theChildNodeLabelText + "s to" + this.fmmNodeTypeWidget.getText() + ":  " + this.headlineWidgetTextView.getText());
				}
			} else {
				if(this.secondaryChildDeleteDisposition.getCount() == 1) {
					GcgHelper.makeToast("Fatal Error:  Could not move " + theChildNodeLabelText + " to " + this.fmmNodeTypeWidget.getText() + ":  " + this.headlineWidgetTextView.getText());
				} else {
					GcgHelper.makeToast("Fatal Error:  Failed to delete " + theChildNodeLabelText + "s to " + this.fmmNodeTypeWidget.getText() + ":  " + this.headlineWidgetTextView.getText());
				}
			}
		}
		return isTransactionSuccess;
	}

	protected boolean deleteSecondaryChildren() { return true; }
	
	protected boolean orphanSecondaryChildren() { return true; }

	protected boolean moveSecondaryChildrenToNewParent() { return true; }
	
	protected boolean disposeOfPrimaryLinkNodes() {
		boolean isTransactionSuccess = true;
		if(this.primaryLinkDeleteDisposition.isMove()) {
			isTransactionSuccess = movePrimaryLinkNodes();
		} else if(this.primaryLinkDeleteDisposition.isDelete()) {
				isTransactionSuccess = deletePrimaryLinkNodes();
		} else {
			isTransactionSuccess = orphanPrimaryLinkNodes();
		}
		return isTransactionSuccess;
	}

	protected boolean deletePrimaryLinkNodes() { return true; }
	
	protected boolean orphanPrimaryLinkNodes() { return true; }

	protected boolean movePrimaryLinkNodes() { return true; }
	
	protected class DeleteDisposition {

		protected FmmHeadlineNode targetHeadlineNodeException;
		protected ArrayList<? extends FmmHeadlineNode> dispositionHeadlineNodeList;
		protected RadioButton choiceDeleteButton;
		protected RadioButton choiceOrphanButton;
		protected RadioButton choiceMoveButton;
		protected LinearLayout dispositionLayout;
		protected FmmHeadlineNodeWidgetSpinner targetGreatGrandparentWidgetSpinner;
		protected FmmHeadlineNodeWidgetSpinner targetGrandparentWidgetSpinner;
		protected FmmHeadlineNodeWidgetSpinner targetParentWidgetSpinner;
		protected FmmHeadlineNodeWidgetSpinner targetWidgetSpinner;
		protected SequencePositionWidgetSpinner sequencePositionSpinner;
        protected TextView noMoveTargetTextView;
		protected boolean isLinked = false;
		protected boolean canOrphan = true;
		
		public DeleteDisposition() {
			this.targetHeadlineNodeException = getFmmHeadlineNode();
		}
		public boolean canOrphan() {
			return this.canOrphan;
		}
		public void setCanOrphan(boolean aBoolean) {
			this.canOrphan = aBoolean;
		}
		public FmmHeadlineNode getTargetHeadlineNodeException() {
			return this.targetHeadlineNodeException;
		}
		public int getCount() {
			return this.dispositionHeadlineNodeList.size();
		}
		public String getTargetNodeIdString() {
			return ((FmmHeadlineNode) this.targetWidgetSpinner.getSelectedItem()).getNodeIdString();
		}
        public boolean hasTargetGreatGrandparentWidgetSpinner() {
            return this.targetGreatGrandparentWidgetSpinner != null;
        }
		public boolean hasTargetGrandparentWidgetSpinner() {
			return this.targetGrandparentWidgetSpinner != null;
		}
		public boolean hasTargetParentWidgetSpinner() {
			return this.targetParentWidgetSpinner != null;
		}
		public ArrayList<? extends FmmHeadlineNode> getDispositionNodeIdStringList() {
			return this.dispositionHeadlineNodeList;
		}
		public void setDispositionHeadlineNodeList(ArrayList<? extends FmmHeadlineNode> aDispositionList) {
			this.dispositionHeadlineNodeList = aDispositionList;
		}
		public RadioButton getChoiceDeleteButton() {
			return this.choiceDeleteButton;
		}
		public void setChoiceDeleteButton(RadioButton aChoiceDeleteButton) {
			this.choiceDeleteButton = aChoiceDeleteButton;
		}
		public RadioButton getChoiceOrphanButton() {
			return this.choiceOrphanButton;
		}
		public boolean hasChoiceOrphanButton() {
			return this.choiceOrphanButton != null;
		}
		public void selectChoiceOrphanOrDelete() {
			if(hasChoiceOrphanButton()) {
				if(! canOrphan()) {
					getChoiceOrphanButton().setVisibility(View.INVISIBLE);
					getChoiceDeleteButton().setChecked(true);
				} else {
					getChoiceOrphanButton().setChecked(true);
				}
			} else {
				getChoiceDeleteButton().setChecked(true);
			}
		}
		public void setChoiceOrphanButton(RadioButton choiceOrphanButton) {
			this.choiceOrphanButton = choiceOrphanButton;
		}
		public RadioButton getChoiceMoveButton() {
			return this.choiceMoveButton;
		}
		public void checkChoiceMoveButtonIfVisible() {
			if(this.choiceMoveButton.getVisibility() == View.VISIBLE) {
				this.choiceMoveButton.setChecked(true);
			}
		}
		public void setChoiceMoveButton(RadioButton aChoiceMoveButton) {
			this.choiceMoveButton = aChoiceMoveButton;
		}
		public boolean isDelete() {
			return this.choiceDeleteButton.isChecked();
		}
		public boolean isOrphan() {
			return hasChoiceOrphanButton() && this.choiceOrphanButton.isChecked();
		}
		public boolean isMove() {
			return this.choiceMoveButton.isChecked();
		}
		public LinearLayout getDispositionLayout() {
			return this.dispositionLayout;
		}
		public void setDispositionLayout(LinearLayout aDispositionLayout) {
			this.dispositionLayout = aDispositionLayout;
		}
        public FmmHeadlineNodeWidgetSpinner getTargetGreatGrandparentWidgetSpinner() {
            return this.targetGreatGrandparentWidgetSpinner;
        }
		public FmmHeadlineNodeWidgetSpinner getTargetGrandparentWidgetSpinner() {
			return this.targetGrandparentWidgetSpinner;
		}
        public void setTargetGreatGrandparentWidgetSpinner(FmmHeadlineNodeWidgetSpinner aHeadlineNodeWidgetSpinner) {
            this.targetGreatGrandparentWidgetSpinner = aHeadlineNodeWidgetSpinner;
        }
		public void setTargetGrandparentWidgetSpinner(FmmHeadlineNodeWidgetSpinner aHeadlineNodeWidgetSpinner) {
			this.targetGrandparentWidgetSpinner = aHeadlineNodeWidgetSpinner;
		}
		public FmmHeadlineNodeWidgetSpinner getTargetParentWidgetSpinner() {
			return this.targetParentWidgetSpinner;
		}
		public void setTargetParentWidgetSpinner(FmmHeadlineNodeWidgetSpinner aHeadlineNodeWidgetSpinner) {
			this.targetParentWidgetSpinner = aHeadlineNodeWidgetSpinner;
		}
		public FmmHeadlineNodeWidgetSpinner getTargetWidgetSpinner() {
			return this.targetWidgetSpinner;
		}
		public void setTargetWidgetSpinner(FmmHeadlineNodeWidgetSpinner aHeadlineNodeWidgetSpinner) {
			this.targetWidgetSpinner = aHeadlineNodeWidgetSpinner;
		}
		public void setSequencePositionSpinnerVisibility(int aVisibility) {
			if(this.sequencePositionSpinner != null) {
                this.sequencePositionSpinner.setVisibility(aVisibility);
            }
		}
        public void setSequencePositionSpinnerAtEnd(boolean bAtEnd) {
            if(this.sequencePositionSpinner != null) {
                this.sequencePositionSpinner.setSequenceAtEnd(bAtEnd);
            }
        }
		public void setSequencePositionSpinner(SequencePositionWidgetSpinner aSequencePositionSpinner) {
			this.sequencePositionSpinner = aSequencePositionSpinner;
		}
        public TextView getNoMoveTargetTextView() {
            if(this.noMoveTargetTextView == null) {
                this.noMoveTargetTextView = (TextView) this.dispositionLayout.findViewById(R.id.fms__no_move_target);
            }
            return this.noMoveTargetTextView;
        }
        public void setNoMoveTargetTextView(TextView aTextView) {
            this.noMoveTargetTextView = aTextView;
        }
        public void enableNoMoveTargetTextView() {
            if(getNoMoveTargetTextView() != null) {
                this.noMoveTargetTextView.setVisibility(View.VISIBLE);
            }
        }
		public boolean isLinked() {
			return this.isLinked;
		}
		public void setLinked(boolean bLinked) {
			this.isLinked = bLinked;
		}

        public void setSequencePositionSpinnerOnItemSelectedListener(OnItemSelectedListener anOnItemSelectedListener) {
            if(this.sequencePositionSpinner != null) {
                this.sequencePositionSpinner.setOnItemSelectedListener(anOnItemSelectedListener);
            }
        }
        public boolean isSequencePositionSpinnerVisibile() {
            return this.sequencePositionSpinner == null ? false : this.sequencePositionSpinner.getVisibility() == View.VISIBLE;
        }

        public boolean isSequencePositionSpinnerAtEnd() {
            return this.sequencePositionSpinner == null ? false : this.sequencePositionSpinner.getSelectedItem().getDataText().equals("End");
        }
    }

}
